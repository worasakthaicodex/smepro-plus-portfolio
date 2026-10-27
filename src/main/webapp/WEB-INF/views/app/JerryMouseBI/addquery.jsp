<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tenant Setup - Filter Criteria</title>
    <style>
        :root {
            --bg-color: #ffffff;
            --text-color: #333;
            --header-blue: #00265a;
            --selection-blue: #0078d7;
            --border-color: #adadad;
            --btn-bg: #e1e1e1;
            --btn-border: #adadad;
            --input-border: #ccc;
            --font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }


        .add_querywindow-container {
            width:100%; /* ปรับขนาดให้กระชับแนวตั้ง */
            background-color: white;
            border: 1px solid #999;
            box-shadow: 0 4px 15px rgba(0,0,0,0.15);
            padding: 5px;
            position: relative;
            box-sizing: border-box;
            font-size: 13px;
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

         .add_querywindow-container  h1 {
            color: var(--header-blue);
            font-size: 22px;
            margin: 0 0 10px 0;
            font-weight: 600;
        }

        /* --- New Input Section Styles --- */
        .add_querywindow-container   .input-section {
            background-color: #f7f7f7;
            border: 1px solid #e0e0e0;
            padding: 15px;
            border-radius: 2px;

        }

         .add_querywindow-container  .section-title {
            font-weight: bold;
            color: #444;
            margin-bottom: 10px;
            font-size: 12px;
            text-transform: uppercase;
            border-bottom: 1px solid #ddd;
            padding-bottom: 5px;
        }

         .add_querywindow-container  .form-grid {
            display: grid;
            grid-template-columns: 1fr 1fr; /* แบ่งเป็น 2 คอลัมน์ */
            gap: 5px;
        }

        .add_querywindow-container   .form-group {
            display: flex;
            flex-direction: column;
            gap: 4px;
        }

        .form-group label {
            font-weight: 600;
            font-size: 12px;
            color: #333;
        }

         .add_querywindow-container  .form-control {
            font-family: var(--font-family);
            font-size: 13px;
            padding: 4px 8px;
            border: 1px solid var(--input-border);
            background-color: #fff;
            height: 28px;
            box-sizing: border-box;
            width: 100%;
        }

         .add_querywindow-container  .form-control:focus {
            border-color: var(--selection-blue);
            outline: none;
        }

         .add_querywindow-container  select.form-control {
            cursor: pointer;
        }

        /* --- Toolbar --- */
         .add_querywindow-container  .toolbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 5px;
        }
  

         .add_querywindow-container  button {
            font-family: var(--font-family);
            background-color: var(--btn-bg);
            border: 1px solid var(--btn-border);
            padding: 4px 12px;
            font-size: 12px;
            cursor: pointer;
            min-width: 80px;
        }
        
        
         .add_querywindow-container  button:hover { background-color: #d1d1d1; }
        .btn-primary { border: 2px solid #0078d7; font-weight: 500; }



        /* --- Table/Grid Styles --- */
        .add_querywindow-container   .grid-wrapper {
            border: 1px solid var(--border-color);
            height: 200px; /* เพิ่มความสูงตาราง */
            overflow: auto;
            background-color: #fff;
            position: relative;
        }

    #filterTable   { width: 100%; border-collapse: collapse; table-layout: fixed; }

         .add_querywindow-container  th {
            background-color: #f0f0f0;
            border-right: 1px solid #d0d0d0;
            border-bottom: 1px solid #d0d0d0;
            padding: 5px 8px;
            text-align: left;
            font-weight: normal;
            font-size: 12px;
            color: #333;
            height: 26px;
            position: sticky;
            top: 0;
            z-index: 10;
        }

         .add_querywindow-container  td {
            border-right: 1px solid #e0e0e0;
            border-bottom: 1px solid #e0e0e0;
            padding: 0 5px;
            height: 28px;
            vertical-align: middle;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        /* Column Widths */
  
        
        .add_querywindow-container   tr.selected { background-color: var(--selection-blue); color: white; }
       .add_querywindow-container    tr.selected td { border-right-color: transparent; }

        /* Footer */
       .add_querywindow-container    .footer-actions {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
            margin-top: 10px;
            border-top: 1px solid #eee;
            padding-top: 15px;
        }
        
        /* --- แก้ไข: เพิ่มความสูงตรงนี้ครับ --- */
         .add_querywindow-container  select[multiple].form-control {
            height: 80px; /* ปรับจาก 80px เป็น 120px */
            padding: 0;
        }
         .add_querywindow-container  select[multiple].form-control option {
            padding: 3px 8px;
        }
    /* Tabs */
       .subsettingwindow  .tabs {
            display: flex;
            margin-bottom: -1px; /* Overlap border */
            padding-left: 2px;
        }

       .subsettingwindow  .tab {
            padding: 4px 8px;
            background-color: var(--bg-color);
            border: 1px solid var(--bg-color);
            cursor: pointer;
            margin-right: 2px;
            position: relative;
            top: 1px;
        }

        .subsettingwindow .tab.active {
            background-color: #fff;
            border: 1px solid #999;
            border-bottom: 1px solid #fff; /* Hide bottom border */
            z-index: 2;
        }

    </style>
</head>
<body >
<div class="subsettingwindow">
   <div class="tabs">
<div class="tab active" onclick="switchSubSettingTab('panel1', this)">Query</div>
       <div class="tab" onclick="switchSubSettingTab('panel2', this)">Condition & Join</div>
    </div>
  <div class="add_querywindow-container" style=" cursor: pointer;">
    <div class="panel" id="panel1">
  
     
     <div class="input-section">

    
    <div class="form-grid">
        <div class="form-group">
            <label>เงื่อนไข (Condition)</label>
            <select class="form-control" id="conditionSelect" onchange="checkCondition()">
                <option>Start</option>
                <option>And</option>
                <option>Or</option>
                <option>End</option>
            </select>
        </div>

        <div class="form-group">
            <label>คอลัมน์ (Column)</label>
            <select class="form-control" id="columnSelect" onchange="updateValueInput()">
                <option value="0" data-custom="text" data-tag="">- Select -</option>
                <c:forEach items="${Fullhead }" var="row" >
                    <option value="${row.mainkey }" data-tag="<spring:message code="${row.title}" text="-" />" data-custom="${row.type}" data-value="${row.action_type}">
                        <spring:message code="${row.title}" text="-" />
                    </option>
                </c:forEach>
                <c:forEach items="${eformhead }" var="row" >
                    <option value="data_value${row.field_postion }" data-tag="${row.field_label}" data-custom="${row.field_type}" data-value="${row.display}">
                        ${row.field_label}
                    </option>
                </c:forEach>
                <c:forEach items="${eformhead2 }" var="row" >
                    <option value="${row.field_label}" data-tag="${row.field_label}" data-custom="${row.field_type}" data-value="${row.display}">
                        ${row.field_label}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>กลุ่ม (Group)</label>
            <select class="form-control" id="groupInput" >
                <option value="1">Group 1</option>
                <option value="2">Group 2</option>
                <option value="3">Group 3</option>
                <option value="4">Group 4</option>
            </select>
        </div>

        <div class="form-group">
            <label>การดำเนินการ (Operation)</label>
            <select class="form-control" id="operationSelect">
                <option value="=">Equals (=)</option>
                <option value="!=">Not Equal (<>)</option> 
                <option value=">">Greater Than (>)</option>
                <option value="<">Less Than (<)</option>
                <option value="=>">Greater Than or Equal (>=)</option>
                <option value="<=">Less Than or Equal (<=)</option>
                <option value="(D)=>">(Date)Greater Than or Equal (>=)</option>
                <option value="(D)<=">(Date)Less Than or Equal (<=)</option>
                <option value="(M)=>">(Month)Greater Than or Equal (>=)</option>
                <option value="(M)<=">(Month)Less Than or Equal (<=)</option>
                <option value="link%">Starts With</option>     
                <option value="%link">Ends With</option>       
                <option value="%link%">Contains</option>       
                <option value="find_in_set">In List</option>
                <option value="not_find_in_set">Not In List</option>
                <option value="Is null">Is Null</option>
                <option value="Is not null">Is Not Null</option>
                <option value="Is empty">Is Empty</option>
                <option value="Is not empty">Is Not Empty</option>
                <option value="(C)>=">Count Than or Equal (>=)</option>
                <option value="(C)<=">Count Than or Equal (<=)</option>
                <option value="3_months_passed">3 months have passed.</option>
                <option value="6_months_passed">6 months have passed.</option>
                <option value="9_months_passed">9 months have passed.</option>
                <option value="12_months_passed">12 months have passed.</option>
                        <option value="24_months_passed">24 months have passed.</option>
                
            </select>
        </div>

        <div class="form-group" id="valueWrapper">
            <label>ค่าที่ใช้ (Value)</label>
            <input type="text" class="form-control" id="valueInput" placeholder="ระบุค่า..." value="Company2">
        </div>
             <div class="form-group" id="valueWrapper">
            <label>ProfileId</label>
            <select class="form-control" id="profileIdInput" >
                 <c:forEach items="${eformhead }" var="row" >
                    <option value="data_value${row.field_postion }" data-tag="${row.field_label}" data-custom="${row.field_type}" data-value="${row.display}">
                        ${row.field_label}
                    </option>
                </c:forEach>
                </select>
        </div>
        
        
    </div>


    <div style="margin-top: 10px; display: flex; justify-content: flex-end; gap: 8px;">
        <button onclick="clearInputs()">Clear</button>
        <button class="btn-primary" onclick="addToTable()">Add Filter</button>
    </div>
</div>

    <div class="toolbar">
        <label>Result List:</label>
        <div style="font-size: 11px; color: #666;" id="recordCount">0 records added</div>
    </div>

    <div class="grid-wrapper">
      <table id="filterTable">
    <thead>
        <tr>
            <th style="width: 100px;">Condition</th>
            <th style="width:100px;">Column (Tag)</th> 
            <th style="width: 100px;">Operation</th>
            <th style="width: 100px;">Value</th>

            
            <th style="width: 40px; text-align:center;">Del</th>
        </tr>
    </thead>
    <tbody>
        </tbody>
</table>
    </div>
          </div>
    
      <div class="panel" id="panel2" style="display:none">
       <div id="summarySectionWrapper" style="display: none;">
    
    <div style="margin-top: 20px; padding-bottom: 5px; border-bottom: 2px solid #28a745;">
        <span style="font-weight: bold; color: #28a745;" id="summaryTitleText">📊 การตั้งค่าโหมดสรุปผล (Summary / Trend)</span>
    </div>

    <div class="form-grid" style="background: #eefaf0; padding: 15px; border-radius: 8px 8px 0 0; margin-top: 15px;">
        <div class="form-group">
            <label>ชื่อตัวเลข (เช่น ยอดขาย)</label>
            <input type="text" class="form-control" id="summaryNameInput" placeholder="ตั้งชื่อผลลัพธ์...">
        </div>
        <div class="form-group">
            <label>ฟังก์ชัน</label>
            <select class="form-control" id="summaryFuncSelect">
                <option value="">- เลือก -</option>
                <option value="COUNT">นับจำนวน (COUNT)</option>
                <option value="D_COUNT">นับจำนวนไม่ซ้ำ (D_COUNT)</option>
                <option value="SUM">ผลรวม (SUM)</option>
                <option value="AVG">ค่าเฉลี่ย (AVG)</option>
                <option value="MAX">ค่ามากสุด (MAX)</option>
                <option value="MIN">ค่าน้อยสุด (MIN)</option>
            </select>
        </div>
        <div class="form-group">
            <label>ช่องที่จะคำนวณ</label>
            <select class="form-control" id="summaryColumnSelect">
                <option value="">- Select -</option>
                <c:forEach items="${Fullhead }" var="row" ><option value="${row.mainkey }"><spring:message code="${row.title}" text="-" /></option></c:forEach>
                <c:forEach items="${eformhead }" var="row" ><option value="data_value${row.field_postion }">${row.field_label}</option></c:forEach>
                <c:forEach items="${eformhead2 }" var="row" ><option value="${row.field_label}">${row.field_label}</option></c:forEach>
            </select>
        </div>
        <div class="form-group">
                <label style="color: #d97706;">แบ่งกลุ่มย่อย (แยกเส้นกราฟ)</label>
                <select class="form-control" id="trendGroupByColumn" style="border-color: #fcd34d;">
                    <option value="">- ไม่แบ่งกลุ่ม (เส้นเดียว) -</option>
                    <c:forEach items="${Fullhead }" var="row" ><option value="${row.mainkey }"><spring:message code="${row.title}" text="-" /></option></c:forEach>
                    <c:forEach items="${eformhead }" var="row" ><option value="data_value${row.field_postion }">${row.field_label}</option></c:forEach>
                    <c:forEach items="${eformhead2 }" var="row" ><option value="${row.field_label}">${row.field_label}</option></c:forEach>
                </select>
            </div>
    </div>

    <div id="trendSettingsWrapper" style="display: none; background: #eef4fa; padding: 15px; border-radius: 0 0 8px 8px; border-top: 1px dashed #ccc;">
  
        <div class="form-grid">
            <div class="form-group">
                <label>คอลัมน์วันที่ (Date Column)</label>
                <select class="form-control" id="trendDateColumn">
                    <option value="">- เลือกช่องวันที่ -</option>
                    <c:forEach items="${Fullhead }" var="row" ><option value="${row.mainkey }"><spring:message code="${row.title}" text="-" /></option></c:forEach>
                    <c:forEach items="${eformhead }" var="row" ><option value="data_value${row.field_postion }">${row.field_label}</option></c:forEach>
                    <c:forEach items="${eformhead2 }" var="row" ><option value="${row.field_label}">${row.field_label}</option></c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>จัดกลุ่มตาม (Format)</label>
                <select class="form-control" id="trendFormat">
                    <option value="DAY">รายวัน (Daily)</option>
                    <option value="MONTH">รายเดือน (Monthly)</option>
                    <option value="YEAR">รายปี (Yearly)</option>
                </select>
            </div>
            <div class="form-group"></div> </div>
    </div>
    
    
   

</div>
 <div id="joinSettingsWrapper"  style="display:=none;background: #fff3cd; padding: 15px; border-radius: 8px; margin-bottom: 15px; border: 1px solid #ffeeba;">
            <div style="font-weight: bold; color: #856404; margin-bottom: 10px;">🔗 การเชื่อมโยงข้อมูล (Join Tables)</div>
            <div class="form-grid">
                
                <div class="form-group">
                    <label>ประเภทการ Join</label>
                    <select class="form-control" id="joinTypeSelect">
                        <option value="">- ไม่มีการ Join -</option>
                        <option value="INNER JOIN">INNER JOIN (ต้องมีข้อมูลทั้ง 2 ฝั่ง)</option>
                        <option value="LEFT JOIN">LEFT JOIN (ยึดตารางหลักเป็นหลัก)</option>
                    </select>
                </div>

				               <div class="form-group">
				    <label>คีย์ตารางแรก (Master Key)</label>
				    <select class="form-control" id="joinMasterKeySelect">
				        <option value="" data-table="">- เลือกคีย์หลัก -</option>
				        
				        <optgroup label="📌 ข้อมูลหลัก (Master)">
				            <option value="id" data-table="MAIN_TBL">id</option>
				            <option value="data_id" data-table="MAIN_TBL">data_id</option>
				            <c:forEach items="${Fullhead}" var="row">
				                <option value="${row.mainkey}" data-table="MAIN_TBL"><spring:message code="${row.title}" text="${row.title}" /></option>
				            </c:forEach>
				        </optgroup>
				    </select>
				</div>
                <div class="form-group">
                    <label>คีย์ตารางปัจจุบัน (Child Key)</label>
                    <select class="form-control" id="joinChildKeySelect">
                        <option value="">- เลือกคอลัมน์ที่ตรงกัน -</option>
                        <c:forEach items="${Fullhead}" var="row"><option value="${row.mainkey}"><spring:message code="${row.title}" text="${row.title}" /></option></c:forEach>
                        <c:forEach items="${eformhead}" var="row"><option value="data_value${row.field_postion}">${row.field_label}</option></c:forEach>
                        <c:forEach items="${eformhead2}" var="row"><option value="${row.field_label}">${row.field_label}</option></c:forEach>
                    </select>
                </div>

            </div>
        </div>
   <div id="masterDisplayWrapper" style="background: #f0f7ff; padding: 15px; border-radius: 8px; margin-top: 15px; border: 1px solid #cce5ff;">
    <div style="font-weight: bold; color: #004085; margin-bottom: 10px;">👁️ คอลัมน์แสดงผล (ลากเพื่อเรียงลำดับ & พิมพ์แก้ชื่อได้)</div>
    
    <div class="input-group mb-3">
        <select class="form-control" id="columnSelectorToAdd">
            <option value="">-- เลือกคอลัมน์ที่ต้องการโชว์ --</option>
            <optgroup label="📌 ข้อมูลหลัก (Master)">
                <c:forEach items="${Fullhead}" var="row"><option value="${row.mainkey}"><spring:message code="${row.title}" text="-" /></option></c:forEach>
            </optgroup>
            <optgroup label="📋 ข้อมูลฟอร์ม">
                <c:forEach items="${eformhead}" var="row"><option value="data_value${row.field_postion}">${row.field_label}</option></c:forEach>
                <c:forEach items="${eformhead2}" var="row"><option value="${row.field_label}">${row.field_label}</option></c:forEach>
            </optgroup>
        </select>
        <div class="input-group-append">
            <button type="button" class="btn btn-primary" onclick="addDisplayColumnUI()">➕ เพิ่ม</button>
        </div>
    </div>

    <ul id="sortableColumnsList" style="list-style: none; padding: 10px; margin: 0; min-height: 80px; background: #fff; border: 1px dashed #007bff; border-radius: 4px;">
        </ul>
    <small class="text-muted mt-2 d-block">💡 ลากขึ้น-ลงเพื่อเรียงลำดับ, พิมพ์ในช่องเพื่อเปลี่ยนชื่อแสดงผลบนหัวตาราง, กด ❌ เพื่อลบ</small>
</div>
     </div>
    
    
    <div style="margin-top: 10px;display:none">
       
        <label style="font-size: 11px; font-weight: bold;">Debug JSON Output:</label>
        <textarea id="jsonOutput"  name="json_out_put"  style="width: 100%; height: 60px; font-size: 11px; color: #333; border: 1px solid #ccc;" readonly>${filtercriteria.json_out_put}</textarea>

       
        <input type="hidden" name="id" id="id"  value="${app_id_refer}">

    </div>

        <div class="footer-actions">
         
            <button  onclick="saveFilterCriteriaToCheese();closeed2('draggable-window6');closeed2('draggable-window6');">Save Configuration</button>
        </div>

    </div>
    </div>
     
     
     
     
  
</body>
</html>


<script>
applyReportModeToUI();
loadFilter();

</script>

<script>
// 🚀 ฟังก์ชันสลับ Tab ภายในหน้าต่าง Sub-setting
function switchSubSettingTab(panelId, tabElement) {
    // 1. ซ่อนทุก Panel
    document.getElementById('panel1').style.display = 'none';
    document.getElementById('panel2').style.display = 'none';

    // 2. เอาคลาส active ออกจาก Tab ทุกตัว
    const tabs = tabElement.parentElement.querySelectorAll('.tab');
    tabs.forEach(t => t.classList.remove('active'));

    // 3. โชว์ Panel ที่ถูกเลือก และเพิ่มคลาส active
    document.getElementById(panelId).style.display = 'block';
    tabElement.classList.add('active');
}
</script>