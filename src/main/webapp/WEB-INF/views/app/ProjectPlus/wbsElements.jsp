<%-- ======================================================
     wbsElements.jsp — WBS & Task Tree (fragment)
     ====================================================== --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
#pm-glass-app .pp-tree-toolbar {
    display:flex;justify-content:space-between;align-items:center;
    padding:10px 14px;border-bottom:1px solid rgba(255,255,255,0.05);
}
#pm-glass-app .pp-tree-table { width:100%; border-collapse:collapse; }
#pm-glass-app .pp-tree-table th {
    padding:8px 12px;text-align:left;font-size:10px;color:var(--text-muted);
    text-transform:uppercase;letter-spacing:0.5px;font-weight:600;

    border-bottom:1px solid rgba(255,255,255,0.06);
}
#pm-glass-app .pp-tree-table td { padding:9px 12px;border-bottom:1px solid rgba(255,255,255,0.04);font-size:12px; }
#pm-glass-app .pp-tree-table tr:hover td { background:rgba(255,255,255,0.03); }
</style>

<div class="pp-breadcrumb" style="margin-bottom:12px;">
  <span onclick="getData('allProjects-app/${appid}','pp-content-area')" style="cursor:pointer;">
    <spring:message code="pp.nav.allProjects" text="All Projects"/>
  </span>
  <span onclick="getData('projectDetail-app/${appid}?projectId=${project.id}','pp-content-area')" style="cursor:pointer;">${project.projectCode}</span>
  <span style="color:white;"><spring:message code="pp.wbs.title" text="WBS Elements"/></span>
</div>

<div class="pp-section-hd" style="margin-bottom:12px;">
  <div class="pp-proj-title">
    <i class="fa fa-sitemap" style="color:var(--accent-blue);"></i>
    <spring:message code="pp.wbs.title" text="Work Breakdown Structure &amp; Tasks"/>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm"><i class="fa fa-file-export"></i> Export</button>
    <button class="pp-btn-primary pp-btn-sm"
            onclick="getData('newWbs-app/${appid}?projectId=${project.id}','pp-content-area')">
      <i class="fa fa-plus"></i> <spring:message code="pp.wbs.addWbs" text="Add WBS"/>
    </button>
  </div>
</div>

<div class="pp-card" style="padding:0;">
  <div class="pp-tree-toolbar">
    <div style="display:flex;gap:14px;font-size:12px;color:var(--text-muted);">
      <span style="cursor:pointer;color:white;"><i class="fa fa-angles-down"></i>
        <spring:message code="pp.wbs.expandAll" text="Expand All"/>
      </span>
      <span style="cursor:pointer;"><i class="fa fa-angles-up"></i>
        <spring:message code="pp.wbs.collapseAll" text="Collapse All"/>
      </span>
    </div>
    <select style="background:rgba(0,0,0,0.3);border:1px solid var(--input-border);
            color:white;padding:4px 10px;border-radius:5px;font-size:11px;outline:none;">
      <option>Cost / Budget View</option>
      <option>Schedule View</option>
      <option>Resource View</option>
    </select>
  </div>
  <table class="pp-tree-table">
    <thead>
      <tr>
        <th style="width:40%;"><spring:message code="pp.wbs.name" text="WBS / Task Description"/></th>
        <th><spring:message code="pp.wbs.assignee" text="Assignee"/></th>
        <th><spring:message code="pp.project.status" text="Status"/></th>
        <th style="width:130px;"><spring:message code="pp.label.overallProgress" text="Progress"/></th>
        <th><spring:message code="pp.budget.planned" text="Budget"/></th>
        <th><spring:message code="pp.label.endDate" text="Due"/></th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="w" items="${wbsRoots}">
        <tr>
          <td style="padding-left:${w.levelNo*18+12}px;">
            <c:if test="${w.elementType == 'WBS'}">
              <i class="fa fa-folder" style="color:#f39c12;margin-right:7px;font-size:12px;"></i>
              <strong>${w.elementCode}</strong> ${w.elementName}
            </c:if>
            <c:if test="${w.elementType == 'TASK'}">
              <i class="fa fa-check-square" style="color:var(--text-muted);margin-right:7px;font-size:11px;"></i>
              <span style="font-family:monospace;font-size:11px;color:var(--accent-blue);">${w.elementCode}</span>
              ${w.elementName}
            </c:if>
          </td>
          <td style="font-size:11px;">${w.assignee}</td>
          <td>
            <span class="pp-badge
              ${w.status=='COMPLETED'?'pp-badge-green':
                w.status=='IN_PROGRESS'?'pp-badge-orange':
                w.status=='NOT_STARTED'?'pp-badge-gray':'pp-badge-red'}">
              <spring:message code="pp.wbs.status.${fn:toLowerCase(fn:replace(w.status,'_',''))}"
                              text="${w.status}"/>
            </span>
          </td>
          <td>
            <div style="display:flex;align-items:center;gap:6px;">
              <div class="pp-prog-track" style="flex:1;">
                <div class="pp-prog-fill"
                     style="width:${w.progress}%;
                     background:${w.progress==100?'var(--status-green)':
                                  w.progress>0?'var(--status-orange)':'rgba(255,255,255,0.2)'};">
                </div>
              </div>
              <span style="font-size:10px;width:30px;text-align:right;">${w.progress}%</span>
            </div>
          </td>
          <td style="font-size:11px;color:var(--text-muted);">
            <c:choose>
              <c:when test="${not empty w.budgetAmount}">${w.budgetAmount}</c:when>
              <c:otherwise>—</c:otherwise>
            </c:choose>
          </td>
          <td style="font-size:11px;color:var(--text-muted);">${w.endDate}</td>
          <td>
            <div style="display:flex;gap:8px;">
              <i class="fa fa-plus" style="color:var(--accent-blue);cursor:pointer;font-size:11px;"
                 title="Add child"
                 onclick="getData('newWbs-app/${appid}?projectId=${project.id}&amp;parentId=${w.id}','pp-content-area')"></i>
              <i class="fa fa-pen" style="color:var(--text-muted);cursor:pointer;font-size:11px;"
                 onclick="getData('taskDetail-app/${appid}?taskId=${w.id}','pp-content-area')"></i>
            </div>
          </td>
        </tr>
      </c:forEach>
      <c:if test="${empty wbsRoots}">
        <tr>
          <td colspan="7" style="text-align:center;padding:40px;color:var(--text-muted);">
            <i class="fa fa-sitemap" style="font-size:24px;display:block;margin-bottom:10px;"></i>
            <spring:message code="pp.label.noData" text="No WBS elements"/>
            <br>
            <button class="pp-btn-primary" style="margin-top:10px;"
                    onclick="getData('newWbs-app/${appid}?projectId=${project.id}','pp-content-area')">
              <i class="fa fa-plus"></i> <spring:message code="pp.wbs.addWbs" text="Add First WBS"/>
            </button>
          </td>
        </tr>
      </c:if>
    </tbody>
  </table>
</div>
