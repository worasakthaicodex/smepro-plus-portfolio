<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Custom Label UI Mockup</title>
    <style>
        /* Reset & Base Styles to match Windows UI */
        :root {
            --bg-color: #f0f0f0;
            --border-color: #d9d9d9;
            --input-border: #7a7a7a;
            --input-bg: #ffffff;
            --text-color: #000000;
            --focus-blue: #0078d7;
            --btn-border: #adadad;
            --btn-bg: #e1e1e1;
            --btn-hover: #e5f1fb;
        }

    
        /* The Main Window */
        .settingprint_window {
            background-color: var(--bg-color);
          width: 100%;
            border: 1px solid #999;
            box-shadow: 0 4px 10px rgba(0,0,0,0.5);
            display: flex;
            flex-direction: column;
        }

        /* Title Bar */
        .settingprint_title-bar {
            background-color: #ffffff;
            padding: 6px 10px;
            font-size: 12px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid var(--bg-color); /* Subtle separator */
        }
        
        .settingprint_window-content {
            padding: 10px 14px;
              width: 100%;
        }

        /* Common Form Elements */
       .settingprint_window   input[type="text"], 
         .settingprint_window  input[type="number"],
         .settingprint_window  select {
            border: 1px solid var(--input-border);
            padding: 2px 4px;
            font-family: inherit;
            font-size: 12px;
            background-color: var(--input-bg);
            outline: none;
        }

        .settingprint_window  input:focus, select:focus {
            border-color: var(--focus-blue);
        }

        /* Top Name Row */
        .row-name {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .row-name label {
            width: 50px;
        }
        .row-name input {
            flex-grow: 1;
        }

        /* Main Grid Layout */
        .main-grid {
            display: grid;
            grid-template-columns: 260px 1fr;
            gap: 10px;
        }

        /* Fieldsets */
        fieldset {
            border: 1px solid #d0d0d0;
            margin: 0 0 10px 0;
            padding: 8px 10px;
            border-radius: 2px;
        }

        legend {
            color: #000;
            padding: 0 4px;
            margin-left: -4px;
        }

        /* Paper Section */
        .paper-select {
            width: 100%;
            margin-bottom: 8px;
        }

        .checkbox-row {
            margin-bottom: 8px;
            display: flex;
            align-items: center;
        }
        .checkbox-row input {
            margin: 0 6px 0 0;
        }

        /* Label Input Grid */
        .input-grid {
            display: grid;
            grid-template-columns: 1fr 90px;
            row-gap: 6px;
            align-items: center;
        }

        /* Numeric Inputs Specifics */
        .input-grid input[type="number"] {
            /* Hiding default arrows visually but keeping functionality - simplistic approach */
            width: 100%;
            box-sizing: border-box;
        }
        .input-grid input[type="text"] {
            width: 100%;
            box-sizing: border-box;
        }

        /* Spinner imitation for number inputs */
        input[type=number]::-webkit-inner-spin-button {
            opacity: 1; 
            height: 20px;
        }

        /* Right Column (Sample) */
        .sample-fieldset {
            height: 100%; /* Fill height */
                width: 100%;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
           
        }

        .sample-container {
            background-color: #999999;
            flex-grow: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            border: 1px solid #7a7a7a;
            overflow:hidden;
            max-width:274px;
        }

        /* The White Paper in Sample */
        .paper-sheet {
            background-color: white;
          	width:80%;
        
            box-shadow: 2px 2px 5px rgba(0,0,0,0.3);
            padding: 5px; /* Margins */
            box-sizing: border-box;
            
            /* Grid for Labels */
            display: grid;
            grid-template-columns: 1fr 1fr;
            grid-template-rows: 1fr 1fr;
            gap: 2px; /* Gap between labels */
        }

        .label-preview-item {
            border: 1px solid #ccc;
            border-radius: 8px; /* Rounded corners like in screenshot */
        }

        /* Button Row */
        .button-row {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
            margin-top: 4px;
            padding: 10px 14px 14px 14px;
        }

        .settingprint_btn {
            min-width: 75px;
            padding: 4px 10px;
            background-color: var(--btn-bg);
            border: 1px solid var(--btn-border);
            font-size: 12px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: var(--btn-hover);
            border-color: #0078d7;
        }

        .btn-primary {
            border: 1px solid #0078d7;
            box-shadow: inset 0 0 0 1px #0078d755;
        }
        
        .cp-table-container {
    flex: 1;
    border: 1px solid #adadad;
    background-color: white;
    margin-bottom: 10px;
    display: flex;
    flex-direction: column;
    overflow: hidden;
    width:100%;
    
}
 .cp-table-header {
    display: grid;
    grid-template-columns: 1fr 1fr;
    background-color: #ffffff;
    border-bottom: 1px solid #e0e0e0;
    font-weight: 600;
    font-size: 12px;
  padding: 5px 10px;
  
     position: sticky;
    top: 0;
    z-index: 10;
}
.cp-td {
    padding: 5px 10px;
    display: flex;
    align-items: center;
    user-select: text;
    -webkit-user-select: text;

}
.cp-table-row {
    display: grid;
    grid-template-columns: 1fr 1fr;
    cursor: pointer;
}
#my-preview-img{

}

    </style>
