<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Action Plan Form</title>
    <style>
     :root {
            --bg-color: #f0f0f0;
            --border-color: #d9d9d9;
            --text-color: #000;
            --highlight-blue: #3399ff;
            --input-border: #7a7a7a;
            --light-border: #fff;
        }

        /* Main Window */
        .subsettingwindow {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            position: relative;
        }

  .subsettingwindow  input[type="text"], .subsettingwindow  input[type="number"], .subsettingwindow  input[type="date"] {
            flex-grow: 1;
            border: 1px solid var(--input-border);
            padding: 3px;
            font-family: inherit;
        }

        .subsettingwindow select {
            border: 1px solid var(--input-border);
            padding: 3px;
            font-family: inherit;
            background-color: var(--input-bg);
            outline: none;
            width:100%;
        }

      .subsettingwindow   h1.title {
            font-size: 12px;
            margin: 0 0 10px 0;
            font-weight: normal;
        }

        /* Tabs */
       .subsettingwindow  .tabs {
            display: flex;
            margin-bottom: -1px; /* Overlap border */
            padding-left: 2px;
        }

       .subsettingwindow  .tab {
            padding: 6px 12px;
            background-color: var(--bg-color);
            border: 1px solid var(--border-color);
            cursor: pointer;
            margin-right: 2px;
            position: relative;
            top: 1px;
            font-size: 13px;
        }

        .subsettingwindow .tab.active {
            background-color: #fff;
            border: 1px solid #999;
            border-bottom: 1px solid #fff; /* Hide bottom border */
            z-index: 2;
            font-weight: bold;
            color: var(--highlight-blue);
        }

       .subsettingwindow  .tab:hover:not(.active) {
            background-color: #e5e5e5;
        }

        /* Content Panel */
        .panel {
            background-color: #fff;
            border: 1px solid #999;
            padding: 15px;
            height: 380px;
            position: relative;
            z-index: 1;
            overflow-y: auto;
        }

        /* Section Header with Line */
       .subsettingwindow  .section-header {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
            white-space: nowrap;
            font-weight: bold;
        }

       .subsettingwindow  .section-header span {
            margin-right: 5px;
        }

       .subsettingwindow  .section-header .line {
            width: 100%;
            height: 1px;
            background-color: #a0a0a0;
            margin-top: 4px;
        }

       .subsettingwindow  .form-row {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .subsettingwindow .label {
            width: 120px;
            flex-shrink: 0;
            font-size: 12px;
        }

        /* Footer Buttons */
        .footers {
            display: flex;
            justify-content: flex-end;
            margin-top: 15px;
            gap: 10px;
        }

       .subsettingwindow  button {
            min-width: 75px;
            padding: 6px 12px;
            background-color: #e1e1e1;
            border: 1px solid #adadad;
            font-size: 12px;
            cursor: pointer;
        }
        
      .subsettingwindow   button:hover {
            border-color: #0078d7;
            background-color: #e5f1fb;
        }

        .subsettingwindow button.primary {
            border: 1px solid #0078d7;
            box-shadow: inset 0 0 0 1px #8dcce8;
            background-color: #005FB8;
            color: white;
        }
        .subsettingwindow button.primary:hover {
            background-color: #004c99;
        }
        .subsettingwindow button.danger {
            background-color: #fee2e2;
            border-color: #fecaca;
            color: #dc2626;
        }
        .subsettingwindow button.danger:hover {
            background-color: #fca5a5;
            color: white;
        }
    </style>
</head>
<body>

<div class="subsettingwindow">

    <div class="tabs">
        <div class="tab active" id="tab-plan" onclick="switchActionTab('plan')">Action Plan Setup</div>
        <div class="tab" id="tab-result" onclick="switchActionTab('result')">Execution Result</div>
    </div>

    <div class="panel">
        <div id="saveResult"></div>
        <form id="actionPlanForm" name="actionPlanForm" method="POST">
            
            <c:if test="${not empty actionplanData.id}">
                <input type="hidden" name="id" value="${actionplanData.id}">   
            </c:if>
            <input type="hidden" name="idreport" value="${idreport}">

            <div id="content-plan" style="display: block;">
                <div class="section-header">
                    <span>รายละเอียดแผนดำเนินการ:</span>
                    <div class="line"></div>
                </div>

                <div class="form-row">
                    <span class="label">ปัญหา / หัวข้อ:</span>
                    <input type="text" name="issueTopic" value="${actionplanData.issueTopic}" required placeholder="ระบุปัญหา หรือ หัวข้อ...">
                </div>

                <div class="form-row">
                    <span class="label">Impact:</span>
                    <input type="number" step="0.01" name="impact" value="${actionplanData.impact+0.0}" placeholder="ตัวเลขประเมิน Impact...">
                </div>

                <div class="form-row">
                    <span class="label">% สะสม:</span>
                    <input type="number" step="0.01" name="cumulativePercent" value="${actionplanData.cumulativePercent+0.0}" placeholder="เปอร์เซ็นต์สะสม...">
                </div>

                <div class="form-row" style="align-items: flex-start;">
                    <span class="label">แผนดำเนินการ:</span>
                    <textarea name="actionPlanDetails" style="flex-grow: 1; border: 1px solid var(--input-border); padding: 5px; font-family: inherit; resize: none;" rows="4" placeholder="อธิบายแผนการทำงาน...">${actionplanData.actionPlanDetails}</textarea>
                </div>

                <div class="form-row">
                    <span class="label">ผู้รับผิดชอบ:</span>
                    <input type="text" name="owner" value="${actionplanData.owner}" placeholder="ชื่อผู้รับผิดชอบงาน...">
                </div>
            </div>

            <div id="content-result" style="display: none;">
                <div class="section-header">
                    <span>ผลการดำเนินงาน (Execution Result):</span>
                    <div class="line"></div>
                </div>

                <div class="form-row">
                    <span class="label">สถานะ:</span>
                    <select name="status">
                        <option value="Not Started" ${actionplanData.status == 'Not Started' ? 'selected' : ''}>รอดำเนินการ (Not Started)</option>
                        <option value="In Progress" ${actionplanData.status == 'In Progress' ? 'selected' : ''}>กำลังดำเนินการ (In Progress)</option>
                        <option value="Completed" ${actionplanData.status == 'Completed' ? 'selected' : ''}>เสร็จสิ้น (Completed)</option>
                        <option value="Cancelled" ${actionplanData.status == 'Cancelled' ? 'selected' : ''}>ยกเลิก (Cancelled)</option>
                        <option value="On Hold" ${actionplanData.status == 'On Hold' ? 'selected' : ''}>ระงับชั่วคราว (On Hold)</option>
                    </select>
                </div>

                <div class="form-row">
                    <span class="label">ความคืบหน้า (%):</span>
                    <input type="number" step="1" min="0" max="100" name="progress" value="${actionplanData.progress != null ? actionplanData.progress : 0}" placeholder="0 - 100">
                </div>

                <div class="form-row">
                    <span class="label">กำหนดเสร็จ:</span>
                    <input type="date" name="targetDate" value="${actionplanData.targetDate}">
                </div>

                <div class="form-row">
                    <span class="label">วันที่เสร็จจริง:</span>
                    <input type="date" name="actualDate" value="${actionplanData.actualDate}">
                </div>

                <div class="form-row" style="align-items: flex-start;">
                    <span class="label">สรุปผลลัพธ์:</span>
                    <textarea name="resultRemarks" style="flex-grow: 1; border: 1px solid var(--input-border); padding: 5px; font-family: inherit; resize: none;" rows="4" placeholder="บันทึกผลการดำเนินงาน หรือ หมายเหตุเพิ่มเติม...">${actionplanData.resultRemarks}</textarea>
                </div>
            </div>

        </form>
    </div>

    <div class="footers">
        <button class="primary" onclick="postData('/saveSpikeDogActionplan-filemanager?${_csrf.parameterName}=${_csrf.token}', 'actionPlanForm','actionplan-container');closeed2('draggable-window6')">Save Action</button>
    
      <c:if test="${not empty actionplanData.id}">
          <button class="danger" onclick="getData('/deleteSpikeDogActionplan-filemanager/${actionplanData.id}', 'actionplan-container');closeed2('draggable-window6')">Delete..</button>
   
      </c:if>
   
        <button onclick="closeed2('draggable-window6')">Cancel</button>
    </div>

</div>

<script>
    function switchActionTab(tabName) {
        // 1. ล้างสถานะ Active ของแท็บทั้งหมด
        document.getElementById('tab-plan').classList.remove('active');
        document.getElementById('tab-result').classList.remove('active');
        
        // 2. ปิดหน้าจอเนื้อหาทั้งหมด
        document.getElementById('content-plan').style.display = 'none';
        document.getElementById('content-result').style.display = 'none';
        
        // 3. เปิดสถานะ Active และแสดงเนื้อหาตามที่คลิก
        document.getElementById('tab-' + tabName).classList.add('active');
        document.getElementById('content-' + tabName).style.display = 'block';
    }
</script>

</body>
</html>