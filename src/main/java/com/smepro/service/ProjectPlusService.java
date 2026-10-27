package com.smepro.service;

import com.smepro.dto.DashboardSummaryDto;
import com.smepro.dto.DashboardSummaryDto.*;
import com.smepro.entity.*;
import com.smepro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectPlusService {

    /* ===== Repositories ===== */
    @Autowired private ProjectRepository        projectRepo;
    @Autowired private ProjectPhaseRepository   phaseRepo;
    @Autowired private WbsElementRepository     wbsRepo;
    @Autowired private ProjectResourceRepository resourceRepo;
    @Autowired private BudgetLineRepository     budgetRepo;
    @Autowired private TimesheetEntryRepository  timesheetRepo;
    @Autowired private MaterialResourceRepository materialRepo;
    @Autowired private ApprovalInboxRepository   approvalRepo;
    @Autowired private ProjectClosureRepository  closureRepo;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    /* =========================================
       DASHBOARD — My Tasks (หน้าแรก)
       ========================================= */

    public DashboardSummaryDto getDashboardSummary(int appId, String username) {
        DashboardSummaryDto dto = new DashboardSummaryDto();

        // 1. นับโครงการแยก status
        List<Object[]> statusCounts = projectRepo.countByStatus(appId);
        int total = 0, onTrack = 0, atRisk = 0, delayed = 0;
        for (Object[] row : statusCounts) {
            String st = (String) row[0];
            long cnt = (long) row[1];
            total += cnt;
            if ("ON_TRACK".equals(st))  onTrack  += cnt;
            if ("AT_RISK".equals(st))   atRisk   += cnt;
            if ("DELAYED".equals(st))   delayed  += cnt;
        }
        dto.setTotalProjects(total);
        dto.setOnTrackCount(onTrack);
        dto.setAtRiskCount(atRisk);
        dto.setDelayedCount(delayed);

        // 2. Task ของฉันที่รอทำ
        List<WbsElement> pendingTasks = wbsRepo.findMyPendingTasks(appId, username);
        dto.setMyPendingTaskCount(pendingTasks.size());
        dto.setMyPendingTasks(pendingTasks.stream()
                .map(this::toTaskRowDto)
                .collect(Collectors.toList()));

        // 3. Pending Approvals
        long pendingApproval = approvalRepo.countByApproverAndStatus(username, "PENDING");
        dto.setPendingApprovalCount((int) pendingApproval);

        // 4. Announcements (placeholder — ขยายได้ภายหลัง)
        dto.setAnnouncements(Collections.singletonList(
            new AnnouncementDto("Q3 Budget Approved",
                "The steering committee has approved additional resources for Phase 2.",
                "2 hours ago")));

        return dto;
    }

    /* =========================================
       ALL PROJECTS
       ========================================= */

    public List<Project> getAllProjects(int appId) {
        return projectRepo.findByAppIdOrderByCreatedAtDesc(appId);
    }

    public List<Project> getProjectsByStatus(int appId, String status) {
        return projectRepo.findByAppIdAndStatusOrderByCreatedAtDesc(appId, status);
    }

    public List<Project> getPipelineProjects(int appId) {
        return projectRepo.findByAppIdAndStatusIn(appId,
                Arrays.asList("PIPELINE", "PLANNING"));
    }

    public List<Project> getArchivedProjects(int appId) {
        return projectRepo.findByAppIdAndStatusOrderByUpdatedAtDesc(appId, "ARCHIVED");
    }

    public List<Project> searchProjects(int appId, String keyword) {
        return projectRepo.searchByKeyword(appId, keyword);
    }

    /* =========================================
       PROJECT DETAIL
       ========================================= */

    public Project getProjectById(Long projectId) {
        return projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found: " + projectId));
    }

    /* =========================================
       CREATE / UPDATE PROJECT
       ========================================= */

    @Transactional
    public Project createProject(Project project, int appId) {
        project.setAppId(appId);
        if (project.getProjectCode() == null || project.getProjectCode().isBlank()) {
            project.setProjectCode(generateProjectCode(appId));
        }
        project.setStatus("PLANNING");
        return projectRepo.save(project);
    }

    @Transactional
    public Project updateProject(Project updated) {
        Project existing = getProjectById(updated.getId());
        existing.setProjectName(updated.getProjectName());
        existing.setProjectType(updated.getProjectType());
        existing.setObjective(updated.getObjective());
        existing.setPriority(updated.getPriority());
        existing.setCostCenter(updated.getCostCenter());
        existing.setProjectManager(updated.getProjectManager());
        existing.setSponsor(updated.getSponsor());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());
        existing.setCurrency(updated.getCurrency());
        existing.setBudgetLimit(updated.getBudgetLimit());
        existing.setStatus(updated.getStatus());
        return projectRepo.save(existing);
    }

    /** คำนวณ progress โครงการใหม่จาก Task แล้วบันทึก */
    @Transactional
    public void recalculateProgress(Long projectId) {
        Double avg = wbsRepo.avgProgressByProject(projectId);
        Project p = getProjectById(projectId);
        p.setOverallProgress(avg != null ? avg.intValue() : 0);
        projectRepo.save(p);
    }

    /* =========================================
       PHASE
       ========================================= */

    public List<ProjectPhase> getPhasesByProject(Long projectId) {
        return phaseRepo.findByProjectIdOrderBySequenceNoAsc(projectId);
    }

    @Transactional
    public ProjectPhase createPhase(ProjectPhase phase, Long projectId) {
        Project project = getProjectById(projectId);
        phase.setProject(project);
        return phaseRepo.save(phase);
    }

    @Transactional
    public ProjectPhase approvePhaseGate(Long phaseId, String approver, String note, boolean approved) {
        ProjectPhase phase = phaseRepo.findById(phaseId)
                .orElseThrow(() -> new RuntimeException("Phase not found: " + phaseId));
        phase.setGateStatus(approved ? "APPROVED" : "REJECTED");
        phase.setApprover(approver);
        phase.setApprovalNote(note);
        phase.setApprovedAt(java.time.LocalDateTime.now());
        return phaseRepo.save(phase);
    }

    /* =========================================
       WBS & TASK
       ========================================= */

    public List<WbsElement> getRootWbsElements(Long projectId) {
        return wbsRepo.findByProjectIdAndParentIsNullOrderBySortOrderAsc(projectId);
    }

    public List<WbsElement> getChildrenOf(Long parentId) {
        return wbsRepo.findByParentIdOrderBySortOrderAsc(parentId);
    }

    @Transactional
    public WbsElement createWbsElement(WbsElement element, Long projectId, Long parentId) {
        Project project = getProjectById(projectId);
        element.setProject(project);
        if (parentId != null) {
            WbsElement parent = wbsRepo.findById(parentId)
                    .orElseThrow(() -> new RuntimeException("Parent WBS not found: " + parentId));
            element.setParent(parent);
            element.setLevelNo(parent.getLevelNo() + 1);
        }
        WbsElement saved = wbsRepo.save(element);
        recalculateProgress(projectId);
        return saved;
    }

    @Transactional
    public WbsElement updateTaskProgress(Long taskId, int progress, String status) {
        WbsElement task = wbsRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found: " + taskId));
        task.setProgress(progress);
        task.setStatus(status);
        WbsElement saved = wbsRepo.save(task);
        recalculateProgress(task.getProject().getId());
        return saved;
    }

    /* =========================================
       RESOURCE
       ========================================= */

    public List<ProjectResource> getResources(Long projectId) {
        return resourceRepo.findByProjectIdOrderByResourceNameAsc(projectId);
    }

    @Transactional
    public ProjectResource addResource(ProjectResource resource, Long projectId) {
        resource.setProject(getProjectById(projectId));
        return resourceRepo.save(resource);
    }

    /* =========================================
       BUDGET
       ========================================= */

    public List<BudgetLine> getBudgetLines(Long projectId) {
        return budgetRepo.findByProjectIdOrderByCostTypeAsc(projectId);
    }

    /** DTO สรุปงบประมาณ (สำหรับ KPI cards) */
    public Map<String, BigDecimal> getBudgetSummary(Long projectId) {
        Map<String, BigDecimal> map = new LinkedHashMap<>();
        map.put("planned",    safeDecimal(budgetRepo.sumPlannedByProject(projectId)));
        map.put("actual",     safeDecimal(budgetRepo.sumActualByProject(projectId)));
        map.put("commitment", safeDecimal(budgetRepo.sumCommitmentByProject(projectId)));
        BigDecimal budgetLimit = projectRepo.findById(projectId)
                .map(Project::getBudgetLimit).orElse(BigDecimal.ZERO);
        map.put("available", budgetLimit
                .subtract(map.get("actual"))
                .subtract(map.get("commitment")));
        return map;
    }

    @Transactional
    public BudgetLine saveBudgetLine(BudgetLine line, Long projectId) {
        line.setProject(getProjectById(projectId));
        return budgetRepo.save(line);
    }

    /* =========================================
       TIMESHEET
       ========================================= */

    @Transactional
    public TimesheetEntry logTime(TimesheetEntry entry, Long wbsId, Long projectId) {
        entry.setWbsElement(wbsRepo.findById(wbsId)
                .orElseThrow(() -> new RuntimeException("WBS not found: " + wbsId)));
        entry.setProject(getProjectById(projectId));

        TimesheetEntry saved = timesheetRepo.save(entry);

        // อัปเดต actual hours ของ Task
        Double totalHours = timesheetRepo.sumHoursByWbs(wbsId);
        WbsElement task = saved.getWbsElement();
        task.setActualHours(totalHours != null
                ? BigDecimal.valueOf(totalHours) : BigDecimal.ZERO);
        wbsRepo.save(task);

        return saved;
    }

    public List<TimesheetEntry> getTimesheetByWeek(String username,
                                                    java.time.LocalDate start,
                                                    java.time.LocalDate end) {
        return timesheetRepo.findByUsernameAndWorkDateBetweenOrderByWorkDateAsc(username, start, end);
    }

    @Transactional
    public TimesheetEntry approveTimesheet(Long entryId, String approver, boolean approved) {
        TimesheetEntry entry = timesheetRepo.findById(entryId)
                .orElseThrow(() -> new RuntimeException("Timesheet not found: " + entryId));
        entry.setTsStatus(approved ? "APPROVED" : "REJECTED");
        entry.setApprovedBy(approver);
        entry.setApprovedAt(java.time.LocalDateTime.now());
        return timesheetRepo.save(entry);
    }

    /* =========================================
       MATERIAL / BOM
       ========================================= */

    public List<MaterialResource> getMaterialsByProject(Long projectId) {
        return materialRepo.findByProjectIdOrderByMaterialNameAsc(projectId);
    }

    @Transactional
    public MaterialResource saveMaterial(MaterialResource material, Long projectId, Long wbsId) {
        material.setProject(getProjectById(projectId));
        if (wbsId != null) {
            material.setWbsElement(wbsRepo.findById(wbsId).orElse(null));
        }
        return materialRepo.save(material);
    }

    /* =========================================
       APPROVAL INBOX
       ========================================= */

    public List<ApprovalInbox> getPendingApprovals(int appId, String approver) {
        return approvalRepo.findByAppIdAndApprover(appId, approver);
    }

    @Transactional
    public ApprovalInbox resolveApproval(Long approvalId, String approver,
                                          boolean approved, String note) {
        ApprovalInbox approval = approvalRepo.findById(approvalId)
                .orElseThrow(() -> new RuntimeException("Approval not found: " + approvalId));
        approval.setStatus(approved ? "APPROVED" : "REJECTED");
        approval.setApproverNote(note);
        approval.setResolvedAt(java.time.LocalDateTime.now());
        return approvalRepo.save(approval);
    }

    /* =========================================
       PROJECT CLOSURE
       ========================================= */

    public Optional<ProjectClosure> getClosure(Long projectId) {
        return closureRepo.findByProjectId(projectId);
    }

    @Transactional
    public ProjectClosure saveClosure(ProjectClosure closure, Long projectId) {
        closure.setProject(getProjectById(projectId));
        ProjectClosure saved = closureRepo.save(closure);

        // เปลี่ยน status โครงการเป็น ARCHIVED เมื่อ Approved
        if ("APPROVED".equals(closure.getClosureStatus())) {
            Project p = getProjectById(projectId);
            p.setStatus("ARCHIVED");
            projectRepo.save(p);
        }
        return saved;
    }

    /* =========================================
       UTILITIES
       ========================================= */

    private String generateProjectCode(int appId) {
        int year = java.time.LocalDate.now().getYear() % 100;
        long count = projectRepo.count() + 1;
        return String.format("PRJ-%02d-%03d", year, count);
    }

    private BigDecimal safeDecimal(BigDecimal value) {
        return value != null ? value : BigDecimal.ZERO;
    }

    private TaskRowDto toTaskRowDto(WbsElement w) {
        TaskRowDto dto = new TaskRowDto();
        dto.setId(w.getId());
        dto.setTaskCode(w.getElementCode());
        dto.setTaskName(w.getElementName());
        dto.setProjectCode(w.getProject() != null ? w.getProject().getProjectCode() : "");
        dto.setWbsCode(w.getParent() != null ? w.getParent().getElementCode() : "");
        dto.setAssignee(w.getAssignee());
        dto.setStatus(w.getStatus());
        dto.setProgress(w.getProgress() != null ? w.getProgress() : 0);
        dto.setCategory(w.getTaskCategory());
        dto.setPriority(w.getPriority());
        if (w.getEndDate() != null) {
            dto.setEndDateDisplay(w.getEndDate().format(DATE_FMT));
        }
        return dto;
    }
}
