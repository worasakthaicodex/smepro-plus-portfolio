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
                    <li class="cp-item active" id="Tab-TabSet${process.id}-1" onclick="switchTabflex('TabSet${process.id}','5','1')">เเบ่งหน้าข้อมูล</li>
                     <li class="cp-item" id="Tab-TabSet${process.id}-2"  onclick="switchTabflex('TabSet${process.id}','5','2')">ข้อมูลประกอบการทำงาน</li>
                     <li class="cp-item" id="Tab-TabSet${process.id}-3"  onclick="switchTabflex('TabSet${process.id}','5','3')">ซ่อน Input</li>
                      <li class="cp-item" id="Tab-TabSet${process.id}-4"  onclick="switchTabflex('TabSet${process.id}','5','4')">ตารางย่อย</li>
                     <li class="cp-item" id="Tab-TabSet${process.id}-5"  onclick="switchTabflex('TabSet${process.id}','5','5')">เเบ่งกลุ่มข้อมูล Input</li>
                </ul>

                <div class="cp-content" id="Form-TabSet${process.id}-1" >
                
                      <form id="useTabs" name="useTabs">   
                    <label class="cp-chk-label">
                     
                       	<input type="checkbox" name="tabform"  onchange="postDataNotReset('/usetab-filemanager?${_csrf.parameterName}=${_csrf.token}','useTabs','savediv');" 
                       	 value="1" <c:if test="${process.tabform == 1}">checked</c:if> > ใช้งานการเเบ่งข้อมูล
                    </label>
                    
                    
                        <input type="hidden" id="process_id" name="process_id" value="${process.id}">  
                      </form>



                    <div class="cp-table-container">
                        <div class="cp-table-header">
                            <div class="cp-th">Tab</div>
                            <div class="cp-th">Column</div>
                        </div>
     
                       <form id="delAddTabs" name="delAddTabs">   
                              <input type="hidden" id="process_id" name="process_id" value="${process.id}">  
                   <c:forEach items="${TabForm }" var="rows" varStatus="count">
                                      <div class="cp-table-row">
                            <div class="cp-td">
                                <input type="checkbox" value="${rows.id }" name="remove_id"> 
                                <span class="cp-icon-user"> </span>
                                ${rows.title }
                            </div>
                            <div class="cp-td">${rows.form_column_title }</div>
                              </div>
                    </c:forEach>
                            </form>
                            
                      
                     
                    </div>

                    <div class="cp-bottom-controls">
                      
                        
                        <div class="cp-btn-row">
                            <button class="cp-btn" onclick="shows2('draggable-window6','Add Tab');getData('/addtab-filemanager/${process.id}', 'mainbody6');clearColumnSelect();">Add Tab</button>
                       
                            <button class="cp-btn" onclick="shows2('draggable-window6','Add Tab');
                            postDatawaitFor('/edittab-filemanager?${_csrf.parameterName}=${_csrf.token}', 'delAddTabs','mainbody6','loadSavedColumns');
                            ">Edit...</button>
                            
                            
                            <button class="cp-btn" onclick="postData('/removetab-filemanager?${_csrf.parameterName}=${_csrf.token}', 'delAddTabs','mainbody3');closeed2('draggable-window6')">Remove</button>
                        </div>
                    </div>

                </div>
                
                
                
                 <div class="content" id="Form-TabSet${process.id}-2" style="display:none"> 
                
                <fieldset>
                    <legend>
                        <label class="checkbox-label">
                        
                         
                      <form id="useValid" name="useValid">   
                       <input type="checkbox" name="used_valid" value="1"  id="chk-send-as"onchange="postDataNotReset('/usevalid-filemanager?${_csrf.parameterName}=${_csrf.token}','useValid','savediv');" 
                        <c:if test="${process.used_valid == 1}">checked</c:if> > ใช้งาน Valid.
                    
                        <input type="hidden" id="process_id" name="process_id" value="${process.id}">  
                      </form>
                        </label>
                    </legend>
                    <div class="description">
                        ใช้สำหรับแจ้งผลการตรวจสอบและสถานะการดำเนินการของระบบ
