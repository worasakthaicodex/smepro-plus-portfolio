<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                                <h4 class="card-title"><spring:message code="menu.Traceability" /></h4>
                                 
                      
                            </div>
                             
                            <div class="card-body">
                  <table class="table" style="font-size:14px">
                                    <thead>
                                        <tr >
                                            <th >
                                            <spring:message code="tables.No" text="ลำดับ​" />
                                            </th>
                                            
                                            
                                                <th >condition_name</th>
                                                   <th style="width:10%"><spring:message code="common.status" /></th>
                                    
                                        </tr>
                                    </thead>
                                    <tbody>
                       
                                           <c:set var="Good" value="0" />
                                            <c:set var="Normal" value="0" />
                                             <c:set var="Bad" value="0" />
                                         <c:forEach items="${conditions }" var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                        
                                            <td>${count.count }</td>
                                            <td>${row.condition_name }</td>
                          
                                           <td>
                                              <c:if test="${row.new_status == '9' }"> 
                                                <span class="badge rounded-pill bg-success" style="width:20px;">&nbsp;</span>
                                                  <span class="badge rounded-pill bg-success" style="width:20px;">&nbsp;</span>
                                                    <span class="badge rounded-pill bg-success" style="width:20px;">&nbsp;</span>
                                                    
                                                         <c:set var="Good" value="${Good+3 }" />
                                              </c:if>
                                                 <c:if test="${row.new_status == '8' }"> 
                                                <span class="badge rounded-pill bg-success" style="width:20px;">&nbsp;</span>
                                                  <span class="badge rounded-pill bg-success" style="width:20px;">&nbsp;</span>
                                                   	<span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span>  
                                                  
                                                     <c:set var="Good" value="${Good+2 }" />
                                                  <c:set var="Normal" value="${Normal+1 }" />
                                              </c:if>
                                              
                                              
                                              
                                              
                                                 <c:if test="${row.new_status == '7' }"> 
                                                <span class="badge rounded-pill bg-success" style="width:20px;">&nbsp;</span>
                                                   <span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span>  
                                                 <span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span>  
                                                 
                                                   <c:set var="Good" value="${Good+1 }" />
                                                  <c:set var="Normal" value="${Normal+2 }" />
                                              </c:if>
                                           
                                           
                                           
                                            <c:if test="${row.new_status == '6' }"> 
                                            	<span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span>  
                                              	<span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span>  
                                            	  	<span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span>  
                                            	
                                        
                                                  <c:set var="Normal" value="${Normal+3 }" />
                                              </c:if>
                                              
                                              
                                              
                                                 <c:if test="${row.new_status == '5' }"> 
                                                	<span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span>  
                                                 	<span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span> 
                                                 	  <span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>  
                                                 	
                                                 	  <c:set var="Normal" value="${Normal+2 }" />
                                                 	   <c:set var="Bad" value="${Bad+1 }" />
                                              </c:if>
                                              
                                              
                                              
                                                 <c:if test="${row.new_status == '4' }"> 
                                                	<span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span>  
                                                	 <span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>  
                                                	  <span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>  
                                                	   	   	  
                                                	   	   	  	
                                                	   	   	  <c:set var="Normal" value="${Normal+1 }" />
                                                 	   <c:set var="Bad" value="${Bad+2 }" />
                                                	   	   	
                                              </c:if>
                                           
                                           
                                           
                                            <c:if test="${row.new_status == '3' }"> 
                                    
                                                  <span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>  
                                                      	 <span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>  
                                                	  <span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>  
                                                  
                                             
                                                 	   <c:set var="Bad" value="${Bad+3 }" />
                                              </c:if>
                                              
                                              
                                              
                                              
                                                 <c:if test="${row.new_status == '2' }"> 
                                               <span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>  
                                                 <span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>  
                                                    <c:set var="Bad" value="${Bad+2 }" />
                                              </c:if>
                                                 <c:if test="${row.new_status == '1' }"> 
                                                 <span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>  
                                                        <c:set var="Bad" value="${Bad+1 }" />
                                              </c:if>
                                           
                                           
                                           
                                        
                                       
                                           
                                           </td>
                                     
                                     
                                      
                                                   
                                                   
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
