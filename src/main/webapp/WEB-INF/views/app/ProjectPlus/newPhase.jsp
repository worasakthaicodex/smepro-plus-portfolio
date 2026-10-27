<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="pp-breadcrumb" style="margin-bottom:12px;">
  <span onclick="getData('allProjects-app/${appid}','pp-content-area')" style="cursor:pointer;">
    <spring:message code="pp.nav.allProjects" text="All Projects"/>
  </span>
  <span onclick="getData('projectDetail-app/${appid}?projectId=${project.id}','pp-content-area')" style="cursor:pointer;">${project.projectCode}</span>
  <span onclick="getData('phaseGate-app/${appid}?projectId=${project.id}','pp-content-area')" style="cursor:pointer;">Phase Gates</span>
  <span style="color:white;"><spring:message code="pp.phase.createNew" text="New Phase"/></span>
</div>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-layer-group" style="color:var(--accent-purple);"></i>
    <spring:message code="pp.phase.createNew" text="Create Project Phase"/>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm"
            onclick="getData('phaseGate-app/${appid}?projectId=${project.id}','pp-content-area')">
      <spring:message code="pp.btn.cancel" text="Cancel"/>
    </button>
    <button class="pp-btn-primary pp-btn-sm" onclick="ppSubmitPhase()">
      <i class="fa fa-check"></i>
      <spring:message code="pp.phase.createNew" text="Create Phase"/>
    </button>
  </div>
</div>

<form id="newPhaseForm">
  <input type="hidden" name="appid" value="${appid}">
  <input type="hidden" name="projectId" value="${project.id}">

  <div class="pp-form-grid">
    <div>
      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-id-badge"></i>
          <spring:message code="pp.phase.sequence" text="Phase Identity"/>
        </div>

        <div class="pp-form-row">
          <div class="pp-form-group">
            <label>
              <spring:message code="pp.phase.sequence" text="Sequence No."/>
              <span class="req">*</span>
            </label>
            <input type="number" name="sequenceNo" min="1" value="${fn:length(phases)+1}" required>
          </div>
          <div class="pp-form-group">
            <label>
              Phase Code
            </label>
            <div class="pp-input-group">
              <span class="pp-input-addon">${project.projectCode}-PH</span>
              <input type="text" name="phaseCode" placeholder="e.g. 01">
            </div>
          </div>
        </div>

        <div class="pp-form-group" style="margin-bottom:14px;">
          <label>
            <spring:message code="pp.phase.name" text="Phase Name"/>
            <span class="req">*</span>
          </label>
          <input type="text" name="phaseName"
                 placeholder="e.g. Phase 1: Infrastructure Provisioning"
                 required>
        </div>

        <div class="pp-form-group">
          <label><spring:message code="pp.phase.description" text="Phase Description"/></label>
          <textarea name="phaseDescription" rows="4"
                    placeholder="Describe deliverables, scope, and gate approval criteria..."></textarea>
        </div>
      </div>

      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-door-open"></i> Gate Approval Settings
        </div>
        <div class="pp-form-row">
          <div class="pp-form-group">
            <label>
              <spring:message code="pp.phase.gateStatus" text="Initial Gate Status"/>
            </label>
            <select name="gateStatus">
              <option value="PENDING">
                <spring:message code="pp.phase.gateStatus.pending" text="Pending"/>
              </option>
              <option value="IN_REVIEW">
                <spring:message code="pp.phase.gateStatus.inReview" text="In Review"/>
              </option>
            </select>
          </div>
          <div class="pp-form-group">
            <label>
              <spring:message code="pp.phase.approver" text="Approver (Gate Reviewer)"/>
            </label>
            <input type="text" name="approver" placeholder="Name of phase approver">
          </div>
        </div>
      </div>
    </div>

    <div>
      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-calendar-range"></i>
          <spring:message code="pp.project.startDate" text="Schedule"/>
        </div>
        <div class="pp-form-group" style="margin-bottom:12px;">
          <label>
            <spring:message code="pp.project.startDate" text="Phase Start Date"/>
            <span class="req">*</span>
          </label>
          <input type="date" name="startDate" value="${project.startDate}" required>
        </div>
        <div class="pp-form-group">
          <label>
            <spring:message code="pp.label.endDate" text="Phase End Date"/>
            <span class="req">*</span>
          </label>
          <input type="date" name="endDate" required>
        </div>
      </div>

      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-coins"></i>
          <spring:message code="pp.project.budget" text="Phase Budget"/>
        </div>
        <div class="pp-form-group">
          <label><spring:message code="pp.budget.planned" text="Budget (${project.currency})"/></label>
          <input type="number" name="budget" placeholder="0.00" step="0.01">
        </div>
      </div>

      <%-- Preview: phases already in project --%>
      <div class="pp-form-card" style="background:rgba(45,114,217,0.06);border-color:rgba(45,114,217,0.15);">
        <div class="pp-form-card-title" style="color:var(--accent-blue);">
          <i class="fa fa-timeline"></i> Existing Phases
        </div>
        <c:choose>
          <c:when test="${not empty phases}">
            <c:forEach var="ph" items="${phases}">
              <div style="display:flex;justify-content:space-between;align-items:center;
                   padding:6px 0;border-bottom:1px solid rgba(255,255,255,0.05);font-size:11px;">
                <span style="color:var(--text-muted);">
                  Phase ${ph.sequenceNo}:
                </span>
                <span style="font-weight:600;">${ph.phaseName}</span>
                <span class="pp-badge pp-badge-gray" style="font-size:9px;">${ph.gateStatus}</span>
              </div>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <div style="font-size:11px;color:var(--text-muted);">No phases yet — this will be Phase 1.</div>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
</form>

<c:if test="${not empty message}">
  <div style="margin-top:12px;padding:10px 16px;border-radius:8px;
       background:rgba(39,174,96,0.15);border:1px solid rgba(39,174,96,0.3);
       color:var(--status-green);font-size:12px;">
    <i class="fa fa-check-circle"></i>
    <spring:message code="pp.msg.saveSuccess" text="Phase created successfully"/>
  </div>
</c:if>

<script>
function ppSubmitPhase() {
    postData('newPhase-app', 'newPhaseForm', 'pp-content-area');
}
</script>
