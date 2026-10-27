<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- taskDetail.jsp — Task View/Edit (fragment) --%>

<div class="pp-breadcrumb" style="margin-bottom:12px;">
  <span onclick="getData('allProjects-app/${appid}','pp-content-area')" style="cursor:pointer;">
    <spring:message code="pp.nav.allProjects" text="All Projects"/>
  </span>
  <span onclick="getData('wbsElements-app/${appid}?projectId=${task.project.id}','pp-content-area')" style="cursor:pointer;">
    <spring:message code="pp.nav.wbs" text="WBS &amp; Tasks"/>
  </span>
  <span style="color:white;">${task.elementCode}</span>
</div>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <span style="font-family:monospace;font-size:13px;color:var(--accent-blue);
          background:rgba(45,114,217,0.1);padding:3px 8px;border-radius:5px;">
      ${task.elementCode}
    </span>
    ${task.elementName}
    <span class="pp-badge ${task.status=='COMPLETED'?'pp-badge-green':
                             task.status=='IN_PROGRESS'?'pp-badge-orange':
                             task.status=='NOT_STARTED'?'pp-badge-gray':'pp-badge-red'}">
      <spring:message code="pp.wbs.status.${fn:toLowerCase(fn:replace(task.status,'_',''))}" text="${task.status}"/>
    </span>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm"
            onclick="getData('timesheet-app/${appid}','pp-content-area')">
      <i class="fa fa-clock"></i> <spring:message code="pp.btn.logTime" text="Log Time"/>
    </button>
    <button class="pp-btn-primary pp-btn-sm"
            onclick="ppToggleEditMode()">
      <i class="fa fa-pen" id="ppEditIcon"></i> <span id="ppEditLabel">
        <spring:message code="pp.btn.edit" text="Edit Task"/>
      </span>
    </button>
  </div>
</div>

<%-- Loading bar --%>
<div class="pp-loading-bar" id="bartask-update-area"></div>

