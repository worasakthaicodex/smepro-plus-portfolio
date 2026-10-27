<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../common/mainhead.jspf" %> 
   <link href="<c:url value="/resources/app-assets/css/plugins/charts/chart-apex.min.css" />" rel="stylesheet">   

<title>index </title>
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
            <div class="content-body">
                <!-- Dashboard Analytics Start -->
                <section id="dashboard-analytics">
                
                   <div class="row match-height">
                        <!-- Avg Sessions Chart Card starts -->
                       
                
   
                    
                              <!--Bar Chart Start -->
                        <div class="col-xl-12 col-12">
                        
                        <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><spring:message code="nav.distributionlogs" text="" /></h4>
                                </div>
                                <div class="card-body">
                              
                                            <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                              <button onclick="download('xlsx');"  type="button"  class="btn btn-primary">Export table to excel xlsx</button>
                                 <br>   <br>   
                                    <table class="table" id="data-table">
                                    <thead>
                                        <tr>
                                            <th> NO</th>
                                          
                                                   <th><spring:message code="dar.name" text="ชื่อเอกสาร" /></th>
                                            
                                              
                                            <th><spring:message code="dar.view" text="จำนวนครั้งที่มีการเปิดดู" /></th>
                                               <th><spring:message code="action.action" text="ดำเนินการ" /></th>
                                        </tr>
                                        </thead>
                                       
                                        <c:forEach items="${doc }" var="row" varStatus="count">
                                      
                                        <tr class="datatable">
                                            <td> ${count.count } </td>
                                             <td> ${row.file_name_original }</td>
                                            
                                             <td> ${row.log_note }</td>
                                              <td> 
                                              		   <a href="/distribution-log?refer=${row.log_refer }" class="btn btn-primary waves-effect waves-float waves-light">
							         View</a>
                                              </td>
                                      </tr>
                                      
                            		  </c:forEach>
                                        
                                         </table>
                                    </div>
                                </div>
                           
                        </div>
                        <!-- Bar Chart End -->
                      
                      
                       </div>
                        <!--/ Timeline Card -->

                        <!-- Avg Sessions Chart Card ends -->


