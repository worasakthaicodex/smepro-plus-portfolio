<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../../common/mainhead.jspf" %>    

       <link href="<c:url value="/resources/app-assets/css/pages/app-email.css" />" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>


<link href="<c:url value="/resources/app-assets/vendors/css/pickers/pickadate/pickadate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/pickers/flatpickr/flatpickr.min.css" />" rel="stylesheet">

<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-flat-pickr.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-pickadate.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-drag-drop.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
 

  <link href="<c:url value="/resources/app-assets/vendors/css/forms/wizard/bs-stepper.min.css" />" rel="stylesheet">
  
  
   <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/css/plugins/forms/form-wizard.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/app-assets/css/plugins/forms/form-validation.css" />" rel="stylesheet">




 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>Payroll info</title>


<style>
 
 
 .select2-selection__arrow{
		 display:none;
 }
 
 table {
    border-collapse:collapse;
    margin: 0;
  width: 100%;
   border: 1px solid #dddddd;
      font-size:5px;
   
}


  
td {
    text-align: center;
   border: 1px solid #dddddd;
      max-width: 140px;
     font-size:12px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */

         min-width: 5px;
    padding:3px;
  
}





th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
     font-size:10px;
     padding:3px;
     max-width: 140px;
       text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
         min-width: 5px;
}





  </style>


 <style>
 
 
 
 
   #searchdata3 {
  width: 100%;
  max-width: 100%;
  overflow-x: auto; /* แก้ปัญหา table ล้นโดยให้ scroll แนวนอน */
}
 
  #transfer_searchdata {
  width: 100%;
  max-width: 100%;
  overflow-x: auto; /* แก้ปัญหา table ล้นโดยให้ scroll แนวนอน */
}
 #searchdata {
  width: 100%;
  max-width: 100%;
  overflow-x: auto; /* แก้ปัญหา table ล้นโดยให้ scroll แนวนอน */
}
 
 .profile-container {

  background-color: #fff;
 


  margin: 0 auto;
  font-family: sans-serif;
}

.profile-list {
  display: flex;
  flex-direction: column;
  font-size: 14px;
}

.profile-item {
  display: grid;
  grid-template-columns: 1fr 2fr;
  gap: 10px;
  padding: 12px 0;
  border-bottom: 1px solid #e5e7eb;
}

.profile-label {
  font-weight: 600;
  color: #111827;
}

.profile-value {
  color: #374151;
}

.profile-item:last-child {
  border-bottom: none;
}


.sidebar {
  padding: 16px;
  background-color: #f9fafb;
  font-family: sans-serif;
}

.menus {
  list-style: none;
  padding: 0;
  margin: 0;
}

.menus-item {
  display: block;
  padding: 8px 16px;
  font-size: 14px;
  font-weight: 500;
  color: #6b7280; /* text-gray-500 */
  border-radius: 8px;
  text-decoration: none;
  transition: background-color 0.2s, color 0.2s;
}

.menus-item:hover {
  background-color: #f3f4f6; /* hover:bg-gray-100 */
  color: #374151; /* hover:text-gray-700 */
}

.menus-item.active {
  background-color: #f3f4f6; /* bg-gray-100 */
  color: #374151; /* text-gray-700 */
}


td{
font-size: 16px;
}

  </style>

</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="../../common/subhead.jspf" %>  
<%@ include file="../../common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body" >
          <section id="multiple-column-form" >
          
     
     
      
          
				
			
          
                    <div class="row">
                 
                        
                           <div class="col-lg-10 col-12">
                         
                          <div class="card page" id="page1">     
                            <div class="card-body" >     
<div class="profile-container">

  <dl class="profile-list">
  
    <div class="profile-item">
        <dt class="profile-label"><spring:message code="report.month" /></dt>
      <dd class="profile-value">${searchMonth }</dd>
    </div>
    
        <div class="profile-item">
      <dt class="profile-label"><spring:message code="report.employeeName"  /></dt>
      <dd class="profile-value">${payroll_record.employee_name }</dd>
    </div>

    <div class="profile-item">
      <dt class="profile-label"><spring:message code="report.baseSalary"  /></dt>
      <dd class="profile-value">${payroll_record.base_salary }</dd>
    </div>

    <div class="profile-item">
      <dt class="profile-label"><spring:message code="report.overtime"  /></dt>
      <dd class="profile-value">${payroll_record.overtime_amount }</dd>
    </div>

    <div class="profile-item">
      <dt class="profile-label"><spring:message code="report.shiftBonus"  /></dt>
      <dd class="profile-value">${payroll_record.shift_bonus }</dd>
    </div>
      <div class="profile-item">
      <dt class="profile-label"><spring:message code="report.otherAllowance"  /></dt>
      <dd class="profile-value">${payroll_record.other_allowance }</dd>
    </div>

   <div class="profile-item">
      <dt class="profile-label"><spring:message code="report.totalEarning"  /></dt>
      <dd class="profile-value">${payroll_record.total_earning }</dd>
    </div>
    
        <div class="profile-item">
      <dt class="profile-label"><spring:message code="report.totalDeduction"  /></dt>
      <dd class="profile-value">
