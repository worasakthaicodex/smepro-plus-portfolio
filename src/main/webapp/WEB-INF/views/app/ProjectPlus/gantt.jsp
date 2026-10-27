<%-- gantt.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-chart-gantt" style="color:var(--accent-blue);"></i>
    <spring:message code="pp.gantt.title" text="Gantt Timeline"/>
    <c:if test="${not empty project}"> — ${project.projectCode}</c:if>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm"><i class="fa fa-compress"></i> Fit</button>
    <button class="pp-btn-outline pp-btn-sm"><i class="fa fa-file-export"></i> Export</button>
  </div>
</div>

<div class="pp-card" style="overflow-x:auto;">
  <%-- Gantt Header --%>
  <div style="display:grid;grid-template-columns:200px 1fr;gap:0;min-width:700px;">
    <div style="padding:8px 12px;font-size:10px;color:var(--text-muted);font-weight:600;
         border-bottom:1px solid rgba(255,255,255,0.06);border-right:1px solid rgba(255,255,255,0.06);">
      TASK
    </div>
    <div style="display:grid;grid-template-columns:repeat(12,1fr);
         border-bottom:1px solid rgba(255,255,255,0.06);">
      <c:forEach var="m" items="${'Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec'.split(',')}">
        <div style="padding:8px 4px;font-size:9px;color:var(--text-muted);text-align:center;
             border-right:1px solid rgba(255,255,255,0.04);">${m}</div>
      </c:forEach>
    </div>

    <%-- Phase Rows --%>
    <c:forEach var="ph" items="${phases}">
      <div style="padding:8px 12px;font-size:11px;font-weight:600;
           border-bottom:1px solid rgba(255,255,255,0.04);
           border-right:1px solid rgba(255,255,255,0.06);">
        <i class="fa fa-folder" style="color:#f39c12;margin-right:6px;"></i>${ph.phaseName}
      </div>
      <div style="border-bottom:1px solid rgba(255,255,255,0.04);padding:8px 4px;
           display:grid;grid-template-columns:repeat(12,1fr);">
        <%-- Simplified bar (ในงานจริงคำนวณ offset+width จาก startDate/endDate) --%>
        <div style="grid-column:1/span ${ph.progress > 0 ? 6 : 3};
             height:20px;background:rgba(45,114,217,0.3);border-radius:4px;
             border:1px solid rgba(45,114,217,0.4);display:flex;align-items:center;
             padding:0 6px;font-size:10px;white-space:nowrap;overflow:hidden;">
          ${ph.phaseName} (${ph.progress}%)
        </div>
      </div>
    </c:forEach>

    <%-- Task Rows --%>
    <c:forEach var="w" items="${wbsRoots}">
      <c:if test="${w.elementType == 'TASK'}">
        <div style="padding:6px 12px 6px 24px;font-size:11px;
             border-bottom:1px solid rgba(255,255,255,0.03);
             border-right:1px solid rgba(255,255,255,0.06);
             color:var(--text-muted);">
          <i class="fa fa-check-square" style="margin-right:5px;font-size:10px;"></i>
          ${w.elementCode}
        </div>
        <div style="border-bottom:1px solid rgba(255,255,255,0.03);padding:6px 4px;
             display:grid;grid-template-columns:repeat(12,1fr);">
          <div style="grid-column:2/span ${w.progress > 0 ? 4 : 2};
               height:16px;border-radius:3px;display:flex;align-items:center;
               padding:0 4px;font-size:9px;
               background:${w.status=='COMPLETED'?'rgba(39,174,96,0.3)':
                             w.status=='IN_PROGRESS'?'rgba(243,156,18,0.3)':
                             'rgba(255,255,255,0.08)'};
               border:1px solid ${w.status=='COMPLETED'?'rgba(39,174,96,0.4)':
                                   w.status=='IN_PROGRESS'?'rgba(243,156,18,0.4)':
                                   'rgba(255,255,255,0.1)'};">
            ${w.progress}%
          </div>
        </div>
      </c:if>
    </c:forEach>
  </div>

  <c:if test="${empty phases && empty wbsRoots}">
    <div style="text-align:center;padding:40px;color:var(--text-muted);">
      <i class="fa fa-chart-gantt" style="font-size:32px;display:block;margin-bottom:10px;opacity:0.2;"></i>
      No data — create phases and tasks first.
    </div>
  </c:if>
</div>
