
	var root3 = am5.Root.new("chartdiv3");
	var chart3 = root3.container.children.push(
			  am5percent.PieChart.new(root3, {
			    endAngle: 270
			  })
			);
	
	var chartClicked3 = false;
	var series3 =null;

	
	function runchartdiv3() {
		am5.ready(function() {


              if(chartClicked3){
                chart3.series.removeIndex ( chart3.series.indexOf ( series3 ) );
      
              }


			// Create root element
			// https://www.amcharts.com/docs/v5/getting-started/#Root_element
			

			// Set themes
			// https://www.amcharts.com/docs/v5/concepts/themes/
			root3.setThemes([
			  am5themes_Animated.new(root3)
			]);

		

			// Create series
			// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
			series3 = chart3.series.push(
			  am5percent.PieSeries.new(root3, {
			    valueField: "value",
			    categoryField: "category",
			    endAngle: 270
			  })
			);

			series3.states.create("hidden", {
			  endAngle: -90
			});

			
			
			var data = [];
			var roomdata;
			
			if("${e_report.pie_data }" == "lable_num"){
			
			
			 if(chartClicked3){
			    var roomCounts = {};
					$("#tabledata tbody tr:visible").each(function () {
								    if ($(this).is(":visible")) {
								     
								        var date = $("td[data-custom='${e_report.pie_label }']", this).text();
								        var tdElements = $("td[data-custom='${e_report.pie_label }']", this);
								        
								        // นับจำนวนและจับคู่กับชื่อห้อง
								        tdElements.each(function () {
								            var roomName = $(this).text();
								            if (roomCounts.hasOwnProperty(roomName)) {
								                // หากชื่อห้องนี้มีอยู่แล้ว นับจำนวนขึ้น
								                roomCounts[roomName]++;
								            } else {
								                // หากชื่อห้องนี้ยังไม่มีใน object, เพิ่มชื่อห้องและกำหนดค่าเป็น 1
								                roomCounts[roomName] = 1;
								            }
								        });
								        
								        roomdata = roomCounts;
								       // console.log(roomdata);
								    }
						});
			}else{
					$("#tabledata tbody tr").each(function () {
					var roomCounts = {};
		
				
								 var date =  $("td[data-custom='${e_report.pie_label }']").text();
							
					             var value =  $("td[data-custom='${e_report.pie_data }']").text();
							
					             
					             var tdElements = $("td[data-custom='${e_report.pie_label }']");
					      
					          // นับจำนวนและจับคู่กับชื่อห้อง
					          tdElements.each(function() {
					              var roomName = $(this).text();
					              if (roomCounts.hasOwnProperty(roomName)) {
					                  // หากชื่อห้องนี้มีอยู่แล้ว นับจำนวนขึ้น
					                  roomCounts[roomName]++;
					     
					              } else {
					                  // หากชื่อห้องนี้ยังไม่มีใน object, เพิ่มชื่อห้องและกำหนดค่าเป็น 1
					                  roomCounts[roomName] = 1;
					                
					              }
					          });
					
					        
					          
					         // console.log(roomdata); 
					          
					          roomdata =roomCounts;
	 
				});
			}	
				
				
	/*	*/
	}
			
	
	if("${e_report.pie_data }" != "lable_num"){
	
	if(chartClicked3){
	  var roomCounts = {};
	
				$("#tabledata tbody tr").each(function () {
				    if ($(this).is(":visible")) {
				      
				        var date = $("td[data-custom='${e_report.pie_label }']", this).text();
				        var tdElements = $("td[data-custom='${e_report.pie_label }']", this);
				        
				        // นับจำนวนและจับคู่กับชื่อห้อง
				        tdElements.each(function () {
				            var roomName = $(this).text(); // ดึงชื่อห้อง
				            var value = $(this).siblings("td[data-custom='${e_report.pie_data }']").text(); // ดึงค่า value จาก td ที่เกี่ยวข้อง
				
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
				        //console.log(roomdata);
				    }
				});
				
		}else{
		
				$("#tabledata tbody ").each(function () {
			var roomCounts = {};

		
						 var date =  $("td[data-custom='${e_report.pie_label }']").text();
						 var tdElements = $("td[data-custom='${e_report.pie_label }']");
						 var roomCounts = {}; // สร้าง object สำหรับเก็บจำนวนห้อง

						 tdElements.each(function() {
						     var roomName = $(this).text(); // ดึงชื่อห้อง
						     var value = $(this).siblings("td[data-custom='${e_report.pie_data }']").text(); // ดึงค่า value จาก td ที่เกี่ยวข้อง

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
               //	console.log(JSON.stringify(roomdata, null, 2));
			    for (var room in roomdata) {
			    //	alert(room);
			     	//alert(roomdata[room]);
			      	     data.push({ category: room, value: roomdata[room] });
			    }
			
			// Set data
			// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Setting_data
			series3.data.setAll(data);

			series3.appear(1000, 100);
			
			
			chartClicked3 =true;
			
			
			}); // en
		
	}
			
					