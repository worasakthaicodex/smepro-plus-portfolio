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



<title>Raw Data</title>
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
                                <h4 class="card-title" title="id : ${upload_pn.upload_pn_id }">${upload_pn.upload_pn_note }  [Raw Datas]</h4>
                                 
                      
                            </div>
                             
                            <div class="card-body">
                         <label  style="color:black;min-width:100px;"><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /> :</label>
                                                      <small  style="color:black">${upload_pn.upload_pn_respon }</small>
                                                       <br>
                                                                 <label  style="color:black;min-width:100px;"> <spring:message code="tables.inspector" text="ผู้ดูเเล" /> :</label>
                                                      <small  style="color:black">${upload_pn.upload_pn_app }</small>
                                  <br>
                            
                                         <label  style="color:black;min-width:100px;"> Location  :</label>
                                                      <small  style="color:black">${upload_pn.upload_pn_storage }</small>
                            
                            
                            <c:if test="${edatas.size() == 0}">    
                            <div class="alert alert-primary" role="alert">
                                            <div class="alert-body"><strong>Data not found!</strong> ไม่พบข้อมูล.</div>
                                        </div>
                            </c:if>
                            
                            
                            
                                  <c:if test="${edatas.size() > 0}">              
                            
                  <table class="table" style="font-size:14px">

                                    <tbody>
	                             <tr class="datatable">
						                      <c:forEach items="${edatas }" begin="0" end="0" var="row" varStatus="count">
						                      	
						                      		  <th>${row.data_title1 }</th>
						                    	  		<th>${row.data_title2 }</th>
						                    	  			<th>${row.data_title3 }</th>
						                    	  				<th>${row.data_title4 }</th>
						                    	  					<th>${row.data_title5 }</th>
						                      </c:forEach>
						                      <th>Status</th>
                                   </tr>           
                                         <c:forEach items="${edatas }"  begin="0" end="300"  var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                           <td> ${row.data_value1 }</<td>
                                            <td> ${row.data_value2 }</<td>
                                            <td> ${row.data_value3 }</<td>
                                            <td> ${row.data_value4 }</td>
                                            <td> ${row.data_value5 }</td>
				                          <td>
				                  
				                               <c:if test="${row.data_status == 'Good' }"> 
				                                        <span class="badge rounded-pill bg-success" style="width:20px;">&nbsp;</span>
				                               </c:if>
				                   		  <c:if test="${row.data_status == 'Normal' }"> 
				                                   <span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span> 
				                               </c:if>
				                          
				                             <c:if test="${row.data_status == 'Bad' }"> 
				                                     <span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>
				                               </c:if>
				                               <c:if test="${row.data_status == ''  || row.data_status == null  }"> 
				                                             <span class="badge rounded-pill bg-secondary" style="width:20px;">&nbsp;</span>  
				                               </c:if>
				                          </td>
                
                                                         
                                      
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table></div>
                  
              
                  
                  </c:if>
                  
                  
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