เพื่อบอกผู้ใช้ว่าข้อมูลสามารถดำเนินการได้หรือไม่ พร้อมเหตุผลประกอบ
                    </div>
                    <div class="clearfix">
                        <button class="action-btn" id="btn-send-as" disabled>ปรับค่า...</button>
                    </div>
                </fieldset>

                <fieldset>
                    <legend>
                        <label class="checkbox-label">
                        
   				<form id="useAttachment" name="useAttachment">   
                       <input type="checkbox" name="used_attachment" value="1" id="chk-on-behalf" onchange="postDataNotReset('/useattachment-filemanager?${_csrf.parameterName}=${_csrf.token}','useAttachment','savediv');" 
                        <c:if test="${process.used_attachment == 1}">checked</c:if> >ใช้งาน Attachment
                    
                        <input type="hidden" id="process_id" name="process_id" value="${process.id}">  
                      </form>
                        </label>
                    </legend>
                    <div ="description">
                         ใช้สำหรับแนบไฟล์ประกอบข้อมูลหรือเอกสาร
					เพื่อใช้เป็นหลักฐานหรือข้อมูลสนับสนุนในการดำเนินการ
                    </div>
                    <div class="clearfix">
                        <button class="action-btn" id="btn-on-behalf" disabled>ปรับค่า...</button>
                    </div>
                </fieldset>

                <fieldset style="display:none">
                    <legend>
                        <label class="checkbox-label">
                            <input type="checkbox" id="chk-rights"> Mailbox Rights
                        </label>
                    </legend>
                    <div class="description">
                        The Mailbox Rights editor enables you to assign permissions to access this mailbox.
                    </div>
                    <div class="clearfix">
                        <button class="action-btn" id="btn-rights" disabled>ปรับค่า...</button>
                    </div>
                </fieldset>

            </div>
                
                
                
                
                
                
                
                         <div class="cp-content" id="Form-TabSet${process.id}-3"  style="display:none">
                
                  
                    <label class="cp-chk-label">
                    ซ่อนเเสดงผล Input ตามเงื่อนไข
                    </label>
                    


                    <div class="cp-table-container">
                        <div class="cp-table-header">
                            <div class="cp-th">Action Type</div>
                            <div class="cp-th">Input</div>
                        </div>
     
                       <form id="delInputHiddenVisibility" name="delInputHiddenVisibility">   
                              <input type="hidden" id="process_id" name="process_id" value="${process.id}">  
                   <c:forEach items="${InputVisibility }" var="rows" varStatus="count">
                                      <div class="cp-table-row">
                            <div class="cp-td">
                                <input type="checkbox" value="${rows.id }" name="remove_id"> 
                                <span class="cp-icon-user"> </span>
                                
                                
                               <c:if test="${rows.action_type  == 0}">Insert</c:if> 
                                  <c:if test="${rows.action_type  == 1}">Update</c:if> 
                                    <c:if test="${rows.action_type  == 2}">Change Status</c:if> 
                                      <c:if test="${rows.action_type  == 3}">Delete</c:if> 
                            </div>
    
                            <div class="cp-td">${rows.form_column_title }</div>
                              </div>
                    </c:forEach>
                            </form>
                            
                      
                     
                    </div>

                    <div class="cp-bottom-controls">
                      
                        
                        <div class="cp-btn-row">
                            <button class="cp-btn" onclick="shows2('draggable-window6','Input Hidden Visibility');getData('/addInputHiddenVisibility-filemanager/${process.id}', 'mainbody6');
                            clearColumnSelect();">Add</button>
                       
                            <button class="cp-btn" onclick="shows2('draggable-window6','Add Tab');
                            postDatawaitFor('/editInputHiddenVisibility-filemanager?${_csrf.parameterName}=${_csrf.token}', 'delInputHiddenVisibility','mainbody6','Input Hidden Visibility');
                            ">Edit...</button>
                            
                            
                            <button class="cp-btn" onclick="postData('/removeInputHiddenVisibility-filemanager?${_csrf.parameterName}=${_csrf.token}', 'delInputHiddenVisibility','mainbody3');closeed2('draggable-window6')">Remove</button>
                        </div>
                    </div>

                </div>
                
    	            
                 <div class="cp-content" id="Form-TabSet${process.id}-4"  style="display:none">
                 
                 <form id="usesubtable" name="usesubtable">  
                 <div class="form-row" style="display: flex;
    align-items: center;
    margin-bottom: 8px;">
      
           <span class="label" style="width: 70%;
    flex-shrink: 0;">Edit Mode:</span>


<select name="subtable_editmode" multiple="multiple" onchange="postDataNotReset('/usesubtable-filemanager?${_csrf.parameterName}=${_csrf.token}','usesubtable','savediv');">
<option value="0" >-</option>
<c:forEach items="${sub_process }"  var="row">
	<option value="${row.id }"  <c:if test="${fn:contains(process.subtable_editmode, row.id)}">selected="selected"</c:if>>${row.description }</option>
