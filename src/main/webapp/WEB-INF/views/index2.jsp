<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="common/mainhead.jspf" %> 
   <link href="<c:url value="/resources/app-assets/css/plugins/charts/chart-apex.min.css" />" rel="stylesheet">   

<title>index </title>
<style>
.chartdiv {
  width: 100%;
  height: 500px;
}

.chartdiv2 {
  width: 100%;

}


#chartdiv2 {
  width: 100%;
  height: 500px;
}

#chartdiv3 {
  width: 100%;
  height: 500px;
}

#chartdivnew1 {
  width: 100%;
  height: 300px;
}
#chartdivnew2 {
  width: 100%;
  height: 300px;
}
#chartdivnew3 {
  width: 100%;
  height: 300px;
}
</style>
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
          <h4 class="card-title"><spring:message code="menu.Specific" text="กราฟเฉพาะทาง" /></h4>
          <div class="d-flex align-items-center">
           
          </div>
        </div>
        <div class="card-body statistics-body">
          <div class="row">
          
              <div class="col-xl-4 col-sm-6 col-12 mb-2 mb-xl-0">
              <div class="d-flex flex-row">
                <div class="avatar bg-light-info me-2">
                  <div class="avatar-content">
                    <i data-feather="book" class="avatar-icon"></i>
                
                  </div>
                </div>
                <div class="my-auto">
                  <h4 class="fw-bolder mb-0">${dartotal }</h4>
                  <p class="card-text font-small-3 mb-0"><spring:message code="menu.alldar" text="DAR ทั้งหมด" /></p>
                </div>
              </div>
            </div>
          
            <div class="col-xl-4 col-sm-6 col-12 mb-2 mb-xl-0">
              <div class="d-flex flex-row">
                <div class="avatar bg-light-danger  me-2">
                  <div class="avatar-content">
                
                    <i data-feather='book'  class="avatar-icon"></i>
                  </div>
                </div>
                <div class="my-auto">
                  <h4 class="fw-bolder mb-0">${dartotal_unfinished }</h4>
                  <p class="card-text font-small-3 mb-0"><spring:message code="menu.pending" text="DAR ที่คงค้าง" /></p>
                </div>
              </div>
            </div>
        
            <div class="col-xl-4 col-sm-6 col-12 mb-2 mb-sm-0">
              <div class="d-flex flex-row">
                <div class="avatar bg-light-primary me-2">
                  <div class="avatar-content">

                    <i data-feather='download' class="avatar-icon"></i>
                  </div>
                </div>
                <div class="my-auto">
                  <h4 class="fw-bolder mb-0">${doc }</h4>
                           <p class="card-text font-small-3 mb-0"><spring:message code="menu.alldar" text="เอกสารถูก Download (เดือน)" /></p>
                </div>
              </div>
            </div>
     
          </div>
        </div>
      </div>
    </div>
 
                        
                
                     
                    </div>
                
                   <div class="row match-height">
                        <!-- Avg Sessions Chart Card starts -->
                       
                            <div class="col-xl-5 col-12">
                          <div class="card">
                          
                          
                          
                              <div class="card-header d-flex justify-content-between align-items-baseline align-items-sm-center flex-sm-row flex-column">
                                    <h4 class="card-title"> <spring:message code="menu.chart6" text="การ Login ใช้งาน" /></h4>
                                    <div class="btn-group mt-md-0 mt-1" role="group" aria-label="Basic radio toggle button group">
                                        <input type="radio" class="btn-check" name="radios" id="sradio_option1s" autocomplete="off" checked  onclick="show('body1','body2','body3')"/>
                                        <label class="btn btn-outline-primary" for="sradio_option1s">Daily</label>

                                        <input type="radio" class="btn-check" name="radios" id="sradio_option2s" autocomplete="off" onclick="show('body2','body3','body1')"/>
                                        <label class="btn btn-outline-primary" for="sradio_option2s">Monthly</label>

                                        <input type="radio" class="btn-check" name="radios" id="sradio_option3s" autocomplete="off" onclick="show('body3','body2','body1')"/>
                                        <label class="btn btn-outline-primary" for="sradio_option3s">Yearly</label>
                                    </div>
                                </div>

                                
                                <div class="card-body">
                               
                               
                                  <div id="body1">
                                     <a  href="/logattendance-filemanager?type=day" class="btn btn-sm btn-light"><i class="ficon" data-feather="database"></i></a>
                                   <div id="pie1" class="chartdiv2"></div>
                                 
                                  </div>
                                 <div id="body2" style="display:none">
                           <a  href="/logattendance-filemanager?type=month" class="btn btn-sm btn-light"><i class="ficon" data-feather="database"></i></a>
                                   <div id="pie2" class="chartdiv2"></div>
                                  </div>  
                                 <div id="body3" style="display:none">
                              <a  href="/logattendance-filemanager?type=year" class="btn btn-sm btn-light"><i class="ficon" data-feather="database"></i></a>
                                   <div id="pie3" class="chartdiv2"></div>
                                  </div>       
                                  
                   
                                   

                                </div>
                                
                                
                                
                            </div>
                        </div>    
   
             <div class="col-xl-7 col-12">
                          <div class="card">
                          
                          
                          
                              <div class="card-header d-flex justify-content-between align-items-baseline align-items-sm-center flex-sm-row flex-column">
                                    <h4 class="card-title"><spring:message code="menu.chart7" text="การ Login ใช้งานของพนักงานระดับต่างๆ" /></h4>
                                    <div class="btn-group mt-md-0 mt-1" role="group" aria-label="Basic radio toggle button group">
                                        <input type="radio" class="btn-check" name="radio_options1" id="radio_option1s" autocomplete="off" checked  onclick="show('body1_1','body1_2','body1_3')"/>
                                        <label class="btn btn-outline-primary" for="radio_option1s">Daily</label>

                                        <input type="radio" class="btn-check" name="radio_options1" id="radio_option2s" autocomplete="off" onclick="show('body1_2','body1_3','body1_1')"/>
                                        <label class="btn btn-outline-primary" for="radio_option2s">Monthly</label>

                                        <input type="radio" class="btn-check" name="radio_options1" id="radio_option3s" autocomplete="off" onclick="show('body1_3','body1_2','body1_1')"/>
                                        <label class="btn btn-outline-primary" for="radio_option3s">Yearly</label>
                                    </div>
                                </div>

                                
                                <div class="card-body">
                               
                               
                                  <div id="body1_1">
                                          <a  href="/logattendance-filemanager?type=day" class="btn btn-sm btn-light"><i class="ficon" data-feather="database"></i></a>
                                   <div id="chartdiv_1" class="chartdiv"></div>
                                  </div>
                                 <div id="body1_2" style="display:none">
                                     <a  href="/logattendance-filemanager?type=month" class="btn btn-sm btn-light"><i class="ficon" data-feather="database"></i></a>
                                   <div id="chartdiv_2" class="chartdiv"></div>
                                  </div>  
                                 <div id="body1_3" style="display:none">
                                    <a  href="/logattendance-filemanager?type=year" class="btn btn-sm btn-light"><i class="ficon" data-feather="database"></i></a>
                                   <div id="chartdiv_3" class="chartdiv"></div>
                                  </div>       
                                  
                   
                                   

                                </div>
                                
                                
                                
                            </div>
                        </div>   
            <!-- Support Tracker Chart Card starts -->
                        <div class="col-lg-12 col-12">
                        <div class="card">
                        
                        
    <div class="
            card-header
            d-flex
            flex-md-row flex-column
            justify-content-md-between justify-content-start
            align-items-md-center align-items-start
          ">
               
                                    <div>
                                        <h4 class="card-title"><spring:message code="menu.chart8" text="การสร้างใบ DAR ตามเหตุผล" /></h4>
                                        
                                  
                                        
                                        
                                        <span class="card-subtitle text-muted"></span>
                                    </div>
                                    <div class="d-flex align-items-center flex-wrap mt-sm-0 mt-1">
                                        <h5 class="fw-bolder mb-0 me-1"></h5>
                                              <div style="float:right" class="btn-group mt-md-0 mt-1 " role="group" aria-label="Basic radio toggle button group">
                                      
                                        <input type="radio" class="btn-check" name="chartdiv3" id="radio_option2" autocomplete="off" checked onclick="show('body2_1','body2_2','body2_3')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_option2">Monthly</label>

                                        <input type="radio" class="btn-check" name="chartdiv3" id="radio_option3" autocomplete="off" onclick="show('body2_2','body2_1','body2_3')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_option3">Yearly</label>
                                    </div>
                                    </div>
                                </div>
                                
                                
                                
                                <div class="card-body">
                               
                                       <div id="body2_1">
                          
                                    <div id="chartdiv3_1"  class="chartdiv" ></div>
                                  </div>
                                      <div id="body2_2" style="display:none">
                               <div id="chartdiv3_2"  class="chartdiv" ></div>
                                  </div>
                                </div>
                            </div>
                     
                      </div>
                        <!-- Support Tracker Chart Card ends -->
                              <div class="col-lg-12 col-12" style="display:none">
                        <div class="card">
    <div class="
            card-header
            d-flex
            flex-md-row flex-column
            justify-content-md-between justify-content-start
            align-items-md-center align-items-start
          ">
               
                                    <div>
                                        <h4 class="card-title"><spring:message code="menu.chart9" text="เอกสารที่ครบอายุการจัดเก็บในแต่ละปี" /></h4>
                                        
                                  
                                        
                                        
                                        <span class="card-subtitle text-muted"></span>
                                    </div>
                            
                                </div>
                                
                                
                                
                                <div class="card-body">
                                <div id="chartdiv2" ></div>
                                  
                                  
                                  
                                  
                                </div>
                            </div>
                     
                      </div>      
                 <div class="col-lg-7 col-12">
                 
                                  <div class="card">
                                <div class="
            card-header
            d-flex
            flex-md-row flex-column
            justify-content-md-between justify-content-start
            align-items-md-center align-items-start
          ">
                                    <h4 class="card-title"><spring:message code="menu.chart10" text="ภาพรวมของการส่งบันทึก" /></h4>
                                
                                </div>
                                <div class="card-body">
                              
                                  <a  href="/details_pn_due-filemanager?type=week"  class="btn btn-sm btn-light"><i class="ficon" data-feather="database"></i></a>
                                    <div id="column-charts" ></div>
                                </div>
                            </div>
                 
                     </div>
                  <!-- Support Tracker Chart Card starts -->
                        <div class="col-lg-5 col-12">
                        
                        
                        <div class="card">
                        
                   
                          
                              <div class="card-header d-flex justify-content-between align-items-baseline align-items-sm-center flex-sm-row flex-column">
                                    <h4 class="card-title"><spring:message code="menu.chart11" text="ล่าช้า 5 อันดับแรก" /></h4>
                                    <div class="btn-group mt-md-0 mt-1" role="group" aria-label="Basic radio toggle button group">
                                     
                                        <input type="radio" class="btn-check" name="exsy" id="bar-chart-exsy1" autocomplete="off"  checked onclick="show('body6_1','body6_2','body6_3')" />
                                        <label class="btn btn-outline-primary" for="bar-chart-exsy1">Monthly</label>

                                        <input type="radio" class="btn-check" name="exsy" id="bar-chart-exsy2" autocomplete="off" onclick="show('body6_2','body6_3','body6_1')"/>
                                        <label class="btn btn-outline-primary" for="bar-chart-exsy2">Yearly</label>
                                    </div>
                                </div>
                        
                          
                                <div class="card-body">
                                   
                                    
                                           <div id="body6_1">
                              <canvas class="bar-chart-exs1 chartjs" data-height="400" style="height:200px"></canvas>
                                  </div>
                                 <div id="body6_2">
                                <canvas class="bar-chart-exs2 chartjs" data-height="400" style="height:200px"></canvas>
                                  </div>  
                                     
                                  
                                    
                                    
                                </div>
                            </div>
                              <div class="card">
                       
                                
                                    <div class="card-header d-flex justify-content-between align-items-baseline align-items-sm-center flex-sm-row flex-column">
                                    <h4 class="card-title"><spring:message code="menu.chart12" text="ขาดส่งรายงาน 5 อันดับแรก" /></h4>
                                    <div class="btn-group mt-md-0 mt-1" role="group" aria-label="Basic radio toggle button group">
                                     
                                        <input type="radio" class="btn-check" name="chart-exs" id="chart-exs1" autocomplete="off"  checked onclick="show('body7_1','body7_2','body7_3')" />
                                        <label class="btn btn-outline-primary" for="chart-exs1">Monthly</label>

                                        <input type="radio" class="btn-check" name="chart-exs" id="chart-exs2" autocomplete="off" onclick="show('body7_2','body7_3','body7_1')"/>
                                        <label class="btn btn-outline-primary" for="chart-exs2">Yearly</label>
                                    </div>
                                </div>
                                <div class="card-body">
                                
                                    
                                             <div id="body7_1">
                           
                                  <canvas class="horizontal-bar-chart-exs1 chartjs" data-height="400" style="height:250px"></canvas>
                                  </div>
                                 <div id="body7_2">
                              
                                    <canvas class="horizontal-bar-chart-exs2 chartjs" data-height="400" style="height:250px"></canvas>
                                  </div>  
                                     
                                </div>
                            </div>
                      
                      </div>
                        <!-- Support Tracker Chart Card ends -->
       
       
       
        <div class="col-lg-6 col-12">  
          <div class="card">
    <div class="
            card-header
            d-flex
            flex-md-row flex-column
            justify-content-md-between justify-content-start
            align-items-md-center align-items-start
          ">
               
                                    <div>
                                        <h4 class="card-title"><spring:message code="menu.chart13" text="ไฟล์ที่มีการเปิดสูงสุด 5 อันดับ" /></h4>
                                        
                                  
                                        
                                        
                                        <span class="card-subtitle text-muted"></span>
                                    </div>
                                    <div class="d-flex align-items-center flex-wrap mt-sm-0 mt-1">
                                        <h5 class="fw-bolder mb-0 me-1"></h5>
                                              <div style="float:right" class="btn-group mt-md-0 mt-1 " role="group" aria-label="Basic radio toggle button group">
                                        <input type="radio" class="btn-check" name="radio_optionsbar-chart" id="radio_optionsd1" autocomplete="off" checked onclick="show('body3_1','body3_2','body3_3')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_optionsd1">Daily</label>

                                        <input type="radio" class="btn-check" name="radio_optionsbar-chart" id="radio_optionsd2" autocomplete="off" onclick="show('body3_2','body3_3','body3_1')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_optionsd2">Monthly</label>

                                        <input type="radio" class="btn-check" name="radio_optionsbar-chart" id="radio_optionsd3" autocomplete="off" onclick="show('body3_3','body3_2','body3_1')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_optionsd3">Yearly</label>
                                    </div>
                                    </div>
                                </div>
                                
                                
                                
                                <div class="card-body">
                                
                                       <div id="body3_1">
                                    <canvas id="bar-chart-ex1_1"  data-height="400" style="width:100%;height:400px"></canvas>
                                  </div>
                                      <div id="body3_2" >
                               <canvas id="bar-chart-ex1_2"  data-height="400" style="width:100%;height:400px"></canvas>
                                  </div>
                                       <div id="body3_3" >
                            <canvas id="bar-chart-ex1_3"  data-height="400" style="width:100%;height:400px"></canvas>
                                  </div>
                                
                                
                             
                                  
                                </div>
                            </div>
         </div>
         
         
         
         
                <div class="col-lg-6 col-12">
            
                 
                      <div class="card">
    <div class="
            card-header
            d-flex
            flex-md-row flex-column
            justify-content-md-between justify-content-start
            align-items-md-center align-items-start
          ">
                 					<div>
                                    <h4 class="card-title"><spring:message code="menu.chart14" text="ไฟล์ที่มีการเปิดน้อยที่สุด 5 อันดับ" /></h4>
                           <span class="card-subtitle text-muted"></span>
                                    </div>
                                    <div class="d-flex align-items-center flex-wrap mt-sm-0 mt-1">
                                        <h5 class="fw-bolder mb-0 me-1"></h5>
                                              <div style="float:right" class="btn-group mt-md-0 mt-1 " role="group" aria-label="Basic radio toggle button group">
                                        <input type="radio" class="btn-check" name="optionsbar-charts" id="sradio_option1" autocomplete="off" checked onclick="show('body4_1','body4_2','body4_3')">
                                        <label class="btn btn-outline-primary waves-effect" for="sradio_option1">Daily</label>

                                        <input type="radio" class="btn-check" name="optionsbar-charts" id="sradio_option2" autocomplete="off" onclick="show('body4_2','body4_3','body4_1')">
                                        <label class="btn btn-outline-primary waves-effect" for="sradio_option2">Monthly</label>

                                        <input type="radio" class="btn-check" name="optionsbar-charts" id="sradio_option3" autocomplete="off" onclick="show('body4_3','body4_2','body4_1')">
                                        <label class="btn btn-outline-primary waves-effect" for="sradio_option3">Yearly</label>
                                    </div>
                                    </div>
                              </div>
                                      <div class="card-body">
                                   <div id="body4_1">
                                    <canvas id="bar-chart-ex2_1"   data-height="400" style="width:100%;height:400px">></canvas>
                                  </div>
                                      <div id="body4_2" >
                               <canvas id="bar-chart-ex2_2"   data-height="400" style="width:100%;height:400px">></canvas>
                                  </div>
                                       <div id="body4_3" >
                            <canvas id="bar-chart-ex2_3"   data-height="400" style="width:100%;height:400px">></canvas>
                                  </div>
                                
                            </div>
                     </div>

 
        
                    
                    
                     
                      
                       </div>
                        <!--/ Timeline Card -->

                        <!-- Avg Sessions Chart Card ends -->





      <div class="col-lg-4 col-12">
            
                   
                      <div class="card">
    <div class="
            card-header
            d-flex
            flex-md-row flex-column
            justify-content-md-between justify-content-start
            align-items-md-center align-items-start
          ">
                 					<div>
                                         <h4 class="card-title"><spring:message code="menu.chart15" text="การอัพโหลดบันทึกคุณภาพรายวัน (%)" /></h4>
                           <span class="card-subtitle text-muted"></span>
                                    </div>
                               
                              </div>
                                      <div class="card-body">
                                   <div >
                                    <div id="chartdivnew1" ></div>
                                  </div>
                                    
                                
                            </div>
                     </div>
                       </div>
                       
                       
                             <div class="col-lg-4 col-12">
            
                   
                      <div class="card">
    <div class="
            card-header
            d-flex
            flex-md-row flex-column
            justify-content-md-between justify-content-start
            align-items-md-center align-items-start
          ">
                 					<div>
                                    <h4 class="card-title"><spring:message code="menu.chart16" text="การอัพโหลดบันทึกคุณภาพสะสมรายเดือน (%)" /></h4>
                           <span class="card-subtitle text-muted"></span>
                                    </div>
                               
                              </div>
                                      <div class="card-body">
                                   <div >
                                    <div id="chartdivnew2" ></div>
                                  </div>
                                    
                                
                            </div>
                     </div>
                       </div>
                       
                             <div class="col-lg-4 col-12">
            
                   
                      <div class="card">
    <div class="
            card-header
            d-flex
            flex-md-row flex-column
            justify-content-md-between justify-content-start
            align-items-md-center align-items-start
          ">
                 					<div>
                                 <h4 class="card-title"><spring:message code="menu.chart17" text="การอัพโหลดบันทึกคุณภาพสะสมรายปี (%)" /></h4>
                           <span class="card-subtitle text-muted"></span>
                                    </div>
                               
                              </div>
                                      <div class="card-body">
                                   <div >
                                    <div id="chartdivnew3" ></div>
                                  </div>
                                    
                                
                            </div>
                     </div>
                       </div>
                       
                       
                        <div class="col-xl-6 col-12">
                          <div class="card">
                          
                          
                          
                              <div class="card-header d-flex justify-content-between align-items-baseline align-items-sm-center flex-sm-row flex-column">
                                    <h4 class="card-title"><spring:message code="menu.chart18" text="จำนวนเครื่องที่ Log In ตาม Level 1 - 5" /></h4>
                             
                                </div>

                                
                                <div class="card-body">
                                   <div id="newchartdiv_1" class="chartdiv"></div>
                                  </div>       
                                  
                   
                                   

                                </div>
                                
                                
                                
                            </div>
                        
                           <div class="col-xl-6 col-12">
                          <div class="card">
                          
                          
                          
                              <div class="card-header d-flex justify-content-between align-items-baseline align-items-sm-center flex-sm-row flex-column">
                                    <h4 class="card-title"><spring:message code="menu.chart19" text="จำนวน USER ตาม Level 1 - 5" /></h4>
                             
                                </div>

                                
                                <div class="card-body">
                                   <div id="newchartdiv_2" class="chartdiv"></div>
                                  </div>       
                                  
                   
                                   

                                </div>
                                
                                
                                
                            </div>
                        
                        
                        
                        
                       
                        <!--/ Timeline Card -->


