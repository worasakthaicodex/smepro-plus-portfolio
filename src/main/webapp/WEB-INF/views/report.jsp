<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Report kpi</title>
  
       <link href="<c:url value="/resources/app-assets/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/app-assets/css/bootstrap-extended.css" />" rel="stylesheet">
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>


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
font-size:16px;
}
</style>

</head>
<body>
<!-- partial:index.partial.html -->
<div class="header">
<h1 style="color:#fff;margin:0px">Reports </h1>
</div>




<div class="charts-page">
  <div class="charts">
    <form method="POST" action="/searchreport-dormitory">
      <div class="filter activities alert">
      
          <input  type="hidden" name="condition_id" value="${conditions.condition_id}">
         <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      DATE Start :
       <input  type="date" name="datestart"  required>
&emsp;

       DATE End :
     <input  type="date" name="dateend" required>&emsp;
     
      <button  type="submit" class="btn btn-default">Filter</button>
        <a href="#" class="btn btn-primary me-1 waves-effect waves-float waves-light"  onclick="setting()">
                                Setting
     </a> 
    </div>
   </form>
   
   
    <h2>${conditions.condition_name}  : ${tilte }</h2>


<hr>

    <div class="row activities">
       
     <div class="col-md-12 ">
         

        <div id="chartdiv" class="chart">
        
            
    <div class="btn-group mt-md-0 mt-1" role="group" aria-label="Basic radio toggle button group" style="float:right">
                                        <input type="radio" class="btn-check" name="radio_options" id="radio_option1" autocomplete="off" checked="" onchange="sw('chartdiv1')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_option1">Pie</label>

                                        <input type="radio" class="btn-check" name="radio_options" id="radio_option2" autocomplete="off" onchange="sw('chartdiv2')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_option2">Column</label>

                                        <input type="radio" class="btn-check" name="radio_options" id="radio_option3" autocomplete="off" onchange="sw('chartdiv3')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_option3">Line</label>
                                        
                                       
                                    </div>
        
          <div id="chartdiv1" class="chart" style="clear:both"> </div>
           <div id="chartdiv2" class="chart" style="clear:both;display:none"> </div>
              <div id="chartdiv3" class="chart" style="clear:both;display:none"> </div>
        
        </div>

          
           <div id="setting" style="display:none">
             <form method="POST" action="/setreport-dormitory">
                 <input  type="hidden" name="condition_id" value="${conditions.condition_id}">
         <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
           <div class="row">
            <div class="col-md-6 ">
           Type Chart :  Pie
          <br>   <br>
              Lable : <select name="chartdiv1label">
                   <option value="type1"      <c:if test="${chartdiv1label == 'type1' }">selected</c:if> >รายการ</option>
              <option value="type2" <c:if test="${chartdiv1label == 'type2' }">selected</c:if> >${conditions.condition_type1 }</option>
               <option value="type3" <c:if test="${chartdiv1label == 'type3' }">selected</c:if> > ${conditions.condition_type2 }</option> 
               <option value="type4" <c:if test="${chartdiv1label == 'type4' }">selected</c:if> > ${conditions.condition_type3 }</option> 
                            
              </select>
          <br>   <br>   
             Data : <select name="chartdiv1data">
              
              <option value="type2"  <c:if test="${chartdiv1data == 'type2' }">selected</c:if>>${conditions.condition_type1 }</option>
               <option value="type3" <c:if test="${chartdiv1data == 'type3' }">selected</c:if> > ${conditions.condition_type2 }</option> 
               <option value="type4" <c:if test="${chartdiv1data == 'type4' }">selected</c:if>> ${conditions.condition_type3 }</option> 
                 <option value="type5" <c:if test="${chartdiv1data == 'type5' }">selected</c:if>>${conditions.condition_target }</option> 
                     <option value="type7" <c:if test="${chartdiv1data == 'type7' }">selected</c:if>>${conditions.condition_result }</option>  
                            
              </select>
                <br>   <br>   
           
        
           <br>   <br>  
           
            Type Chart : Column 
          <br>   <br>
              Lable : <select name="chartdiv2label">
                <option value="type1" <c:if test="${chartdiv2label == 'type1' }">selected</c:if> >รายการ</option>
              <option  value="type2" <c:if test="${chartdiv2label == 'type2' }">selected</c:if>>${conditions.condition_type1 }</option>
               <option  value="type3" <c:if test="${chartdiv2label == 'type3' }">selected</c:if>> ${conditions.condition_type2 }</option> 
               <option  value="type4" <c:if test="${chartdiv2label == 'type4' }">selected</c:if>> ${conditions.condition_type3 }</option> 
                            
              </select>
          <br>   <br>   
             Data : <select name="chartdiv2data">
              
              <option  value="type2" <c:if test="${chartdiv2data == 'type2' }">selected</c:if>>${conditions.condition_type1 }</option>
               <option  value="type3" <c:if test="${chartdiv2data == 'type3' }">selected</c:if>> ${conditions.condition_type2 }</option> 
               <option  value="type4" <c:if test="${chartdiv2data == 'type4' }">selected</c:if>> ${conditions.condition_type3 }</option> 
                 <option  value="type5" <c:if test="${chartdiv2data == 'type5' }">selected</c:if>>${conditions.condition_target }</option> 
                     <option  value="type7" <c:if test="${chartdiv2data == 'type7' }">selected</c:if>>${conditions.condition_result }</option>  
                         <option  value="type6" <c:if test="${chartdiv2data == 'type6' }">selected</c:if>>Months</option>
                            
              </select>
        <br>   <br>   <br>  
            Type Chart : Line  
          <br>   <br>
              Lable : <select name="chartdiv3label">
                <option value="type1" <c:if test="${chartdiv3label == 'type1' }">selected</c:if>>รายการ</option>
              <option value="type2" <c:if test="${chartdiv3label == 'type2' }">selected</c:if>>${conditions.condition_type1 }</option>
               <option value="type3" <c:if test="${chartdiv3label == 'type3' }">selected</c:if>> ${conditions.condition_type2 }</option> 
               <option value="type4" <c:if test="${chartdiv3label == 'type4' }">selected</c:if>> ${conditions.condition_type3 }</option> 
                            
              </select>
          <br>   <br>   
             Data : <select name="chartdiv3data">
              
              <option value="type6">Months</option>
                            
              </select>
          <br>  
          
           <br>   <br>     
           
            </div>
               <c:set var="list" value="${shows }" />
             <div class="col-md-6 ">
                View : <br> <input type="checkbox" onchange="show('type1')" name="shows"  value="type1" <c:if test='${fn:contains(list,"type1")}'>checked</c:if>> รายการ <br>
                <input type="checkbox" onchange="show('type2')" name="shows"  value="type2"  <c:if test='${fn:contains(list,"type2")}'>checked</c:if>> ${conditions.condition_type1 } <br>
           <input type="checkbox" onchange="show('type3')" name="shows"  value="type3"  <c:if test='${fn:contains(list,"type3")}'>checked</c:if>> ${conditions.condition_type2 } <br>
           <input type="checkbox" onchange="show('type4')" name="shows"  value="type4"  <c:if test='${fn:contains(list,"type4")}'>checked</c:if>> ${conditions.condition_type3}  <br>
           <input type="checkbox" onchange="show('type5')" name="shows"  value="type5"  <c:if test='${fn:contains(list,"type5")}'>checked</c:if>> ${conditions.condition_target}  <br>
           <input type="checkbox" onchange="show('type6')" name="shows"  value="type6"  <c:if test='${fn:contains(list,"type6")}'>checked</c:if>> MONTHS <br>
           <input type="checkbox" onchange="show('type7')" name="shows"  value="type7"  <c:if test='${fn:contains(list,"type7")}'>checked</c:if>> ${conditions.condition_result} 
        
    
           </div>
            </div>
            
                 <br> <br>
              <input type="submit" value="Save"  class="btn btn-primary me-1 waves-effect waves-float waves-light" >

          
           <br>   <br>   <br>  
          
           </form>
           </div>
           
           
           
      </div>
      <div class="col-md-12 ">
      
        <br>   <br>  
         <h2>KPI</h2>
        <table class="table table-bordered">
          <thead>
            <tr >
                                            <th style="width:20%;font-size:14px;" class="type1">
                                            รายการ
                                            </th>
                                                <th  class="type2" style="font-size:14px;">${conditions.condition_type1 }</th>
                                            <th class="type3" style="font-size:14px;"> ${conditions.condition_type2 }</th>
                                            <th class="type4" style="font-size:14px;">${conditions.condition_type3 }</th>
                                            <th class="type5" style="font-size:14px;">${conditions.condition_target } </th>
                                                <th  class="type6" style="font-size:14px;">01</th>
                                               <th  class="type6" style="font-size:14px;">02</th>
                                                <th  class="type6" style="font-size:14px;">03</th>
                                                <th  class="type6" style="font-size:14px;">04</th>
                                                  <th  class="type6" style="font-size:14px;">05</th>
                                                   <th  class="type6" style="font-size:14px;">06</th>
                                                        <th  class="type6" style="font-size:14px;">07</th>
                                                              <th  class="type6" style="font-size:14px;">08</th>
                                                                <th  class="type6" style="font-size:14px;">09</th>
                                                                    <th  class="type6" style="font-size:14px;">10</th>
                                                                      <th  class="type6" style="font-size:14px;">11</th>
                                                                        <th  class="type6" style="font-size:14px;">12</th>
                                              <th class="type7" style="font-size:14px;">${conditions.condition_result }</th>
                                         
                                        </tr>
          </thead>
          
            
          
          
          <tbody>
            <tr>
              <td id="title1" class="type1">${conditions.condition_1 }&nbsp; </td>
              <td id="note1_1" class="type2">${eformdata.condition_1_note1 }</td>
              <td id="note1_2" class="type3">${eformdata.condition_1_note2 }</td>
              <td id="note1_3" class="type4">${eformdata.condition_1_note3 }</td>
               <td id="note1_4" class="type5"> ${conditions.condition_target1 }</td>
               
               
                       <td  id="note1_61"  class="type6">${eformdata.month1_1 }</td>
                       <td id="note1_62" class="type6">${eformdata.month2_1 }</td>
                   <td  id="note1_63" class="type6">${eformdata.month3_1 }</td>
                     <td id="note1_64" class="type6">${eformdata.month4_1 }</td>
                  <td id="note1_65" class="type6">${eformdata.month5_1}</td>
                    <td id="note1_66" class="type6">${eformdata.month6_1 }</td>
                    <td id="note1_67" class="type6">${eformdata.month7_1 }</td>
                    <td id="note1_68" class="type6">${eformdata.month8_1 }</td>
                    <td id="note1_69" class="type6">${eformdata.month9_1 }</td>
                     <td id="note1_610" class="type6">${eformdata.month10_1 }</td>
                      <td id="note1_611" class="type6">${eformdata.month11_1 }</td>
                      <td id="note1_612" class="type6">${eformdata.month12_1 }</td>
                      
                      
                <td id="note1_5" class="type7">${eformdata.condition_1_total }</td>
            </tr>
       
       
            <tr>
              <td id="title2" class="type1">${conditions.condition_2 }&nbsp; </td>
              <td id="note2_1"  class="type2">${eformdata.condition_2_note1 }</td>
              <td id="note2_2" class="type3">${eformdata.condition_2_note2 }</td>
              <td id="note2_3" class="type4">${eformdata.condition_2_note3 }</td>
               <td id="note2_4" class="type5"> ${conditions.condition_target2 }</td>
               
        
                       <td  id="note2_61"  class="type6">${eformdata.month1_2 }</td>
                       <td id="note2_62" class="type6">${eformdata.month2_2 }</td>
                   <td  id="note2_63" class="type6">${eformdata.month3_2 }</td>
                     <td id="note2_64" class="type6">${eformdata.month4_2 }</td>
                  <td id="note2_65" class="type6">${eformdata.month5_2}</td>
                    <td id="note2_66" class="type6">${eformdata.month6_2 }</td>
                    <td id="note2_67" class="type6">${eformdata.month7_2 }</td>
                    <td id="note2_68" class="type6">${eformdata.month8_2 }</td>
                    <td id="note2_69" class="type6">${eformdata.month9_2 }</td>
                     <td id="note2_610" class="type6">${eformdata.month10_2 }</td>
                      <td id="note2_611" class="type6">${eformdata.month11_2 }</td>
                      <td id="note2_612" class="type6">${eformdata.month12_2 }</td>
                      
                <td id="note2_5" class="type7">${eformdata.condition_2_total }</td>
            </tr>
            
                 <tr>
              <td id="title3" class="type1">${conditions.condition_3 }&nbsp; </td>
              <td id="note3_1"  class="type2">${eformdata.condition_3_note1 }</td>
              <td id="note3_2" class="type3">${eformdata.condition_3_note2 }</td>
              <td id="note3_3" class="type4">${eformdata.condition_3_note3 }</td>
               <td id="note3_4" class="type5"> ${conditions.condition_target3 }</td>
               
                       
                       <td  id="note3_61"  class="type6">${eformdata.month1_3 }</td>
                       <td id="note3_62" class="type6">${eformdata.month2_3 }</td>
                   <td  id="note3_63" class="type6">${eformdata.month3_3 }</td>
                     <td id="note3_64" class="type6">${eformdata.month4_3 }</td>
                  <td id="note3_65" class="type6">${eformdata.month5_3}</td>
                    <td id="note3_66" class="type6">${eformdata.month6_3 }</td>
                    <td id="note3_67" class="type6">${eformdata.month7_3 }</td>
                    <td id="note3_68" class="type6">${eformdata.month8_3 }</td>
                    <td id="note3_69" class="type6">${eformdata.month9_3 }</td>
                     <td id="note3_610" class="type6">${eformdata.month10_3 }</td>
                      <td id="note3_611" class="type6">${eformdata.month11_3 }</td>
                      <td id="note3_612" class="type6">${eformdata.month12_3 }</td>
                
                                <td id="note3_5" class="type7">${eformdata.condition_3_total }</td>
            </tr>
            
                 <tr>
              <td id="title4" class="type1">${conditions.condition_4 }&nbsp; </td>
              <td id="note4_1"  class="type2">${eformdata.condition_4_note1 }</td>
              <td id="note4_2" class="type3">${eformdata.condition_4_note2 }</td>
              <td id="note4_3" class="type4">${eformdata.condition_4_note3 }</td>
               <td id="note4_4" class="type5"> ${conditions.condition_target4 }</td>
               
               
                    
                       <td  id="note4_61"  class="type6">${eformdata.month1_4 }</td>
                       <td id="note4_62" class="type6">${eformdata.month2_4 }</td>
                   <td  id="note4_63" class="type6">${eformdata.month3_4 }</td>
                     <td id="note4_64" class="type6">${eformdata.month4_4 }</td>
                  <td id="note4_65" class="type6">${eformdata.month5_4}</td>
                    <td id="note4_66" class="type6">${eformdata.month6_4 }</td>
                    <td id="note4_67" class="type6">${eformdata.month7_4 }</td>
                    <td id="note4_68" class="type6">${eformdata.month8_4 }</td>
                    <td id="note4_69" class="type6">${eformdata.month9_4 }</td>
                     <td id="note4_610" class="type6">${eformdata.month10_4 }</td>
                      <td id="note4_611" class="type6">${eformdata.month11_4 }</td>
                      <td id="note4_612" class="type6">${eformdata.month12_4 }</td>
                      
                      
                <td id="note4_5" class="type7">${eformdata.condition_4_total }</td>
                
                
            </tr>
            
                 <tr>
              <td id="title5" class="type1">${conditions.condition_5 }&nbsp; </td>
              <td id="note5_1"  class="type2">${eformdata.condition_5_note1 }</td>
              <td id="note5_2" class="type3">${eformdata.condition_5_note2 }</td>
              <td id="note5_3"  class="type4">${eformdata.condition_5_note3 }</td>
               <td id="note5_4" class="type5"> ${conditions.condition_target5 }</td>
  
          
                       <td  id="note5_61"  class="type6">${eformdata.month1_5 }</td>
                       <td id="note5_62" class="type6">${eformdata.month2_5 }</td>
                   <td  id="note5_63" class="type6">${eformdata.month3_5 }</td>
                     <td id="note5_64" class="type6">${eformdata.month4_5 }</td>
                  <td id="note5_65" class="type6">${eformdata.month5_5}</td>
                    <td id="note5_66" class="type6">${eformdata.month6_5 }</td>
                    <td id="note5_67" class="type6">${eformdata.month7_5 }</td>
                    <td id="note5_68" class="type6">${eformdata.month8_5 }</td>
                    <td id="note5_69" class="type6">${eformdata.month9_5 }</td>
                     <td id="note5_610" class="type6">${eformdata.month10_5 }</td>
                      <td id="note5_611" class="type6">${eformdata.month11_5 }</td>
                      <td id="note5_612" class="type6">${eformdata.month12_5 }</td>
  
  
                <td id="note5_5" class="type7">${eformdata.condition_5_total }</td>
            </tr>
            
                 <tr>
              <td id="title1" class="type1">${conditions.condition_6 }&nbsp; </td>
              <td id="note6_1"  class="type2">${eformdata.condition_6_note1 }</td>
              <td id="note6_2" class="type3">${eformdata.condition_6_note2 }</td>
              <td id="note6_3" class="type4">${eformdata.condition_6_note3 }</td>
               <td id="note6_4" class="type5"> ${conditions.condition_target6 }</td>
             
                       <td  id="note6_61"  class="type6">${eformdata.month1_6 }</td>
                       <td id="note6_62" class="type6">${eformdata.month2_6 }</td>
                   <td  id="note6_63" class="type6">${eformdata.month3_6 }</td>
                     <td id="note6_64" class="type6">${eformdata.month4_6 }</td>
                  <td id="note6_65" class="type6">${eformdata.month5_6}</td>
                    <td id="note6_66" class="type6">${eformdata.month6_6 }</td>
                    <td id="note6_67" class="type6">${eformdata.month7_6 }</td>
                    <td id="note6_68" class="type6">${eformdata.month8_6 }</td>
                    <td id="note6_69" class="type6">${eformdata.month9_6 }</td>
                     <td id="note6_610" class="type6">${eformdata.month10_6 }</td>
                      <td id="note6_611" class="type6">${eformdata.month11_6 }</td>
                      <td id="note6_612" class="type6">${eformdata.month12_6 }</td>
                      
                      
                <td id="note6_5" class="type7">${eformdata.condition_6_total }</td>
            </tr>
            
                 <tr>
              <td id="title7" class="type1">${conditions.condition_7 }&nbsp; </td>
              <td id="note7_1"  class="type2">${eformdata.condition_7_note1 }</td>
              <td id="note7_2" class="type3">${eformdata.condition_7_note2 }</td>
              <td id="note7_3" class="type4">${eformdata.condition_7_note3 }</td>
               <td id="note7_4" class="type5"> ${conditions.condition_target7 }</td>
        
                       <td  id="note7_61"  class="type6">${eformdata.month1_7 }</td>
                       <td id="note7_62" class="type6">${eformdata.month2_7 }</td>
                   <td  id="note7_63" class="type6">${eformdata.month3_7 }</td>
                     <td id="note7_64" class="type6">${eformdata.month4_7 }</td>
                  <td id="note7_65" class="type6">${eformdata.month5_7}</td>
                    <td id="note7_66" class="type6">${eformdata.month6_7 }</td>
                    <td id="note7_67" class="type6">${eformdata.month7_7 }</td>
                    <td id="note7_68" class="type6">${eformdata.month8_7 }</td>
                    <td id="note7_69" class="type6">${eformdata.month9_7 }</td>
                     <td id="note7_610" class="type6">${eformdata.month10_7 }</td>
                      <td id="note7_611" class="type6">${eformdata.month11_7 }</td>
                      <td id="note7_612" class="type6">${eformdata.month12_7 }</td>
                      
                <td id="note7_5" class="type7">${eformdata.condition_7_total }</td>
            </tr>
            
                 <tr>
              <td id="title8" class="type1">${conditions.condition_8 }&nbsp; </td>
              <td id="note8_1"  class="type2">${eformdata.condition_8_note1 }</td>
              <td id="note8_2" class="type3">${eformdata.condition_8_note2 }</td>
              <td id="note8_3" class="type4">${eformdata.condition_8_note3 }</td>
               <td id="note8_4" class="type5"> ${conditions.condition_target8 }</td>
        
                       <td  id="note8_61"  class="type6">${eformdata.month1_8 }</td>
                       <td id="note8_62" class="type6">${eformdata.month2_8 }</td>
                   <td  id="note8_63" class="type6">${eformdata.month3_8 }</td>
                     <td id="note8_64" class="type6">${eformdata.month4_8 }</td>
                  <td id="note8_65" class="type6">${eformdata.month5_8}</td>
                    <td id="note8_66" class="type6">${eformdata.month6_8 }</td>
                    <td id="note8_67" class="type6">${eformdata.month7_8 }</td>
                    <td id="note8_68" class="type6">${eformdata.month8_8 }</td>
                    <td id="note8_69" class="type6">${eformdata.month9_8 }</td>
                     <td id="note8_610" class="type6">${eformdata.month10_8 }</td>
                      <td id="note8_611" class="type6">${eformdata.month11_8 }</td>
                      <td id="note8_612" class="type6">${eformdata.month12_8 }</td>
                      
                <td id="note8_5" class="type7">${eformdata.condition_8_total }</td>
            </tr>
            
                 <tr>
              <td id="title9" class="type1">${conditions.condition_9 }&nbsp; </td>
              <td id="note9_1"  class="type2">${eformdata.condition_9_note1 }</td>
              <td id="note9_2" class="type3">${eformdata.condition_9_note2 }</td>
              <td id="note9_3" class="type4">${eformdata.condition_9_note3 }</td>
               <td id="note9_4" class="type5"> ${conditions.condition_target9 }</td>

                       <td  id="note9_61"  class="type6">${eformdata.month1_9 }</td>
                       <td id="note9_62" class="type6">${eformdata.month2_9 }</td>
                   <td  id="note9_63" class="type6">${eformdata.month3_9 }</td>
                     <td id="note9_64" class="type6">${eformdata.month4_9 }</td>
                  <td id="note9_65" class="type6">${eformdata.month5_9}</td>
                    <td id="note9_66" class="type6">${eformdata.month6_9 }</td>
                    <td id="note9_67" class="type6">${eformdata.month7_9 }</td>
                    <td id="note9_68" class="type6">${eformdata.month8_9 }</td>
                    <td id="note9_69" class="type6">${eformdata.month9_9 }</td>
                     <td id="note9_610" class="type6">${eformdata.month10_9 }</td>
                      <td id="note9_611" class="type6">${eformdata.month11_9 }</td>
                      <td id="note9_612" class="type6">${eformdata.month12_9 }</td>
                      
                <td id="note9_5" class="type7">${eformdata.condition_9_total }</td>
            </tr>
            
                 <tr>
              <td id="title10" class="type1">${conditions.condition_10 }&nbsp; </td>
              <td id="note10_1"  class="type2">${eformdata.condition_10_note1 }</td>
              <td id="note10_2" class="type3">${eformdata.condition_10_note2 }</td>
              <td id="note10_3" class="type4">${eformdata.condition_10_note3 }</td>
               <td id="note10_4" class="type5"> ${conditions.condition_target10 }</td>
        
                       <td  id="note10_61"  class="type6">${eformdata.month1_10 }</td>
                       <td id="note10_62" class="type6">${eformdata.month2_10 }</td>
                   <td  id="note10_63" class="type6">${eformdata.month3_10 }</td>
                     <td id="note10_64" class="type6">${eformdata.month4_10 }</td>
                  <td id="note10_65" class="type6">${eformdata.month5_10}</td>
                    <td id="note10_66" class="type6">${eformdata.month6_10 }</td>
                    <td id="note10_67" class="type6">${eformdata.month7_10 }</td>
                    <td id="note10_68" class="type6">${eformdata.month8_10 }</td>
                    <td id="note10_69" class="type6">${eformdata.month9_10 }</td>
                     <td id="note10_610" class="type6">${eformdata.month10_10 }</td>
                      <td id="note10_611" class="type6">${eformdata.month11_10 }</td>
                      <td id="note10_612" class="type6">${eformdata.month12_10 }</td>
                      
                <td id="note10_5" class="type7">${eformdata.condition_10_total }</td>
            </tr>
            
                 <tr>
              <td id="title11" class="type1">${conditions.condition_11 }&nbsp; </td>
              <td id="note11_1"  class="type2">${eformdata.condition_11_note1 }</td>
              <td id="note11_2" class="type3">${eformdata.condition_11_note2 }</td>
              <td id="note11_3" class="type4">${eformdata.condition_11_note3 }</td>
               <td id="note11_4" class="type5"> ${conditions.condition_target11 }</td>
        
                       <td  id="note11_61"  class="type6">${eformdata.month1_11 }</td>
                       <td id="note11_62" class="type6">${eformdata.month2_11 }</td>
                   <td  id="note11_63" class="type6">${eformdata.month3_11 }</td>
                     <td id="note11_64" class="type6">${eformdata.month4_11 }</td>
                  <td id="note11_65" class="type6">${eformdata.month5_11}</td>
                    <td id="note11_66" class="type6">${eformdata.month6_11 }</td>
                    <td id="note11_67" class="type6">${eformdata.month7_11 }</td>
                    <td id="note11_68" class="type6">${eformdata.month8_11 }</td>
                    <td id="note11_69" class="type6">${eformdata.month9_11 }</td>
                     <td id="note11_610" class="type6">${eformdata.month10_11 }</td>
                      <td id="note11_611" class="type6">${eformdata.month11_11 }</td>
                      <td id="note11_612" class="type6">${eformdata.month12_11 }</td>
                      
                <td id="note11_5" class="type7">${eformdata.condition_11_total }</td>
            </tr>
            
                 <tr>
              <td id="title12" class="type1">${conditions.condition_12 }&nbsp; </td>
              <td id="note12_1"  class="type2">${eformdata.condition_12_note1 }</td>
              <td id="note12_2" class="type3">${eformdata.condition_12_note2 }</td>
              <td id="note12_3" class="type4">${eformdata.condition_12_note3 }</td>
               <td id="note12_4" class="type5"> ${conditions.condition_target12 }</td>
          
                       <td  id="note12_61"  class="type6">${eformdata.month1_12 }</td>
                       <td id="note12_62" class="type6">${eformdata.month2_12 }</td>
                   <td  id="note12_63" class="type6">${eformdata.month3_12 }</td>
                     <td id="note12_64" class="type6">${eformdata.month4_12 }</td>
                  <td id="note12_65" class="type6">${eformdata.month5_12}</td>
                    <td id="note12_66" class="type6">${eformdata.month6_12 }</td>
                    <td id="note12_67" class="type6">${eformdata.month7_12 }</td>
                    <td id="note12_68" class="type6">${eformdata.month8_12 }</td>
                    <td id="note12_69" class="type6">${eformdata.month9_12 }</td>
                     <td id="note12_610" class="type6">${eformdata.month10_12 }</td>
                      <td id="note12_611" class="type6">${eformdata.month11_12 }</td>
                      <td id="note12_612" class="type6">${eformdata.month12_12 }</td>
                      
                <td id="note12_5" class="type7">${eformdata.condition_12_total }</td>
            </tr>
            
                 <tr>
              <td id="title13" class="type1">${conditions.condition_13 }&nbsp; </td>
              <td id="note13_1"  class="type2">${eformdata.condition_13_note1 }</td>
              <td id="note13_2" class="type3">${eformdata.condition_13_note2 }</td>
              <td id="note13_3" class="type4">${eformdata.condition_13_note3 }</td>
               <td id="note13_4" class="type5"> ${conditions.condition_target13 }</td>
        
                       <td  id="note13_61"  class="type6">${eformdata.month1_13 }</td>
                       <td id="note13_62" class="type6">${eformdata.month2_13 }</td>
                   <td  id="note13_63" class="type6">${eformdata.month3_13 }</td>
                     <td id="note13_64" class="type6">${eformdata.month4_13 }</td>
                  <td id="note13_65" class="type6">${eformdata.month5_13}</td>
                    <td id="note13_66" class="type6">${eformdata.month6_13 }</td>
                    <td id="note13_67" class="type6">${eformdata.month7_13 }</td>
                    <td id="note13_68" class="type6">${eformdata.month8_13}</td>
                    <td id="note13_69" class="type6">${eformdata.month9_13 }</td>
                     <td id="note13_610" class="type6">${eformdata.month10_13 }</td>
                      <td id="note13_611" class="type6">${eformdata.month11_13 }</td>
                      <td id="note13_612" class="type6">${eformdata.month12_13 }</td>
                      
                <td id="note13_5" class="type7">${eformdata.condition_13_total }</td>
            </tr>
            
                 <tr>
              <td id="title14" class="type1">${conditions.condition_14 }&nbsp; </td>
              <td id="note14_1"  class="type2">${eformdata.condition_14_note1 }</td>
              <td id="note14_2" class="type3">${eformdata.condition_14_note2 }</td>
              <td id="note14_3" class="type4">${eformdata.condition_14_note3 }</td>
               <td id="note14_4" class="type5"> ${conditions.condition_target14 }</td>
                 
                       <td  id="note14_61"  class="type6">${eformdata.month1_14 }</td>
                       <td id="note14_62" class="type6">${eformdata.month2_14 }</td>
                   <td  id="note14_63" class="type6">${eformdata.month3_14 }</td>
                     <td id="note14_64" class="type6">${eformdata.month4_14 }</td>
                  <td id="note14_65" class="type6">${eformdata.month5_14}</td>
                    <td id="note14_66" class="type6">${eformdata.month6_14 }</td>
                    <td id="note14_67" class="type6">${eformdata.month7_14 }</td>
                    <td id="note14_68" class="type6">${eformdata.month8_14 }</td>
                    <td id="note14_69" class="type6">${eformdata.month9_14 }</td>
                     <td id="note14_610" class="type6">${eformdata.month10_14 }</td>
                      <td id="note14_611" class="type6">${eformdata.month11_14 }</td>
                      <td id="note14_612" class="type6">${eformdata.month12_14 }</td>
                      
                <td id="note14_5" class="type7">${eformdata.condition_14_total }</td>
            </tr>
            
                 <tr>
              <td id="title15" class="type1" >${conditions.condition_15 }&nbsp; </td>
              <td id="note15_1"  class="type2">${eformdata.condition_15_note1 }</td>
              <td id="note15_2"  class="type3">${eformdata.condition_15_note2 }</td>
              <td id="note15_3" class="type4">${eformdata.condition_15_note3 }</td>
               <td id="note15_4" class="type5"> ${conditions.condition_target15 }</td>
        
                       <td  id="note15_61"  class="type6">${eformdata.month1_15 }</td>
                       <td id="note15_62" class="type6">${eformdata.month2_15 }</td>
                   <td  id="note15_63" class="type6">${eformdata.month3_15 }</td>
                     <td id="note15_64" class="type6">${eformdata.month4_15 }</td>
                  <td id="note15_65" class="type6">${eformdata.month5_15}</td>
                    <td id="note15_66" class="type6">${eformdata.month6_15 }</td>
                    <td id="note15_67" class="type6">${eformdata.month7_15 }</td>
                    <td id="note15_68" class="type6">${eformdata.month8_15}</td>
                    <td id="note15_69" class="type6">${eformdata.month9_15 }</td>
                     <td id="note15_610" class="type6">${eformdata.month10_15 }</td>
                      <td id="note15_611" class="type6">${eformdata.month11_15 }</td>
                      <td id="note15_612" class="type6">${eformdata.month12_15 }</td>
                      
                <td id="note15_5" class="type7">${eformdata.condition_15_total }</td>
            </tr>
       
          </tbody>
        </table>
        
      
        <br><br>
        

               
            
        <h2>Action plan   <button type="reset" onclick="view()" style="float:right" class="btn btn-primary me-1 waves-effect waves-float waves-light">Add</button></h2>
        
      
        
        
        
             <div class="row" id="view" style="display:none">
                        <div class="col-12">
                        
    
                                <div class="card-body">
                                    <form class="form">
                                        <div class="row">
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">ปัญหา</label>
                                                    <textarea id="first-name-column" class="form-control" name="fname-column"></textarea>
                                                </div>
                                            </div>
                                        
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="city-column">สาเหตุ</label>
                                                      <textarea id="first-name-column" class="form-control" name="fname-column"></textarea>
                                                </div>
                                            </div>
                                               <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="company-column">เเนวทางเเก้ไข</label>
                                                       <textarea id="first-name-column" class="form-control"name="fname-column"></textarea>
                                                </div>
                                            </div>
                                                <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="last-name-column">ผู้รับผิดชอบ</label>
                                                    <input type="text" id="last-name-column" class="form-control"  name="lname-column">
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="country-floating">ผู้ตรวจสอบ</label>
                                                    <input type="text" id="country-floating" class="form-control"  >
                                                </div>
                                            </div>
                                            
                                            
                                         
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="email-id-column">	กำหนดเสร็จ</label>
                                                    <input type="date" id="email-id-column" class="form-control" name="email-id-column">
                                                </div>
                                            </div>
                                            
       
                                            
                                            <div class="col-12">
                                                <button type="reset" class="btn btn-primary me-1 waves-effect waves-float waves-light">Save</button>
                                                <button type="reset" class="btn btn-outline-secondary waves-effect" onclick="view()">ยกเลิก</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                          
                        </div>
                    </div>
        
        
        
        
 <table class="table table-bordered">   
          <thead>
            <tr >
                                            <th  style="font-size:14px;">
                                          ลำดับ
                                            </th>
                                                <th  style="font-size:14px;">วันที่สร้าง</th>
                                            <th style="font-size:14px;">ปัญหา</th>
                                            <th style="font-size:14px;">สาเหตุ</th>
                                            <th  style="font-size:14px;">เเนวทางเเก้ไข </th>
                                                <th   style="font-size:14px;">ผู้รับผิดชอบ</th>
                                               <th   style="font-size:14px;">ผู้ตรวจสอบ</th>
                                               <th  style="font-size:14px;">กำหนดเสร็จ</th>
                                                  <th   style="font-size:14px;">สถานะ</th>
                                                <th   style="font-size:14px;">ความคิดเห็นผู้ตรวจสอบ</th>
                                                <th   style="font-size:14px;">ดำเนินการ</th>
                                            
                                         
                                        </tr>
          </thead>
          
            
          
          
          <tbody>   
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

