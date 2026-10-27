<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var = "now" value = "<%= new java.util.Date()%>" />
<%@ include file="common/mainhead.jspf" %>    
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">




<title>Upload</title>
<style>

.load-wrapp {
  width: 100px;
  height: 100px;
  margin-left: 43%;
    margin-top: 25px;
  padding: 20px 20px 20px;
  border-radius: 5px;
  text-align: center;
}

.load-wrapp p {
  padding: 0 0 20px;
}
.load-wrapp:last-child {
  margin-right: 0;
}

.line {
  display: inline-block;
  width: 13px;
  height: 13px;
  border-radius: 13px;
  background-color: #4b9cdb;
}

.ring-1 {
  width: 10px;
  height: 10px;
  margin: 0 auto;
  padding: 10px;
  border: 7px dashed #4b9cdb;
  border-radius: 100%;
}

.ring-2 {
  position: relative;
  width: 45px;
  height: 45px;
  margin: 0 auto;
  border: 4px solid #4b9cdb;
  border-radius: 100%;
}

.ball-holder {
  position: absolute;
  width: 12px;
  height: 45px;
  left: 17px;
  top: 0px;
}

.ball {
  position: absolute;
  top: -11px;
  left: 0;
  width: 16px;
  height: 16px;
  border-radius: 100%;
  background: #4282b3;
}

.letter-holder {
  padding: 16px;
}

.letter {
  float: left;
  font-size: 14px;
  color: #777;
}

.square {
  width: 12px;
  height: 12px;
  border-radius: 4px;
  background-color: #4b9cdb;
}

.spinner {
  position: relative;
  width: 45px;
  height: 45px;
  margin: 0 auto;
}

.bubble-1,
.bubble-2 {
  position: absolute;
  top: 0;
  width: 25px;
  height: 25px;
  border-radius: 100%;
  background-color: #4b9cdb;
}

.bubble-2 {
  top: auto;
  bottom: 0;
}

.bar {
  float: left;
  width: 15px;
  height: 6px;
  border-radius: 2px;
  background-color: #4b9cdb;
}

/* =Animate the stuff
------------------------ */
.load-1 .line:nth-last-child(1) {
  animation: loadingA 1.5s 1s infinite;
}
.load-1 .line:nth-last-child(2) {
  animation: loadingA 1.5s 0.5s infinite;
}
.load-1 .line:nth-last-child(3) {
  animation: loadingA 1.5s 0s infinite;
}

.load-2 .line:nth-last-child(1) {
  animation: loadingB 1.5s 1s infinite;
}
.load-2 .line:nth-last-child(2) {
  animation: loadingB 1.5s 0.5s infinite;
}
.load-2 .line:nth-last-child(3) {
  animation: loadingB 1.5s 0s infinite;
}

.load-3 .line:nth-last-child(1) {
  animation: loadingC 0.6s 0.1s linear infinite;
}
.load-3 .line:nth-last-child(2) {
  animation: loadingC 0.6s 0.2s linear infinite;
}
.load-3 .line:nth-last-child(3) {
  animation: loadingC 0.6s 0.3s linear infinite;
}

.load-4 .ring-1 {
  animation: loadingD 1.5s 0.3s cubic-bezier(0.17, 0.37, 0.43, 0.67) infinite;
}

.load-5 .ball-holder {
  animation: loadingE 1.3s linear infinite;
}

.load-6 .letter {
  animation-name: loadingF;
  animation-duration: 1.6s;
  animation-iteration-count: infinite;
  animation-direction: linear;
}

.l-1 {
  animation-delay: 0.48s;
}
.l-2 {
  animation-delay: 0.6s;
}
.l-3 {
  animation-delay: 0.72s;
}
.l-4 {
  animation-delay: 0.84s;
}
.l-5 {
  animation-delay: 0.96s;
}
.l-6 {
  animation-delay: 1.08s;
}
.l-7 {
  animation-delay: 1.2s;
}
.l-8 {
  animation-delay: 1.32s;
}
.l-9 {
  animation-delay: 1.44s;
}
.l-10 {
  animation-delay: 1.56s;
}

.load-7 .square {
  animation: loadingG 1.5s cubic-bezier(0.17, 0.37, 0.43, 0.67) infinite;
}

.load-8 .line {
  animation: loadingH 1.5s cubic-bezier(0.17, 0.37, 0.43, 0.67) infinite;
}

.load-9 .spinner {
  animation: loadingI 2s linear infinite;
}
.load-9 .bubble-1,
.load-9 .bubble-2 {
  animation: bounce 2s ease-in-out infinite;
}
.load-9 .bubble-2 {
  animation-delay: -1s;
}

.load-10 .bar {
  animation: loadingJ 2s cubic-bezier(0.17, 0.37, 0.43, 0.67) infinite;
}

@keyframes loadingA {
  0 {
    height: 15px;
  }
  50% {
    height: 35px;
  }
  100% {
    height: 15px;
  }
}

@keyframes loadingB {
  0 {
    width: 15px;
  }
  50% {
    width: 35px;
  }
  100% {
    width: 15px;
  }
}

@keyframes loadingC {
  0 {
    transform: translate(0, 0);
  }
  50% {
    transform: translate(0, 15px);
  }
  100% {
    transform: translate(0, 0);
  }
}

