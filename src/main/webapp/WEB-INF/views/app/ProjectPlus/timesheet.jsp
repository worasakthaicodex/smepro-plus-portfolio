<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
#pm-glass-app .pp-ts-table { width:100%;border-collapse:collapse; }
#pm-glass-app .pp-ts-table th,
#pm-glass-app .pp-ts-table td { padding:9px 10px;border:1px solid rgba(255,255,255,0.06);font-size:12px; }
#pm-glass-app .pp-ts-table thead th { background:rgba(0,0,0,0.2);font-size:10px;color:var(--text-muted);text-align:center; }
#pm-glass-app .pp-ts-table .pp-ts-task { text-align:left; }
#pm-glass-app .pp-ts-input {
    width:50px;text-align:center;background:rgba(0,0,0,0.2);border:1px solid rgba(255,255,255,0.1);
    color:white;padding:4px;border-radius:4px;font-size:11px;font-family:inherit;
}
#pm-glass-app .pp-ts-total { font-weight:bold;color:var(--accent-blue); }
</style>

<div class="pp-proj-title-row" style="margin-bottom:16px;">
  <div class="pp-proj-title">
    <i class="fa fa-clock-rotate-left" style="color:var(--accent-blue);"></i>
    <spring:message code="pp.timesheet.title" text="Timesheet Entry"/>
  </div>
  <div class="pp-hd-actions">
    <button class="pp-btn-outline pp-btn-sm"
            onclick="getData('timesheet-app/${appid}?weekStart=${prevWeek}','pp-content-area')">
      <i class="fa fa-chevron-left"></i>
    </button>
    <span style="font-size:12px;padding:0 10px;">
      <spring:message code="pp.timesheet.weekOf" text="Week of"/> ${weekStart} — ${weekEnd}
    </span>
    <button class="pp-btn-outline pp-btn-sm"
            onclick="getData('timesheet-app/${appid}?weekStart=${nextWeek}','pp-content-area')">
      <i class="fa fa-chevron-right"></i>
    </button>
    <button class="pp-btn-primary pp-btn-sm" onclick="ppSubmitTimesheet()">
      <i class="fa fa-paper-plane"></i>
      <spring:message code="pp.timesheet.submit" text="Submit Timesheet"/>
    </button>
  </div>
</div>

<form id="timesheetForm">
  <input type="hidden" name="appid" value="${appid}">

  <div class="pp-card" style="padding:0;overflow-x:auto;">
    <table class="pp-ts-table">
      <thead>
        <tr>
          <th class="pp-ts-task" style="width:35%;text-align:left;">
            <spring:message code="pp.timesheet.task" text="Project / Task"/>
          </th>
          <th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th><th>Sun</th>
          <th style="color:var(--accent-blue);">Total</th>
          <th style="color:var(--text-muted);">Status</th>
        </tr>
      </thead>
      <tbody>
        <c:choose>
          <c:when test="${not empty entries}">
            <c:forEach var="e" items="${entries}">
              <tr>
                <td class="pp-ts-task">
                  <div style="font-weight:600;">${e.wbsElement.elementCode}: ${e.wbsElement.elementName}</div>
                  <div style="font-size:10px;color:var(--text-muted);">${e.project.projectCode}</div>
                </td>
                <td style="text-align:center;">
                  <input class="pp-ts-input" type="number" min="0" max="24" step="0.5" value="0"
                         onchange="ppCalcTotal(this)">
                </td>
                <td style="text-align:center;"><input class="pp-ts-input" type="number" min="0" max="24" step="0.5" value="0" onchange="ppCalcTotal(this)"></td>
                <td style="text-align:center;"><input class="pp-ts-input" type="number" min="0" max="24" step="0.5" value="0" onchange="ppCalcTotal(this)"></td>
                <td style="text-align:center;"><input class="pp-ts-input" type="number" min="0" max="24" step="0.5" value="0" onchange="ppCalcTotal(this)"></td>
                <td style="text-align:center;"><input class="pp-ts-input" type="number" min="0" max="24" step="0.5" value="${e.hoursLogged}" onchange="ppCalcTotal(this)"></td>
                <td style="text-align:center;"><input class="pp-ts-input" type="number" min="0" max="24" step="0.5" value="0" onchange="ppCalcTotal(this)"></td>
                <td style="text-align:center;"><input class="pp-ts-input" type="number" min="0" max="24" step="0.5" value="0" onchange="ppCalcTotal(this)"></td>
                <td class="pp-ts-total" style="text-align:center;" id="pp-row-total-${e.id}">${e.hoursLogged}</td>
                <td style="text-align:center;">
                  <span class="pp-badge
                    ${e.tsStatus=='APPROVED'?'pp-badge-green':
                      e.tsStatus=='SUBMITTED'?'pp-badge-blue':
                      e.tsStatus=='REJECTED'?'pp-badge-red':'pp-badge-gray'}">
                    <spring:message code="pp.timesheet.status.${fn:toLowerCase(e.tsStatus)}" text="${e.tsStatus}"/>
                  </span>
                </td>
              </tr>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <%-- Empty row template --%>
            <tr id="ppEmptyRow">
              <td class="pp-ts-task">
                <div style="color:var(--text-muted);font-size:11px;font-style:italic;">
                  Click "+ Add Task" to log time
                </div>
              </td>
              <td colspan="9"></td>
            </tr>
          </c:otherwise>
        </c:choose>
        <%-- Daily Total Row --%>
        <tr style="background:rgba(0,0,0,0.15);">
          <td style="font-weight:600;font-size:11px;color:var(--text-muted);">Daily Total</td>
          <td class="pp-ts-total" id="pp-day-0" style="text-align:center;">0</td>
          <td class="pp-ts-total" id="pp-day-1" style="text-align:center;">0</td>
          <td class="pp-ts-total" id="pp-day-2" style="text-align:center;">0</td>
          <td class="pp-ts-total" id="pp-day-3" style="text-align:center;">0</td>
          <td class="pp-ts-total" id="pp-day-4" style="text-align:center;">0</td>
          <td class="pp-ts-total" id="pp-day-5" style="text-align:center;">0</td>
          <td class="pp-ts-total" id="pp-day-6" style="text-align:center;">0</td>
          <td class="pp-ts-total" id="pp-grand-total" style="color:var(--status-green);">0</td>
          <td></td>
        </tr>
      </tbody>
    </table>
  </div>
