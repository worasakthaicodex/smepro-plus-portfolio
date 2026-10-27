<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="common/mainhead.jspf" %>    
<c:set var="list" value="${permission }" />
<title>index</title>
 <c:set var="listcheck2" value="READ-RST" />
<style>
.inactiveLink {
   pointer-events: none;
   cursor: default;
   opacity: 0.4;
}
</style>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">

<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  



    <!-- BEGIN: Content-->
    <div class="app-content content file-manager-application">
     
        <div class="content-header row">
                <div class="content-header-left col-md-9 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-12">
                            
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                   <li class="breadcrumb-item"><a href="/filemanager">Document Access</a>
                                    </li>
                                    
                                    
                             
                                   <c:if test="${!Drivesname.equals('') and Drivesname != null}">    
                                    <li class="breadcrumb-item "><a href="/filemanager/${Drives }?id=${Drivesid }">${Drivesname }</a>
                                    </li>
                                    </c:if>
                                
                                 
                                     <c:if test="${Foldername != null and  Subfoldername != null}">    
                                    <li class="breadcrumb-item"><a href="/filemanager/${Drives }/${Folders }?id=${Foldersid }">${Foldername }</a>
                                    </li>
                                    </c:if>
                                
                                      <c:if test="${Foldername != null and  Subfoldername == null}">    
                                    <li class="breadcrumb-item"><a href="#">${Foldername }</a>
                                    </li>
                                    </c:if>
                                       <c:if test="${Subfoldername != null}">     
                                    <li class="breadcrumb-item "><a href="#">${Subfoldername }</a>
                                    </li>
                                    </c:if>
                                  
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="content-header-right text-md-end col-md-3 col-12 d-md-block d-none">
                    <div class="mb-1 breadcrumb-right">
                  
                    </div>
                </div>
            </div>
        <div class="content-area-wrapper container-xxl p-0">
        
        
            <div class="sidebar-left">
                <div class="sidebar">
                    <div class="sidebar-file-manager">
                        <div class="sidebar-inner">
                            <!-- sidebar menu links starts -->
                            <!-- add file button -->
                            <div class="dropdown dropdown-actions">
                                <button class="btn btn-primary add-file-btn text-center w-100" type="button" id="addNewFile" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="align-middle"><spring:message code="btn.add" /></span>
                                </button>
                                <div class="dropdown-menu" aria-labelledby="addNewFile">
                                 <c:set var="listcheck" value="ADD-DRIVE" />
                                    <c:if test='${fn:contains(list,listcheck)  ||  fn:contains(list, listcheck2)}'>
                                <c:if test="${show.equals('drives')}">    
                                   <a href="/adddrives-filemanager"> <div class="dropdown-item" >
                                        <div class="mb-0">
                                            <i data-feather="hard-drive" class="me-25"></i>
                                            <span class="align-middle"><spring:message code="Drives.ADD" text="เพิ่มไดร์" /></span>
                                        </div>
                                       
                                    </div>
                                     </a>
                                </c:if> 
                                 </c:if>
                                 
                                       
                                        <c:set var="listcheck" value="ADD-${Drives }" />
                              
                                    <sec:authorize access="hasAnyAuthority('ADD-${Drives }','ROLE_SUPPEROWNER','READ-RST')"> 
                                 <c:if test="${show.equals('folders')}">    
                                        <a href="/addfolder-filemanager?id=${Drivesid}"> <div class="dropdown-item" >
                                        <div class="mb-0">
                                            <i data-feather="folder" class="me-25"></i>
                                            <span class="align-middle"><spring:message code="Folder.ADD" text="เพิ่มโฟลเดอร์" /></span>
                                        </div>
                                       
                                    </div>
                                     </a> 
                                 </c:if> 
                                  </sec:authorize>  
                                 
                                            <c:set var="listcheck" value="ADD-${Folders }" />
                                    
                                    
                                     <sec:authorize access="hasAnyAuthority('UPLOAD-${Folderstype }','ROLE_SUPPEROWNER','READ-RST')"> 
                                       <c:if test="${show.equals('subfolders') || show.equals('subfolderss')}">    
                                       
                                       <c:if test="${shows != 'subfolderss'}"> 
		                                        <a href="/addsubfolder-filemanager?id=${Drivesid}&&id2=${MFoldersid }"> <div class="dropdown-item" >
				                                       			 <div class="mb-0">
				                                            <i data-feather="folder" class="me-25"></i>
				                                            <span class="align-middle"><spring:message code="Folder.ADD" text="เพิ่มโฟลเดอร์" /></span>
				                                        </div>
				                                       
				                                   			 </div>
		                                     </a> 
                                      </c:if>  
                                      
                                      <c:if test="${ myroot.folder_type2 == 0}">    
                                            <sec:authorize access="hasAnyAuthority('UPLOAD-${Folderstype }','ROLE_SUPPEROWNER','READ-RST')"> 
				                                           <a href="/addfile-filemanager?id=${uploadpnid }&&level=${level}"> <div class="dropdown-item" >
				                                        <div class="mb-0">
				                                            <i data-feather="file" class="me-25"></i>
				                                            <span class="align-middle"><spring:message code="file.ADD" text="เพิ่มไฟล์" /></span>
				                                        </div>
				                                       
				                                    </div>
				                                     </a> 
				                                     
                                      </sec:authorize> 
                                          </c:if> 
                                      
                                      
                                 </c:if>     
                                   </sec:authorize> 
                                 
                                 
                                      <c:if test="${show != 'drives' and show != 'subfolders'  and show != 'folders' and show != 'subfolderss'}">  
                                         
                                            <sec:authorize access="hasAnyAuthority('UPLOAD-${Folderstype }','ROLE_SUPPEROWNER','READ-RST')"> 
			                                          <a href="/addfile-filemanager"> <div class="dropdown-item" >
			                                        <div class="mb-0">
			                                            <i data-feather="file" class="me-25"></i>
			                                            <span class="align-middle"><spring:message code="file.ADD" text="เพิ่มไฟล์" /></span>
			                                        </div>
			                                       
			                                    </div>
			                                     </a>
                                      </sec:authorize> 
                                     
                                      </c:if>  
                                    
                                </div>
                            </div>
                            <!-- add file button ends -->

                            <!-- sidebar list items starts  -->
                            <div class="sidebar-list">
                                <!-- links for file manager sidebar //active -->
                                <div class="list-group">
                      <a href="#"  class="list-group-item list-group-item-action"  onclick="openCenteredWindow('/root-filemanager', 'File Window', 900, 600); changeRowColor(this); return false;" target="_blank" >
                                    
                                        <i data-feather="star" class="me-50 font-medium-3"></i>
                                        <span class="align-middle">
                                      System structure
                                        </span>
                                    </a>
									
						    <c:if test="${show.equals('folder') || show.equals('subfolders') }">    			
                        <sec:authorize access="hasAnyAuthority('SET-${myroot.folder_type }','ROLE_SUPPEROWNER','READ-RST')">    
                            <a class="list-group-item list-group-item-action" href="/setinfo/${myroot.folder_type }?id=${myroot.folder_id }" >
                                <i data-feather="info" class="align-middle me-50"></i>
                                <span class="align-middle"><spring:message code="btn.property" /></span>
                            </a>
                            </sec:authorize> 
                                  </c:if>
                                </div>
                          
                                <!-- storage status of file manager starts-->
                                <div class="storage-status mb-1 px-2" style="display:none">
                                    <h6 class="section-label mb-1">Storage Status</h6>
                                    <div class="d-flex align-items-center cursor-pointer">
                                        <i data-feather="server" class="font-large-1"></i>
                                        <div class="file-manager-progress ms-1">
                                            <span>68GB used of 100GB</span>
                                            <div class="progress progress-bar-primary my-50" style="height: 6px">
                                                <div class="progress-bar" role="progressbar" aria-valuenow="80" aria-valuemin="80" aria-valuemax="100" style="width: 80%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- storage status of file manager ends-->
                            </div>
                            <!-- side bar list items ends  -->
                            <!-- sidebar menu links ends -->
                        </div>
                    </div>

                </div>
            </div>
            <div class="content-right">
                <div class="content-wrapper container-xxl p-0">
                    <div class="content-header row">
                    </div>
                    <div class="content-body">
                        <!-- overlay container -->
                        <div class="body-content-overlay"></div>

                        <!-- file manager app content starts -->
                        <div class="file-manager-main-content">
                            <!-- search area start -->
                            <div class="file-manager-content-header d-flex justify-content-between align-items-center">
                                <div class="d-flex align-items-center">
                                    <div class="sidebar-toggle d-block d-xl-none float-start align-middle ms-1">
                                        <i data-feather="menu" class="font-medium-5"></i>
                                    </div>
                                    <div class="input-group input-group-merge shadow-none m-0 flex-grow-1">
                                        <span class="input-group-text border-0">
                                            <i data-feather="search"></i>
                                        </span>
                                        <input type="text" class="form-control files-filter2 border-0 bg-transparent" placeholder="Search" />
                                    </div>
                                </div>
                                <div class="d-flex align-items-center" >
                                    <div class="file-actions" style="display:block">
                                      
                                      
                                      
                                      
                                       
                                        <div class="dropdown d-inline-block" >
                                            <i class="font-medium-2 cursor-pointer" data-feather="more-vertical" role="button" id="fileActions" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            </i>
                                            
                                            
                                            
                                            
                                            <div class="dropdown-menu d-inline-block" aria-labelledby="fileActions">
                                        
                   
                                                <a class="dropdown-item" href="#" onclick="Sort(1)">
                                                <i data-feather='arrow-up' class="cursor-pointer me-50"></i>
                                          
                                                    <span class="align-middle">Sort up  by name</span>
                                                </a>
                                  
                                    
                                                <a class="dropdown-item" href="#" onclick="Sort(2)">
                                               <i data-feather='arrow-down' class="cursor-pointer me-50"></i>
                                                    <span class="align-middle">Sort down by name</span>
                                                </a>
                                               
                                                <a class="dropdown-item" href="#" onclick="Sort(3)">
                                                <i data-feather='arrow-up' class="cursor-pointer me-50"></i>
                                          
                                                    <span class="align-middle">Sort up  by date</span>
                                                </a>
                                          <a class="dropdown-item" href="#" onclick="Sort(4)">
                                               <i data-feather='arrow-down' class="cursor-pointer me-50"></i>
                                                    <span class="align-middle">Sort down by date</span>
                                                </a>
                                               
                                               
                                               
                                            </div>
                                        </div>
                                    </div>
                                    <div class="btn-group view-toggle ms-50" role="group">
                                        <input type="radio" class="btn-check" name="view-btn-radio" data-view="grid" id="gridView" checked autocomplete="off" onchange="changeview(1)"/>
                                        <label class="btn btn-outline-primary p-50 btn-sm" for="gridView" id="gridViews">
                                            <i data-feather="grid"></i>
                                        </label>
                                        <input type="radio" class="btn-check" name="view-btn-radio" data-view="list" id="listView" autocomplete="off" onchange="changeview(2)"/>
                                        <label class="btn btn-outline-primary p-50 btn-sm" for="listView" id="listViews">
                                            <i data-feather="list"></i>
                                        </label>
                                    </div>
									
								
                                </div>
                            </div>
                            <!-- search area ends here -->

                            <div class="file-manager-content-body" >
                               <div style="display:none" id="Viewstype">
                                   <c:if test="${show.equals('drives')}">    
                                <!-- drives area starts-->
                                <div class="drives">
                                    <div class="row">
                                        <div class="col-12">
                                            <h6 class="files-section-title mb-75">Drives</h6>
                                        </div>
                                   
                                   
                                   <c:forEach items="${drives }" var="row" >
                                    <c:set var="pclass" value="inactiveLink" />
                                    <c:set var="listcheck" value="READ-${row.drives_drive }" />
                                   <c:if test='${fn:contains(list,listcheck)  ||  fn:contains(list, listcheck2)}'>
                                  <c:set var="pclass" value="" />
                                     </c:if>
                                       <div class="col-lg-3 col-md-6 col-12 file-managers" data-date="${row.drives_id }">
                                          
                                            <div class="card shadow-none border cursor-pointer">
                                                <div class="card-body">
                                                    <div class="d-flex justify-content-between">
                                                         <a href="/filemanager/${row.drives_drive }?id=${row.drives_id }" class="${pclass }"><img src="/resources/app-assets/images/icons/drive.png" alt="google drive" height="38" /></a>
                                                        <div class="dropdown-items-wrapper">
                                                            <i data-feather="more-vertical" class="${pclass }" id="dropdownMenuLink1" role="button" data-bs-toggle="dropdown" aria-expanded="false"></i>
                                                            <div class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuLink1">
                                                             
                                                                
                                                  
                                                        <sec:authorize access="hasAnyAuthority('SET-EDIT-DRIVE','ROLE_SUPPEROWNER','READ-RST')">    
                                                                <a class="dropdown-item" href="/editdrives-filemanager/Drives?id=${row.drives_id }">
                                                                    <i data-feather="settings" class="me-25"></i>
                                                                    <span class="align-middle"><spring:message code="btn.edit" /></span>
                                                                </a>
                                                         </sec:authorize> 
                                                           
                                                              <sec:authorize access="hasAnyAuthority('DELETE-DRIVE','ROLE_SUPPEROWNER','READ-RST')">    
									                                    <a class="dropdown-item " href="/deletedrives-filemanager/${row.drives_id }"
									                                          onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.drives_name}');">
									                                                                    <i data-feather="trash" class="me-25"></i>
									                                                                    <span class="align-middle"><spring:message code="btn.delete" /></span>
                                                                </a>
                                                                
                                                                  </sec:authorize> 
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="my-1">
                                                        <h5> <a href="/filemanager/${row.drives_drive }?id=${row.drives_id }" class="${pclass }">${row.drives_name }</a></h5>
                                                    </div>
                                                    <div class="d-flex justify-content-between mb-50">
                                                        <span class="text-truncate">${row.drives_remain2 } </span>
                                                        <small class="text-muted">${row.drives_size2 }</small>
                                                    </div>
                                                    <div class="progress progress-bar-warning progress-md mb-0" style="height: 10px">
                                                        <div class="progress-bar" role="progressbar" aria-valuenow="${row.drives_usedisplay}" aria-valuemin="0" aria-valuemax="100" style="width: ${row.drives_usedisplay}%"></div>
                                                    </div>
                                                </div>
                                            </div>   
                                        
                                        </div>
                               
                                  </c:forEach>
                                  
                                    </div>
                                </div>
                                <!-- drives area ends-->
                                  </c:if>
                               
                                <c:if test="${show.equals('folders') or show.equals('subfolders')}">    
                                <!-- Folders Container Starts -->
                                <div class="view-container">
                                    <h6 class="files-section-title mt-25 mb-75">Folders </h6>
                                    <div class="files-header">
                                        <h6 class="fw-bold mb-0">Filename</h6>
                                        <div>
                                            <h6 class="fw-bold file-item-size d-inline-block mb-0">Size</h6>
                                            <h6 class="fw-bold file-last-modified d-inline-block mb-0">Last modified</h6>
                                            <h6 class="fw-bold d-inline-block me-1 mb-0">Actions</h6>
                                        </div>
                                    </div>
                                
                                 <c:forEach items="${folders }" var="row" >  
                            
                                   <c:set var="pclass" value="inactiveLink" />
                                   <c:set var="listcheck" value="READ-${row.folder_type }" />
                               
                               
                               
                                   <c:if test='${fn:contains(list,listcheck)  ||  fn:contains(list, listcheck2)}'>
                                  <c:set var="pclass" value="" />
                                     </c:if> 
                                      <sec:authorize access="hasAnyAuthority('ROLE_SUPPEROWNER')">    
                                         <c:set var="pclass" value="" />
                                     </sec:authorize>
                                     
                                    <div class="card file-manager-item folder file-managers" data-date="${row.folder_id }">
                                        <div class="form-check">
                                        
                                            <label class="form-check-label" for="customCheck1"></label>
                                        </div>
                                        <div class="card-img-top file-logo-wrapper">
                                            <div class="dropdown float-end">
                                                <i data-feather="more-vertical" class="${pclass }"  id="dropdownMenuLink${row.folder_id }" role="button" data-bs-toggle="dropdown" aria-expanded="false" ></i>
                                                                      <!-- File Dropdown Starts-->
                        <div class="dropdown-menu dropdown-menu-end file-dropdown"  aria-labelledby="dropdownMenuLink${row.folder_id }">
                   
                            
                                            <c:if test="${show.equals('folders')}">  
                                              <c:set var="link" value="/editfolder-filemanager/${row.folder_type }?id=${row.folder_id }" />
                                              </c:if>
                                                 <c:if test="${show.equals('subfolders')}">  
                                              <c:set var="link" value="/editsubfolder-filemanager/${row.folder_type }?id=${row.folder_id }" />
                                              </c:if>
                            
                               <c:set var="listcheck" value="EDIT-${row.folder_type }" />
                              <c:if test='${fn:contains(list,listcheck)  ||  fn:contains(list, listcheck2)}'>
                            <a class="dropdown-item" href="${link }" >
                                <i data-feather="edit" class="align-middle me-50"></i>
                                <span class="align-middle"><spring:message code="btn.edit" /></span>
                            </a>
                            </c:if>
                          
                          
                          
                            <sec:authorize access="hasAnyAuthority('SET-${row.folder_type }','ROLE_SUPPEROWNER','READ-RST')">    
                            <a class="dropdown-item" target="_blank" href="/setinfo/${row.folder_type }?id=${row.folder_id }" >
                                <i data-feather="info" class="align-middle me-50"></i>
                                <span class="align-middle"><spring:message code="btn.property" /></span>
                            </a>
                            </sec:authorize>  
                            
                                              <c:if test="${show.equals('folders')}">  
                                              <c:set var="link" value="/deletefolder-filemanager/${row.folder_type }/${row.folder_id }" />
                                              </c:if>
                                                 <c:if test="${show.equals('subfolders')}">  
                                              <c:set var="link" value="/deletesubfolder-filemanager/${row.folder_type }/${row.folder_id }" />
                                              </c:if>
                                              
                            <c:if test="${show.equals('folders')}">  
                             
                            <sec:authorize access="hasAnyAuthority('DELETE-${row.folder_type }','DELETEIETM-${Drives }','ROLE_SUPPEROWNER','READ-RST')">    
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${link }" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.folder_name}');">
                                <i data-feather="trash" class="align-middle me-50"></i>
                                <span class="align-middle"><spring:message code="btn.delete" /></span>
                            </a>
                            
                       	  </sec:authorize> 
                            
                                   </c:if>
                            
     							   <c:if test="${show.equals('subfolders')}">  
    
    				  <sec:authorize access="hasAnyAuthority('DELETE-${row.folder_type }','DELETEITEM-${Folders }','ROLE_SUPPEROWNER','READ-RST')">    
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${link }" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.folder_name}');">
                                <i data-feather="trash" class="align-middle me-50"></i>
                                <span class="align-middle"><spring:message code="btn.delete" /></span>
                            </a>
                            
                       	  </sec:authorize> 

                                   </c:if>
                        </div>
                        <!-- /File Dropdown Ends -->
                                    
                                            </div>
                                            <div class="d-flex align-items-center justify-content-center w-100">
                                            
                                              <c:if test="${show.equals('folders')}">  
                                              <c:set var="link" value="/filemanager/${Drives }/${row.folder_type }?id=${row.folder_id }" />
                                              </c:if>
                                                 <c:if test="${show.equals('subfolders')}">  
                                              <c:set var="link" value="/filemanager/${Drives }/${Folders }/${row.folder_type }?id=${row.folder_id }" />
                                              </c:if>
                                              
                                             
                                                <c:if test="${row.folder_type2 == 0 }">
                                                <img src="/resources/app-assets/images/icons/Folder.png" alt="file-icon" height="35">
                                              </c:if>  
                                                 
                                              <c:if test="${row.folder_type2 == 1 }">
                                                <img src="/resources/app-assets/images/icons/Folder2.png" alt="file-icon" height="45">
                                              </c:if>
                                                 <c:if test="${row.folder_type2 == 2 }">
                                                 <img src="/resources/app-assets/images/icons/Folder3.png" alt="file-icon" height="40">
                                              </c:if>
                                                   <c:if test="${row.folder_type2 == 3 }">
                                                <img src="/resources/app-assets/images/icons/Folder4.png" alt="file-icon" height="45">
                                              </c:if>
                                              

                                           
                                                <c:if test="${row.folder_work > 0 }"><span class="badge rounded-pill bg-danger badge-up">${row.folder_work}</span></c:if></a>
                                            
                                            
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            
                                             <div class="content-wrapper">
                                             
                                            
                                                <p class="card-text file-name mb-0">
                                                 <a href="${link }" class="${pclass }">
                                                ${row.folder_name }
                                                 </a>
                                                </p>
                                                <p class="card-text file-size mb-0">${row.folder_use2 }</p>
                                               
                                                <p class="card-text file-date"></p>
                                             
                                            </div>
                                           
                                            <small class="file-accessed text-muted"></small>
                                          
                                        </div>
                                    </div>
                           
                                 </c:forEach>
                                 
                                         <c:forEach items="${file }" var="row" >  
                                  
                             
                                          
                                    <div class="card file-manager-item folder file-managers" data-date="${row.file_dateadd }">
                                        
                                        <div class="card-img-top file-logo-wrapper">
                                            <div class="dropdown float-end">
                                                <i data-feather="more-vertical"  id="dropdownMenuLink${row.file_id }"
												role="button" data-bs-toggle="dropdown" aria-expanded="false" ></i>
                                                                         <!-- File Dropdown Starts-->
                        <div class="dropdown-menu dropdown-menu-end file-dropdown"  aria-labelledby="dropdownMenuLink${row.file_id }">
                      <c:set var="path" value="${row.file_path}" />
                      
                     
                            <a class="dropdown-item" target="_blank" href="/fileinfo-filemanager/${Drives }/${path}/?id=${row.file_id }" class="${pclass }">
                                <i data-feather="info" class="align-middle me-50"></i>
                                <span class="align-middle">Info</span>
                            </a>
                            
                            
                             <sec:authorize access="hasAnyAuthority('EDIT-${Folders }','ROLE_SUPPEROWNER','READ-RST')"> 
		                            <c:if test="${row.file_idpn ne null }" >
		                             <a class="dropdown-item"  href="/filemove-filemanager?id=${row.file_id }" class="${pclass }">
		                                <i data-feather="move" class="align-middle me-50"></i>
		                                <span class="align-middle">Move</span>
		                            </a>
		                              </c:if>
                                   </sec:authorize>   
                                         
                              
                              
                       
                            <div class="dropdown-divider"></div>
                            
                            <c:set var="delete" value="false"/> 
                              <c:set var="names" value="${name }"/> 
            
                                      <sec:authorize access="hasAnyAuthority('DELETEITEM-${Folders }','ROLE_SUPPEROWNER','READ-RST')">    
                                         <c:if test="${row.file_shotcut ne null}">
                                           <c:set var="delete" value="true"/> 
                                            </c:if>
                                         </sec:authorize> 
                                    
                                    <c:if test='${row.file_idpn ne null && row.file_by.equals(names)}'>
                                     <c:set var="delete" value="true"/> 
                                         </c:if>     
                                         
                                         
                                 <c:if test='${delete.equals("true")}'>               
                            <a class="dropdown-item"  href="/deleteinfo-filemanager/${Drives }/${row.file_path}/${row.file_id }?level=${level}"  onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.file_name_original}');">
                                <i data-feather="trash" class="align-middle me-50"></i>
                                <span class="align-middle">Delete</span>
                            </a>
                                
                       </c:if> 
                                   
                        
                            
                        </div>
                        <!-- /File Dropdown Ends -->
                                    
                                            </div>
                                            <div class="d-flex align-items-center justify-content-center w-100">
                                            
                                           <c:if test="${row.file_shotcut ne null}">
                                           <img src="/resources/app-assets/images/icons/Shortcut.png" alt="file-icon" height="35" style="position: absolute;margin-right:10px">
                                              </c:if>           
                                              
                                              
                                                <a href="/fileinfo-filemanager/${Drives }/${path}/?id=${row.file_id }" target="_blank" >
                                                
                                           
                                                 <c:choose>
                                <c:when test="${row.file_type.equals('application/vnd.openxmlformats-officedocument.wordprocessingml.document') 
                                or row.file_type.equals('application/msword') 
                                }">
                                     <img src="/resources/app-assets/images/icons/doc.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                    <c:when test="${row.file_type.equals('application/pdf')  }">
                                     <img src="/resources/app-assets/images/icons/pdf.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                    <c:when test="${row.file_type.equals('image/jpg')  or row.file_type.equals('image/jpeg') }">
                                     <img src="/resources/app-assets/images/icons/jpg.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                      <c:when test="${row.file_type.equals('application/vnd.ms-excel') or  row.file_type.equals('application/vnd.openxmlformats-officedocument.spreadsheetml.sheet')
                                }">
                                     <img src="/resources/app-assets/images/icons/xls.png" alt="file-icon" height="35">
                                      
                                                
                                </c:when>
                                
                                        <c:when test="${row.file_type.equals('application/vnd.ms-powerpoint') or  row.file_type.equals('application/vnd.openxmlformats-officedocument.presentationml.presentation')
                                }">
                                     <img src="/resources/app-assets/images/icons/ppt.png" alt="file-icon" height="35">
                                      
                                                
                                </c:when>
                                
                                
                                          <c:when test="${row.file_type.equals('application/json')  }">
                                     <img src="/resources/app-assets/images/icons/json.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                
                                               <c:when test="${row.file_type.equals('text/plain')  }">
                                     <img src="/resources/app-assets/images/icons/txt.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                          <c:when test="${row.file_type.equals('audio/mpeg')  }">
                                     <img src="/resources/app-assets/images/icons/mp3.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                
                                             <c:when test="${row.file_type.equals('video/mp4')  }">
                                     <img src="/resources/app-assets/images/icons/mp4.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                
                                         <c:when test="${row.file_type.equals('text/csv')  }">
                                     <img src="/resources/app-assets/images/icons/csv.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                
                                       <c:when test="${row.file_type.equals('video/vnd.dlna.mpeg-tts')  }">
                                     <img src="/resources/app-assets/images/icons/ts.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                        <c:when test="${row.file_type.equals('image/png')  }">
                                     <img src="/resources/app-assets/images/icons/png.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                
                                        <c:when test="${row.file_type.equals('image/webp')  }">
                                     <img src="/resources/app-assets/images/icons/webp.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                
                                           <c:when test="${row.file_type.equals('image/gif')  }">
                                     <img src="/resources/app-assets/images/icons/gif.png" alt="file-icon" height="35">
                                                
                                </c:when>
                                <c:otherwise>
                                
                                    <img src="/resources/app-assets/images/icons/unknown.png" alt="file-icon" height="35" style="position: relative;" >
                                         
                               </c:otherwise>
         
                               </c:choose>       
                                                </a>
                                            
                                            
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <a href="/fileinfo-filemanager/${Drives }/${path}/?id=${row.file_id }"  target="_blank" >
                                            
                                            
                                            
                                             <div class="content-wrapper">
                                                <p class="card-text file-name mb-0">
                                                
                                                <c:if test="${row.file_no != null}"> 
                                                
                                                   <c:if test="${row.file_size == 0}"> 
                                             ${row.file_no }     
                                              <c:if test="${row.file_rev > 0}"> 
                                                 rev ${row.file_rev }
                                                   </c:if>   Shortcut
                                                </c:if>
                                                    <c:if test="${row.file_size > 0}"> 
                                                    
                                                    
                                                 ${row.file_no } 
                                                         <c:if test="${row.file_rev > 0}"> 
                                                 rev ${row.file_rev }
                                                   </c:if>
                                                 
                                                 
                                                </c:if>
                                                </c:if>
                                              ${row.file_name_original }
                                                </p>
                                                <p class="card-text file-size mb-0">${row.file_size2 }</p>
                                                <p class="card-text file-date">${row.file_dateadd }</p>
                                            </div>
                                            <small class="file-accessed  text-muted">
                                            ${row.file_dateadd } </small>
                                            </a>
                                        </div>
                                    </div>
                           
                                 </c:forEach>
                                 
                                 
                                 
                                    <div class="d-none flex-grow-1 align-items-center no-result mb-3">
                                        <i data-feather="alert-circle" class="me-50"></i>
                                        No Results
                                    </div>
                                </div>
                                <!-- /Folders Container Ends -->
                                </c:if>


                          </div>
                            </div>
                        </div>
                        <!-- file manager app content ends -->

                        <!-- File Info Sidebar Starts-->
                        <div class="modal modal-slide-in fade show" id="app-file-manager-info-sidebar">
                            <div class="modal-dialog sidebar-lg">
                                <div class="modal-content p-0">
                                    <div class="modal-header d-flex align-items-center justify-content-between mb-1 p-2">
                                        <h5 class="modal-title">menu.js</h5>
                                        <div>
                                            <i data-feather="trash" class="cursor-pointer me-50" data-bs-dismiss="modal"></i>
                                            <i data-feather="x" class="cursor-pointer" data-bs-dismiss="modal"></i>
                                        </div>
                                    </div>
                                    <div class="modal-body flex-grow-1 pb-sm-0 pb-1">
                                        <ul class="nav nav-tabs tabs-line" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active" data-bs-toggle="tab" href="#details-tab" role="tab" aria-controls="details-tab" aria-selected="true">
                                                    <i data-feather="file"></i>
                                                    <span class="align-middle ms-25">Details</span>
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" data-bs-toggle="tab" href="#activity-tab" role="tab" aria-controls="activity-tab" aria-selected="true">
                                                    <i data-feather="activity"></i>
                                                    <span class="align-middle ms-25">Activity</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active" id="details-tab" role="tabpanel" aria-labelledby="details-tab">
                                                <div class="d-flex flex-column justify-content-center align-items-center py-5">
                                                    <img src="/resources/app-assets/images/icons/js.png" alt="file-icon" height="64" />
                                                    <p class="mb-0 mt-1">54kb</p>
                                                </div>
                                                <h6 class="file-manager-title my-2">Settings</h6>
                                                <ul class="list-unstyled">
                                                    <li class="d-flex justify-content-between align-items-center mb-1">
                                                        <span>File Sharing</span>
                                                        <div class="form-check form-switch">
                                                            <input type="checkbox" class="form-check-input" id="sharing" />
                                                            <label class="form-check-label" for="sharing"></label>
                                                        </div>
                                                    </li>
                                                    <li class="d-flex justify-content-between align-items-center mb-1">
                                                        <span>Synchronization</span>
                                                        <div class="form-check form-switch">
                                                            <input type="checkbox" class="form-check-input" checked id="sync" />
                                                            <label class="form-check-label" for="sync"></label>
                                                        </div>
                                                    </li>
                                                    <li class="d-flex justify-content-between align-items-center mb-1">
                                                        <span>Backup</span>
                                                        <div class="form-check form-switch">
                                                            <input type="checkbox" class="form-check-input" id="backup" />
                                                            <label class="form-check-label" for="backup"></label>
                                                        </div>
                                                    </li>
                                                </ul>
                                                <hr class="my-2" />
                                                <h6 class="file-manager-title my-2">Info</h6>
                                                <ul class="list-unstyled">
                                                    <li class="d-flex justify-content-between align-items-center">
                                                        <p>Type</p>
                                                        <p class="fw-bold">JS</p>
                                                    </li>
                                                    <li class="d-flex justify-content-between align-items-center">
                                                        <p>Size</p>
                                                        <p class="fw-bold">54kb</p>
                                                    </li>
                                                    <li class="d-flex justify-content-between align-items-center">
                                                        <p>Location</p>
                                                        <p class="fw-bold">Files > Documents</p>
                                                    </li>
                                                    <li class="d-flex justify-content-between align-items-center">
                                                        <p>Owner</p>
                                                        <p class="fw-bold">Sheldon Cooper</p>
                                                    </li>
                                                    <li class="d-flex justify-content-between align-items-center">
                                                        <p>Modified</p>
                                                        <p class="fw-bold">12th Aug, 2020</p>
                                                    </li>

                                                    <li class="d-flex justify-content-between align-items-center">
                                                        <p>Created</p>
                                                        <p class="fw-bold">01 Oct, 2019</p>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="tab-pane fade" id="activity-tab" role="tabpanel" aria-labelledby="activity-tab">
                                                <h6 class="file-manager-title my-2">Today</h6>
                                                <div class="d-flex align-items-center mb-2">
                                                    <div class="avatar avatar-sm me-50">
                                                        <img src="/resources/app-assets/images/avatars/5-small.png" alt="avatar" width="28" />
                                                    </div>
                                                    <div class="more-info">
                                                        <p class="mb-0">
                                                            <span class="fw-bold">Mae</span>
                                                            shared the file with
                                                            <span class="fw-bold">Howard</span>
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="d-flex align-items-center">
                                                    <div class="avatar avatar-sm bg-light-primary me-50">
                                                        <span class="avatar-content">SC</span>
                                                    </div>
                                                    <div class="more-info">
                                                        <p class="mb-0">
                                                            <span class="fw-bold">Sheldon</span>
                                                            updated the file
                                                        </p>
                                                    </div>
                                                </div>
                                                <h6 class="file-manager-title mt-3 mb-2">Yesterday</h6>
                                                <div class="d-flex align-items-center mb-2">
                                                    <div class="avatar avatar-sm bg-light-success me-50">
                                                        <span class="avatar-content">LH</span>
                                                    </div>
                                                    <div class="more-info">
                                                        <p class="mb-0">
                                                            <span class="fw-bold">Leonard</span>
                                                            renamed this file to
                                                            <span class="fw-bold">menu.js</span>
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="d-flex align-items-center">
                                                    <div class="avatar avatar-sm me-50">
                                                        <img src="/resources/app-assets/images/portrait/small/avatar-s-1.jpg" alt="Avatar" width="28" />
                                                    </div>
                                                    <div class="more-info">
                                                        <p class="mb-0">
                                                            <span class="fw-bold">You</span>
                                                            shared this file with Leonard
                                                        </p>
                                                    </div>
                                                </div>
                                                <h6 class="file-manager-title mt-3 mb-2">3 days ago</h6>
                                                <div class="d-flex align-items-start">
                                                    <div class="avatar avatar-sm me-50">
                                                        <img src="/resources/app-assets/images/portrait/small/avatar-s-1.jpg" alt="Avatar" width="28" />
                                                    </div>
                                                    <div class="more-info">
                                                        <p class="mb-50">
                                                            <span class="fw-bold">You</span>
                                                            uploaded this file
                                                        </p>
                                                        <img src="/resources/app-assets/images/icons/js.png" alt="Avatar" class="me-50" height="24" />
                                                        <span class="fw-bold">app.js</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- File Info Sidebar Ends -->

        
                        <!-- Create New Folder Modal Starts-->
                        <div class="modal fade" id="new-folder-modal">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">New Folder</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <input type="text" class="form-control" value="New folder" placeholder="Untitled folder" />
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary me-1" data-bs-dismiss="modal">Create</button>
                                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Cancel</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /Create New Folder Modal Ends -->

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>




