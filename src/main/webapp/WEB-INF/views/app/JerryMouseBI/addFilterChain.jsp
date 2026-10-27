<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Attributes Replica</title>
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

  .subsettingwindow  input[type="text"] {
            flex-grow: 1;
            border: 1px solid var(--input-border);
            padding: 3px;
        }

			.subsettingwindow select {
			    border: 1px solid var(--input-border);
			   padding: 3px;
			    font-family: inherit;
			    background-color: var(--input-bg);
			    outline: none;
			    max-width:230px;
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
        }

        /* Section Header with Line */
       .subsettingwindow  .section-header {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            white-space: nowrap;
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

        /* Form Elements */
      .subsettingwindow   .radio-group {
            margin-left: 10px;
            margin-bottom: 15px;
            display: flex;
            flex-direction: column;
            gap: 6px;
        }

       .subsettingwindow  .radio-row {
            display: flex;
            align-items: center;
        }

        .subsettingwindow .subsettingwindow  input[type="radio"] {
            margin: 0 6px 0 0;
        }

       .subsettingwindow  .form-row {
            display: flex;
            align-items: center;
            margin-bottom: 8px;
        }

        .subsettingwindow .label {
            width: 120px;
            flex-shrink: 0;
        }

     
        /* Custom Dropdown Simulation */
       .subsettingwindow  .dropdown-container {
            flex-grow: 1;
            position: relative;
        }

       .subsettingwindow  .fake-select {
            border: 1px solid #3399ff; /* Blue border like active */
            padding: 3px;
            background: #fff;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .subsettingwindow .fake-select::after {
            content: "▼";
            font-size: 8px;
            margin-left: 5px;
        }

        /* The Open List */
       .subsettingwindow  .dropdown-list {
            position: absolute;
            top: 100%;
            left: 0;
            width: 100%;
            background: #fff;
            border: 1px solid #666;
            list-style: none;
            padding: 0;
            margin: 0;
            box-shadow: 2px 2px 5px rgba(0,0,0,0.2);
            z-index: 100;
            max-height: 170px;
              min-height: 170px;
            overflow-y: auto;
        }

        .subsettingwindow .dropdown-list li {
            padding: 2px 5px;
            cursor: pointer;
            font-size:11px;
        }

      .subsettingwindow   .dropdown-list li:hover {
            background-color: #3399ff;
            color: white;
        }

        .subsettingwindow .dropdown-list li.selected {
            background-color: #3399ff;
            color: white;
            border: 1px dotted #fff;
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
            padding: 4px 10px;
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
        }

       .subsettingwindow  .list-item {
            display: flex;              /* เปิดใช้ Flexbox */
            justify-content: space-between; /* ดันลูกๆ ไปชนขอบซ้าย-ขวา (Label ซ้าย, Badge ขวา) */
            align-items: center;        /* จัดกึ่งกลางแนวตั้ง (Vertical Center) */
            
            padding: 10px 15px;         /* ระยะห่างภายใน */
            border-bottom: 1px solid #eee; /* เส้นคั่นบางๆ */
            cursor: pointer;
            transition: background 0.2s;
        }

       .subsettingwindow  .list-item:hover {
            background-color: #f5f9ff; /* สีพื้นหลังตอนเอาเมาส์ชี้ */
        }

        /* ส่วนของ Label + Checkbox */
        .subsettingwindow .list-item label {
            display: flex;          /* ใช้ Flex เพื่อจัด checkbox กับ text ให้ตรงกัน */
            align-items: center;    /* กึ่งกลางแนวตั้ง */
            gap: 10px;              /* ระยะห่างระหว่าง checkbox กับตัวหนังสือ */
            cursor: pointer;
            width: 100%;            /* ให้พื้นที่คลิกเต็มส่วนที่เหลือ */
            font-size: 12px;
            color: #333;
        }

 

        /* ตกแต่ง Badge (ตัวเลขลำดับ) */
       .subsettingwindow  .order-badge {
            background-color: #007bff; /* สีน้ำเงินสด (เปลี่ยนได้) */
            color: white;
            font-size: 8px;
            font-weight: bold;
            
            /* จัดขนาดและรูปร่าง */
            min-width: 20px;       /* ความกว้างขั้นต่ำ วงกลมจะได้ไม่บี้ */
            height: 20px;
            border-radius: 12px;   /* ทำมุมมน (Pill shape) */
            
            /* จัดตัวเลขให้อยู่ตรงกลางวงกลม */
            display: none;         /* ซ่อนไว้ก่อน (JS จะมาเปิดเป็น flex) */
            align-items: center;
            justify-content: center;
            
            /* ระยะห่าง */
            margin-left: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.2); /* เงาเล็กน้อยให้ดูลอย */
        }
    </style>
