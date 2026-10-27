<%-- ================================================
     budget.jsp — Budget & Costs Management (fragment)
     ================================================ --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="pp-breadcrumb" style="margin-bottom:12px;">
  <span onclick="getData('allProjects-app/${appid}','pp-content-area')" style="cursor:pointer;">
    <spring:message code="pp.nav.allProjects" text="All Projects"/>
  </span>
  <span onclick="getData('projectDetail-app/${appid}?projectId=${project.id}','pp-content-area')" style="cursor:pointer;">${project.projectCode}</span>
  <span style="color:white;"><spring:message code="pp.budget.title" text="Budget &amp; Costs"/></span>
</div>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-money-check-dollar" style="color:var(--status-green);"></i>
    <spring:message code="pp.budget.title" text="Budget &amp; Cost Management"/>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm"><i class="fa fa-file-excel"></i>
      <spring:message code="pp.budget.export" text="Export FI/CO"/>
    </button>
    <button class="pp-btn-outline pp-btn-sm"><i class="fa fa-arrow-right-arrow-left"></i>
      <spring:message code="pp.budget.reallocate" text="Reallocate"/>
    </button>
  </div>
</div>

<div class="pp-kpi-grid" style="margin-bottom:20px;">
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.budget.totalLimit" text="Total Budget Limit"/></div>
    <div class="pp-kpi-value">${project.currency} ${project.budgetLimit}</div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.budget.actual" text="Actual Cost (ACWP)"/></div>
    <div class="pp-kpi-value" style="color:var(--accent-purple);">${budgetSummary.actual}</div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.budget.commitment" text="Commitment (PO)"/></div>
    <div class="pp-kpi-value" style="color:var(--status-orange);">${budgetSummary.commitment}</div>
  </div>
  <div class="pp-kpi-card" style="background:rgba(39,174,96,0.08);border-color:rgba(39,174,96,0.2);">
    <div class="pp-kpi-title"><spring:message code="pp.budget.available" text="Available (Remaining)"/></div>
    <div class="pp-kpi-value" style="color:var(--status-green);">${budgetSummary.available}</div>
  </div>
</div>

<div class="pp-section-hd">
  <div class="pp-section-title">Cost Breakdown by WBS</div>
  <button class="pp-btn-primary pp-btn-sm"
          onclick="ppShowBudgetForm()">
    <i class="fa fa-plus"></i> Add Budget Line
  </button>
</div>

<div class="pp-card" style="padding:0;margin-top:10px;">
  <table class="pp-table">
    <thead>
      <tr>
        <th><spring:message code="pp.budget.costType" text="Cost Type"/></th>
        <th><spring:message code="pp.wbs.element" text="WBS"/></th>
        <th><spring:message code="pp.budget.description" text="Description"/></th>
        <th style="text-align:right;"><spring:message code="pp.budget.planned" text="Planned"/></th>
        <th style="text-align:right;"><spring:message code="pp.budget.actual" text="Actual"/></th>
        <th style="text-align:right;"><spring:message code="pp.budget.commitment" text="Commitment"/></th>
        <th style="text-align:right;"><spring:message code="pp.budget.variance" text="Variance"/></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="b" items="${budgetLines}">
        <tr>
          <td>
            <span class="pp-badge pp-badge-blue" style="font-size:9px;">${b.costType}</span>
          </td>
          <td style="font-size:11px;color:var(--text-muted);">
            <c:if test="${not empty b.wbsElement}">${b.wbsElement.elementCode}</c:if>
            <c:if test="${empty b.wbsElement}">—</c:if>
          </td>
          <td style="font-size:12px;">${b.description}</td>
          <td style="text-align:right;font-weight:600;">${b.plannedAmount}</td>
          <td style="text-align:right;color:var(--accent-purple);">${b.actualAmount}</td>
          <td style="text-align:right;color:var(--status-orange);">${b.commitmentAmount}</td>
          <td style="text-align:right;">
            <c:set var="variance" value="${b.plannedAmount - b.actualAmount - b.commitmentAmount}"/>
            <span style="color:${variance >= 0 ? 'var(--status-green)' : 'var(--status-red)'};">
              ${variance}
            </span>
          </td>
        </tr>
      </c:forEach>
      <c:if test="${empty budgetLines}">
        <tr><td colspan="7" style="text-align:center;padding:30px;color:var(--text-muted);">
          <spring:message code="pp.label.noData" text="No budget lines"/>
        </td></tr>
      </c:if>
    </tbody>
  </table>
</div>

<%-- Add Budget Line Form (hidden) --%>
<div id="ppBudgetForm" style="display:none;margin-top:14px;">
  <form id="budgetLineForm">
    <input type="hidden" name="appid" value="${appid}">
    <input type="hidden" name="projectId" value="${project.id}">
    <div class="pp-form-card">
      <div class="pp-form-card-title"><i class="fa fa-plus"></i> Add Budget Line</div>
      <div class="pp-form-row">
        <div class="pp-form-group">
          <label><spring:message code="pp.budget.costType" text="Cost Type"/> <span class="req">*</span></label>
          <select name="costType" required>
            <option value="LABOR"><spring:message code="pp.budget.costType.labor" text="Labor"/></option>
            <option value="MATERIAL"><spring:message code="pp.budget.costType.material" text="Material"/></option>
            <option value="EQUIPMENT"><spring:message code="pp.budget.costType.equipment" text="Equipment"/></option>
            <option value="SERVICE"><spring:message code="pp.budget.costType.service" text="Service"/></option>
            <option value="OTHER"><spring:message code="pp.budget.costType.other" text="Other"/></option>
          </select>
        </div>
        <div class="pp-form-group">
          <label><spring:message code="pp.budget.planned" text="Planned Amount"/> <span class="req">*</span></label>
          <input type="number" name="plannedAmount" placeholder="0.00" step="0.01" required>
        </div>
      </div>
      <div class="pp-form-group" style="margin-bottom:10px;">
        <label>Description</label>
        <input type="text" name="description" placeholder="Budget line description...">
      </div>
      <div style="display:flex;gap:8px;justify-content:flex-end;">
        <button type="button" class="pp-btn-outline pp-btn-sm" onclick="document.getElementById('ppBudgetForm').style.display='none';">
          <spring:message code="pp.btn.cancel" text="Cancel"/>
        </button>
        <button type="button" class="pp-btn-primary pp-btn-sm" onclick="postData('saveBudget-app','budgetLineForm','pp-content-area')">
          <i class="fa fa-save"></i> <spring:message code="pp.btn.save" text="Save"/>
        </button>
      </div>
    </div>
  </form>
</div>

<script>
function ppShowBudgetForm() {
    var f = document.getElementById('ppBudgetForm');
    f.style.display = f.style.display === 'none' ? 'block' : 'none';
}
</script>
