<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



  <div id="body" class="mainbody2"  style="background-color:#eee;">
     
<style>
.file-explorer {
    font-family: Arial, sans-serif;
    width: 100%;
}

.folder {
    margin: 1px 0;
}

.folder-header {
 
    padding: 5px;
    cursor: pointer;
    position: relative;
}

.folder-header:before {
    content: '▶';
    margin-right: 5px;
    transition: transform 0.3s;
}

.folder-header.active:before {
    content: '▼';
}

.folder .content {
    display: none;
    padding-left: 20px;
}

.folder-header.active + .content {
    display: block;
}

.file {
    padding: 5px;
    background-color: #fff;
        cursor: pointer;
}

.file:hover {
    background-color: #f9f9f9;
}

.folder-header:hover {
    background-color: #e0e0e0;
}

.folder-header.active {
    background-color: #0078d4;
    color: white;
}
</style>
<form action="#" name="myFormssetting"  method="POST" id="myFormssetting" >
 <div class="middle" style="padding:10px">
<label for="first-input" style="font-size:12px">ระบุไฟล์ที่ต้องการค้นหา</label>
<input type="text" name="dataset_data_title"  value="${dataset_datas.dataset_data_title }" style="width:100%"  required>
<br><br>
      <div class="display">
     <div class="file-explorer">
     
        <c:forEach items="${relationList}" var="row">  
        <div class="folder">
            <div class="folder-header"  onclick="expandFileList(this);">${row.doc} ${row.data_title}</div>
            <div class="content">
                <div class="file"><input type="radio" name="link" class="relation_id" data-custom="${row.doc} ${row.data_title}"  value="${row.data_id}"> ทั้งเอกสาร</div>
                
                
            </div>
        </div>
        </c:forEach>
        
        
                 <c:forEach items="${doc}" var="rows">  
               		 <div class="file"><input type="radio" name="link" value="${rows.doc_id }"> ${rows.doc }</div>
                </c:forEach>
    </div>
  </div>
    </div>
</form>
  </div>
<div class="footer">
<button id="new"  class="ripple" onclick="selectRelation('relation_id','join-${app_processes_id}','relation-${app_processes_id}');closeed2('draggable-window6')">ตกลง</button>
</div>

<script>
  function selectRelation(radioClassName, displayInputId, hiddenInputId) {
    // 1. ค้นหา element input radio ที่มี class ชื่อนี้ และถูกติ๊กเลือกอยู่ (:checked)
    // ใส่จุด (.) ข้างหน้าเพื่อระบุว่าเป็น Class
    var selectedRadio = document.querySelector('.' + radioClassName + ':checked');

    if (selectedRadio) {
        // 2. ดึงค่าจาก attribute
        var idValue = selectedRadio.value; // ค่าจาก value="${row.id}"
        var customText = selectedRadio.getAttribute('data-custom'); // ค่าจาก data-custom="..."

        // 3. นำค่าไปใส่ใน Input ปลายทาง (ตาม ID ที่ส่งมา)
        
        // ใส่ข้อความโชว์ในช่อง Text (join-...)
        var displayInput = document.getElementById(displayInputId);
        if (displayInput) {
            displayInput.value = customText;
        }

        // ใส่ ID ลงในช่อง Hidden (relation-...)
        var hiddenInput = document.getElementById(hiddenInputId);
        if (hiddenInput) {
            hiddenInput.value = idValue;
        }
        
        // (Optional) สั่งปิด Modal หรือทำอย่างอื่นต่อตรงนี้
        console.log("บันทึกข้อมูลเรียบร้อย: " + customText + " (" + idValue + ")");

    } else {
        // กรณีที่ยังไม่ได้เลือก Radio
        alert("กรุณาเลือกรายการก่อนกดตกลง");
    }
}
  </script>
        