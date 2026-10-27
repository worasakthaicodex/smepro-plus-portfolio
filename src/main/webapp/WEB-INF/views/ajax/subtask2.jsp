<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



				                                    <div class="card-body">
				                                        <div class="d-flex align-items-start">
				                                            <div class="avatar me-75">
				                                                <img src="/resources/app-assets/images/portrait/small/avatar.jpg" width="38" height="38" alt="Avatar">
				                                            </div>
				                                            <div class="author-info">
				                                                <h6 class="fw-bolder mb-25">${actionplan.actionplan_responsible }</h6>
				                                                
				                                                
				                                            
				                                                
				                                                <p class="card-text">${actionplan.actionplan_dateadd} <span class="badge rounded-pill badge-light-info">ค่าใช้จ่าย : ${actionplan.actionplan_expenses +0.0}</span>  <span class="badge rounded-pill badge-light-success">การเก็บเงินจากลูกค้า : ${actionplan.actionplan_payment+0.0}</span></p>
				                                                <p class="card-text">
				                                                
				                                                
				                                                 <c:set var = "newLineChar" value = "<%= \"\n\" %>" />
				                                                      <c:set var = "br" value = "<br/>" />
				                                                  ${fn:replace(actionplan.actionplan_comment,newLineChar,br)}
				                                                
				                                                </p>
				                                                
				                                                    <c:forEach var="rows" items="${actionplan.file}" >             
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
				                                               
				                                              
				                                            </div>
				                                            
				                                            
				                                          
				                                            
				                                        </div>
				                                        
				                                        
				                                        
				                                        <br>
				                                        				                              
				                                                    <div class="feedback" style="float:left">
<c:if test='${actionplan.actionplan_solutions.equals("0")  }'>			                                                    
  <img src="/resources/app-assets/images/icons/0.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  <c:if test='${actionplan.actionplan_solutions.equals("1")  }'>			                                                    
  <img src="/resources/app-assets/images/icons/1.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  <c:if test='${actionplan.actionplan_solutions.equals("2")   }'>			                                                    
  <img src="/resources/app-assets/images/icons/2.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  <c:if test='${actionplan.actionplan_solutions.equals("3")   }'>			                                                    
  <img src="/resources/app-assets/images/icons/3.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  <c:if test='${actionplan.actionplan_solutions.equals("4")   }'>			                                                    
  <img src="/resources/app-assets/images/icons/4.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  <c:if test='${actionplan.actionplan_solutions.equals("5")   }'>			                                                    
  <img src="/resources/app-assets/images/icons/5.png" style="width:25px;margin-right:5px;"></img>
  </c:if>
  
  </div>
				                                          <a href="#" style="padding-button:5px;">
				                                                    <div class="d-inline-flex align-items-center">
				                                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-corner-up-left font-medium-3 me-50"><polyline points="9 14 4 9 9 4"></polyline><path d="M20 20v-7a4 4 0 0 0-4-4H4"></path></svg>
<span onclick="show('${actionplan.actionplan_id }');getdatas('/subactionplan-filemanager/${actionplan.actionplan_id }','cm${actionplan.actionplan_id }',0)">ตอบกลับ (${actionplan.actionplan_result })</span>
				                                                        
				                                    
				                                                        
				                                                    </div>
				                                                     
				                                               
				                                                </a>
				                                                
				                                                
				                                                
				                                          <div style="float:right">
                                                         <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Action
                                </button>
                                <div class="dropdown-menu" style="">
                             
                                
                                     
                                
                                
                              
                                 
   <a class="dropdown-item" href="javascript:void(0)" onclick="getdata('/edittask-filemanager/${tasks.task_id}','${tasks.task_id}')" class="btn btn-sm btn-danger"  
                                 >เเก้ไข
                              
                                 </a>
                                 
                                   <c:if test='${actionplan.actionplan_responsible.equals(Member_name)}' >    
                                     <a class="dropdown-item" href="#" 
                                     onclick="getdatas('/delactionplan-filemanager/${actionplan.actionplan_idrfer}/${actionplan.actionplan_id }','dataplan',1);" class="btn btn-sm btn-danger" >ลบ
                              
                                 </a>
                                 </c:if>
                              <c:if test="${tasks.task_idadd == Member_id}" >     
                                      <div class="dropdown-divider"></div>    
                                      
                                      
                                       <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" onclick="getdatas('/setpoinactionplan-filemanager/${actionplan.actionplan_id }/5','sub-${actionplan.actionplan_id }',0);">ให้คะเเนน 5
                              
                                 </a>
                                         <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" onclick="getdatas('/setpoinactionplan-filemanager/${actionplan.actionplan_id }/4','sub-${actionplan.actionplan_id }',0);">ให้คะเเนน 4
                              
                                 </a>
                                         <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" onclick="getdatas('/setpoinactionplan-filemanager/${actionplan.actionplan_id }/3','sub-${actionplan.actionplan_id }',0);">ให้คะเเนน 3
                              
                                 </a>
                                         <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" onclick="getdatas('/setpoinactionplan-filemanager/${actionplan.actionplan_id }/2','sub-${actionplan.actionplan_id }',0);">ให้คะเเนน 2
                              
                                 </a>
                                         <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" onclick="getdatas('/setpoinactionplan-filemanager/${actionplan.actionplan_id }/1','sub-${actionplan.actionplan_id }',0);">ให้คะเเนน 1
                              
                                 </a>
    						</c:if>
  
                                
                                </div>
                            </div>
                                            </div>
				                                     

				                                            
				                                                       
				                                     
				                                     
				                                        
				                                    </div>
				                