</div>
                        <!-- Sales Stats Chart Card ends -->



               
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



 
<%@ include file="common/mainfoot.jspf" %>  
 
    <script src="<c:url value="/resources/app-assets/vendors/js/charts/apexcharts.min.js" />"></script>
       <script src="<c:url value="/resources/app-assets/vendors/js/charts/chart.min.js" />"></script>
    
    <script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/radar.js"></script>
<script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>




    <script>
    
    
    
    
am5.ready(function() {

	// Create root element
	// https://www.amcharts.com/docs/v5/getting-started/#Root_element
	var root = am5.Root.new("newchartdiv_1");


	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	root.setThemes([
	  am5themes_Animated.new(root)
	]);


	// Create chart
	// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
	var chart = root.container.children.push(am5percent.PieChart.new(root, {
	  layout: root.verticalLayout
	}));


	// Create series
	// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
	var series = chart.series.push(am5percent.PieSeries.new(root, {
	  valueField: "value",
	  categoryField: "category"
	}));


	// Set data
	// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Setting_data
	series.data.setAll([
	  { value: ${loglevel1}, category: "Labour" },
	  { value:  ${loglevel2}, category: "Seniour Labour" },
	  { value:  ${loglevel3}, category: "Supervisor" },
	  { value:  ${loglevel4}, category: "Manager" },
	  { value:  ${loglevel5}, category: "Director" },
	]);


	// Play initial series animation
	// https://www.amcharts.com/docs/v5/concepts/animations/#Animation_of_series
	series.appear(1000, 100);

	}); // end am5.ready()
    
    
    
    
	am5.ready(function() {

		// Create root element
		// https://www.amcharts.com/docs/v5/getting-started/#Root_element
		var root = am5.Root.new("newchartdiv_2");


		// Set themes
		// https://www.amcharts.com/docs/v5/concepts/themes/
		root.setThemes([
		  am5themes_Animated.new(root)
		]);


		// Create chart
		// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
		var chart = root.container.children.push(am5percent.PieChart.new(root, {
		  layout: root.verticalLayout
		}));


		// Create series
		// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
		var series = chart.series.push(am5percent.PieSeries.new(root, {
		  valueField: "value",
		  categoryField: "category"
		}));


		// Set data
		// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Setting_data
		series.data.setAll([
			  { value: ${countlevel1}, category: "Labour" },
			  { value: ${countlevel2}, category: "Seniour Labour" },
			  { value: ${countlevel3}, category: "Supervisor" },
			  { value: ${countlevel4}, category: "Manager" },
			  { value: ${countlevel5}, category: "Director" },
		]);


		// Play initial series animation
		// https://www.amcharts.com/docs/v5/concepts/animations/#Animation_of_series
		series.appear(1000, 100);

		}); // end am5.ready()
	    
	    
	    
    
    
    
    
