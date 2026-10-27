<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../common/mainhead.jspf" %>    


<c:if test="${subdata.datalink_name == 'stock_requisition'  }">  
<c:set var="typeshow" value="5" />
  <%@ include file="stock_requisition.jspf" %>                                   
</c:if>
<c:if test="${subdata.datalink_name == 'petty_cash_book'  }">  
<c:set var="typeshow" value="5" />
  <%@ include file="petty_cash_book.jspf" %>                                   
</c:if>


<c:if test="${delitem != null  }">  

<script> 
$("#item-${delitem}").closest("tr").remove();
</script>  

</c:if>


<c:if test="${type == 'edata_procurement' }"> 
<script> 
try{
	

	var id ="${item.id}";
	var tempId="${item.tempId}";
	var procurement_itemid ="${item.procurement_itemid}";
	var procurement_itemname ="${item.procurement_itemname}";
	var procurement_quantityrequested ="${item.procurement_quantityrequested}";
	var procurement_estimatedprice ="${item.procurement_estimatedprice}";
	var procurement_unit ="${item.procurement_unit}";
	var procurement_requesteddate ="${item.procurement_requesteddate}";
	var procurement_quantity_ordered ="${item.procurement_quantity_ordered}";
	var procurement_unit_price ="${item.procurement_unit_price}";
	var quantity_received ="${item.quantity_received}";
	
	
	
	$("form#formgr #id").val(id);
	$("form#formgr #tempId").val(tempId);
	$("form#formgr #procurement_itemid").val(procurement_itemid);
	$("form#formgr #display_stock_stockid").val(procurement_itemid);
	$("form#formgr #quantity_received").val(quantity_received);
	
	$("form#formgr #procurement_quantity_ordered").val(procurement_quantity_ordered);
	$("form#formgr #procurement_unit_price").val(procurement_unit_price);
	$("form#formgr #procurement_itemname").val(procurement_itemname);
	$("form#formgr #procurement_quantityrequested").val(procurement_quantityrequested);
	$("form#formgr #procurement_estimatedprice").val(procurement_estimatedprice);
	$("form#formgr #procurement_unit").val(procurement_unit);
	$("form#formgr #procurement_requesteddate").val(procurement_requesteddate);

	
}catch (error) {}




try{
	

	var id ="${item.id}";
	var tempId="${item.tempId}";
	var procurement_itemid ="${item.procurement_itemid}";
	var procurement_itemname ="${item.procurement_itemname}";
	var procurement_quantityrequested ="${item.procurement_quantityrequested}";
	var procurement_estimatedprice ="${item.procurement_estimatedprice}";
	var procurement_unit ="${item.procurement_unit}";
	var procurement_requesteddate ="${item.procurement_requesteddate}";
	
	var procurement_itemid ="${item.procurement_itemid}";
	var procurement_quantity_ordered ="${item.procurement_quantity_ordered}";
	var procurement_unit_price ="${item.procurement_unit_price}";
	var procurement_payment_status ="${item.procurement_payment_status}";
	var procurement_delivery_date ="${item.procurement_delivery_date}";
	var procurement_supplier_name ="${item.procurement_supplier_name}";
	var procurement_supplier_address ="${item.procurement_supplier_address}";
	var procurement_supplier_id ="${item.procurement_supplier_id}";
	var quantity_received ="${item.quantity_received}";
	

	
	$("form#formgrc #id").val(id);
	$("form#formgrc #tempId").val(tempId);
	$("form#formgrc #procurement_itemid").val(procurement_itemid);
	$("form#formgrc #display_stock_stockid").val(procurement_itemid);
	$("form#formgrc #procurement_itemname").val(procurement_itemname);
	$("form#formgrc #procurement_quantityrequested").val(procurement_quantityrequested);
	$("form#formgrc #procurement_estimatedprice").val(procurement_estimatedprice);
	$("form#formgrc #procurement_unit").val(procurement_unit);
	$("form#formgrc #procurement_requesteddate").val(procurement_requesteddate);
	$("form#formgrc #quantity_received").val(quantity_received);
	
	$("form#formgrc #procurement_quantity_ordered").val(procurement_quantity_ordered);
	$("form#formgrc #procurement_unit_price").val(procurement_unit_price);
	$("form#formgrc #procurement_payment_status").val(procurement_payment_status);
	$("form#formgrc #procurement_requesteddate").val(procurement_delivery_date);
	$("form#formgrc #procurement_supplier_name").val(procurement_supplier_name);
	$("form#formgrc #procurement_supplier_address").val(procurement_supplier_address);
	$("form#formgrc #procurement_supplier_id").val(procurement_supplier_id);
	
	
	
	
	
}catch (error) {}
</script> 
</c:if>


