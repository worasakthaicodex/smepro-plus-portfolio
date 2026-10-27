<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Fragment: โหลดผ่าน getData --%>
<style>
#pm-glass-app .pp-detail-header {
    padding:16px 0 0 0; border-bottom:1px solid rgba(255,255,255,0.06);
    margin-bottom:16px;
}
#pm-glass-app .pp-health-grid { display:grid; grid-template-columns:repeat(3,1fr); gap:10px; }
#pm-glass-app .pp-health-box {
    background:rgba(0,0,0,0.15); border-radius:8px; padding:12px;
    border:1px solid rgba(255,255,255,0.05);
}
#pm-glass-app .pp-health-label { font-size:10px; color:var(--text-muted); margin-bottom:5px; }
#pm-glass-app .pp-health-val { font-size:13px; font-weight:600; }
#pm-glass-app .pp-detail-grid { display:grid; grid-template-columns:2fr 1fr; gap:16px; }
#pm-glass-app .pp-widget {
    background:rgba(0,0,0,0.2); border:1px solid rgba(255,255,255,0.05);
    border-radius:10px; padding:14px; margin-bottom:12px;
}
#pm-glass-app .pp-widget-title {
    font-size:12px; font-weight:600; margin-bottom:12px;
    display:flex; justify-content:space-between; align-items:center;
}
#pm-glass-app .pp-milestone { margin-left:12px; padding-left:14px; border-left:2px solid rgba(255,255,255,0.1); }
#pm-glass-app .pp-milestone-item { margin-bottom:12px; }
#pm-glass-app .pp-milestone-date { font-size:10px; color:var(--accent-blue); }
#pm-glass-app .pp-milestone-name { font-size:12px; font-weight:600; }
#pm-glass-app .pp-milestone-name.done { text-decoration:line-through; color:#666; }
</style>

<%-- Project Header --%>
<div class="pp-detail-header">
  <div class="pp-breadcrumb">
    <span onclick="getData('allProjects-app/${appid}','pp-content-area')" style="cursor:pointer;">
      <spring:message code="pp.nav.allProjects" text="All Projects"/>
    </span>
    <span>${project.projectCode}</span>
  </div>
  <div class="pp-proj-title-row">
    <div class="pp-proj-title">
      <i class="fa fa-folder-open" style="color:var(--accent-blue);"></i>
      ${project.projectName}
      <span class="pp-badge
        ${project.status == 'ON_TRACK' ? 'pp-badge-green' :
          project.status == 'AT_RISK' ? 'pp-badge-orange' :
          project.status == 'DELAYED' ? 'pp-badge-red' : 'pp-badge-gray'}">
        <spring:message code="pp.project.status.${fn:toLowerCase(project.status)}" text="${project.status}"/>
      </span>
    </div>
    <div class="pp-hd-actions">
      <button class="pp-btn-outline pp-btn-sm"
              onclick="getData('newProject-app/${appid}?editId=${project.id}','pp-content-area')">
        <i class="fa fa-pen"></i> <spring:message code="pp.project.editDetail" text="Edit"/>
      </button>
      <button class="pp-btn-primary pp-btn-sm"
              onclick="getData('gantt-app/${appid}?projectId=${project.id}','pp-content-area')">
        <i class="fa fa-chart-gantt"></i> <spring:message code="pp.btn.openGantt" text="Open Gantt"/>
      </button>
    </div>
  </div>
  <%-- Inner Tabs --%>
  <div class="pp-inner-tabs">
    <div class="pp-inner-tab active" onclick="ppTab(this,'pp-tab-overview')">Overview</div>
    <div class="pp-inner-tab" onclick="ppTab(this,'pp-tab-wbs')">
      <spring:message code="pp.nav.wbs" text="WBS &amp; Tasks"/>
    </div>
    <div class="pp-inner-tab" onclick="ppTab(this,'pp-tab-budget')">
      <spring:message code="pp.nav.budget" text="Budget"/>
    </div>
    <div class="pp-inner-tab" onclick="ppTab(this,'pp-tab-team')">
      <spring:message code="pp.nav.resources" text="Team"/>
    </div>
    <div class="pp-inner-tab" onclick="ppTab(this,'pp-tab-phase')">Phase Gates</div>
  </div>
</div>