@keyframes loadingD {
  0 {
    transform: rotate(0deg);
  }
  50% {
    transform: rotate(180deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

@keyframes loadingE {
  0 {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

@keyframes loadingF {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

@keyframes loadingG {
  0% {
    transform: translate(0, 0) rotate(0deg);
  }
  50% {
    transform: translate(70px, 0) rotate(360deg);
  }
  100% {
    transform: translate(0, 0) rotate(0deg);
  }
}

@keyframes loadingH {
  0% {
    width: 15px;
  }
  50% {
    width: 35px;
    padding: 4px;
  }
  100% {
    width: 15px;
  }
}

@keyframes loadingI {
  100% {
    transform: rotate(360deg);
  }
}

@keyframes bounce {
  0%,
  100% {
    transform: scale(0);
  }
  50% {
    transform: scale(1);
  }
}

@keyframes loadingJ {
  0%,
  100% {
    transform: translate(0, 0);
  }

  50% {
    transform: translate(80px, 0);
    background-color: #f5634a;
    width: 25px;
  }
}

</style>
<style>

.select2-selection__arrow{
display:none;
}
body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        padding: 20px;
    }

    h2 {
        color: #333;
        margin-bottom: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0 2px 6px rgba(0,0,0,0.1);
    }

    th, td {
        padding: 12px 15px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f4f4f4;
        font-weight: 600;
    }

    tr:nth-child(even) {
        background-color: #fafafa;
    }

    .status-submitted {
        background-color: #d4edda; /* สีเขียวอ่อน */
    }
      .error-submitted {
        background-color: #EB8A75; /* สีเขียวอ่อน */
    }
      .warm-submitted {
        background-color: #F29A46; /* สีเขียวอ่อน */
    }
    .status-comment {
        color: #007bff;
        cursor: pointer;
        text-decoration: none;
    }

    .file-link {
        display: flex;
        align-items: center;
        gap: 5px;
        color: #007bff;
        text-decoration: none;
    }

    .file-link img {
        width: 16px;
        height: 16px;
    }
</style>

<c:choose>
                                <c:when test='${cookie.theme.value.equals("moon")}'>
                               			<style>
											.select2-container--default .select2-selection--single .select2-selection__rendered{
											color:#b4b7bd;
											}
									 </style>
                                </c:when>
                                <c:otherwise>
								
                              </c:otherwise>
                               </c:choose> 

</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  






    <!-- BEGIN: Content-->
    <div class="app-content content ">
 

<div class="row breadcrumbs-top">
                        <div class="col-12">
                           
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                     <li class="breadcrumb-item"> Project
                                    </li>  
                                    <li class="breadcrumb-item"><a href="/projectmanager-filemanager?id=${upload_pns.upload_pn_id }&&step=0&&group=1">${upload_pns.upload_pn_note }</a>
                                    </li>
                          
                                </ol>
                            </div>
                        </div>
                        
                        
                    </div>


        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
                        <div class="col-12">
                            <div >
                      
                      
                      
                      
                                <div class="card-body">
                              
                               <div class="row">
                            <!-- Blog -->
                            <div class="col-9" id="maindata">
                            	 <%@ include file="ajax/savetask.jsp" %>  
                            	 
                            	 
                	           
 <c:if test="${ ( tasks.task_idcheck  == Member_id 
 || useradd.user_name ==  Member_name ) 

 
 }">       
       <!-- Blog Comment -->
                            <div class=" mt-1" id="blogComment">
         <c:set var="display" value="none" /> 
		                               <div >
		                                <div class="card">
				                                    <div class="card-body">
		                               <h2><spring:message code="submitted.submissioncheck" /></h2>
		                               
	<%@ include file="task/submission.jsp" %>  	                               
		                               

</div></div></div>
		                    
                        </div>
               </c:if> 
                
                                    
                               
                                  <!-- Blog Comment -->
                            <!-- Blog Comment -->
                            <div class="mt-1" id="blogComment" >
                                        <c:set var="listcheck" value="${Member_name}" />                           
                                <c:set var="list" value="${tasks.task_iduser}" />                    
                                           <c:if test='${fn:contains(list,listcheck)}'>
		                               <div id="dataplan" style="display:${display}">
		                                <div class="card">
				                                    <div class="card-body">
		                               <h2><spring:message code="submitted.status" /></h2>
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
        <th><spring:message code="submitted.inspection_status" /></th>
       
          
                   <c:if test="${actionplan.submission == 1}">    
                    <td class="status-submitted">
        <spring:message code="submitted.checked" />
           </td>
        </c:if>
        
             <c:if test="${actionplan.submission == 0 }">   
                  <td >
          <spring:message code="submitted.notchecked" />
           </td>
        </c:if>
            <c:if test="${actionplan.submission == -2}">   
                  <td class="warm-submitted">
           <spring:message code="submitted.rechecked" />
           </td>
        </c:if>
              <c:if test="${actionplan.submission == -1}">   
                  <td class="error-submitted">
    
          <spring:message code="submitted.failed" />
           </td>
        </c:if>
        
     
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
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">${rows.file_name_original }</img>
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
      
    
  
    
                                                  
    
</table>

<br>
 <c:if test='${actionplan.submission != 1}'>  
   <button type="button"  class="btn btn-primary me-1 waves-effect waves-float waves-light" onclick="tab('post','dataplan')" >
   
    <c:if test='${actionplan.actionplan_id > 0}'>  
  		 <spring:message code="btn.editsubmission" />
   </c:if>
     <c:if test='${actionplan.actionplan_id < 1}'>  

      <spring:message code="btn.submission" />
   </c:if>
   </button>
</c:if>



     <c:if test='${actionplan != null}'>          
           
              <a class="btn btn-light" data-bs-toggle="modal" data-bs-target="#addNewCard4"
		onclick="getdatas('/commenttask-filemanager/${actionplan.actionplan_id }','viewtask','0');"
		><spring:message code="btn.comment" />      [${listcms2.size() + listcms.size() }]</a>
		
		       <a class="btn btn-light" data-bs-toggle="modal" data-bs-target="#addNewCard4"
		onclick="getdatas('/problemstask-filemanager/${actionplan.actionplan_id }','viewtask','0');"
		><spring:message code="project.problemsidentified" />    [${countmyproblems+0 }]</a>
		
		
		 
          </c:if> 
          
          
                     <a href="/task-filemanager/${tasks.task_id}"  class="btn btn-light me-1 waves-effect waves-float waves-light" 
                                   style="float:right"><spring:message code="btn.cancel" /></a>         
                     
</div></div></div>
		                    



 
                            <!-- Leave a Blog Comment -->
                            
                                  <c:if test='${actionplan != null}'>
                                                 <c:set var="link" value="/editactionplan-filemanager?${_csrf.parameterName}=${_csrf.token}"/>    
                                        </c:if>
                                       <c:if test='${actionplan == null}'>
                                                   <c:set var="link" value="/addactionplan-filemanager?${_csrf.parameterName}=${_csrf.token}"/>    
                                        </c:if>
                                    
                                       <form action="${link }" method="POST" modelAttribute="actionplan"  enctype="multipart/form-data">
                                
                            <div class="col-12 mt-1" id="post" style="display:none">
                                <h6 class="section-label mt-25">Action</h6>
                                <div class="card">
                                    <div class="card-body">
                                    
                                    
      
                                            <div class="row">
                                                        <input type="hidden" name="task_id" value="${tasks.task_id}">
                                                       
                                                          <input type="hidden" name="folder" id="folder" value="${folder }"  >
                               					
                                                               
                                          <c:if test='${actionplan != null}'>
                                                    <input type="hidden" name="actionplan_id" value="${actionplan.actionplan_id}"> 
                                        </c:if>
                                   
                                                            
                                                               
                                                               
                                                <div class="col-12">
                                                      <label class="form-label" for="modalRoleName"><spring:message code="submitted.comments" /></label>
                                                    <textarea class="form-control mb-2" name="actionplan_comment"  id="comment-${actionplan.actionplan_id}" rows="4" >${actionplan.actionplan_comment}</textarea>
                                                </div>
                                            
                                     
                                      
                                       
                                            
                                            
                                            
                                        <div class="col-12">
		                                        <label class="form-label" for="modalRoleName"><spring:message code="actionplan.actualcost" /></label>
		                                       <input type="number" id="modalRoleName" name="actionplan_expenses" class="form-control" min="0" value="${actionplan.actionplan_expenses}"  >
		                                        <br>  
		                              </div>
                               
                                            
                                            
                                           
                                            
                                      
                                                  <div class="col-12">
                                                  
                                                   <input  class="form-control mb-2" type="file" name="files" multiple>
                                                  
                                                    
                                               
                                                
                                                </div>
                                                     <br>   <br>  
                                                    <span > 
                                                    
                                                    
                                         
                      				   <c:forEach var="file" items="${actionplan.file}">
		                                            <li class="d-block"  id="img-${file.file_id }">
		                                                <span class="me-25">-</span>
		                                                <span>	<a href="/render2-filemanager?file=${file.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">${file.file_name_original }</a>
									     
									     
									     </span>
									     
					  
								<span class="me-25"><a  href="#"  onclick="getdata3('/delfileactionplan-filemanager/${actionplan.actionplan_id }/${file.file_id }','${actionplan.actionplan_id }');" 
								class="btn btn-sm btn-danger"  ><spring:message code="btn.remove" /></a></span>
		                                            </li>
		                                            <br>
		                                         </c:forEach>  
                                         
                                                
                                                
                                                
                                                           <br>   <br>  
                                                
                                                
                                                <div class="col-12">
                                                     <button type="submit"  class="btn btn-primary me-1 waves-effect waves-float waves-light" ><spring:message code="btn.save" /></button>
                                              <a href="/task-filemanager/${tasks.task_id}"  class="btn btn-light me-1 waves-effect waves-float waves-light" 
                                   ><spring:message code="btn.cancel" /></a>
                   
              
                                   
                                  <c:if test='${actionplan != null}'>
                                               <a href="/delactionplan-filemanager/${actionplan.actionplan_idrfer}/${actionplan.actionplan_id }"  
                                               class="btn btn-danger me-1 waves-effect waves-float waves-light"  onclick="return confirm('<spring:message code="btn.showconfirm" />');" style="float:right"
                                   ><spring:message code="btn.delete" /></a>
                                 </c:if>
                                                </div>
                                            </div>
                        	    
                                    
                          
                                 
                                
                                    </div>
                                </div>
                            </div>
                                 </form>	
                            </c:if>
                        </div>
                                    
                               
                                  <!-- Blog Comment -->
                               
                           </div>
                           
                           
                                <div class="col-3" >
                              <div class="card">
                                 <div class="card-header">
                        <h3 class="card-title"><spring:message code="project.timeline"  /></h3>
                       
                    </div>
                                <div class="card-body">
                                    <ul class="timeline">
                                    
                                        <c:forEach var="row" items="${progress}">
                              
                                       <c:if test= "${row.title == 'task.startTask' 
                                       || row.title == 'task.completeTask'
                                        || row.title == 'task.reviewTask' 
                                         || row.title == 'task.closeAllTaskIssues' }">      
                                        <li class="timeline-item">
                                            <span class="timeline-point  timeline-point-success timeline-point-indicator"></span>
                                            <div class="timeline-event">
                                                <div class="d-flex justify-content-between flex-sm-row flex-column mb-sm-0 mb-1">
                                                          <span class="timeline-event-time">${row.date_log} <spring:message code="${row.timeAgo}" text="-" /></span>
                                                </div>

                                                <p><spring:message code="${row.title}"  /></p>
                                                   <c:if test= "${row.title == 'task.completeTask' }">
                                                    <per>${row.note}</per>
                                                   </c:if>
                                                
                                                
                                                   <c:if test= "${row.title == 'task.reviewTask' || row.title == 'task.closeAllTaskIssues' }">
                                                       <div class="d-flex justify-content-between flex-wrap flex-sm-row flex-column">
                                                    <div >
                                                       
                                                    
		                                                
		                                                        <div class="d-flex align-items-center">
		                                                                   <c:if test="${row.title == 'task.closeAllTaskIssues' }">
		                                                            <div class="avatar bg-light-primary avatar-sm me-50">
		                                                                <span class="avatar-content"><i data-feather='smile'></i></span>
		                                                            </div>
		                                                            </c:if>
		                                                            <div class="avatar bg-light-success avatar-sm me-50">
		                                                                <span class="avatar-content"><i data-feather='check'></i></span>
		                                                            </div>
		                                                            <div class="avatar bg-light-danger avatar-sm" style="display:none">
		                                                                <span class="avatar-content">C</span>
		                                                            </div>
		                                                        </div>
		                                                    </div>
		                                                
                                                
                                                    
                                                    
                                                </div>
                                                    </c:if>
                                            </div>
                                        </li>
                                          </c:if>
                              
                                              <c:if test= "${row.title == 'task.activityTask' }">  
                                            <li class="timeline-item">
                                            <span class="timeline-point  timeline-point-indicator"></span>
                                            <div class="timeline-event">
                                                <div class="d-flex justify-content-between flex-sm-row flex-column mb-sm-0 mb-1">
                                                        <span class="timeline-event-time">${row.date_log} <spring:message code="${row.timeAgo}" text="-" /></span>
                                                </div>
                                                <per>${row.note}</per>
                                                <div class="d-flex flex-row align-items-center" style="display:none" >
                                                  
                                                    <div class="ms-50"  style="display:none">
                                                        <h6 class="mb-0" >John Doe (Client)</h6>
                                                        <span>CEO of Infibeam</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                             </c:if>
                                          <c:if test= "${row.title == 'task.submitTask' }">  
                                                 <li class="timeline-item">
                                            <span class="timeline-point timeline-point-success timeline-point-indicator"></span>
                                            <div class="timeline-event">
                                                <div class="d-flex justify-content-between flex-sm-row flex-column mb-sm-0 mb-1">
                                                                <span class="timeline-event-time">${row.date_log} <spring:message code="${row.timeAgo}" text="-" /></span>
                                                    
                                                </div>
                                                <p class="mb-50"><spring:message code="${row.title}"  /></p>
                                              <button class="btn btn-outline-primary btn-sm waves-effect" type="button" 
                                              data-bs-toggle="modal" data-bs-target="#addNewCard7">
                                                    Show Report
                                                </button>
                                            </div>
                                        </li>
                                             </c:if>
                                             
                                               <c:if test= "${row.title == 'task.openTaskIssue' }">  
                                                 <li class="timeline-item">
                                            <span class="timeline-point timeline-point-warning timeline-point-indicator"></span>
                                            <div class="timeline-event">
                                                <div class="d-flex justify-content-between flex-sm-row flex-column mb-sm-0 mb-1">
               
                                                                 <span class="timeline-event-time">${row.date_log} <spring:message code="${row.timeAgo}" text="-" /></span>
                                                </div>
                                                <p class="mb-50"><spring:message code="${row.title}"  /></p>
                                               
                                               
                                            </div>
                                        </li>
                                             </c:if>
                                             
                                             
                                      </c:forEach>  
                                        
                                        
                                        <div style="display:none">
                                             <li class="timeline-item">
                                            <span class="timeline-point timeline-point-indicator"></span>
                                            <div class="timeline-event">
                                                <div class="d-flex justify-content-between flex-sm-row flex-column mb-sm-0 mb-1">
                                                    <h6>55</h6>
                                                    <span class="timeline-event-time">12 min ago</span>
                                                </div>
                                                <p>Invoices have been paid to the company.</p>
                                                <div class="d-flex flex-row align-items-center">
                                                    <img class="me-1" src="../../../app-assets/images/icons/file-icons/pdf.png" alt="invoice" height="23">
                                                    <span>invoice.pdf</span>
                                                </div>
                                            </div>
                                        </li>
                                    
                                        <li class="timeline-item">
                                            <span class="timeline-point timeline-point-success timeline-point-indicator"></span>
                                            <div class="timeline-event">
                                                <div class="d-flex justify-content-between flex-sm-row flex-column mb-sm-0 mb-1">
                                                    <h6>Financial Report</h6>
                                                    <span class="timeline-event-time">2 hours ago</span>
                                                </div>
                                                <p class="mb-50">Click the button below to read financial reports</p>
                                                <button class="btn btn-outline-primary btn-sm waves-effect" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="true" aria-controls="collapseExample">
                                                    Show Report
                                                </button>
                                                <div class="collapse" id="collapseExample">
                                                    <ul class="list-group list-group-flush mt-1">
                                                        <li class="list-group-item d-flex justify-content-between flex-wrap">
                                                            <span>Last Year's Profit : <span class="fw-bold">$20000</span></span>
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-share-2 cursor-pointer font-medium-2"><circle cx="18" cy="5" r="3"></circle><circle cx="6" cy="12" r="3"></circle><circle cx="18" cy="19" r="3"></circle><line x1="8.59" y1="13.51" x2="15.42" y2="17.49"></line><line x1="15.41" y1="6.51" x2="8.59" y2="10.49"></line></svg>
                                                        </li>
                                                        <li class="list-group-item d-flex justify-content-between flex-wrap">
                                                            <span> This Year's Profit : <span class="fw-bold">$25000</span></span>
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-share-2 cursor-pointer font-medium-2"><circle cx="18" cy="5" r="3"></circle><circle cx="6" cy="12" r="3"></circle><circle cx="18" cy="19" r="3"></circle><line x1="8.59" y1="13.51" x2="15.42" y2="17.49"></line><line x1="15.41" y1="6.51" x2="8.59" y2="10.49"></line></svg>
                                                        </li>
                                                        <li class="list-group-item d-flex justify-content-between flex-wrap">
                                                            <span> Last Year's Commission : <span class="fw-bold">$5000</span></span>
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-share-2 cursor-pointer font-medium-2"><circle cx="18" cy="5" r="3"></circle><circle cx="6" cy="12" r="3"></circle><circle cx="18" cy="19" r="3"></circle><line x1="8.59" y1="13.51" x2="15.42" y2="17.49"></line><line x1="15.41" y1="6.51" x2="8.59" y2="10.49"></line></svg>
                                                        </li>
                                                        <li class="list-group-item d-flex justify-content-between flex-wrap">
                                                            <span> This Year's Commission : <span class="fw-bold">$7000</span></span>
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-share-2 cursor-pointer font-medium-2"><circle cx="18" cy="5" r="3"></circle><circle cx="6" cy="12" r="3"></circle><circle cx="18" cy="19" r="3"></circle><line x1="8.59" y1="13.51" x2="15.42" y2="17.49"></line><line x1="15.41" y1="6.51" x2="8.59" y2="10.49"></line></svg>
                                                        </li>
                                                        <li class="list-group-item d-flex justify-content-between flex-wrap">
                                                            <span> This Year's Total Balance : <span class="fw-bold">$70000</span></span>
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-share-2 cursor-pointer font-medium-2"><circle cx="18" cy="5" r="3"></circle><circle cx="6" cy="12" r="3"></circle><circle cx="18" cy="19" r="3"></circle><line x1="8.59" y1="13.51" x2="15.42" y2="17.49"></line><line x1="15.41" y1="6.51" x2="8.59" y2="10.49"></line></svg>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="timeline-item">
                                            <span class="timeline-point timeline-point-warning timeline-point-indicator"></span>
                                            <div class="timeline-event">
                                                <div class="d-flex justify-content-between flex-sm-row flex-column mb-sm-0 mb-1">
                                                    <h6 class="mb-50">Interview Schedule</h6>
                                                    <span class="timeline-event-time">03:00 PM</span>
                                                </div>
                                                <p>Have to interview Katy Turner for the developer job.</p>
                                                <hr>
                                                <div class="d-flex justify-content-between flex-sm-row flex-column mb-sm-0 mb-1">
                                                    <div class="d-flex flex-row align-items-center">
                                                        <div class="avatar me-1">
                                                            <img src="../../../app-assets/images/avatars/1-small.png" alt="Avatar" height="32" width="32">
                                                        </div>
                                                        <span>
                                                            <p class="mb-0">Katy Turner</p>
                                                            <span class="text-muted">Javascript Developer</span>
                                                        </span>
                                                    </div>
                                                    <div class="d-flex align-items-center cursor-pointer mt-sm-0 mt-50">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-message-square me-1"><path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path></svg>
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-phone-call"><path d="M15.05 5A5 5 0 0 1 19 8.95M15.05 1A9 9 0 0 1 23 8.94m-1 7.98v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z"></path></svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="timeline-item">
                                            <span class="timeline-point timeline-point-danger timeline-point-indicator"></span>
                                            <div class="timeline-event">
                                                <div class="d-flex justify-content-between flex-sm-row flex-column mb-sm-0 mb-1">
                                                    <h6>Online Store</h6>
                                                    <span class="timeline-event-time">03:00PM</span>
                                                </div>
                                                <p>
                                                    Develop an online store of electronic devices for the provided layout, as well as develop a mobile
                                                    version of it. The must be compatible with any CMS.
                                                </p>
                                                <div class="d-flex justify-content-between flex-wrap flex-sm-row flex-column">
                                                    <div>
                                                        <p class="text-muted mb-50">Developers</p>
                                                        <div class="d-flex align-items-center">
                                                            <div class="avatar bg-light-primary avatar-sm me-50">
                                                                <span class="avatar-content">A</span>
                                                            </div>
                                                            <div class="avatar bg-light-success avatar-sm me-50">
                                                                <span class="avatar-content">B</span>
                                                            </div>
                                                            <div class="avatar bg-light-danger avatar-sm">
                                                                <span class="avatar-content">C</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="mt-sm-0 mt-1">
                                                        <p class="text-muted mb-50">Deadline</p>
                                                        <p class="mb-0">20 Dec 2077</p>
                                                    </div>
                                                    <div class="mt-sm-0 mt-1">
                                                        <p class="text-muted mb-50">Budget</p>
                                                        <p class="mb-0">$50000</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="timeline-item">
                                            <span class="timeline-point timeline-point-info timeline-point-indicator"></span>
                                            <div class="timeline-event">
                                                <div class="d-flex justify-content-between align-items-center mb-50">
                                                    <h6>Designing UI</h6>
                                                    <div>
                                                        <span class="badge rounded-pill badge-light-primary">Design</span>
                                                    </div>
                                                </div>
                                                <p>
                                                    Our main goal is to design a new mobile application for our client. The customer wants a clean &amp; flat
                                                    design.
                                                </p>
                                                <div>
                                                    <span class="text-muted">Participants</span>
                                                    <div class="avatar-group mt-50">
                                                        <div data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="bottom" title="" class="avatar pull-up" data-bs-original-title="Vinnie Mostowy">
                                                            <img src="../../../app-assets/images/portrait/small/avatar-s-5.jpg" alt="Avatar" height="30" width="30">
                                                        </div>
                                                        <div data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="bottom" title="" class="avatar pull-up" data-bs-original-title="Elicia Rieske">
                                                            <img src="../../../app-assets/images/portrait/small/avatar-s-7.jpg" alt="Avatar" height="30" width="30">
                                                        </div>
                                                        <div data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="bottom" title="" class="avatar pull-up" data-bs-original-title="Julee Rossignol">
                                                            <img src="../../../app-assets/images/portrait/small/avatar-s-10.jpg" alt="Avatar" height="30" width="30">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                              </div>
                                    </ul>
                                </div>
                                </div>               
                                           
                               
                           </div>
                            <!--/ Blog -->
                            
           
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
  <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>

    <script>
    

    function closetab(div){
    	
    	 $("#"+div).hide();
    }
    
    function mytype(){
    	 var value = document.getElementById("duetype").value;
    	
    	 
    	 
      	 if(value == 1){
    		 $("#duetype2").show();
    		 $("#duetype1").hide();
    		
    	 }else if(value == 2){
    
    		 $("#duetype1").hide();
    		 $("#duetype2").hide();
    		 
    	 }else{
    		 $("#duetype1").show();
    		 $("#duetype2").hide();
    	 }
    	 
    }
    
    
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
            var value = document.getElementById("duetype").value;
         	 if(value == 1){
        		 $("#duetype2").show();
        		 $("#duetype1").hide();
        		
        	 }else if(value == 2){
        
        		 $("#duetype1").hide();
        		 $("#duetype2").hide();
        		 
        	 }else{
        		 $("#duetype1").show();
        		 $("#duetype2").hide();
        	 }
        	 
        	 
            
            
        })
        
        var element = document.getElementById("project");
  element.classList.add("active");      


    </script>
    
    
    
    