<div style="display:grid;grid-template-columns:2fr 1fr;gap:16px;">
  <div>
    <%-- View Mode --%>
    <div id="ppViewMode">
      <div class="pp-form-card">
        <div class="pp-form-card-title">Description &amp; Details</div>
        <div style="display:grid;grid-template-columns:1fr 1fr;gap:14px;margin-bottom:14px;">
          <div>
            <div style="font-size:10px;color:var(--text-muted);">Category</div>
            <div style="font-size:13px;font-weight:600;">
              <i class="fa ${task.taskCategory=='BUG'?'fa-bug':task.taskCategory=='DESIGN'?'fa-pen-nib':'fa-code'}"
                 style="color:var(--accent-blue);margin-right:6px;"></i>
              ${task.taskCategory}
            </div>
          </div>
          <div>
            <div style="font-size:10px;color:var(--text-muted);">Priority</div>
            <div style="font-size:13px;font-weight:600;
                 color:${task.priority=='CRITICAL'||task.priority=='HIGH'?'var(--status-red)':'white'};">
              <i class="fa fa-arrow-up" style="margin-right:4px;"></i>${task.priority}
            </div>
          </div>
        </div>
        <div style="font-size:11px;color:var(--text-muted);margin-bottom:6px;">Description &amp; Scope</div>
        <div style="font-size:12px;line-height:1.6;color:rgba(255,255,255,0.8);">
          ${not empty task.description ? task.description : 'No description provided.'}
        </div>
      </div>

      <%-- Activity Feed placeholder --%>
      <div class="pp-form-card">
        <div class="pp-form-card-title">Activity &amp; Updates</div>
        <div style="display:flex;align-items:center;gap:10px;">
          <div class="pp-avatar" style="background:var(--accent-blue);">W1</div>
          <input type="text" id="ppCommentInput"
                 placeholder="Write an update or comment... (Press Enter to send)"
                 style="flex:1;background:rgba(0,0,0,0.2);border:1px solid var(--input-border);
                        color:white;padding:8px 12px;border-radius:7px;font-size:12px;
                        font-family:inherit;outline:none;"
                 onkeydown="if(event.key==='Enter'&&this.value.trim()){ppAddComment(this.value);this.value='';}">
        </div>
        <div id="ppFeed" style="margin-top:12px;display:flex;flex-direction:column;gap:10px;"></div>
      </div>
    </div>

    <%-- Edit Mode (hidden by default) --%>
    <div id="ppEditMode" style="display:none;">
      <form id="editTaskForm">
        <input type="hidden" name="appid" value="${appid}">
        <input type="hidden" name="projectId" value="${task.project.id}">
        <input type="hidden" name="id" value="${task.id}">
        <div class="pp-form-card">
          <div class="pp-form-card-title">Edit Task</div>
          <div class="pp-form-group" style="margin-bottom:12px;">
            <label><spring:message code="pp.wbs.name" text="Task Name"/> <span class="req">*</span></label>
            <input type="text" name="elementName" value="${task.elementName}" required>
          </div>
          <div style="display:grid;grid-template-columns:1fr 1fr;gap:12px;margin-bottom:12px;">
            <div class="pp-form-group">
              <label><spring:message code="pp.project.status" text="Status"/></label>
              <select name="status">
                <option value="NOT_STARTED" ${task.status=='NOT_STARTED'?'selected':''}>Not Started</option>
                <option value="IN_PROGRESS" ${task.status=='IN_PROGRESS'?'selected':''}>In Progress</option>
                <option value="ON_HOLD" ${task.status=='ON_HOLD'?'selected':''}>On Hold</option>
                <option value="COMPLETED" ${task.status=='COMPLETED'?'selected':''}>Completed</option>
              </select>
            </div>
            <div class="pp-form-group">
              <label>
                <spring:message code="pp.label.overallProgress" text="Completion (%)"/>
                — <span id="ppEditProg">${task.progress}</span>%
              </label>
              <input type="range" class="pp-range" name="progress"
                     min="0" max="100" value="${task.progress}"
                     oninput="document.getElementById('ppEditProg').textContent=this.value">
            </div>
          </div>
          <div class="pp-form-group">
            <label>Description</label>
            <textarea name="description" rows="4">${task.description}</textarea>
          </div>
        </div>
        <div style="display:flex;gap:8px;justify-content:flex-end;">
          <button type="button" class="pp-btn-outline pp-btn-sm" onclick="ppToggleEditMode()">
            <spring:message code="pp.btn.cancel" text="Cancel"/>
          </button>
          <button type="button" class="pp-btn-primary pp-btn-sm" onclick="ppSaveTask()">
            <i class="fa fa-save"></i> <spring:message code="pp.btn.save" text="Save Changes"/>
          </button>
        </div>
      </form>
    </div>
  </div>

  <div>
    <div class="pp-form-card">
      <div class="pp-form-card-title">Progress</div>
      <div style="text-align:center;padding:10px 0 16px 0;">
        <div style="font-size:36px;font-weight:bold;
             color:${task.progress==100?'var(--status-green)':
                    task.progress>0?'var(--status-orange)':'var(--status-red)'};">
          ${task.progress}%
        </div>
      </div>
      <div class="pp-prog-track" style="height:8px;">
        <div class="pp-prog-fill" style="width:${task.progress}%;
             background:${task.progress==100?'var(--status-green)':
                          task.progress>0?'var(--status-orange)':'var(--status-red)'};">
        </div>
      </div>
    </div>

    <div class="pp-form-card">
      <div class="pp-form-card-title">Details</div>
      <table style="width:100%;font-size:11px;">
        <tr><td style="color:var(--text-muted);padding:4px 0;"><spring:message code="pp.wbs.assignee" text="Assignee"/></td>
            <td style="font-weight:600;">${task.assignee}</td></tr>
        <tr><td style="color:var(--text-muted);padding:4px 0;"><spring:message code="pp.project.startDate" text="Start"/></td>
            <td>${task.startDate}</td></tr>
        <tr><td style="color:var(--text-muted);padding:4px 0;"><spring:message code="pp.label.endDate" text="Due"/></td>
            <td>${task.endDate}</td></tr>
        <tr><td style="color:var(--text-muted);padding:4px 0;"><spring:message code="pp.wbs.plannedHours" text="Planned hrs"/></td>
            <td>${task.plannedHours}</td></tr>
        <tr><td style="color:var(--text-muted);padding:4px 0;"><spring:message code="pp.wbs.actualHours" text="Actual hrs"/></td>
            <td>${task.actualHours}</td></tr>
        <tr><td style="color:var(--text-muted);padding:4px 0;"><spring:message code="pp.project.priority" text="Priority"/></td>
            <td>${task.priority}</td></tr>
      </table>
    </div>
  </div>
</div>

<script>
function ppToggleEditMode() {
    var view = document.getElementById('ppViewMode');
    var edit = document.getElementById('ppEditMode');
    var lbl  = document.getElementById('ppEditLabel');
    var ico  = document.getElementById('ppEditIcon');
    if (edit.style.display === 'none') {
        edit.style.display = 'block';
        view.style.display = 'none';
        lbl.textContent = 'Cancel Edit';
        ico.className = 'fa fa-times';
    } else {
        edit.style.display = 'none';
        view.style.display = 'block';
        lbl.textContent = 'Edit Task';
        ico.className = 'fa fa-pen';
    }
}

function ppSaveTask() {
    postData('editTask-app', 'editTaskForm', 'pp-content-area');
}

function ppAddComment(text) {
    var feed = document.getElementById('ppFeed');
    var div = document.createElement('div');
    div.style.cssText = 'display:flex;gap:10px;align-items:flex-start;';
    div.innerHTML = '<div class="pp-avatar" style="background:var(--accent-blue);min-width:26px;">W1</div>'
        + '<div style="background:rgba(255,255,255,0.05);border-radius:8px;padding:10px 12px;flex:1;">'
        + '<div style="font-size:11px;font-weight:600;margin-bottom:4px;">You <span style="font-size:10px;color:var(--text-muted);">Just now</span></div>'
        + '<div style="font-size:12px;">' + text + '</div></div>';
    feed.insertBefore(div, feed.firstChild);
}
</script>
