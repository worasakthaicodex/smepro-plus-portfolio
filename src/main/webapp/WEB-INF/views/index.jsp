<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="common/mainhead.jspf" %> 
   <link href="<c:url value="/resources/app-assets/css/plugins/charts/chart-apex.min.css" />" rel="stylesheet">   

<title>index </title>

<style>
#chartdiv {
  width: 100%;
  height: 350px;
}

.chartdiv {
  width: 100%;
  height: 350px;
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
          <h4 class="card-title"><spring:message code="menu.Overview" text="กราฟภาพรวม" /></h4>
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
                  <p class="card-text font-small-3 mb-0"><spring:message code="menu.user" text="ผู้ใช้งาน" /></p>
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
                  <p class="card-text font-small-3 mb-0"><spring:message code="menu.devices" text="จำนวนเครื่องที่ใช้งาน" /></p>
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
                  <p class="card-text font-small-3 mb-0"><spring:message code="menu.Specific" text="ระยะเวลา Login โดยเฉลี่ย" /></p>
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
          <h4 class="card-title"><spring:message code="menu.space" text="พื้นที่ใช้งาน" /></h4>
          <i data-feather="help-circle" class="font-medium-3 text-muted cursor-pointer"></i>
        </div>
        <div class="card-body p-0">
          <div id="goal-overview-radial-bar-chart" class="my-2"></div>
          <div class="row border-top text-center mx-0">
            <div class="col-4 border-end py-1">
              <p class="card-text text-muted mb-0"><spring:message code="menu.space" text="พื้นที่ใช้งาน" /></p>
              <h3 class="fw-bolder mb-0">${drives_total  }</h3>
            </div>
               <div class="col-4 border-end py-1">
                 <p class="card-text text-muted mb-0"><spring:message code="menu.used" text="ผู้ใช้ใช้ไปเเล้ว" /></p>
              <h3 class="fw-bolder mb-0">${drives_use  }</h3>
            </div>
            <div class="col-4 py-1">
                 <p class="card-text text-muted mb-0"><spring:message code="menu.sysused" text="ระบบใช้ไปเเล้ว" /></p>
              <h3 class="fw-bolder mb-0">${drives_sysuse }</h3>
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
                                    <h4 class="card-title"><spring:message code="menu.used" text="ใช้ไปเเล้ว" /></h4>
                                </div>
                                <div class="card-body">
                                    <canvas class="doughnut-chart-exs chartjs" data-height="275"></canvas>
                              
                              
                              	  <c:forEach items="${foldersuse }" var="row" varStatus="count" end="2">
    	            	 
    	            	  
    	            	      <div class="d-flex justify-content-between  mb-1">
                                        <div class="d-flex align-items-center">
                                            <i data-feather="monitor" class="font-medium-2 text-primary"></i>
                                            <span class="fw-bold ms-75 me-25">${row.folder_name}</span>
                                       
                                        </div>
                                        <div>
                                            <span>${row.folder_use} K</span>
                                          
                                        </div>
                                    </div>
    	            	  </c:forEach>
    	            	
                           
                                    
                                    
                                    
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
                                        <h4 class="card-title mb-25"><spring:message code="menu.quantity" text="ประเภทเอกสารและจำนวนในระบบ" /></h4>
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
                        
                        
                   <!-- Donut Chart Starts -->
                        <div class="col-lg-4 col-12" style="display:none">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><spring:message code="menu.chart1" text="ความคืบหน้าในการอัพโหลดบันทึก" /></h4>
                                </div>
                                <div class="card-body">
                                    <div id="chartdiv"></div>
    	            	
                           
                                    
                                    
                                    
                                </div>
                     
    	    
                        </div>
                        <!-- Donut Chart Starts -->
                  </div>
                 </div> 
               
               
                 <div class="row match-height">
                      <div class="col-lg-8 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><spring:message code="menu.chart2" text="ผู้ใช้ที่เกี่ยวข้องกับ DAR ไม่สำเร็จมากที่สุด 5 ลำดับ" /></h4>
                                </div>
                                <div class="card-body">
                                    <div id="newchartdiv1"  class="chartdiv"></div>
    	            	
                           
                                    
                                    
                                    
                                </div>
                     
    	    
                        </div>
                        <!-- Donut Chart Starts -->
                  </div>
                      <div class="col-lg-4 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><spring:message code="menu.chart3" text="DAR ดำเนินการสำเร็จแยกตามเรื่องขอ" /></h4>
                                </div>
                                <div class="card-body">
                                    <div id="newchartdiv2" class="chartdiv"></div>
    	            	
                           
                                    
                                    
                                    
                                </div>
                     
    	    
                        </div>
                        <!-- Donut Chart Starts -->
                  </div>
                  
                  
                  
                  
         
                   
                      <div class="col-lg-4 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><spring:message code="menu.chart4" text="ผู้ที่รับผิดชอบตรวจสอบบันทึกมากที่สุด" /></h4>
                                </div>
                                <div class="card-body">
                                    <div id="newchartdiv4" class="chartdiv"></div>
    	            	
                           
                                    
                                    
                                    
                                </div>
                     
    	    
                        </div>
                        <!-- Donut Chart Starts -->
                  </div>
                  
                     <div class="col-lg-8 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><spring:message code="menu.chart5" text="ผู้ที่รับผิดชอบอัพโหลดบันทึกมากที่สุด" /></h4>
                                </div>
                                <div class="card-body">
                                    <div id="newchartdiv3"  class="chartdiv"></div>
    	            	
                           
                                    
                                    
                                    
                                </div>
                     
    	    
                        </div>
                        <!-- Donut Chart Starts -->
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
var root = am5.Root.new("newchartdiv1");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);

