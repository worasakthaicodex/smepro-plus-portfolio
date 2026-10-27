<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="common/mainhead.jspf" %> 
   <link href="<c:url value="/resources/app-assets/css/plugins/charts/chart-apex.min.css" />" rel="stylesheet">   

<title>index </title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">

<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>     

    <!-- BEGIN: Content-->
    <div class="app-content content ">
       
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
                <!-- Dashboard Analytics Start -->
                <section id="dashboard-analytics">
                
                   <div class="row match-height">
                        <!-- Avg Sessions Chart Card starts -->
                        
                                          <div class="col-xl-12 col-md-6 col-12">
      <div class="card card-statistics">
        <div class="card-header">
          <h4 class="card-title">ภาพรวม</h4>
          <div class="d-flex align-items-center">
           
          </div>
        </div>
        <div class="card-body statistics-body">
          <div class="row">
          
              <div class="col-xl-4 col-sm-6 col-12 mb-2 mb-xl-0">
              <div class="d-flex flex-row">
                <div class="avatar bg-light-info me-2">
                  <div class="avatar-content">
                    <i data-feather="user" class="avatar-icon"></i>
                  </div>
                </div>
                <div class="my-auto">
                  <h4 class="fw-bolder mb-0">${online }</h4>
                  <p class="card-text font-small-3 mb-0">ผู้ใช้งาน</p>
                </div>
              </div>
            </div>
          
          
            <div class="col-xl-4 col-sm-6 col-12 mb-2 mb-xl-0">
              <div class="d-flex flex-row">
                <div class="avatar bg-light-primary me-2">
                  <div class="avatar-content">
                
                    <i data-feather='airplay'  class="avatar-icon"></i>
                  </div>
                </div>
                <div class="my-auto">
                  <h4 class="fw-bolder mb-0">${SESSIONCOUNTER }</h4>
                  <p class="card-text font-small-3 mb-0">จำนวนเครื่องที่ใช้งาน</p>
                </div>
              </div>
            </div>
        
            <div class="col-xl-4 col-sm-6 col-12 mb-2 mb-sm-0">
              <div class="d-flex flex-row">
                <div class="avatar bg-light-danger me-2">
                  <div class="avatar-content">

                    <i data-feather='activity' class="avatar-icon"></i>
                  </div>
                </div>
                <div class="my-auto">
                  <h4 class="fw-bolder mb-0">${doctotal }</h4>
                  <p class="card-text font-small-3 mb-0">ระยะเวลา Login โดยเฉลี่ย</p>
                </div>
              </div>
            </div>
     
          </div>
        </div>
      </div>
    </div>
 
                        
                
                     
                    </div>
                
                
                
                
                
                
                  <div class="row match-height">
                 <div class="col-lg-4 col-md-6 col-12">
      <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
          <h4 class="card-title">พื้นที่ใช้งาน</h4>
          <i data-feather="help-circle" class="font-medium-3 text-muted cursor-pointer"></i>
        </div>
        <div class="card-body p-0">
          <div id="goal-overview-radial-bar-chart" class="my-2"></div>
          <div class="row border-top text-center mx-0">
            <div class="col-6 border-end py-1">
              <p class="card-text text-muted mb-0">ที่กำหนดไว้</p>
              <h3 class="fw-bolder mb-0">${drives_total }</h3>
            </div>
            <div class="col-6 py-1">
              <p class="card-text text-muted mb-0">ใช้ไปเเล้ว</p>
              <h3 class="fw-bolder mb-0">${drives_use }</h3>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--/ Goal Overview Card -->
               
                   <!-- Donut Chart Starts -->
                        <div class="col-lg-4 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">พื้นที่ใช้งาน</h4>
                                </div>
                                <div class="card-body">
                                    <canvas class="doughnut-chart-exs chartjs" data-height="275"></canvas>
                              
                              
                               
                                    
                                    
                                    
                                </div>
                     
    	    
                        </div>
                        <!-- Donut Chart Starts -->
                  </div>
               
               
               
                  <!-- Support Tracker Chart Card starts -->
                        <div class="col-lg-4 col-12">
                        <div class="card">
                                <div class="
            card-header
            d-flex
            flex-sm-row flex-column
            justify-content-md-between
            align-items-start
            justify-content-start
          ">
                                    <div>
                                        <h4 class="card-title mb-25">เอกสารในระบบ</h4>
                                        <span class="card-subtitle text-muted"></span>
                                    </div>
                                    <div class="d-flex align-items-center flex-wrap mt-sm-0 mt-1">
                                        <h5 class="fw-bolder mb-0 me-1"></h5>
                                        
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div id="bar-chart"></div>
                                </div>
                            </div>
                     
                      </div>
                        <!-- Support Tracker Chart Card ends -->
               
  </div> 

                   <div class="row">  
                   <!-- Polar Area Chart Starts -->
                        <div class="col-lg-4 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">ข้อมูลที่ครบกำหนดแยกตามชนิดเอกสาร</h4>
                      
                                </div>
                                <div class="card-body">
                                    <canvas class="polar-area-chart-ex chartjs" data-height="350" style="height:400px"></canvas>
                                </div>
                            </div>
                        </div>     
                        
                             <!-- Column Chart Starts -->
                        <div class="col-lg-8  col-12">
                            <div class="card">
                                <div class="
            card-header
            d-flex
            flex-md-row flex-column
            justify-content-md-between justify-content-start
            align-items-md-center align-items-start
          ">
                                    <h4 class="card-title">ภาพรวมของการส่งบันทึก</h4>
                                
                                </div>
                                <div class="card-body">
                                    <div id="column-charts"></div>
                                </div>
                            </div>
                        </div>
                        <!-- Column Chart Ends -->     
                 
               </div>     
                 
   <div class="row">
                        <!--Bar Chart Start -->
                        <div class="col-xl-6 col-12">
                            <div class="card">
                                <div class="card-header d-flex justify-content-between align-items-sm-center align-items-start flex-sm-row flex-column">
                                    <div class="header-left">
                                        <h4 class="card-title">ล่าช้า 5 อันดับแรก</h4>
                                    </div>
                               
                                </div>
                                <div class="card-body">
                                    <canvas class="bar-chart-exs chartjs" data-height="400" style="height:400px"></canvas>
                                </div>
                            </div>
                        </div>
                        <!-- Bar Chart End -->

                        <!-- Horizontal Bar Chart Start -->
                        <div class="col-xl-6 col-12">
                            <div class="card">
                                <div class="card-header d-flex justify-content-between align-items-sm-center align-items-start flex-sm-row flex-column">
                                    <div class="header-left">
                                        <h4 class="card-subtitle">ขาดส่งรายงาน 5 อันดับแรก</h4>
                                      
                                    </div>
                                    
                                </div>
                                <div class="card-body">
                                    <canvas class="horizontal-bar-chart-exs chartjs" data-height="400" style="height:400px"></canvas>
                                </div>
                            </div>
                        </div>
                        <!-- Horizontal Bar Chart End -->
                    </div>
                    
                    
                    <div class="row match-height" style="display:none">
                        <!-- Timeline Card -->
                        <div class="col-lg-4 col-12">
                       <div class="card card-employee-task">
                       
                            
                             <div class="header-left">
                                        <p class="card-subtitle text-muted mb-25">ผู้รับผิดชอบอัพโหลด</p>
                                      
                                    </div>
                            <div class="card-body">
                            
                                <c:forEach items="${reportC }" var="row" varStatus="count">
                                <div class="employee-task d-flex justify-content-between align-items-center">
                                    <div class="d-flex flex-row">
                                        <div class="avatar me-75">
                                            <img src="/resources/app-assets/images/portrait/small/avatar.jpg" class="rounded" width="42" height="42" alt="Avatar" />
                                        </div>
                                        <div class="my-auto">
                                            <h6 class="mb-0">${row.report_pn_respon }</h6>
                                            <small></small>
                                        </div>
                                    </div>
                                    <div class="d-flex align-items-center">
                                        <small class="text-muted me-75">${row.report_pn_statussccess }ทัน ${row.report_pn_statuserror }ไม่ทัน</small>
                                        <div class="employee-task-chart-danger"></div>
                                    </div>
                                </div>
                                
                                </c:forEach>
                         </div> 
                      </div> 
                      
                      
                       </div>
                        <!--/ Timeline Card -->

                      <div class="col-lg-8 col-12" >
                     <div class="card card-revenue-budget">
        <div class="row mx-0">
          <div class="col-md-12 col-12 revenue-report-wrapper">
            <div class="d-sm-flex justify-content-between align-items-center mb-3">
              <h4 class="card-title mb-50 mb-sm-0">อัพโหลดบันทึก</h4>
              <div class="d-flex align-items-center">
               
              </div>
            </div>
           <div id="column-chart"></div>
          </div>
       </div>
      </div>
    
                 
                    </div>
                        <!-- Avg Sessions Chart Card ends -->


