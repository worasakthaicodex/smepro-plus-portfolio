<%-- phaseGate.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="pp-breadcrumb" style="margin-bottom:12px;">
  <span onclick="getData('allProjects-app/${appid}','pp-content-area')" style="cursor:pointer;">
    <spring:message code="pp.nav.allProjects" text="All Projects"/>
  </span>
  <span onclick="getData('projectDetail-app/${appid}?projectId=${project.id}','pp-content-area')" style="cursor:pointer;">${project.projectCode}</span>
  <span style="color:white;">Phase Gate Reviews</span>
</div>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-flag-checkered" style="color:var(--accent-purple);"></i>
    Phase Gate Approval — ${project.projectName}
  </div>
  <button class="pp-btn-primary pp-btn-sm"
          onclick="getData('newPhase-app/${appid}?projectId=${project.id}','pp-content-area')">
    <i class="fa fa-plus"></i>
    <spring:message code="pp.phase.createNew" text="Create Phase"/>
  </button>
</div>

<div style="display:flex;flex-direction:column;gap:14px;">
  <c:forEach var="ph" items="${phases}" varStatus="st">
    <div class="pp-card">
      <div style="display:grid;grid-template-columns:1fr auto;gap:16px;align-items:start;">
        <div>
          <div style="font-size:11px;color:var(--text-muted);margin-bottom:4px;">
            <spring:message code="pp.phase.sequence" text="Phase"/> ${ph.sequenceNo}
          </div>
          <div style="font-size:15px;font-weight:600;margin-bottom:6px;">${ph.phaseName}</div>
          <div style="font-size:11px;color:var(--text-muted);margin-bottom:10px;">
            ${ph.startDate} → ${ph.endDate}
            <c:if test="${not empty ph.budget}"> &bull; Budget: ${ph.budget}</c:if>
          </div>
          <div class="pp-prog-wrap" style="max-width:300px;">
            <div class="pp-prog-labels">
              <span><spring:message code="pp.label.overallProgress" text="Progress"/></span>
              <span>${ph.progress}%</span>
            </div>
            <div class="pp-prog-track">
              <div class="pp-prog-fill"
                   style="width:${ph.progress}%;
                   background:${ph.progress==100?'var(--status-green)':
                                ph.progress>0?'var(--status-orange)':'rgba(255,255,255,0.2)'};">
              </div>
            </div>
          </div>
          <c:if test="${not empty ph.approvalNote}">
            <div style="margin-top:10px;font-size:11px;color:var(--text-muted);
                 background:rgba(255,255,255,0.04);padding:8px 12px;border-radius:6px;">
              <i class="fa fa-comment"></i> ${ph.approvalNote}
            </div>
          </c:if>
        </div>
        <div style="text-align:right;min-width:150px;">
          <span class="pp-badge
            ${ph.gateStatus=='APPROVED'?'pp-badge-green':
              ph.gateStatus=='REJECTED'?'pp-badge-red':
              ph.gateStatus=='IN_REVIEW'?'pp-badge-orange':'pp-badge-gray'}">
            <spring:message code="pp.phase.gateStatus.${fn:toLowerCase(ph.gateStatus)}"
                            text="${ph.gateStatus}"/>
          </span>
          <c:if test="${ph.gateStatus == 'PENDING' || ph.gateStatus == 'IN_REVIEW'}">
            <div style="display:flex;gap:6px;margin-top:10px;justify-content:flex-end;">
              <form id="approveForm${ph.id}" style="display:inline;">
                <input type="hidden" name="appid" value="${appid}">
                <input type="hidden" name="projectId" value="${project.id}">
                <input type="hidden" name="phaseId" value="${ph.id}">
                <input type="hidden" name="approved" value="true">
                <input type="hidden" name="note" id="note${ph.id}" value="">
                <button type="button" class="pp-btn-green pp-btn-sm"
                        onclick="ppApproveGate(${ph.id},true)">
                  <i class="fa fa-check"></i>
                  <spring:message code="pp.approval.approve" text="Approve"/>
                </button>
              </form>
              <button class="pp-btn-red pp-btn-sm"
                      onclick="ppApproveGate(${ph.id},false)">
                <i class="fa fa-times"></i>
                <spring:message code="pp.approval.reject" text="Reject"/>
              </button>
            </div>
            <%-- Note Input --%>
            <div id="ppNoteBox${ph.id}" style="display:none;margin-top:8px;">
              <input type="text" id="ppNoteInput${ph.id}"
                     placeholder="Approval note..."
                     style="background:rgba(0,0,0,0.2);border:1px solid var(--input-border);
                            color:white;padding:6px 10px;border-radius:6px;font-size:11px;
                            width:100%;font-family:inherit;outline:none;">
              <button class="pp-btn-primary pp-btn-sm" style="margin-top:6px;width:100%;"
                      onclick="ppConfirmGate(${ph.id})">
                Confirm
              </button>
            </div>
          </c:if>
          <c:if test="${not empty ph.approver}">
            <div style="margin-top:8px;font-size:10px;color:var(--text-muted);">
              By: ${ph.approver}
            </div>
          </c:if>
        </div>
      </div>
    </div>
  </c:forEach>
  <c:if test="${empty phases}">
    <div style="text-align:center;padding:40px;color:var(--text-muted);">
      <i class="fa fa-flag" style="font-size:24px;display:block;margin-bottom:10px;opacity:0.3;"></i>
      <spring:message code="pp.label.noData" text="No phases defined"/>
    </div>
  </c:if>
</div>

<script>
var ppPendingApproval = { id: null, approved: null };

function ppApproveGate(phaseId, approved) {
    ppPendingApproval = { id: phaseId, approved: approved };
    document.getElementById('ppNoteBox' + phaseId).style.display = 'block';
}

function ppConfirmGate(phaseId) {
    var note = document.getElementById('ppNoteInput' + phaseId).value;
    document.getElementById('note' + phaseId).value = note;
    var form = document.getElementById('approveForm' + phaseId);
    // ถ้า reject ต้องเปลี่ยน approved เป็น false
    form.querySelector('[name="approved"]').value = ppPendingApproval.approved;
    postData('phaseGate-app', 'approveForm' + phaseId, 'pp-content-area');
}
</script>
