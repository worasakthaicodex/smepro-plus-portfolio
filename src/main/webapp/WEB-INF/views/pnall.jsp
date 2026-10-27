<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    
<title>Folder</title>

<style>




 table {
    border-collapse:collapse;
    margin: 0;
  width: 100%;
   border: 1px solid #dddddd;
   
}


  
td {
    text-align: center;
   border: 1px solid #dddddd;
      max-width: 150px;
     font-size:14px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
}
td  .action{
 text-align: center;
    border: 1px solid #dddddd;
    max-width: 150px;
    font-size: 14px;
    white-space: normal; /* ขึ้นบรรทัดใหม่เมื่อเนื้อหาล้น */
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
       font-size:14px;
}


.fixhead{
    max-width: 150px;
      min-width: 80px;
      
}

thead {
    position: sticky;
    top: 0;
    background-color: #f1f1f1; /* สีพื้นหลังของหัวตารางเมื่อ sticky */
    z-index: 1; /* ให้หัวตารางอยู่ด้านบนของเนื้อหา */
}

</style>


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
                           <spring:message code="log.pnall" text="" />
                                </h4>
                                <div class="design-planning-wrapper">
                                    <div class="design-planning">
                                        <p class="card-text mb-25"><spring:message code="plan.total" text="จำนวนแผนงาน" /></p>
                                        <h6 class="mb-0" id="totalplan"></h6>
                                    </div>
                                    <div class="design-planning">
                                        <p class="card-text mb-25"><spring:message code="btn.enable" text="เปิด" /></p>
                                        <h6 class="mb-0" id="use"></h6>
                                    </div>
                                    <div class="design-planning">
                                        <p class="card-text mb-25"><spring:message code="btn.disable" text="ปิด" /></h6>
                                          <h6 class="mb-0"  id="notuse"></h6>
                                    </div>
                                        <div class="design-planning">
                                        <p class="card-text mb-25"><spring:message code="btn.Pending"  /></h6>
                                          <h6 class="mb-0"  id="pending"></h6>
                                    </div>
                                </div>
                           
                            </div>
                        </div>
                    </div>
                         
                        </div>
                  
                  
                   <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                             
                                
                                     <c:if test="${folder.folder_type2 ==  3}">  
                                   <a href="/addupload_pn-filemanager/${idfolder}"><button class="btn btn-primary waves-effect waves-float waves-light">
                                   <spring:message code="btn.add"  />
                                   </button></a>
                      </c:if>
                      
                            </div>
                            
                            
                            
                            <div class="card-body"> 
                           <input type="checkbox" id="enable" checked> <spring:message code="btn.enable" text="เปิด" />
                            <input type="checkbox" id="disable" checked>  <spring:message code="btn.disable" text="ปิด" />
                          
                                <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                 <br>   <br>
                                                     <div class="table-responsive" style='padding:5px'>
             <div class="table-responsive" style="height:500px;clear:both">
                        
                         
                   <table  >
                                    <thead>
                                        <tr>
                                            <th class="form-label fixhead">  <spring:message code="tables.No" text="ลำดับ"  /></th>
                                            <th class="form-label fixhead"><spring:message code="tables.record" text="ชื่อบันทึก"  /></th>
                                            <th class="form-label fixhead"><spring:message code="tables.filelist"  /></th>
                                             <th class="form-label fixhead"><spring:message code="btn.Pending"  /></th>
                                                 <th class="form-label fixhead"><spring:message code="tables.type" text="ประเภท" /></th>
                                              <th class="form-label fixhead"> <spring:message code="tables.due" text="กำหนด" /></th>
                                              
                                              <th class="form-label fixhead"><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /></th>
                                              <th class="form-label fixhead"><spring:message code="tables.inspector" text="ผู้ดูเเล" /></th>
                                            <th class="form-label fixhead">  <spring:message code="tables.department" text="หน่วยงาน" /></th>
                                              <th class="form-label fixhead"><spring:message code="tables.status" text="สถานะ" /></th>
                                               <th class="form-label fixhead"><spring:message code="tables.storage" text="ที่เก็บ" /></th>
                                                    <th class="form-label fixhead"><spring:message code="tables.storage" text="ที่เก็บ" /></th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                    <c:set var="use" value="0" />
                                    <c:set var="notuse" value="0" />
                                     <c:forEach items="${upload_pn }" var="row" varStatus="count">
                                        <c:set var="due" value="" />
                                       <c:if test="${row.upload_pn_due ==  0 && row.upload_pn_type == 0 }">   
                      						<spring:message code="due.date" text="ทุกวัน"  var="due"/>
                     					 </c:if>
                                        <c:if test="${row.upload_pn_due ==  1 }">   
                      						<spring:message code="due.week" text="ทุกอาทิตย์"  var="due"/>
                     					 </c:if>
                     					    <c:if test="${row.upload_pn_due ==  2 }">   
                      						<spring:message code="due.month" text="ทุกเดือน"  var="due"/>
                     					 </c:if>
                     					
                     				
                                       <tr class="datatable" data-custom="${row.upload_pn_end}">
                                        
                                            <td>${count.count }</td>
                                            <td>
                        <c:if test="${row.upload_pn_monitor > 0  && row.upload_pn_monitor < 100000000 }">                            
                                            
       <a href="/relationplan-condition/${row.upload_pn_id}"  target="_blank"   
              onclick="openCenteredWindow(this.href, 'File Window', 1500, 600);  return false;" title="Relation view" > ${row.upload_pn_note } </a>       
       </a>
                                        
                                          </c:if>
                                          
                                          
                                            <c:if test="${row.upload_pn_monitor > 100000000 }">   
                                            ${row.upload_pn_note }
                                             </c:if>
                                             
                                             
                                           </td>
                                           
                                           
                                      <td>
                                         
                                            -${row.upload_pn_name } 
                                           <c:if test="${row.upload_pn_name2 != '' }"><br>-${row.upload_pn_name2 }</c:if>
                                           <c:if test="${row.upload_pn_name3 != '' }"><br>-${row.upload_pn_name3 }</c:if>
                                            <c:if test="${row.upload_pn_name4 != '' }"><br>-${row.upload_pn_name4 }</c:if>
                                             
                                             
                                           </td>
                                           
                                           
                                             <td>${row.status1 + row.status2 + row.status3 }
                                             <c:set var="pending" value="${pending + (row.status1 + row.status2 + row.status3) }" /> 
                                             </td>
                                              <td>
                                              
                                               <c:set var="type" value="" />
                                                  <c:if test="${row.upload_pn_type == 0}">    
                                                             <spring:message code="due.type1" text="กำหนดตามช่วงเวลา"  var="type"/>
                                                         </c:if>  
                                                      <c:if test="${row.upload_pn_type == 1}">    
                                                            <spring:message code="due.type2" text="กำหนดตามวันที่" var="type"/>
                                                         </c:if>  
                                                    <c:if test="${row.upload_pn_type ==2}">    
                                                            <spring:message code="due.type3" text="ไม่มีกำหนด" var="type"/>
                                                         </c:if>  
                                              
                                         ${type }
                                              </td>
                                            <td>${due }
                                            	     <c:if test="${row.upload_pn_type == 1 }">   
                      					 	${row.upload_pn_datedue }
                     					 </c:if>
                                            
                                            </td>
                                             <td>${row.upload_pn_respon }</td>
                                              <td>${row.upload_pn_app }</td>
                                               <td>${row.upload_pn_department }</td>
                                            <td >
                                                <c:if test="${row.upload_pn_end == 0}"> 
                                                <spring:message code="btn.enable" text=" เปิด" />
                                                   <c:set var="use" value="${use+1 }" />
                                                	</c:if>	
                                               <c:if test="${row.upload_pn_end == 1}"> 
                                               <font style="color:red"> <spring:message code="btn.disable" text="ปิด" />
                                               
                                                  <c:set var="notuse" value="${notuse+1 }" />    </font>
                                                	</c:if>	   
                 
                                            
                                            
                                            
                                            </td>
                                             <td>${row.upload_pn_storage }</td>
                                                 <td> <a href="/duepn-filemanager?id=${row.upload_pn_id}&&step=${row.status0}&&group=1" class="btn btn-sm">view</a></td>
                                           
                                        </tr>
                                              <script>
                                              $("#totalplan").html(${count.count});
                                              $("#use").html(${use});
                                              $("#notuse").html(${notuse});
                                              $("#pending").html(${pending});
                                              </script>
                                     </c:forEach>
                                    </tbody>
                                </table>
                                </div>
                                
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