var data = [
	
<c:forEach items="${darunfinished }" var="row" varStatus="count">	
		{
	  name: "${row.user_name}",
	  steps: ${row.dar_step},
	  pictureSettings: {
		  
		  < c:set var="img" value="/resources/app-assets/images/portrait/small/avatar.jpg" />
		  <c:if test="${row.user_img != null}"> 
		  < c:set var="img" value="/avatar/${row.user_img}" />
		  </c:if>
		  
	    src: "${img}"
	  }
	},
</c:forEach>


];

// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root.container.children.push(
  am5xy.XYChart.new(root, {
    panX: false,
    panY: false,
    wheelX: "none",
    wheelY: "none",
    paddingBottom: 50,
    paddingTop: 40
  })
);

// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/

var xRenderer = am5xy.AxisRendererX.new(root, {});
xRenderer.grid.template.set("visible", false);

var xAxis = chart.xAxes.push(
  am5xy.CategoryAxis.new(root, {
    paddingTop:40,
    categoryField: "name",
    renderer: xRenderer
  })
);


var yRenderer = am5xy.AxisRendererY.new(root, {});
yRenderer.grid.template.set("strokeDasharray", [3]);

var yAxis = chart.yAxes.push(
  am5xy.ValueAxis.new(root, {
    min: 0,
    renderer: yRenderer
  })
);

// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
var series = chart.series.push(
  am5xy.ColumnSeries.new(root, {
    name: "Income",
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: "steps",
    categoryXField: "name",
    sequencedInterpolation: true,
    calculateAggregates: true,
    maskBullets: false,
    tooltip: am5.Tooltip.new(root, {
      dy: -30,
      pointerOrientation: "vertical",
      labelText: "{valueY}"
    })
  })
);

series.columns.template.setAll({
  strokeOpacity: 0,
  cornerRadiusBR: 10,
  cornerRadiusTR: 10,
  cornerRadiusBL: 10,
  cornerRadiusTL: 10,
  maxWidth: 50,
  fillOpacity: 0.8
});

var currentlyHovered;

series.columns.template.events.on("pointerover", function (e) {
  handleHover(e.target.dataItem);
});

series.columns.template.events.on("pointerout", function (e) {
  handleOut();
});

function handleHover(dataItem) {
  if (dataItem && currentlyHovered != dataItem) {
    handleOut();
    currentlyHovered = dataItem;
    var bullet = dataItem.bullets[0];
    bullet.animate({
      key: "locationY",
      to: 1,
      duration: 600,
      easing: am5.ease.out(am5.ease.cubic)
    });
  }
}

