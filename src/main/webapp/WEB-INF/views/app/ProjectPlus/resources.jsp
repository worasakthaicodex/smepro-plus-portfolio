<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="pp-breadcrumb" style="margin-bottom:12px;">
  <span onclick="getData('allProjects-app/${appid}','pp-content-area')" style="cursor:pointer;">
    <spring:message code="pp.nav.allProjects" text="All Projects"/>
  </span>
  <span onclick="getData('projectDetail-app/${appid}?projectId=${project.id}','pp-content-area')" style="cursor:pointer;">${project.projectCode}</span>
  <span style="color:white;"><spring:message code="pp.resource.title" text="Resources"/></span>
</div>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-users" style="color:var(--accent-blue);"></i>
    <spring:message code="pp.resource.title" text="Project Resources &amp; Capacity"/>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm" onclick="ppShowResourceForm()">
      <i class="fa fa-user-plus"></i>
      <spring:message code="pp.resource.requestResource" text="Request Resource"/>
    </button>
    <button class="pp-btn-primary pp-btn-sm">
      <i class="fa fa-chart-bar"></i>
      <spring:message code="pp.resource.capacityReport" text="Capacity Report"/>
    </button>
  </div>
</div>

<div style="display:grid;grid-template-columns:1fr 2fr;gap:16px;">
  <%-- Resource List --%>
  <div>
    <div style="font-size:13px;font-weight:600;margin-bottom:12px;">
      Resource Pool (${project.projectCode})
    </div>
    <div class="pp-search" style="margin-bottom:12px;">
      <i class="fa fa-search"></i>
      <input type="text" placeholder="Search by name, role...">
    </div>
    <div style="display:flex;flex-direction:column;gap:8px;">
      <c:forEach var="r" items="${resources}">
        <div class="pp-card" style="padding:12px;cursor:pointer;"
             onclick="ppSelectResource('${r.id}')">
          <div style="display:flex;align-items:center;gap:10px;">
            <div class="pp-avatar" style="width:36px;height:36px;font-size:12px;
                 background:${not empty r.avatarColor ? r.avatarColor : '#555'};">
              ${r.avatarInitials}
            </div>
            <div style="flex:1;">
              <div style="font-weight:600;font-size:12px;">${r.resourceName}</div>
              <div style="font-size:10px;color:var(--text-muted);">${r.role} &bull; ${r.resourceType}</div>
            </div>
            <div style="text-align:right;">
              <div style="font-size:13px;font-weight:bold;
                   color:${r.capacityPct > 100 ? 'var(--status-red)' : 'var(--status-green)'};">
                ${r.capacityPct}%
              </div>
              <div class="pp-prog-track" style="width:50px;margin-top:4px;">
                <div class="pp-prog-fill"
                     style="width:${r.capacityPct > 100 ? 100 : r.capacityPct}%;
                     background:${r.capacityPct > 100 ? 'var(--status-red)' : 'var(--status-green)'};">
                </div>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
      <c:if test="${empty resources}">
        <div style="text-align:center;padding:30px;color:var(--text-muted);">
          <i class="fa fa-user-slash" style="font-size:24px;margin-bottom:8px;display:block;"></i>
          <spring:message code="pp.label.noData" text="No resources assigned"/>
        </div>
      </c:if>
    </div>
  </div>

  <%-- Detail / Add Form --%>
  <div>
    <div class="pp-card" id="ppResourceDetail">
      <div style="text-align:center;padding:30px;color:var(--text-muted);">
        <i class="fa fa-user" style="font-size:32px;margin-bottom:10px;display:block;opacity:0.3;"></i>
        Select a resource to view details
      </div>
    </div>

    <%-- Add Resource Form (hidden) --%>
    <div id="ppResourceForm" style="display:none;">
      <form id="addResourceForm">
        <input type="hidden" name="appid" value="${appid}">
        <input type="hidden" name="projectId" value="${project.id}">
        <div class="pp-form-card">
          <div class="pp-form-card-title">
            <i class="fa fa-user-plus"></i>
            <spring:message code="pp.resource.requestResource" text="Add Resource"/>
          </div>
          <div class="pp-form-row">
            <div class="pp-form-group">
              <label><spring:message code="pp.resource.name" text="Name"/> <span class="req">*</span></label>
              <input type="text" name="resourceName" required>
            </div>
            <div class="pp-form-group">
              <label><spring:message code="pp.resource.role" text="Role"/></label>
              <input type="text" name="role">
            </div>
          </div>
          <div class="pp-form-row">
            <div class="pp-form-group">
              <label><spring:message code="pp.resource.type" text="Type"/></label>
              <select name="resourceType">
                <option value="INTERNAL"><spring:message code="pp.resource.type.internal" text="Internal"/></option>
                <option value="EXTERNAL"><spring:message code="pp.resource.type.external" text="External"/></option>
                <option value="CONTRACTOR"><spring:message code="pp.resource.type.contractor" text="Contractor"/></option>
              </select>
            </div>
            <div class="pp-form-group">
              <label><spring:message code="pp.resource.capacity" text="% Capacity"/></label>
              <input type="number" name="capacityPct" min="0" max="200" value="100">
            </div>
          </div>
          <div class="pp-form-row">
            <div class="pp-form-group">
              <label><spring:message code="pp.resource.hourlyRate" text="Hourly Rate"/></label>
              <input type="number" name="hourlyRate" step="0.01">
            </div>
            <div class="pp-form-group">
              <label>Avatar Initials</label>
              <input type="text" name="avatarInitials" maxlength="3" placeholder="e.g. SA">
            </div>
          </div>
          <div style="display:flex;gap:8px;justify-content:flex-end;">
            <button type="button" class="pp-btn-outline pp-btn-sm"
                    onclick="document.getElementById('ppResourceForm').style.display='none';
                             document.getElementById('ppResourceDetail').style.display='block';">
              <spring:message code="pp.btn.cancel" text="Cancel"/>
            </button>
            <button type="button" class="pp-btn-primary pp-btn-sm"
                    onclick="postData('addResource-app','addResourceForm','pp-content-area')">
              <i class="fa fa-save"></i> <spring:message code="pp.btn.save" text="Save"/>
            </button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>

<script>
function ppShowResourceForm() {
    document.getElementById('ppResourceDetail').style.display = 'none';
    document.getElementById('ppResourceForm').style.display = 'block';
}
function ppSelectResource(id) {
    // placeholder — ในงานจริงโหลดข้อมูล resource ผ่าน getData
}
</script>
