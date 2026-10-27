<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


             <style>
 
 
 table {
    border-collapse:collapse;
    margin: 0;
  width: 100%;
   border: 1px solid #dddddd;
      font-size:5px;
   
}


  
td {
    text-align: center;
   border: 1px solid #dddddd;
      max-width: 140px;
     font-size:11px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */

         min-width: 5px;
    padding:3px;
  
}





th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
     font-size:11px;
     padding:3px;
     max-width: 140px;
       text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
         min-width: 5px;
}





  </style>
  
  
                                                 <br><br>
                                                 <b><spring:message code="material.usageby"  /></b><br><br>
                                                  <table  id="tables" width="100%">
                                    <thead>
<tr>  <th class="form-label fixhead" style="width:10px">#</th>    
 <th class="form-label fixhead" ><spring:message code="fg.date"  /> </th>    
 <th class="form-label fixhead" ><spring:message code="product.lot_batch"  /></th>   	                          
    
<th class="form-label fixhead" ><spring:message code="stock.quantity"  /></th>   
<th class="form-label fixhead" ><spring:message code="maindata.batch_producer"  /> </th>     

         </tr>
                                    </thead>
                                    <tbody>
                                       
                                       
                                
                                        <c:forEach items="${pdList }" var="rows"  varStatus="count">
				                                     
				                                     
				                                                            <tr>
                                        
<td class="form-label fixhead" style="padding:5px;font-size:13px">${count.count }</td>   
 <td class="form-label fixhead" style="padding:5px;font-size:13px" >${rows.ski_date }</td>     			
<td class="form-label fixhead" style="padding:5px;font-size:13px"  title="${rows.ski_lot_batch }">${rows.ski_lot_batch }</td>      
<td class="form-label fixhead" style="padding:5px;font-size:13px"   title="${rows.ski_quantity }">${rows.ski_quantity }</td> 
	
	<td class="form-label fixhead" style="padding:5px;font-size:13px" title="${rows.ski_tracking_number }">${rows.ski_tracking_number }</td>     	


                                    

                                        </tr>
									</c:forEach>					
				                                 
                                        
                                    </tbody>
                                </table>
                                
                                <br>
                            
                                
                                        <br>
                                                 <b><spring:message code="material.fguse"  /></b><br><br>
                                                  <table  id="tables" width="100%">
                                    <thead>
<tr>  <th class="form-label fixhead" style="width:10px">#</th>    
 <th class="form-label fixhead" ><spring:message code="fg.date"  /> </th>    
 <th class="form-label fixhead" ><spring:message code="product.lot_batch"  /></th>   	                          
    <th class="form-label fixhead" ><spring:message code="product.product_code"  /></th>     
<th class="form-label fixhead" ><spring:message code="product.product_name"  /></th>  
<th class="form-label fixhead" ><spring:message code="stock.quantity"  /></th>   
<th class="form-label fixhead" ><spring:message code="fg.unit"  /></th>    
<th class="form-label fixhead" ><spring:message code="maindata.storagelocation"  /></th>   
<th class="form-label fixhead" ><spring:message code="tables.status"  /></th>
<th class="form-label fixhead" ><spring:message code="maindata.batch_producer"  /> </th>     
         </tr>
                                    </thead>
                                    <tbody>
                                       
                                       
                                
                                        <c:forEach items="${fgList }" var="rows"  varStatus="count">
				                                   			                                     
 <tr>
<td class="form-label fixhead" style="padding:5px;font-size:13px">${count.count }</td>   
 <td class="form-label fixhead" style="padding:5px;font-size:13px" >${date }</td>   
 
 <td class="form-label fixhead" style="padding:5px;font-size:13px"  title="${rows.lot_batch }">${rows.lot_batch }</td>     			
<td class="form-label fixhead" style="padding:5px;font-size:13px"  title="${rows.product_code }">${rows.product_code }</td>      
<td class="form-label fixhead" style="padding:5px;font-size:13px"   title="${rows.product_name }">${rows.product_name }</td> 
<td class="form-label fixhead" style="padding:5px;font-size:13px" title="${rows.quantity }">${rows.quantity }</td>   
<td class="form-label fixhead" style="padding:5px;font-size:13px" title="${rows.unit }">${rows.unit }</td>      	
 

  <td class="form-label fixhead" style="padding:5px;font-size:13px" >${rows.location }</td>
  <td class="form-label fixhead" style="padding:5px;font-size:13px" title="${rows.fg_status }">${rows.fg_status }</td>     
   <td class="form-label fixhead" style="padding:5px;font-size:13px" >${search }</td>    
</tr>
</c:forEach>					
				                                 
                                        
                                    </tbody>
                                </table>
            <br>                 

                                                <br>
                                                 <b><spring:message code="material.usageby"  /></b><br><br>
                                                  <table  id="tables" width="100%">
                                    <thead>
<tr>  <th class="form-label fixhead" style="width:10px">#</th>    
 <th class="form-label fixhead" ><spring:message code="fg.date"  /> </th>    
   	
    <th class="form-label fixhead" ><spring:message code="product.customer_code"  /></th>   	                          
  <th class="form-label fixhead" ><spring:message code="product.customer_name"  /></th>   
      <th class="form-label fixhead" ><spring:message code="product.product_name"  /></th>	
	 <th class="form-label fixhead" ><spring:message code="product.lot_batch"  /></th>   
    
<th class="form-label fixhead" ><spring:message code="stock.quantity"  /></th>   
<th class="form-label fixhead" ><spring:message code="fg.unit"  /></th>    
<th class="form-label fixhead" ><spring:message code="maindata.batch_producer"  /> </th>     

         </tr>
                                    </thead>
                                    <tbody>
                                       
                                       
                                
                                        <c:forEach items="${customerList }" var="rows"  varStatus="count">
				                                     
				                                     
				                                                            <tr>
                                        
<td class="form-label fixhead" style="padding:5px;font-size:13px">${count.count }</td>   
 <td class="form-label fixhead" style="padding:5px;font-size:13px" title="${rows.issue_date }" >${rows.issue_date }</td>     
	
 <td class="form-label fixhead" style="padding:5px;font-size:13px"   title="${rows.issued_customer_code }">${rows.issued_customer_code }</td>
  <td class="form-label fixhead" style="padding:5px;font-size:13px"   title="${rows.issued_customer_name }">${rows.issued_customer_name }</td>	
    <td class="form-label fixhead" style="padding:5px;font-size:13px" >${rows.issued_product_name }</td>		
<td class="form-label fixhead" style="padding:5px;font-size:13px"  title="${rows.issued_lot_batch }">${rows.issued_lot_batch }</td>      
<td class="form-label fixhead" style="padding:5px;font-size:13px"   title="${rows.issued_quantity }">${rows.issued_quantity }</td> 
<td class="form-label fixhead" style="padding:5px;font-size:13px"   title="${rows.issued_unit }">${rows.issued_unit }</td>  	
 <td class="form-label fixhead" style="padding:5px;font-size:13px" >${search }</td>   

                                    

                                        </tr>
									</c:forEach>					
				                                 
                                        
                                    </tbody>
                                </table>
                               
                                
                  