<%-- Tab: Overview --%>
<div id="pp-tab-overview" class="pp-tab-content active">
  <div class="pp-detail-grid">
    <div>
      <div class="pp-widget">
        <div class="pp-widget-title">
          Project Health (Triple Constraints) <i class="fa fa-heart-pulse"></i>
        </div>
        <div class="pp-health-grid">
          <div class="pp-health-box">
            <div class="pp-health-label">Time (Schedule)</div>
            <div class="pp-health-val" style="color:var(--status-green);">
              <i class="fa fa-check-circle"></i> On Schedule
            </div>
          </div>
          <div class="pp-health-box">
            <div class="pp-health-label">Cost (Budget)</div>
            <div class="pp-health-val" style="color:var(--status-green);">
              <i class="fa fa-check-circle"></i> On Target
            </div>
          </div>
          <div class="pp-health-box">
            <div class="pp-health-label">Scope (Completion)</div>
            <div class="pp-health-val" style="color:var(--status-green);">
              <i class="fa fa-check-circle"></i> ${project.overallProgress}% Done
            </div>
          </div>
        </div>
      </div>

      <div class="pp-widget" style="background:rgba(45,114,217,0.08);border-color:rgba(45,114,217,0.2);">
        <div class="pp-widget-title" style="color:white;">Project Executive Summary</div>
        <p style="font-size:12px;line-height:1.6;color:rgba(255,255,255,0.75);margin:0;">
          ${not empty project.objective ? project.objective : 'No objective defined.'}
        </p>
      </div>

      <div class="pp-widget">
        <div class="pp-widget-title">
          <spring:message code="pp.project.details" text="Project Details"/>
        </div>
        <table class="pp-table">
          <tr>
            <td style="color:var(--text-muted);width:40%;"><spring:message code="pp.project.manager" text="PM"/></td>
            <td>${project.projectManager}</td>
          </tr>
          <tr>
            <td style="color:var(--text-muted);"><spring:message code="pp.project.sponsor" text="Sponsor"/></td>
            <td>${project.sponsor}</td>
          </tr>
          <tr>
            <td style="color:var(--text-muted);"><spring:message code="pp.project.startDate" text="Start"/></td>
            <td>${project.startDate}</td>
          </tr>
          <tr>
            <td style="color:var(--text-muted);"><spring:message code="pp.label.endDate" text="End"/></td>
            <td>${project.endDate}</td>
          </tr>
          <tr>
            <td style="color:var(--text-muted);"><spring:message code="pp.project.budget" text="Budget"/></td>
            <td>${project.currency} ${project.budgetLimit}</td>
          </tr>
          <tr>
            <td style="color:var(--text-muted);"><spring:message code="pp.project.costCenter" text="Cost Center"/></td>
            <td>${project.costCenter}</td>
          </tr>
        </table>
      </div>
    </div>

    <div>
      <div class="pp-widget">
        <div class="pp-widget-title">
          Key Milestones <i class="fa fa-flag"></i>
        </div>
        <div class="pp-milestone">
          <c:forEach var="ph" items="${phases}">
            <div class="pp-milestone-item">
              <div class="pp-milestone-date">${ph.endDate}</div>
              <div class="pp-milestone-name ${ph.progress == 100 ? 'done' : ''}">
                ${ph.phaseName}
              </div>
            </div>
          </c:forEach>
          <c:if test="${empty phases}">
            <div style="color:var(--text-muted);font-size:11px;">No phases defined yet.</div>
          </c:if>
        </div>
      </div>

      <div class="pp-widget">
        <div class="pp-widget-title">Overall Progress</div>
        <div style="text-align:center;padding:10px 0;">
          <div style="font-size:36px;font-weight:bold;color:var(--accent-blue);">
            ${project.overallProgress}%
          </div>
        </div>
        <div class="pp-prog-track" style="height:8px;">
          <div class="pp-prog-fill"
               style="width:${project.overallProgress}%;
               background:${project.overallProgress >= 75 ? 'var(--status-green)' :
                             project.overallProgress >= 40 ? 'var(--status-orange)' :
                             'var(--status-red)'};">
          </div>
        </div>
      </div>

      <div class="pp-widget">
        <div class="pp-widget-title">Quick Actions</div>
        <div style="display:flex;flex-direction:column;gap:6px;">
          <button class="pp-btn-outline"
                  onclick="getData('wbsElements-app/${appid}?projectId=${project.id}','pp-content-area')"
                  style="text-align:left;">
            <i class="fa fa-sitemap"></i> View WBS
          </button>
          <button class="pp-btn-outline"
                  onclick="getData('budget-app/${appid}?projectId=${project.id}','pp-content-area')"
                  style="text-align:left;">
            <i class="fa fa-coins"></i> Budget &amp; Costs
          </button>
          <button class="pp-btn-outline"
                  onclick="getData('closure-app/${appid}?projectId=${project.id}','pp-content-area')"
                  style="text-align:left;">
            <i class="fa fa-lock"></i> Project Closure
          </button>
        </div>
      </div>
    </div>
  </div>
</div>

