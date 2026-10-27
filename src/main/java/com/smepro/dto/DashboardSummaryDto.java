package com.smepro.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * DashboardSummaryDto — ข้อมูลสรุปสำหรับหน้า Dashboard (My Tasks / Home)
 */
public class DashboardSummaryDto {

    private int totalProjects;
    private int onTrackCount;
    private int atRiskCount;
    private int delayedCount;
    private int myPendingTaskCount;
    private int pendingApprovalCount;

    private List<ProjectCardDto> myTaskProjects;    // โครงการที่มี task ของ user
    private List<TaskRowDto>     myPendingTasks;    // Task ที่รอทำ
    private List<AnnouncementDto> announcements;

    // ===== inner DTO classes =====

    public static class ProjectCardDto {
        private Long   id;
        private String projectCode;
        private String projectName;
        private String status;
        private int    progress;
        private String budgetDisplay;  // "฿1.2M / ฿2.0M"
        private String endDateDisplay; // "Sep 30, 2026"
        private String priority;
        private List<AvatarDto> avatars;

        // Getters & Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getProjectCode() { return projectCode; }
        public void setProjectCode(String projectCode) { this.projectCode = projectCode; }
        public String getProjectName() { return projectName; }
        public void setProjectName(String projectName) { this.projectName = projectName; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public int getProgress() { return progress; }
        public void setProgress(int progress) { this.progress = progress; }
        public String getBudgetDisplay() { return budgetDisplay; }
        public void setBudgetDisplay(String budgetDisplay) { this.budgetDisplay = budgetDisplay; }
        public String getEndDateDisplay() { return endDateDisplay; }
        public void setEndDateDisplay(String endDateDisplay) { this.endDateDisplay = endDateDisplay; }
        public String getPriority() { return priority; }
        public void setPriority(String priority) { this.priority = priority; }
        public List<AvatarDto> getAvatars() { return avatars; }
        public void setAvatars(List<AvatarDto> avatars) { this.avatars = avatars; }
    }

    public static class TaskRowDto {
        private Long   id;
        private String taskCode;
        private String taskName;
        private String projectCode;
        private String wbsCode;
        private String assignee;
        private String assigneeInitials;
        private String assigneeColor;
        private String status;
        private int    progress;
        private String category;   // DEVELOPMENT, DESIGN, BUG
        private String priority;
        private String endDateDisplay;

        // Getters & Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTaskCode() { return taskCode; }
        public void setTaskCode(String taskCode) { this.taskCode = taskCode; }
        public String getTaskName() { return taskName; }
        public void setTaskName(String taskName) { this.taskName = taskName; }
        public String getProjectCode() { return projectCode; }
        public void setProjectCode(String projectCode) { this.projectCode = projectCode; }
        public String getWbsCode() { return wbsCode; }
        public void setWbsCode(String wbsCode) { this.wbsCode = wbsCode; }
        public String getAssignee() { return assignee; }
        public void setAssignee(String assignee) { this.assignee = assignee; }
        public String getAssigneeInitials() { return assigneeInitials; }
        public void setAssigneeInitials(String assigneeInitials) { this.assigneeInitials = assigneeInitials; }
        public String getAssigneeColor() { return assigneeColor; }
        public void setAssigneeColor(String assigneeColor) { this.assigneeColor = assigneeColor; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public int getProgress() { return progress; }
        public void setProgress(int progress) { this.progress = progress; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public String getPriority() { return priority; }
        public void setPriority(String priority) { this.priority = priority; }
        public String getEndDateDisplay() { return endDateDisplay; }
        public void setEndDateDisplay(String endDateDisplay) { this.endDateDisplay = endDateDisplay; }
    }

    public static class AvatarDto {
        private String initials;
        private String color;
        private String name;

        public AvatarDto() {}
        public AvatarDto(String initials, String color, String name) {
            this.initials = initials;
            this.color = color;
            this.name = name;
        }
        public String getInitials() { return initials; }
        public void setInitials(String initials) { this.initials = initials; }
        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    public static class AnnouncementDto {
        private String title;
        private String detail;
        private String timeAgo;

        public AnnouncementDto() {}
        public AnnouncementDto(String title, String detail, String timeAgo) {
            this.title = title;
            this.detail = detail;
            this.timeAgo = timeAgo;
        }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDetail() { return detail; }
        public void setDetail(String detail) { this.detail = detail; }
        public String getTimeAgo() { return timeAgo; }
        public void setTimeAgo(String timeAgo) { this.timeAgo = timeAgo; }
    }

    // ===== Main DTO Getters & Setters =====
    public int getTotalProjects() { return totalProjects; }
    public void setTotalProjects(int totalProjects) { this.totalProjects = totalProjects; }
    public int getOnTrackCount() { return onTrackCount; }
    public void setOnTrackCount(int onTrackCount) { this.onTrackCount = onTrackCount; }
    public int getAtRiskCount() { return atRiskCount; }
    public void setAtRiskCount(int atRiskCount) { this.atRiskCount = atRiskCount; }
    public int getDelayedCount() { return delayedCount; }
    public void setDelayedCount(int delayedCount) { this.delayedCount = delayedCount; }
    public int getMyPendingTaskCount() { return myPendingTaskCount; }
    public void setMyPendingTaskCount(int myPendingTaskCount) { this.myPendingTaskCount = myPendingTaskCount; }
    public int getPendingApprovalCount() { return pendingApprovalCount; }
    public void setPendingApprovalCount(int pendingApprovalCount) { this.pendingApprovalCount = pendingApprovalCount; }
    public List<ProjectCardDto> getMyTaskProjects() { return myTaskProjects; }
    public void setMyTaskProjects(List<ProjectCardDto> myTaskProjects) { this.myTaskProjects = myTaskProjects; }
    public List<TaskRowDto> getMyPendingTasks() { return myPendingTasks; }
    public void setMyPendingTasks(List<TaskRowDto> myPendingTasks) { this.myPendingTasks = myPendingTasks; }
    public List<AnnouncementDto> getAnnouncements() { return announcements; }
    public void setAnnouncements(List<AnnouncementDto> announcements) { this.announcements = announcements; }
}
