<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="pp-breadcrumb" style="margin-bottom:12px;">
  <span onclick="getData('allProjects-app/${appid}','pp-content-area')" style="cursor:pointer;">
    <spring:message code="pp.nav.allProjects" text="All Projects"/>
  </span>
  <span onclick="getData('projectDetail-app/${appid}?projectId=${project.id}','pp-content-area')" style="cursor:pointer;">${project.projectCode}</span>
  <span style="color:white;"><spring:message code="pp.nav.materials" text="Materials (BOM)"/></span>
</div>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-boxes-stacked" style="color:var(--status-orange);"></i>
    <spring:message code="pp.material.title" text="Material Resources (BOM)"/>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm"><i class="fa fa-file-import"></i>
      <spring:message code="pp.material.import" text="Import BOM"/>
    </button>
    <button class="pp-btn-primary pp-btn-sm" onclick="ppShowMaterialForm()">
      <i class="fa fa-plus"></i>
      <spring:message code="pp.material.add" text="Add Material"/>
    </button>
  </div>
</div>

<%-- Summary Strip --%>
<div class="pp-kpi-grid" style="margin-bottom:16px;">
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.material.totalItems" text="Total Items"/></div>
    <div class="pp-kpi-value">${fn:length(materials)}</div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.material.totalPlanned" text="Total Planned Amount"/></div>
    <div class="pp-kpi-value" style="color:var(--accent-blue);">
      <c:set var="totalPlan" value="0"/>
      <c:forEach var="m" items="${materials}">
        <c:set var="totalPlan" value="${totalPlan + (m.plannedQty * m.unitPrice)}"/>
      </c:forEach>
      ${project.currency} ${totalPlan}
    </div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.material.totalActual" text="Total Actual Amount"/></div>
    <div class="pp-kpi-value" style="color:var(--accent-purple);">
      <c:set var="totalActual" value="0"/>
      <c:forEach var="m" items="${materials}">
        <c:set var="totalActual" value="${totalActual + (m.actualQty * m.unitPrice)}"/>
      </c:forEach>
      ${project.currency} ${totalActual}
    </div>
  </div>
  <div class="pp-kpi-card">
    <div class="pp-kpi-title"><spring:message code="pp.material.pending" text="Pending PO"/></div>
    <div class="pp-kpi-value" style="color:var(--status-orange);">
      <c:set var="pendingCnt" value="0"/>
      <c:forEach var="m" items="${materials}">
        <c:if test="${m.status == 'PENDING' || m.status == 'ORDERED'}">
          <c:set var="pendingCnt" value="${pendingCnt+1}"/>
        </c:if>
      </c:forEach>
      ${pendingCnt}
    </div>
  </div>
</div>

<%-- Material Table --%>
<div class="pp-card" style="padding:0;">
  <table class="pp-table">
    <thead>
      <tr>
        <th><spring:message code="pp.material.code" text="Material Code"/></th>
        <th><spring:message code="pp.material.name" text="Material Name"/></th>
        <th><spring:message code="pp.material.category" text="Category"/></th>
        <th style="text-align:center;"><spring:message code="pp.material.plannedQty" text="Planned Qty"/></th>
        <th style="text-align:center;"><spring:message code="pp.material.actualQty" text="Actual Qty"/></th>
        <th style="text-align:right;"><spring:message code="pp.material.unitPrice" text="Unit Price"/></th>
        <th style="text-align:right;"><spring:message code="pp.material.total" text="Total Amount"/></th>
        <th><spring:message code="pp.project.status" text="Status"/></th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="m" items="${materials}">
        <tr>
          <td style="font-family:monospace;font-size:11px;color:var(--accent-blue);">${m.materialCode}</td>
          <td>
            <div style="font-weight:600;font-size:12px;">${m.materialName}</div>
            <div style="font-size:10px;color:var(--text-muted);">${m.unit}</div>
          </td>
          <td>
            <span class="pp-badge pp-badge-blue" style="font-size:9px;">${m.category}</span>
          </td>
          <td style="text-align:center;">${m.plannedQty}</td>
          <td style="text-align:center;color:var(--text-muted);">
            <c:choose>
              <c:when test="${m.actualQty > m.plannedQty}">
                <span style="color:var(--status-red);">${m.actualQty}</span>
              </c:when>
              <c:otherwise>${m.actualQty}</c:otherwise>
            </c:choose>
          </td>
          <td style="text-align:right;font-size:11px;">${m.unitPrice}</td>
          <td style="text-align:right;font-weight:600;">
            ${m.totalAmount > 0 ? m.totalAmount : m.plannedQty * m.unitPrice}
          </td>
          <td>
            <span class="pp-badge
              ${m.status=='RECEIVED'?'pp-badge-green':
                m.status=='ORDERED'?'pp-badge-blue':
                m.status=='PENDING'?'pp-badge-orange':
                m.status=='CANCELLED'?'pp-badge-red':'pp-badge-gray'}"
                  style="font-size:9px;">
              <spring:message code="pp.material.status.${fn:toLowerCase(m.status)}"
                              text="${m.status}"/>
            </span>
          </td>
          <td>
            <i class="fa fa-pen" style="color:var(--text-muted);cursor:pointer;font-size:11px;"
               onclick="ppEditMaterial(${m.id})"></i>
          </td>
        </tr>
      </c:forEach>
      <c:if test="${empty materials}">
        <tr><td colspan="9" style="text-align:center;padding:40px;color:var(--text-muted);">
          <i class="fa fa-boxes-stacked" style="font-size:24px;display:block;margin-bottom:10px;opacity:0.2;"></i>
          <spring:message code="pp.label.noData" text="No materials defined"/>
          <br>
          <button class="pp-btn-primary" style="margin-top:10px;" onclick="ppShowMaterialForm()">
            <i class="fa fa-plus"></i> Add First Material
          </button>
        </td></tr>
      </c:if>
    </tbody>
  </table>
