<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-box-archive" style="color:var(--text-muted);"></i>
    <spring:message code="pp.archived.title" text="Archived Projects"/>
  </div>
  <div class="pp-hd-actions">
    <div class="pp-search">
      <i class="fa fa-search"></i>
      <input type="text" id="ppArchiveSearch" placeholder="Search archived..."
             oninput="ppFilterArchive(this.value)">
    </div>
    <button class="pp-btn-outline pp-btn-sm"><i class="fa fa-file-excel"></i>
      <spring:message code="pp.budget.export" text="Export"/>
    </button>
  </div>
</div>

<div style="margin-bottom:14px;padding:10px 14px;border-radius:8px;
     background:rgba(255,255,255,0.04);border:1px solid rgba(255,255,255,0.06);
     font-size:12px;color:var(--text-muted);display:flex;align-items:center;gap:8px;">
  <i class="fa fa-circle-info"></i>
  <spring:message code="pp.archived.info"
      text="Archived projects are read-only. To reactivate, contact your system administrator."/>
</div>

<%-- Stats Row --%>
<div class="pp-kpi-grid" style="margin-bottom:16px;">
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.archived.total" text="Total Archived"/></div>
    <div class="pp-kpi-value">${fn:length(archivedProjects)}</div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title">Completed <i class="fa fa-check-circle" style="color:var(--status-green);"></i></div>
    <div class="pp-kpi-value" style="color:var(--status-green);">
      <c:set var="comp" value="0"/>
      <c:forEach var="p" items="${archivedProjects}">
        <c:if test="${p.status=='COMPLETED' || (not empty p.closure && p.closure.outcome=='SUCCESS')}">
          <c:set var="comp" value="${comp+1}"/>
        </c:if>
      </c:forEach>
      ${comp}
    </div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title">Cancelled <i class="fa fa-ban" style="color:var(--status-red);"></i></div>
    <div class="pp-kpi-value" style="color:var(--status-red);">
      <c:set var="canc" value="0"/>
      <c:forEach var="p" items="${archivedProjects}">
        <c:if test="${not empty p.closure && p.closure.outcome=='CANCELLED'}">
          <c:set var="canc" value="${canc+1}"/>
        </c:if>
      </c:forEach>
      ${canc}
    </div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title">Years Covered</div>
    <div class="pp-kpi-value" style="color:var(--text-muted);">—</div>
  </div>
</div>

<div class="pp-card" style="padding:0;" id="ppArchiveTable">
  <table class="pp-table">
    <thead>
      <tr>
        <th><spring:message code="pp.project.code" text="Code"/></th>
        <th><spring:message code="pp.project.name" text="Name"/></th>
        <th><spring:message code="pp.project.type" text="Type"/></th>
        <th><spring:message code="pp.project.manager" text="PM"/></th>
        <th><spring:message code="pp.project.startDate" text="Period"/></th>
        <th><spring:message code="pp.closure.outcome" text="Outcome"/></th>
        <th><spring:message code="pp.closure.finalCost" text="Final Cost"/></th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="p" items="${archivedProjects}">
        <tr>
          <td style="font-family:monospace;font-size:11px;color:var(--text-muted);">${p.projectCode}</td>
          <td style="font-weight:600;">${p.projectName}</td>
          <td style="font-size:11px;color:var(--text-muted);">${p.projectType}</td>
          <td style="font-size:11px;">${p.projectManager}</td>
          <td style="font-size:11px;color:var(--text-muted);">${p.startDate} → ${p.endDate}</td>
          <td>
            <c:choose>
              <c:when test="${not empty p.closure}">
                <span class="pp-badge
                  ${p.closure.outcome=='SUCCESS'?'pp-badge-green':
                    p.closure.outcome=='PARTIAL_SUCCESS'?'pp-badge-orange':
                    p.closure.outcome=='FAILED'?'pp-badge-red':'pp-badge-gray'}"
                      style="font-size:9px;">
                  <spring:message code="pp.closure.outcome.${fn:toLowerCase(fn:replace(p.closure.outcome,'_',''))}"
                                  text="${p.closure.outcome}"/>
                </span>
              </c:when>
              <c:otherwise>
                <span class="pp-badge pp-badge-gray" style="font-size:9px;">N/A</span>
              </c:otherwise>
            </c:choose>
          </td>
          <td style="font-size:11px;">
            <c:if test="${not empty p.closure}">${p.currency} ${p.closure.finalCost}</c:if>
            <c:if test="${empty p.closure}">—</c:if>
          </td>
          <td>
            <button class="pp-btn-outline pp-btn-sm"
                    onclick="getData('projectDetail-app/${appid}?projectId=${p.id}','pp-content-area')">
              <i class="fa fa-eye"></i>
            </button>
          </td>
        </tr>
      </c:forEach>
      <c:if test="${empty archivedProjects}">
        <tr><td colspan="8" style="text-align:center;padding:40px;color:var(--text-muted);">
          <i class="fa fa-box-archive" style="font-size:24px;display:block;margin-bottom:10px;opacity:0.2;"></i>
          <spring:message code="pp.label.noData" text="No archived projects"/>
        </td></tr>
      </c:if>
    </tbody>
  </table>
</div>

<script>
function ppFilterArchive(q) {
    var rows = document.querySelectorAll('#ppArchiveTable tbody tr');
    q = q.toLowerCase();
    rows.forEach(function(r) {
        r.style.display = r.textContent.toLowerCase().indexOf(q) > -1 ? '' : 'none';
    });
}
</script>