</c:forEach>
</select>
   <input type="hidden" id="process_id" name="process_id" value="${process.id}">  
</div>
                 <div class="form-row" style="display: flex;
    align-items: center;
    margin-bottom: 8px;">
      
           <span class="label" style="width: 70%;
    flex-shrink: 0;">View Mode:</span>

<select name="subtable_viewmode" multiple="multiple" onchange="postDataNotReset('/usesubtable-filemanager?${_csrf.parameterName}=${_csrf.token}','usesubtable','savediv');">
<option value="0" >-</option>
<c:forEach items="${sub_process }"  var="row">
	<option value="${row.id }"  <c:if test="${fn:contains(process.subtable_viewmode, row.id)}">selected</c:if>>${row.description }</option>
</c:forEach>
</select>
</div>
   <input type="hidden" id="process_id" name="process_id" value="${process.id}">  
     </form>   
                 </div>
           
                
                <div class="content" id="Form-TabSet${process.id}-5" style="display:none"> 
    <table>
	<c:forEach var="rows"  items="${eformfields}" varStatus="count">   
     
     
    <tr>
	
    <td >${rows.field_label}:</td>
   <td >  
 <select class="group-select" id="select_${rows.field_id}" 
        onchange="applyColorAndSave(this, '${rows.field_id}')" 
        style="transition: background 0.3s; padding: 4px; border-radius: 4px;">
    <option value="0">-</option>
    <c:forEach var="listgroup" items="${listgroup_label}">
        <option value="${listgroup.id}" 
            <c:if test="${rows.group_label_id == listgroup.id}">selected</c:if>>
            ${listgroup.group_label_name}
        </option>
    </c:forEach>
</select>
</td>
</tr>



      </c:forEach>
         </table>  
       
                </div>
                
            </div>

            <div class="cp-footer">
                <button class="cp-btn-footer" onclick="Autoclick('refresh-${process.id}');closeed2('draggable-window3');">Apply</button>
                <button class="cp-btn-footer" onclick="closeed2('draggable-window3')">Cancel</button>
            </div>
        </div>
    </div>

 <div id="savediv"></div>
</body>

  <script>
  // Function to link checkbox to button
  function linkCheckboxToButton(checkboxId, buttonId) {
      const checkbox = document.getElementById(checkboxId);
      const button = document.getElementById(buttonId);

      checkbox.addEventListener('change', function() {
          if (this.checked) {
              button.disabled = false;
          } else {
              button.disabled = true;
          }
      });
  }

  // Link all 3 sections
  linkCheckboxToButton('chk-send-as', 'btn-send-as');
  linkCheckboxToButton('chk-on-behalf', 'btn-on-behalf');
  linkCheckboxToButton('chk-rights', 'btn-rights');

  // Sidebar interactivity (Visual only)
  const menuItems = document.querySelectorAll('.sidebar li');
  menuItems.forEach(item => {
      item.addEventListener('click', () => {
          menuItems.forEach(i => i.classList.remove('active'));
          item.classList.add('active');
      });
  });
  
  
  
  function getColorFromId(id) {
	    // ใช้สูตร HSL เพื่อให้สีที่ออกมาเป็นโทนพาสเทล ดูสบายตา
	    // id * 137.5 เป็นการกระจายเฉดสี (Golden Angle) เพื่อไม่ให้สีใกล้กันเกินไป
	    
	  
	    const hue = (id * 137.5) % 360; 
	    return `hsl(`+hue+`, 70%, 90%)`; // 90% คือความสว่าง (Pastel)
	}


	
	// 2. ฟังก์ชันจัดการเรื่องสีและการบันทึก
	function applyColorAndSave(selectObj, fieldId) {
	    const val = selectObj.value;
	    
	    // เปลี่ยนสีพื้นหลังทันที
	    selectObj.style.backgroundColor = getColorFromId(val);
	    selectObj.style.borderColor = "rgba(0,0,0,0.1)";

	    // ส่ง AJAX ไปบันทึก (เฉพาะตอนคนคลิกเปลี่ยนค่า)
	    if (fieldId) {
	        getData("/applygroup_label-condition?field_id=" + fieldId + "&value=" + val, "savediv");
	    }
	}
	function SelectorAll() {
	 document.querySelectorAll(".group-select").forEach(function(el) {
	        // ระบายสีตามค่าที่ selected มาจาก Database
	        el.style.backgroundColor = getColorFromId(el.value);
	    });
	}
	SelectorAll();	 	
	  </script>
</html>