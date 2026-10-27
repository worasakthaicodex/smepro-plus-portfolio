package com.smepro.controller;

import com.smepro.entity.*;
import com.smepro.repository.WbsElementRepository;
import com.smepro.service.ProjectPlusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * ProjectPlusController
 * -------------------------------------------------------
 * Pattern:
 *   GET  /ProjectPlus-app/{appid}        → หน้า JSP (return view name)
 *   GET  /SomePage-app/{appid}           → return fragment JSP (โหลดผ่าน getData)
 *   POST /SomePage-app  (ไม่มี {appid})  → รับ Form + appId hidden field
 *
 * Logic ทั้งหมดอยู่ที่ ProjectPlusService เท่านั้น
 */
@org.springframework.stereotype.Controller
public class ProjectPlusController {

    @Autowired
    private ProjectPlusService service;

    // ====================================================
    // หน้าหลัก — My Tasks Dashboard
    // ====================================================

    @RequestMapping(value = "/ProjectPlus-app/{appid}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public String dashboard(ModelMap model,
                            @PathVariable int appid,
                            Principal principal) {
        String username = principal != null ? principal.getName() : "guest";
        model.addAttribute("appid", appid);
        model.addAttribute("summary", service.getDashboardSummary(appid, username));
        return "app/ProjectPlus/ProjectPlus";
    }

    // ====================================================
    // All Projects (โหลดผ่าน getData)
    // ====================================================

    @GetMapping("/allProjects-app/{appid}")
    public String allProjects(ModelMap model, @PathVariable int appid) {
        model.addAttribute("appid", appid);
        model.addAttribute("projects", service.getAllProjects(appid));
        return "app/ProjectPlus/allProjects";
    }

    @GetMapping("/pipelineProjects-app/{appid}")
    public String pipelineProjects(ModelMap model, @PathVariable int appid) {
        model.addAttribute("appid", appid);
        model.addAttribute("projects", service.getPipelineProjects(appid));
        return "app/ProjectPlus/pipelineProjects";
    }

    @GetMapping("/archivedProjects-app/{appid}")
    public String archivedProjects(ModelMap model, @PathVariable int appid) {
        model.addAttribute("appid", appid);
        model.addAttribute("projects", service.getArchivedProjects(appid));
        return "app/ProjectPlus/archivedProjects";
    }

    // ====================================================
    // Project Detail (Multi-Tab)
    // ====================================================

    @GetMapping("/projectDetail-app/{appid}")
    public String projectDetail(ModelMap model,
                                @PathVariable int appid,
                                @RequestParam Long projectId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("phases", service.getPhasesByProject(projectId));
        model.addAttribute("wbsRoots", service.getRootWbsElements(projectId));
        model.addAttribute("resources", service.getResources(projectId));
        model.addAttribute("budgetSummary", service.getBudgetSummary(projectId));
        return "app/ProjectPlus/projectDetail";
    }

    // ====================================================
    // Create Project (Form)
    // ====================================================

