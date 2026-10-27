<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ADD Profile</title>
    <style>
        :root {
            --bg-color: #f0f0f0;
            --border-color: #d9d9d9;
            --input-border: #7a7a7a;
            --focus-border: #0078d7;
            --text-color: #222;
            --font-stack: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

 

        /* Window Container */
       .cl_window .window {
            width: 750px;
            background-color: var(--bg-color);
            border: 1px solid #0078d7; /* Active window border color */
            box-shadow: 0 4px 10px rgba(0,0,0,0.2);
            display: flex;
            flex-direction: column;
        }

        /* Header */
       .cl_window .window-header {
            background-color: white;
            padding: 8px 10px;
            font-size: 14px;
            font-weight: 500;
            display: flex;
            justify-content: space-between;
            align-items: center;
            /* In Win10 style, header often blends, but adding separator for clarity */
            border-bottom: 1px solid #eee; 
        }

       .cl_window .close-btn {
            cursor: pointer;
            font-size: 16px;
            color: #666;
            line-height: 1;
        }

       .cl_window .window-body {
            padding: 15px;
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        /* Layout Grid */
       .cl_window .top-section {
            display: flex;
            gap: 20px;
        }

       .cl_window .main-form {
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 8px;
        }

       .cl_window .sidebars {
            width: 300px;
   
        }

        /* Form Elements */
       .cl_window .form-row {
            display: flex;
            align-items: center;
        }

       .cl_window label {
            width: 120px;
            padding-left:5px;
            color: var(--text-color);
        }

      .cl_window  input[type="text"], 
       .cl_window input[type="date"], 
       .cl_window input[type="time"],
      .cl_window  select {
            padding: 3px 5px;
            border: 1px solid var(--input-border);
            font-family: var(--font-stack);
            font-size: 13px;
            height: 26px;
            width:98%;
        }

      .cl_window  input[type="text"]:focus, 
      .cl_window  input[type="date"]:focus, 
      .cl_window  input[type="time"]:focus,
     .cl_window   select:focus {
            border-color: var(--focus-border);
            outline: none;
        }

       .cl_window .full-width {
            flex: 1;
        }

        /* Date Time Row Specifics */
      .cl_window  .datetime-group {
            display: flex;
            align-items: center;
            gap: 5px;
        }

     .cl_window   .datetime-group input[type="date"] { width: 110px; }
       .cl_window .datetime-group input[type="time"] { width: 100px; }

      .cl_window  .checkbox-label {
            margin-left: 10px;
            width: auto;
            display: flex;
            align-items: center;
            gap: 5px;
            cursor: pointer;
        }

        /* Horizontal Rule */
      .cl_window  .separator {
            border-bottom: 1px solid #ccc;
            margin: 5px 0;
        }

        /* Sidebar Specifics */
       .cl_window .sidebar-row {
            display: flex;
            align-items: center;
            margin-bottom: 5px;
            width:100%;
        }
       .cl_window .sidebar-row label { width: 140px; }
       .cl_window .sidebar-row select { flex: 1; }

       .cl_window .color-preview {
            display: inline-block;
            width: 12px;
            height: 12px;
            background-color: #5c8ae6; /* Example Blue */
            margin-right: 5px;
            vertical-align: middle;
        }

        /* Table Area (Replacing the large textarea) */
       .cl_window .table-container {
          
            border: 1px solid #adadad; /* Blue border like in the mockup for the textarea */
            background: white;
            height: 200px;
            overflow-y: auto;
        }

       .cl_window table {
            width: 100%;
            border-collapse: collapse;
        }

       .cl_window th {
            background-color: #f0f0f0;
            border-bottom: 1px solid #ccc;
            border-right: 1px solid #eee;
            text-align: left;
            padding: 5px;
            font-weight: 600;
            font-size: 12px;
            position: sticky;
            top: 0;
        }

      .cl_window  td {
            border-bottom: 1px solid #eee;
            border-right: 1px solid #eee;
            padding: 5px;
            vertical-align: top;
        }

      .cl_window  tr:hover {
            background-color: #e6f2fa;
        }

        /* Footer */
       .cl_window .window-footer {
            padding: 10px 15px;
            display: flex;
            justify-content: flex-end;
            gap: 10px;
            background-color: var(--bg-color);
        }

      .cl_window  button {
            min-width: 80px;
            padding: 4px 15px;
            font-family: var(--font-stack);
            font-size: 13px;
            border: 1px solid #adadad;
            background-color: #e1e1e1;
            cursor: pointer;
        }

      .cl_window  button:hover {
            background-color: #e5f1fb;
            border-color: #0078d7;
        }

        button.primary {
            border: 2px solid #0078d7; /* Emulate the focus look */
            border-radius: 0;
        }
        
       .cl_window button.sidebar-btn {
            width: 100%;
   
        }

        /* Utilities */
        .cl_window .disabled {
            background-color: #e6e6e6;
            color: #888;
        }
    </style>
</head>
<body>

    <div class="cl_window">
      
  



        <div class="window-body">
      <form id="formprofile" name="formprofile">
    <input type="hidden" name="process_id" class="full-width" value="${id_refer }">
    <c:if test="${item.id > 0}">
        <input type="hidden" id="id" name="id" value="${item.id}">

        <input type="hidden" id="registerCode" name="customerCode" value="${item.register_code}${item.register_daterun}-${item.register_id}">
    </c:if>

    <div class="top-section" style="padding: 10px;">
        <div style="display: flex; justify-content: space-between;">
            <div class="main-form" style="width: 48%;">
                <div class="form-row">
                    <label>คำนำหน้า/รูปแบบ:</label>
                          <select name="register_title" class="full-width">
                        <c:forEach items="${Register_title }" var="row">
                            <option value="${row.dataset_data_title }" <c:if test="${item.register_title == row.dataset_data_title }">selected</c:if>> ${row.dataset_data_title }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-row">
                    <label>ชื่อ:</label>
                    <input type="text" name="register_name" class="full-width" value="${item.register_name }">
                </div>
                <div class="form-row">
                    <label>ประเภท (Party Type):</label>
     
                        <select name="party_type" class="full-width">
                        <c:forEach items="${Party_type }" var="row">
                            <option value="${row.dataset_data_title }" <c:if test="${item.party_type == row.dataset_data_title }">selected</c:if>> ${row.dataset_data_title }</option>
                        </c:forEach>
                    </select>
                    
                </div>
                <div class="form-row">
                    <label>สถานะ:</label>
                    <select name="register_status" class="full-width">
                    
                    <c:set  var="status"  value="ACTIVE" />
                         <c:if  test="${item !=  null }"> <c:set  var="status"  value="${item.register_status}" /></c:if>
                    
                        <c:forEach items="${Register_status }" var="row">

                        
                            <option value="${row.dataset_data_title }" <c:if test="${status == row.dataset_data_title }">selected</c:if>> ${row.dataset_data_title }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="main-form" style="width: 48%;">
                <div class="form-row">
                    <label>กลุ่ม (Type):</label>
                    <select name="register_type" class="full-width">
                        <c:forEach items="${Register_Type }" var="row">
                            <option value="${row.dataset_data_title }" <c:if test="${item.register_type == row.dataset_data_title }">selected</c:if>> ${row.dataset_data_title }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-row">
                    <label>หมวดหมู่ (Groups):</label>
                    <select name="register_groups" class="full-width">
                        <c:forEach items="${Register_groups }" var="row">
                            <option value="${row.dataset_data_title }" <c:if test="${item.register_groups == row.dataset_data_title }">selected</c:if>> ${row.dataset_data_title }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-row">
                    <label>ประเภทธุรกิจ:</label>
              
                        <select name="business_type" class="full-width">
                        <c:forEach items="${Business_category }" var="row">
                            <option value="${row.dataset_data_title }" <c:if test="${item.product_category == row.dataset_data_title }">selected</c:if>> ${row.dataset_data_title }</option>
                        </c:forEach>
                    </select>
                    
                </div>
                <div class="form-row">
                    <label>รายละเอียด:</label>
                    <textarea class="full-width" name="register_note" rows="2">${item.register_note }</textarea>
                </div>
            </div>
        </div>
   
    </div>

    <div class="windows active" style="background-color:#F3F4F6; margin-top: 15px;">
        <div class="cl_window">
            <div id="tm-v2-wrapper">
                <div class="tm-window">

                    <div class="tm-tab-bar" style="width:100%">
                        <div class="tm-tab active" id="Tab-Tabs-EditForm-1" onclick="switchTab('Tabs-EditForm','3','1')">
                            ที่อยู่ & การติดต่อองค์กร
                        </div>
                        <div class="tm-tab" id="Tab-Tabs-EditForm-2" onclick="switchTab('Tabs-EditForm','3','2')">
                            ผู้ติดต่อ & ผู้อนุมัติ
                        </div>
                        <div class="tm-tab" id="Tab-Tabs-EditForm-3" onclick="switchTab('Tabs-EditForm','3','3')">
                            การเงิน & ข้อมูลอื่นๆ
                        </div>
                    </div>

                    <div class="tm-content">
                        <div id="Form-Tabs-EditForm-1">
                            <div class="wpf-datagrid-container" style="padding:15px; display: flex; justify-content: space-between;">
                                <div class="main-form" style="width:48%">
                                    <div class="form-row">
                                        <label>ที่อยู่:</label>
                                        <textarea class="full-width" name="register_address" rows="3">${item.register_address}</textarea>
                                    </div>
 							
								 							
								 						<div class="form-row">
						    <label>ที่อยู่เเบบเต็ม (ค้นหาได้):</label>
						    
						   <select id="select-address" name="register_fulladdress" placeholder="พิมพ์เพื่อค้นหาที่อยู่...">
    <c:if test="${not empty item.register_fulladdress}">
        <option value="${item.register_fulladdress}" selected>${item.register_fulladdress}</option>
    </c:if>
</select>

<script>
    // 🌟 เริ่มการทำงานของ TomSelect สำหรับ Address 🌟
    var addressSelectInstance = new TomSelect("#select-address", {
        valueField: 'value', 
        labelField: 'text',  
        searchField: 'text',
        create: false,          
        maxOptions: 50,         
        allowEmptyOption: true,
        
        load: function(query, callback) {
            if (!query.length) return callback();

            var formData = new FormData();
            formData.append('search', query); 
            // ถ้า endpoint ใหม่ต้องการ parameter อื่นๆ เพิ่ม (เช่น type) สามารถ append เพิ่มตรงนี้ได้

            var targetUrl = '/fulladdresssearchajax-filemanage?${_csrf.parameterName}=${_csrf.token}';

            fetch(targetUrl, {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(json => {
                if(!json) json = [];
                
                // 🔥 แปลงร่าง JSON ให้เข้ากับ TomSelect 
                var formattedJson = json.map(function(item) {
                    // ⚠️ สำคัญ: เปลี่ยน 'item.dataset_data_title' ให้ตรงกับ Key ของ JSON ที่ Backend ส่งกลับมานะครับ
                    // เช่น ถ้า Backend รีเทิร์นมาเป็น { "optVal1": "กรุงเทพมหานคร..." } ก็ให้เปลี่ยนเป็น item.optVal1
                    var addressText = item.dataset_data_title; 
                    
                    return {
                        value: addressText, // Value ที่จะถูก submit
                        text: addressText   // Text ที่จะแสดงให้ User เห็น
                    };
                });
                
                callback(formattedJson);
            }).catch(()=>{
                callback();
            });
        }
    });
</script>
						</div>					
						                     
                                    
                                    
                                    <div class="form-row">
                                        <label>ภูมิภาค (Region):</label>
                                         <select name="region" class="full-width">
					                        <c:forEach items="${Region }" var="row">
					                            <option value="${row.dataset_data_title }" <c:if test="${item.region == row.dataset_data_title }">selected</c:if>> ${row.dataset_data_title }</option>
					                        </c:forEach>
                  					  </select>
                                        
                                    </div>
                                    <div class="form-row">
                                        <label>พิกัด (Lat / Lon):</label>
                                        <div style="display: flex; gap: 5px;">
                                            <input type="text" name="lat" value="${item.lat}" placeholder="Lat" style="width: 50%;">
                                            <input type="text" name="lon" value="${item.lon}" placeholder="Lon" style="width: 50%;">
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label>เลขผู้เสียภาษี:</label>
                                        <input type="text" class="full-width" name="tax_id" value="${item.tax_id}">
                                    </div>
                             
                                    <div class="form-row">
                                        <label>สาขา (Branch):</label>
                                        <input type="text" class="full-width" name="branch_code" value="${item.branch_code}">
                                    </div>
                                </div>  
                                <div class="main-form" style="width:48%">
                                    <div class="form-row">
                                        <label>โทรศัพท์องค์กร:</label>
                                        <input type="text" class="full-width" name="phone_number" value="${item.phone_number}">
                                    </div>
                                    <div class="form-row">
                                        <label>แฟกซ์ (Fax):</label>
                                        <input type="text" class="full-width" name="fax" value="${item.fax}">
                                    </div>
                                    <div class="form-row">
                                        <label>มือถือองค์กร:</label>
                                        <input type="text" class="full-width" name="mobile_number" value="${item.mobile_number}">
                                    </div>
                                    <div class="form-row">
                                        <label>อีเมลบริษัท:</label>
                                        <input type="text" class="full-width" name="register_email" value="${item.register_email}">
                                    </div>
                                    <div class="form-row">
                                        <label>Website:</label>
                                        <input type="text" class="full-width" name="website" value="${item.website}">
                                    </div>
                                    <div class="form-row">
                                        <label>Social Media:</label>
                                        <input type="text" class="full-width" name="social" value="${item.social}">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="Form-Tabs-EditForm-2" style="display:none;">
                            <div class="wpf-datagrid-container" style="padding:15px; display: flex; justify-content: space-between;">
                                <div class="main-form" style="width:48%">
                                    <h4 style="margin-top:0; color:#555;">ผู้ติดต่อหลัก (Contact Person)</h4>
                                    <div class="form-row">
                                        <label>ชื่อผู้ติดต่อ:</label>
                                        <input type="text" class="full-width" name="contact_person" value="${item.contact_person}">
                                    </div>
                                    <div class="form-row">
                                        <label>ตำแหน่ง:</label>
                                        <input type="text" class="full-width" name="person_position" value="${item.person_position}">
                                    </div>
                                    <div class="form-row">
                                        <label>เบอร์โทร:</label>
                                        <input type="text" class="full-width" name="person_number" value="${item.person_number}">
                                    </div>
                                    <div class="form-row">
                                        <label>อีเมล:</label>
                                        <input type="text" class="full-width" name="person_email" value="${item.person_email}">
                                    </div>
                                </div>
                                <div class="main-form" style="width:48%">
                                    <h4 style="margin-top:0; color:#555;">ผู้อนุมัติ (Approver)</h4>
                                    <div class="form-row">
                                        <label>ชื่อผู้อนุมัติ:</label>
                                        <input type="text" class="full-width" name="contact_approvers" value="${item.contact_approvers}">
                                    </div>
                                    <div class="form-row">
                                        <label>ตำแหน่ง:</label>
                                        <input type="text" class="full-width" name="approvers_position" value="${item.approvers_position}">
                                    </div>
                                    <div class="form-row">
                                        <label>เบอร์โทร:</label>
                                        <input type="text" class="full-width" name="approvers_number" value="${item.approvers_number}">
                                    </div>
                                    <div class="form-row">
                                        <label>อีเมล:</label>
                                        <input type="text" class="full-width" name="approvers_email" value="${item.approvers_email}">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="Form-Tabs-EditForm-3" style="display:none;">
                            <div class="wpf-datagrid-container" style="padding:15px; display: flex; justify-content: space-between;">
                                <div class="main-form" style="width:48%">
                                    <div class="form-row">
                                        <label>เงื่อนไขชำระเงิน (Payment Terms):</label>
                                        <textarea class="full-width" name="payment_terms" rows="2">${item.payment_terms}</textarea>
                                    </div>
                                    <div class="form-row">
                                        <label>วงเงินเครดิต (Credit Limit):</label>
                                        <input type="text" class="full-width" name="credit_limit" value="${item.credit_limit}">
                                    </div>
                                    <div class="form-row">
                                        <label>บัญชีธนาคาร (Bank Account):</label>
                                        <input type="text" class="full-width" name="bank_account" value="${item.bank_account}">
                                    </div>
                                    <div class="form-row">
                                        <label>สกุลเงิน (Currency):</label>
                                        <input type="text" class="full-width" name="preferred_currency" value="${item.preferred_currency}">
                                    </div>
                                </div>
                                <div class="main-form" style="width:48%">
                                    <div class="form-row">
                                        <label>ข้อมูล DBD:</label>
                                        <input type="text" class="full-width" name="dbd" value="${item.dbd}">
                                    </div>
                                    <div class="form-row">
                                        <label>เงื่อนไขอื่นๆ:</label>
                                        <textarea class="full-width" name="register_other_conditions" rows="4">${item.register_other_conditions}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div> </div>
            </div>
        </div>
    </div>
</form>
        </div>
         
<c:if test="${item.id > 0}">
<c:set  var="url"  value="updateprofile-filemanager" />
   <div class="window-footer">
            <button class="primary" onclick="postData('/${url}?${_csrf.parameterName}=${_csrf.token}', 'formprofile','microsofts${id_refer }');closeed2('draggable-window3');">OK</button>
            <button onclick="closeed2('draggable-window3')">Cancel</button>
        </div>
</c:if>
<c:if test="${item == null}">
<c:set  var="url"  value="saveprofile-filemanager" />
   <div class="window-footer">
            <button class="primary" onclick="postData('/${url}?${_csrf.parameterName}=${_csrf.token}', 'formprofile','microsofts${id_refer }');">SAVE..</button>
            <button onclick="closeed2('draggable-window3')">Cancel</button>
        </div>
</c:if>
     
    </div>

</body>

                 
 <script>
    new TomSelect("#select-address", {
        create: false,          // ห้ามพิมพ์ค่าใหม่เอง (ต้องเลือกจากที่มี)
        sortField: {
            field: "text",
            direction: "asc"
        },
        maxOptions: 50,         // **สำคัญ** แสดงผลลัพธ์แค่ 50 ตัวพอ (พิมพ์แล้วค่อยกรอง) จะได้ไม่หน่วง
        allowEmptyOption: true
    });
</script>

</html>