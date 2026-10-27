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

<style>
        /* ตกแต่งเล็กน้อย */
        .copy-container {
            display: flex;
            gap: 10px;
            max-width: 500px;
            font-family: sans-serif;
        }

        input[type="text"] {
            flex-grow: 1;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
        }

        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        button:active {
            background-color: #004085;
        }
    </style>
 <style>
 
 
    .photoviewer-modal {
      background-color: transparent;
      border: none;
      border-radius: 0;
      box-shadow: 0 0 6px 2px rgba(0, 0, 0, .3);

    pointer-events: none;

    }

    .photoviewer-header .photoviewer-toolbar {
      background-color: rgba(0, 0, 0, .5);
    }

    .photoviewer-stage {
      top: 0;
      right: 0;
      bottom: 0;
      left: 0;
      background-color: rgba(0, 0, 0, .85);
      border: none;
    }

    .photoviewer-footer .photoviewer-toolbar {
      background-color: rgba(0, 0, 0, .5);
      border-top-left-radius: 5px;
      border-top-right-radius: 5px;
    }

    .photoviewer-header,
    .photoviewer-footer {
      border-radius: 0;
      pointer-events: none;
    }

    .photoviewer-title {
      color: #ccc;
    }

    .photoviewer-button {
      color: #ccc;
      pointer-events: auto;
    }

    .photoviewer-header .photoviewer-button:hover,
    .photoviewer-footer .photoviewer-button:hover {
      color: white;
    }
    
    .col-form{
     font-weight: bold;
    
    }
    
    
  </style>

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
                                    <h4 class="card-title"><spring:message code="file.information" text="ข้อมูลเอกสาร"/></h4>
                                </div>
                                <div class="card-body">
                                     
                               
                              
                    
                                               
                                   		  	   <c:if test="${file.file_no != null   }"> 	
                                          <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form">
                                                <spring:message code="file.no" text="รหัสเอกสาร" /> : </label>
                                                <div class="col-sm-9">
                                                     ${file.file_no }
                                                </div>
                                            </div>  
                                                <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form">
                                               <spring:message code="dar.category" text="ประเภท" /> : </label>
                                                <div class="col-sm-9">
                                                     ${file.file_code }
                                                </div>
                                            </div> 
                                            
                                            
                                                    <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form">
                                                <spring:message code="file.rev" text="Rev" /> :</label>
                                                <div class="col-sm-9">
                                                    ${file.file_rev }
                                                </div>
                                            </div>   
                                            
                                            </c:if>
                                            
                                            
                                         
                                                
                                <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form">
                                                <spring:message code="file.name" text="ชื่อไฟล์" /> :</label>
                                                <div class="col-sm-9">
                                                    ${file.file_name_original }
                                                </div>
                                            </div>
                            
                                         
                                              
                                               <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form">
                                                <spring:message code="file.type" text="ชนิด" /> :</label>
                                                <div class="col-sm-9">
                                                    ${file.file_type }
                                                </div>
                                            </div>
                                                <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form">
                                                <spring:message code="file.size" text="ขนาด" /> :</label>
                                                <div class="col-sm-9">
                                                    ${file.file_size2 }
                                                </div>
                                            </div>
                            
                            
                              <c:if test="${file.file_no != null  }"> 
                                                  <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form">
                                               <spring:message code="dar.jd" text="ผู้รับผิดชอบ" />: </label>
                                                <div class="col-sm-9" id="jd">
                                        ${file.file_by }
                                                </div>
                                                  </div>
                            </c:if>                
                                             
                                     <div class="mb-1 row">     
                                                       <label for="colFormLabelLg" class="col-sm-3 col-form">
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
								   
								   
				  	     
		
			  	   <c:if test="${file.file_type.equals('application/pdf')  }"> 	
			  		   
			  		   
			  
		               
         								<a href="/showpdf-filemanager/${Drives }/${path}/?id=${file.file_id }" target="blank" class="mb-50">
                                                            <img src="/resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18">
                                                            <small class="text-muted fw-bolder">${file.file_name_original}</small>
                                                        </a>
                                   
    				</c:if> 	
			  		 
		              
		              
		              
		              
		       <c:if test="${file.file_type.equals('image/png') or file.file_type.equals('image/webp') or file.file_type.equals('image/webp') 
		       or file.file_type.equals('image/jpg')  or file.file_type.equals('image/jpeg') }"> 	        
		              
		                     <div class="image-set">                                              
      <a data-gallery="photoviewer" data-title="Show by IDOC" data-group="a"
         href="/document-filemanager/${Drives }/${path}/${file.file_name}">
        <img src="/document-filemanager/${Drives }/${path}/${file.file_name}" alt="" style="width:150px">
      </a>              
	    </div>	
		  </c:if> 
		  
		  
		  					
		 
									             </div>
									             </div>
									          
                      
                       <sec:authorize access="hasAnyAuthority('DOWNLOAD-${Folders }','ROLE_SUPPEROWNER','ROLE_SUPPEROWNER','READ-RST')"> 
                      
                           <div class="mb-1 row">     
                                                       <label for="colFormLabelLg" class="col-sm-3 col-form">
                                              <spring:message code="file.load" text="ไฟล์สำหรับดาวน์โหลด" />: </label>
                                              
                                <c:set var="path" value="${file.file_path}" />
                      <c:if test="${file.file_shotcut ne null}">   
                       <c:set var="path" value="${file.file_shotcut}" />
                      </c:if>                  
                                              
                                                <div class="col-sm-9">   
                                                    <c:if test="${file.file_id > 0}"> 
									             <a href="/document-filemanager/${Drives }/${path}/${file.file_name}" onclick="filedownload()"  download class="mb-50">
                                                            <img src="/resources/app-assets/images/icons/doc.png" class="me-25" alt="png" height="18">
                                                            <small class="text-muted fw-bolder">${file.file_name}</small>
                                                        </a>
                                                        
     							    
									         </c:if> 	
									         
                                                         </div>
									             </div> 
									             
									             
									                  <div class="mb-1 row">     
                                                       <label for="colFormLabelLg" class="col-sm-3 col-form">
                                              <spring:message code="file.loadlink" text="ลิงค์ไฟล์" />: </label>
                                              
                                <c:set var="path" value="${file.file_path}" />
                      <c:if test="${file.file_shotcut ne null}">   
                       <c:set var="path" value="${file.file_shotcut}" />
                      </c:if>                  
                                              
                                                <div class="col-sm-9">  
                                                <div class="copy-container">
        <input type="text" value="Idoc:/${Drives }/${path}/${file.file_name}" id="myInput">
        
        <button onclick="copyText()">Copy!</button>
    </div> 
                                                
                                                         </div>
									             </div> 
                                                </sec:authorize>             
                                            
   <br> <br>

                                                   	<c:if test="${row.file_no  ne null   }">   
                                                                 <sec:authorize access="hasAnyAuthority('APP-DAR')">    
                                               					    <a href="/shotcut-filemanager?id=${row.file_id }&&Folders=${row.file_path}"  
                                               					     class="btn btn-light waves-effect waves-float waves-light" >
							          								Shotcut</a>  
							          
						                                         </sec:authorize> 
						                            </c:if>     
						                            
						                                  
                                </div>
                            </div>
                        </div>
                   
                         <div class="col-12">
                   
                   <div class="card">
 
                            <div class="card-body"> 
                            
                            
                            
                            
                                  <div class="card-body">
                                    <ul class="nav nav-tabs" role="tablist">
                                    
                                       <li class="nav-item">
                                            <a class="nav-link active" id="about-tab" data-bs-toggle="tab" href="#about" aria-controls="about" role="tab" aria-selected="false">
                                           
                                       Log</a>
                                        </li>  
                             
                              	<c:if test="${file.file_no  ne NULL  or  file.file_shotcut  != null }">          
                                        <li class="nav-item">
                                            <a class="nav-link " id="home-tab" data-bs-toggle="tab" href="#home" aria-controls="home" role="tab" aria-selected="true"> 
                                      
                               Revision     
                                 
                                              </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" id="profile-tab" data-bs-toggle="tab" href="#profile" aria-controls="profile" role="tab" aria-selected="false">
                                     
                                DAR History
                                            </a>
                                        </li>
        
                                   
                                                <li class="nav-item">
                                            <a class="nav-link" id="about-tab2" data-bs-toggle="tab" href="#about2" aria-controls="about" role="tab" aria-selected="false">
                                         Distribution
                                            </a>
                                        </li>
                                      </c:if>  
                                        
                                        
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane " id="home" aria-labelledby="home-tab" role="tabpanel">
                                         
                                            <table class="table">
                                    <thead>
                                        <tr>
                                            <th>NO</th>
                                                <th><spring:message code="file.no" text="รหัสเอกสาร" /></th>
                                            <th><spring:message code="dar.name" text="ชื่อเอกสาร" /></th>
                                            <th><spring:message code="file.rev" text="การแก้ไข" /></th>
                                            <th><spring:message code="file.size" text="ขนาด" /></th>
                                             <th><spring:message code="dar.category" text="ประเภท" /> </th>
                                       
                                         <th><spring:message code="file.date" text="วันที่บังคับใช้" /></th>
                                          <th><spring:message code="dar.dateend" text="วันที่หมดอายุ" /></th>
                                           <th><spring:message code="dar.Status" text="สถานะเอกสาร" /> </th>
                                              <th><spring:message code="action.action" text="ดำเนินการ" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${fileold }" var="row" varStatus="count">
                                         
                     
                                        <tr>
                                        
                                            <td>${count.count }</td>
                                            <td>${row.file_no }</td>
                                            <td>${row.file_name_original }</td>
                                            <td>${row.file_rev }</td>
                                             <td>${row.file_size2 }</td>
                                             <td>${row.file_code }</td>
                                      
                                           
                                                 <td>${row.file_dateadd }</td>
                                                  <td>${row.file_dateend }</td>
                                                   <td>
                   
                                                               <c:if test="${row.file_type2  == -1}">    
                              <spring:message code="file.filecanceled" text="ถูกยกเลิก"  var="status" />
                                  <c:set var="type1" value="${type1+1 }" />
                         
                               </c:if> 
                             <c:if test="${row.file_type2  == -2}">    
                                <spring:message code="file.fileexpired" text="หมดอายุ"  var="status" />
                                    <c:set var="type2" value="${type2+1 }" />
                              
                               </c:if>     
                               
                                <c:if test="${row.file_type2  == -3}">    
                                <spring:message code="file.fileexpired2" text="เวอร์ชั่นเก่า"  var="status" />
                                    <c:set var="type3" value="${type3+1 }" />
                              
                               </c:if>   
                                                   ${status }
                                                   </td>
                                                   <td>
                                               
                                               
                                                   	<c:if test="${file.file_no  ne NULL   }"> 
                                                                 <sec:authorize access="hasAnyAuthority('APP-DAR')">    
                                               					    <a href="/mainfileinfo-filemanager?id=${row.file_id }&&Folders=${row.file_path}" class="btn btn-light waves-effect waves-float waves-light">
							          								View</a>  
							          
                                         </sec:authorize> 
                                     </c:if>
							          
							          </td>
                                                   
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table>
                                
                                         
                                        
                                        </div>
                                        <div class="tab-pane " id="profile" aria-labelledby="profile-tab" role="tabpanel">
                                             
                                              <table class="table" >
                                    <thead>
                                     <tr>
                                            <th><spring:message code="label.no" text="ลำดับ" /></th>
                                           <th><spring:message code="dar.darid" text="หมายเลขใบคำร้อง" /></th>
                                            <th><spring:message code="dar.requesteddate" text="วันที่ร้องขอ" /></th>
                                            <th><spring:message code="dar.requester" text="ผู้ร้องขอ" /></th>
                                            <th><spring:message code="dar.Request" text="เรื่องที่ร้องขอ" /></th>
                                             <th><spring:message code="dar.remark" text="หมายเหตุ" /></th>
                                        
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
                                             <td>  ${fn:replace(row.dar_doc_remark, newLineChar, "<br />")}</td>
                                        </tr>
                                        
                                          <c:set var="jdname" value="${row.dar_jd }" />
                                          </c:forEach>
                                     <script>     
                                     if("${jdname}" != ''){
                                    	 $("#jd").html("${jdname}");    
                                     }
                                     
                                     </script>    
                                    </tbody>
                                </table>
                                             </div>
                  
                                      
                                          <div class="tab-pane active" id="about" aria-labelledby="about-tab" role="tabpanel">
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
                                   
                                
                                </div>
                  
                               
                               
                                    
                                        
                                        
                                        <div class="tab-pane" id="about2" aria-labelledby="about-tab" role="tabpanel">
                                           
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
                                </table>
                    
                                        </div>
                                    </div>
                                </div>
                    
              
                            
                            
                            
                            
                            
                       
                   </div>   </div>
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
    
    document.addEventListener("contextmenu", function(e) {
        e.preventDefault(); // ห้ามเปิด context menu
    });
    
    
    function filedownload(){
    	

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
    
    <script>
        function copyText() {
            // 1. เลือก Element input
            const copyText = document.getElementById("myInput");

            // 2. ใช้ Clipboard API สั่ง Copy
            navigator.clipboard.writeText(copyText.value).then(() => {
                // 3. แจ้งเตือน User (Optional)
                alert("คัดลอกข้อความแล้ว: " + copyText.value);
            }).catch(err => {
                console.error('ไม่สามารถคัดลอกได้', err);
            });
        }
    </script>
</body>


