<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    



    <!-- BEGIN: Vendor CSS-->
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/vendors.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/forms/wizard/bs-stepper.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/forms/select/select2.min.css">
    <!-- END: Vendor CSS-->

   <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-wizard.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-validation.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/pages/modal-create-app.css">



             <table  class="table">
                                    <thead>
                                        <tr>
                                            <th class="form-label fixhead" style="padding:5px;font-size:13px">#</th>
                                            <th class="form-label fixhead" style="padding:5px;font-size:13px"> <spring:message code="item.description" text="รายละเอียดสินค้า/บริการ" /></th>
                                             <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="item.quantity" text="จำนวน" /></th>
                                                      <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="item.unit" text="หน่วยนับ" /></th>
                                              <th class="form-label fixhead" style="padding:5px;font-size:13px"> <spring:message code="item.estimated" text="ราคาประมาณการ" /></th>
                                              
                                              <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="item.total" text="มูลค่ารวม" /></th>
                                        
                                               		 <th class="form-label fixhead" style="padding:5px;font-size:13px">ผู้จำหน่าย</th>
                                              
                                                
                                                 
                                                  
                                                           <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="tables.action" text="จัดการ" /></th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="Clear_status" value="0" />
                                        <c:forEach items="${item }" var="row" varStatus="count">
				                                      <tr>
				                                            <td style="padding:5px;font-size:13px">${count.count }</td >
				                                             <td style="padding:5px;font-size:13px">${row.description }</td >
				                                             <td style="padding:5px;font-size:13px">${row.qty }</td >
				                                            <td style="padding:5px;font-size:13px">${row.unit }</td >
				                                            
				                                            
				                                     
				                                            
				                                            
				                                      <td style="padding:5px;font-size:13px">${row.estimated * row.qty  } <c:set var="Clear_status" value="${ Clear_status + (row.estimated * row.qty)  }" /></td>
				                                      <td style="padding:5px;font-size:13px">${row.estimated }</td >
				                                               
				                                        		   <td style="padding:5px;font-size:13px"> ${row.distributor_name}</td >
				                                                  
				                                             <td style="padding:5px;font-size:13px">
				                                             
                              				<c:if test="${docs.current_step == 'draft' || docs.current_step == 'rejected'}">   
				                                             <a href="#" style="padding:5px;font-size:13px" 
				                                             onclick="getdata('/delitem-filemanager/${count.count-1 }/${row.item_id}','subdata')" 
				                                             class="btn btn-sm btn-danger">ลบ</a>
				                                             
				                           <a href="#" onclick="getdatas('/showitem-filemanager/${count.count-1 }/${row.item_id}?pr=ok','subdata_edititem')" 
				                           class="btn btn-sm btn-light"  style="padding:5px;font-size:13px" data-bs-toggle="modal"  data-bs-target="#edititem">เเก้ไข</a>                     
				                             </c:if>           
				                                             </td>
				                                        </tr>
                                        </c:forEach>
                                        
                                        
                                    </tbody>
                                    </table>  
                                             <br>
                                  <h3 style="float:right"> รวม  :  ${Clear_status }</h3>    
                                    
                                        