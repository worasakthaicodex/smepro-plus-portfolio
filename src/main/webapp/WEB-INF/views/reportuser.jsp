<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>KPI</title>

<style>
#chartdiv {
  width: 100%;
  height: 200px;
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
                <section class="app-user-view-account">
                    <div class="row">
                   
                    <c:if test="${member.user_name  ==  null  or  member.user_name  == '' }">   
                    
                    
                   <div class="alert alert-secondary" role="alert">
                                            <h4 class="alert-heading">ไม่พบรายชื่อ</h4>
                                       
                                        </div>
                                        
                                        
                        <div class="d-flex justify-content-center pt-2">
                                            <a href="/showreportuser-filemanager?id=${id-1 }" class="btn btn-primary me-1">
                                            <i data-feather='arrow-left'></i>
                                            </a>
                                              <a href="/reportuser-filemanager?" class="btn btn-primary me-1"><i data-feather='home'></i></a>
                                            <a href="/showreportuser-filemanager?id=${id+1 }" class="btn btn-primary me-1"><i data-feather='arrow-right'></i></a>
                                        </div>
                                        
                          </c:if>                 
                       <c:if test="${member.user_name  !=  null  and member.user_name  != '' }">   
                    
                        <!-- User Sidebar -->
                        <div class="col-xl-3 col-lg-5 col-md-5 order-1 order-md-0">
                            <!-- User Card -->
                            <div class="card">
                                <div class="card-body">
                                    <div class="user-avatar-section">
                                        <div class="d-flex align-items-center flex-column">
                                            <img class="img-fluid rounded mt-3 mb-2"  style="display:none" src="../../../app-assets/images/portrait/small/avatar-s-2.jpg" height="110" width="110" alt="User avatar" />
                                            <div class="user-info text-center">
                                                <h4>${member.user_name }</h4>
                                                <span class="badge bg-light-secondary">${member.group_name}</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-around my-2 pt-75">
                                    
                                    
                              <!--
                                        <div class="d-flex align-items-start me-2">
                                            <span class="badge bg-light-primary p-75 rounded">
                                                <i data-feather="check" class="font-medium-2"></i>
                                            </span>
                                            <div class="ms-75">
                                                <h4 class="mb-0">0</h4>
                                                <small>จำนวนงาน</small>
                                            </div>
                                        </div>
                                //-->
                                       
                                     
                                         
                                        <div class="d-flex align-items-start">
                                            <span class="badge bg-light-primary p-75 rounded">
                                                <i data-feather="briefcase" class="font-medium-2"></i>
                                            </span>
                                            <div class="ms-75">
                                                <h4 class="mb-0"><span id="total"></span></h4>
                                                <small>จำนวนไฟล์ที่อัพโหลด</small>
                                            </div>
                                        </div>
                                    </div>
                                    <h4 class="fw-bolder border-bottom pb-50 mb-1">Details</h4>
                                    <div class="info-container">
                                        <ul class="list-unstyled">
                                            <li class="mb-75">
                                                <span class="fw-bolder me-25">ระดับพนักงาน:</span>
                                                <span>${member.group_level}</span>
                                            </li>
                                            <li class="mb-75">
                                                <span class="fw-bolder me-25">แผนก:</span>
                                                <span>${member.group_department}</span>
                                            </li>
                                           
                                        </ul>
                                        
                                        
                                        <div>
                                        
                                        <div class="d-flex justify-content-center pt-2">
                                            <a href="/showreportuser-filemanager?id=${id-1 }" class="btn btn-primary me-1">
                                            <i data-feather='arrow-left'></i>
                                            </a>
                                            
                                            <a href="/reportuser-filemanager?" class="btn btn-primary me-1"><i data-feather='home'></i></a>
                                            <a href="/showreportuser-filemanager?id=${id+1 }" class="btn btn-primary me-1"><i data-feather='arrow-right'></i></a>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /User Card -->
                         
                         
                             <div class="card" style="display:none">
                                <div class="card-body">
                        <div id="chartdiv"></div>
                                </div>
                            </div>
                            <!-- /User Card -->
                         
                         
                         
                        </div>
                        <!--/ User Sidebar -->

                        <!-- User Content -->
                        <div class="col-xl-9 col-lg-7 col-md-7 order-0 order-md-1">
                            <!-- User Pills -->
                            <ul class="nav nav-pills mb-2">
                                <li class="nav-item">
                                    <a class="nav-link active" id="record"  href="#">
                                        <i data-feather="user" class="font-medium-3 me-50"></i>
                                        <span class="fw-bold">การอัพโหลดบันทึก</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="activity"  href="#">
                                        <i data-feather="lock" class="font-medium-3 me-50"></i>
                                        <span class="fw-bold">กิจกรรม</span>
                                    </a>
                                </li>
                                

                                
                                <li class="nav-item" style="display:none">
                                    <a class="nav-link" href="app-user-view-billing.html">
                                        <i data-feather="bookmark" class="font-medium-3 me-50"></i>
                                        <span class="fw-bold">Billing & Plans</span>
                                    </a>
                                </li>
                                <li class="nav-item" style="display:none">
                                    <a class="nav-link" href="app-user-view-notifications.html">
                                        <i data-feather="bell" class="font-medium-3 me-50"></i><span class="fw-bold">Notifications</span>
                                    </a>
                                </li>
                                <li class="nav-item" style="display:none">
                                    <a class="nav-link" href="app-user-view-connections.html">
                                        <i data-feather="link" class="font-medium-3 me-50"></i><span class="fw-bold">Connections</span>
                                    </a>
                                </li>
                            </ul>
                            <!--/ User Pills -->
            
                            <!-- Project table -->
                            <div class="card"  id="divrecord">
                           
                                <div class="card-header">
                                    <h4 class="card-title">Performance</h4>
                                </div>
                                <div class="card-body">
                                    <ul class="nav nav-tabs" role="tablist">
                                    
                                   
                                    <li class="nav-item" >
                                            <a class="nav-link active" id="home-tab" data-bs-toggle="tab" href="#home0" aria-controls="home0" role="tab" aria-selected="true">Overview</a>
                                        </li>
                                      
                                        <li class="nav-item" >
                                            <a class="nav-link" id="home-tab" data-bs-toggle="tab" href="#home" aria-controls="home" role="tab" aria-selected="true">Success </a>
                                        </li>
                                      
                                        
                                        
                                        <li class="nav-item">
                                            <a class="nav-link" id="profile-tab" data-bs-toggle="tab" href="#profile" aria-controls="profile" role="tab" aria-selected="false">Failure</a>
                                        </li>
                                       
                                    </ul>
                                    <div class="tab-content">
                                    
                                     <div class="tab-pane active" id="home0" aria-labelledby="home-tab" role="tabpanel">
                                  <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Task</th>
                                            <th>Target</th>
                                            <th>Success</th>
                                            <th>Failure</th>
                          
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                               
                                                <span class="fw-bold">การอัพโหลดบันทึกคุณภาพ</span>
                                            </td>
                                               <td><span id="kpi0"></span></td>
                                            <td><span id="kpi1"></span></td>
                                           <td><span id="kpi2"></span></td>
                                       
                                     
                                        </tr>
                                      
                                       
                                      
                                    </tbody>
                                </table>
                                    </div>
                                    
                                        <div class="tab-pane" id="home" aria-labelledby="home-tab" role="tabpanel">
                                          
                                <div class="table-responsive">
                                
                                  <c:set var="success" value="0" />
                                 
                                  
                                      <c:forEach items="${upload_pn }" var="row" varStatus="count"> 
                                         <c:set var="due" value="" />
                                         
                                      
									<c:if test="${row.upload_pn_type ==  0 }">   

											<c:if test="${row.upload_pn_due ==  0 }">   
                      						<spring:message code="due.date" text="ทุกวัน"  var="due"/>
                     					 </c:if>
                                        <c:if test="${row.upload_pn_due ==  1 }">   
                      						<spring:message code="due.week" text="ทุกอาทิตย์"  var="due"/>
                     					 </c:if>
                     					    <c:if test="${row.upload_pn_due ==  2 }">   
                      						<spring:message code="due.month" text="ทุกเดือน"  var="due"/>
                     					 </c:if>
                     				</c:if>   
                     <c:if test="${row.upload_pn_type ==  1 }">
                     <c:set var="due" value="${row.upload_pn_date}" />
                     </c:if>      
                                         
                                         
                     
                                      
                                    ${count.count}. Record : ${row.upload_pn_name }    (${due})
                                      <table class="table datatable-project">
                                        <thead>
                                            <tr>
                                             <tr>
                                           <th>วันที่ต้องอัพโหลด</th>
                                             <th>วันที่อัพโหลด</th>
                                              <th>ขาดส่ง</th>
                                            <th>อัพโหลดช้าสะสม (วัน)</th>
                                      
                                        </tr>
                                          <c:forEach items="${report_success }" var="rows" varStatus="count"> 
                                             
                                             		<c:if test="${row.upload_pn_id ==  rows.report_pn_idrefer }">  
                                             		      <c:set var="success" value="${success+1 }" /> 
                                               <tr>
                                             <tr>
                                         <td>(${success }) ${rows.report_pn_date}</td>
                                            <td> ${rows.report_pn_dateupload}</td>
                                       <td> ${rows.report_pn_sendstatus}</td>
                                       <td> ${rows.report_pn_statuserror}</td>
                                      
                                        </tr>
                                        
                                        </c:if>
                                         </c:forEach>
                                         
                                         
                                           
                                        </thead>
                                    </table>
                                   <br><br>  <hr>
                                    <br><br>
                                        </c:forEach>
                                
                                </div>
                                        </div>
                                        <div class="tab-pane" id="profile" aria-labelledby="profile-tab" role="tabpanel">
                                        
                                         <c:set var="error" value="0" />
                                        
                                             <c:forEach items="${upload_pn }" var="row" varStatus="count"> 
                        <c:set var="due" value="" />
                                         
                                      
									<c:if test="${row.upload_pn_type ==  0 }">   

											<c:if test="${row.upload_pn_due ==  0 }">   
                      						<spring:message code="due.date" text="ทุกวัน"  var="due"/>
                     					 </c:if>
                                        <c:if test="${row.upload_pn_due ==  1 }">   
                      						<spring:message code="due.week" text="ทุกอาทิตย์"  var="due"/>
                     					 </c:if>
                     					    <c:if test="${row.upload_pn_due ==  2 }">   
                      						<spring:message code="due.month" text="ทุกเดือน"  var="due"/>
                     					 </c:if>
                     				</c:if>   
                     <c:if test="${row.upload_pn_type ==  1 }">
                     <c:set var="due" value="${row.upload_pn_date}" />
                     </c:if>      
                                         
                                         
                     
                                      
                                    ${count.count}. Record : ${row.upload_pn_name }    (${due})
                                      <table class="table datatable-project">
                                        <thead>
                                            <tr>
                                             <tr>
                                           <th>วันที่ต้องอัพโหลด</th>
                                             <th>วันที่อัพโหลด</th>
                                              <th>ขาดส่ง</th>
                                            <th>อัพโหลดช้าสะสม (วัน)</th>
                                      
                                        </tr>
                                        
                                       
                                                 <c:forEach items="${report_error }" var="rows" > 
                                             
                                             		<c:if test="${row.upload_pn_id ==  rows.report_pn_idrefer }">   
                                             		 <c:set var="error" value="${error+1}" />
                                               <tr>
                                             <tr>
                                         <td>(${error}) ${rows.report_pn_date}</td>
                                            <td> ${rows.report_pn_dateupload}</td>
                                              <td> ${rows.report_pn_sendstatus}</td>
                                                   <td> ${rows.report_pn_statuserror}</td>
                                      
                                        </tr>
                                        
                                        </c:if>
                                         </c:forEach>
                                        </thead>
                                    </table>
                                   <br><br>  <hr>
                                    <br><br>
                                        </c:forEach>
                                        </div>
                                    
                                    </div>
                                </div>
                     
                              
                            </div>
                            <!-- /Project table -->
          
                            <!-- Activity Timeline -->
                            <div class="card"  style="display:none" id="divactivity">
                                <h4 class="card-header">User Activity Timeline</h4>
                                <div class="card-body pt-1">
                                    <ul class="timeline ms-50">
                                       
                                        <c:forEach items="${logs }" var="row" varStatus="count">   
                                        		  
                                        <li class="timeline-item">
                                        
                                        
                                       <c:if test="${row.log_step ==  'LOGIN' }"> 
                                            <span class="timeline-point timeline-point-indicator"></span>
                                            </c:if> 
                                               <c:if test="${row.log_step ==  'LOGOUT'}"> 
                                             <span class="timeline-point timeline-point-danger timeline-point-indicator"></span>
                                            </c:if> 
                                              <c:if test="${row.log_step !=  'LOGOUT' and  row.log_step !=  'LOGIN' }"> 
                                         <span class="timeline-point timeline-point-info timeline-point-indicator"></span>
                                            </c:if> 
                                            
                                            <div class="timeline-event">
                                                <div class="d-flex justify-content-between flex-sm-row flex-column mb-sm-0 mb-1">
                                                    <h6>${row.log_step }</h6>
                                                    <span class="timeline-event-time me-1">${row.log_note }</span>
                                                </div>
                                                <p>${row.log_date } ${row.log_time }</p>
                                            </div>
                                        </li>
                                    </c:forEach>
                                     
                                    </ul>
                                </div>
                            </div>
                            <!-- /Activity Timeline -->

                            
                        </div>
                        <!--/ User Content -->
                    </div>
                    
                       </c:if>   
                    
                    
                </section>
        
                <!-- upgrade your plan Modal -->
                <div class="modal fade" id="upgradePlanModal" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-upgrade-plan">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-2">
                                <div class="text-center mb-2">
                                    <h1 class="mb-1">Upgrade Plan</h1>
                                    <p>Choose the best plan for user.</p>
                                </div>
                                <form id="upgradePlanForm" class="row pt-50" onsubmit="return false">
                                    <div class="col-sm-8">
                                        <label class="form-label" for="choosePlan">Choose Plan</label>
                                        <select id="choosePlan" name="choosePlan" class="form-select" aria-label="Choose Plan">
                                            <option selected>Choose Plan</option>
                                            <option value="standard">Standard - $99/month</option>
                                            <option value="exclusive">Exclusive - $249/month</option>
                                            <option value="Enterprise">Enterprise - $499/month</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-4 text-sm-end">
                                        <button type="submit" class="btn btn-primary mt-2">Upgrade</button>
                                    </div>
                                </form>
                            </div>
                            <hr />
                            <div class="modal-body px-5 pb-3">
                                <h6>User current plan is standard plan</h6>
                                <div class="d-flex justify-content-between align-items-center flex-wrap">
                                    <div class="d-flex justify-content-center me-1 mb-1">
                                        <sup class="h5 pricing-currency pt-1 text-primary">$</sup>
                                        <h1 class="fw-bolder display-4 mb-0 text-primary me-25">99</h1>
                                        <sub class="pricing-duration font-small-4 mt-auto mb-2">/month</sub>
                                    </div>
                                    <button class="btn btn-outline-danger cancel-subscription mb-1">Cancel Subscription</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ upgrade your plan Modal -->

            </div>
        
              
              

            </div>
        </div>
    </div>
    <!-- END: Content-->




