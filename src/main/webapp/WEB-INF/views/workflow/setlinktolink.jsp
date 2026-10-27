<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
 
	  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	      <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>


<form method="POST"   action="/savelinktolink-workflow">
    <div class="card-body"> 
 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<input type="hidden"  name="workflow_id" value="${workflow_id }">
<input type="hidden"  name="id" value="${process.id }">



                                   					 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-4">Allowed Link Relation</div>         
                                             <div class="col-sm-8">
                                            <select class="form-control" name="link_to">
                                             <option value="0" <c:if test="${process.link_to == 0 }">selected</c:if> >Not Allowed</option>
                                             <option value="1" <c:if test="${process.link_to == 1 }">selected</c:if>>Allowed</option>
                                                         </select>
                                                        </div>
                                                 </div>
                                                       
                                                       				 <div class="mb-1 row">
                                                                <div class="col-sm-4">Cardinality (รูปแบบจำนวนการเชื่อม)</div>
                                                         
                                                                   <div class="col-sm-8">
                                             <select class="form-control" name="relationship">
                                                      <option <c:if test='${ fn:contains(process.relationship,"exclusive")}'>selected</c:if>  value="exclusive">Exclusive (1:1)</option>
                                                         <option <c:if test='${ fn:contains(process.relationship,"unlimited")}'>selected</c:if> value="unlimited">Unlimited (1:N)</option>
                                                         </select>
                                                        </div>
                                                           
                                                            </div>
                                                            
                                             
                                              				 <div class="mb-1 row">
                                                                <div class="col-sm-4">Data Transfer (การดึงข้อมูลลูก)</div>
                                                         
                                                                   <div class="col-sm-8">
                                             <select class="form-control" name="data_transfer">
<option <c:if test='${ fn:contains(process.data_transfer,"link_only")}'>selected</c:if>  value="exclusive">Link Only: รู้แค่ว่าเกี่ยวข้องกัน (Reference ID) ไม่ดึงข้อมูล</option>
 <option <c:if test='${ fn:contains(process.data_transfer,"full_inherit")}'>selected</c:if> value="full_inherit">Full Inherit: ดึงมาทั้งหมด (Copy All Items)</option>
  <option <c:if test='${ fn:contains(process.data_transfer,"partial_inherit")}'>selected</c:if> value="partial_inherit">Partial Inherit: ดึงมาบางส่วน (Select Items)</option>
                                                         </select>
                                                        </div>
                                                           
                                                            </div>
                                                           				 <div class="mb-1 row">
                                                                <div class="col-sm-4">Validation & Integrity (การตรวจสอบสถานะข้อมูลลูก)</div>
                                                         
                                                                   <div class="col-sm-8">
                                             <select class="form-control" name="integrity">
<option <c:if test='${ fn:contains(process.integrity,"locking")}'>selected</c:if>  value="locking">Locking: ห้ามแก้ไขต้นทาง</option>
 <option <c:if test='${ fn:contains(process.integrity,"flexible")}'>selected</c:if> value="flexible">Warning/Notification: แก้ไขได้ แต่ต้องแจ้งเตือนปลายทาง</option>

                                                         </select>
                                                        </div>
                                                           
                                                            </div>                        
                                             
                                                             				 <div class="mb-1 row">
                                                                <div class="col-sm-4">Current Status (สถานะที่เชื่อมได้)</div>
                                                         
                                                                   <div class="col-sm-8">
                                                                  
                                                                   
                                             <select class="form-control select2" name="current_status" multiple="multiple" >
 <c:forEach items="${enumData }" var="rows" varStatus="count">
 	<option <c:if test='${ fn:contains(process.current_status,rows.dataset_data_title)}'>selected</c:if> value="${rows.dataset_data_title }">${rows.dataset_data_title }</option>
</c:forEach>
                                                         </select>
                                                        </div>
                                                           
                                                            </div>      


  	
 </div>      
                     <div class="modal-footer" >
                                                              <a  href="/docworkflowapp-workflow"  target="_blank" class="btn btn-light waves-effect waves-float waves-light" >Doc</a>
                                                                <input type="Submit"  value="Save"  class="btn btn-primary waves-effect waves-float waves-light">
                                                            </div>                               
</form>                                                               
                                            