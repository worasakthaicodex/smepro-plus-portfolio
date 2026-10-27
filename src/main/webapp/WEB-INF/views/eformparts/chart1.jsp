
	var roots = am5.Root.new("chartdiv");
	var charts = roots.container.children.push(am5xy.XYChart.new(roots, {
				  panX: true,
				  panY: true,
				  wheelX: "panX",
				  wheelY: "zoomX",
				  maxTooltipDistance: 0,
				  pinchZoomX:true
	}));




	var seriesed =null;
	var lineSeries =null;
	var datas = [];
	var	xAxiss =null;
	var yAxiss=null; 
	var legended=null;
	var chartClickeds = false;
	  var seriess=null;
	function runchartdiv1(){
	
am5.ready(function() {

                      if(chartClickeds){
            
        
	               charts.yAxes.clear();
	               charts.xAxes.clear();
	               charts.series.clear();   
	               legended.data.clear();
            	   charts.rightAxesContainer.children.clear();
				
         	  	 
        
                   datas = [];

                       
                   // return false;
                      }


				roots.setThemes([
				  am5themes_Animated.new(root)
				]);

	


				// Create axes
				// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
				xAxiss = charts.xAxes.push(am5xy.DateAxis.new(roots, {
				  maxDeviation: 0.2,
				  baseInterval: {
				    timeUnit: "day",
				    count: 1
				  },
				  renderer: am5xy.AxisRendererX.new(roots, {}),
				  tooltip: am5.Tooltip.new(roots, {})
				}));

				yAxiss = charts.yAxes.push(am5xy.ValueAxis.new(roots, {
				  renderer: am5xy.AxisRendererY.new(roots, {})
				}));


				// Add series
				// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
				
				
				
				
				
				
				
				
			
		if("${e_report.line_data }" != "lable_num" ){		
		var data = {};
		

		 if(chartClickeds){
		$("#tabledataes tbody tr:visible").each(function () {
							var roomCounts = {};
					
					var tdElementsLabel = $("td[data-custom='${e_report.line_label }']", this);
					var tdElementsValue = $("td[data-custom='data_due']", this);
					var tdElementsData = $("td[data-custom='${e_report.line_data }']", this); // ดึงค่า data จาก tdElementsData
					var quantity = parseInt("${e_report.line_target }"); // Parse the quantity as an integer
					
					tdElementsLabel.each(function (index) {
					    var roomLabel = $(this).text();
					    var roomValue = tdElementsValue.eq(index).text();
					    var roomData = parseInt(tdElementsData.eq(index).text()); // แปลงค่าเป็นจำนวนเต็มจาก tdElementsData
					
					    if (roomCounts.hasOwnProperty(roomLabel)) {
					        if (roomCounts[roomLabel].hasOwnProperty(roomValue)) {
					            roomCounts[roomLabel][roomValue] += roomData; // เพิ่มค่าจาก tdElementsData
					        } else {
					            roomCounts[roomLabel][roomValue] = roomData; // กำหนดค่าเริ่มต้นจาก tdElementsData
					        }
					    } else {
					        roomCounts[roomLabel] = {};
					        roomCounts[roomLabel][roomValue] = roomData; // กำหนดค่าเริ่มต้นจาก tdElementsData
					    }
					
					    // Add quantity to the roomCounts object as a direct value, not as a property
					    if (quantity > 0) {
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
		
		
		}else{
			$("#tabledataes tbody tr").each(function () {
		  var roomCounts = {};

var tdElementsLabel = $("td[data-custom='${e_report.line_label }']", this);
var tdElementsValue = $("td[data-custom='data_due']", this);
var tdElementsData = $("td[data-custom='${e_report.line_data }']", this); // ดึงค่า data จาก tdElementsData
var quantity = parseInt("${e_report.line_target }"); // Parse the quantity as an integer

tdElementsLabel.each(function (index) {
    var roomLabel = $(this).text();
    var roomValue = tdElementsValue.eq(index).text();
    var roomData = parseInt(tdElementsData.eq(index).text()); // แปลงค่าเป็นจำนวนเต็มจาก tdElementsData

    if (roomCounts.hasOwnProperty(roomLabel)) {
        if (roomCounts[roomLabel].hasOwnProperty(roomValue)) {
            roomCounts[roomLabel][roomValue] += roomData; // เพิ่มค่าจาก tdElementsData
        } else {
            roomCounts[roomLabel][roomValue] = roomData; // กำหนดค่าเริ่มต้นจาก tdElementsData
        }
    } else {
        roomCounts[roomLabel] = {};
        roomCounts[roomLabel][roomValue] = roomData; // กำหนดค่าเริ่มต้นจาก tdElementsData
    }

    // Add quantity to the roomCounts object as a direct value, not as a property
    if (quantity > 0) {
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
		
		
		
		}else{
			var data = {};
			
			 if(chartClickeds){
		$("#tabledataes tbody tr:visible").each(function () {
		
		
		
			    var roomCounts = {};
			var tdElementsLabel = $("td[data-custom='${e_report.line_label }']", this);
			var tdElementsValue = $("td[data-custom='data_due']", this);
			var tdElementsdata = $("td[data-custom='${e_report.line_data }']", this);
			var quantity = parseInt("${e_report.line_target }"); // Parse the quantity as an integer


             

			tdElementsLabel.each(function (index) {
			    var roomLabel = $(this).text();
			    var roomValue = tdElementsValue.eq(index).text();
			    var roomData = tdElementsdata.eq(index).text(); // ข้อมูลจาก tdElementsdata

			    if (roomCounts.hasOwnProperty(roomLabel)) {
			        if (roomCounts[roomLabel].hasOwnProperty(roomValue)) {
			            roomCounts[roomLabel][roomValue] += parseInt(roomData); // รวมค่าจาก tdElementsdata
			        } else {
			            roomCounts[roomLabel][roomValue] = parseInt(roomData);
			        }
			    } else {
			        roomCounts[roomLabel] = {};
			        roomCounts[roomLabel][roomValue] = parseInt(roomData);
			    }

			    // Add quantity to the roomCounts object as a direct value, not as a property
			    if (quantity > 0) {
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
			 
			 }else{
			 
			 			$("#tabledataes tbody tr").each(function () {
			    var roomCounts = {};
			var tdElementsLabel = $("td[data-custom='${e_report.line_label }']", this);
			var tdElementsValue = $("td[data-custom='data_due']", this);
			var tdElementsdata = $("td[data-custom='${e_report.line_data }']", this);
			var quantity = parseInt("${e_report.line_target }"); // Parse the quantity as an integer

			tdElementsLabel.each(function (index) {
			    var roomLabel = $(this).text();
			    var roomValue = tdElementsValue.eq(index).text();
			    var roomData = tdElementsdata.eq(index).text(); // ข้อมูลจาก tdElementsdata

			    if (roomCounts.hasOwnProperty(roomLabel)) {
			        if (roomCounts[roomLabel].hasOwnProperty(roomValue)) {
			            roomCounts[roomLabel][roomValue] += parseInt(roomData); // รวมค่าจาก tdElementsdata
			        } else {
			            roomCounts[roomLabel][roomValue] = parseInt(roomData);
			        }
			    } else {
			        roomCounts[roomLabel] = {};
			        roomCounts[roomLabel][roomValue] = parseInt(roomData);
			    }

			    // Add quantity to the roomCounts object as a direct value, not as a property
			    if (quantity > 0) {
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
			


			
			
		}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
			var newData = {};
		
		// วน loop ในข้อมูลเดิม
		for (var status in data) {
		    var statusData = data[status];
		    var sortedDates = Object.keys(statusData).sort(function(a, b) {
		        return new Date(a) - new Date(b);
		    });
		
		    // สร้าง object ใหม่ที่มีข้อมูลที่เรียงลำดับตามวันที่
		    var sortedStatusData = {};
		    sortedDates.forEach(function(date) {
		        sortedStatusData[date] = statusData[date];
		    });
		
		    // ใส่ข้อมูลที่เรียงลำดับลงใน newData object
		    newData[status] = sortedStatusData;
		}
		
		
		 console.log(newData);
		

		datas = [];
		
		for (var room in newData) {
	
			//alert(room);
			
		    var roomData = newData[room]; // ดึงข้อมูลของห้องนั้นๆ
		    for (var date in roomData) {
		        var value = roomData[date]; // ดึงค่าจากวันที่นั้นๆ

		        
		        // แปลงรูปแบบของวันที่เป็น timestamp
		        var timestamp = new Date(date).getTime();

		        // เพิ่มข้อมูลในรูปแบบที่ต้องการใน data array
		        datas.push({ date: timestamp, value: value });
		    }

		   seriesed = charts.series.push(am5xy.LineSeries.new(roots, {
		        name: room,
		        xAxis: xAxiss,
		        yAxis: yAxiss,
		        valueYField: "value",
		        valueXField: "date",
		        legendValueText: "{valueY}",
		        tooltip: am5.Tooltip.new(roots, {
		            pointerOrientation: "horizontal",
		            labelText: "{valueY}"
		        })
		    }));

		    
		
		    // ใส่ข้อมูลใน series
		    seriesed.data.setAll(datas);

		    // Make stuff animate on load
		    // https://www.amcharts.com/docs/v5/concepts/animations/
			  if(chartClickeds == false){
		    seriesed.appear();
		    }
		    
		    
		}
		
		
		


				// Add cursor
				// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
				var cursor = charts.set("cursor", am5xy.XYCursor.new(roots, {
				  behavior: "none"
				}));
				cursor.lineY.set("visible", false);


				// Add scrollbar
				// https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/
				charts.set("scrollbarX", am5.Scrollbar.new(roots, {
				  orientation: "horizontal"
				}));

				charts.set("scrollbarY", am5.Scrollbar.new(roots, {
				  orientation: "vertical"
				}));


				// Add legend
				// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
				legended = charts.rightAxesContainer.children.push(am5.Legend.new(roots, {
				  width: 200,
				  paddingLeft: 15,
				  height: am5.percent(100)
				}));

				// When legend item container is hovered, dim all the series except the hovered one
				legended.itemContainers.template.events.on("pointerover", function(e) {
				  var itemContainer = e.target;

				  // As series list is data of a legend, dataContext is series
				  var series = itemContainer.dataItem.dataContext;

				  charts.series.each(function(chartSeries) {
				    if (chartSeries != series) {
				      chartSeries.strokes.template.setAll({
				        strokeOpacity: 0.15,
				        stroke: am5.color(0x000000)
				      });
				    } else {
				      chartSeries.strokes.template.setAll({
				        strokeWidth: 3
				      });
				    }
				  })
				})

				// When legend item container is unhovered, make all series as they are
				legended.itemContainers.template.events.on("pointerout", function(e) {
				  var itemContainer = e.target;
				  var series = itemContainer.dataItem.dataContext;

				  charts.series.each(function(chartSeries) {
				    chartSeries.strokes.template.setAll({
				      strokeOpacity: 1,
				      strokeWidth: 1,
				      stroke: chartSeries.get("fill")
				    });
				  });
				})

				legended.itemContainers.template.set("width", am5.p100);
				legended.valueLabels.template.setAll({
				  width: am5.p100,
				  textAlign: "right"
				});

				// It's is important to set legend data after all the events are set on template, otherwise events won't be copied
				legended.data.setAll(charts.series.values);


				// Make stuff animate on load
				// https://www.amcharts.com/docs/v5/concepts/animations/
				if(chartClickeds == false){
					charts.appear(1000, 100);
				}
			
				
				chartClickeds = true;
				}); // end a
		
	}
	
		