    @GetMapping("/newProject-app/{appid}")
    public String newProjectForm(ModelMap model, @PathVariable int appid) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", new Project());
        return "app/ProjectPlus/newProject";
    }

    @PostMapping("/newProject-app")
    public String createProject(@ModelAttribute Project project,
                                @RequestParam int appid,
                                ModelMap model) {
        Project saved = service.createProject(project, appid);
        model.addAttribute("appid", appid);
        model.addAttribute("project", saved);
        model.addAttribute("message", "success");
        return "app/ProjectPlus/projectDetail";
    }

    @PostMapping("/editProject-app")
    public String updateProject(@ModelAttribute Project project,
                                @RequestParam int appid,
                                ModelMap model) {
        Project saved = service.updateProject(project);
        model.addAttribute("appid", appid);
        model.addAttribute("project", saved);
        model.addAttribute("message", "updated");
        return "app/ProjectPlus/projectDetail";
    }

    // ====================================================
    // Phase
    // ====================================================

    @GetMapping("/projectPhase-app/{appid}")
    public String phaseList(ModelMap model,
                            @PathVariable int appid,
                            @RequestParam Long projectId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("phases", service.getPhasesByProject(projectId));
        return "app/ProjectPlus/projectPhase";
    }

    @GetMapping("/newPhase-app/{appid}")
    public String newPhaseForm(ModelMap model,
                               @PathVariable int appid,
                               @RequestParam Long projectId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("phase", new ProjectPhase());
        return "app/ProjectPlus/newPhase";
    }

    @PostMapping("/newPhase-app")
    public String createPhase(@ModelAttribute ProjectPhase phase,
                              @RequestParam Long projectId,
                              @RequestParam int appid,
                              ModelMap model) {
        ProjectPhase saved = service.createPhase(phase, projectId);
        model.addAttribute("appid", appid);
        model.addAttribute("phases", service.getPhasesByProject(projectId));
        model.addAttribute("message", "success");
        return "app/ProjectPlus/projectPhase";
    }

    // ====================================================
    // Phase Gate Approval
    // ====================================================

    @GetMapping("/phaseGate-app/{appid}")
    public String phaseGate(ModelMap model,
                            @PathVariable int appid,
                            @RequestParam Long projectId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("phases", service.getPhasesByProject(projectId));
        return "app/ProjectPlus/phaseGate";
    }

    @PostMapping("/phaseGate-app")
    public String approvePhaseGate(@RequestParam Long phaseId,
                                   @RequestParam String approver,
                                   @RequestParam String note,
                                   @RequestParam boolean approved,
                                   @RequestParam Long projectId,
                                   @RequestParam int appid,
                                   ModelMap model) {
        service.approvePhaseGate(phaseId, approver, note, approved);
        model.addAttribute("appid", appid);
        model.addAttribute("phases", service.getPhasesByProject(projectId));
        return "app/ProjectPlus/phaseGate";
    }

    // ====================================================
    // WBS & Task
    // ====================================================

    @GetMapping("/wbsElements-app/{appid}")
    public String wbsElements(ModelMap model,
                              @PathVariable int appid,
                              @RequestParam Long projectId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("wbsRoots", service.getRootWbsElements(projectId));
        return "app/ProjectPlus/wbsElements";
    }

    @GetMapping("/newWbs-app/{appid}")
    public String newWbsForm(ModelMap model,
                             @PathVariable int appid,
                             @RequestParam Long projectId,
                             @RequestParam(required = false) Long parentId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("wbsElement", new WbsElement());
        model.addAttribute("parentId", parentId);
        model.addAttribute("wbsRoots", service.getRootWbsElements(projectId));
        return "app/ProjectPlus/newWbs";
    }

    @PostMapping("/newWbs-app")
    public String createWbs(@ModelAttribute WbsElement element,
                            @RequestParam Long projectId,
                            @RequestParam(required = false) Long parentId,
                            @RequestParam int appid,
                            ModelMap model) {
        service.createWbsElement(element, projectId, parentId);
        model.addAttribute("appid", appid);
        model.addAttribute("wbsRoots", service.getRootWbsElements(projectId));
        return "app/ProjectPlus/wbsElements";
    }

    /** Update progress / status ผ่าน Ajax */
    @PostMapping("/updateTask-app")
    @ResponseBody
    public ResponseEntity<String> updateTask(@RequestParam Long taskId,
                                             @RequestParam int progress,
                                             @RequestParam String status) {
        service.updateTaskProgress(taskId, progress, status);
        return ResponseEntity.ok("updated");
    }

    // ====================================================
    // Task Detail / Edit Form
    // ====================================================

    @GetMapping("/taskDetail-app/{appid}")
    public String taskDetail(ModelMap model,
                             @PathVariable int appid,
                             @RequestParam Long taskId) {
        WbsElement task = wbsRepo_fallback(taskId);
        model.addAttribute("appid", appid);
        model.addAttribute("task", task);
        return "app/ProjectPlus/taskDetail";
    }

    @PostMapping("/editTask-app")
    public String editTask(@ModelAttribute WbsElement element,
                           @RequestParam int appid,
                           @RequestParam Long projectId,
                           ModelMap model) {
        service.updateTaskProgress(element.getId(),
                element.getProgress() != null ? element.getProgress() : 0,
                element.getStatus());
        model.addAttribute("appid", appid);
        model.addAttribute("task", element);
        return "app/ProjectPlus/taskDetail";
    }

    // ====================================================
    // Resource Management
    // ====================================================

    @GetMapping("/resources-app/{appid}")
    public String resources(ModelMap model,
                            @PathVariable int appid,
                            @RequestParam Long projectId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("resources", service.getResources(projectId));
        return "app/ProjectPlus/resources";
    }

    @PostMapping("/addResource-app")
    public String addResource(@ModelAttribute ProjectResource resource,
                              @RequestParam Long projectId,
                              @RequestParam int appid,
                              ModelMap model) {
        service.addResource(resource, projectId);
        model.addAttribute("appid", appid);
        model.addAttribute("resources", service.getResources(projectId));
        return "app/ProjectPlus/resources";
    }

    // ====================================================
    // Budget & Costs
    // ====================================================

    @GetMapping("/budget-app/{appid}")
    public String budget(ModelMap model,
                         @PathVariable int appid,
                         @RequestParam Long projectId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("budgetLines", service.getBudgetLines(projectId));
        model.addAttribute("budgetSummary", service.getBudgetSummary(projectId));
        return "app/ProjectPlus/budget";
    }

    @PostMapping("/saveBudget-app")
    public String saveBudget(@ModelAttribute BudgetLine line,
                             @RequestParam Long projectId,
                             @RequestParam int appid,
                             ModelMap model) {
        service.saveBudgetLine(line, projectId);
        model.addAttribute("appid", appid);
        model.addAttribute("budgetLines", service.getBudgetLines(projectId));
        model.addAttribute("budgetSummary", service.getBudgetSummary(projectId));
        return "app/ProjectPlus/budget";
    }

    // ====================================================
    // Material / BOM
    // ====================================================

    @GetMapping("/materials-app/{appid}")
    public String materials(ModelMap model,
                            @PathVariable int appid,
                            @RequestParam Long projectId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("materials", service.getMaterialsByProject(projectId));
        return "app/ProjectPlus/materials";
    }

    @PostMapping("/saveMaterial-app")
    public String saveMaterial(@ModelAttribute MaterialResource material,
                               @RequestParam Long projectId,
                               @RequestParam(required = false) Long wbsId,
                               @RequestParam int appid,
                               ModelMap model) {
        service.saveMaterial(material, projectId, wbsId);
        model.addAttribute("appid", appid);
        model.addAttribute("materials", service.getMaterialsByProject(projectId));
        return "app/ProjectPlus/materials";
    }

    // ====================================================
    // Timesheet
    // ====================================================

    @GetMapping("/timesheet-app/{appid}")
    public String timesheet(ModelMap model,
                            @PathVariable int appid,
                            Principal principal,
                            @RequestParam(required = false) String weekStart) {
        String username = principal != null ? principal.getName() : "guest";
        java.time.LocalDate start = weekStart != null
                ? java.time.LocalDate.parse(weekStart)
                : java.time.LocalDate.now().with(java.time.DayOfWeek.MONDAY);
        java.time.LocalDate end = start.plusDays(6);
        model.addAttribute("appid", appid);
        model.addAttribute("weekStart", start);
        model.addAttribute("weekEnd", end);
        model.addAttribute("entries", service.getTimesheetByWeek(username, start, end));
        return "app/ProjectPlus/timesheet";
    }

    @PostMapping("/logTime-app")
    public String logTime(@ModelAttribute TimesheetEntry entry,
                          @RequestParam Long wbsId,
                          @RequestParam Long projectId,
                          @RequestParam int appid,
                          Principal principal,
                          ModelMap model) {
        if (principal != null) entry.setUsername(principal.getName());
        service.logTime(entry, wbsId, projectId);
        model.addAttribute("appid", appid);
        model.addAttribute("message", "logged");
        return "app/ProjectPlus/timesheet";
    }

    // ====================================================
    // Kanban Board
    // ====================================================

    @GetMapping("/kanban-app/{appid}")
    public String kanban(ModelMap model,
                         @PathVariable int appid,
                         @RequestParam Long projectId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("wbsRoots", service.getRootWbsElements(projectId));
        return "app/ProjectPlus/kanban";
    }

    // ====================================================
    // Gantt Timeline
    // ====================================================

    @GetMapping("/gantt-app/{appid}")
    public String gantt(ModelMap model,
                        @PathVariable int appid,
                        @RequestParam Long projectId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("phases", service.getPhasesByProject(projectId));
        model.addAttribute("wbsRoots", service.getRootWbsElements(projectId));
        return "app/ProjectPlus/gantt";
    }

    // ====================================================
    // Dashboard & Analytics
    // ====================================================

    @GetMapping("/analytics-app/{appid}")
    public String analytics(ModelMap model, @PathVariable int appid) {
        model.addAttribute("appid", appid);
        model.addAttribute("projects", service.getAllProjects(appid));
        return "app/ProjectPlus/analytics";
    }

    // ====================================================
    // Approval Inbox
    // ====================================================

    @GetMapping("/approvalInbox-app/{appid}")
    public String approvalInbox(ModelMap model,
                                @PathVariable int appid,
                                Principal principal) {
        String username = principal != null ? principal.getName() : "guest";
        model.addAttribute("appid", appid);
        model.addAttribute("approvals", service.getPendingApprovals(appid, username));
        return "app/ProjectPlus/approvalInbox";
    }

    @PostMapping("/resolveApproval-app")
    public String resolveApproval(@RequestParam Long approvalId,
                                  @RequestParam boolean approved,
                                  @RequestParam String note,
                                  @RequestParam int appid,
                                  Principal principal,
                                  ModelMap model) {
        String username = principal != null ? principal.getName() : "guest";
        service.resolveApproval(approvalId, username, approved, note);
        model.addAttribute("appid", appid);
        model.addAttribute("approvals", service.getPendingApprovals(appid, username));
        return "app/ProjectPlus/approvalInbox";
    }

    // ====================================================
    // Project Closure
    // ====================================================

    @GetMapping("/closure-app/{appid}")
    public String closure(ModelMap model,
                          @PathVariable int appid,
                          @RequestParam Long projectId) {
        model.addAttribute("appid", appid);
        model.addAttribute("project", service.getProjectById(projectId));
        model.addAttribute("closure", service.getClosure(projectId).orElse(new ProjectClosure()));
        return "app/ProjectPlus/projectClosure";
    }

    @PostMapping("/saveClosure-app")
    public String saveClosure(@ModelAttribute ProjectClosure closure,
                              @RequestParam Long projectId,
                              @RequestParam int appid,
                              ModelMap model) {
        service.saveClosure(closure, projectId);
        model.addAttribute("appid", appid);
        model.addAttribute("message", "saved");
        return "app/ProjectPlus/projectClosure";
    }

    /* =========================================
       Private Helpers
       ========================================= */

    /**
     * Fallback: ดึง WbsElement โดยตรง
     * (เพื่อไม่ให้ inject WbsElementRepository ใน Controller)
     * ใน production ควรสร้าง method ใน service แทน
     */
    @Autowired
    private WbsElementRepository wbsElementRepository;

    private WbsElement wbsRepo_fallback(Long taskId) {
        return wbsElementRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found: " + taskId));
    }
}