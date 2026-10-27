<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="common/mainhead.jspf" %>    
  
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/vendors.min.css">
    
         <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/calendars/fullcalendar.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/pages/app-calendar.css">
        
           <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/pickers/flatpickr/flatpickr.min.css">
      
<title>File index</title>
</head>
<style>
.box {
  display: flex;
}

.left {
.box {
  display: flex;
}

.left {
  flex: 3;
  padding: 20px;
}

.right {
  flex: 7;
  background-color: lightgray; /* สีพื้นหลังของส่วน right */
}

.tabs {
  display: flex;
  background-color: #f2f2f2; /* สีพื้นหลังของแท็บ */
  border-radius: 5px; /* ขอบมนไปให้เป็นทรงกลม */
  margin-bottom: 10px; /* ระยะห่างด้านล่างของแท็บ */
}

.list {
  list-style-type: none;
  padding: 0;
  display: flex;
}

.list li {
  margin-right: 20px; /* ระยะห่างระหว่างแท็บ */
  cursor: pointer; /* เพื่อให้เป็นตัวชี้ */
  padding: 10px; /* ขนาดขอบของแท็บ */
  border: 1px solid transparent; /* ขอบเริ่มต้น */
  border-radius: 5px; /* ขอบมนไปให้เป็นทรงกลม */
}

.list li:hover {
  border-color: #007bff; /* เปลี่ยนสีขอบเมื่อนำเมาส์ไปชี้ */
}

.row {
  font-weight: bold;
}



/* Style the tab */
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  display: none;
  padding: 6px 12px;
  border: 1px solid #ccc;
  border-top: none;
}



.fc-daygrid-event-dot{

display:none;
}
.fc-event-time{
display:none;
}
</style>



<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  


    <!-- BEGIN: Content-->
    <div class="app-content content ">
     
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper container-xxl p-0">
         
            <div class="content-body">
            
            <div class="row">
                    <div class="col-12">
                        <ul class="nav nav-pills mb-2">
                            <!-- account -->
                            
                            
                               <li class="nav-item" >
                                <a class="nav-link active record" href="#">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-lock font-medium-3 me-50"><rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect><path d="M7 11V7a5 5 0 0 1 10 0v4"></path></svg>
                                    <span class="fw-bold"><spring:message code="idoc.step1" text="ตารางงาน" /></span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link  user" href="#">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-user font-medium-3 me-50"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                                    <span class="fw-bold"><spring:message code="idoc.step2" text="ประสิทธิภาพ" /></span>
                                </a>
                            </li>
                            <!-- security -->
                         
                            <!-- billing and plans -->
                            <li class="nav-item">
                                <a class="nav-link doc"  href="#">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-bookmark font-medium-3 me-50"><path d="M19 21l-7-5-7 5V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2z"></path></svg>
                                    <span class="fw-bold"><spring:message code="idoc.step3" text="เอกสารคุณภาพของฉัน" /></span>
                                </a>
                            </li>
                       
                        
                        </ul>