am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdivnew1");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/radar-chart/
var chart = root.container.children.push(
  am5radar.RadarChart.new(root, {
    panX: false,
    panY: false,
    startAngle: 180,
    endAngle: 360
  })
);

// Create axis and its renderer
// https://www.amcharts.com/docs/v5/charts/radar-chart/gauge-charts/#Axes
var axisRenderer = am5radar.AxisRendererCircular.new(root, {
  innerRadius: -10,
  strokeOpacity: 1,
  strokeWidth: 15,
  strokeGradient: am5.LinearGradient.new(root, {
    rotation: 0,
    stops: [
      { color: am5.color(0x19d228) },
      { color: am5.color(0xf4fb16) },
      { color: am5.color(0xf6d32b) },
      { color: am5.color(0xfb7116) }
    ]
  })
});

var xAxis = chart.xAxes.push(
  am5xy.ValueAxis.new(root, {
    maxDeviation: 0,
    min: 0,
    max: 100,
    strictMinMax: true,
    renderer: axisRenderer
  })
);

// Add clock hand
// https://www.amcharts.com/docs/v5/charts/radar-chart/gauge-charts/#Clock_hands
var axisDataItem = xAxis.makeDataItem({});
axisDataItem.set("value", ${now_percent});

var bullet = axisDataItem.set("bullet", am5xy.AxisBullet.new(root, {
  sprite: am5radar.ClockHand.new(root, {
    radius: am5.percent(99)
  })
}));

xAxis.createAxisRange(axisDataItem);

axisDataItem.get("grid").set("visible", false);


// Make stuff animate on load
chart.appear(1000, 100);

}); // end am5.ready()
</script>
    
    
    <script>
am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdivnew2");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/radar-chart/
var chart = root.container.children.push(
  am5radar.RadarChart.new(root, {
    panX: false,
    panY: false,
    startAngle: 180,
    endAngle: 360
  })
);

// Create axis and its renderer
// https://www.amcharts.com/docs/v5/charts/radar-chart/gauge-charts/#Axes
var axisRenderer = am5radar.AxisRendererCircular.new(root, {
  innerRadius: -10,
  strokeOpacity: 1,
  strokeWidth: 15,
  strokeGradient: am5.LinearGradient.new(root, {
    rotation: 0,
    stops: [
      { color: am5.color(0x19d228) },
      { color: am5.color(0xf4fb16) },
      { color: am5.color(0xf6d32b) },
      { color: am5.color(0xfb7116) }
    ]
  })
});

var xAxis = chart.xAxes.push(
  am5xy.ValueAxis.new(root, {
    maxDeviation: 0,
    min: 0,
    max: 100,
    strictMinMax: true,
    renderer: axisRenderer
  })
);

// Add clock hand
// https://www.amcharts.com/docs/v5/charts/radar-chart/gauge-charts/#Clock_hands
var axisDataItem = xAxis.makeDataItem({});
axisDataItem.set("value", ${now_percent2});

var bullet = axisDataItem.set("bullet", am5xy.AxisBullet.new(root, {
  sprite: am5radar.ClockHand.new(root, {
    radius: am5.percent(99)
  })
}));

xAxis.createAxisRange(axisDataItem);

axisDataItem.get("grid").set("visible", false);


// Make stuff animate on load
chart.appear(1000, 100);

}); // end am5.ready()
</script>
    


   <script>
am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdivnew3");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/radar-chart/
var chart = root.container.children.push(
  am5radar.RadarChart.new(root, {
    panX: false,
    panY: false,
    startAngle: 180,
    endAngle: 360
  })
);

// Create axis and its renderer
// https://www.amcharts.com/docs/v5/charts/radar-chart/gauge-charts/#Axes
var axisRenderer = am5radar.AxisRendererCircular.new(root, {
  innerRadius: -10,
  strokeOpacity: 1,
  strokeWidth: 15,
  strokeGradient: am5.LinearGradient.new(root, {
    rotation: 0,
    stops: [
      { color: am5.color(0x19d228) },
      { color: am5.color(0xf4fb16) },
      { color: am5.color(0xf6d32b) },
      { color: am5.color(0xfb7116) }
    ]
  })
});

var xAxis = chart.xAxes.push(
  am5xy.ValueAxis.new(root, {
    maxDeviation: 0,
    min: 0,
    max: 100,
    strictMinMax: true,
    renderer: axisRenderer
  })
);

// Add clock hand
// https://www.amcharts.com/docs/v5/charts/radar-chart/gauge-charts/#Clock_hands
var axisDataItem = xAxis.makeDataItem({});
axisDataItem.set("value", ${now_percent3});

var bullet = axisDataItem.set("bullet", am5xy.AxisBullet.new(root, {
  sprite: am5radar.ClockHand.new(root, {
    radius: am5.percent(99)
  })
}));

xAxis.createAxisRange(axisDataItem);

axisDataItem.get("grid").set("visible", false);


// Make stuff animate on load
chart.appear(1000, 100);

}); // end am5.ready()
</script>

    
     <script>
   
     function show(data1,data2,data3){
    	 
    	 $("#"+data1).show();
    	 $("#"+data2).hide();
    	 $("#"+data3).hide();
     }
     
     
     
     var element = document.getElementById("nav_main2");
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
 		
    	
    	    
    	   	  
    	    var flatPicker = $('.flat-picker'),
    	    isRtl = $('html').attr('data-textdirection') === 'rtl',
    	    chartColors = {
    	      column: {
    	        series1: '#826af9',
    	        series2: '#d2b0ff',
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

    	    
    	    
    	
    
    	    // Bar Chart
    	    // --------------------------------------------------------------------
    	    
    	    var  barChartEx = $('#bar-chart-ex1_1');
    	    
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
    	                    fontColor: labelColor,
    	                   
    	                    fontSize:10, // ขนาดฟอนต์แกน X
    	                    callback: function(value) {
    	                      return value.length > 10 ? value.substr(0, 10) + '...' : value; // ตัดข้อความ
    	                    }
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
    	              barThickness: 50,
    	              backgroundColor: successColorShade,
    	              borderColor: 'transparent'
    	            }
    	          ]
    	        }
    	      });
    	    }	    
    	    
    	    
    	    
   var  barChartEx = $('#bar-chart-ex1_2');
    	    
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
    	                    fontColor: labelColor,
    	                   
    	                    fontSize:10, // ขนาดฟอนต์แกน X
    	                    callback: function(value) {
    	                      return value.length > 10 ? value.substr(0, 10) + '...' : value; // ตัดข้อความ
    	                    }
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
    	        	   <c:forEach items="${maxs2 }" var="row" varStatus="count">
    	        	  ' ${row.file_name_original }',
    	        	  </c:forEach>
    	        	  ],
    	          datasets: [
    	            {
    	              data: [
    	            	  <c:forEach items="${maxs2 }" var="row" varStatus="count">
    	            	 ${row.log_note},
    	            	  </c:forEach>
    	            	  ],
    	              barThickness: 50,
    	              backgroundColor: successColorShade,
    	              borderColor: 'transparent'
    	            }
    	          ]
    	        }
    	      });
    	    }	    
    	    
    	    $("#body3_2").hide();
    	    
    	    
    	    
			var  barChartEx3 = $('#bar-chart-ex1_3');
    	    
    	    if (barChartEx3.length) {
  	
    	      var barChartExample3 = new Chart(barChartEx3, {
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
    	                    fontColor: labelColor,
    	                   
    	                    fontSize:10, // ขนาดฟอนต์แกน X
    	                    callback: function(value) {
    	                      return value.length > 10 ? value.substr(0, 10) + '...' : value; // ตัดข้อความ
    	                    }
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
    	        	   <c:forEach items="${maxs3 }" var="row" varStatus="count">
    	        	  ' ${row.file_name_original }',
    	        	  </c:forEach>
    	        	  ],
    	          datasets: [
    	            {
    	              data: [
    	            	  <c:forEach items="${maxs3 }" var="row" varStatus="count">
    	            	 ${row.log_note},
    	            	  </c:forEach>
    	            	  ],
    	              barThickness: 50,
    	              backgroundColor: successColorShade,
    	              borderColor: 'transparent'
    	            }
    	          ]
    	        }
    	      });
    	    }	    
    	    
    	    $("#body3_3").hide();  
    	    
    	    
    	    
    	    
    	    
    	    
    	    var  barChartEx = $('#bar-chart-ex2_1');
    	    
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
    	                    fontColor: labelColor,
    	                   
    	                    fontSize:10, // ขนาดฟอนต์แกน X
    	                    callback: function(value) {
    	                      return value.length > 10 ? value.substr(0, 10) + '...' : value; // ตัดข้อความ
    	                    }
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
    	              barThickness: 50,
    	              backgroundColor:  '#36a2eb',
    	              borderColor: 'transparent'
    	            }
    	          ]
    	        }
    	      });
    	    }	    
  
    	    
    	
    	  
    var  barChartEx = $('#bar-chart-ex2_2');
    
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
                    fontColor: labelColor,
                   
                    fontSize:10, // ขนาดฟอนต์แกน X
                    callback: function(value) {
                      return value.length > 10 ? value.substr(0, 10) + '...' : value; // ตัดข้อความ
                    }
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
        	   <c:forEach items="${mins2 }" var="row" varStatus="count">
        	  ' ${row.file_name_original }',
        	  </c:forEach>
        	  ],
          datasets: [
            {
              data: [
            	  <c:forEach items="${mins2 }" var="row" varStatus="count">
            	 ${row.log_note},
            	  </c:forEach>
            	  ],
            	  barThickness: 50,
                  backgroundColor:  '#36a2eb',
              borderColor: 'transparent'
            }
          ]
        }
      });
    }	    

    
    $("#body4_2").hide();  
 
var  barChartEx = $('#bar-chart-ex2_3');

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
                fontColor: labelColor,
               
                fontSize:10, // ขนาดฟอนต์แกน X
                callback: function(value) {
                  return value.length > 10 ? value.substr(0, 10) + '...' : value; // ตัดข้อความ
                }
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
    	   <c:forEach items="${mins3 }" var="row" varStatus="count">
    	  ' ${row.file_name_original }',
    	  </c:forEach>
    	  ],
      datasets: [
        {
          data: [
        	  <c:forEach items="${mins3 }" var="row" varStatus="count">
        	 ${row.log_note},
        	  </c:forEach>
        	  ],
          barThickness: 50,
          backgroundColor:  '#36a2eb',
          borderColor: 'transparent'
        }
      ]
    }
  });
}	

$("#body4_3").hide();  



// Bar Chart
// --------------------------------------------------------------------

var  barChartEx = $('.bar-chart-exs1');

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
                fontColor: labelColor,
               
                fontSize:10, // ขนาดฟอนต์แกน X
                callback: function(value) {
                  return value.length > 10 ? value.substr(0, 10) + '...' : value; // ตัดข้อความ
                }
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
        	 ${row.report_pn_sendstatus},
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

