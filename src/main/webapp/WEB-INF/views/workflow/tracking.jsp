<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    

       <link href="<c:url value="/resources/app-assets/css/pages/app-email.css" />" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>


<link href="<c:url value="/resources/app-assets/vendors/css/pickers/pickadate/pickadate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/pickers/flatpickr/flatpickr.min.css" />" rel="stylesheet">

<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-flat-pickr.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-pickadate.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-drag-drop.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
 

  <link href="<c:url value="/resources/app-assets/vendors/css/forms/wizard/bs-stepper.min.css" />" rel="stylesheet">
  
  
   <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/css/plugins/forms/form-wizard.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/app-assets/css/plugins/forms/form-validation.css" />" rel="stylesheet">


    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel='stylesheet' href='https://code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css'>
 <link href="<c:url value="/resources/treeview/style.css" />" rel="stylesheet">


 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>Tracking</title>


 <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        }

        body {
            background-color: #f5f5f5;
            padding: 20px;
        }

        .containers {
            max-width: 100%;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 12px;
            padding: 20px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        .section {
            margin-bottom: 40px;
        }

        .section h2 {
            font-size: 18px;
            color: #333;
            margin-bottom: 20px;
        }

        .report-grid {
            display: grid;
            gap: 20px;
        }

        /* Analysis of the Helpdesk */
        .analysis-grid {
            grid-template-columns: repeat(2, 1fr);
        }

        /* Customer Satisfaction */
        .customer-grid {
            grid-template-columns: repeat(2, 1fr);
        }

        /* Productivity */
        .productivity-grid {
            grid-template-columns: repeat(3, 1fr);
        }

        .report-card {
            background-color: #f0f7ff;
            border-radius: 12px;
            padding: 20px;
            display: flex;
            flex-direction: column;
            gap: 10px;
            transition: background-color 0.3s;
        }

        .report-card:hover {
            background-color: #e6f0ff;
        }

        .report-card .icon {
            font-size: 24px;
            color: #007bff;
        }

        /* Adjust icon color for specific cards */
        .report-card.orange-icon .icon {
            color: #ff6f61;
        }

        .report-card h3 {
            font-size: 16px;
            color: #333;
            font-weight: 600;
        }

        .report-card p {
            font-size: 14px;
            color: #666;
        }

        .report-card a {
            margin-top: auto;
            font-size: 14px;
            color: #007bff;
            text-decoration: none;
        }

        .report-card a:hover {
            text-decoration: underline;
        }
    </style>
    
    
    
        <style>
  @import url("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css");

.track_tbl td.track_dot {
    width: 50px;
    position: relative;
    padding: 0;
    text-align: center;
}

.track_tbl td.track_dot:after {
    content: "\f111";
    font-family: FontAwesome;
    position: absolute;
    margin-left: -5px;
    top: 11px;
}

.track_tbl td.track_dot span.track_line {
    background: #000;
    width: 3px;
    min-height: 50px;
    position: absolute;
    height: 101%;
}

.track_tbl tbody tr:first-child td.track_dot span.track_line {
    top: 30px;
    min-height: 25px;
}

.track_tbl tbody tr:last-child td.track_dot span.track_line {
    top: 0;
    min-height: 25px;
    height: 10%;
}

/* Style for success (completed) steps */
.track_tbl td.track_dot.Good {
    color: #4CAF50; /* Green for completed steps */
}

.track_tbl td.track_dot.Good span.track_line {
    background: #4CAF50; /* Green for the success line */
}

/* Style for normal (warning) steps */
.track_tbl td.track_dot.Normal {
    color: #FFC107; /* Yellow/Orange for warning steps, matching bg-warning */
}

.track_tbl td.track_dot.Normal span.track_line {
    background: #FFC107; /* Yellow/Orange for the warning line */
}

/* Style for bad (danger) steps */
.track_tbl td.track_dot.Bad {
    color: #DC3545; /* Red for danger steps, matching bg-danger */
}

.track_tbl td.track_dot.Bad span.track_line {
    background: #DC3545; /* Red for the danger line */
}

  </style>

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
            <div class="content-body" >
          <section id="multiple-column-form" >
          
     
     
       
    <div class="content-header row">
                <div class="content-header-left col-md-9 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-12">
                            <h2 class="content-header-title float-start mb-0">APP</h2>
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">${workflows.workflow_name}</a>
                                    </li>
                                              
                                </ol>
                                   
                          
                            </div>
                         
                        </div>
                    </div>
                </div>
           
                <div class="content-header-left col-md-3 col-12 mb-2">
                        <a href="/stepdetails2-workflow/${id}" class="btn btn-sm btn-light  waves-effect waves-float waves-light" style="float:right"> 
                            <spring:message code="btn.view"  /></a>
    
                             </div>
            </div> 

     

          
                         <div class="containers">
     
    <table class="table table-bordered track_tbl">
    <thead>
        <tr>
            <th>${workflows.tracking_type}</th>
            <th>S No</th>
            <th><spring:message code="workflows.DataLink" /></th>
            

          <c:forEach var="date" items="${last7Days}">
			                <th>${date}</th>
			</c:forEach>
		
        </tr>
    </thead>
    <tbody>
        <!-- Completed step with the success class -->
      <c:forEach items="${data}" var="row" varStatus="count">
    <tr class="active">
          
               <c:if test="${summary.tracking_type != 'Max'}">   
      
      
       <c:set var="firstStatusClass" value="" />
        <c:forEach var="summary" items="${row.statusSummary}" varStatus="counts">
     
                <c:choose>
                    <c:when test="${summary.mostFrequentStatus == 'Good'}"><c:set var="firstStatusClass" value="Good" /></c:when>
                    <c:when test="${summary.mostFrequentStatus == 'Normal'}"><c:set var="firstStatusClass" value="Normal" /></c:when>
                    <c:when test="${summary.mostFrequentStatus == 'Bad'}"><c:set var="firstStatusClass" value="Bad" /></c:when>
                       <c:when test="${summary.mostFrequentStatus == 'UNKNOWN'}"><c:set var="firstStatusClass" value="UNKNOWN" /></c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
        </c:forEach>
        
        
        </c:if>

      <c:if test="${summary.tracking_type == 'Max'}">   
            <c:set var="firstStatusClass" value="" />
             <!-- นับจำนวนแต่ละสถานะในแถวนี้ -->
        <c:set var="goodCount" value="0" />
        <c:set var="normalCount" value="0" />
        <c:set var="badCount" value="0" />
        <c:set var="unknownCount" value="0" />

        <c:forEach var="summary" items="${row.statusSummary}">
            <c:if test="${summary.mostFrequentStatus == 'Good'}">
                <c:set var="goodCount" value="${goodCount + 1}" />
            </c:if>
            <c:if test="${summary.mostFrequentStatus == 'Normal'}">
                <c:set var="normalCount" value="${normalCount + 1}" />
            </c:if>
            <c:if test="${summary.mostFrequentStatus == 'Bad'}">
                <c:set var="badCount" value="${badCount + 1}" />
            </c:if>
            <c:if test="${summary.mostFrequentStatus == 'UNKNOWN'}">
                <c:set var="unknownCount" value="${unknownCount + 1}" />
            </c:if>
           
        </c:forEach>

        <!-- หาสถานะที่มีจำนวนมากที่สุด -->
        <c:set var="mostFrequentStatusClass" value="UNKNOWN" />
        <c:set var="maxCount" value="${unknownCount}" />

        <!-- เปรียบเทียบ Good -->
        <c:if test="${goodCount > maxCount}">
            <c:set var="mostFrequentStatusClass" value="Good" />
            <c:set var="maxCount" value="${goodCount}" />
        </c:if>

        <!-- เปรียบเทียบ Normal -->
        <c:if test="${normalCount > maxCount}">
            <c:set var="mostFrequentStatusClass" value="Normal" />
            <c:set var="maxCount" value="${normalCount}" />
        </c:if>

        <!-- เปรียบเทียบ Bad -->
        <c:if test="${badCount > maxCount}">
            <c:set var="mostFrequentStatusClass" value="Bad" />
            <c:set var="maxCount" value="${badCount}" />
        </c:if>

        <!-- กรณีที่มีจำนวนเท่ากัน ให้เลือกตามลำดับความสำคัญ: Good > Normal > Bad > UNKNOWN -->
        <c:if test="${goodCount == maxCount && goodCount > 0}">
            <c:set var="firstStatusClass" value="Good" />
        </c:if>
        <c:if test="${normalCount == maxCount && normalCount > 0 && mostFrequentStatusClass != 'Good'}">
            <c:set var="firstStatusClass" value="Normal" />
        </c:if>
        <c:if test="${badCount == maxCount && badCount > 0 && mostFrequentStatusClass != 'Good' && mostFrequentStatusClass != 'Normal'}">
            <c:set var="firstStatusClass" value="Bad" />
        </c:if>
            </c:if>
            
        <td class="track_dot ${firstStatusClass} ">
            <span class="track_line"></span>
        </td>
        <td>${count.count}</td>
        <td>${row.name}</td>
        <c:forEach var="summary" items="${row.statusSummary}" varStatus="counts">
            <c:set var="statusClass" value="Good" />
          
            
            <td>

          
                <c:if test="${summary.mostFrequentStatus == 'Good'}">   
                    <c:set var="statusClass" value="Good" />
                    <span class="badge rounded-pill bg-success" style="width: 20px; height: 20px; display: inline-block; padding: 0;"> </span>  
                </c:if>    
                <c:if test="${summary.mostFrequentStatus == 'Normal'}">   
                    <span class="badge rounded-pill bg-warning" style="width: 20px; height: 20px; display: inline-block; padding: 0;"> </span>  
                    <c:set var="statusClass" value="Normal" />
                </c:if>    
                <c:if test="${summary.mostFrequentStatus == 'Bad'}">   
                    <span class="badge rounded-pill bg-danger" style="width: 20px; height: 20px; display: inline-block; padding: 0;"> </span>  
                    <c:set var="statusClass" value="Bad" />
                </c:if> 
                <c:if test="${summary.mostFrequentStatus == 'UNKNOWN'}">   
                    <span class="badge rounded-pill bg-secondary" style="width: 20px; height: 20px; display: inline-block; padding: 0;"> </span>  
                    <c:set var="firstStatusClass" value="UNKNOWN" />
                </c:if> 
                     <c:if test="${summary.mostFrequentStatus == 'UNKNOWN2'}">   
                    <span class="badge rounded-pill bg-light" style="width: 20px; height: 20px; display: inline-block; padding: 0;"> </span>  
               
                </c:if> 
         
            </td>
        </c:forEach>
    </tr>
</c:forEach>
		
        
  
    </tbody>
</table>
                  
                
    
       
    </div>
                  
                  
                  
                  
                  
                  
             
                        <!--end col-->
                    </div>           
                    
                    
                    
                    
                       
                   
            
          
          

                  
          
                 
                 
            
            
     
                </section>

            </div>
        </div>
 
 
  
 
 

 
 
 
    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>



 <div style="clear:both"></div>
    
    
    
<%@ include file="../common/mainfoot.jspf" %>  


	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>

 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>

 
    <script src="<c:url value="/resources/app-assets/js/scripts/tables/table-datatables-advanced.js" />"></script>
 
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/legacy.js" />"></script>
  <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.date.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>
 <script src="<c:url value="/resources/app-assets/js/scripts/forms/pickers/form-pickers.js" />"></script>



 <script src="<c:url value="/resources/app-assets/js/scripts/extensions/ext-component-drag-drop.js" />"></script>
<script src="<c:url value="/resources/app-assets/vendors/js/extensions/dragula.min.js" />"></script>
	  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	      <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>

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
</body>



  
	    <script>
	      $(window).on('load', function() {
	            if (feather) {
	                feather.replace({
	                    width: 14,
	                    height: 14
	                });
	            }
	        })
	        
    var element = document.getElementById("nav_workflowsystem");
    element.classList.add("active"); 
    
    
    
    
    
    
    
    
    
    function postdatacal(Mode,Mode2,Mode3) {

	    var formData = new FormData($("#"+ Mode2)[0]);

			    $.ajax({
			        url: Mode,
			        type: "POST",
			        data: formData,
			        processData: false,
			        contentType: false,
				    success: function(result){
				    $('.' + Mode3).html(result);
				       },
				    error: function(jqXHR, textStatus, errorThrown) {
				            if (jqXHR.status === 401) {
				                // Redirect to login page if unauthorized
				                window.location.href = '/login'; // URL ของหน้า login
				     }}
				    });
			

		
			   
	    return false;
	    }	
    
    
    
	   function getdata(url,id){
	        $.ajax({
	        type:'GET',
	        url:url,
	        data: {ID : id},
	        
	     success: function(data){
	    $("#mainkey").html(data);	

	     }
	});





return false;

}
    </script>				

 <script>
    
 
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
