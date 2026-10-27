<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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



             <table  class="table" style="font-size:13px;">
                                    <thead>
                                        <tr>
                                            <th class="form-label fixhead" style="padding:5px;font-size:13px">#</th>
                                            <th class="form-label fixhead" style="padding:5px;font-size:13px"> <spring:message code="item.description" text="รายละเอียดสินค้า/บริการ" /></th>
                                             <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="item.quantity" text="จำนวน" /></th>
                                                      <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="item.unit" text="หน่วยนับ" /></th>
                                              <th class="form-label fixhead" style="padding:5px;font-size:13px"> <spring:message code="item.estimated" text="ราคาประมาณการ" /></th>
                                                    <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="item.cost" text="ราคา" /></th>
                                              <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="item.total" text="มูลค่ารวม" /></th>
                                         <th class="form-label fixhead" style="padding:5px;font-size:13px">PR</th>
                                                           <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="tables.action" text="จัดการ" /></th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                      <c:set var="Clear_status" value="0.00" />
                                        <c:forEach items="${item }" var="row" varStatus="count">
				                                      <tr>
				                                            <td  style="padding:5px;">${count.count }</td >
				                                             <td  style="padding:5px;">${row.description }</td >
				                                             <td  style="padding:5px;">${row.qty }</td >
				                                            <td style="padding:5px;">${row.unit }</td >
				                                            <td style="padding:5px;">${row.estimated }</td >
				                                               <td style="padding:5px;">${row.cost }</td >
				                                            <td style="padding:5px;">
				                                              <fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${row.cost * row.qty  } " />
				                                            
				                                            
				                                             <c:set var="Clear_status" value="${ Clear_status + (row.cost * row.qty)  }" /></td>
				                                              <td style="padding:5px;">${row.pr_date  }${row.pr_id  } </td>
				                                             <td style="padding:5px;">
				                                             <a href="#" onclick="getdata('/delitem-filemanager/${count.count-1 }/${row.item_id}?del=cleared','subdata')"  style="padding:5px;font-size:13px" class="btn btn-sm btn-danger" >ลบ</a>
				                                             <a href="#" onclick="getdatas('/showitem-filemanager/${count.count-1 }/${row.item_id}','subdata_edititem')" class="btn btn-sm btn-light"  style="padding:5px;font-size:13px" data-bs-toggle="modal"  data-bs-target="#edititem" >เเก้ไข</a>  
				                                             </td>
				                                        </tr>
                                        </c:forEach>
                                        
                                        
                                    </tbody>
                                    </table>  
                                       <br>
                                  <h3 style="float:right"> รวม  :    <fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${Clear_status }" /></h3>    
                                  
                                  
                                  
                                  
                                  