<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
 
<div style= "height:<c:if test="${join > 0}">150px;</c:if><c:if test="${join < 1}">80px;</c:if> padding:10px; width:100%">

<form id="newfile" name="newfile" style="padding:20px">
   <div style="width:100%;">
        <span style="display:inline-block; min-width:150px">ชื่อไฟล์</span>
        <input type="text" id="doc_title"class="form-control newinput" name="name_file" id="doc_title" value="" style="    width: 450px;">
               <button  type="button" class="ripple" style="padding:4px"
      onclick="generateAutoName()"
        
        >สร้างออโต้</button> 
        
        
        <div class="quick-tags" style="margin-top: 5px;display:none">
     <span style="display:inline-block; min-width:150px">เลือกชุดคำ:</span>
    <button type="button" onclick="addTag('งานด่วน')">🔥 งานด่วน</button>
    <button type="button" onclick="addTag('ขออนุมัติ')">📝 ขออนุมัติ</button>
    <button type="button" onclick="addTag('รายงาน ' + getToday())">📅 รายงานวันนี้</button>
    <button type="button" ></button>
</div>
        
        
        <input type="hidden" name="app_processes_id" value="${process.id}">
<input type="hidden" name="edata.data_eformid" value="${conditions.condition_id+0}">       
<input type="hidden" name="edata.data_eformrv" value="${conditions.condition_rv+0}"> 
    </div>     <br>
    
    
    
    <c:if test="${join > 0}">
    
    <div style="width:100%">
        <span style="display:inline-block; min-width:150px">ไฟล์เชื่อมโยง</span>
        <input type="text" class="form-control newinput" value="${refer_title}" id="join-${process.id}"  style="    width: 450px;" required readonly>
      <input type="hidden" id="relation-${process.id}"   name="relation_id"  value="${id_refer}" >
        
        
        <button  type="button" class="ripple" style="padding:4px"
         onclick=" shows2('draggable-window6','Choose Files');getData('/Chooselinkapp-filemanager/${process.id}/${join}', 'mainbody6')"
        
        >เลือก.......</button> 
    </div> 
    <br>
  
        </c:if>
        
           <c:if test="${hasIndex > 0}"> 
           <div style="width:100%">
        <span style="display:inline-block; min-width:200px">ระบุประเภทการดำเนินการ</span>
 

		<select class="form-control newinput" name="edata.edata_index_action_type" id="edata_index_action_type" style="    width: 450px;" >
			<option value="0" data-custom="เพิ่ม">เพิ่ม</option>
			<option value="1" data-custom="เเก้ไข">เเก้ไข</option>
				<option value="2" data-custom="เปลี่ยนสถานะ">เปลี่ยนสถานะ</option>
			<option value="3" data-custom="ลบ">ลบ</option>
		
		</select>        
        
     
    </div> 
    <br>
  
        </c:if>
        
        
 </form>   
    
</div>					 
<div class="footer">
<button id="new"  class="ripple" onclick="postData('/newfile-filemanager?${_csrf.parameterName}=${_csrf.token}', 'newfile','microsofts${process.id}');closeed2('draggable-window3')">ตกลง</button>
</div>



<script>

// ฟังก์ชันเติมคำ
function addTag(text) {
    var input = document.getElementById('doc_title');
    // ถ้ามีข้อความอยู่แล้ว ให้เว้นวรรคแล้วต่อด้วยคำใหม่
    if(input.value.trim() !== "") {
        input.value += " - " + text;
    } else {
        input.value = text;
    }
    input.focus(); // ให้ cursor ไปกระพริบรอพิมพ์ต่อ
}

// ฟังก์ชันดึงวันที่สั้นๆ
function getToday() {
    var d = new Date();
    // format: 30/12
    return d.getDate() + "/" + (d.getMonth() + 1);
}

function generateAutoName() {
    // 1. ลองหา Element ดูก่อน
    var select = document.getElementById("edata_index_action_type");
    var typePrefix = "";

    // 2. เช็คว่ามี Select ไหม? และมีการเลือก Option ไหม?
    if (select && select.selectedIndex >= 0) {
        var selectedOption = select.options[select.selectedIndex];
        
        // ถ้ามี data-custom ให้ใช้, ถ้าไม่มีให้ใช้ "ดำเนินการ"
        var customType = selectedOption.dataset.custom ? selectedOption.dataset.custom : "ดำเนินการ";
        
        // จัดรูปแบบ: "ดำเนินการ_" (มี Underscore ต่อท้าย)
        typePrefix = customType + "_";
    }

    // 3. หาวันเวลาปัจจุบัน
    var now = new Date();
    var time = String(now.getHours()).padStart(2, '0') + "." + String(now.getMinutes()).padStart(2, '0');

    // 4. รวมร่าง (ถ้าไม่มี Select ตัว typePrefix จะเป็นค่าว่าง "" ก็จะเหลือแค่ Undefined_...)
    var finalName = typePrefix + "Undefined_(" + time + ")";

    // 5. ใส่ค่าลงไป (เช็คก่อนว่ามีช่อง doc_title ไหม กัน Error)
    var titleInput = document.getElementById('doc_title');
    if (titleInput) {
        titleInput.value = finalName;
    }
}
</script>