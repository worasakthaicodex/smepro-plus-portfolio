<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="pp-breadcrumb" style="margin-bottom:12px;">
  <span onclick="getData('allProjects-app/${appid}','pp-content-area')" style="cursor:pointer;">
    <spring:message code="pp.nav.allProjects" text="All Projects"/>
  </span>
  <span onclick="getData('projectDetail-app/${appid}?projectId=${project.id}','pp-content-area')" style="cursor:pointer;">${project.projectCode}</span>
  <span style="color:white;"><spring:message code="pp.closure.title" text="Project Closure"/></span>
</div>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-flag-checkered" style="color:var(--status-red);"></i>
    <spring:message code="pp.closure.title" text="Project Closure &amp; Settlement"/>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm" onclick="ppSaveClosure('DRAFT')">
      <i class="fa fa-floppy-disk"></i>
      <spring:message code="pp.btn.saveDraft" text="Save Draft"/>
    </button>
    <button class="pp-btn-primary pp-btn-sm" onclick="ppSaveClosure('SUBMITTED')">
      <i class="fa fa-paper-plane"></i>
      <spring:message code="pp.btn.submit" text="Submit for Approval"/>
    </button>
  </div>
</div>

<%-- Status Banner --%>
<c:if test="${not empty closure.closureStatus && closure.closureStatus != 'DRAFT'}">
  <div style="margin-bottom:16px;padding:12px 16px;border-radius:8px;
       background:${closure.closureStatus=='APPROVED'?'rgba(39,174,96,0.15)':'rgba(45,114,217,0.1)'};
       border:1px solid ${closure.closureStatus=='APPROVED'?'rgba(39,174,96,0.3)':'rgba(45,114,217,0.2)'};
       display:flex;align-items:center;gap:10px;">
    <i class="fa ${closure.closureStatus=='APPROVED'?'fa-check-circle':'fa-hourglass-half'}"
       style="color:${closure.closureStatus=='APPROVED'?'var(--status-green)':'var(--accent-blue)'}"></i>
    <span style="font-size:12px;font-weight:600;">
      <spring:message code="pp.closure.status.${fn:toLowerCase(closure.closureStatus)}"
                      text="${closure.closureStatus}"/>
    </span>
  </div>
</c:if>

<form id="closureForm">
  <input type="hidden" name="appid" value="${appid}">
  <input type="hidden" name="projectId" value="${project.id}">
  <input type="hidden" name="closureStatus" id="ppClosureStatus"
         value="${not empty closure.closureStatus ? closure.closureStatus : 'DRAFT'}">

  <div class="pp-form-grid">
    <div>
      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-file-lines"></i>
          <spring:message code="pp.closure.summary" text="Project Summary"/>
        </div>
        <div class="pp-form-group">
          <textarea name="summary" rows="6"
                    placeholder="Summarize project objectives achieved, overall outcomes, and key deliverables...">${closure.summary}</textarea>
        </div>
      </div>

      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-lightbulb"></i>
          <spring:message code="pp.closure.lessonsLearned" text="Lessons Learned"/>
        </div>
        <div class="pp-form-group">
          <textarea name="lessonsLearned" rows="6"
                    placeholder="What went well? What should be improved? Key insights for future projects...">${closure.lessonsLearned}</textarea>
        </div>
      </div>
    </div>

    <div>
      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-chart-bar"></i>
          Final Figures
        </div>
        <div class="pp-form-group" style="margin-bottom:12px;">
          <label><spring:message code="pp.closure.finalCost" text="Final Actual Cost"/></label>
          <input type="number" name="finalCost" value="${closure.finalCost}"
                 placeholder="0.00" step="0.01">
        </div>
        <div class="pp-form-group" style="margin-bottom:12px;">
          <label><spring:message code="pp.closure.closeDate" text="Actual Close Date"/></label>
          <input type="date" name="actualCloseDate" value="${closure.actualCloseDate}">
        </div>
        <div class="pp-form-group" style="margin-bottom:12px;">
          <label><spring:message code="pp.closure.outcome" text="Outcome"/></label>
          <select name="outcome">
            <option value="SUCCESS" ${closure.outcome=='SUCCESS'?'selected':''}>
              <spring:message code="pp.closure.outcome.success" text="Success"/>
            </option>
            <option value="PARTIAL_SUCCESS" ${closure.outcome=='PARTIAL_SUCCESS'?'selected':''}>
              <spring:message code="pp.closure.outcome.partial" text="Partial Success"/>
            </option>
            <option value="FAILED" ${closure.outcome=='FAILED'?'selected':''}>
              <spring:message code="pp.closure.outcome.failed" text="Failed"/>
            </option>
            <option value="CANCELLED" ${closure.outcome=='CANCELLED'?'selected':''}>
              <spring:message code="pp.closure.outcome.cancelled" text="Cancelled"/>
            </option>
          </select>
        </div>
        <div class="pp-form-group">
          <label><spring:message code="pp.closure.closedBy" text="Closed By"/></label>
          <input type="text" name="closedBy" value="${closure.closedBy}">
        </div>
      </div>

      <%-- Project Summary Widget --%>
      <div class="pp-form-card" style="background:rgba(231,76,60,0.06);border-color:rgba(231,76,60,0.15);">
        <div class="pp-form-card-title" style="color:var(--status-red);">
          <i class="fa fa-triangle-exclamation"></i> Project Info
        </div>
        <table style="width:100%;font-size:11px;">
          <tr><td style="color:var(--text-muted);padding:4px 0;">Project</td>
              <td style="font-weight:600;">${project.projectCode}</td></tr>
          <tr><td style="color:var(--text-muted);padding:4px 0;">Status</td>
              <td>${project.status}</td></tr>
          <tr><td style="color:var(--text-muted);padding:4px 0;">Progress</td>
              <td>${project.overallProgress}%</td></tr>
          <tr><td style="color:var(--text-muted);padding:4px 0;">Budget</td>
              <td>${project.currency} ${project.budgetLimit}</td></tr>
          <tr><td style="color:var(--text-muted);padding:4px 0;">End Date</td>
              <td>${project.endDate}</td></tr>
        </table>
      </div>
    </div>
  </div>
</form>

<c:if test="${not empty message}">
  <div style="margin-top:12px;padding:10px 16px;border-radius:8px;
       background:rgba(39,174,96,0.15);border:1px solid rgba(39,174,96,0.3);
       color:var(--status-green);font-size:12px;">
    <i class="fa fa-check-circle"></i>
    <spring:message code="pp.msg.saveSuccess" text="Saved successfully"/>
  </div>
</c:if>

<script>
function ppSaveClosure(status) {
    document.getElementById('ppClosureStatus').value = status;
    postData('saveClosure-app', 'closureForm', 'pp-content-area');
}
</script>
