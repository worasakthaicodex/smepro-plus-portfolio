<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-drag-drop.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">

<title>Holidays</title>

<style>
.handle{
cursor: all-scroll;
}
</style>
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
                            
                              <form:form method="POST" modelAttribute="folders" action="/holiday-setting">
                                <div class="card-header">
                                    <h4 class="card-title" style="width:100%"><spring:message code="Holiday.Holidays" /> : ${holiday_year }
                                    
                                  
                                         <a  href="/copyholiday-setting/${holiday_year }" onclick="return confirm('<spring:message code="Holiday.implode" /> ?')" 
                                         class="btn  btn-info " style="float:right;margin-right:5px">
                                         <spring:message code="btn.implode" /></a>
                                         
                                          <button type="submit" class="btn  btn-success " style="float:right;margin-right:5px">
                                           <spring:message code="btn.save" /></button> 
                                         </h4>
                                   
                               
                                </div>
                                 <input type="hidden" name="holiday_year" value="${holiday_year }">
                                <div class="card-body">
                                <c:if test="${settings.holiday_notsun > 0 }">
                                <c:set var="check" value="checked" />
                                </c:if>
                     
                                
                                
                                  
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                  <c:if test="${folders.folder_id > 0}">    
                                 <input  type="hidden" name="folder_id" value="${folders.folder_id }">
                               </c:if>  
                  
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <h4 class="my-1"><spring:message code="Holiday.Publicholidays" />
                                              <a onclick="addpublic()" class="btn btn-sm btn-light " style="float:right"> <spring:message code="btn.add" /></a></h4>
                                          
                                            <ul class="list-group" id="handle-list-1" style="clear:both">
                                            
                                            <c:forEach items="${holiday1 }" var="row">
                                                <li class="list-group-item" ><span class="handle me-50">+</span>
                                                <spring:message code="common.date" /> :<input type="date" name="holiday_date" value="${row.holiday_date }" required>    <spring:message code="Holiday.name" />: <input type="text" name="holiday_name" value="${row.holiday_name }" required>
                                                <input type="hidden" name="holiday_type"   value="public" readonly required>
                                                 <a onclick="del(this)" class="btn btn-sm btn-danger " style="float:right"><i data-feather='trash-2'></i></a>
                                                </li>
                                             </c:forEach>
                                            </ul>
                                        </div>
                                        <div class="col-sm-6">
                                            <h4 class="my-1"><spring:message code="Holiday.Companyholidays" /><a onclick="addCompany()" class="btn btn-sm btn-light " style="float:right"><spring:message code="btn.add" /></a>
                                            <a data-bs-toggle="modal" data-bs-target="#large" modal" data-bs-target="#new-task-modal" "  class="btn btn-sm btn-light " style="float:right"><spring:message code="btn.addall" /></a></h4>
                                            <ul class="list-group" id="handle-list-2">
                                                   <c:forEach items="${holiday2 }" var="row">
                                                <li class="list-group-item" ><span class="handle me-50">+</span>
                                                   <spring:message code="common.date" /> :<input type="date" name="holiday_date" value="${row.holiday_date }" required>  <spring:message code="Holiday.name" />: <input type="text" name="holiday_name" value="${row.holiday_name }" required>
                                                <input type="hidden" name="holiday_type"   value="company" readonly required>
                                                 <a onclick="del(this)" class="btn btn-sm btn-danger " style="float:right"><i data-feather='trash-2'></i></a>
                                                </li>
                                             </c:forEach>
                                            </ul>
                                        </div>
                                             </div>

                                </div>
                            
                                </form:form>
                            
                            </div>
                        </div>
                    </div>
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->





 <div class="modal fade text-start" id="large" tabindex="-1" aria-labelledby="myModalLabel17" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered ">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel17">
                                                            <spring:message code="Holiday.saveall" />
                                                            </h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                          <input type="hidden"  id="dataset_node" value="">
                                                          
                                                          
                                                        <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="Holiday.Companyholidays" /></label>
                               <select class="form-select select2" id="day_all">
                                                         
                                      <option value="1"><spring:message code="Holiday.Monday" /></option>
                                      <option value="2"> <spring:message code="Holiday.Tuesday" /></option>
                                      <option value="3"><spring:message code="Holiday.Wednesday" /></option>
                                      <option value="4"><spring:message code="Holiday.Thursday" /></option>
                                      <option value="5"><spring:message code="Holiday.Friday" /></option>
                                      <option value="6"><spring:message code="Holiday.Saturday" /></option>
                                       <option value="7"><spring:message code="Holiday.Sunday" /></option>
                                                         </select>
                              
                                </div>     
                                
                                                         <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="Holiday.everyotherweek" /></label>
                               <select class="form-select select2" id="skip_all">
                                                         
                         
                                           <option value="0">NO</option>
                                            <option value="1" >Yes</option>
                                                         </select>
                              
                                </div>           
                                                      
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="addall()"><spring:message code="btn.add" /></button>
                                                        </div>
                                                    </div>
                                                </div>
      </div>


    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>





