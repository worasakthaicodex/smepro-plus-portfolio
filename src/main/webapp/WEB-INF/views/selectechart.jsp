<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Report kpi</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'><link rel="stylesheet" href="./style.css">


<style>

@import url(https://fonts.googleapis.com/css?family=Roboto:300);

.header {
  background: #182444;
  margin: -5px;
  padding-left: 5px;  
}

.filter{
  background: #C0C0C0;
}

.charts-page{
  padding: 3% 0 0;
  margin: auto;
}

.charts{
  position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 80%;
  margin: 0 auto 100px;
  padding: 45px;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}

.chart{
  width: 100%; 
  min-height: 450px;
}

h2{
  color: #E06216;
  font-family: "Roboto", sans-serif;
}
body{
  background: #E9EFF3;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; 
}
</style>

</head>
<body>
<!-- partial:index.partial.html -->
<div class="header">
<h1 style="color:#fff;margin:0px">Reports</h1>
</div>




<div class="charts-page">
  <div class="charts">
    <form method="POST" action="/report-dormitory">
      <div class="filter activities alert">
      
      
         <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      Year :
      <select name="years" class="selectpicker">
        <option value="${year }" <c:if test="${years == year}">selected</c:if> >${year }</option>
 <option value="${year-1 }"  <c:if test="${years == year-1}">selected</c:if>   >${year-1 }</option>
  <option value="${year-2 }" <c:if test="${years == year-2}">selected</c:if>>${year-2 }</option>
   <option value="${year-3 }" <c:if test="${years == year-3}">selected</c:if>>${year-3 }</option>
    <option value="${year-4 }" <c:if test="${years == year-4}">selected</c:if>>${year-4 }</option>
     <option value="${year-5 }" <c:if test="${years == year-5}">selected</c:if>>${year-5 }</option>
      </select>&emsp;

      Month :
      <select name="months">
        <option value="1" <c:if test="${month == 1}">selected</c:if> >01</option>
        <option value="2" <c:if test="${month == 2}">selected</c:if>>02</option>
        <option value="3" <c:if test="${month == 3}">selected</c:if>>03</option>
           <option value="4" <c:if test="${month == 4}">selected</c:if>>04</option>
            <option value="5" <c:if test="${month == 5}">selected</c:if>>05</option>
             <option value="6" <c:if test="${month == 6}">selected</c:if>>06</option>
              <option value="7" <c:if test="${month == 7}">selected</c:if>>07</option>
               <option value="8" <c:if test="${month == 8}">selected</c:if>>08</option>
                <option value="9" <c:if test="${month == 9}">selected</c:if>>09</option>
                 <option value="10" <c:if test="${month == 10}">selected</c:if>>10</option>
                  <option value="11" <c:if test="${month == 11}">selected</c:if>>11</option>
                   <option value="12" <c:if test="${month == 12}">selected</c:if>>12</option>
      </select>&emsp;
     
      <button  type="submit" class="btn btn-default">Filter</button>
        <a href="" class="btn btn-primary me-1 waves-effect waves-float waves-light"  target="_blank">
                                Setting
     </a> 
    </div>
   </form>
   
   
    <h2>${conditions.condition_name}</h2>
    
   
<hr>
    <div class="row activities">
     <div class="col-md-12 ">
        <div id="chart_activites" class="chart"></div>
      </div>
      <div class="col-md-12 ">
        <table class="table table-bordered">
          <thead>
            <tr >
                                            <th style="width:20%">
                                            รายการ
                                            </th>
                                                <th>${conditions.condition_type1 }</th>
                                            <th> ${conditions.condition_type2 }</th>
                                            <th>${conditions.condition_type3 }</th>
                                            <th>${conditions.condition_target } </th>
                                              <th>${conditions.condition_result }</th>
                                         
                                        </tr>
          </thead>
          <tbody>
            <tr>
              <td id="title1">${conditions.condition_1 }&nbsp; </td>
              <td id="note1_1">${eformdata.condition_1_note1 }</td>
              <td id="note1_2">${eformdata.condition_1_note2 }</td>
              <td id="note1_3">${eformdata.condition_1_note3 }</td>
               <td id="note1_4"> ${conditions.condition_target1 }</td>
                <td id="note1_5">${eformdata.condition_1_total }</td>
            </tr>
       
       
            <tr>
              <td id="title2">${conditions.condition_2 }&nbsp; </td>
              <td id="note2_1">${eformdata.condition_2_note1 }</td>
              <td id="note2_2">${eformdata.condition_2_note2 }</td>
              <td id="note2_3">${eformdata.condition_2_note3 }</td>
               <td id="note2_4"> ${conditions.condition_target2 }</td>
                <td id="note2_5">${eformdata.condition_2_total }</td>
            </tr>
            
                 <tr>
              <td id="title3">${conditions.condition_3 }&nbsp; </td>
              <td id="note3_1">${eformdata.condition_3_note1 }</td>
              <td id="note3_2">${eformdata.condition_3_note2 }</td>
              <td id="note3_3">${eformdata.condition_3_note3 }</td>
               <td id="note3_4"> ${conditions.condition_target3 }</td>
                <td id="note3_5">${eformdata.condition_3_total }</td>
            </tr>
            
                 <tr>
              <td id="title4">${conditions.condition_4 }&nbsp; </td>
              <td id="note4_1">${eformdata.condition_4_note1 }</td>
              <td id="note4_2">${eformdata.condition_4_note2 }</td>
              <td id="note4_3">${eformdata.condition_4_note3 }</td>
               <td id="note4_4"> ${conditions.condition_target4 }</td>
                <td id="note4_5">${eformdata.condition_4_total }</td>
            </tr>
            
                 <tr>
              <td id="title5">${conditions.condition_5 }&nbsp; </td>
              <td id="note5_1">${eformdata.condition_5_note1 }</td>
              <td id="note5_2">${eformdata.condition_5_note2 }</td>
              <td id="note5_3">${eformdata.condition_5_note3 }</td>
               <td id="note5_4"> ${conditions.condition_target1 }</td>
                <td id="note5_5">${eformdata.condition_5_total }</td>
            </tr>
            
                 <tr>
              <td id="title1">${conditions.condition_6 }&nbsp; </td>
              <td id="note6_1">${eformdata.condition_6_note1 }</td>
              <td id="note6_2">${eformdata.condition_6_note2 }</td>
              <td id="note6_3">${eformdata.condition_6_note3 }</td>
               <td id="note6_4"> ${conditions.condition_target6 }</td>
                <td id="note6_5">${eformdata.condition_6_total }</td>
            </tr>
            
                 <tr>
              <td id="title7">${conditions.condition_7 }&nbsp; </td>
              <td id="note7_1">${eformdata.condition_7_note1 }</td>
              <td id="note7_2">${eformdata.condition_7_note2 }</td>
              <td id="note7_3">${eformdata.condition_7_note3 }</td>
               <td id="note7_4"> ${conditions.condition_target7 }</td>
                <td id="note7_5">${eformdata.condition_7_total }</td>
            </tr>
            
                 <tr>
              <td id="title8">${conditions.condition_8 }&nbsp; </td>
              <td id="note8_1">${eformdata.condition_8_note1 }</td>
              <td id="note8_2">${eformdata.condition_8_note2 }</td>
              <td id="note8_3">${eformdata.condition_8_note3 }</td>
               <td id="note8_4"> ${conditions.condition_target8 }</td>
                <td id="note8_5">${eformdata.condition_8_total }</td>
            </tr>
            
                 <tr>
              <td id="title9">${conditions.condition_9 }&nbsp; </td>
              <td id="note9_1">${eformdata.condition_9_note1 }</td>
              <td id="note9_2">${eformdata.condition_9_note2 }</td>
              <td id="note9_3">${eformdata.condition_9_note3 }</td>
               <td id="note9_4"> ${conditions.condition_target9 }</td>
                <td id="note9_5">${eformdata.condition_9_total }</td>
            </tr>
            
                 <tr>
              <td id="title10">${conditions.condition_10 }&nbsp; </td>
              <td id="note10_1">${eformdata.condition_10_note1 }</td>
              <td id="note10_2">${eformdata.condition_10_note2 }</td>
              <td id="note10_3">${eformdata.condition_10_note3 }</td>
               <td id="note10_4"> ${conditions.condition_target10 }</td>
                <td id="note10_5">${eformdata.condition_10_total }</td>
            </tr>
            
                 <tr>
              <td id="title11">${conditions.condition_11 }&nbsp; </td>
              <td id="note11_1">${eformdata.condition_11_note1 }</td>
              <td id="note11_2">${eformdata.condition_11_note2 }</td>
              <td id="note11_3">${eformdata.condition_11_note3 }</td>
               <td id="note11_4"> ${conditions.condition_target11 }</td>
                <td id="note11_5">${eformdata.condition_11_total }</td>
            </tr>
            
                 <tr>
              <td id="title12">${conditions.condition_12 }&nbsp; </td>
              <td id="note12_1">${eformdata.condition_12_note1 }</td>
              <td id="note12_2">${eformdata.condition_12_note2 }</td>
              <td id="note12_3">${eformdata.condition_12_note3 }</td>
               <td id="note12_4"> ${conditions.condition_target12 }</td>
                <td id="note12_5">${eformdata.condition_12_total }</td>
            </tr>
            
                 <tr>
              <td id="title13">${conditions.condition_13 }&nbsp; </td>
              <td id="note13_1">${eformdata.condition_13_note1 }</td>
              <td id="note13_2">${eformdata.condition_13_note2 }</td>
              <td id="note13_3">${eformdata.condition_13_note3 }</td>
               <td id="note13_4"> ${conditions.condition_target13 }</td>
                <td id="note13_5">${eformdata.condition_13_total }</td>
            </tr>
            
                 <tr>
              <td id="title14">${conditions.condition_14 }&nbsp; </td>
              <td id="note14_1">${eformdata.condition_14_note1 }</td>
              <td id="note14_2">${eformdata.condition_14_note2 }</td>
              <td id="note14_3">${eformdata.condition_14_note3 }</td>
               <td id="note14_4"> ${conditions.condition_target14 }</td>
                <td id="note14_5">${eformdata.condition_14_total }</td>
            </tr>
            
                 <tr>
              <td id="title15">${conditions.condition_15 }&nbsp; </td>
              <td id="note15_1">${eformdata.condition_15_note1 }</td>
              <td id="note15_2">${eformdata.condition_15_note2 }</td>
              <td id="note15_3">${eformdata.condition_15_note3 }</td>
               <td id="note15_4"> ${conditions.condition_target15 }</td>
                <td id="note15_5">${eformdata.condition_15_total }</td>
            </tr>
       
          </tbody>
        </table>
      </div>
     
    </div>


   
    </div>
  </div>  
</div>
<!-- partial -->
  <script src='https://www.gstatic.com/charts/loader.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>

<script>
$(document).ready(function(){
    // Load the Visualization API and the corechart package.
    google.charts.load('current', {'packages':['corechart']});

    // Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawChartActivities);
    google.charts.setOnLoadCallback(drawChartIncidents);
    google.charts.setOnLoadCallback(drawChartProblems);
});
//Callback that creates and populates a data table,
//instantiates the pie chart, passes in the data and
//draws it.
function drawChartActivities() {

// Create the data table.
var data = new google.visualization.DataTable();
data.addColumn('string', 'Topping');
data.addColumn('number', 'Slices');
data.addRows([
  [$("#title1").text(), parseInt($("#actOpPer1").text())],
  [$("#title2").text(), parseInt($("#actOpPer2").text())],
  [$("#title3").text(), parseInt($("#actOpPer3").text())],
  ['Other', parseInt($("#actOthPer").text())],
  ['Monitoring', parseInt($("#actMoPer").text())],
  ['QIP Definition', parseInt($("#actQipPer").text())]
]);

// Set chart options
var options = {'title':'จำนวน'};

// Instantiate and draw our chart, passing in some options.
var chart = new google.visualization.PieChart(document.getElementById('chart_activites'));
chart.draw(data, options);
}

function drawChartIncidents(){
	// Create the data table.
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Topping');
	data.addColumn('number', 'Slices');
	data.addRows([
	  ['ห้องว่าง', parseInt($("#actOpPer2").text())],
	  ['ห้องไม่ว่าง', parseInt($("#actBaPer2").text())],
	  ['ห้องปิดปรับปรุง', parseInt($("#actFuPer2").text())]
	]);

	// Set chart options
	var options = {'title':'Percentage'};

	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document.getElementById('chart_activites2'));
	chart.draw(data, options);
}

function drawChartProblems(){
// Create the data table.
var data = new google.visualization.DataTable();
data.addColumn('string', 'Topping');
data.addColumn('number', 'บาท');
data.addRows([
  ['01', ${month1 }],
  ['02', ${month2 }],
  ['03', ${month3 }],
  ['04', ${month4 }],
  ['05', ${month5 }],
  ['06', ${month6 }],
  ['07', ${month7 }],
  ['08', ${month8 }],
  ['09', ${month9 }],
  ['10', ${month10 }],
  ['11', ${month11 }],
  ['12', ${month12 }]
]);







// Set chart options
var options = {'title':'Income',
               'colors': ['#C0504D']};

// Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById('chart_problems'));
chart.draw(data, options);
}
</script>





</body>
</html>
