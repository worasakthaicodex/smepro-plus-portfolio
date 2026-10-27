<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
     <!-- Blog Comment -->
                            <div class="col-12 mt-1" id="blogComment">
                        
		                  
		                  
		                  <ul class="nav nav-tabs card-header-tabs ms-0" id="nav-tabs" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="home-tab" data-bs-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="false"><spring:message code="submitted.status" /></a>
                                        </li>
                                     
                                          <li class="nav-item">
                                            <a class="nav-link " id="profile-tab2" data-bs-toggle="tab" href="#profile2" role="tab" aria-controls="profile" aria-selected="true">
                                            <spring:message code="project.problemsidentified" />
                                           <span > [${problem_list.size() }]</span>
                                            </a>
                                        </li>
                                        
                                        
                                    	<c:if test="${tasked.task_idcheck  == Member_id}">      
                                        <li class="nav-item">
                                            <a class="nav-link " id="profile-tab" data-bs-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="true">
                                            <spring:message code="btn.comment" />
                                          <span id="comment"> [${listcms2.size() + listcms.size() }]</span>
                                            </a>
                                        </li>
                                        </c:if>
                                    </ul>
		                  

                                    <div class="tab-content" id="myTabContent">
                                        <div class="tab-pane fade active show" id="home" role="tabpanel" aria-labelledby="home-tab">
                              
                                             <div id="save_ajax"></div>
                                               <div id="dataplan">
                                                               
    <form action="/app_submitted-filemanager?${_csrf.parameterName}=${_csrf.token}" method="POST" modelAttribute="actionplan"  >  
		                         
		                           
<table>
<tr>
        <th><spring:message code="submitted.submitter" /></th>
        <td >
     ${actionplan.actionplan_responsible}
        </td>
    </tr>
    <tr>
        <th><spring:message code="submitted.status" /></th>
        <td class="  <c:if test="${actionplan != null}">  status-submitted    </c:if>">
            <c:if test="${actionplan != null}">    
    		    <spring:message code="submitted.submitted" />
        </c:if>
          <c:if test="${actionplan == null}">    
    		    <spring:message code="submitted.notsubmitted" />
        </c:if>
        </td>
    </tr>
  
    <tr>
        <th><spring:message code="submitted.timeremaining" /></th>
   
              <c:if test="${timeremaining == 1}">    
                          <td class="status-submitted"> 
       
       <spring:message code="submitted.ontime" />
        </td>
        </c:if>
              <c:if test="${timeremaining == 0}">    
                <td class="error-submitted">
       
       <spring:message code="submitted.late" />
         </td>
        </c:if>
   
           <c:if test="${timeremaining == null}">    
                <td >

         </td>
        </c:if>
               </tr>
    <tr>
        <th><spring:message code="submitted.lastmodified" /></th>
        <td>${actionplan.actionplan_dateadd}</td>
    </tr>
    
       <tr>
        <th><spring:message code="actionplan.actualcost" /></th>
        <td>${actionplan.actionplan_expenses}</td>
    </tr>
    
    <tr>
        <th><spring:message code="submitted.file" /></th>
        <td>
               <c:forEach var="rows" items="${actionplan.file}" >             
				                                            <c:if test='${rows.file_type.equals("image/png") or rows.file_type.equals("image/jpg")  or rows.file_type.equals("image/jpeg")}'>
				                                            
				                                           <a href="/render2-filemanager?file=${rows.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;"> <img src="/document/${rows.file_path}/${rows.file_name}" style="width:100px"></img>
				                                               </a>  
				                                               </c:if> 
				                                               
				                                 <c:if test='${!rows.file_type.equals("image/png") && !rows.file_type.equals("image/jpg")  && !rows.file_type.equals("image/jpeg")}'>
				                                            
				                                           <a href="/render2-filemanager?file=${rows.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">${rows.file_name_original } </img>
				                                               </a>  
				                                               <br>
				                                    </c:if> 
				                                    
				                                </c:forEach>  
        </td>
    </tr>
    <tr>
        <th><spring:message code="submitted.comments" /></th>
        <td>
        
         <c:set var = "newLineChar" value = "<%= \"\n\" %>" />
				                                                      <c:set var = "br" value = "<br/>" />
				                                                  ${fn:replace(actionplan.actionplan_comment,newLineChar,br)}
				                                                
        </td>
    </tr>
    
    
    
    
 
    
    
  <tr>
                
        <th><spring:message code="submitted.inspection_status" /></th>
       
       
       
                <td >
       <select class="form-control" name="submission">
       				<option  value="1"  <c:if test="${actionplan.submission == 1}"> selected</c:if>><spring:message code="submitted.checked" /></option>
       			  <option value="-1" <c:if test="${actionplan.submission == -1}"> selected</c:if>><spring:message code="submitted.failed" /></option>
       </select>
            <input type="hidden" name="actionplan_id" value="${actionplan.actionplan_id}">
              <input type="hidden" name="actionplan_idrfer" value="${actionplan.actionplan_idrfer}">
           </td>
       
     
    </tr>
