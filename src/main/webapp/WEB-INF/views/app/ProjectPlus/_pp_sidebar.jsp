<%-- app/ProjectPlus/_pp_sidebar.jsp
     Params ที่ต้องส่งมา: appid, activeNav (string)
     Optional: project (Project object) — ถ้ามีจะแสดง Current Project box
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="pp-sidebar">
    <div class="nav-logo">
        <i class="fa fa-diagram-project"></i> ProjectPlus
    </div>

    <div class="pp-nav-section">
        <div class="pp-nav-title"><spring:message code="pp.nav.workspace" text="Workspace"/></div>
        <div class="pp-nav-item ${activeNav == 'allProjects' ? 'active' : ''}"
             onclick="getData('allProjects-app/${appid}','pp-content-area')">
            <i class="fa fa-briefcase"></i>
            <spring:message code="pp.nav.allProjects" text="All Projects"/>
        </div>
        <div class="pp-nav-item ${activeNav == 'myTasks' ? 'active' : ''}"
             onclick="getData('ProjectPlus-app/${appid}','pp-content-area')">
            <i class="fa fa-list-check"></i>
            <spring:message code="pp.nav.myTasks" text="My Tasks"/>
            <c:if test="${summary.myPendingTaskCount > 0}">
                <span class="pp-nav-badge">${summary.myPendingTaskCount}</span>
            </c:if>
        </div>
        <div class="pp-nav-item ${activeNav == 'gantt' ? 'active' : ''}"
             onclick="getData('gantt-app/${appid}?projectId=${project.id}','pp-content-area')">
            <i class="fa fa-chart-gantt"></i>
            <spring:message code="pp.nav.gantt" text="Gantt Timeline"/>
        </div>
        <div class="pp-nav-item ${activeNav == 'kanban' ? 'active' : ''}"
             onclick="getData('kanban-app/${appid}?projectId=${project.id}','pp-content-area')">
            <i class="fa fa-table-columns"></i>
            <spring:message code="pp.nav.kanban" text="Kanban Board"/>
        </div>
    </div>

    <div class="pp-nav-section">
        <div class="pp-nav-title"><spring:message code="pp.nav.planning" text="Planning &amp; Control"/></div>
        <div class="pp-nav-item ${activeNav == 'wbs' ? 'active' : ''}"
             onclick="getData('wbsElements-app/${appid}?projectId=${project.id}','pp-content-area')">
            <i class="fa fa-sitemap"></i>
            <spring:message code="pp.nav.wbs" text="WBS Elements"/>
        </div>
        <div class="pp-nav-item ${activeNav == 'budget' ? 'active' : ''}"
             onclick="getData('budget-app/${appid}?projectId=${project.id}','pp-content-area')">
            <i class="fa fa-coins"></i>
            <spring:message code="pp.nav.budget" text="Budget &amp; Costs"/>
        </div>
        <div class="pp-nav-item ${activeNav == 'resources' ? 'active' : ''}"
             onclick="getData('resources-app/${appid}?projectId=${project.id}','pp-content-area')">
            <i class="fa fa-users-gear"></i>
            <spring:message code="pp.nav.resources" text="Resources"/>
        </div>
        <div class="pp-nav-item ${activeNav == 'materials' ? 'active' : ''}"
             onclick="getData('materials-app/${appid}?projectId=${project.id}','pp-content-area')">
            <i class="fa fa-boxes-stacked"></i>
            <spring:message code="pp.nav.materials" text="Materials (BOM)"/>
        </div>
    </div>

    <div class="pp-nav-section">
        <div class="pp-nav-title"><spring:message code="pp.nav.reporting" text="Reporting"/></div>
        <div class="pp-nav-item ${activeNav == 'analytics' ? 'active' : ''}"
             onclick="getData('analytics-app/${appid}','pp-content-area')">
            <i class="fa fa-chart-pie"></i>
            <spring:message code="pp.nav.dashboards" text="Dashboards"/>
        </div>
        <div class="pp-nav-item ${activeNav == 'timesheet' ? 'active' : ''}"
             onclick="getData('timesheet-app/${appid}','pp-content-area')">
            <i class="fa fa-clock-rotate-left"></i>
            <spring:message code="pp.nav.timesheets" text="Timesheets"/>
        </div>
        <div class="pp-nav-item ${activeNav == 'approvalInbox' ? 'active' : ''}"
             onclick="getData('approvalInbox-app/${appid}','pp-content-area')">
            <i class="fa fa-inbox"></i>
            <spring:message code="pp.approval.title" text="Approval Inbox"/>
            <c:if test="${summary.pendingApprovalCount > 0}">
                <span class="pp-nav-badge">${summary.pendingApprovalCount}</span>
            </c:if>
        </div>
    </div>

    <%-- Current Project Box (แสดงเมื่อมี project ใน scope) --%>
    <c:if test="${not empty project}">
        <div class="pp-nav-project-box">
            <div class="pp-nav-title" style="color:var(--accent-blue);">Current Project</div>
            <div class="pp-nav-item"
                 onclick="getData('projectDetail-app/${appid}?projectId=${project.id}','pp-content-area')">
                <i class="fa fa-folder-open"></i> ${project.projectCode}
            </div>
            <div class="pp-nav-item"
                 onclick="getData('wbsElements-app/${appid}?projectId=${project.id}','pp-content-area')">
                <i class="fa fa-sitemap"></i>
                <spring:message code="pp.nav.wbs" text="WBS Elements"/>
            </div>
            <div class="pp-nav-item"
                 onclick="getData('phaseGate-app/${appid}?projectId=${project.id}','pp-content-area')">
                <i class="fa fa-flag-checkered"></i> Phase Gates
            </div>
            <div class="pp-nav-item"
                 onclick="getData('closure-app/${appid}?projectId=${project.id}','pp-content-area')">
                <i class="fa fa-lock"></i>
                <spring:message code="pp.closure.title" text="Project Closure"/>
            </div>
        </div>
    </c:if>
</div>