<div id="users" style="display:none">
    
                  <div class="row">
                  
                   <div class="col-12">
                   
                  

                                <h4 class="card-title"><spring:message code="idoc.step2" text="ประสิทธิภาพ" /></h4>
                          </div>
                       </div>
                       
                        <div class="row">
                        
                        
                       <div class="col-lg-2  col-12">
                            <div class="card">
                            
                                <div class="card-header flex-column align-items-start pb-0">
                                 
                                    <h2 class="fw-bolder mt-1">${num_daily } /${num_daily_targrt } </h2>
                                    <p class="card-text"><spring:message code="idoc.due1" text="ทุกวัน" /></p>
                                </div>
                                <br>
                  <div id="chartdiv" style="min-height: 100px;height: 100px;"></div>
                          <br>
                            </div>
                        </div>
                        
                        
                        <div class="col-lg-2 col-sm-6 col-12">
                            <div class="card">
                                <div class="card-header flex-column align-items-start pb-0">
                               
                                      <h2 class="fw-bolder mt-1">${num_weekly } /${num_weekly_targrt }  </h2>
                                    <p class="card-text"><spring:message code="idoc.due2" text="ทุกอาทิตย์" /></p>
                                </div>
                            <br>
                           <div id="chartdiv2" style="min-height: 100px;height: 100px;"></div>
                            <br>
                            
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 col-12">
                            <div class="card">
                                <div class="card-header flex-column align-items-start pb-0">
                                   
                                      <h2 class="fw-bolder mt-1">${num_monthly } /${num_monthly_targrt }  </h2>
                                    <p class="card-text"><spring:message code="idoc.due3" text="ทุกเดือน" /></p>
                                </div>
                            <br>
                           <div id="chartdiv3" style="min-height: 100px;height: 100px;"></div>
                            <br>
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 col-12">
                            <div class="card">
                                <div class="card-header flex-column align-items-start pb-0">
                                   
                                      <h2 class="fw-bolder mt-1">${num_annually } /${num_annually_targrt } </h2>
                                    <p class="card-text"><spring:message code="idoc.due4" text="ไม่กำหนด" /></p>
                                </div>
                              <br>
                           <div id="chartdiv4" style="min-height: 100px;height: 100px;"></div>
                            <br>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6 col-12">
                     
                       
                      
                            <div class="card">
                                <div class="card-header">
                                    <div>
                                        <h2 class="fw-bolder mb-0">${num_login } /366</h2>
                                        <p class="card-text"><spring:message code="idoc.due5" text="จำนวนวันที่ Login" /></p>
                                    </div>
                                    <div class="avatar bg-light-primary p-50 m-0">
                                        <div class="avatar-content">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity font-medium-5"><polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
                                     </div>
                                    </div>
                                </div>
                     </div>
                     
                     <div class="row">
                     <div class="col-lg-6">
                          <div class="card">
                                <div class="card-header">
                                    <div>
                                        <h2 class="fw-bolder mb-0"><spring:message code="idoc.due6" text="ลำดับ" /> ${ranks1 }</h2>
                                        <p class="card-text"><spring:message code="idoc.due7" text="ขาดการอัพโหลด" /></p>
                                        
                                    </div>
                                    <div class="avatar bg-light-danger p-50 m-0">
                                        <div class="avatar-content">
                                             <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trending-up avatar-icon"><polyline points="23 6 13.5 15.5 8.5 10.5 1 18"></polyline><polyline points="17 6 23 6 23 12"></polyline></svg>
                                        </div>
                                    </div>
                                </div>
                     </div>
                     </div>
                           <div class="col-lg-6">
                          <div class="card">
                                <div class="card-header">
                                    <div>
                                        <h2 class="fw-bolder mb-0"><spring:message code="idoc.due6" text="ลำดับ" /> ${ranks2 }</h2>
                                        <p class="card-text"><spring:message code="idoc.due8" text="อัพโหลดล่าช้า" /></p>
                                        
                                    </div>
                                    <div class="avatar bg-light-danger p-50 m-0">
                                        <div class="avatar-content">
                                             <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trending-up avatar-icon"><polyline points="23 6 13.5 15.5 8.5 10.5 1 18"></polyline><polyline points="17 6 23 6 23 12"></polyline></svg>
                                        </div>
                                    </div>
                                </div>
                     </div>
                     </div>
                     </div>  
                        </div>
                    </div>
        
            
                       
