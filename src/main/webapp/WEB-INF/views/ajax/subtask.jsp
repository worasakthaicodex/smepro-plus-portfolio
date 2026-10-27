<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



   <c:forEach var="row" items="${actionplans}" >
                                   <div class="card" id="sub-${row.actionplan_id }">
				                                    <div class="card-body">
				                                        <div class="d-flex align-items-start">
				                                            <div class="avatar me-75">
				                                                <img src="/resources/app-assets/images/portrait/small/avatar.jpg" width="38" height="38" alt="Avatar">
				                                            </div>
				                                            <div class="author-info">
				                                                <h6 class="fw-bolder mb-25">${row.actionplan_responsible }</h6>
				                                                
				                                                
				                                            
				                   
                                   
				                                                <p class="card-text">${row.actionplan_dateadd}   <span class="badge rounded-pill badge-light-info">ค่าใช้จ่าย : ${row.actionplan_expenses +0.0}</span>  <span class="badge rounded-pill badge-light-success">การเก็บเงินจากลูกค้า : ${row.actionplan_payment+0.0}</span></p>
				                                              
				                                              
				                                              
				                                              
				                                                <p class="card-text" >
				                                                
				                                                
				                                                 <c:set var = "newLineChar" value = "<%= \"\n\" %>" />
				                                                      <c:set var = "br" value = "<br/>" />
				                                                  ${fn:replace(row.actionplan_comment,newLineChar,br)}
				                                                
				                                                </p>
				                   
				                                                
				                             <c:forEach var="rows" items="${row.file}" >             
				                                            <c:if test='${rows.file_type.equals("image/png") or rows.file_type.equals("image/jpg")  or rows.file_type.equals("image/jpeg")}'>
				                                            
				                                           <a href="/render2-filemanager?file=${rows.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;"> <img src="/document/${rows.file_path}/${rows.file_name}" style="width:100px"></img>
				                                               </a>  
				                                               </c:if> 
				                                               
				                                 <c:if test='${!rows.file_type.equals("image/png") && !rows.file_type.equals("image/jpg")  && !rows.file_type.equals("image/jpeg")}'>
				                                            
				                                           <a href="/render2-filemanager?file=${rows.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">${rows.file_name_original }</img>
				                                               </a>  
				                                               <br>
				                                    </c:if> 
				                                    
				                                </c:forEach>    
				                                    
				                                               
				                                               <textarea  id="c-${row.actionplan_id }" style="display:none">${row.actionplan_comment}</textarea>
				                                              <input type="hidden" id="f-${row.actionplan_id }" value="${row.file_name }">
				                                            </div>
				                                            
				                                            
				                                          
				                                            
				                                        </div>
				                                        
				                                        
				                                        
				                                        <br>
				                                        				                              
				                                                    <div class="feedback" style="float:left">
<c:if test='${row.actionplan_solutions.equals("0")  }'>			                                                    
  <img src="/resources/app-assets/images/icons/0.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  <c:if test='${row.actionplan_solutions.equals("1")  }'>			                                                    
  <img src="/resources/app-assets/images/icons/1.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  <c:if test='${row.actionplan_solutions.equals("2")   }'>			                                                    
  <img src="/resources/app-assets/images/icons/2.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  <c:if test='${row.actionplan_solutions.equals("3")   }'>			                                                    
  <img src="/resources/app-assets/images/icons/3.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  <c:if test='${row.actionplan_solutions.equals("4")   }'>			                                                    
  <img src="/resources/app-assets/images/icons/4.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  <c:if test='${row.actionplan_solutions.equals("5")   }'>			                                                    
  <img src="/resources/app-assets/images/icons/5.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  
  </div>
				                                          <a href="#" style="padding-button:5px;">
				                                                    <div class="d-inline-flex align-items-center">
				                                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-corner-up-left font-medium-3 me-50"><polyline points="9 14 4 9 9 4"></polyline><path d="M20 20v-7a4 4 0 0 0-4-4H4"></path></svg>
