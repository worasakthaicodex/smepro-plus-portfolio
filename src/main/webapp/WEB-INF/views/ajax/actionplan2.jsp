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
				                                                <h6 class="fw-bolder mb-25">${actionplans.actionplan_responsible }</h6>
				                                                
				                                                
				                                    
				                                                
				                                                <p class="card-text">${actionplans.actionplan_dateadd}</p>
				                                                <p class="card-text">
				                                                
				                                            
				                                                 <c:set var = "newLineChar" value = "<%= \"\n\" %>" />
				                                                      <c:set var = "br" value = "<br/>" />
				                                                  ${fn:replace(actionplans.actionplan_comment,newLineChar,br)}
				                                                
				                                                </p>
				                                                
				                                            <c:if test='${actionplans.file_type.equals("image/png") or actionplans.file_type.equals("image/jpg")  or actionplans.file_type.equals("image/jpeg")}'>
				                                            
				                                           <a href="/render2-filemanager?file=${actionplans.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;"> <img src="/readdocument/${actionplans.file_path}/${actionplans.file_name}" style="width:100px"></img>
				                                               </a>  <br>
				                                               </c:if> 
				                                               
				                                 <c:if test='${!actionplans.file_type.equals("image/png") && !actionplans.file_type.equals("image/jpg")  && !actionplans.file_type.equals("image/jpeg")}'>
				                                            
				                                           <a href="/render2-filemanager?file=${actionplans.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">${actionplans.file_name_original }</img>
				                                               </a>  <br>
				                                    </c:if> 
				                                               
				                                     
				                                              
				                                            </div>
				                                            
				                                            
				                                          
				                                            
				                                        </div>
				                                        
				                                        
				                                            <textarea  id="c-${actionplans.actionplan_id }" style="display:none">${actionplans.actionplan_comment}</textarea>
				                                              <input type="hidden" id="f-${actionplans.actionplan_id }" value="${actionplans.file_name }">
				                                        <br>
				                                        				                              
		                                      
				                                                
				                                                
				                                          <div style="float:right">
				                                          
				          <c:if test='${actionplans.actionplan_responsible.equals(Member_name)}' >                                    
                                                         <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Action
                                </button>
                                <div class="dropdown-menu" style="">
                             
                                
                                     
                                <a class="dropdown-item" href="javascript:void(0)" onclick="edit2('${actionplans.actionplan_id }')" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" >เเก้ไข
                              
                                 </a>
                                
                              

                                     <a class="dropdown-item" href="#" 
                                     onclick="getdatas('/delsubactionplan-filemanager/${actionplans.actionplan_idrfer}/${actionplans.actionplan_id }','cm${actionplans.actionplan_idrfer}',1);" class="btn btn-sm btn-danger" >ลบ
                              
                                 </a>
                                    
                                      
                                
                                </div>
                            </div>
                            </c:if>
                            
                            
                                            </div>
				                                     

				                                         

				                                        
				                                    </div>


<script>



$("#subs-${actionplans.actionplan_id }").show();
$("#edits-${actionplans.actionplan_id }").hide();


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