${payroll_record.total_deduction }
      </dd>
    </div>
        <div class="profile-item">
      <dt class="profile-label"><spring:message code="report.netPay"  /></dt>
      <dd class="profile-value">
${payroll_record.net_pay }
      </dd>
    </div>
    


    
  </dl>
</div> 	 
	 
	 
	 
	 


		 
 
 
 
 
 
 
 
 
 </div>
	      </div>  
                  
                              
                                      <div class="card page" id="page2" style="display: none;">   
                                           <div class="card-header">
          <h4 class="card-title"><spring:message code="hr.attendance_record"  /></h4>
          <div class="d-flex align-items-center">
          </div>
        </div>   
                            <div class="card-body" >     
   <table  style="font-size:16px">
                                    <thead>
                                        <tr>
                                        
            	 <th class="form-label fixhead" >#</th>   
            	    	                          
<th class="form-label fixhead" ><spring:message code="report.employee_code"  /></th>     
<th class="form-label fixhead" ><spring:message code="report.employeeName"  /></th>  
<th class="form-label fixhead" ><spring:message code="report.attendance_date"  /> </th>  


  
<th class="form-label fixhead" ><spring:message code="report.check_in_time"  /></th>     
<th class="form-label fixhead" ><spring:message code="report.check_out_time"  /></th>     
<th class="form-label fixhead" ><spring:message code="report.work_hours"  /></th>   
<th class="form-label fixhead" ><spring:message code="report.late_minutes"  /></th>   
<th class="form-label fixhead" ><spring:message code="report.overtime_hours"  /></th>     
         </tr>
                                    </thead>
                                    <tbody>

 
														
				                   <c:forEach items="${attendance }" var="row"  varStatus="count">   
				                                                              <tr>
                                        
            	 <td class="form-label fixhead" >${count.count }</td>   
            	 
            	                          
 					<td class="form-label fixhead"  data-custom="code" 
 					title="${row.employeeCode }">${row.employeeCode }</td>    
 					
 					 
<td class="form-label fixhead" >${row.employeeName }</td>     
<td class="form-label fixhead"  >${row.attendance_date }</td>     
<td class="form-label fixhead"  >${row.check_in_time }</td>  
<td class="form-label fixhead" >${row.check_out_time }</td>   
<td class="form-label fixhead" > ${data.work_hours }</td>  
<td class="form-label fixhead"> ${row.late_minutes }</td>  
<td class="form-label fixhead"> ${row.overtime_hours }</td> 


 
                               </tr>
												                  
				                       	</c:forEach>                  
				                                      
                                      
                                        
                                    </tbody>
                                    </table>     
       
 </div>
	      </div> 
                         <div class="card page" id="page3" style="display: none;">     
                                                             <div class="card-header">
          <h4 class="card-title"><spring:message code="hr.deduction_record"  /></h4>
          <div class="d-flex align-items-center">
          </div>
        </div>   
                            <div class="card-body" >     
   <table  style="font-size:16px">
                                    <thead>
                                        <tr>
                                        
            	 <th class="form-label fixhead" >#</th>   
            	    	                          
<th class="form-label fixhead" ><spring:message code="report.employee_code"  /></th>     
<th class="form-label fixhead" ><spring:message code="report.employeeName"  /></th>  
<th class="form-label fixhead" ><spring:message code="report.deduct_date"  /> </th>  
<th class="form-label fixhead" ><spring:message code="report.deduct_type"  /></th>     
<th class="form-label fixhead" ><spring:message code="report.description"  /></th>   
<th class="form-label fixhead" ><spring:message code="report.deduct_amount"  /></th>   
<th class="form-label fixhead" ><spring:message code="report.payroll_month"  /></th>     

         </tr>
                                    </thead>
                                    <tbody>

 
														
				                   <c:forEach items="${deduction }" var="row"  varStatus="count">   
				                                                              <tr>
                                        
            	 <td class="form-label fixhead" >${count.count }</td>   
            	 
            	                          
 					<td class="form-label fixhead"  data-custom="code" 
 					title="${row.employeeCode }">${row.employeeCode }</td>    
 					
 					 
