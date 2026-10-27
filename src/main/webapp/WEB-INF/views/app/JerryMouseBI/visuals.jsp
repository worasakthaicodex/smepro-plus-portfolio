<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>	 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

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
        .quickwindow {
            width: 100%;
            background-color: var(--bg-color);

        
            position: relative;
        }

       .quickwindow  h1.title {
            font-size: 12px;
            margin: 0 0 10px 0;
            font-weight: normal;
            
        }

        /* Tabs */
        .quickwindow .tabs {
            display: flex;
            margin-bottom: -1px; /* Overlap border */
            padding-left: 2px;
        }

        .quickwindow .tab {
            padding: 4px 8px;
            background-color: var(--bg-color);
            border: 1px solid var(--bg-color);
            cursor: pointer;
            margin-right: 2px;
            position: relative;
            top: 1px;
        }

        .quickwindow .tab.active {
            background-color: #fff;
            border: 1px solid #999;
            border-bottom: 1px solid #fff; /* Hide bottom border */
            z-index: 2;
        }

        .quickwindow .tab:hover:not(.active) {
            background-color: #e5e5e5;
        }

        /* Content Panel */
        .quickwindow .panel {
            background-color: #fff;
            border: 1px solid #999;
            padding: 15px;
            height: 380px;
            position: relative;
            z-index: 1;
        }

        /* Section Header with Line */
      .quickwindow .section-header {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            white-space: nowrap;
        }

       .quickwindow  .section-header span {
            margin-right: 5px;
        }

        .quickwindow .section-header .line {
            width: 100%;
            height: 1px;
            background-color: #a0a0a0;
            margin-top: 4px;
        }

        /* Form Elements */
       .quickwindow  .radio-group {
            margin-left: 10px;
            margin-bottom: 15px;
            display: flex;
            flex-direction: column;
            gap: 6px;
        }

       .quickwindow  .radio-row {
            display: flex;
            align-items: center;
        }

       .quickwindow  input[type="radio"] {
            margin: 0 6px 0 0;
        }

        .quickwindow .form-row {
            display: flex;
            align-items: center;
            margin-bottom: 8px;
        }

       .quickwindow  .label {
            width: 120px;
            flex-shrink: 0;
        }

      .quickwindow   input[type="text"],select {
            flex-grow: 1;
          
        
        }

        /* Custom Dropdown Simulation */
        .quickwindow .dropdown-container {
            flex-grow: 1;
            position: relative;
        }

       .quickwindow  .fake-select {
            border: 1px solid #3399ff; /* Blue border like active */
            padding: 3px;
            background: #fff;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
       .quickwindow  .fake-select::after {
            content: "▼";
            font-size: 8px;
            margin-left: 5px;
        }

        /* The Open List */
     .quickwindow    .dropdown-list {
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
            max-height: 200px;
            overflow-y: auto;
        }

      .quickwindow   .dropdown-list li {
            padding: 2px 5px;
            cursor: pointer;
        }

       .quickwindow  .dropdown-list li:hover {
            background-color: #3399ff;
            color: white;
        }

     .quickwindow    .dropdown-list li.selected {
            background-color: #3399ff;
            color: white;
            border: 1px dotted #fff;
        }

        /* Footer Buttons */
      .quickwindow   .footer {
            display: flex;
            justify-content: flex-end;
            margin-top: 15px;
            gap: 10px;
        }

      .quickwindow   button {
            min-width: 75px;
            padding: 4px 10px;
            background-color: #e1e1e1;
            border: 1px solid #adadad;
            font-size: 12px;
            cursor: pointer;
        }
        
       .quickwindow  button:hover {
            border-color: #0078d7;
            background-color: #e5f1fb;
        }

     .quickwindow   button.primary {
            border: 1px solid #0078d7;
            box-shadow: inset 0 0 0 1px #8dcce8;
        }
       #setquick { 
        padding: 8px;
		}
    </style>
</head>
<body>

<div class="quickwindow">

                  <form id="setquick" name="setquick">   


<div class="tabs">
            <div class="tab active" id="tabChains-1" onclick="switchtabActiveAndData('Chains','2','1')">
               Setting
            </div>
        
            <div class="tab " id="tabChains-2" onclick="switchtabActiveAndData('Chains','2','2')">
                Trend visuals
            </div>
        </div>
        
        <div id="quickFilterContainer">
        <div id="showChains-1" class="panel" style="">
        <div class="form-row">
    <span class="label">โหมดประมวลผล BI :</span>
    <select class="form-control" id="bi-mode-selector" >
        <option value="off">โหมดปกติ (รองรับการคลิกดู Drill-down)</option>
        <option value="on">โหมด BI (วิเคราะห์ขั้นสูง / โยนเข้าตารางจัดการ)</option>
    </select>
</div>
          </div>
    
        
        <div id="showChains-2" class="panel" style="display:none;">
     <div class="form-row">
		    <span class="label">Date format:</span>
		    <select class="form-control date-format-selector" id="date-format-selector">
		    
		     <option value="-" selected>-</option>
		        <option value="yyyy-mm-dd">ปี-เดือน-วัน (เช่น 2026-04-15)</option>
		        <option value="timestamp_ms">Timestamp มิลลิวินาที (เช่น 1769014800000)</option>
		        <option value="timestamp_sec">Timestamp วินาที (เช่น 1769014800)</option>
		        <option value="dd-mm-yyyy">วัน-เดือน-ปี (เช่น 15-04-2026)</option>
		        <option value="mm-yyyy">เดือน-ปี (เช่น 04-2026)</option>
		        <option value="yyyy">ปี (เช่น 2026)</option>
		    </select>
		</div>
           
		           <div class="form-row">
		    <span class="label">รูปแบบตาราง (Table View):</span>
		    <select class="form-control" id="table-view-selector" >
		        <option value="normal">ตารางแนวตั้ง (Normal)</option>
		        <option value="pivot">ตารางไขว้ (Pivot - แยกคอลัมน์ตามวันที่)</option>
		    </select>
		</div>
            
          <div class="form-row">
    <span class="label">สูตรคำนวณ (Aggregation):</span>
    <select class="form-control" id="pivot-agg-selector" >
        <option value="SUM">ผลรวม (SUM)</option>
        <option value="AVG">ค่าเฉลี่ย (AVG)</option>
        <option value="MAX">ค่าสูงสุด (MAX)</option>
        <option value="MIN">ค่าต่ำสุด (MIN)</option>
        <option value="MEDIAN">มัธยฐาน (Median)</option>
        <option value="MODE">ฐานนิยม (Mode)</option>
    </select>
</div>
            </div>
            </div>
</form>
    <div class="footer">
        <button type="button" class="primary" onclick="updateGlobalDateFormat();closeed2('draggable-window3')">Apply</button>
    </div>
    


</div>
</body>
</html>


<script>
loadGlobalSettings();
</script>