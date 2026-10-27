<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Fragment: โหลดเข้า #pp-content-area ผ่าน getData --%>

<style>
#pm-glass-app .pp-proj-grid {
    display:grid; grid-template-columns:repeat(auto-fill,minmax(320px,1fr)); gap:16px;
}
#pm-glass-app .pp-proj-card {
    background:var(--card-bg); border:1px solid rgba(255,255,255,0.05);
    border-radius:10px; padding:16px;
    display:flex; flex-direction:column; gap:12px; cursor:pointer;
    transition:transform 0.2s, box-shadow 0.2s;
}
#pm-glass-app .pp-proj-card:hover {
    transform:translateY(-4px); background:var(--card-hover);
    box-shadow:0 8px 20px rgba(0,0,0,0.3);
}
#pm-glass-app .pp-proj-card-hd { display:flex; justify-content:space-between; align-items:flex-start; }
#pm-glass-app .pp-proj-code { font-size:10px; color:var(--accent-blue); font-weight:bold; letter-spacing:0.5px; margin-bottom:4px; }
#pm-glass-app .pp-proj-name { font-size:14px; font-weight:600; line-height:1.3; }
#pm-glass-app .pp-proj-details { display:grid; grid-template-columns:1fr 1fr; gap:8px; font-size:11px; border-top:1px solid rgba(255,255,255,0.05); padding-top:10px; }
#pm-glass-app .pp-proj-detail-label { color:var(--text-muted); font-size:10px; margin-bottom:2px; }
#pm-glass-app .pp-proj-detail-val { font-weight:600; font-size:12px; }
#pm-glass-app .pp-proj-footer { display:flex; justify-content:space-between; align-items:center; }
</style>

<%-- Section Header --%>
<div class="pp-section-hd" style="margin-bottom:14px;">
  <div>
    <div class="pp-section-title" style="font-size:15px;">
      <spring:message code="pp.nav.allProjects" text="All Projects"/>
    </div>
    <div style="font-size:11px;color:var(--text-muted);margin-top:2px;">
      Active Portfolio (FY${fn:substring(pageContext.session.id,0,0)})
    </div>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm"><i class="fa fa-filter"></i>
      <spring:message code="pp.btn.filter" text="Filter"/>
    </button>
    <button class="pp-btn-outline pp-btn-sm"><i class="fa fa-arrow-down-short-wide"></i> Sort</button>
    <button class="pp-btn-primary pp-btn-sm"
            onclick="getData('newProject-app/${appid}','pp-content-area')">
      <i class="fa fa-plus"></i>
      <spring:message code="pp.project.createNew" text="New Project"/>
    </button>
  </div>
</div>

<%-- KPI Row --%>
<div class="pp-kpi-grid" style="margin-bottom:20px;">
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.dashboard.totalProjects" text="Total"/> <i class="fa fa-briefcase"></i></div>
    <div class="pp-kpi-value">${fn:length(projects)}</div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.dashboard.onTrack" text="On Track"/> <i class="fa fa-circle-check" style="color:var(--status-green);"></i></div>
    <div class="pp-kpi-value" style="color:var(--status-green);">
      <c:set var="cnt" value="0"/>
      <c:forEach var="p" items="${projects}"><c:if test="${p.status == 'ON_TRACK'}"><c:set var="cnt" value="${cnt+1}"/></c:if></c:forEach>
      ${cnt}
    </div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.dashboard.atRisk" text="At Risk"/> <i class="fa fa-triangle-exclamation" style="color:var(--status-orange);"></i></div>
    <div class="pp-kpi-value" style="color:var(--status-orange);">
      <c:set var="cnt2" value="0"/>
      <c:forEach var="p" items="${projects}"><c:if test="${p.status == 'AT_RISK'}"><c:set var="cnt2" value="${cnt2+1}"/></c:if></c:forEach>
      ${cnt2}
    </div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.dashboard.delayed" text="Delayed"/> <i class="fa fa-clock" style="color:var(--status-red);"></i></div>
    <div class="pp-kpi-value" style="color:var(--status-red);">
      <c:set var="cnt3" value="0"/>
      <c:forEach var="p" items="${projects}"><c:if test="${p.status == 'DELAYED'}"><c:set var="cnt3" value="${cnt3+1}"/></c:if></c:forEach>
      ${cnt3}
    </div>
  </div>
</div>

<%-- Project Cards --%>
<div class="pp-proj-grid">
  <c:forEach var="p" items="${projects}">
    <div class="pp-proj-card"
         onclick="getData('projectDetail-app/${appid}?projectId=${p.id}','pp-content-area')">

      <div class="pp-proj-card-hd">
        <div>
          <div class="pp-proj-code">${p.projectCode}</div>
          <div class="pp-proj-name">${p.projectName}</div>
        </div>
        <span class="pp-badge
          ${p.status == 'ON_TRACK' ? 'pp-badge-green' :
            p.status == 'AT_RISK' ? 'pp-badge-orange' :
            p.status == 'DELAYED' ? 'pp-badge-red' :
            p.status == 'COMPLETED' ? 'pp-badge-blue' : 'pp-badge-gray'}">
          <spring:message code="pp.project.status.${fn:toLowerCase(p.status)}" text="${p.status}"/>
        </span>
      </div>

      <div class="pp-prog-wrap">
        <div class="pp-prog-labels">
          <span><spring:message code="pp.label.overallProgress" text="Overall Progress"/></span>
          <span style="color:${p.overallProgress >= 75 ? 'var(--status-green)' :
                               p.overallProgress >= 40 ? 'var(--status-orange)' :
                               'var(--status-red)'};">${p.overallProgress}%</span>
        </div>
        <div class="pp-prog-track">
          <div class="pp-prog-fill"
               style="width:${p.overallProgress}%;
               background:${p.overallProgress >= 75 ? 'var(--status-green)' :
                             p.overallProgress >= 40 ? 'var(--status-orange)' :
                             'var(--status-red)'};">
          </div>
        </div>
      </div>

      <div class="pp-proj-details">
        <div>
          <div class="pp-proj-detail-label"><spring:message code="pp.label.budgetSpent" text="Budget"/></div>
          <div class="pp-proj-detail-val">
            <c:choose>
              <c:when test="${not empty p.budgetLimit}">${p.currency} ${p.budgetLimit}</c:when>
              <c:otherwise>—</c:otherwise>
            </c:choose>
          </div>
        </div>
        <div>
          <div class="pp-proj-detail-label"><spring:message code="pp.label.endDate" text="End Date"/></div>
          <div class="pp-proj-detail-val">${p.endDate}</div>
        </div>
      </div>

      <div class="pp-proj-footer">
        <div style="font-size:10px;color:var(--text-muted);">
          <i class="fa fa-user"></i> ${p.projectManager}
        </div>
        <i class="fa fa-chevron-right" style="color:var(--text-muted);font-size:11px;"></i>
      </div>

    </div>
  </c:forEach>

  <c:if test="${empty projects}">
    <div style="grid-column:1/-1;text-align:center;padding:50px;color:var(--text-muted);">
      <i class="fa fa-folder-open" style="font-size:32px;margin-bottom:12px;display:block;"></i>
      <spring:message code="pp.label.noData" text="No projects found"/>
      <br>
      <button class="pp-btn-primary" style="margin-top:12px;"
              onclick="getData('newProject-app/${appid}','pp-content-area')">
        <i class="fa fa-plus"></i>
        <spring:message code="pp.project.createNew" text="Create First Project"/>
      </button>
    </div>
  </c:if>
</div>