</div>

    <div id="record" >  
        <!-- Full calendar start -->
                <section>
                    <div class="app-calendar overflow-hidden border">
                        <div class="row g-0">
                            <!-- Sidebar -->
                            <div class="col app-calendar-sidebar flex-grow-0 overflow-hidden d-flex flex-column" id="app-calendar-sidebar">
                                <div class="sidebar-wrapper">
                                  
                                  
                            
                                    
                                    
                                    
                                    <div class="card-body pb-0">
                                        <h5 class="section-label mb-1">
                                            <span class="align-middle">
                                              <spring:message code="Filter.Filter" text="กรองงาน" />
                                            </span>
                                        </h5>
                                        <div class="form-check mb-1">
                                            <input type="checkbox" class="form-check-input select-all" id="select-all" checked />
                                            <label class="form-check-label" for="select-all">
                                              <spring:message code="Filter.ALL" text="งานอัพโหลดบันทึก" />
                                           </label>
                                        </div>
                                        <div class="calendar-events-filter">
                                            <div class="form-check form-check-danger mb-1">
                                                <input type="checkbox" class="form-check-input input-filter" id="personal" data-value="personal" checked />
                                                <label class="form-check-label" for="personal">
                                                <spring:message code="idoc.step1" text="งานอัพโหลดบันทึก" />
                                                </label>
                                            </div>
                                            <div class="form-check form-check-primary mb-1" style="display:none">
                                                <input type="checkbox" class="form-check-input input-filter" id="business" data-value="business" checked />
                                                <label class="form-check-label" for="business">
                                                <spring:message code="idoc.step2" text="รหัสเอกสาร" />
                                                </label>
                                            </div>
                                              <div class="form-check form-check-success mb-1">
                                                <input type="checkbox" class="form-check-input input-filter" id="holiday" data-value="holiday" checked />
                                                <label class="form-check-label" for="holiday">
                                                <spring:message code="Filter.holiday" text="วันหยุด" />
                                                </label>
                                            </div>
                                           
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-auto">
                                    <img src="/resources/app-assets/images/pages/calendar-illustration.png" alt="Calendar illustration" class="img-fluid" />
                                </div>
                            </div>
                            <!-- /Sidebar -->

                            <!-- Calendar -->
                            <div class="col position-relative">
                                <div class="card shadow-none border-0 mb-0 rounded-0">
                                    <div class="card-body pb-0">
                                        <div id="calendar"></div>
                                    </div>
                                </div>
                            </div>
                            <!-- /Calendar -->
                            <div class="body-content-overlay"></div>
                        </div>
                    </div>
                 
                </section>
                <!-- Full calendar end -->
    </div>
     
    
    
    
        <div id="doc" style="display:none">  
        
        
        
                         
         <div class="row">
           
                  
                   <div class="col-12">
                   
                   
                   
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title"><spring:message code="idoc.step3" text="เอกสารคุณภาพของฉัน" /></h4>
                            </div>
                            
                  
                            
                            <div class="card-body"> 
                            
                            
                            
                            <div class="table-responsive">
                             <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                <br> <br>
                   <table class="table" id="data-table">
                                    <thead>
                                        <tr >
                                            <th >
                                            <spring:message code="tables.No" text="ลำดับ​" />
                                            </th>
                                                <th >  <spring:message code="tables.docid" text="รหัสเอกสาร​" />(Rev)</th>
                                           <th >
                                             <spring:message code="tables.storage" text="แหล่งที่เก็บ" />
                                           </th>
                                             <th >
                                             <spring:message code="btn.action" />	
                                           </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${file }" var="row" varStatus="count">
                                     
                               
                                        <tr>
                                        
                                            <td>${count.count }</td>
                                                <td>${row.file_name_original }</td> 
                                       
                                          
                                           
                                                  <td>${row.file_path_full }</td>
                                                   <td>
                                       
           <c:if test="${row.file_type2 ==  0}">                                         		
  <a href="/subfileinfo-filemanager?id=${row.file_id }&&Folders=${row.file_path}" class="btn btn-light waves-effect waves-float waves-light">
							          								  <spring:message code="btn.view" />	</a> 
					</c:if>		          								
							          								
		   <c:if test="${row.file_type2 !=  0}">    					          								
		   
		                                      <c:if test="${row.file_type2  == -1}">    
                              <spring:message code="file.filecanceled" text="ถูกยกเลิก"  var="status" />
                             
                         
                               </c:if> 
                             <c:if test="${row.file_type2  == -2}">    
                                <spring:message code="file.fileexpired" text="หมดอายุ"  var="status" />
                      
                              
                               </c:if>     
                               
                                <c:if test="${row.file_type2  == -3}">    
                                <spring:message code="file.fileexpired2" text="เวอร์ชั่นเก่า"  var="status" />
                                
                              
                               </c:if>   
				</c:if>					          								
							          								   
                                                   </td>
                                        </tr>
                                        
                                       
                                        
                                          </c:forEach>
                                    </tbody>
                                </table></div>
                   </div>   </div>
                    </div>
                   
              
                    
                </div>     
                        
    </div>               
                    </div>
                </div>
            
            
            
            
            
            
        
            
          <section id="multiple-column-form">
              
                     <!-- Resources -->
<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/radar.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>


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



am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdivs");

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
axisDataItem.set("value", 0);

var bullet = axisDataItem.set("bullet", am5xy.AxisBullet.new(root, {
  sprite: am5radar.ClockHand.new(root, {
    radius: am5.percent(99)
  })
}));

xAxis.createAxisRange(axisDataItem);

axisDataItem.get("grid").set("visible", false);

setInterval(() => {
  axisDataItem.animate({
    key: "value",
    to: Math.round(Math.random() * 100),
    duration: 800,
    easing: am5.ease.out(am5.ease.cubic)
  });
}, 2000);

// Make stuff animate on load
chart.appear(1000, 100);

}); // end am5.ready()
</script>


    


<!-- Chart code -->
<script>
am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdiv");

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

var data =  ${num_daily * 100 / num_daily_targrt };
if (isNaN(data)) {
	data = 0;
}


var axisDataItem = xAxis.makeDataItem({});
axisDataItem.set("value", data);

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
var root = am5.Root.new("chartdiv2");

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

var data = ${num_weekly * 100 / num_weekly_targrt };
if (isNaN(data)) {
	data = 0;
}

axisDataItem.set("value", data);

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
var root = am5.Root.new("chartdiv3");

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


var data = ${num_monthly * 100 / num_monthly_targrt };
if (isNaN(data)) {
	data = 0;
}


