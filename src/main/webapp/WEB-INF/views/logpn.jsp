<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>Log pn</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
                  
                  
                  
                  
                   <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title"> <spring:message code="record.details"  /></h4>
                            </div>
                            
                             
                            
                            
                            <div class="card-body"> 
                           
                                     <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                1.<spring:message code="tables.record" text="ชื่อบันทึก"  />:</label>
                                                <div class="col-sm-9">
                                                       ${upload_pn.upload_pn_name }
                                                </div>
                                            </div>
                        
                         
                                <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                               2.<spring:message code="tables.record" text="ชื่อบันทึก"  />:</label>
                                                <div class="col-sm-9">
                                                       ${upload_pn.upload_pn_name2 }
                                                </div>
                                                
                                                
                                            </div>
                                   
                                     
   						     
                                               <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                3.<spring:message code="tables.record" text="ชื่อบันทึก"  />:</label>
                                                <div class="col-sm-9">
                                                       ${upload_pn.upload_pn_name3 }
                                                </div>
                                            </div>
                                            
                                           
                                       
                                               <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                4.<spring:message code="tables.record" text="ชื่อบันทึก"  />:</label>
                                                <div class="col-sm-9">
                                                       ${upload_pn.upload_pn_name4 }
                                                </div>
                                            </div>
                                            
                            	       
                                               <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                5.<spring:message code="tables.record" text="ชื่อบันทึก"  />:</label>
                                                <div class="col-sm-9">
                                                       ${upload_pn.upload_pn_name5 }
                                                </div>
                                            </div>
                                            
                                            
                                            
                               <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                <spring:message code="tables.due" text="กำหนด" /> :</label>
                                                <div class="col-sm-9">
                                                 ${report_pn.report_pn_date }
                                                </div>
                                            </div>
                            
                               <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                <spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /> :</label>
                                                <div class="col-sm-9">
                                                 ${report_pn.report_pn_respon }
                                                </div>
                                            </div>
                                 <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                               <spring:message code="tables.inspector" text="ผู้ดูเเล" /> :</label>
                                                <div class="col-sm-9">
                                                 ${report_pn.report_pn_app }
                                                </div>
                                            </div>
                                         <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                               Location :</label>
                                                <div class="col-sm-9">
                                                 ${Pathfull }
                                                </div>
                                            </div>       
                                            
                                        <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                              <spring:message code="tables.dateadd"  /> :</label>
                                                <div class="col-sm-9">
                                             ${report_pn.report_pn_dateupload }
                                                </div>
                                            </div>            
                                                <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                               <spring:message code="tables.dues"  /> :</label>
                                                <div class="col-sm-9">
                                                ${report_pn.report_pn_datecheck }
                                                </div>
                                            </div>    
                            <div class="row">
                            
                       
                            
                      <div class="col-md-1">
                                    <c:if test="${idoc ==  null }">   
                                <a  href="/duepn-filemanager?id=${upload_pn.upload_pn_id }"  class="btn btn-info" >previous</a><br><br>
                                </c:if>
                                   <c:if test="${idoc !=  null }">   
                                 <a  href="/duepn-filemanager?id=${upload_pn.upload_pn_id }&&idoc=yes"  class="btn btn-info" >previous</a><br><br>
                                   </c:if>
                             </div>
                            
                                <div class="col-md-1">           
                            <a  href="/addfiles-filemanager?id=${upload_pn.upload_pn_idrefer }&&upload_pn=${upload_pn.upload_pn_id }&&level=${level}&&due=${report_pn.report_pn_date }"  class="btn btn-primary me-1 waves-effect waves-float waves-light" >อัพโหลด</a><br><br>
                            </div>
                            
                              </div>
                            
                            <div class="table-responsive">
                   <table class="table" style="font-size:12px">
                                    <thead>
                                        <tr>
                                            <th><spring:message code="tables.No" /></th>
                                                <th><spring:message code="comment.date" /></th>
                                            <th style="width:15%"><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /></th>
                                            <th><spring:message code="file.name" /></th>
                                         
                                             <th>Eform</th>
                                             <th><spring:message code="comment.note" /></th>
                                              <th><spring:message code="tables.status" /></th>
                                            <th style="width:20%"><spring:message code="comment.action" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${log }" var="row" varStatus="count">
                                         
                     
                                        <tr>
                                        
                                            <td>${count.count }</td>
                                            <td>${row.log_date } ${row.log_time }</td>
                                            <td>${row.user_name }</td>
                                               <td>   
                                               <a href="/subfileinfos-filemanager?file=${row.log_file }"  target="_blank">
                                               ${row.log_file}
                                               </a>
                                                 </td>
                                               
                                               <td>  
                                      <c:if test="${upload_pn.upload_pn_monitor >   0 }">  
                                                <a href="/Eform-filemanager/${row.log_id }/${upload_pn.upload_pn_monitor }"  target="_blank">
                                              E-form
                                               </a>
                                        </c:if>
                                               </td>
                                           
                                        
                                             <td>
                                             <c:set var="color" value="" />
                     					<c:if test="${row.log_flow ==  0 }">   
                      							<spring:message code="status.waitingtocheck" text="รอตรวจ"  var="status"/>
                      						<c:set var="color" value="black" />
                     					 </c:if>
                                        <c:if test="${row.log_flow ==  1 }">   
                      						<spring:message code="status.success" text="อนุมัติเเล้ว"  var="status"/>
                      							<c:set var="color" value="green" />
                     					 </c:if>
                     					    <c:if test="${row.log_flow ==  -1 }">   
                      						<spring:message code="status.reject" text="ไม่อนุมัติ"  var="status"/>
                      							<c:set var="color" value="red" />
                     					 </c:if>
                                             ${row.log_note }
                                             </td>
                                             
                                          <td style="color: ${color}">${status }</td>
                                             <td>
                                        <c:if test="${row.log_flow ==  0 }">          
                                          <c:if test="${Member_name ==  upload_pn.upload_pn_app }">       
							          <a href="/appdocument-filemanager/${row.log_id}" class="btn btn-sm btn-success" onclick="return confirm('<spring:message code="action.app" text="คุณต้องการอนุมัติไฟล์นี้" />');">
							          <spring:message code="btn.edit2" text="อนุมัติ" /></a>

            							<a href="#" class="btn btn-sm btn-danger"  onclick="reject(${row.log_id})" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard">
							          <spring:message code="btn.edit2" text="ไม่อนุมัติ" /></a>
								
								  </c:if>
                                   	  </c:if>
                                               <c:if test="${row.log_flow !=  1 }">        
                                                  <c:if test="${Member_name ==  row.user_name }"> 
								     
								     		<a href="/undocument-filemanager/${row.log_id}" class="btn btn-sm btn-danger"  onclick="return confirm('<spring:message code="action.del" text="คุณต้องการลบไฟล์นี้" />');"   >
							          <spring:message code="btn.del2" text="นำออก" /></a>     
							          
							          	<a href="/editdocument-filemanager/${row.log_id}" class="btn btn-sm btn-light"    >
							          <spring:message code="btn.edit" text="แก้ไข" /></a>    
								 </c:if>
                                     </c:if>        
                                             
                                             </td>
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table>
                                
                                
                         
                                
                                </div>
                   </div>   </div>
                    </div>
                    </div>
                    
                    
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><spring:message code="comment.comment" /></h4>
                                </div>
                                <div class="card-body">
                                     
                                <div class="row">
                        <div class="col-12">
                            <div class="card">
                      
                                <div class="card-body">
                                 <form:form method="POST" modelAttribute="comment" action="/commentrecord-log">
                                 
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                   <input  type="hidden" name="upload_pn_idrefer" value="${idrefer }">
                                    <input  type="hidden" name="upload_pn_id" value="${id }">
                                          <input  type="hidden" name="comment_idrefer" value="${id }">
                                          
                                      <c:if test="${comment.comment_id > 0  }">      
                                    <input  type="hidden" name="comment_id" value="${comment.comment_id}">
                                    </c:if>
                                    
                                      <input  type="hidden" name="comment_type" value="record">   
                                        <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="comment.comment" /></label>
                                                    <textarea id="first-name-column" class="form-control"  name="comment_comment" >${comment.comment_comment }</textarea>
                                                </div>
                                                  <div class="mb-1">
                                                    <label class="form-label" for="last-name-column"><spring:message code="comment.note" /></label>
                                                    <input type="text" id="last-name-column" class="form-control" name="comment_note" value="${comment.comment_note }">
                                                </div>
                                            </div>
                                          
                                      
                                         
                                   
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">Submit</button>
                                          
                                            </div>
                                        </div>
                                    </form:form> 
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <br>  <br>
              <table class="table table-borderless">
                                    <thead>
                                        <tr>
                                            <th>
                                            	<spring:message code="comment.date" />
                                            </th>
                                            <th><spring:message code="comment.comment" /></th>
                                             <th><spring:message code="comment.note" /></th>
                                  
                                                  <th><spring:message code="comment.add" /></th>
                                                    <th><spring:message code="comment.action" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                     <c:forEach items="${comments }" var="row" varStatus="count">
                                         
                     
                                        <tr>
                                            <td>${row.comment_date }</td>
                                            <td>${row.comment_comment }</td>
                                            <td>${row.comment_note }</td>
                                             <td>${row.comment_name }</td>
                                            <td>  
                                            
                                 <c:if test="${Member_name ==  row.comment_name  }">                
							   <a href="/lohpn-filemanager?comment_id=${row.comment_id}&&idrefer=${idrefer }&&id=${id }" class="btn btn-primary waves-effect waves-float waves-light">
							         <spring:message code="comment.edit" /></a>
							        </c:if>
							            <c:if test="${Member_name ==  row.comment_name  or report_pn.report_pn_app}">  
							    <a href="/delcomment-log?comment_id=${row.comment_id}&&upload_pn_idrefer=${idrefer }&&upload_pn_id=${id }" class="btn btn-danger waves-effect waves-float waves-light" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />');">
							          <spring:message code="comment.del" /></a>
							         </c:if>
							          
							          
							          </td>        
                                        </tr>
                                       </c:forEach>
                                    </tbody>
                                </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>


        <!-- add new card modal  -->
                <div class="modal fade" id="addNewCard" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="dar.uncheck" /></h1>
                              

                                <!-- form -->
                                <form  action="/rejectdocument-filemanager"id="addNewCardValidation" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber"><spring:message code="dar.reason" /></label>
                                        <div class="input-group input-group-merge">
                                       
                                            <input id="modalAddCardNumber" name="note" class="form-control add-credit-card-mask" type="text" required/>
                                            <span class="input-group-text cursor-pointer p-25" id="modalAddCard2">
                                                <span class="add-card-type"></span>
                                            </span>
                                        </div>
                                          <input type="hidden" name="id" id="id" value=""> 
                                    </div>

                                   
                                

                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1">Save</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->

<%@ include file="common/mainfoot.jspf" %>  
 

    <script>
    
    function reject(id){
    	$("#id").val(id);
    }
    
 
    $('.trigger').click(function(e){
      e.preventDefault();
      
      var childUl = $(this).siblings("ul.tree-parent");
      
      if( childUl.hasClass('open') ){
        
        childUl.removeClass('open');
      } else {
        
        childUl.addClass('open');
      }
      
    });
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
        document.getElementById("file").onchange = function() {
    document.getElementById("form").submit();
};



    </script>
</body>


