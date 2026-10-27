<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
#pm-glass-app .pp-pipeline-col {
    display:flex;flex-direction:column;gap:10px;
}
#pm-glass-app .pp-pipeline-stage {
    padding:8px 14px;border-radius:8px 8px 0 0;
    font-size:11px;font-weight:700;letter-spacing:0.5px;text-transform:uppercase;
}
#pm-glass-app .pp-pipeline-body {
    background:rgba(0,0,0,0.15);border:1px solid rgba(255,255,255,0.06);
    border-radius:0 0 8px 8px;padding:10px;
    display:flex;flex-direction:column;gap:8px;min-height:120px;
}
#pm-glass-app .pp-pipeline-card {
    background:var(--card-bg);border:1px solid rgba(255,255,255,0.06);
    border-radius:8px;padding:12px;cursor:pointer;transition:0.15s;
}
#pm-glass-app .pp-pipeline-card:hover { background:var(--card-hover);transform:translateY(-2px); }
</style>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-filter-circle-dollar" style="color:var(--accent-purple);"></i>
    <spring:message code="pp.pipeline.title" text="Project Pipeline"/>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-primary pp-btn-sm"
            onclick="getData('newProject-app/${appid}','pp-content-area')">
      <i class="fa fa-plus"></i>
      <spring:message code="pp.project.createNew" text="New Project"/>
    </button>
  </div>
</div>

<div style="grid-column:span 4;font-size:11px;color:var(--text-muted);margin-bottom:14px;">
  <spring:message code="pp.pipeline.description"
      text="Track projects before they move to active execution. Drag or click to update stage."/>
</div>

<%-- Pipeline Stage Columns --%>
<div style="display:grid;grid-template-columns:repeat(4,1fr);gap:14px;">

  <%-- Concept --%>
  <div class="pp-pipeline-col">
    <div class="pp-pipeline-stage"
         style="background:rgba(255,255,255,0.06);color:var(--text-muted);">
      <i class="fa fa-lightbulb"></i>
      <spring:message code="pp.pipeline.concept" text="Concept"/>
    </div>
    <div class="pp-pipeline-body">
      <c:forEach var="p" items="${pipelineProjects}">
        <c:if test="${p.status == 'PIPELINE' && (empty p.pipelineStage || p.pipelineStage == 'CONCEPT')}">
          <div class="pp-pipeline-card"
               onclick="getData('projectDetail-app/${appid}?projectId=${p.id}','pp-content-area')">
            <div style="font-size:10px;color:var(--accent-blue);">${p.projectCode}</div>
            <div style="font-size:12px;font-weight:600;">${p.projectName}</div>
            <div style="font-size:10px;color:var(--text-muted);margin-top:4px;">
              <i class="fa fa-user"></i> ${p.projectManager}
            </div>
          </div>
        </c:if>
      </c:forEach>
      <c:if test="${empty pipelineProjects}">
        <div style="font-size:11px;color:var(--text-muted);text-align:center;padding:16px;">
          No projects
        </div>
      </c:if>
    </div>
  </div>

  <%-- Initiation --%>
  <div class="pp-pipeline-col">
    <div class="pp-pipeline-stage"
         style="background:rgba(45,114,217,0.1);color:var(--accent-blue);">
      <i class="fa fa-rocket"></i>
      <spring:message code="pp.pipeline.initiation" text="Initiation"/>
    </div>
    <div class="pp-pipeline-body">
      <c:forEach var="p" items="${pipelineProjects}">
        <c:if test="${p.pipelineStage == 'INITIATION'}">
          <div class="pp-pipeline-card"
               onclick="getData('projectDetail-app/${appid}?projectId=${p.id}','pp-content-area')">
            <div style="font-size:10px;color:var(--accent-blue);">${p.projectCode}</div>
            <div style="font-size:12px;font-weight:600;">${p.projectName}</div>
            <div style="font-size:10px;color:var(--text-muted);margin-top:4px;">
              ${p.currency} ${p.budgetLimit}
            </div>
          </div>
        </c:if>
      </c:forEach>
    </div>
  </div>

  <%-- Planning --%>
  <div class="pp-pipeline-col">
    <div class="pp-pipeline-stage"
         style="background:rgba(243,156,18,0.1);color:var(--status-orange);">
      <i class="fa fa-clipboard-list"></i>
      <spring:message code="pp.pipeline.planning" text="Planning &amp; Charter"/>
    </div>
    <div class="pp-pipeline-body">
      <c:forEach var="p" items="${pipelineProjects}">
        <c:if test="${p.status == 'PLANNING'}">
          <div class="pp-pipeline-card"
               onclick="getData('projectDetail-app/${appid}?projectId=${p.id}','pp-content-area')">
            <div style="font-size:10px;color:var(--accent-blue);">${p.projectCode}</div>
            <div style="font-size:12px;font-weight:600;">${p.projectName}</div>
            <div style="font-size:10px;color:var(--text-muted);margin-top:4px;">
              <i class="fa fa-calendar"></i> Start: ${p.startDate}
            </div>
          </div>
        </c:if>
      </c:forEach>
    </div>
  </div>

  <%-- Ready to Kick-off --%>
  <div class="pp-pipeline-col">
    <div class="pp-pipeline-stage"
         style="background:rgba(39,174,96,0.1);color:var(--status-green);">
      <i class="fa fa-flag"></i>
      <spring:message code="pp.pipeline.readyKickoff" text="Ready to Kick-off"/>
    </div>
    <div class="pp-pipeline-body">
      <%-- projects with status PLANNING + progress 0 as proxy for "ready" --%>
      <div style="font-size:11px;color:var(--text-muted);text-align:center;padding:16px;">
        <i class="fa fa-arrow-left" style="margin-right:4px;"></i>
        Move from Planning when charter approved
      </div>
    </div>
  </div>

