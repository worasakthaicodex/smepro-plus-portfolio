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

         Drawers : <br><br>
                   	 <div id="showform"> 
		                     
		                              
		                                        <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
		                                     
		                                         <div class="todo-task-list-wrapper list-group" id="wrappermain_2">
		                                     <input type="hidden"  name="edata_id"  value="${edata_id}" >
		                                       <input type="hidden"  name="type"  value="distributor" >
		                                        <input type="hidden"  name="distributor_id"  value="${distributors.distributor_id+0}" >  
		                                         
		                                     <label><spring:message code="item.name" text="ชื่อ" />: </label>
                                             <div class="mb-1"><input type="text"  name="distributor_name"   class="form-control" value="${distributors.distributor_name}"></div>
                                             <br>
                                             
		                                     <label><spring:message code="item.address" text="ที่อยู่" />: </label>
                                             <div class="mb-1"><textarea  name="distributor_address"   class="form-control">${distributors.distributor_address}</textarea>
                                             
                                             
		                                     </div>
		                      <div>                    
		                                <button type="submit" onclick="postdata('/addshipping-filemanager?${_csrf.parameterName}=${_csrf.token}','adddistributor','wrappermain_2')" 
                                       id="submits" class="btn btn-sm btn-light " >
                                		<spring:message code="btn.add" text="เพิ่ม" />
                               </button> 
                               
                               
                                <c:if test="${distributors != null}">
		                                  <button type="button"  
		                                  onclick="getdata('/delsubdata-filemanager/distributor/${distributors.distributor_id}/${edata_id}','wrappermain_2')" 
		                                   class="btn btn-sm btn-danger " >
		                                		ลบ
		                               </button> 
                               </c:if>
                               
                               
                              </div>   
                               
		                                        </div>
		                              
                                    
                          </div>
                                     
                          <br><br><br>
                          
                          **ต้อง Save เอกสารก่อนเลือกรายการ ***
  								 <table  class="table">
                                    <thead>
                                        <tr>
                                            <th class="form-label fixhead">#</th>
                                       
                                             <th class="form-label fixhead"><spring:message code="item.name" text="ชื่อ" /></th>
                                              <th class="form-label fixhead"><spring:message code="item.address" text="ที่อยู่" /></th>
                                             <th class="form-label fixhead"><spring:message code="tables.action" text="จัดการ" /></th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                      
                                        <c:forEach items="${distributored }" var="row" varStatus="count">
				                                      <tr>
				                                            <td>${count.count }</td >
				                                   
				                                             <td>${row.distributor_name }</td >
				                                             <td>${row.distributor_address }</td >
				                                             <td>
				                                             <a href="#" onclick="getdatas('/editsubdata-filemanager/distributor/${row.distributor_id}/${edata_id}','wrappermain_2')" 
				                                             class="btn btn-sm btn-light">เเก้ไข</a>
				                                              <a href="/poset-filemanager/distributor/${row.distributor_id}/${edata_id}"  class="btn btn-sm btn-success">ใช้</a>

				                                             </td>
				                                        </tr>
                                        </c:forEach>
                                        
                                        
                                    </tbody>
                                    </table>    