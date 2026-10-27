<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <style>
        .admin-box { background-color: #e6f7ff; border: 1px solid #91d5ff; padding: 10px; margin-top: 15px; }
        .form-row { display: flex; align-items: center; margin-bottom: 5px; }
        .form-row label { width: 120px; font-weight: 500; }
        .full-width { flex: 1; padding: 4px; }
    </style>
</head>
<body>

<div class="cl_window">
    <div class="window-body">
        
        <form id="formHelpDesk" name="formHelpDesk" method="POST" enctype="multipart/form-data">
            
            <input type="hidden" name="process_id" value="${process_id}">
            <input type="hidden" name="item_id" value="${item_id}"> <input type="hidden" name="id" value="${ticket.id}">
            
            <input type="hidden" name="folder" value="${file_patch}"> <input type="hidden" name="file_tag" value="ticket_attach">

            <div style="padding-bottom: 10px; border-bottom: 1px solid #ccc; margin-bottom:10px;">
                 <div class="form-row">
                    <label>ลูกค้า/อ้างอิง:</label>
                    <input type="text" class="full-width" value="[${profile.register_code}${profile.register_daterun}-${profile.register_id}] - ${profile.register_name}" readonly style="background-color:#eee;">
                </div>
            </div>

            <h4 style="margin: 0 0 10px 0; color: #555;">📝 รายละเอียดคำขอ</h4>
            
            <div class="form-row">
                <label>หัวข้อเรื่อง:</label>
                <select name="requestTopic" class="full-width">
                    <option value="">-- เลือกหัวข้อ --</option>
                    <c:forEach items="${Action_type}" var="row">
                        <option value="${row.dataset_data_title}" <c:if test="${ticket.requestTopic == row.dataset_data_title}">selected</c:if>>
                            ${row.dataset_data_title}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-row" style="align-items: flex-start;">
                <label>รายละเอียด:</label>
                <textarea class="full-width" name="description" rows="4" placeholder="ระบุรายละเอียด...">${ticket.description}</textarea>
            </div>

            <div class="form-row">
                <label>ความเร่งด่วน:</label>
                <select name="priority" style="width: 150px;">
                    <option value="NORMAL" <c:if test="${ticket.priority == 'NORMAL'}">selected</c:if>>ปกติ</option>
                    <option value="URGENT" <c:if test="${ticket.priority == 'URGENT'}">selected</c:if>>ด่วน</option>
                </select>
            </div>
<c:if test="${not empty ticket.attachedFiles}">
                 <div class="form-row" style="align-items: flex-start; margin-top:5px;">
                    <label>ไฟล์เดิม:</label>
                    <div class="full-width" style="font-size:13px;">
                        <c:forEach items="${ticket.attachedFiles}" var="f">
                            <div id="file-row-${f.file_id}" style="margin-bottom:3px; display:flex; align-items:center;">
                                <a href="${f.file_path_full}"  onclick="shows2('draggable-window6','Preview image'); 
        getData('/renderbyapp-filemanager?file_edata=${f.file_id }', 'mainbody6')"  style="text-decoration:none; color:#0078d7; margin-right:10px;">
                                    📄 ${f.file_name_original} <span style="color:#999; font-size:11px;">(${f.file_size2})</span>
                                </a>
                                
             
                                
                                
                                <span onclick="deleteOldFile('${f.file_id}')" 
                                      style="cursor:pointer; color:red; font-weight:bold; font-size:14px;" 
                                      title="ลบไฟล์นี้">
                                    [x]
                                </span>
                            </div>
                        </c:forEach>
                    </div>
                 </div>
            </c:if>
            <div class="form-row" style="background:#fafafa; padding:10px; border:1px dashed #ccc; margin-top:5px;">
                <label>แนบรูป/ไฟล์:</label>
                <input type="file" name="files" id="files" multiple> 
            </div>
            
            <div class="admin-box">
                <h4 style="margin: 0 0 10px 0; color: #0050b3;">🔧 ส่วนเจ้าหน้าที่ (Admin)</h4>
                
                <div class="form-row">
                    <label>สถานะ:</label>
                    <select name="status" class="full-width">
                        <option value="OPEN" <c:if test="${ticket.status == 'OPEN'}">selected</c:if>>รอตรวจสอบ</option>
                        <option value="PROCESSING" <c:if test="${ticket.status == 'PROCESSING'}">selected</c:if>>กำลังดำเนินการ</option>
                        <option value="COMPLETED" <c:if test="${ticket.status == 'COMPLETED'}">selected</c:if>>เสร็จสิ้น</option>
                        <option value="REJECTED" <c:if test="${ticket.status == 'REJECTED'}">selected</c:if>>ยกเลิก</option>
                    </select>
                </div>

                <div class="form-row">
                    <label>เลขที่ใบแก้:</label>
                    <input type="text" name="resultDocNo" class="full-width" value="${ticket.resultDocNo}" placeholder="เช่น RE-2024-001">
                </div>

                <div class="form-row" style="align-items: flex-start;">
                    <label>บันทึกจนท.:</label>
                    <textarea class="full-width" name="adminNote" rows="2">${ticket.adminNote}</textarea>
                </div>
            </div>

        </form>
    </div>

    <div class="window-footer" style="padding:10px; text-align:right;">
        <button class="primary" onclick="submitHelpDesk()">Save</button>
        <button onclick="closeed2('draggable-window3')">Cancel</button>
    </div>
</div>

<script>
    function submitHelpDesk() {
        // ใช้ Ajax หรือ Form Submit ปกติ
        // ถ้าใช้ Library ของคุณ (postData) ต้องมั่นใจว่ารองรับ FormData (File Upload)
        // ถ้าไม่รองรับ ให้ใช้ Standard JS แบบนี้:
        
        var form = document.getElementById('formHelpDesk');
        var formData = new FormData(form);
        var xhr = new XMLHttpRequest();
        
        xhr.open('POST', '/saveHelpDesk?${_csrf.parameterName}=${_csrf.token}', true);
        
        xhr.onload = function () {
            if (xhr.status === 200) {
                // Success: Reload หน้า List
                closeed2('draggable-window3');
                document.getElementById("main${item_id}").innerHTML = xhr.responseText;
            } else {
                alert('Error Saving Data');
            }
        };
        
        xhr.send(formData);
    }
    
 // ฟังก์ชันลบไฟล์
    function deleteOldFile(fileId) {
        if(!confirm('คุณต้องการลบไฟล์นี้ใช่หรือไม่?')) {
            return;
        }

        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/deleteFileHelpDesk', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        // ส่ง CSRF Token ไปด้วย (ถ้า Spring Security บังคับ)
        xhr.setRequestHeader('X-CSRF-TOKEN', '${_csrf.token}'); 

        xhr.onload = function() {
            if (xhr.status === 200 && xhr.responseText === 'success') {
                // ลบสำเร็จ -> ซ่อนบรรทัดนั้นหายไปเลย ไม่ต้อง Refresh หน้า
                var row = document.getElementById('file-row-' + fileId);
                if(row) {
                    row.style.display = 'none';
                }
            } else {
                alert('เกิดข้อผิดพลาดในการลบไฟล์');
            }
        };
        
        xhr.send('file_id=' + fileId);
    }
</script>

</body>
</html>