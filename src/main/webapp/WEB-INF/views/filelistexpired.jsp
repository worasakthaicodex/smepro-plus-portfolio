<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>File index</title>
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
                    
                         
                         
                         <div class="col-lg-4 col-md-6 col-12">
                        <div class="card card-app-design">
                        
                            <div class="card-body">
                    <h4 class="card-title">
                        <spring:message code="menu.expiredfiles" text="เอกสารล้าสมัย" />
                                </h4>
                                <div class="design-planning-wrapper">
                                    <div class="design-planning">
                                        <p class="card-text mb-25">ALL</p>
                                        <h6 class="mb-0" id="ALL"></h6>
                                    </div>
                                    <div class="design-planning">
                                        <p class="card-text mb-25"><spring:message code="file.filecanceled" text="ถูกยกเลิก"   /> </p>
                                        <h6 class="mb-0" id="type1"></h6>
                                    </div>
                                    <div class="design-planning">
                                        <p class="card-text mb-25"><spring:message code="file.fileexpired" text="หมดอายุ"  /></h6>
                                          <h6 class="mb-0"  id="type2"></h6>
                                    </div>
                                        <div class="design-planning">
                                        <p class="card-text mb-25"><spring:message code="file.fileexpired2" text="เวอร์ชั่นเก่า" /></h6>
                                          <h6 class="mb-0"  id="type3"></h6>
                                    </div>
                                  
                                    
                                </div>
                           
                            </div>
                        </div>
                    </div>
                         
                        </div>
          
          
                    <div class="row">
                  
                   <div class="col-12">
                   
                   <div class="card">
                        
                            <div class="card-body"> <div class="table-responsive">
                            
                                  <input type="checkbox" id=filecanceled  value="-1" checked>  <spring:message code="file.filecanceled" text="ถูกยกเลิก"   />  
                                  <input type="checkbox" id="fileexpired" value="-2" checked>  <spring:message code="file.fileexpired" text="หมดอายุ"  />  
                            <input type="checkbox" id="fileexpired2" value="-3" checked> <spring:message code="file.fileexpired2" text="เวอร์ชั่นเก่า" />  
                     
                         <button onclick="download('xlsx');"  type="button" style="float:right;margin-left:5px"  class="btn btn-sm btn-primary"><spring:message code="btn.export"  /></button>
                                <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                <br> <br>
                            
                   <table class="table" id="data-table">
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
                                    <tbody >
                                            <c:set var="type1" value="0" />
                                    <c:set var="type2" value="0" />
                                    <c:set var="type3" value="0" />
                                         <c:forEach items="${file }" var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable"  data-custom="${row.file_type2}">
                                        
                                            <td>${count.count }</td>
                                            <td>${row.file_no } ${row.file_id }</td>
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
                                               
                                               
                                                   <c:if test="${row.eform  >  0 }">     
                                        <a href="/render-condition/${row.eform}" target="_blank" onclick="openCenteredWindow(this.href, 'File Window', 900, 650);return false;">Eform</a>
                                </c:if>
                                                 <c:if test="${row.eform  <  0 }"> 
                                                                 <sec:authorize access="hasAnyAuthority('APP-DAR')">    
                                               					    <a href="/mainfileinfo-filemanager?id=${row.file_id }&&Folders=${row.file_path}" class="btn btn-light waves-effect waves-float waves-light">
							          								View</a>  
							          
                                         </sec:authorize> 
                                     </c:if>
							          
							          </td>
                                                   
                                        </tr>
                                          </c:forEach>
                                          
                                              <script>
                                            $("#ALL").html(${type1 +type2+ type3});
                                            $("#type1").html(${type1});
                                            $("#type2").html(${type2});
                                            $("#type3").html(${type3});
                                        
                                            </script>
                                          
                                          
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
    
    
    
    var element = document.getElementById("nav_dar6");
    element.classList.add("active");    
 
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
    function filterTable() {
        const canceledChecked = document.getElementById("filecanceled").checked;
        const expiredChecked = document.getElementById("fileexpired").checked;
        const expired2Checked = document.getElementById("fileexpired2").checked;

        const rows = document.querySelectorAll("tr.datatable");

        rows.forEach(row => {
            const customValue = row.getAttribute("data-custom");

            if ((customValue === "-1" && !canceledChecked) ||
                (customValue === "-2" && !expiredChecked) ||
                (customValue === "-3" && !expired2Checked)) {
                row.style.display = "none";
            } else {
                row.style.display = "";
            }
        });
    }

    document.querySelectorAll('input[type="checkbox"]').forEach(item => {
        item.addEventListener('change', filterTable);
    });

    // ใช้ฟังก์ชัน filterTable เพื่อซ่อนหรือแสดงแถวที่ถูกตรวจสอบเมื่อหน้าเว็บโหลด
    filterTable();
</script>
</body>


