<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 

<script>



   var formData = {
	        // --- ส่วน ID และ Code ---
	        product_id: `${item.product_id}`,
	        productCode: `${item.product_code}${item.product_daterun}-${item.product_id}`,
	        
	        // --- ข้อมูลอื่นๆ (ใช้ Backtick ทุกอัน กันเหนียว) ---
	        product_names: `${item.product_names}`,
	        product_category: `${item.product_category}`,
	        product_details: `${item.product_details}`,
	        unit: `${item.unit}`,
	        product_status: `${item.product_status}`,
	        product_type: `${item.product_type}`,
	        product_dimensions: `${item.product_dimensions}`,
	        product_expiration_date: `${item.product_expiration_date}`,
	        product_selling_price: `${item.product_selling_price}`,
	        product_cost_price: `${item.product_cost_price}`,
	        product_wholesale_price: `${item.product_wholesale_price}`,
	      
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