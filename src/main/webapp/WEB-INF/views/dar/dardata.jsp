<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <% pageContext.setAttribute("newLineChar", "\n"); %>


<%@ include file="../common/mainhead.jspf" %>    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel='stylesheet' href='https://code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css'>
 <link href="<c:url value="/resources/treeview/style.css" />" rel="stylesheet">
 
 
 <link href="<c:url value="/resources/app-assets/vendors/css/vendors.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/editors/quill/katex.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/editors/quill/monokai-sublime.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/editors/quill/quill.snow.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/editors/quill/quill.bubble.css" />" rel="stylesheet">
 
 <style>
th.activestep{
 background-color: black; 
color:green;
}


.col-form-label-lg {
  color: rgba(0, 0, 0, 0.5); /* สีจาง */
  font-weight: 700; /* หนามาก */
}
 </style>
 
<title>DAR Data</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>  




    <!-- BEGIN: Content-->
    <div class="app-content content ">
      
      
      <div class="content-header row">
                <div class="content-header-left col-md-9 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-12">
                            
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                   <li class="breadcrumb-item">
DAR
                                    </li>   
                                       <li class="breadcrumb-item"><spring:message code="nav.dar" text="คำขอดำเนินการด้านเอกสาร​(DAR)​" />
                                    </li>   
                             
                                    
                                  <c:if test="${dar.dar_flow == 0}"> 
                    <c:set var="step1" value="activestep" /> 
                     <c:set var="step2" value="" /> 
                      <c:set var="step3" value="" /> 
                      <c:set var="tilte" value="จัดทำ" /> 
                             <li class="breadcrumb-item"><spring:message code="nav.create" text="สร้างใบ DAR" /> 
                                  
                                    </li>  
                   </c:if> 
                    <c:if test="${dar.dar_flow == 1}"> 
                    <c:set var="step1" value="activestep" /> 
                     <c:set var="step2" value="activestep" /> 
                      <c:set var="step3" value="" /> 
                       <c:set var="tilte" value="ตรวจสอบ-อนุมัติ" /> 
                              <li class="breadcrumb-item"> <spring:message code="nav.pendingreview" text="รอตรวจสอบ" />
                                  
                                    </li>  
                   </c:if> 
                   
                    <c:if test="${dar.dar_flow == 2}"> 
                    <c:set var="step1" value="activestep" /> 
                     <c:set var="step2" value="activestep" /> 
                      <c:set var="step3" value="activestep" /> 
                       <c:set var="tilte" value="เเจกจ่าย" /> 
                              <li class="breadcrumb-item"><spring:message code="nav.pendingapp" text="รออนุมัติ" /> 
                                  
                                    </li>  
                   </c:if> 
                    <c:if test="${dar.dar_flow == 3}"> 
                
                       <c:set var="tilte" value="เสร็จสิ้น" /> 
                              <li class="breadcrumb-item"> <spring:message code="nav.distributionlogs" text="ประวัติเอกสารเเจกจ่าย" />
                                  
                                    </li>  
                   </c:if>     
                                    
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
         
            </div>
      
      
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
                        <div class="col-8">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Dar</h4>
                                </div>
                                <div class="card-body">
                                     
                               <c:if test="${dar.dar_request.equals('request1')}">    
                               <spring:message code="dar.request1" text="ขอออกเอกสารใหม่" var="request" />
                               </c:if>  
                                <c:if test="${dar.dar_request.equals('request2')}">    
                              <spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" var="request"/>
                               </c:if>  
                                <c:if test="${dar.dar_request.equals('request3')}">    
                            <spring:message code="dar.request3" text="ขอยกเลิกเอกสาร" var="request"/>
                               </c:if>         
                                     <c:if test="${dar.dar_request.equals('request4')}">    
                            <spring:message code="dar.request4" text="ขอยกเลิกเอกสาร" var="request"/>
                               </c:if>    
                                               
                                    <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">DAR ID :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" value="${dar.dar_id }" readonly>
                                                </div>
                                            </div>   
                                     
                                <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.Request" text="วัตถุประสงค์" /> :</label>
                                                <div class="col-sm-9">
                                                          <input type="text" class="form-control" value="${request }" readonly>
                                                </div>
                                            </div>
                                  <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.Type" text="ชนิดเอกสาร" /> : </label>
                                                <div class="col-sm-9">
                                                             <input type="text" class="form-control" value="${dar.dar_type }" readonly>
                                                </div>
                                            </div> 
                                                         <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.oldid" text="หมายเลขเอกสารเดิม" /> :</label>
                                                <div class="col-sm-9">
                                                 <input type="text" class="form-control" value="${dar.dar_docold_id }" readonly>
                                                </div>
                                            </div> 
                                        
                                          
                                                           <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.id" text="หมายเลขเอกสารใหม่" /> :</label>
                                                <div class="col-sm-9">
                                             <input type="text" class="form-control" value="${dar.dar_doc_id }" readonly>
                                                </div>
                                            </div> 
                                                         <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.name" text="ชื่อเอกสาร" /> : </label>
                                                <div class="col-sm-9">
                                          <input type="text" class="form-control" value="${dar.dar_doc_name }" readonly>
                                                </div>
                                            </div> 
                                          
                                                            <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.page" text="จำนวนหน้า" />: </label>
                                                <div class="col-sm-9">
                                          <input type="text" class="form-control" value="${dar.dar_doc_page }" readonly>
                                                </div>
                                            </div> 
                                            
                                                                     <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.olddate" text="วันที่บังคับใช้เอกสารเดิม" />: </label>
                                                <div class="col-sm-9">
                                                
                                                 <c:set var="docold_end" value="${dar.dar_docold_end}" /> 
                                            <c:if test="${dar.dar_docold_end  == 'null'}">
                                               <c:set var="docold_end" value="-" /> 
                                            </c:if>
                                            
                                            <input type="text" class="form-control" value="${docold_end}" readonly>
                                                </div>
                                            </div> 
                                                                     <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                <spring:message code="dar.oldRev" text="Rev เอกสารเดิม" />: </label>
                                                <div class="col-sm-9">
                                                 <c:set var="vars" value="${dar.dar_docold_rev}" /> 
                                             <c:if test="${dar.dar_docold_rev  == 'null'}"><c:set var="vars" value="-" /> </c:if>
                                                 <input type="text" class="form-control" value="${vars}" readonly>
                                                </div>
                                            </div> 
                                                                        <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                <spring:message code="dar.date" text="วันที่บังคับใช้เอกสารใหม่" />: </label>
                                                <div class="col-sm-9">
                                                                  <c:set var="vars" value="${dar.dar_doc_date}" /> 
                                             <c:if test="${dar.dar_doc_date  == 'null'}"><c:set var="vars" value="-" /> </c:if>
                                        
                                                
                                                            <input type="text" class="form-control" value="${vars}" readonly>
                                                </div>
                                            </div> 
                                                
                                                                            <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                <spring:message code="dar.dateend" text="วันที่หมดอายุเอกสารใหม่" />: </label>
                                                <div class="col-sm-9">
                                                
                                                                     <c:set var="vars" value="${dar.dar_doc_end}" /> 
                                             <c:if test="${dar.dar_doc_end  == 'null'}"><c:set var="vars" value="-" /> </c:if>
                                        
                                                
                                                            <input type="text" class="form-control" value="${vars}" readonly>
                                       
                                                </div>
                                            </div>                                        <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                               <spring:message code="dar.Rev" text="Rev เอกสารใหม่" />: </label>
                                                <div class="col-sm-9">
                                                     
                                                          
                                                                                       <c:set var="vars" value="${dar.dar_doc_rev}" /> 
                                             <c:if test="${dar.dar_doc_rev  == 'null'}"><c:set var="vars" value="-" /> </c:if>
                                        
                                                
                                                            <input type="text" class="form-control" value="${vars}" readonly>
                                
                                                </div>
                                            </div> 
                                                       
                                                               
                                                                <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                               <spring:message code="dar.jd" text="ผู้รับผิดชอบ" />: </label>
                                                <div class="col-sm-9">
                                                
                                     
                                                
                                                            <input type="text" class="form-control" value="${dar.dar_jd}" readonly>
                                                </div>
                                            </div>         
                                                                       <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                               <spring:message code="dar.department" />: </label>
                                                <div class="col-sm-9">
                                                
                                     
                                                
                                                            <input type="text" class="form-control" value="${department.department_name}" readonly>
                                                </div>
                                            </div>         
                                                                                         <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                               <spring:message code="dar.remark2" text="อธิบายส่วนที่ปรับเเก้จากเดิม" />: </label>
                                                <div class="col-sm-9">
                               
                                       
                                       <textarea class ="form-control" readonly>${dar.dar_doc_remark }</textarea>        ${fn:replace(dar.dar_doc_remark, newLineChar, "<br />")}
                                                </div>
                                            </div>    
                                                           
                                                            
		                                      <div class="mb-1 row">
		                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
		                                               <spring:message code="dar.remark3" text="อธิบายส่วนที่ปรับเเก้จากเดิม" />: </label>
		                                                <div class="col-sm-9">
		                              
                                              <div id="snow-wrapper">
                                                <div id="snow-container">
                                                    <div class="quill-toolbar">
                                                 Detail
                                                    </div>
                                                    <div class="editor" id="editor"  style="max-height: 500px;">${dar.dar_doc_remarkfull }</div>
                                                   
                                                </div>
                                            </div>
                                 
                                          
		                                                </div>
		                                            </div> 
		                                         
		                                                                 <div> 
		                                                <c:if test="${dar.dar_step == memberid or dar.dar_step < 1}" >     
                            <c:if test="${dar.dar_flow < 3}"> 
                            <a class="btn btn-outline-primary me-1 mt-1 waves-effect waves-float waves-light" href="/edit-dar?id=${dar.dar_id}" >
                                             Edit
                                        </a>       
                                       </c:if>    
                                        </c:if>   </div> 
		                                             </div>
		                      
		                                            </div> 
		                                         
		                                         
                                   
                                                <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Dar</h4>
                                </div>
                                <div class="card-body">
                                               
                                <c:if test="${dar.dar_request  ne 'request1' and file2 != null  and conditionsed == null }">  
                                                
                                                
                                                <div class="mb-1 row">  
                                                 <div class="mb-1 row">     
                                                       <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                              Location: </label>
                                                <div class="col-sm-9"> 
	                                          ${file2.file_path_full}
	                                      </div></div></div>
                                             
                                            
                                            
                                            <c:if test="${file2 != null and dar.dar_flow == 1  or file2 != null and dar.dar_flow == 2 }">   
                                              <div class="mb-1 row">  
                                                 <div class="mb-1 row">     
                                                       <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                              <spring:message code="dar.file2" text="ไฟล์เก่า" />: </label>
                                                <div class="col-sm-9"> 
                                                
	                                             <a href="/document/${file2.file_path}/${file2.file_name}" target="_blank" class="mb-50">
                                                            <img src="resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18">
                                                            <small class="text-muted fw-bolder">${file2.file_name}</small>
                                                        </a>
	                                      </div></div>
	                                      </div>
	                                       </c:if>
	                                      
	                                     
	                                      
	                             </c:if>
                                            
                                            
                                            
                                              <div class="mb-1 row">     
                                                       <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                            File: </label>
                                                <div class="col-sm-9">  
                                    
                                    <c:if test="${dar.eform  == 0 && conditionsed.condition_id > 0 }">     
                                        <a href="/render-condition/${conditions.condition_id}/${conditionsed.condition_id }" target="_blank" onclick="openCenteredWindow(this.href, 'File Window',1000, 650);return false;">${conditionsed.condition_name}</a>
                                     </c:if>     
                                                    
                                       <c:if test="${dar.eform  >  0  && conditionsed == null }">     
                                        <a href="/render-condition/${conditions.condition_id}" target="_blank" onclick="openCenteredWindow(this.href, 'File Window',900, 650);return false;">${conditions.condition_name}</a>
                                        </c:if>
                                          <c:if test="${dar.eform  >  0  && conditionsed != null }">     
                                        <a href="/render-condition/${conditions.condition_id}/${conditionsed.condition_id }" target="_blank" onclick="openCenteredWindow(this.href, 'File Window',1000, 650);return false;">${conditions.condition_name}</a>
                                        </c:if>      
                                            
                                            
                                            
                                            
                                                     
                                      <c:if test="${file == null and dar.dar_request  ne 'request3' and  dar.dar_flow < 3   and dar.dar_request  ne 'request4' and  dar.eform  == 0 and conditionsed == null }">            
                                   
                                   
                                   
                                  
                                    <form action="/savefile-dar?${_csrf.parameterName}=${_csrf.token}"  id="form" method="post" enctype="multipart/form-data">  
                               
                                 
                                       
                                        <input type="hidden" name="dartype"  value="${dar.dar_type }" class="form-control"  >
                                         <input type="file" name="file" id="file" class="form-control"  >
                                         <input type="hidden" name="id"  value="${dar.dar_id }" class="form-control"  >
                                            
                                                   
                                          
                                       </form>
                                            
                                            
                                            
                                            
                                       </c:if>
       
       
       
      						 <c:if test="${file != null and dar.dar_flow < 3  and  dar.eform  == 0   }"> 
      						
									         <c:if test="${file.file_id > 0  }"> 
									             <a href="/document/${file.file_path}/${file.file_name}" target="_blank" class="mb-50">


