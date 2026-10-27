<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    



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
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  

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
                                <h4 class="card-title"><spring:message code="forms.center" /></h4>
                                 
                                 
			                           <div>        
			                                   <a href="/listcondition2-condition" class="btn btn-primary waves-effect waves-float waves-light" ><spring:message code="forms.All" /></a>
			                            		 <a href="#" class="btn btn-primary waves-effect waves-float waves-light" data-bs-toggle="modal" data-bs-target="#createAppModal">
			                            		 <spring:message code="btn.add" />
			                            		 </a>
			                           </div>
                            </div>
                             
                            <div class="card-body">
                             
                                   <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                 <br>   <br>
                             <div class="table-responsive">
                   <table class="table" >
                                    <thead>
                                        <tr >
                                            <th >
                                            <spring:message code="tables.No" text="ลำดับ​" />
                                            </th>
                                            <th ><spring:message code="forms.Name" /></th>
                                            

                                           <th >
                                             <spring:message code="tables.action" text="ดำเนินการ" />
                                           </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${conditions }" var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                        
                                            <td>${count.count }</td>
                                           
                                            <td>
                                           ${row.condition_title } 
                                             </td>
                                    
                                                   <td>
                                          
                                                   
                                               <c:if test="${row.condition_status == 0 and row.condition_idadd ==  Member_id or Member_id == 1}"> 
                                                          <a href="/editcondition-condition/${row.condition_id }" class="btn btn-info"><spring:message code="btn.edit" text="แก้ไข" /></a>
                                                   <a href="/deletecondition-condition/${row.condition_id }" class="btn btn-danger" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.condition_name }');">
                                                   <spring:message code="btn.delete" text="ลบ" /></a>
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


    <!-- create app modal -->
                <div class="modal fade" id="createAppModal" tabindex="-1" aria-labelledby="createAppTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-lg">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body pb-3 px-sm-3">
                                <h1 class="text-center mb-1" id="createAppTitle">Create E-Forms</h1>
                                <p class="text-center mb-2">จัดทำ E-form</p>

                                <div class="bs-stepper vertical wizard-modern create-app-wizard">
                                    
                                    
                                    <div class="bs-stepper-header" role="tablist">
                                    
                                    
                                    
                                    
                                        
                                        <div class="step" data-target="#create-app-billing" role="tab" id="create-app-billing-trigger">
                                            <button type="button" class="step-trigger py-75">
                                                <span class="bs-stepper-box">
                                                    <i data-feather="credit-card" class="font-medium-3"></i>
                                                </span>
                                                <span class="bs-stepper-label">
                                                    <span class="bs-stepper-title">E-Forms</span>
                                                    <span class="bs-stepper-subtitle">Enter E-Forms name</span>
                                                </span>
                                            </button>
                                        </div>
                                    
                                        <div class="step" data-target="#create-app-submit" role="tab" id="create-app-submit-trigger">
                                            <button type="button" class="step-trigger py-75">
                                                <span class="bs-stepper-box">
                                                    <i data-feather="check" class="font-medium-3"></i>
                                                </span>
                                                <span class="bs-stepper-label">
                                                    <span class="bs-stepper-title">Submit</span>
                                                    <span class="bs-stepper-subtitle">Submit your E-Forms</span>
                                                </span>
                                            </button>
                                        </div>
                                    </div>

                                    <!-- content -->
                                    <div class="bs-stepper-content shadow-none">
                         
                                          <form:form method="POST" modelAttribute="condition" action="/addconditions-condition">
                                          
                                          
                                          
                                           <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     
                                        <div id="create-app-billing" class="content" role="tabpanel" aria-labelledby="create-app-billing-trigger">
                                            <h5 class="mb-1">E-Forms</h5>

                                            <!-- form -->
                                         
                                                <div class="col-12">
                                                    <label class="form-label" for="cardNumberBilling">E-Forms name (EN)</label>
                                                    <div class="input-group ">
                                                        <input name="condition_name" class="form-control " type="text" required />
                                                        
                                                    </div>
                                                </div>
  												  <div class="col-12">
                                                    <label class="form-label" for="cardNumberBilling">ชื่อที่ใช้ในเอกสาร (TH)</label>
                                                    <div class="input-group ">
                                                        <input name="condition_title" class="form-control " type="text" required />
                                                        
                                                    </div>
                                                </div>
                                                
                                 
                                             

                                               
                                         <div class="d-flex justify-content-between mt-2">
                                          
                                  
                                                 <button  type="button" class="btn btn-primary btn-next">
                                                    <span class="align-middle d-sm-inline-block d-none">Next</span>
                                                    <i data-feather="arrow-right" class="align-middle ms-sm-25 ms-0"></i>
                                                </button>
                                            </div>

                                       
                                        </div>
                                        <div id="create-app-submit" class="content text-center" role="tabpanel" aria-labelledby="create-app-submit-trigger">
                                            <h3>Submit 🥳</h3>
                                            <p>ตรวจสอบข้อมูลให้เรียบร้อย E-form ที่ถูกสร้างเเล้วจะเเก้ไขรายละเอียดไม่ได้</p>
                                            <img src="/resources/app-assets/images/illustration/pricing-Illustration.svg" height="218" alt="illustration" />
                                            <div class="d-flex justify-content-between mt-3">
                                                <button type="button"  class="btn btn-primary btn-prev">
                                                    <i data-feather="arrow-left" class="align-middle me-sm-25 me-0"></i>
                                                    <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                                </button>
                                                <button  class="btn btn-success">
                                                    <span class="align-middle d-sm-inline-block d-none">Submit</span>
                                                    <i data-feather="check" class="align-middle ms-sm-25 ms-0"></i>
                                                </button>
                                            </div>
                                        </div>
                                           
                                                 </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- / create app modal -->



<%@ include file="common/mainfoot.jspf" %>  
 
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
    <script>
    
    var element = document.getElementById("e-form1");
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