<%@ include file="common/mainfoot.jspf" %>  
 
 
    <script>

    

    
    
    
    
    
    $(window).on('load', function() {
    	  displays();
    	   
        });
    
    
    function changeview(data) {
    	
    	
    	$("#Viewstype").show();
    	var list_or_block =  getCookie("list_or_block");
    	
     	if(data == 1){  
     		
     		if(list_or_block == null){
     	  		newcookielist_or_block(1);
    			}else{
    				eraseCookie("list_or_block");
    				newcookielist_or_block(1);
    			}

 	   
		}
	
     	
    	if(data == 2){  
		    		if(list_or_block == null){
		    			newcookielist_or_block(2);
		    		}else{
						eraseCookie("list_or_block");
						newcookielist_or_block(2);
					}
		    	
     		$(".view-container").addClass("list-view");
     		$("#listView").prop("checked", true);
	}
    	
    
    
}

    

    function displays(){ 
    	
    	
    	
    	   var sort =  getCookie("Sort");
    	   
    	    if( sort != null ){
    	    	Sort(sort);
    	    }
    	    
    	 	var list_or_block =  getCookie("list_or_block");
       	
    	 	
       	    if( list_or_block != null ){
       	    	
       	    	
       	     	if(list_or_block == 1){   
       	     	changeview(1);
       			}
       		
       	     	
       	    	if(list_or_block == 2){    		
       	    
       	     	changeview(2);
       			}

         
       	    }else{
       	    	changeview(2);
       	    }
    	    
       	
    
    }

 
      
    
    function newcookieSort(type) { 
    	  var now = new Date();
    	  var time = now.getTime();
    	  var expireTime = time + 1000*36000;
    	  now.setTime(expireTime);
    	  document.cookie = 'Sort='+type+';expires='+now.toUTCString()+';path=/';
    	  //console.log(document.cookie);  // 'Wed, 31 Oct 2012 08:50:17 UTC'
    	
    	}
 
    
    function newcookielist_or_block(type) { 
  	  var now = new Date();
  	  var time = now.getTime();
  	  var expireTime = time + 1000*36000;
  	  now.setTime(expireTime);
  	  document.cookie = 'list_or_block='+type+';expires='+now.toUTCString()+';path=/';
  	  //console.log(document.cookie);  // 'Wed, 31 Oct 2012 08:50:17 UTC'
  	
  	}
    
    function getCookie(name) {
        function escape(s) { return s.replace(/([.*+?\^$(){}|\[\]\/\\])/g, '\\$1'); }
        var match = document.cookie.match(RegExp('(?:^|;\\s*)' + escape(name) + '=([^;]*)'));
        return match ? match[1] : null;
    }
    
    
    
    function eraseCookie(name) {
        document.cookie = name+'=; Max-Age=0';
    }
    
    
    
    function getText(el) {
    	  return el.textContent || el.innerText || '';
    	}

    	function Sort(data) {
    		
    	    
    		  var sort =  getCookie("Sort")
    		
    		var nodeList = document.getElementsByClassName('file-managers');	
    		if(nodeList.length > 0){
			    		if(data == 1){    		
			    			
			    			if(sort == null){
			    			newcookieSort(1);
			    			}else{
			    				eraseCookie("Sort");
			    				newcookieSort(1);
			    			}
						    		var nodeList = document.getElementsByClassName('file-managers');	
						    		
						    		
						    		
						    	  // Assume there's a common parent
						    	  var node, parentNode = nodeList[0].parentNode
						
						    	  // Define a sort function
						    	  function sortEls(a, b) {
						    	    var aText = getText(a);
						    	    var bText = getText(b);
						    	    return aText == bText? 0 : aText < bText? -1 : 1;
						    	  }
						
						    	  // Convert nodelist to an array and remove from the DOM at the same time
						    	  var a = [], i = nodeList.length;
						    	  while (i--) {
						    	    a[i] = parentNode.removeChild(nodeList[i]);
						
						    	  }
						
						    	  // Sort the array
						    	  a.sort(sortEls);
						
						    	  // Put elements back in order
						    	  i = 0;
						    	  while (node = a[i++]) {
						    	    parentNode.appendChild(node);
						    	  }
						    	
			    			}
			    		
			    		
			    		if(data == 2){    		
			    			
			    			

			    			if(sort == null){
			    			newcookieSort(2);
			    			}else{
			    				eraseCookie("Sort");
			    				newcookieSort(2);
			    			}
			    			
			    			
				    		var nodeList = document.getElementsByClassName('file-managers');	
				    		
				    	  // Assume there's a common parent
				    	  var node, parentNode = nodeList[0].parentNode
				
				    	  // Define a sort function
				    	  function sortEls(a, b) {
				    	    var aText = getText(a);
				    	    var bText = getText(b);
				    	    return aText == bText? 0 : aText > bText? -1 : 1;
				    	  }
				
				    	  // Convert nodelist to an array and remove from the DOM at the same time
				    	  var a = [], i = nodeList.length;
				    	  while (i--) {
				    	    a[i] = parentNode.removeChild(nodeList[i]);
				
				    	  }
				
				    	  // Sort the array
				    	  a.sort(sortEls);
				
				    	  // Put elements back in order
				    	  i = 0;
				    	  while (node = a[i++]) {
				    	    parentNode.appendChild(node);
				    	  }
				    	
					}
			    	
			    		   if(data == 3){    		
			
			    			if(sort == null){
				    			newcookieSort(3);
			    			}else{
			    				eraseCookie("Sort");
			    				newcookieSort(3);
			    			}
			    			
			    			
			    	  		var nodeList = document.getElementsByClassName('file-managers');	
				    		
			  	    	  // Assume there's a common parent
			  	    	  var node, parentNode = nodeList[0].parentNode
			  	
			  	    	  // Define a sort function
			  	    	  function sortEls(a, b) {
			  	    	    var aText = $(a).data("date");
			  	    	    var bText = $(b).data("date");
			  	    	    return aText == bText? 0 : aText > bText? -1 : 1;
			  	    	  }
			  	
			  	    	  // Convert nodelist to an array and remove from the DOM at the same time
			  	    	  var a = [], i = nodeList.length;
			  	    	  while (i--) {
			  	    	    a[i] = parentNode.removeChild(nodeList[i]);
			  	
			  	    	  }
			  	
			  	    	  // Sort the array
			  	    	  a.sort(sortEls);
			  	
			  	    	  // Put elements back in order
			  	    	  i = 0;
			  	    	  while (node = a[i++]) {
			  	    	    parentNode.appendChild(node);
			  	    	  }
			  	    	
			  	    	  
			  	    	  
			
			    		}
				      		
					    	  
		 
    	    }
    		
    		
	if(data == 4){    		

		if(sort == null){
			newcookieSort(4);
		}else{
			eraseCookie("Sort");
			newcookieSort(4);
		}
    	  		var nodeList = document.getElementsByClassName('file-managers');	
	    		
  	    	  // Assume there's a common parent
  	    	  var node, parentNode = nodeList[0].parentNode
  	
  	    	  // Define a sort function
  	    	  function sortEls(a, b) {
  	    	    var aText = $(a).data("date");
  	    	    var bText = $(b).data("date");
  	    	    return aText == bText? 0 : aText < bText? -1 : 1;
  	    	  }
  	
  	    	  // Convert nodelist to an array and remove from the DOM at the same time
  	    	  var a = [], i = nodeList.length;
  	    	  while (i--) {
  	    	    a[i] = parentNode.removeChild(nodeList[i]);
  	
  	    	  }
  	
  	    	  // Sort the array
  	    	  a.sort(sortEls);
  	
  	    	  // Put elements back in order
  	    	  i = 0;
  	    	  while (node = a[i++]) {
  	    	    parentNode.appendChild(node);
  	    	  }
  	    	
  	    	  
  	    	  
    			
	      
	      		
		    	  
		 
    	    }
    		
    }
  
 
  
    $('.files-filter2').keyup(delay(function (e) {
    	
  
    	return search(this);
     
    return false;	   
    }, 500));

    
    
    function search() {

    	     var filter = $('.files-filter2').val();
    	      $('.file, .folder').filter(function() {
  	            var regex = new RegExp(filter, 'ig');

  	            if (regex.test($(this).text())) {
  	                $(this).show();
  	            } else {
  	                $(this).hide();
  	            }
  	        });
    	  
        
    }
    
   
        $(window).on('load', function() {
        	$('#example').DataTable(); 
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
                      var element = document.getElementById("nav_doc");
  element.classList.add("active");  
    </script>
</body>
<script>
function openCenteredWindow(url, name, width, height) {
    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>