var  barChartEx = $('.bar-chart-exs2');

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
                fontColor: labelColor,
               
                fontSize:10, // ขนาดฟอนต์แกน X
                callback: function(value) {
                  return value.length > 10 ? value.substr(0, 10) + '...' : value; // ตัดข้อความ
                }
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
    	   <c:forEach items="${sendstatus2 }" var="row" varStatus="count">
    	  '${row.user_name }',
    	  </c:forEach>
    	  ],
      datasets: [
        {
          data: [
        	  <c:forEach items="${sendstatus2 }" var="row" varStatus="count">
        	 ${row.report_pn_sendstatus},
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

$("#body6_2").hide();   





// Horizontal Bar Chart
// --------------------------------------------------------------------
 var horizontalBarChartEx = $('.horizontal-bar-chart-exs1');
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
                fontColor: labelColor,
               
                fontSize:10, // ขนาดฟอนต์แกน X
                callback: function(value) {
                  return value.length > 10 ? value.substr(0, 10) + '...' : value; // ตัดข้อความ
                }
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

//Horizontal Bar Chart
//--------------------------------------------------------------------
var horizontalBarChartEx = $('.horizontal-bar-chart-exs2');
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
	   
	  
	   
 	   <c:forEach items="${statuserror2 }" var="row" varStatus="count">
  	  '${row.user_name }',
  	  </c:forEach>  
   ],
   datasets: [
     {
       data: [
     	  <c:forEach items="${statuserror2 }" var="row" varStatus="count">
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


$("#body7_2").hide();   




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
        columnWidth: '75%',
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




}); 

    </script>
    


<script>
am5.ready(function() {


	// Create root element
	// https://www.amcharts.com/docs/v5/getting-started/#Root_element
	var root = am5.Root.new("chartdiv3_1");

	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	root.setThemes([am5themes_Animated.new(root)]);

	var container = root.container.children.push(
	  am5.Container.new(root, {
	    width: am5.p100,
	    height: am5.p100,
	    layout: root.horizontalLayout
	  })
	);

	// Create main chart
	// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
	var chart = container.children.push(
	  am5percent.PieChart.new(root, {
	    tooltip: am5.Tooltip.new(root, {})
	  })
	);

	// Create series
	// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
	var series = chart.series.push(
	  am5percent.PieSeries.new(root, {
	    valueField: "value",
	    alignLabels: false,


	  })
	);


	series.slices.template.set('tooltipText', '{category}: {value}');
	
	//series.labels.template.set("radius", 30);
	series.labels.template.set('text', '{category}: {value}');
	series.labels.template.setAll({
		  text: "{category}",
		  textType: "circular",
		  inside: true,
		  radius: 10
		});
	
	
	series.ticks.template.set("visible", true);
	series.slices.template.set("toggleKey", "none");

	// add events
	series.slices.template.events.on("click", function(e) {
	  selectSlice(e.target);
	});

	// Create sub chart
	// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
	var subChart = container.children.push(
	  am5percent.PieChart.new(root, {
	    radius: am5.percent(50),
	    tooltip: am5.Tooltip.new(root, {})
	  })
	);

	// Create sub series
	// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
	var subSeries = subChart.series.push(
	  am5percent.PieSeries.new(root, {
	    valueField: "value",
	    categoryField: "category"
	  })
	);

	subSeries.data.setAll([
	  { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: 0 },
	  { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: 0 },
	  { category: '<spring:message code="dar.request3" text="ขอยกเลิกเอกสาร" />', value: 0 },
	  { category: '<spring:message code="dar.request4" text="ขอยกเลิกเอกสาร" />', value: 0 }
	]);
	subSeries.slices.template.set("toggleKey", "none");

	subSeries.slices.template.set('tooltipText', '{category}: {value}');
	
	//series.labels.template.set("radius", 30);
	subSeries.labels.template.set('text', '{category}: {value}');
	subSeries.labels.template.setAll({
		  text: "{category}",
		  textType: "circular",
		  inside: true,
		  radius: 10
		});
	
	
	
	var selectedSlice;

	series.on("startAngle", function() {
	  updateLines();
	});

	container.events.on("boundschanged", function() {
	  root.events.on("frameended", function(){
	    updateLines();
	   })
	})

	function updateLines() {
	  if (selectedSlice) {
	    var startAngle = selectedSlice.get("startAngle");
	    var arc = selectedSlice.get("arc");
	    var radius = selectedSlice.get("radius");

	    var x00 = radius * am5.math.cos(startAngle);
	    var y00 = radius * am5.math.sin(startAngle);

	    var x10 = radius * am5.math.cos(startAngle + arc);
	    var y10 = radius * am5.math.sin(startAngle + arc);

	    var subRadius = subSeries.slices.getIndex(0).get("radius");
	    var x01 = 0;
	    var y01 = -subRadius;

	    var x11 = 0;
	    var y11 = subRadius;

	    var point00 = series.toGlobal({ x: x00, y: y00 });
	    var point10 = series.toGlobal({ x: x10, y: y10 });

	    var point01 = subSeries.toGlobal({ x: x01, y: y01 });
	    var point11 = subSeries.toGlobal({ x: x11, y: y11 });

	    line0.set("points", [point00, point01]);
	    line1.set("points", [point10, point11]);
	  }
	}

	// lines
	var line0 = container.children.push(
	  am5.Line.new(root, {
	    position: "absolute",
	    stroke: root.interfaceColors.get("text"),
	    strokeDasharray: [2, 2]
	  })
	);
	var line1 = container.children.push(
	  am5.Line.new(root, {
	    position: "absolute",
	    stroke: root.interfaceColors.get("text"),
	    strokeDasharray: [2, 2]
	  })
	);

	// Set data
	// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Setting_data
	series.data.setAll([
	 
		
		
		{
	    category: "MONTH 1",
	    value: ${darmonth1},
	    subData: [
	      { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month1} },
	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month1} },
	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month1} },
	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month1} }
	    ]
	  },

      <c:if test="${darmonth2 > 0 }">  
	  {
		  category: "MONTH 2",
		    value: ${darmonth2},
	    subData: [
	        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month2} },
	  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month2 } },
	  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month2 } },
	  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month2 } }
	    ]
	  },
	 </c:if>
	 
	  
      <c:if test="${darmonth3 > 0 }">  
	  {
		  category: "MONTH 3",
		    value: ${darmonth3},
	    subData: [
	        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month3} },
	  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month3 } },
	  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month3 } },
	  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month3 } }
	    ]
	  },
	  </c:if>
		 
	  
	  <c:if test="${darmonth4 > 0 }">  
	  {
		  category: "MONTH 4",
		    value: ${darmonth4},
	    subData: [
	        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month4} },
	  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month4 } },
	  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month4 } },
	  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month4 } }
	    ]
	  }
	  ,
	  </c:if>
	  
	  <c:if test="${darmonth5 > 0 }">  
	  {
		  category: "MONTH 5",
		    value: ${darmonth5},
	    subData: [
	        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month5} },
	  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month5 } },
	  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month5 } },
	  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month5 } }
	    ]
	  }
	  ,
	  </c:if>
	  
	  <c:if test="${darmonth6 > 0 }">  
	  {
		  category: "MONTH 6",
		    value: ${darmonth6},
	    subData: [
	        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month6} },
	  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month6 } },
	  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month6 } },
	  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month6 } }
	    ]
	  } 
	  
	  ,
	  </c:if>
	  <c:if test="${darmonth7 > 0 }">  
	  {
		    category: "MONTH 7",
		    value: '${darmonth7}',
		    subData: [
		        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month7} },
		  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month7 } },
		  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month7 } },
		  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month7 } }
		    ]
		  },
		  </c:if>
		  <c:if test="${darmonth8 > 0 }">  
		  {
			  category: "MONTH 8",
			    value: '${darmonth8}',
		    subData: [
		        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month8} },
		  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month8 } },
		  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month8 } },
		  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month8 } }
		    ]
		  },
		  </c:if> 
		  <c:if test="${darmonth9 > 0 }">  
		  {
			  category: "MONTH 9",
			    value: ${darmonth9},
		    subData: [
		        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month9} },
		  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month9 } },
		  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month9 } },
		  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month9 } }
		    ]
		  },
		  </c:if>
		  <c:if test="${darmonth10 > 0 }">  
		  {
			  category: "MONTH 10",
			    value: ${darmonth10},
		    subData: [
		        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month10} },
		  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month10 } },
		  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month10 } },
		  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month10 } }
		    ]
		  }
		  ,
		  </c:if>
		  <c:if test="${darmonth11 > 0 }">  
		  {
			  category: "MONTH 11",
			    value: ${darmonth11},
		    subData: [
		        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month11} },
		  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month11 } },
		  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month11 } },
		  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month11 } }
		    ]
		  }
		  ,
		  </c:if> 
		  <c:if test="${darmonth12 > 0 }">  
		  {
			  category: "MONTH 12",
			    value: ${darmonth12},
		    subData: [
		        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${request1.month12} },
		  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${request2.month12 } },
		  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${request3.month12 } },
		  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${request4.month12 } }
		    ]
		  } 
		  </c:if>
	  
	]);

	function selectSlice(slice) {
	  selectedSlice = slice;
	  var dataItem = slice.dataItem;
	  var dataContext = dataItem.dataContext;

	  if (dataContext) {
	    var i = 0;
	    subSeries.data.each(function(dataObject) {
	      var dataObj = dataContext.subData[i];
	      if(dataObj){
	          subSeries.data.setIndex(i, dataObj);
	          if(!subSeries.dataItems[i].get("visible")){
	              subSeries.dataItems[i].show();
	          }
	      }
	      else{
	          subSeries.dataItems[i].hide();
	      }
	      
	      i++;
	    });
	  }

	  var middleAngle = slice.get("startAngle") + slice.get("arc") / 2;
	  var firstAngle = series.dataItems[0].get("slice").get("startAngle");

	  series.animate({
	    key: "startAngle",
	    to: firstAngle - middleAngle,
	    duration: 1000,
	    easing: am5.ease.out(am5.ease.cubic)
	  });
	  series.animate({
	    key: "endAngle",
	    to: firstAngle - middleAngle + 360,
	    duration: 1000,
	    easing: am5.ease.out(am5.ease.cubic)
	  });
	}

	container.appear(1000, 10);

	series.events.on("datavalidated", function() {
	  selectSlice(series.slices.getIndex(0));
	});

	}); // end am5.ready()
	
	
	
	
	
	
	am5.ready(function() {


		// Create root element
		// https://www.amcharts.com/docs/v5/getting-started/#Root_element
		var root = am5.Root.new("chartdiv3_2");

		// Set themes
		// https://www.amcharts.com/docs/v5/concepts/themes/
		root.setThemes([am5themes_Animated.new(root)]);

		var container = root.container.children.push(
		  am5.Container.new(root, {
		    width: am5.p100,
		    height: am5.p100,
		    layout: root.horizontalLayout
		  })
		);

		// Create main chart
		// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
		var chart = container.children.push(
		  am5percent.PieChart.new(root, {
		    tooltip: am5.Tooltip.new(root, {})
		  })
		);

		// Create series
		// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
		var series = chart.series.push(
		  am5percent.PieSeries.new(root, {
		    valueField: "value",
		    alignLabels: false,


		  })
		);

		series.labels.template.setAll({
		  textType: "circular", 
		  inside: true,

		});
		series.ticks.template.set("visible", true);
		series.slices.template.set("toggleKey", "none");

		
		series.slices.template.set('tooltipText', '{category}: {value}');
		
		//series.labels.template.set("radius", 30);
		series.labels.template.set('text', '{category}: {value}');
		series.labels.template.setAll({
			  text: "{category}",
			  textType: "circular",
			  inside: true,
			  radius: 10
			});
		
		
		
		
		// add events
		series.slices.template.events.on("click", function(e) {
		  selectSlice(e.target);
		});

		// Create sub chart
		// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
		var subChart = container.children.push(
		  am5percent.PieChart.new(root, {
		    radius: am5.percent(50),
		    tooltip: am5.Tooltip.new(root, {})
		  })
		);

		// Create sub series
		// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
		var subSeries = subChart.series.push(
		  am5percent.PieSeries.new(root, {
		    valueField: "value",
		    categoryField: "category"
		  })
		);

		subSeries.data.setAll([
		  { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: 0 },
		  { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: 0 },
		  { category: '<spring:message code="dar.request3" text="ขอยกเลิกเอกสาร" />', value: 0 },
		  { category: '<spring:message code="dar.request4" text="ขอยกเลิกเอกสาร" />', value: 0 }
		]);
		subSeries.slices.template.set("toggleKey", "none");

		
		subSeries.slices.template.set('tooltipText', '{category}: {value}');
		
		//series.labels.template.set("radius", 30);
		subSeries.labels.template.set('text', '{category}: {value}');
		subSeries.labels.template.setAll({
			  text: "{category}",
			  textType: "circular",
			  inside: true,
			  radius: 10
			});
		
		
		
		
		var selectedSlice;

		series.on("startAngle", function() {
		  updateLines();
		});

		container.events.on("boundschanged", function() {
		  root.events.on("frameended", function(){
		    updateLines();
		   })
		})

		function updateLines() {
		  if (selectedSlice) {
		    var startAngle = selectedSlice.get("startAngle");
		    var arc = selectedSlice.get("arc");
		    var radius = selectedSlice.get("radius");

		    var x00 = radius * am5.math.cos(startAngle);
		    var y00 = radius * am5.math.sin(startAngle);

		    var x10 = radius * am5.math.cos(startAngle + arc);
		    var y10 = radius * am5.math.sin(startAngle + arc);

		    var subRadius = subSeries.slices.getIndex(0).get("radius");
		    var x01 = 0;
		    var y01 = -subRadius;

		    var x11 = 0;
		    var y11 = subRadius;

		    var point00 = series.toGlobal({ x: x00, y: y00 });
		    var point10 = series.toGlobal({ x: x10, y: y10 });

		    var point01 = subSeries.toGlobal({ x: x01, y: y01 });
		    var point11 = subSeries.toGlobal({ x: x11, y: y11 });

		    line0.set("points", [point00, point01]);
		    line1.set("points", [point10, point11]);
		  }
		}

		// lines
		var line0 = container.children.push(
		  am5.Line.new(root, {
		    position: "absolute",
		    stroke: root.interfaceColors.get("text"),
		    strokeDasharray: [2, 2]
		  })
		);
		var line1 = container.children.push(
		  am5.Line.new(root, {
		    position: "absolute",
		    stroke: root.interfaceColors.get("text"),
		    strokeDasharray: [2, 2]
		  })
		);

		// Set data
		// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Setting_data
		series.data.setAll([
		 
			
			
			{
		    category: "${years}",
		    value: ${daryear1},
		    subData: [
		      { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${requests1.year1} },
		      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${requests2.year1} },
		      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${requests3.year1} },
		      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${requests4.year1} }
		    ]
		  },

		  <c:if test="${daryear2 > 0 }">   
		  {
			  category: "${years2}",
			    value: ${daryear2},
		    subData: [
		        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${requests1.year2} },
		  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${requests2.year2 } },
		  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${requests3.year2 } },
		  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${requests4.year2 } }
		    ]
		  },
		  </c:if>
		
		  
		  <c:if test="${daryear3 > 0 }">   
		  {
			  category: "${years3}",
			    value: ${daryear3},
		    subData: [
		        { category: '<spring:message code="dar.request1" text="ขอออกเอกสารใหม่" />', value: ${requests1.year3} },
		  	      { category: '<spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" />', value: ${requests2.year3 } },
		  	      { category: '<spring:message code="dar.request3" text="ขอเเก้ไขเอกสาร" />', value: ${requests3.year3 } },
		  	      { category: '<spring:message code="dar.request4" text="ขอเเก้ไขเอกสาร" />', value: ${requests4.year3 } }
		    ]
		  }
		  </c:if> 
		]);

		function selectSlice(slice) {
		  selectedSlice = slice;
		  var dataItem = slice.dataItem;
		  var dataContext = dataItem.dataContext;

		  if (dataContext) {
		    var i = 0;
		    subSeries.data.each(function(dataObject) {
		      var dataObj = dataContext.subData[i];
		      if(dataObj){
		          subSeries.data.setIndex(i, dataObj);
		          if(!subSeries.dataItems[i].get("visible")){
		              subSeries.dataItems[i].show();
		          }
		      }
		      else{
		          subSeries.dataItems[i].hide();
		      }
		      
		      i++;
		    });
		  }

		  var middleAngle = slice.get("startAngle") + slice.get("arc") / 2;
		  var firstAngle = series.dataItems[0].get("slice").get("startAngle");

		  series.animate({
		    key: "startAngle",
		    to: firstAngle - middleAngle,
		    duration: 1000,
		    easing: am5.ease.out(am5.ease.cubic)
		  });
		  series.animate({
		    key: "endAngle",
		    to: firstAngle - middleAngle + 360,
		    duration: 1000,
		    easing: am5.ease.out(am5.ease.cubic)
		  });
		}

		container.appear(1000, 10);

		series.events.on("datavalidated", function() {
		  selectSlice(series.slices.getIndex(0));
		});

		}); // end am5.ready()
