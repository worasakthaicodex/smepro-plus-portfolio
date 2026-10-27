<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Window Properties</title>
    <style>
        /* --- SCOPED CSS: เริ่มต้นด้วย #cal-perm-wrapper --- */
        #cal-perm-wrapper {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            font-size: 12px;
          
        }

        #cal-perm-wrapper * { box-sizing: border-box; }

        /* --- Window Container --- */
        #cal-perm-wrapper .cp-window {
            width: 100%;
            height: 550px;
            background-color: #ffffff;
            border: 1px solid #707070;
            box-shadow: 0 4px 20px rgba(0,0,0,0.15);
            display: flex;
            flex-direction: column;
        }

        /* --- Header --- */
        #cal-perm-wrapper .cp-header {
            height: 30px;
            padding: 0 10px;
            background-color: white;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        #cal-perm-wrapper .cp-close { font-size: 16px; color: #666; cursor: pointer; }
        #cal-perm-wrapper .cp-close:hover { color: #e81123; }

        /* --- Body --- */
        #cal-perm-wrapper .cp-body {
            display: flex;
            flex: 1;
            border-top: 1px solid #d9d9d9;
            overflow: hidden;
        }

        /* --- Sidebar --- */
        #cal-perm-wrapper .cp-sidebar {
            width: 180px;
            background-color: #f0f0f0;
            list-style: none;
            margin: 0;
            padding-left: 0;
            border-right: 1px solid #d9d9d9;
        }
        #cal-perm-wrapper .cp-item {
            padding: 4px 15px; cursor: pointer; margin-bottom: 2px;
        }
        #cal-perm-wrapper .cp-item:hover { background-color: #e0e0e0; }
        #cal-perm-wrapper .cp-item.active {
            background-color: #2a8dd4; color: white;
        }

        /* --- Content Area --- */
        #cal-perm-wrapper .cp-content {
            flex: 1;
            padding: 15px 20px;
            background-color: white;
            display: flex;
            flex-direction: column;
        }

        /* Checkbox Top */
        #cal-perm-wrapper .cp-chk-label {
            display: flex; align-items: center; font-weight: 600; margin-bottom: 10px; cursor: pointer;
        }
        #cal-perm-wrapper .cp-chk-label input { margin-right: 8px; }

        /* Table Area */
        #cal-perm-wrapper .cp-table-container {
            flex: 1;
            border: 1px solid #adadad;
            background-color: white;
            margin-bottom: 10px;
            display: flex;
            flex-direction: column;
            overflow: hidden;
        }

        #cal-perm-wrapper .cp-table-header {
            display: grid;
            grid-template-columns: 1fr 1fr 1fr 1fr; /* Name, Permission Level */
            background-color: #ffffff;
            border-bottom: 1px solid #e0e0e0;
            font-weight: 600; /* Bold header */
            font-size: 12px;
        }
        #cal-perm-wrapper .cp-th { padding: 5px 10px; border-right: 1px solid #e0e0e0; }
        #cal-perm-wrapper .cp-th:last-child { border-right: none; }

        #cal-perm-wrapper .cp-table-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            cursor: pointer;
        }
        #cal-perm-wrapper .cp-table-row:hover { background-color: #f0f0f0; }
        #cal-perm-wrapper .cp-table-row.cp-selected {
            background-color: #cce8ff; /* Light blue selection */
        }
        
        #cal-perm-wrapper .cp-td {
            padding: 5px 10px;
            display: flex; align-items: center;
        }
        
        /* Green Plus Icon Mockup */
        #cal-perm-wrapper .cp-icon-plus {
            display: inline-block; width: 10px; height: 10px;
            position: relative; margin-right: 8px;
        }
        #cal-perm-wrapper .cp-icon-plus::before,
        #cal-perm-wrapper .cp-icon-plus::after {
            content: ''; position: absolute; background-color: #28a745;
        }
        #cal-perm-wrapper .cp-icon-plus::before { width: 2px; height: 10px; left: 4px; top: 0; }
        #cal-perm-wrapper .cp-icon-plus::after { width: 10px; height: 2px; left: 0; top: 4px; }
        
        /* Person Icon Mockup */
        #cal-perm-wrapper .cp-icon-user {
            margin-right: 5px; color: #555;
        }

        /* Bottom Controls */
        #cal-perm-wrapper .cp-bottom-controls {
            margin-bottom: 5px;
        }
        #cal-perm-wrapper .cp-chk-bottom {
            margin-bottom: 10px; display: flex; align-items: center;
        }
        #cal-perm-wrapper .cp-chk-bottom input { margin-right: 5px; }

        #cal-perm-wrapper .cp-btn-row {
            display: flex; gap: 10px;
        }
        #cal-perm-wrapper .cp-btn {
            height: 24px;
            padding: 0 10px;
            border: 1px solid #adadad;
            background-color: #e1e1e1;
            font-size: 12px;
            font-family: inherit;
            cursor: pointer;
            display: flex; align-items: center; justify-content: center;
        }
        #cal-perm-wrapper .cp-btn:hover {
            background-color: #e5f1fb; border-color: #0078d7;
        }
        #cal-perm-wrapper .cp-btn-arrow::after {
            content: '▼'; font-size: 8px; margin-left: 5px;
        }

        /* --- Footer --- */
        #cal-perm-wrapper .cp-footer {
            height: 45px;
            background-color: #f0f0f0;
            border-top: 1px solid #d9d9d9;
            display: flex;
            align-items: center;
            justify-content: flex-end;
            padding-right: 15px;
            gap: 10px;
        }
        #cal-perm-wrapper .cp-btn-footer {
            width: 75px; height: 25px;
            border: 1px solid #adadad; background-color: #e1e1e1;
            font-size: 12px; cursor: pointer;
        }
        #cal-perm-wrapper .cp-btn-footer:hover {
            background-color: #e5f1fb; border-color: #0078d7;
        }




