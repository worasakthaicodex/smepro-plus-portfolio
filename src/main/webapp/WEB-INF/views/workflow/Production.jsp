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
@media print {
  .page {
    page-break-before: always;
  }
  .page:first-child {
    page-break-before: auto;
  }
}

.card{
  border:0px;
}


  th {
    -webkit-print-color-adjust: exact;
    print-color-adjust: exact; /* สำหรับ browser อื่นที่รองรับ */
  }
</style>

<style>

 
 table {
    border-collapse:collapse;
    margin: 0;
  width: 100%;
   border: 1px solid #dddddd;
      font-size:12px;
   
}


  
td {
    text-align: center;
   border: 1px solid #dddddd;
      max-width: 150px;
     font-size:12px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */

         min-width: 5px;
    padding:3px;
  
}
  td:first-child {
    text-align: left;
  }





th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
     font-size:12px;
     padding:3px;
     max-width: 150px;
       text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
         min-width: 5px;
}





  </style>
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
                            <h2 class="content-header-title float-start mb-0">Traceability</h2>
       
                        </div>
                    </div>
                    
                </div>
           
                <div class="content-header-left col-md-3 col-12 mb-2">
  
                   
                            
                                 <button  id="Print" class="btn btn-sm btn-light  waves-effect waves-float waves-light" style="float:right;background-color:#fff">  
                            Print
                             </button>

                      
           
                             </div>
            </div> 

     

          
                         <div >
                         
                         
                          <div >
                         
                         
            <div class="row " >
                        <div class="col-9" id="print_body">   
            <c:if test="${search_status !=  1 }">
             <spring:message code="maindata.provide"  />...
                   </c:if>

                
                   <c:if test="${search_status ==  1 }">
           
                 <div class="card">
                         
                             
                            
                            <div class="card-body">
                        
            
            
          
               <p style="width:48%;float:left"><b><spring:message code="maindata.product"  />: </b>${product.product_code } ${product.product }</p>   
               

                <p style="width:48%;float:left"><b><spring:message code="product.order_quantity"  />: </b>${product.order_quantity } </p>
                     <p style="width:48%;float:left"><b><spring:message code="product.customer_name"  />: </b>${product.customer }</p>
  
            </div>
        </div>


     
        <c:forEach items="${trace}" var="row" varStatus="count">    
        <!-- Production Details -->
      <div class="card  <c:if test="${count.count > 1 }">page</c:if>">
  
              <div class="card-body">
                                  
          <h4 class="card-title"><strong>${row.trace_name }</strong></h4>
  
       
                <table   style="width:99%">
                 <tr>
                    <thead>
                     <c:forEach items="${row.key2}" var="key2" varStatus="count">                  
                         <th><spring:message code="${key2}"  /></th>
                    </c:forEach>
                     <th ><spring:message code="data.Attached"  /></th>
                     </tr>
                     
                    </thead>
                    <tbody>
                   
        		<c:set var="lastTitle" value="" />
                  <c:forEach items="${row.data}" var="rows" varStatus="counts">    
                        <tr>
			<c:forEach items="${row.key}" var="key" varStatus="status">
				    <c:choose>
				        <c:when test="${status.index == 0}">
				            <!-- คอลัมน์แรก: title -->
				            <c:set var="currentTitle" value="${rows[key]}" />
				            <c:choose>
				                <c:when test="${pageScope.lastTitle == null or currentTitle != pageScope.lastTitle}">
	
				                    <c:set var="lastTitle" value="${currentTitle}" scope="page" />
				                    
				                   <td> ${currentTitle}</td>
				                </c:when>
				                <c:otherwise>
				                    <td></td>
				                </c:otherwise>
				            </c:choose>
				        </c:when>
				        <c:otherwise>
				            <!-- คอลัมน์อื่นๆ -->
				            <td>${rows[key]}</td>
				        </c:otherwise>
				    </c:choose>
				</c:forEach>
                  
                  
                  
               
                         	  <td >
                          	            <c:forEach items="${rows.file}" var="file"   varStatus="count">
                         
                       
								<a href="/render-filemanager?file=${rows.data_id }&&count=${count.count}&&file_edata=${file.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">
									       ${file.file_name_original }
								</a>
								<br>
                                   </c:forEach>     
                          	      </td>
                        </tr>
                  </c:forEach>
                  
                  
                    </tbody>
                </table>
                
                
                <br>
          
                
            </div>
        </div>
  
     
          </c:forEach>     
     
   
    
             
           </c:if>
                
                

                        </div>     
                           <div class="col-3">   
                           
                           <div class="card">
                         
                             
                            
                            <div class="card-body">
                        
                                <div class="business-items">
         
                        <form:form method="POST" modelAttribute="user" action="/newTraceability-workflow">
     
                 <input  type="hidden" name="search_id" value="${id}">     
                                 
                                 <ul class="nav nav-tabs" role="tablist">
                                  
                                        <li class="nav-item">
                                            <a class="nav-link active" id="profile-tab" data-bs-toggle="tab" href="#profile" aria-controls="profile" role="tab" aria-selected="false">Search</a>
                                        </li>
 
                                        <li class="nav-item">
                                            <a class="nav-link" id="about-tab" data-bs-toggle="tab" href="#about" aria-controls="about" role="tab" aria-selected="false">Setup</a>
                                        </li>
                                    
                                    
                                    </ul>
                                 
                                 <div class="tab-content">
                                
                                        <div class="tab-pane active" id="profile" aria-labelledby="profile-tab" role="tabpanel">
                                           <div class="row">
                                     
                                           
                                           
                                                   <div class="col-12">
                                                   
 
 
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="maindata.batch"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                     <select id="search_batch" name="search_batch"  class="form-select select2" >
                     <option value="-">-</option>
                             <c:set var="pd" value="" />
                <c:forEach items="${batch }" var="row" varStatus="count">
                
 					<option value="${row.batch}"  <c:if test='${search.search_batch == row.batch}'>selected</c:if>>${row.batch}</option>   
                </c:forEach>
                </select>    
                                                    </div>
                                                </div>
                                            </div>  
                                                 
                                                 
                                                   <div class="col-12">
                                                   
 
 
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="maindata.batch_producer"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                     <select id="search_batch_material" name="search_batch_material" class="form-select select2" >
                  <option value="-">-</option>
                             <c:set var="pd" value="" />
                <c:forEach items="${materials }" var="row" varStatus="count">
                 <c:if test='${row.batch_material != null   && row.batch_material != ""  }'>
 					<option value="${row.batch_material}"  <c:if test='${search.search_batch_material == row.batch_material}'>selected</c:if>>${row.batch_material}</option>   
 					 </c:if>
                </c:forEach>
                </select>
                                                    </div>
                                                </div>
                                            </div>
                                     
                                     
                                     

                                                       
                                                   <div class="col-12">
                                                   
 
 
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="maindata.pd"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                     <select  id="search_pd" name="search_pd"  class="form-select select2" >
                     <option value="-">-</option>
                             <c:set var="pd" value="" />
                <c:forEach items="${pds }" var="row" varStatus="count">
                    <c:if test="${row.production_directive != null }">
 					<option value="${row.production_directive}"  <c:if test='${search.search_pd == row.production_directive}'>selected</c:if>>${row.production_directive}</option>   
 					</c:if>
                </c:forEach>
                </select>
                                                    </div>
                                                </div>
                                            </div>
                                     
                                     
                                                     <div class="col-12">
                                                   
 
 
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="search.datestart"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                  <input type="date" name="search_date"  class="form-control" value="${search.search_date}">
                                                    </div>
                                                </div>
                                            </div>
                                                          <div class="col-12">
                                                   
 
 
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="search.dateend"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                  <input type="date" name="search_date_end" class="form-control" value="${search.search_date_end}">
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
                                     </select>
                                     
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                            
                                               <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="report.data"  /></label>
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
                                                        <label class="col-form-label" for="first-name">Raw material</label>
                                                    </div>
                                                    <div class="col-sm-8">
                      <c:if test='${control_steps.id > 0}'>
                       <input type="hidden" name="id" value="${control_steps.id}">
                       </c:if>
                       
                         <input type="hidden" name="step" value="${id}">
                                           <select class="form-select select2" id="step_1" name="step_1" multiple="multiple" >
                                           <c:forEach items="${key1 }" var="row1" varStatus="count">
	                                		<option value="${row1.label}" <c:if test='${control_steps.step_1.contains(row1.label)}'>selected</c:if>><spring:message code="${row1.label}"  /></option>
	            								 </c:forEach>   
                                     </select>
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                            
                                                     <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">Production section</label>
                                                    </div>
                                                    <div class="col-sm-8">
                      
                                           <select class="form-select select2" id="step_2" name="step_2" multiple="multiple" >
                                           <c:forEach items="${key2 }" var="row2" varStatus="count">
	                                		<option value="${row2.label}" <c:if test='${control_steps.step_2.contains(row2.label)}'>selected</c:if>><spring:message code="${row2.label}"  /></option>
	            								 </c:forEach>   
                                     </select>
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                            
                                            
                                                     <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-4">
                                                        <label class="col-form-label" for="first-name">Finish Product Details</label>
                                                    </div>
                                                    <div class="col-sm-8">
                      
                                           <select class="form-select select2" id="step_3" name="step_3" multiple="multiple" >
                                            <c:forEach items="${key3 }" var="row3" varStatus="count">
	                                		<option value="${row3.label}" <c:if test='${control_steps.step_3.contains(row3.label)}'>selected</c:if>><spring:message code="${row3.label}"  /></option>
	            								 </c:forEach>   
                                     </select>
                                     
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
<script>
function openCenteredWindow(url, name, width, height) {
	
	

    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>

<script>
document.getElementById("Print").addEventListener("click", function () {
    const printContents = document.getElementById("print_body").innerHTML;
    const originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;
    window.print();
    document.body.innerHTML = originalContents;
});
</script>