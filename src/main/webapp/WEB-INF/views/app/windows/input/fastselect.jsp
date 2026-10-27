<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>     




	   <c:set var="linkId" value="${edatas[key]}" />  
	  <c:if test="${transaction[row.keymap.mainkey] != null}">
	   <c:set var="linkId" value="${transaction[row.keymap.mainkey]}" />  
	  </c:if>
	  
	  
<c:if test="${row.keymap.mainkey == 'customerCode' }">

	




<div class="search-select-box" id="box-${row.keymap.mainkey}" style="display: flex;align-items: center;gap: 5px;">

    <input type="hidden" 
           id="hidden-${process.id}-${row.keymap.mainkey}" 
           name="edata.${key}" 
           value="${linkId}">

    <select id="input-${process.id}-${row.keymap.mainkey}" 
            placeholder="พิมพ์เพื่อค้นหาลูกค้า..." 
            autocomplete="off"
            ${readonly}>
    </select>
  
 
 
    <c:if test="${linkId != null}">
		        <span class="input-group-text cursor-pointer" style="cursor: pointer;"
             		   onclick="openAppAndLoadData('${linkId}', '${appId}','${edatas.data_id}');
             getData('/profileapp-filemanager/${linkId}/${appId}?from=${edatas.data_id}', 'microsofts${appId}');">
		        <a target="_blank" > 
		         <img  src="/app/system/icon/find next.ico" />
		        </a>
		            </span> 
 </c:if>
 
    <div id="statussave${edatas[key]}"></div>
</div>

