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
                   <div class="row" id="basic-table">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">ค้นหาตามประเภทความถี่</h4>
                                 <a href="/recordslog2-log" class="btn btn-light waves-effect waves-float waves-light">
							          ค้นหาตามช่วงวันที่ </a>
                                    
                            </div>
  
                            <div class="table-responsive" style='padding:5px'>
                            
                            
                           <div style="display:none">
                                  <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                              <button onclick="download('xlsx');"  type="button"  class="btn btn-primary">Export table to excel xlsx</button>
                                 <br>   <br>
                            </div> 
                                 
                                     <form:form method="POST" action="/recordslog-log">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                  ค้นหาตามวันที่  : <input type="date"  placeholder="Search" name="date"/>
                                  <button type="submit"  class="btn btn-primary">Search</button>
                                   </form:form>
                                   
                               
                                
                                    <br>
                       ผลการค้นหา : ${dates }
      
                               <br> <br>     
                                   <h4 class="card-title">Daily</h4>
                                <table class="table table-bordered" id="data-table" >
                                    <thead>
                                        <tr>
                                           <th>วันที่ต้องอัพโหลด</th>
                                            <th>เอกสารที่ต้องอัพโหลด</th>
                                      
                                            <th>ผู้อัพโหลด</th>
                                             <th>วันที่อัพโหลด</th>
                                            <th>ผู้ตรวจสอบ</th>
                                              <th>วันที่ตรวจสอบ</th>
                                            <th>เป้าหมาย</th>
                                              <th>ขาดส่ง</th>
                                            <th>อัพโหลดช้าสะสม (วัน)</th>
                                              <th> ตรวจสอบช้าสะสม (วัน)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${report_pn }" var="row" varStatus="count">
                                        
                                        <tr class="datatable ">
                                        
                                                   <td>${row.report_pn_date}</td>
                                            <td>
                                           ${row.upload_pn_name}
                                           
                                           
                                             <c:if test="${!row.upload_pn_name2.equals('') && row.upload_pn_name2 != null }"> 
                                            <br> <br>${row.upload_pn_name2}
                                               </c:if>
                                                 <c:if test="${!row.upload_pn_name3.equals('') && row.upload_pn_name3 != null }"> 
                                             <br> <br> ${row.upload_pn_name3}
                                               </c:if>
                                               <c:if test="${!row.upload_pn_name4.equals('') && row.upload_pn_name4 != null }"> 
                                             <br> <br> ${row.upload_pn_name4}
                                               </c:if>
                                               <c:if test="${!row.upload_pn_name5.equals('') && row.upload_pn_name5 != null }"> 
                                             <br> <br> ${row.upload_pn_name5}
                                               </c:if>
                                           
                                              
                                            </td>
                                
                                            <td>
                                                ${row.report_pn_respon}
                                            </td>
                                              <td> ${row.report_pn_dateupload}</td>
                                          
                                            
                                                <td>
                                                ${row.report_pn_app}
                                            </td>
                                             <td> ${row.report_pn_datecheck}</td>
                                             <td> ${row.report_pn_target}</td>
                                            
                                       
                                            <td> ${row.report_pn_sendstatus}</td>
                                                   <td> ${row.report_pn_statuserror}</td>
                                       <td> ${row.report_pn_appstatus}</td>
                                  
                                        </tr>
                                              
                                              
                                
                                    </tbody>
                                  </c:forEach>
                                  
                                   
                                </table>
                            
                               <br>
                           <hr>
                             <br>
                                 <h4 class="card-title">Weekly</h4>
                                <table class="table table-bordered" id="data-table">
                                    <thead>
                                        <tr>
                                           <th>วันที่ต้องอัพโหลด</th>
                                            <th>เอกสารที่ต้องอัพโหลด</th>
                                      
                                            <th>ผู้อัพโหลด</th>
                                             <th>วันที่อัพโหลด</th>
                                            <th>ผู้ตรวจสอบ</th>
                                              <th>วันที่ตรวจสอบ</th>
                                            <th>เป้าหมาย</th>
                                              <th>ขาดส่ง</th>
                                            <th>อัพโหลดช้าสะสม (วัน)</th>
                                              <th> ตรวจสอบช้าสะสม (วัน)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${report_pnweek }" var="row" varStatus="count">
                                        
                                        <tr class="datatable">
                                        
                                                   <td>${row.report_pn_date}</td>
                                             <td>
                                           ${row.upload_pn_name}
                                           
                                           
                                             <c:if test="${!row.upload_pn_name2.equals('') && row.upload_pn_name2 != null }"> 
                                            <br> <br>${row.upload_pn_name2}
                                               </c:if>
                                                 <c:if test="${!row.upload_pn_name3.equals('') && row.upload_pn_name3 != null }"> 
                                             <br> <br> ${row.upload_pn_name3}
                                               </c:if>
                                               <c:if test="${!row.upload_pn_name4.equals('') && row.upload_pn_name4 != null }"> 
                                             <br> <br> ${row.upload_pn_name4}
                                               </c:if>
                                               <c:if test="${!row.upload_pn_name5.equals('') && row.upload_pn_name5 != null }"> 
                                             <br> <br> ${row.upload_pn_name5}
                                               </c:if>
                                           
                                              
                                            </td>
                                
                                            <td>
                                                ${row.report_pn_respon}
                                            </td>
                                              <td> ${row.report_pn_dateupload}</td>
                                          
                                            
                                                <td>
                                                ${row.report_pn_app}
                                            </td>
                                             <td> ${row.report_pn_datecheck}</td>
                                              <td> ${row.report_pn_target}</td>
                                            
                                       
                                            <td> ${row.report_pn_sendstatus}</td>
                                                   <td> ${row.report_pn_statuserror}</td>
                                       <td> ${row.report_pn_appstatus}</td>
                                  
                                        </tr>
                                              
                                              
                                
                                    </tbody>
                                  </c:forEach>
                                  
                                   
                                </table>
                            
                            
                                 <br>
                           <hr>
                             <br>
                                      <h4 class="card-title">Monthly</h4>
                                <table class="table table-bordered" id="data-table">
                                    <thead>
                                        <tr>
                                           <th>วันที่ต้องอัพโหลด</th>
                                            <th>เอกสารที่ต้องอัพโหลด</th>
                                      
                                            <th>ผู้อัพโหลด</th>
                                             <th>วันที่อัพโหลด</th>
                                            <th>ผู้ตรวจสอบ</th>
                                              <th>วันที่ตรวจสอบ</th>
                                            <th>เป้าหมาย</th>
                                              <th>ขาดส่ง</th>
                                            <th>อัพโหลดช้าสะสม (วัน)</th>
                                              <th> ตรวจสอบช้าสะสม (วัน)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${report_pnmonth }" var="row" varStatus="count">
                                        
                                        <tr class="datatable">
                                        
                                                   <td>${row.report_pn_date}</td>
                                             <td>
                                           ${row.upload_pn_name}
                                           
                                           
                                             <c:if test="${!row.upload_pn_name2.equals('') && row.upload_pn_name2 != null }"> 
                                            <br> <br>${row.upload_pn_name2}
                                               </c:if>
                                                 <c:if test="${!row.upload_pn_name3.equals('') && row.upload_pn_name3 != null }"> 
                                             <br> <br> ${row.upload_pn_name3}
                                               </c:if>
                                               <c:if test="${!row.upload_pn_name4.equals('') && row.upload_pn_name4 != null }"> 
                                             <br> <br> ${row.upload_pn_name4}
                                               </c:if>
                                               <c:if test="${!row.upload_pn_name5.equals('') && row.upload_pn_name5 != null }"> 
                                             <br> <br> ${row.upload_pn_name5}
                                               </c:if>
                                           
                                              
                                            </td>
                                
                                
                                            <td>
                                                ${row.report_pn_respon}
                                            </td>
                                              <td> ${row.report_pn_dateupload}</td>
                                          
                                            
                                                <td>
                                                ${row.report_pn_app}
                                            </td>
                                             <td> ${row.report_pn_datecheck}</td>
                                              <td> ${row.report_pn_target}</td>
                                            
                                       
                                            <td> ${row.report_pn_sendstatus}</td>
                                                   <td> ${row.report_pn_statuserror}</td>
                                       <td> ${row.report_pn_appstatus}</td>
                                  
                                        </tr>
                                              
                                              
                                
                                    </tbody>
                                  </c:forEach>
                                  
                                   
                                </table>
                           <br>
                           <hr>
                             <br>
                                 <h4 class="card-title">Annually</h4>
                                <table class="table table-bordered" id="data-table">
                                    <thead>
                                        <tr>
                                           <th>วันที่ต้องอัพโหลด</th>
                                            <th>เอกสารที่ต้องอัพโหลด</th>
                                      
                                            <th>ผู้อัพโหลด</th>
                                             <th>วันที่อัพโหลด</th>
                                            <th>ผู้ตรวจสอบ</th>
                                              <th>วันที่ตรวจสอบ</th>
                                           
                                              <th>ขาดส่ง</th>
                                            <th>อัพโหลดช้าสะสม (วัน)</th>
                                              <th> ตรวจสอบช้าสะสม (วัน)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${report_pnyear }" var="row" varStatus="count">
                                        
                                        <tr class="datatable">
                                        
                                                   <td>${row.report_pn_date}</td>
                                            <td>
                                           ${row.upload_pn_name}
                                            </td>
                                
                                            <td>
                                                ${row.report_pn_respon}
                                            </td>
                                              <td> ${row.report_pn_dateupload}</td>
                                          
                                            
                                                <td>
                                                ${row.report_pn_app}
                                            </td>
                                             <td> ${row.report_pn_datecheck}</td>
                                            
                                            
                                       
                                            <td> ${row.report_pn_sendstatus}</td>
                                                   <td> ${row.report_pn_statuserror}</td>
                                       <td> ${row.report_pn_appstatus}</td>
                                  
                                        </tr>
                                              
                                              
                                
                                    </tbody>
                                  </c:forEach>
                                  
                                   
                                </table>
                           
                           
                            </div>
                        </div>
                    </div>
                </div>
               
                     </div>
                     </section>
                     
                       
                       </div>
                         </div>
                         </div>
                       
 
    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>




 
<%@ include file="../common/mainfoot.jspf" %>   


     <script>
     var element = document.getElementById("nav_other4");
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
         