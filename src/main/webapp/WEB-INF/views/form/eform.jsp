<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>




 <div class="modal fade" id="addNewCards2" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog   <c:if test='${eformfields.size() <= 9 }'>modal-lg</c:if><c:if test='${eformfields.size() > 9 }'>modal-xl</c:if>  modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                       
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="forms.Fillininformation"  /></h1>

                           <form:form method="POST" modelAttribute="edata"  action="/addfiles2-filemanager?${_csrf.parameterName}=${_csrf.token}" onsubmit="return validateForm()"  id="form" enctype="multipart/form-data"> 
                      
                             
                                <span id="aboutdata" style="display:none;padding:10px;">
                                <div class="demo-spacing-0">
                                <div class="alert alert-primary alert-dismissible fade show" role="alert">
                                            <div class="alert-body">
                                              
                                             <spring:message code="forms.editinfo"  />
            <button type="button"   onclick="unedit()" class="btn btn-sm btn-light me-1 waves-effect waves-float waves-light">
              <spring:message code="btn.cancel"  />
           </button>
           
           
           
                                            </div>
                               
                                        </div>
                                        </div>
                                </span>
                                
                                
                    
                                <div class="card-body" style="overflow:hidden">
                      
     
                              
                                        <div class="row" >
                              
               
                              