function handleOut() {
  if (currentlyHovered) {
    var bullet = currentlyHovered.bullets[0];
    bullet.animate({
      key: "locationY",
      to: 0,
      duration: 600,
      easing: am5.ease.out(am5.ease.cubic)
    });
  }
}

var circleTemplate = am5.Template.new({});

series.bullets.push(function (root, series, dataItem) {
  var bulletContainer = am5.Container.new(root, {});
  var circle = bulletContainer.children.push(
    am5.Circle.new(
      root,
      {
        radius: 34
      },
      circleTemplate
    )
  );

  var maskCircle = bulletContainer.children.push(
    am5.Circle.new(root, { radius: 27 })
  );

  // only containers can be masked, so we add image to another container
  var imageContainer = bulletContainer.children.push(
    am5.Container.new(root, {
      mask: maskCircle
    })
  );

  var image = imageContainer.children.push(
    am5.Picture.new(root, {
      templateField: "pictureSettings",
      centerX: am5.p50,
      centerY: am5.p50,
      width: 60,
      height: 60
    })
  );

  return am5.Bullet.new(root, {
    locationY: 0,
    sprite: bulletContainer
  });
});

// heatrule
series.set("heatRules", [
  {
    dataField: "valueY",
    min: am5.color(0xe5dc36),
    max: am5.color(0x5faa46),
    target: series.columns.template,
    key: "fill"
  },
  {
    dataField: "valueY",
    min: am5.color(0xe5dc36),
    max: am5.color(0x5faa46),
    target: circleTemplate,
    key: "fill"
  }
]);

series.data.setAll(data);
xAxis.data.setAll(data);

var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
cursor.lineX.set("visible", false);
cursor.lineY.set("visible", false);

cursor.events.on("cursormoved", function () {
  var dataItem = series.get("tooltip").dataItem;
  if (dataItem) {
    handleHover(dataItem);
  } else {
    handleOut();
  }
});

// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
series.appear();
chart.appear(1000, 100);

}); // end am5.ready()
</script>



<script>
am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("newchartdiv2");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
var chart = root.container.children.push(
  am5percent.PieChart.new(root, {
    endAngle: 270
  })
);

// Create series
// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
var series = chart.series.push(
  am5percent.PieSeries.new(root, {
    valueField: "value",
    categoryField: "category",
    endAngle: 270
  })
);

series.states.create("hidden", {
  endAngle: -90
});

// Set data
// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Setting_data
series.data.setAll([{
  category: "ขอออกเอกสารใหม่",
  value: ${darrequest1}
}, {
  category: "ขอเเก้ไขเอกสาร",
  value: ${darrequest2}
}, {
  category: "ขอยกเลิกเอกสาร",
  value: ${darrequest3}
}, {
  category: "ขอย้ายเอกสาร",
  value: ${darrequest4}
}
]);

series.appear(1000, 100);

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
var chart = root.container.children.push(am5radar.RadarChart.new(root, {
  panX: false,
  panY: false,
  wheelX: "panX",
  wheelY: "zoomX",
  innerRadius: am5.percent(20),
  startAngle: -90,
  endAngle: 180
}));


// Data
var data = [{
  category: "Annualy",
  value: ${RadarY.report_pn_sendstatus},
  full: ${RadarY.report_pn_statuserror },
  columnSettings: {
    fill: chart.get("colors").getIndex(0)
  }
}

<c:if test="${RadarM.report_pn_sendstatus  > 0}"> 
, {
  category: "Monthly",
  value: ${RadarM.report_pn_sendstatus},
  full: ${RadarM.report_pn_statuserror},
  columnSettings: {
    fill: chart.get("colors").getIndex(1)
  }
}
</c:if>

<c:if test="${RadarW.report_pn_sendstatus > 0}"> 
, 
{
  category: "Weekly",
  value: ${RadarW.report_pn_sendstatus},
  full: ${RadarW.report_pn_statuserror},
  columnSettings: {
    fill: chart.get("colors").getIndex(2)
  }
}
</c:if>

, 
{
  category: "Daily",
  value: ${RadarD.report_pn_sendstatus},
  full: ${RadarD.report_pn_statuserror},
  columnSettings: {
    fill: chart.get("colors").getIndex(3)
  }
}


];

