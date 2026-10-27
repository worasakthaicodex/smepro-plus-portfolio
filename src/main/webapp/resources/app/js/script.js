//For the demo only
setTimeout(function(){
   //document.getElementById('downloads').classList.add('demo')
}, 1000)
document.body.addEventListener('click', function(){  
//document.getElementById('downloads').classList.remove('demo')
});



function showAddressTitle(element,type,formID) {
    // หา parent <tr> ที่อยู่ใกล้ที่สุดของปุ่มที่กด
    
    
      let row = element.closest("tr");
   
     if (type == "customer") {
	
		        // ค้นหา <td> ที่มี data-custom="address"
		        let codeTd = row.querySelector('td[data-custom="code"]').getAttribute("title");
		        let nameTd = row.querySelector('td[data-custom="name"]').getAttribute("title");
		        let typeTd = row.querySelector('td[data-custom="type"]').getAttribute("title");
		        let groupsTd = row.querySelector('td[data-custom="groups"]').getAttribute("title");
		        let addressTd = row.querySelector('td[data-custom="address"]').getAttribute("title");
		        let noteTd = row.querySelector('td[data-custom="note"]').getAttribute("title");
		      
		        fillPossibleFields(['customer_code','skg_supplier_code'], codeTd,formID);
		        fillPossibleFields(['customer_name','skg_supplier_name'], nameTd,formID);
		      
	
		    
		   }  else  if  (type == "product") {
			
	        let codeTd = row.querySelector('td[data-custom="product_code"]')?.getAttribute("title");
	        let nameTd = row.querySelector('td[data-custom="product_name"]')?.getAttribute("title");
	        let product_categoryTd = row.querySelector('td[data-custom="product_category"]')?.getAttribute("title");
	
	        fillPossibleFields(['product_code','bom_product_code','goods_receipt_product_code'], codeTd,formID);
	        fillPossibleFields(['product_name','bom_product_name','goods_receipt_product_name'], nameTd,formID);
	        fillPossibleFields(['product_category'], product_categoryTd,formID);
        
  		    }  else  if  (type == "fg") {
    	
    	
        let codeTd = row.querySelector('td[data-custom="code"]')?.getAttribute("title");
       
        let product_codeTd = row.querySelector('td[data-custom="product_code"]')?.getAttribute("title");
        let product_nameTd = row.querySelector('td[data-custom="product_name"]')?.getAttribute("title");
        
        let customer_codeTd = row.querySelector('td[data-custom="customer_code"]')?.getAttribute("title");
        let customer_nameTd = row.querySelector('td[data-custom="customer_name"]')?.getAttribute("title");
        let issued_unitTd = row.querySelector('td[data-custom="unit"]')?.getAttribute("title");
        let quantityTd = row.querySelector('td[data-custom="quantity"]')?.getAttribute("title");
        
        
        fillPossibleFields(['issued_lot_batch', 'transfer_lot_batch', 'adjustment_lot_batch', 'lot_batch'], codeTd,formID);
        fillPossibleFields(['issued_product_code', 'transfer_product_code', 'adjustment_product_code', 'product_code'], product_codeTd,formID);
        fillPossibleFields(['issued_product_name', 'transfer_product_name', 'adjustment_product_name', 'product_name'], product_nameTd,formID);
        fillPossibleFields(['issued_customer_code', 'transfer_customer_code', 'adjustment_customer_code'], customer_codeTd,formID);
        fillPossibleFields(['issued_customer_name', 'transfer_customer_name', 'adjustment_customer_name'], customer_nameTd,formID);
        fillPossibleFields(['issued_unit', 'transfer_unit','adjustment_unit','unit'], issued_unitTd,formID);
        fillPossibleFields(['issued_quantity'], quantityTd,formID);


	 }  else  if  (type == "sk") {
    	   
    	
        let codeTd = row.querySelector('td[data-custom="code"]')?.getAttribute("title");
        let stock_codeTd = row.querySelector('td[data-custom="stock_code"]')?.getAttribute("title");
        let stock_nameTd = row.querySelector('td[data-custom="stock_name"]')?.getAttribute("title");
        let supplier_codeTd = row.querySelector('td[data-custom="supplier_code"]')?.getAttribute("title");
        let supplier_nameTd = row.querySelector('td[data-custom="supplier_name"]')?.getAttribute("title");
        let unitTd = row.querySelector('td[data-custom="unit"]')?.getAttribute("title");
        let quantityTd = row.querySelector('td[data-custom="quantity"]')?.getAttribute("title");
        let priceTd = row.querySelector('td[data-custom="price"]')?.getAttribute("title");
        let tracking_numberTd = row.querySelector('td[data-custom="tracking_number"]')?.getAttribute("title");
        let stock_storageTd = row.querySelector('td[data-custom="stock_storage"]')?.getAttribute("title");
        
        
        fillPossibleFields(['skg_lot_batch','ski_lot_batch','ska_lot_batch','skt_lot_batch'], codeTd,formID);
        fillPossibleFields(['ski_lot_batch2'], codeTd,formID);
        fillPossibleFields(['skg_stock_code','ski_stock_code','ska_stock_code','skt_stock_code'], stock_codeTd,formID);
        fillPossibleFields(['skg_stock_name','ski_stock_name','ska_stock_name','skt_stock_name','bom_name'], stock_nameTd,formID);
        fillPossibleFields(['skt_quantity','ski_quantity'], quantityTd,formID);
        fillPossibleFields(['skg_supplier_code', 'ski_supplier_code', 'ska_supplier_code', 'skt_supplier_code'], supplier_codeTd,formID);
        fillPossibleFields(['skg_supplier_name', 'ski_supplier_name', 'ska_supplier_name', 'skt_supplier_name'], supplier_nameTd,formID);
        fillPossibleFields(['skg_unit', 'ski_unit', 'ska_unit','bom_base_unit'], unitTd,formID);
        fillPossibleFields(['tracking_number','ski_tracking_number'], tracking_numberTd,formID);
        fillPossibleFields(['bom_price'], priceTd,formID);
        fillPossibleFields(['skt_from_location'], stock_storageTd,formID);
        }
		    
   
}

