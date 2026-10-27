<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-inbox" style="color:var(--accent-blue);"></i>
    <spring:message code="pp.approval.title" text="Approval Inbox"/>
    <c:if test="${fn:length(approvals) > 0}">
      <span class="pp-badge pp-badge-orange">${fn:length(approvals)}</span>
    </c:if>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm">
      <i class="fa fa-filter"></i>
      <spring:message code="pp.btn.filter" text="Filter"/>
    </button>
  </div>
</div>

<div class="pp-inner-tabs" style="margin-bottom:14px;">
  <div class="pp-inner-tab active" onclick="ppApprovalTab(this,'pp-ap-pending')">
    <spring:message code="pp.approval.pending" text="Pending"/>
    <c:set var="pendingCnt" value="0"/>
    <c:forEach var="a" items="${approvals}"><c:if test="${a.status=='PENDING'}"><c:set var="pendingCnt" value="${pendingCnt+1}"/></c:if></c:forEach>
    <c:if test="${pendingCnt > 0}"> (${pendingCnt})</c:if>
  </div>
  <div class="pp-inner-tab" onclick="ppApprovalTab(this,'pp-ap-resolved')">
    <spring:message code="pp.approval.resolved" text="Resolved"/>
  </div>
</div>

<div id="pp-ap-pending" class="pp-tab-content active">
  <div style="display:flex;flex-direction:column;gap:10px;">
    <c:forEach var="a" items="${approvals}">
      <c:if test="${a.status == 'PENDING'}">
        <div class="pp-card" id="ppApCard${a.id}">
          <div style="display:grid;grid-template-columns:1fr auto;gap:16px;align-items:start;">
            <div>
              <div style="display:flex;align-items:center;gap:8px;margin-bottom:6px;">
                <span class="pp-badge
                  ${a.urgency=='URGENT'?'pp-badge-red':
                    a.urgency=='HIGH'?'pp-badge-orange':'pp-badge-blue'}">
                  <spring:message code="pp.approval.urgency.${fn:toLowerCase(a.urgency)}"
                                  text="${a.urgency}"/>
                </span>
                <span class="pp-badge pp-badge-gray">
                  <spring:message code="pp.approval.type.${fn:toLowerCase(fn:replace(a.approvalType,'_',''))}"
                                  text="${a.approvalType}"/>
                </span>
              </div>
              <div style="font-size:13px;font-weight:600;margin-bottom:4px;">${a.subject}</div>
              <div style="font-size:11px;color:var(--text-muted);margin-bottom:8px;">${a.detail}</div>
              <div style="font-size:10px;color:var(--text-muted);">
                <i class="fa fa-user"></i>
                <spring:message code="pp.approval.requestedBy" text="Requested by"/>:
                ${a.requestedBy} &bull;
                <c:if test="${not empty a.project}">${a.project.projectCode} &bull;</c:if>
                ${a.createdAt}
              </div>
              <%-- Note Input (hidden) --%>
              <div id="ppApNote${a.id}" style="display:none;margin-top:10px;">
                <input type="text" id="ppApNoteInput${a.id}"
                       placeholder="Add a note (optional)..."
                       style="background:rgba(0,0,0,0.2);border:1px solid var(--input-border);
                              color:white;padding:6px 10px;border-radius:6px;
                              font-size:11px;width:100%;font-family:inherit;outline:none;">
              </div>
            </div>
            <div style="display:flex;flex-direction:column;gap:6px;min-width:100px;">
              <button class="pp-btn-green pp-btn-sm" onclick="ppResolve(${a.id},true)">
                <i class="fa fa-check"></i>
                <spring:message code="pp.approval.approve" text="Approve"/>
              </button>
              <button class="pp-btn-red pp-btn-sm" onclick="ppResolve(${a.id},false)">
                <i class="fa fa-times"></i>
                <spring:message code="pp.approval.reject" text="Reject"/>
              </button>
            </div>
          </div>

          <%-- Confirm Bar (hidden) --%>
          <div id="ppApConfirm${a.id}" style="display:none;margin-top:10px;
               border-top:1px solid rgba(255,255,255,0.06);padding-top:10px;">
            <form id="resolveForm${a.id}">
              <input type="hidden" name="appid" value="${appid}">
              <input type="hidden" name="approvalId" value="${a.id}">
              <input type="hidden" name="approved" id="ppApApproved${a.id}" value="true">
              <input type="hidden" name="note" id="ppApNoteVal${a.id}" value="">
            </form>
            <div style="display:flex;gap:8px;align-items:center;">
              <input type="text" id="ppApNoteText${a.id}"
                     placeholder="Note (optional)..."
                     style="flex:1;background:rgba(0,0,0,0.2);border:1px solid var(--input-border);
                            color:white;padding:6px 10px;border-radius:6px;font-size:11px;
                            font-family:inherit;outline:none;">
              <button class="pp-btn-primary pp-btn-sm" onclick="ppConfirmResolve(${a.id})">
                Confirm
              </button>
              <button class="pp-btn-outline pp-btn-sm"
                      onclick="document.getElementById('ppApConfirm${a.id}').style.display='none'">
                Cancel
              </button>
            </div>
          </div>
        </div>
      </c:if>
    </c:forEach>
    <c:set var="hasPending" value="false"/>
    <c:forEach var="a" items="${approvals}"><c:if test="${a.status=='PENDING'}"><c:set var="hasPending" value="true"/></c:if></c:forEach>
    <c:if test="${!hasPending}">
      <div style="text-align:center;padding:40px;color:var(--text-muted);">
        <i class="fa fa-check-circle" style="font-size:32px;display:block;margin-bottom:10px;color:var(--status-green);"></i>
        All caught up! No pending approvals.
      </div>
    </c:if>
  </div>
</div>

<div id="pp-ap-resolved" class="pp-tab-content">
  <div style="display:flex;flex-direction:column;gap:10px;">
    <c:forEach var="a" items="${approvals}">
      <c:if test="${a.status != 'PENDING'}">
        <div class="pp-card" style="opacity:0.7;">
          <div style="display:flex;justify-content:space-between;align-items:center;">
            <div>
              <div style="font-size:12px;font-weight:600;">${a.subject}</div>
              <div style="font-size:10px;color:var(--text-muted);">${a.requestedBy} &bull; ${a.resolvedAt}</div>
            </div>
            <span class="pp-badge ${a.status=='APPROVED'?'pp-badge-green':'pp-badge-red'}">
              <spring:message code="pp.approval.${fn:toLowerCase(a.status)}" text="${a.status}"/>
            </span>
          </div>
        </div>
      </c:if>
    </c:forEach>
  </div>
</div>

<script>
var ppPendingResolve = {};

function ppResolve(id, approved) {
    ppPendingResolve[id] = approved;
    document.getElementById('ppApApproved' + id).value = approved;
    document.getElementById('ppApConfirm' + id).style.display = 'block';
}

function ppConfirmResolve(id) {
    var note = document.getElementById('ppApNoteText' + id).value;
    document.getElementById('ppApNoteVal' + id).value = note;
    postData('resolveApproval-app', 'resolveForm' + id, 'pp-content-area');
}

function ppApprovalTab(el, targetId) {
    el.closest('.pp-inner-tabs').querySelectorAll('.pp-inner-tab').forEach(function(t){ t.classList.remove('active'); });
    document.querySelectorAll('.pp-tab-content').forEach(function(c){ c.classList.remove('active'); });
    el.classList.add('active');
    var t = document.getElementById(targetId);
    if(t) t.classList.add('active');
}
</script>