<%@ include file="common/mainfoot.jspf" %>  
 
     <script>
    
    var element = document.getElementById("nav_new1");
    element.classList.add("active");  
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
    </script>
 
 
  <c:if test="${dar.dar_flow > 1}"> 
   <script>
   function check(){
		 if( $('ul.required :radio:checked').length > 0){
			 return false;
			 alert('ระบุตำเเหน่งเอกสาร');
		 } 
		 return true;
   }

     </script>
     </c:if>
   <c:if test="${dar.dar_flow < 2}"> 
      <script>
   function check(){

		 return true;
   }

     </script>
  </c:if>
    <script>
    
 
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
     // JavaScript to handle checkbox changes
     document.getElementById('disable').addEventListener('change', function() {
       updateRowsVisibility();
     });

     document.getElementById('enable').addEventListener('change', function() {
       updateRowsVisibility();
     });

     function updateRowsVisibility() {
       const enableChecked = document.getElementById('disable').checked;
       const disableChecked = document.getElementById('enable').checked;

       toggleRows(enableChecked, disableChecked);
     }

     function toggleRows(enableChecked, disableChecked) {
       // Get all table rows
       const rows0 = document.querySelectorAll('tr[data-custom="0"]');
       const rows1 = document.querySelectorAll('tr[data-custom="1"]');

       // Show or hide rows based on checkbox states
       rows0.forEach(function(row) {
         if (disableChecked || (enableChecked && disableChecked)) {
           row.classList.remove('hidden');
         } else {
           row.classList.add('hidden');
         }
       });

       rows1.forEach(function(row) {
         if (enableChecked || (enableChecked && disableChecked)) {
           row.classList.remove('hidden');
         } else {
           row.classList.add('hidden');
         }
       });
     }

     // Initialize the visibility based on the default state of the checkboxes
     updateRowsVisibility();
  </script>
    
</body>


