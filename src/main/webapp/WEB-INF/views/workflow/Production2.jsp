<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="th">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>One Pager Daily Production Sheet</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            width: 100%;
            max-width: 1200px;
            margin: auto;
        }
       .section {
            border-top: 2px solid #000;
            padding: 10px;
            margin-bottom: 10px;
        }
        .header {
            text-align: center;
			border: 2px solid #000;
			color:#fff;
			background-color:#042338;
        }
        .section-title {
            font-weight: bold;
            margin-bottom: 5px;
        }
        .row {
            display: flex;
            justify-content: space-between;
        }
        .column {
            flex: 1;
            padding: 5px;
        }
        .chart-container {
            width: 100%;
            height: 270px; /* ลดความสูงของกราฟลงครึ่งหนึ่ง */
            background-color: #f0f0f0;
          
        }
        .summary-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .summary-table th, .summary-table td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        .summary-table th {
            background-color: #e7520d;
			color: #fff;
            text-align: left;
        }
        
        
          .container2 {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            margin: 10px;
        }
        .box {
            width: 150px;
            padding: 20px;
            margin: 10px;
            background-color:  #e7520d;
            	color: #fff;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .box h2 {
            margin: 0 0 10px;
            font-size: 24px;
        }
        .box p {
            margin: 5px 0;
            font-size: 18px;
        }
        
        
        
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1> Daily Production</h1>
        </div>
		 <br> <br>
	
   
            
               <div class="container2">
        <div class="box">
            <h2>จำนวนงาน</h2>
            <p id="job"></p>
        </div>
        <div class="box">
            <h2>ยอดผลิต</h2>
            <p id="production"></p>
        </div>
        <div class="box">
            <h2>งานดี</h2>
            <p>18,193</p>
        </div>
        <div class="box">
            <h2>งานเสีย</h2>
            <p>255</p>
        </div>
    </div>
        
        	<br>
         
    
			    <div class="section-title">จำนวนงาน</div> <br>
        <div class="section">
        
            <div class="chart-container" >
                <canvas id="kpi-chart2" style="height: 250px;margin: auto;"></canvas>
                  
            </div>
      
                  <table class="summary-table">
                <thead>
                    <tr>
          
                        <th>แผนก</th>
                      
                       <c:forEach items="${date }" var="dates" varStatus="count" begin="0" end="6">     
                        <th>${dates }</th>
  						 </c:forEach>
                                     <th>Total</th> 
                    </tr>
                </thead>
                <tbody>
                     <c:set var="jobTotal" value="0" />
             <c:forEach items="${jobs }" var="row" varStatus="count">     
                    <tr>
                    
       
                        <td>${row.department }</td>
              
              
              
                     <td>${row.total1 }</td>
                    <td>${row.total2 }</td>
                    <td>${row.total3 }</td>
                    <td>${row.total4 }</td>
                     <td>${row.total5 }</td>
                      <td>${row.total6 }</td>
                        <td>${row.total7 }</td>
         
                            
                            
                            
                            
                              <c:set var="Total" value="${row.total1 + row.total2 + row.total3 + row.total4 + row.total5 + row.total6 + row.total7 }" />
                            
                               <td>${Total }</td>
                               
                                      <c:set var="jobTotal" value="${jobTotal+Total}" />
                            
                    </tr>
                   </c:forEach> 
                    
                         <script>  
                         document.getElementById('job').innerText = '${jobTotal}';
                         </script>
                </tbody>
            </table>
       
        </div>
		<br>
		  <div class="section-title">Daily Production Sheet</div> <br>
        <div class="section">
          
            <div class="chart-container" style="display:none">
                <canvas id="production-chart" style="  margin: auto;height: 250px;"></canvas>
            </div>
              <div class="chart-container" style="width:100%">
                <canvas id="kpi-chart" style="height: 240px;float:left"></canvas>
                  <canvas id="kpi-chart3" style="height: 240px;float:left"></canvas>
            </div>
            
                
             <table class="summary-table">
                <thead>
                    <tr>
                    <th rowspan="2">แผนก</th>
                       <c:forEach items="${date_production }" var="dates" varStatus="count" begin="0" end="6">     
                        <th colspan="3">${dates }</th>
  						 
 						</c:forEach>
 							   <th  rowspan="2">Total</th> 
   						</tr>
   						
    					<tr>
                     <c:forEach items="${date_production }" var="dates" varStatus="count" begin="0" end="6">     
                      
                          <th>ยอดผลิต</th>
                          <th>งานดี</th>
                          <th>งานเสีย</th>
                              
  						 </c:forEach>
  					
  						</tr>
  						
                                   
                   
                </thead>
                <tbody>
                     <c:set var="productionTotal" value="0" />
             <c:forEach items="${productionDTOs }" var="row" varStatus="count">     
                    <tr>
                    
       
                        <td>${row.department }</td>
              
              
              
                     <td>${row.good_production_output1 + row.defective_production_output1 }</td>
                       <td>${row.good_production_output1 }</td>
                       <td>${row.defective_production_output1 }</td>
                     
                    <td>${row.good_production_output2  + row.defective_production_output2}</td>
                     <td>${row.good_production_output2 }</td>
                     <td>${row.defective_production_output2 }</td>
                    
                    <td>${row.good_production_output3  + row.defective_production_output3 }</td>
                     <td>${row.good_production_output3 }</td>
                     <td>${row.defective_production_output3 }</td>
                     
                     
                    <td>${row.good_production_output4  + row.defective_production_output4}</td>
                    <td>${row.good_production_output4 }</td>
                    <td>${row.defective_production_output4 }</td>
                    
                     <td>${row.good_production_output5  + row.defective_production_output5 }</td>
                       <td>${row.good_production_output5 }</td>
                       <td>${row.defective_production_output5 }</td>
                     
                      <td>${row.good_production_output6 + row.defective_production_output6  }</td>
                       <td>${row.good_production_output6 }</td>
                       <td>${row.defective_production_output6 }</td>
                       
                       
                        <td>${row.good_production_output7  + row.defective_production_output7 }</td>
                        <td>${row.good_production_output7 }</td>
                           <td>${row.defective_production_output7 }</td>     
                            
                                <c:set var="Total" value="${
                                row.good_production_output1 + row.defective_production_output1 
                                +row.good_production_output2 + row.defective_production_output2 
                                +row.good_production_output3 + row.defective_production_output3 
                                +row.good_production_output4 + row.defective_production_output4 
                                +row.good_production_output5 + row.defective_production_output5 
                                +row.good_production_output6 + row.defective_production_output6 
                                +row.good_production_output7 + row.defective_production_output7 
                                 }" />
                 
                               <td>${Total }</td> 
                           
                            
                            
                            
                                      <c:set var="productionTotal" value="${productionTotal +Total}" />
                            
                    </tr>
                   </c:forEach> 
                    
                         <script>  
                         document.getElementById('production').innerText = '${productionTotal}';
                         </script>
                </tbody>
            </table>
        </div>
	
		
        <div class="section" style="width:45%;float:left;margin-left:10px;display:none">
            <div class="section-title">จำนวนผลิตมากสุด</div>
            <div class="chart-container">
                <canvas id="top-production-chart" style="  margin: auto;"></canvas>
            </div>
        </div>
		
	
        <div class="section" style="width:45%;float:left;margin-left:10px;display:none">
          <div class="section-title">งานเสียมากสุด</div>
            <div class="chart-container">
                <canvas id="top-defect-chart" style="  margin: auto;"></canvas>
            </div>
        </div>
		
		
	 	  <div class="section-title"  style="clear:both;">
		  	<br><br>
		  พื้นที่จัดเก็บ FG</div><br>
        <div class="section">
      
            <table class="summary-table">
                <thead>
                    <tr>
                        <th>Production</th>
                       <th>Production ID</th>
                        <th>สถานที่จัดเก็บ</th>
                         <th>รับเข้า</th>
                          <th>จ่ายออก</th>
                           <th>ใช้ไป</th>
                        <th>อยู่ในพื้นที่</th>
      
                    </tr>
                </thead>
                <tbody>
          <c:forEach items="${fgDTOs }" var="row" varStatus="count">     
                    <tr>
                       
                        <td>${row.product }</td>
                         <td>${row.lot }</td>
                        <td>${row.department }</td>
                          <td>${row.receivede_fg }</td>
                          <td></td>
                        <td></td>
                        
                 		 <td>${row.receivede_fg }</td>
                    </tr>
           </c:forEach>
                </tbody>
            </table>
        </div>
		
		
			<br>
			
			
		
		
    </div>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        const kpiCtx = document.getElementById('kpi-chart').getContext('2d');
        
        const kpiCtx3 = document.getElementById('kpi-chart3').getContext('2d');
        const kpiCtx2 = document.getElementById('kpi-chart2').getContext('2d');
        const productionCtx = document.getElementById('production-chart').getContext('2d');
        const topProductionCtx = document.getElementById('top-production-chart').getContext('2d');
        const topDefectCtx = document.getElementById('top-defect-chart').getContext('2d');

        const kpiChart = new Chart(kpiCtx, {
            type: 'bar',
            data: {
                labels: ['${date_production[0] }', '${date_production[1] }', '${date_production[2] }', '${date_production[3] }', '${date_production[4] }', '${date_production[5] }', '${date_production[6] }'],  // Labels for each day
                datasets: [
                	
                    <c:forEach items="${productionDTOs }" var="row" varStatus="count">     
                    {
                        label: '${row.department}',
                        data: [${row.good_production_output1}, ${row.good_production_output2},${row.good_production_output3}, ${row.good_production_output4}, ${row.good_production_output5}
                        , ${row.good_production_output6}, ${row.good_production_output7}],  // Data for 'งานดี' for แผนก A over 7 days
                        backgroundColor: '#4BC0C0',  // Green
                        hoverBackgroundColor: '##4BC0C0'
                    },
                    </c:forEach>
                    
                ]
            },
            options: {
                responsive: true,
                scales: {
                    x: {
                        stacked: false,  // Group the bars
                        title: {
                            display: true,
                            text: 'งานดี'
                        }
                    },
                    y: {
                        stacked: false,  // Group the bars
                        title: {
                            display: true,
                            text: 'จำนวน'
                        }
                    }
                }
            }
        });
        const kpiChart3 = new Chart(kpiCtx3, {
            type: 'bar',
            data: {
            	labels: ['${date_production[0] }', '${date_production[1] }', '${date_production[2] }', '${date_production[3] }', '${date_production[4] }', '${date_production[5] }', '${date_production[6] }'], 
                datasets: [
                  
                    <c:forEach items="${productionDTOs }" var="row" varStatus="count">     
                    {
                        label: '${row.department}',
                        data: [${row.defective_production_output1}, ${row.defective_production_output2},${row.defective_production_output3}
                        , ${row.defective_production_output4}, ${row.defective_production_output5}
                        , ${row.defective_production_output6}, ${row.defective_production_output7}],  // Data for 'งานดี' for แผนก A over 7 days
                        backgroundColor: '#FF6384',  // Green
                        hoverBackgroundColor: '#FF6384'
                    },
                    </c:forEach>
                ]
            },
            options: {
                responsive: true,
                scales: {
                    x: {
                        stacked: false,  // Group the bars
                        title: {
                            display: true,
                            text: 'งานเสีย'
                        }
                    },
                    y: {
                        stacked: false,  // Group the bars
                        title: {
                            display: true,
                            text: 'จำนวน'
                        }
                    }
                }
            }
        });
        
        
        const color = ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0'];
        const kpiChart2 = new Chart(kpiCtx2, {
            type: 'line',
            data: {
                labels: [ '${date[0] }', '${date[1] }', '${date[2] }', '${date[3] }', '${date[4] }', '${date[5] }', '${date[6] }'],
                datasets: [
                    
              <c:forEach items="${jobs }" var="row" varStatus="count">     	
                	{
                    label: '${row.department}',
                    data: [${row.total1}, ${row.total2}, ${row.total3}, ${row.total4}, ${row.total5}, ${row.total6}, ${row.total7}],
                    borderColor: color[${count.count-1}],
                    backgroundColor: color[${count.count-1}],
                    fill: false
                },
              </c:forEach>
                
                ]
            }
        });
        

        const productionChart = new Chart(productionCtx, {
            type: 'line',
            data: {
                labels: ['1', '2', '3', '4', '5', '6', '7'],
                datasets: [{
                    label: 'Daily Production',
                    data: [120, 150, 180, 200, 170, 160, 210],
                    borderColor: '#36A2EB',
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    fill: false
                }]
            }
        });

        const topProductionChart = new Chart(topProductionCtx, {
            type: 'pie',
            data: {
                labels: ['ผลิตภัณฑ์ A', 'ผลิตภัณฑ์ B', 'ผลิตภัณฑ์ C'],
                datasets: [{
                    label: 'Top Production',
                    data: [50, 30, 20],
                    backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                    hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
                }]
            }
        });

        const topDefectChart = new Chart(topDefectCtx, {
            type: 'pie',
            data: {
                labels: ['ผลิตภัณฑ์ X', 'ผลิตภัณฑ์ Y', 'ผลิตภัณฑ์ Z'],
                datasets: [{
                    label: 'Top Defects',
                    data: [40, 35, 25],
                    backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                    hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
                }]
            }
        });
    </script>
</body>
</html>
