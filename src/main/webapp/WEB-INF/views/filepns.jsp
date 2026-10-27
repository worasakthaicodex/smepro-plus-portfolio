<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>File Pn</title>
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
                  
                   <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Record Lists All </h4>
                               
                            </div>
                            <div class="card-body">
                        <button onclick="download('xlsx');"  type="button"  class="btn btn-primary">Export table to excel xlsx</button>
                         <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                         <br> <br>
                
                             <div class="table-responsive">
                   <table class="dt-multilingual table"  id="data-table">
                                    <thead>
                                        <tr>
                                            <th>
                                            <spring:message code="tables.No" text="ลำดับ"  />
                                            </th>
                                          
                                            <th>
                                                <spring:message code="tables.record" text="ชื่อบันทึก"  />
                                            </th>
                                            <th>
                                             <spring:message code="tables.due" text="กำหนด" />
                                            </th>
                                            <th>
                                               <spring:message code="tables.department" text="หน่วยงาน" />
                                            </th>
                                            
                                           
                                             <th>
                                             Folder
                                            </th>
                                            <th>
                                             <spring:message code="tables.responsible" text="ผู้รับผิดชอบ" />
                                            </th>
                                            <th>
                                             <spring:message code="tables.inspector" text="ผู้ดูเเล" />
                                            </th>
                                           
                                             <th>
                                               <spring:message code="tables.status" text="สถานะ" />
                                             </th>
                                         <th>
                                               Action
                                             </th>
                                        </tr>
                                    </thead>
                                    <tbody >
                                        
                                         <c:forEach items="${upload_pn }" var="row" varStatus="count">

											<c:if test="${row.upload_pn_due ==  0 }">   
                      						<spring:message code="due.date" text="ทุกวัน"  var="due"/>
                     					 </c:if>
                                        <c:if test="${row.upload_pn_due ==  1 }">   
                      						<spring:message code="due.week" text="ทุกอาทิตย์"  var="due"/>
                     					 </c:if>
                     					    <c:if test="${row.upload_pn_due ==  2 }">   
                      						<spring:message code="due.month" text="ทุกเดือน"  var="due"/>
                     					 </c:if>
                     
                     
                     					<c:set var="color" value="" />
                     			
                     			
                     				  <c:if test="${row.report_pn_status ==  1 }">   
  														 <c:set var="color" value="black" />
                      							<spring:message code="status.wait" text="รออัพโหลด"  var="status"/>
                     					 </c:if>
                     					  <c:if test="${row.report_pn_status ==  2 }">   
  														 <c:set var="color" value="black" />
                      							<spring:message code="status.waitingtocheck" text="รอตรวจ"  var="status"/>
                     					 </c:if>	 
                     					 
                     						
                     					  <c:if test="${row.report_pn_status ==  3 }">   
   													 <c:set var="color" value="green" />
   	                   							<spring:message code="status.success" text="อนุมัติเเล้ว"  var="status"/>
                     					 </c:if>
                     					 
                     					 
                     					 <c:if test="${row.report_pn_status ==  -1 }">   
   														 <c:set var="color" value="black" />
                      									<spring:message code="status.reject" text="ไม่อนุมัติ"  var="status"/>
                     					 </c:if> 
                     					 
                     					 
                     					 
                                        <tr class="tablework  datatable" >
                                        
                                            <td>${count.count }</td>
                                            <td>${row.upload_pn_name }</td>
                                            <td>${row.report_pn_date } </td>
                                            <td>${row.upload_pn_department }</td>
                                             <td>${row.folder_name }</td>
                                            <td>${row.upload_pn_respon }</td>
                                            <td>${row.upload_pn_app }</td>
                                            
                                            
                                            <td style="color: ${color}">${status }</td>
                                          <td><a  href="/duepn-filemanager?id=${row.upload_pn_id }&&step=4&&group=1" class="btn btn-sm btn-success"><spring:message code="btn.view" text="ดู"/></a></td>
                                      
                                        </tr>
                                          </c:forEach>
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





<%@ include file="common/mainfoot.jspf" %>  
 <script src="<c:url value="/lib/xlsx.full.min.js" />"></script> 

    <script>
    
    const download = type => {
  	  const el = document.getElementById("data-table");
  	  const file = XLSX.utils.table_to_book(el);
  	  // Configuring width of the columns:
  	  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
  	  return XLSX.writeFile(file, "alllog." + type);
  	};
    
    var element = document.getElementById("nav_other7");
    element.classList.add("active"); 
    
    function search() {

    	     var filter = $('.files-filter2').val();
    	      $('.datatable').filter(function() {
    	    
    	    	  
  	            var regex = new RegExp(filter, 'ig');
  	          
  	            if (regex.test($(this).text())) {
  	                $(this).show();
  	            } else {
  	                $(this).hide();
  	            }
  	            
  	            
  	        });
    	  
        
    }
    
    
    
    $('.normal').hide();
    
    
    function active (){
    	
   
    	if (document.getElementById('active').checked) {
    		  $('.normal').hide();
    		  $('.success').hide();
    		 
        } else {
            $('.normal').show();
            $('.success').show();
         
        }
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

 

    </script>
</body>


