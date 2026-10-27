<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

      <c:forEach items="${uploadpn }" var="row" varStatus="count">    
                            
                                 <li class="todo-item <c:if test="${(row.status2 +row.status1 +row.status3) == 0 && uploadtype == 1}">  completed</c:if>" title="ID${row.upload_pn_id} ">
                                      <a href="/duepn-filemanager?id=${row.upload_pn_id}&&step=${row.status0}&&group=3" style="color:black"> 
                                        <div class="todo-title-wrapper">
                                            <div class="todo-title-area">
                                                <c:if test="${row.upload_pn_type == 1}"> <small class="text-nowrap text-muted me-1">${row.upload_pn_date} </small></c:if>
                                    
                                    <c:if test='${row.upload_pn_category.equals("Work plan") }'> <i data-feather="edit-2" class="font-medium-3 me-50" title="Work plan"></i></c:if>
      <c:if test='${row.upload_pn_category.equals("E-FORM DATA") }'><i data-feather='database' class="font-medium-3 me-50" title="E-FORM DATA"></i></c:if>
      <c:if test='${row.upload_pn_category.equals("E-FORM Transactions") }'><i data-feather='link' class="font-medium-3 me-50" title="E-FORM Transactions"></i></c:if>
     <c:if test='${row.upload_pn_category.equals("E-FORM") }'>        <i data-feather='layout' class="font-medium-3 me-50" title="E-FORM"></i></c:if>
                                    
                                                <div class="title-wrapper">
                                                    <div class="form-check">
          
                                                    </div>
                                                    <span class="todo-title">${row.upload_pn_note}  
                                                    <br>
                                                           <span style="font-size:11px;color:#aaa">
                                                    <spring:message code="tables.responsible" text="ผู้รับผิดชอบ" />  : ${row.upload_pn_respon}  ,  <spring:message code="tables.inspector" text="ผู้ดูเเล" /> : ${row.upload_pn_app} 
                                                    </span>
          
                                                    
             
                                                    
                                                </div>
                                            </div>
                                            <div class="todo-item-action">
                                             <c:if test="${row.upload_pn_group != null }">
                                                <div class="badge-wrapper me-1" >
                                                    <span class="badge rounded-pill badge-light-primary">${row.upload_pn_group}</span>
                                                </div>
                                                </c:if>
                                                
                                                
                                                                                        
            <c:if test="${row.upload_pn_type == 0}">    
                  <div class="badge-wrapper me-1">
                   <span class='task__tag task__tag--copyright'>
                     <c:if test="${row.upload_pn_due == 0}">  
                       <spring:message code="due.date" text="ทุกวัน"  />  
                     </c:if> 
                   <c:if test="${row.upload_pn_due == 1}">  
                         <spring:message code="due.week" text="ทุกอาทิตย์" />
                     </c:if> 
                    <c:if test="${row.upload_pn_due == 2}">  
                              <spring:message code="due.month" text="ทุกเดือน"  />
                     </c:if> 
                   </span> 
                     </div>                                          
            </c:if>
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                    <c:if test="${row.status2 > 0}">  
                                                      <div class="badge-wrapper me-1">
                                               
                                                       <small class="text-nowrap text-muted me-1">รอตรวจ</small>
                                                </div>
                                                  </c:if>
                                                
                                                <c:if test="${(row.status2 +row.status1 +row.status3) == 0}"> 
                                                      <div class="badge-wrapper me-1">
                                               
                                                       <small class="text-nowrap text-muted me-1">เสร็จสิ้น</small>
                                                </div>
                                                  </c:if>
                                                
                                          
                                                
                                                
                                                
        
                                 <c:if test="${row.upload_pn_type == 1}">   
         <span class='task__tag task__tag--design'><spring:message code="due.type2" text="กำหนดตามวันที่" /></span>
          </c:if>
                   
                                            
                                           
                                                 <c:if test="${(row.status2 +row.status1 +row.status3) >  0}"> 
                                                 <div class="avatar bg-light-primary">
                                                    <div class="avatar-content">  ${row.status2 +row.status1 +row.status3}</div>
                                                    
                                                    
                                                </div>
                                        </c:if>
                          
                                
                                                
                                            </div>
                                        </div>
                                             </a>
                                    </li>
                               
                                    </c:forEach>
                                    
<script>                                     
 
                feather.replace({
                    width: 14,
                    height: 14
                });
         
      


</script>                             
                                    
                                    