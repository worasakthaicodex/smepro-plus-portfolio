<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<style>
.alert-success {
  width: 100%;
  background-color: #229954 ; /* ปรับสีเขียวให้ซอฟต์ขึ้น */
  color: white;
  text-align: center;
  padding: 15px;
  z-index: 1000;
  animation: fadeOut 2s forwards 2s;
}

@keyframes fadeOut {
  to {
      opacity: -1;
      visibility: hidden;
      height: -1;
      padding: -1; /* ลบ padding เพื่อลดช่องว่าง */
      margin: -1;  /* ลบ margin เพื่อลดช่องว่าง */
  }
}
  select {
  -webkit-appearance: menulist-button;
  color: black;
}
	select:invalid {
  color: red;
}

        .notused{
           background-color:  #a6acaf; /* Light gray with transparency */
            color:  #a6acaf;            /* Light gray text with transparency */
         	border: 1px solid  #a6acaf; /* Light gray border with transparency */
            cursor: not-allowed;  
            pointer-events: none; 
        }



</style>


         <div class="row">
         
         
<c:if test="${Sys_Save == 'Save_Sucess'  }">
<div class="alert-success" id="success-alert">
  		<p>บันทึกสำเร็จ [Save Success]</p>
</div>    
</c:if>          

            <input type="hidden" name="field_index_idcondition" value="${key.field_index_idcondition}">
                             <input type="hidden" name="field_index_idcondition2" value="${key.field_index_idcondition2}">
                                <input type="hidden" name="fieldindex_idrefer" value="${key.fieldindex_idrefer}">
                              <input type="hidden" name="fieldindex_id" value="${key.fieldindex_id}">

                                        <div class="row">
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">ประเภท</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                           <select name="fieldindex_type"  class="form-select" id="fieldindex_type" onchange="updateVisibility()">
                                                      <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                          <option value="-1" >ไม่ใช้</option>
                                                      <c:forEach items="${Traceability}" var="trace">
                                                        <c:set var="selected" value='' />
                                                      <c:if test="${key.fieldindex_type == trace}"><c:set var="selected" value='selected' /></c:if>
                                                      	 <option value="${trace}" ${selected}>${trace.label }</option>
                              						 </c:forEach> 
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="email-id">ฟอร์มเเม่</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                           <select name="field_index_idcondition2"  class="form-select type use_material" id="field_index_idcondition2">
                                                      
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                     
                                                     	<c:if test="${key.field_index_idcondition2 == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                        <option value="-1" ${selected}>ไม่ใช้</option>
                                                        
                                                          
                                                          
                                                     <c:forEach items="${Associated }" var="row" varStatus="count">    
                                                            <c:set var="selected" value='' />
	 													<c:if test="${key.good_output == row.condition_id }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.condition_id }" ${selected}>${row.condition_name }</option>
                              						 </c:forEach>  
                              						 	
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="contact-info">ยอดผลิตงานดี</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                            <select name="good_output"  class="form-select type production use_material preparation_material" id="good_output">
                                                    
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                       		<c:if test="${key.good_output == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                        <option value="-1" ${selected}>ไม่ใช้</option>
                                                      
                                                        
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">   
                                                              <c:set var="selected" value='' /> 
	 													<c:if test="${key.good_output == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                              						 	
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">งานเสีย</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                           <select name="defective_output"  class="form-select  type production use_material preparation_material" id="defective_output">
                                                        <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                       		<c:if test="${key.defective_output == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                        <option value="-1" ${selected}>ไม่ใช้</option>
                                                          
                                                        
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">    
                                                          <c:set var="selected" value='' />
	 													<c:if test="${key.defective_output == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                          
                                          
                                       
                                        
                                        
                                                 <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">สินค้า</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                 <select name="product"  class="form-select type production fg" id="product">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                       		<c:if test="${key.product == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                        <option value="-1" ${selected}>ไม่ใช้</option>
                                                       
                                                        
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">    
                                                             <c:set var="selected" value='' />
	 													<c:if test="${key.product == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                        
                                        
                                        
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">ล๊อต</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                   <select name="lot"  class="form-select type production fg" id="lot">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                     
                                                     
                                                         		<c:if test="${key.lot == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                        <option value="-1" ${selected}>ไม่ใช้</option>
                                                           
                                                            
                                                          
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">    
                                                         <c:set var="selected" value='' />
	 													<c:if test="${key.lot == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                        
                                        
                                        
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">รอบผลิต</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                   <select name="batch"  class="form-select type production fg use_material material preparation_material" id="batch">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                     
                                                     
                                                         		<c:if test="${key.batch == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                        <option value="-1" ${selected}>ไม่ใช้</option>
                                                           
                                                            
                                                          
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">    
                                                         <c:set var="selected" value='' />
	 													<c:if test="${key.batch == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                        
                                        
                                           <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">สินค้ากึ่งสำเร็จรูป</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                             <select name="wip"  class="form-select type production fg" id="wip">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                       		<c:if test="${key.wip == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                        <option value="-1" ${selected}>ไม่ใช้</option>
                                                       
                                                        
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">    
                                                             <c:set var="selected" value='' />
	 													<c:if test="${key.wip == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                        
                                        
                                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">ล๊อตสินค้ากึ่งสำเร็จรูป</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                             <select name="lot_wip"  class="form-select type production fg" id="lot_wip">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                     
                                                     
                                                         		<c:if test="${key.lot_wip == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                        <option value="-1" ${selected}>ไม่ใช้</option>
                                                           
                                                            
                                                          
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">    
                                                         <c:set var="selected" value='' />
	 													<c:if test="${key.lot_wip == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                        
                                              <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">ผู้จำหน่าย</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                           <select name="manufacturer"  class="form-select type  material use_material preparation_material" id="manufacturer">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                        

          		<c:if test="${key.manufacturer == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                        <option value="-1" ${selected}>ไม่ใช้</option>
                                                           


                                                          
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
    														<c:set var="selected" value='' /> 
	 													<c:if test="${key.manufacturer == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                        
                                        
                                        
                                                 <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">วัตถุดิบ</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                    <select name="material"  class="form-select type material use_material preparation_material" id="material">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                     
                                                       		<c:if test="${key.material == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                        <option value="-1" ${selected}>ไม่ใช้</option>
                                                           
                                                        
                                                        
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">   
                                                         <c:set var="selected" value='' /> 
	 													<c:if test="${key.material == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                       
                                       
                                       
                                                 <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">ล๊อตวัตถุดิบ</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                  <select name="lot_material"  class="form-select  type material use_material preparation_material" id="lot_material">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                     
                                                       		<c:if test="${key.lot_material == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                        <option value="-1" ${selected}>ไม่ใช้</option>
                                                           
                                                               
                                                        
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">    
                                                         <c:set var="selected" value='' />
	 													<c:if test="${key.lot_material == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                       
                                       
                                       
                                                      <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">หน่วยนับ</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                     <select name="unit"  class="form-select type material production  use_material fg preparation_material" id="unit">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                     
                                                     
                                                        		<c:if test="${key.unit == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                          <option value="-1" >ไม่ใช้</option>
                                                               
                                                          
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                                                       <c:set var="selected" value='' />  
	 													<c:if test="${key.unit == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                       
                                                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">ยอดเคลื่อนไหว</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                          <select name="incoming_or_deliver"  class="form-select type fg material use_material preparation_material" id="incoming_or_deliver">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                     
                                                      	<c:if test="${key.incoming_or_deliver == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                         <option value="-1" >ไม่ใช้</option>
                                                      
                                                                  
                                                                  
                                                         
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                                                        <c:set var="selected" value='' />  
	 													<c:if test="${key.incoming_or_deliver == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                       
                                       
                                                              <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">วันที่ดำเนินการ</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                            <select name="date"  class="form-select type material production  use_material fg preparation_material " id="date">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                          		
                                                          		
                                                          		<c:if test="${key.date == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                         <option value="-1" >ไม่ใช้</option>
                                                      
                                                                  
                                                                  
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">    
                                                           <c:set var="selected" value='' />
	 													<c:if test="${key.date == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                       
                                       
                                                         <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">คำสั่งขาย</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                 <select name="po"  class="form-select type material production purchase_requisition use_material fg preparation_material" id="po">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                        		
                                                        		
                                                        <c:if test="${key.po == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                         <option value="-1" >ไม่ใช้</option>
                                                     
                                                                  
                                                                  
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                                                           <c:set var="selected" value='' />
	 													<c:if test="${key.po == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                       
                                       
                                       
                                                         <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">คำสั่งซื้อ</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                           <select name="pr"  class="form-select type material production  purchase_requisition  use_material fg preparation_material" id="pr">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                        		
                                                        		
                                                        <c:if test="${key.pr == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                         <option value="-1" >ไม่ใช้</option>
                                                     
                                                                  
                                                                  
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                                                           <c:set var="selected" value='' />
	 													<c:if test="${key.pr == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                       
                                      
         							   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">ใบสั่งผลิต</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                           <select name="pd"  class="form-select type material production    use_material fg preparation_material" id="pd">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                        		
                                                        		
                                                        <c:if test="${key.pd == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                         <option value="-1" >ไม่ใช้</option>
                                                     
                                                                  
                                                                  
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                                                           <c:set var="selected" value='' />
	 													<c:if test="${key.pd == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                       
                                      
                                       
                                       	   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">ใบเบิก</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                            <select name="vc"  class="form-select type voucher" id="vc">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                        <c:if test="${key.vc == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                         <option value="-1" >ไม่ใช้</option>
  
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                                                           <c:set var="selected" value='' />
	 													<c:if test="${key.vc == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                       
                                       
                                           	   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">เวลาเริ่มผลิต</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                               <select name="timestart"  class="form-select type material production  use_material fg preparation_material" id="timestart">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                     
                                                     
                                                     	<c:if test="${key.timestart == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                         <option value="-1" >ไม่ใช้</option>
                                                      
                                                                  
                                                                  
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">    
                                                        <c:set var="selected" value='' />
	 													<c:if test="${key.timestart == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                       
                                           	   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="password">เวลาผลิตสิ้นสุด</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                             <select name="timeend"  class="form-select type material production  use_material fg preparation_material" id="timeend">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                     
                                                         <c:if test="${key.timeend == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                         <option value="-1" >ไม่ใช้</option>
                                               
                                                                  
                                                                  
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">    
                                                               <c:set var="selected" value='' />
	 													<c:if test="${key.timeend == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                   
                                   
                                       	   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" >ลูกค้า</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                  <select name="customer"  class="form-select type fg production preparation_material" id="customer">
                                                       <c:set var="selected" value='' />
                                                     <option value="0" selected>-</option>
                                                     
                                                     
                                                      	<c:if test="${key.customer == -1 }"><c:set var="selected" value='selected' /></c:if>
                                                         <option value="-1" >ไม่ใช้</option>
                                                   
                                                                  
                                                                  
                                                                  
                                                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                                                           <c:set var="selected" value='' />  
	 													<c:if test="${key.customer == row.field_postion }"><c:set var="selected" value='selected' /></c:if>
                                       					 <option value="${row.field_postion }" ${selected}>${row.field_label }</option>
                              						 </c:forEach>  
                                                    </select>
                                                    </div>
                                                </div>
                                            </div>
                                   
                                      <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" ></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                            
                                               
                                               <div class="form-check form-check-primary">
                                   
                                               <input type="checkbox" value="1" class="form-check-input" id="colorCheck1" name="register" <c:if test="${key.register == 1}">checked</c:if> >  
                                            <label class="form-check-label" for="colorCheck1">Register</label>
                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                   
                                        </div>
                             
    
         
                                     
                                            
                                                 
                                            
                                            
             
                              </div>
                            </div>
               
                       
  <script>

      var selector = document.getElementById('fieldindex_type');
      
      function updateVisibility() {
          var selectedValue = selector.value;
          var allSelects = document.querySelectorAll('select.type');

          allSelects.forEach(select => {
              if (Array.from(select.classList).includes(selectedValue)) {
                  select.classList.remove('notused');
                  select.setAttribute('required', 'required'); // Add 'required' attribute
              } else {
                  select.classList.add('notused');
                  select.removeAttribute('required'); // Remove 'required' attribute
              }
          });
      }

      // Initial update
      updateVisibility();

      // Add event listener for changes
      selector.addEventListener('change', updateVisibility);

    </script>