</head>
<body onload="loadSavedColumns(); ">

<div class="subsettingwindow">

    <div class="tabs">

        <div class="tab active">Add Filter</div>
    </div>

    <div class="panel">
        <form id="formFilterChain" name="formFilterChain">
        
        

         <div class="form-row">
            <span class="label">Process or Extension:</span>
         
           <select name="tables" class="form-control" id="tableSelect">
    <option value="0">- กรุณาเลือกตาราง -</option>

    <optgroup label="📌 ข้อมูลหลัก (Register)">
         <c:forEach items="${useregister}" var="rows">
            <c:if test="${rows.name != null}">
                <option value="${rows.name}-${rows.register }-${rows.id}">${rows.name}</option>
            </c:if> 
        </c:forEach>
    </optgroup>

    <optgroup label="⚙️ กระบวนการ (Program)">
        <c:forEach items="${useprocess}" var="rows">
            <c:if test="${rows.name != null}">
                <option value="${rows.name}-program-${rows.id}"
                 data-profile_id="${rows.profile_column}" data-product_master_id="${rows.product_master_column}">${rows.name}</option>
            </c:if> 
        </c:forEach>
    </optgroup>

    <optgroup label="🧩 ส่วนขยาย (Extension)">
          
        <c:forEach items="${useextension}" var="rows">
            <c:if test="${rows.name != null}">
                <option value="${rows.name}-extension-${rows.eform_id}" data-extension_id="${rows.id}">${rows.name}</option>
            </c:if> 
        </c:forEach>
    </optgroup>
       <optgroup label="อื่นๆ">
   			<option value="Calendar-calendar-1E" >Calendar</option>
   			<option value="User-user-2E" >User</option>
   			<option value="Department-department-3E" >Department</option>
   			<option value="CustomerInsight-customerInsight-4E" >Customer Insight</option>
   			<option value="SalesPredictionData-salesPredictionData-5E" >Sales Prediction Data</option>
   				<option value="HelpDesk-helpDesk-6E" >help desk</option>
   					<option value="DocumentEffect-documentEffect-7E" >document effect</option>
    </optgroup>
    
    
</select>
        </div>
      
      
   <div class="form-row" id="report_mode_wrapper">
    <span class="label">Report Mode:</span>
    <select name="report_mode" class="form-control" id="report_mode">
        <option value="filter_list">คัดรายชื่อผู้ผ่านเกณฑ์ (Filter / List)</option>
        <option value="summary">สรุปข้อมูลเชิงสถิติ นับจำนวน/ผลรวม (Summary)</option>
        <option value="trend">วิเคราะห์แนวโน้มตามช่วงเวลา (Time-Series / Trend)</option>
        <option value="join">เชื่อมความสัมพันธ์ตาราง (Table Join)</option>

    </select>
</div>
        
    
            <input type="hidden" id="process_id" name="process_id" value="${item_id}">
           
      
</form>
      

    </div>

    <div class="footers" >
        <button class="primary" onclick="addNewFilterRow();closeed2('draggable-window6');">Save</button>
        <button onclick="closeed2('draggable-window6')">Cancel</button>
    </div>

</div>

</body>
</html>

<script>

toggleReportModeVisibility();
</script>

