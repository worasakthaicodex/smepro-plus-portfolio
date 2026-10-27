<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../common/mainhead.jspf" %>    



    <!-- BEGIN: Vendor CSS-->
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/vendors.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/forms/wizard/bs-stepper.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/forms/select/select2.min.css">
    <!-- END: Vendor CSS-->

   <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-wizard.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-validation.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/pages/modal-create-app.css">



<title>List</title>
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
                                <h4 class="card-title"><spring:message code="menu.Oversee"  /></h4>
                                 
                      
                            </div>
                             
                            <div class="card-body">
                             
                              
                              
                              
                                   <ul class="nav nav-pills" style="display:none">
                                    
                                    
                                   
                                        <li class="nav-item">
                                            <a class="nav-link active" id="profile-tab" data-bs-toggle="pill" href="#profile" aria-expanded="false">
                                            Main
                                           </a>
                                        </li>
                                      <li class="nav-item">
                                            <a class="nav-link" id="home-tab" data-bs-toggle="pill" href="#home" aria-expanded="true">
                              Stock
                                           </a>
                                        </li>
                                        
                                    </ul>
                                    
                                    
                                    <div class="tab-content">
              
                              
    <div class="tab-pane " id="home" role="tabpanel"  ria-labelledby="home-tab" aria-expanded="true">
                                        
                                 <br>   <br>
                             <div class="table-responsive">
                   <table class="table" >
                                    <thead>
                                        <tr >
                                            <th >
                                            <spring:message code="tables.No" text="ลำดับ​" />
                                            </th>
                                            
                                    <th colspan="2"><spring:message code="forms.Name"  /></th>
  
                      
                                        
                                        
                                           <th >
                                             <spring:message code="tables.action" text="ดำเนินการ" />
                                           </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                    
                                    </tbody>
                                </table></div>
                   </div>
               
                 <div role="tabpanel" class="tab-pane active"  id="profile" a  aria-labelledby="profile-tab"  aria-expanded="false">
                     <div class="col-lg-6 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Average</h4>
                                    <div class="dropdown">
                                        <i data-feather="more-vertical" class="cursor-pointer" role="button" id="heat-chart-dd" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        </i>
                                        <div class="dropdown-menu dropdown-menu-end" aria-labelledby="heat-chart-dd">
                                            <a class="dropdown-item" href="#">Last 28 Days</a>
                                            <a class="dropdown-item" href="#">Last Month</a>
                                            <a class="dropdown-item" href="#">Last Year</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                	<div style="width: 350px; height: 350px	;margin:auto">
                                		 <canvas id="myPieChart"></canvas>
                                  </div>
                                </div>
                            </div>
                        </div>
              
                  <table class="table" style="font-size:14px">
                                    <thead>
                                        <tr >
                                            <th >
                                            <spring:message code="tables.No" text="ลำดับ​" />
                                            </th>
                                            
                                            
                                                <th><spring:message code="workflows.name" /></th>
                                           <th style="width:50%" ><spring:message code="workflows.link" /></th>
                                
                                           <th style="width:15%">
                                             <spring:message code="tables.action" text="ดำเนินการ" />
                                           </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                       
      
                          <c:set var="Good" value="0" /> 
                           <c:set var="Normal" value="0" /> 
                           <c:set var="Bad" value="0" /> 
                          
          
                   
                                         <c:forEach items="${workflows }"   var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                        
                                            <td>${count.count }</td>
                                            <td>${row.workflow_name }</td>
                                     <td >

                                 <table style="font-size:12px;">
                                 
								  <c:forEach items="${row.process}" var="item" varStatus="counts">
								      <tr>  
								     <td style="width:300px;padding:4px">${counts.count}  ${item.name} </td>
								     
								      <td>
                                
                                     <c:forEach var="summary" items="${item.statusSummary}">
				                     
				                               <c:if test="${summary.mostFrequentStatus == 'Good' }"> 
				                                 <c:set var="Good" value="${Good+1 }" /> 
				                                        <span class="badge rounded-pill bg-success" style="width:20px;">&nbsp;</span>
				                               </c:if>
				                               
				                   		  <c:if test="${summary.mostFrequentStatus == 'Normal' }"> 
				                   		      <c:set var="Normal" value="${Normal+1 }" /> 
				                                   <span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span> 
				                               </c:if>
				                               
				                               
				                           		  <c:if test="${summary.mostFrequentStatus == 'UNKNOWN' }"> 
				                                   <span class="badge rounded-pill bg-secondary" style="width:20px;">&nbsp;</span> 
				                               </c:if>
				                               
				                             <c:if test="${summary.mostFrequentStatus == 'Bad'}"> 
				                                  <c:set var="Bad" value="${Bad+1 }" /> 
				                                     <span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>
				                               </c:if>
                               
                                             
                       	    </c:forEach>
                      
                                           </td>
								     
								     
								      </tr>  
								    </c:forEach>
                                 </table>  
                                    
                                     </td>
                                          
                                        
                                                   <td>
                                                   
  									
                                                         <a href="/tracking-workflow/${row.workflow_id}" class="btn btn-sm btn-info">
                                                            <spring:message code="btn.view" />
                                                         </a>   
                                   
                                            
                                             
                                                   </td>
                                                   
                                                   
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table></div>
                  
              
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
 
    <script src="/resources/app-assets/vendors/js/forms/wizard/bs-stepper.min.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/select/select2.full.min.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/cleave/cleave.min.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/cleave/addons/cleave-phone.us.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/validation/jquery.validate.min.js"></script>
    <!-- END: Page Vendor JS-->

    <!-- BEGIN: Theme JS-->
    <script src="/resources/app-assets/js/core/app-menu.js"></script>
    <script src="/resources/app-assets/js/core/app.js"></script>
    <!-- END: Theme JS-->

    <!-- BEGIN: Page JS-->
    <script src="/resources/app-assets/js/scripts/pages/modal-add-new-cc.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/page-pricing.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-add-new-address.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-create-app.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-two-factor-auth.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-edit-user.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-share-project.js"></script>
    
    
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    
    
    <script>
    
    var element = document.getElementById("nav_Traceability");
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
        

