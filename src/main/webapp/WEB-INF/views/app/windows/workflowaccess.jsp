<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>

    

        /* --- Main Window --- */
        #tm-v2-wrapper .tm-windows {
            width: 100%;
            height: 500px;
            background-color: #ffffff;
            border: 1px solid #999;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            display: flex;
            flex-direction: column;
        }

        /* --- 1. Tab Bar (Box Style) --- */
        #tm-v2-wrapper .tm-tab-bar {
            background-color: #f0f0f0; /* พื้นหลังสีเทาอ่อน */
            padding-top: 6px;
            padding-left: 6px;
            display: flex;
            border-bottom: 1px solid #d9d9d9;
        }

        #tm-v2-wrapper .tm-tab {
            padding: 5px 12px;
            cursor: pointer;
            font-size: 12px;
            color: #333;
            background-color: #f0f0f0;
            border: 1px solid transparent;
            margin-right: -1px; /* ให้ขอบซ้อนกัน */
            margin-bottom: -1px; /* ให้ทับเส้นขอบล่าง */
            position: relative;
            border-top-left-radius: 2px;
            border-top-right-radius: 2px;
        }

        #tm-v2-wrapper .tm-tab:hover {
            background-color: #e5f1fb;
            border-color: #cdcdcd;
        }

        /* Active Tab Style */
        #tm-v2-wrapper .tm-tab.active {
            background-color: #ffffff;
            border-color: #d9d9d9;
            border-bottom-color: #ffffff; /* ลบเส้นล่างออกเพื่อให้เชื่อมกับเนื้อหา */
            color: #000;
            z-index: 10;
        }

        /* --- 2. Content Area --- */
        #tm-v2-wrapper .tm-content {
            flex: 1;
            display: flex;
            flex-direction: column;
            background-color: #ffffff;
        }

        /* --- 3. Grid Header (Stacked Percentages) --- */
        #tm-v2-wrapper .tm-header-row {
            display: flex;
            border-bottom: 1px solid #d9d9d9;
            height: 45px; /* ความสูงพอดีสำหรับ 2 บรรทัด */
        }

        #tm-v2-wrapper .tm-h-col {
            border-right: 1px solid #e0e0e0;
            padding: 4px 8px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            cursor: pointer;
        }
        #tm-v2-wrapper .tm-h-col:hover { background-color: #eef4fc; }

        /* Column Widths */
        #tm-v2-wrapper .col-name { flex: 2; min-width: 250px; justify-content: flex-end; padding-bottom: 8px; }
        #tm-v2-wrapper .col-status { width: 95px; border-left: 1px solid #e0e0e0; justify-content: flex-end; padding-bottom: 8px; }
        #tm-v2-wrapper .col-metric { width: 95px; }

        /* Typography inside Header */
        #tm-v2-wrapper .h-percent {
            font-size: 15px; color: #000; margin-bottom: 0px; line-height: 1.2;
        }
        #tm-v2-wrapper .h-label {
            font-size: 11px; color: #555; display: flex; align-items: center;
        }
        #tm-v2-wrapper .h-label-single {
            font-size: 11px; color: #333;
        }

        /* Active Sort Header Styling */
        #tm-v2-wrapper .tm-h-col.sorted-mem {
            background-color: #fff; /* Memory ในรูปพื้นหลังขาว แต่ตัวเลขเข้ม */
        }
        #tm-v2-wrapper .tm-h-col.sorted-mem .h-label::after {
            content: '▼'; font-size: 8px; margin-left: 5px; color: #666;
        }

        /* Active CPU Header */
        #tm-v2-wrapper .tm-h-col.active-cpu {
            background-color: #fff; 
        }

        /* --- 4. Data Rows --- */
        #tm-v2-wrapper .tm-data-container {
            flex: 1; overflow-y: auto;
        }
        #tm-v2-wrapper .tm-row {
            display: flex; height: 32px; align-items: center;
            border-bottom: 1px solid #f0f0f0;
        }
        #tm-v2-wrapper .tm-row:hover { background-color: #dcebf9; }

        #tm-v2-wrapper .tm-d-cell {
            padding: 0 8px; font-size: 12px; color: #000;
            display: flex; align-items: center;
            height: 100%;
            border-right: 1px solid transparent;
        }

        #tm-v2-wrapper .d-name { flex: 2; min-width: 250px; }
        #tm-v2-wrapper .d-status { width: 80px; }
        #tm-v2-wrapper .d-metric { width: 95px; justify-content: flex-end; color: #555; }

        /* Highlights for sorted columns */
        #tm-v2-wrapper .bg-tint-yellow { background-color: #fffbef; } /* สีเหลืองอ่อนๆ เวลาเรียง */
        #tm-v2-wrapper .bg-tint-orange { background-color: #ffefdf; } /* สีส้มอ่อนๆ ถ้าใช้เยอะ */

        /* Icons */
        #tm-v2-wrapper .app-icon {
            width: 16px; height: 16px; margin-right: 8px;
            display: inline-block;
        }

    </style>
</head>
<body>

    <div id="tm-v2-wrapper">
        <div class="tm-windows">
            
            <div class="tm-tab-bar">
           
                       <c:forEach var="row" items="${listsub_processes}" varStatus="count">
 
        
         <div class="tm-tab  setting-tab <c:if test="${count.count == 1 }"> active</c:if>" id="setting-${count.count }"  onclick="switchtabsetting(${count.count });show('new');">${row.step }</div>
     </c:forEach>
                
                
            </div>

            <div class="tm-content">
                
                <div class="tm-header-row">
                    <div class="tm-h-col col-name">
                        <span class="h-label-single">Acccess </span>
                    </div>
                    
                    <div class="tm-h-col col-status">
                         <span class="h-label-single">Type</span>
                    </div>

                  
                </div>
                
<c:forEach var="row"  items="${listsub_processes}" varStatus="count">
                <div class=" window-content <c:if test="${count.count > 1 }">hidden</c:if>" id="window-content-${count.count }" title="${row.step }">
 
           <c:if test='${!row.policies.respon.equals("0") && !row.policies.respon.equals("ALL") }'>     
            <c:forEach var="rows"  items="${row.user_all}" varStatus="count">
            
               <div class="tm-row " >
                         <div class="tm-d-cell d-name">
                       ${rows.user_name }
                        </div>
                        <div class="tm-d-cell d-status">USER</div>
                        </div>
              
           </c:forEach>
                <c:forEach var="rows"  items="${row.position_all}" varStatus="count">
                    <div class="tm-row " >
                      <div class="tm-d-cell d-name">
                     ${rows.group_name }
                        </div>
                        <div class="tm-d-cell d-status">Position</div>
                </div>
           </c:forEach>
               <c:forEach var="rows"  items="${row.department_all}" varStatus="count">
              
                  <div class="tm-row " >
                       <div class="tm-d-cell d-name">
                    ${rows.department_name }
                        </div>
                        <div class="tm-d-cell d-status">Department</div>
              </div>
           </c:forEach>
           
         
           
           
        </c:if>    
                   <c:if test='${row.policies.respon.equals("ALL") }'>
                       <div class="tm-row " >
                     
                      <div class="tm-d-cell d-name">
                  ALL
                        </div>
                        <div class="tm-d-cell d-status">USER</div> 
            </div>
            </c:if>   
                        
                        
                    </div>



 

                 
             
</c:forEach>
   </div>
            </div>
        </div>
  
</body>
</html>
 
 
 
 