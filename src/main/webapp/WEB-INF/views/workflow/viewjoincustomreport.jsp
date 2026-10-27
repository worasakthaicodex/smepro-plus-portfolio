<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns"></script>
 <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-zoom"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>

 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>(Join) Custom Report</title>

     <link href="<c:url value="/lib/Spreadsheet/css/dynamic-table.jquery.css" />" rel="stylesheet">
       <link href="<c:url value="/lib/Spreadsheet/css/jquery-ui.css" />" rel="stylesheet">
   
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
.ui-dynamic-table-header-cell{
  background-color: #2B579A;
  color:#fff;
      font-size: 75%;
}
      </style>
    
      
      <style type="text/css">
      
         body {
            font-family: sans-serif;
            font-size: 14px;
            margin: 0px;
            padding: 20px;
            background-color: #666666;
         }
         
         #sample-grid-customreports-${customreports.id}{
            width: 100%; 
            height: 500px;
         }
      
         #selected-data,
         #save-data {
            width: 700px; 
            background-color: #f3f3f3;
            padding: 5px;
            margin-top: 10px;
            border-radius: 5px;
            box-sizing:border-box;
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
         <c:if test="${iframe ==  0 }">  
    
<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>  
</c:if>
    <!-- BEGIN: Content-->
    
    
    <div class=" <c:if test="${iframe ==  0 }">  app-content content </c:if>">
  
        <div class="content-wrapper container-xxl p-0">
 
            <div class="content-body" >
          <section id="multiple-column-form" >
          
     
           <c:if test="${iframe ==  0 }">  
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
                                    <li class="breadcrumb-item active">Custom report
                                    </li>
                                </ol>
                                   
                          
                            </div>
                         
                        </div>
                    </div>
                </div>

 
                <div class="content-header-left col-md-3 col-12 mb-2">
            
                            
                                     <a href="/settingcustomerreport-workflow/${workflows.workflow_id}/${id}" class="btn btn-sm btn-primary  waves-effect waves-float waves-light" style="float:right;margin-left:5px" >  
                             <spring:message code="btn.setting"  /></a>
           
                             <a href="/settingcustomerreport2-workflow/${workflows.workflow_id}/${id}" class="btn btn-sm btn-primary  waves-effect waves-float waves-light" style="float:right;margin-left:5px" >  
                             <spring:message code="btn.setting_chart"  /></a>
           
                             </div>
                        
                             
            </div> 

     

          
                      
                         
                   </c:if>    
                      <div >                 
            <div class="row ">
                        <div class="col-12">   
                        <div class="card">
                            <div class="card-body">
                            
                                 <c:if test="${customreports.type ==  4 }">
          									  <div id="funnel_chart" style="width: 100%; height: 300px;"></div>
                                     </c:if>
                                         <c:if test="${customreports.type ==  1}">  
                    		<div style="width: 400px; height: 350px;margin:auto">
 								 <canvas id="myChart"></canvas>
							</div>
                                     </c:if>
                                             <c:if test="${customreports.type != 1 && customreports.type  !=  4  }">
                                <canvas id="myChart"  height="75"></canvas>
                        
                                     </c:if>
                              
                                
                                     
                            </div>
                        </div> 

                        </div>          
                             <div class="col-12">   
                        <div class="card">
                         
      
                               <div id="sample-grid-customreports-${customreports.id}"  style="width: 100%;"></div>     
                  
                       
                         
                        </div> 

                        </div>     
            </div>     
            
                 
      
    
       
    </div>
                  
                  
                  
                  
                  
                  
             
                        <!--end col-->
                    </div>           
                    
                    
                    
                    
                       
                   
            
          
          

                  
          
                 
                 
            
            
     
                </section>

            </div>
        </div>
 
 
  
 
 
         
<c:forEach var="row" items="${transaction}">
  <p>ข้อมูลหลัก: ${row.id}</p>
  <p>ข้อมูล join1: ${row.join1.size()}</p>
    <p>ข้อมูล join2: ${row.join2.size()}</p>
  <ul>
    <c:forEach var="edataItem" items="${row.join1}">
      <li>${edataItem['id']} ${edataItem['product_name']}</li>
    </c:forEach>
  </ul>