/* --- Content Area (Right) --- */
        #cal-perm-wrapper .content {
            flex: 1;
            padding: 15px 20px;
            background-color: white;
            overflow-y: auto;
        }

        /* --- Fieldset Grouping --- */
        fieldset {
            border: 1px solid #d9d9d9;
            margin: 0 0 20px 0;
            padding: 10px 15px 15px 15px;
            border-radius: 2px;
        }

        legend {
            font-size: 12px;
            margin-left: -5px;
            padding: 0 5px;
            color: #333;
        }

        .checkbox-label {
            display: flex;
            align-items: center;
            cursor: pointer;
        }

        .checkbox-label input {
            margin-right: 6px;
        }

        .description {
            margin-top: 5px;
            margin-bottom: 15px;
            color: #333;
            line-height: 1.4;
        }

        /* --- Buttons inside sections --- */
        .action-btn {
            float: right;
            padding: 4px 15px;
            min-width: 100px;
            border: 1px solid #adadad;
            background-color: #e1e1e1;
            font-family: inherit;
            font-size: 12px;
            cursor: pointer;
            transition: all 0.1s;
        }

        .action-btn:hover:not(:disabled) {
            background-color: #e5f1fb;
            border-color: #0078d7;
        }

        .action-btn:disabled {
            color: #8d8d8d; /* Gray text */
            background-color: #f0f0f0;
            border-color: #d9d9d9;
            cursor: default;
        }

        /* Clearfix for floated buttons */
        .clearfix::after {
            content: "";
            clear: both;
            display: table;
        }

    </style>
</head>
<body>

    <div id="cal-perm-wrapper">
        <div class="cp-window">
       

            <div class="cp-body">
                <ul class="cp-sidebar">
                    <li class="cp-item active" id="Tab-TabSet-trans-1" onclick="switchTabflex('TabSet-trans','5','1');buildDuplicateScanUI()">เป้าหมายที่จะเเสกนค่าซ้ำ</li>
                     <li class="cp-item" id="Tab-TabSet-trans-2"  onclick="switchTabflex('TabSet-trans','5','2');buildValueMappingUI()">การเข้ารหัสข้อมูล</li>
   <li class="cp-item" id="Tab-TabSet-trans-3"  onclick="switchTabflex('TabSet-trans','5','3');buildSmartFillUI()">การเเทนข้อมูล</li>
    <li class="cp-item" id="Tab-TabSet-trans-4"  onclick="switchTabflex('TabSet-trans','5','4');buildRowCleanerUI()">การลบเเถวว่างออก</li>
     <li class="cp-item" id="Tab-TabSet-trans-5"  onclick="switchTabflex('TabSet-trans','5','5');buildAdvancedTransformUI()">การจัดรูปเเบบ</li>
                </ul>

                <div class="cp-content" id="Form-TabSet-trans-1" >

</div>

     
                
                     <div class="cp-content" id="Form-TabSet-trans-2" style="display:none;overflow-y:auto" >
             
   
                </div>          
                
                     <div class="cp-content" id="Form-TabSet-trans-3" style="display:none;overflow-y:auto" >
             
   
                </div>      
     
                        <div class="cp-content" id="Form-TabSet-trans-4" style="display:none;overflow-y:auto" >
             
   
                </div>      
                
                              <div class="cp-content" id="Form-TabSet-trans-5" style="display:none;overflow-y:auto" >
             
   
                </div>    
                
                
                
                         
                
    	            
                
           
                
            </div>

            <div class="cp-footer">
                <button class="cp-btn-footer" onclick="refreshPreviewTable();closeed2('draggable-window3')">Apply</button>
        
            </div>
        </div>
    </div>

 <div id="savediv"></div>
</body>

  <script>
  buildDuplicateScanUI();

    </script>
</html>