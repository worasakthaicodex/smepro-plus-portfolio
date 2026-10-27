<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%-- Fragment: สร้าง WBS/Task --%>

<div class="pp-breadcrumb" style="margin-bottom:12px;">
  <span onclick="getData('allProjects-app/${appid}','pp-content-area')" style="cursor:pointer;">
    <spring:message code="pp.nav.allProjects" text="All Projects"/>
  </span>
  <span onclick="getData('projectDetail-app/${appid}?projectId=${project.id}','pp-content-area')" style="cursor:pointer;">${project.projectCode}</span>
  <span onclick="getData('wbsElements-app/${appid}?projectId=${project.id}','pp-content-area')" style="cursor:pointer;">
    <spring:message code="pp.nav.wbs" text="WBS"/>
  </span>
  <span style="color:white;"><spring:message code="pp.wbs.createElement" text="New Element"/></span>
</div>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-diagram-project" style="color:var(--accent-blue);"></i>
    <spring:message code="pp.wbs.createElement" text="Create WBS / Task"/>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm"
            onclick="getData('wbsElements-app/${appid}?projectId=${project.id}','pp-content-area')">
      <spring:message code="pp.btn.cancel" text="Cancel"/>
    </button>
    <button class="pp-btn-primary pp-btn-sm" onclick="ppSubmitWbs()">
      <i class="fa fa-check"></i> <spring:message code="pp.btn.save" text="Save Element"/>
    </button>
  </div>
</div>

<%-- Type Selector --%>
<div style="display:flex;gap:10px;margin-bottom:16px;" id="ppTypeSelector">
  <div id="ppTypeWbs"
       style="flex:1;padding:12px 16px;border-radius:8px;cursor:pointer;text-align:center;
              border:1px solid rgba(255,255,255,0.1);background:rgba(255,255,255,0.04);
              color:var(--text-muted);font-size:13px;"
       onclick="ppSelectType('WBS')">
    <i class="fa fa-folder-open" style="display:block;font-size:20px;margin-bottom:6px;"></i>
    <spring:message code="pp.wbs.type.wbs" text="WBS Element (Group / Phase)"/>
  </div>
  <div id="ppTypeTask"
       style="flex:1;padding:12px 16px;border-radius:8px;cursor:pointer;text-align:center;
              border:1px solid var(--accent-blue);background:rgba(45,114,217,0.1);
              color:white;font-size:13px;font-weight:600;"
       onclick="ppSelectType('TASK')">
    <i class="fa fa-list-check" style="display:block;font-size:20px;margin-bottom:6px;color:var(--accent-blue);"></i>
    <spring:message code="pp.wbs.type.task" text="Execution Task (Activity)"/>
  </div>
</div>

