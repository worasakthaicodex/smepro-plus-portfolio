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
      
      
               <div class="container-xxl">
             
              
                        <div class="card-header no-bg bg-transparent d-flex align-items-center px-0 justify-content-between border-bottom flex-wrap">
                            <h3 class="h4 mb-0">Attendance</h3>
                            <div class="col-auto d-flex w-sm-100 mt-2 mt-sm-0">

                                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
                                        Filter
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item" href="/attendance-filemanager?month=01">01</a></li>
                                    <li><a class="dropdown-item" href="/attendance-filemanager?month=02">02</a></li>
                                    <li><a class="dropdown-item" href="/attendance-filemanager?month=03">03</a></li>
                                     <li><a class="dropdown-item" href="/attendance-filemanager?month=04">04</a></li>
                                   <li><a class="dropdown-item" href="/attendance-filemanager?month=05">05</a></li>
                                   <li><a class="dropdown-item" href="/attendance-filemanager?month=06">06</a></li>
                                  <li><a class="dropdown-item" href="/attendance-filemanager?month=07">07</a></li>
                                   <li><a class="dropdown-item" href="/attendance-filemanager?month=08">08</a></li>
                                        <li><a class="dropdown-item" href="/attendance-filemanager?month=09">09</a></li>
                                            <li><a class="dropdown-item" href="/attendance-filemanager?month=10">10</a></li>
                                              <li><a class="dropdown-item" href="/attendance-filemanager?month=11">12</a></li>
                                                   <li><a class="dropdown-item" href="/attendance-filemanager?month=12">12</a></li>
                                  </ul>
                            </div>
                        </div>
              
            
                <div class="row clearfix g-3">
                  <div class="col-sm-12">
                        <div class="card mb-3">
                            <div class="card-body">
                        
                                <div class="table-responsive">
                                    <table class="table table-hover align-middle mb-0" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th style="padding-right: 55px;">Employee</th>
                                                <th>1</th>
                                                <th>2</th>
                                                <th>3</th>
                                                <th>4</th>
                                                <th>5</th>
                                                <th>6</th>
                                                <th>7</th>
                                                <th>8</th>
                                                <th>9</th>
                                                <th>10</th>
                                                <th>11</th>
                                                <th>12</th>
                                                <th>13</th>
                                                <th>14</th>
                                                <th>15</th>
                                                <th>16</th>
                                                <th>17</th>
                                                <th>18</th>
                                                <th>19</th>
                                                <th>20</th>
                                                <th>21</th>
                                                <th>22</th>
                                                <th>23</th>
                                                <th>24</th>
                                                <th>25</th>
                                                <th>27</th>
                                                <th>28</th>
                                                <th>29</th>
                                                <th>30</th>
                                                <th>31</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                       
                                       
                                           <c:forEach items="${users }" var="row" varStatus="count">
                                            <tr>
                                            <td>
                                                <span class="fw-bold small">${row.user_name }</span>
                                            </td>
                                            <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                            </td>
                                            <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                            </td>
                                            <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                            </td>
                                            <td>
                                                        <i class="icofont-wall-clock text-warning"></i>
                                            </td>
                                            <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                            </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                        <i class="icofont-wall-clock text-warning"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                            </td>
                                            <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                            </td>
                                            <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                            </td>
                                            <td>
                                                        <i class="icofont-wall-clock text-warning"></i>
                                            </td>
                                            <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                            </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                <td>
                                                       <i data-feather='check-circle' class="text-success"></i>
                                                </td>
                                                
                                            </tr>
                                         </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                  </div>
                </div><!-- Row End -->
            </div>
  
      
     
      
       
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>






<%@ include file="common/mainfoot.jspf" %>  
 

    <script>
    
    var element = document.getElementById("usermanage5");
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