// Add cursor
// https://www.amcharts.com/docs/v5/charts/radar-chart/#Cursor
var cursor = chart.set("cursor", am5radar.RadarCursor.new(root, {
  behavior: "zoomX"
}));

cursor.lineY.set("visible", false);

// Create axes and their renderers
// https://www.amcharts.com/docs/v5/charts/radar-chart/#Adding_axes
var xRenderer = am5radar.AxisRendererCircular.new(root, {
  //minGridDistance: 50
});

xRenderer.labels.template.setAll({
  radius: 10
});

xRenderer.grid.template.setAll({
  forceHidden: true
});

var xAxis = chart.xAxes.push(am5xy.ValueAxis.new(root, {
  renderer: xRenderer,
  min: 0,
  max: 100,
  strictMinMax: true,
  numberFormat: "#'%'",
  tooltip: am5.Tooltip.new(root, {})
}));


var yRenderer = am5radar.AxisRendererRadial.new(root, {
  minGridDistance: 20
});

yRenderer.labels.template.setAll({
  centerX: am5.p100,
  fontWeight: "500",
  fontSize: 18,
  templateField: "columnSettings"
});

yRenderer.grid.template.setAll({
  forceHidden: true
});

var yAxis = chart.yAxes.push(am5xy.CategoryAxis.new(root, {
  categoryField: "category",
  renderer: yRenderer
}));

yAxis.data.setAll(data);


// Create series
// https://www.amcharts.com/docs/v5/charts/radar-chart/#Adding_series
var series1 = chart.series.push(am5radar.RadarColumnSeries.new(root, {
  xAxis: xAxis,
  yAxis: yAxis,
  clustered: false,
  valueXField: "full",
  categoryYField: "category",
  fill: root.interfaceColors.get("alternativeBackground")
}));

series1.columns.template.setAll({
  width: am5.p100,
  fillOpacity: 0.08,
  strokeOpacity: 0,
  cornerRadius: 20
});

series1.data.setAll(data);


var series2 = chart.series.push(am5radar.RadarColumnSeries.new(root, {
  xAxis: xAxis,
  yAxis: yAxis,
  clustered: false,
  valueXField: "value",
  categoryYField: "category"
}));

series2.columns.template.setAll({
  width: am5.p100,
  strokeOpacity: 0,
  tooltipText: "{category}: {valueX}%",
  cornerRadius: 20,
  templateField: "columnSettings"
});

series2.data.setAll(data);

// Animate chart and series in
// https://www.amcharts.com/docs/v5/concepts/animations/#Initial_animation
series1.appear(1000);
series2.appear(1000);
chart.appear(1000, 100);

}); // end am5.ready()
</script>
    
    
    
    
    
    <script>
am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("newchartdiv3");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);

var data = [
	<c:forEach items="${userupload }" var="row" varStatus="count">	
	
  {
    name: "${row.upload_pn_respon}",
    steps: ${row.upload_pn_note},
    pictureSettings: {
		  
		  < c:set var="img" value="/resources/app-assets/images/portrait/small/avatar.jpg" />
		  <c:if test="${row.upload_pn_app != null}"> 
		  < c:set var="img" value="/avatar/${row.upload_pn_app}" />
		  </c:if>
		  
	    src: "${img}"
    }
  },
  </c:forEach>
  
  
];

// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root.container.children.push(
  am5xy.XYChart.new(root, {
    panX: false,
    panY: false,
    wheelX: "none",
    wheelY: "none",
    paddingLeft: 50,
    paddingRight: 40
  })
);

// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/

var yRenderer = am5xy.AxisRendererY.new(root, {});
yRenderer.grid.template.set("visible", false);

var yAxis = chart.yAxes.push(
  am5xy.CategoryAxis.new(root, {
    categoryField: "name",
    renderer: yRenderer,
    paddingRight:40
  })
);