<script>
    // 🌟 เริ่มการทำงานของ TomSelect 🌟
    var tomSelectInstance = new TomSelect("#input-${process.id}-${row.keymap.mainkey}", {
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
            formData.append('type', 'Profile'); 
            
            var targetUrl = '/indexappsearchajax-filemanage?${_csrf.parameterName}=${_csrf.token}';

            fetch(targetUrl, {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(json => {
                if(!json) json = [];
                
                // 🔥 แปลงร่าง JSON ให้กลับไปเป็นรูปแบบเดิม (รหัส|ชื่อ และ รหัส ชื่อ)
                var formattedJson = json.map(function(item) {
                    return {
                        value: item.optVal1 + '|' + item.optVal2,
                        text: item.optVal1 + ' ' + item.optVal2
                    };
                });
                
                callback(formattedJson);
            }).catch(()=>{
                callback();
            });
        },
        
        onChange: function(value) {
            var inputHidden = document.getElementById("hidden-${process.id}-${row.keymap.mainkey}");
            var inputShow2 = document.getElementById("${process.id}-customer_name");

            if (!value) {
                if (inputHidden) inputHidden.value = ""; 
                if (inputShow2) inputShow2.value = ""; 
                return;
            }
            
            try {
                // แกะค่า รหัส|ชื่อ เหมือนเดิมเป๊ะ!
                var splitData = value.split('|');
                var val1 = splitData[0]; // รหัส
                var val2 = splitData[1]; // ชื่อ
                
                if (inputHidden) inputHidden.value = val1.trim();
                if (inputShow2) inputShow2.value = (val2 ? val2.trim() : "");
                
            } catch (e) {
                console.error("TomSelect error:", e);
            }
        }
    });

    // ==============================================================
    // 🌟 โหลดข้อมูลชื่อลูกค้าตอนเปิดหน้าเว็บ (ยิง Ajax ไปค้นจากรหัส linkId)
    // ==============================================================
    var initialId = "${linkId}";
    if (initialId && initialId.trim() !== "") {
        var formData = new FormData();
        formData.append('search', initialId); // เอา linkId ไปค้นหาเลย
        formData.append('type', 'Profile');
        
        fetch('/indexappsearchajax-filemanage?${_csrf.parameterName}=${_csrf.token}', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
  		 .then(json => {
            if (json && json.length > 0) {
                
                // 🔥 1. วิ่งหาตัวที่ "รหัสตรงกันเป๊ะๆ" ก่อน
                var exactMatchItem = json.find(function(data) {
                    return data.optVal1 === initialId; // initialId คือรหัสที่เราต้องการ
                });

                // 🔥 2. ถ้าหาตัวเป๊ะๆ เจอ ค่อยเอามาใช้งาน (ถ้าไม่เจออาจจะยอมหยิบตัวแรก หรือข้ามไปเลย)
                var item = exactMatchItem ? exactMatchItem : json[0];

                if (item) {
                    var exactValue = item.optVal1 + '|' + item.optVal2;
                    var exactText = item.optVal1 + ' ' + item.optVal2;
                    
                    // เอาประกอบใส่ Select และสั่งเลือก
                    tomSelectInstance.addOption({ value: exactValue, text: exactText });
                    tomSelectInstance.setValue(exactValue, true); // true = เงียบๆ ไม่ต้อง trigger onChange ซ้ำ
                    
                    // เอาชื่อบริษัทยัดใส่ inputShow2
                    var inputShow2 = document.getElementById("${process.id}-customer_name");
                    if (inputShow2) {
                        inputShow2.value = item.optVal2.trim();
                    }
                }
            }
        })
        .catch(e => console.error("โหลดข้อมูลตั้งต้นไม่สำเร็จ:", e));
    }
</script>
</c:if>


<c:if test="${ row.keymap.mainkey == 'productCode' }">
 <c:set var="linkId" value="${edatas[key]}" />  	
    <div class="search-select-box" id="box-${row.keymap.mainkey}" style="display: flex;align-items: center;gap: 5px;">

    <input type="hidden" 
           id="hidden-${process.id}-${row.keymap.mainkey}" 
           name="edata.${key}" 
           value="${edatas[key]}">
           
    <select class="${tiftext}"
            id="input-${process.id}-${row.keymap.mainkey}" 
            placeholder="พิมพ์เพื่อค้นหาสินค้า..." 
            autocomplete="off"
            ${readonly} <c:if test="${readonly != ''}">disabled</c:if>>
    </select>
    
</div>  

<script>
    // 🌟 เริ่มการทำงานของ TomSelect แบบ AJAX (สินค้า) 🌟
    var tomSelectProduct = new TomSelect("#input-${process.id}-${row.keymap.mainkey}", {
        valueField: 'value', 
        labelField: 'text',  
        searchField: 'text',
        create: false,          
        maxOptions: 50,         
        allowEmptyOption: true,
        
        // 🚀 ดึงข้อมูลผ่าน AJAX ตอนพิมพ์
        load: function(query, callback) {
            if (!query.length) return callback();

            var formData = new FormData();
            formData.append('search', query);
            formData.append('type', 'Product'); // 🌟 เปลี่ยน Type เป็น Product
            
            var targetUrl = '/indexappsearchajax-filemanage?${_csrf.parameterName}=${_csrf.token}';

            fetch(targetUrl, {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(json => {
                if(!json) json = [];
                
                // แปลงร่าง JSON ให้อยู่ในรูป รหัส|ชื่อ เหมือนเดิม
                var formattedJson = json.map(function(item) {
                    return {
                        value: item.optVal1 + '|' + item.optVal2,
                        text: item.optVal1 + ' ' + item.optVal2
                    };
                });
                
                callback(formattedJson);
            }).catch(()=>{
                callback();
            });
        },
        
        // เมื่อผู้ใช้เลือกสินค้า
        onChange: function(value) {
            var inputHidden = document.getElementById("hidden-${process.id}-${row.keymap.mainkey}");
            var inputShow2 = document.getElementById("${process.id}-product_names"); // 🌟 เป้าหมายเป็นชื่อสินค้า

            if (!value) {
                if (inputHidden) inputHidden.value = ""; 
                if (inputShow2) inputShow2.value = ""; 
                return;
            }
            
            try {
                // แกะค่า รหัส|ชื่อ
                var splitData = value.split('|');
                var val1 = splitData[0]; // รหัส
                var val2 = splitData[1]; // ชื่อ
                
                if (inputHidden) inputHidden.value = val1.trim();
                if (inputShow2) inputShow2.value = (val2 ? val2.trim() : "");
                
            } catch (e) {
                console.error("TomSelect parsing error:", e);
            }
        }
    });

    // ==============================================================
    // 🌟 โหลดข้อมูลชื่อสินค้าตอนเปิดหน้าเว็บ (ยิง Ajax ไปค้นจากรหัส)
    // ==============================================================
    var initialProdId = "${edatas[key]}"; // ดึงรหัสสินค้าที่มีอยู่แล้ว
    
    if (initialProdId && initialProdId.trim() !== "") {
        var formData = new FormData();
        formData.append('search', initialProdId); 
        formData.append('type', 'Product'); // 🌟 ค้นหาด้วยโหมด Product
        
        fetch('/indexappsearchajax-filemanage?${_csrf.parameterName}=${_csrf.token}', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
   .then(json => {
            if (json && json.length > 0) {
                
                // 🔥 1. วิ่งหาตัวที่ "รหัสสินค้า" ตรงกันเป๊ะๆ ก่อน
                var exactMatchItem = json.find(function(data) {
                    return data.optVal1 === initialProdId; // initialProdId คือรหัสสินค้าตั้งต้น
                });

                // 🔥 2. ถ้าหาตัวเป๊ะๆ เจอเอาตัวนั้น ถ้าไม่เจอถึงค่อยยอมเอาตัวแรก json[0]
                var item = exactMatchItem ? exactMatchItem : json[0];

                if (item) {
                    var exactValue = item.optVal1 + '|' + item.optVal2;
                    var exactText = item.optVal1 + ' ' + item.optVal2;
                    
                    // เอาประกอบใส่ Select และสั่งเลือก
                    tomSelectProduct.addOption({ value: exactValue, text: exactText });
                    tomSelectProduct.setValue(exactValue, true); // true = ไม่ต้องกระตุ้น onChange ซ้ำ
                    
                    // เอาชื่อสินค้ายัดใส่ inputShow2 (เผื่อไว้ให้มันตรงกัน)
                    var inputShow2 = document.getElementById("${process.id}-product_names");
                    if (inputShow2) {
                        inputShow2.value = item.optVal2.trim();
                    }
                }
            }
        })
        .catch(e => console.error("โหลดข้อมูลสินค้าตั้งต้นไม่สำเร็จ:", e));
    }
</script>
</c:if>