<img src="resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18">
                                                            <small class="text-muted fw-bolder">${file.file_name}</small>
                                                        </a>
                                                           <c:if test="${dar.dar_flow == 0 or  dar.dar_flow == -1}">  
                                                         &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp; <a href="/deletefile-dar/${file.file_id}" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${file.file_name}');"><i data-feather='trash-2'></i></a>
									                      </c:if>
									        
									         </c:if> 
									         
					  </c:if> 		
					  		         
						 <c:if test="${dar.dar_flow == 3 and file != null  and  dar.eform  == 0  }"> 		
                                                            <img src="resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18">
                                                            <small class="text-muted fw-bolder">${file.file_name}</small>                     
						 	         
							</c:if> 		          
									             </div>
									             </div>
									              <form:form method="POST"   modelAttribute="dar" action="/app-dar"> 
 
    <c:if test="${dar.dar_flow == 1 or dar.dar_flow == -2}">   
  <div class="mb-1 row">  									              
					<label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.checkresult" text="ผลการตรวจสอบ" /></label>
          <div class="col-sm-9">
          <input type="radio" name="result" value="2" required> <spring:message code="dar.check" text="ตรวจสอบผ่าน" /> 
          <input type="radio" name="result" value="-1"> <spring:message code="dar.uncheck" text="ตรวจสอบไม่ผ่าน" />
          </div>				              
	  </div>								              
	  <div class="mb-1 row">  									              
					<label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.verification" text="บันทึกเพิ่มเติ่มการตรวจสอบ" /></label>
          <div class="col-sm-9">
          <input type="text" name="note" value="${dar.dar_note1}"  class="form-control">
         
          </div>				              
	  </div>
	     </c:if>	
	     
	 
	     
	      <c:if test="${dar.dar_flow == 2  }">     
	     								              
	  <div class="mb-1 row">  									              
					<label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.appresult" text="ผลการอนุมัติ" /></label>
          <div class="col-sm-9">
          <input type="radio" name="result" value="3" required ><spring:message code="dar.app" text="อนุมัติ" />
          <input type="radio" name="result" value="-2" > <spring:message code="dar.unapp" text="ไม่อนุมัติ" />
          </div>				              
	  </div>	
	       </c:if>	
	      <c:if test="${dar.dar_flow == 2 and  dar.eform  == 0 }"> 							              
	  <div class="mb-1 row">  									              
					<label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.verification" text="บันทึกเพิ่มเติ่มการตรวจสอบ" /></label>
          <div class="col-sm-9">
          <input type="text" name="note" value="${dar.dar_note2}" class="form-control"> 
         
          </div>				              
	  </div>
	        <c:if test="${dar.dar_request.equals('request1') or dar.dar_request.equals('request4')}">     
	  	  <div class="mb-1 row">  									              
					<label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.setpath" text="ตำแหน่งในการเก็บเอกสารต้นฉบับ" /></label>
          <div class="col-sm-9">
