<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProjectPlus</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <%@ include file="_pp_style.jsp" %>
</head>
<body>

<div id="pm-glass-app">
  <div id="pp-window">

    <%-- Sidebar --%>
    <c:set var="activeNav" value="myTasks"/>
    <%@ include file="_pp_sidebar.jsp" %>

    <%-- Main --%>
    <div class="pp-main">

      <%-- Top Bar --%>
      <div class="pp-topbar">
        <div class="pp-tabs">
          <div class="pp-tab"
               onclick="getData('allProjects-app/${appid}','pp-content-area')">
            <spring:message code="pp.nav.allProjects" text="All Projects"/>
          </div>
          <div class="pp-tab active">
            <spring:message code="pp.nav.myTasks" text="My Tasks"/>
          </div>
          <div class="pp-tab"
               onclick="getData('approvalInbox-app/${appid}','pp-content-area')">
            <spring:message code="pp.approval.title" text="Approval Inbox"/>
          </div>
        </div>
        <div class="pp-topbar-right">
          <div class="pp-search">
            <i class="fa fa-search"></i>
            <input type="text" placeholder="Search task or project...">
          </div>
          <i class="fa fa-bell" style="color:var(--text-muted);cursor:pointer;"></i>
          <div class="pp-avatar-btn">W1</div>
        </div>
      </div>

      <%-- Loading bar --%>
      <div class="pp-loading-bar" id="barpp-content-area"></div>

      <div class="pp-scroll" id="pp-content-area">

        <%-- KPI Strip --%>
        <div class="pp-kpi-grid">
          <div class="pp-kpi-card">
            <div class="pp-kpi-title">
              <spring:message code="pp.dashboard.totalProjects" text="Total Projects"/>
              <i class="fa fa-briefcase"></i>
            </div>
            <div class="pp-kpi-value">${summary.totalProjects}</div>
            <div class="pp-kpi-trend trend-up">
              <i class="fa fa-check-circle"></i>
              <spring:message code="pp.dashboard.onTrack" text="On Track"/>: ${summary.onTrackCount}
            </div>
          </div>
          <div class="pp-kpi-card">
            <div class="pp-kpi-title">
              <spring:message code="pp.dashboard.atRisk" text="At Risk"/>
              <i class="fa fa-triangle-exclamation" style="color:var(--status-orange);"></i>
            </div>
            <div class="pp-kpi-value" style="color:var(--status-orange);">${summary.atRiskCount}</div>
            <div class="pp-kpi-trend" style="color:var(--status-red);">
              <spring:message code="pp.dashboard.delayed" text="Delayed"/>: ${summary.delayedCount}
            </div>
          </div>
          <div class="pp-kpi-card">
            <div class="pp-kpi-title">
              <spring:message code="pp.dashboard.pendingApproval" text="Pending Approval"/>
              <i class="fa fa-inbox"></i>
            </div>
            <div class="pp-kpi-value" style="color:var(--accent-blue);">${summary.pendingApprovalCount}</div>
            <div class="pp-kpi-trend">
              <span style="cursor:pointer;color:var(--accent-blue);"
                    onclick="getData('approvalInbox-app/${appid}','pp-content-area')">
                View inbox <i class="fa fa-arrow-right"></i>
              </span>
            </div>
          </div>
          <div class="pp-kpi-card">
            <div class="pp-kpi-title">
              <spring:message code="pp.dashboard.myTasks" text="My Pending Tasks"/>
              <i class="fa fa-list-check"></i>
            </div>
            <div class="pp-kpi-value" style="color:var(--status-orange);">${summary.myPendingTaskCount}</div>
            <div class="pp-kpi-trend">Active this sprint</div>
          </div>
        </div>

        <%-- My Pending Tasks --%>
        <div class="pp-section-hd">
          <div class="pp-section-title">
            <spring:message code="pp.dashboard.myTasks" text="My Tasks (Current Sprint)"/>
          </div>
          <div class="pp-hd-actions">
            <button class="pp-btn-outline pp-btn-sm"><i class="fa fa-filter"></i>
              <spring:message code="pp.btn.filter" text="Filter"/>
            </button>
          </div>
        </div>

        <div class="pp-card" style="padding:0;margin-bottom:20px;">
          <table class="pp-table">
            <thead>
              <tr>
                <th style="width:36px;"></th>
                <th><spring:message code="pp.wbs.name" text="Task"/></th>
                <th><spring:message code="pp.wbs.assignee" text="Assignee"/></th>
                <th style="width:160px;"><spring:message code="pp.label.overallProgress" text="Progress"/></th>
                <th style="width:80px;"></th>
                <th style="width:30px;"></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="task" items="${summary.myPendingTasks}">
                <tr>
                  <td>
                    <div style="width:32px;height:32px;border-radius:7px;
                         display:flex;justify-content:center;align-items:center;
                         background:${task.category == 'BUG' ? '#c0392b' :
                                      task.category == 'DESIGN' ? '#e67e22' : '#2980b9'};">
                      <i class="fa ${task.category == 'BUG' ? 'fa-bug' :
                                     task.category == 'DESIGN' ? 'fa-pen-nib' : 'fa-code'}"
                         style="color:white;font-size:13px;"></i>
                    </div>
                  </td>
                  <td>
                    <div style="font-weight:600;font-size:12px;">${task.taskCode}: ${task.taskName}</div>
                    <div style="font-size:10px;color:var(--text-muted);">
                      ${task.projectCode}
                      <c:if test="${not empty task.wbsCode}"> &bull; WBS: ${task.wbsCode}</c:if>
                    </div>
                  </td>
                  <td>
                    <div style="display:flex;align-items:center;gap:6px;">
                      <div class="pp-avatar"
                           style="background:${not empty task.assigneeColor ? task.assigneeColor : '#555'};">
                        ${not empty task.assigneeInitials ? task.assigneeInitials : '?'}
                      </div>
                      <span style="font-size:11px;">${task.assignee}</span>
                    </div>
                  </td>
                  <td>
                    <div class="pp-prog-wrap">
                      <div class="pp-prog-labels">
                        <span style="color:${task.status == 'COMPLETED' ? 'var(--status-green)' :
                                             task.status == 'NOT_STARTED' ? 'var(--status-red)' :
                                             'var(--text-muted)'};">
                          <spring:message code="pp.wbs.status.${fn:toLowerCase(task.status)}"
                                          text="${task.status}"/>
                        </span>
                        <span>${task.progress}%</span>
                      </div>
                      <div class="pp-prog-track">
                        <div class="pp-prog-fill"
                             style="width:${task.progress}%;
                             background:${task.progress == 100 ? 'var(--status-green)' :
                                          task.progress == 0 ? 'var(--status-red)' :
                                          'var(--status-orange)'};">
                        </div>
                      </div>
                    </div>
                  </td>
                  <td>
                    <button class="pp-btn-primary pp-btn-sm"
                            onclick="getData('taskDetail-app/${appid}?taskId=${task.id}','pp-content-area')">
                      <spring:message code="pp.btn.update" text="Update"/>
                    </button>
                  </td>
                  <td>
                    <i class="fa fa-ellipsis" style="color:var(--text-muted);cursor:pointer;"></i>
                  </td>
                </tr>
              </c:forEach>
              <c:if test="${empty summary.myPendingTasks}">
                <tr>
                  <td colspan="6" style="text-align:center;padding:30px;color:var(--text-muted);">
                    <i class="fa fa-check-circle" style="font-size:24px;margin-bottom:8px;display:block;color:var(--status-green);"></i>
                    <spring:message code="pp.label.noData" text="No pending tasks"/>
                  </td>
                </tr>
              </c:if>
            </tbody>
          </table>
        </div>

        <%-- Announcements --%>
        <div class="pp-section-hd">
          <div class="pp-section-title">
            <spring:message code="pp.dashboard.announcements" text="Recent Announcements"/>
          </div>
        </div>
        <div class="pp-card" style="padding:0;opacity:0.85;">
          <c:forEach var="ann" items="${summary.announcements}">
            <div style="display:flex;align-items:center;gap:14px;padding:12px 16px;
                 border-bottom:1px solid rgba(255,255,255,0.04);">
              <div style="width:32px;height:32px;border-radius:7px;background:rgba(255,255,255,0.08);
                   display:flex;justify-content:center;align-items:center;">
                <i class="fa fa-bullhorn" style="color:var(--text-muted);font-size:13px;"></i>
              </div>
              <div style="flex:1;">
                <div style="font-weight:600;font-size:12px;">${ann.title}</div>
                <div style="font-size:11px;color:var(--text-muted);">${ann.detail}</div>
              </div>
              <div style="font-size:10px;color:var(--text-muted);white-space:nowrap;">${ann.timeAgo}</div>
            </div>
          </c:forEach>
        </div>

      </div><%-- /pp-content-area --%>
    </div><%-- /pp-main --%>
  </div><%-- /pp-window --%>
</div><%-- /pm-glass-app --%>

<script>
<%-- ไม่ต้องการ JS พิเศษ — getData/postData มาจาก main.js --%>
</script>
</body>
</html>