</c:forEach>    
 

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>




     
    
<%@ include file="../common/mainfoot.jspf" %>  

 <script src="<c:url value="/lib/Spreadsheet/js/jquery.js" />"></script>	  
<script src="<c:url value="/lib/Spreadsheet/js/jquery-ui.js" />"></script>	  
<script src="<c:url value="/lib/Spreadsheet/js/moment.js" />"></script>	  
<script src="<c:url value="/lib/Spreadsheet/js/dynamic-table.jquery.js" />"></script>	  
 <script src="<c:url value="/lib/Spreadsheet/js/dynamic-table-editor.jquery.js" />"></script>	  
 
   <c:if test="${customreports.type ==  1 }">
 <%@ include file="../app/Chart/Chart1.jspf" %>  
  </c:if>
  <c:if test="${customreports.type ==  2 }">
 <%@ include file="../app/Chart/Chart2.jspf" %>  
  </c:if>
   <c:if test="${customreports.type ==  3 }">  
   <%@ include file="../app/Chart/Chart3.jspf" %>  
  </c:if> 
   <c:if test="${customreports.type ==  4 }">  
  
  <script src="https://cdn.amcharts.com/lib/5/index.js"></script>
  <script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
  <script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
     <%@ include file="../app/Chart/Chart4.jspf" %>  
  </c:if>
 
 
 <c:if test="${customreports.type ==  5}">  
      <%@ include file="../app/Chart/Chart5.jspf" %>  
  </c:if> 
  
  <c:if test="${customreports.type == 6}">  
      <%@ include file="../app/Chart/Chart6.jspf" %>  
  </c:if> 
    <c:if test="${customreports.type == 7}">  
      <%@ include file="../app/Chart/Chart7.jspf" %>  
  </c:if> 
  <c:if test="${customreports.type ==  8 }">
        <%@ include file="../app/Chart/Chart8.jspf" %>  
  </c:if> 
  <c:if test="${customreports.type ==  9 }">
        <%@ include file="../app/Chart/Chart9.jspf" %>  
  </c:if> 
 
   <c:if test="${customreports.type ==  10 }">
        <%@ include file="../app/Chart/Chart10.jspf" %>  
  </c:if> 
 
   <c:if test="${customreports.type ==  11 }">
        <%@ include file="../app/Chart/Chart11.jspf" %>  
  </c:if> 
 
    <c:if test="${customreports.type ==  12 }">
        <%@ include file="../app/Chart/Chart12.jspf" %>  
  </c:if> 

 
 
 <script>
 
 
 <c:set var='skip' value='procurement_grandtotal,procurement_grandtotal2'/>
      $(function() {
         
         // Initialize table
         $("#sample-grid-customreports-${customreports.id}").dynamicTable({
            fillParent : false,
            showCounter: true,
            changeColumns : true
         });
         
         // Define columns
         var myColumns = [
        	 	   
	
        	 		<c:forEach items="${fieldsToPrint }"   var="row" varStatus="count">
        	 		
        	 	
        	 		 <c:choose>

        	 		  <c:when test='${row.format == "NUMBER" }'>
                      {  // Hidden Identifier column
                          name       : "${row.title }",
                          type       : "number",
                          visible    : true
                       },
        	 		   </c:when> 
                       
                 	  <c:when test='${row.format == "Enum" }'>
                 	 {// Limited values with default picklist filter 
                         name       : "${row.title }",
                         type       : "string",
                         visible    : true,
                      },
        	 		   </c:when> 
                       
                 	  <c:when test='${row.format == "DATE" }'>
                 	    {// Date column with date filter
                            name       : "${row.title }",
                            type       : "date",
                            visible    : true,
                            format     : "d-MMM-YYYY", 
                            filterType : "dateRange",
                            cssClass   : function (aColumn, aValue, aDisplayValue) {
                               return aValue != null  ? "has-birth-date" : null
                            }
                         },
         	 		   </c:when> 
                      
                         <c:when test='${row.format == "TEXT" }'>
                         { 
                             name       : "${row.title }",
                             type       : "string",
                             visible    : true,
                             editor     : $("<div/>").dynamicTableEditor({
                                editHandler: function(aData, aContext) {
                                   $("#save-data").html("Saving note: <strong>" + aData + "</strong>");       
                                }
                             })              
                          },
          	 		   </c:when> 
                       
                          
                          
                          
                          
               	    <c:otherwise>
          	          {// Searchable text column
                             name       : "${row.title }",
                             type       : "string",
                             visible    : true,
                             filterType : "search",
                             width      : 200
                          },
          	  		  </c:otherwise>
                        
                          
        	 		</c:choose>   

                          </c:forEach>
                          
                       
                         ];

         
         // Get the data. This would usually be done in an AJAS call
         // ($.getJSON) but for demo purposes we show hardcoded data
 
  
       //  alert("${autoid}");
         var myData = [
        	 <c:forEach var="row" items="${resultTable}">
        	 [
        
       
                 <c:forEach var="field" items="${fieldsToPrint}">
                     "${row[field.key]}",
                 </c:forEach>
     
    ],
 
    </c:forEach>  
];
     
         
         // Load the data into the grid
         $("#sample-grid-customreports-${customreports.id}").dynamicTable("data", myData, myColumns);
         
         setTimeout(function () {
        	    adjustDynamicTableWidth();
        	}, 100);

      });
      
      
      function adjustDynamicTableWidth() {
    	    let header = $(".ui-dynamic-table-header");
    	    let body = $("#sample-grid-customreports-${customreports.id}");

    	    // บังคับให้ header กว้างเท่า body
    	    if (header.length && body.length) {
    	        header.width(body.width());
    	    }
    	}
      
      
      $(window).on('load', function() {
          if (feather) {
              feather.replace({
                  width: 14,
                  height: 14
              });
          }
      })
      
      </script>
    
    
    
 <script>



 

  

  
  
  
  
  
 
  

  
 
  
  
  
  
  const colorThemes = {
		  vibrant: [
			  '#3cb44b','#e6194b', '#ffe119', '#4363d8',
		    '#f58231', '#911eb4', '#46f0f0', '#f032e6',
		    '#bcf60c', '#fabebe', '#008080', '#e6beff'
		  ],
		  pastel: [
		    'rgba(255,179,186,0.6)', 'rgba(255,223,186,0.6)', 'rgba(255,255,186,0.6)',
		    'rgba(186,255,201,0.6)', 'rgba(186,225,255,0.6)', 'rgba(255,204,229,0.6)',
		    'rgba(204,229,255,0.6)', 'rgba(204,255,229,0.6)', 'rgba(229,204,255,0.6)',
		    'rgba(255,255,204,0.6)', 'rgba(255,229,204,0.6)', 'rgba(204,255,255,0.6)'
		  ],
		  blue: [
		    'rgba(0, 51, 102, 0.8)', 'rgba(0, 76, 153, 0.75)', 'rgba(0, 102, 204, 0.7)',
		    'rgba(0, 128, 255, 0.65)', 'rgba(51, 153, 255, 0.6)', 'rgba(102, 178, 255, 0.55)',
		    'rgba(153, 204, 255, 0.5)', 'rgba(179, 217, 255, 0.5)', 'rgba(204, 229, 255, 0.5)',
		    'rgba(229, 240, 255, 0.5)', 'rgba(240, 248, 255, 0.5)', 'rgba(245, 250, 255, 0.5)'
		  ],
		  onlyblue: [
			    'rgba(0, 51, 102, 0.8)', 'rgba(0, 51, 102, 0.8)', 'rgba(0, 51, 102, 0.8)',
			    'rgba(0, 51, 102, 0.8)', 'rgba(0, 51, 102, 0.8)', 'rgba(0, 51, 102, 0.8)',
			    'rgba(0, 51, 102, 0.8)', 'rgba(0, 51, 102, 0.8)', 'rgba(0, 51, 102, 0.8)',
			    'rgba(0, 51, 102, 0.8)', 'rgba(0, 51, 102, 0.8)', 'rgba(0, 51, 102, 0.8)'
			  ],
		  green: [
		    'rgba(0, 102, 0, 0.8)', 'rgba(0, 128, 0, 0.75)', 'rgba(0, 153, 51, 0.7)',
		    'rgba(51, 204, 51, 0.65)', 'rgba(102, 255, 102, 0.6)', 'rgba(153, 255, 153, 0.55)',
		    'rgba(178, 255, 178, 0.5)', 'rgba(204, 255, 204, 0.5)', 'rgba(229, 255, 229, 0.5)',
		    'rgba(240, 255, 240, 0.5)', 'rgba(245, 255, 245, 0.5)', 'rgba(250, 255, 250, 0.5)'
		  ],
		  green: [
			    'rgba(0, 102, 0, 0.8)', 'rgba(0, 128, 0, 0.75)', 'rgba(0, 153, 51, 0.7)',
			    'rgba(51, 204, 51, 0.65)', 'rgba(102, 255, 102, 0.6)', 'rgba(153, 255, 153, 0.55)',
			    'rgba(178, 255, 178, 0.5)', 'rgba(204, 255, 204, 0.5)', 'rgba(229, 255, 229, 0.5)',
			    'rgba(240, 255, 240, 0.5)', 'rgba(245, 255, 245, 0.5)', 'rgba(250, 255, 250, 0.5)'
			  ],
			  neonMinimal: [
				  '#FF2D55AA', // ชมพูนีออน
				    '#00FF7FAA', // เขียวมินต์นีออน
				    '#1E90FFAA', // น้ำเงินดอดจ์
				    '#FFD700AA', // ทองนีออน
				    '#FF69B4AA', // ชมพูฮอตพิงค์
				    '#00CED1AA', // ฟ้าทะเล
				    '#FF4500AA', // ส้มเรดออเรนจ์
				    '#9400D3AA', // ม่วงดิจิทัล
				    '#ADFF2FAA', // เขียวมะนาว
				    '#FF6347AA', // มะเขือเทศ
				    '#4682B4AA', // น้ำเงินสตีล
				    '#FFB6C1AA'  // ชมพูอ่อนเลิฟลี่
				  ],			        
				  retroFuture: [
					  'rgba(255, 99, 71, 0.7)',   // มะเขือเทศวินเทจ
					    'rgba(60, 179, 113, 0.7)',  // เขียวมิ้นต์
					    'rgba(106, 90, 205, 0.7)',  // ม่วงสเลท
					    'rgba(255, 215, 0, 0.7)',   // ทองเรโทร
					    'rgba(199, 21, 133, 0.7)',  // ชมพูเมเจนต้า
					    'rgba(0, 206, 209, 0.7)',   // ฟ้าทะเลคราม
					    'rgba(255, 140, 0, 0.7)',   // ส้มดาร์ก
					    'rgba(138, 43, 226, 0.7)',  // ม่วงโบลว์
					    'rgba(50, 205, 50, 0.7)',   // เขียวไลม์
					    'rgba(255, 105, 180, 0.7)', // ชมพูฮอต
					    'rgba(70, 130, 180, 0.7)',  // น้ำเงินสตีล
					    'rgba(240, 230, 140, 0.7)'  // เหลืองขมิ้น
					  ],
					  monoChic: [

						    'rgba(44, 44, 44, 0.8)',    // ดำเข้ม
						    'rgba(77, 77, 77, 0.8)',    // เทาดาร์ก
						    'rgba(136, 136, 136, 0.8)', // เทากลาง
						    'rgba(200, 200, 200, 0.8)', // เทาอ่อน
						    'rgba(255, 255, 255, 0.8)', // ขาว
						    'rgba(255, 51, 51, 0.6)',   // แดงแต้ม
						    'rgba(51, 153, 255, 0.6)',  // ฟ้าแต้ม
						    'rgba(102, 204, 0, 0.6)',   // เขียวแต้ม
						    'rgba(204, 51, 255, 0.6)',  // ม่วงแต้ม
						    'rgba(255, 153, 0, 0.6)',   // ส้มแต้ม
						    'rgba(255, 255, 153, 0.6)', // เหลืองแต้ม
						    'rgba(153, 153, 153, 0.8)'  // เทาคลาสสิก
						  ],
						  cosmicGlow: [
							   '#191970AA', // มิดไนต์บลู
							    '#483D8BAA', // น้ำเงินสเลท
							    '#8A2BE2AA', // ม่วงไวโอเล็ต
							    '#FF00FFAA', // ฟูเชียกาแล็กซี่
							    '#00B7EBAA', // ฟ้าซีอัน
							    '#FFD700AA', // ทองดารา
							    '#FF1493AA', // ชมพูดีพพิงค์
							    '#32CD32AA', // เขียวไลม์สตาร์
							    '#FF4500AA', // ส้มเรดออเรนจ์
							    '#9932CCAA', // ม่วงดาร์กออร์คิด
							    '#00CED1AA', // ฟ้าทะเลมืด
							    '#FFFFFFAA'  // ขาวสตาร์ไลต์
							  ],
							  
							  zenVibes: [
								  'rgba(34, 139, 34, 0.7)',   // เขียวป่า
								    'rgba(70, 130, 180, 0.7)',  // น้ำเงินสตีล
								    'rgba(147, 112, 219, 0.7)', // ม่วงเมเดียม
								    'rgba(255, 165, 0, 0.7)',   // ส้มแมนดาริน
								    'rgba(255, 192, 203, 0.7)', // ชมพูพิงค์
								    'rgba(64, 224, 208, 0.7)',  // ฟ้าทะเล
								    'rgba(189, 183, 107, 0.7)', // เขียวขมิ้น
								    'rgba(218, 112, 214, 0.7)', // ม่วงออร์คิด
								    'rgba(154, 205, 50, 0.7)',  // เขียวมะกอก
								    'rgba(255, 99, 71, 0.7)',   // แดงมะเขือเทศ
								    'rgba(176, 196, 222, 0.7)', // น้ำเงินไลต์สตีล
								    'rgba(245, 245, 220, 0.7)'  // เบจเซน
								  ],
								  VibrantFade: 	  [
									    'rgba(60, 180, 75, 0.6)',   // เขียวจาก #3cb44b
									    'rgba(230, 25, 75, 0.6)',   // แดงจาก #e6194b
									    'rgba(255, 225, 25, 0.6)',  // เหลืองจาก #ffe119
									    'rgba(67, 99, 216, 0.6)',   // น้ำเงินจาก #4363d8
									    'rgba(245, 130, 49, 0.6)',  // ส้มจาก #f58231
									    'rgba(145, 30, 180, 0.6)',  // ม่วงจาก #911eb4
									    'rgba(70, 240, 240, 0.6)',  // ฟ้าจาก #46f0f0
									    'rgba(240, 50, 230, 0.6)',  // ชมพูจาก #f032e6
									    'rgba(188, 246, 12, 0.6)',  // เขียวมะนาวจาก #bcf60c
									    'rgba(250, 190, 190, 0.6)', // ชมพูอ่อนจาก #fabebe
									    'rgba(0, 128, 128, 0.6)',   // ทีลจาก #008080
									    'rgba(230, 190, 255, 0.6)'  // ม่วงอ่อนจาก #e6beff
									],
									CyberZen: 	  [
										  'rgba(25, 25, 112, 0.7)',   // มิดไนต์บลู
										    'rgba(138, 43, 226, 0.7)',  // ม่วงโบลว์
										    'rgba(0, 191, 255, 0.7)',   // ฟ้าดีพสกาย
										    'rgba(255, 20, 147, 0.7)',  // ชมพูดีพพิงค์
										    'rgba(255, 69, 0, 0.7)',    // ส้มเรดออเรนจ์
										    'rgba(124, 252, 0, 0.7)',   // เขียวลawn
										    'rgba(186, 85, 211, 0.7)',  // ม่วงเมเดียม
										    'rgba(0, 245, 255, 0.7)',   // ฟ้าทะเลนีออน
										    'rgba(255, 215, 0, 0.7)',   // ทองโกลด์
										    'rgba(255, 105, 180, 0.7)', // ชมพูฮอตพิงค์
										    'rgba(47, 79, 79, 0.7)',    // เทาดาร์กสเลท
										    'rgba(240, 255, 240, 0.7)'  // ขาวมิ้นต์
										],
		};
  
  


  
  

		function getThemeColor(theme, index) {
		  const colors = colorThemes[theme] || colorThemes['vibrant'];
		  return colors[index % colors.length];
		}
  </script>

</body>



  

