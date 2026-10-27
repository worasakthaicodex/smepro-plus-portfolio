<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    

<title>All log</title>
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
          <section id="multiple-column-form">
                    <div class="row">
                  
                   <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title"> <spring:message code="nav.alllog" text="ประวัติการใช้งาน" /></h4>
                            </div>
                            <div class="card-body"> 
               
                        
         
                            
                            
                            
                            
                            <div class="table-responsive">
                            
                                <input type="checkbox" id="LOGIN" checked> LOGIN  <input type="checkbox" id="LOGOUT" checked> LOGOUT  
                            <input type="checkbox" id="READ" checked> READ  <input type="checkbox" id="DELETE" checked> DELETE
                             <input type="checkbox" id="UPLOAD" checked> UPLOAD  <input type="checkbox" id="DOWNLOAD" checked> DOWNLOAD
                            <input type="checkbox" id="OTHER" checked> OTHER
                                            <button onclick="download('xlsx');"  type="button" style="float:right;margin-left:5px"  class="btn btn-sm btn-primary"><spring:message code="btn.export"  /></button>
                                      <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                            
                                 <br>   <br>
                   <table class="table" id="data-table">
                                    <thead>
                                        <tr>
                                            
                                                <th><spring:message code="label.date" text="วันที่" /></th>
                                            <th><spring:message code="label.name" text="ชื่อผู้ใช้" /></th>
                                            <th><spring:message code="tables.action" text="ดำเนินการ"/></th>
                                            <th><spring:message code="tables.storage" text="แหล่งที่เก็บ"/></th>
                                             <th><spring:message code="tables.note" text="หมายเหตุ"/></th>
                                        
                                        </tr>
                                    </thead>
                                    <tbody >
                                        
                                         <c:forEach items="${log }" var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                        
                                       
                                            <td>${row.log_date } ${row.log_time }</td>
                                            <td>${row.user_name }</td>
                                            <td>${row.log_step }</td>
                                             <td>${row.log_folder }</td>
                                             <td>${row.log_note }</td>
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table>
                                
                                </div>
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





<%@ include file="../common/mainfoot.jspf" %>  
 

    <script>
    var element = document.getElementById("nav_other1");
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
    

 
 <script>
 // ซ่อนหรือแสดงแถวของตารางตาม checkbox
 function filterTable() {
     const loginChecked = document.getElementById("LOGIN").checked;
     const logoutChecked = document.getElementById("LOGOUT").checked;
     const readChecked = document.getElementById("READ").checked;
     const deleteChecked = document.getElementById("DELETE").checked;
     const uploadChecked = document.getElementById("UPLOAD").checked;
     const downloadChecked = document.getElementById("DOWNLOAD").checked;
     const otherChecked = document.getElementById("OTHER").checked;

     const tableBody = document.getElementById("data-table").getElementsByTagName("tbody")[0];
     const rows = tableBody.getElementsByTagName("tr");

     for (let i = 0; i < rows.length; i++) {
         const row = rows[i];
         const rowText = row.textContent || row.innerText;

         // ตรวจสอบแต่ละ checkbox และซ่อนแถวที่ไม่ตรงตามเงื่อนไข
         if ((!loginChecked && rowText.includes("LOGIN")) ||
             (!logoutChecked && rowText.includes("LOGOUT")) ||
             (!readChecked && rowText.includes("READ")) ||
             (!deleteChecked && rowText.includes("DELETE")) ||
             (!uploadChecked && rowText.includes("UPLOAD")) ||
             (!downloadChecked && rowText.includes("DOWNLOAD")) ||
             (!otherChecked && !isStandardAction(rowText))) {
             row.style.display = "none";
         } else {
             row.style.display = "";
         }
     }
 }

 // เช็คว่าเป็นกิจกรรมมาตรฐานหรือไม่
 function isStandardAction(actionText) {
     const standardActions = ["LOGIN", "LOGOUT", "READ", "DELETE", "UPLOAD", "DOWNLOAD"];
     return standardActions.some(action => actionText.includes(action));
 }

 // เรียกใช้งาน filterTable เมื่อ checkbox มีการเปลี่ยนแปลง
 document.querySelectorAll('input[type="checkbox"]').forEach(item => {
     item.addEventListener('change', filterTable);
 });

 // ใช้ฟังก์ชัน filterTable เพื่อซ่อนหรือแสดงแถวที่ถูกตรวจสอบเมื่อหน้าเว็บโหลด
 filterTable();
</script>
</body>


