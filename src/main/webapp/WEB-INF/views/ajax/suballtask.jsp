<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <script>  

$("#datatitle").html("${upload_pns.upload_pn_note }");
</script>  
 
                            <!-- Detailed Email Content starts -->
                            <div class="email-scroll-area" style="overflow-y: scroll;">
                              
                                <c:if test="${upload_pn.size() > 0}"> 
                                <div class="row">
                                    <div class="col-12">
                                        <div class="email-label">
                                            <span class="mail-label badge rounded-pill badge-light-success">
                                            <spring:message code="task.alltasks" text="งานที่เกี่ยวข้องทั้งหมด" /> : ${upload_pn.size()}</span>
                                            
                                              
                                          <span class="mail-label badge rounded-pill badge-light-primary">
                                                        <spring:message code="task.Taskready" text="งานที่ต้องทำ" />: <span id="status1">0</span>
                                         </span>
                                            <span class="mail-label badge rounded-pill badge-light-warning">
                                                        <spring:message code="task.Taskreview" text="งานรอตรวจ" /> : <span id="status2">0</span>
                                         </span> 
                                        </div>
                                      
                                        
                                    </div>
                                </div>
                                </c:if>
                               <c:set var="status1" value="0" />
                              <c:set var="status2" value="0" />
                                 <c:forEach items="${upload_pn }" var="row" varStatus="count">  
                                <div class="row">
                                    <div class="col-12">
                                        <div class="card">
                                            <div class="card-header email-detail-head">
                                                <div class="user-details d-flex justify-content-between align-items-center flex-wrap">
                                                    <div class="avatar me-75">
                                                        <img src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="avatar img holder" width="48" height="48" />
                                                    </div>
                                                    <div class="mail-items">
                                                        <h5 class="mb-0">${row.upload_pn_respon }</h5>
                                                        <div class="email-info-dropup dropdown">
                                                            <span role="button" class="dropdown-toggle font-small-3 text-muted" id="card_top01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                               
                                                            </span>
                                                            <div class="dropdown-menu" aria-labelledby="card_top01" style="display:none">
                                                                <table class="table table-sm table-borderless">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td class="text-end">From:</td>
                                                                            <td>demo.user@example.com</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="text-end">To:</td>
                                                                            <td>johndoe@ow.ly</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="text-end">Date:</td>
                                                                            <td>14:58, 29 Aug 2020</td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mail-meta-item d-flex align-items-center">
                                                    <small class="mail-date-time text-muted">${row.active }</small>
                                                    <div class="dropdown ms-50" style="display:none">
                                                        <div role="button" class="dropdown-toggle hide-arrow" id="email_more" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            <i data-feather="more-vertical" class="font-medium-2"></i>
                                                        </div>
                                                        <div class="dropdown-menu dropdown-menu-end" aria-labelledby="email_more">
                                                            <div class="dropdown-item"><i data-feather="corner-up-left" class="me-50"></i>Reply</div>
                                                            <div class="dropdown-item"><i data-feather="corner-up-right" class="me-50"></i>Forward</div>
                                                            <div class="dropdown-item"><i data-feather="trash-2" class="me-50"></i>Delete</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body mail-message-wrapper pt-2">
                                                <div class="mail-message">
                                                    <p class="card-text">  
                                                    <c:if test="${row.status1 > 0}"> 
                                                        <span class="mail-label badge rounded-pill badge-light-primary"><spring:message code="task.Taskready" text="งานที่ต้องทำ" /> : ${row.status1}</span>
                                                     <c:set var="status1" value="${status1+row.status1 }" />
                                                   </c:if>
                                                      <c:if test="${row.status2 > 0}"> 
                                                        <span class="mail-label badge rounded-pill badge-light-warning">
                                                        <spring:message code="task.Taskreview" text="งานรอตรวจ" /> : ${row.status1}</span>
                                                     <c:set var="status2" value="${status2+row.status2 }" />
                                                   </c:if>
                                                    </p>
                                                    <p class="card-text">
                                                    
                                                    
                                                        <br>
<c:if test='${row.upload_pn_private == 1}'>                                                  
    <c:set var="listcheck" value="${Member_name}" />
     <c:set var="list" value=" ${row.upload_pn_respon}${row.upload_pn_app}" />
                                           
                                               <c:set var="listcheck" value="${Member_name}" />
                                          <c:if test='${fn:contains(list,listcheck)}'>             
                                             <a class="btn btn-sm btn-light" href="/duepn-filemanager?id=${row.upload_pn_id}&&step=0&&group=3">
                                                                <spring:message code="btn.view" /> 
                                                                </a>
       </c:if>
