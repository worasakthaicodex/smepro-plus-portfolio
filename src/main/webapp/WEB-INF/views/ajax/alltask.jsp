<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
                                <div class="card-body">
                                    <!-- Nav tabs -->
                                    <ul class="nav nav-tabs nav-fill" id="myTab" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="home-tab-fill" data-bs-toggle="tab" href="#home-fill" role="tab" aria-controls="home-fill" aria-selected="true">ระหว่างดำเนินการ</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" id="profile-tab-fill" data-bs-toggle="tab" href="#profile-fill" role="tab" aria-controls="profile-fill" aria-selected="false">เสร็จสิ้น</a>
                                        </li>
                                     
                                    </ul>

                                    <!-- Tab panes -->
                                    <div class="tab-content pt-1">
                                        <div class="tab-pane active" id="home-fill" role="tabpanel" aria-labelledby="home-tab-fill">
                                    
                                      <div class="row">
                                       <c:forEach items="${task1 }" var="row" varStatus="count">                          
                                               
                                               
                    <div class="col-xl-4 col-lg-6 col-md-6" >
                        <div class="card">
                        
                  <c:if test="${row.task_alert == 1 }">
                        <span class="badge rounded-pill bg-primary badge-up cart-item-count">รอดำเนินการ</span>
                  </c:if>
                        
                            <div class="card-body">
                                <div class="d-flex justify-content-between">
                                    <span id="t_${row.task_id }">0 users</span>
                             
                                    
                                <c:if test='${row.task_iduser != null and row.task_iduser != "" }'>
		                                    <ul class="list-unstyled d-flex align-items-center avatar-group mb-0">
		                                    <c:set var="namesArray" value="${fn:split(row.task_iduser, ',')}" />
		
														<c:forEach var="nameItem" items="${namesArray}" begin="0" end="5">
														   
														   
														    <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top"
														        title="${nameItem}" class="avatar avatar-sm pull-up"
														        data-bs-original-title="">
														        <img class="rounded-circle" src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="Avatar">
														    </li>
														    
														</c:forEach>
		                                    </ul>
					               <script>
					                   $("#t_${row.task_id }").html("${count.count} users");
					               </script>
                                </c:if>
                                </div>
                                <div class="d-flex justify-content-between align-items-end mt-1 pt-25">
                                    <div class="role-heading">
                                        <h4 class="fw-bolder">${row.task_name }</h4>
                                        
                                        
                                        <a href="javascript:;" class="role-edit-modal" >
                                            <small class="fw-bolder">
                                            
                                  <c:if test="${row.task_status == 1 }">
                                            <span class="badge rounded-pill badge-light-info">ระหว่างดำเนินงาน</span>
                                            </small>
                                   </c:if>
                                       <c:if test="${row.task_status == 2 }">
                                            <span class="badge rounded-pill badge-light-success">เสร็จสิ้น</span>
                                            </small>
                                   </c:if>        
                                        </a>
                                        
                                    </div>
                                  
                             
                                </div>
                                <hr>
                                   <p style="font-size:10px">${row.task_datestart } - ${row.task_dateend }</p>
                                <div class="d-flex justify-content-between align-items-center">
                                     
                                            <a href="#">
                                                <div class="d-flex align-items-center">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-message-square font-medium-1 text-body me-50"><path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path></svg>
                                                    <span class="text-body fw-bold">${row.task_cm } กิจกรรม</span>
                                                </div>
                                            </a>
                                            <a href="/task-filemanager/${row.task_id }" class="fw-bold">จัดการ</a>
                                        </div>
                            </div>
                        </div>
                    </div>
            
                     </c:forEach> 
                                     
                                       </div>
                                        </div>
                                        <div class="tab-pane" id="profile-fill" role="tabpanel" aria-labelledby="profile-tab-fill">
                                                     
                                                     
                                                   <div class="row">   
                                                     <c:forEach items="${task2 }" var="row" varStatus="count">                          
                                               
                                               
                    <div class="col-xl-4 col-lg-6 col-md-6" >
                        <div class="card">
                        
                  <c:if test="${row.task_alert == 1 }">
                   <span class="badge rounded-pill bg-primary badge-up cart-item-count">รอดำเนินการ</span>
                  </c:if>
                        
                            <div class="card-body">
                                <div class="d-flex justify-content-between">
                                    <span id="t_${row.task_id }">0 users</span>
                             
                                    
                                <c:if test='${row.task_iduser != null and row.task_iduser != "" }'>
		                                    <ul class="list-unstyled d-flex align-items-center avatar-group mb-0">
		                                    <c:set var="namesArray" value="${fn:split(row.task_iduser, ',')}" />
		
														<c:forEach var="nameItem" items="${namesArray}" begin="0" end="5">
														   
														   
														    <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top"
														        title="${nameItem}" class="avatar avatar-sm pull-up"
														        data-bs-original-title="">
														        <img class="rounded-circle" src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="Avatar">
														    </li>
														    
														</c:forEach>
		                                    </ul>
					               <script>
					                   $("#t_${row.task_id }").html("${count.count} users");
					               </script>
                                </c:if>
                                </div>
                                <div class="d-flex justify-content-between align-items-end mt-1 pt-25">
                                    <div class="role-heading">
                                        <h4 class="fw-bolder">${row.task_name }</h4>
                                        <a href="javascript:;" class="role-edit-modal" >
                                            <small class="fw-bolder">
                                            
                                  <c:if test="${row.task_status == 1 }">
                                            <span class="badge rounded-pill badge-light-info">ระหว่างดำเนินงาน</span>
                                            </small>
                                   </c:if>
                                       <c:if test="${row.task_status == 2 }">
                                            <span class="badge rounded-pill badge-light-success">เสร็จสิ้น</span>
                                            </small>
                                   </c:if>        
                                        </a>
                                                
                                    </div>
                                  
                             
                                </div>
                                <hr>
                                   <p style="font-size:10px">${row.task_datestart } - ${row.task_dateend }</p>
                                <div class="d-flex justify-content-between align-items-center">
                                
                                            <a href="#">
                                                <div class="d-flex align-items-center">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-message-square font-medium-1 text-body me-50"><path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path></svg>
                                                    <span class="text-body fw-bold">${row.task_cm } กิจกรรม</span>
                                                </div>
                                            </a>
                                            <a href="/task-filemanager/${row.task_id }" class="fw-bold">จัดการ</a>
                                        </div>
                            </div>
                        </div>
                    </div>
            
                     </c:forEach> 
                                       
                                         </div>
                                        </div>
                                       
                                    </div>
                                </div>
                         
   