<br>

       <ul class="tree required" id="webmeterdata_1_1" >  
 <c:forEach items="${drivess}" var="row">
               
	       <li class="tree-item" id="drivess">	
	       
	    <a href="javascript:void(0)" class="trigger" ><i class="d"></i>${row.drives_name}</a>

     
	     
	     <ul class="tree-parent" id="webmeterdata">
	<c:forEach items="${folders}" var="rows">    
	<c:if test="${rows.folder_idrefer == row.drives_id   }">      
		 
		                      
		 
		      
		      <li class="tree-item" id="metertree">
		       	<c:if test="${rows.folder_type2 == 1   }">  
		       	 <input type="radio" name="mainfolder" value="${rows.folder_type}" > 
		       	 </c:if>
		       	  <a href="javascript:void(0)" class="trigger" ><i data-feather='folder'></i> ${rows.folder_name}</a>
       
				   <ul class="tree-parent">
				   
							   <c:forEach items="${folders}" var="rowss">    
								             <c:if test="${rowss.folder_idrefer == rows.folder_id }">     
								         
								                             
								         
								             
								              <li class="tree-item" id="metertree">
 	<c:if test="${rowss.folder_type2 == 1   }">  <input type="radio" name="mainfolder" value="${rowss.folder_type}"></c:if> <a href="javascript:void(0)"  class="trigger"  > <i data-feather='folder'></i> ${rowss.folder_name}</a>
												         
								              </li>
								             
								              </c:if> 		    
				           </c:forEach>    
				    </ul>     
				         
		      </li>
		 
     </c:if> 		    
	  </c:forEach>   
    </ul>
	  
	  </li>
	  
 
 </c:forEach>