<%-- Tab: WBS --%>
<div id="pp-tab-wbs" class="pp-tab-content">
  <div class="pp-section-hd" style="margin-bottom:10px;">
    <div class="pp-section-title"><spring:message code="pp.wbs.title" text="WBS &amp; Tasks"/></div>
    <button class="pp-btn-primary pp-btn-sm"
            onclick="getData('newWbs-app/${appid}?projectId=${project.id}','pp-content-area')">
      <i class="fa fa-plus"></i> <spring:message code="pp.wbs.addWbs" text="Add WBS"/>
    </button>
  </div>
  <div class="pp-card" style="padding:0;">
    <table class="pp-table">
      <thead>
        <tr>
          <th><spring:message code="pp.wbs.code" text="Code"/></th>
          <th><spring:message code="pp.wbs.name" text="Name"/></th>
          <th><spring:message code="pp.wbs.assignee" text="Assignee"/></th>
          <th><spring:message code="pp.project.status" text="Status"/></th>
          <th style="width:130px;"><spring:message code="pp.label.overallProgress" text="Progress"/></th>
          <th><spring:message code="pp.label.endDate" text="Due"/></th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="w" items="${wbsRoots}">
          <tr>
            <td style="color:var(--accent-blue);font-family:monospace;font-size:11px;">${w.elementCode}</td>
            <td style="font-weight:${w.elementType == 'WBS' ? '600' : 'normal'};
                padding-left:${w.levelNo * 16 + 12}px;">
              <c:if test="${w.elementType == 'WBS'}"><i class="fa fa-folder" style="color:var(--accent-orange);margin-right:6px;font-size:11px;"></i></c:if>
              <c:if test="${w.elementType == 'TASK'}"><i class="fa fa-check-square" style="color:var(--text-muted);margin-right:6px;font-size:11px;"></i></c:if>
              ${w.elementName}
            </td>
            <td style="font-size:11px;">${w.assignee}</td>
            <td>
              <span class="pp-badge
                ${w.status == 'COMPLETED' ? 'pp-badge-green' :
                  w.status == 'IN_PROGRESS' ? 'pp-badge-orange' :
                  w.status == 'NOT_STARTED' ? 'pp-badge-gray' : 'pp-badge-red'}">
                <spring:message code="pp.wbs.status.${fn:toLowerCase(fn:replace(w.status,'_',''))}"
                                text="${w.status}"/>
              </span>
            </td>
            <td>
              <div class="pp-prog-track">
                <div class="pp-prog-fill" style="width:${w.progress}%;"></div>
              </div>
              <div style="font-size:10px;color:var(--text-muted);text-align:right;">${w.progress}%</div>
            </td>
            <td style="font-size:11px;color:var(--text-muted);">${w.endDate}</td>
            <td>
              <i class="fa fa-pen" style="color:var(--text-muted);cursor:pointer;font-size:11px;"
                 onclick="getData('taskDetail-app/${appid}?taskId=${w.id}','pp-content-area')"></i>
            </td>
          </tr>
        </c:forEach>
        <c:if test="${empty wbsRoots}">
          <tr><td colspan="7" style="text-align:center;padding:30px;color:var(--text-muted);">
            <spring:message code="pp.label.noData" text="No WBS elements"/>
          </td></tr>
        </c:if>
      </tbody>
    </table>
  </div>
</div>

<%-- Tab: Budget --%>
<div id="pp-tab-budget" class="pp-tab-content">
  <div class="pp-kpi-grid" style="margin-bottom:16px;">
    <div class="pp-kpi-card">
      <div class="pp-kpi-title"><spring:message code="pp.budget.totalLimit" text="Budget Limit"/></div>
      <div class="pp-kpi-value">${project.currency} ${project.budgetLimit}</div>
    </div>
    <div class="pp-kpi-card">
      <div class="pp-kpi-title"><spring:message code="pp.budget.actual" text="Actual Cost"/></div>
      <div class="pp-kpi-value" style="color:var(--accent-purple);">${budgetSummary.actual}</div>
    </div>
    <div class="pp-kpi-card">
      <div class="pp-kpi-title"><spring:message code="pp.budget.commitment" text="Commitment"/></div>
      <div class="pp-kpi-value" style="color:var(--status-orange);">${budgetSummary.commitment}</div>
    </div>
    <div class="pp-kpi-card" style="background:rgba(39,174,96,0.08);border-color:rgba(39,174,96,0.2);">
      <div class="pp-kpi-title"><spring:message code="pp.budget.available" text="Available"/></div>
      <div class="pp-kpi-value" style="color:var(--status-green);">${budgetSummary.available}</div>
    </div>
  </div>
  <div style="text-align:right;margin-bottom:10px;">
    <button class="pp-btn-primary pp-btn-sm"
            onclick="getData('budget-app/${appid}?projectId=${project.id}','pp-content-area')">
      <i class="fa fa-arrow-right"></i> View Full Budget
    </button>
  </div>