</div>

<%-- All Pipeline Projects Table --%>
<div class="pp-section-hd" style="margin-top:20px;">
  <div class="pp-section-title">All Pipeline &amp; Planning Projects</div>
</div>
<div class="pp-card" style="padding:0;margin-top:10px;">
  <table class="pp-table">
    <thead>
      <tr>
        <th><spring:message code="pp.project.code" text="Code"/></th>
        <th><spring:message code="pp.project.name" text="Name"/></th>
        <th><spring:message code="pp.project.type" text="Type"/></th>
        <th><spring:message code="pp.project.manager" text="PM"/></th>
        <th><spring:message code="pp.project.startDate" text="Start"/></th>
        <th><spring:message code="pp.project.budget" text="Budget"/></th>
        <th><spring:message code="pp.project.status" text="Status"/></th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="p" items="${pipelineProjects}">
        <tr>
          <td style="font-family:monospace;font-size:11px;color:var(--accent-blue);">${p.projectCode}</td>
          <td style="font-weight:600;">${p.projectName}</td>
          <td style="font-size:11px;color:var(--text-muted);">${p.projectType}</td>
          <td style="font-size:11px;">${p.projectManager}</td>
          <td style="font-size:11px;color:var(--text-muted);">${p.startDate}</td>
          <td style="font-size:11px;">${p.currency} ${p.budgetLimit}</td>
          <td>
            <span class="pp-badge pp-badge-gray" style="font-size:9px;">${p.status}</span>
          </td>
          <td>
            <button class="pp-btn-outline pp-btn-sm"
                    onclick="getData('projectDetail-app/${appid}?projectId=${p.id}','pp-content-area')">
              <i class="fa fa-arrow-right"></i>
            </button>
          </td>
        </tr>
      </c:forEach>
      <c:if test="${empty pipelineProjects}">
        <tr><td colspan="8" style="text-align:center;padding:30px;color:var(--text-muted);">
          <i class="fa fa-filter" style="font-size:20px;display:block;margin-bottom:8px;opacity:0.3;"></i>
          <spring:message code="pp.label.noData" text="No pipeline projects"/>
        </td></tr>
      </c:if>
    </tbody>
  </table>
</div>
