<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%-- Fragment: สร้าง/แก้ไขโครงการ โหลดผ่าน getData --%>

<div class="pp-proj-header" style="margin-bottom:16px;">
  <div class="pp-breadcrumb">
    <span onclick="getData('allProjects-app/${appid}','pp-content-area')" style="cursor:pointer;">
      <spring:message code="pp.nav.allProjects" text="All Projects"/>
    </span>
    <span style="color:white;">
      <c:choose>
        <c:when test="${not empty project.id}">
          <spring:message code="pp.project.editDetail" text="Edit Project"/>
        </c:when>
        <c:otherwise>
          <spring:message code="pp.project.createNew" text="New Project"/>
        </c:otherwise>
      </c:choose>
    </span>
  </div>
  <div class="pp-proj-title-row" style="margin-top:8px;">
    <div class="pp-proj-title">
      <i class="fa fa-folder-plus" style="color:var(--accent-blue);"></i>
      <c:choose>
        <c:when test="${not empty project.id}">
          <spring:message code="pp.project.editDetail" text="Edit Project Definition"/>
        </c:when>
        <c:otherwise>
          <spring:message code="pp.project.createNew" text="Create Project Definition"/>
        </c:otherwise>
      </c:choose>
    </div>
    <div class="pp-hd-actions">
      <button class="pp-btn-outline pp-btn-sm"
              onclick="getData('allProjects-app/${appid}','pp-content-area')">
        <spring:message code="pp.btn.cancel" text="Cancel"/>
      </button>
      <button class="pp-btn-primary pp-btn-sm" onclick="ppSubmitProject()">
        <i class="fa fa-check"></i>
        <c:choose>
          <c:when test="${not empty project.id}">
            <spring:message code="pp.btn.save" text="Save Changes"/>
          </c:when>
          <c:otherwise>
            <spring:message code="pp.project.createNew" text="Create Project"/>
          </c:otherwise>
        </c:choose>
      </button>
    </div>
  </div>
</div>