</ul>


          </div>				              
	  </div>
	  
	         <c:if test="${dar.dar_request.equals('request1') }">   
	  	  	  <div class="mb-1 row">  									              
					<label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.setpath2" text="ระบุตำเเหน่งสำเนาเอกสาร" /></label>
          <div class="col-sm-9">
<br>

       <ul class="tree required" id="webmeterdata_1_1" >  
 <c:forEach items="${drivess}" var="row">
               
	       <li class="tree-item" id="drivess">	
	       
	    <a href="javascript:void(0)" class="trigger" ><i class="d"></i>${row.drives_name}</a>

     
	     
	     <ul class="tree-parent" id="webmeterdata">
	<c:forEach items="${folders}" var="rows">    
	<c:if test="${rows.folder_idrefer == row.drives_id   }">      
		 
		                      
		 
		      
		      <li class="tree-item" id="metertree">
		       <c:if test="${rows.folder_type2 == 2   }">  <input type="checkbox" name="subfolder" value="${rows.folder_type}" ></c:if>  <a href="javascript:void(0)" class="trigger" ><i data-feather='folder'></i> ${rows.folder_name}</a>
       
				   <ul class="tree-parent">
				   
							   <c:forEach items="${folders}" var="rowss">    
								             <c:if test="${rowss.folder_idrefer == rows.folder_id }">     
								         
								                             
								         
								             
								              <li class="tree-item" id="metertree">
				<c:if test="${rowss.folder_type2 == 2   }">  <input type="checkbox" name="subfolder" value="${rowss.folder_type}"></c:if> <a href="javascript:void(0)"  class="trigger"  > <i data-feather='folder'></i> ${rowss.folder_name}</a>
												         
								              </li>
								             
								              </c:if> 		    
				           </c:forEach>    
				    </ul>     
				         
		      </li>
		 
     </c:if> 		    
	  </c:forEach>   
    </ul>
	  
	  </li>
	  
 
 </c:forEach>