<c:if test="${type == 'production_list' }"> 
<script>
try {
    // สร้างอ็อบเจ็กต์ใน JavaScript ที่เก็บข้อมูลทั้งหมด
    var itemData = {
        id: "${item.id}",
        tempId: "${item.tempId}",
        product_name: "${item.product_name}",
        order_quantity: "${item.order_quantity}",
        start_date: "${item.start_date}",
        end_date: "${item.end_date}",
        processes: "${item.processes}",
        responsible_person: "${item.responsible_person}",
        production_location: "${item.production_location}",
        customer_code: "${item.customer_code}",
        customer_name: "${item.customer_name}",
        unit: "${item.unit}",
        item_code: "${item.item_code}",
        department: "${item.department}",
        sub_order_quantity: "${item.sub_order_quantity}",
        sub_unit: "${item.sub_unit}",
    };


    // ใช้ข้อมูลจากอ็อบเจ็กต์ในการตั้งค่า fields
    $("form#formgr #id").val(itemData.id);
    $("form#formgr #tempId").val(itemData.tempId);
    $("form#formgr #product_name").val(itemData.product_name);
    $("form#formgr #order_quantity").val(itemData.order_quantity);
    $("form#formgr #quantity_received").val(itemData.quantity_received);
    $("form#formgr #start_date").val(itemData.start_date);
    $("form#formgr #end_date").val(itemData.end_date);
    $("form#formgr #processes").val(itemData.processes).trigger('change');
    $("form#formgr #responsible_person").val(itemData.responsible_person);
    $("form#formgr #production_location").val(itemData.production_location);
    $("form#formgr #customer_code").val(itemData.customer_code);
    $("form#formgr #customer_name").val(itemData.customer_name);
    $("form#formgr #unit").val(itemData.unit).trigger('change');
    $("form#formgr #item_code").val(itemData.item_code);
    
    $("form#formgr #department").val(itemData.department).trigger('change');
    $("form#formgr #sub_order_quantity").val(itemData.sub_order_quantity);
    $("form#formgr #sub_unit").val(itemData.sub_unit).trigger('change');
} catch (error) {
    console.error("Error setting values:", error);
}
</script>
</c:if>




<c:if test="${edititem != null  }">  

<script> 
try{
	var qty ="${edititem.qty}";
	var list = "${Maindata.stock_list}";
	var type = "${Maindata.stock_type }";
	var quantity = "${Maindata.stock_remaining}";
	var unit = "${Maindata.stock_unit}";
	var id = "${edititem.id}";
	var id_stock = "${edititem.id_stock}";
	var product = "${Maindata.stock_pd }";
	var batch = "${Maindata.stock_batch }";

	$("form#formsr #id_stock").val("${edititem.id_stock}");
	$("form#formsr #qty").val(qty);
	$("form#formsr #id").val(id);

// ตั้งค่า max และ value ให้ input number
$("form#formsr #qty").attr("max", quantity);


const data =  " SID: "+id_stock+" List: "+list+" Quantity: "+quantity+""+unit+" PD: "+product+" Batch: "+batch;
$("form#formsr #display_stock_stockid").val(data);
}catch (error) {}




try{
	var qty ="${edititem.qty}";
	var date_action ="${edititem.date_action}";
	var issued_qty ="${edititem.issued_qty}";
	var list = "${Maindata.stock_list}";
	var type = "${Maindata.stock_type }";
	var quantity = "${Maindata.stock_remaining}";
	var unit = "${Maindata.stock_unit}";
	var id = "${edititem.id}";
	var id_stock = "${edititem.id_stock}";
	var product = "${Maindata.stock_pd }";
	var batch = "${Maindata.stock_batch }";

	$("form#formsr_c #id_stock").val("${edititem.id_stock}");
	$("form#formsr_c  #qty").val(qty);
	$("form#formsr_c  #id").val(id);

	
	
	if(issued_qty !=""){
		$("form#formsr_c  #issued_qty").val(issued_qty);
	}else{
		$("form#formsr_c  #issued_qty").val(qty);
	}
	
	
	
	
	if(date_action !=""){
		$("form#formsr_c  #date_action").val(date_action);
	}else{
		   let today = new Date().toISOString().split('T')[0]; // ดึงวันที่ปัจจุบันในรูปแบบ YYYY-MM-DD
		   $("form#formsr_c  #date_action").val(today);
	}
	
	
	
 
	
	
// ตั้งค่า max และ value ให้ input number
$("form#formsr_c  #qty").attr("max", quantity);
$("form#formsr_c  #issued_qty").attr("max", quantity);

const data =  " SID: "+id_stock+" List: "+list+" Quantity: "+quantity+""+unit+" PD: "+product+" Batch: "+batch;
$("form#formsr_c  #display_stock_stockid").val(data);
}catch (error) {}