var xRenderer = am5xy.AxisRendererX.new(root, {});
xRenderer.grid.template.set("strokeDasharray", [3]);

var xAxis = chart.xAxes.push(
  am5xy.ValueAxis.new(root, {
    min: 0,
    renderer: xRenderer
  })
);

// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
var series = chart.series.push(
  am5xy.ColumnSeries.new(root, {
    name: "Income",
    xAxis: xAxis,
    yAxis: yAxis,
    valueXField: "steps",
    categoryYField: "name",
    sequencedInterpolation: true,
    calculateAggregates: true,
    maskBullets: false,
    tooltip: am5.Tooltip.new(root, {
      dy: -30,
      pointerOrientation: "vertical",
      labelText: "{valueX}"
    })
  })
);

series.columns.template.setAll({
  strokeOpacity: 0,
  cornerRadiusBR: 10,
  cornerRadiusTR: 10,
  cornerRadiusBL: 10,
  cornerRadiusTL: 10,
  maxHeight: 50,
  fillOpacity: 0.8
});

var currentlyHovered;

series.columns.template.events.on("pointerover", function(e) {
  handleHover(e.target.dataItem);
});

series.columns.template.events.on("pointerout", function(e) {
  handleOut();
});

function handleHover(dataItem) {
  if (dataItem && currentlyHovered != dataItem) {
    handleOut();
    currentlyHovered = dataItem;
    var bullet = dataItem.bullets[0];
    bullet.animate({
      key: "locationX",
      to: 1,
      duration: 600,
      easing: am5.ease.out(am5.ease.cubic)
    });
  }
}

function handleOut() {
  if (currentlyHovered) {
    var bullet = currentlyHovered.bullets[0];
    bullet.animate({
      key: "locationX",
      to: 0,
      duration: 600,
      easing: am5.ease.out(am5.ease.cubic)
    });
  }
}


var circleTemplate = am5.Template.new({});

series.bullets.push(function(root, series, dataItem) {
  var bulletContainer = am5.Container.new(root, {});
  var circle = bulletContainer.children.push(
    am5.Circle.new(
      root,
      {
        radius: 34
      },
      circleTemplate
    )
  );

  var maskCircle = bulletContainer.children.push(
    am5.Circle.new(root, { radius: 27 })
  );

  // only containers can be masked, so we add image to another container
  var imageContainer = bulletContainer.children.push(
    am5.Container.new(root, {
      mask: maskCircle
    })
  );

  // not working
  var image = imageContainer.children.push(
    am5.Picture.new(root, {
      templateField: "pictureSettings",
      centerX: am5.p50,
      centerY: am5.p50,
      width: 60,
      height: 60
    })
  );

  return am5.Bullet.new(root, {
    locationX: 0,
    sprite: bulletContainer
  });
});

// heatrule
series.set("heatRules", [
  {
    dataField: "valueX",
    min: am5.color(0xe5dc36),
    max: am5.color(0x5faa46),
    target: series.columns.template,
    key: "fill"
  },
  {
    dataField: "valueX",
    min: am5.color(0xe5dc36),
    max: am5.color(0x5faa46),
    target: circleTemplate,
    key: "fill"
  }
]);

series.data.setAll(data);
yAxis.data.setAll(data);

var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
cursor.lineX.set("visible", false);
cursor.lineY.set("visible", false);

cursor.events.on("cursormoved", function() {
  var dataItem = series.get("tooltip").dataItem;
  if (dataItem) {
    handleHover(dataItem)
  }
  else {
    handleOut();
  }
})

// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
series.appear();
chart.appear(1000, 100);

}); // end am5.ready()
</script>
    
    
    
    
    
    <script>
am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("newchartdiv4");

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
  wheelX: "none",
  wheelY: "none"
}));

// Add cursor
// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
cursor.lineY.set("visible", false);

// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xRenderer = am5xy.AxisRendererX.new(root, { minGridDistance: 30 });

var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
  maxDeviation: 0,
  categoryField: "name",
  renderer: xRenderer,
  tooltip: am5.Tooltip.new(root, {})
}));