</body>
<div class="modal fade" id="addNewCard" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                                <div class="text-center mb-4">
                                    <h1 class="role-title"><spring:message code="work.assignwork"  /></h1>
                                    <p><spring:message code="work.tasksteam"  /></p> 
                                </div>
                                <!-- Add role form -->
                              <form:form action="/edittask-filemanager?${_csrf.parameterName}=${_csrf.token}" method="POST" modelAttribute="task" id="formaddNewCardValidation" name="formaddNewCardValidation">
                                     <div id="savestatus">
                                   
          
                                    </div>
                                         <br>
                                    <div class="col-12 text-center mt-2">
     <button type="submit"  class="btn btn-primary me-1 waves-effect waves-float waves-light" 
     ><spring:message code="btn.save"  /></button>
                                    </div>
                            </form:form>
                            
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
                </div>
 





<div class="modal fade" id="addNewCard2" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                                <div class="text-center mb-4">
                                    <h1 class="role-title"><spring:message code="task.responsible" /></h1>
                                </div>
                                <!-- Add role form -->
                              <form:form method="POST" modelAttribute="task" id="formaddNewCardValidation2" name="formaddNewCardValidation2">
                                     <div >
                                   
                                      <input  type="hidden" name="task_id" id="task_id" value="${tasks.task_id }">
                                       <div  class="row">
                                    <div class="col-12">
                                        <label class=" form-label" for="modalRoleName"><spring:message code="tables.inspector"  /></label>
                              
                                    <select class="select2 form-select" name="task_idcheck"> 
                                    	<option value="0" >-</option>
    					<c:forEach var="nameItem" items="${user_all}">
            				<option value="${nameItem.user_id}" <c:if test="${nameItem.user_id == tasks.task_idcheck}"> </c:if>>${nameItem.user_name}</option>
   					 </c:forEach>
					</select>
                                        
                                    </div>
                                    
                                    
                                  </div>   
                                  <br>
                                    
                                <div  class="row">
                                    <div class="col-12">
                                        <label class="form-label" for="modalRoleName"><spring:message code="goals.responsible"  /></label>
                              
                                    <select class="select2 form-select" name="task_iduser" id="default-select-multi" multiple="multiple"> 
    <c:set var="listrespon" value="${tasks.task_iduser}" />
 
    <c:forEach var="nameItem" items="${user_all}">
            <option value="${nameItem.user_name}" ${fn:contains(listrespon, nameItem.user_name) ? 'selected' : ''}>${nameItem.user_name}</option>
    </c:forEach>
