<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    

  <link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
 



    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">



 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>Customer info</title>


 <style>
/* ปรับสีขอบของ select2 ให้เหมือน form-control */
.select2-container--default .select2-selection--single {
  border: 1px solid #ced4da; /* สีเดียวกับ form-control */
  border-radius: 0.375rem;   /* radius ของ Bootstrap 4/5 */
  height: calc(2.25rem + 2px); /* ให้สูงเท่า form-control */
  padding: 0.375rem 0.75rem; /* padding เดียวกัน */
  font-size: 1rem;
}
.select2-container--default .select2-selection--multiple {
  border: 1px solid #ced4da !important;  /* เปลี่ยนเป็นสีที่คุณต้องการ */
  border-radius: 4px;                /* เพิ่มมุมมนถ้าต้องการ */
}
/* ปรับตอน active/focus */
.select2-container--default .select2-selection--single:focus,
.select2-container--default .select2-selection--single.select2-selection--focus {
  border-color: #86b7fe; /* เหมือน form-control focus */
  outline: 0;
  box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25); /* เหมือน form-control */
}

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
        
        .select2-selection__arrow{
        display:none
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
                            <h2 class="content-header-title float-start mb-0">Reporting</h2>
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="/setting2-workflow/${workflows.workflow_id}">${workflows.workflow_name}</a>
                                    </li>
                                                <li class="breadcrumb-item"><a href="/monitoringapp-workflow/${workflows.workflow_id}">Reports</a>
                                    </li>
                                                <li class="breadcrumb-item"><a href="/viewcustomerreport-workflow/${workflows.workflow_id}/${id}">Custom report</a>
                                    </li>
                                    <li class="breadcrumb-item active">Setting Chart
                                    </li>
                                </ol>
                                   
                          
                            </div>
                         
                        </div>
                    </div>
                </div>
           
                <div class="content-header-left col-md-3 col-12 mb-2">
            
                         
           
                             </div>
            </div> 

     

          
                         <div >
                         
                         
                          <div >
                         
                         
            <div class="row ">
                        <div class="col-8">   
                        

      
                        
               <iframe src="/viewcustomerreport-workflow/${workflows.workflow_id }/${customreport.id}?iframe=1" title="description" 
               style="height:1000px;width:100%;" ></iframe>

                        </div>     
                           <div class="col-4">   
                           
                           <div class="card">
                         
                             
                            
                            <div class="card-body">
                        
                                <div class="business-items">
         
                        <form:form method="POST" modelAttribute="user" action="/editreportcustom2-workflow">
                                 
                                 
                                 <ul class="nav nav-tabs" role="tablist">
                                  
                                        <li class="nav-item">
                                            <a class="nav-link active" id="profile-tab" data-bs-toggle="tab" href="#profile" aria-controls="profile" role="tab" aria-selected="false">Graph data</a>
                                        </li>
 
                                        <li class="nav-item">
                                            <a class="nav-link" id="about-tab" data-bs-toggle="tab" href="#about" aria-controls="about" role="tab" aria-selected="false">Query</a>
                                        </li>
                                    
                                           <li class="nav-item">
                                            <a class="nav-link" id="about-tab" data-bs-toggle="tab" href="#pivot" aria-controls="pivot" role="tab" aria-selected="false">Pivot</a>
                                        </li>
                                              <li class="nav-item">
                                            <a class="nav-link" id="about-tab" data-bs-toggle="tab" href="#binning" aria-controls="binning" role="tab" aria-selected="false">Binning</a>
                                        </li>
                                    </ul>
                                 
                                 <div class="tab-content">
                                        <div class="tab-pane " id="home" aria-labelledby="home-tab" role="tabpanel">
                                                <div class="row">
                                                   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="report.name" /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                      <input type="hidden" class="form-control"  name="id" id="id" value="${customreport.id}">
                                <input type="text" class="form-control"  name="report_name" id="report_name" value="${customreport.report_name}">
                                                    </div>
                                                </div>
                                            </div>
                                                  
                                        <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="report.description" /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                              <input type="text"  name="report_description" id="report_description"  value="${customreport.report_description}"  class="form-control">
                                                    </div>
                                                </div>
                                            </div>     
                                        
                                                 <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="report.table" /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                 <select class="form-select select2"  name="report_table" id="report_table" >
								                               <option value="0">-</option>
								                               <c:forEach items="${data }" var="row" varStatus="count">
								                                        <option value="${row.id}" <c:if test="${customreport.report_table == row.id  }">
								                                        selected</c:if>>${row.name }</option>
								                                 </c:forEach>   
								                                        </select> 
                                                    </div>
                                                </div>
                                            </div>   
                                            
                                                       <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="workflows.publication" /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                         <select class="form-select" id="status" name="status" >
                                 
                                         <option value="0"  <c:if test="${customerreport.status == '0' }">selected</c:if>><spring:message code="workflows.private" /></option>
                                         <option value="1"   <c:if test="${customerreport.status == '1' }">selected</c:if>><spring:message code="workflows.public" /></option>
                                     </select>
                               
                                             
                                             
                                                    </div>
                                                </div>
                                            </div>
                                                </div>
                                        </div>
                                        <div class="tab-pane active" id="profile" aria-labelledby="profile-tab" role="tabpanel">
                                           <div class="row">
                                     
                                     
                                     
                          <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.type"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                    
                                     <select class="form-select" id="type" name="type" >
                                     
                                            <option value="0"  <c:if test="${customreport.type == 0 }">selected</c:if>>-</option>
                                            
                                            
         									<option value="1"  <c:if test="${customreport.type ==1 }">selected</c:if>>Pie</option>
                                            <option value="2"  <c:if test="${customreport.type == 2 }">selected</c:if>>Bar Chart</option>
                                            <option value="3"  <c:if test="${customreport.type ==3 }">selected</c:if>>Line Chart</option>
                                            <option value="4"  <c:if test="${customreport.type ==4 }">selected</c:if>>Funnel</option>
                                            <option value="5"  <c:if test="${customreport.type == 5 }">selected</c:if>>Bubble</option>
                                           <option value="6"  <c:if test="${customreport.type == 6 }">selected</c:if>>Horizontal Bar</option>
                                      		 <option value="7"  <c:if test="${customreport.type == 7 }">selected</c:if>>Area charts</option>
                      					      <option value="8"  <c:if test="${customreport.type == 8 }">selected</c:if>>Stacked Bar</option>
                             				 <option value="9"  <c:if test="${customreport.type == 9 }">selected</c:if>> Bell Shape</option>
                                            <option value="10"  <c:if test="${customreport.type == 10 }">selected</c:if>>  Combo bar/line (Total)</option>
                    						 <option value="11"  <c:if test="${customreport.type == 11 }">selected</c:if>> Line Chart (Date)</option>
                    						 <option value="12"  <c:if test="${customreport.type == 12 }">selected</c:if>> Bar Chart (Count)</option>
                                     </select>
                                 
                                   
                                                    </div>
                                                </div>
                             </div> 
                             
                             
                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.theme"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                    
                                     <select class="form-select" id="theme" name="theme" >
                                     
                                            <option value="vibrant"  <c:if test="${customreport.theme == 'vibrant' }">selected</c:if>>Vibrant</option>
                                             <option value="VibrantFade"  <c:if test="${customreport.theme == 'VibrantFade' }">selected</c:if>>Vibrant Fade</option>
                                            
         									<option value="pastel"  <c:if test="${customreport.theme =='pastel' }">selected</c:if>>Pastel</option>
                                            <option value="blue"  <c:if test="${customreport.theme == 'blue' }">selected</c:if>>Blue</option>
                                            <option value="onlyblue"  <c:if test="${customreport.theme =='onlyblue' }">selected</c:if>>Only Blue</option>
                                            <option value="green"  <c:if test="${customreport.theme =='green' }">selected</c:if>>Green</option>
                                            <option value="neonMinimal"  <c:if test="${customreport.theme == 'neonMinimal' }">selected</c:if>>Neon Minimal</option>
                                           <option value="retroFuture"  <c:if test="${customreport.theme == 'retroFuture' }">selected</c:if>>Retro Future</option>
                                      		 <option value="monoChic"  <c:if test="${customreport.theme == 'monoChic' }">selected</c:if>>Mono Chic</option>
                      					      <option value="cosmicGlow"  <c:if test="${customreport.theme == 'cosmicGlow' }">selected</c:if>>Cosmic Glow</option>
                             				 <option value="zenVibes"  <c:if test="${customreport.theme == 'zenVibes' }">selected</c:if>>Zen Vibes</option>
                             				 <option value="CyberZen"  <c:if test="${customreport.theme == 'CyberZen' }">selected</c:if>>Cyber Zen</option>
                             				
               
                                     </select>
                                 
                                   
                                   
                                  
                                   
                                                    </div>  
                                                </div>
                             </div> 
     
  
                                      
                                      
                                           	  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.level"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                       
                                     <select class="form-select select2" id="level" name="level"  >
                                     
                      
         									<c:forEach items="${eformfields }"   var="row" varStatus="count">
                                         	<c:if test="${row.keymap != null }">
                                            <option value="${count.count }"  <c:if test="${customreport_level.contains(count.count.toString())  }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                            <c:if test="${row.keymap == null }">
                                              <c:set var="datavalue" value="data_value${count.count }"/>      
                                            <option value="${count.count }"  <c:if test="${customreport_level.contains(count.count.toString())  }">selected</c:if>>${row.field_label }</option>
                                            </c:if>

 										<c:set var="last" value="${count.count }"/>   
                                            </c:forEach>
                                        
                                            <c:if test="${eformfields != null  }">
                                              <c:set var="last" value="${last +1 }"/>   
                                                 <option value="${last}" <c:if test="${customreport_level.contains(last.toString())  }">selected</c:if>>จำนวนนับ</option>
                                               <c:set var="fieldIndex" value="${last + 1}" />
											<c:set var="labels" value="Year,Target,Month 1,Month 2,Month 3,Month 4,Month 5,Month 6,Month 7,Month 8,Month 9,Month 10,Month 11
											,Month 12,Summarize,Variance,Std. Dev.,RSD,title,Binning,Frequency" />
										
											<c:forEach var="label" items="${fn:split(labels, ',')}">
											    <option value="${fieldIndex}" <c:if test="${customreport_level.contains(fieldIndex.toString())  }">selected</c:if>>${label} </option>
											    <c:set var="fieldIndex" value="${fieldIndex + 1}" />
											</c:forEach>

											</c:if>


      											 <c:forEach items="${fieldsToPrint }"   var="row" varStatus="count">
                                             				 <option value="${count.count }"  <c:if test="${customreport_level.contains(count.count.toString())  }">selected</c:if>>${row.title }</option>
                                            				  </c:forEach>

                                     </select>
                                     
                                     
                                                    </div>
                                                </div>
                                            </div> 
     							
  							
	                                        <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"> <spring:message code="report.binning_value"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                            <c:set var="axis_y" value="${customreport.axis_y},0"/>                
                                                    
                                                    
                                     <select class="form-select select2" id="axis_y" name="axis_y" multiple="multiple" >
         									<c:forEach items="${eformfields }"   var="row" varStatus="count">
                                            <option value="${count.count }" 
                                                   <c:forEach var="item" items="${fn:split(axis_y, ',')}">
       											 <c:if test="${item == count.count.toString()}">selected</c:if>
   											 </c:forEach>
                                             >${row.field_label }</option>
                                             
                                               <c:set var="last" value="${count.count }"/>                
                                            </c:forEach>
                                            
                                     <c:if test="${eformfields != null }">       
                                              <c:set var="last" value="${last +1 }"/>   
                                                 <option value="${last}" 
                                                   <c:forEach var="item" items="${fn:split(axis_y, ',')}">
       											 <c:if test="${item == last.toString()}">selected</c:if>
   											 </c:forEach>
                                             >จำนวนนับ</option>
                                             
                                         <c:set var="fieldIndex" value="${last + 1}" />
											<c:set var="labels" value="Year,Target,Month 1,Month 2,Month 3,Month 4,Month 5,Month 6,Month 7,Month 8,Month 9,Month 10,Month 11,Month 12
											,Summarize,Variance,Std. Dev.,RSD,title,Binning,Frequency" />
											<c:forEach var="label" items="${fn:split(labels, ',')}">
											    <option value="${fieldIndex}"
											        <c:forEach var="item" items="${fn:split(axis_y, ',')}">
											            <c:if test="${item == fieldIndex.toString()}">selected</c:if>
											        </c:forEach>
											    >${label}</option>
											    <c:set var="fieldIndex" value="${fieldIndex + 1}" />
											</c:forEach>
									</c:if>		
											
											<c:forEach items="${fieldsToPrint }"   var="row" varStatus="count">
											
											      <option value="${count.count }" 
											         <c:forEach var="item" items="${fn:split(axis_y, ',')}">
       											 <c:if test="${item == count.count.toString()}">selected</c:if>
   											 </c:forEach>
                                             >${row.title }</option>
                                             				
                                            </c:forEach>
											
                                     </select>
                                     
                                     
                                     
           
                                     
                                     
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                            	  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">            <spring:message code="report.binning_date"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                              <c:set var="axis_x" value="${customreport.axis_x},0"/>        
                                                    
                                     <select class="form-select select2" id="axis_x" name="axis_x" multiple="multiple">
         
         									<c:forEach items="${eformfields }"   var="row" varStatus="count">
                                            <option value="${count.count }" 
                                            <c:forEach var="item" items="${fn:split(axis_x, ',')}">
       											 <c:if test="${item == count.count.toString()}">selected</c:if>
   											 </c:forEach>
                                            >${row.field_label }</option>
                                               <c:set var="last" value="${count.count }"/>          
                                                    </c:forEach>
                                      
                                           <c:if test="${eformfields != null }">               
                                              <c:set var="last" value="${last +1 }"/>   
                                                 <option value="${last}" 
                                                   <c:forEach var="item" items="${fn:split(axis_x, ',')}">
       											 <c:if test="${item == last.toString()}">selected</c:if>
   											 </c:forEach>
                                             >จำนวนนับ</option>
                                               <c:set var="fieldIndex" value="${last + 1}" />
											<c:set var="labels" value="Year,Target,Month 1,Month 2,Month 3,Month 4,Month 5,Month 6,Month 7,Month 8,Month 9,Month 10,Month 11,Month 12
											,Summarize,Variance,Std. Dev.,RSD,title,Binning,Frequency" />
											<c:forEach var="label" items="${fn:split(labels, ',')}">
											    <option value="${fieldIndex}"
											        <c:forEach var="item" items="${fn:split(axis_x, ',')}">
											            <c:if test="${item == fieldIndex.toString()}">selected</c:if>
											        </c:forEach>
											    >${label}</option>
											    <c:set var="fieldIndex" value="${fieldIndex + 1}" />
											</c:forEach>
                                            
                                            </c:if>
                                            
                                            
                                    		<c:forEach items="${fieldsToPrint }"   var="row" varStatus="count">
											
											      <option value="${count.count }" 
											         <c:forEach var="item" items="${fn:split(axis_x, ',')}">
       											 <c:if test="${item == count.count.toString()}">selected</c:if>
   											 </c:forEach>
                                             >${row.title }</option>
                                             				
                                            </c:forEach>
                                    
                                    
                                     </select>
                                     
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                            
                                            
                                             </div> 
                                        </div>

                                           <div class="tab-pane" id="pivot" aria-labelledby="pivot-tab" role="tabpanel">
                                   
                                            
                                            	  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.filter"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                     <select class="form-select select2" id="filter" name="filter" multiple="multiple" >
                
         									<c:forEach items="${eformfields }"   var="row" varStatus="count">
         									<c:if test="${row.keymap != null }">
                                            <option value="${row.keymap.mainkey }"  <c:if test="${customreport.filter.contains(row.keymap.mainkey)  }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                            <c:if test="${row.keymap == null }">
                                              <c:set var="datavalue" value="data_value${count.count }"/>      
                                            <option value="data_value${count.count }"  <c:if test="${customreport.filter.contains(datavalue)  }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                            </c:forEach>
                                            
                                            	<c:forEach items="${fieldsToPrint }"   var="row" varStatus="count">
                                              <option value="${row.key}"  <c:if test="${customreport.filter.contains(row.key)  }">selected</c:if>>${row.title }</option>
                                              </c:forEach>
                                     </select>
                                     
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                            
                                               <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.datas"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                               <c:set var="filter_data" value="${customreport.filter_data},0"/>       
                                           <select class="form-select select2" id="filter_data" name="filter_data" multiple="multiple" >
                                            
                                            	<c:forEach items="${eformfields }"   var="row" varStatus="count">
                                            <option value="${count.count }" 
                                            <c:forEach var="item" items="${fn:split(filter_data, ',')}">
       											 <c:if test="${item == count.count.toString()}">selected</c:if>
   											 </c:forEach>
                                            >${row.field_label }</option>
                                            </c:forEach>
                                            
                                                  	<c:forEach items="${fieldsToPrint }"   var="row" varStatus="count">
                                              <option value="${row.key}"  <c:if test="${customreport.filter_data.contains(row.key)  }">selected</c:if>>${row.title }</option>
                                              </c:forEach>
                                     </select>
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                              <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.analysis"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                     <select class="form-select" id="analysis" name="analysis" >
                                     	 <option value="-"   <c:if test="${customreport.analysis == '-' }">selected</c:if>>-</option>
                                    	 <option value="Sum"   <c:if test="${customreport.analysis == 'Sum' }">selected</c:if>>Sum</option>
                                    <option value="Average"   <c:if test="${customreport.analysis == 'Average' }">selected</c:if>>Average</option>
                                     <option value="Median"   <c:if test="${customreport.analysis == 'Median' }">selected</c:if>>Median</option>
                                      <option value="Mode"   <c:if test="${customreport.analysis == 'Mode' }">selected</c:if>>Mode</option>
                                       <option value="Maximum"   <c:if test="${customreport.analysis == 'Maximum' }">selected</c:if>>Maximum</option>
                                        <option value="Minimum"   <c:if test="${customreport.analysis == 'Minimum' }">selected</c:if>>Minimum</option>
                                         <option value="Count"   <c:if test="${customreport.analysis == 'Count' }">selected</c:if>>Count</option>    
                                     </select>
                                     
                                     
                                                    </div>
                                                </div>
                                            </div> 
                            
                             
                       
                                            
                                                      <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="report.combine" /> </label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                         <select class="form-select" id="date_group" name="date_group" >
                                 
                                         <option value="-"  <c:if test="${customreport.date_group == '-' }">selected</c:if>>-</option>
                                         <option value="DAY"   <c:if test="${customreport.date_group == 'DAY' }">selected</c:if>>DAY</option>
                                          <option value="MONTH"   <c:if test="${customreport.date_group == 'MONTH' }">selected</c:if>>MONTH</option>
                                           <option value="YEAR"   <c:if test="${customreport.date_group == 'YEAR' }">selected</c:if>>YEAR</option>
                                     </select>
                               
                                             
                                             
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            
                                            
                                            	  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.date_group"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                    
                                     <select class="form-select select2" id="filter_date" name="filter_date"  >
                                              <option value="-"  <c:if test="${customreport.filter_date == '-' }">selected</c:if>>-</option>
                                     
         									<c:forEach items="${eformfields }"   var="row" varStatus="count">
                                          	<c:if test="${row.keymap != null }">
                                            <option value="${row.keymap.mainkey }"  <c:if test="${customreport.filter_date == row.keymap.mainkey  }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                            <c:if test="${row.keymap == null }">
                                              <c:set var="datavalue" value="data_value${count.count }"/>      
                                            <option value="data_value${count.count }"  <c:if test="${customreport.filter_date == datavalue     }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                            
                                            
                                              
                                            </c:forEach>
                                                   	<c:forEach items="${fieldsToPrint }"   var="row" varStatus="count">
                                              <option value="${row.key}"  <c:if test="${customreport.filter_date.contains(row.key)  }">selected</c:if>>${row.title }</option>
                                              </c:forEach>
                                     </select>
                                     
                                     
                                                    </div>
                                                </div>
                                            </div> 

										</div>


                                      <div class="tab-pane" id="binning" aria-labelledby="binning-tab" role="tabpanel">
			                                  
			                                                      
			                                             <div class="col-12">
			                                                <div class="mb-1 row">
			                                                    <div class="col-sm-4">
			                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.binning"  /></label>
			                                                    </div>
			                                                    <div class="col-sm-8">
			                      
			                                  <select class="form-select" id="binning" name="binning"  >
			                                  			<option value="0" <c:if test="${customreport.binning == 0  }">selected</c:if>><spring:message code="report.Disabled"  /></option>
			                                            <option value="1" <c:if test="${customreport.binning == 1  }">selected</c:if>>Aggregate</option>
			                                            <option value="2" <c:if test="${customreport.binning == 2  }">selected</c:if>>Binning</option>
			                                  </select>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                  
			                                  
			                                           <div class="col-12">
			                                                <div class="mb-1 row">
			                                                    <div class="col-sm-4">
			                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.binning_date"  /></label>
			                                                    </div>
			                                                    <div class="col-sm-8">
			               
			                                                 <select class="form-select" id="binning_date" name="binning_date"  >
			                                                 	 <option value="-"   <c:if test="${customreport.binning_date == '-' }">selected</c:if>>-</option>
			                                               			  <c:forEach items="${eformfields }"   var="row" varStatus="count">
						                                            	<c:if test="${row.keymap != null }">
						                                            <option value="${row.keymap.mainkey }"  <c:if test="${customreport.binning_date == row.keymap.mainkey  }">selected</c:if>>${row.field_label }</option>
						                                            </c:if>
						                                            <c:if test="${row.keymap == null }">
						                                              <c:set var="datavalue" value="data_value${count.count }"/>      
						                                            <option value="data_value${count.count }"  <c:if test="${customreport.binning_date == datavalue     }">selected</c:if>>${row.field_label }</option>
						                                            </c:if>
						                                            </c:forEach>
						                                            
						                                            
						                                     <c:forEach items="${fieldsToPrint }"   var="row" varStatus="count">
                                             				 <option value="${row.key}"  <c:if test="${customreport.binning_date.contains(row.key)  }">selected</c:if>>${row.title }</option>
                                            				  </c:forEach>
			                                                </select>
			                                  
			                                                    </div>
			                                           
			                                                </div>
			                                            </div>
			                                  

			                                       <div class="col-12">
			                                                <div class="mb-1 row">
			                                                    <div class="col-sm-4">
			                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.binning_value"  /></label>
			                                                    </div>
			                                                    <div class="col-sm-8">
			               
			                                                 <select class="form-select" id="binning_value" name="binning_value"  >
			                                                  	 <option value="-"   <c:if test="${customreport.binning_value == '-' }">selected</c:if>>-</option>
			                                               			  <c:forEach items="${eformfields }"   var="row" varStatus="count">
						                                            	<c:if test="${row.keymap != null }">
						                                            <option value="${row.keymap.mainkey }"  <c:if test="${customreport.binning_value == row.keymap.mainkey  }">selected</c:if>>${row.field_label }</option>
						                                            </c:if>
						                                            <c:if test="${row.keymap == null }">
						                                              <c:set var="datavalue" value="data_value${count.count }"/>      
						                                            <option value="data_value${count.count }"  <c:if test="${customreport.binning_value == datavalue     }">selected</c:if>>${row.field_label }</option>
						                                            </c:if>
						                                            </c:forEach>
						                                                       <c:forEach items="${fieldsToPrint }"   var="row" varStatus="count">
                                             				 <option value="${row.key}"  <c:if test="${customreport.binning_value.contains(row.key)  }">selected</c:if>>${row.title }</option>
                                            				  </c:forEach>
			                                                </select>
			                                  
			                                                    </div>
			                                                </div>
			                                            </div>
			                                            
			                                      <div class="col-12">
			                                                <div class="mb-1 row">
			                                                    <div class="col-sm-4">
			                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.binning_group"  /></label>
			                                                    </div>
			                                                    <div class="col-sm-8">
			               
			                                                 <select class="form-select select2" id="binning_group" name="binning_group" multiple="multiple" >
			                                                      	 <option value="-"   <c:if test="${customreport.binning_group == '-' }">selected</c:if>>-</option>
			                                               			  <c:forEach items="${eformfields }"   var="row" varStatus="count">
						                                            	<c:if test="${row.keymap != null }">
						                                            <option value="${row.keymap.mainkey }"  <c:if test="${customreport.binning_group == row.keymap.mainkey  }">selected</c:if>>${row.field_label }</option>
						                                            </c:if>
						                                            <c:if test="${row.keymap == null }">
						                                              <c:set var="datavalue" value="data_value${count.count }"/>      
						                                            <option value="data_value${count.count }"  <c:if test="${customreport.binning_group == datavalue     }">selected</c:if>>${row.field_label }</option>
						                                            </c:if>
						                                            </c:forEach>
						                                            
						                                     <c:forEach items="${fieldsToPrint }"   var="row" varStatus="count">
                                             				 <option value="${row.key}"  <c:if test="${customreport.binning_group.contains(row.key)  }">selected</c:if>>${row.title }</option>
                                            				  </c:forEach>
						                                            
			                                                </select>
			                                  
			                                                    </div>
			                                                </div>
			                                      </div>
			                                         
			                                             
			                                         <hr>
			                                     <u>   Aggregate</u>
			                                       <div class="col-12">
			                                                <div class="mb-1 row">
			                                                    <div class="col-sm-4">
			                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.binning_operation"  /></label>
			                                                    </div>
			                                                    <div class="col-sm-8">
			               
								                         <select class="form-select" id="binning_operation" name="binning_operation" >
								                                     	 <option value="-"   <c:if test="${customreport.binning_operation == '-' }">selected</c:if>>-</option>
								                                    	 <option value="Sum"   <c:if test="${customreport.binning_operation == 'Sum' }">selected</c:if>>Sum</option>
								                                    <option value="Average"   <c:if test="${customreport.binning_operation == 'Average' }">selected</c:if>>Average</option>
								                                     <option value="Median"   <c:if test="${customreport.binning_operation == 'Median' }">selected</c:if>>Median</option>
								                                      <option value="Mode"   <c:if test="${customreport.binning_operation == 'Mode' }">selected</c:if>>Mode</option>
								                                       <option value="Maximum"   <c:if test="${customreport.binning_operation == 'Maximum' }">selected</c:if>>Maximum</option>
								                                        <option value="Minimum"   <c:if test="${customreport.binning_operation == 'Minimum' }">selected</c:if>>Minimum</option>
								                                     
					                                     </select>
                                     
			                                  
			                                                    </div>
			                                                </div>
			                                            </div>
			                                  
			                                  
			                                  
			                                         <hr>
			                                                   <u>   Binning</u>
			                                            <div class="col-12">
			                                                <div class="mb-1 row">
			                                                    <div class="col-sm-4">
			                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.binning_title"  /></label>
			                                                    </div>
			                                                    <div class="col-sm-8">
			               
			                                                 <select class="form-select" id="binning_title" name="binning_title" multiple="multiple" >
			                                            </select>
			                                  
			                                                    </div>
			                                                </div>
			                                            </div>
			                                           <div class="col-12">
			                                                <div class="mb-1 row">
			                                                    <div class="col-sm-4">
			                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.binning_range"  /></label>
			                                                    </div>
			                                                    <div class="col-sm-8">
			                      
			                                           <select class="form-select" id="binning_range" name="binning_range" multiple="multiple" >
			                                            </select>
			                                     
			                                                    </div>
			                                                </div>
			                                            </div> 
			                                  
			                                  
			                                 
			                                  
			              </div>
                                            
                                            
                                            
                                            
                                         	        <div class="tab-pane" id="about" aria-labelledby="about-tab" role="tabpanel">
                                        
                                        
                                        <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.condition"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                        				 <div class="form-check form-check-primary">
                                            <input type="radio" id="customColorRadio1" name="condition_choice1" class="form-check-input"  value="-"  <c:if test="${customreport.condition_choice1 == '-' }">checked</c:if>>
                                            <label class="form-check-label" for="customColorRadio1"><spring:message code="report.none"  /></label>
                                        </div>
                                     	 <div class="form-check form-check-primary">
                                            <input type="radio" id="customColorRadio1" name="condition_choice1" class="form-check-input" value="AND"  <c:if test="${customreport.condition_choice1 == 'AND' }">checked</c:if>>
                                            <label class="form-check-label" for="customColorRadio1"><spring:message code="report.and"  /></label>
                                        </div>
                                    
                                                    </div>
                                                </div>
                                            </div> 
                                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.column"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                      
                                           <select class="form-select select2" id="filter_column1" name="filter_column1" multiple="multiple" >
                                            	<c:forEach items="${eformfields }"   var="row" varStatus="count">
                                            	<c:if test="${row.keymap != null }">
                                            <option value="${row.keymap.mainkey }"  <c:if test="${customreport.filter_column1 == row.keymap.mainkey  }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                            <c:if test="${row.keymap == null }">
                                              <c:set var="datavalue" value="data_value${count.count }"/>      
                                            <option value="data_value${count.count }"  <c:if test="${customreport.filter_column1 == datavalue     }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                            
                                            
                                    
                                                  </c:forEach>
                                         <c:forEach items="${fields }"   var="row" varStatus="count">
                                             	<c:if test="${row.keymap != null }">
                                            <option value="${row.keymap.mainkey }"  <c:if test="${customreport.filter_column1 == row.keymap.mainkey  }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                         </c:forEach>            
                                                  
                                     </select>
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                      <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.operation"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                                            <select class="form-select" id="operation1" name="operation1" >
                                 
                                         <option value="="  <c:if test="${customreport.operation1 == '=' }">selected</c:if>>=</option>
                                         <option value=">"   <c:if test="${customreport.operation1 == '>' }">selected</c:if>>></option>
                                          <option value="<"   <c:if test="${customreport.operation1 == '<' }">selected</c:if>><</option>
                                           <option value=">="   <c:if test="${customreport.operation1 == '>=' }">selected</c:if>>>=</option>
                                             <option value="<="   <c:if test="${customreport.operation1 == '<=' }">selected</c:if>><=</option>
                                                  <option value="IS NULL"   <c:if test="${customreport.operation1 == 'IS NULL' }">selected</c:if>>IS NULL</option>
                                                    <option value="IS NOT NULL"   <c:if test="${customreport.operation1 == 'IS NOT NULL' }">selected</c:if>>IS NOT NULL</option>
                                          <option value="NOT BLANK"   <c:if test="${customreport.operation1 == 'NOT BLANK' }">selected</c:if>>NOT BLANK</option>
                                      <option value="BLANK"   <c:if test="${customreport.operation1 == 'BLANK' }">selected</c:if>>BLANK</option>
                                                  <option value="FIND_IN_SET"   <c:if test="${customreport.operation1 == 'FIND_IN_SET' }">selected</c:if>>FIND_IN_SET</option>
                                     </select>
                                     
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            
                                            
                                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.value"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                           <input type="text" name="condition_value1" class="form-control" value="${customreport.condition_value1}">
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                            <HR>
                                    
                                      <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.condition"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                        				 <div class="form-check form-check-primary">
                                            <input type="radio" id="customColorRadio1" name="condition_choice2" class="form-check-input"  value="-"  <c:if test="${customreport.condition_choice2 == '-' }">checked</c:if>>
                                            <label class="form-check-label" for="customColorRadio1"><spring:message code="report.none"  /></label>
                                        </div>
                                     	 <div class="form-check form-check-primary">
                                            <input type="radio" id="customColorRadio1" name="condition_choice2" class="form-check-input" value="AND"  <c:if test="${customreport.condition_choice2 == 'AND' }">checked</c:if>>
                                            <label class="form-check-label" for="customColorRadio1"><spring:message code="report.and"  /></label>
                                        </div>
                                        	 <div class="form-check form-check-primary">
                                            <input type="radio" id="customColorRadio1" name="condition_choice2" class="form-check-input" value="OR"   <c:if test="${customreport.condition_choice2 == 'OR' }">checked</c:if>>
                                            <label class="form-check-label" for="customColorRadio1"><spring:message code="report.or"  /></label>
                                        </div>
                                                    </div>
                                                </div>
                                            </div> 
                                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.column"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                                  
                                           <select class="form-select select2" id="filter_column2" name="filter_column2" multiple="multiple" >
                                            
                                            	<c:forEach items="${eformfields }"   var="row" varStatus="count">
                                           	<c:if test="${row.keymap != null }">
                                            <option value="${row.keymap.mainkey }"  <c:if test="${customreport.filter_column2 == row.keymap.mainkey  }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                            <c:if test="${row.keymap == null }">
                                              <c:set var="datavalue" value="data_value${count.count }"/>      
                                            <option value="data_value${count.count }"  <c:if test="${customreport.filter_column2 == datavalue     }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                                  </c:forEach>
                                                  
                                          <c:forEach items="${fields }"   var="row" varStatus="count">
                                             	<c:if test="${row.keymap != null }">
                                            <option value="${row.keymap.mainkey }"  <c:if test="${customreport.filter_column2 == row.keymap.mainkey  }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                         </c:forEach>   
                                     </select>
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                                                    <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.operation"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                                            <select class="form-select" id="operation2" name="operation2" >
                                 
                                         <option value="="  <c:if test="${customreport.operation2 == '=' }">selected</c:if>>=</option>
                                         <option value=">"   <c:if test="${customreport.operation2 == '>' }">selected</c:if>>></option>
                                          <option value="<"   <c:if test="${customreport.operation2 == '<' }">selected</c:if>><</option>
                                           <option value=">="   <c:if test="${customreport.operation2 == '>=' }">selected</c:if>>>=</option>
                                             <option value="<="   <c:if test="${customreport.operation2 == '<=' }">selected</c:if>><=</option>
                                                  <option value="IS NULL"   <c:if test="${customreport.operation2 == 'IS NULL' }">selected</c:if>>IS NULL</option>
                                                    <option value="IS NOT NULL"   <c:if test="${customreport.operation2 == 'IS NOT NULL' }">selected</c:if>>IS NOT NULL</option>
                                          <option value="NOT BLANK"   <c:if test="${customreport.operation2 == 'NOT BLANK' }">selected</c:if>>NOT BLANK</option>
                                      <option value="BLANK"   <c:if test="${customreport.operation2 == 'BLANK' }">selected</c:if>>BLANK</option>
                                                  <option value="FIND_IN_SET"   <c:if test="${customreport.operation2 == 'FIND_IN_SET' }">selected</c:if>>FIND_IN_SET</option>
                                     </select>
                                     
                                                    </div>
                                                </div>
                                            </div>
                                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.value"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                           <input type="text" name="condition_value2" class="form-control" value="${customreport.condition_value2}">
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                 
                                 
                                   
<hr>
  
 	 						
      				      
      			
                                            
                                               <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.condition"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                        				 <div class="form-check form-check-primary">
                                            <input type="radio" id="customColorRadio3" name="condition_choice3" class="form-check-input"  value="-"  <c:if test="${customreport.condition_choice3 == '-' }">checked</c:if>>
                                            <label class="form-check-label" for="customColorRadio3"><spring:message code="report.none"  /></label>
                                        </div>
                                     	 <div class="form-check form-check-primary">
                                            <input type="radio" id="customColorRadio3" name="condition_choice3" class="form-check-input" value="AND"  <c:if test="${customreport.condition_choice3 == 'AND' }">checked</c:if>>
                                            <label class="form-check-label" for="customColorRadio3"><spring:message code="report.and"  /></label>
                                        </div>
                                        	 <div class="form-check form-check-primary">
                                            <input type="radio" id="customColorRadio3" name="condition_choice3" class="form-check-input" value="OR"   <c:if test="${customreport.condition_choice3 == 'OR' }">checked</c:if>>
                                            <label class="form-check-label" for="customColorRadio3"><spring:message code="report.or"  /></label>
                                        </div>
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                            
                                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.column"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                                
                                           <select class="form-select select2" id="filter_column3" name="filter_column3" multiple="multiple" >
                                            
                                            	<c:forEach items="${eformfields }"   var="row" varStatus="count">
                                           	<c:if test="${row.keymap != null }">
                                            <option value="${row.keymap.mainkey }"  <c:if test="${customreport.filter_column3 == row.keymap.mainkey  }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                            <c:if test="${row.keymap == null }">
                                              <c:set var="datavalue" value="data_value${count.count }"/>      
                                            <option value="data_value${count.count }"  <c:if test="${customreport.filter_column3 == datavalue     }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                            </c:forEach>
                                            
                                         <c:forEach items="${fields }"   var="row" varStatus="count">
                                             	<c:if test="${row.keymap != null }">
                                            <option value="${row.keymap.mainkey }"  <c:if test="${customreport.filter_column3 == row.keymap.mainkey  }">selected</c:if>>${row.field_label }</option>
                                            </c:if>
                                         </c:forEach>   
                                     </select>
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                                                    <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.operation"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                                            <select class="form-select" id="operation3" name="operation3" >
                                 
                                         <option value="="  <c:if test="${customreport.operation3 == '=' }">selected</c:if>>=</option>
                                         <option value=">"   <c:if test="${customreport.operation3 == '>' }">selected</c:if>>></option>
                                          <option value="<"   <c:if test="${customreport.operation3 == '<' }">selected</c:if>><</option>
                                           <option value=">="   <c:if test="${customreport.operation3 == '>=' }">selected</c:if>>>=</option>
                                             <option value="<="   <c:if test="${customreport.operation3 == '<=' }">selected</c:if>><=</option>
                                                  <option value="IS NULL"   <c:if test="${customreport.operation3 == 'IS NULL' }">selected</c:if>>IS NULL</option>
                                                    <option value="IS NOT NULL"   <c:if test="${customreport.operation3 == 'IS NOT NULL' }">selected</c:if>>IS NOT NULL</option>
                                          <option value="NOT BLANK"   <c:if test="${customreport.operation3 == 'NOT BLANK' }">selected</c:if>>NOT BLANK</option>
                                      <option value="BLANK"   <c:if test="${customreport.operation3 == 'BLANK' }">selected</c:if>>BLANK</option>
                                                  <option value="FIND_IN_SET"   <c:if test="${customreport.operation3 == 'FIND_IN_SET' }">selected</c:if>>FIND_IN_SET</option>
                                     </select>
                                     
                                                    </div>
                                                </div>
                                            </div>
                                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.value"  /></label>
                                                    </div>
                                                    <div class="col-sm-8">
                           <input type="text" name="condition_value3" class="form-control" value="${customreport.condition_value3}">
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
      				                          </div>
                      </div>
                              
                                                     
                                            
                                      
                                            
                             
                 
                                 
                                     
                                            <div class="col-sm-9 offset-sm-3">
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden" class="form-select"  name="report_app" value="${workflows.workflow_id }"> 
                          
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"><spring:message code="btn.save" /></button>
                                          
                                            </div>
                  
                      
                                        
                                        
                        
                                         
                                          
                                    </form:form>
                             
                                     
                                </div>
                                
                          
                                
                            </div>
                      
                            
                        </div>       
    
       
                                </div> 
            </div>     
            
                 
      
    
       
    </div>
                       
                         
                         
                         

    </div>
                  
                   <script>
  $(document).ready(function() {
	  
	  // สมมุติว่าได้ค่าจาก server มาแบบนี้
			const fileTagString2 = "${customreport.binning_range}"; // เช่น "data1,data2, ,data3"
		
		// แปลงเป็น array และกรองค่าว่างออก
		const tags2 = fileTagString2
		  .split(",")
		  .map(tag => tag.trim())
		  .filter(tag => tag.length > 0); // กรอง tag ที่ว่างเปล่าออก
		
		const select = document.getElementById("binning_range");
		
		// เพิ่ม option และทำให้ถูกเลือกไว้
		tags2.forEach(tag => {
		  const option = document.createElement("option");
		  option.value = tag;
		  option.text = tag;
		  option.selected = true;
		  select.appendChild(option);
		});
	  
    $('#binning_range').select2({
      tags: true, // เปิดฟังก์ชันการพิมพ์ tag ใหม่
      placeholder: "พิมพ์ tag ที่ต้องการ", // ข้อความแนะนำเมื่อไม่มีการเลือก
      allowClear: true // เพิ่มปุ่มเคลียร์
    });
  });
</script>    
                         
               <script>
  $(document).ready(function() {
	  
	  // สมมุติว่าได้ค่าจาก server มาแบบนี้
			const fileTagString = "${customreport.binning_title}"; // เช่น "data1,data2, ,data3"
		
		// แปลงเป็น array และกรองค่าว่างออก
		const tags = fileTagString
		  .split(",")
		  .map(tag => tag.trim())
		  .filter(tag => tag.length > 0); // กรอง tag ที่ว่างเปล่าออก
		
		const select = document.getElementById("binning_title");
		
		// เพิ่ม option และทำให้ถูกเลือกไว้
		tags.forEach(tag => {
		  const option = document.createElement("option");
		  option.value = tag;
		  option.text = tag;
		  option.selected = true;
		  select.appendChild(option);
		});
	  
    $('#binning_title').select2({
      tags: true, // เปิดฟังก์ชันการพิมพ์ tag ใหม่
      placeholder: "พิมพ์ tag ที่ต้องการ", // ข้อความแนะนำเมื่อไม่มีการเลือก
      allowClear: true // เพิ่มปุ่มเคลียร์
    });
  });
</script>    
                  
                  
                  
             
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
