<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>



<%@ include file="common/mainhead.jspf" %>    
    <link href="<c:url value="/resources/app-assets/vendors/css/pickers/pickadate/pickadate.css" />" rel="stylesheet">
      <link href="<c:url value="/resources/app-assets/vendors/css/pickers/flatpickr/flatpickr.min.css" />" rel="stylesheet">

    <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
      <link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-flat-pickr.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-pickadate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">


<link href="<c:url value="/resources/app-assets/vendors/css/vendors.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/editors/quill/katex.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/editors/quill/monokai-sublime.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/editors/quill/quill.snow.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/editors/quill/quill.bubble.css" />" rel="stylesheet">







<title>Dar</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">

        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
                        <div class="col-8">
                            <div class="card">
                                <div class="card-header">
                                   
                                 
                                 
                                 
                                 
                                    
                                     <c:choose>
                                <c:when test='${dar.dar_id > 0}'>
                                  <c:set var="link" value="/edit-dar"/>     
                                   <h4 class="card-title">Edit Dar</h4> 
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/add-dar"/>  
                                 <h4 class="card-title">New Dar</h4>
                              </c:otherwise>
                               </c:choose>  
                                
                                    
                                </div>
                                <div class="card-body">
                                      <c:choose>
                                <c:when test='${dar.dar_id > 0}'>
                                  <c:set var="link" value="/edit-dar"/>      
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/add-dar"/>  
                              </c:otherwise>
                               </c:choose>  
                                
                                    <c:if test="${User_id  == '1'}">    
                        
                        <div class="alert text-white bg-danger d-flex align-items-center justify-content-between" role="alert">
                                       <div class="alert-text">Error <b>Major</b> ผู้ใช้นี้ไม่สามารถดำเนินการนี้ได้ ....</div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if>
                                
                                   <c:if test="${User_id  != '1'}"> 
                           
                                
                                    <form:form method="POST" modelAttribute="drives" action="${link }">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                  <c:if test="${dar.dar_id > 0}">    
                                 <input  type="hidden" name="dar_id" value="${dar.dar_id }">
                               </c:if>  
                                   
                                   
                          <c:set var="request1" value="" />
                          <c:set var="request2" value="" />
                           <c:set var="request3" value="" />
                            <c:set var="request4" value="" />
                          
                          
                                   <c:set var="dartype1" value="block" />
                                  <c:set var="dartype2" value="none" />
                                    <c:set var="dartype3" value="block" />   
                                    
                                        
                                   <c:if test="${dar.dar_request.equals('request1')}">  
                                    <c:set var="request1" value="selected" />
                                      <c:set var="dartype1" value="block" />
                                        <c:set var="dartype3" value="block" />
                                  </c:if> 
                                      <c:if test="${dar.dar_request.equals('request2')}">  
                                    <c:set var="request2" value="selected" />
                                     <c:set var="dartype2" value="block" />
                                      <c:set var="dartype1" value="none" />
                                        <c:set var="dartype3" value="block" />
                                  </c:if> 
                                      <c:if test="${dar.dar_request.equals('request3')}">  
                                    <c:set var="request3" value="selected" />
                                       <c:set var="dartype2" value="block" />
                                        <c:set var="dartype1" value="none" />
                                         <c:set var="dartype3" value="none" /> 
                                  </c:if> 
                                      <c:if test="${dar.dar_request.equals('request4')}">  
                                    <c:set var="request4" value="selected" />
                                       <c:set var="dartype2" value="block" />
                                        <c:set var="dartype1" value="none" />
                                         <c:set var="dartype3" value="none" /> 
                                  </c:if>
                                  
                                        <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="dar.Request" text="วัตถุประสงค์" /></label>
                                                 
                                                    <select name="dar_request"  class="form-select" id="dar_request">
                                                    <option value="request1" ${request1 }><spring:message code="dar.request1" text="ขอออกเอกสารใหม่" /></option>
				                                    <option value="request2" ${request2 }><spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" /></option>
				                                    <option value="request3" ${request3 }><spring:message code="dar.request3" text="ขอยกเลิกเอกสาร" /></option>
				                                       <option value="request4" ${request4 }><spring:message code="dar.request4" text="ขอย้ายเอกสาร" />(Unsupported E-form type)</option>
                                                    </select>
                                                    
                                                </div>
                                            </div>
                                           <div id="dartype1" style="display:${dartype1}">
                                           <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="dar.Type" text="ชนิดเอกสาร" /></label>
                                     
                                                      <select name="dar_type"  class="form-select" id="doc" >
                                    
                                     <c:set var="type1" value="" />
                                    <c:set var="type2" value="" />
                                    <c:set var="type3" value="" />
                                    <c:set var="type4" value="" />
                                     <c:set var="type5" value="" />
                                     
                                          
                                     <c:if test="${dar.dar_type.equals('Quality Manual (QM)')}">  
                                    <c:set var="type1" value="selected" />
                                  </c:if> 
                                  <c:if test="${dar.dar_type.equals('Quality Procedure (QP)')}">  
                                    <c:set var="type2" value="selected" />
                                  </c:if> 
                                  <c:if test="${dar.dar_type.equals('Work Instruction (WI)')}">  
                                    <c:set var="type3" value="selected" />
                                  </c:if> 
                                  <c:if test="${dar.dar_type.equals('Supporting Data (SD)')}">  
                                    <c:set var="type4" value="selected" />
                                  </c:if>
                                  <c:if test="${dar.dar_type.equals('Form (FO)')}">
                                    <c:set var="type5" value="selected" />
                                  </c:if> 
                                        
                                         
                                         
                                                    
                                                    <option value="Quality Manual (QM)" ${type1 }>Quality Manual (QM)</option>
                                                    <option value="Quality Procedure (QP)" ${type2 }>Quality Procedure (QP)</option>
                                                    <option value="Work Instruction (WI)" ${type3 }>Work Instruction (WI)</option>
                                                     <option value="Supporting Data (SD)" ${type4 }>Supporting Data (SD) </option>
                                                     <option value="Form (FO)" ${type5 }>Form (FO)</option>

                                                    </select>
                                                </div>
                                            </div>  
                                             </div> 
                                           
                                        
                                                            <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="dar.id" text="หมายเลขเอกสารใหม่" /></label> 
                                                     <c:set var="dar_doc" value="${dar.dar_doc_id }" />
                                                  
                                                   <c:if test="${dar.dar_doc_id  == '' || dar == null }">  
                                                     <c:set var="dar_doc" value="#AUTO_ID" />
                                                     </c:if>
                                                  
                                                    <input type="text" name="dar_doc_id" id="dar_doc_id"  class="form-control"  value="${dar_doc }"  >
                                                     <c:if test="${dar.dar_id > 0}">    
                                                   <input  type="hidden" name="dar_id" value="${dar.dar_id }">
                                                   </c:if>
                                                </div>
                                            </div>  
                                             </div>  
                                                    <span id="showolddar2"></span>
                                             <script>                    
                                           
                      $('#dar_doc_id').keyup(delay(function (e) {
                   
                    		return postdatas('/searchdocold-dar','showolddar2');
                    	 
                    	return false;	   
                    	}, 500));

                    	function postdatas(Mode,Mode2) {
                   
                    		 var ID = $('#dar_doc_id').val();
                    		 if(ID == "#AUTO_ID"){ 
                    			    $("#" + Mode2).html("");	
                    				return false;
                    		 }
                    		
                    		$("#" + Mode2).html("Loading.........");
                    	     
                    	        $.ajax({
                    	        type:'POST',
                    	        url:Mode,
                    	        data: {data : ID},
                    	        
                    	        beforeSend: function(xhr) {
                    	            // here it is
                    	            xhr.setRequestHeader(header, token);
                    	        },
                    	        
                    	     success: function(data){
                    	    $("#" + Mode2).html(data);	
                    	       highlight(ID);     
                    	     }
                    	});
                    	return false;
                    	    
                    	}
     			   </script>
                                                 <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="dar.name" text="ชื่อเอกสาร" /></label>
                                                    <input type="text" name="dar_doc_name"  class="form-control"  value="${dar.dar_doc_name }"  >
                                                </div>
                                            </div>  
                                             </div>  
 									
              
                                             
                                        
                                             
                                        
                                        
                                             
                                               </div> 
                                               
                                               
                                               
                            <div class="row" id="FO"  
       <c:if test="${dar.eform == 0 and !dar.dar_request.equals('request2')  }">style='display:none'</c:if>>
        							  <div class="col-md-12 col-12">
                                                <div class="mb-1">
				                                                    <label class="form-label" for="first-name-column">E-form</label>
				                                                  
				                                               
				                                                  
				                          <select class="form-select select2"   name="eform"  >   
				                             
				                              
				                                	
				                               	      <option value="0" ${monitor }>Disable</option>   
				                               	      
				                               	      
				                               	                   
				                                    <c:forEach items="${conditions }" var="row" varStatus="count">
				                                     <c:set var="monitor" value="" />
				                              
				                                    
				                                     <c:if test="${dar.eform == row.condition_id }">    
				                                                              <c:set var="monitor" value="selected" />
				                                           </c:if>  
				                                            <option value="${row.condition_id }" ${monitor }>${row.condition_name }</option>
				                                      </c:forEach>
				                            </select>
                                                </div>
                                       </div>
                                             </div>  
                                             
                                             <div id="dartype2" style="display:${dartype2}">
                                             
                                                     <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="dar.oldid" text="หมายเลขเอกสารเดิม" /></label>
                                                    <input type="text" name="dar_docold_id"  id="dar_docold_id" class="form-control date"  value="${dar.dar_docold_id }"   >
                                                </div>
                                            </div>  
                                             </div>  
                                <span id="showolddar"></span>
                                             
                                             
                            
                      <script>                    
                                           
                      $('#dar_docold_id').keyup(delay(function (e) {
                    	
                    		return postdatas2('/searchdocold2-dar','showolddar');
                    	 
                    	return false;	   
                    	}, 500));

                    	function postdatas2(Mode,Mode2) {
                    		  $("#" + Mode2).html("Loading.........");
                    	      var ID = $('#dar_docold_id').val();
                    	        $.ajax({
                    	        type:'POST',
                    	        url:Mode,
                    	        data: {data : ID},
                    	        
                    	        beforeSend: function(xhr) {
                    	            // here it is
                    	            xhr.setRequestHeader(header, token);
                    	        },
                    	        
                    	     success: function(data){
                    	    $("#" + Mode2).html(data);	
                    	       highlight(ID);     
                    	     }
                    	});
                    	return false;
                    	    
                    	}
     			   </script>
                                           
                                           
                                                        <div class="row">
                                             <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="dar.remark2" text="อธิบายส่วนที่ปรับเเก้จากเดิมหรือเหตุผลการลบ" /></label>
                                                    <textarea  name="dar_doc_remark"  class="form-control"  >${dar.dar_doc_remark }</textarea>
                                                </div>
                                            </div>  
                                             </div> 
                              
                              
                               
                                                        <div class="row">
                                             <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="dar.remark3" text="อธิบายส่วนที่ปรับเเก้จากเดิม" /></label>
                                           <div id="full-wrapper">
                                                <div id="full-container">
                                               
                                                    <div class="editor" id="editor" style="max-height: 500px;">${dar.dar_doc_remarkfull }</div>
                                                    <textarea rows="6" cols="75" name="dar_doc_remarkfull" id="dar_doc_remarkfull"  hidden>${dar.dar_doc_remarkfull }</textarea>
                                                </div>
                                            </div>
                                 
                                                </div>
                                            </div>  
                                             </div> 
                              <br> <br>   <br>     <br>  
                              
                                </div> 
                                             <div id="dartype3" style="display:${dartype3}">
                                                              <div class="row">
                                             <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="dar.date" text="วันที่บังคับใช้เอกสารใหม่" /></label>
                                                    <input type="text" name="dar_doc_date"   value="${dar.dar_doc_date }"  class="form-control flatpickr-basic flatpickr-input" placeholder="YYYY-MM-DD" >
                                                </div>
                                            </div>  
                                             </div> 
                                          
                                          <div class="row">
                                             <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="dar.dateend" text="วันที่หมดอายุ" /></label>
                                                  
                                               
                                            
                                            
                                            
                                                            <c:if test="${dar.dar_doc_end2 == 1}">    
                                                              <c:set var="sizes1" value="selected" />
                                                         </c:if>  
                                                      <c:if test="${dar.dar_doc_end2 == 2}">    
                                                              <c:set var="sizes2" value="selected" />
                                                         </c:if>  
                                                           <c:if test="${dar.dar_doc_end2 == 3}">    
                                                              <c:set var="sizes3" value="selected" />
                                                         </c:if>  
                                                      <c:if test="${dar.dar_doc_end2 == 4}">    
                                                              <c:set var="sizes4" value="selected" />
                                                         </c:if> 
                                                             <c:if test="${dar.dar_doc_end2 == 5}">    
                                                              <c:set var="sizes5" value="selected" />
                                                         </c:if> 
                                                          
                                               
                                               
                                               <select name="dar_doc_end2"  class="form-select">
                                                      <option value="1" ${size1 }>1 Year </option>
                                                       <option value="2" ${size2 }>2 Year </option>
                                                       <option value="3" ${size3 }>3 Year </option>
                                                      <option value="4" ${size4 }>4 Year</option>
                                                       <option value="5" ${size5 }>5 Year</option>
                                                    </select>
                                                </div>
                                            </div>  
                                             </div> 
                                                       
                                                 <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="dar.page" text="จำนวนหน้า" /></label>
                                                    <input type="number" name="dar_doc_page"  class="form-control"  value="${dar.dar_doc_page }" >
                                                </div>
                                            </div>  
                                             </div>  
                                               
                                             
                                                               <div class="row">
                                             <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="dar.jd" text="ผู้รับผิดชอบ" /></label>
                                                   
                                                             <select class="select2 form-select"  name="dar_jd" id="default-select-multi">
                                                        
                                                        
                                                        <c:set var="listrespon" value="${dar.dar_jd }" />
                                                        
                                                        <c:forEach items="${users }" var="row" varStatus="count">
                                                        
                                                        
                                                           <c:set var="listcheck" value="${row.user_name }" />
                                                           
                                                           
                                                        <c:set var="check" value="" />
					                                     <c:if test='${fn:contains(listrespon,listcheck)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
					                                     
					                                  <c:if test='${row.user_name != "" }'>     
                                                    <option value="${row.user_name }"  ${check }>${row.user_name } (${row.group_name })</option>
                                                 </c:if> 
                                                     
                                                      </c:forEach>
                                                </select>
                                                </div>
                                            </div>  
                                             </div>
                                             </div>                                                           
                      <div class="row">
                                   
   

   
   
                             
                                            <div class="col-12">
                         
                           
                              <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"  onclick="copy()">
                                <c:choose>
                                <c:when test='${dar.dar_id > 0}'>
                                <spring:message code="btn.edit" text="แก้ไข" />
                                </c:when>
                                <c:otherwise>
                                <spring:message code="btn.add" text="เพิ่ม" />
                               </c:otherwise>
         
                               </c:choose>       
                               </button>   
                              
                               
                               
                                            </div>
                                        </div>
                                    </form:form>
                                     </c:if>
                                 
                                </div>
                              
                            </div>
                            
                        </div>
                   </div>
                  
                           <div class="col-4">
                         <div class="card">
                           <div class="card-header">
                           <spring:message code="dar.Latestregistered" />
                             </div>
                         
                                 <div class="card-body">
                                    <ul class="nav nav-pills">
                                      
                                        <li class="nav-item">
                                            <a class="nav-link active" id="profile-tab" data-bs-toggle="pill" href="#profile" aria-expanded="false">QM</a>
                                        </li>
                                      <li class="nav-item">
                                            <a class="nav-link" id="home-tab" data-bs-toggle="pill" href="#home" aria-expanded="true">QP</a>
                                        </li>
                                          <li class="nav-item">
                                            <a class="nav-link" id="home-tab" data-bs-toggle="pill" href="#home1" aria-expanded="true">WI</a>
                                        </li>
                                            <li class="nav-item">
                                            <a class="nav-link" id="home-tab" data-bs-toggle="pill" href="#home2" aria-expanded="true">SD</a>
                                        </li>
                                            <li class="nav-item">
                                            <a class="nav-link" id="home-tab" data-bs-toggle="pill" href="#home3" aria-expanded="true">FO</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                       
                                        <div class="tab-pane active" id="profile" role="tabpanel" aria-labelledby="profile-tab" aria-expanded="false">
                                        
                                        
                      <table class="table">
		                                    <thead>     
		                                         <tr style="font-size:12px">
		   
		                                                <th style="font-size:12px">  <spring:message code="tables.docid" text="รหัสเอกสาร​" /></th>
		                                            <th style="font-size:12px">
		                                            <spring:message code="tables.docname" text="ชื่อเอกสาร" />
		                                            </th>
		                                     
		                                        </tr>
		                                 </thead>  
		                      <c:forEach items="${files1 }" var="row" varStatus="count">
		                                        <tr class="datatable">
		                                            <td>${row.file_no }</td>
		                                            <td>${row.file_name_original }</td>
		                                        </tr>
		                       </c:forEach>
                     </table>                      
                                          
                                          
                                        </div>
                                         <div role="tabpanel" class="tab-pane" id="home" aria-labelledby="home-tab" aria-expanded="true">
                                         
                                                      <table class="table">
		                                    <thead>     
		                                         <tr style="font-size:12px">
		   
		                                                <th style="font-size:12px">  <spring:message code="tables.docid" text="รหัสเอกสาร​" /></th>
		                                            <th style="font-size:12px">
		                                            <spring:message code="tables.docname" text="ชื่อเอกสาร" />
		                                            </th>
		                                     
		                                        </tr>
		                                 </thead>  
		                      <c:forEach items="${files2 }" var="row" varStatus="count">
		                                        <tr class="datatable">
		                                            <td>${row.file_no }</td>
		                                            <td>${row.file_name_original }</td>
		                                        </tr>
		                       </c:forEach>
                     </table>     
        
                                        </div>
                                      <div role="tabpanel" class="tab-pane" id="home1" aria-labelledby="home-tab" aria-expanded="true">
                                      
                       <table class="table">
		                                    <thead>     
		                                         <tr style="font-size:12px">
		   
		                                                <th style="font-size:12px">  <spring:message code="tables.docid" text="รหัสเอกสาร​" /></th>
		                                            <th style="font-size:12px">
		                                            <spring:message code="tables.docname" text="ชื่อเอกสาร" />
		                                            </th>
		                                     
		                                        </tr>
		                                 </thead>  
		                      <c:forEach items="${files3 }" var="row" varStatus="count">
		                                        <tr class="datatable">
		                                            <td>${row.file_no }</td>
		                                            <td>${row.file_name_original }</td>
		                                        </tr>
		                       </c:forEach>
                     </table>     
        
                                        </div>
                                         <div role="tabpanel" class="tab-pane" id="home2" aria-labelledby="home-tab" aria-expanded="true">
                                <table class="table">
		                                    <thead>     
		                                         <tr style="font-size:12px">
		   
		                                                <th style="font-size:12px">  <spring:message code="tables.docid" text="รหัสเอกสาร​" /></th>
		                                            <th style="font-size:12px">
		                                            <spring:message code="tables.docname" text="ชื่อเอกสาร" />
		                                            </th>
		                                     
		                                        </tr>
		                                 </thead>  
		                      <c:forEach items="${files4 }" var="row" varStatus="count">
		                                        <tr class="datatable">
		                                            <td>${row.file_no }</td>
		                                            <td>${row.file_name_original }</td>
		                                        </tr>
		                       </c:forEach>
                             </table>     
        
                                        </div>
                                         <div role="tabpanel" class="tab-pane" id="home3" aria-labelledby="home-tab" aria-expanded="true">
                              <table class="table">
		                                    <thead>     
		                                         <tr style="font-size:12px">
		   
		                                                <th style="font-size:12px">  <spring:message code="tables.docid" text="รหัสเอกสาร​" /></th>
		                                            <th style="font-size:12px">
		                                            <spring:message code="tables.docname" text="ชื่อเอกสาร" />
		                                            </th>
		                                     
		                                        </tr>
		                                 </thead>  
		                      <c:forEach items="${files5 }" var="row" varStatus="count">
		                                        <tr class="datatable">
		                                            <td>${row.file_no }</td>
		                                            <td>${row.file_name_original }</td>
		                                        </tr>
		                       </c:forEach>
                             </table>     
                                        </div>
                                    
                                    </div>
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