<%@ include file="common/mainfoot.jspf" %>  
  
    <script src="<c:url value="/resources/app-assets/vendors/js/extensions/dragula.min.js" />"></script>
     <script src="<c:url value="/resources/app-assets/js/scripts/extensions/ext-component-drag-drop.js" />"></script>
    
    <script>
    
    
    function getAllSaturdays(year,currentdates) {
        const result = [];

        // Create a date object for January 1st of the given year
        const startDate = new Date(year, 0, 1);

        // Iterate through the days of the year
        for (let i = 0; i < 365; i++) {
            const currentDate = new Date(startDate.getTime() + i * 24 * 60 * 60 * 1000);

            // Check if the current day is a Saturday (day index 6)
            if (currentDate.getDay() === currentdates ) {
            	
            	 var year = currentDate.getFullYear();
                 var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
                 var date = currentDate.getDate().toString().padStart(2, '0');
                
            	
            	
            	if(date > 0 && date < 32){
            		
            		var formattedDate = year+"-"+month+"-"+date;
                    result.push(formattedDate);
            	}
            
            }
        }

        return result;
    }

    
    
    function addall(){
    	
    	 var day_all =  parseInt(document.getElementById("day_all").value, 10); 
    	 var skip_all = document.getElementById("skip_all").value;


	    // Replace 2023 with the desired year
	    var saturdaysIn2023 = getAllSaturdays(2023,day_all);
	  
	    
	  
	    var i =1;
	    
	    
	    
	    // Print the result to the console
	    saturdaysIn2023.forEach(formattedMonday => {
	    	
	    	
	    	
	    	if(i == 1 && formattedMonday != ''){
	    	var div = `<li class="list-group-item"><span class="handle me-50">+</span>
	            วันที่ :<input type="date" name="holiday_date" value="`+formattedMonday+`" required> ชื่อวันหยุด: <input type="text" name="holiday_name" value="วันหยุดบริษัท" required><input type="hidden" name="holiday_type"  value="company" readonly>
	            <a onclick="del(this)" class="btn btn-sm btn-danger " style="float:right"><i data-feather='trash-2'></i></a>
	            </li> ` ; 
	    	
	            document.getElementById("handle-list-2").insertAdjacentHTML('beforeend', div);
	      	  
	      	  
	      	  
	      	  if (feather) {
	                feather.replace({
	                    width: 14,
	                    height: 14
	                });
	            }
	            
	            
	        console.log(formattedMonday);
	      
	        
			        if(skip_all == 1){
			        	  i =0;
				    }
	        
	    	}else{
	    		i =1;
	    		
	    	}
	        
	        
	    });
    }
    
    
    function addpublic(){
    	
    	var div = `<li class="list-group-item"><span class="handle me-50">+</span>
    		 <spring:message code="common.date" /> :<input type="date" name="holiday_date" required> <spring:message code="Holiday.name" />: <input type="text" name="holiday_name" required><input type="hidden" name="holiday_type"  value="public" readonly>
            <a onclick="del(this)" class="btn btn-sm btn-danger " style="float:right"><i data-feather='trash-2'></i></a>
            </li> ` ; 
    	
    	
    	  document.getElementById("handle-list-1").insertAdjacentHTML('beforeend', div);
    	  
    	  
    	  
    	  if (feather) {
              feather.replace({
                  width: 14,
                  height: 14
              });
          }
    }
    
    function addCompany(){
    	
    	var div = `<li class="list-group-item"><span class="handle me-50">+</span>
    		 <spring:message code="common.date" />:<input type="date" name="holiday_date">  <spring:message code="Holiday.name" />: <input type="text" name="holiday_name"><input type="hidden" name="holiday_type"  value="company" readonly>
            <a onclick="del(this)" class="btn btn-sm btn-danger " style="float:right"><i data-feather='trash-2'></i></a>
            </li> ` ; 
    	
    	
    	  document.getElementById("handle-list-2").insertAdjacentHTML('beforeend', div);
    	  
    	  
    	  
    	  if (feather) {
              feather.replace({
                  width: 14,
                  height: 14
              });
          }
    }
    
    
    
    function del(element){
    	  var listItem = element.closest('.list-group-item');
          listItem.remove();
    }
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
              var element = document.getElementById("nav_tool1");
  element.classList.add("active");  
  
  
  
    </script>
</body>