</select>
                                        
                                    </div>
                                    
                                    
                                  </div>   
                                    </div>
                                         <br>
                                    <div class="col-12 text-center mt-2">
     <button type="button"  class="btn btn-primary me-1 waves-effect waves-float waves-light" 
     data-bs-dismiss="modal" aria-label="Close" 
      onclick='postdata("/addteamtask-filemanager?${_csrf.parameterName}=${_csrf.token}","formaddNewCardValidation2","maindata")'>
    <spring:message code="btn.save"  />
      </button>
                                    </div>
                            </form:form>
                            
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
                </div>


                             
       <div class="modal fade" id="addNewCard3" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                                <div class="text-center mb-4">
                                    <h1 class="role-title"><spring:message code="task.documents" /></h1>
                                </div>
                                <!-- Add role form -->
                              <form:form method="POST" modelAttribute="task" id="formaddNewCardValidation3" name="formaddNewCardValidation3" enctype="multipart/form-data">
                                     <div >
                                   
                                 
                                
                                <div  class="row">
                                    <div class="col-8">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.documents" /></label>
                              
                                    <input type="file" name="file" class="form-control" >
                                    <input type="hidden" name="folder" id="folder" value="${folder }"  >
                                    <input  type="hidden" name="task_id" id="task_id" value="${tasks.task_id }">
                                    </div>
                                                                                                        
                                     <div class="col-4 text-center mt-2">
     <button type="button"  class="btn btn-primary me-1 waves-effect waves-float waves-light" 
     onclick='postdata("/addfiletask-filemanager?${_csrf.parameterName}=${_csrf.token}","formaddNewCardValidation3","maindata")'>
     <spring:message code="btn.add" />
     </button>
                                    </div> 
                                    
                                  </div>  
  
                                    </div>             

                                         <br>
                                         
                                         
                              
                                         
                                         <h4 class="mb-50"><spring:message code="task.documents" /></h4>
                                         
                                         <div id="files">
		                                        <c:forEach var="row" items="${files}" begin="0" end="5">
		                                            <li class="d-block" id="file_${row.file_id }">
		                                                <span class="me-25">-</span>
		                                                <span>	<a href="/render2-filemanager?file=${row.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">${row.file_name_original }</a>
									     
									     
									     </span>
									     
					  
		<span class="me-25"><a  href="#"  onclick="deletefile('/delfiletask-filemanager/${tasks.task_id }/${row.file_id }','file_${row.file_id }','maindata');" class="btn btn-sm btn-danger"  >นำออก</a></span>
		                                            </li>
		                                            <br>
		                                         </c:forEach>  
                                           </div>   
                                           
                                           <br><br><br>
                                         
                      
                            </form:form>
                            
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
                </div>
       
       
       
       
       
       
       
        <div class="modal fade" id="addNewCard7" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                                <div class="text-center mb-4">
                                    <h1 class="role-title" id="task_title"><spring:message code="btn.submission" /></h1>
                                </div>
                                
                    <c:set var="viewmode" value="1" />
                                	<%@ include file="task/submission.jsp" %>  	  
       
                          
                   
                        </div>
                                    
                               
                         
                            
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
       
       
       
                               
       <div class="modal fade" id="addNewCard4" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                                <div class="text-center mb-4">
                                    <h1 class="role-title" id="task_title"><spring:message code="submitted.inspection" /></h1>
                                </div>
                                
                  
                                
                                <!-- Add role form -->
                          
                  <div id="viewtask"></div>
                          
                   
                        </div>
                                    
                               
                         
                            
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
             
       
       
             <div class="modal fade" id="addNewCard5" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-2 pb-2">
                                <div class="text-center mb-4">
                                    <h1 class="role-title" id="task_title"><spring:message code="work.manage_resources"  /></h1>
                                </div>
                                
                                
     
                              <form:form method="POST" modelAttribute="task" id="formresources" name="formresources" enctype="multipart/form-data">
                                <input type="hidden"  name="task_id"  id="task_id" value="${tasks.task_id }"  >
                                   <input type="hidden"  name="upload_pn_id"  id="upload_pn_id" value="${upload_pns.upload_pn_id }"  >
                                   
                                   
                             	 <div  class="row" id="edit_id" style="display:none">
                                    <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.name"  /></label>
                               <select name="resources_id" class="form-select">
                            
                           <c:forEach items="${resource }" var="row" varStatus="count">  
                               	<option  data-custom="${row.estimatedCost }" value="${row.id }">${row.name } ,${row.resourceType } [<spring:message code="resources.estimatedCost"  /> :${row.estimatedCost }]</option>
                             </c:forEach>  	
                               </select>
                                    </div>
                                    <br>
                                    <div class="col-4 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.PlannedQty" /></label>
                                                             <input type="number"  name="plannedQty"  id="plannedqty"  class="form-control"  required >
                                                                  <br> <br>
                                    </div>
                                 <div class="col-4 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.actual" /></label>
                                                             <input type="number"  name="actual"  id="actual"  class="form-control"   >
                                                                  <br> <br>
                                    </div>
                                 <div class="col-4 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.actual_cost" /></label>
                                                             <input type="number"  name="actualCost"  id="actual_cost"  class="form-control"   >
                                                                  <br> <br>
                                    </div>
                              
                               <input type="hidden"  name="estimatedCost"  id="estimatedCost" value="${resource_use.estimatedCost}"   class="form-control"   >
                         
  <button type="button"  class="btn btn-primary me-1 waves-effect waves-float waves-light" 
     onclick='postdatatask("/saveresources-filemanager?${_csrf.parameterName}=${_csrf.token}","formresources","maindataresources")'>
     <spring:message code="btn.add" />
     </button>
       						    </div>     
                             

                                     <div >
                      
                                    </div>             

                                  
                                         
                                   
                            </form:form>
  
     
                                                                                           <div class="col-12 text-center mt-2">
   
         <button type="button"  id="addf" class="btn btn-light me-1 waves-effect waves-float waves-light" onclick="add()">
     <spring:message code="btn.add" />
     </button>
            <button type="button" id="cancelf" class="btn btn-light me-1 waves-effect waves-float waves-light" onclick="reset()" style="display:none">
     <spring:message code="btn.cancel" />
     </button>
        <button type="button" id="deletef"  class="btn btn-danger me-1 waves-effect waves-float waves-light"  style="display:none"
     onclick='deletedatatask("/deleteresources-filemanager?${_csrf.parameterName}=${_csrf.token}","formresources","maindataresources")'>
     <spring:message code="btn.delete" />
     </button>
          <br>       <br>
   
                                    </div> 
                                   
                                   
                         <div id="maindataresources">
                       <table  style="font-size:16px">
                                    <thead>
                                        <tr>
