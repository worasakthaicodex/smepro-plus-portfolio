<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ include file="../common/mainhead.jspf" %> 


<title>index </title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">

<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>     

    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
                         <!--Bar Chart Start -->
                <section id="dashboard-analytics">
                
                   <div class="row match-height">
                   
                <!--Bar Chart Start -->
                        <div class="col-xl-12 col-12">
                        
                        <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"> <spring:message code="nav.darlog" text="ประวัติเอกสาร DAR" /></h4>
                                </div>
                                <div class="card-body">
                                    <ul class="nav nav-pills">
                                    
                                    
                                   
                                        <li class="nav-item">
                                            <a class="nav-link active" id="profile-tab" data-bs-toggle="pill" href="#profile" aria-expanded="false">
                                              <spring:message code="nav.dartotal" text="จำนวน DAR" />
                                           </a>
                                        </li>
                                      <li class="nav-item">
                                            <a class="nav-link" id="home-tab" data-bs-toggle="pill" href="#home" aria-expanded="true">
                                              <spring:message code="nav.darpending" text="DAR คงค้าง" />
                                           </a>
                                        </li>
                                        
                                    </ul>
                                    
                                    
                                    <div class="tab-content">
                                        
                                        
                                        
                                        <div role="tabpanel" class="tab-pane" id="home" aria-labelledby="home-tab" aria-expanded="true">
                                           
                                               <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                              <button onclick="download('xlsx');"  type="button"  class="btn btn-light">Export</button>   
                                              <br> <br>
                                    <table class="table"  id="data-table">
                                    <thead>
                                        <tr>
                                            <th> NO</th>
                                          
                                            <th><spring:message code="nav.creator" text="ผู้สร้าง" /></th>
                                            <th><spring:message code="nav.dartotal" text="จำนวน DAR" /></th>
                                        
                                        </tr>
                                        </thead>
                                       
                                        <c:forEach items="${unfinished }" var="row" varStatus="count">
                                      
                                        <tr class="datatable">
                                            <td> ${count.count } </td>
                                             <td> ${row.user_name }</td>
                                          <td> ${row.dar_step }</td>
                                      </tr>
                                       
                            		  </c:forEach>
                                         </table>
                                        </div>
                                        <div class="tab-pane active" id="profile" role="tabpanel" aria-labelledby="profile-tab" aria-expanded="false">
                                        
                                        
                                          <table class="table" >
                                    <thead>
                                        <tr>
                                            <th> NO</th>
                                          
                                            <th><spring:message code="nav.list" text="รายการ" /></th>
                                            <th><spring:message code="nav.dartotal" text=" จำนวน DAR" /></th>
                                            <th><spring:message code="tables.action"/></th>
                                        </tr>
                                        </thead>
                                       
                                      
                                      
                                        <tr>
                                            <td> 1 </td>
                                          
                                             <td><spring:message code="nav.dartotal" text="จำนวน DAR" /></td>
                                         
                                          <td> ${dartotal }</td>
                                              <td><a href="/dar" class="btn btn-primary"> <spring:message code="btn.view"  /></a></td>
                                      </tr>
                                      
                                         <tr>
                                            <td>2</td>
                                            <td><spring:message code="nav.darpending" text="DAR ค้าง" /></td>
                                         <td> ${dartotal_unfinished }</td>
                                               <td><a href="/dar?type=pending" class="btn btn-primary"> <spring:message code="btn.view"  /></a></td>
                                      </tr>
                                      
                                        
                                        
                                            </table>
                                        </div>
                                     
                                    </div>
                                </div>
                            </div>
                          
                        </div>
                        <!-- Bar Chart End -->

                      
                     </div>
                     </section>
                     
                       
                       </div>
                         </div>
                         </div>
                       
 
    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>





 
<%@ include file="../common/mainfoot.jspf" %>  

    <script>
    var element = document.getElementById("nav_other2");
    element.classList.add("active");
 
     $(window).on('load', function() {
      	
        	
         if (feather) {
             feather.replace({
                 width: 14,
                 height: 14
             });
         }
     });    
         
 
     

  
         
         </script>   
<script src="<c:url value="/lib/xlsx.full.min.js" />"></script>  
    <script>
    
    function search() {

	     var filter = $('.files-filter2').val();
	      $('.datatable').filter(function() {
	    
	    	    $(this).hide();
	            var regex = new RegExp(filter, 'ig');
	          
	            if (regex.test($(this).text())) {
	                $(this).show();
	            } else {
	                $(this).hide();
	            }
	            
	            
	        });
	  
 
}
const download = type => {
	  const el = document.getElementById("data-table");
	  const file = XLSX.utils.table_to_book(el);
	  // Configuring width of the columns:
	  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
	  return XLSX.writeFile(file, "alllog." + type);
	};
	  </script>      