</table>

<br>
	<c:if test="${tasked.task_idcheck  == Member_id}">  
   <button type="submit"  class="btn btn-primary me-1 waves-effect waves-float waves-light" ><spring:message code="btn.save" /></button>
   </c:if>
   
  </form>
</div>
		                    

                                        </div>
                                        <div class="tab-pane fade " id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                        
                                  
                                    <form method="POST"  id="cmform" name="cmform">

                                        <div>
                                                <input type="hidden" name="cm_idrefer" value="${actionplan.actionplan_id}">
                                                <input type="hidden" name="type" value="actionplan">
                                            <div class="action-tags">
                                                <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label"><spring:message code="comment.add" /></label>
                                                    <textarea type="text" name="cm_title" id="todoTitleAdd" name="todoTitleAdd" class="new-todo-item-title form-control" ></textarea>
                                                </div>
                                                
                                           
                                            </div>
                                            <div class="my-1">
                                                <button type="button" onclick='postdata("/addcm-filemanager?${_csrf.parameterName}=${_csrf.token}","cmform","cmdiv")' class="btn btn-primary add-todo-item me-1 waves-effect waves-float waves-light">Add</button>
                                            
                                               
                                             
                                            </div>
                                        </div>
                                    </form>
                                      <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                    <div class="tab-content" id="myTabContent">
                                      
                                            <div class="tab-pane fade active show" id="activity-tab" role="tabpanel" aria-labelledby="activity-tab">
                                                <h6 class="file-manager-title my-2"><spring:message code="comment.today" /></h6>
                                                
                                   <div id="cmdiv">
                                                
                                    <c:forEach items="${listcms2 }" var="row" varStatus="count">
                                   <div class="d-flex align-items-center mb-2" id="cm-${row.cm_id }">
                                                    <div class="avatar avatar-sm me-50" >
                                                        <img src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="avatar" width="28">
                                                    </div>
                                                    <div class="more-info" style="width:90%">
                                                        <p class="mb-0">
                                                            <span class="fw-bold">${row.cm_user_name }</span> <span class="fw-bold"> ${cm_date } </span>
                                                            <br>
                                                            ${row.cm_title }
                                                       
                                                        </p>
                                                    </div>
                                                       <button type="button" onclick="return getdatas('/delcm-filemanager/${row.cm_id}/actionplan','save_ajax','1');"  class="btn btn-sm btn-outline-danger update-btn waves-effect ">
                                                <spring:message code="btn.delete" text="ลบ" />
                                                </button>
                                          </div>


                               </c:forEach>
                                 </div>
                                              
                                              
                                                <h6 class="file-manager-title mt-3 mb-2"><spring:message code="comment.all" /></h6>
                                            <c:forEach items="${listcms1 }" var="row" varStatus="count">
                                   <div class="d-flex align-items-center mb-2" id="cm-${row.cm_id }">
                                                    <div class="avatar avatar-sm me-50" >
                                                        <img src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="avatar" width="28">
                                                    </div>
                                                    <div class="more-info" style="width:90%">
                                                        <p class="mb-0">
                                                            <span class="fw-bold">${row.cm_user_name }</span> <span class="fw-bold"> ${cm_date } </span>
                                                            <br>
                                                            ${row.cm_title }
                                                       
                                                        </p>
                                                    </div>
                                                       <button type="button"   onclick="return getdata('/delcm-filemanager/${row.cm_id}/${row.type} }');"  class="btn btn-sm btn-outline-danger update-btn waves-effect ">
                                                    <spring:message code="btn.delete" />
                                                </button>
                                          </div>


                               </c:forEach>
                                       
                                             
                                            </div>
                                        </div>
                                            </div>
                            
                           
                                        </div>
                                 
                          <div class="tab-pane fade " id="profile2" role="tabpanel" aria-labelledby="profile-tab">
                                 	    <form method="POST"  id="problems_form" name="problems_form">


      									<input type="hidden" name="task_id" value="${actionplan.actionplan_idrfer}">
                                        <input type="hidden" name="actionplan_id" value="${actionplan.actionplan_id}">
                                        
                                        <div id="subproblems_form" style="display:none">
                                            	<c:if test="${tasked.task_idcheck  == Member_id}">  
  												<%@ include file="formproblems_list.jsp" %>  
  												</c:if>
                                        </div>
                                    </form>
                                    
                                    <br>
                                    
                                    
                                    <div id="problems_list">
                                 		  <%@ include file="problems_list.jsp" %>  
                                     </div>
                                    
                                    
                              </div>
                                 
                                    </div>
                           
		                  
		                  
		                       


                                 
                                
                                    </div>
                                </div>
                            </div>
                            
                            
<script>
$("#task_title").html('<spring:message code="submitted.inspection" />');
</script>                     
                            
                         
                            