$('li.level2[data-customer]').hide();
$('li.level3[data-customer]').hide();    


   $('input[name="condition_industry"]').change(function() {
    var selectedIndustry = $(this).val();

   
    
    // ซ่อนทุก <li class="level2"> ที่มี data-customer ไม่เท่ากับค่าที่ถูกเลือก
    $('li.level2[data-customer]').each(function() {
    	console.log($(this).data('customer'));
    	
        if ($(this).data('customer') != selectedIndustry) {
        	
        	
            $(this).hide();
        } else {
            $(this).show();
        }
    });
    
    // แสดงทุก <li class="level3">
   // $('li.level3').show();
});
        

        
        $('input[name="condition_department"]').change(function() {
            var selectedDepartment = $(this).val();

            // ซ่อนทุก <li class="level3"> ที่มี data-customer ไม่เท่ากับค่าที่ถูกเลือก
            $('li.level3[data-customer]').each(function() {
                if ($(this).data('customer') != selectedDepartment) {
                    $(this).hide();
                } else {
                    $(this).show();
                }
            });

            // แสดงทุก <li class="level2">
            $('li.level2').show();
        });
        
    </script>
</body>



<script>
  var ctx = document.getElementById('myPieChart').getContext('2d');
  var myPieChart = new Chart(ctx, {
      type: 'pie',
      data: {
          labels: ['Good', 'Normal', 'Bad'],
          datasets: [{
              label: 'Status',
              data: [${Good},${Normal},${Bad}],
              backgroundColor: [
            	     '#0b5345',
                  '#f1c40f',
                  '#c0392b '
              ],
              borderColor: [
            	     '#0b5345',       
                     '#f1c40f',
                     '#c0392b '
              ],
              borderWidth: 1
          }]
      },
      options: {
          responsive: true,
          plugins: {
              legend: {
                  position: 'top',
              },
              title: {
                  display: true,
              
              }
          }
      }
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