<th class="form-label fixhead" >#</th>    
<th class="form-label fixhead" ><spring:message code="resources.name"  /></th>     	                          
<th class="form-label fixhead" ><spring:message code="resources.resourceType"  /></th>     
<th class="form-label fixhead" ><spring:message code="resources.PlannedQty"  /></th>    
<th class="form-label fixhead" ><spring:message code="resources.estimatedCost"  /></th>   
<th class="form-label fixhead" ><spring:message code="resources.actual"  /></th>    
<th class="form-label fixhead" ><spring:message code="resources.actual_cost"  /></th> 
<th class="form-label fixhead" ><spring:message code="resources.unit"  /></th>    
<th class="form-label fixhead" ><spring:message code="tables.action"  /></th>  
                                        </tr>
                                    </thead>
                                    <tbody>
	
	
  	
<c:set var="actual" value="0" />  	
<c:set var="plan" value="0" />  	 				
				                           <c:forEach items="${resourceUse }" var="row" varStatus="count">  
                          
                                              <tr> 
            	 <td class="form-label fixhead" style='padding-left:5px'> ${count.count }</td>   
            	 
<td class="form-label fixhead">${row.name}</td>     
<td class="form-label fixhead">${row.resourceType }</td>   
<td class="form-label fixhead">${row.plannedQty }</td> 
<td class="form-label fixhead">${row.estimatedCost }</td> 
<td class="form-label fixhead">${row.actual }</td> 
<td class="form-label fixhead">${row.actualCost }</td> 
<td class="form-label fixhead">${row.unit }</td>   
 <td class="form-label fixhead">
   <button  onclick='getdata2("/viewresource-filemanager/${row.id}/${upload_pns.upload_pn_id }","edit_id")'  class="btn btn-light btn-sm"><i data-feather='eye' ></i></button>
    </td>

 
                               </tr>
								 <c:set var="actual" value="${ actual + ( row.actual * row.actualCost) }" />  				                  
				               <c:set var="plan" value="${ Total + ( row.plannedQty * row.estimatedCost) }" />  		
                          </c:forEach>                
				                                      
   
                                    </tbody>
                                    
                                     <tfoot>
                                                        <tr>
                                        
            	 <td class="form-label fixhead" style='padding-left:5px'> </td>   
            	 