</div>

<%-- Tab: Team --%>
<div id="pp-tab-team" class="pp-tab-content">
  <div class="pp-section-hd">
    <div class="pp-section-title"><spring:message code="pp.resource.title" text="Resources"/></div>
    <button class="pp-btn-primary pp-btn-sm"
            onclick="getData('resources-app/${appid}?projectId=${project.id}','pp-content-area')">
      <i class="fa fa-arrow-right"></i> Manage Resources
    </button>
  </div>
  <div class="pp-card" style="padding:0;">
    <table class="pp-table">
      <thead>
        <tr>
          <th></th>
          <th><spring:message code="pp.resource.name" text="Name"/></th>
          <th><spring:message code="pp.resource.role" text="Role"/></th>
          <th><spring:message code="pp.resource.type" text="Type"/></th>
          <th><spring:message code="pp.resource.capacity" text="Capacity"/></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="r" items="${resources}">
          <tr>
            <td>
              <div class="pp-avatar" style="background:${not empty r.avatarColor ? r.avatarColor : '#555'};">
                ${r.avatarInitials}
              </div>
            </td>
            <td style="font-weight:600;">${r.resourceName}</td>
            <td style="color:var(--text-muted);font-size:11px;">${r.role}</td>
            <td>
              <span class="pp-badge pp-badge-gray" style="font-size:9px;">${r.resourceType}</span>
            </td>
            <td>
              <div class="pp-prog-track" style="width:80px;display:inline-block;">
                <div class="pp-prog-fill"
                     style="width:${r.capacityPct > 100 ? 100 : r.capacityPct}%;
                     background:${r.capacityPct > 100 ? 'var(--status-red)' : 'var(--status-green)'};">
                </div>
              </div>
              <span style="font-size:10px;margin-left:6px;
                    color:${r.capacityPct > 100 ? 'var(--status-red)' : 'var(--text-muted)'};">
                ${r.capacityPct}%
              </span>
            </td>
          </tr>
        </c:forEach>
        <c:if test="${empty resources}">
          <tr><td colspan="5" style="text-align:center;padding:30px;color:var(--text-muted);">
            <spring:message code="pp.label.noData" text="No resources assigned"/>
          </td></tr>
        </c:if>
      </tbody>
    </table>
  </div>
</div>

<%-- Tab: Phase Gates --%>
<div id="pp-tab-phase" class="pp-tab-content">
  <div class="pp-section-hd">
    <div class="pp-section-title">Phase Gates</div>
    <button class="pp-btn-primary pp-btn-sm"
            onclick="getData('newPhase-app/${appid}?projectId=${project.id}','pp-content-area')">
      <i class="fa fa-plus"></i> <spring:message code="pp.phase.createNew" text="New Phase"/>
    </button>
  </div>
  <div style="display:flex;flex-direction:column;gap:10px;">
    <c:forEach var="ph" items="${phases}" varStatus="st">
      <div class="pp-widget">
        <div style="display:flex;justify-content:space-between;align-items:center;">
          <div>
            <div style="font-size:11px;color:var(--text-muted);">Phase ${ph.sequenceNo}</div>
            <div style="font-size:13px;font-weight:600;">${ph.phaseName}</div>
            <div style="font-size:10px;color:var(--text-muted);">${ph.startDate} → ${ph.endDate}</div>
          </div>
          <span class="pp-badge
            ${ph.gateStatus == 'APPROVED' ? 'pp-badge-green' :
              ph.gateStatus == 'REJECTED' ? 'pp-badge-red' :
              ph.gateStatus == 'IN_REVIEW' ? 'pp-badge-orange' : 'pp-badge-gray'}">
            <spring:message code="pp.phase.gateStatus.${fn:toLowerCase(ph.gateStatus)}"
                            text="${ph.gateStatus}"/>
          </span>
        </div>
        <div class="pp-prog-wrap" style="margin-top:10px;">
          <div class="pp-prog-track">
            <div class="pp-prog-fill" style="width:${ph.progress}%;"></div>
          </div>
        </div>
      </div>
    </c:forEach>
    <c:if test="${empty phases}">
      <div style="text-align:center;padding:30px;color:var(--text-muted);">
        <spring:message code="pp.label.noData" text="No phases defined"/>
      </div>
    </c:if>
  </div>
</div>

<script>
function ppTab(el, targetId) {
    // deactivate all tabs & content
    el.closest('.pp-inner-tabs').querySelectorAll('.pp-inner-tab').forEach(function(t){ t.classList.remove('active'); });
    document.querySelectorAll('.pp-tab-content').forEach(function(c){ c.classList.remove('active'); });
    // activate selected
    el.classList.add('active');
    var target = document.getElementById(targetId);
    if(target) target.classList.add('active');
}
</script>
