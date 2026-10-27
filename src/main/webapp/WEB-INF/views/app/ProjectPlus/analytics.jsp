<%-- analytics.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-chart-pie" style="color:var(--accent-purple);"></i>
    <spring:message code="pp.nav.dashboards" text="Dashboards &amp; Analytics"/>
  </div>
</div>

<div class="pp-kpi-grid" style="margin-bottom:20px;">
  <div class="pp-kpi-card">
    <div class="pp-kpi-title">Total Projects <i class="fa fa-briefcase"></i></div>
    <div class="pp-kpi-value">${fn:length(projects)}</div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title">On Track <i class="fa fa-check-circle" style="color:var(--status-green);"></i></div>
    <div class="pp-kpi-value" style="color:var(--status-green);">
      <c:set var="cnt" value="0"/>
      <c:forEach var="p" items="${projects}"><c:if test="${p.status=='ON_TRACK'}"><c:set var="cnt" value="${cnt+1}"/></c:if></c:forEach>
      ${cnt}
    </div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title">At Risk / Delayed <i class="fa fa-triangle-exclamation" style="color:var(--status-orange);"></i></div>
    <div class="pp-kpi-value" style="color:var(--status-orange);">
      <c:set var="cnt2" value="0"/>
      <c:forEach var="p" items="${projects}">
        <c:if test="${p.status=='AT_RISK' || p.status=='DELAYED'}"><c:set var="cnt2" value="${cnt2+1}"/></c:if>
      </c:forEach>
      ${cnt2}
    </div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title">Completed <i class="fa fa-flag-checkered"></i></div>
    <div class="pp-kpi-value" style="color:var(--accent-blue);">
      <c:set var="cnt3" value="0"/>
      <c:forEach var="p" items="${projects}"><c:if test="${p.status=='COMPLETED'}"><c:set var="cnt3" value="${cnt3+1}"/></c:if></c:forEach>
      ${cnt3}
    </div>
  </div>
</div>

<div style="display:grid;grid-template-columns:1fr 1fr;gap:16px;">
  <%-- Project Status Distribution --%>
  <div class="pp-form-card">
    <div class="pp-form-card-title"><i class="fa fa-chart-bar"></i> Project Status Distribution</div>
    <div style="display:flex;flex-direction:column;gap:8px;">
      <c:forEach var="p" items="${projects}">
        <div style="display:flex;align-items:center;gap:10px;">
          <div style="font-size:11px;width:120px;color:var(--text-muted);white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${p.projectName}</div>
          <div class="pp-prog-track" style="flex:1;">
            <div class="pp-prog-fill"
                 style="width:${p.overallProgress}%;
                 background:${p.status=='ON_TRACK'?'var(--status-green)':
                               p.status=='DELAYED'?'var(--status-red)':'var(--status-orange)'};">
            </div>
          </div>
          <div style="font-size:10px;width:35px;text-align:right;">${p.overallProgress}%</div>
        </div>
      </c:forEach>
      <c:if test="${empty projects}">
        <div style="text-align:center;padding:20px;color:var(--text-muted);">
          <spring:message code="pp.label.noData" text="No data"/>
        </div>
      </c:if>
    </div>
  </div>

  <%-- Status Summary Table --%>
  <div class="pp-form-card">
    <div class="pp-form-card-title"><i class="fa fa-list"></i> Portfolio Summary</div>
    <table class="pp-table">
      <thead>
        <tr>
          <th>Project</th>
          <th>Status</th>
          <th>PM</th>
          <th>End Date</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="p" items="${projects}">
          <tr style="cursor:pointer;"
              onclick="getData('projectDetail-app/${appid}?projectId=${p.id}','pp-content-area')">
            <td>
              <div style="font-size:10px;color:var(--accent-blue);">${p.projectCode}</div>
              <div style="font-size:11px;font-weight:600;">${p.projectName}</div>
            </td>
            <td>
              <span class="pp-badge
                ${p.status=='ON_TRACK'?'pp-badge-green':
                  p.status=='AT_RISK'?'pp-badge-orange':
                  p.status=='DELAYED'?'pp-badge-red':'pp-badge-gray'}"
                    style="font-size:9px;">${p.status}</span>
            </td>
            <td style="font-size:11px;">${p.projectManager}</td>
            <td style="font-size:11px;color:var(--text-muted);">${p.endDate}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
