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
                                <h4 class="card-title"><spring:message code="forms.All" /></h4>
                                 
                                 
			                           <div>        
<a href="/listcondition-condition" class="btn btn-primary waves-effect waves-float waves-light" >
<spring:message code="forms.center" /></a>
			               
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
                                           <th >RV</th>
                                           <th >
                                             <spring:message code="tables.action" text="ดำเนินการ" />
                                           </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${conditions }" var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                        
                                            <td>${count.count }</td>
                                            <td>${row.condition_name }</td>
                                     <td>${row.condition_rv }</td>
                                                   <td>

                                           <a href="/render-condition/${row.condition_id}"  onclick="openCenteredWindow(this.href, 'File Window', 900, 650);return false;" class="btn btn-light" target="_blank">
                                                      <spring:message code="btn.View" text="ดู" />
                                          </a>
                                                   
                                                   
                                            
                                                   <a href="/copycondition-condition/${row.condition_id }" class="btn btn-success" >
                                                  Copy</a>
                                             
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
                                    
                                    
                                    
                                    
                                        <div class="step" data-target="#create-app-details" role="tab" id="create-app-details-trigger">
                                            <button type="button" class="step-trigger py-75">
                                                <span class="bs-stepper-box">
                                                    <i data-feather="book" class="font-medium-3"></i>
                                                </span>
                                                <span class="bs-stepper-label">
                                                    <span class="bs-stepper-title">อุตสาหกรรม</span>
                                                    <span class="bs-stepper-subtitle">Enter Information</span>
                                                </span>
                                            </button>
                                        </div>
                                        <div class="step" data-target="#create-app-frameworks" role="tab" id="create-app-frameworks-trigger">
                                            <button type="button" class="step-trigger py-75">
                                                <span class="bs-stepper-box">
                                                    <i data-feather="package" class="font-medium-3"></i>
                                                </span>
                                                <span class="bs-stepper-label">
                                                    <span class="bs-stepper-title">แผนก</span>
                                                    <span class="bs-stepper-subtitle">Enter Information</span>
                                                </span>
                                            </button>
                                        </div>
                                          <div class="step" data-target="#create-app-database" role="tab" id="create-app-database-trigger">
                                            <button type="button" class="step-trigger py-75">
                                                <span class="bs-stepper-box">
                                                    <i data-feather="package" class="font-medium-3"></i>
                                                </span>
                                                <span class="bs-stepper-label">
                                                    <span class="bs-stepper-title">ประเภทเเบบฟอร์ม</span>
                                                    <span class="bs-stepper-subtitle">Enter Information</span>
                                                </span>
                                            </button>
                                        </div>
                                        
                                        
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
                                        <div id="create-app-details" class="content" role="tabpanel" aria-labelledby="create-app-details-trigger">
                                          

                                            <h5 >อุตสาหกรรม</h5>
                                                <ul class="list-group list-group-flush" style="height:400px;overflow-y: scroll;">
                                               
                                               <c:forEach items="${centrals }" var="row" varStatus="count">
                                               
                                               
                                               
                                                <li class="list-group-item border-0 px-0 " >
                                                    <label for="createAppOnlineLearning" class="d-flex cursor-pointer">
                                                        <span class="avatar avatar-tag bg-light-danger me-1">
                                                            <i data-feather="award" class="font-medium-5"></i>
                                                        </span>
                                                        <span class="d-flex align-items-center justify-content-between flex-grow-1">
                                                            <span class="me-1">
                                                                <span class="h5 d-block fw-bolder">${row.central_name2 }</span>
                                                                <span>${row.central_name1 }</span>
                                                            </span>
                                                            <span> 
                                                                <input class="form-check-input" type="radio"  value="${row.central_id }"  name="condition_industry" required/>
                                                            </span>
                                                        </span>
                                                    </label>
                                                </li>
                                       
                                                </c:forEach>
                                                
                                            
                                                 
                                    
                                                
                                                
                                                
                                                
                                              
                                                
                                            </ul>
                                            <br>     <br>     <br><br><br>
                                            <div class="d-flex justify-content-between mt-2">
                                                <button type="button"  class="btn btn-outline-secondary btn-prev" disabled>
                                                    <i data-feather="arrow-left" class="align-middle me-sm-25 me-0"></i>
                                                    <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                                </button>
                                                <button type="button" class="btn btn-primary btn-next">
                                                    <span   class="align-middle d-sm-inline-block d-none">Next</span>
                                                    <i data-feather="arrow-right" class="align-middle ms-sm-25 ms-0"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div id="create-app-frameworks" class="content" role="tabpanel" aria-labelledby="create-app-frameworks-trigger">
                                            <h5>แผนก</h5>
                                                    <ul class="list-group list-group-flush" style="height:400px;overflow-y: scroll;">
                                               
                                            
                                            
                                              <c:forEach items="${centrals2 }" var="row" varStatus="count">
                                                       <li class="list-group-item border-0 px-0 level2"  data-customer="${row.central_idrefer }">
                                                    <label for="createAppEcommerce" class="d-flex cursor-pointer">
                                                        <span class="avatar avatar-tag bg-light-info me-1">
                                                            <i data-feather="briefcase" class="font-medium-5"></i>
                                                        </span>
                                                        <span class="d-flex align-items-center justify-content-between flex-grow-1">
                                                            <span class="me-1">
                                                                <span class="h5 d-block fw-bolder">${row.central_name2 }</span>
                                                                <span>${row.central_name1 } </span>
                                                            </span>
                                                            <span>
                                                                <input class="form-check-input" type="radio"   value="${row.central_id }" name="condition_department" required/>
                                                            </span>
                                                        </span>
                                                    </label>
                                                </li>
                                                </c:forEach>
                                                    
                                            </ul>
                                            <div class="d-flex justify-content-between mt-2">
                                                <button type="button" class="btn btn-primary btn-prev">
                                                    <i data-feather="arrow-left" class="align-middle me-sm-25 me-0"></i>
                                                    <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                                </button>
                                                <button type="button" class="btn btn-primary btn-next">
                                                    <span class="align-middle d-sm-inline-block d-none">Next</span>
                                                    <i data-feather="arrow-right" class="align-middle ms-sm-25 ms-0"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div id="create-app-database" class="content" role="tabpanel" aria-labelledby="create-app-database-trigger">
                                            <h5>ประเภทเเบบฟอร์ม</h5>

                                            <ul class="list-group list-group-flush" style="height:400px;overflow-y: scroll;">
                                               
                                                <c:forEach items="${centrals3 }" var="row" varStatus="count">
                                                <li class="list-group-item border-0 px-0 level3" data-customer="${row.central_idrefer }" >
                                                    <label for="createAppFirebase" class="d-flex cursor-pointer">
                                                            <span class="avatar avatar-tag bg-light-secondary me-1">
                                                           <img src="/resources/app-assets/images/icons/database.png" height="25" alt="database" />
                                                        </span>
                                                        <span class="d-flex align-items-center justify-content-between flex-grow-1">
                                                            <span class="me-1">
                                                                <span class="h5 d-block fw-bolder">${row.central_name2 }</span>
                                                                <span>${row.central_name1 }</span>
                                                            </span>
                                                            <span>
                                                                <input class="form-check-input"  type="radio" value="${row.central_id }" name="condition_type" required/>
                                                            </span>
                                                        </span>
                                                    </label>
                                                </li>
                                                </c:forEach>
                                                
                                              
                                           
                                            </ul>
                                            <div class="d-flex justify-content-between mt-2">
                                                <button type="button"  class="btn btn-primary btn-prev">
                                                    <i data-feather="arrow-left" class="align-middle me-sm-25 me-0"></i>
                                                    <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                                </button>
                                                <button type="button"  class="btn btn-primary btn-next">
                                                    <span class="align-middle d-sm-inline-block d-none">Next</span>
                                                    <i data-feather="arrow-right" class="align-middle ms-sm-25 ms-0"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div id="create-app-billing" class="content" role="tabpanel" aria-labelledby="create-app-billing-trigger">
                                            <h5 class="mb-1">E-Forms</h5>

                                            <!-- form -->
                                         
                                                <div class="col-12">
                                                    <label class="form-label" for="cardNumberBilling">E-Forms name</label>
                                                    <div class="input-group ">
                                                        <input name="condition_name" class="form-control " type="text" required />
                                                        
                                                    </div>
                                                </div>
  												  <div class="col-12">
                                                    <label class="form-label" for="cardNumberBilling">ชื่อที่ใช้ในเอกสาร</label>
                                                    <div class="input-group ">
                                                        <input name="condition_title" class="form-control " type="text" required />
                                                        
                                                    </div>
                                                </div>
                                                
                                                
                                                <div class="col-md-12" style="display:none">
                                                 
				                                                            <label class="form-label" for="itemname">หัวข้อเพิ่มเติ่ม</label>  <br>
				                                                   
				                                                            <input type="checkbox"  name="condition_support1" value="1" ${support1 }/> วันที่  <br>
				                                                            <input type="checkbox"  name="condition_support2" value="1" ${support2 }/> เวลา <br>
				                                                            <input type="checkbox"  name="condition_support3" value="1" ${support3 }/> แผนก <br>
				                                                            <input type="checkbox"  name="condition_support4" value="1"  ${support4 }/> ความถี่ <br>
				                                                            <input type="checkbox"  name="condition_support5" value="1"  ${support5 }/>  บันทึกเพิ่มเติ่ม <br>
				                                                             <input type="checkbox"  name="condition_support6" value="1" ${support6 }/> ประเภทการบันทึก  <br>  
                                              
    														 </div>
                                             

                                               
                                      

                                            <div class="d-flex justify-content-between mt-5 pt-1">
                                                <button type="button"  class="btn btn-primary btn-prev">
                                                    <i data-feather="arrow-left" class="align-middle me-sm-25 me-0"></i>
                                                    <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                                </button>
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

 <script>
function openCenteredWindow(url, name, width, height) {
    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>
