<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="common/mainhead.jspf" %>    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel='stylesheet' href='https://code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css'>
 <link href="<c:url value="/resources/treeview/style.css" />" rel="stylesheet">
 

  <link href="<c:url value="/lib/photoviewer/css/photoviewer.css" />" rel="stylesheet">

 
 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>File info</title>
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
                        <div class="col-lg-8 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"> <spring:message code="File.info" text="ข้อมูลเอกสาร"/></h4>
                                </div>
                                <div class="card-body">
                                     
                               
                              
                    
                                               
                                   
                                          <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                <spring:message code="files.file_name" text="รหัสเอกสาร" /> : </label>
                                                <div class="col-sm-9">
                                                     ${file.file_no }
                                                </div>
                                            </div>  
                                            
                                            
                                            
                                <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                <spring:message code="files.file_name" text="ชื่อไฟล์" /> :</label>
                                                <div class="col-sm-9">
                                                    ${file.file_name_original }
                                                </div>
                                            </div>
                            
                                               <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                <spring:message code="files.file_rev" text="Rev" /> :</label>
                                                <div class="col-sm-9">
                                                    ${file.file_rev }
                                                </div>
                                            </div>     
                                              
                                               <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                <spring:message code="files.file_type" text="ชนิด" /> :</label>
                                                <div class="col-sm-9">
                                                    ${file.file_type }
                                                </div>
                                            </div>
                                                <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                <spring:message code="files.file_size2" text="ขนาด" /> :</label>
                                                <div class="col-sm-9">
                                                    ${file.file_size2 }
                                                </div>
                                            </div>
                                            
                                                  <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                                 <spring:message code="dar.Status" text="สถานะเอกสาร" /> :</label>
                                                <div class="col-sm-9">
                                                       <c:if test="${file.file_type2  == -1}">    
                              <spring:message code="file.filecanceled" text="ถูกยกเลิก"  var="status" />
                         
                               </c:if> 
                                                               <c:if test="${file.file_type2  == -2}">    
                                <spring:message code="file.fileexpired" text="หมดอายุ"  var="status" />
                              
                               </c:if>         
                                   <c:if test="${row.file_type2  == -3}">    
                                <spring:message code="file.fileexpired2" text="เวอร์ชั่นเก่า"  var="status" />
                              
                               </c:if>   
                                  
                                                   ${status }
                                            
                                                </div>
                                            </div>  
                                           
                                            
                          
                                          
                          
                                            
                                              <div class="mb-1 row">     
                                                       <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                               <spring:message code="file.read" text="ไฟล์สำหรับเรียกดู" />: </label>
                                              
                                              
                                              
                                                <div class="col-sm-9">      
                                                
                     
                                                
                                                
		                 <c:set var="path" value="${file.file_path}" />
                      <c:if test="${file.file_shotcut ne null}">   
                       <c:set var="path" value="${file.file_shotcut}" />
                      </c:if>     
								 
							   <c:if test="${file.file_type.equals('video/mp4')  }"> 		         
									         
								       <video width="600" height="350" controls autoplay controlsList="nodownload" oncontextmenu="return false;">
    <source src="/document-filemanager/${Drives }/${path}/${file.file_name}" type="video/mp4" />
