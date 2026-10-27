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
                                                  <table  id="table2" width="100%">
                                    <thead>
                                        <tr>  <th class="form-label fixhead" style="width:10px">#</th>    
                                         <th class="form-label fixhead" ><spring:message code="fg.date"  /> </th>    
 <th class="form-label fixhead" ><spring:message code="product.lot_batch"  /></th>   	                          
<th class="form-label fixhead" ><spring:message code="product.product_code"  /></th>     
<th class="form-label fixhead" ><spring:message code="product.product_name"  /></th>     
<th class="form-label fixhead" ><spring:message code="stock.quantity"  /></th>   
<th class="form-label fixhead" ><spring:message code="fg.unit"  /></th>     
   
<th class="form-label fixhead" ><spring:message code="fg.from_location"  /></th>        
<th class="form-label fixhead" ><spring:message code="fg.to_location"  /></th>      
<th class="form-label fixhead" ><spring:message code="tables.status"  /></th>    

<th class="form-label fixhead" ><spring:message code="report.operation"  /></th>                             
<th class="form-label fixhead" ><spring:message code="dar.inspector"  /></th>                                
<th class="form-label fixhead" ><spring:message code="file.file"  /></th>  
         </tr>
                                    </thead>
                                    <tbody>
                                       
                                       
                                
                                        <c:forEach items="${item }" var="rows"  varStatus="count">
				                                     
				                                     
				                                                            <tr>
                                        
            	 <td class="form-label fixhead" style="padding:5px;font-size:13px">${count.count }</td>   
            	 <td class="form-label fixhead" style="padding:5px;font-size:13px" >${rows.transfer_date }</td>     
            	                          
 		  
 					
 				<td class="form-label fixhead" style="padding:5px;font-size:13px" data-custom="code">${rows.transfer_lot_batch }</td>      		 
<td class="form-label fixhead" style="padding:5px;font-size:13px" data-custom="stock_code"  title="${rows.transfer_product_code }">${rows.transfer_product_code }</td>     
<td class="form-label fixhead" style="padding:5px;font-size:13px"  data-custom="stock_name"   title="${rows.transfer_product_name }">${rows.transfer_product_name }</td> 
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.transfer_quantity }</td>   
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.transfer_unit }</td>  

<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.transfer_from_location }</td> 
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.transfer_to_location }</td> 

<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.fg_status }</td>   
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.transferred_by }</td>   
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.fg_approved_by }</td>  

                                          <td >
                          	            <c:forEach items="${rows.file}" var="file"   varStatus="count">
                         
                       
								<a href="/render-filemanager?file=${rows.edata_id }&&count=${count.count}&&file_edata=${file.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">
									       ${file.file_name_original }
								</a>
								<br>
                                   </c:forEach>     
                          	      </td>

                                        </tr>
									</c:forEach>					
				                                 
                                        
                                    </tbody>
                                </table>
                               
                                
 <script>
function openCenteredWindow(url, name, width, height) {
	
	

    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>                               
                  