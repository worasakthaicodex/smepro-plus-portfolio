<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

   
                 <c:forEach var="row" items="${actionplans}" >
                                     <div class="card" id="subs-${row.actionplan_id }" style="width:95%;margin-left:5%">
				                                    <div class="card-body">
				                                        <div class="d-flex align-items-start">
				                                            <div class="avatar me-75">
				                                                <img src="/resources/app-assets/images/portrait/small/avatar.jpg" width="38" height="38" alt="Avatar">
				                                            </div>
				                                            <div class="author-info">
				                                                <h6 class="fw-bolder mb-25">${row.actionplan_responsible }</h6>
				                                                
				                                                
				                                            
				                                                
				                                                <p class="card-text">${row.actionplan_dateadd}</p>
				                                                <p class="card-text">
				                                                
				                                                
				                                                 <c:set var = "newLineChar" value = "<%= \"\n\" %>" />
				                                                      <c:set var = "br" value = "<br/>" />
				                                                  ${fn:replace(row.actionplan_comment,newLineChar,br)}
				                                                
				                                                </p>
				                                                
				                                            <c:if test='${row.file_type.equals("image/png") or row.file_type.equals("image/jpg")  or row.file_type.equals("image/jpeg")}'>
				                                            
				                                           <a href="/render2-filemanager?file=${row.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;"> <img src="/document/${row.file_path}/${row.file_name}" style="width:100px"></img>
				                                               </a>  <br>
				                                               </c:if> 
				                                               
				                                 <c:if test='${!row.file_type.equals("image/png") && !row.file_type.equals("image/jpg")  && !row.file_type.equals("image/jpeg")}'>
				                                            
				                                           <a href="/render2-filemanager?file=${row.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">${row.file_name_original }</img>
				                                               </a>  <br>
				                                    </c:if> 
				                                               
				                                                 <textarea  id="c-${row.actionplan_id }" style="display:none">${row.actionplan_comment}</textarea>
				                                              <input type="hidden" id="f-${row.actionplan_id }" value="${row.file_name }">
				                                              
				                                            </div>
				                                            
				                                            
				                                          
				                                            
				                                        </div>
				                                        
				                                        
				                                        
				                                        <br>
				                                        				                              

				                                     
				                                                
				                                                
				                                          <div style="float:right">
				                                          
				          <c:if test='${row.actionplan_responsible.equals(Member_name)}' >                                    
                                                         <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Action
                                </button>
                                <div class="dropdown-menu" style="">
                             
                                
                                     
                                <a class="dropdown-item" href="javascript:void(0)" onclick="edit2('${row.actionplan_id }')" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" >เเก้ไข
                              
                                 </a>
                                
                              

                                     <a class="dropdown-item" href="#" 
                                     onclick="getdatas('/delsubactionplan-filemanager/${row.actionplan_idrfer}/${row.actionplan_id }','cm${row.actionplan_idrfer}',1);" class="btn btn-sm btn-danger" >ลบ
                              
                                 </a>
                                    
                                      
                                
                                </div>
                            </div>
                            </c:if>
                            
                            
                                            </div>
				                                     

				                                            
				                                                       
				                                     
				                                     
				                                        
				                                    </div>
				                                </div>
				                                
				                                                   
                      <div class="card" id="edits-${row.actionplan_id }" style="display:none">
                                    <div class="card-body">
                                    
                           
                          
                                <form method="POST" modelAttribute="actionplan" id="form-${row.actionplan_id }" name="form-${row.actionplan_id }" enctype="multipart/form-data">
                                            <div class="row">
                                                        <input type="hidden" name="task_id" value="${tasks.task_id}">
                                                          <input type="hidden" name="actionplan_id" value="${row.actionplan_id}">
                                                <div class="col-12">
                                                    <textarea class="form-control mb-2" name="actionplan_comment"  id="comment-${row.actionplan_id}" rows="4" placeholder="Comment">${row.actionplan_comment}</textarea>
                                                </div>
                                                
                                                  <div class="col-6">
		                                        <label class="form-label" for="modalRoleName">ค่าใช้จ่าย</label>
		                                       <input type="number" id="modalRoleName" name="actionplan_expenses" class="form-control" min="0.1" value="${row.actionplan_expenses}"  >
		                              </div>
                                      <div class="col-6">
                                        <label class="form-label" for="modalRoleName">การเก็บเงินจากลูกค้า</label>
                                       <input type="number" id="modalRoleName" name="actionplan_payment" class="form-control" min="0.1" value="${row.actionplan_payment}" >
                                    </div>
                                                
                                                
                                                
                                            	<input  type="hidden" name="id_refer" id="id_refer" value="${actionplan.actionplan_id }">
                                          <span id="img-${row.actionplan_id }" style="display:<c:if test="${row.file_name == null}">none</c:if>"> 
                                           <span id="subimg-${row.actionplan_id }" >-${row.file_name}</span> <a href="#" onclick="delimg2('${row.actionplan_id }')">เปลี่ยนรูป</a></span>
                                                  <input type="hidden" name="del" id="del-${row.actionplan_id }" value="0">
                                            <br>   <br>
                                                  <div class="col-12">
                                                  
                                                  
                                                  <div id="imgs-${row.actionplan_id }" style="display:<c:if test="${row.file_name != null}">none</c:if>">
                                                    <input  class="form-control mb-2" type="file" name="file" >
                                                   </div>
                                                  
                                                    
                                               
                                                   <input type="hidden" name="folder" id="folder" value="${folder }"  >
                               						<input  type="hidden" name="task_id" id="task_id" value="${tasks.task_id }">
                                                </div>
                                                <div class="col-12">
                                                     <button type="button"  class="btn btn-primary me-1 waves-effect waves-float waves-light" 
                                    onclick='postdata("/editsubactionplan-filemanager?${_csrf.parameterName}=${_csrf.token}","form-${row.actionplan_id }","subs-${row.actionplan_id }")'>แก้ไข</button>
                                              <button type="button"  class="btn btn-light me-1 waves-effect waves-float waves-light" 
                                    onclick="edit2('${row.actionplan_id }')">ยกเลิก</button>
                                                </div>
                                            </div>
                                </form>		                                
				         </div>
				      </div>              
				                                
				         </c:forEach>                       
				                
				                
				                   <c:set var="listcheck" value="${Member_name}" />                           
                                <c:set var="list" value="${tasks.task_iduser}" />                    
                                           <c:if test='${fn:contains(list,listcheck)}'>                
				                  <div class="card"  style="width:95%;margin-left:5%">        
				                     <form method="POST" modelAttribute="actionplan" id="forms-${actionplan.actionplan_id}" name="forms-${actionplan.actionplan_id}" enctype="multipart/form-data">
				                      <div class=" card-body">                    
				       
                                            <div class="row">
                                  
                                                <div class="col-12">
                                                    <textarea class="form-control mb-2" name="actionplan_comment" rows="4" placeholder="Comment"></textarea>
                                                </div>
                                            
                                                  <div class="col-12">
                                                   <input type="hidden" name="task_id" value="${tasks.task_id}">
                                                    <input  class="form-control mb-2" type="file" name="file">
                                                   <input type="hidden" name="folder" id="folder" value="${folder }"  >
                               						<input  type="hidden" name="id_refer" id="id_refer" value="${actionplan.actionplan_id }">
                                                </div>
                                                <div class="col-12">
                                                     <button type="button"  class="btn btn-primary me-1 waves-effect waves-float waves-light" 
                                    onclick='postdata("/addsubactionplan-filemanager?${_csrf.parameterName}=${_csrf.token}","forms-${actionplan.actionplan_id}","cm${actionplan.actionplan_id}")'>นำเสนอ</button>
                                                </div>
                                            </div>
                         
				                        </div>
				               </form>
				                                                        
				                                                        </div>
	                       
	                       </c:if>
	                       
	                       
	                       
<script>

function delimg2(id) {
	$("#img-"+id).toggle();
	$("#imgs-"+id).toggle();
	$("#del-"+id).val(1);
	
}
</script>	                       
	                       
	                       
	                       