</video>  
								   </c:if>  
								   
								   
				  		 
				  		   <c:if test="${file2.file_type.equals('application/pdf')  }"> 	
			  		   
		               
         						
         								 <a href="/showpdf-filemanager/${Drives }/${file2.file_path}/?id=${file2.file_id }" target="blank" class="mb-50">
                                                            <img src="/resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18">
                                                            <small class="text-muted fw-bolder">${file2.file_name_original}</small>
                                                        </a>
                                                        
                                                        
    				      </c:if> 						   	     
									         
		
			  
			  
			  		   <c:if test="${file.file_type.equals('application/pdf')  }"> 	
			  		   
			  		   
			  
		               
         								 <a href="/showpdf-filemanager/${Drives }/${path}/?id=${file.file_id }" target="blank" class="mb-50">
                                                            <img src="/resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18">
                                                            <small class="text-muted fw-bolder">${file.file_name_original}</small>
                                                        </a>
                                                       
    				</c:if> 	
		              
		              
		              
		              
		              
		       <c:if test="${file.file_type.equals('image/png') or file.file_type.equals('image/webp') or file.file_type.equals('image/webp') or file.file_type.equals('image/jpg')  
		       or file.file_type.equals('image/jpeg') }"> 	        
		              
		              
		                     <div class="image-set">                                              
      <a data-gallery="photoviewer" data-title="Idoc" data-group="a"
         href="/document-filemanager/${Drives }/${path}/${file.file_name}">
        <img src="/document-filemanager/${Drives }/${path}/${file.file_name}" alt="" style="width:150px">
      </a>              
	    </div>		
		  </c:if> 
		  
		  
		  					
		 
									             </div>
									             </div>
									          
				              
                      
                      
                       <sec:authorize access="hasAnyAuthority('DOWNLOAD-${Folders }','ROLE_SUPPEROWNER')"> 
                      
                               <div class="mb-1 row">     
                                                       <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">
                                              <spring:message code="file.load" text="ไฟล์สำหรับดาวน์โหลด" />: </label>
                                              
                                <c:set var="path" value="${file.file_path}" />
                      <c:if test="${file.file_shotcut ne null}">   
                       <c:set var="path" value="${file.file_shotcut}" />
                      </c:if>                  
                                              
                                                <div class="col-sm-9">   
                                                    <c:if test="${file.file_id > 0}"> 
									           <a href="/document-filemanager/${Drives }/${path}/${file.file_name}"  onclick="filedownload()" download class="mb-50">
                                                            <img src="/resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18">
                                                            <small class="text-muted fw-bolder">${file.file_name}</small>
                                                        </a>
                                                        
     							    
									         </c:if> 	
									         
                                                         </div>
									             </div>  
                                                </sec:authorize>         
				                 
            
                                            
   <br> <br>
     														<c:if test="${file.file_type2  == -2}">   
                                                                 <sec:authorize access="hasAnyAuthority('APP-DAR')">    
                                               					    <a href="/renew-filemanager?id=${file.file_id }&&Folders=${file.file_path}" class="btn btn-light waves-effect waves-float waves-light" onclick="return confirm('Renew document this (3 Month) ? ')">
							          								Renew</a>  
							          
						                                         </sec:authorize> 
						                                   </c:if> 
                                </div>
                            </div>
                        </div>
                  
                    <!-- Timeline Card -->
                        <div class="col-lg-4 col-12">
                            <div class="card card-user-timeline">
                                <div class="card-header">
                                    <div class="d-flex align-items-center">
                                        <i data-feather="list" class="user-timeline-title-icon"></i>
                                        <h4 class="card-title">Update Timeline</h4>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <ul class="timeline ms-50">
                                    
                                   
                                    
                                              <c:forEach items="${dar }" var="row" varStatus="count">
                                           <c:if test="${row.dar_request.equals('request2')}">   
                                        <li class="timeline-item">
                                          
                                          
                                            <span class="timeline-point timeline-point-warning timeline-point-indicator"></span>
                                            <div class="timeline-event">
                                                <h6>${row.dar_date_add }</h6><br>
                                                <p style="font-size:14px">
                                              
                                                ${fn:replace(row.dar_doc_remark, newLineChar, "<br />")}
                                                </p><br>
                                                <div class="d-flex align-items-center">
                                                    <div class="avatar me-50">
                                                        <img src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="Avatar" width="38" height="38" />
                                                    </div>
                                                    <div class="more-info">
                                                        <h6 class="mb-0">${row.user_name }</h6>
                                                        <p class="mb-0">DAR${row.dar_id }</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                          </c:if>
                                           </c:forEach>
                                   </ul>
                                </div>
                            </div>
                        </div>
                        <!--/ Timeline Card -->
                   <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">ประวัติเอกสาร</h4>
                            </div>
                            <div class="card-body"> <div class="table-responsive">
                   <table class="table" >
                                    <thead>
                                        <tr>
                                            <th>ลำดับ</th>
                                                <th>หมายเลขใบคำร้อง</th>
                                            <th>วันที่ร้องขอ</th>
                                            <th>ผู้ร้องขอ</th>
                                            <th>เรื่องที่ร้องขอ</th>
                                             <th>หมายเหตุ</th>
                                        
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${dar }" var="row" varStatus="count">
                                         
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
                                        
                                            <td>${count.count }</td>
                                            <td>${row.dar_id }</td>
                                            <td>${row.dar_date_add }</td>
                                            <td>${row.user_name }</td>
                                             <td>${request }</td>
                                             <td>${row.dar_note1 }</td>
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table>
                                
                                </div>
                   </div>   </div>
                    </div>
                    
                   
               <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Log</h4>
                            </div>
                            <div class="card-body"> <div class="table-responsive">
                   <table class="table" >
                                    <thead>
                                        <tr>
                                            <th><spring:message code="tables.No" text="ลำดับ" /></th>
                                                <th><spring:message code="label.date" text="วันที่" /></th>
                                            <th><spring:message code="label.name" text="ชื่อผู้ใช้" /></th>
                                            <th><spring:message code="tables.action" text="ดำเนินการ"/></th>
                                            <th><spring:message code="tables.storage" text="แหล่งที่เก็บ"/></th>
                                             <th><spring:message code="tables.note" text="หมายเหตุ"/></th>
                                        <th>Read Time(PDF only)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${log }" var="row" varStatus="count">
                                         
                     
                                        <tr>
                                        
                                            <td>${count.count }</td>
                                            <td>${row.log_date } ${row.log_time }</td>
                                            <td>${row.user_name }</td>
                                            <td>${row.log_step }</td>
                                             <td>${row.log_folder }</td>
                                             <td>${row.log_note }</td>
                                                <td>${row.log_readtime }</td>
                                        </tr>
                                          </c:forEach>
                                    </tbody>
             </table>
               </div>   </div>
                    </div>
                      
            
             
                     	  	<c:if test="${file.file_no  ne NULL}"> 
                              <div class="col-12">
                   
                   
                   
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Distribution of documents in the organization</h4>
                            </div>
                            <div class="card-body"> <div class="table-responsive">
                   <table class="table" >
                                    <thead>
                                     <tr>
                                            <th><spring:message code="label.no" text="ลำดับ" /></th>
                                           <th>Location</th>
            
                                        
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${shortcut }" var="row" varStatus="count">
     
                                        <tr>
                                        
                                            <td>${count.count }</td>
                                            <td>${row.file_path_full }</td>
  
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table></div>
                   </div>   </div>
                    </div>
                    
                   </c:if>     
                    
                    
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
 

    <script>
    function filedownload(){
    	
    	window.open("/filedownload-filemanager?id=${file.file_id}", '_blank').focus();
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

var element = document.getElementById("nav_doc");
element.classList.add("active");  

    </script>
    
    
      <script src="<c:url value="/lib/photoviewer/js/photoviewer.js" />"></script>
     

  <script>
    // initialize manually with a list of links
    $('[data-gallery=photoviewer]').click(function (e) {

      e.preventDefault();

      var items = [],
        options = {
          index: $(this).index(),
        };

      $('[data-gallery=photoviewer]').each(function () {
        items.push({
          src: $(this).attr('href'),
          title: $(this).attr('data-title')
        });
      });

      new PhotoViewer(items, options);

    });
    
    let allImages = document.querySelectorAll("img");
    allImages.forEach((value)=>{
        value.oncontextmenu = (e)=>{
            e.preventDefault();
        }
    })
  </script>
    
    
</body>


