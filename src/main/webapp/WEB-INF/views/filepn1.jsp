<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="common/mainhead.jspf" %>    


<link href="<c:url value="/lib/css/main.css" />" rel="stylesheet">



<title>File Pn</title>
<style>
.ellipsis {
  display: inline-block;
  max-width: 120px;       /* ปรับตามความเหมาะสม */
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  vertical-align: middle;
}

.plan-close{
background-color:#ddd;

}


</style>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  




    <!-- BEGIN: Content-->
    <div class="app-content content todo-application" >
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        
        
        <div  style="width:85%;margin:auto">
       <div class="card bg-info-subtle shadow-none position-relative overflow-hidden mb-4">
            <div class="card-body px-4 py-3">
              <div class="row align-items-center">
                <div class="col-10">
                  <h4 class="fw-semibold mb-8">MyTask</h4>
                  <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item">
                        <a class="text-muted text-decoration-none" href="#">MyTask</a>
                      </li>
                      <li class="breadcrumb-item" aria-current="page">Upload</li>
                    </ol>
                  </nav>
                </div>
                <div class="col-2">
                  <div class="text-center mb-n5">
                    <img src="/resources/app-assets/images/banner/upgrade.png" alt="modernize-img" class="img-fluid mb-n4" style="width:170px">
                  </div>
                </div>
              </div>
            </div>
          </div>
        <ul class="nav nav-pills p-3 mb-3 rounded align-items-center card flex-row">
            <li class="nav-item">
              <a href="javascript:void(0)" class="nav-link gap-6 note-link d-flex align-items-center justify-content-center px-3 px-md-3 active" id="all-category">
            <i data-feather='package'></i>
                <span class="d-none d-md-block fw-medium"><spring:message code="task.all"/></span>
              </a>
            </li>
            <li class="nav-item">
              <a href="javascript:void(0)" class="nav-link gap-6 note-link d-flex align-items-center justify-content-center px-3 px-md-3" id="note-important">
              <i data-feather='calendar'></i>
                <span class="d-none d-md-block fw-medium"><spring:message code="task.due"/></span>
              </a>
            </li>
            <li class="nav-item">
              <a href="javascript:void(0)" class="nav-link gap-6 note-link d-flex align-items-center justify-content-center px-3 px-md-3" id="note-social">
              <i data-feather='tablet'></i>
                <span class="d-none d-md-block fw-medium"><spring:message code="task.notdue"/></span>
              </a>
            </li>
        
            <li class="nav-item ms-auto">
              <a href="/addtask-filemanager" class="btn btn-primary d-flex align-items-center px-3 gap-6" id="add-notes">
           <i data-feather='plus'></i>
                <span class="d-none d-md-block fw-medium fs-3"><spring:message code="task.addtask"/></span>
              </a>
            </li>
          </ul>