</c:if>         
   <c:if test='${row.upload_pn_private == 0}'>
             <a class="btn btn-sm btn-light" href="/duepn-filemanager?id=${row.upload_pn_id}&&step=0&&group=3">
                                                                <spring:message code="btn.view" /> 
                                                                </a>
       </c:if>     
           
       
       
                                                    </p> 
                                                
                                                </div>
                                                  <br>
                                                    <div class="card-footer">
                                                <div class="mail-attachments">
                                                 
                                                    <div class="d-flex flex-column">
                                                    
                                                    
                                                          <c:if test="${row.upload_pn_namefile != ''}"> 
                                                        <a href="#" class="mb-50">
                                                            <img src="/resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18" />
                                                            <small class="text-muted fw-bolder">${row.upload_pn_namefile}</small>
                                                        </a>
                                                           </c:if>
                                                           
                                                                 <c:if test="${row.upload_pn_name2 != ''}"> 
                                                        <a href="#" class="mb-50">
                                                            <img src="/resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18" />
                                                            <small class="text-muted fw-bolder">${row.upload_pn_name2}</small>
                                                        </a>
                                                           </c:if>
                                                                 <c:if test="${row.upload_pn_name3 != ''}"> 
                                                        <a href="#" class="mb-50">
                                                            <img src="/resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18" />
                                                            <small class="text-muted fw-bolder">${row.upload_pn_name3}</small>
                                                        </a>
                                                           </c:if>
                                                                   <c:if test="${row.upload_pn_name4 != ''}"> 
                                                        <a href="#" class="mb-50">
                                                            <img src="/resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18" />
                                                            <small class="text-muted fw-bolder">${row.upload_pn_name4}</small>
                                                        </a>
                                                           </c:if>
                                                                   <c:if test="${row.upload_pn_name5 != ''}"> 
                                                        <a href="#" class="mb-50">
                                                            <img src="/resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18" />
                                                            <small class="text-muted fw-bolder">${row.upload_pn_name5}</small>
                                                        </a>
                                                           </c:if>  
                                                           
                                                    </div>
                                                </div>
                                            </div>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                              
                              
                              
                                   </c:forEach>  
                                   
                                   <script>
                                   
                                   $("#status1").html("${status1}");
                                    $("#status2").html("${status2}"); 
                                    </script>
                                   
                                <div class="row" style="display:none">
                                    <div class="col-12">
                                        <div class="card">
                                            <div class="card-header email-detail-head">
                                                <div class="user-details d-flex justify-content-between align-items-center flex-wrap">
                                                    <div class="avatar me-75">
                                                        <img src="/resources/app-assets/images/portrait/small/avatar-s-18.jpg" alt="avatar img holder" width="48" height="48" />
                                                    </div>
                                                    <div class="mail-items">
                                                        <h5 class="mb-0">Ardis Balderson</h5>
                                                        <div class="email-info-dropup dropdown">
                                                            <span role="button" class="dropdown-toggle font-small-3 text-muted" id="dropdownMenuButton200" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                                abaldersong@utexas.edu
                                                            </span>
                                                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton200">
                                                                <table class="table table-sm table-borderless">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td class="text-end">From:</td>
                                                                            <td>abaldersong@utexas.edu</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="text-end">To:</td>
                                                                            <td>johndoe@ow.ly</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="text-end">Date:</td>
                                                                            <td>4:25 AM 13 Jan 2018</td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mail-meta-item d-flex align-items-center">
                                                    <small class="mail-date-time text-muted">17 May, 2020, 4:14</small>
                                                    <div class="dropdown ms-50">
                                                        <div role="button" class="dropdown-toggle hide-arrow" id="email_more_2" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            <i data-feather="more-vertical" class="font-medium-2"></i>
                                                        </div>
                                                        <div class="dropdown-menu dropdown-menu-end" aria-labelledby="email_more_2">
                                                            <div class="dropdown-item"><i data-feather="corner-up-left" class="me-50"></i>Reply</div>
                                                            <div class="dropdown-item"><i data-feather="corner-up-right" class="me-50"></i>Forward</div>
                                                            <div class="dropdown-item"><i data-feather="trash-2" class="me-50"></i>Delete</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body mail-message-wrapper pt-2">
                                                <div class="mail-message">
                                                    <p class="card-text">Hey John,</p>
                                                    <p class="card-text">
                                                        bah kivu decrete epanorthotic unnotched Argyroneta nonius veratrine preimaginary saunders demidolmen
                                                        Chaldaic allusiveness lorriker unworshipping ribaldish tableman hendiadys outwrest unendeavored
                                                        fulfillment scientifical Pianokoto Chelonia
                                                    </p>
                                                    <p class="card-text">
                                                        Freudian sperate unchary hyperneurotic phlogiston duodecahedron unflown Paguridea catena disrelishable
                                                        Stygian paleopsychology cantoris phosphoritic disconcord fruited inblow somewhatly ilioperoneal forrard
                                                        palfrey Satyrinae outfreeman melebiose
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="card-footer">
                                                <div class="mail-attachments">
                                                    <div class="d-flex align-items-center mb-1">
                                                        <i data-feather="paperclip" class="font-medium-1 me-50"></i>
                                                        <h5 class="fw-bolder text-body mb-0">2 Attachments</h5>
                                                    </div>
                                                    <div class="d-flex flex-column">
                                                        <a href="#" class="mb-50">
                                                            <img src="/resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18" />
                                                            <small class="text-muted fw-bolder">interdum.docx</small>
                                                        </a>
                                                        <a href="#">
                                                            <img src="/resources/app-assets/images/icons/jpg.png" class="me-25" alt="png" height="18" />
                                                            <small class="text-muted fw-bolder">image.png</small>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                             
                                
                                <div class="row" style="display:none">
                                    <div class="col-12">
                                        <div class="card">
                                            <div class="card-body">
                                                <div class="d-flex justify-content-between">
                                                    <h5 class="mb-0">
                                                        Click here to
                                                        <a href="#">Reply</a>
                                                        or
                                                        <a href="#">Forward</a>
                                                    </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                              
                            </div>
                        <!-- Detailed Email Content ends -->
                        
                        