</div>
                        <!-- Sales Stats Chart Card ends -->



               
                    </div>

                    <!-- List DataTable -->
    
                    <!--/ List DataTable -->
                </section>
                <!-- Dashboard Analytics end -->

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>

  


 
<%@ include file="../common/mainfoot.jspf" %>  
 
    <script src="<c:url value="/resources/app-assets/vendors/js/charts/apexcharts.min.js" />"></script>
       <script src="<c:url value="/resources/app-assets/vendors/js/charts/chart.min.js" />"></script>
     <script>
   
     
     var element = document.getElementById("nav_other5");
     element.classList.add("active");

     

     $(window).on('load', function() {
     	
       	
         if (feather) {
             feather.replace({
                 width: 14,
                 height: 14
             });
         }
     	
     
 				  
 				
 				  
 				  
     });
     
     
    $(function () {
    	
      	

  	  
  	  
    	  // Color Variables
    	  var primaryColorShade = '#836AF9',
    	    yellowColor = '#ffe800',
    	    successColorShade = '#28dac6',
    	    warningColorShade = '#ffe802',
    	    warningLightColor = '#FDAC34',
    	    infoColorShade = '#299AFF',
    	    greyColor = '#4F5D70',
    	    blueColor = '#2c9aff',
    	    blueLightColor = '#84D0FF',
    	    greyLightColor = '#EDF1F4',
    	    tooltipShadow = 'rgba(0, 0, 0, 0.25)',
    	    lineChartPrimary = '#666ee8',
    	    lineChartDanger = '#ff4961',
    	    labelColor = '#6e6b7b',
    	    grid_line_color = 'rgba(200, 200, 200, 0.2)'; // RGBA color helps in dark layout
 		
    	
    	    
    	
    
    	    // Bar Chart
    	    // --------------------------------------------------------------------
    	    
    	    var  barChartEx = $('#bar-chart-ex1');
    	    
    	    if (barChartEx.length) {
  	
    	      var barChartExample = new Chart(barChartEx, {
    	        type: 'bar',
    	        options: {
    	          elements: {
    	            rectangle: {
    	              borderWidth: 2,
    	              borderSkipped: 'bottom'
    	            }
    	          },
    	          responsive: true,
    	          maintainAspectRatio: false,
    	          responsiveAnimationDuration: 500,
    	          legend: {
    	            display: false
    	          },
    	          tooltips: {
    	            // Updated default tooltip UI
    	            shadowOffsetX: 1,
    	            shadowOffsetY: 1,
    	            shadowBlur: 8,
    	            shadowColor: tooltipShadow,
    	            backgroundColor: window.colors.solid.white,
    	            titleFontColor: window.colors.solid.black,
    	            bodyFontColor: window.colors.solid.black
    	          },
    	          scales: {
    	            xAxes: [
    	              {
    	                display: true,
    	                gridLines: {
    	                  display: true,
    	                  color: grid_line_color,
    	                  zeroLineColor: grid_line_color
    	                },
    	                scaleLabel: {
    	                  display: false
    	                },
    	                ticks: {
    	                  fontColor: labelColor
    	                }
    	              }
    	            ],
    	            yAxes: [
    	              {
    	                display: true,
    	                gridLines: {
    	                  color: grid_line_color,
    	                  zeroLineColor: grid_line_color
    	                },
    	                ticks: {
    	     
    	                  min: 0,
    	                
    	                  fontColor: labelColor
    	                }
    	              }
    	            ]
    	          }
    	        },
    	        data: {
    	          labels: [
    	        	   <c:forEach items="${maxs }" var="row" varStatus="count">
    	        	  ' ${row.file_name_original }',
    	        	  </c:forEach>
    	        	  ],
    	          datasets: [
    	            {
    	              data: [
    	            	  <c:forEach items="${maxs }" var="row" varStatus="count">
    	            	 ${row.log_note},
    	            	  </c:forEach>
    	            	  ],
    	              barThickness: 15,
    	              backgroundColor: successColorShade,
    	              borderColor: 'transparent'
    	            }
    	          ]
    	        }
    	      });
    	    }	    
    	    
    	    
    	    var  barChartEx = $('#bar-chart-ex2');
    	    
    	    if (barChartEx.length) {
  	
    	      var barChartExample = new Chart(barChartEx, {
    	        type: 'bar',
    	        options: {
    	          elements: {
    	            rectangle: {
    	              borderWidth: 2,
    	              borderSkipped: 'bottom'
    	            }
    	          },
    	          responsive: true,
    	          maintainAspectRatio: false,
    	          responsiveAnimationDuration: 500,
    	          legend: {
    	            display: false
    	          },
    	          tooltips: {
    	            // Updated default tooltip UI
    	            shadowOffsetX: 1,
    	            shadowOffsetY: 1,
    	            shadowBlur: 8,
    	            shadowColor: tooltipShadow,
    	            backgroundColor: window.colors.solid.white,
    	            titleFontColor: window.colors.solid.black,
    	            bodyFontColor: window.colors.solid.black
    	          },
    	          scales: {
    	            xAxes: [
    	              {
    	                display: true,
    	                gridLines: {
    	                  display: true,
    	                  color: grid_line_color,
    	                  zeroLineColor: grid_line_color
    	                },
    	                scaleLabel: {
    	                  display: false
    	                },
    	                ticks: {
    	                  fontColor: labelColor
    	                }
    	              }
    	            ],
    	            yAxes: [
    	              {
    	                display: true,
    	                gridLines: {
    	                  color: grid_line_color,
    	                  zeroLineColor: grid_line_color
    	                },
    	                ticks: {
    	
    	                  min: 0,
    	         
    	                  fontColor: labelColor
    	                }
    	              }
    	            ]
    	          }
    	        },
    	        data: {
    	          labels: [
    	        	   <c:forEach items="${mins }" var="row" varStatus="count">
    	        	  ' ${row.file_name_original }',
    	        	  </c:forEach>
    	        	  ],
    	          datasets: [
    	            {
    	              data: [
    	            	  <c:forEach items="${mins }" var="row" varStatus="count">
    	            	 ${row.log_note},
    	            	  </c:forEach>
    	            	  ],
    	              barThickness: 15,
    	              backgroundColor: successColorShade,
    	              borderColor: 'transparent'
    	            }
    	          ]
    	        }
    	      });
    	    }	    
    }); 
    	    
    	
    	  
    	  
    	  

    	  
    
    
 


    </script>
    
        
<script src="<c:url value="/lib/xlsx.full.min.js" />"></script> 
    <script>
    
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
const download = type => {
	  const el = document.getElementById("data-table");
	  const file = XLSX.utils.table_to_book(el);
	  // Configuring width of the columns:
	  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
	  return XLSX.writeFile(file, "alllog." + type);
	};
</script>
    
</body>