<form id="newProjectForm">
  <input type="hidden" name="appid" value="${appid}">
  <c:if test="${not empty project.id}">
    <input type="hidden" name="id" value="${project.id}">
  </c:if>

  <div class="pp-form-grid">

    <%-- Left Column --%>
    <div>
      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-id-card"></i>
          <spring:message code="pp.project.code" text="Project Identity"/>
        </div>

        <div class="pp-form-row">
          <div class="pp-form-group">
            <label>
              <spring:message code="pp.project.code" text="Project Code"/>
            </label>
            <div class="pp-input-group">
              <span class="pp-input-addon" style="width:120px;">PRJ-26-</span>
              <input type="text" name="projectCode" value="${project.projectCode}"
                     id="ppCodeInput" placeholder="Auto">
            </div>
            <label style="display:flex;align-items:center;gap:6px;margin-top:6px;cursor:pointer;">
              <input type="checkbox" id="ppAutoCode" checked
                     onchange="document.getElementById('ppCodeInput').disabled=this.checked;
                               if(this.checked)document.getElementById('ppCodeInput').value='';">
              <span style="font-size:10px;color:var(--text-muted);">
                <spring:message code="pp.project.autoCode" text="Auto-generate"/>
              </span>
            </label>
          </div>
          <div class="pp-form-group">
            <label>
              <spring:message code="pp.project.type" text="Project Type"/>
              <span class="req">*</span>
            </label>
            <select name="projectType" required>
              <option value="">-- Select --</option>
              <option value="CAPEX" ${project.projectType == 'CAPEX' ? 'selected' : ''}>
                <spring:message code="pp.project.type.capex" text="Capital Investment (CAPEX)"/>
              </option>
              <option value="BILLABLE" ${project.projectType == 'BILLABLE' ? 'selected' : ''}>
                <spring:message code="pp.project.type.billable" text="Customer Project (Billable)"/>
              </option>
              <option value="IT_RD" ${project.projectType == 'IT_RD' ? 'selected' : ''}>
                <spring:message code="pp.project.type.itrd" text="Internal IT / R&amp;D"/>
              </option>
              <option value="MAINTENANCE" ${project.projectType == 'MAINTENANCE' ? 'selected' : ''}>
                <spring:message code="pp.project.type.maintenance" text="Maintenance"/>
              </option>
            </select>
          </div>
        </div>

        <div class="pp-form-row">
          <div class="pp-form-group full">
            <label>
              <spring:message code="pp.project.name" text="Project Name"/>
              <span class="req">*</span>
            </label>
            <input type="text" name="projectName" value="${project.projectName}"
                   placeholder="Enter a clear, descriptive project name..."
                   style="font-size:14px;font-weight:600;" required>
          </div>
        </div>

        <div class="pp-form-row">
          <div class="pp-form-group full">
            <label><spring:message code="pp.project.objective" text="Objective &amp; Scope"/></label>
            <textarea name="objective" rows="4"
                      placeholder="Describe the main goals, deliverables, and scope...">${project.objective}</textarea>
          </div>
        </div>
      </div>

      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-sitemap"></i>
          <spring:message code="pp.project.manager" text="Organization &amp; Team"/>
        </div>
        <div class="pp-form-row">
          <div class="pp-form-group">
            <label><spring:message code="pp.project.costCenter" text="Cost Center"/></label>
            <select name="costCenter">
              <option value="">-- Select --</option>
              <option value="CC-1001" ${project.costCenter == 'CC-1001' ? 'selected' : ''}>CC-1001: Corporate IT</option>
              <option value="CC-2050" ${project.costCenter == 'CC-2050' ? 'selected' : ''}>CC-2050: Manufacturing</option>
              <option value="CC-9000" ${project.costCenter == 'CC-9000' ? 'selected' : ''}>CC-9000: R&amp;D Center</option>
            </select>
          </div>
          <div class="pp-form-group">
            <label>
              <spring:message code="pp.project.priority" text="Priority"/>
            </label>
            <select name="priority">
              <option value="LOW" ${project.priority == 'LOW' ? 'selected' : ''}>
                <spring:message code="pp.project.priority.low" text="Low"/>
              </option>
              <option value="MEDIUM" ${project.priority == 'MEDIUM' ? 'selected' : ''}>
                <spring:message code="pp.project.priority.medium" text="Medium"/>
              </option>
              <option value="HIGH" ${project.priority == 'HIGH' ? 'selected' : ''}>
                <spring:message code="pp.project.priority.high" text="High"/>
              </option>
              <option value="CRITICAL" ${project.priority == 'CRITICAL' ? 'selected' : ''}>
                <spring:message code="pp.project.priority.critical" text="Critical"/>
              </option>
            </select>
          </div>
        </div>
        <div class="pp-form-row">
          <div class="pp-form-group">
            <label><spring:message code="pp.project.manager" text="Project Manager"/></label>
            <input type="text" name="projectManager" value="${project.projectManager}"
                   placeholder="Name of Project Manager">
          </div>
          <div class="pp-form-group">
            <label><spring:message code="pp.project.sponsor" text="Sponsor / Client"/></label>
            <input type="text" name="sponsor" value="${project.sponsor}"
                   placeholder="Sponsor or client name">
          </div>
        </div>
      </div>
    </div>

    <%-- Right Column --%>
    <div>
      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-calendar"></i>
          <spring:message code="pp.project.startDate" text="Schedule"/>
        </div>
        <div class="pp-form-group" style="margin-bottom:12px;">
          <label>
            <spring:message code="pp.project.startDate" text="Start Date"/>
            <span class="req">*</span>
          </label>
          <input type="date" name="startDate" value="${project.startDate}" required>
        </div>
        <div class="pp-form-group">
          <label>
            <spring:message code="pp.label.endDate" text="End Date"/>
            <span class="req">*</span>
          </label>
          <input type="date" name="endDate" value="${project.endDate}" required>
        </div>
      </div>

      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-coins"></i>
          <spring:message code="pp.project.budget" text="Budget"/>
        </div>
        <div class="pp-form-group" style="margin-bottom:12px;">
          <label><spring:message code="pp.project.currency" text="Currency"/></label>
          <select name="currency">
            <option value="THB" ${project.currency == 'THB' ? 'selected' : ''}>THB — Thai Baht</option>
            <option value="USD" ${project.currency == 'USD' ? 'selected' : ''}>USD — US Dollar</option>
            <option value="EUR" ${project.currency == 'EUR' ? 'selected' : ''}>EUR — Euro</option>
          </select>
        </div>
        <div class="pp-form-group">
          <label><spring:message code="pp.project.budget" text="Total Budget Limit"/></label>
          <input type="number" name="budgetLimit" value="${project.budgetLimit}"
                 placeholder="0.00" step="0.01">
        </div>
      </div>

      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-flag"></i>
          <spring:message code="pp.project.status" text="Status"/>
        </div>
        <div class="pp-form-group">
          <select name="status">
            <option value="PLANNING" ${project.status == 'PLANNING' ? 'selected' : ''}>
              <spring:message code="pp.project.status.planning" text="Planning"/>
            </option>
            <option value="ON_TRACK" ${project.status == 'ON_TRACK' ? 'selected' : ''}>
              <spring:message code="pp.project.status.onTrack" text="On Track"/>
            </option>
            <option value="AT_RISK" ${project.status == 'AT_RISK' ? 'selected' : ''}>
              <spring:message code="pp.project.status.atRisk" text="At Risk"/>
            </option>
            <option value="DELAYED" ${project.status == 'DELAYED' ? 'selected' : ''}>
              <spring:message code="pp.project.status.delayed" text="Delayed"/>
            </option>
            <option value="PIPELINE" ${project.status == 'PIPELINE' ? 'selected' : ''}>
              <spring:message code="pp.project.status.pipeline" text="Pipeline"/>
            </option>
          </select>
        </div>
      </div>
    </div>

  </div><%-- /pp-form-grid --%>
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
function ppSubmitProject() {
    var url = '${not empty project.id ? "editProject-app" : "newProject-app"}?${_csrf.parameterName}=${_csrf.token}';
    postData(url, 'newProjectForm', 'pp-content-area');
}
</script>