<td class="form-label fixhead" ></td>     
<td class="form-label fixhead" ></td>   
<td class="form-label fixhead" > </td> 
<td class="form-label fixhead" > </td> 
<td class="form-label fixhead" > </td> 
<td class="form-label fixhead" > </td> 
<td class="form-label fixhead"  ><spring:message code="resources.actual"  /></td>   
 <td class="form-label fixhead">${actual} / ${plan}</td>

 
                               </tr>                        
                                    </tfoot>               
                                    
                                    </table>   
	</div> 	</div>                                      
                                           <br><br><br>
                                         
                     
                          
                   
                        </div>
                                    
                               
                         
                            
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
             
       <div class="modal fade" id="addNewCard6" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                                <div class="text-center mb-4">
                                    <h1 class="role-title"><spring:message code="task.increaseprogress" /></h1>
                                </div>
                                <!-- Add role form -->
                              <form:form action="/increaseprogress-filemanager" method="POST" modelAttribute="task" >
                               <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    				   <input  type="hidden" class="form-control"  name="task_id"  value="${tasks.task_id}">	
                    					   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="task.progressreport" /></label>
                                                           <textarea   class="form-control"  name="activity_task"  >${tasks.activity_task}</textarea>
                                           </div>    
                                              <br>   
                     							   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="task.worksummary" /></label>
                                                           <textarea   class="form-control"  name="end_task"  >${tasks.end_task}</textarea>
                                           </div>      
                                         <br>
                                    <div class="col-12 text-center mt-2">
     <button type="submit"  class="btn btn-primary me-1 waves-effect waves-float waves-light" 
     ><spring:message code="btn.save"  /></button>
                                    </div>
                            </form:form>
                            
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
                </div>
       
       
       
       
        
       
       
      <div id="delstatus"></div>          
                                    
  <link href="<c:url value="/lib/multiselect/css/jquery.multiselect.css" />" rel="stylesheet">   
  <link href="<c:url value="/lib/multiselect/css/app.css" />" rel="stylesheet">                                
 <script src="<c:url value="/lib/multiselect/js/jquery.multiselect.js" />"></script>
 <script src="<c:url value="/lib/multiselect/js/app.js" />"></script>
 
     <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>
 
 
  	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
            
              
              
              
              
              
                
  <script>  
