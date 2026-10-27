<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 

<script>


//ประกาศตัวแปรค่าว่างรอไว้
var formData = {
    // --- ส่วน ID และ Code ---
    register_id: ``,
    customerCode: ``,
    
    // --- ข้อมูลอื่นๆ (ค่าว่างทั้งหมด) ---
    register_name: ``,
    register_type: ``,
    register_groups: ``,
    register_address: ``,
    register_note: ``,
    register_poin: ``,
    contact_person: ``,
    phone_number: ``,
    mobile_number: ``,
    website: ``,
    social: ``,
    tax_id: ``,
    branch_code: ``,
    business_type: ``,
    payment_terms: ``,
    credit_limit: ``,
    bank_account: ``,
    preferred_currency: ``
};

// เรียกฟังก์ชันล้างค่า
populateMyForm(formData);

function populateMyForm(data) {

    // ระวัง: ตรงนี้ต้องมั่นใจว่าตัวแปร JSP ${edata_from.processes_id} มีค่าในจุดที่ Script นี้ทำงาน
    var myForm = document.getElementById('myForms${edata_from.processes_id}');

    if (!myForm) return;
    Object.keys(data).forEach(function(key) {
        var value = data[key];

        // 1. ลองหาแบบ ID
        var el = myForm.querySelector('#' + key);

        if (el) {
            if (el.type !== 'radio' && el.type !== 'checkbox') {
                el.value = (value != null) ? value : "";
            }
        } else {
            // 2. กรณี Radio Button
            var radios = myForm.querySelectorAll('input[name="' + key + '"]');
            
            if (radios.length > 0) {
                // *** แก้ตรงนี้: วนลูปเช็คทุกตัว ***
                // ถ้าค่าตรง -> ติ๊กถูก (Checked)
                // ถ้าค่าไม่ตรง (เช่นเป็นค่าว่าง) -> เอาติ๊กออก (Unchecked)
                radios.forEach(function(r) {
                    r.checked = (r.value == value); 
                });
            }
        }
    });

    closeed('draggable-windows','${process.id}');
}
  
</script>