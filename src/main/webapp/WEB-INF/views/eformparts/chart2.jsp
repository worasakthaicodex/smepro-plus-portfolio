
	var root = am5.Root.new("chartdiv2");
	var chartClicked = false;
	var chart = root.container.children.push(
			  am5xy.XYChart.new(root, {
			    panX: false,
			    panY: false,
			    wheelX: "none",
			    wheelY: "none"
			  })
			);

	var series =null;
	var lineSeries =null;
	var chartData = [];
	var	xAxis =null;
	var yAxis=null; 
	var yAxis2=null;
	
	
	function runchartdiv2(){
	
	am5.ready(function() {
		
		if (chartClicked) {
			   chart.yAxes.removeIndex ( chart.yAxes.indexOf ( yAxis ) );
			   chart.yAxes.removeIndex ( chart.yAxes.indexOf ( yAxis2 ) );
		        chart.xAxes.removeIndex ( chart.xAxes.indexOf ( xAxis ) );
		        chart.series.removeIndex ( chart.series.indexOf ( series ) );
		        chart.series.removeIndex ( chart.series.indexOf ( lineSeries ) );
		      	xAxis =null;
			     chartData = [];
	    } 
		
		
		// Create root element
		// https://www.amcharts.com/docs/v5/getting-started/#Root_element
		

		// Set themes
		// https://www.amcharts.com/docs/v5/concepts/themes/
		root.setThemes([
		  am5themes_Animated.new(root)
		]);

		// Create chart
		// https://www.amcharts.com/docs/v5/charts/xy-chart/
		
		
		
		
		
		// Add cursor
		// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
		var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
		cursor.lineY.set("visible", false);

		// Create axes
		// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
		var xRenderer = am5xy.AxisRendererX.new(root, { minGridDistance: 30 });
		xRenderer.labels.template.setAll({ text: "{realName}" });

		xAxis = chart.xAxes.push(
		  am5xy.CategoryAxis.new(root, {
		    maxDeviation: 0,
		    categoryField: "category",
		    renderer: xRenderer,
		    tooltip: am5.Tooltip.new(root, {
		      labelText: "{realName}"
		    })
		  })
		);
	
		yAxis = chart.yAxes.push(
		  am5xy.ValueAxis.new(root, {
		    maxDeviation: 0.3,
		    renderer: am5xy.AxisRendererY.new(root, {})
		  })
		);

		 yAxis2 = chart.yAxes.push(
		  am5xy.ValueAxis.new(root, {
		    maxDeviation: 0.3,
		    syncWithAxis: yAxis,
		    renderer: am5xy.AxisRendererY.new(root, { opposite: true })
		  })
		);

		// Create series
		// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
		series = chart.series.push(
		  am5xy.ColumnSeries.new(root, {
		    name: "Series 1",
		    xAxis: xAxis,
		    yAxis: yAxis2,
		    valueYField: "value",
		    sequencedInterpolation: true,
		    categoryXField: "category",
		    tooltip: am5.Tooltip.new(root, {
		      labelText: "{provider} {realName}: {valueY}"
		    })
		  })
		);

		series.columns.template.setAll({
		  fillOpacity: 0.9,
		  strokeOpacity: 0
		});
		series.columns.template.adapters.add("fill", (fill, target) => {
		  return chart.get("colors").getIndex(series.columns.indexOf(target));
		});

		series.columns.template.adapters.add("stroke", (stroke, target) => {
		  return chart.get("colors").getIndex(series.columns.indexOf(target));
		});

		lineSeries = chart.series.push(
		  am5xy.LineSeries.new(root, {
		    name: "Series 2",
		    xAxis: xAxis,
		    yAxis: yAxis,
		    valueYField: "quantity",
		    sequencedInterpolation: true,
		    stroke: chart.get("colors").getIndex(13),
		    fill: chart.get("colors").getIndex(13),
		    categoryXField: "category",
		    tooltip: am5.Tooltip.new(root, {
		      labelText: "{valueY}"
		    })
		  })
		);

		lineSeries.strokes.template.set("strokeWidth", 2);

		lineSeries.bullets.push(function () {
		  return am5.Bullet.new(root, {
		    locationY: 1,
		    locationX: undefined,
		    sprite: am5.Circle.new(root, {
		      radius: 5,
		      fill: lineSeries.get("fill")
		    })
		  });
		});

		// when data validated, adjust location of data item based on count
		lineSeries.events.on("datavalidated", function () {
		  am5.array.each(lineSeries.dataItems, function (dataItem) {
		    // if count divides by two, location is 0 (on the grid)
		    if (
		      dataItem.dataContext.count / 2 ==
		      Math.round(dataItem.dataContext.count / 2)
		    ) {
		      dataItem.set("locationX", 0);
		    }
		    // otherwise location is 0.5 (middle)
		    else {
		      dataItem.set("locationX", 0.5);
		    }
		  });
		});



		var data = {};
		if (chartClicked) {
			$("#tabledataes tbody tr:visible").each(function () {
			
			
													var roomCounts = {};
						var tdElementsLabel = $("td[data-custom='${e_report.column_label }']", this);
						var tdElementsValue = $("td[data-custom='${e_report.column_data }']", this);
						var quantity = parseInt("${e_report.column_target }"); // Parse the quantity as an integer
						
						tdElementsLabel.each(function (index) {
						    var roomLabel = $(this).text().trim();  // Trim เพื่อลดปัญหาช่องว่างที่ไม่จำเป็น
						    var roomValue = tdElementsValue.eq(index).text().trim();
						
						    // ตรวจสอบว่า roomLabel นี้มีอยู่แล้วใน roomCounts หรือไม่
						    if (!roomCounts.hasOwnProperty(roomLabel)) {
						        // ถ้ายังไม่มี roomLabel, ให้สร้างใหม่
						        roomCounts[roomLabel] = {
						            values: {},
						            quantity: 0 // กำหนดค่าเริ่มต้นของ quantity
						        };
						    }
						
						    // ถ้ามี roomValue แล้ว, ให้บวกจำนวนเข้าไป
						    if (roomCounts[roomLabel].values.hasOwnProperty(roomValue)) {
						        roomCounts[roomLabel].values[roomValue]++;
						    } else {
						        // ถ้ายังไม่มี roomValue, ให้กำหนดค่าเริ่มต้นเป็น 1
						        roomCounts[roomLabel].values[roomValue] = 1;
						    }
						
						    // เพิ่ม quantity ให้กับ roomLabel นี้
						    if (quantity > 0) {
						        roomCounts[roomLabel].quantity += quantity; // บวก quantity เข้าไป
						    }
						});
						
						// Merge roomCounts into the data object
						for (var label in roomCounts) {
						    if (roomCounts.hasOwnProperty(label)) {
						        data[label] = {
						            values: roomCounts[label].values,
						            quantity: roomCounts[label].quantity // รวม quantity ด้วย
						        };
						    }
						}
			    
			    
			});

			
			
		}else{
		
			$("#tabledataes tbody tr").each(function () {
			    var roomCounts = {};

			    var tdElementsLabel = $("td[data-custom='${e_report.column_label }']", this);
			    var tdElementsValue = $("td[data-custom='${e_report.column_data }']", this);
			    var quantity = parseInt("${e_report.column_target }"); // Parse the quantity as an integer

			    tdElementsLabel.each(function (index) {
			        var roomLabel = $(this).text();
			        var roomValue = tdElementsValue.eq(index).text();

			        if (roomCounts.hasOwnProperty(roomLabel)) {
			            if (roomCounts[roomLabel].hasOwnProperty(roomValue)) {
			                roomCounts[roomLabel][roomValue]++;
			            } else {
			                roomCounts[roomLabel][roomValue] = 1;
			            }
			        } else {
			            roomCounts[roomLabel] = {};
			            roomCounts[roomLabel][roomValue] = 1;
			        }

			        // Add quantity to the roomCounts object as a direct value, not as a property
			        if(quantity > 0){
			        	  roomCounts[roomLabel].quantity = quantity;
			        }
			      
			        
			        
			        
			    });

			    // Merge roomCounts into the data object
			    for (var label in roomCounts) {
			        if (roomCounts.hasOwnProperty(label)) {
			            if (data.hasOwnProperty(label)) {
			                Object.keys(roomCounts[label]).forEach(function (key) {
			                    if (data[label].hasOwnProperty(key)) {
			                        data[label][key] += roomCounts[label][key];
			                    } else {
			                        data[label][key] = roomCounts[label][key];
			                    }
			                });
			            } else {
			                data[label] = roomCounts[label];
			            }
			        }
			    }
			});

		}
		
	//	console.log(JSON.stringify(data, null, 2));

			
	

		// process data ant prepare it for the chart
		for (var providerName in data) {
		  var providerData = data[providerName];

		  // add data of one provider to temp array
		  var tempArray = [];
		  var count = 0;
		  // add items
		  for (var itemName in providerData) {
		    if (itemName != "quantity") {
		      count++;
		      // we generate unique category for each column (providerName + "_" + itemName) and store realName
		      tempArray.push({
		        category: providerName + "_" + itemName,
		        realName: itemName,
		        value: providerData[itemName],
		        provider: providerName
		      });
		    }
		  }
		  // sort temp array
		  tempArray.sort(function (a, b) {
		    if (a.value > b.value) {
		      return 1;
		    } else if (a.value < b.value) {
		      return -1;
		    } else {
		      return 0;
		    }
		  });

		  // add quantity and count to middle data item (line series uses it)
		  var lineSeriesDataIndex = Math.floor(count / 2);
		  tempArray[lineSeriesDataIndex].quantity = providerData.quantity;
		  tempArray[lineSeriesDataIndex].count = count;
		  // push to the final data
		  am5.array.each(tempArray, function (item) {
		    chartData.push(item);
		  });

		  // create range (the additional label at the bottom)

		  var range = xAxis.makeDataItem({});
		  xAxis.createAxisRange(range);

		  range.set("category", tempArray[0].category);
		  range.set("endCategory", tempArray[tempArray.length - 1].category);

		  var label = range.get("label");

		  label.setAll({
		    text: tempArray[0].provider,
		    dy: 30,
		    fontWeight: "bold",
		    tooltipText: tempArray[0].provider
		  });

		  var tick = range.get("tick");
		  tick.setAll({ visible: true, strokeOpacity: 1, length: 50, location: 0 });

		  var grid = range.get("grid");
		  grid.setAll({ strokeOpacity: 1 });
		}

		// add range for the last grid
		var range = xAxis.makeDataItem({});
		xAxis.createAxisRange(range);
		range.set("category", chartData[chartData.length - 1].category);
		var tick = range.get("tick");
		tick.setAll({ visible: true, strokeOpacity: 1, length: 50, location: 1 });

		var grid = range.get("grid");
		grid.setAll({ strokeOpacity: 1, location: 1 });

		
			

		xAxis.data.setAll(chartData);
		series.data.setAll(chartData);
		lineSeries.data.setAll(chartData);
		if (chartClicked == false) {

			series.appear(1000);
			chart.appear(1000, 100);
		}
			chartClicked = true;
	
		}); // end am5.ready()
		
	}
	
		