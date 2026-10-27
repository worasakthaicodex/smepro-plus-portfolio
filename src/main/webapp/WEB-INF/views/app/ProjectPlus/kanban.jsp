<%-- kanban.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
#pm-glass-app .pp-kanban-board { display:grid;grid-template-columns:repeat(4,1fr);gap:14px;height:calc(100% - 60px); }
#pm-glass-app .pp-kanban-col { display:flex;flex-direction:column;gap:0; }
#pm-glass-app .pp-kanban-col-hd {
    padding:10px 14px;border-radius:8px 8px 0 0;font-size:12px;font-weight:600;
    display:flex;justify-content:space-between;align-items:center;
    border-bottom:none;
}
#pm-glass-app .pp-kanban-col-body {
    background:rgba(0,0,0,0.15);border:1px solid rgba(255,255,255,0.06);
    border-top:none;border-radius:0 0 8px 8px;
    flex:1;overflow-y:auto;padding:8px;display:flex;flex-direction:column;gap:8px;
}
#pm-glass-app .pp-kanban-card {
    background:var(--card-bg);border:1px solid rgba(255,255,255,0.06);
    border-radius:8px;padding:12px;cursor:grab;
    transition:transform 0.15s,box-shadow 0.15s;
}
#pm-glass-app .pp-kanban-card:hover { transform:translateY(-2px);box-shadow:0 4px 10px rgba(0,0,0,0.3); }
</style>

<div class="pp-proj-title-row" style="margin-bottom:14px;">
  <div class="pp-proj-title">
    <i class="fa fa-table-columns" style="color:var(--accent-blue);"></i>
    <spring:message code="pp.kanban.title" text="Kanban Board"/>
    <c:if test="${not empty project}"> — ${project.projectCode}</c:if>
  </div>
  <button class="pp-btn-primary pp-btn-sm"
          onclick="getData('newWbs-app/${appid}?projectId=${project.id}','pp-content-area')">
    <i class="fa fa-plus"></i> Add Task
  </button>
</div>

<div class="pp-kanban-board">
  <%-- To Do --%>
  <div class="pp-kanban-col">
    <div class="pp-kanban-col-hd" style="background:rgba(255,255,255,0.06);">
      <span><spring:message code="pp.kanban.todo" text="To Do"/></span>
      <span class="pp-badge pp-badge-gray" style="font-size:9px;">
        ${fn:length(wbsRoots)}
      </span>
    </div>
    <div class="pp-kanban-col-body">
      <c:forEach var="w" items="${wbsRoots}">
        <c:if test="${w.status == 'NOT_STARTED' && w.elementType == 'TASK'}">
          <div class="pp-kanban-card"
               onclick="getData('taskDetail-app/${appid}?taskId=${w.id}','pp-content-area')">
            <div style="font-size:10px;color:var(--accent-blue);margin-bottom:4px;">${w.elementCode}</div>
            <div style="font-size:12px;font-weight:600;margin-bottom:6px;">${w.elementName}</div>
            <div style="display:flex;justify-content:space-between;align-items:center;">
              <span style="font-size:10px;color:var(--text-muted);">${w.assignee}</span>
              <span class="pp-badge pp-badge-gray" style="font-size:9px;">${w.priority}</span>
            </div>
          </div>
        </c:if>
      </c:forEach>
    </div>
  </div>

  <%-- In Progress --%>
  <div class="pp-kanban-col">
    <div class="pp-kanban-col-hd" style="background:rgba(243,156,18,0.1);border-bottom:2px solid var(--status-orange);">
      <span><spring:message code="pp.kanban.inProgress" text="In Progress"/></span>
      <span class="pp-badge pp-badge-orange" style="font-size:9px;">—</span>
    </div>
    <div class="pp-kanban-col-body">
      <c:forEach var="w" items="${wbsRoots}">
        <c:if test="${w.status == 'IN_PROGRESS' && w.elementType == 'TASK'}">
          <div class="pp-kanban-card"
               onclick="getData('taskDetail-app/${appid}?taskId=${w.id}','pp-content-area')">
            <div style="font-size:10px;color:var(--accent-blue);margin-bottom:4px;">${w.elementCode}</div>
            <div style="font-size:12px;font-weight:600;margin-bottom:8px;">${w.elementName}</div>
            <div class="pp-prog-track"><div class="pp-prog-fill" style="width:${w.progress}%;background:var(--status-orange);"></div></div>
            <div style="display:flex;justify-content:space-between;align-items:center;margin-top:6px;">
              <span style="font-size:10px;color:var(--text-muted);">${w.assignee}</span>
              <span style="font-size:10px;color:var(--status-orange);">${w.progress}%</span>
            </div>
          </div>
        </c:if>
      </c:forEach>
    </div>
  </div>

  <%-- In Review --%>
  <div class="pp-kanban-col">
    <div class="pp-kanban-col-hd" style="background:rgba(45,114,217,0.08);border-bottom:2px solid var(--accent-blue);">
      <span><spring:message code="pp.kanban.review" text="In Review"/></span>
      <span class="pp-badge pp-badge-blue" style="font-size:9px;">—</span>
    </div>
    <div class="pp-kanban-col-body">
      <%-- tasks in ON_HOLD status used as "review" --%>
      <c:forEach var="w" items="${wbsRoots}">
        <c:if test="${w.status == 'ON_HOLD' && w.elementType == 'TASK'}">
          <div class="pp-kanban-card"
               onclick="getData('taskDetail-app/${appid}?taskId=${w.id}','pp-content-area')">
            <div style="font-size:10px;color:var(--accent-blue);margin-bottom:4px;">${w.elementCode}</div>
            <div style="font-size:12px;font-weight:600;">${w.elementName}</div>
          </div>
        </c:if>
      </c:forEach>
    </div>
  </div>

  <%-- Done --%>
  <div class="pp-kanban-col">
    <div class="pp-kanban-col-hd" style="background:rgba(39,174,96,0.08);border-bottom:2px solid var(--status-green);">
      <span><spring:message code="pp.kanban.done" text="Done"/></span>
      <span class="pp-badge pp-badge-green" style="font-size:9px;">—</span>
    </div>
    <div class="pp-kanban-col-body">
      <c:forEach var="w" items="${wbsRoots}">
        <c:if test="${w.status == 'COMPLETED' && w.elementType == 'TASK'}">
          <div class="pp-kanban-card" style="opacity:0.7;"
               onclick="getData('taskDetail-app/${appid}?taskId=${w.id}','pp-content-area')">
            <div style="font-size:10px;color:var(--accent-blue);margin-bottom:4px;">${w.elementCode}</div>
            <div style="font-size:12px;font-weight:600;text-decoration:line-through;color:var(--text-muted);">${w.elementName}</div>
            <div style="margin-top:6px;"><i class="fa fa-check-circle" style="color:var(--status-green);font-size:12px;"></i></div>
          </div>
        </c:if>
      </c:forEach>
    </div>
  </div>
</div>
