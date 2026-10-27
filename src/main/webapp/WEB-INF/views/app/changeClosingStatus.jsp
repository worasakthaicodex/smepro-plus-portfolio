<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
        :root {
            --ms-green: #007a5e; /* สีเขียวหลักของแอป */
            --ms-green-hover: #005a46;
            --error-red: #a4262c;
            --bg-gray: #f3f2f1;
            --border-color: #d2d0ce;
            --text-main: #323130;
            --text-secondary: #605e5c;
            --font-family: 'Segoe UI', wf_segoe-ui_normal, helvetica, arial, sans-serif;
        }

 

        /* Container หลักสไตล์ Sidebar */
        .alert-panel {
            width: 100%;
            height: 90%;
            flex-direction: column;
            position: relative;
        }

    
       #alert-panel .header .subtitle {
            color: var(--text-secondary);
            font-size: 12px;
            text-transform: uppercase;
            display: block;
            margin-bottom: 4px;
        }

      #alert-panel  .header h1 {
            margin: 0;
            font-size: 20px;
            font-weight: 600;
            color: var(--text-main);
        }

        #alert-panel .close-btn {
            position: absolute;
            top: 60px;
            right: 20px;
            font-size: 20px;
            cursor: pointer;
            color: var(--text-secondary);
        }

        /* ส่วนเนื้อหา (Content) */
     #alert-panel   .content {
            padding: 0 24px;
            overflow-y: auto;
            flex-grow: 1;
        }

        /* ปุ่มเขียว Add alert rule */
      #alert-panel  .btn-add {
            width: 100%;
            background: var(--ms-green);
            color: white;
            border: none;
            padding: 10px;
            border-radius: 4px;
            font-weight: 600;
            cursor: pointer;
            margin: 15px 0;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 8px;
        }

        .btn-add:hover { background: var(--ms-green-hover); }

        /* การตั้งค่าแต่ละส่วน (Form Group) */
       #alert-panel .form-group {
            margin-bottom: 20px;
        }

       #alert-panel .label {
            font-weight: 600;
            font-size: 14px;
            margin-bottom: 8px;
            display: block;
        }

        /* Toggle Switch */
        #alert-panel .toggle-container {
            display: flex;
            align-items: center;
            gap: 10px;
            font-size: 14px;
        }

        .switch {
            position: relative;
            display: inline-block;
            width: 40px;
            height: 20px;
        }

        .switch input { opacity: 0; width: 0; height: 0; }

        .slider {
            position: absolute;
            cursor: pointer;
            top: 0; left: 0; right: 0; bottom: 0;
            background-color: #ccc;
            transition: .4s;
            border-radius: 20px;
        }

        .slider:before {
            position: absolute;
            content: "";
            height: 14px; width: 14px;
            left: 3px; bottom: 3px;
            background-color: white;
            transition: .4s;
            border-radius: 50%;
        }

        input:checked + .slider { background-color: var(--ms-green); }
        input:checked + .slider:before { transform: translateX(20px); }

        /* Input Fields */
      #alert-panel  input[type="text"], #alert-panel select {
            width: 100%;
            padding: 8px;
            border: 1px solid var(--border-color);
            border-radius: 2px;
            font-size: 14px;
        }

        .has-error {
            border-color: var(--error-red) !important;
        }

        .error-msg {
            color: var(--error-red);
            font-size: 11px;
            margin-top: 4px;
        }

        /* Radio Buttons */
        .radio-group {
            display: flex;
            flex-direction: column;
            gap: 8px;
            font-size: 14px;
        }

        .radio-option {
            display: flex;
            align-items: center;
            gap: 8px;
        }

        /* Footer / Checkbox */
        .footer-info {
            font-size: 13px;
            color: var(--text-main);
            border-top: 1px solid var(--border-color);
            padding: 15px 0;
            margin-top: 15px;
        }

        .checkbox-row {
            display: flex;
            align-items: center;
            gap: 8px;
            margin-top: 10px;
        }

        .checkbox-row input {
            accent-color: var(--ms-green);
            width: 16px; height: 16px;
        }
    </style>



<div class="alert-panel" id="alert-panel">


    <div class="content">
     

<form name="form_change_status" id="form_change_status">    
        <div class="form-group">
            <span class="label">เอกสาร</span>
         <input type="text" value="${edata.doc } ${edata.data_title }" readonly style="background-color: var(--bg-gray);">
      <input type="hidden" value="${edata.data_id }" name="id">
       <input type="hidden" value="${edata.processes_id }" name="process_id">
        </div>

        <div class="form-group">
            <span class="label">สถานะล่าสุด</span>
            <input type="text" value="${doc.closing_status }" name="from_status" readonly style="background-color: var(--bg-gray);">
        </div>
     <div class="form-group">
            <span class="label">สถานะที่จะเปลี่ยน</span>
            <select name="status">
            <c:forEach items="${status}" var="row">
                    <option value="${row }">${row }</option>
			</c:forEach>
                </select>
        </div>
</form>      
   <button class="btn-add" onclick='toggleasideInfo();postData("/changeClosingStatusSave-filemanager?${_csrf.parameterName}=${_csrf.token}","form_change_status","body_asideinfo");'>Update Status</button>
      

      
    </div>
</div>
        