<td class="form-label fixhead" >${row.employeeName }</td>     
<td class="form-label fixhead"  >${row.deduct_date }</td>     
<td class="form-label fixhead"  >${row.deductType }</td>  
<td class="form-label fixhead" >${row.description }</td>   
<td class="form-label fixhead" > ${data.work_hours }</td>  
<td class="form-label fixhead"> ${row.deduct_amount }</td>  
<td class="form-label fixhead"> ${row.payroll_month }</td> 


 
                               </tr>
												                  
				                       	</c:forEach>                  
				                                      
                                      
                                        
                                    </tbody>
                                    </table>     
 </div>
	      </div>  
                      
                      
                      
       
                       
                     
      
               
       
          
          
                     
      </div>
                                 
                      <div class="col-lg-2 col-12">
                           
                                  <div class="card" >
                     <div class="sidebar">
<ul class="menus" id="tabdata"> 
<li><a href="#" class="menus-item active" data-custom="1"><spring:message code="hr.payroll_record"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="2"><spring:message code="hr.attendance_record"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="3"><spring:message code="hr.deduction_record"  /></a></li> 

</ul>
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



 <div style="clear:both"></div>
    
    
    
<%@ include file="../../common/mainfoot.jspf" %>  


	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>

 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>

 
    <script src="<c:url value="/resources/app-assets/js/scripts/tables/table-datatables-advanced.js" />"></script>
 
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/legacy.js" />"></script>
  <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.date.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>
 <script src="<c:url value="/resources/app-assets/js/scripts/forms/pickers/form-pickers.js" />"></script>



 <script src="<c:url value="/resources/app-assets/js/scripts/extensions/ext-component-drag-drop.js" />"></script>
<script src="<c:url value="/resources/app-assets/vendors/js/extensions/dragula.min.js" />"></script>
	  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	      <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>


 <%@ include file="../../other/alert.jsp" %>

  <c:if test="${Sys_Save == 'Save_Sucess'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Success');
      });
    
    </script>
</c:if>
  <c:if test="${Sys_Save == 'Sys_Failed'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('error','Save','Failed');
      });
    
    </script>
</c:if>
</body>



  
	    <script>
	    
	    function printDiv() {
	    	  var content = document.getElementById('traceability_searchdata').innerHTML;
	    	  var mywindow = window.open('', 'Print', 'height=600,width=800');

	    	  mywindow.document.write('<html><head><title>Print</title>');
	    	  mywindow.document.write(`<style>
	    	    td {
	    	      text-align: center;
	    	      border: 1px solid #dddddd;
	    	      max-width: 140px;
	    	      font-size: 12px;
	    	      overflow: hidden;
	    	      text-overflow: ellipsis;
	    	      white-space: nowrap;
	    	      min-width: 5px;
	    	      padding: 3px;
	    	    }
	    	    th {
	    	      text-align: center;
	    	      border: 1px solid #dddddd;
	    	      background-color: #f2f2f2;
	    	      height: 30px;
	    	      font-size: 10px;
	    	      padding: 3px;
	    	      max-width: 140px;
	    	      text-overflow: ellipsis;
	    	      min-width: 5px;
	    	    }
	    	    table {
	    	      border-collapse: collapse;
	    	      width: 100%;
	    	    }
	    	  </style>`);

	    	  mywindow.document.write('</head><body>');
	    	  mywindow.document.write(content);
	    	  mywindow.document.write('</body></html>');

	    	  mywindow.document.close();
	    	  mywindow.focus();

	    	  mywindow.print();
	    	  mywindow.close();

	    	  return true;
	    	}

	    
	    
	      $(window).on('load', function() {
	            if (feather) {
	                feather.replace({
	                    width: 14,
	                    height: 14
	                });
	            }
	        })
	        
    var element = document.getElementById("nav_workflowsystem");
    element.classList.add("active"); 
    

    
    function postdatacal(Mode,Mode2,Mode3) {

	    var formData = new FormData($("#"+ Mode2)[0]);

			    $.ajax({
			        url: Mode,
			        type: "POST",
			        data: formData,
			        processData: false,
			        contentType: false,
				    success: function(result){
				    $('.' + Mode3).html(result);
				       },
				    error: function(jqXHR, textStatus, errorThrown) {
				            if (jqXHR.status === 401) {
				                // Redirect to login page if unauthorized
				                window.location.href = '/login'; // URL ของหน้า login
				     }}
				    });
			

		
			   
	    return false;
	    }	
    
    
    
    function getdata(url) {
    	
    	  if (confirm("You want to take action.?")) {
    	    $.ajax({
    	      type: 'GET',
    	      url: url,
    	      success: function(data) {
    	        $("#savedata").html(data);
    	      }
    	    });
    	  } else {
    	    console.log("ยกเลิกการโหลดข้อมูล");
    	  }
    	  return false;
    	}


    </script>				



<script> 	



