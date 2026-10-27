<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../common/mainhead.jspf" %>    


<link href="<c:url value="/resources/app-assets/css/pages/app-todo.css" />" rel="stylesheet">
<title>File Pn</title>
<style>
.cardmain {
    box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.5);
}



:root {
  --bg:#ebf0f7;
  --header:#fbf4f6;
  --text:#2e2e2f;
  --white:#ffffff;
  --light-grey:#c4cad3;
  --tag-1:#ceecfd;
  --tag-1-text:#2e87ba;
  --tag-2:#d6ede2;
  --tag-2-text:#13854e;
  --tag-3:#ceecfd;
  --tag-3-text:#2d86ba;
  --tag-4:#f2dcf5;
  --tag-4-text:#a734ba;
  --purple:#7784ee;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}

body {
  color: var(--text);
}

.app {
 
  width: 100%;
  min-height: 100vh;
}

h1 {
  font-size: 30px;
}

.project {
  padding: 1rem;
  max-width: 75%;
  width: 100%;
  display: inline-block;
}
.project-info {
  padding: 2rem 0;
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
}
.project-participants {
  display: flex;
  align-items: center;
}
.project-participants span, .project-participants__add {
  width: 30px;
  height: 30px;
  display: inline-block;
  background: var(--purple);
  border-radius: 100rem;
  margin: 0 0.2rem;
}
.project-participants__add {
  background: transparent;
  border: 1px dashed #969696;
  font-size: 0;
  cursor: pointer;
  position: relative;
}
.project-participants__add:after {

  font-size: 15px;
  color: #969696;
}
.project-tasks {
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  width: 100%;
  grid-column-gap: 1.5rem;
}
.project-column-heading {
  margin-bottom: 1rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.project-column-heading__title {
  font-size: 20px;
}
.project-column-heading__options {
  background: transparent;
  color: var(--light-grey);
  font-size: 18px;
  border: 0;
  cursor: pointer;
}

.task {

  background-color: var(--white);
  padding: 1rem;
  border-radius: 8px;
  width: 100%;
  box-shadow: rgba(99, 99, 99, 0.1) 0px 2px 8px 0px;
  margin-bottom: 1rem;
  border: 3px dashed transparent;
}
.task:hover {
  box-shadow: rgba(99, 99, 99, 0.3) 0px 2px 8px 0px;
  border-color: rgba(162, 179, 207, 0.2) !important;
}
.task p {
  font-size: 15px;
  margin: 1.2rem 0;
}
.task__tag {
  border-radius: 100px;
  padding: 2px 13px;
  font-size: 12px;
}
.task__tag--copyright {
  color: var(--tag-4-text);
  background-color: var(--tag-4);
}
.task__tag--design {
  color: var(--tag-3-text);
  background-color: var(--tag-3);
}
.task__tag--illustration {
  color: var(--tag-2-text);
  background-color: var(--tag-2);
}
.task__tags {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.task__options {
  background: transparent;
  border: 0;
  color: var(--light-grey);
  font-size: 17px;
}
.task__stats {
  position: relative;
  width: 100%;
  color: var(--light-grey);
  font-size: 12px;
}
.task__stats span:not(:last-of-type) {
  margin-right: 1rem;
}
.task__stats svg {
  margin-right: 5px;
}
.task__owner {
  width: 25px;
  height: 25px;
  border-radius: 100rem;
  background: var(--purple);
  position: absolute;
  display: inline-block;
  right: 0;
  bottom: 0;
    font-size: 15px;
      text-align: center; /* Center the text horizontally */
  line-height: 25px; /* Center the text vertically */
}

.task-hover {
  border: 3px dashed var(--light-grey) !important;
}

.task-details {
  width: 24%;
  border-left: 1px solid #d9e0e9;
  display: inline-block;
  height: 100%;
  vertical-align: top;
  padding: 3rem 2rem;
}

.tag-progress {
  margin: 1.5rem 0;
}
.tag-progress h2 {
  font-size: 16px;
  margin-bottom: 1rem;
}
.tag-progress p {
  display: flex;
  width: 100%;
  justify-content: space-between;
}
.tag-progress p span {
  color: #b4b4b4;
}
.tag-progress .progress {
  width: 100%;
  -webkit-appearance: none;
  -moz-appearance: none;
       appearance: none;
  border: none;
  border-radius: 10px;
  height: 10px;
}
.tag-progress .progress::-webkit-progress-bar, .tag-progress .progress::-webkit-progress-value {
  border-radius: 10px;
}
.tag-progress .progress--copyright::-webkit-progress-bar {
  background-color: #ecd8e6;
}
.tag-progress .progress--copyright::-webkit-progress-value {
  background: #d459e8;
}
.tag-progress .progress--illustration::-webkit-progress-bar {
  background-color: #dee7e3;
}
.tag-progress .progress--illustration::-webkit-progress-value {
  background-color: #46bd84;
}
.tag-progress .progress--design::-webkit-progress-bar {
  background-color: #d8e7f4;
}
.tag-progress .progress--design::-webkit-progress-value {
  background-color: #08a0f7;
}

.task-activity h2 {
  font-size: 16px;
  margin-bottom: 1rem;
}
.task-activity li {
  list-style: none;
  margin: 1rem 0;
  padding: 0rem 1rem 1rem 3rem;
  position: relative;
}
.task-activity time {
  display: block;
  color: var(--light-grey);
}

.task-icon {
  width: 30px;
  height: 30px;
  border-radius: 100rem;
  position: absolute;
  top: 0;
  left: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}
.task-icon svg {
  font-size: 12px;
  color: var(--white);
}
.task-icon--attachment {
  background-color: #fba63c;
}
.task-icon--comment {
  background-color: #5dc983;
}
.task-icon--edit {
  background-color: #7784ee;
}

@media only screen and (max-width: 1300px) {
  .project {
    max-width: 100%;
  }

  .task-details {
    width: 100%;
    display: flex;
  }

  .tag-progress,
.task-activity {
    flex-basis: 50%;
    background: var(--white);
    padding: 1rem;
    border-radius: 8px;
    margin: 1rem;
  }
}
@media only screen and (max-width: 1000px) {
  .project-column:nth-child(2),
.project-column:nth-child(3) {
    display: none;
  }

  .project-tasks {
    grid-template-columns: 1fr 1fr;
  }
}
@media only screen and (max-width: 600px) {
  .project-column:nth-child(5) {
    display: none;
  }

  .project-tasks {
    grid-template-columns: 1fr;
  }

  .task-details {
    flex-wrap: wrap;
    padding: 3rem 1rem;
  }

  .tag-progress,
.task-activity {
    flex-basis: 100%;
  }

  h1 {
    font-size: 25px;
  }
}


</style>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>  






    <!-- BEGIN: Content-->
    <div class="app-content content todo-application">
    
    <div class="content-header row">
                <div class="content-header-left col-md-9 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-12">
                            
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                   <li class="breadcrumb-item">
                                   <c:if test='${process.process_logo != null}'>
                                   <img src=" /resources/app-assets/images/icons/APP/${process.process_logo}" style="width:25px;height:25px;"></img> 
                                   </c:if>
                                   <c:if test='${process.process_logo == null}'> 
                                   APP 
                                   </c:if> 
                                    </li>   
                                       <li class="breadcrumb-item">${flow.workflow_name}
                                    </li>   
                                   <li class="breadcrumb-item">${process.name} 
                                  
                                    </li>   
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
    
            </div>
    
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-area-wrapper container-xxl p-0">
            
            <div class="content-right" style="width:100%">
                <div class="content-wrapper container-xxl p-0">
                    <div class="content-header row">
                    </div>
                    <div class="content-body">
                        <div class="body-content-overlay"></div>
                        <div class="todo-app-list">
                            <!-- Todo search starts -->
                            <div class="app-fixed-search d-flex align-items-center">
                                <div class="sidebar-toggle d-block d-lg-none ms-1">
                                    <i data-feather="menu" class="font-medium-5"></i>
                                </div>
                                <div class="d-flex align-content-center justify-content-between w-100">
                                    <div class="input-group input-group-merge">
                                        <span class="input-group-text"><i data-feather="search" class="text-muted"></i></span>
                                        <input type="text" class="form-control files-filter2" id="todo-search" placeholder="Search task" aria-label="Search..." aria-describedby="todo-search" />
                                    </div>
                                </div>
                                <div class="dropdown"  style="display:none">>
                                    <a href="#" class="dropdown-toggle hide-arrow me-1" id="todoActions" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i data-feather="more-vertical" class="font-medium-2 text-body"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-end" aria-labelledby="todoActions">
                                        <a class="dropdown-item sort-asc" href="#">Sort A - Z</a>
                                        <a class="dropdown-item sort-desc" href="#">Sort Z - A</a>
                                        <a class="dropdown-item" href="#">Sort Assignee</a>
                                        <a class="dropdown-item" href="#">Sort Due Date</a>
                                        <a class="dropdown-item" href="#">Sort Today</a>
                                        <a class="dropdown-item" href="#">Sort 1 Week</a>
                                        <a class="dropdown-item" href="#">Sort 1 Month</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Todo search ends -->

                            <!-- Todo List starts -->
                            <div class="todo-task-list-wrapper list-group" style="height:740px;   overflow-y: scroll;">
                              
                              
                              
                     <ul class="todo-task-list media-list" id="todo-task-list1">
                                  
                              
                           <c:forEach items="${draft }" var="row" varStatus="count">    
			 					<%@ include file="sub/mark.jspf" %>  

                                    </c:forEach>    
                                <c:forEach items="${reviewed }" var="row" varStatus="count">    
                            
 								 <%@ include file="sub/mark.jspf" %>  
                                    </c:forEach>
                            
                                     <c:forEach items="${transaction_sends }" var="row" varStatus="count">        
						 <%@ include file="sub/mark2.jspf" %>       
                                         </c:forEach>
                              </ul>
                              
                        
                              
                              
                             
                         
           
                                
                                
                                
                          
                       
                                
                                
                                
                            
                                
                                
                                
                                <div class="no-results">
                                    <h5>No Items Found</h5>
                                </div>
                            </div>
                            <!-- Todo List ends -->
                        </div>


      






                        <!-- Right Sidebar starts -->
                        <div class="modal modal-slide-in sidebar-todo-modal fade" id="new-task-modal">
                            <div class="modal-dialog sidebar-lg" style="width:800px">
                                <div class="modal-content p-0" style="height: 100%;">
                                    <form id="form-modal-todo" class="todo-modal needs-validation" novalidate onsubmit="return false">
                                        <div class="modal-header align-items-center mb-1">
                                            <h5 class="modal-title" >Data</h5>
                                            <div class="todo-item-action d-flex align-items-center justify-content-between ms-auto">
                                              
                                                <i data-feather="x" class="cursor-pointer" data-bs-dismiss="modal" stroke-width="3"></i>
                                            </div>
                                        </div>
                                        <div class="modal-body flex-grow-1 pb-sm-0 pb-3"  style="height: 100%;">
                                    <div class="card" style="border:0px;height: 100%;" >
                                         <div class="todo-task-list-wrapper card-content list-group" id="wrappermain" style="height: 100%;">
                                 
                                 
                                 
                                 
                                       </div>
                                        </div>     </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- Right Sidebar ends -->

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END: Content-->
      
           
           


    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>


<div id="savestatus"></div>


<%@ include file="../common/mainfoot.jspf" %>  
 

    <script>
    


    function getdata(url,div){
    
			if(div == undefined){
				div ="savestatus";
			}
			
			let loadingSpinner = `
			    <div class="d-flex justify-content-center align-items-center" style="height: 100%;">
			        <div class="spinner-border text-primary" role="status">
			            <span class="visually-hidden">Loading...</span>
			        </div>
			    </div>`;

				$("#" + div).html(loadingSpinner);
			
    	
					        $.ajax({
					        type:'GET',
					        url:url,
					        data: {ID : 1},
					     success: function(data){
					   $("#"+div).html(data);	
					     },
					        error: function(jqXHR, textStatus, errorThrown) {
					            if (jqXHR.status === 401) {
					                // Redirect to login page if unauthorized
					                window.location.href = '/error'; // URL ของหน้า login
					            }      
					        }  
					        
					        
					        
					});
		
			 
		return false;
	    
		}
    
    
    

    
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
    	

    	
    }
    
	   function jobview(){
    	
		   
		   if(tabs == null){
			   tabs = "${jobviews.jobview_tab}";
		   }
		  
		  
		   
		   var url ="/pnsearch-filemanager";
		   
		   var selectedJobViews = []; 
		   var selectedJobViews2 = []; 
		    $("input[name='jobview_type']:checked").each(function() {
		        selectedJobViews.push($(this).val());
		    });
		   
		    $("input[name='jobview_category']:checked").each(function() {
		        selectedJobViews2.push($(this).val());
		    });
		    
		    
		    var selectedJobViewsString = selectedJobViews.join(",");
		    var selectedJobViewsString2 = selectedJobViews2.join(",");
					        $.ajax({
					        type:'GET',
					        url:url,
					        data: {tab : tabs,jobid : "${jobviews.jobview_id}",jobview_type: selectedJobViewsString,jobview_category: selectedJobViewsString2},
					        
					     success: function(data){
					    $("#todo-task-list").html(data);	
		
					     }
					    	});
			 
			
		
			 
		return false;
	    
		}
    
</script>
    
</body>

<script>
const items = document.querySelectorAll('.list-group-items');
const taskLists = [
    document.getElementById('todo-task-list1'),
    document.getElementById('todo-task-list2'),
    document.getElementById('todo-task-list3'),
    document.getElementById('todo-task-list4'),
    document.getElementById('todo-task-list5'),
    document.getElementById('todo-task-list6'),

];

// ฟังก์ชันเพื่อจัดการการคลิก
items.forEach((item, index) => {
    item.addEventListener('click', function(event) {
        event.preventDefault();

        // เอา active ออกจากทุกปุ่ม
        items.forEach(i => i.classList.remove('active'));

        // ใส่ active ให้ปุ่มที่ถูกคลิก
        this.classList.add('active');

        // ซ่อนทุก task list
        taskLists.forEach(list => list.style.display = 'none');

        // แสดง task list ที่เกี่ยวข้องกับปุ่มที่คลิก
        taskLists[index].style.display = 'block';
    });
});



function reject(id){
	$("#idreject").val(id);
}

function app(id){
	$("#idapp").val(id);
}
</script>

     <div class="modal fade" id="addNewCard" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="dar.check" /></h1>
                              

                                <!-- form -->
                                <form  action="/flowdoc-filemanager" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                
                                
                                
            
            <c:forEach items="${eformfields }"  var="row" varStatus="count">

            
                                 <c:set var="title" value="${row.field_label }" />   
                       <c:set var="appfield" value="${upload_pn.upload_pn_appfield }" /> 
                          <c:if test="${fn:contains(appfield,row.field_label)}"> 
             
                               
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${title }   </label>
                                                    
                                                 <c:if test='${row.field_type.equals("select") && !row.field_cal_type.equals("product") &&  !row.field_cal_type.equals("lot")  }'>     
                                           
                                                 <select name="data_value${count.count }"  id="data_values${row.field_id }" class="form-select">
                                                 </select>
                                                  </c:if>
                                                   <c:if test='${row.field_type.equals("text")}'>   
                                                 <input type="text" name="data_value${count.count }"  id="data_values${row.field_id }" class="autocomplete-input form-control">
                                                  </c:if>
                                                  
                                                  
                                          
                                                        <c:if test='${row.field_type.equals("number") }'>  
                                            					      <input type="number" name="data_values${count.count }"  id="data_value${row.field_id }" class="form-control">
                                                     </c:if>
                                             
        
                                                 
                                                 
                                                    <c:if test='${row.field_type.equals("textarea")}'>      
                                                 <textarea name="data_value${count.count }"  id="data_values${row.field_id }" class="form-control"></textarea>
                                                  </c:if>
                                              <c:if test='${row.field_type.equals("date")}'>      
                                               <input type="date" name="data_value${count.count }"  id="data_values${row.field_id }" class="form-control">
                                                  </c:if>
                                                  
                                                   <c:if test='${row.field_type.equals("time")}'>      
                                              <input type="time" class="form-control mydata"  name="data_value${count.count }" id="data_values${row.field_id }"  placeholder="hh:mm" >
                                                  </c:if> 
                                                  
                                                  
                                                               
                                               
                                                  
                                                    <c:if test='${row.field_type.equals("checkbox")}'>
                                                 <br>      
                                                <span id="data_value${row.field_id }"></span>
                                                    </c:if>
                                             
                                           <c:set var="totaltitle" value="${count.count }" />
                                            
                                            
                                            <c:if test='${totaltitle == null}'>  <c:set var="totaltitle" value="1" />  </c:if> 
                                            
                                    <input type="hidden" name="data_title${count.count }"  id="data_titles${row.field_id }"  value="${title }">     
                                   
                                   
                                    <input type="hidden" name="field_cal_type"  id="field_cal_type"  value="${row.field_cal_type }">         
                                     <input type="hidden" name="field_field_no"  id="field_field_no"  value="${row.field_field_no }">    
                                     
                                    </div>   
                                    
                                       </c:if> 
                            
                        </c:forEach>
                        
                        
                                                	<div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${title }   </label>
                                 						 <div>    
								                           <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
									                       <input type="radio" class="btn-check mydata" value="Good"  name="data_status" id="btnradio1" autocomplete="off" >
									                       <label class="btn btn-outline-success waves-effect" for="btnradio1">Good</label>
								
									                       <input type="radio" class="btn-check mydata" value="Normal"   name="data_status" id="btnradio2" autocomplete="off" checked>
									                       <label class="btn btn-outline-warning waves-effect" for="btnradio2">Normal</label>
								
									                       <input type="radio" class="btn-check mydata" value="Bad"  name="data_status" id="btnradio3" autocomplete="off">
									                       <label class="btn btn-outline-danger waves-effect" for="btnradio3">Bad</label>
									                       </div>
									                         </div>
                                    				</div>
                                    
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber"><spring:message code="dar.reason" /></label>
                                        <div class="input-group input-group-merge">
                                       
                                            <input id="modalAddCardNumber" name="note" class="form-control autocomplete-input3" type="text" />
                                        
                                            <span class="input-group-text cursor-pointer p-25" id="modalAddCard2">
                                                <span class="add-card-type"></span>
                                            </span>
                                        </div>
                                         
                                          <input type="hidden" name="id" id="idapp" value=""> 
                                           <input type="hidden" name="app"  value="app"> 
                                          
                                          <input type="hidden" name="Transition"  value="publish"> 
                                          
                                          
                                          
                                    </div>
                                    
                                    
                                    
                                    
                                    

                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1" >Save</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
<script>             


 var element = document.getElementById("app_${group}");
element.classList.add("active");                                                                 

	  $( function() {
		  
		  var newdata = 'เข้าตามเเผน,เข้าไม่ตามเเผน,ไม่ได้เข้า,เลื่อน'; // ข้อความเริ่มต้
		  var trimmedData = newdata.trim(); // ตัดช่องว่างที่อยู่ท้ายสตริง
		var dataItems = trimmedData.split(',');
	    $( ".autocomplete-input3" ).autocomplete({
	      source: dataItems
	    });
	  } );
	  
	  
	  
</script> 
                </div>
               


      <div class="modal fade" id="addNewCards" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="dar.uncheck" /></h1>
                              

                                <!-- form -->
                                <form    action="/flowdoc-filemanager" id="formaddNewCardValidation" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber"><spring:message code="dar.reason" /></label>
                                        <div class="input-group input-group-merge">
                                       
                                            <input id="modalAddCardNumber" name="note" class="form-control autocomplete-input2" type="text" />
                                              
                                            <span class="input-group-text cursor-pointer p-25" id="modalAddCard2">
                                                <span class="add-card-type">   </span>
                                            </span>
                                        </div>
                                        ไม่ได้เข้า,ข้อมูลไม่ถูกต้อง หรือ อื่นๆ
                                          <input type="hidden" name="id" id="idreject" value=""> 
                                                 <input type="hidden" name="app"  value="app"> 
                                                      <input type="hidden" name="Transition"  value="reject"> 
                                    </div>

                                   
                                

                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1"   data-bs-dismiss="modal" aria-label="Close">Save</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                                                 <script>

                                             
                                                		    
                                                		                                               
                                                 
                                                 
	  $( function() {
		  
		  var newdata = 'ไม่ได้เข้า,ข้อมูลไม่ถูกต้อง'; // ข้อความเริ่มต้
		  var trimmedData = newdata.trim(); // ตัดช่องว่างที่อยู่ท้ายสตริง
		var dataItems = trimmedData.split(',');
		  
	    $( ".autocomplete-input2" ).autocomplete({
	      source: dataItems
	    });
	  } );
</script>     
                </div>
                <!--/ add new card modal  -->



   <script>

                                                 function postdata(Mode, Mode2, Mode3) {

                                                	    var formData = new FormData($("#" + Mode2)[0]);

                                                	    $.ajax({
                                                	        url: Mode,
                                                	        type: "POST",
                                                	        data: formData,
                                                	        processData: false,
                                                	        contentType: false,
                                                	        success: function(result){
                                                	            $('#' + Mode3).html(result);
                                                	        },
                                                	        error: function(jqXHR, textStatus, errorThrown) {
                                                	                window.location.href = '/login'; // URL ของหน้า login
                                                	       
                                                	        }
                                                	    });

                                                	    $("#" + Mode2)[0].reset();
                                                	   
                                                	    return false;
                                                	}
                                                		    
        </script>                                                		      

  <script src="<c:url value="/resources/app-assets/vendors/js/scripts/pages/app-todo.js" />"></script>

 <%@ include file="../other/alert.jsp" %>

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