<div class="tab-content">
            <div id="note-full-container" class="note-has-grid row" >
            
            
            
                        
                             <c:forEach items="${workall }" var="row" varStatus="count">  
                             
                             <c:set var="note" value="note-social" />
                            <c:if test="${row.upload_pn_type == 0}">  
                            	   <c:set var="note" value="note-important" />
                            </c:if>
                               <c:if test="${row.upload_pn_type == 1}">  
                            	   <c:set var="note" value="note-social" />
                            </c:if>
                                
                  <c:if test="${row.upload_pn_end == 0 }">
                     <c:set var="type" value="plan-open" />
                  </c:if>
                   <c:if test="${row.upload_pn_end == 1 }">
                     <c:set var="type" value="plan-close" />
                  </c:if>
                  
                                     <div class="col-md-4 single-note-item ${note } all-category " style="font-family: 'Roboto', sans-serif !important;">
                       
                        <div class="card ${type}" stype="padding:0px">  
                        
                              <div class="dropdown chart-dropdown" style="text-align: right;padding:2px;height:5px;margin:0px">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-vertical font-medium-3 cursor-pointer" data-bs-toggle="dropdown"><circle cx="12" cy="12" r="1"></circle><circle cx="12" cy="5" r="1"></circle><circle cx="12" cy="19" r="1"></circle></svg>
                                        <div class="dropdown-menu dropdown-menu-end">
                                            <a class="dropdown-item" href="/editupload_pn-filemanager?id=${row.upload_pn_id}">Edit</a>
                                            <a class="dropdown-item"   href="/deletemainupload_pn-filemanager/${row.upload_pn_id}" onclick="return confirm('Are you sure delete ?')">Remove</a>
                                      
               <c:if test="${row.upload_pn_end == 0 }"><a href="/stateupload_pn-filemanager?ids=${row.upload_pn_id}&&state=1"  class="dropdown-item" > ปิด</a></c:if>		   
			<c:if test="${row.upload_pn_end == 1 }"><a href="/stateupload_pn-filemanager?ids=${row.upload_pn_id}&&state=0"  class="dropdown-item" >เปิด</a> </c:if>	
                                      
                                        </div>
                                    </div>    
              
                        <div class="card-body">               
                                 
                    
                  <span class="side-stick"></span>
                  <h6 class="note-title text-truncate w-75 mb-0" data-noteheading="Book a Ticket for Movie" style="font-size: 14px;">
                    ${row.upload_pn_note} </h6>
                  <p class="note-date fs-2">
                              <c:if test="${row.upload_pn_type == 0}">    
                                                             <spring:message code="due.type1" text="กำหนดตามช่วงเวลา"  />
                                                         </c:if>  
                                                     
                                                  
                                                       <c:if test="${row.upload_pn_type == 0}">        
                                                     
                                              
                                                         
                                                       <spring:message code="tables.due" text="ความถี่การอัพโหลด" />  :  <c:if test="${row.upload_pn_due == 0}">    
                                                             <spring:message code="due.date" text="ทุกวัน"  />
                                                         </c:if>  
                                                      <c:if test="${row.upload_pn_due == 1}">    
                                                 			 <spring:message code="due.week" text="ทุกอาทิตย์" /> 
                                                 			 
                                      
										<c:if test="${row.upload_pn_type != '0' }">    
                                            <c:if test='${row.getUpload_pn_setdue().equals("W-1") }'>    
                                             (MONDAY)
                                          	</c:if> 
                                          	  <c:if test='${row.getUpload_pn_setdue().equals("W-2") }'>   
                                             (TUESDAY)
                                          	</c:if> 
                                          
                                         <c:if test='${row.getUpload_pn_setdue().equals("W-3") }'>   
                                             (THURSDAY)
                                          	</c:if> 
                                          
                                          <c:if test='${row.getUpload_pn_setdue().equals("W-4") }'>     
                                             (WEDNESDAY)
                                          	</c:if> 
                                          
                                            <c:if test='${row.getUpload_pn_setdue().equals("W-5") }'>    
                                             (FRIDAY)
                                          	</c:if> 
                                          
                                            <c:if test='${row.getUpload_pn_setdue().equals("W-6") }'>    
                                             (SATURDAY)
                                          	</c:if> 
                                          
                                           <c:if test='${row.getUpload_pn_setdue().equals("W-7") }'>   
                                             (SUNDAY)
                                          	</c:if> 
                                      </c:if> 
                                          </c:if>
                         </c:if>  
                         &nbsp;
                  </p>
                  <div class="note-content">
                    <p class="note-inner-content" data-notecontent="Blandit tempus porttitor aasfs. Integer posuere erat a ante venenatis.">  ${row.project_info} </p>
                  </div>
                  <div class="d-flex align-items-center">
                  
 
 
  <c:if test='${row.upload_pn_alert > 0 }'> 
      

      
            <span class="badge rounded-pill badge-light-danger">
<i data-feather='alert-circle'></i> ${row.upload_pn_alert}
    </span>
      
      </c:if>
  <c:if test='${row.upload_pn_alert == 0 }'> 

      
            <span class="badge rounded-pill badge-light-success">
<i data-feather='check-circle'></i> 
    </span>
     </c:if>     
    <c:if test="${row.turn == 1 }">
            <span class="badge rounded-pill badge-light-primary">
			<i data-feather='check-circle'></i> TURN
    </span>
    </c:if>   
         
                    
             
                    <div class="ms-auto">
                      <div class="category-selector btn-group">
                    
                         <c:if test="${row.upload_pn_end == 0 }">
                      <div class="avatar bg-light-primary">
                                                         <a href="/duepn-filemanager?id=${row.upload_pn_id}&&step=0&&group=3" style="color:black">      <div class="avatar-content">+</div></a>
                                                    
                                                    
                                                </div>
                               </c:if>                 
                                                
                                                
                        <div class="dropdown-menu dropdown-menu-right category-menu">
                      
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
                </div>
             
                            
               
                                    </c:forEach>
                     
                                  
                   </div>                   
            
            
            
            
            
     
       </div>
          </div>
  
    </div>
    <!-- END: Content-->
      
           
           


    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>