<%@ include file="common/mainfoot.jspf" %>  

  <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/pickers/form-pickers.js" />"></script>
    <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>
  
 
    <script>
  
    
    function copy(){
    	
    	var data = $('#editor').html(); 
    	$('#dar_doc_remarkfull').val(data); 
    }
    
    
    $(window).on('load', function() {
    	
    	
  	  var  request = $('#dar_request').val();
    	  
    	  if(request == "request1"){
    		  
    		  $('#dartype1').show(); 
    		  $('#dartype2').hide(); 
    		  $('#dartype3').show();
    		  
    	  }else if(request == "request2"){
    		  
    		  $('#dartype2').show(); 
    		  $('#dartype1').hide(); 
    		  $('#dartype3').show();
    		  
    	  }else{
    		  
    		  $('#dartype2').show(); 
    		  $('#dartype1').hide();
    		  $('#dartype3').hide();
    		  
    	  }
    	  
    });
    
    
    
    
    $('#dar_request').on('change', function() {
    	  var  request = this.value;
    	  
    	  if(request == "request1"){
    		  
    		  $('#dartype1').show(); 
    		  $('#dartype2').hide(); 
    		  $('#dartype3').show();
    		  
    		  
    	  }else if(request == "request2"){
    		  
    		  $('#dartype2').show(); 
    		  $('#dartype1').hide(); 
    		  $('#dartype3').show();
    		  $('#FO').show(); 
    		  
    	  }else{
    		  
    		  $('#dartype2').show(); 
    		  $('#dartype1').hide();
    		  $('#dartype3').hide();
    		  $('#FO').hide(); 
    		  
    	  }
    	  
    	  
    	});
    
    $('.flatpickr-input:visible').on('focus', function () {
        $(this).blur()
    })
    $('.flatpickr-input:visible').prop('readonly', false)
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
 var element = document.getElementById("${nav_dar}");
  element.classList.add("active");  
  
  

  
    </script>
    
     <script src="<c:url value="/resources/app-assets/vendors/js/editors/quill/katex.min.js" />"></script>
       <script src="<c:url value="/resources/app-assets/vendors/js/editors/quill/highlight.min.js" />"></script>
         <script src="<c:url value="/resources/app-assets/vendors/js/editors/quill/quill.min.js" />"></script>
           <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-quill-editor.js" />"></script>
    
   <div class="modal fade" id="addNewCards" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"><spring:message code="dar.category"/></h1>
                              

                                <!-- form -->
                         
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber"><spring:message code="tables.category"/></label>
                                        <div class="input-group input-group-merge">
                                       
                                    
                                         <select name="document_no" id="document_no" class="form-select autocomplete-input3" type="text" required>
		                                          <c:forEach items="${document_no }" var="row" varStatus="count">
		                                          <option value="${row.document_no_id  }">${row.document_no_pattern } [ ${row.document_no_note } ]</option>
		                                          </c:forEach>
                                         </select>
                                        
                                            <span class="input-group-text cursor-pointer p-25" id="modalAddCard2">
                                                <span class="add-card-type"></span>
                                            </span>
                                        </div>
                                         
                                          <input type="hidden" name="id" id="idapp" value=""> 
                                    </div>

                                    <div class="col-12 text-center">
                                        <button type="button" class="btn btn-primary me-1 mt-1"  
                                        onclick='document_no()' 
                                        data-bs-dismiss="modal" aria-label="Close"><spring:message code="btn.save"/></button>
                                    
                                 
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         <spring:message code="btn.cancel"/>
                                        </button>
                                    </div>
                            
                            </div>
                        </div>
                    </div>
                    
                                                                     <script>

	  $( function() {
		  
		  var newdata = 'เข้าตามเเผน,เข้าไม่ตามเเผน,ไม่ได้เข้า,เลื่อน'; // ข้อความเริ่มต้
		  var trimmedData = newdata.trim(); // ตัดช่องว่างที่อยู่ท้ายสตริง
		var dataItems = trimmedData.split(',');
	    $( ".autocomplete-input3" ).autocomplete({
	      source: dataItems
	    });
	  } );