axisDataItem.set("value", data);

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
var root = am5.Root.new("chartdiv4");

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

var data = ${num_annually * 100 / num_annually_targrt };
if (isNaN(data)) {
	data = 0;
}

axisDataItem.set("value", data);

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
                    
           </section>         
 
                   
                    </div>
             

            </div>
        </div>
  
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>






<%@ include file="common/mainfoot.jspf" %>  
   

 
 

    <!-- BEGIN: Page Vendor JS-->
    <script src="/resources/app-assets/vendors/js/ui/jquery.sticky.js"></script>
    <script src="/resources/app-assets/vendors/js/calendar/fullcalendar.min.js"></script>
    <script src="/resources/app-assets/vendors/js/extensions/moment.min.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/validation/jquery.validate.min.js"></script>
    <script src="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js"></script>
    <!-- END: Page Vendor JS-->
  <script src="/resources/app-assets/vendors/js/forms/select/select2.full.min.js"></script>


    <!-- BEGIN: Page JS-->
    <script >
    
    'use strict';

    var date = new Date();
    var nextDay = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
    // prettier-ignore
    var nextMonth = date.getMonth() === 11 ? new Date(date.getFullYear() + 1, 0, 1) : new Date(date.getFullYear(), date.getMonth() + 1, 1);
    // prettier-ignore
    var prevMonth = date.getMonth() === 11 ? new Date(date.getFullYear() - 1, 0, 1) : new Date(date.getFullYear(), date.getMonth() - 1, 1);

    

    
    var events = [
    	
        
      <c:forEach items="${task }" var="row" varStatus="count">
      {
        id: ${row.task_id},
        url: '/task-filemanager/${row.task_id}',
        title: '${row.task_name}',
        start: new Date("${row.task_datestart} 08:00"),
        end: new Date("${row.task_dateend} 09:00"),
        allDay: false,
        extendedProps: {
          calendar: 'Business'
        }
      },
      </c:forEach>
      
      <c:forEach items="${report_pn }" var="row" varStatus="count">
      {
        id: ${row.report_pn_id},
        url: '/duepn-filemanager?id=${row.report_pn_idrefer}&&step=1&&group=3',
        title: '${row.upload_pn_name}',
        start: new Date("${row.report_pn_date} 11:00"),
        end: new Date("${row.report_pn_date} 12:00"),
        allDay: false,
        extendedProps: {
          calendar: 'Personal'
        }
      },
      </c:forEach>
      <c:forEach items="${holidays }" var="row" varStatus="count">
      {
        id: ${row.holiday_id},
        url: '#',
        title: '${row.holiday_name}',
        start: new Date("${row.holiday_date} 08:00"),
        end: new Date("${row.holiday_date} 16:00"),
        allDay: false,
        extendedProps: {
          calendar: 'Holiday'
        }
      },
      </c:forEach>
    ];


    
    
    </script>
    <script src="/resources/app-assets/js/scripts/pages/app-calendar.js"></script>
    
</body>


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

var element = document.getElementById("nav_doc");
element.classList.add("active");  

    </script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var userLink = document.querySelector('.user');
        var recordLink = document.querySelector('.record');
        var docLink = document.querySelector('.doc');
        var userDiv = document.getElementById('users');
        var recordDiv = document.getElementById('record');
        var docDiv = document.getElementById('doc');

        userLink.addEventListener('click', function() {
            userDiv.style.display = 'block';
            recordDiv.style.display = 'none';
            docDiv.style.display = 'none';
            userLink.classList.add('active');
            recordLink.classList.remove('active');
            docLink.classList.remove('active');
        });

        recordLink.addEventListener('click', function() {
            userDiv.style.display = 'none';
            recordDiv.style.display = 'block';
            docDiv.style.display = 'none';
            userLink.classList.remove('active');
            recordLink.classList.add('active');
            docLink.classList.remove('active');
        });

        docLink.addEventListener('click', function() {
            userDiv.style.display = 'none';
            recordDiv.style.display = 'none';
            docDiv.style.display = 'block';
            userLink.classList.remove('active');
            recordLink.classList.remove('active');
            docLink.classList.add('active');
        });
    });
    
    
    document.addEventListener("DOMContentLoaded", function() {
        var showDataLinks = document.querySelectorAll('.showdara');

        showDataLinks.forEach(function(link) {
            link.addEventListener('click', function(event) {
                event.preventDefault();
                var emailUserList = document.querySelector('.email-user-list');
                var emailDataList = document.querySelector('.email-data-list');
                
                emailUserList.style.display = 'none';
                emailDataList.style.display = 'block';
            });
        });
    });
</script>    

    

 
 
 
 
 
 
 
 
 