<input type="hidden" name="data_planid"  id="data_planid"  value="${upload_pn.upload_pn_id  }">                                                           
<input type="hidden" name="data_eformid"  id="data_eformid"  value="${eformid+0 }">                                        
<input type="hidden" name="data_eformrv"  id="data_eformrv"  value="${eformrv +0}">  
<input type="hidden" name="data_dateplan"  id="data_dateplan"  value="-">
<input type="hidden" name="report_id"  id="report_id"  value="0">  	   
<input type="hidden" name="data_id"  id="data_id"  value="0">  	  	  
<input type="hidden" name="data_plantype"  id="data_plantype"  value="${plantype }">  	 
<input type="hidden" name="edit"  id="edit"  value="0">     
<input type="hidden" name="port" value="${port}">    
<input type="hidden" name="data_filetype"  id="data_filetype"  value="">     


   <input type="hidden"   id="orders" class="form-control" title=""   value="0" readonly>
   <input type="hidden"    id="balance_befores" class="form-control" title="สต๊อก"  value="0" readonly>
   <input type="hidden"    id="amounts" class="form-control" title="สะสม"  value="0" readonly>


    <c:if test='${eformfields.size() > 9 }'>     
       <c:set var="size" value="${eformfields.size() / 2 }" />
       <c:set var="mdrows" value="4" />
       <c:set var="subrow" value="block" />
    </c:if>
       <c:if test='${eformfields.size() <= 9 }'>     
       <c:set var="mdrows" value="6" />
      <c:set var="subrow" value="none" />
       <c:set var="size" value="${eformfields.size() }" />
    </c:if>
    
      <c:set var="lot" value="0" />
   <div class="col-md-${mdrows } col-12">
                     <c:forEach items="${eformfields }" begin="0" end="${size }"  var="row" varStatus="count">
                                 <c:set var="title" value="${row.field_label }" />   
                       <c:set var="appfield" value="${upload_pn.upload_pn_appfield }" />
                          <c:if test="${!fn:contains(appfield,row.field_label)}"> 
                             
                        
 
                                         
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column" 
                                                    <c:if test='${row.field_cal_type2 == "verification" }' >title="verification ระบบจะทำการตรวจสอบว่าค่านี้ว่ามีอยู่หรือไม่"</c:if>
                                                    <c:if test='${row.field_cal_type2 == "register" }' >title="register ระบบจะทำการลงทะเบียนค่านี้"</c:if>
                                                    >
                                                                          <c:if test='${row.field_cal_type2 == "verification" }' > ***</c:if>
                                                                          <c:if test='${row.field_cal_type2 == "register" }' > *</c:if>
                                                                          
                                                                          
                                                    ${title }</label>
                                                    
                                                 <c:if test='${row.field_type.equals("select")   }'>     
                                           
                                                 <select name="data_value${count.count }"  id="data_value${row.field_id }" class="form-select ${row.field_cal_type }"  onchange="copyValueToClass('${row.field_cal_type}')" >
                                                 </select>
                                                 
                                                 
                                               
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }" id="action_${row.field_cal_type }"  value="${row.field_cal_type2 }"/>
                                                  </c:if>
                                                  
                                    
                                           
                                           
                                           
                                   
                                           
                                           
                                           
                                                 
                                                   <c:if test='${row.field_type.equals("text")}'>   
                                                   
                                               
                                                      
                                                 <input type="text" name="data_value${count.count }"  id="data_value${row.field_id }" 
                                                 class="autocomplete-input ${row.field_cal_type } form-control"  onchange="copyValueToClass('${row.field_cal_type}')">
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }"   value="${row.field_cal_type2 }"/>
                                                  </c:if>
                                                  
                                                  
                                                  
                                    
                                                  
                                                    <c:if test='${row.field_type.equals("number")}'>
                                                  
                                            					      <input type="number" step="0.01" min="0" name="data_value${count.count }"  
                                            					      id="data_value${row.field_id }" 
                                            					        class="autocomplete-input ${row.field_cal_type } form-control"  onchange="copyValueToClass('${row.field_cal_type}')">
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }" id="action_${row.field_cal_type }"  value="${row.field_cal_type2 }"/>

                                             
                                                  </c:if>
                                                 
                                                 
                                         
                                                 
                                                    <c:if test='${row.field_type.equals("textarea")}'>      
                                                 <textarea name="data_value${count.count }"  id="data_value${row.field_id }" class="form-control"></textarea>
                                                  </c:if>
                                              <c:if test='${row.field_type.equals("date")}'>      
                                               <input type="date" name="data_value${count.count }"  id="data_value${row.field_id }" class="form-control">
                                                  </c:if>
                                                  
                                                   <c:if test='${row.field_type.equals("time")}'>      
                                              <input type="time" class="form-control  mydata  ${row.field_cal_type }"  name="data_value${count.count }" id="data_value${row.field_id }" 
                                               placeholder="hh:mm"  onchange="copyValueToClass('${row.field_cal_type}')">
                                          
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }"   value="${row.field_cal_type2 }"/>
                                                  </c:if> 
                                                  
                                               <c:if test='${row.field_type.equals("status")}'>      
                                               <div>
								                           <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
									                       <input type="radio" class="btn-check mydata" value="Good"  name="data_status" id="btnradio1" autocomplete="off" >
									                       <label class="btn btn-outline-success waves-effect" for="btnradio1">Good</label>
								
									                       <input type="radio" class="btn-check mydata" value="Normal"   name="data_status" id="btnradio2" autocomplete="off" checked>
									                       <label class="btn btn-outline-warning waves-effect" for="btnradio2">Normal</label>
								
									                       <input type="radio" class="btn-check mydata" value="Bad"  name="data_status" id="btnradio3" autocomplete="off">
									                       <label class="btn btn-outline-danger waves-effect" for="btnradio3">Bad</label>
									                       </div>
									                           </div>
                                                  </c:if> 
                                                  
                                                  
                                                  
                                                    <c:if test='${row.field_type.equals("checkbox")}'>
                                                 <br>      
                                                <span id="data_value${row.field_id }"></span>
                                                    </c:if>
                                             
                                           <c:set var="totaltitle" value="${count.count }" />
                                            
                                            
                                            <c:if test='${totaltitle == null}'>  <c:set var="totaltitle" value="1" />  </c:if> 
                                            
                                    <input type="hidden" name="data_title${count.count }"  id="data_title${row.field_id }"  value="${title }">     
                                   
                                   
                                    <input type="hidden" name="field_cal_type"  id="field_cal_type"  value="${row.field_cal_type }">         
                                     <input type="hidden" name="field_field_no"  id="field_field_no"  value="${row.field_field_no }">    
                                     
                                    </div>   
                                    
                                       </c:if> 
                              <c:if test="${fn:contains(appfield,row.field_label)}"> 
                                     <input type="hidden" class="form-control time-mask mydata"  name="data_value${count.count }" id="data_value${row.field_id }"  >
                                     <input type="hidden" name="data_title${count.count }"  id="data_title${row.field_id }"  value="${title }">     
                                    <input type="hidden" name="field_cal_type"  id="field_cal_type"  value="${row.field_cal_type }">         
                                     <input type="hidden" name="field_field_no"  id="field_field_no"  value="${row.field_field_no }">   
                                       <input type="hidden" name="data_title${count.count }"  id="data_title${row.field_id }"  value="${title }">     
                              </c:if>         
                        </c:forEach>
                        
                        
                            <c:if test='${lot == 0    }'>     
                                         <div class="lot" style="display:none"></div>
                                        </c:if>   
                                           
                        
                            </div> 
                            
                            
                            <div class="col-md-${mdrows } col-12" style="display:${subrow}">
                     <c:forEach items="${eformfields }" begin="${size+1 }"   var="row" varStatus="count2">
                                <c:set var="title" value="${row.field_label }" />
                             
                               <c:set var="appfield" value="${upload_pn.upload_pn_appfield }" />
                          <c:if test="${!fn:contains(appfield,row.field_label)}"> 
                             
                             
                           
 
                                         
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"     
                                                        <c:if test='${row.field_cal_type2 == "verification" }' >title="verification ระบบจะทำการตรวจสอบว่าค่านี้ว่ามีอยู่หรือไม่"</c:if>
                                                    <c:if test='${row.field_cal_type2 == "register" }' >title="register ระบบจะทำการลงทะเบียนค่านี้"</c:if>
                                                    >
                                                                          <c:if test='${row.field_cal_type2 == "verification" }' > ***</c:if>
                                                                          <c:if test='${row.field_cal_type2 == "register" }' > *</c:if>
                                                     
                                                     
                                                    ${title }</label>
                                                    
                                                 <c:if test='${row.field_type.equals("select") && !row.field_cal_type.equals("product") &&  !row.field_cal_type.equals("lot")  }'>     
                                           
                                                 <select name="data_value${count2.count+totaltitle }"  id="data_value${row.field_id }" class="form-select ${row.field_cal_type }"    onchange="copyValueToClass('${row.field_cal_type}')" >
                                           
                                                 </select>
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }" id="action_${row.field_cal_type }"  value="${row.field_cal_type2 }"/>
                                                  </c:if>
                                                  
                                
                                           
                             
                             
                             
                             
                             
                             
                                                 
                                                 
                                                   <c:if test='${row.field_type.equals("text")}'>      
                                                 <input type="text" name="data_value${count2.count+totaltitle }"  id="data_value${row.field_id }" 
                                                 class="autocomplete-input ${row.field_cal_type } form-control"  onchange="copyValueToClass('${row.field_cal_type}')">
                                                      <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                  </c:if>
                                                  
                                            
                                            
                                            
                                                           <c:if test='${row.field_type.equals("number")}'>
                                                  
                                            			<input type="number" step="0.01" min="0" name="data_value${count2.count+totaltitle }"  
                                            			 id="data_value${row.field_id }" class="autocomplete-input ${row.field_cal_type } form-control"  
                                            			 onchange="copyValueToClass('${row.field_cal_type}')">
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }" id="action_${row.field_cal_type }"   value="${row.field_cal_type2 }"/>

                                             
                                                  </c:if>
                                                 
                                                 
                                                 
                                        
                                            
                                                 
                                                    <c:if test='${row.field_type.equals("textarea")}'>      
                                                 <textarea name="data_value${count2.count+totaltitle }"  id="data_value${row.field_id }" class="form-control"></textarea>
                                                  </c:if>
                                              <c:if test='${row.field_type.equals("date")}'>      
                                               <input type="date" name="data_value${count2.count+totaltitle }"  id="data_value${row.field_id }" class="form-control">
                                                  </c:if>
                                                  
                                                   <c:if test='${row.field_type.equals("time")}'>      
                                              <input type="time" class="form-control mydata  ${row.field_cal_type }"  name="data_value${count2.count+totaltitle }" id="data_value${row.field_id }"  
                                              placeholder="hh:mm"     class="autocomplete-input ${row.field_cal_type } form-control"  onchange="copyValueToClass('${row.field_cal_type}')">
                                              
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }"   value="${row.field_cal_type2 }"/>
                                              
                                              
                                                  </c:if> 
                                                  
                                                 <c:if test='${row.field_type.equals("status")}'>    
                                                   <div>  
								                           <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
									                       <input type="radio" class="btn-check mydata" value="Good"  name="data_status"id="btnradio1" autocomplete="off" >
									                       <label class="btn btn-outline-success waves-effect" for="btnradio1">Good</label>
								
									                       <input type="radio" class="btn-check mydata" value="Normal"   name="data_status" id="btnradio2" autocomplete="off" checked>
									                       <label class="btn btn-outline-warning waves-effect" for="btnradio2">Normal</label>
								
									                       <input type="radio" class="btn-check mydata" value="Bad"  name="data_status" id="btnradio3" autocomplete="off">
									                       <label class="btn btn-outline-danger waves-effect" for="btnradio3">Bad</label>
									                       </div>
									                        </div>
                                                  </c:if> 
                                                  
                                                  
                                                    <c:if test='${row.field_type.equals("checkbox")}'>
                                                 <br>      
                                                <span id="data_value${row.field_id }"></span>
                                                    </c:if>
                                                </div>
                                         
                                            
                                    <input type="hidden" name="data_title${count2.count+totaltitle }"  id="data_title${row.field_id }"  value="${title }">       
                                     
                                     </c:if>
                                        <c:if test="${fn:contains(appfield,row.field_label)}"> 
                                     <input type="hidden" class="form-control time-mask mydata"  name="data_value${count2.count+totaltitle }" id="data_value${row.field_id }"  >
                                     <input type="hidden" name="data_title${count.count }"  id="data_title${row.field_id }"  value="${title }">     
                                    <input type="hidden" name="field_cal_type"  id="field_cal_type"  value="${row.field_cal_type }">         
                                     <input type="hidden" name="field_field_no"  id="field_field_no"  value="${row.field_field_no }">    
                                       <input type="hidden" name="data_title${count2.count+totaltitle }"  id="data_title${row.field_id }"  value="${title }">       
                              </c:if>   
                                                             
                        </c:forEach>
                            </div> 
                            
                     
                        
                        
                        
                                               <div class="col-md-${mdrows } col-12">
                                               
  
                                        
                                                <div class="mb-1">
                <input type="hidden" name="id" id="id" value="${idrefer }"  >
               <input type="hidden" name="level" id="level" value="${level }"  >
               <input type="hidden" name="upload_pn_ids" id="upload_pn_ids" value="${upload_pn.upload_pn_id }"  > 
               <input type="hidden" name="log_id" id="log_id" value="0"  > 
               
               
                 <c:if test="${upload_pn.upload_pn_filecheck == 1}">  
               <c:set var="filecheck" value="required" />
                 </c:if>
                             <c:if test="${ not empty upload_pn.upload_pn_name }">
                        <label class="form-label" for="first-name-column">1.${upload_pn.upload_pn_name }</label>  <br>
                                     <div class="input-group mb-1">
                                             <span id="filedata1">  </span>
                     		<span id="filedatas1">
                              <input type="file" name="file1" id="files" class="form-control"   ${filecheck }  >
                 		</span>	
                            </div>
                          
                           </c:if>
                           
                              <c:if test="${  empty upload_pn.upload_pn_name }">
                                    <input type="file" name="file1" id="files" class="form-control"    style="display:none"  >
                            </c:if>
                            
                            
                            
                               <c:if test="${not empty  upload_pn.upload_pn_name2 }">
                               <label class="form-label" for="first-name-column">2.${upload_pn.upload_pn_name2 }</label><br>
                                      <div class="input-group mb-1">
                              
                                      <span id="filedata2">  </span>
                           <span id="filedatas2">
                              <input type="file" name="file2" id="files2" class="form-control"   ${filecheck }   >
                     		</span>	
                         
                            </div>
                             </c:if>
                                <c:if test="${not empty  upload_pn.upload_pn_name3 }">
                               <label class="form-label" for="first-name-column">3.${upload_pn.upload_pn_name3 }</label><br>
                                      <div class="input-group mb-1">
                              
                                      <span id="filedata3">  </span>
                                            <span id="filedatas3">
                              <input type="file" name="file3" id="files3" class="form-control"   ${filecheck }   >
                  </span>	
                         
                            </div>
                             </c:if>
                                <c:if test="${not empty  upload_pn.upload_pn_name4}">
                               <label class="form-label" for="first-name-column">4.${upload_pn.upload_pn_name4 }</label><br>
                                      <div class="input-group mb-1">
                                <span id="filedata4">  </span>
                            <span id="filedatas4">
                              <input type="file" name="file4" id="files4" class="form-control"   ${filecheck }   >
                           </span>	
                            </div>
                             </c:if>
                                <c:if test="${not empty  upload_pn.upload_pn_name5 }">
                                
                       
                               <label class="form-label" for="first-name-column">5.${upload_pn.upload_pn_name5 }</label><br>
                                      <div class="input-group mb-1">
                           <span id="filedata5">  </span>
                          <span id="filedatas5">
                              <input type="file" name="file5" id="files5" class="form-control"   ${filecheck }    >
         				  </span>
                            </div>
                             </c:if>
                            
                            
                   
                            
                            
                                                </div>
                                                  <div class="mb-1" style="display:none">
                                                    <label class="form-label" for="first-name-column"><spring:message code="common.userrefer"  /></label>
                                           
                                            
                                          <div class="input-group mb-1">
                               <select  class="form-select" name="namerefer" id="namerefer">
                               <c:set var="namesArray" value="${fn:split(upload_pnsall.upload_pn_respon, ',')}" />
								<option value=""><spring:message code="forms.Notspecified"  /></option>	
												<c:forEach var="nameItem" items="${namesArray}" begin="0" end="5">
									<option value="${nameItem}">${nameItem}</option>			
												</c:forEach>
                               </select>
                            </div>  
                            
                            
                            
                                            
                                                </div>
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">
                                                  
                                                             <spring:message code="forms.date"  />
                                                    </label>
                                           
                                            
                                          <div class="input-group mb-3">
                                <button class="btn btn-light btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">เลือกวันที่จากแผน</button>
                                <div class="dropdown-menu">
                                     <c:forEach items="${report_pn }" var="row" varStatus="count">   
                                    <a class="dropdown-item dropdownitem" href="#" data-custom="${row.report_pn_date }">${row.report_pn_date }</a>
                                    </c:forEach>
                                </div>
                                 <span >
                                 <input type="date"  name="due" id="fp-default" class="form-control flatpickr-basic" placeholder="YYYY-MM-DD" value="${due }"  style="width:100%;" required/>
                                 </span>
                            </div>  
                            
                            
                            
                                            
                                                </div>
                                                
                                                
                                                                   
               
                                                
                                                
                                            </div>   
                                            
                                            
                          
                              
                                            
                                            
                                            
                                       
                                                         <br><br>
                                           
                                            
                                   
                                      <hr>   
                          
                                       
                                       
                                       
                           
                   
                                       
                                       
                                            
            
               
                                      
                                            
                                         <div class="col-12">
                               
                                   
                        
               
                                           

                                    
                               
                                         
                        <c:if test="${upload_pn.turn == 0}">     
                                         
                              <button type="button"  id="submits" class="btn btn-primary me-1 waves-effect waves-float waves-light" 
                              onclick='uploadfile("/addfiles2-filemanager?${_csrf.parameterName}=${_csrf.token}","form","savestatus")'>
                                <spring:message code="btn.add" text="เพิ่ม" />
                               </button>   
                                <button type="button"   id="submits" class="btn btn-primary me-1 waves-effect waves-float waves-light "  
                              onclick='uploadfile("/addfiles2-filemanager?${_csrf.parameterName}=${_csrf.token}","form","savestatus")'      >
                              <spring:message code="btn.addandclose"  />
                               </button>
                               

                        </c:if>
              
                             <c:if test='${upload_pn.upload_pn_respon == Member_name}'>     </c:if>
                         <c:if test='${upload_pn.upload_pn_respon != Member_name}'> 
                      <button type="button"    >
                               Not access ${Member_name } 
                               </button>   
                           </c:if>     

                                            </div>
                                            
                                            
                                            
                        
   
                           
                                        </div>
                                 
                 
                                </div>
                           
                            
                        
                      
               </form:form>
                  
                  
                  
                     <div id="eform" style="display:none">  </div>
                    
            	
                        
                           <form:form method="POST" id="calform">
                                
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                   <input  type="hidden" name="formid" id="formid"  value="">
                                   <input  type="hidden" name="field"  id="field"  value="">
                                   <input  type="hidden" name="value" id="value"  value="">
                                    <input  type="hidden" name="value2" id="value2"  value="">
                                   <input  type="hidden" name="product_no" id="product_nos"  value="">
                                   
                                   <input  type="hidden" name="amount_no" id="amount_no"  value="${amount_no }">
                                   <input  type="hidden" name="conditionid" id="conditionid"  value="${eformid}">

                                 
             
                            </form:form>
  
  
<div class="lot2"></div>
    	<script> 	
function copyValueToClass(fieldClass) {
	

    // หาค่า input แรกตาม class
    var sourceInput = document.querySelector('.' + fieldClass);
    
    // หาค่า input เป้าหมายที่ต้องการจะคัดลอกค่าไป
    var targetInput = document.getElementById(fieldClass);

    // คัดลอกค่าจาก input แรกไปยัง input ที่สอง
    if (sourceInput && targetInput) {

    	
        targetInput.value = sourceInput.value.toString();
    }
}
	</script> 
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->

	
 
	    