function tab(show,hide){
	  
	  $("#"+show).show();
	 $("#"+hide).hide();
	  
	   // เลื่อนไปล่างสุดของหน้า
	    $("html, body").animate({
	        scrollTop: $(document).height()
	    }, "fast");
	 
}
  
function edit(div){
	  
	  $("#sub-"+div).toggle();
	  $("#edit-"+div).toggle();
	  var c = $("#c-"+div).val();
	  var f = $("#f-"+div).val();
	  $("#subimg-"+div).html(f);
     $("#comment-"+div).val(c);
	  $("#img-"+div).show();
	 $("#imgs-"+div).hide();
	  
	 
	 
}
function edit2(div){
	  
	  $("#subs-"+div).toggle();
	  $("#edits-"+div).toggle();
	  var c = $("#c-"+div).val();
	  var f = $("#f-"+div).val();
	  $("#subimg-"+div).html(f);
      $("#comment-"+div).val(c);
	 $("#img-"+div).show();
	$("#imgs-"+div).hide();
	  
}
  function filedownload(file){
  	
  	window.open("/filedownload-filemanager?id="+file, '_blank').focus();
  }
	   
  

  function postdatas(Mode,Mode2,Mode3) {
  	 $("#"+Mode3).html('<div class="load-wrapp"> <div class="load-3"><div class="line"></div><div class="line"></div><div class="line"></div> </div> </div>');
	    var formData = new FormData($("#"+ Mode2)[0]);

			    $.ajax({
			        url: Mode,
			        type: "POST",
			        data: formData,
			        processData: false,
			        contentType: false,
				    success: function(result){
	
				    
					 setTimeout(function(){
	    	    	        // ค่อย ๆ จางหาย spinner แล้วค่อยแทน data
	    	    	        $("#"+Mode3).fadeOut(300, function(){
	    	    	            $("#"+Mode3).html(result).fadeIn(300);
	    	    	            
	    	    	            getdatas2('/addproblems-filemanager/${tasks.task_id}','subproblems_form',0);
	    	    	        });
	    	    	    }, 1000);
				    
		
				       },
				       error: function(xhr) {
			               window.location.href = "/login";
			       }
				    });

			    
			    
				

			    
	    return false;
	    }	
  
  
  
  function deletedatas(Mode,Mode2,Mode3) {
	  
		if (confirm('<spring:message code="policy.deleteconfirm"  />'))
		{
	  
	  $("#"+Mode3).html('<div class="load-wrapp"> <div class="load-3"><div class="line"></div><div class="line"></div><div class="line"></div> </div> </div>');
		    var formData = new FormData($("#"+ Mode2)[0]);

				    $.ajax({
				        url: Mode,
				        type: "POST",
				        data: formData,
				        processData: false,
				        contentType: false,
					    success: function(result){
		
					    
						 setTimeout(function(){
		    	    	        // ค่อย ๆ จางหาย spinner แล้วค่อยแทน data
		    	    	        $("#"+Mode3).fadeOut(300, function(){
		    	    	            $("#"+Mode3).html(result).fadeIn(300);
		    	    	            
		    	    	            getdatas2('/addproblems-filemanager/${tasks.task_id}','subproblems_form',0);
		    	    	        });
		    	    	    }, 1000);
					    
			
					       },
					       error: function(xhr) {
				               window.location.href = "/login";
				       }
					    });

				    
		}    
					

				    
		    return false;
		    }	
	  
  
  
  
	    function postdata(Mode,Mode2,Mode3) {

		    var formData = new FormData($("#"+ Mode2)[0]);

				    $.ajax({
				        url: Mode,
				        type: "POST",
				        data: formData,
				        processData: false,
				        contentType: false,
					    success: function(result){
					    $('#' + Mode3).html(result);
			
					       },
					       error: function(xhr) {
				               window.location.href = "/login";
				       }
					    });


				    $("#"+Mode2)[0].reset();
				    
		    return false;
		    }	
	    
	    function postdatatask(Mode,Mode2,Mode3) {

		    var formData = new FormData($("#"+ Mode2)[0]);

				    $.ajax({
				        url: Mode,
				        type: "POST",
				        data: formData,
				        processData: false,
				        contentType: false,
					    success: function(result){
					    $('#' + Mode3).html(result);
					    $("#tasks_resources").html(data);	
					       },
					       error: function(xhr) {
				               window.location.href = "/login";
				       }
					    });


					  $("#maindataresources").show();
					  $("#edit_id").hide();
					  $("#addf").show();
					  $("#cancelf").hide();
					  $("#deletef").hide();
		    return false;
		    }	
	    
	    function deletedatatask(Mode,Mode2,Mode3) {

	       	
	    	if (confirm('<spring:message code="action.delete"  />?'))
		    	{
	    		
			    	var formData = new FormData($("#"+ Mode2)[0]);
		
						    $.ajax({
						        url: Mode,
						        type: "POST",
						        data: formData,
						        processData: false,
						        contentType: false,
							    success: function(result){
							    $('#' + Mode3).html(result);
							    $("#tasks_resources").html(data);	
							       },
							       error: function(xhr) {
							               window.location.href = "/login";
							       }
							    });
		
		
							  $("#maindataresources").show();
							  $("#edit_id").hide();
							  $("#addf").show();
							  $("#cancelf").hide();
							  $("#deletef").hide();
				    return false;
		    	}
		    }	
	    
	  
	
    function getdata3(url,id){
	    	
	    	
	    	if (confirm("คุณต้องการดำเนินการ?"))
	    	{
			    	    $.ajax({
			    	        type:'GET',
			    	        url:url,
			    	        data: {ID : id},
			    	        
			    	     success: function(data){
			    	    $("#delstatus").html(data);	
		
			    	     },
					       error: function(xhr) {
				               window.location.href = "/login";
				       }
			    	});
	    		
	    	}
	    	
	    	
	    	
	    	

return false;

}
    
    function add(){
   	  $("#addf").hide();
   	$("#deletef").hide();
  	 $("#cancelf").show();
  	  $("#maindataresources").hide();
    	  $("#edit_id").show();
    	  
    	  $("#actual_cost").val(0);
    	  $("#actual").val(1);
          $("#use_id").val(0);
          $("#plannedqty").val(1);
return false;

}  
    
    
    
    
    function reset(){

    	  $("#addf").show();
    	  $("#cancelf").hide();
    	   	$("#deletef").hide();
    	  $("#maindataresources").show();
    	  $("#edit_id").hide();
     	  $("#actual").val(1);
          $("#use_id").val(0);
          $("#actual_cost").val(0);
return false;

}  
    
    function getdata2(url,div){
    	
  	  $("#maindataresources").hide();
	  $("#edit_id").show();
	  $("#addf").hide();
	  
	  
        $.ajax({
        type:'GET',
        url:url,
        
     success: function(data){
    $("#"+div).html(data);	
 	 $("#cancelf").show();
 	 $("#deletef").show();
     },
     error: function(xhr) {
         window.location.href = "/login";
 }
});
        
}
    function getdata(url,id){
    	
        $.ajax({
        type:'GET',
        url:url,
        data: {ID : id},

     success: function(data){
    $("#savestatus").html(data);	

     },
     error: function(xhr) {
         window.location.href = "/login";
 }
});

return false;

}
    
    
    function getdatas2(url,div,type){
	    
    	var status = 0;
    	
    	
    	if(type == 1){
      		if (confirm('<spring:message code="policy.deleteconfirm"  />'))
			{
    			status=1;
			}
    	}else{
    		status=1;
    	}

			
			if(status == 1){
				
			     
				  $.ajax({
		    	        type:'GET',
		    	        url:url,
		    	        data: {ID : 0},
		    	        beforeSend: function(){
		    	            // แสดง spinner พร้อมข้อความเล็ก ๆ
		    	       
		    	          },
		    	     success: function(data){
		    	    	 $("#"+div).html(data);
		    
		    	     },
				       error: function(xhr) {
			               window.location.href = "/login";
			       }
		    	});
				
			}
			    	  
		
		
		
		return false;
		
}

	    
	    function getdatas(url,div,type){
	    
	    	var status = 0;
	    	
	    	
	    	if(type == 1){
	      		if (confirm('<spring:message code="policy.deleteconfirm"  />'))
				{
	    			status=1;
				}
	    	}else{
	    		status=1;
	    	}
	
				
				if(status == 1){
					
				     $("#"+div).html('<div class="load-wrapp"> <div class="load-3"><div class="line"></div><div class="line"></div><div class="line"></div> </div> </div>');
					  $.ajax({
			    	        type:'GET',
			    	        url:url,
			    	        data: {ID : 0},
			    	        beforeSend: function(){
			    	            // แสดง spinner พร้อมข้อความเล็ก ๆ
			    	       
			    	          },
			    	     success: function(data){
			    	    	 setTimeout(function(){
			    	    	        // ค่อย ๆ จางหาย spinner แล้วค่อยแทน data
			    	    	        $("#"+div).fadeOut(300, function(){
			    	    	            $(this).html(data).fadeIn(300);
			    	    	        });
			    	    	    }, 1000);
			    
			    	     },
					       error: function(xhr) {
				               window.location.href = "/login";
				       }
			    	});
					
				}
				    	  
			
			
			
			return false;
			
	}
	
	    
	    
    function deletefile(url,file,div){
		    
	    	var status = 0;
	
	    		if (confirm("คุณต้องการดำเนินการ?"))
				{
	    			status=1;
				}
	    
	
				
				if(status == 1){
					  $.ajax({
			    	        type:'GET',
			    	        url:url,
			    	        data: {ID : 0},
			    	        
			    	     success: function(data){
			    	    $("#"+div).html(data);	
		
			    	     },
					       error: function(xhr) {
				               window.location.href = "/login";
				       }
			    	});
					   $("#"+file).remove();	
				}
				    	  
			return false;
			
	}
	    
</script>  


<script>
function openCenteredWindow(url, name, width, height) {
    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}




</script>    

<script>
document.querySelector('select[name="resources_id"]').addEventListener('change', function () {
    // option ที่ถูกเลือก
    let selectedOption = this.options[this.selectedIndex];

    // ดึงค่าจาก data-custom
    let estimatedCost = selectedOption.getAttribute('data-custom');

    // ใส่ค่าใน estimatedCost
    document.getElementById('estimatedCost').value = estimatedCost;

    // ตรวจสอบ actual_cost
    let actualCostInput = document.getElementById('actual_cost');
    actualCostInput.value = estimatedCost;
   
});
</script>
	    