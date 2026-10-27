
	var root = am5.Root.new("chartdiv2");
	var chartClicked = false;
	var chart = root.container.children.push(
		  am5xy.XYChart.new(root, {
		    panX: false,
		    panY: false,
		    wheelX: "panX",
		    wheelY: "zoomX",
		    layout: root.verticalLayout
		  })
		);

	var series1 =null;
	var series2 =null;
	var lineSeries =null;
	var data = [];
	var	xAxis =null;
	var yAxis=null; 
   var legend =null; 
function runchartdiv2(){
	am5.ready(function() {


	if (chartClicked) {
			   chart.yAxes.removeIndex ( chart.yAxes.indexOf ( yAxis ) );
		         legend.data.setAll([]);
		        chart.series.removeIndex ( chart.series.indexOf ( series2 ) );
		         chart.series.removeIndex ( chart.series.indexOf ( series1 ) );
		           chart.xAxes.removeIndex ( chart.xAxes.indexOf ( xAxis ) );
		        var roomCounts = null;
		      	xAxis =null;
		
			   data=[];
		
	    } 
		


		root.setThemes([am5themes_Animated.new(root)]);
		
		chart.set(
		  "scrollbarX",
		  am5.Scrollbar.new(root, {
		    orientation: "horizontal"
		  })
		);

	
	
	
		
		var roomdata;
		
		
		if("${e_report.column_data }" == "lable_num"){
		
		
	
		
					var roomCounts = {};
					
					$("#tabledataes tbody tr").each(function () {
					
					
			
								if(chartClicked ){
									if (chartClicked && $(this).is(":visible")) {
										// ดึงข้อมูลของห้องและค่าจากคอลัมน์ที่กำหนด
										var roomName = $("td[data-custom='${e_report.column_label }']", this).text();
										var value = $("td[data-custom='${e_report.column_data }']", this).text();
										
										// ตรวจสอบว่าห้องนี้มีใน roomCounts หรือไม่ ถ้ามีให้เพิ่มจำนวนขึ้น 1 ถ้าไม่มีให้เริ่มจำนวนที่ 1
										if (roomCounts.hasOwnProperty(roomName)) {
											roomCounts[roomName]++;
										} else {
											roomCounts[roomName] = 1;
										}
									}
								}else{
								
											var roomName = $("td[data-custom='${e_report.column_label }']", this).text().trim();
											var value = $("td[data-custom='${e_report.column_data }']", this).text().trim();

											

											// ตรวจสอบว่า roomName ไม่เป็นค่าว่างก่อนทำงาน
											if (roomName) {
										
												// ตรวจสอบว่าห้องนี้มีใน roomCounts หรือไม่ ถ้ามีให้เพิ่มจำนวนขึ้น 1 ถ้าไม่มีให้เริ่มจำนวนที่ 1
												if (roomCounts.hasOwnProperty(roomName)) {
													roomCounts[roomName]++;
												} else {
													roomCounts[roomName] = 1;
												}
											}
								}
									
					});
					
					// ทำสิ่งที่คุณต้องการกับข้อมูลที่นับได้ที่นี่
					roomdata = roomCounts;
					
					
					//console.log(JSON.stringify(roomdata, null, 2));
					
			
					
					
		/*	*/
		}

		
		if("${e_report.column_data }" != "lable_num"){
		
		
			if(chartClicked ){
			
			  var roomCounts = {}; // สร้าง object สำหรับเก็บจำนวนห้อง
							$("#tabledataes tbody tr:visible").each(function () {
					  
					    var date = $(this).find("td[data-custom='${e_report.column_label }']").text();
					
					    $(this).closest('tr').find("td[data-custom='${e_report.column_data }']").each(function () {
					        var roomName = $(this).siblings("td[data-custom='${e_report.column_label }']").text(); // ดึงชื่อห้อง
					        var value = $(this).text(); // ดึงค่า value จาก td ที่เกี่ยวข้อง
					
					        // ตรวจสอบว่าค่า value เป็นเลขหรือไม่
					        if (!Number.isNaN(Number(value))) {
					            // หากชื่อห้องนี้มีอยู่แล้ว นับจำนวนขึ้น
					            if (roomCounts.hasOwnProperty(roomName)) {
					                roomCounts[roomName] += Number(value);
					            } else {
					                // หากชื่อห้องนี้ยังไม่มีใน object, เพิ่มชื่อห้องและกำหนดค่าเป็น value
					                roomCounts[roomName] = Number(value);
					            }
					        }
					    });
					    roomdata = roomCounts;
					    // ทำสิ่งที่คุณต้องการกับข้อมูลที่นับได้ที่นี่
					});
			
			}else{
			
			$("#tabledataes tbody tr").each(function () {
				var roomCounts = {};
	
			
							 var date =  $("td[data-custom='${e_report.column_label }']").text();
							 var tdElements = $("td[data-custom='${e_report.column_label }']");
							 var roomCounts = {}; // สร้าง object สำหรับเก็บจำนวนห้อง

							 tdElements.each(function() {
							     var roomName = $(this).text(); // ดึงชื่อห้อง
							     var value = $(this).siblings("td[data-custom='${e_report.column_data }']").text(); // ดึงค่า value จาก td ที่เกี่ยวข้อง

							     // ตรวจสอบว่าค่า value เป็นเลขหรือไม่
							     if (!Number.isNaN(Number(value))) {
							         // หากชื่อห้องนี้มีอยู่แล้ว นับจำนวนขึ้น
							         if (roomCounts.hasOwnProperty(roomName)) {
							             roomCounts[roomName] += Number(value);
							         } else {
							             // หากชื่อห้องนี้ยังไม่มีใน object, เพิ่มชื่อห้องและกำหนดค่าเป็น value
							             roomCounts[roomName] = Number(value);
							         }
							     }
							 });
				          roomdata =roomCounts;
	          // var date = $(this).find("td:eq(4)").text();
			   // var value = parseInt($(this).find("td:eq(2)").text()); 	 
			});
			/*	*/
			}
			
			
			
			
		}
		
               //  console.log(roomdata); 

		          for (var room in roomdata) {
					      
						     if( room !== ""){
								   data.push({ year: room, income: roomdata[room], expenses: ${e_report.column_target } });
							 }
		            	   
		          }

		          
		
		          
		// Create axes
		// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
		var xRenderer = am5xy.AxisRendererX.new(root, {});
		xAxis = chart.xAxes.push(
		  am5xy.CategoryAxis.new(root, {
		    categoryField: "year",
		    renderer: xRenderer,
		    tooltip: am5.Tooltip.new(root, {})
		  })
		);
		xRenderer.grid.template.setAll({
		  location: 1
		})

		xAxis.data.setAll(data);

		yAxis = chart.yAxes.push(
		  am5xy.ValueAxis.new(root, {
		    min: 0,
		    extraMax: 0.1,
		    renderer: am5xy.AxisRendererY.new(root, {
		      strokeOpacity: 0.1
		    })
		  })
		);


		// Add series
		// https://www.amcharts.com/docs/v5/charts/xy-chart/series/

		series1 = chart.series.push(
		  am5xy.ColumnSeries.new(root, {
		    name: "${e_report.column_label }",
		    xAxis: xAxis,
		    yAxis: yAxis,
		    valueYField: "income",
		    categoryXField: "year",
		    tooltip: am5.Tooltip.new(root, {
		      pointerOrientation: "horizontal",
		      labelText: "{name} in {categoryX}: {valueY} {info}"
		    })
		  })
		);

		series1.columns.template.setAll({
		  tooltipY: am5.percent(10),
		  templateField: "columnSettings"
		});

		series1.data.setAll(data);

	series2 = chart.series.push(
		  am5xy.LineSeries.new(root, {
		    name: "Target",
		    xAxis: xAxis,
		    yAxis: yAxis,
		    valueYField:"${e_report.column_target}" == "0" ? "s" : "expenses",
		    categoryXField: "year",
		    tooltip: am5.Tooltip.new(root, {
		      pointerOrientation: "horizontal",
		      labelText: "{name} in {categoryX}: {valueY} {info}"
		    })
		  })
		);

		series2.strokes.template.setAll({
		  strokeWidth: 3,
		  templateField: "strokeSettings"
		});


		series2.data.setAll(data);

		series2.bullets.push(function() {
		  return am5.Bullet.new(root, {
		    sprite: am5.Circle.new(root, {
		      strokeWidth: 3,
		      stroke: series2.get("stroke"),
		      radius: 5,
		      fill: root.interfaceColors.get("background")
		    })
		  });
		});

		chart.set("cursor", am5xy.XYCursor.new(root, {}));

		// Add legend
		// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
		legend = chart.children.push(
		  am5.Legend.new(root, {
		    centerX: am5.p50,
		    x: am5.p50
		  })
		);
		legend.data.setAll(chart.series.values);

		// Make stuff animate on load
		// https://www.amcharts.com/docs/v5/concepts/animations/
		chart.appear(1000, 100);
		series1.appear();
	
chartClicked = true;
		
		}); // end am5.ready()
	}