<span onclick="show('${row.actionplan_id }');getdatas('/subactionplan-filemanager/${row.actionplan_id }','cm${row.actionplan_id }',0)">ตอบกลับ (${row.actionplan_result+0 })</span>
				                                                        
				                                    
				                                                        
				                                                    </div>
				                                                     
				                                               
				                                                </a>
				                                                
				                                                
				                                                
				                                          <div style="float:right">
                                                         <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Action 
                                </button>
                                <div class="dropdown-menu" style="">
                             
                                
                                     
                                
                                
                              
                                 

                                  <c:if test='${row.actionplan_responsible.equals(Member_name)}' >
                                  
                                 
                                       <a class="dropdown-item" href="javascript:void(0)" onclick="edit('${row.actionplan_id }')" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" >เเก้ไข
                              
                                 </a>
                                     <a class="dropdown-item" href="#" 
                                     onclick="getdatas('/delactionplan-filemanager/${row.actionplan_idrfer}/${row.actionplan_id }','dataplan',1);" class="btn btn-sm btn-danger" >ลบ
                              
                                 </a>
                                  </c:if>
                                   <c:if test="${tasks.task_idadd == Member_id}" >     
                                      <div class="dropdown-divider"></div>    
                                            <a class="dropdown-item" href="/setsubmission-filemanager/${row.actionplan_id }" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary">
                                 เลือกเป็น Submission
                              
                                 </a>
                                       <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" onclick="getdatas('/setpoinactionplan-filemanager/${row.actionplan_id }/5','sub-${row.actionplan_id }',0);">ให้คะเเนน 5
                              
                                 </a>
                                         <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" onclick="getdatas('/setpoinactionplan-filemanager/${row.actionplan_id }/4','sub-${row.actionplan_id }',0);">ให้คะเเนน 4
                              
                                 </a>
                                         <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" onclick="getdatas('/setpoinactionplan-filemanager/${row.actionplan_id }/3','sub-${row.actionplan_id }',0);">ให้คะเเนน 3
                              
                                 </a>
                                         <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" onclick="getdatas('/setpoinactionplan-filemanager/${row.actionplan_id }/2','sub-${row.actionplan_id }',0);">ให้คะเเนน 2
                              
                                 </a>
                                         <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" onclick="getdatas('/setpoinactionplan-filemanager/${row.actionplan_id }/1','sub-${row.actionplan_id }',0);">ให้คะเเนน 1
                              
                                 </a>
  
     						 </c:if>
                                
                                </div>
                            </div>
                                            </div>
				                                     

				                                            
				                                                       
				                                     
				                                     
				                                        
				                                    </div>
				                                </div>
				                                
				                                
				                                
                      <div class="card" id="edit-${row.actionplan_id }" style="display:none">
                                    <div class="card-body">
                                    
                           
                          
                                <form:form method="POST" modelAttribute="actionplan" id="form-${row.actionplan_id }" name="form-${row.actionplan_id }" enctype="multipart/form-data">
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
                                          <br>        <br>
                                    </div>
                                            
                                            
                                            <br>
                                            
                                      
                                                  <div class="col-12">
                                                  
                                                   <input  class="form-control mb-2" type="file" name="files" multiple>
                                                  
                                                    
                                               
                                                   <input type="hidden" name="folder" id="folder" value="${folder }"  >
                               						<input  type="hidden" name="task_id" id="task_id" value="${tasks.task_id }">
                                                </div>
                                                     <br>   <br>  
                                                    <span > 
                                                    
                                                    
                                         
                      				   <c:forEach var="file" items="${row.file}">
		                                            <li class="d-block"  id="img-${file.file_id }">
		                                                <span class="me-25">-</span>
		                                                <span>	<a href="/render2-filemanager?file=${file.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">${file.file_name_original }</a>
									     
									     
									     </span>
									     
					  
								<span class="me-25"><a  href="#"  onclick="getdata3('/delfileactionplan-filemanager/${row.actionplan_id }/${file.file_id }','${row.actionplan_id }');" 
								class="btn btn-sm btn-danger"  >นำออก</a></span>
		                                            </li>
		                                            <br>
		                                         </c:forEach>  
                                         
                                                
                                                
                                                
                                                           <br>   <br>  
                                                
                                                
                                                <div class="col-12">
                                                     <button type="button"  class="btn btn-primary me-1 waves-effect waves-float waves-light" 
                                    onclick='postdata("/editactionplan-filemanager?${_csrf.parameterName}=${_csrf.token}","form-${row.actionplan_id }","sub-${row.actionplan_id }")'>แก้ไข</button>
                                              <button type="button"  class="btn btn-light me-1 waves-effect waves-float waves-light" 
                                    onclick="edit('${row.actionplan_id }')">ยกเลิก</button>
                                                </div>
                                            </div>
                                </form:form>		                                
				         </div>
				      </div>              
			   
				         
				                            <div id="cm${row.actionplan_id }" style="display:none" > 
				                            	
				 
				           
				              </div>                      
				                                
</c:forEach> 


<script>

function delimg(id) {
	$("#img-"+id).toggle();
	$("#imgs-"+id).toggle();
	$("#del-"+id).val(1);
	
}



function show(id) {
	$("#cm"+id).toggle();
}

function openCenteredWindow(url, name, width, height) {
    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>  