</script>


<!-- Chart code -->
<script>
am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdiv_1");
root.dateFormatter.setAll({
  dateFormat: "yyyy-MM-dd HH:mm",
  dateFields: ["valueX", "openValueX"]
});


// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);


// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root.container.children.push(am5xy.XYChart.new(root, {
  panX: false,
  panY: false,
  wheelX: "panX",
  wheelY: "zoomX",
  layout: root.verticalLayout
}));


// Add legend
// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
var legend = chart.children.push(am5.Legend.new(root, {
  centerX: am5.p50,
  x: am5.p50
}))

var colors = chart.get("colors");

// Data
var data = [
 
	
	
	 <c:forEach items="${user_login }" var="rows" varStatus="count">	
	 <c:forEach items="${rows.logDay }" var="row" >	
			{
		    category: "${rows.group_level}",
		    fromDate: "${row.log_datestart} ${row.log_timestart}",
		    toDate: "${row.log_date} ${row.log_time}",
		    columnSettings: {
		      fill: am5.Color.brighten(colors.getIndex(0), 0)
		    }
		  },
	
  </c:forEach>
		  </c:forEach>

  
];


//Create axes
//https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var yAxis = chart.yAxes.push(
am5xy.CategoryAxis.new(root, {
 categoryField: "category",
 renderer: am5xy.AxisRendererY.new(root, { inversed: true }),
 tooltip: am5.Tooltip.new(root, {
   themeTags: ["axis"],
   animationDuration: 200
 })
})
);

yAxis.data.setAll([
{ category: "Labour" },
{ category: "Seniour Labour" },
{ category: "Supervisor" },
{ category: "Manager" },
{ category: "Director" }
]);

var xAxis = chart.xAxes.push(
am5xy.DateAxis.new(root, {
 baseInterval: { timeUnit: "minute", count: 1 },
 renderer: am5xy.AxisRendererX.new(root, {strokeOpacity: 0.1})
})
);


//Add series
//https://www.amcharts.com/docs/v5/charts/xy-chart/series/
var series = chart.series.push(am5xy.ColumnSeries.new(root, {
xAxis: xAxis,
yAxis: yAxis,
openValueXField: "fromDate",
valueXField: "toDate",
categoryYField: "category",
sequencedInterpolation: true
}));

series.columns.template.setAll({
templateField: "columnSettings",
strokeOpacity: 0,
tooltipText: "{category}: {openValueX} - {valueX}"
});

series.data.processor = am5.DataProcessor.new(root, {
dateFields: ["fromDate", "toDate"],
dateFormat: "yyyy-MM-dd HH:mm"
});
series.data.setAll(data);

//Add scrollbars
chart.set("scrollbarX", am5.Scrollbar.new(root, {
orientation: "horizontal"
}));

//Make stuff animate on load
//https://www.amcharts.com/docs/v5/concepts/animations/
series.appear();
chart.appear(1000, 100);

}); // end am5.ready()



am5.ready(function() {

	// Create root element
	// https://www.amcharts.com/docs/v5/getting-started/#Root_element
	var root = am5.Root.new("chartdiv_2");
	root.dateFormatter.setAll({
	  dateFormat: "yyyy-MM-dd HH:mm",
	  dateFields: ["valueX", "openValueX"]
	});


	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	root.setThemes([
	  am5themes_Animated.new(root)
	]);


	// Create chart
	// https://www.amcharts.com/docs/v5/charts/xy-chart/
	var chart = root.container.children.push(am5xy.XYChart.new(root, {
	  panX: false,
	  panY: false,
	  wheelX: "panX",
	  wheelY: "zoomX",
	  layout: root.verticalLayout
	}));


	// Add legend
	// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
	var legend = chart.children.push(am5.Legend.new(root, {
	  centerX: am5.p50,
	  x: am5.p50
	}))

	var colors = chart.get("colors");

	// Data
	var data = [
	 
		 <c:forEach items="${user_login }" var="rows" varStatus="count">	
		 <c:forEach items="${rows.logMonth }" var="row" >	
				{
			    category: "${rows.group_level}",
			    fromDate: "${row.log_datestart} ${row.log_timestart}",
			    toDate: "${row.log_date} ${row.log_time}",
			    columnSettings: {
			      fill: am5.Color.brighten(colors.getIndex(0), 0)
			    }
			  },
		
	  </c:forEach>
			  </c:forEach>
	  

	  
	];


	// Create axes
	// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
	var yAxis = chart.yAxes.push(
	  am5xy.CategoryAxis.new(root, {
	    categoryField: "category",
	    renderer: am5xy.AxisRendererY.new(root, { inversed: true }),
	    tooltip: am5.Tooltip.new(root, {
	      themeTags: ["axis"],
	      animationDuration: 200
	    })
	  })
	);

	yAxis.data.setAll([
	  { category: "Labour" },
	  { category: "Seniour Labour" },
	  { category: "Supervisor" },
	  { category: "Manager" },
	  { category: "Director" }
	]);

	var xAxis = chart.xAxes.push(
	  am5xy.DateAxis.new(root, {
	    baseInterval: { timeUnit: "minute", count: 1 },
	    renderer: am5xy.AxisRendererX.new(root, {strokeOpacity: 0.1})
	  })
	);


	// Add series
	// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
	var series = chart.series.push(am5xy.ColumnSeries.new(root, {
	  xAxis: xAxis,
	  yAxis: yAxis,
	  openValueXField: "fromDate",
	  valueXField: "toDate",
	  categoryYField: "category",
	  sequencedInterpolation: true
	}));

	series.columns.template.setAll({
	  templateField: "columnSettings",
	  strokeOpacity: 0,
	  tooltipText: "{category}: {openValueX} - {valueX}"
	});

	series.data.processor = am5.DataProcessor.new(root, {
	  dateFields: ["fromDate", "toDate"],
	  dateFormat: "yyyy-MM-dd HH:mm"
	});
	series.data.setAll(data);

	// Add scrollbars
	chart.set("scrollbarX", am5.Scrollbar.new(root, {
	  orientation: "horizontal"
	}));

	// Make stuff animate on load
	// https://www.amcharts.com/docs/v5/concepts/animations/
	series.appear();
	chart.appear(1000, 100);

	}); // end am5.ready()
	
	
	
	
	am5.ready(function() {

		// Create root element
		// https://www.amcharts.com/docs/v5/getting-started/#Root_element
		var root = am5.Root.new("chartdiv_3");
		root.dateFormatter.setAll({
		  dateFormat: "yyyy-MM-dd HH:mm",
		  dateFields: ["valueX", "openValueX"]
		});


		// Set themes
		// https://www.amcharts.com/docs/v5/concepts/themes/
		root.setThemes([
		  am5themes_Animated.new(root)
		]);


		// Create chart
		// https://www.amcharts.com/docs/v5/charts/xy-chart/
		var chart = root.container.children.push(am5xy.XYChart.new(root, {
		  panX: false,
		  panY: false,
		  wheelX: "panX",
		  wheelY: "zoomX",
		  layout: root.verticalLayout
		}));


		// Add legend
		// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
		var legend = chart.children.push(am5.Legend.new(root, {
		  centerX: am5.p50,
		  x: am5.p50
		}))

		var colors = chart.get("colors");

		// Data
		var data = [
		 
			 <c:forEach items="${user_login }" var="rows" varStatus="count">	
			 <c:forEach items="${rows.logYear }" var="row" >	
					{
				    category: "${rows.group_level}",
				    fromDate: "${row.log_datestart} ${row.log_timestart}",
				    toDate: "${row.log_date} ${row.log_time}",
				    columnSettings: {
				      fill: am5.Color.brighten(colors.getIndex(0), 0)
				    }
				  },
			
		  </c:forEach>
				  </c:forEach>
		  

		  
		];


		// Create axes
		// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
		var yAxis = chart.yAxes.push(
		  am5xy.CategoryAxis.new(root, {
		    categoryField: "category",
		    renderer: am5xy.AxisRendererY.new(root, { inversed: true }),
		    tooltip: am5.Tooltip.new(root, {
		      themeTags: ["axis"],
		      animationDuration: 200
		    })
		  })
		);

		yAxis.data.setAll([
		  { category: "Labour" },
		  { category: "Seniour Labour" },
		  { category: "Supervisor" },
		  { category: "Manager" },
		  { category: "Director" }
		]);

		var xAxis = chart.xAxes.push(
		  am5xy.DateAxis.new(root, {
		    baseInterval: { timeUnit: "minute", count: 1 },
		    renderer: am5xy.AxisRendererX.new(root, {strokeOpacity: 0.1})
		  })
		);


		// Add series
		// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
		var series = chart.series.push(am5xy.ColumnSeries.new(root, {
		  xAxis: xAxis,
		  yAxis: yAxis,
		  openValueXField: "fromDate",
		  valueXField: "toDate",
		  categoryYField: "category",
		  sequencedInterpolation: true
		}));

		series.columns.template.setAll({
		  templateField: "columnSettings",
		  strokeOpacity: 0,
		  tooltipText: "{category}: {openValueX} - {valueX}"
		});

		series.data.processor = am5.DataProcessor.new(root, {
		  dateFields: ["fromDate", "toDate"],
		  dateFormat: "yyyy-MM-dd HH:mm"
		});
		series.data.setAll(data);

		// Add scrollbars
		chart.set("scrollbarX", am5.Scrollbar.new(root, {
		  orientation: "horizontal"
		}));

		// Make stuff animate on load
		// https://www.amcharts.com/docs/v5/concepts/animations/
		series.appear();
		chart.appear(1000, 100);

		}); // end am5.ready()