xRenderer.grid.template.set("visible", false);

var yRenderer = am5xy.AxisRendererY.new(root, {});
var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
  maxDeviation: 0,
  min: 0,
  extraMax: 0.1,
  renderer: yRenderer
}));

yRenderer.grid.template.setAll({
  strokeDasharray: [2, 2]
});

// Create series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
var series = chart.series.push(am5xy.ColumnSeries.new(root, {
  name: "Series 1",
  xAxis: xAxis,
  yAxis: yAxis,
  valueYField: "value",
  sequencedInterpolation: true,
  categoryXField: "name",
  tooltip: am5.Tooltip.new(root, { dy: -25, labelText: "{valueY}" })
}));


series.columns.template.setAll({
  cornerRadiusTL: 5,
  cornerRadiusTR: 5,
  strokeOpacity: 0
});

series.columns.template.adapters.add("fill", (fill, target) => {
  return chart.get("colors").getIndex(series.columns.indexOf(target));
});

series.columns.template.adapters.add("stroke", (stroke, target) => {
  return chart.get("colors").getIndex(series.columns.indexOf(target));
});

// Set data
var data = [
	
	<c:forEach items="${userapp }" var="row" varStatus="count">	
	
  {
	  
    name: "${row.upload_pn_app}",
    value: ${row.upload_pn_note},
    
    < c:set var="img" value="/resources/app-assets/images/portrait/small/avatar.jpg" />
		  <c:if test="${row.upload_pn_respon != null}"> 
		  < c:set var="img" value="/avatar/${row.upload_pn_respon}" />
		  </c:if>
		  
    bulletSettings: { src: "${img}"
  
    }
  },
  
  </c:forEach>
  
];

series.bullets.push(function() {
  return am5.Bullet.new(root, {
    locationY: 1,
    sprite: am5.Picture.new(root, {
      templateField: "bulletSettings",
      width: 50,
      height: 50,
      centerX: am5.p50,
      centerY: am5.p50,
      shadowColor: am5.color(0x000000),
      shadowBlur: 4,
      shadowOffsetX: 4,
      shadowOffsetY: 4,
      shadowOpacity: 0.6
    })
  });
});

xAxis.data.setAll(data);
series.data.setAll(data);

// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
series.appear(1000);
chart.appear(1000, 100);

}); // end am5.ready()
</script>
    
    
    
    
    
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
    	          maintainAspectRatio: true,
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

        	    var $strokeColor = '#ebe9f1';
        	    var $textHeadingColor = '#5e5873';
        	    var $goalStrokeColor2 = '#51e5a8'; // สีเขียวปกติ

        	    var usageValue = ${drives_percent + usagePercent};

        	    // กำหนดสีตามเงื่อนไข
        	    if (usageValue > 80) {
        	        $goalStrokeColor2 = '#ff4d4f'; // สีแดง
        	    } else if (usageValue > 70) {
        	        $goalStrokeColor2 = '#ffc107'; // สีเหลือง
        	    }

        	    var $goalOverviewChart = document.querySelector('#goal-overview-radial-bar-chart');
        	    var goalOverviewChartOptions = {
        	        chart: {
        	            height: 245,
        	            type: 'radialBar',
        	            sparkline: { enabled: true },
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
        	                hollow: { size: '77%' },
        	                track: {
        	                    background: $strokeColor,
        	                    strokeWidth: '50%'
        	                },
        	                dataLabels: {
        	                    name: { show: false },
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
        	                gradientToColors: [$goalStrokeColor2],
        	                inverseColors: true,
        	                opacityFrom: 1,
        	                opacityTo: 1,
        	                stops: [0, 100]
        	            }
        	        },
        	        series: [usageValue],
        	        stroke: { lineCap: 'round' },
        	        grid: { padding: { bottom: 30 } }
        	    };

        	    var goalOverviewChart = new ApexCharts($goalOverviewChart, goalOverviewChartOptions);
        	    goalOverviewChart.render();
        	})
        
  var element = document.getElementById("nav_main");
  element.classList.add("active");

    </script>
</body>