</div>

<%-- Add Material Form (hidden) --%>
<div id="ppMaterialForm" style="display:none;margin-top:14px;">
  <form id="addMaterialForm">
    <input type="hidden" name="appid" value="${appid}">
    <input type="hidden" name="projectId" value="${project.id}">
    <input type="hidden" name="editId" id="ppMaterialEditId" value="">
    <div class="pp-form-card">
      <div class="pp-form-card-title">
        <i class="fa fa-plus"></i>
        <spring:message code="pp.material.add" text="Add / Edit Material"/>
      </div>
      <div class="pp-form-row">
        <div class="pp-form-group">
          <label><spring:message code="pp.material.code" text="Material Code"/> <span class="req">*</span></label>
          <input type="text" name="materialCode" id="ppMatCode" placeholder="e.g. MAT-001" required>
        </div>
        <div class="pp-form-group">
          <label><spring:message code="pp.material.category" text="Category"/> <span class="req">*</span></label>
          <select name="category" required>
            <option value="RAW_MATERIAL">
              <spring:message code="pp.material.category.raw" text="Raw Material"/>
            </option>
            <option value="EQUIPMENT">
              <spring:message code="pp.material.category.equipment" text="Equipment"/>
            </option>
            <option value="CONSUMABLE">
              <spring:message code="pp.material.category.consumable" text="Consumable"/>
            </option>
            <option value="SERVICE">
              <spring:message code="pp.material.category.service" text="Service"/>
            </option>
            <option value="SOFTWARE">
              <spring:message code="pp.material.category.software" text="Software License"/>
            </option>
          </select>
        </div>
      </div>
      <div class="pp-form-group" style="margin-bottom:12px;">
        <label><spring:message code="pp.material.name" text="Material Name"/> <span class="req">*</span></label>
        <input type="text" name="materialName" id="ppMatName" placeholder="Enter material description" required>
      </div>
      <div class="pp-form-row">
        <div class="pp-form-group">
          <label><spring:message code="pp.material.unit" text="Unit of Measure"/></label>
          <select name="unit">
            <option value="PCS">PCS (Piece)</option>
            <option value="KG">KG (Kilogram)</option>
            <option value="M">M (Metre)</option>
            <option value="L">L (Litre)</option>
            <option value="SET">SET</option>
            <option value="LOT">LOT</option>
            <option value="HR">HR (Hour)</option>
          </select>
        </div>
        <div class="pp-form-group">
          <label><spring:message code="pp.material.unitPrice" text="Unit Price"/></label>
          <input type="number" name="unitPrice" id="ppMatUnitPrice" placeholder="0.00" step="0.01"
                 oninput="ppCalcMatTotal()">
        </div>
      </div>
      <div class="pp-form-row">
        <div class="pp-form-group">
          <label><spring:message code="pp.material.plannedQty" text="Planned Qty"/></label>
          <input type="number" name="plannedQty" id="ppMatPlannedQty" placeholder="0" step="0.01"
                 oninput="ppCalcMatTotal()">
        </div>
        <div class="pp-form-group">
          <label><spring:message code="pp.material.total" text="Total (auto)"/></label>
          <input type="number" name="totalAmount" id="ppMatTotal" readonly
                 style="background:rgba(0,0,0,0.35);opacity:0.7;">
        </div>
      </div>
      <div class="pp-form-row">
        <div class="pp-form-group">
          <label><spring:message code="pp.project.status" text="Status"/></label>
          <select name="status">
            <option value="PENDING"><spring:message code="pp.material.status.pending" text="Pending"/></option>
            <option value="ORDERED"><spring:message code="pp.material.status.ordered" text="Ordered"/></option>
            <option value="RECEIVED"><spring:message code="pp.material.status.received" text="Received"/></option>
            <option value="CANCELLED"><spring:message code="pp.material.status.cancelled" text="Cancelled"/></option>
          </select>
        </div>
      </div>
      <div style="display:flex;gap:8px;justify-content:flex-end;margin-top:6px;">
        <button type="button" class="pp-btn-outline pp-btn-sm"
                onclick="document.getElementById('ppMaterialForm').style.display='none'">
          <spring:message code="pp.btn.cancel" text="Cancel"/>
        </button>
        <button type="button" class="pp-btn-primary pp-btn-sm"
                onclick="postData('saveMaterial-app','addMaterialForm','pp-content-area')">
          <i class="fa fa-save"></i>
          <spring:message code="pp.btn.save" text="Save Material"/>
        </button>
      </div>
    </div>
  </form>
</div>

<script>
function ppShowMaterialForm() {
    document.getElementById('ppMaterialForm').style.display = 'block';
    document.getElementById('ppMaterialEditId').value = '';
    window.scrollTo(0, document.body.scrollHeight);
}

function ppCalcMatTotal() {
    var qty   = parseFloat(document.getElementById('ppMatPlannedQty').value) || 0;
    var price = parseFloat(document.getElementById('ppMatUnitPrice').value) || 0;
    document.getElementById('ppMatTotal').value = (qty * price).toFixed(2);
}

function ppEditMaterial(id) {
    document.getElementById('ppMaterialEditId').value = id;
    document.getElementById('ppMaterialForm').style.display = 'block';
    window.scrollTo(0, document.body.scrollHeight);
}
</script>