try{

	var description ="${edititem.description}";
	var issued_qty ="${edititem.issued_qty}";
	var received_qty ="${edititem.received_qty}";
	var id = "${edititem.id}";

	$("form#formvc #description").val(description);
	$("form#formvc  #issued_qty").val(issued_qty);
	$("form#formvc  #received_qty").val(received_qty);
	$("form#formvc  #id").val(id);



}catch (error) {}

</script>  



</c:if>













<c:if test="${type == 'bom' }"> 
<script> 
try{

	var id ="${item.id}";
	var tempId="${item.tempId}";
	var bom_name ="${item.bom_name}";
	var bom_refer ="${item.bom_refer}";
	var bom_description ="${item.bom_description}";
	var bom_base_quantity ="${item.bom_base_quantity}";
	var bom_base_unit ="${item.bom_base_unit}";
	var bom_note ="${item.bom_note}";
	var bom_status ="${item.bom_status}";
	var bom_price ="${item.bom_price}";
	var status ="${item.status}";
	var bom_category ="${item.bom_category}";
	
	
	$("form#formgr #id").val(id);
	$("form#formgr #tempId").val(tempId);
	$("form#formgr #bom_name").val(bom_name);
	$("form#formgr #bom_refer").val(bom_refer);
	$("form#formgr #bom_description").val(bom_description);
	$("form#formgr #bom_base_quantity").val(bom_base_quantity);
	$("form#formgr #bom_base_unit").val(bom_base_unit);
	$("form#formgr #bom_note").val(bom_note);
	$("form#formgr #status").val(status);
	$("form#formgr #bom_price").val(bom_price);
	$("form#formgr #bom_status").val(bom_status);
    $("form#formgr #bom_category").val(bom_category).trigger('change');

	
}catch (error) {}


</script> 
</c:if>



<c:if test="${type == 'production_standard' }"> 
<script> 
try{
	
	
	

	var id ="${item.id}";
	var tempId="${item.tempId}";
	var title ="${item.title}";
	var process_step ="${item.process_step}";
	var product_code ="${item.product_code}";
	var product_name ="${item.product_name}";
	var title_string1 ="${item.title_string1}";
	var title_string2 ="${item.title_string2}";
	var title_string3 ="${item.title_string3}";
	var title_string4 ="${item.title_string4}";
	var title_string5 ="${item.title_string5}";
	
	var title_number1 ="${item.title_number1}";
	var title_number2 ="${item.title_number2}";
	var title_number3 ="${item.title_number3}";
	var title_number4 ="${item.title_number4}";
	var title_number5 ="${item.title_number5}";
	var note ="${item.note}";
	var status ="${item.status}";
	

	
	
	$("form#formgr #id").val(id);
	$("form#formgr #tempId").val(tempId);
	$("form#formgr #process_step").val(process_step);
	$("form#formgr #product_code").val(product_code);
	$("form#formgr #product_name").val(product_name);
	
	$("form#formgr #title_string1").val(title_string1);
	$("form#formgr #title_string2").val(title_string2);
	$("form#formgr #title_string3").val(title_string3);
	$("form#formgr #title_string4").val(title_string4);
	$("form#formgr #title_string5").val(title_string5);

	$("form#formgr #title_number1").val(title_number1);
	$("form#formgr #title_number2").val(title_number2);
	$("form#formgr #title_number3").val(title_number3);
	$("form#formgr #title_number4").val(title_number4);
	$("form#formgr #title_number5").val(title_number5);
	
	$("form#formgr #note").val(note);
	$("form#formgr #status").val(status);
}catch (error) {}






</script> 
</c:if>





<c:if test="${type == 'other' }"> 
<script> 
try{
	
	


	var id ="${item.id}";
	var tempId="${item.tempId}";
	var doc_code ="${item.doc_code}";
	var product_code ="${item.product_code}";
	var product_name ="${item.product_name}";
	var lot_batch ="${item.lot_batch}";
	var process_step ="${item.process_step}";
	var department ="${item.department}";
	var list ="${item.list}";
	var employee ="${item.employee}";
	var inspection ="${item.inspection}";
	var equipment ="${item.equipment}";
	var date_start ="${item.date_start}";

	
	$("form#formgr #id").val(id);
	$("form#formgr #tempId").val(tempId);
	$("form#formgr #doc_code").val(doc_code);
	$("form#formgr #product_code").val(product_code);
	$("form#formgr #product_code").val(product_code);
	$("form#formgr #product_name").val(product_name);
	$("form#formgr #lot_batch").val(lot_batch);
	$("form#formgr #list").val(list);
	$("form#formgr #date_start").val(date_start);

	
	$("form#formgr select#department").val(department).trigger('change.select2');
	$("form#formgr select#process_step").val(process_step).trigger('change.select2');
	$("form#formgr select#employee").val(employee).trigger('change.select2');
	$("form#formgr select#equipment").val(equipment).trigger('change.select2');
}catch (error) {}






</script> 
</c:if>