</script> 
                </div>
               

    


</body>
<script>
function document_no() {
	//alert(55);
 	      var ID = $('#dar_doc_id').val();
			var document = $('#document_no').val();
 	        $.ajax({
 	        type:'POST',
 	        url: '/autoid-dar',
 	        data: {id : document},
 	        
 	        beforeSend: function(xhr) {
 	            // here it is
 	            xhr.setRequestHeader(header, token);
 	        },
 	        
 	     success: function(data){
 	    $("#showolddar2").html(data);	
 	    //alert(data);
 	   return postdatas('/searchdocold-filemanager','showolddar2');
  
 	     }
 	});

	
}
</script>


<script>
    // ดึงอินพุตที่มี id="doc"
    var docDropdown = document.getElementById("doc");
    // ดึงตัวเลือกที่ต้องแสดงหรือซ่อน
    var FOSection = document.getElementById("FO");

    // เพิ่มตัวหรือนำหน้ากับอินพุต dropdown
    docDropdown.addEventListener("change", function() {
        // ถ้าค่าที่ถูกเลือกเป็น "Form (FO)"
        if (docDropdown.value === "Form (FO)") {
            // แสดงส่วนที่มี id="FO"
            FOSection.style.display = "block";
        } else {
            // ซ่อนส่วนที่มี id="FO"
            FOSection.style.display = "none";
        }
    });
</script>