<%@ include file="common/mainfoot.jspf" %>  
  <%@ include file="other/alert.jsp" %>

  <c:if test="${save == 'success'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Scccess');
      });
    
    </script>
</c:if>

    <script>
   $("#kpi2").html("${error}");
   $("#kpi1").html(" ${success} ");
   $("#kpi0").html(" ${success + error} ");
   
   
   $('#record').click(function(){
	   $("#divactivity").hide();
	   $("#divrecord").show();
	   
	   var element = document.getElementById("activity");
	   element.classList.remove("active");
	   var element = document.getElementById("record");
	   element.classList.add("active");
	   
	 });
   
   
   $('#activity').click(function(){
	   $("#divactivity").show();
	   $("#divrecord").hide();
	   
	   
	  
	   
	   var element = document.getElementById("record");
	   element.classList.remove("active");
	   var element = document.getElementById("activity");
	   element.classList.add("active");
	 });
   
   
   
   
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


<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/radar.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>

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

chart.getNumberFormatter().set("numberFormat", "#'%'");

// Create axis and its renderer
// https://www.amcharts.com/docs/v5/charts/radar-chart/gauge-charts/#Axes
var axisRenderer = am5radar.AxisRendererCircular.new(root, {
  innerRadius: -40
});

axisRenderer.grid.template.setAll({
  stroke: root.interfaceColors.get("background"),
  visible: true,
  strokeOpacity: 0.8
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

var clockHand = am5radar.ClockHand.new(root, {
  pinRadius: 50,
  radius: am5.percent(100),
  innerRadius: 50,
  bottomWidth: 0,
  topWidth: 0
});

clockHand.pin.setAll({
  fillOpacity: 0,
  strokeOpacity: 0.5,
  stroke: am5.color(0x000000),
  strokeWidth: 1,
  strokeDasharray: [2, 2]
});
clockHand.hand.setAll({
  fillOpacity: 0,
  strokeOpacity: 0.5,
  stroke: am5.color(0x000000),
  strokeWidth: 0.5
});

var bullet = axisDataItem.set(
  "bullet",
  am5xy.AxisBullet.new(root, {
    sprite: clockHand
  })
);

xAxis.createAxisRange(axisDataItem);

var label = chart.radarContainer.children.push(
  am5.Label.new(root, {
    centerX: am5.percent(50),
    textAlign: "center",
    centerY: am5.percent(50),
    fontSize: "1.5em"
  })
);


bullet.get("sprite").on("rotation", function () {
  var value = axisDataItem.get("value");
  if (isNaN(value)) {
	  value =0;
	  }

  label.set("text", Math.round(value).toString() + "%");
});



chart.bulletsContainer.set("mask", undefined);
var Range=${success * 100 /  (success+error)};
var colorSet = am5.ColorSet.new(root, {});

var axisRange0 = xAxis.createAxisRange(
  xAxis.makeDataItem({
    above: true,
    value: 0,
    endValue: Range
  })
);

axisRange0.get("axisFill").setAll({
  visible: true,
  fill: colorSet.getIndex(0)
});

axisRange0.get("label").setAll({
  forceHidden: true
});





axisDataItem.set("value", Range);



var axisRange1 = xAxis.createAxisRange(
  xAxis.makeDataItem({
    above: true,
    value: Range,
    endValue: 100
  })
);

axisRange1.get("axisFill").setAll({
  visible: true,
  fill: colorSet.getIndex(4)
});

axisRange1.get("label").setAll({
  forceHidden: true
});

// Make stuff animate on load
chart.appear(1000, 100);

}); // end am5.ready()
</script>

</body>

<button class="btn btn-block btn-outline-warning" onclick="notify('ban','Sample Message','This is a sample message.');"><i class="fas fa-2x fa-ban"></i></button>


   