</div>
                        <!-- Sales Stats Chart Card ends -->



<div class="row" id="basic-table">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">ข้อมูลการอัพโหลดบันทึก</h4>
                                        <a href="/listreport" class="btn btn-light waves-effect waves-float waves-light">
							          Report</a>
                            </div>
        
                            <div class="table-responsive">
                            
                            
                                <table class="table">
                                    <thead>
                                        <tr>
                                          <th>ลำดับ</th>
                                            <th>เอกสารที่ต้องอัพโหลด</th>
                                            <th>วันที่ต้องอัพโหลด</th>
                                            <th>ผู้รับผิดชอบ</th>
                                            <th>ผู้ตรวจสอบ</th>
                                            <th>จำนวนครั้งที่อัพโหลด</th>
                                              <th>ขาดส่ง</th>
                                            <th>ตรวจสอบล่าช้า</th>
                                              <th> จำนวนวันที่ล่าช้า</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${report_pn }" var="row" varStatus="count">
                                        
                                        <tr>
                                        
                                           <td>${count.count}</td>
                                            <td>
                                           ${row.upload_pn_name}
                                            </td>
                                            <td>${row.report_pn_date}</td>
                                            <td>
                                                ${row.report_pn_respon}
                                            </td>
                                                <td>
                                                ${row.report_pn_app}
                                            </td>
                                            <td> ${row.report_pn_total}</td>
                                            <td> ${row.report_pn_sendstatus}</td>
                                       <td> ${row.report_pn_appstatus}</td>
                                         <td> ${row.report_pn_statuserror}</td>
                                        </tr>
                                              
                                              
                                
                                    </tbody>
                                  </c:forEach>
                                  
                                   
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
               
                    </div>

                    <!-- List DataTable -->
                    <div class="row" style="display:none">
                        <div class="col-12">
                            <div class="card invoice-list-wrapper">
                                <div class="card-datatable table-responsive">
                                    <table class="invoice-list-table table" id="DataTables_Table_0">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>#</th>
                                                <th><i data-feather="trending-up"></i></th>
                                                <th>Client</th>
                                                <th>Total</th>
                                                <th class="text-truncate">Issued Date</th>
                                                <th>Balance</th>
                                                <th>Invoice Status</th>
                                                <th class="cell-fit">Actions</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--/ List DataTable -->
                </section>
                <!-- Dashboard Analytics end -->

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>

    <!-- BEGIN: Footer-->
    <footer class="footer footer-static footer-light">
        <p class="clearfix mb-0"><span class="float-md-start d-block d-md-inline-block mt-25">COPYRIGHT &copy; 2021<a class="ms-25" href="https://1.envato.market/pixinvent_portfolio" target="_blank">Pixinvent</a><span class="d-none d-sm-inline-block">, All rights Reserved</span></span><span class="float-md-end d-none d-md-block">Hand-crafted & Made with<i data-feather="heart"></i></span></p>
    </footer>
    <button class="btn btn-primary btn-icon scroll-top" type="button"><i data-feather="arrow-up"></i></button>
    <!-- END: Footer-->




 