</form>

<div style="margin-top:12px;display:flex;gap:8px;">
  <button class="pp-btn-outline pp-btn-sm" onclick="ppAddTimesheetRow()">
    <i class="fa fa-plus"></i> Add Task Row
  </button>
</div>

<script>
function ppCalcTotal(input) {
    var row = input.closest('tr');
    var inputs = row.querySelectorAll('.pp-ts-input');
    var total = 0;
    inputs.forEach(function(inp){ total += parseFloat(inp.value) || 0; });
    var totalCell = row.querySelector('[id^="pp-row-total"]');
    if (totalCell) totalCell.textContent = total;
    ppCalcDailyTotals();
}

function ppCalcDailyTotals() {
    var table = document.querySelector('.pp-ts-table tbody');
    var rows = table.querySelectorAll('tr:not(:last-child)');
    var dayTotals = [0,0,0,0,0,0,0];
    rows.forEach(function(row) {
        var inputs = row.querySelectorAll('.pp-ts-input');
        inputs.forEach(function(inp, idx) {
            if (idx < 7) dayTotals[idx] += parseFloat(inp.value) || 0;
        });
    });
    var grand = 0;
    dayTotals.forEach(function(d, i) {
        var cell = document.getElementById('pp-day-' + i);
        if (cell) cell.textContent = d;
        grand += d;
    });
    document.getElementById('pp-grand-total').textContent = grand;
}

function ppAddTimesheetRow() {
    var empty = document.getElementById('ppEmptyRow');
    if (empty) empty.remove();
    var tbody = document.querySelector('.pp-ts-table tbody');
    var lastRow = tbody.querySelector('tr:last-child');
    var newRow = document.createElement('tr');
    newRow.innerHTML = '<td class="pp-ts-task">'
        + '<input type="text" style="background:transparent;border:none;color:white;width:100%;font-size:12px;outline:none;" placeholder="Enter task name...">'
        + '</td>'
        + [0,0,0,0,0,0,0].map(function(){
            return '<td style="text-align:center;"><input class="pp-ts-input" type="number" min="0" max="24" step="0.5" value="0" onchange="ppCalcTotal(this)"></td>';
          }).join('')
        + '<td class="pp-ts-total" style="text-align:center;">0</td>'
        + '<td><span class="pp-badge pp-badge-gray">Draft</span></td>';
    tbody.insertBefore(newRow, lastRow);
}

function ppSubmitTimesheet() {
    postData('logTime-app', 'timesheetForm', 'pp-content-area');
}
</script>
