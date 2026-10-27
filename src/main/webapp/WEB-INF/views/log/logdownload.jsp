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
                                    <h4 class="card-title"><spring:message code="log.file" text="" /></h4>
                                </div>
                                <div class="card-body">
                                  <button onclick="download('xlsx');"  type="button" style="float:right;margin-left:5px"  class="btn btn-sm btn-primary"><spring:message code="btn.export"  /></button>
                                     <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                          
                                 <br>   <br>
                                        
                                    <table class="table" id="data-table">
                                    <thead>
                                        <tr>
                                            <th> NO</th>
                                          
                                             <th style="font-size:12px">
                                            <spring:message code="tables.docname" text="ชื่อเอกสาร" />
                                            </th>
                                             <th style="font-size:12px">
                                             <spring:message code="tables.storage" text="แหล่งที่เก็บ" />
                                           </th>
                                            <th> <spring:message code="log.download" text="จำนวนครั้งดาวน์โหลด" /></th>
                                        
                                        </tr>
                                        </thead>
                                       
                                        <c:forEach items="${doc }" var="row" varStatus="count">
                                      
                                        <tr class="datatable">
                                            <td> ${count.count } </td>
                                             <td> ${row.file_name_original }</td>
                                             
                                                <td>${row.file_path_full }</td>
                                             <td> ${row.log_note }</td>
                                      </tr>
                                      
                            		  </c:forEach>
                                        
                                         </table>
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
   
     $(window).on('load', function() {
      	
        	
         if (feather) {
             feather.replace({
                 width: 14,
                 height: 14
             });
         }
     });
     
     var element = document.getElementById("nav_other3");
     element.classList.add("active");

     

  
         
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