<%@ include file="common/mainfoot.jspf" %>  
 
    <script src="<c:url value="/resources/app-assets/vendors/js/charts/apexcharts.min.js" />"></script>
       <script src="<c:url value="/resources/app-assets/vendors/js/charts/chart.min.js" />"></script>
     <script>
   
  

    $(function () {
    	
    	  'use strict';
    	  var chartWrapper = $('.chartjs'),
    	    flatPicker = $('.flat-picker'),
    	    barChartEx = $('.bar-chart-ex'),
    	    horizontalBarChartEx = $('.horizontal-bar-chart-ex'),
    	    lineChartEx = $('.line-chart-ex'),
    	    radarChartEx = $('.radar-chart-ex'),
    	    polarAreaChartEx = $('.polar-area-chart-ex'),
    	    bubbleChartEx = $('.bubble-chart-ex'),
    	    doughnutChartEx = $('.doughnut-chart-ex'),
    	    scatterChartEx = $('.scatter-chart-ex'),
    	    lineAreaChartEx = $('.line-area-chart-ex');

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
 		
    	
    	    
    	 // Doughnut Chart
    	    // --------------------------------------------------------------------
    	    var doughnutChartEx = $('.doughnut-chart-exs');
    	    if (doughnutChartEx.length) {
    	      var doughnutExample = new Chart(doughnutChartEx, {
    	        type: 'doughnut',
    	        options: {
    	          responsive: true,
    	          maintainAspectRatio: false,
    	          responsiveAnimationDuration: 500,
    	          cutoutPercentage: 60,
    	          legend: { display: false },
    	          tooltips: {
    	            callbacks: {
    	              label: function (tooltipItem, data) {
    	                var label = data.datasets[0].labels[tooltipItem.index] || '',
    	                  value = data.datasets[0].data[tooltipItem.index];
    	                var output = ' ' + label + ' : ' + value + ' K';
    	                return output;
    	              }
    	            },
    	            // Updated default tooltip UI
    	            shadowOffsetX: 1,
    	            shadowOffsetY: 1,
    	            shadowBlur: 8,
    	            shadowColor: tooltipShadow,
    	            backgroundColor: window.colors.solid.white,
    	            titleFontColor: window.colors.solid.black,
    	            bodyFontColor: window.colors.solid.black
    	          }
    	        },
    	        data: {
    	          datasets: [
    	            {
    	              labels: [
    	            	  <c:forEach items="${foldersuse }" var="row" varStatus="count">
    	            	  '${row.folder_name}',
    	            	  </c:forEach>
    	            	  ],
    	              data: [
    	            	  <c:forEach items="${foldersuse }" var="row" varStatus="count">
    	            	  '${row.folder_use}',
    	            	  </c:forEach>
    	              ],
    	              backgroundColor: [successColorShade, warningLightColor, window.colors.solid.primary,'#ffe700','#00d4bd','#826bf8','#2b9bf4','#FFA1A1'],
    	              borderWidth: 0,
    	              pointStyle: 'rectRounded'
    	            }
    	          ]
    	        }
    	      });
    	    } 
    
    	    
    
    	    // Bar Chart
    	    // --------------------------------------------------------------------
    	    
    	    var  barChartEx = $('.bar-chart-exs');
    	    
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
    	                  stepSize: 100,
    	                  min: 0,
    	                  max: 400,
    	                  fontColor: labelColor
    	                }
    	              }
    	            ]
    	          }
    	        },
    	        data: {
    	          labels: [
    	        	   <c:forEach items="${sendstatus }" var="row" varStatus="count">
    	        	  '${row.user_name }',
    	        	  </c:forEach>
    	        	  ],
    	          datasets: [
    	            {
    	              data: [
    	            	  <c:forEach items="${sendstatus }" var="row" varStatus="count">
    	            	 ${row.report_pn_statuserror},
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
    	    
    	
    	    
    	    // Horizontal Bar Chart
    	    // --------------------------------------------------------------------
    	     var horizontalBarChartEx = $('.horizontal-bar-chart-exs');
    	    if (horizontalBarChartEx.length) {
    	      new Chart(horizontalBarChartEx, {
    	        type: 'horizontalBar',
    	        options: {
    	          elements: {
    	            rectangle: {
    	              borderWidth: 2,
    	              borderSkipped: 'right'
    	            }
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
    	          responsive: true,
    	          maintainAspectRatio: false,
    	          responsiveAnimationDuration: 500,
    	          legend: {
    	            display: false
    	          },
    	          layout: {
    	            padding: {
    	              bottom: -30,
    	              left: -25
    	            }
    	          },
    	          scales: {
    	            xAxes: [
    	              {
    	                display: true,
    	                gridLines: {
    	                  zeroLineColor: grid_line_color,
    	                  borderColor: 'transparent',
    	                  color: grid_line_color
    	                },
    	                scaleLabel: {
    	                  display: true
    	                },
    	                ticks: {
    	                  min: 0,
    	                  fontColor: labelColor
    	                }
    	              }
    	            ],
    	            yAxes: [
    	              {
    	                display: true,
    	                gridLines: {
    	                  display: false
    	                },
    	                scaleLabel: {
    	                  display: true
    	                },
    	                ticks: {
    	                  fontColor: labelColor
    	                }
    	              }
    	            ]
    	          }
    	        },
    	        data: {
    	          labels: [
    	        	   <c:forEach items="${statuserror }" var="row" varStatus="count">
     	        	  '${row.user_name }',
     	        	  </c:forEach>  
    	          ],
    	          datasets: [
    	            {
    	              data: [
    	            	  <c:forEach items="${statuserror }" var="row" varStatus="count">
    	            	  ${row.report_pn_sendstatus},
         	        	  </c:forEach>  
    	              ],
    	              barThickness: 15,
    	              backgroundColor: window.colors.solid.info,
    	              borderColor: 'transparent'
    	            }
    	          ]
    	        }
    	      });
    	    }    
    	    
    	    
    	    
    	    
    	  
    	  // Employee Danger Chart
    	  var employeeDangerChart;  
  	    var employeeDangerChartOptions;
  	    var $employeeChartDanger = document.querySelector('.employee-task-chart-danger');
  	  
  	  employeeDangerChartOptions = {
  	    chart: {
  	      height: 30,
  	      width: 30,
  	      type: 'radialBar'
  	    },
  	    grid: {
  	      show: false,
  	      padding: {
  	        left: -15,
  	        right: -15,
  	        top: -12,
  	        bottom: -15
  	      }
  	    },
  	    colors: ["#8B0000"],
  	    series: [
    	      <c:forEach items="${reportC }" var="row" varStatus="count">
    	  	  < c:set var="totalpn" value="${row.report_pn_statussccess + row.report_pn_statuserror}" />
    	  	  
    	  		 <c:if test="${row.report_pn_statussccess > 0}"> 
    	  	    ${row.report_pn_statussccess * 100 / totalpn}
    	  	  </c:if> 
    			 <c:if test="${row.report_pn_statussccess == 0}"> 
    		  	   0
    		  	  </c:if> ,
    		  	 </c:forEach> 
  	    	],
  	    plotOptions: {
  	      radialBar: {
  	        hollow: {
  	          size: '22%'
  	        },
  	        track: {
  	          background: '#e9ecef'
  	        },
  	        dataLabels: {
  	          showOn: 'always',
  	          name: {
  	            show: false
  	          },
  	          value: {
  	            show: false
  	          }
  	        }
  	      }
  	    },
  	    stroke: {
  	      lineCap: 'round'
  	    }
  	  };
  	  
  	  employeeDangerChart = new ApexCharts($employeeChartDanger, employeeDangerChartOptions);
  	  employeeDangerChart.render();
    	  
    	  
    	  
    	  
    		 
    		 
    	  var flatPicker = $('.flat-picker'),
    	    isRtl = $('html').attr('data-textdirection') === 'rtl',
    	    chartColors = {
    	      column: {
    	        series1: '#826af9',
    	        series2: '#ff9f43',
    	        bg: '#f8d3ff'
    	      },
    	      success: {
    	        shade_100: '#7eefc7',
    	        shade_200: '#06774f'
    	      },
    	      donut: {
    	        series1: '#ffe700',
    	        series2: '#00d4bd',
    	        series3: '#826bf8',
    	        series4: '#2b9bf4',
    	        series5: '#FFA1A1'
    	      },
    	      area: {
    	        series3: '#a4f8cd',
    	        series2: '#60f2ca',
    	        series1: '#2bdac7'
    	      }
    	    };

    	  
    	  
    	  
    	  

    	  
    	  
    	  // heat chart data generator
    	  function generateDataHeat(count, yrange) {
    	    var i = 0;
    	    var series = [];
    	    while (i < count) {
    	      var x = 'w' + (i + 1).toString();
    	      var y = Math.floor(Math.random() * (yrange.max - yrange.min + 1)) + yrange.min;

    	      series.push({
    	        x: x,
    	        y: y
    	      });
    	      i++;
    	    }
    	    return series;
    	  }

    	  // Init flatpicker
    	  if (flatPicker.length) {
    	    var date = new Date();
    	    flatPicker.each(function () {
    	      $(this).flatpickr({
    	        mode: 'range',
    	        defaultDate: ['2019-05-01', '2019-05-10']
    	      });
    	    });
    	  }

    	  // Area Chart
    	  // --------------------------------------------------------------------
    	  var areaChartEl = document.querySelector('#line-area-chart'),
    	    areaChartConfig = {
    	      chart: {
    	        height: 400,
    	        type: 'area',
    	        parentHeightOffset: 0,
    	        toolbar: {
    	          show: false
    	        }
    	      },
    	      dataLabels: {
    	        enabled: false
    	      },
    	      stroke: {
    	        show: false,
    	        curve: 'straight'
    	      },
    	      legend: {
    	        show: true,
    	        position: 'top',
    	        horizontalAlign: 'start'
    	      },
    	      grid: {
    	        xaxis: {
    	          lines: {
    	            show: true
    	          }
    	        }
    	      },
    	      colors: [chartColors.area.series3, chartColors.area.series2, chartColors.area.series1],
    	      series: [
    	        {
    	          name: 'Visits',
    	          data: [100, 120, 90, 170, 130, 160, 140, 240, 220, 180, 270, 280, 375]
    	        },
    	        {
    	          name: 'Clicks',
    	          data: [60, 80, 70, 110, 80, 100, 90, 180, 160, 140, 200, 220, 275]
    	        },
    	        {
    	          name: 'Sales',
    	          data: [20, 40, 30, 70, 40, 60, 50, 140, 120, 100, 140, 180, 220]
    	        }
    	      ],
    	      xaxis: {
    	        categories: [
    	          '7/12',
    	          '8/12',
    	          '9/12',
    	          '10/12',
    	          '11/12',
    	          '12/12',
    	          '13/12',
    	          '14/12',
    	          '15/12',
    	          '16/12',
    	          '17/12',
    	          '18/12',
    	          '19/12',
    	          '20/12'
    	        ]
    	      },
    	      fill: {
    	        opacity: 1,
    	        type: 'solid'
    	      },
    	      tooltip: {
    	        shared: false
    	      },
    	      yaxis: {
    	        opposite: isRtl
    	      }
    	    };
    	  if (typeof areaChartEl !== undefined && areaChartEl !== null) {
    	    var areaChart = new ApexCharts(areaChartEl, areaChartConfig);
    	    areaChart.render();
    	  }

    	  // Column Chart
    	  // --------------------------------------------------------------------
    	  var columnChartEl = document.querySelector('#column-chart'),
    	    columnChartConfig = {
    	      chart: {
    	        height: 400,
    	        type: 'bar',
    	        stacked: true,
    	        parentHeightOffset: 1,
    	        toolbar: {
    	          show: false
    	        }
    	      },
    	      plotOptions: {
    	        bar: {
    	          columnWidth: '80%',
    	          colors: {
    	       
    	            backgroundBarRadius: 10
    	          }
    	        }
    	      },
    	      dataLabels: {
    	        enabled: false
    	      },
    	      legend: {
    	        show: true,
    	        position: 'top',
    	        horizontalAlign: 'start'
    	      },
    	      colors: [chartColors.column.series1, chartColors.column.series2],
    	      stroke: {
    	        show: true,
    	        colors: ['transparent']
    	      },
    	      grid: {
    	        xaxis: {
    	          lines: {
    	            show: true
    	          }
    	        }
    	      },
    	      series: [
    	        {
    	          name: 'เป้า',
    	          data: [${target[9]},${target[8]}, ${target[7]}, ${target[6]}, ${target[5]}, ${target[4]}, ${target[3]}, ${target[2]},${target[1]}, ${target[0]}]
    	        },
    	        {
    	          name: 'อัพโหลดจริง',
    	          data: [${total[9]}, ${total[8]}, ${total[7]}, ${total[6]}, ${total[5]}, ${total[4]}, ${total[3]}, ${total[2]}, ${total[1]}, ${total[0]}]
    	        }
    	      ],
    	      xaxis: {
    	        categories: ['${date[9]}', '${date[8]}', '${date[7]}', '${date[6]}', '${date[5]}', '${date[4]}', '${date[3]}', '${date[2]}', '${date[1]}', '${date[0]}']
    	      },
    	      fill: {
    	        opacity: 1
    	      },
    	      yaxis: {
    	        opposite: isRtl
    	      }
    	    };
    	  if (typeof columnChartEl !== undefined && columnChartEl !== null) {
    	    var columnChart = new ApexCharts(columnChartEl, columnChartConfig);
    	    columnChart.render();
    	  }


    	  
    	  // Column Chart
    	  // --------------------------------------------------------------------
    	  var columnChartEl = document.querySelector('#column-charts'),
    	    columnChartConfig = {
    	      chart: {
    	        height: 400,
    	        type: 'bar',
    	        stacked: true,
    	        parentHeightOffset: 0,
    	        toolbar: {
    	          show: false
    	        }
    	      },
    	      plotOptions: {
    	        bar: {
    	          columnWidth: '15%',
    	          colors: {
    	            backgroundBarColors: [
    	
    	            ],
    	            backgroundBarRadius: 10
    	          }
    	        }
    	      },
    	      dataLabels: {
    	        enabled: false
    	      },
    	      legend: {
    	        show: true,
    	        position: 'top',
    	        horizontalAlign: 'start'
    	      },
    	      colors: ['#00d4bd', chartColors.column.series2,'#C0392B'],
    	      stroke: {
    	        show: true,
    	        colors: ['transparent']
    	      },
    	      grid: {
    	        xaxis: {
    	          lines: {
    	            show: true
    	          }
    	        }
    	      },
    	      series: [
    	        {
    	          name: 'จำนวนบันทึกที่ส่งตามเวลา',
    	          data: [${total3[9]}, ${total3[8]}, ${total3[7]}, ${total3[6]}, ${total3[5]}, ${total3[4]}, ${total3[3]}, ${total3[2]}, ${total3[1]}, ${total3[0]}]
    	        },
    	        {
    	          name: 'จำนวนบันทึกที่มีการส่งแต่ไม่ตามเวลา',
    	          data: [${total2[9]}, ${total2[8]}, ${total2[7]}, ${total2[6]}, ${total2[5]}, ${total2[4]}, ${total2[3]}, ${total2[2]}, ${total2[1]}, ${total2[0]}]
    	        }
    	        ,
    	        {
    	          name: 'จำนวนบันทึกที่ขาดส่ง',
    	          data: [${total4[9]}, ${total4[8]}, ${total4[7]}, ${total4[6]}, ${total4[5]}, ${total4[4]}, ${total4[3]}, ${total4[2]}, ${total4[1]}, ${total4[0]}]
    	        }
    	      ],
    	      xaxis: {
    	        categories: ['${date[9]}', '${date[8]}', '${date[7]}', '${date[6]}', '${date[5]}', '${date[4]}', '${date[3]}', '${date[2]}', '${date[1]}', '${date[0]}']
    	      },
    	      fill: {
    	        opacity: 1
    	      },
    	      yaxis: {
    	        opposite: isRtl
    	      }
    	    };
    	  if (typeof columnChartEl !== undefined && columnChartEl !== null) {
    	    var columnChart = new ApexCharts(columnChartEl, columnChartConfig);
    	    columnChart.render();
    	  }
    	  
    	  
    	  
    	  
    	  // Scatter Chart
    	  // --------------------------------------------------------------------
    	  var scatterChartEl = document.querySelector('#scatter-chart'),
    	    scatterChartConfig = {
    	      chart: {
    	        height: 400,
    	        type: 'scatter',
    	        zoom: {
    	          enabled: true,
    	          type: 'xy'
    	        },
    	        parentHeightOffset: 0,
    	        toolbar: {
    	          show: false
    	        }
    	      },
    	      grid: {
    	        xaxis: {
    	          lines: {
    	            show: true
    	          }
    	        }
    	      },
    	      legend: {
    	        show: true,
    	        position: 'top',
    	        horizontalAlign: 'start'
    	      },
    	      colors: [window.colors.solid.warning, window.colors.solid.primary, window.colors.solid.success],
    	      series: [
    	        {
    	          name: 'Angular',
    	          data: [
    	            [5.4, 170],
    	            [5.4, 100],
    	            [6.3, 170],
    	            [5.7, 140],
    	            [5.9, 130],
    	            [7.0, 150],
    	            [8.0, 120],
    	            [9.0, 170],
    	            [10.0, 190],
    	            [11.0, 220],
    	            [12.0, 170],
    	            [13.0, 230]
    	          ]
    	        },
    	        {
    	          name: 'Vue',
    	          data: [
    	            [14.0, 220],
    	            [15.0, 280],
    	            [16.0, 230],
    	            [18.0, 320],
    	            [17.5, 280],
    	            [19.0, 250],
    	            [20.0, 350],
    	            [20.5, 320],
    	            [20.0, 320],
    	            [19.0, 280],
    	            [17.0, 280],
    	            [22.0, 300],
    	            [18.0, 120]
    	          ]
    	        },
    	        {
    	          name: 'React',
    	          data: [
    	            [14.0, 290],
    	            [13.0, 190],
    	            [20.0, 220],
    	            [21.0, 350],
    	            [21.5, 290],
    	            [22.0, 220],
    	            [23.0, 140],
    	            [19.0, 400],
    	            [20.0, 200],
    	            [22.0, 90],
    	            [20.0, 120]
    	          ]
    	        }
    	      ],
    	      xaxis: {
    	        tickAmount: 10,
    	        labels: {
    	          formatter: function (val) {
    	            return parseFloat(val).toFixed(1);
    	          }
    	        }
    	      },
    	      yaxis: {
    	        opposite: isRtl
    	      }
    	    };
    	  if (typeof scatterChartEl !== undefined && scatterChartEl !== null) {
    	    var scatterChart = new ApexCharts(scatterChartEl, scatterChartConfig);
    	    scatterChart.render();
    	  }

    	  // Line Chart
    	  // --------------------------------------------------------------------
    	  var lineChartEl = document.querySelector('#line-chart'),
    	    lineChartConfig = {
    	      chart: {
    	        height: 400,
    	        type: 'line',
    	        zoom: {
    	          enabled: false
    	        },
    	        parentHeightOffset: 0,
    	        toolbar: {
    	          show: false
    	        }
    	      },
    	      series: [
    	        {
    	          data: [280, 200, 220, 180, 270, 250, 70, 90, 200, 150, 160, 100, 150, 100, 50]
    	        }
    	      ],
    	      markers: {
    	        strokeWidth: 7,
    	        strokeOpacity: 1,
    	        strokeColors: [window.colors.solid.white],
    	        colors: [window.colors.solid.warning]
    	      },
    	      dataLabels: {
    	        enabled: false
    	      },
    	      stroke: {
    	        curve: 'straight'
    	      },
    	      colors: [window.colors.solid.warning],
    	      grid: {
    	        xaxis: {
    	          lines: {
    	            show: true
    	          }
    	        },
    	        padding: {
    	          top: -20
    	        }
    	      },
    	      tooltip: {
    	        custom: function (data) {
    	          return (
    	            '<div class="px-1 py-50">' +
    	            '<span>' +
    	            data.series[data.seriesIndex][data.dataPointIndex] +
    	            '%</span>' +
    	            '</div>'
    	          );
    	        }
    	      },
    	      xaxis: {
    	        categories: [
    	          '7/12',
    	          '8/12',
    	          '9/12',
    	          '10/12',
    	          '11/12',
    	          '12/12',
    	          '13/12',
    	          '14/12',
    	          '15/12',
    	          '16/12',
    	          '17/12',
    	          '18/12',
    	          '19/12',
    	          '20/12',
    	          '21/12'
    	        ]
    	      },
    	      yaxis: {
    	        opposite: isRtl
    	      }
    	    };
    	  if (typeof lineChartEl !== undefined && lineChartEl !== null) {
    	    var lineChart = new ApexCharts(lineChartEl, lineChartConfig);
    	    lineChart.render();
    	  }

    	  // Bar Chart
    	  // --------------------------------------------------------------------
    	  var barChartEl = document.querySelector('#bar-chart'),
    	    barChartConfig = {
    	      chart: {
    	        height: 300,
    	        type: 'bar',
    	        parentHeightOffset: 0,
    	        toolbar: {
    	          show: false
    	        }
    	      },
    	      plotOptions: {
    	        bar: {
    	          horizontal: true,
    	          barHeight: '30%',
    	          endingShape: 'rounded'
    	        }
    	      },
    	      grid: {
    	        xaxis: {
    	          lines: {
    	            show: false
    	          }
    	        },
    	        padding: {
    	          top: -15,
    	          bottom: -10
    	        }
    	      },
    	      colors: window.colors.solid.info,
    	      dataLabels: {
    	        enabled: false
    	      },
    	      series: [
    	        {
    	          data: [${doctotal_qm},${doctotal_qp},${doctotal_wi},${doctotal_sd},${doctotal_fo}]
    	        }
    	      ],
    	      xaxis: {
    	          categories: ['Quality Manual (QM)', 'Quality Procedure (QP)', 'Work Instruction (WI)', 'Supporting Data (SD)', 'Form (FO)']
    	       
    	      },
    	      yaxis: {
    	        opposite: isRtl
    	      }
    	    };
    	  if (typeof barChartEl !== undefined && barChartEl !== null) {
    	    var barChart = new ApexCharts(barChartEl, barChartConfig);
    	    barChart.render();
    	  }

    	  // Candlestick Chart
    	  // --------------------------------------------------------------------
    	  var candlestickEl = document.querySelector('#candlestick-chart'),
    	    candlestickChartConfig = {
    	      chart: {
    	        height: 400,
    	        type: 'candlestick',
    	        parentHeightOffset: 0,
    	        toolbar: {
    	          show: false
    	        }
    	      },
    	      series: [
    	        {
    	          data: [
    	            {
    	              x: new Date(1538778600000),
    	              y: [150, 170, 50, 100]
    	            },
    	            {
    	              x: new Date(1538780400000),
    	              y: [200, 400, 170, 330]
    	            },
    	            {
    	              x: new Date(1538782200000),
    	              y: [330, 340, 250, 280]
    	            },
    	            {
    	              x: new Date(1538784000000),
    	              y: [300, 330, 200, 320]
    	            },
    	            {
    	              x: new Date(1538785800000),
    	              y: [320, 450, 280, 350]
    	            },
    	            {
    	              x: new Date(1538787600000),
    	              y: [300, 350, 80, 250]
    	            },
    	            {
    	              x: new Date(1538789400000),
    	              y: [200, 330, 170, 300]
    	            },
    	            {
    	              x: new Date(1538791200000),
    	              y: [200, 220, 70, 130]
    	            },
    	            {
    	              x: new Date(1538793000000),
    	              y: [220, 270, 180, 250]
    	            },
    	            {
    	              x: new Date(1538794800000),
    	              y: [200, 250, 80, 100]
    	            },
    	            {
    	              x: new Date(1538796600000),
    	              y: [150, 170, 50, 120]
    	            },
    	            {
    	              x: new Date(1538798400000),
    	              y: [110, 450, 10, 420]
    	            },
    	            {
    	              x: new Date(1538800200000),
    	              y: [400, 480, 300, 320]
    	            },
    	            {
    	              x: new Date(1538802000000),
    	              y: [380, 480, 350, 450]
    	            }
    	          ]
    	        }
    	      ],
    	      xaxis: {
    	        type: 'datetime'
    	      },
    	      yaxis: {
    	        tooltip: {
    	          enabled: true
    	        },
    	        opposite: isRtl
    	      },
    	      grid: {
    	        xaxis: {
    	          lines: {
    	            show: true
    	          }
    	        },
    	        padding: {
    	          top: -23
    	        }
    	      },
    	      plotOptions: {
    	        candlestick: {
    	          colors: {
    	            upward: window.colors.solid.success,
    	            downward: window.colors.solid.danger
    	          }
    	        },
    	        bar: {
    	          columnWidth: '40%'
    	        }
    	      }
    	    };
    	  if (typeof candlestickEl !== undefined && candlestickEl !== null) {
    	    var candlestickChart = new ApexCharts(candlestickEl, candlestickChartConfig);
    	    candlestickChart.render();
    	  }

    	  // Heat map chart
    	  // --------------------------------------------------------------------
    	  var heatmapEl = document.querySelector('#heatmap-chart'),
    	    heatmapChartConfig = {
    	      chart: {
    	        height: 350,
    	        type: 'heatmap',
    	        parentHeightOffset: 0,
    	        toolbar: {
    	          show: false
    	        }
    	      },
    	      plotOptions: {
    	        heatmap: {
    	          enableShades: false,

    	          colorScale: {
    	            ranges: [
    	              {
    	                from: 0,
    	                to: 10,
    	                name: '0-10',
    	                color: '#b9b3f8'
    	              },
    	              {
    	                from: 11,
    	                to: 20,
    	                name: '10-20',
    	                color: '#aba4f6'
    	              },
    	              {
    	                from: 21,
    	                to: 30,
    	                name: '20-30',
    	                color: '#9d95f5'
    	              },
    	              {
    	                from: 31,
    	                to: 40,
    	                name: '30-40',
    	                color: '#8f85f3'
    	              },
    	              {
    	                from: 41,
    	                to: 50,
    	                name: '40-50',
    	                color: '#8176f2'
    	              },
    	              {
    	                from: 51,
    	                to: 60,
    	                name: '50-60',
    	                color: '#7367f0'
    	              }
    	            ]
    	          }
    	        }
    	      },
    	      dataLabels: {
    	        enabled: false
    	      },
    	      legend: {
    	        show: true,
    	        position: 'bottom'
    	      },
    	      grid: {
    	        padding: {
    	          top: -25
    	        }
    	      },
    	      series: [
    	        {
    	          name: 'SUN',
    	          data: generateDataHeat(24, {
    	            min: 0,
    	            max: 60
    	          })
    	        },
    	        {
    	          name: 'MON',
    	          data: generateDataHeat(24, {
    	            min: 0,
    	            max: 60
    	          })
    	        },
    	        {
    	          name: 'TUE',
    	          data: generateDataHeat(24, {
    	            min: 0,
    	            max: 60
    	          })
    	        },
    	        {
    	          name: 'WED',
    	          data: generateDataHeat(24, {
    	            min: 0,
    	            max: 60
    	          })
    	        },
    	        {
    	          name: 'THU',
    	          data: generateDataHeat(24, {
    	            min: 0,
    	            max: 60
    	          })
    	        },
    	        {
    	          name: 'FRI',
    	          data: generateDataHeat(24, {
    	            min: 0,
    	            max: 60
    	          })
    	        },
    	        {
    	          name: 'SAT',
    	          data: generateDataHeat(24, {
    	            min: 0,
    	            max: 60
    	          })
    	        }
    	      ],
    	      xaxis: {
    	        labels: {
    	          show: false
    	        },
    	        axisBorder: {
    	          show: false
    	        },
    	        axisTicks: {
    	          show: false
    	        }
    	      }
    	    };
    	  if (typeof heatmapEl !== undefined && heatmapEl !== null) {
    	    var heatmapChart = new ApexCharts(heatmapEl, heatmapChartConfig);
    	    heatmapChart.render();
    	  }

    	  // Radialbar Chart
    	  // --------------------------------------------------------------------
    	  var radialBarChartEl = document.querySelector('#radialbar-chart'),
    	    radialBarChartConfig = {
    	      chart: {
    	        height: 350,
    	        type: 'radialBar'
    	      },
    	      colors: [chartColors.donut.series1, chartColors.donut.series2, chartColors.donut.series4],
    	      plotOptions: {
    	        radialBar: {
    	          size: 185,
    	          hollow: {
    	            size: '25%'
    	          },
    	          track: {
    	            margin: 15
    	          },
    	          dataLabels: {
    	            name: {
    	              fontSize: '2rem',
    	              fontFamily: 'Montserrat'
    	            },
    	            value: {
    	              fontSize: '1rem',
    	              fontFamily: 'Montserrat'
    	            },
    	            total: {
    	              show: true,
    	              fontSize: '1rem',
    	              label: 'Comments',
    	              formatter: function (w) {
    	                return '80%';
    	              }
    	            }
    	          }
    	        }
    	      },
    	      grid: {
    	        padding: {
    	          top: -35,
    	          bottom: -30
    	        }
    	      },
    	      legend: {
    	        show: true,
    	        position: 'bottom'
    	      },
    	      stroke: {
    	        lineCap: 'round'
    	      },
    	      series: [80, 50, 35],
    	      labels: ['Comments', 'Replies', 'Shares']
    	    };
    	  if (typeof radialBarChartEl !== undefined && radialBarChartEl !== null) {
    	    var radialChart = new ApexCharts(radialBarChartEl, radialBarChartConfig);
    	    radialChart.render();
    	  }

    	  // Radar Chart
    	  // --------------------------------------------------------------------
    	  var radarChartEl = document.querySelector('#radar-chart'),
    	    radarChartConfig = {
    	      chart: {
    	        height: 400,
    	        type: 'radar',
    	        toolbar: {
    	          show: false
    	        },
    	        parentHeightOffset: 0,
    	        dropShadow: {
    	          enabled: false,
    	          blur: 8,
    	          left: 1,
    	          top: 1,
    	          opacity: 0.2
    	        }
    	      },
    	      legend: {
    	        show: true,
    	        position: 'bottom'
    	      },
    	      yaxis: {
    	        show: false
    	      },
    	      series: [
    	        {
    	          name: 'iPhone 11',
    	          data: [41, 64, 81, 60, 42, 42, 33, 23]
    	        },
    	        {
    	          name: 'Samsung s20',
    	          data: [65, 46, 42, 25, 58, 63, 76, 43]
    	        }
    	      ],
    	      colors: [chartColors.donut.series1, chartColors.donut.series3],
    	      xaxis: {
    	        categories: ['Battery', 'Brand', 'Camera', 'Memory', 'Storage', 'Display', 'OS', 'Price']
    	      },
    	      fill: {
    	        opacity: [1, 0.8]
    	      },
    	      stroke: {
    	        show: false,
    	        width: 0
    	      },
    	      markers: {
    	        size: 0
    	      },
    	      grid: {
    	        show: false,
    	        padding: {
    	          top: -20,
    	          bottom: -20
    	        }
    	      }
    	    };
    	  if (typeof radarChartEl !== undefined && radarChartEl !== null) {
    	    var radarChart = new ApexCharts(radarChartEl, radarChartConfig);
    	    radarChart.render();
    	  }

    	  
    	  
    	  
    	  
    	  // Donut Chart
    	  // --------------------------------------------------------------------
    	  var donutChartEl = document.querySelector('#donut-chart'),
    	    donutChartConfig = {
    	      chart: {
    	        height: 350,
    	        type: 'donut'
    	      },
    	      legend: {
    	        show: true,
    	        position: 'bottom'
    	      },
    	      labels: ['Operational', 'Networking', 'Hiring', 'R&D'],
    	      series: [85, 16, 50, 50],
    	      colors: [
    	        chartColors.donut.series1,
    	        chartColors.donut.series5,
    	        chartColors.donut.series3,
    	        chartColors.donut.series2
    	      ],
    	      dataLabels: {
    	        enabled: true,
    	        formatter: function (val, opt) {
    	          return parseInt(val) + '%';
    	        }
    	      },
    	      plotOptions: {
    	        pie: {
    	          donut: {
    	            labels: {
    	              show: true,
    	              name: {
    	                fontSize: '2rem',
    	                fontFamily: 'Montserrat'
    	              },
    	              value: {
    	                fontSize: '1rem',
    	                fontFamily: 'Montserrat',
    	                formatter: function (val) {
    	                  return parseInt(val) + '%';
    	                }
    	              },
    	              total: {
    	                show: true,
    	                fontSize: '1.5rem',
    	                label: 'Operational',
    	                formatter: function (w) {
    	                  return '31%';
    	                }
    	              }
    	            }
    	          }
    	        }
    	      },
    	      responsive: [
    	        {
    	          breakpoint: 992,
    	          options: {
    	            chart: {
    	              height: 380
    	            }
    	          }
    	        },
    	        {
    	          breakpoint: 576,
    	          options: {
    	            chart: {
    	              height: 320
    	            },
    	            plotOptions: {
    	              pie: {
    	                donut: {
    	                  labels: {
    	                    show: true,
    	                    name: {
    	                      fontSize: '1.5rem'
    	                    },
    	                    value: {
    	                      fontSize: '1rem'
    	                    },
    	                    total: {
    	                      fontSize: '1.5rem'
    	                    }
    	                  }
    	                }
    	              }
    	            }
    	          }
    	        }
    	      ]
    	    };
    	  if (typeof donutChartEl !== undefined && donutChartEl !== null) {
    	    var donutChart = new ApexCharts(donutChartEl, donutChartConfig);
    	    donutChart.render();
    	  }
    	  
       
    	 
    	  var  polarAreaChartEx = $('.polar-area-chart-ex');
			// --------------------------------------------------------------------
			  if (polarAreaChartEx.length) {
			    var polarExample = new Chart(polarAreaChartEx, {
			      type: 'polarArea',
			      options: {
			        responsive: true,
			        maintainAspectRatio: false,
			        responsiveAnimationDuration: 500,
			        legend: {
			          position: 'right',
			          labels: {
			            usePointStyle: true,
			            padding: 25,
			            boxWidth: 9,
			            fontColor: '#6e6b7b'
			          }
			        },
			        layout: {
			          padding: {
			            top: -5,
			            bottom: -45
			          }
			        },
			        tooltips: {
			          // Updated default tooltip UI
			          shadowOffsetX: 1,
			          shadowOffsetY: 1,
			          shadowBlur: 8,
			          shadowColor: 'rgba(0, 0, 0, 0.25)',
			          backgroundColor: '#F8F6F5',
			          titleFontColor:'#0C0C0C',
			          bodyFontColor: '#0C0C0C'
			        },
			        scale: {
			          scaleShowLine: true,
			          scaleLineWidth: 1,
			          ticks: {
			            display: false,
			            fontColor: '#6e6b7b'
			          },
			          reverse: false,
			          gridLines: {
			            display: false
			          }
			        },
			        animation: {
			          animateRotate: false
			        }
			      },
			      data: {
			        labels: ['QM', 'QP', 'WI', 'SD', 'FO'],
			        datasets: [
			          {
			            label: 'Population (millions)',
			            backgroundColor: [
			            	'#836AF9',
			            	'#ffe802',
			            	'#E82121',
			              '#299AFF',
			              '#4F5D70'
			            ],
			            data: ['${expiredqm}','${expiredqp}','${expiredwi}','${expiredsd}','${expiredfo}'],
			            borderWidth: 0
			          }
			        ]
			      }
			    });
			  }
    	  
    	  
    	  
    	  
    	});
    
    
    
    
    
    
    
        $(window).on('load', function() {
        	
          	
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        	
            var $barColor = '#f3f3f3';
            var $trackBgColor = '#EBEBEB';
            var $textMutedColor = '#b9b9c3';
            var $budgetStrokeColor2 = '#dcdae3';
            var $goalStrokeColor2 = '#51e5a8';
            var $strokeColor = '#ebe9f1';
            var $textHeadingColor = '#5e5873';
            var $earningsStrokeColor2 = '#28c76f66';
            var $earningsStrokeColor3 = '#28c76f33';

        	 var $goalOverviewChart = document.querySelector('#goal-overview-radial-bar-chart');
    		 var goalOverviewChartOptions;
    		 
    		 goalOverviewChartOptions = {
    				    chart: {
    				      height: 245,
    				      type: 'radialBar',
    				      sparkline: {
    				        enabled: true
    				      },
    				      dropShadow: {
    				        enabled: true,
    				        blur: 3,
    				        left: 1,
    				        top: 1,
    				        opacity: 0.1
    				      }
    				    },
    				    colors: [$goalStrokeColor2],
    				    plotOptions: {
    				      radialBar: {
    				        offsetY: -10,
    				        startAngle: -150,
    				        endAngle: 150,
    				        hollow: {
    				          size: '77%'
    				        },
    				        track: {
    				          background: $strokeColor,
    				          strokeWidth: '50%'
    				        },
    				        dataLabels: {
    				          name: {
    				            show: false
    				          },
    				          value: {
    				            color: $textHeadingColor,
    				            fontSize: '2.86rem',
    				            fontWeight: '600'
    				          }
    				        }
    				      }
    				    },
    				    fill: {
    				      type: 'gradient',
    				      gradient: {
    				        shade: 'dark',
    				        type: 'horizontal',
    				        shadeIntensity: 0.5,
    				        gradientToColors: [window.colors.solid.success],
    				        inverseColors: true,
    				        opacityFrom: 1,
    				        opacityTo: 1,
    				        stops: [0, 100]
    				      }
    				    },
    				    series: [${drives_percent}],
    				    stroke: {
    				      lineCap: 'round'
    				    },
    				    grid: {
    				      padding: {
    				        bottom: 30
    				      }
    				    }
    				  };
    				  goalOverviewChart = new ApexCharts($goalOverviewChart, goalOverviewChartOptions);
    				  goalOverviewChart.render();
        	
      
    				  
    				
    				  
    				  
        })
 
        
  var element = document.getElementById("nav_main");
  element.classList.add("active");

    </script>
</body>