</ul>


          </div>				              
	  </div>					
	  	 
     </c:if> 	
	  
	  </c:if>
	  
	  </c:if>
	  					                 
        <div class="mb-1 row">  
                        
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    
                   <c:if test="${dar.dar_flow < 1}">   
                   
                   
                
					                                     
                    <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">                                  
                       <spring:message code="dar.inspector" text="ผู้ตรวจสอบ" />: 
                       </label>
                              <div class="col-sm-9">
                                        <select name="app"  class="form-select" >
                                        
                                                      <c:forEach items="${flow }" var="row" >
                                                                    <c:if test='${row.user_name != "" }'> 
                                                    <option value="${row.user_id }"  <c:if test="${dar.dar_jd == row.user_name }">selected</c:if>>${row.user_name } (${row.group_name}) <c:if test="${dar.dar_jd == row.user_name }"><spring:message code="dar.jd" text="ผู้รับผิดชอบ" /></c:if></option>
                                        					   </c:if>
                                           </c:forEach>

                                                    </select>
                                                </div>
                         </c:if>   
                         
                                                      
                         <c:if test="${dar.dar_flow == 1  }">   
                          <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">                                  
                       <spring:message code="dar.approver" text="ผู้อนุมัติ" />:
                        </label>
                                          <div class="col-sm-9">
                                        <select name="app"  class="form-select" >
                                                      <c:forEach items="${flow }" var="row" >
                                                                 <c:if test='${row.user_name != "" }'> 
                                                    <option value="${row.user_id }">${row.user_name } (${row.group_name})</option>
                                                       </c:if>
                                           </c:forEach>

                                                    </select>
                                                </div>
                         </c:if>          
                         
                         <c:if test="${dar.dar_flow > 1}"> 
                           <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">  </label>    
                          <div class="col-sm-9"> 
                                              <input type="hidden" name="app"  value="0" class="form-control"  >
                           </div>
                           </c:if>                   
                                         
                                                <input type="hidden" name="id"  value="${dar.dar_id }" class="form-control"  >
                                            <input type="hidden" name="flow"  value="${dar.dar_flow }" class="form-control"  >
                                            </div> 
                                  <c:if test="${dar.dar_flow == -1  }">             
                               <div class="alert alert-danger" role="alert">
                                            <h4 class="alert-heading">Danger</h4>
                                            <div class="alert-body">
                                                 <spring:message code="dar.Danger1" text="เอกสารไม่ผ่านการตรวจสอบ" />
                                            </div>
                                        </div>             
                                        </c:if>  
                                               <c:if test="${dar.dar_flow == -2  }">             
                               <div class="alert alert-danger" role="alert">
                                            <h4 class="alert-heading">Danger</h4>
                                            <div class="alert-body">
                                                 <spring:message code="dar.Danger2" text="เอกสารไม่ผ่านการอนุมัติ" />
                                            </div>
                                        </div>             
                                        </c:if>    
                                            
   <br> <br>
      <div class="row">
                                            <div class="col-12">
             <c:if test="${dar.dar_step == memberid or dar.dar_step < 1}" >     
                                
                         <c:if test="${dar.dar_flow < 1}">                                     
                         <button class="btn btn-primary me-1 mt-1 waves-effect waves-float waves-light"  onclick="return confirm('<spring:message code="action.flow" text="คุณต้องการส่งเอกสาร :" />${dar.dar_doc_name} ?');">
                                        Flow
                                        </button>
                         </c:if>                                
                         <c:if test="${dar.dar_flow == 1 or ordar.dar_flow == -2 }">                                     
                         <button class="btn btn-primary me-1 mt-1 waves-effect waves-float waves-light"  onclick="return confirm('<spring:message code="action.check" text="ยืนยันการตรวจเอกสาร  :" />${dar.dar_doc_name} ?');">
                                      Flow
                                        </button>
                         </c:if>          
                       
                          <c:if test="${dar.dar_flow == 2}" >                                     
                         <button class="btn btn-primary me-1 mt-1 waves-effect waves-float waves-light"  onclick="return confirm('<spring:message code="action.app" text="ยืนยันการอนุมัติเอกสาร :" />${dar.dar_doc_name} ?');">
                                        Flow
                                        </button>
                         </c:if>  
                         
                          
                           </c:if>        
                       
                                            </div>
                                                 </form:form>
                                        </div>
                                
                                </div>
                            </div>
                        </div>
                               <div class="col-4">
                   
                   <div class="card">
                            <div class="card-header">
                                <h5 class="card-title"><spring:message code="dar.history" text="ใบ DAR ก่อนหน้า" /></h5>
                            </div>
                            <div class="card-body"> <div class="table-responsive">
                    <table class="table" >
                                    <thead>
                                        <tr>
                                   
                                           <th style="font-size:12px;padding:5px"><spring:message code="dar.darid" text="หมายเลขใบคำร้อง" /></th>
                                            <th style="font-size:12px;padding:5px"><spring:message code="dar.requesteddate" text="วันที่ร้องขอ" /></th>
                                         
                                            <th style="font-size:12px;padding:5px"><spring:message code="dar.Request" text="เรื่องที่ร้องขอ" /></th>
                       
                                        <th scope="col" style="font-size:12px;padding:5px"><spring:message code="label.building_action" text="จัดการ" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${olddar }" var="row" varStatus="count">
                                                  
                                          <c:if test="${row.dar_request.equals('request1')}">    
                               <spring:message code="dar.request1" text="ขอออกเอกสารใหม่" var="request" />
                               </c:if>  
                                <c:if test="${row.dar_request.equals('request2')}">    
                              <spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" var="request"/>
                               </c:if>  
                                <c:if test="${row.dar_request.equals('request3')}">    
                            <spring:message code="dar.request3" text="ขอยกเลิกเอกสาร" var="request"/>
                               </c:if>   
                                        <tr>
                                        
                                 
                                            <td>${row.dar_id }</td>
                                            <td>${row.dar_date_add }</td>
                                        
                                             <td>${request }</td>
                                   
                                             <td><a href="/show-dar?id=${row.dar_id }" class="btn btn-danger">View</a></td>
                                        </tr>
                                       
                                          </c:forEach>
                                          
                                    </tbody>
                                    
                                    
                                    
                                </table></div>
                   </div>   </div>
             
                   
                 
                   
                   <div class="card">
            
                            <div class="card-body"> <div class="table-responsive">
                   <table class="table" >
                                    <thead>
                                        <tr>
                                            <th  style="font-size:12px;padding:5px"><spring:message code="dar.sign" text="ลงนาม" /></th>
                                            <th class="${step1}"  style="font-size:12px;padding:5px"><spring:message code="dar.created" text="จัดทำโดย" /></th>
                                      
                                          <th class="${step2}"  style="font-size:12px;padding:5px"><spring:message code="dar.checkedby" text="ตรวจสอบโดย" /></th> 
                                               <th class="${step3}"  style="font-size:12px;padding:5px"><spring:message code="dar.approvedby" text="อนุมัติโดย" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                        
                                            <td  style="font-size:12px;padding:5px"><spring:message code="label.name" text="ชื่อ - นามสกุล" /></td>
                                            <td  style="font-size:12px;padding:5px"><c:if test="${!dar.dar_nameadd.equals('null')}">${dar.dar_nameadd }</c:if></td>
                                            <td  style="font-size:12px;padding:5px"> <c:if test="${!dar.dar_namecheck.equals('null')}"> ${dar.dar_namecheck }</c:if></td>
                                            <td  style="font-size:12px;padding:5px"><c:if test="${!dar.dar_nameapp.equals('null')}">  ${dar.dar_nameapp }</c:if></td>
                                            
                                        </tr>
                                            <tr>
                                            <td  style="font-size:12px;padding:5px"><spring:message code="usermanage.position" text="ตำเเหน่ง" /></td>
                                            <td  style="font-size:12px;padding:5px">${useradd.group_name }</td>
                                            <td  style="font-size:12px;padding:5px">${usercheck.group_name }</td>
                                            <td  style="font-size:12px;padding:5px">${userapp.group_name }</td>
                                            
                                        </tr>
                                        <tr>
                                            <td  style="font-size:12px;padding:5px"><spring:message code="dar.remark" text="เพิ่มเติ่ม" /></td>
                                            <td  style="font-size:12px;padding:5px"></td>
                                            <td  style="font-size:12px;padding:5px">${dar.dar_note1 }</td>
                                            <td  style="font-size:12px;padding:5px">${dar.dar_note2 }</td>
                                            
                                        </tr>
                                    </tbody>
                                </table></div>
                   </div>   </div>
                    </div>
                    
                    
                    
                    </div>
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>