am5.ready(function() {

	// Create root element
	// https://www.amcharts.com/docs/v5/getting-started/#Root_element
	var root = am5.Root.new("chartdiv2");

	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	root.setThemes([
	  am5themes_Animated.new(root)
	]);

	// Create chart
	// https://www.amcharts.com/docs/v5/charts/xy-chart/
	var chart = root.container.children.push(
	  am5xy.XYChart.new(root, {
	    panX: false,
	    panY: false,
	    wheelX: "none",
	    wheelY: "none",
	    layout: root.verticalLayout
	  })
	);

	// Create axes and their renderers
	var yRenderer = am5xy.AxisRendererY.new(root, {
	  visible: false,
	  minGridDistance: 20,
	  inversed: true
	});

	yRenderer.grid.template.set("visible", false);

	var yAxis = chart.yAxes.push(
	  am5xy.CategoryAxis.new(root, {
	    renderer: yRenderer,
	    categoryField: "category"
	  })
	);

	var xRenderer = am5xy.AxisRendererX.new(root, {
	  visible: false,
	  minGridDistance: 30,
	  inversed: true
	});

	xRenderer.grid.template.set("visible", false);

	var xAxis = chart.xAxes.push(
	  am5xy.CategoryAxis.new(root, {
	    renderer: xRenderer,
	    categoryField: "category"
	  })
	);

	// Create series
	// https://www.amcharts.com/docs/v5/charts/xy-chart/#Adding_series
	var series = chart.series.push(
	  am5xy.ColumnSeries.new(root, {
	    calculateAggregates: true,
	    stroke: am5.color(0xffffff),
	    clustered: false,
	    xAxis: xAxis,
	    yAxis: yAxis,
	    categoryXField: "x",
	    categoryYField: "y",
	    valueField: "value"
	  })
	);

	series.columns.template.setAll({
	  tooltipText: "{value}",
	  strokeOpacity: 1,
	  strokeWidth: 2,
	  cornerRadiusTL: 5,
	  cornerRadiusTR: 5,
	  cornerRadiusBL: 5,
	  cornerRadiusBR: 5,
	  width: am5.percent(100),
	  height: am5.percent(100),
	  templateField: "columnSettings"
	});

	var circleTemplate = am5.Template.new({});

	// Add heat rule
	// https://www.amcharts.com/docs/v5/concepts/settings/heat-rules/
	series.set("heatRules", [{
	  target: circleTemplate,
	  min: 10,
	  max: 35,
	  dataField: "value",
	  key: "radius"
	}]);

	series.bullets.push(function () {
	  return am5.Bullet.new(root, {
	    sprite: am5.Circle.new(
	      root,
	      {
	        fill: am5.color(0x000000),
	        fillOpacity: 0.5,
	        strokeOpacity: 0
	      },
	      circleTemplate
	    )
	  });
	});

	series.bullets.push(function () {
	  return am5.Bullet.new(root, {
	    sprite: am5.Label.new(root, {
	      fill: am5.color(0xffffff),
	      populateText: true,
	      centerX: am5.p50,
	      centerY: am5.p50,
	      fontSize: 10,
	      text: "{value}"
	    })
	  });
	});

	var colors = {
	  critical: am5.color(0xca0101),
	  bad: am5.color(0xe17a2d),
	  medium: am5.color(0xe1d92d),
	  good: am5.color(0x5dbe24),
	  verygood: am5.color(0x0b7d03)
	};

	// Set data
	// https://www.amcharts.com/docs/v5/charts/xy-chart/#Setting_data
	var data = [
	  
	{
	    y: "${years}",
	    x: "QM",
	    columnSettings: {
	      fill: colors.critical
	    },
	    value: ${docyear1.total_QM}
	  },
	{
		    y: "${years}",
		    x: "QP",
		    columnSettings: {
		      fill: colors.critical
		    },
		    value: ${docyear1.total_QP}
	},
		  
	{
			    y: "${years}",
			    x: "WI",
			    columnSettings: {
			      fill: colors.critical
			    },
			    value: ${docyear1.total_WI}
	 },
			  
	{
				    y: "${years}",
				    x: "SD",
				    columnSettings: {
				      fill: colors.critical
				    },
				    value: ${docyear1.total_SD}
	},
				  
	{
					    y: "${years}",
					    x: "FO",
					    columnSettings: {
					      fill: colors.critical
					    },
					    value: ${docyear1.total_FO}
	},
					  
	
	
	{
	    y: "${years4}",
	    x: "QM",
	    columnSettings: {
	      fill: colors.bad
	    },
	    value: ${docyear2.total_QM}
	  },
	{
		    y: "${years4}",
		    x: "QP",
		    columnSettings: {
		      fill: colors.bad
		    },
		    value: ${docyear2.total_QP}
	},
		  
	{
			    y: "${years4}",
			    x: "WI",
			    columnSettings: {
			      fill: colors.bad
			    },
			    value: ${docyear2.total_WI}
	 },
			  
	{
				    y: "${years4}",
				    x: "SD",
				    columnSettings: {
				      fill: colors.bad
				    },
				    value: ${docyear2.total_SD}
	},
				  
	{
					    y: "${years4}",
					    x: "FO",
					    columnSettings: {
					      fill: colors.bad
					    },
					    value: ${docyear2.total_FO}
	},

	{
	    y: "${years5}",
	    x: "QM",
	    columnSettings: {
	      fill: colors.verygood
	    },
	    value: ${docyear3.total_QM}
	  },
	{
		    y: "${years5}",
		    x: "QP",
		    columnSettings: {
		      fill: colors.verygood
		    },
		    value: ${docyear3.total_QP}
	},
		  
	{
			    y: "${years5}",
			    x: "WI",
			    columnSettings: {
			      fill: colors.verygood
			    },
			    value: ${docyear3.total_WI}
	 },
			  
	{
				    y: "${years5}",
				    x: "SD",
				    columnSettings: {
				      fill: colors.verygood
				    },
				    value: ${docyear3.total_SD}
	},
				  
	{
					    y: "${years5}",
					    x: "FO",
					    columnSettings: {
					      fill: colors.verygood
					    },
					    value: ${docyear3.total_FO}
	}
	];

	series.data.setAll(data);

	yAxis.data.setAll([
		  { category: "${years}" },
		  { category: "${years4}" },
	  { category: "${years5}" }
	
	
	]);


	xAxis.data.setAll([
		  { category: "FO" },
		  { category: "SD" },
		  { category: "WI" },
		  { category: "QP" },
	  { category: "QM" }
	 
	
	 
	
	]);

	// Make stuff animate on load
	// https://www.amcharts.com/docs/v5/concepts/animations/#Initial_animation
	chart.appear(1000, 100);

	}); // end am5.ready()
	
	
	
	</script>
	
	
	<script>
	
	am5.ready(function() {

		// Create root and chart
		var root = am5.Root.new("pie1");

		root.setThemes([
		  am5themes_Animated.new(root)
		]);

		var chart = root.container.children.push( 
		  am5percent.PieChart.new(root, {
		    layout: root.verticalLayout
		  }) 
		);

		// Create series
		var series = chart.series.push(
		  am5percent.PieSeries.new(root, {
		    valueField: "percent",
		    categoryField: "type",
		    fillField: "color",
		    alignLabels: false,
		    legendLabelText: "type",
		    legendValueText: "percent"
		  })
		);

		series.slices.template.set("templateField", "sliceSettings",);
		series.slices.template.set('tooltipText', '{type}: {percent}');
		
		//series.labels.template.set("radius", 30);
		series.labels.template.set('text', '{type}: {percent}');
		series.labels.template.setAll({
			  text: "{type}",
			  textType: "circular",
			  inside: true,
			  radius: 10
			});
		

		// Set up click events
		series.slices.template.events.on("click", function(event) {
		  console.log(event.target.dataItem.dataContext)
		  if (event.target.dataItem.dataContext.id != undefined) {
		    selected = event.target.dataItem.dataContext.id;
		  } else {
		    selected = undefined;
		  }
		  series.data.setAll(generateChartData());
		});

		// Define data
		var selected;
		var types = [
			
			 <c:if test="${pieday1.total > 0 }">   	
			{
		  type: "06:00 - 09:00",
		  percent:  ${pieday1.total1 + pieday1.total2 + pieday1.total3 +pieday1.total4 +pieday1.total5},
		  color: series.get("colors").getIndex(0),
		  subs: [
			  <c:if test="${pieday1.total1 > 0 }"> 
			    {
				    type: "Labour",
				    percent: ${pieday1.total1}
				  }, 
				  </c:if>
				 
				  <c:if test="${pieday1.total2 > 0 }"> 
				  {
				    type: "Seniour Labour",
				    percent: ${pieday1.total2}
				  }
				  </c:if>
				  <c:if test="${pieday1.total3 > 0 }"> 
				  
				  , {
				    type: "Supervisor",
				    percent: ${pieday1.total3}
				  }
				  , 
				  </c:if>
				  
				  
				  <c:if test="${pieday1.total4 > 0 }"> 
				  {
					    type: "Manager",
					    percent: ${pieday1.total4}
					  }
				  , 
				  </c:if> 
				  
				  <c:if test="${pieday1.total5 > 0 }"> 
				  {
					    type: "Director",
					    percent: ${pieday1.total5}
					  }
				  </c:if>
					  	  
			  	  
		  
		  ]
		}
		
		,
		  </c:if>
		 <c:if test="${pieday2.total > 0 }">   
		{
		 
			type: "09:01 - 11:00",
			percent:  ${pieday2.total1 + pieday2.total2 + pieday2.total3 +pieday2.total4 +pieday2.total5},
		  color: series.get("colors").getIndex(1),
		  subs: [
			    
			  
			  <c:if test="${pieday2.total1 > 0 }"> 
			  {
				    type: "Labour",
				    percent: ${pieday2.total1}
				  }, 
			
				  </c:if> 
				  <c:if test="${pieday2.total2 > 0 }"> 
				  {
				    type: "Seniour Labour",
				    percent: ${pieday2.total2}
				  }, 
				  </c:if>
				  <c:if test="${pieday1.total3 > 0 }"> 
				  
				  
				  {
				    type: "Supervisor",
				    percent: ${pieday2.total3}
				  }
				  , 
				  </c:if>
				  
				  
				  <c:if test="${pieday2.total4 > 0 }"> 
				  {
					    type: "Manager",
					    percent: ${pieday2.total4}
					  }
				  , 
				  </c:if> 
				  
				  <c:if test="${pieday2.total5 > 0 }"> 
				  {
					    type: "Director",
					    percent: ${pieday2.total5}
					  }
				  </c:if>
					  	  
		  
		  
		  ]
		}
		
		
		, 
		  </c:if>
		  <c:if test="${pieday3.total > 0 }">   
		{
			 
			type: "11:01 - 13:00",
			percent:  ${pieday3.total1 + pieday3.total2 + pieday3.total3 +pieday3.total4 +pieday3.total5},
		  color: series.get("colors").getIndex(2),
		  subs: [
			  <c:if test="${pieday3.total1 > 0 }"> 
			      {
				    type: "Labour",
				    percent: ${pieday3.total1}
				  }, 
			
				  </c:if>
				  <c:if test="${pieday3.total2 > 0 }"> 
				  {
				    type: "Seniour Labour",
				    percent: ${pieday3.total2}
				  }, 
				  </c:if>
				  <c:if test="${pieday1.total3 > 0 }"> 
				  
				  
				  {
				    type: "Supervisor",
				    percent: ${pieday3.total3}
				  }
				  , 
				  </c:if>
				  
				  
				  <c:if test="${pieday3.total4 > 0 }"> 
				  {
					    type: "Manager",
					    percent: ${pieday3.total4}
					  }
				  , 
				  </c:if> 
				  
				  <c:if test="${pieday3.total5 > 0 }"> 
				  {
					    type: "Director",
					    percent: ${pieday3.total5}
					  }
				  </c:if>
		  ]
		}
		
		, 
		</c:if>
		  <c:if test="${pieday4.total > 0 }">   
		{
			 
			type: "13:01 - 15:00",
			percent:  ${pieday4.total1 + pieday4.total2 + pieday4.total3 +pieday4.total4 +pieday4.total5},
		  color: series.get("colors").getIndex(3),
		  subs: [
			  <c:if test="${pieday4.total1 > 0 }"> 
		      {
				    type: "Labour",
				    percent: ${pieday4.total1}
				  }, 
				  </c:if>
				 
				  <c:if test="${pieday4.total2 > 0 }"> 
				  {
				    type: "Seniour Labour",
				    percent: ${pieday4.total2}
				  }, {
				    type: "Supervisor",
				    percent: ${pieday4.total3}
				  }
				  , 
				  </c:if>
				  
				  
				  <c:if test="${pieday4.total4 > 0 }"> 
				  {
					    type: "Manager",
					    percent: ${pieday4.total4}
					  }
				  , 
				  </c:if> 
				  
				  <c:if test="${pieday3.total5 > 0 }"> 
				  {
					    type: "Director",
					    percent: ${pieday4.total5}
					  }
				  </c:if>
					  	  
		  
		  ]
		}
		, 
		</c:if>
		
		
		  <c:if test="${pieday5.total > 0 }">   
		{
		
			type: "15:01 - 18:00",
		  percent:  ${pieday5.total1 + pieday5.total2 + pieday5.total3 +pieday5.total4 +pieday5.total5},
		  color: series.get("colors").getIndex(4),
		  subs: [
			  
			  <c:if test="${pieday5.total1 > 0 }"> 
		      {
				    type: "Labour",
				    percent: ${pieday5.total1}
				  }, 
				  </c:if>
				 
				  <c:if test="${pieday5.total2 > 0 }"> 
				  {
				    type: "Seniour Labour",
				    percent: ${pieday5.total2}
				  }, {
				    type: "Supervisor",
				    percent: ${pieday5.total3}
				  }
				  , 
				  </c:if>
				  
				  
				  <c:if test="${pieday5.total4 > 0 }"> 
				  {
					    type: "Manager",
					    percent: ${pieday4.total4}
					  }
				  , 
				  </c:if> 
				  
				  <c:if test="${pieday5.total5 > 0 }"> 
				  {
					    type: "Director",
					    percent: ${pieday5.total5}
					  }
				  </c:if>
		  
		  ]
		}
		</c:if>
		
		
		
		
		];
		series.data.setAll(generateChartData());


		function generateChartData() {
		  var chartData = [];
		  for (var i = 0; i < types.length; i++) {
		    if (i == selected) {
		      for (var x = 0; x < types[i].subs.length; x++) {
		        chartData.push({
		          type: types[i].subs[x].type,
		          percent: types[i].subs[x].percent,
		          color: types[i].color,
		          pulled: true,
		          sliceSettings: {
		            active: true
		          }
		        });
		      }
		    } else {
		      chartData.push({
		        type: types[i].type,
		        percent: types[i].percent,
		        color: types[i].color,
		        id: i
		      });
		    }
		  }
		  return chartData;
		}

		}); // end am5.ready()
		
		
		
		
		
		am5.ready(function() {

			// Create root and chart
			var root = am5.Root.new("pie2");

			root.setThemes([
			  am5themes_Animated.new(root)
			]);

			var chart = root.container.children.push( 
			  am5percent.PieChart.new(root, {
			    layout: root.verticalLayout
			  }) 
			);

			// Create series
			var series = chart.series.push(
			  am5percent.PieSeries.new(root, {
			    valueField: "percent",
			    categoryField: "type",
			    fillField: "color",
			    alignLabels: false,
			    legendLabelText: "type",
			    legendValueText: "percent"
			  })
			);

			series.slices.template.set("templateField", "sliceSettings",);
			series.slices.template.set('tooltipText', '{type}: {percent}');
			
			//series.labels.template.set("radius", 30);
			series.labels.template.set('text', '{type}: {percent}');
			series.labels.template.setAll({
				  text: "{type}",
				  textType: "circular",
				  inside: true,
				  radius: 10
				});
			

			// Set up click events
			series.slices.template.events.on("click", function(event) {
			  console.log(event.target.dataItem.dataContext)
			  if (event.target.dataItem.dataContext.id != undefined) {
			    selected = event.target.dataItem.dataContext.id;
			  } else {
			    selected = undefined;
			  }
			  series.data.setAll(generateChartData());
			});

			// Define data
			var selected;
			var types = [
			
				
				  <c:if test="${piemonth1.total1 + piemonth1.total2 + piemonth1.total3 +piemonth1.total4 +piemonth1.total5 > 0 }"> 
				{
			  type: "06:00 - 09:00",
				percent: ${piemonth1.total1 + piemonth1.total2 + piemonth1.total3 +piemonth1.total4 +piemonth1.total5},
			  color: series.get("colors").getIndex(0),
			  subs: [
				 
			<c:if test="${piemonth1.total1 > 0 }">   
				  {
			    type: "Labour",
			    percent: ${piemonth1.total1}
			  }, 
			  </c:if>
			  <c:if test="${piemonth1.total2 > 0 }">    
			  {
			    type: "Seniour Labour",
			    percent: ${piemonth1.total2}
			  }, 
			  </c:if>
			  <c:if test="${piemonth1.total3 > 0 }">  
			  {
			    type: "Supervisor",
			    percent: ${piemonth1.total3}
			  }
			  , 
			  </c:if>
			  <c:if test="${piemonth1.total4 > 0 }">    
			  {
				    type: "Manager",
				    percent: ${piemonth1.total4}
				  }
			  , 
			  </c:if> 
			  <c:if test="${piemonth1.total5 > 0 }">  
			  {
				    type: "Director",
				    percent: ${piemonth1.total5}
				  }
			  </c:if> 	  	  
			  
			  ]
			}
			
			, 
			</c:if>
			
			  <c:if test="${piemonth2.total1 + piemonth2.total2 + piemonth2.total3 +piemonth2.total4 +piemonth2.total5 > 0 }"> 
			{
			 
				type: "09:01 - 11:00",
				percent: ${piemonth2.total1 + piemonth2.total2 + piemonth2.total3 +piemonth2.total4 +piemonth2.total5},
			  color: series.get("colors").getIndex(1),
			  subs: [
				  
					<c:if test="${piemonth2.total1 > 0 }">   
					  {
				    type: "Labour",
				    percent: ${piemonth2.total1}
				  }, 
				  </c:if>
				  <c:if test="${piemonth2.total2 > 0 }">    
				  {
				    type: "Seniour Labour",
				    percent: ${piemonth2.total2}
				  }, 
				  </c:if>
				  <c:if test="${piemonth2.total3 > 0 }">  
				  {
				    type: "Supervisor",
				    percent: ${piemonth2.total3}
				  }
				  , 
				  </c:if>
				  <c:if test="${piemonth2.total4 > 0 }">    
				  {
					    type: "Manager",
					    percent: ${piemonth2.total4}
					  }
				  , 
				  </c:if> 
				  <c:if test="${piemonth2.total5 > 0 }">  
				  {
					    type: "Director",
					    percent: ${piemonth2.total5}
					  }
				  </c:if> 
			  
			  ]
			}
			, 
			</c:if>
			
			  <c:if test="${piemonth3.total1 + piemonth3.total2 + piemonth3.total3 +piemonth3.total4 +piemonth3.total5 > 0 }"> 
			{
				 
				type: "11:01 - 13:00",
				percent: ${piemonth3.total1 + piemonth3.total2 + piemonth3.total3 +piemonth3.total4 +piemonth3.total5},
			  color: series.get("colors").getIndex(2),
			  subs: [
					<c:if test="${piemonth3.total1 > 0 }">   
					  {
				    type: "Labour",
				    percent: ${piemonth3.total1}
				  }, 
				  </c:if>
				  <c:if test="${piemonth3.total2 > 0 }">    
				  {
				    type: "Seniour Labour",
				    percent: ${piemonth3.total2}
				  }, 
				  </c:if>
				  <c:if test="${piemonth3.total3 > 0 }">  
				  {
				    type: "Supervisor",
				    percent: ${piemonth3.total3}
				  }
				  , 
				  </c:if>
				  <c:if test="${piemonth3.total4 > 0 }">    
				  {
					    type: "Manager",
					    percent: ${piemonth3.total4}
					  }
				  , 
				  </c:if> 
				  <c:if test="${piemonth3.total5 > 0 }">  
				  {
					    type: "Director",
					    percent: ${piemonth3.total5}
					  }
				  </c:if> 
			  
			  
			  ]
			}
			
			, 
			
			</c:if>
			  <c:if test="${piemonth4.total1 + piemonth4.total2 + piemonth4.total3 +piemonth4.total4 +piemonth4.total5  > 0 }"> 
			{
				 
				type: "13:01 - 15:00",
				percent: ${piemonth4.total1 + piemonth4.total2 + piemonth4.total3 +piemonth4.total4 +piemonth4.total5},
			  color: series.get("colors").getIndex(3),
			  subs: [
				  
					<c:if test="${piemonth4.total1 > 0 }">   
					  {
				    type: "Labour",
				    percent: ${piemonth4.total1}
				  }, 
				  </c:if>
				  <c:if test="${piemonth4.total2 > 0 }">    
				  {
				    type: "Seniour Labour",
				    percent: ${piemonth4.total2}
				  }, 
				  </c:if>
				  <c:if test="${piemonth4.total3 > 0 }">  
				  {
				    type: "Supervisor",
				    percent: ${piemonth4.total3}
				  }
				  , 
				  </c:if>
				  <c:if test="${piemonth4.total4 > 0 }">    
				  {
					    type: "Manager",
					    percent: ${piemonth4.total4}
					  }
				  , 
				  </c:if> 
				  <c:if test="${piemonth4.total5 > 0 }">  
				  {
					    type: "Director",
					    percent: ${piemonth4.total5}
					  }
				  </c:if> 
			  
			  ]
			}
			, 
			</c:if>
			  <c:if test="${piemonth5.total1 + piemonth5.total2 + piemonth5.total3 +piemonth5.total4 +piemonth5.total5  > 0 }"> 
			{
				 
				type: "15:01 - 18:00",
				percent: ${piemonth5.total1 + piemonth5.total2 + piemonth5.total3 +piemonth5.total4 +piemonth5.total5},
			  color: series.get("colors").getIndex(4),
			  subs: [
				  
					<c:if test="${piemonth5.total1 > 0 }">   
					  {
				    type: "Labour",
				    percent: ${piemonth5.total1}
				  }, 
				  </c:if>
				  <c:if test="${piemonth5.total2 > 0 }">    
				  {
				    type: "Seniour Labour",
				    percent: ${piemonth5.total2}
				  }, 
				  </c:if>
				  <c:if test="${piemonth5.total3 > 0 }">  
				  {
				    type: "Supervisor",
				    percent: ${piemonth5.total3}
				  }
				  , 
				  </c:if>
				  <c:if test="${piemonth5.total4 > 0 }">    
				  {
					    type: "Manager",
					    percent: ${piemonth5.total4}
					  }
				  , 
				  </c:if> 
				  <c:if test="${piemonth5.total5 > 0 }">  
				  {
					    type: "Director",
					    percent: ${piemonth5.total5}
					  }
				  </c:if>	  	  
			  
			  ]
			}
			</c:if>
			];
			series.data.setAll(generateChartData());


			function generateChartData() {
			  var chartData = [];
			  for (var i = 0; i < types.length; i++) {
			    if (i == selected) {
			      for (var x = 0; x < types[i].subs.length; x++) {
			        chartData.push({
			          type: types[i].subs[x].type,
			          percent: types[i].subs[x].percent,
			          color: types[i].color,
			          pulled: true,
			          sliceSettings: {
			            active: true
			          }
			        });
			      }
			    } else {
			      chartData.push({
			        type: types[i].type,
			        percent: types[i].percent,
			        color: types[i].color,
			        id: i
			      });
			    }
			  }
			  return chartData;
			}

			}); // end am5.ready()
			
			
			am5.ready(function() {

				// Create root and chart
				var root = am5.Root.new("pie3");

				root.setThemes([
				  am5themes_Animated.new(root)
				]);

				var chart = root.container.children.push( 
				  am5percent.PieChart.new(root, {
				    layout: root.verticalLayout
				  }) 
				);

				// Create series
				var series = chart.series.push(
				  am5percent.PieSeries.new(root, {
				    valueField: "percent",
				    categoryField: "type",
				    fillField: "color",
				    alignLabels: false,
				    legendLabelText: "type",
				    legendValueText: "percent"
				  })
				);

				series.slices.template.set("templateField", "sliceSettings",);
				series.slices.template.set('tooltipText', '{type}: {percent}');
				
				//series.labels.template.set("radius", 30);
				series.labels.template.set('text', '{type}: {percent}');
				series.labels.template.setAll({
					  text: "{type}",
					  textType: "circular",
					  inside: true,
					  radius: 10
					});
				

				// Set up click events
				series.slices.template.events.on("click", function(event) {
				  console.log(event.target.dataItem.dataContext)
				  if (event.target.dataItem.dataContext.id != undefined) {
				    selected = event.target.dataItem.dataContext.id;
				  } else {
				    selected = undefined;
				  }
				  series.data.setAll(generateChartData());
				});

				// Define data
				var selected;
				var types = [
					
					   
					  <c:if test="${pieyear1.total1 + pieyear1.total2 + pieyear1.total3 +pieyear1.total4 +pieyear1.total5 > 0 }">   
					{
				  type: "06:00 - 09:00",
				  percent: ${pieyear1.total1 + pieyear1.total2 + pieyear1.total3 +pieyear1.total4 +pieyear1.total5},
				  color: series.get("colors").getIndex(0),
				  subs: [
					  
					  <c:if test="${pieyear1.total1 > 0 }">   

					  {
						    type: "Labour",
						    percent: ${pieyear1.total1}
						  }, 
							</c:if>  
						  <c:if test="${pieyear1.total2 > 0 }">     
						  {
						    type: "Seniour Labour",
						    percent: ${pieyear1.total2}
						  },
							</c:if>  
						  
						  <c:if test="${pieyear1.total3 > 0 }">
						  {
						    type: "Supervisor",
						    percent: ${pieyear1.total3}
						  }
						  , 
						  </c:if>  
						  <c:if test="${pieyear1.total4 > 0 }">
						  {
							    type: "Manager",
							    percent: ${pieyear1.total4}
							  }
						  ,
						  </c:if> 
						  <c:if test="${pieyear1.total5 > 0 }">
						  {
							    type: "Director",
							    percent: ${pieyear1.total5}
							  }
						  </c:if> 
					  	  
				  
				  ]
				}
				
				, 
				
				</c:if>
				  <c:if test="${pieyear2.total1 + pieyear2.total2 + pieyear2.total3 +pieyear2.total4 +pieyear2.total5 > 0 }">   
				{
				 
					type: "09:01 - 11:00",
					 percent: ${pieyear2.total1 + pieyear2.total2 + pieyear2.total3 +pieyear2.total4 +pieyear2.total5},
				  color: series.get("colors").getIndex(1),
				  subs: [
					  
					  <c:if test="${pieyear2.total1 > 0 }">   

					  {
						    type: "Labour",
						    percent: ${pieyear2.total1}
						  }, 
							</c:if>  
						  <c:if test="${pieyear4.total2 > 0 }">     
						  {
						    type: "Seniour Labour",
						    percent: ${pieyear2.total2}
						  },
							</c:if>  
						  
						  <c:if test="${pieyear2.total3 > 0 }">
						  {
						    type: "Supervisor",
						    percent: ${pieyear2.total3}
						  }
						  , 
						  </c:if>  
						  <c:if test="${pieyear2.total4 > 0 }">
						  {
							    type: "Manager",
							    percent: ${pieyear2.total4}
							  }
						  ,
						  </c:if> 
						  <c:if test="${pieyear2.total5 > 0 }">
						  {
							    type: "Director",
							    percent: ${pieyear2.total5}
							  }
						  </c:if>  
				  
				  
				  ]
				}
			
				
				,
				</c:if>
				
				  <c:if test="${pieyear3.total1 + pieyear3.total2 + pieyear3.total3 +pieyear3.total4 +pieyear3.total5 > 0 }">   
				
				{
					 
					type: "11:01 - 13:00",
				  percent: ${pieyear3.total1 + pieyear3.total2 + pieyear3.total3 +pieyear3.total4 +pieyear3.total5},
				  color: series.get("colors").getIndex(2),
				  subs: [
					  
					  <c:if test="${pieyear3.total1 > 0 }">   
					  {
						    type: "Labour",
						    percent: ${pieyear3.total1}
						  }, 
							</c:if>  
						  <c:if test="${pieyear3.total2 > 0 }">     
						  {
						    type: "Seniour Labour",
						    percent: ${pieyear3.total2}
						  },
							</c:if>  
						  
						  <c:if test="${pieyear3.total3 > 0 }">
						  {
						    type: "Supervisor",
						    percent: ${pieyear3.total3}
						  }
						  , 
						  </c:if>  
						  <c:if test="${pieyear3.total4 > 0 }">
						  {
							    type: "Manager",
							    percent: ${pieyear3.total4}
							  }
						  ,
						  </c:if> 
						  <c:if test="${pieyear3.total5 > 0 }">
						  {
							    type: "Director",
							    percent: ${pieyear3.total5}
							  }
						  </c:if>    	  
				  
				  ]
				}
				
				,
				</c:if>
				  <c:if test="${pieyear4.total1 + pieyear4.total2 + pieyear4.total3 +pieyear4.total4 +pieyear4.total5 > 0 }">   
				{
					 
					type: "13:01 - 15:00",
					 percent: ${pieyear4.total1 + pieyear4.total2 + pieyear4.total3 +pieyear4.total4 +pieyear4.total5},
				  color: series.get("colors").getIndex(3),
				  subs: [
					  
					  <c:if test="${pieyear4.total1 > 0 }">   
					  {
						    type: "Labour",
						    percent: ${pieyear4.total1}
						  }, 
							</c:if>  
						  <c:if test="${pieyear4.total2 > 0 }">     
						  {
						    type: "Seniour Labour",
						    percent: ${pieyear4.total2}
						  },
							</c:if>  
						  
						  <c:if test="${pieyear4.total3 > 0 }">
						  {
						    type: "Supervisor",
						    percent: ${pieyear4.total3}
						  }
						  , 
						  </c:if>  
						  <c:if test="${pieyear4.total4 > 0 }">
						  {
							    type: "Manager",
							    percent: ${pieyear4.total4}
							  }
						  ,
						  </c:if> 
						  <c:if test="${pieyear4.total5 > 0 }">
						  {
							    type: "Director",
							    percent: ${pieyear4.total5}
							  }
						  </c:if>    	  
				  
				  
				  ]
				}
				, 
				</c:if>
				  <c:if test="${pieyear5.total1 + pieyear5.total2 + pieyear5.total3 +pieyear5.total4 +pieyear5.total5 > 0 }">   
				{
					 
					type: "15:01 - 18:00",
					 percent: ${pieyear5.total1 + pieyear5.total2 + pieyear5.total3 +pieyear5.total4 +pieyear5.total5},
				  color: series.get("colors").getIndex(4),
				  subs: [
					  
					  
					  <c:if test="${pieyear5.total1 > 0 }">   
					  {
						    type: "Labour",
						    percent: ${pieyear5.total1}
						  }, 
							</c:if>  
						  <c:if test="${pieyear5.total2 > 0 }">     
						  {
						    type: "Seniour Labour",
						    percent: ${pieyear5.total2}
						  },
							</c:if>  
						  
						  <c:if test="${pieyear5.total3> 0 }">
						  {
						    type: "Supervisor",
						    percent: ${pieyear5.total3}
						  }
						  , 
						  </c:if>  
						  <c:if test="${pieyear5.total4 > 0 }">
						  {
							    type: "Manager",
							    percent: ${pieyear5.total4}
							  }
						  ,
						  </c:if> 
						  <c:if test="${pieyear5.total5 > 0 }">
						  {
							    type: "Director",
							    percent: ${pieyear5.total5}
							  }
						  </c:if>    	  
				  
				  ]
				}
				</c:if>
				];
				series.data.setAll(generateChartData());


				function generateChartData() {
				  var chartData = [];
				  for (var i = 0; i < types.length; i++) {
				    if (i == selected) {
				      for (var x = 0; x < types[i].subs.length; x++) {
				        chartData.push({
				          type: types[i].subs[x].type,
				          percent: types[i].subs[x].percent,
				          color: types[i].color,
				          pulled: true,
				          sliceSettings: {
				            active: true
				          }
				        });
				      }
				    } else {
				      chartData.push({
				        type: types[i].type,
				        percent: types[i].percent,
				        color: types[i].color,
				        id: i
				      });
				    }
				  }
				  return chartData;
				}

				}); // end am5.ready()
</script>
</body>


