<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 

<script>



   var formData = {
	        // --- ส่วน ID และ Code ---
	        register_id: `${item.register_id}`,
	        customerCode: `${item.register_code}${item.register_daterun}-${item.register_id}`,
	        
	        // --- ข้อมูลอื่นๆ (ใช้ Backtick ทุกอัน กันเหนียว) ---
	        register_name: `${item.register_name}`,
	        register_type: `${item.register_type}`,
	        register_groups: `${item.register_groups}`,
	        register_address: `${item.register_address}`,
	        register_note: `${item.register_note}`,
	        register_poin: `${item.register_poin}`,
	        contact_person: `${item.contact_person}`,
	        phone_number: `${item.phone_number}`,
	        mobile_number: `${item.mobile_number}`,
	        website: `${item.website}`,
	        social: `${item.social}`,
	        tax_id: `${item.tax_id}`,
	        branch_code: `${item.branch_code}`,
	        business_type: `${item.business_type}`,
	        payment_terms: `${item.payment_terms}`,
	        credit_limit: `${item.credit_limit}`,
	        bank_account: `${item.bank_account}`,
	        preferred_currency: `${item.preferred_currency}`
	    };
  	populateMyForm(formData);
    
    
    function populateMyForm(data) {
    	
  
    	
        var myForm = document.getElementById('myForms${edata_from.processes_id}')

     
        
        // 1. ถ้าหาฟอร์มไม่เจอ จบงานทันที (กันตายด่านแรก)
        if (!myForm) return;

        Object.keys(data).forEach(function(key) {
            var value = data[key];

            // 2. ลองหาแบบ ID ก่อน (สำหรับ Input ทั่วไป)
            var el = myForm.querySelector('#' + key);

            if (el) {
                // --- กรณีเจอ ID (พวก Text, Select, Textarea) ---
                if (el.type !== 'radio' && el.type !== 'checkbox') {
                    el.value = (value != null) ? value : "";
                }
            } else {
                // --- กรณีไม่เจอ ID (อาจเป็น Radio หรือ ไม่มีเลย) ---
                
                // หา Radio ที่มี name ตรงกับ key
                var radios = myForm.querySelectorAll('input[name="' + key + '"]');

                // 3. เช็คว่าเจอ Radio ไหม? (ถ้า radios.length เป็น 0 คือไม่มี -> ข้ามเลย ไม่ตาย)
                if (radios.length > 0) {
                    for (var i = 0; i < radios.length; i++) {
                        if (radios[i].value == value) {
                            radios[i].checked = true;
                            break; // เจอแล้วติ๊ก แล้วหยุดวนลูป
                        }
                    }
                }
                // ถ้าไม่เจอทั้ง ID และ Radio ก็จะเงียบไปเอง ไม่ Error
            }
        });
        
        closeed('draggable-windows','${process.id}');
    }
  
</script>