function fillPossibleFields(possibleIds, value, formID) {
    var form = document.getElementById(formID);
    if (!form) return; // ถ้าไม่เจอฟอร์ม ก็หยุดเลย

    for (var i = 0; i < possibleIds.length; i++) {
        var id = possibleIds[i];
        var field = form.querySelector('#' + id); // ค้นหาเฉพาะในฟอร์ม

        if (field !== null) {
            field.value = value;
            break;
        }
    }
}


  function expandFileList(head){
		  
	document.querySelectorAll('.folder-header').forEach(folder => {
        folder.classList.remove('active');
    });
    
    
		  head.classList.toggle('active');
		  
		  
		  
	  const content = head.nextElementSibling;
	  if (content.style.display === 'block') {
	      content.style.display = 'none';
	  } else {
	      content.style.display = 'block';
	  }
}





// รับ buttonId เพิ่มเข้ามา
function filterMaster(input, listId, hiddenId, dataSet, buttonId) {
    var filter = input.value.toUpperCase();
    var ul = document.getElementById(listId);
    
    ul.innerHTML = "";
    ul.style.display = 'block';

    var matches = dataSet.filter(function(item) {
        if (filter === "") return true;
        return item.txt && item.txt.toUpperCase().indexOf(filter) > -1;
    });

    var showData = matches.slice(0, 50);

    if (showData.length > 0) {
        var liClear = document.createElement("li");
        liClear.innerText = "-";
        // ส่ง buttonId ไปต่อ
        liClear.onclick = function() { selectMaster(this, input.id, hiddenId, '', '-', buttonId); };
        ul.appendChild(liClear);

        showData.forEach(function(item) {
            var li = document.createElement("li");
            li.innerText = item.txt;
            // ส่ง buttonId ไปต่อ
            li.onclick = function() { 
                selectMaster(this, input.id, hiddenId, item.val1, item.txt, buttonId); 
            };
            ul.appendChild(li);
        });
    } else {
        var li = document.createElement("li");
        li.innerText = "ไม่พบข้อมูล";
        li.style.color = "#999";
        ul.appendChild(li);
    }
}

// ฟังก์ชันเลือกค่า (เพิ่ม Logic อัปเดตปุ่ม)
function selectMaster(element, inputId, hiddenId, realValue, showText, buttonId) {
    if(element.innerText === "ไม่พบข้อมูล") return;

    var inputDisplay = document.getElementById(inputId);
    var inputHidden = document.getElementById(hiddenId);

    // 1. อัปเดตค่า Input
    inputDisplay.value = (showText === '-') ? '' : showText;
    inputHidden.value = (realValue === '') ? '' : realValue;

    // 2. *** ส่วนที่เพิ่มมา: อัปเดต Link ปุ่ม ***
    if (buttonId && realValue) {
        var btnSpan = document.getElementById(buttonId);
        if (btnSpan) {
            var link = btnSpan.querySelector('a'); // หา tag <a> ข้างใน
            if (link) {
                // เขียนคำสั่ง onclick ใหม่ โดยเอา realValue (ID ใหม่) ยัดเข้าไป
                var newOnclick = "shows2('draggable-window3','จัดการชุดข้อมูล'); getData('/vieweditstaticsnapshot-filemanager/" + realValue + "', 'mainbody3');";
                link.setAttribute('onclick', newOnclick);
            }
        }
    }

    element.parentNode.style.display = 'none';
}
function validateMaster(input, hiddenId, dataSet) {
    setTimeout(function() {
        var listId = input.getAttribute('onkeyup').split("'")[1];
        var ul = document.getElementById(listId);
        var inputHidden = document.getElementById(hiddenId);

        // หาไอเทมที่ตรงกับค่าที่แสดงในช่อง Input
        var foundItem = dataSet.find(x => x.txt === input.value);
        
        if (!foundItem && input.value !== "" && input.value !== "-") {
            input.value = "";
            inputHidden.value = "";
        } else if (foundItem) {
            // *** แก้ตรงนี้: เมื่อตรวจสอบผ่าน ให้ยัดค่า val1 เข้า hidden ***
            inputHidden.value = foundItem.val1; 
        } else if (input.value === "") {
             inputHidden.value = "";
        }

        ul.style.display = 'none';
    }, 200);
}
// Event ปิดเมื่อคลิกข้างนอก
document.addEventListener('click', function(e) {
    if (!e.target.closest('.search-select-box')) {
        document.querySelectorAll('.search-results').forEach(l => l.style.display='none');
    }
});


