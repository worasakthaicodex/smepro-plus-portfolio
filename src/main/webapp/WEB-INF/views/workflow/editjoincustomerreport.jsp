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
<title>Join info</title>


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
                                                <li class="breadcrumb-item"><a href="/viewcustomerreport-workflow/${workflows.workflow_id}/${settingJoin.reportSettingId}">Custom report</a>
                                    </li>
                                    <li class="breadcrumb-item active">Setting
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
 
                           <div class="col-12">   
                           
                           <div class="card">
                         
                             
                            
                            <div class="card-body">
                        
                                <div class="business-items">
         
                        <form:form method="POST"  action="/editjoinreportcustom-workflow">
                                 
                                 
                                 <ul class="nav nav-tabs" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="home-tab" data-bs-toggle="tab" href="#home" aria-controls="home" role="tab" aria-selected="true">Report data</a>
                                        </li>
                                      
                                    </ul>
                                 
                                 <div class="tab-content">
                                        <div class="tab-pane active" id="home" aria-labelledby="home-tab" role="tabpanel">
                                                <div class="row">
                                                   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-2">
                                                        <label class="col-form-label" for="first-name">Table</label>
                                                    </div>
                                                    <div class="col-sm-10">
                                                      <input type="hidden" class="form-control"  name="id" id="id" value="${settingJoin.id}">
                                                      
                                        <input type="hidden" class="form-control"  name="report_app" id="report_app" value="${workflows.workflow_id }">
                                        <input type="hidden" class="form-control"  name="report_id" id="report_id" value="${settingJoin.reportSettingId}">
                                                      
                                <input type="text" class="form-control"  name="table1Name" id="table1Name" value="${settingJoin.table2Name}" readonly>
                                                    </div>
                                                </div>
                                            </div>
                                                  
                                        <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-2">
                                                        <label class="col-form-label" for="first-name">Key1</label>
                                                    </div>
                                                    <div class="col-sm-5">
                         
                                 <select class="form-select select2"  name="key1Table1" id="key1Table1" >
								                               <option value="0">-</option>
								                                 <c:forEach items="${my_eformfields }" var="row" varStatus="count">
								                                 
								                                 <c:if test="${row.keymap != null }">
								                                        <c:set var="mainkey" value="${row.field_id}-${row.keymap.mainkey}" />
								                                        <option value="${mainkey }" <c:if test="${settingJoin.key1Table1 == mainkey  }">
								                                        selected</c:if>>${row.field_label }</option>
								                                 </c:if>       
								                                        
								                                        
								                                 </c:forEach>   
								                                        </select> 
                         
                                                    </div>
                                                                      <div class="col-sm-5">
                         
                                 <select class="form-select select2"  name="key1Table2" id="key1Table2" >
								                               <option value="0">-</option>
								                  
								                                       <c:forEach items="${main_eformfields }" var="row" varStatus="count">
								                                          <c:if test="${row.keymap != null }">
								                                           <c:set var="mainkey" value="${row.field_id}-${row.keymap.mainkey}" />
								                                        <option value="${mainkey}" <c:if test="${settingJoin.key1Table2 == mainkey  }">
								                                        selected</c:if>>${row.field_label }</option>
								                                             </c:if> 
								                                 </c:forEach>  
								                                        </select> 
                         
                                                    </div>
                                                </div>
                                            </div>     
                                        
                                       
                                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-2">
                                                        <label class="col-form-label" for="first-name">Key2</label>
                                                    </div>
                                                    <div class="col-sm-5">
                         
                                 <select class="form-select select2"  name="key2Table1" id="key2Table1" >
								                               <option value="0">-</option>
								                                 <c:forEach items="${my_eformfields }" var="row" varStatus="count">
								                                    
								                                                 <c:if test="${row.keymap != null }">
								                                        <c:set var="mainkey" value="${row.field_id}-${row.keymap.mainkey}" />
								                                        <option value="${mainkey}" <c:if test="${settingJoin.key2Table1 == mainkey  }">
								                                        selected</c:if>>${row.field_label }</option>
								                                        </c:if>
								                                 </c:forEach> 
								                                        </select> 
                         
                                                    </div>
                                                                      <div class="col-sm-5">
                         
                                 <select class="form-select select2"  name="key2Table2" id="key2Table2" >
								                               <option value="0">-</option>
								                                     <c:forEach items="${main_eformfields }" var="row" varStatus="count">
								                                                 <c:if test="${row.keymap != null }">
								                                        <c:set var="mainkey" value="${row.field_id}-${row.keymap.mainkey}" />
								                                        <option value="${rmainkey}" <c:if test="${settingJoin.key2Table2 == mainkey  }">
								                                        selected</c:if>>${row.field_label }</option>
								                                        </c:if>
								                                 </c:forEach>  
								                                        </select> 
                         
                                                    </div>
                                                </div>
                                            </div>     
                                        
                                        
                                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-2">
                                                        <label class="col-form-label" for="first-name">Key3</label>
                                                    </div>
                                                    <div class="col-sm-5">
                         
                                 <select class="form-select select2"  name="key3Table1" id="key3Table1" >
								                               <option value="0">-</option>
								                               <c:forEach items="${my_eformfields }" var="row" varStatus="count">
								                                         <c:if test="${row.keymap != null }">
								                                        <c:set var="mainkey" value="${row.field_id}-${row.keymap.mainkey}" />
								                                        <option value="${mainkey}" <c:if test="${settingJoin.key3Table1 == mainkey  }">
								                                        selected</c:if>>${row.field_label }</option>
								                                        </c:if>
								                                        
								                                 </c:forEach>   
								                                        </select> 
                         
                                                    </div>
                                                                      <div class="col-sm-5">
                         
                                 <select class="form-select select2"  name="key3Table2" id="key3Table2" >
								                               <option value="0">-</option>
								                                  <c:forEach items="${main_eformfields }" var="row" varStatus="count">
								                                     <c:if test="${row.keymap != null }">
								                                        <c:set var="mainkey" value="${row.field_id}-${row.keymap.mainkey}" />
								                                        <option value="${mainkey}" <c:if test="${settingJoin.key3Table2 == mainkey  }">
								                                        selected</c:if>>${row.field_label }</option>
								                                        </c:if>
								                                 </c:forEach>    
								                                        </select> 
                         
                                                    </div>
                                                </div>
                                            </div>     
                                              
                                                </div>
                                        </div>
                                
          
                                            
                                  
                                      
                                            
                      
                                            <div class="col-sm-10 offset-sm-2">
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden" class="form-select"  name="report_app" value="${workflows.workflow_id }"> 
                          
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"><spring:message code="btn.save" /></button>
                                          
                                          
                    </div>
                      
                                        
                                        
                        
                                         
                                          
                                    </form:form>
                             
                                     
                                </div>
                                
                          
                                
                            </div>
                      
                            
                        </div>       
    
             </div> 
       
  
              <div class="col-12">   
                           
                           <div class="card">
                         
                             
                            
                            <div class="card-body">
                        
                                <div class="business-items">
         
                        <form:form method="POST"  action="/deletejoinreportcustom-workflow">
                                 
                                 
                                 <ul class="nav nav-tabs" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="home-tab" data-bs-toggle="tab" href="#home" aria-controls="home" role="tab" aria-selected="true"><spring:message code="btn.delete" /></a>
                                        </li>
                                      
                                    </ul>
                                 
                                 <div class="tab-content">
                                        <div class="tab-pane active" id="home" aria-labelledby="home-tab" role="tabpanel">
                                           
                                        <input type="hidden" class="form-control"  name="report_app" id="report_app" value="${workflows.workflow_id }">
                                        <input type="hidden" class="form-control"  name="report_id" id="report_id" value="${settingJoin.reportSettingId}">
                                                      
                              
             							<div class="row">
                                                   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-2">
                                                        <label class="col-form-label" for="first-name"><spring:message code="btn.deletedatajoin" /></label>
                                                    </div>
                                                    <div class="col-sm-10">
                                                      <input type="hidden" class="form-control"  name="id" id="id" value="${settingJoin.id}">
                                                      
                                        <input type="hidden" class="form-control"  name="report_app" id="report_app" value="${workflows.workflow_id }">
                                        <input type="hidden" class="form-control"  name="report_id" id="report_id" value="${settingJoin.reportSettingId}">
                                                      
                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden" class="form-select"  name="report_app" value="${workflows.workflow_id }"> 
                          
                                                <button type="submit" class="btn btn-danger me-1 waves-effect waves-float waves-light" onclick="return confirm('<spring:message code="btn.showconfirm" />');"><spring:message code="btn.delete" /></button>
                                          
                                                    </div>
                                                </div>
                                            </div>
                          </div>
                          
                                     
              
                      
                                        
                                        
                        
                                         
                                          
                                    </form:form>
                             
                                     
                                </div>
                                
                          
                                
                            </div>
                      
                            
                        </div>       
    
             </div> 
       
  
            </div>     
            
                 
      
    
       
    </div>
                       
                         
                         
                         

    </div>
                  
                  
            
 <div class="modal fade" id="Integrations" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
 
                    <div class="modal-dialog   modal-lg modal-dialog-centered">
                         <div class="modal-content">
                                                        <div class="modal-header">
                                                              <h4 class="modal-title" id="myModalLabel33"> Join</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                  
                                                       <form:form method="POST" modelAttribute="traceability" action="/savejoinreportcustom-workflow">
                                               
                                                            <div class="modal-body">
              
                                                              <label><spring:message code="report.table" />: </label>
                                                                <div class="mb-1">
                                                                
                                                                   <input type="text" class="form-control"  name="reportSettingId" id="reportSettingId" value="${customreport.id}">
                                                                      <input type="hidden" class="form-control"  name="table1Name" id="table1Name" value="${customreport.report_table}">

								                 <select class="form-select select2"  name="table2Name" id="table2Name" >
								                               <option value="0">-</option>
								                                 <optgroup label="${workflows.workflow_name}">
								                               <c:forEach items="${process1 }" var="rows" varStatus="count">
								                                        <option value="${rows.id}_${rows.name }">${rows.name }</option>
								                                 </c:forEach>   
								                                   </optgroup>
								                                   
								                                   
								                                  <c:set var="previousNote" value="" />
																	<c:forEach items="${process2}" var="row" varStatus="count">
																	    <c:if test="${row.note != previousNote}">
																	        <c:if test="${not empty previousNote}">
																	            </optgroup>
																	        </c:if>
																	        <optgroup label="${row.note}">
																	        <c:set var="previousNote" value="${row.note}" />
																	    </c:if>
																	    
																	    <option value="${row.id}_${rows.name }">${row.name}</option>
																	</c:forEach>
																	<c:if test="${not empty previousNote}">
																	    </optgroup>
																	    </c:if>
								                     </select> 
                                                                </div>
                                                    
                                                              
                                                                
                                                                
                                                    
                                                                    
                                     
                                                    <div class="mb-1">  
      												<input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      			
                                   					        <input type="hidden" class="form-select"  name="report_app" value="${workflows.workflow_id }"> 
                  							       </div>
                  				     
                  							       
                  							       
                                                          
                                                            </div>
                                                      
                                                      
                                                      
                                                      
                                                            
                                                            
                                                            <div class="modal-footer" Style="display: inline-block;">
                                                                <button type="Submit"   class="btn btn-primary waves-effect waves-float waves-light" data-bs-dismiss="modal">เพิ่ม</button>
                                                            </div>
                                                </form:form>
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