<form id="newWbsForm">
  <input type="hidden" name="appid" value="${appid}">
  <input type="hidden" name="projectId" value="${project.id}">
  <input type="hidden" name="parentId" value="${parentId}">
  <input type="hidden" name="elementType" id="ppElementType" value="TASK">

  <div class="pp-form-grid">
    <div>
      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-network-wired"></i>
          <spring:message code="pp.wbs.hierarchyPreview" text="Hierarchy &amp; Identity"/>
        </div>

        <div class="pp-form-group" style="margin-bottom:14px;">
          <label>
            <spring:message code="pp.wbs.parent" text="Parent WBS Element"/>
            <span class="req">*</span>
          </label>
          <select name="parentId" onchange="document.querySelector('[name=parentId]').value=this.value;">
            <option value="">[${project.projectCode}] ${project.projectName} (Root)</option>
            <c:forEach var="w" items="${wbsRoots}">
              <option value="${w.id}" ${w.id == parentId ? 'selected' : ''}>
                [${w.elementCode}] ${w.elementName}
              </option>
            </c:forEach>
          </select>
        </div>

        <div class="pp-form-row">
          <div class="pp-form-group">
            <label>
              <spring:message code="pp.wbs.code" text="Element Code"/>
              <span class="req">*</span>
            </label>
            <div class="pp-input-group">
              <span class="pp-input-addon" id="ppCodePrefix">TSK-</span>
              <input type="text" name="elementCode" placeholder="e.g. 101" required>
            </div>
          </div>
          <div class="pp-form-group">
            <label>
              <spring:message code="pp.wbs.category.dev" text="Category"/> / Type
            </label>
            <select name="taskCategory">
              <option value="DEVELOPMENT">
                <spring:message code="pp.wbs.category.dev" text="Development"/>
              </option>
              <option value="DESIGN">
                <spring:message code="pp.wbs.category.design" text="Design"/>
              </option>
              <option value="BUG">
                <spring:message code="pp.wbs.category.bug" text="Bug Fix"/>
              </option>
              <option value="TESTING">
                <spring:message code="pp.wbs.category.testing" text="Testing"/>
              </option>
              <option value="MEETING">
                <spring:message code="pp.wbs.category.meeting" text="Meeting"/>
              </option>
            </select>
          </div>
        </div>

        <div class="pp-form-group" style="margin-bottom:14px;">
          <label>
            <spring:message code="pp.wbs.name" text="Element Name"/>
            <span class="req">*</span>
          </label>
          <input type="text" name="elementName" placeholder="Enter task name..." required
                 oninput="document.getElementById('ppPreviewName').textContent=this.value||'New Element'">
        </div>

        <div class="pp-form-group">
          <label><spring:message code="pp.wbs.element" text="Description"/></label>
          <textarea name="description" rows="3" placeholder="Task scope and acceptance criteria..."></textarea>
        </div>
      </div>

      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-spinner"></i>
          Execution &amp; Progress
        </div>
        <div class="pp-form-row">
          <div class="pp-form-group">
            <label><spring:message code="pp.project.status" text="Status"/></label>
            <select name="status">
              <option value="NOT_STARTED">
                <spring:message code="pp.wbs.status.notStarted" text="Not Started"/>
              </option>
              <option value="IN_PROGRESS">
                <spring:message code="pp.wbs.status.inProgress" text="In Progress"/>
              </option>
              <option value="ON_HOLD">
                <spring:message code="pp.wbs.status.onHold" text="On Hold"/>
              </option>
              <option value="COMPLETED">
                <spring:message code="pp.wbs.status.completed" text="Completed"/>
              </option>
            </select>
          </div>
          <div class="pp-form-group">
            <label>
              <spring:message code="pp.project.priority" text="Priority"/>
            </label>
            <select name="priority">
              <option value="LOW"><spring:message code="pp.project.priority.low" text="Low"/></option>
              <option value="MEDIUM" selected><spring:message code="pp.project.priority.medium" text="Medium"/></option>
              <option value="HIGH"><spring:message code="pp.project.priority.high" text="High"/></option>
              <option value="CRITICAL"><spring:message code="pp.project.priority.critical" text="Critical"/></option>
            </select>
          </div>
        </div>
        <div class="pp-form-group">
          <label>
            <spring:message code="pp.label.overallProgress" text="Completion (%)"/>
            — <span id="ppProgVal">0</span>%
          </label>
          <input type="range" class="pp-range" name="progress" min="0" max="100" value="0"
                 oninput="document.getElementById('ppProgVal').textContent=this.value">
        </div>
      </div>
    </div>

    <div>
      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-calendar"></i>
          <spring:message code="pp.project.startDate" text="Scheduling"/>
        </div>
        <div class="pp-form-group" style="margin-bottom:10px;">
          <label><spring:message code="pp.project.startDate" text="Start Date"/></label>
          <input type="date" name="startDate">
        </div>
        <div class="pp-form-group">
          <label><spring:message code="pp.label.endDate" text="End Date"/></label>
          <input type="date" name="endDate">
        </div>
      </div>

      <div class="pp-form-card">
        <div class="pp-form-card-title">
          <i class="fa fa-user"></i>
          <spring:message code="pp.wbs.assignee" text="Assignment"/>
        </div>
        <div class="pp-form-group" style="margin-bottom:10px;">
          <label><spring:message code="pp.wbs.assignee" text="Assignee"/></label>
          <input type="text" name="assignee" placeholder="Enter name or username">
        </div>
        <div class="pp-form-row">
          <div class="pp-form-group">
            <label><spring:message code="pp.wbs.plannedHours" text="Planned Hours"/></label>
            <input type="number" name="plannedHours" placeholder="0" step="0.5">
          </div>
          <div class="pp-form-group">
            <label><spring:message code="pp.budget.planned" text="Budget"/></label>
            <input type="number" name="budgetAmount" placeholder="0.00" step="0.01">
          </div>
        </div>
      </div>

      <%-- Hierarchy Preview --%>
      <div class="pp-form-card" style="background:rgba(45,114,217,0.06);border-color:rgba(45,114,217,0.15);">
        <div class="pp-form-card-title" style="color:var(--accent-blue);">
          <i class="fa fa-eye"></i>
          <spring:message code="pp.wbs.hierarchyPreview" text="Hierarchy Preview"/>
        </div>
        <div style="font-size:12px;padding-left:10px;border-left:2px solid rgba(255,255,255,0.1);">
          <div style="color:var(--text-muted);margin-bottom:8px;">
            <i class="fa fa-folder"></i> ${project.projectCode}: ${project.projectName}
          </div>
          <div style="color:white;padding-left:16px;">
            <i class="fa fa-asterisk" style="color:var(--accent-blue);font-size:10px;"></i>
            <span id="ppPreviewName">New Element</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</form>

<script>
function ppSelectType(type) {
    document.getElementById('ppElementType').value = type;
    document.getElementById('ppCodePrefix').textContent = type === 'WBS' ? 'WBS-' : 'TSK-';

    var wbsEl = document.getElementById('ppTypeWbs');
    var taskEl = document.getElementById('ppTypeTask');
    if (type === 'WBS') {
        wbsEl.style.cssText += 'border-color:var(--accent-blue);background:rgba(45,114,217,0.1);color:white;font-weight:600;';
        taskEl.style.cssText += 'border-color:rgba(255,255,255,0.1);background:rgba(255,255,255,0.04);color:var(--text-muted);font-weight:normal;';
    } else {
        taskEl.style.cssText += 'border-color:var(--accent-blue);background:rgba(45,114,217,0.1);color:white;font-weight:600;';
        wbsEl.style.cssText += 'border-color:rgba(255,255,255,0.1);background:rgba(255,255,255,0.04);color:var(--text-muted);font-weight:normal;';
    }
}

function ppSubmitWbs() {
    postData('newWbs-app', 'newWbsForm', 'pp-content-area');
}
</script>