</head>
<body>

    <div class="settingprint_window">
     <form id="UseformPrint" name="UseformPrint">
        <input type="hidden" id="process_id" name="process_id" value="${process.id}">
        <input type="hidden" id="data_id" name="data_id" value="${data_id}">
        <c:if test="${UseformPrint.id > 0 }">
           <input type="hidden" id="id" value="${UseformPrint.id}">
         </c:if>   
        
     
             
        <div class="settingprint_window-content">
          
            <div class="main-grid">
               
                <div >
                    <fieldset class="sample-fieldset">
                        <legend>Sample</legend>
                        <div class="sample-container">
                     
                              <img id="my-preview-img"></img>
                         
                        </div>
                    </fieldset>
                </div>
               
                <div >
                
                
                <fieldset>
                    <legend>
                        <label class="checkbox-label">
                          เลือกฟอร์ม
                        </label>
                    </legend>
                        <select class="paper-select" name="form_id">
                              <c:forEach items="${ listForm }" var="row">
        							<option value="${row.id}" data-custom="${row.form_link}"
        							 <c:if test="${UseformPrint.form_id == row.id }">selected</c:if> >${row.form_name}</option>
								</c:forEach>
                        </select>		          
                            
                            
                            <div class="input-grid">
                 	        <label>ข้อมูลประกอบ:</label> 
							 <button type="button"  class="settingprint_btn" onclick="openwindow('draggable-window6','New Form',10000);
								 getData('/setfromprint-filemanager/${process.id}/${data_id}', 'mainbody6')">นำเข้าฟอร์ม</button>
                        </div>
                </fieldset>
                    <fieldset>
                        <legend>ข้อมูลฟอร์ม</legend>
            

                        <div class="input-grid">
                          <label>Paper width:</label> 
                          
                          
							    <input type="text" id="paper-width-disp" readonly>
							
							    <label>Paper height:</label> 
							    <input type="text" id="paper-height-disp" readonly>
							
							    <label>Landscape:</label> 
							    <input type="text" id="paper-orient-disp" readonly>
							
							    <label>Pages:</label> 
							    <input type="text" id="paper-total-disp" readonly>
							    
							       
                        </div>
                    </fieldset>

                    <fieldset>
                        <legend>Label parameters</legend>
                        <div >
                         <div class="cp-table-container" style="max-height:150px;overflow-y: scroll;">
                        <div class="cp-table-header">
                            <div class="cp-th">Load Data</div>
                            <div class="cp-th">Parameters</div>
                        </div>
     

                                      <div class="cp-table-row" >
                            <div class="cp-td">
                               
                                <span class="cp-icon-user"> </span>
                            Register
                            </div>
                            
                            <div class="cp-td">
                            <select name="register_id">
                            <option value="-">-</option>
                            <c:forEach items="${eformfields }" var="row">
                       		   <c:set var="register_id" value="data_value${row.field_postion }" />
                            	<option value="data_value${row.field_postion }" <c:if test="${UseformPrint.register_id ==register_id}">selected</c:if> >${row.field_label }</option>
                            </c:forEach>
                            </select>
                            
                            </div>
                            
                            
                              </div>
                              
                              
                            <div class="cp-table-row" >
                            <div class="cp-td">
                               
                                <span class="cp-icon-user"> </span>
                             Payment terms
                            </div>
                            <div class="cp-td">
                             <select name="payment_terms_column">
                          <option value="0" >-</option>
		              	  <option value="1" <c:if test="${UseformPrint.payment_terms_column == 1}">selected</c:if>>ชำระเงินก่อนเริ่มดำเนินกิจกรรม ไม่เกิน 7 วัน</option>
		                  <option value="2" <c:if test="${UseformPrint.payment_terms_column == 2}">selected</c:if>>เเบ่งจ่าย 3 งวด</option>
		                  <option value="3" <c:if test="${UseformPrint.payment_terms_column == 3}">selected</c:if>>เเบ่งจ่าย 2 งวด  (70/30)</option>
		                  <option value="4" <c:if test="${UseformPrint.payment_terms_column == 4}">selected</c:if>>เเบ่งจ่าย 2 งวด</option>
		                  <option value="5" <c:if test="${UseformPrint.payment_terms_column == 5}">selected</c:if>>เเบ่งจ่าย 4 งวด</option>
		                  <option value="6" <c:if test="${UseformPrint.payment_terms_column == 6}">selected</c:if>>เเบ่งจ่าย 4 งวด ( แบบไตรมาส ) </option>
		            
                            </select>
                            </div>
                              </div>
                      
                       <div class="cp-table-row" >
                            <div class="cp-td">
                               
                                <span class="cp-icon-user"> </span>
                             Promotion
                            </div>
                            <div class="cp-td">
                       
                              <select name="promotion_column">
                                    <option value="0" >-</option>
                            <c:forEach items="${eformfields }" var="row">
                             <c:set var="promotion_column" value="data_value${row.field_postion }" />
                            	<option value="data_value${row.field_postion }"  
                            	<c:if test="${UseformPrint.promotion_column == promotion_column}">selected</c:if>
                            	>${row.field_label }</option>
                            </c:forEach>
                            </select>
                            </div>
                              </div>
                                  
                
                    </div>
                        </div>
                    </fieldset>
                </div>

            </div>
        </div>
        </form>
        <div class="button-row">
         	<button class="settingprint_btn btn-primary" onclick="postData('/saveUseformPrint-filemanager?${_csrf.parameterName}=${_csrf.token}','UseformPrint','mainbody3')">Save</button>
            
         <c:if test="${UseformPrint.id > 0 }">
            <button class="settingprint_btn" onclick="openReportApp('${data_id}','${UseformPrint.id}');closeed2('draggable-window3')">Print...</button>
         </c:if>   
            
            <button class="settingprint_btn" onclick="closeed2('draggable-window3')">Cancel</button>
        </div>
        

    </div>
<div id="saveUseformPrint"></div>
</body>



<script>
loadword();
PreviewUpdate();
var selectElement;
function PreviewUpdate() {
	  selectElement = document.querySelector('.paper-select');
	  handlePreviewUpdate();
	  selectElement.addEventListener('change', handlePreviewUpdate);
}

    // ฟังก์ชันสำหรับดึงค่าและส่งไปที่ loadSamplePreviewURL
    function handlePreviewUpdate() {
        // ดึง Option ที่ถูกเลือกอยู่ในขณะนั้น
        const selectedOption = selectElement.options[selectElement.selectedIndex];
        const rawData = selectedOption.getAttribute('data-custom');

        if (rawData) {
            // เปลี่ยนจาก "Idoc:/" เป็น "/document-filemanager/"
            const formattedURL = rawData.replace("Idoc:/", "/document-filemanager/");
            
         
            loadSamplePreviewURL(formattedURL);
            
            // (Optional) Log ดูผลลัพธ์
            console.log("ส่งค่าไปที่ Preview:", formattedURL);
        }
    }
  

</script>
</html>