<%@ include file="common/mainfoot.jspf" %>  
 
<script>
  document.addEventListener("DOMContentLoaded", () => {
    const filterLinks = document.querySelectorAll(".note-link");
    const allNotes = document.querySelectorAll(".single-note-item");

    filterLinks.forEach(link => {
      link.addEventListener("click", () => {
        filterLinks.forEach(l => l.classList.remove("active"));
        link.classList.add("active");

        const id = link.id; // เช่น 'note-important'

        allNotes.forEach(note => {
          if (id === "all-category") {
            note.style.display = "block";
          } else if (note.classList.contains(id)) {
            note.style.display = "block";
          } else {
            note.style.display = "none";
          }
        });
      });
    });
  });
</script>
    <script>
    
    
    

    
    $('.files-filter2').keyup(delay(function (e) {
    	
    	  
    	return search(this);
     
    return false;	   
    }, 300));

    
    
    function search() {

    	     var filter = $('.files-filter2').val();
    	      $('.todo-item').filter(function() {
  	            var regex = new RegExp(filter, 'ig');

  	            if (regex.test($(this).text())) {
  	                $(this).show();
  	            } else {
  	                $(this).hide();
  	            }
  	        });
    	  
        
    }
    
    
    
    
    
    
    
    
    function  switchs(){
    
    $("#switch1").toggle();
    $("#switch2").toggle();
}
 
    
    
    var element = document.getElementById("nav_dar5");
    element.classList.add("active"); 
    

    
    
    
    $('.normal').hide();
    
    
    function active (){
    	
   
    	if (document.getElementById('active').checked) {
    		  $('.normal').hide();
    		  $('.success').hide();
    		 
        } else {
            $('.normal').show();
            $('.success').show();
         
        }
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
    
    
    // เมื่อมีการเปลี่ยนแปลงใน <select>
    document.getElementById("mySelect").addEventListener("change", function() {
        var selectedValue = this.value;
        var baseUrl = "/pntask-filemanager?group=";
        var finalUrl = baseUrl + selectedValue;
        window.location.href = finalUrl;
    });
    
    
   
    


    var tabs = null;


    
    function setview(id){
    	
    	tabs = id;

    	$(".list-group-item-action").removeClass("active");
    	$("#item-"+id).addClass("active");
    	
    	
    	$("#todo-task-list1").hide();
    	$("#todo-task-list2").hide();
    	$("#todo-task-list3").hide();
    	$("#todo-task-list4").hide();
    	$("#todo-task-list5").hide();
    	
    	$("#todo-task-list"+id).show();
    	
    	
    }
    
	   function jobview(){
    	
		   
		   if(tabs == null){
			   tabs = "${jobviews.jobview_tab}";
		   }
		  
		  
		   
		   var url ="/pnsearch-filemanager";
		   
		   var selectedJobViews = []; 
		   var selectedJobViews2 = []; 
		   var selectedJobViews3 = []; 
		    $("input[name='jobview_type']:checked").each(function() {
		        selectedJobViews.push($(this).val());
		    });
		   
		    $("input[name='jobview_category']:checked").each(function() {
		        selectedJobViews2.push($(this).val());
		    });
			   
		    $("input[name='jobview_app']:checked").each(function() {
		        selectedJobViews3.push($(this).val());
		    });
		    
		    var selectedJobViewsString = selectedJobViews.join(",");
		    var selectedJobViewsString2 = selectedJobViews2.join(",");
		    var selectedJobViewsString3 = selectedJobViews3.join(",");
		    
					        $.ajax({
					        type:'GET',
					        url:url,
					        data: {tab : tabs,jobid : "${jobviews.jobview_id}",jobview_type: selectedJobViewsString
					        	,jobview_category: selectedJobViewsString2,jobview_app: selectedJobViewsString3},
					        
					     success: function(data){
					    $("#todo-task-list").html(data);	
		
					     }
					    	});
			 
			
		
			 
		return false;
	    
		}
    
</script>
    
</body>