function postdata(Mode, Mode2, Mode3) {

    let targetDiv = $("#" + Mode3);
    
    // ทำให้เนื้อหาเก่าจางลง
    targetDiv.css("opacity", "0.5").css("position", "relative");

    // สร้าง Spinner อยู่ตรงกลางของ div
    let loadingSpinner = `
        <div class="spinner-overlay">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>`;

    // แทรก Spinner เข้าไปใน div
    targetDiv.append(loadingSpinner);

		
	
	
    var formData = new FormData($("#" + Mode2)[0]);

    $.ajax({
        url: Mode,
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function(result){
            $('#' + Mode3).html(result);
            targetDiv.css("opacity", "1"); // คืนค่า opacity ปกติ
        },
        error: function(jqXHR, textStatus, errorThrown) {
                window.location.href = '/login'; // URL ของหน้า login
       
        }
    });

   // $("#" + Mode2)[0].reset();
   
    return false;
}
	    
</script> 		    
	    
	   <script>
  // เมื่อโหลดหน้าเว็บ: ซ่อนทุกหน้า ยกเว้น active
  document.querySelectorAll('.page').forEach(div => div.style.display = 'none');
  const activeTab = document.querySelector('.menus-item.active');
  if (activeTab) {
    const pageId = 'page' + activeTab.getAttribute('data-custom');
    document.getElementById(pageId).style.display = 'block';
  }

  // เมื่อคลิกเปลี่ยนแท็บ
  document.querySelectorAll('.menus-item').forEach(item => {
    item.addEventListener('click', function (e) {
      e.preventDefault();

      // ลบ active เดิม
      document.querySelectorAll('.menus-item').forEach(el => el.classList.remove('active'));

      // ตั้ง active ใหม่
      this.classList.add('active');

      // ซ่อนทุกหน้า
      document.querySelectorAll('.page').forEach(div => div.style.display = 'none');

      // แสดงหน้าที่ถูกเลือก
      const pageId = 'page' + this.getAttribute('data-custom');
      const page = document.getElementById(pageId);
      if (page) {
        page.style.display = 'block';
      }
    });
  });
</script> 

<script>

document.getElementById('export-csv3').addEventListener('click', function () {
	  const table = document.getElementById('table3');
	  const rows = table.querySelectorAll('tr');
	  let csv = [];

	  rows.forEach(row => {
	    if (row.style.display === 'none') return;

	    const cols = row.querySelectorAll('th, td');
	    const rowData = [];

	    cols.forEach(col => {
	      // ข้ามถ้ามี class "Action"
	      if (col.classList.contains('Action')) return;

	      let text = col.textContent.trim().replace(/"/g, '""');
	      rowData.push('"' + text + '"');
	    });

	    // ถ้ามีข้อมูลจริงจึงเพิ่มเข้า csv
	    if (rowData.length > 0) {
	      csv.push(rowData.join(','));
	    }
	  });

	  const csvContent = csv.join('\n');
	  const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
	  const link = document.createElement('a');
	  link.href = URL.createObjectURL(blob);
	  link.download = 'table.csv';
	  link.click();
	});


</script>


<script>

document.getElementById('export-csv2').addEventListener('click', function () {
	  const table = document.getElementById('table2');
	  const rows = table.querySelectorAll('tr');
	  let csv = [];

	  rows.forEach(row => {
	    if (row.style.display === 'none') return;

	    const cols = row.querySelectorAll('th, td');
	    const rowData = [];

	    cols.forEach(col => {
	      // ข้ามถ้ามี class "Action"
	      if (col.classList.contains('Action')) return;

	      let text = col.textContent.trim().replace(/"/g, '""');
	      rowData.push('"' + text + '"');
	    });

	    // ถ้ามีข้อมูลจริงจึงเพิ่มเข้า csv
	    if (rowData.length > 0) {
	      csv.push(rowData.join(','));
	    }
	  });

	  const csvContent = csv.join('\n');
	  const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
	  const link = document.createElement('a');
	  link.href = URL.createObjectURL(blob);
	  link.download = 'table.csv';
	  link.click();
	});


</script>

<script>

document.getElementById('export-csv').addEventListener('click', function () {
	  const table = document.getElementById('table');
	  const rows = table.querySelectorAll('tr');
	  let csv = [];

	  rows.forEach(row => {
	    if (row.style.display === 'none') return;

	    const cols = row.querySelectorAll('th, td');
	    const rowData = [];

	    cols.forEach(col => {
	      // ข้ามถ้ามี class "Action"
	      if (col.classList.contains('Action')) return;

	      let text = col.textContent.trim().replace(/"/g, '""');
	      rowData.push('"' + text + '"');
	    });

	    // ถ้ามีข้อมูลจริงจึงเพิ่มเข้า csv
	    if (rowData.length > 0) {
	      csv.push(rowData.join(','));
	    }
	  });

	  const csvContent = csv.join('\n');
	  const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
	  const link = document.createElement('a');
	  link.href = URL.createObjectURL(blob);
	  link.download = 'table.csv';
	  link.click();
	});


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