<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>

<style>
#chartdiv1 {
  width: 100%; 
  min-height: 450px;
}
#chartdiv2 {
  width: 100%; 
  min-height: 450px;
}
#chartdiv3 {
  width: 100%; 
  min-height: 450px;
}




</style>


<script>

function view(){
	$("#view").toggle();
}

function  sw(div){
	$("#chartdiv1").hide();
	$("#chartdiv2").hide();
	$("#chartdiv3").hide();

	$("#"+div).show();
}



function setting(){
	$("#setting").toggle();
	$("#chartdiv").toggle();
	
}


function show(type){
	
	$("."+type).toggle();
}

</script>





<script>
am5.ready(function() {


// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdiv2");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([am5themes_Animated.new(root)]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root.container.children.push(
  am5xy.XYChart.new(root, {
    panX: false,
    panY: false,
    wheelX: "panX",
    wheelY: "zoomX",
    layout: root.verticalLayout
  })
);



var label = "${chartdiv2label}"; 
var data = "${chartdiv2data}"; 



if(label == "type1"){
	var	labelid1 = "title1";
	var	labelid2 = "title2";
	var	labelid3 = "title3";
	var	labelid4 = "title4";
	var	labelid5 = "title5";
	var	labelid6 = "title6";
	var	labelid7 = "title7";
	var	labelid8 = "title8";
	var	labelid9 = "title9";
	var	labelid10 = "title10";
	var	labelid11 = "title11";
	var	labelid12 = "title12";
	var	labelid13 = "title13";
	var	labelid14 = "title14";
	var	labelid15 = "title15";


	
}
if(label == "type2"){
	var	labelid1 = "note1_1";
	var	labelid2 = "note2_1";
	var	labelid3= "note3_1";
	var	labelid4 = "note4_1";
	var	labelid5 = "note5_1";
	var	labelid6 = "note6_1";
	var	labelid7 = "note7_1";
	var	labelid8 = "note8_1";
	var	labelid9 = "note9_1";
	var	labelid10 = "note10_1";
	var	labelid11= "note11_1";
	var	labelid12 = "note12_1";
	var	labelid13 = "note13_1";
	var	labelid14= "note14_1";
	var	labelid15 = "note15_1";


	
}
if(label == "type3"){
	var	labelid1 = "note1_2";
	var	labelid2 = "note2_2";
	var	labelid3= "note3_2";
	var	labelid4 = "note4_2";
	var	labelid5 = "note5_2";
	var	labelid6 = "note6_2";
	var	labelid7 = "note7_2";
	var	labelid8 = "note8_2";
	var	labelid9 = "note9_2";
	var	labelid10 = "note10_2";
	var	labelid11= "note11_2";
	var	labelid12 = "note12_2";
	var	labelid13 = "note13_2";
	var	labelid14= "note14_2";
	var	labelid15 = "note15_2";

	
}
if(label == "type4"){
	var	labelid1 = "note1_3";
	var	labelid2 = "note2_3";
	var	labelid3= "note3_3";
	var	labelid4 = "note4_3";
	var	labelid5 = "note5_3";
	var	labelid6 = "note6_3";
	var	labelid7 = "note7_3";
	var	labelid8 = "note8_3";
	var	labelid9 = "note9_3";
	var	labelid10 = "note10_3";
	var	labelid11= "note11_3";
	var	labelid12 = "note12_3";
	var	labelid13 = "note13_3";
	var	labelid14= "note14_3";
	var	labelid15 = "note15_3";


	
}


if(data == "type2"){
	var	dataid1 = "note1_1";
	var	dataid2 = "note2_1";
	var	dataid3= "note3_1";
	var	dataid4 = "note4_1";
	var	dataid5 = "note5_1";
	var	dataid6 = "note6_1";
	var	dataid7 = "note7_1";
	var	dataid8 = "note8_1";
	var	dataid9 = "note9_1";
	var	dataid10 = "note10_1";
	var	dataid11= "note11_1";
	var	dataid12 = "note12_1";
	var	dataid13 = "note13_1";
	var	dataid14= "note14_1";
	var	dataid15 = "note15_1";
	var	title =  "${conditions.condition_type1 }";
	
}
if(data == "type3"){
	var	dataid1 = "note1_2";
	var	dataid2 = "note2_2";
	var	dataid3= "note3_2";
	var	dataid4 = "note4_2";
	var	dataid5 = "note5_2";
	var	dataid6 = "note6_2";
	var	dataid7 = "note7_2";
	var	dataid8 = "note8_2";
	var	dataid9 = "note9_2";
	var	dataid10 = "note10_2";
	var	dataid11= "note11_2";
	var	dataid12 = "note12_2";
	var	dataid13 = "note13_2";
	var	dataid14= "note14_2";
	var	dataid15 = "note15_2";
	var	title =  "${conditions.condition_type2 }";
	
}
if(data == "type4"){
	var	dataid1 = "note1_3";
	var	dataid2 = "note2_3";
	var	dataid3= "note3_3";
	var	dataid4 = "note4_3";
	var	dataid5 = "note5_3";
	var	dataid6 = "note6_3";
	var	dataid7 = "note7_3";
	var	dataid8 = "note8_3";
	var	dataid9 = "note9_3";
	var	dataid10 = "note10_3";
	var	dataid11= "note11_3";
	var	dataid12 = "note12_3";
	var	dataid13 = "note13_3";
	var	dataid14= "note14_3";
	var	dataid15 = "note15_3";
	var	title =  "${conditions.condition_type3 }";
	
}
if(data == "type5"){
	var	dataid1 = "note1_4";
	var	dataid2 = "note2_4";
	var	dataid3 = "note3_4";
	var	dataid4 = "note4_4";
	var	dataid5 = "note5_4";
	var	dataid6 = "note6_4";
	var	dataid7 = "note7_4";
	var	dataid8 = "note8_4";
	var	dataid9 = "note9_4";
	var	dataid10 = "note10_4";
	var	dataid11 = "note11_4";
	var	dataid12 = "note12_4";
	var	dataid13 = "note13_4";
	var	dataid14 = "note14_4";;
	var	dataid15 = "note15_4";

	
}
if(data == "type7"){
	var	dataid1 = "note1_5";
	var	dataid2 = "note2_5";
	var	dataid3 = "note3_5";
	var	dataid4 = "note4_5";
	var	dataid5 = "note5_5";
	var	dataid6 = "note6_5";
	var	dataid7 = "note7_5";
	var	dataid8 = "note8_5";
	var	dataid9 = "note9_5";
	var	dataid10 = "note10_5";
	var	dataid11 = "note11_5";
	var	dataid12 = "note12_5";
	var	dataid13 = "note13_5";
	var	dataid14 = "note14_5";
	var	dataid15 = "note15_5";
	var	title =  "${conditions.condition_result }";
	
}






// Add scrollbar
// https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/
chart.set(
  "scrollbarX",
  am5.Scrollbar.new(root, {
    orientation: "horizontal"
  })
);





var data = [];


$("#"+dataid1).text() == '' ?  null: data.push({year: $("#"+labelid1).text(),income: parseInt($("#"+dataid1).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid2).text() == '' ?  null: data.push({year: $("#"+labelid2).text(),income: parseInt($("#"+dataid2).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid3).text() == '' ?  null: data.push({year: $("#"+labelid3).text(),income: parseInt($("#"+dataid3).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid4).text() == '' ?  null: data.push({year: $("#"+labelid4).text(),income: parseInt($("#"+dataid4).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid5).text() == '' ?  null: data.push({year: $("#"+labelid5).text(),income: parseInt($("#"+dataid5).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid6).text() == '' ?  null: data.push({year: $("#"+labelid6).text(),income: parseInt($("#"+dataid6).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid7).text() == '' ?  null: data.push({year: $("#"+labelid7).text(),income: parseInt($("#"+dataid7).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid8).text() == '' ?  null: data.push({year: $("#"+labelid8).text(),income: parseInt($("#"+dataid8).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid9).text() == '' ?  null: data.push({year: $("#"+labelid9).text(),income: parseInt($("#"+dataid9).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid10).text() == '' ?  null: data.push({year: $("#"+labelid10).text(),income: parseInt($("#"+dataid10).text()),expenses: parseInt($("#"+dataid1).text()) });				
$("#"+dataid11).text() == '' ?  null: data.push({year: $("#"+labelid11).text(),income: parseInt($("#"+dataid11).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid12).text() == '' ?  null: data.push({year: $("#"+labelid12).text(),income: parseInt($("#"+dataid12).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid13).text() == '' ?  null: data.push({year: $("#"+labelid13).text(),income: parseInt($("#"+dataid13).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid14).text() == '' ?  null: data.push({year: $("#"+labelid14).text(),income: parseInt($("#"+dataid14).text()),expenses: parseInt($("#"+dataid1).text()) });
$("#"+dataid15).text() == '' ?  null: data.push({year: $("#"+labelid15).text(),income: parseInt($("#"+dataid15).text()),expenses: parseInt($("#"+dataid1).text()) });



// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xRenderer = am5xy.AxisRendererX.new(root, {});
var xAxis = chart.xAxes.push(
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

var yAxis = chart.yAxes.push(
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

var series1 = chart.series.push(
  am5xy.ColumnSeries.new(root, {
    name: title,
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

var series2 = chart.series.push(
  am5xy.LineSeries.new(root, {
    name: "${conditions.condition_target }",
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: "expenses",
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
var legend = chart.children.push(
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


}); // end am5.ready()
</script>





<script>


<c:if test='${!fn:contains(list,"type1")}'>
$(".type1").toggle();
</c:if>
<c:if test='${!fn:contains(list,"type2")}'>
$(".type2").toggle();
</c:if>
<c:if test='${!fn:contains(list,"type3")}'>
$(".type3").toggle();
</c:if>
<c:if test='${!fn:contains(list,"type4")}'>
$(".type4").toggle();
</c:if>
<c:if test='${!fn:contains(list,"type5")}'>
$(".type5").toggle();
</c:if>
<c:if test='${!fn:contains(list,"type6")}'>
$(".type6").toggle();
</c:if>
<c:if test='${!fn:contains(list,"type7")}'>
$(".type7").toggle();
</c:if>




am5.ready(function() {

	var label = "${chartdiv1label}"; 
	var data = "${chartdiv1data}"; 
	
	if(label == "type1"){
		var	labelid1 = "title1";
		var	labelid2 = "title2";
		var	labelid3 = "title3";
		var	labelid4 = "title4";
		var	labelid5 = "title5";
		var	labelid6 = "title6";
		var	labelid7 = "title7";
		var	labelid8 = "title8";
		var	labelid9 = "title9";
		var	labelid10 = "title10";
		var	labelid11 = "title11";
		var	labelid12 = "title12";
		var	labelid13 = "title13";
		var	labelid14 = "title14";
		var	labelid15 = "title15";

		
	}
	if(label == "type2"){
		var	labelid1 = "note1_1";
		var	labelid2 = "note2_1";
		var	labelid3= "note3_1";
		var	labelid4 = "note4_1";
		var	labelid5 = "note5_1";
		var	labelid6 = "note6_1";
		var	labelid7 = "note7_1";
		var	labelid8 = "note8_1";
		var	labelid9 = "note9_1";
		var	labelid10 = "note10_1";
		var	labelid11= "note11_1";
		var	labelid12 = "note12_1";
		var	labelid13 = "note13_1";
		var	labelid14= "note14_1";
		var	labelid15 = "note15_1";


		
	}
	if(label == "type3"){
		var	labelid1 = "note1_2";
		var	labelid2 = "note2_2";
		var	labelid3= "note3_2";
		var	labelid4 = "note4_2";
		var	labelid5 = "note5_2";
		var	labelid6 = "note6_2";
		var	labelid7 = "note7_2";
		var	labelid8 = "note8_2";
		var	labelid9 = "note9_2";
		var	labelid10 = "note10_2";
		var	labelid11= "note11_2";
		var	labelid12 = "note12_2";
		var	labelid13 = "note13_2";
		var	labelid14= "note14_2";
		var	labelid15 = "note15_2";

		
	}
	if(label == "type4"){
		var	labelid1 = "note1_3";
		var	labelid2 = "note2_3";
		var	labelid3= "note3_3";
		var	labelid4 = "note4_3";
		var	labelid5 = "note5_3";
		var	labelid6 = "note6_3";
		var	labelid7 = "note7_3";
		var	labelid8 = "note8_3";
		var	labelid9 = "note9_3";
		var	labelid10 = "note10_3";
		var	labelid11= "note11_3";
		var	labelid12 = "note12_3";
		var	labelid13 = "note13_3";
		var	labelid14= "note14_3";
		var	labelid15 = "note15_3";


		
	}
	

	if(data == "type2"){
		var	dataid1 = "note1_1";
		var	dataid2 = "note2_1";
		var	dataid3= "note3_1";
		var	dataid4 = "note4_1";
		var	dataid5 = "note5_1";
		var	dataid6 = "note6_1";
		var	dataid7 = "note7_1";
		var	dataid8 = "note8_1";
		var	dataid9 = "note9_1";
		var	dataid10 = "note10_1";
		var	dataid11= "note11_1";
		var	dataid12 = "note12_1";
		var	dataid13 = "note13_1";
		var	dataid14= "note14_1";
		var	dataid15 = "note15_1";

		
	}
	if(data == "type3"){
		var	dataid1 = "note1_2";
		var	dataid2 = "note2_2";
		var	dataid3= "note3_2";
		var	dataid4 = "note4_2";
		var	dataid5 = "note5_2";
		var	dataid6 = "note6_2";
		var	dataid7 = "note7_2";
		var	dataid8 = "note8_2";
		var	dataid9 = "note9_2";
		var	dataid10 = "note10_2";
		var	dataid11= "note11_2";
		var	dataid12 = "note12_2";
		var	dataid13 = "note13_2";
		var	dataid14= "note14_2";
		var	dataid15 = "note15_2";

		
	}
	if(data == "type4"){
		var	dataid1 = "note1_3";
		var	dataid2 = "note2_3";
		var	dataid3= "note3_3";
		var	dataid4 = "note4_3";
		var	dataid5 = "note5_3";
		var	dataid6 = "note6_3";
		var	dataid7 = "note7_3";
		var	dataid8 = "note8_3";
		var	dataid9 = "note9_3";
		var	dataid10 = "note10_3";
		var	dataid11= "note11_3";
		var	dataid12 = "note12_3";
		var	dataid13 = "note13_3";
		var	dataid14= "note14_3";
		var	dataid15 = "note15_3";

		
	}
	if(data == "type5"){
		var	dataid1 = "note1_4";
		var	dataid2 = "note2_4";
		var	dataid3 = "note3_4";
		var	dataid4 = "note4_4";
		var	dataid5 = "note5_4";
		var	dataid6 = "note6_4";
		var	dataid7 = "note7_4";
		var	dataid8 = "note8_4";
		var	dataid9 = "note9_4";
		var	dataid10 = "note10_4";
		var	dataid11 = "note11_4";
		var	dataid12 = "note12_4";
		var	dataid13 = "note13_4";
		var	dataid14 = "note14_4";;
		var	dataid15 = "note15_4";

		
	}
	if(data == "type7"){
		var	dataid1 = "note1_5";
		var	dataid2 = "note2_5";
		var	dataid3 = "note3_5";
		var	dataid4 = "note4_5";
		var	dataid5 = "note5_5";
		var	dataid6 = "note6_5";
		var	dataid7 = "note7_5";
		var	dataid8 = "note8_5";
		var	dataid9 = "note9_5";
		var	dataid10 = "note10_5";
		var	dataid11 = "note11_5";
		var	dataid12 = "note12_5";
		var	dataid13 = "note13_5";
		var	dataid14 = "note14_5";
		var	dataid15 = "note15_5";

		
	}
	
// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdiv1");


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
  $("#"+dataid1).text() == '' ?  null: { value: parseInt($("#"+dataid1).text()), category: $("#"+labelid1).text() },
  $("#"+dataid2).text() == '' ?  null: { value: parseInt($("#"+dataid2).text()), category: $("#"+labelid2).text() },
  $("#"+dataid3).text() == '' ?  null: { value: parseInt($("#"+dataid3).text()), category: $("#"+labelid3).text() },
  $("#"+dataid4).text() == '' ?  null: { value: parseInt($("#"+dataid4).text()), category: $("#"+labelid4).text() },  
  $("#"+dataid5).text() == '' ?  null: { value: parseInt($("#"+dataid5).text()), category: $("#"+labelid5).text() },
  $("#"+dataid6).text() == '' ?  null: { value: parseInt($("#"+dataid6).text()), category: $("#"+labelid6).text() },
  $("#"+dataid7).text() == '' ?  null: { value: parseInt($("#"+dataid7).text()), category: $("#"+labelid7).text() },
  $("#"+dataid8).text() == '' ?  null: { value: parseInt($("#"+dataid8).text()), category: $("#"+labelid8).text() },
  $("#"+dataid9).text() == '' ?  null: { value: parseInt($("#"+dataid9).text()), category: $("#"+labelid9).text() },  
  $("#"+dataid10).text() == '' ?  null: { value: parseInt($("#"+dataid10).text()), category: $("#"+labelid10).text() },
   $("#"+dataid11).text() == '' ?  null: { value: parseInt($("#"+dataid11).text()), category: $("#"+labelid11).text() }, 
  $("#"+dataid12).text() == '' ?  null: { value: parseInt($("#"+dataid12).text()), category: $("#"+labelid12).text() },
  $("#"+dataid13).text() == '' ?  null: { value: parseInt($("#"+dataid13).text()), category: $("#"+labelid13).text() },
  $("#"+dataid14).text() == '' ?  null: { value: parseInt($("#"+dataid14).text()), category: $("#"+labelid14).text() },  
  $("#"+dataid15).text() == '' ?  null: { value: parseInt($("#"+dataid15).text()), category: $("#"+labelid15).text() },
								    

]);


// Play initial series animation
// https://www.amcharts.com/docs/v5/concepts/animations/#Animation_of_series
series.appear(1000, 100);

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
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root.container.children.push(
  am5xy.XYChart.new(root, {


    layout: root.verticalLayout,
  pinchZoomX:false
  })
);

// Add cursor
// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {
  behavior: "none"
}));
cursor.lineY.set("visible", false);


var label = "${chartdiv1label}"; 
var data = "${chartdiv1data}"; 

if(label == "type1"){
	var	labelid1 = "title1";
	var	labelid2 = "title2";
	var	labelid3 = "title3";
	var	labelid4 = "title4";
	var	labelid5 = "title5";
	var	labelid6 = "title6";
	var	labelid7 = "title7";
	var	labelid8 = "title8";
	var	labelid9 = "title9";
	var	labelid10 = "title10";
	var	labelid11 = "title11";
	var	labelid12 = "title12";
	var	labelid13 = "title13";
	var	labelid14 = "title14";
	var	labelid15 = "title15";

	
}
if(label == "type2"){
	var	labelid1 = "note1_1";
	var	labelid2 = "note2_1";
	var	labelid3= "note3_1";
	var	labelid4 = "note4_1";
	var	labelid5 = "note5_1";
	var	labelid6 = "note6_1";
	var	labelid7 = "note7_1";
	var	labelid8 = "note8_1";
	var	labelid9 = "note9_1";
	var	labelid10 = "note10_1";
	var	labelid11= "note11_1";
	var	labelid12 = "note12_1";
	var	labelid13 = "note13_1";
	var	labelid14= "note14_1";
	var	labelid15 = "note15_1";


	
}
if(label == "type3"){
	var	labelid1 = "note1_2";
	var	labelid2 = "note2_2";
	var	labelid3= "note3_2";
	var	labelid4 = "note4_2";
	var	labelid5 = "note5_2";
	var	labelid6 = "note6_2";
	var	labelid7 = "note7_2";
	var	labelid8 = "note8_2";
	var	labelid9 = "note9_2";
	var	labelid10 = "note10_2";
	var	labelid11= "note11_2";
	var	labelid12 = "note12_2";
	var	labelid13 = "note13_2";
	var	labelid14= "note14_2";
	var	labelid15 = "note15_2";

	
}
if(label == "type4"){
	var	labelid1 = "note1_3";
	var	labelid2 = "note2_3";
	var	labelid3= "note3_3";
	var	labelid4 = "note4_3";
	var	labelid5 = "note5_3";
	var	labelid6 = "note6_3";
	var	labelid7 = "note7_3";
	var	labelid8 = "note8_3";
	var	labelid9 = "note9_3";
	var	labelid10 = "note10_3";
	var	labelid11= "note11_3";
	var	labelid12 = "note12_3";
	var	labelid13 = "note13_3";
	var	labelid14= "note14_3";
	var	labelid15 = "note15_3";


	
}


if(data == "type2"){
	var	dataid1 = "note1_1";
	var	dataid2 = "note2_1";
	var	dataid3= "note3_1";
	var	dataid4 = "note4_1";
	var	dataid5 = "note5_1";
	var	dataid6 = "note6_1";
	var	dataid7 = "note7_1";
	var	dataid8 = "note8_1";
	var	dataid9 = "note9_1";
	var	dataid10 = "note10_1";
	var	dataid11= "note11_1";
	var	dataid12 = "note12_1";
	var	dataid13 = "note13_1";
	var	dataid14= "note14_1";
	var	dataid15 = "note15_1";

	
}
if(data == "type3"){
	var	dataid1 = "note1_2";
	var	dataid2 = "note2_2";
	var	dataid3= "note3_2";
	var	dataid4 = "note4_2";
	var	dataid5 = "note5_2";
	var	dataid6 = "note6_2";
	var	dataid7 = "note7_2";
	var	dataid8 = "note8_2";
	var	dataid9 = "note9_2";
	var	dataid10 = "note10_2";
	var	dataid11= "note11_2";
	var	dataid12 = "note12_2";
	var	dataid13 = "note13_2";
	var	dataid14= "note14_2";
	var	dataid15 = "note15_2";

	
}
if(data == "type4"){
	var	dataid1 = "note1_3";
	var	dataid2 = "note2_3";
	var	dataid3= "note3_3";
	var	dataid4 = "note4_3";
	var	dataid5 = "note5_3";
	var	dataid6 = "note6_3";
	var	dataid7 = "note7_3";
	var	dataid8 = "note8_3";
	var	dataid9 = "note9_3";
	var	dataid10 = "note10_3";
	var	dataid11= "note11_3";
	var	dataid12 = "note12_3";
	var	dataid13 = "note13_3";
	var	dataid14= "note14_3";
	var	dataid15 = "note15_3";

	
}
if(data == "type5"){
	var	dataid1 = "note1_4";
	var	dataid2 = "note2_4";
	var	dataid3 = "note3_4";
	var	dataid4 = "note4_4";
	var	dataid5 = "note5_4";
	var	dataid6 = "note6_4";
	var	dataid7 = "note7_4";
	var	dataid8 = "note8_4";
	var	dataid9 = "note9_4";
	var	dataid10 = "note10_4";
	var	dataid11 = "note11_4";
	var	dataid12 = "note12_4";
	var	dataid13 = "note13_4";
	var	dataid14 = "note14_4";;
	var	dataid15 = "note15_4";

	
}
if(data == "type7"){
	var	dataid1 = "note1_5";
	var	dataid2 = "note2_5";
	var	dataid3 = "note3_5";
	var	dataid4 = "note4_5";
	var	dataid5 = "note5_5";
	var	dataid6 = "note6_5";
	var	dataid7 = "note7_5";
	var	dataid8 = "note8_5";
	var	dataid9 = "note9_5";
	var	dataid10 = "note10_5";
	var	dataid11 = "note11_5";
	var	dataid12 = "note12_5";
	var	dataid13 = "note13_5";
	var	dataid14 = "note14_5";
	var	dataid15 = "note15_5";

	
}



// The data
var data = [
	  {
		    year: "01",
		    N01: $("#note1_61").text() == '' ? 0 : parseInt($("#note1_61").text()),
		    N02: $("#note2_61").text() == '' ? 0 :parseInt($("#note2_61").text()),
		    N03: $("#note3_61").text() == '' ? 0 :parseInt($("#note3_61").text()),
		    N04: $("#note4_61").text() == '' ? 0 :parseInt($("#note4_61").text()),
		    N05: $("#note5_61").text() == '' ? 0 :parseInt($("#note5_61").text()),
		    N06: $("#note6_61").text() == '' ? 0 :parseInt($("#note6_61").text()),
		    N07: $("#note7_61").text() == '' ? 0 :parseInt($("#note7_61").text()),
		    N08: $("#note8_61").text() == '' ? 0 :parseInt($("#note8_61").text()),
		    N09: $("#note9_61").text() == '' ? 0 :parseInt($("#note9_61").text()),
		    N010:$("#note10_61").text() == '' ? 0 :parseInt($("#note10_61").text()),
		    N011:$("#note11_61").text() == '' ? 0 : parseInt($("#note11_61").text()),
		    N012:$("#note12_61").text() == '' ? 0 : parseInt($("#note12_61").text()),
		    N013:$("#note13_61").text() == '' ? 0 :parseInt($("#note13_61").text()),
		    N014:$("#note14_61").text() == '' ? 0 : parseInt($("#note14_61").text()),
		    N015:$("#note15_61").text() == '' ? 0 : parseInt($("#note15_61").text()),
		    target:parseInt(${conditions.condition_target1 }),		
		    		
		    		
},
  {
    year: "02",
    N01: $("#note1_62").text() == '' ? 0 :parseInt($("#note1_62").text()),
    N02: $("#note2_62").text() == '' ? 0 :parseInt($("#note2_62").text()),
    N03: $("#note3_62").text() == '' ? 0 :parseInt($("#note3_62").text()),
    N04: $("#note4_62").text() == '' ? 0 :parseInt($("#note4_62").text()),
    N05: $("#note5_62").text() == '' ? 0 :parseInt($("#note5_62").text()),
    N06: $("#note6_62").text() == '' ? 0 :parseInt($("#note6_62").text()),
    N07: $("#note7_62").text() == '' ? 0 :parseInt($("#note7_62").text()),
    N08: $("#note8_62").text() == '' ? 0 :parseInt($("#note8_62").text()),
    N09: $("#note9_62").text() == '' ? 0 :parseInt($("#note9_62").text()),
    N010:$("#note10_62").text() == '' ? 0 :parseInt($("#note10_62").text()),
    N011: $("#note11_62").text() == '' ? 0 :parseInt($("#note11_62").text()),
    N012: $("#note12_62").text() == '' ? 0 :parseInt($("#note12_62").text()),
    N013:$("#note13_62").text() == '' ? 0 :parseInt($("#note13_62").text()),
    N014: $("#note14_62").text() == '' ? 0 :parseInt($("#note14_62").text()),
    N015: $("#note15_62").text() == '' ? 0 :parseInt($("#note15_62").text()),
    		  target:parseInt(${conditions.condition_target1 }),			
  },
  {
    year: "03",
    N01: $("#note1_63").text() == '' ? 0 :parseInt($("#note1_63").text()),
    N02: $("#note2_63").text() == '' ? 0 :parseInt($("#note2_63").text()),
    N03: $("#note3_63").text() == '' ? 0 :parseInt($("#note3_63").text()),
    N04:$("#note4_63").text() == '' ? 0 : parseInt($("#note4_63").text()),
    N05:$("#note5_63").text() == '' ? 0 : parseInt($("#note5_63").text()),
    N06: $("#note6_63").text() == '' ? 0 :parseInt($("#note6_63").text()),
    N07: $("#note7_63").text() == '' ? 0 :parseInt($("#note7_63").text()),
    N08: $("#note8_63").text() == '' ? 0 :parseInt($("#note8_63").text()),
    N09: $("#note9_63").text() == '' ? 0 :parseInt($("#note9_63").text()),
    N010:$("#note10_63").text() == '' ? 0 :parseInt($("#note10_63").text()),
    N011: $("#note11_63").text() == '' ? 0 :parseInt($("#note11_63").text()),
    N012: $("#note12_63").text() == '' ? 0 :parseInt($("#note12_63").text()),
    N013:$("#note13_63").text() == '' ? 0 :parseInt($("#note13_63").text()),
    N014: $("#note14_63").text() == '' ? 0 :parseInt($("#note14_63").text()),
    N015:$("#note15_63").text() == '' ? 0 : parseInt($("#note15_63").text()),
    		  target:parseInt(${conditions.condition_target1 }),		
  },
  {
    year: "04",
    N01: $("#note1_64").text() == '' ? 0 :parseInt($("#note1_64").text()),
    N02:$("#note2_64").text() == '' ? 0 : parseInt($("#note2_64").text()),
    N03:$("#note3_64").text() == '' ? 0 : parseInt($("#note3_64").text()),
    N04:$("#note4_64").text() == '' ? 0 : parseInt($("#note4_64").text()),
    N05:$("#note5_64").text() == '' ? 0 : parseInt($("#note5_64").text()),
    N06:$("#note6_64").text() == '' ? 0 : parseInt($("#note6_64").text()),
    N07:$("#note7_64").text() == '' ? 0 : parseInt($("#note7_64").text()),
    N08:$("#note8_64").text() == '' ? 0 : parseInt($("#note8_64").text()),
    N09:$("#note9_64").text() == '' ? 0 : parseInt($("#note9_64").text()),
    N010:$("#note10_64").text() == '' ? 0 :parseInt($("#note10_64").text()),
    N011: $("#note11_64").text() == '' ? 0 :parseInt($("#note11_64").text()),
    N012:$("#note12_64").text() == '' ? 0 : parseInt($("#note12_64").text()),
    N013:$("#note13_64").text() == '' ? 0 :parseInt($("#note13_64").text()),
    N014: $("#note14_64").text() == '' ? 0 :parseInt($("#note14_64").text()),
    N015: $("#note15_64").text() == '' ? 0 :parseInt($("#note15_64").text()),
    		  target:parseInt(${conditions.condition_target1 }),		
  },
  {
    year: "05",
    N01: $("#note1_65").text() == '' ? 0 :parseInt($("#note1_65").text()),
    N02: $("#note2_65").text() == '' ? 0 :parseInt($("#note2_65").text()),
    N03: $("#note3_65").text() == '' ? 0 :parseInt($("#note3_65").text()),
    N04: $("#note4_65").text() == '' ? 0 :parseInt($("#note4_65").text()),
    N05: $("#note5_65").text() == '' ? 0 :parseInt($("#note5_65").text()),
    N06: $("#note6_65").text() == '' ? 0 :parseInt($("#note6_65").text()),
    N07:$("#note7_65").text() == '' ? 0 : parseInt($("#note7_65").text()),
    N08: $("#note8_65").text() == '' ? 0 :parseInt($("#note8_65").text()),
    N09: $("#note9_65").text() == '' ? 0 :parseInt($("#note9_65").text()),
    N010:$("#note10_65").text() == '' ? 0 :parseInt($("#note10_65").text()),
    N011: $("#note11_65").text() == '' ? 0 :parseInt($("#note11_65").text()),
    N012:$("#note12_65").text() == '' ? 0 : parseInt($("#note12_65").text()),
    N013:$("#note13_65").text() == '' ? 0 :parseInt($("#note13_65").text()),
    N014: $("#note14_65").text() == '' ? 0 :parseInt($("#note14_65").text()),
    N015:$("#note15_65").text() == '' ? 0 : parseInt($("#note15_65").text()),
    		  target:parseInt(${conditions.condition_target1 }),		
  },
  {
    year: "06",
    N01: $("#note1_66").text() == '' ? 0 :parseInt($("#note1_66").text()),
    N02: $("#note2_66").text() == '' ? 0 :parseInt($("#note2_66").text()),
    N03: $("#note3_66").text() == '' ? 0 :parseInt($("#note3_66").text()),
    N04: $("#note4_66").text() == '' ? 0 :parseInt($("#note4_66").text()),
    N05: $("#note5_66").text() == '' ? 0 :parseInt($("#note5_66").text()),
    N06: $("#note6_66").text() == '' ? 0 :parseInt($("#note6_66").text()),
    N07: $("#note7_66").text() == '' ? 0 :parseInt($("#note7_66").text()),
    N08: $("#note8_66").text() == '' ? 0 :parseInt($("#note8_66").text()),
    N09: $("#note9_66").text() == '' ? 0 :parseInt($("#note9_66").text()),
    N010:$("#note10_66").text() == '' ? 0 :parseInt($("#note10_66").text()),
    N011: $("#note11_66").text() == '' ? 0 :parseInt($("#note11_66").text()),
    N012: $("#note12_66").text() == '' ? 0 :parseInt($("#note12_66").text()),
    N013:$("#note13_66").text() == '' ? 0 :parseInt($("#note13_66").text()),
    N014: $("#note14_66").text() == '' ? 0 :parseInt($("#note14_66").text()),
    N015:$("#note15_66").text() == '' ? 0 : parseInt($("#note15_66").text()),
    		  target:parseInt(${conditions.condition_target1 }),		
  },
  {
    year: "07",
    N01: $("#note1_67").text() == '' ? 0 :parseInt($("#note1_67").text()),
    N02: $("#note2_67").text() == '' ? 0 :parseInt($("#note2_67").text()),
    N03:$("#note3_67").text() == '' ? 0 : parseInt($("#note3_67").text()),
    N04: $("#note4_67").text() == '' ? 0 :parseInt($("#note4_67").text()),
    N05: $("#note5_67").text() == '' ? 0 :parseInt($("#note5_67").text()),
    N06: $("#note6_67").text() == '' ? 0 :parseInt($("#note6_67").text()),
    N07: $("#note7_67").text() == '' ? 0 :parseInt($("#note7_67").text()),
    N08: $("#note8_67").text() == '' ? 0 :parseInt($("#note8_67").text()),
    N09: $("#note9_67").text() == '' ? 0 :parseInt($("#note9_67").text()),
    N010:$("#note10_67").text() == '' ? 0 :parseInt($("#note10_67").text()),
    N011: $("#note11_67").text() == '' ? 0 :parseInt($("#note11_67").text()),
    N012: $("#note12_67").text() == '' ? 0 :parseInt($("#note12_67").text()),
    N013:$("#note13_67").text() == '' ? 0 :parseInt($("#note13_67").text()),
    N014:$("#note14_67").text() == '' ? 0 : parseInt($("#note14_67").text()),
    N015:$("#note15_67").text() == '' ? 0 : parseInt($("#note15_67").text()),
    		  target:parseInt(${conditions.condition_target1 }),		
  },
  {
    year: "08",
    N01:$("#note1_68").text() == '' ? 0 : parseInt($("#note1_68").text()),
    N02: $("#note2_68").text() == '' ? 0 :parseInt($("#note2_68").text()),
    N03: $("#note3_68").text() == '' ? 0 :parseInt($("#note3_68").text()),
    N04: $("#note4_68").text() == '' ? 0 :parseInt($("#note4_68").text()),
    N05: $("#note5_68").text() == '' ? 0 :parseInt($("#note5_68").text()),
    N06: $("#note6_68").text() == '' ? 0 :parseInt($("#note6_68").text()),
    N07: $("#note7_68").text() == '' ? 0 :parseInt($("#note7_68").text()),
    N08:$("#note8_68").text() == '' ? 0 : parseInt($("#note8_68").text()),
    N09: $("#note9_68").text() == '' ? 0 :parseInt($("#note9_68").text()),
    N010:$("#note10_68").text() == '' ? 0 :parseInt($("#note10_68").text()),
    N011: $("#note11_68").text() == '' ? 0 :parseInt($("#note11_68").text()),
    N012: $("#note12_68").text() == '' ? 0 :parseInt($("#note12_68").text()),
    N013:$("#note13_68").text() == '' ? 0 :parseInt($("#note13_68").text()),
    N014: $("#note14_68").text() == '' ? 0 :parseInt($("#note14_68").text()),
    N015: $("#note15_68").text() == '' ? 0 :parseInt($("#note15_68").text()),
    		  target:parseInt(${conditions.condition_target1 }),		
  },
  {
    year: "09",
    N01: $("#note1_69").text() == '' ? 0 :parseInt($("#note1_69").text()),
    N02: $("#note2_69").text() == '' ? 0 :parseInt($("#note2_69").text()),
    N03: $("#note3_69").text() == '' ? 0 :parseInt($("#note3_69").text()),
    N04: $("#note4_69").text() == '' ? 0 :parseInt($("#note4_69").text()),
    N05: $("#note5_69").text() == '' ? 0 :parseInt($("#note5_69").text()),
    N06: $("#note6_69").text() == '' ? 0 :parseInt($("#note6_69").text()),
    N07: $("#note7_69").text() == '' ? 0 :parseInt($("#note7_69").text()),
    N08: $("#note8_69").text() == '' ? 0 :parseInt($("#note8_69").text()),
    N09: $("#note9_69").text() == '' ? 0 :parseInt($("#note9_69").text()),
    N010:$("#note10_69").text() == '' ? 0 :parseInt($("#note10_69").text()),
    N011: $("#note11_69").text() == '' ? 0 :parseInt($("#note11_69").text()),
    N012:$("#note12_69").text() == '' ? 0 : parseInt($("#note12_69").text()),
    N013:$("#note13_69").text() == '' ? 0 :parseInt($("#note13_69").text()),
    N014: $("#note14_69").text() == '' ? 0 :parseInt($("#note14_69").text()),
    N015:$("#note15_69").text() == '' ? 0 : parseInt($("#note15_69").text()),
    		  target:parseInt(${conditions.condition_target1 }),		
  },
  {
    year: "10",
    N01: $("#note1_610").text() == '' ? 0 :parseInt($("#note1_610").text()),
    N02: $("#note2_610").text() == '' ? 0 :parseInt($("#note2_610").text()),
    N03: $("#note3_610").text() == '' ? 0 :parseInt($("#note3_610").text()),
    N04: $("#note4_610").text() == '' ? 0 :parseInt($("#note4_610").text()),
    N05: $("#note5_610").text() == '' ? 0 :parseInt($("#note5_610").text()),
    N06: $("#note6_610").text() == '' ? 0 :parseInt($("#note6_610").text()),
    N07: $("#note7_610").text() == '' ? 0 :parseInt($("#note7_610").text()),
    N08: $("#note8_610").text() == '' ? 0 :parseInt($("#note8_610").text()),
    N09: $("#note9_610").text() == '' ? 0 :parseInt($("#note9_610").text()),
    N010:$("#note10_610").text() == '' ? 0 :parseInt($("#note10_610").text()),
    N011: $("#note11_610").text() == '' ? 0 :parseInt($("#note11_610").text()),
    N012: $("#note12_610").text() == '' ? 0 :parseInt($("#note12_610").text()),
    N013:$("#note13_610").text() == '' ? 0 :parseInt($("#note13_610").text()),
    N014:$("#note14_610").text() == '' ? 0 : parseInt($("#note14_610").text()),
    N015: $("#note15_610").text() == '' ? 0 :parseInt($("#note15_610").text()),
    		  target:parseInt(${conditions.condition_target1 }),		
  },
  {
    year: "11",
    N01:$("#note1_611").text() == '' ? 0 : parseInt($("#note1_611").text()),
    N02: $("#note2_611").text() == '' ? 0 :parseInt($("#note2_611").text()),
    N03: $("#note3_611").text() == '' ? 0 :parseInt($("#note3_611").text()),
    N04: $("#note4_611").text() == '' ? 0 :parseInt($("#note4_611").text()),
    N05:$("#note6_611").text() == '' ? 0 : parseInt($("#note5_611").text()),
    N06: $("#note7_611").text() == '' ? 0 :parseInt($("#note6_611").text()),
    N07: $("#note8_611").text() == '' ? 0 :parseInt($("#note7_611").text()),
    N08: $("#note9_611").text() == '' ? 0 :parseInt($("#note8_611").text()),
    N09: $("#note10_611").text() == '' ? 0 :parseInt($("#note9_611").text()),
    N010:$("#note11_611").text() == '' ? 0 :parseInt($("#note10_611").text()),
    N011:$("#note12_611").text() == '' ? 0 : parseInt($("#note11_611").text()),
    N012: $("#note12_611").text() == '' ? 0 :parseInt($("#note12_611").text()),
    N013:$("#note13_611").text() == '' ? 0 :parseInt($("#note13_611").text()),
    N014:$("#note14_611").text() == '' ? 0 : parseInt($("#note14_611").text()),
    N015:$("#note15_611").text() == '' ? 0 : parseInt($("#note15_611").text()),
    		  target:parseInt(${conditions.condition_target1 }),		
  }
  ,
  {
    year: "12",
    N01: $("#note1_612").text() == '' ? 0 :parseInt($("#note1_612").text()),
    N02:$("#note2_612").text() == '' ? 0 : parseInt($("#note2_612").text()),
    N03: $("#note3_612").text() == '' ? 0 :parseInt($("#note3_612").text()),
    N04:$("#note4_612").text() == '' ? 0 : parseInt($("#note4_612").text()),
    N05: $("#note5_612").text() == '' ? 0 :parseInt($("#note5_612").text()),
    N06:$("#note6_612").text() == '' ? 0 : parseInt($("#note6_612").text()),
    N07:$("#note7_612").text() == '' ? 0 : parseInt($("#note7_612").text()),
    N08: $("#note8_612").text() == '' ? 0 :parseInt($("#note8_612").text()),
    N09: $("#note9_612").text() == '' ? 0 :parseInt($("#note9_612").text()),
    N010:$("#note10_612").text() == '' ? 0 :parseInt($("#note10_612").text()),
    N011: $("#note11_612").text() == '' ? 0 :parseInt($("#note11_612").text()),
    N012:$("#note12_612").text() == '' ? 0 : parseInt($("#note12_612").text()),
    N013:$("#note13_612").text() == '' ? 0 :parseInt($("#note13_612").text()),
    N014: $("#note14_612").text() == '' ? 0 :parseInt($("#note14_612").text()),
    N015:$("#note15_612").text() == '' ? 0 : parseInt($("#note15_612").text()),
    		  target:parseInt(${conditions.condition_target1 }),		
  }
 
];

// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xRenderer = am5xy.AxisRendererX.new(root, {});
xRenderer.grid.template.set("location", 0.5);
xRenderer.labels.template.setAll({
  location: 0.5,
  multiLocation: 0.5
});

var xAxis = chart.xAxes.push(
  am5xy.CategoryAxis.new(root, {
    categoryField: "year",
    renderer: xRenderer,
    tooltip: am5.Tooltip.new(root, {})
  })
);



xAxis.data.setAll(data);

var yAxis = chart.yAxes.push(
  am5xy.ValueAxis.new(root, {
    maxPrecision: 0,
    renderer: am5xy.AxisRendererY.new(root, {
      inversed: false
    })
  })
);



// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/

function createSeries(name, field,target) {
	
	
	
	if(target != 1 ){
  var series = chart.series.push(
    am5xy.LineSeries.new(root, {
      name: name,
      xAxis: xAxis,
      yAxis: yAxis,
      valueYField: field,
      categoryXField: "year",
      tooltip: am5.Tooltip.new(root, {
        pointerOrientation: "horizontal",
        labelText: "[bold]{name}[/]\n{categoryX}: {valueY}"
      })
    })
  );

	   
			  series.bullets.push(function() {
				    return am5.Bullet.new(root, {
				      sprite: am5.Circle.new(root, {
				        radius: 5,
				        fill:  series.get("fill")
				      })
				    });
				  });
	
	   }else{
		   
		   var series = chart.series.push(
				    am5xy.LineSeries.new(root, {
				      name: name,
				      xAxis: xAxis,
				      yAxis: yAxis,
				      valueYField: field,
				      categoryXField: "year",
				      fill:  "red",
				      stroke: "red" ,
				      tooltip: am5.Tooltip.new(root, {
				        pointerOrientation: "horizontal",
				        labelText: "[bold]{name}[/]\n{categoryX}: {valueY}"
				      })
				    })
				  );

		   
	   }
  
  
  
  // create hover state for series and for mainContainer, so that when series is hovered,
  // the state would be passed down to the strokes which are in mainContainer.
  series.set("setStateOnChildren", true);
  series.states.create("hover", {});

  series.mainContainer.set("setStateOnChildren", true);
  series.mainContainer.states.create("hover", {});

  series.strokes.template.states.create("hover", {
    strokeWidth: 4
  });

  if(target == 1 ){
  series.strokes.template.setAll({
	    strokeWidth: 3,
	    strokeDasharray: [10,5]
	  });

  }
  
  series.data.setAll(data);
  
  
  

  
  
  
  
  series.appear(1000);
}


$("#"+dataid1).text() == '' ?  null:  createSeries($("#"+labelid1).text(), "N01");
$("#"+dataid2).text() == '' ?  null:  createSeries($("#"+labelid2).text(), "N02");
$("#"+dataid3).text() == '' ?  null:  createSeries($("#"+labelid3).text(), "N03");
$("#"+dataid4).text() == '' ?  null:  createSeries($("#"+labelid4).text(), "N04");
$("#"+dataid5).text() == '' ?  null:  createSeries($("#"+labelid5).text(), "N05");
$("#"+dataid6).text() == '' ?  null:  createSeries($("#"+labelid6).text(), "N06");
$("#"+dataid7).text() == '' ?  null:  createSeries($("#"+labelid7).text(), "N07");
$("#"+dataid8).text() == '' ?  null:  createSeries($("#"+labelid8).text(), "N08");
$("#"+dataid9).text() == '' ?  null:  createSeries($("#"+labelid9).text(), "N09");
$("#"+dataid10).text() == '' ?  null:  createSeries($("#"+labelid10).text(), "N10");
$("#"+dataid11).text() == '' ?  null:  createSeries($("#"+labelid11).text(), "N11");
$("#"+dataid12).text() == '' ?  null:  createSeries($("#"+labelid12).text(), "N12");
$("#"+dataid13).text() == '' ?  null:  createSeries($("#"+labelid13).text(), "N13");
$("#"+dataid14).text() == '' ?  null:  createSeries($("#"+labelid14).text(), "N14");
$("#"+dataid15).text() == '' ?  null:  createSeries($("#"+labelid15).text(), "N15");
createSeries("Target", "target",1);
// Add scrollbar
// https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/





var legend = chart.children.push(
  am5.Legend.new(root, {
    centerX: am5.p50,
    x: am5.p50
  })
);

// Make series change state when legend item is hovered
legend.itemContainers.template.states.create("hover", {});

legend.itemContainers.template.events.on("pointerover", function(e) {
  e.target.dataItem.dataContext.hover();
});
legend.itemContainers.template.events.on("pointerout", function(e) {
  e.target.dataItem.dataContext.unhover();
});



legend.data.setAll(chart.series.values);

// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
chart.appear(1000, 100);

}); // end am5.ready()
</script>









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
