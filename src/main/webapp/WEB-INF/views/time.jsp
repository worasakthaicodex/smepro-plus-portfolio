<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>File index</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">
      
      
      
       <div class="body d-flex py-3">
                <div class="container-xxl">
           
                    <div class="row g-3 mb-3 align-items-center">
                        <div class="col-lg-3 col-md-12">
                            <ul class="nav nav-tabs tab-body-header rounded" role="tablist">
                                <li class="nav-item" role="presentation"><a class="nav-link active" data-bs-toggle="tab" href="#summery-today" aria-selected="true" role="tab">Attendance</a></li>
                            
                            </ul>
                        </div>
                        <div class="col-lg-9 col-md-12">
                            <div class="row g-2">
                                <div class="col-12 col-md-6">
                                    <div class="input-group-lg">
                                        <input type="date" class="form-control">
                                    </div>
                                </div>
                                <div class="col-12 col-md-6">
                                    <div class="input-group-lg">  
                                        <select class="form-select" aria-label="Default select example">
                                            <option selected="">Select User</option>
                                            <option value="1">Grace</option>
                                            <option value="2">Gabrielle</option>
                                            <option value="3">Molly</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row g-3">
                        <div class="col-lg-12 col-md-12">
                            <div class="tab-content">
                                <div class="tab-pane fade active show" id="summery-today" role="tabpanel">
                                       <div class="row pb-3 row-deck">
                                        <div class="col-xl-12">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <div id="timetracker-table-two_wrapper" class="dataTables_wrapper dt-bootstrap5 no-footer"><div class="row"><div class="col-sm-12 col-md-6"><div class="dataTables_length" id="timetracker-table-two_length"><label>Show <select name="timetracker-table-two_length" aria-controls="timetracker-table-two" class="form-select form-select-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> entries</label></div></div><div class="col-sm-12 col-md-6"><div id="timetracker-table-two_filter" class="dataTables_filter"><label>Search:<input type="search" class="form-control form-control-sm" placeholder="" aria-controls="timetracker-table-two"></label></div></div></div><div class="row"><div class="col-sm-12"><table id="timetracker-table-two" class="table table-hover align-middle mb-0 nowrap dataTable no-footer dtr-inline" style="width: 100%;" role="grid" aria-describedby="timetracker-table-two_info">
                                                            <thead>
                                                                <tr role="row"><th class="sorting_asc" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-sort="ascending" aria-label="User: activate to sort column descending">User</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="User Name: activate to sort column ascending">User Name</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Time Worked: activate to sort column ascending">Time Worked</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Monady: activate to sort column ascending">Monady</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Tuesday: activate to sort column ascending">Tuesday</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Wednesday: activate to sort column ascending">Wednesday</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Thursday: activate to sort column ascending">Thursday</th><th class="dt-body-right sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Friday: activate to sort column ascending">Friday</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Saturday: activate to sort column ascending">Saturday</th><th class="dt-body-right sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Sunday: activate to sort column ascending">Sunday</th></tr>
                                                            </thead>
                                                            <tbody>
                                                                
                                                                
                                                         <c:forEach items="${users }" var="row" varStatus="count">          
                                                            <tr role="row" class="odd">
                                                                    <td tabindex="0" class="sorting_1">${row.group_level }</td>
                                                                    <td>
                                                                    <span>${row.user_name }</span></td>
                                                                    <td>55h 10m</td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 72%" aria-valuenow="72" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 76%" aria-valuenow="76" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 65%" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td class=" dt-body-right">
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 45%" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 0%" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td class=" dt-body-right">
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 0%" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>    
                                                                
                                                                
                                                           </tbody>
                                                        </table></div></div><div class="row"><div class="col-sm-12 col-md-5"><div class="dataTables_info" id="timetracker-table-two_info" role="status" aria-live="polite">Showing 1 to 3 of 3 entries</div></div><div class="col-sm-12 col-md-7"><div class="dataTables_paginate paging_simple_numbers" id="timetracker-table-two_paginate"><ul class="pagination"><li class="paginate_button page-item previous disabled" id="timetracker-table-two_previous"><a href="#" aria-controls="timetracker-table-two" data-dt-idx="0" tabindex="0" class="page-link">Previous</a></li><li class="paginate_button page-item active"><a href="#" aria-controls="timetracker-table-two" data-dt-idx="1" tabindex="0" class="page-link">1</a></li><li class="paginate_button page-item next disabled" id="timetracker-table-two_next"><a href="#" aria-controls="timetracker-table-two" data-dt-idx="2" tabindex="0" class="page-link">Next</a></li></ul></div></div></div></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> <!-- row end -->
                                </div>
                                <div class="tab-pane fade" id="summery-week" role="tabpanel">
                                    <div class="row pb-3 row-deck">
                                        <div class="col-xl-12">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <div id="timetracker-table-two_wrapper" class="dataTables_wrapper dt-bootstrap5 no-footer"><div class="row"><div class="col-sm-12 col-md-6"><div class="dataTables_length" id="timetracker-table-two_length"><label>Show <select name="timetracker-table-two_length" aria-controls="timetracker-table-two" class="form-select form-select-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> entries</label></div></div><div class="col-sm-12 col-md-6"><div id="timetracker-table-two_filter" class="dataTables_filter"><label>Search:<input type="search" class="form-control form-control-sm" placeholder="" aria-controls="timetracker-table-two"></label></div></div></div><div class="row"><div class="col-sm-12"><table id="timetracker-table-two" class="table table-hover align-middle mb-0 nowrap dataTable no-footer dtr-inline" style="width: 100%;" role="grid" aria-describedby="timetracker-table-two_info">
                                                            <thead>
                                                                <tr role="row"><th class="sorting_asc" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-sort="ascending" aria-label="User: activate to sort column descending">User</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="User Name: activate to sort column ascending">User Name</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Time Worked: activate to sort column ascending">Time Worked</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Monady: activate to sort column ascending">Monady</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Tuesday: activate to sort column ascending">Tuesday</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Wednesday: activate to sort column ascending">Wednesday</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Thursday: activate to sort column ascending">Thursday</th><th class="dt-body-right sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Friday: activate to sort column ascending">Friday</th><th class="sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Saturday: activate to sort column ascending">Saturday</th><th class="dt-body-right sorting" tabindex="0" aria-controls="timetracker-table-two" rowspan="1" colspan="1" style="width: 0px;" aria-label="Sunday: activate to sort column ascending">Sunday</th></tr>
                                                            </thead>
                                                            <tbody>
                                                                
                                                                
                                                                
                                                            <tr role="row" class="odd">
                                                                    <td tabindex="0" class="sorting_1">US-0001</td>
                                                                    <td><img src="assets/images/xs/avatar3.svg" class="avatar sm rounded-circle me-2" alt="profile-image"><span>Natalie Gibson </span></td>
                                                                    <td>55h 10m</td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 72%" aria-valuenow="72" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 76%" aria-valuenow="76" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 65%" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td class=" dt-body-right">
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 45%" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 0%" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td class=" dt-body-right">
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 0%" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                </tr><tr role="row" class="even">
                                                                    <td tabindex="0" class="sorting_1">US-0002</td>
                                                                    <td><img src="assets/images/xs/avatar6.svg" class="avatar sm rounded-circle me-2" alt="profile-image"><span>John Quinn </span></td>
                                                                    <td>60h 20m</td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 35%" aria-valuenow="35" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 96%" aria-valuenow="96" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 76%" aria-valuenow="76" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 80%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td class=" dt-body-right">
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 78%" aria-valuenow="78" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 0%" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td class=" dt-body-right">
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 0%" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                </tr><tr role="row" class="odd">
                                                                    <td tabindex="0" class="sorting_1">US-0003</td>
                                                                    <td><img src="assets/images/xs/avatar2.svg" class="avatar sm rounded-circle me-2" alt="profile-image"><span>Robert Young</span></td>
                                                                    <td>58h 30m</td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 55%" aria-valuenow="55" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 82%" aria-valuenow="82" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 86%" aria-valuenow="86" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td class=" dt-body-right">
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 55%" aria-valuenow="55" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 0%" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td class=" dt-body-right">
                                                                        <div class="progress">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 0%" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                </tr></tbody>
                                                        </table></div></div><div class="row"><div class="col-sm-12 col-md-5"><div class="dataTables_info" id="timetracker-table-two_info" role="status" aria-live="polite">Showing 1 to 3 of 3 entries</div></div><div class="col-sm-12 col-md-7"><div class="dataTables_paginate paging_simple_numbers" id="timetracker-table-two_paginate"><ul class="pagination"><li class="paginate_button page-item previous disabled" id="timetracker-table-two_previous"><a href="#" aria-controls="timetracker-table-two" data-dt-idx="0" tabindex="0" class="page-link">Previous</a></li><li class="paginate_button page-item active"><a href="#" aria-controls="timetracker-table-two" data-dt-idx="1" tabindex="0" class="page-link">1</a></li><li class="paginate_button page-item next disabled" id="timetracker-table-two_next"><a href="#" aria-controls="timetracker-table-two" data-dt-idx="2" tabindex="0" class="page-link">Next</a></li></ul></div></div></div></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> <!-- row end -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
      
     
      
       
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>






<%@ include file="common/mainfoot.jspf" %>  
 

    <script>
    
    var element = document.getElementById("nav_other6");
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
    
</body>