<%@ include file="../common/mainfoot.jspf" %>  
 
 <script>
 

 var element = document.getElementById("${nav_dar}");
 element.classList.add("active");  
 

 
function openCenteredWindow(url, name, width, height) {
    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>
 
 
  <c:if test="${dar.dar_flow > 1}"> 
   <script>
   function check(){
		 if( $('ul.required :radio:checked').length > 0){
			 return false;
			 alert('ระบุตำเเหน่งเอกสาร');
		 } 
		 return true;
   }

     </script>
     </c:if>
   <c:if test="${dar.dar_flow < 2}"> 
      <script>
   function check(){

		 return true;
   }

     </script>
  </c:if>
    <script>
    
 
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
<c:if test="${dar.dar_flow <= 0 }"> 
var element = document.getElementById("nav_dar2");
element.classList.add("active");  
</c:if>
<c:if test="${dar.dar_flow == 1 }"> 
var element = document.getElementById("nav_dar3");
element.classList.add("active");  
</c:if>

<c:if test="${dar.dar_flow == 2 }"> 
var element = document.getElementById("nav_dar4");
element.classList.add("active");  
</c:if>
<c:if test="${dar.dar_flow == 3 }"> 
var element = document.getElementById("nav_dar5");
element.classList.add("active");  
</c:if>
    </script>
</body>

   <script src="<c:url value="/resources/app-assets/vendors/js/editors/quill/katex.min.js" />"></script>
       <script src="<c:url value="/resources/app-assets/vendors/js/editors/quill/highlight.min.js" />"></script>
         <script src="<c:url value="/resources/app-assets/vendors/js/editors/quill/quill.min.js" />"></script>
           <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-quill-editor.js" />"></script>
