<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="common/mainhead.jspf" %>    
<title>Folder</title>
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
                                    <h4 class="card-title">Report</h4>
                                </div>
                                <div class="card-body">
                                <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>ชื่อรายงาน</th>
                                            <th>คำอธิบาย</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                              ข้อมูลการอัพโหลดบันทึกคุณภาพแยกรายบุลคล
                                            </td>
                                            <td>เเบบรายเดือน</td>
                                            <td>
                                            <a href="#" class="btn btn-light waves-effect waves-float waves-light" data-bs-toggle="modal" data-bs-target="#addNewCard1">
							          View</a>
                                            </td>
                                        </tr>
                                                        <tr>
                                            <td>
                                              ข้อมูลการอัพโหลดบันทึกคุณภาพแยกรายบุลคล
                                            </td>
                                            <td>เเบบรายปี</td>
                                            <td>
                                            <a href="#" class="btn btn-light waves-effect waves-float waves-light" data-bs-toggle="modal" data-bs-target="#addNewCard2">
							          View</a>
                                            </td>
                                        </tr>
                            
                                    </tbody>
                                </table>
                            </div>
                                 </div>
                             
                                </div>
                                
                            </div>
                         
                        </div>
                  
                  
    </div>
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>

    <!-- BEGIN: Footer-->
    <footer class="footer footer-static footer-light">
        <p class="clearfix mb-0"><span class="float-md-start d-block d-md-inline-block mt-25">COPYRIGHT &copy; 2021<a class="ms-25" href="https://1.envato.market/pixinvent_portfolio" target="_blank">Pixinvent</a><span class="d-none d-sm-inline-block">, All rights Reserved</span></span><span class="float-md-end d-none d-md-block">Hand-crafted & Made with<i data-feather="heart"></i></span></p>
    </footer>
    <button class="btn btn-primary btn-icon scroll-top" type="button"><i data-feather="arrow-up"></i></button>
    <!-- END: Footer-->


    <!-- add new card modal  -->
                <div class="modal fade" id="addNewCard1" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle">ระบุเดือนที่ต้องการ</h1>
                                <p class="text-center" id="addNewCardTitle title" >ข้อมูลการอัพโหลดบันทึกคุณภาพแยกรายบุลคล</p>

                                <!-- form -->
                                <form id="addNewCardValidation" class="row gy-1 gx-2 mt-75" onsubmit="return false">
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber">เดือน</label>
                                        <div class="mb-1">
                                            <select id="month" name="month" class="form-select">
                                             <option value="1">1</option>
                                              <option value="2">2</option>
                                               <option value="3">3</option>
                                                <option value="4">4</option>
                                                 <option value="5">5</option>
                                                  <option value="6">6</option>
                                                   <option value="7">7</option>
                                                    <option value="8">8</option>
                                                     <option value="9">9</option>
                                                      <option value="10">10</option>
                                                       <option value="11">11</option>
                                                        <option value="12">12</option>
                                             </select>
                                            
                                            
                                            
                                   
                                        </div>
                                    </div>

                            



                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1" onclick="postform1()">นำออก</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                            Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->
                
                
                
                
                
        <!-- add new card modal  -->
                <div class="modal fade" id="addNewCard2" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle">ระบุปีที่ต้องการ</h1>
                                <p class="text-center" id="addNewCardTitle title" >ข้อมูลการอัพโหลดบันทึกคุณภาพแยกรายบุลคล</p>

                                <!-- form -->
                                <form id="addNewCardValidation" class="row gy-1 gx-2 mt-75" onsubmit="return false">
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber">ปี</label>
                                        <div class="mb-1">
                                            <select id="year" name="year" class="form-select">
                                             <option value="${year }">${year }</option>
                                              <option value="${year1 }">${year1 }</option>
                                               <option value="${year2 }">${year2 }</option>

                                             </select>
                                            
                                            
                                            
                                   
                                        </div>
                                    </div>

                            



                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1" onclick="postform2()">นำออก</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                            Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->

<%@ include file="common/mainfoot.jspf" %>  
 

   <script>
   
   function postform1() {
	   var e = document.getElementById("month");
	   var value = e.options[e.selectedIndex].value;
	   var month = e.options[e.selectedIndex].text;
	  document.location.href = "/export/pdf/month/"+month;
   
   }
   
   function postform2() {
	   var e = document.getElementById("year");
	   var value = e.options[e.selectedIndex].value;
	   var year = e.options[e.selectedIndex].text;
	  document.location.href = "/export/pdf/year/"+year;
   
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
        

    


    </script>
</body>

    
