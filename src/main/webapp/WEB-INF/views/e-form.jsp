<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <% pageContext.setAttribute("newLineChar", "\n"); %>


<%@ include file="common/mainhead.jspf" %>    
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
  <style>
    .box
	{
	  margin: 10px;
	
	  width: 80px;
	  padding: 10px;
	  text-align: center;
	  font-weight: bold;
	  border: 2px solid black;
	}
	


  </style>
  
  
    <style> 
    
    .bar {
  background: blue;
  width: 50px;
  height: 12px;
  display: block;
  margin-bottom: 50px;
}
 
.dot {
  height: 8px;
  width: 8px;
  border: 1px solid black;
  border-radius: 50%;
  background: #fff;
  display: block;
  float: right;
  margin-right: -4px;
  margin-top: 1px;
}
    


.fixhead2{
 background-color: #F0F8FF;
}



.node {
    position: relative;
    width: 270px;
    background-color: #f0f0f0;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 10px;
    cursor: grab;
    margin: 0 10px 50px 0; /* เพิ่มขอบด้านขวาและด้านล่างของ .node */
  }

 
    .node input {
      width: 100%;
      border: none;
      outline: none;
    }
    
.node-editor {
  position: relative;
}


.port {
  width: 20px;
  height: 20px;
  background-color: lightblue;
  border-radius: 50%;
  position: absolute;
   margin-top:10px;
  transform: translateY(-50%);
  right: -10px;
  cursor: pointer;
}
 .port1 {
    display: inline-block;
    width: 20px; /* ปรับขนาดตามที่คุณต้องการ */
    height: 20px; /* ปรับขนาดตามที่คุณต้องการ */
    background-color: blue; /* เปลี่ยนสีตามที่คุณต้องการ */
    border-radius: 50%; /* ทำให้เป็นวงกลม */
    margin-left: 5px; /* ปรับตำแหน่งตามที่คุณต้องการ */
  }
.port2 {
  width: 20px;
  height: 20px;
  background-color: lightblue;
  border-radius: 50%;
  position: absolute;
  margin-top:10px;
  transform: translateY(-50%);
  left: -10px;
  cursor: pointer;
}
.port3 {
    display: inline-block;
    width: 20px; /* ปรับขนาดตามที่คุณต้องการ */
    height: 20px; /* ปรับขนาดตามที่คุณต้องการ */
    background-color: blue; /* เปลี่ยนสีตามที่คุณต้องการ */
    border-radius: 50%; /* ทำให้เป็นวงกลม */
    margin-left: 5px; /* ปรับตำแหน่งตามที่คุณต้องการ */
}
.port4 {
  width: 20px;
  height: 20px;
  background-color: lightblue;
  border-radius: 50%;
  position: absolute;
  margin-top:10px;
  transform: translateY(-50%);
  left: -10px;
  cursor: pointer;
}

.port5 {
  width: 20px;
  height: 20px;
  background-color: lightblue;
  border-radius: 50%;
  position: absolute;
  margin-top:10px;
  transform: translateY(-50%);
  left: -10px;
  cursor: pointer;
}



.agm{
padding-top:10px

}
    
      </style>
 
 <style>
.center-text{

  display: flex;
  align-items: flex-start; /* จัดตรงขอบบนขององค์ประกอบ */
  justify-content: center; /* จัดตรงกลางในแนวนอน */
  height: 100%; /* กำหนดความสูงของ div ให้เต็มส่วนทั้งหมด */
}
 .excl{
 
 border: none;
   border:0px;
   outline:0px;
 }
 table {
    border-collapse:collapse;
    margin: 0;
    height: 100%;
   border: 1px solid #dddddd;
    table-layout: fixed;
}


  
td {
    text-align: center;
   border: 1px solid #dddddd;
     font-size:14px;
}
td:first-child { text-align: left; }
th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
}
td input {
    width: 100%;

  height: 100%;
     border: 1px solid #eee;
}

.fixhead{
    max-width: 150px;
      min-width: 150px;
}


::placeholder {
  color: red;
  opacity: 1; /* Firefox */
}

:-ms-input-placeholder { /* Internet Explorer 10-11 */
 color: red;
}

::-ms-input-placeholder { /* Microsoft Edge */
 color: red;
}


.form-builder {
    display: flex;
    gap: 20px;
    margin: 20px;
}

.toolbox {
    width: 200px;
       min-width: 200px;
    background-color: #f0f0f0;
    padding: 10px;
    border: 1px solid #ccc;
}

.toolbox .draggable {
    cursor: pointer;
    margin-bottom: 5px;
    padding: 5px;
    background-color: #fff;
    border: 1px solid #ccc;
}

.form-preview {
    flex-grow: 1;
    border: 1px dashed #ccc;
    min-height: 525px;
    height: 550px;
    width:90%;
    padding: 10px;
    background-color: #ffffff; /* เปลี่ยนสีพื้นหลังของฟอร์มตัวอย่างเป็นสีขาว */
    overflow:scroll;
    padding-bottom:100px;

}
.form-preview2 {
    flex-grow: 1;
    border: 1px dashed #ccc;
    min-height: 525px;

    padding: 10px;
    background-color: #ffffff; /* เปลี่ยนสีพื้นหลังของฟอร์มตัวอย่างเป็นสีขาว */

   
}

.toolbox-header {
     color: #fff;
    padding: 10px;
    text-align: center;
}


.item-options-menu{
  width:400px;
}


.selecteform option[value="green"] {
  background-color: green; /* Remove quotes */
       color: #fff;
}

.selecteform option[value="yellow"] {
  background-color: orange; /* Remove quotes */
       color: #fff;
}

.selecteform option[value="red"] {
  background-color: red; /* Remove quotes */
       color: #fff;
}


 </style>
 
<title>E-form</title>
</head>
   <div class="actives2"></div>
<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static" data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">
      
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
        <section class="form-control-repeater">
        
                <c:choose>
                                <c:when test='${condition.condition_id > 0}'>
                                  <c:set var="link" value="/editcondition-condition"/>      
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/addcondition-condition"/>  
                              </c:otherwise>
                               </c:choose>  
                
                
   <form:form method="POST" modelAttribute="condition" action="${link }"  >
                                <div class="card-header">
                                    <h4 class="card-title">E-form  : <input name="condition_name"  style="width:50%;padding:6px;font-size:14px" value="${condition.condition_title }"></h4>
                                     <input  type="hidden" name="condition_sub" id="condition_sub"  value="${condition.condition_sub }">    
                                       <input  type="hidden" name="condition_sub2" id="condition_sub2"  value="${condition.condition_sub2 }">   
                                         <input  type="hidden" name="condition_sub3" id="condition_sub3"  value="${condition.condition_sub3 }">   
                                         
                                         
                                           <input  type="hidden" name="calculation_port1" id="calculation_port1"  value="${condition.calculation_port1 }">  
                                           <input  type="hidden" name="calculation_port2" id="calculation_port2"  value="${condition.calculation_port2 }">  
                                           <input  type="hidden" name="calculation_port3" id="calculation_port3"  value="${condition.calculation_port3 }">  
                                           <input  type="hidden" name="calculation_port4" id="calculation_port4"  value="${condition.calculation_port4 }">  
                                             <input  type="hidden" name="calculation_port5" id="calculation_port5"  value="${condition.calculation_port5}"> 
                                    
                                </div>
                




    
                
        <div class="form-builder">

        <div class="toolbox" >
                <div class="toolbox-header">
    <h3>Field</h3>
</div>
            <div class="draggable" draggable="true">Title</div>

            <br><br>
            <div class="draggable" draggable="true" title="กรอกข้อมูลเเบบข้อความ">Text</div>
            <div class="draggable" draggable="true" title="กรอกข้อมูลเเบบตัวเลข">Number</div>
            <div class="draggable" draggable="true" title="กรอกข้อมูลเจำนวนมาก">Textarea</div>
            <div class="draggable" draggable="true" title="กรอกข้อมูลเเบบเลือกได้หลายตัวเลือก">Checkbox</div>
            <div class="draggable" draggable="true" title="กรอกข้อมูลเเบบวันที่">Date</div>
            <div class="draggable" draggable="true" title="กรอกข้อมูลเเบบเลือกได้ตัวเลือกเดียว">Select</div>
            <div class="draggable" draggable="true" title="การเลือกสถานะ" style="display:none">Status</div>
            <div class="draggable" draggable="true" title="กรอกข้อมูลเเบบเวลา">Time</div>
       
         
              
              
            <!-- เพิ่มองค์ประกอบอื่นๆ ตามต้องการ -->
     
     
     
     
     
     
        </div>
        
          <div class="card-body" style="margin-top:0px;top:-100;padding:0px;">
                                    <ul class="nav nav-pills">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="home-tab" data-bs-toggle="pill" href="#home" aria-expanded="true" onclick="run2()">ตั้งค่าฟอร์ม</a>
                                        </li>
                                      
                                        <li class="nav-item">
                                            <a class="nav-link " id="about-tab" data-bs-toggle="pill" href="#dropdown1" aria-expanded="false" onclick="run2()">ตั้งค่าตารางข้อมูล</a>
                                        </li>
                                         <li class="nav-item">
                                            <a class="nav-link " id="about-tab2" data-bs-toggle="pill" href="#dropdown2" aria-expanded="false" onclick="run()">การเชื่อมต่อข้อมูล</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane active" id="home" aria-labelledby="home-tab" aria-expanded="true">

	 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">  
	 <input  type="hidden" name="field_formid" value="${condition.condition_id }">           
	 <input  type="hidden" name="field_formrv" value="0">  
	 
	 

	   
	                        
   <div class="form-preview " droppable="true" id="editerDiv" style="max-width:95%">
            <!-- ตำแหน่งที่สามารถวางองค์ประกอบของฟอร์ม -->

        </div>

         
            
                                        </div>
                                        <div class="tab-pane" id="dropdown1" role="tabpanel" aria-labelledby="dropdown1-tab" aria-expanded="false" >
                                    
                              <div class="form-preview" style="max-width:95%" >
                                   
                                   
                                   
                                
                
                                      <table  id="tabledata" style="height:auto">
                                
                                    <thead>
                                        <tr >
                       
                                        </tr>
                                    </thead>
                                 <tbody>  </tbody>
                                 </table>
                                 
                                 
                                     <div >
                                     
                                     <br><br><br>
                                <div style="display:none">
                                     <hr>
                   <h2> <spring:message code="form.receiveinformation"  /></h2>   
          <c:if test="${conditionssub.condition_id > 0}">   
 
           <c:if test="${conditionssub.join_type == 1}">  
              <c:set var="check1" value="checked" />
           </c:if>
             <c:if test="${conditionssub.join_type == 0}">  
                <c:set var="check2" value="checked" />
           </c:if>
           
                  <h3>  EForm 1 : ${conditionssub.condition_name  } </h3>   
                    <br> 
                  1.<spring:message code="forms.calculable"  />    <br>  
                 <input type="radio" name="join_type1" value="1" ${check1}> <spring:message code="forms.Yes"  />  <br> 
                 <input type="radio" name="join_type1" value="0" ${check2}><spring:message code="forms.No"  />
                   <br> 
                  
           <c:if test="${conditionssub.join_type == 1}">      <br>      
                   2.<spring:message code="forms.calculable"  />    <br>  
               <span style="float:left">   
               <spring:message code="forms.MapKeys"  /> 1:  <select name="map_key_main1">
                 <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                     
                      <c:set var="checkmain1" value="" />
                        <c:if test="${map_key1.map_key_main1 == row.field_postion}">  
          					    <c:set var="checkmain1" value="selected" />
         			  </c:if>
                     
                        <option value="${row.field_postion }" ${checkmain1}>${row.field_label }</option> 
                     </c:forEach>
                  </select> :    
                  
                  
                  <select name="map_key_sub1">
                         <option value="">-</option> 
                     <c:forEach items="${eformfields2 }" var="row" varStatus="count">  
                  
                    <c:set var="checksub1" value="" />
                        <c:if test="${map_key1.map_key_sub1 ==row.field_postion}">  
          					    <c:set var="checksub1" value="selected" />
         			  </c:if>
                  
                  
                        <option value="${row.field_postion }" ${checksub1}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                       <br> <br>
                  
                   <spring:message code="forms.MapKeys"  /> 2:  <select name="map_key_main2">
                          <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                   
                      <c:set var="checkmain2" value="" />
                        <c:if test="${map_key1.map_key_main2 ==row.field_postion}">  
          					    <c:set var="checkmain2" value="selected" />
         			  </c:if>
                   
                        <option value="${row.field_postion }" ${checkmain2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :  
                  
                  
                    <select name="map_key_sub2">
                        <option value="">-</option> 
                     <c:forEach items="${eformfields2 }" var="row" varStatus="count">  
                       <c:set var="checksub2" value="" />
                        <c:if test="${map_key1.map_key_sub2 ==row.field_postion}">  
          					    <c:set var="checksub2" value="selected" />
         			  </c:if>
                     
                     
                        <option  value="${row.field_postion }" ${checksub2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                   <br> <br>
                  
                    <spring:message code="forms.MapKeys"  /> 3:  <select name="map_key_main3">
                        <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                     
                      <c:set var="checkmain3" value="" />
                        <c:if test="${map_key1.map_key_main3 ==row.field_postion}">  
          					    <c:set var="checkmain3" value="selected" />
         			  </c:if>
                     
                     
                        <option  value="${row.field_postion }" ${checkmain3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :   
                  
                  
                   <select name="map_key_sub3">
                       <option value="">-</option> 
                     <c:forEach items="${eformfields2 }" var="row" varStatus="count">  
                    
                       <c:set var="checksub3" value="" />
                        <c:if test="${map_key1.map_key_sub3 ==row.field_postion}">  
          					    <c:set var="checksub3" value="selected" />
         			  </c:if>
                     
                    
                        <option value="${row.field_postion }" ${checksub3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                    <spring:message code="forms.MapKeys"  /> 4:  <select name="map_key_main4">
                        <option value="">-</option>  
                     <c:forEach items="${eformfields }" var="row" varStatus="count"> 
                     
                           <c:set var="checkmain4" value="" />
                        <c:if test="${map_key1.map_key_main4 ==row.field_postion}">  
          					    <c:set var="checkmain4" value="selected" />
         			  </c:if>
                     
                     
                        <option value="${row.field_postion }" ${checkmain4}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :    <select name="map_key_sub4">
                    <option value="">-</option> 
                     <c:forEach items="${eformfields2 }" var="row" varStatus="count"> 
                     
                        <c:set var="checksub4" value="" />
                        <c:if test="${map_key1.map_key_sub4 ==row.field_postion}">  
          					    <c:set var="checksub4" value="selected" />
         			  </c:if>
                    
                        <option  value="${row.field_postion }" ${checksub4}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                    <spring:message code="forms.MapKeys"  /> 5:  <select name="map_key_main5">
                     <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                        
                         <c:set var="checkmain5" value="" />
                        <c:if test="${map_key1.map_key_main5 ==row.field_postion}">  
          					    <c:set var="checkmain5" value="selected" />
         			  </c:if>
                     
                        
                        
                        <option value="${row.field_postion }" ${checkmain5}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :    <select name="map_key_sub5">
                    <option value="">-</option> 
                     <c:forEach items="${eformfields2 }" var="row" varStatus="count">  
                       
                       
                          <c:set var="checksub5" value="" />
                        <c:if test="${map_key1.map_key_sub5 ==row.field_postion}">  
          					    <c:set var="checksub5" value="selected" />
         			  </c:if>
                       
                       
                        <option  value="${row.field_postion }" ${checksub5}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                   <br>    <br>
                     </span>   
                     
                     
                    <span style="float:left;margin-left:20px;">   
                <spring:message code="forms.Linkdata"  /> 1:  <select name="map_key_mainsend1">
                  <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">
                         
                             <c:set var="checkmainlink1" value="" />
                        <c:if test="${map_key1.map_key_mainsend1 ==row.field_postion}">  
          					    <c:set var="checkmainlink1" value="selected" />
         			  </c:if>
                     
                         
                        <option value="${row.field_postion }" ${checkmainlink1}>  ${row.field_label }</option> 
                     </c:forEach>
                     
                     
                     
                     
                  </select> :    <select name="map_key_subsend1">
                     <option value="">-</option> 
                     <c:forEach items="${eformfields2 }" var="row" varStatus="count"> 
                       
                             <c:set var="checksubsend1" value="" />
                        <c:if test="${map_key1.map_key_subsend1 ==row.field_postion}">  
          					    <c:set var="checksubsend1" value="selected" />
         			  </c:if>
                     
                       
                       
                        <option value="${row.field_postion }" ${checksubsend1}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                       <br> <br>
                  
                   <spring:message code="forms.Linkdata"  /> 2:  <select name="map_key_mainsend2">
                       <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count"> 
                       
                             <c:set var="checkmainlink2" value="" />
                        <c:if test="${map_key1.map_key_mainsend2 ==row.field_postion}">  
          					    <c:set var="checkmainlink2" value="selected" />
         			  </c:if>
                     
                       
                        <option value="${row.field_postion }" ${checkmainlink2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :   
                  
                  
                   <select name="map_key_subsend2">
                      <option value="">-</option>  
                     <c:forEach items="${eformfields2 }" var="row" varStatus="count"> 
                             <c:set var="checksubsend2" value="" />
                        <c:if test="${map_key1.map_key_subsend2 ==row.field_postion}">  
          					    <c:set var="checksubsend2" value="selected" />
         			  </c:if>
                        <option value="${row.field_postion }" ${checksubsend2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                   <br> <br>
                  
                    <spring:message code="forms.Linkdata"  /> 3:  <select name="map_key_mainsend3">
                      <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                       
                                <c:set var="checkmainlink3" value="" />
                        <c:if test="${map_key1.map_key_mainsend3 ==row.field_postion}">  
          					    <c:set var="checkmainlink3" value="selected" />
         			  </c:if>
                       
                        <option  value="${row.field_postion }" ${checkmainlink3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :   
                  
                  
                   <select name="map_key_subsend3">
                     <option value="">-</option>  
                     <c:forEach items="${eformfields2 }" var="row" varStatus="count">
                       
                           <c:set var="checksubsend3" value="" />
                        <c:if test="${map_key1.map_key_subsend3 ==row.field_postion}">  
          					    <c:set var="checksubsend3" value="selected" />
         			  </c:if>
                        <option value="${row.field_postion }" ${checksubsend3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                     <spring:message code="forms.Linkdata"  /> 4:  <select name="map_key_mainsend4">
                         <option value="">-</option>   
                     <c:forEach items="${eformfields }" var="row" varStatus="count">
                 		
                 		
                 		   <c:set var="checkmainlink4" value="" />
                        <c:if test="${map_key1.map_key_mainsend4 ==row.field_postion}">  
          					    <c:set var="checkmainlink4" value="selected" />
         			  </c:if>
         			  
         			  
                        <option value="${row.field_postion }" ${checkmainlink4}>  ${row.field_label }</option> 
                     </c:forEach>
                     
                     
                  </select name="map_key_subsend4"> :    <select >
                   <option value="">-</option> 
                     <c:forEach items="${eformfields2 }" var="row" varStatus="count">  
                     
                            <c:set var="checksubsend4" value="" />
                        <c:if test="${map_key1.map_key_subsend4 ==row.field_postion}">  
          					    <c:set var="checksubsend4" value="selected" />
         			  </c:if>
                       
                        <option value="${row.field_postion }" ${checksubsend4}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                    <spring:message code="forms.Linkdata"  /> 5:  <select name="map_key_mainsend5">
                        <option value="">-</option>  
                     <c:forEach items="${eformfields }" var="row" varStatus="count"> 
                     
                     
                     <c:set var="checkmainlink5" value="" />
                        <c:if test="${map_key1.map_key_mainsend5 ==row.field_postion}">  
          					    <c:set var="checkmainlink5" value="selected" />
         			  </c:if>
                        <option value="${row.field_postion }" ${checkmainlink5}>  ${row.field_label }</option> 
                     </c:forEach>
                     
                     
                  </select> :    <select name="map_key_subsend5">
                       <option value="">-</option> 
                     <c:forEach items="${eformfields2 }" var="row" varStatus="count">  
                    
                            <c:set var="checksubsend5" value="" />
                        <c:if test="${map_key1.map_key_subsend5 ==row.field_postion}">  
          					    <c:set var="checksubsend5" value="selected" />
         			  </c:if>
                    
                    
                        <option value="${row.field_postion }" ${checksubsend5}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                   <br>    <br>
                     </span>   
                           
              </c:if>
              
                 
                   <c:set var="list1" value="${conditionssub.condition_titlerefer}" />
  
                    
  <c:if test="${conditionssub.join_type == 0}">     
  
   <br> 
                   2.<spring:message code="forms.calculable"  />     
                  <br> 
                               <c:forEach items="${eformfields }" var="row" varStatus="count">  
                                   <c:set var="listcheck1" value="${count.count }" />
                                <input type="checkbox" name="refer1" value="${count.count }"
                                 <c:if test='${fn:contains(list1,listcheck1) ||  conditionssub.condition_titlerefer  == null }'>checked</c:if>> ${row.field_label }**<br>
                              </c:forEach>
                              <c:forEach items="${eformfields2 }" var="row" varStatus="count">     
                                     <input type="checkbox"  value="${count.count }" disabled>  ${row.field_label } <br>
                               </c:forEach> 
                           
              
                       </c:if>
          
                  <hr style="clear:both">
                  
                  
                  
         </c:if>   
      
         
              
         <c:if test="${conditionssub2.condition_id > 0}">  
         
           <c:if test="${conditionssub2.join_type == 1}">  
              <c:set var="check1" value="checked" />
           </c:if>
             <c:if test="${conditionssub2.join_type == 0}">  
                <c:set var="check2" value="checked" />
           </c:if>
           
           
               EForm 2 : ${conditionssub2.condition_name  } <span style="float:right">  <spring:message code="forms.calculable"  />   : 
                 <input type="radio" name="join_type2" value="1" ${check1}> <spring:message code="forms.Yes"  /> 
                 <input type="radio" name="join_type2" value="0" ${check2}><spring:message code="forms.No"  /></span>
                  
                  
           <c:if test="${conditionssub2.join_type == 1}">          
                  
                  <br> <br>
               <span style="float:left">   
               <spring:message code="forms.MapKeys"  /> 1:  <select name="map_key2_main1">
                 <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                     
                      <c:set var="checkmain1" value="" />
                        <c:if test="${map_key2.map_key_main1 == row.field_postion}">  
          					    <c:set var="checkmain1" value="selected" />
         			  </c:if>
                     
                        <option value="${row.field_postion }" ${checkmain1}>${row.field_label }</option> 
                     </c:forEach>
                  </select> :    
                  
                  
                  <select name="map_key2_sub1">
                         <option value="">-</option> 
                     <c:forEach items="${eformfields3 }" var="row" varStatus="count">  
                  
                    <c:set var="checksub1" value="" />
                        <c:if test="${map_key2.map_key_sub1 ==row.field_postion}">  
          					    <c:set var="checksub1" value="selected" />
         			  </c:if>
                  
                  
                        <option value="${row.field_postion }" ${checksub1}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                       <br> <br>
                  
                   <spring:message code="forms.MapKeys"  /> 2:  <select name="map_key2_main2">
                          <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                   
                      <c:set var="checkmain2" value="" />
                        <c:if test="${map_key2.map_key_main2 ==row.field_postion}">  
          					    <c:set var="checkmain2" value="selected" />
         			  </c:if>
                   
                        <option value="${row.field_postion }" ${checkmain2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :  
                  
                  
                    <select name="map_key2_sub2">
                        <option value="">-</option> 
                     <c:forEach items="${eformfields3 }" var="row" varStatus="count">  
                       <c:set var="checksub2" value="" />
                        <c:if test="${map_key2.map_key_sub2 ==row.field_postion}">  
          					    <c:set var="checksub2" value="selected" />
         			  </c:if>
                     
                     
                        <option  value="${row.field_postion }" ${checksub2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                   <br> <br>
                  
                  
                  
                    <spring:message code="forms.MapKeys"  /> 3:  <select name="map_key2_main3">
                        <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                     
                      <c:set var="checkmain3" value="" />
                        <c:if test="${map_key2.map_key_main3 ==row.field_postion}">  
          					    <c:set var="checkmain3" value="selected" />
         			  </c:if>
                     
                     
                        <option  value="${row.field_postion }" ${checkmain3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :   
                  
                  
                   <select name="map_key2_sub3">
                       <option value="">-</option> 
                     <c:forEach items="${eformfields3 }" var="row" varStatus="count">  
                    
                       <c:set var="checksub3" value="" />
                        <c:if test="${map_key2.map_key_sub3 ==row.field_postion}">  
          					    <c:set var="checksub3" value="selected" />
         			  </c:if>
                     
                    
                        <option value="${row.field_postion }" ${checksub3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                    <spring:message code="forms.MapKeys"  /> 4:  <select name="map_key2_main4">
                        <option value="">-</option>  
                     <c:forEach items="${eformfields }" var="row" varStatus="count"> 
                     
                           <c:set var="checkmain4" value="" />
                        <c:if test="${map_key2.map_key_main4 ==row.field_postion}">  
          					    <c:set var="checkmain4" value="selected" />
         			  </c:if>
                     
                     
                        <option value="${row.field_postion }" ${checkmain4}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :    <select name="map_key2_sub4">
                    <option value="">-</option> 
                     <c:forEach items="${eformfields3 }" var="row" varStatus="count"> 
                     
                        <c:set var="checksub4" value="" />
                        <c:if test="${map_key2.map_key_sub4 ==row.field_postion}">  
          					    <c:set var="checksub4" value="selected" />
         			  </c:if>
      
                        <option  value="${row.field_postion }" ${checksub4}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                    <spring:message code="forms.MapKeys"  /> 5:  <select name="map_key2_main5">
                     <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                        
                         <c:set var="checkmain5" value="" />
                        <c:if test="${map_key2.map_key_main5 ==row.field_postion}">  
          					    <c:set var="checkmain5" value="selected" />
         			  </c:if>
                     
                        
                        
                        <option value="${row.field_postion }" ${checkmain5}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :    <select name="map_key2_sub5">
                    <option value="">-</option> 
                     <c:forEach items="${eformfields3 }" var="row" varStatus="count">  
                       
                       
                          <c:set var="checksub5" value="" />
                        <c:if test="${map_key2.map_key_sub5 ==row.field_postion}">  
          					    <c:set var="checksub5" value="selected" />
         			  </c:if>
                       
                       
                        <option  value="${row.field_postion }" ${checksub5}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                   <br>    <br>
                     </span>   
                     
                     
                    <span style="float:left;margin-left:20px;">   
                <spring:message code="forms.Linkdata"  /> 1:  <select name="map_key2_mainsend1">
                  <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">
                         
                             <c:set var="checkmainlink1" value="" />
                        <c:if test="${map_key2.map_key_mainsend1 ==row.field_postion}">  
          					    <c:set var="checkmainlink1" value="selected" />
         			  </c:if>
                     
                         
                        <option value="${row.field_postion }" ${checkmainlink1}>  ${row.field_label }</option> 
                     </c:forEach>
                     
                     
                     
                     
                  </select> :    <select name="map_key2_subsend1">
                     <option value="">-</option> 
                     <c:forEach items="${eformfields3 }" var="row" varStatus="count"> 
                       
                             <c:set var="checksubsend1" value="" />
                        <c:if test="${map_key2.map_key_subsend1 ==row.field_postion}">  
          					    <c:set var="checksubsend1" value="selected" />
         			  </c:if>
                     
                       
                       
                        <option value="${row.field_postion }" ${checksubsend1}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                       <br> <br>
                  
                   <spring:message code="forms.Linkdata"  /> 2:  <select name="map_key2_mainsend2">
                       <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count"> 
                       
                             <c:set var="checkmainlink2" value="" />
                        <c:if test="${map_key2.map_key_mainsend2 ==row.field_postion}">  
          					    <c:set var="checkmainlink2" value="selected" />
         			  </c:if>
                     
                       
                        <option value="${row.field_postion }" ${checkmainlink2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :   
                  
                  
                   <select name="map_key2_subsend2">
                      <option value="">-</option>  
                     <c:forEach items="${eformfields3 }" var="row" varStatus="count"> 
                             <c:set var="checksubsend2" value="" />
                        <c:if test="${map_key2.map_key_subsend2 ==row.field_postion}">  
          					    <c:set var="checksubsend2" value="selected" />
         			  </c:if>
                        <option value="${row.field_postion }" ${checksubsend2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                   <br> <br>
                  
                    <spring:message code="forms.Linkdata"  /> 3:  <select name="map_key2_mainsend3">
                      <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                       
                                <c:set var="checkmainlink3" value="" />
                        <c:if test="${map_key2.map_key_mainsend3 ==row.field_postion}">  
          					    <c:set var="checkmainlink3" value="selected" />
         			  </c:if>
                       
                        <option  value="${row.field_postion }" ${checkmainlink3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :   
                  
                  
                   <select name="map_key2_subsend3">
                     <option value="">-</option>  
                     <c:forEach items="${eformfields3 }" var="row" varStatus="count">
                       
                           <c:set var="checksubsend3" value="" />
                        <c:if test="${map_key2.map_key_subsend3 ==row.field_postion}">  
          					    <c:set var="checksubsend3" value="selected" />
         			  </c:if>
                        <option value="${row.field_postion }" ${checksubsend3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                     <spring:message code="forms.Linkdata"  /> 4:  <select name="map_key2_mainsend4">
                         <option value="">-</option>   
                     <c:forEach items="${eformfields }" var="row" varStatus="count">
                 		
                 		
                 		   <c:set var="checkmainlink4" value="" />
                        <c:if test="${map_key2.map_key_mainsend4 ==row.field_postion}">  
          					    <c:set var="checkmainlink4" value="selected" />
         			  </c:if>
         			  
         			  
                        <option value="${row.field_postion }" ${checkmainlink4}>  ${row.field_label }</option> 
                     </c:forEach>
                     
                     
                  </select name="map_key2_subsend4"> :    <select >
                   <option value="">-</option> 
                     <c:forEach items="${eformfields3 }" var="row" varStatus="count">  
                     
                            <c:set var="checksubsend4" value="" />
                        <c:if test="${map_key2.map_key_subsend4 ==row.field_postion}">  
          					    <c:set var="checksubsend4" value="selected" />
         			  </c:if>
                       
                        <option value="${row.field_postion }" ${checksubsend4}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                    <spring:message code="forms.Linkdata"  /> 5:  <select name="map_key2_mainsend5">
                        <option value="">-</option>  
                     <c:forEach items="${eformfields }" var="row" varStatus="count"> 
                     
                     
                     <c:set var="checkmainlink5" value="" />
                        <c:if test="${map_key2.map_key_mainsend5 ==row.field_postion}">  
          					    <c:set var="checkmainlink5" value="selected" />
         			  </c:if>
                        <option value="${row.field_postion }" ${checkmainlink5}>  ${row.field_label }</option> 
                     </c:forEach>
                     
                     
                  </select> :    <select name="map_key2_subsend5">
                       <option value="">-</option> 
                     <c:forEach items="${eformfields3 }" var="row" varStatus="count">  
                    
                            <c:set var="checksubsend5" value="" />
                        <c:if test="${map_key2.map_key_subsend5 ==row.field_postion}">  
          					    <c:set var="checksubsend5" value="selected" />
         			  </c:if>
                    
                    
                        <option value="${row.field_postion }" ${checksubsend5}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                   <br>    <br>
                     </span>   
                           
              </c:if>
              
         
         
         
         

         
                               <c:set var="list2" value="${conditionssub2.condition_titlerefer}" />
         
         
         <c:if test="${conditionssub2.join_type == 0}">     
  
  <br> <br>
         
                                        <table class="tabledata" style="height:auto">
                                    <thead>
                                        <tr>
                                <c:forEach items="${eformfields }" var="row" varStatus="count">  
                                      <c:set var="listcheck2" value="${count.count }" />
                                
                                  <th class="form-label fixhead fixhead2"><input type="checkbox" name="refer2" value="${count.count }"
                                       <c:if test='${fn:contains(list2,listcheck2)  ||  conditionssub2.condition_titlerefer  == null  }'>checked</c:if>
                                  > ${row.field_label }</th> 
                              </c:forEach>
                              <c:forEach items="${eformfields3 }" var="row" varStatus="count">     
                                        <th class="form-label fixhead">${row.field_label }</th>
                             </c:forEach> 
                               </tr>
                                 </thead>
                 </table>
                 
                  <hr>
              </c:if>       
              










    <c:if test="${conditionssub3.condition_id > 0}">  
    
   
           <c:if test="${conditionssub3.join_type == 1}">  
              <c:set var="check1" value="checked" />
           </c:if>
             <c:if test="${conditionssub3.join_type == 0}">  
                <c:set var="check2" value="checked" />
           </c:if>
           
           
               EForm 3 : ${conditionssub3.condition_name  } <span style="float:right">  <spring:message code="forms.calculable"  />   : 
                 <input type="radio" name="join_type3" value="1" ${check1}> <spring:message code="forms.Yes"  /> 
                 <input type="radio" name="join_type3" value="0" ${check2}><spring:message code="forms.No"  /></span>
                  
                  
           <c:if test="${conditionssub3.join_type == 1}">          
                  
                  <br> <br>
               <span style="float:left">   
               <spring:message code="forms.MapKeys"  /> 1:  <select name="map_key3_main1">
                 <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                     
                      <c:set var="checkmain1" value="" />
                        <c:if test="${map_key3.map_key_main1 == row.field_postion}">  
          					    <c:set var="checkmain1" value="selected" />
         			  </c:if>
                     
                        <option value="${row.field_postion }" ${checkmain1}>${row.field_label }</option> 
                     </c:forEach>
                  </select> :    
                  
                  
                  <select name="map_key3_sub1">
                         <option value="">-</option> 
                     <c:forEach items="${eformfields4 }" var="row" varStatus="count">  
                  
                    <c:set var="checksub1" value="" />
                        <c:if test="${map_key3.map_key_sub1 ==row.field_postion}">  
          					    <c:set var="checksub1" value="selected" />
         			  </c:if>
                  
                  
                        <option value="${row.field_postion }" ${checksub1}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                       <br> <br>
                  
                   <spring:message code="forms.MapKeys"  /> 2:  <select name="map_key3_main2">
                          <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                   
                      <c:set var="checkmain2" value="" />
                        <c:if test="${map_key3.map_key_main2 ==row.field_postion}">  
          					    <c:set var="checkmain2" value="selected" />
         			  </c:if>
                   
                        <option value="${row.field_postion }" ${checkmain2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :  
                  
                  
                    <select name="map_key3_sub2">
                        <option value="">-</option> 
                     <c:forEach items="${eformfields4 }" var="row" varStatus="count">  
                       <c:set var="checksub2" value="" />
                        <c:if test="${map_key3.map_key_sub2 ==row.field_postion}">  
          					    <c:set var="checksub2" value="selected" />
         			  </c:if>
                     
                     
                        <option  value="${row.field_postion }" ${checksub2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                   <br> <br>
                  
                  
                  
                    <spring:message code="forms.MapKeys"  /> 3:  <select name="map_key3_main3">
                        <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                     
                      <c:set var="checkmain3" value="" />
                        <c:if test="${map_key3.map_key_main3 ==row.field_postion}">  
          					    <c:set var="checkmain3" value="selected" />
         			  </c:if>
                     
                     
                        <option  value="${row.field_postion }" ${checkmain3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :   
                  
                  
                   <select name="map_key3_sub3">
                       <option value="">-</option> 
                     <c:forEach items="${eformfields4 }" var="row" varStatus="count">  
                    
                       <c:set var="checksub3" value="" />
                        <c:if test="${map_key3.map_key_sub3 ==row.field_postion}">  
          					    <c:set var="checksub3" value="selected" />
         			  </c:if>
                     
                    
                        <option value="${row.field_postion }" ${checksub3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                    <spring:message code="forms.MapKeys"  /> 4:  <select name="map_key3_main4">
                        <option value="">-</option>  
                     <c:forEach items="${eformfields }" var="row" varStatus="count"> 
                     
                           <c:set var="checkmain4" value="" />
                        <c:if test="${map_key3.map_key_main4 ==row.field_postion}">  
          					    <c:set var="checkmain4" value="selected" />
         			  </c:if>
                     
                     
                        <option value="${row.field_postion }" ${checkmain4}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :    <select name="map_key3_sub4">
                    <option value="">-</option> 
                     <c:forEach items="${eformfields4 }" var="row" varStatus="count"> 
                     
                        <c:set var="checksub4" value="" />
                        <c:if test="${map_key3.map_key_sub4 ==row.field_postion}">  
          					    <c:set var="checksub4" value="selected" />
         			  </c:if>
                     
    
                        <option  value="${row.field_postion }" ${checksub4}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                    <spring:message code="forms.MapKeys"  /> 5:  <select name="map_key3_main5">
                     <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                        
                         <c:set var="checkmain5" value="" />
                        <c:if test="${map_key3.map_key_main5 ==row.field_postion}">  
          					    <c:set var="checkmain5" value="selected" />
         			  </c:if>
                     
                        
                        
                        <option value="${row.field_postion }" ${checkmain5}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :    <select name="map_key3_sub5">
                    <option value="">-</option> 
                     <c:forEach items="${eformfields4 }" var="row" varStatus="count">  
                       
                       
                          <c:set var="checksub5" value="" />
                        <c:if test="${map_key3.map_key_sub5 ==row.field_postion}">  
          					    <c:set var="checksub5" value="selected" />
         			  </c:if>
                       
                       
                        <option  value="${row.field_postion }" ${checksub5}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                   <br>    <br>
                     </span>   
                     
                     
                    <span style="float:left;margin-left:20px;">   
                <spring:message code="forms.Linkdata"  /> 1:  <select name="map_key3_mainsend1">
                  <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">
                         
                             <c:set var="checkmainlink1" value="" />
                        <c:if test="${map_key3.map_key_mainsend1 ==row.field_postion}">  
          					    <c:set var="checkmainlink1" value="selected" />
         			  </c:if>
                     
                         
                        <option value="${row.field_postion }" ${checkmainlink1}>  ${row.field_label }</option> 
                     </c:forEach>
                     
                     
                     
                     
                  </select> :    <select name="map_key3_subsend1">
                     <option value="">-</option> 
                     <c:forEach items="${eformfields4 }" var="row" varStatus="count"> 
                       
                             <c:set var="checksubsend1" value="" />
                        <c:if test="${map_key3.map_key_subsend1 ==row.field_postion}">  
          					    <c:set var="checksubsend1" value="selected" />
         			  </c:if>
                     
                       
                       
                        <option value="${row.field_postion }" ${checksubsend1}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                       <br> <br>
                  
                   <spring:message code="forms.Linkdata"  /> 2:  <select name="map_key3_mainsend2">
                       <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count"> 
                       
                             <c:set var="checkmainlink2" value="" />
                        <c:if test="${map_key3.map_key_mainsend2 ==row.field_postion}">  
          					    <c:set var="checkmainlink2" value="selected" />
         			  </c:if>
                     
                       
                        <option value="${row.field_postion }" ${checkmainlink2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :   
                  
                  
                   <select name="map_key3_subsend2">
                      <option value="">-</option>  
                     <c:forEach items="${eformfields4 }" var="row" varStatus="count"> 
                             <c:set var="checksubsend2" value="" />
                        <c:if test="${map_key3.map_key_subsend2 ==row.field_postion}">  
          					    <c:set var="checksubsend2" value="selected" />
         			  </c:if>
                        <option value="${row.field_postion }" ${checksubsend2}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                   <br> <br>
                  
                    <spring:message code="forms.Linkdata"  /> 3:  <select name="map_key3_mainsend3">
                      <option value="">-</option> 
                     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                       
                                <c:set var="checkmainlink3" value="" />
                        <c:if test="${map_key3.map_key_mainsend3 ==row.field_postion}">  
          					    <c:set var="checkmainlink3" value="selected" />
         			  </c:if>
                       
                        <option  value="${row.field_postion }" ${checkmainlink3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select> :   
                  
                  
                   <select name="map_key3_subsend3">
                     <option value="">-</option>  
                     <c:forEach items="${eformfields4 }" var="row" varStatus="count">
                       
                           <c:set var="checksubsend3" value="" />
                        <c:if test="${map_key3.map_key_subsend3 ==row.field_postion}">  
          					    <c:set var="checksubsend3" value="selected" />
         			  </c:if>
                        <option value="${row.field_postion }" ${checksubsend3}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                     <spring:message code="forms.Linkdata"  /> 4:  <select name="map_key3_mainsend4">
                         <option value="">-</option>   
                     <c:forEach items="${eformfields }" var="row" varStatus="count">
                 		
                 		
                 		   <c:set var="checkmainlink4" value="" />
                        <c:if test="${map_key3.map_key_mainsend4 ==row.field_postion}">  
          					    <c:set var="checkmainlink4" value="selected" />
         			  </c:if>
         			  
         			  
                        <option value="${row.field_postion }" ${checkmainlink4}>  ${row.field_label }</option> 
                     </c:forEach>
                     
                     
                  </select name="map_key3_subsend4"> :    <select >
                   <option value="">-</option> 
                     <c:forEach items="${eformfields4 }" var="row" varStatus="count">  
                     
                            <c:set var="checksubsend4" value="" />
                        <c:if test="${map_key3.map_key_subsend4 ==row.field_postion}">  
          					    <c:set var="checksubsend4" value="selected" />
         			  </c:if>
                       
                        <option value="${row.field_postion }" ${checksubsend4}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                  
                        <br> <br>
                  
                    <spring:message code="forms.Linkdata"  /> 5:  <select name="map_key3_mainsend5">
                        <option value="">-</option>  
                     <c:forEach items="${eformfields }" var="row" varStatus="count"> 
                     
                     
                     <c:set var="checkmainlink5" value="" />
                        <c:if test="${map_key3.map_key_mainsend5 ==row.field_postion}">  
          					    <c:set var="checkmainlink5" value="selected" />
         			  </c:if>
                        <option value="${row.field_postion }" ${checkmainlink5}>  ${row.field_label }</option> 
                     </c:forEach>
                     
                     
                  </select> :    <select name="map_key3_subsend5">
                       <option value="">-</option> 
                     <c:forEach items="${eformfields4 }" var="row" varStatus="count">  
                    
                            <c:set var="checksubsend5" value="" />
                        <c:if test="${map_key3.map_key_subsend5 ==row.field_postion}">  
          					    <c:set var="checksubsend5" value="selected" />
         			  </c:if>
                    
                    
                        <option value="${row.field_postion }" ${checksubsend5}>  ${row.field_label }</option> 
                     </c:forEach>
                  </select>
                   <br>    <br>
                     </span>   
                           
              </c:if>
              
         
 
 
                     <c:set var="list3" value="${conditionssub3.condition_titlerefer}" />

                        <c:if test="${conditionssub2.join_type == 0}">     
                                        <table class="tabledata" style="height:auto">
                                    <thead>
                                        <tr>
                                <c:forEach items="${eformfields }" var="row" varStatus="count">  
                                      <c:set var="listcheck3" value="${count.count }" />
                                  <th class="form-label fixhead fixhead2"><input type="checkbox" name="refer3" value="${count.count }"
                                       <c:if test='${fn:contains(list3,listcheck3) ||  conditionssub3.condition_titlerefer  == null }'>checked</c:if>
                                  > ${row.field_label }</th> 
                              </c:forEach>
            <c:forEach items="${eformfields4 }" var="row" varStatus="count">     
                              
                            
                              
                              
                                        <th class="form-label fixhead">${row.field_label }</th>
             </c:forEach> 
                               </tr>
                                 </thead>
                 </table>
                   </c:if>  
                  </c:if>
                 
                 
                 
                      </c:if>  
                                  </div>
                                </div>
                                  
                                       </div>    
                                        </div>
                                     
                                                <div class="tab-pane" id="dropdown2" role="tabpanel" aria-labelledby="dropdown1-tab" aria-expanded="false">
                                    
                              <div class="form-preview2 " >
<div class="node-editor" id="node-editor">

                    <div class="row">
                      
                   
				                               
				                                 
				      
	<div class="col-4">


  <div class="node node1" id="node1">
   <div class="agm" style="padding-top:10px">

   My form    <span id="myform" class="port port1" style="display:none" ></span>
                      
                        <span id="myform2" class="port port1" style="display:none" ></span>
                  
                           <span id="myform3" class="port port1"style="display:none" ></span>
   <hr>
   
    INPUT
     </div>


 </div>
  
  
 </div> 
    <div class="col-4" >
    
    
   <br> <br> <br> <br> <br> <br> <br>
     <div class="node" id="node2" style="magin-top:100px">
      <div class="agm style="padding-top:10px">

   Data set
     <button type="button"  data-bs-toggle="modal" data-bs-target="#large" style="float:right;border: 1px #fff"  onclick='loaddataset("dataset_node1")'>+</button>
   <hr>
     </div>   
     OUTPUT
     <div id="dataset_node1"></div>
     
  </div>
  
  
  
  
  
       <div class="node" id="node2" style="display:none">
      <div class="agm style="padding-top:10px;display:none">

   Calculation

          <button type="button"  data-bs-toggle="modal" data-bs-target="#large3" style="float:right;border: 1px #fff" 
           onclick='loadCalculation("calculation_node1")'>+</button>
   <hr>
     </div>   
     
          <div id="calculation_node1">
          
          
<c:set var="cal" value='${conditionscal1 }' />
<c:set var="sub1" value='${eformfieldsub1_1 }' />
<c:set var="sub2" value='${eformfieldsub1_2 }' />
<c:set var="sub3" value='${eformfieldsub1_3 }' />
<c:set var="sub4" value='${eformfieldsub1_4 }' />
<c:set var="sub5" value='${eformfieldsub1_5 }' />
<c:set var="sub6" value='${eformfieldsub1_6 }' />


<c:set var="sub5s" value='${eformfieldsub1_5s }' />
<c:set var="sub6s" value='${eformfieldsub1_6s }' />
<c:set var="sub7s" value='${eformfieldsub1_7s }' />


<c:set var="sub7" value='${eformfieldsub1_7 }' />
  <c:set var="sub8" value='${eformfieldsub1_8 }' />
<c:set var="sub9" value='${eformfieldsub1_9 }' />
<c:set var="sub10" value='${eformfieldsub1_10 }' />
    <c:if test="${cal.condition_id > 0}">   
    
   
		              <input id="${cal.condition_id}" value="${cal.condition_name}-${cal.condition_id}" 
		       data-custom="${cal.condition_id}" data-custom2="dataset" readonly=""> 
		     
		       <div id="calculatio_node1">  
		        <div class="agm"><span id="portP1" class="port5 <c:if test="${sub1.field_datareferid > 0 }">portedmyid${sub1.field_id }</c:if>"></span>Product 

		        
		        <select style="float:right;width:50%" name="portP1"> 
		          <option>-</option>
		        <c:forEach items="${eformcal1 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_1_1" <c:if test="${row.field_id == sub1.field_datareferid }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
		        </select></div>
		        
		        
		        
		       <div class="agm"><span id="portL1" class="port5 <c:if  test="${sub2.field_datareferid2 > 0 }">portedmyid${sub2.field_id }</c:if>"></span>Lot 
		       <select style="float:right;width:50%" name="portL1">
       <option>-</option>
		         <c:forEach items="${eformcal1 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_1_2" <c:if test="${row.field_id == sub2.field_datareferid2}">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
		       </select></div>
		       
		       
		       
		        <div class="agm"><span id="portO1" class="port4 <c:if  test="${sub3.field_datareferid3 > 0 }">portedmyid${sub3.field_id }</c:if>"></span>Order 
		        <select style="float:right;width:50%" name="portO1"> 
		               <option>-</option>
		         <c:forEach items="${eformcal1 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_1_3" <c:if test="${row.field_id == sub3.field_datareferid3 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
		        
		        </select></div>
		        
		        	 	 <div class="agm" title="ยอดคงเหลือก่อนเบิก"><span id="portA1" class="port4 <c:if test="${sub9.field_datareferid16 > 0 }">portedmyid${sub9.field_id }</c:if>">
		        	 	 </span>Balance Before<select style="float:right;width:50%" name="portA1"> 
 				 
 				        <option>-</option>
		       <c:forEach items="${eformcal1 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_1_16" <c:if test="${row.field_id == sub9.field_datareferid16 }">selected</c:if> > ${row.field_label }</option>
		        </c:forEach>
 				 </select></div>
 				 
		        
 				 <div class="agm" title="ยอดคงเหลือหลังเบิก"><span id="portR1" class="port4 <c:if test="${sub4.field_datareferid4 > 0 }">
 				 portedmyid${sub4.field_id }</c:if>"></span>Balance After <select style="float:right;width:50% " name="portR1"> 
 				 
 				        <option>-</option>
		       <c:forEach items="${eformcal1 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_1_4" <c:if test="${row.field_id == sub4.field_datareferid4 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
 				 </select></div>
 				 
 				 
 				 
 				 
 				 	 <div class="agm" title="คงค้างได้จาก นำ Order มาลบออกด้วย ยอดสะสม"><span id="portD1" class="port4 <c:if test="${sub8.field_datareferid15 > 0 }">
 				 	 portedmyid${sub8.field_id }</c:if>"></span>Due <select style="float:right;width:50%" name="portD1"> 
 				 
 				        <option>-</option>
		       <c:forEach items="${eformcal1 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_1_15" <c:if test="${row.field_id == sub8.field_datareferid15 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
 				 </select></div>
 				 
 				 
 				 
 				 
 				 	 	 <div class="agm" title="จ่ายออกสะสม"><span id="portT1" class="port4 <c:if test="${sub10.field_datareferid19 > 0 }">portedmyid${sub10.field_id }</c:if>"></span>
 				 	 	 Total <select style="float:right;width:50%" name="portT1"> 
 				 
 				        <option>-</option>
		       <c:forEach items="${eformcal1 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_1_19" <c:if test="${row.field_id == sub10.field_datareferid19 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
 				 </select></div>
 			
 				 
 				 
 				 
 				 
		       <div class="agm"><span id="portCU1" class="port4 <c:if test="${sub5.field_datareferid5 > 0 }">portedmyid${sub5.field_id } portedmyids${sub5s.field_id }</c:if>"></span>
		       Update<select style="float:right;width:50%" name="portCU1">
		              <option>-</option>
		         <c:forEach items="${eformcal1 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_1_5" <c:if test="${row.field_id == sub5.field_datareferid5 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
		       </select></div>
		       
		       
		       <div class="agm"><span id="portCI1" class="port4 <c:if test="${sub6.field_datareferid6 > 0 }">portedmyid${sub6.field_id } portedmyid${sub6s.field_id }</c:if>"></span>
		       Insert<select  style="float:right;width:50%"  name="portCI1">
		                  <option>-</option>
		         <c:forEach items="${eformcal1 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_1_6" <c:if test="${row.field_id == sub6.field_datareferid6 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
		       </select> </div>  
		       
		       
		       
                <div class="agm"><span id="portCD1" class="port4 <c:if test="${sub7.field_datareferid7 > 0 }">portedmyid${sub7.field_id } portedmyid${sub7s.field_id }</c:if>"></span>
                Delete<select  style="float:right;width:50%" name="portCD1">
                           <option>-</option>
		          <c:forEach items="${eformcal1 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_1_7" <c:if test="${row.field_id == sub7.field_datareferid7 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
                </select>
		
		       </div>  
		          </div>
          
    </c:if>
    
    
    
    
    
    <br>
    
    
          
<c:set var="cal" value='${conditionscal2 }' />
<c:set var="sub1" value='${eformfieldsub2_1 }' />
<c:set var="sub2" value='${eformfieldsub2_2 }' />
<c:set var="sub3" value='${eformfieldsub2_3 }' />
<c:set var="sub4" value='${eformfieldsub2_4 }' />


<c:set var="sub5s" value='${eformfieldsub2_5s }' />
<c:set var="sub6s" value='${eformfieldsub2_6s }' />
<c:set var="sub7s" value='${eformfieldsub2_7s }' />



<c:set var="sub5" value='${eformfieldsub2_5 }' />
<c:set var="sub6" value='${eformfieldsub2_6 }' />
<c:set var="sub7" value='${eformfieldsub2_7 }' />
  <c:set var="sub8" value='${eformfieldsub2_8 }' />
<c:set var="sub9" value='${eformfieldsub2_9 }' />
<c:set var="sub10" value='${eformfieldsub2_10 }' />
    <c:if test="${cal.condition_id > 0}">   
    
   
		              <input id="${cal.condition_id}" value="${cal.condition_name}-${cal.condition_id}" 
		       data-custom="${cal.condition_id}" data-custom2="dataset" readonly=""> 
		     
		       <div id="calculatio_node1">  
		        <div class="agm"><span id="portP2" class="port5 <c:if test="${sub1.field_datareferid8 > 0 }">portedmyid${sub1.field_id }</c:if>"></span>Product 
		        <select style="float:right;width:50%" name="portP2"> 
		          <option>-</option>
		        <c:forEach items="${eformcal2 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_2_8" <c:if test="${row.field_id == sub1.field_datareferid8 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
		        </select></div>
		        
		        
		        
		        
		       <div class="agm"><span id="portL2" class="port5 <c:if  test="${sub2.field_datareferid9 > 0 }">portedmyid${sub2.field_id }</c:if>"></span>Lot 
		       <select style="float:right;width:50%" name="portL2">
       <option>-</option>
		         <c:forEach items="${eformcal2 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_2_9" <c:if test="${row.field_id == sub2.field_datareferid9}">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
		       </select></div>
		       
		       
		       
		        <div class="agm"><span id="portO2" class="port4 <c:if  test="${sub3.field_datareferid10 > 0 }">portedmyid${sub3.field_id }</c:if>"></span>Order 
		        <select style="float:right;width:50%" name="portO2"> 
		               <option>-</option>
		         <c:forEach items="${eformcal2 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_2_10" <c:if test="${row.field_id == sub3.field_datareferid10 }">selected</c:if>${row.field_label }</option>
		        </c:forEach>
		        
		        </select></div>
		        
		         	 <div class="agm" title="ยอดคงเหลือก่อนเบิก"><span id="portR2" class="port4 <c:if test="${sub9.field_datareferid18 > 0 }">portedmyid${sub9.field_id }</c:if>"></span>
		         	 Balance Before 
		         	 <select style="float:right;width:50%" name="portA2"> 
 				 
 				        <option>-</option>
		       <c:forEach items="${eformcal2 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_2_18" <c:if test="${row.field_id == sub9.field_datareferid18 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
 				 </select></div>
		        
		        
 				 <div class="agm"  title="ยอดคงเหลือหลังเบิก"><span id="portR1" class="port4 <c:if test="${sub4.field_datareferid11 > 0 }">portedmyid${sub4.field_id }</c:if>">
 				 </span>Balance After <select style="float:right;width:50%" name="portR2"> 
 				 
 				        <option>-</option>
		       <c:forEach items="${eformcal2 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_2_11" <c:if test="${row.field_id == sub4.field_datareferid11 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
 				 </select></div>
 				 
 				 
 				 
 				 
 				 
 			<div class="agm"  title="คงค้างได้จาก นำ Order มาลบออกด้วย ยอดสะสม"><span id="portD2" class="port4 <c:if test="${sub8.field_datareferid17 > 0 }">portedmyid${sub8.field_id }
 					 	 	 </c:if>"></span>Due <select style="float:right;width:50%" name="portD2"> 
 				 
 				        <option>-</option>
		       <c:forEach items="${eformcal2 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_2_17" <c:if test="${row.field_id == sub8.field_datareferid17 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
 				 </select>
 				 </div>
 				 
 				 
 				 
 				 
 				 	 	 <div class="agm" title="ยอดเบิกหรือผลิตสะสม"><span id="portT2" class="port4 <c:if test="${sub10.field_datareferid20 > 0 }">portedmyid${sub10.field_id }</c:if>"></span>
 				 	 	Total
 				 	 	 <select style="float:right;width:50%" name="portT2"> 
 				 
 				        <option>-</option>
		       <c:forEach items="${eformcal2 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_2_20" <c:if test="${row.field_id == sub10.field_datareferid20 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
 				 </select></div>
 				 
		       <div class="agm"><span id="portCU2" class="port4 <c:if test="${sub5.field_datareferid12 > 0 }">portedmyid${sub5.field_id } portedmyid${sub5s.field_id }</c:if>"></span>
		       Update<select style="float:right;width:50%" name="portCU2">
		              <option>-</option>
		         <c:forEach items="${eformcal2 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_2_12" <c:if test="${row.field_id == sub5.field_datareferid12 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
		       </select></div>
		       
		       
		       
		       <div class="agm"><span id="portCI2" class="port4 <c:if test="${sub6.field_datareferid13 > 0 }">portedmyid${sub6.field_id } portedmyid${sub6s.field_id }</c:if>"></span>
		       Insert<select  style="float:right;width:50%" name="portCI2">
		                  <option>-</option>
		         <c:forEach items="${eformcal2 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_2_13" <c:if test="${row.field_id == sub6.field_datareferid13 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
		       </select> </div>  
		       
		       
		       
                <div class="agm"><span id="portCD2" class="port4 <c:if test="${sub7.field_datareferid14 > 0 }">portedmyid${sub7.field_id } portedmyid${sub7s.field_id }</c:if>"></span>
                Delete<select  style="float:right;width:50%" name="portCD2">
                           <option>-</option>
		          <c:forEach items="${eformcal2 }" var="row" varStatus="count">    
		        
		       		 <option value ="${row.field_id }_${cal.condition_id }_2_14" <c:if test="${row.field_id == sub7.field_datareferid14 }">selected</c:if>>${row.field_label }</option>
		        </c:forEach>
                </select>
		
		       </div>  
		          </div>
          
    </c:if>
    
    
    
    
       </div>
        
        
  </div>
  
 
  
  
  
     
  </div>
  
          <div class="col-4" style="display:none">
    
     <div class="node" id="node2" >
      <div class="agm style="padding-top:10px">

JOIN : E-form
       <button type="button"  data-bs-toggle="modal" data-bs-target="#large2" style="float:right;border: 1px #fff" 
        onclick='loaddataset2("dataset_node2")'>+</button>
   <hr>
     </div>   
     INPUT          
       <div id="dataset_node2">
       <c:if test="${conditionssub.condition_id > 0}">  
       <div class="agm"><span id="port2" class="port2 myform"></span><input id="${conditionssub.condition_id}" value="${conditionssub.condition_name}-${conditionssub.condition_id}" 
       data-custom="${conditionssub.condition_id}" data-custom2="dataset" readonly="">

       </div>
     
       </c:if>
              <c:if test="${conditionssub2.condition_id > 0}">  
       <div class="agm"><span id="port2" class="port2 myform2"></span><input id="${conditionssub2.condition_id}" value="${conditionssub2.condition_name}-${conditionssub2.condition_id}" 
       data-custom="${conditionssub2.condition_id}" data-custom2="dataset" readonly=""></div>
       </c:if>
              <c:if test="${conditionssub3.condition_id > 0}">  
       <div class="agm"><span id="port2" class="port2 myform3"></span><input id="${conditionssub3.condition_id}" value="${conditionssub3.condition_name}-${conditionssub3.condition_id}" 
       data-custom="${conditionssub3.condition_id}" data-custom2="dataset" readonly=""></div>
       </c:if>
       </div>
     
  
  </div>
  
 
 
  

  </div>
</div>

</div>



                                  
                                       </div> 

  
                                        </div>
                                     
              <br>  <br>
                   <button type="button" class="btn btn-outline-secondary" onclick="savetest()">Test</button>      

               <button type="Submit"  id="Submit" class="btn btn-success " style="float:right">Save</button>    
                     
                                     
                                     
                                     
                                     
                                     
                                     
                                    </div>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                         </div>     
                                
      
    </div>        
                  
                
                                
     </form:form>               
                </section>
       

            </div>
        </div>
    </div>
    <!-- END: Content-->



 <div class="modal fade text-start" id="large" tabindex="-1" aria-labelledby="myModalLabel17" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel17">Data Set</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                          <input type="hidden"  id="dataset_node" value="">
                                                         <select class="form-select select2" id="dataset_data">
                                                <c:forEach items="${datasets }" var="row" varStatus="count">
                                                
                             
                               <c:set var="data" value='${row.dataset_1 }' />
                               
                                                         <option value="${row.dataset_id }"  data-custom="${data}">${row.dataset_name }</option>
                                                 </c:forEach>
                                                         </select>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="adddataset()">Import</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
    
    
    
    
    
     <div class="modal fade text-start" id="large2" tabindex="-1" aria-labelledby="myModalLabel18" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel17">E-form</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                          <input type="hidden"  id="dataset_node2" value="">
                                                         <select class="form-select select2" id="dataset_data2">
                                                <c:forEach items="${conditionadd }" var="row" varStatus="count">
                                                
                             
                               <c:set var="data" value='${row.condition_name }-${row.condition_id }' />
                               
                                                         <option value="${row.condition_id }"  data-custom="${data}">${row.condition_name }</option>
                                                 </c:forEach>
                                                         </select>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="adddataset2()">Import</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
    
    
    
    
    
        <div class="modal fade text-start" id="large3" tabindex="-1" aria-labelledby="myModalLabel18" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel17">E-form</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                          <input type="hidden"  id="calculation_node" value="">
                                                          
                                                          
                                                          
                                                         <select class="form-select select2" id="calculation_data">
                                                <c:forEach items="${conditionadd }" var="row" varStatus="count">
     
                                                         <option value="${row.condition_id }"  data-custom="${row.condition_name }">${row.condition_name }</option>
                                                 </c:forEach>
                                                         </select>
                                                        </div>
                                                        
                                                           <div  id="calculation_field" style="display:none"></div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="adddataset3()">Import</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>



    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>

 <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>




<%@ include file="common/mainfoot.jspf" %>  

 
   <%@ include file="other/alert.jsp" %>

  <c:if test="${save == 'success'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Success');
    	   edit = false;
      });
    
    </script>
</c:if>
 
    <script>

    $("#Submit").click(function() {
        edit = false;
    });
    
    window.onbeforeunload = function() {
       if(edit == true){
    	   return 'คุณยังไม่ได้บันทึก ต้องการออกจากหน้านี้หรือไม่?'; 
       }
    };
    
    
    function loaddataset(node){
    	
    	$("#dataset_node").val(node);
    }
  function loaddataset2(node){
    	
    	$("#dataset_node2").val(node);
    }
 
	  function loadCalculation(node){
	  	
	  	$("#calculation_node").val(node);
	  }
    
    function adddataset(){
   	    
    	 var node = $("#dataset_node").val();
    	 var data = $("#dataset_data").data('custom');
    	 var dataid = $("#dataset_data").val();
    	 
    	 //$('#'+node).html('');
    	 
    	 var selectedOption = document.querySelector('#dataset_data option:checked');
    	var data = selectedOption.dataset.custom;
    	 
    	
    	
   
    	let NewInput;
    	NewInput = document.createElement('div');
    	NewInput.className = 'agm';
    	NewInput.innerHTML = '<span id="port2" class="port2"></span><input id="2" value="'+data+'" data-custom ="'+dataid+'" data-custom2 ="dataset" readonly>';
        
        //dataset_data
       // dataset_node
       

	   const nodePreview = document.querySelector('#'+node);
	   nodePreview.appendChild(NewInput);
    }
     
    
    
    
    function adddataset2(){
   	    
   	 var node = $("#dataset_node2").val();
   	 var data = $("#dataset_data2").data('custom');
   	 var dataid = $("#dataset_data2").val();
   	 
   	 //$('#'+node).html('');
   	 
   	 var selectedOption = document.querySelector('#dataset_data2 option:checked');
   	var data = selectedOption.dataset.custom;
   	 
   	
   	
  
   	let NewInput;
   	NewInput = document.createElement('div');
   	NewInput.className = 'agm';
   	NewInput.innerHTML = '<span id="port2" class="port2"></span><input id="2" value="'+data+'" data-custom ="'+dataid+'" data-custom2 ="dataset" readonly><span id="myform2" class="port port1"></span>';
       
       //dataset_data
      // dataset_node
      

	   const nodePreview = document.querySelector('#'+node);
	   nodePreview.appendChild(NewInput);
   }
    
    
    

    	
    	
    	/*
    	if($("#calculation_port1").val() != null  ){
    		
    		ports =  1; 
    	} 
    	else if($("#calculation_port2").val() != null ){
	    		
	    		ports =  2; 
	    	} 
       	else if($("#calculation_port3").val() != null ){
    		
    		ports =  3; 
    	} 
    	
       	else if($("#calculation_port4").val() != null  ){
    		
    		ports =  4; 
    	} 
    	
       	else if($($("#calculation_port5").val() != null ){
    		
    		ports =  5; 
    	} 
    	*/

    
    
    
    
    function adddataset3(){
 
      	 var dataid = $("#calculation_data").val();
      	 

      	 
    	 getdata2("/eformfield-condition/"+dataid,"calculation_field");

      }
    

   

    
    var port = 1;
    function adddatasets3(){
   	    
     	 var node = $("#calculation_node").val();
        	
      	 var selectedOption = document.querySelector('#calculation_data option:checked');
        var data = selectedOption.dataset.custom;

     	var dataloot = $("#calculation_field").html();
    	 var dataid = $("#calculation_data").val();
   	   var dataoption1 = "";
   	 var dataoption2 = "";
   	 var dataoption3 = "";
   	 var dataoption4 = "";
   	 var dataoption5 = "";
   	 var dataoption6 = "";
	 var dataoption7 = ""; 
	 var dataoption8 = ""; 	
	 var dataoption9 = ""; 
	 var dataoption10 = ""; 	
	 
			  	if($("#calculation_port1").val() <  1   ){
					
			  		port =  1; 
				}else if($("#calculation_port2").val() <  1  ){
			    		
					port =  2; 
			    }else if($("#calculation_port3").val() <  1  ){
					
			   		port =  3; 
				} else if($("#calculation_port4").val() <  1   ){
					
			   		port =  4; 
				} 
				else if($("#calculation_port5").val() <  1  ){
					
			   		port =  5; 
				} 
			  
   				else {
					
			   		port =  1; 
				} 
      
 
   	   if(dataloot != ""){

   	   	     var values = dataloot.split(',');

   	         for (var i = 0; i < values.length; i++) {
   	        	    	        	 
   	            var valuesid = values[i].split('|');
   	        	 
   	            if(port == 1){
   	          	 dataoption1 =  dataoption1+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_1' >"+valuesid[0]+"</option>";
   	        	 dataoption2 =  dataoption2+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_2' >"+valuesid[0]+"</option>";
   	        	 dataoption3 =  dataoption3+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_3' >"+valuesid[0]+"</option>";
   	        	 dataoption4 =  dataoption4+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_4'>"+valuesid[0]+"</option>";
   	     	     dataoption5 =  dataoption5+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_5' >"+valuesid[0]+"</option>";
   	     	     dataoption6 =  dataoption6+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_6'>"+valuesid[0]+"</option>";
   	             dataoption7 =  dataoption7+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_7'>"+valuesid[0]+"</option>";
   	         	 dataoption8 =  dataoption8+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_15'>"+valuesid[0]+"</option>";
   	      		 dataoption9 =  dataoption9+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_16'>"+valuesid[0]+"</option>";
   	      	     dataoption10 =  dataoption10+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_19'>"+valuesid[0]+"</option>";
   	            }
   	            if(port == 2){
   	            	
   	         	 dataoption1 =  dataoption1+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_8' >"+valuesid[0]+"</option>";
   	        	 dataoption2 =  dataoption2+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_9' >"+valuesid[0]+"</option>";
   	        	 dataoption3 =  dataoption3+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_10' >"+valuesid[0]+"</option>";
   	             dataoption7 =  dataoption7+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_11'>"+valuesid[0]+"</option>";
   	        	 dataoption4 =  dataoption4+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_12'>"+valuesid[0]+"</option>";
   	     	     dataoption5 =  dataoption5+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_13' >"+valuesid[0]+"</option>";
   	     	     dataoption6 =  dataoption6+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_14'>"+valuesid[0]+"</option>";
   	     	     dataoption8 =  dataoption8+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_17'>"+valuesid[0]+"</option>";
	      		 dataoption9 =  dataoption9+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_18'>"+valuesid[0]+"</option>";
	      	   dataoption10 =  dataoption10+"<option value='"+valuesid[1]+"_"+dataid+"_"+port+"_20'>"+valuesid[0]+"</option>";
   	            }
   	       

   	         }
   	      
   	      }

     	let NewInput;
     	NewInput = document.createElement('div');
     	NewInput.className = 'agm';
     	NewInput.innerHTML = `
     		 <div class="agm"><input id="" value="`+data+`" data-custom="`+dataid+`" readonly=""></div>
   	        <div class="agm"><span id="portP`+port+`" class="port5"></span>Product <select   name="portP`+port+`" style="float:right;width:40%">`+dataoption1+`</select></div>
   		     <div class="agm"><span id="portL`+port+`" class="port5"></span>Lot <select  name="portL`+port+`"   style="float:right;width:40%">`+dataoption2+`</select></div>
   		       <div class="agm"><span id="portO`+port+`" class="port4"></span>Order <select id="s1" name="portO`+port+`"  style="float:right;width:40%">`+dataoption3+`</select></div>
   			 <div class="agm" title="ยอดรับแล้ว"><span id="portA`+port+`" class="port4"></span>Balance Before <select id="s1" name="portD`+port+`"  style="float:right;width:40%">`+dataoption9+`</select></div>
   		    <div class="agm"   title="ยอดคงเหลือได้จาก นำ Amount มาลบออกดว้ย ยอดเบิก หรือ ยอดผลิต "><span id="portR`+port+`" class="port4"></span>Balance After <select id="s1" name="portD`+port+`"  style="float:right;width:40%">`+dataoption7+`</select></div>
   		 <div class="agm" title="คงค้างได้จาก นำ Order มาลบออกด้วย ยอดสะสม"><span id="portD`+port+`" class="port4"></span>Due <select id="s1" name="portD`+port+`"  style="float:right;width:40%">`+dataoption8+`</select></div>
   		 <div class="agm" title="ยอดเบิกหรือผลิตสะสม"><span id="portT`+port+`" class="port4"></span>Total <select id="s1" name="portD`+port+`"  style="float:right;width:40%">`+dataoption10+`</select></div>
   		     <div class="agm"><span id="portCU`+port+`" class="port4"></span>Update<select  name="portCU`+port+`"  style="float:right;width:40%">`+dataoption4+`</select></div>
   		      <div class="agm"><span id="portCI`+port+`"   class="port4"></span>Insert<select  name="portCI`+port+`"  style="float:right;width:40%">`+dataoption5+`</select></div>  
   		       <div class="agm"><span id="portCD`+port+`"  class="port4"></span>Delete<select name="portCD`+port+`"  style="float:right;width:40%">`+dataoption6+`</select></div> `;
         
         //dataset_data
        // dataset_node
        

  	   const nodePreview = document.querySelector('#'+node);
  	   nodePreview.appendChild(NewInput);

     }
    
    
    
    
    
    
    
    
    
    
    
    
    

    function   addCondition(){
   	    
   	 var node = $("#dataset_node").val();
   	 var data = $("#dataset_data2").data('custom');
   	 
   	 
   	 $('#condition_node1').html('');
   	 
   	 var selectedOption = document.querySelector('#dataset_data2 option:checked');
   	var data = selectedOption.dataset.custom;
   	 
   	
   
  
   	let NewInput;
   	NewInput = document.createElement('div');
   	NewInput.className = 'agm';
   	
   	
   	if(data == "Type 1"){
   	NewInput.innerHTML = `
   		INPUT
   		     <div class="agm style="padding-top:10px">
   		    <span id="port2" class="port2"></span>
   		     <input id="2" value="รายการ" readonly>
   		  <span id="port1ss" class="port port1"></span>
   		   </div> 
   		   <div class="agm style="padding-top:10px">
  		    <span id="port2" class="port2"></span>
  		     <input id="2" value="LOT" readonly>
  		  <span id="port1sss" class="port port1"></span>
  		   </div> 
  		   <div class="agm style="padding-top:10px">
 		    <span id="port2" class="port2"></span>
 		     <input id="2" value="Order" readonly>
 		  <span id="port1sssss" class="port port1"></span>
 		   </div> 
   		     <div class="agm style="padding-top:10px">
   		    <span id="port2" class="port2"></span>
   		    <input id="2ssss" value="จำนวน" readonly>
   		   </div> 
   		        <hr>
   		        
   		        OUTPUT
   		     <div class="agm style="padding-top:10px">
   		    <span id="port2" class="port2"></span>
   		     <input id="2" value="คงเหลือ" readonly>
   		   </div> 
   		     <div class="agm style="padding-top:10px">
   		    <span id="port2" class="port2"></span>
   		    <input id="2" value="สะสม" readonly>
   		   </div> 
   	`;
       
   	}
   	
   	if(data == "Type 2"){
   	   	NewInput.innerHTML = `
   	   	
   	   		INPUT
   	   	 <div class="agm style="padding-top:10px">
		    <span id="port2" class="port2"></span>
		     <input id="2" value="รายการ" readonly>
		  <span id="port1ss" class="port port1"></span>
		   </div>
   	   		     <div class="agm style="padding-top:10px">
   	   		    <span id="port2" class="port2"></span>
   	   		     <input id="2" value="เป้าหมาย" readonly>
   	   		  <span id="port1" class="port port1"></span>
   	   		   </div> 
   	   		     <div class="agm style="padding-top:10px">
   	   		    <span id="port2" class="port2"></span>
   	   		    <input id="2" value="จำนวน" readonly>
   	   		   </div> 
   	   		        <hr>
   	   		        
   	   		        OUTPUT
   	   		     <div class="agm style="padding-top:10px">
   	   		    <span id="port2" class="port2"></span>
   	   		     <input id="2" value="ผลประเมิน" readonly>
   	   		   </div> 
   	   		    
   	   	`;
   	       
   	   	}
	if(data == "Type 3"){
   	   	NewInput.innerHTML = `
   	   	
   	   		INPUT
   	   	 <div class="agm style="padding-top:10px">
		    <span id="port2" class="port2"></span>
		     <input id="2" value="รายการ" readonly>
		  <span id="port1ss" class="port port1"></span>
		   </div>
   	   		     <div class="agm style="padding-top:10px">
   	   		    <span id="port2" class="port2"></span>
   	   		     <input id="2" value="เป้าหมาย" readonly>
   	   		  <span id="port1" class="port port1"></span>
   	   		   </div> 
   	   		     <div class="agm style="padding-top:10px">
   	   		    <span id="port2" class="port2"></span>
   	   		    <input id="2" value="จำนวน" readonly>
   	   		   </div> 
   	   		        <hr>
   	   		        
   	   		        OUTPUT
   	   		     <div class="agm style="padding-top:10px">
   	   		  <span id="port2" class="port2"></span>
   	   		     <select> <option>></option><option><</option><option>==</option> </select>
   	   		 <input id="2" value="ผลประเมิน" >
   	   	 <input id="2" value="ผลประเมิน" >
   	   		   </div> 
   		     <div class="agm style="padding-top:10px">
  	   		  <span id="port2" class="port2"></span>
  	   		     <select> <option>></option><option><</option><option>==</option> </select>
  	   		 <input id="2" value="ผลประเมิน" >
  	   	 <input id="2" value="ผลประเมิน" >
  	   		   </div> 
  		     <div class="agm style="padding-top:10px">
  	   		  <span id="port2" class="port2"></span>
  	   		     <select> <option>></option><option><</option><option>==</option> </select>
  	   		 <input id="2" value="ผลประเมิน" >
  	   	 <input id="2" value="ผลประเมิน" >
  	   		   </div> 	    
   	   	`;
   	       
   	   	}
       //dataset_data
      // dataset_node
      

	   const nodePreview = document.querySelector('#condition_node1');
	   nodePreview.appendChild(NewInput);
   }
   
  
    (function($){
    	$.fn.extend({ 
    		bezier: function(options)
    		{
    			var defaults = 	{
    				strokeColor : '#999',
    				strokeWidth : 2,
    				opacity : 1,
    				fill : 'none',
    				animate : true,
    				animationDirection : 'right',
    				animationDuration : 0.75
    			};
    			
    			
    			// Color wheel stuff
    			var color_wheel = [];
    			var frequency = .3;
                for (var i = 0; i < 32; ++i)
                {
                   red   = Math.sin(frequency*i + 0) * 127 + 128;
                   green = Math.sin(frequency*i + (2*Math.PI/3)) * 127 + 128;
                   blue  = Math.sin(frequency*i + (4*Math.PI/3)) * 127 + 128;
                
                   color_wheel.push(RGB2Color(red,green,blue));
                }
                var color_index = 0;
                
                  function RGB2Color(r,g,b)
    	          {
    	            return '#' + byte2Hex(r) + byte2Hex(g) + byte2Hex(b);
    	          }
    	          
    	          
    	    	  function byte2Hex(n)
    	          {
    	            var nybHexString = "0123456789ABCDEF";
    	            return String(nybHexString.substr((n >> 4) & 0x0F,1)) + nybHexString.substr(n & 0x0F,1);
    	          }
    	          // Color wheel stuff  
                
                
    			
    			//override default options with user set options
    			var settings = $.extend(defaults, options);
    			
    			var me = {};
    			
    			me.init = function(initObj){
    				if (initObj) {
    					$.each(initObj, function(index, value){
    						settings[index] = value;
    					});
    				}
    			};

    			me.set = function(prop, val) {
    				settings[prop] = val;
    			};

    			me.on = function(el1, el2) {
    				var $el1 = $(el1);
    				var $el2 = $(el2);
    				if ($el1.length && $el2.length) {
    					var svgheight, p, svgleft, svgtop, svgwidth;
    	
    					var el1pos = $(el1).offset();
    					var el2pos = $(el2).offset();
    	
    					var el1H = $(el1).outerHeight();
    					var el1W = $(el1).outerWidth();
    	
    					var el2H = $(el2).outerHeight();
    					var el2W = $(el2).outerWidth();
    	
    					svgleft = Math.round(el1pos.left + el1W);
    					svgwidth = Math.round(el2pos.left - svgleft);
    	
    					var curvinessFactor, cpt;
    	
    					////Determine which is higher/lower
    					if ((el2pos.top + (el2H / 2)) <= (el1pos.top + (el1H / 2))) 
    					{
    						// console.log("low to high");
    						svgheight = Math.round((el1pos.top + el1H / 2) - (el2pos.top + el2H / 2));
    						svgtop = Math.round(el2pos.top + el2H / 2) - settings.strokeWidth;
    						cpt = Math.round(svgwidth * Math.min(svgheight / 300, 1));
    						p = "M0," + (svgheight + settings.strokeWidth) + " C" + cpt + "," + (svgheight + settings.strokeWidth) + " " + (svgwidth - cpt) + "," + settings.strokeWidth + " " + svgwidth + "," + settings.strokeWidth;
    					} 
    					else 
    					{
    						// console.log("high to low");
    						svgheight = Math.round((el2pos.top + el2H / 2) - (el1pos.top + el1H / 2));
    						svgtop = Math.round(el1pos.top + el1H / 2) - settings.strokeWidth;
    						cpt = Math.round(svgwidth * Math.min(svgheight / 300, 1));
    						p = "M0," + settings.strokeWidth + " C" + cpt + ",0 " + (svgwidth - cpt) + "," + (svgheight + settings.strokeWidth) + " " + svgwidth + "," + (svgheight + settings.strokeWidth);
    					}
    					$ropebag = $('#ropebag').length ? $('#ropebag') : $('body').append($("<div id='ropebag' />")).find('#ropebag');
    	
    					var svgnode = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
    					var newpath = document.createElementNS('http://www.w3.org/2000/svg', "path");
    					
    					var color_to_use = (settings.strokeColor != 'rainbow') ? settings.strokeColor : color_wheel[color_index];
    					color_index = color_index + 1;
    					if (color_index == 32)
    					{
    						color_index = 0;
    					}
    					newpath.setAttributeNS(null, "d", p);
    					newpath.setAttributeNS(null, "stroke", color_to_use);
    					newpath.setAttributeNS(null, "stroke-width", settings.strokeWidth);
    					newpath.setAttributeNS(null, "opacity", settings.opacity);
    					newpath.setAttributeNS(null, "fill", settings.fill);
    					svgnode.appendChild(newpath);
    					$(svgnode).css({
    						left : svgleft,
    						top : svgtop,
    						position : 'absolute',
    						width : svgwidth,
    						height : svgheight + settings.strokeWidth * 2,
    						minHeight : '20px'
    					});
    					$ropebag.append(svgnode);
    					if (settings.animate) 
    					{
    						var pl = newpath.getTotalLength();
    						
    						// Set up the starting positions
    						newpath.style.strokeDasharray = pl + ' ' + pl;
    	
    						if (settings.animationDirection == 'right') 
    						{
    							newpath.style.strokeDashoffset = pl;
    						} 
    						else 
    						{
    							newpath.style.strokeDashoffset = -pl;
    						}
    	
    						// IE sucks, dont expect this to work. 
    						newpath.getBoundingClientRect();
    						newpath.style.transition = newpath.style.WebkitTransition = 'stroke-dashoffset ' + settings.animationDuration + 's ease-in-out';
    						newpath.style.strokeDashoffset = '0';
    					}
    				}
    			};

    			me.off = function() {
    				$("#ropebag").empty();
    			};
    			
    			// Redraw upon resizing the window
    			var selector = this.selector;
    			$(window).resize(function(){
    			     me.off();
    			     $(selector).each(function(){
    					var theID = this.id;
    				    $("."+theID).each(function(i,e){
    				      var rand = Math.random() * 0.7 + 0.3;
    				      me.set('animationDuration', rand);
    				      me.on($("#"+theID), e);
    				    });
    				});
    			});
    			
    			// Loop through each parent and draw the lines connecting children
    			return this.each(function(){
    				var theID = this.id;
    			    $("."+theID).each(function(i,e){
    			      var rand = Math.random() * 0.7 + 0.3;
    			      me.set('animationDuration', rand);
    			      me.on($("#"+theID), e);
    			    });
    			});
    		}
    	});
    })(jQuery);


    function run(){
  
   	 $('.port1').bezier({strokeColor : 'blue',});
	 $('.port3').bezier({strokeColor : 'red',});
  
        

        
 
        
       
        
        
        
        
     
    }
    function run2(){
    	 $('svg').css('display', 'none');
     
    }

    
 

    var idmain = 0;
    var setdata = null;
    var setdata2 = null;
    
$(document).on('click', '.port1', function () {
	
	
	//$(".unactives").removeClass("unactives");
	//$(".unactives2").removeClass("unactives2");
	$('.port1').css('background-color', 'blue');
	
	
 var divId = $(this).attr('id');
 idmain = divId;
 idmain2 = divId;
 
 //alert(idmain2);
 
 
 $('#'+divId).css('background-color', 'green');
 $('#'+divId).addClass("unactives");
 
 $('.port2').css('background-color', 'red');
 $('.port2').addClass("actives");

 $('.port5').css('background-color', 'red');
 $('.port5').addClass("actives2");
 
 
 // เลือก <input> element ที่อยู่ในคลาส "agm" ที่เกี่ยวข้องกับ <span> element
 var inputElement = $(this).closest('.agm').find('input');
 
 // ถ้าพบ <input> element
 if (inputElement.length > 0) {
     // ดึงค่า value ของ <input> element
     var inputValue = inputElement.val();
     // แสดงค่าที่ดึงได้ใน alert
 
     setdata = inputValue;

 }

});






$(document).on('click', '.port3', function () {
	
	
	$(".unactives2").removeClass("unactives2");
	$('.port3').css('background-color', 'blue');
	
	
 var divId = $(this).attr('id');
 idmain2 = divId;
 idmain = divId;
 $('#'+divId).css('background-color', 'green');
 $('#'+divId).addClass("unactives2");
 
 $('.port4').css('background-color', 'red');
 $('.port4').addClass("actives2");

 
 // เลือก <input> element ที่อยู่ในคลาส "agm" ที่เกี่ยวข้องกับ <span> element
 var inputElement = $(this).closest('.agm').find('input');
 
 
 
 // ถ้าพบ <input> element
 if (inputElement.length > 0) {
     // ดึงค่า value ของ <input> element
     var inputValue = inputElement.val();
     // แสดงค่าที่ดึงได้ใน alert
 
     setdata2 = inputValue;


 }

});





function getdata(url,div){



				        $.ajax({
				        type:'GET',
				        url:url,
				        data: {ID : 1},
				        
				     success: function(data){
				    $("#"+div).html(data);	
			
				     }
				});
	
	return false;
 
	}

function getdata2(url,div){


    $.ajax({
    type:'GET',
    url:url,
    data: {ID : 1},
    
 success: function(data){
$("#"+div).html(data);	
adddatasets3();
 }
});

return false;

}


$(document).on('click', '.unactives', function () {
	
	$("."+idmain).removeClass(idmain);
	$(".actives").removeClass("actives");
	$(".unactives").removeClass("unactives");
	$('.port1').css('background-color', 'blue');
	$('.port2').css('background-color', 'lightblue');
	$(this).css('background-color', 'blue');
	 $('svg').css('display', 'none');
	
	
	
	 
	  // เลือก <input> element ที่อยู่ในคลาส "agm" ที่เกี่ยวข้องกับ <span> element
	    var inputElement = $(this).closest('.agm').find('input');
	    
	 if(idmain == "myform"){
		  $("#condition_sub").val(0); 
		 
	 } 
	    
	 if(idmain == "myform2"){
		  $("#condition_sub2").val(0); 
		 
	 } 
	    
	 if(idmain == "myform3"){
		  $("#condition_sub3").val(0); 
		 
	 } 
	    
	    // ถ้าพบ <input> element
	    if (inputElement.length > 0) {
	        // ดึงค่า value ของ <input> element
	        var inputValue = inputElement.val();
	        var datacustom = $(inputElement).data('custom');
		    var datacustom2 = $(inputElement).data('custom2');
	        // แสดงค่าที่ดึงได้ใน alert
	       // alert(inputValue);
	    }
	 
	    
	    var selectDivs = document.querySelectorAll('.Selectdata');

	 // วนลูปทุก <div> element
	 selectDivs.forEach(function(div) {
	     // หา <label> element ภายใน <div> element
	     var label = div.querySelector('label.form-label');
	     
	     // ตรวจสอบว่า <label> มีข้อความเป็น "Select" หรือไม่
	     if (label && label.textContent.trim() === setdata) {
	         // หา <select> element ที่อยู่ร่วมกับ <label> "Select"
	         var select = div.querySelector('select.form-select.mydata');


	         var checkbox = div.querySelector('span.checkbox');
	       
	         

	         // ทำอะไรกับ <select> element ที่พบได้ที่นี่
	         console.log(select);
	      
	         if(select){
		    
	        	 
	        $(select).remove();
			         
	        
	        
	        
	         var selectElement = document.createElement('select');
	         selectElement.classList.add('form-select'); 
	         selectElement.classList.add('mydata'); 

	         // เพิ่ม <select> element หลังจาก <label> element
	         label.parentNode.appendChild(selectElement);
	         
	         }
	         
	         if(checkbox){
	        	 
	 
	        	 $(checkbox).html(''); 
	        	 
	       	  

	         }
	     }
	     
	     
	     
	     
	     
	     
	     
	     
	     
	 });

	    
	  
	      
	 $('.port1').bezier({strokeColor : 'blue',});
	 $('.port3').bezier({strokeColor : 'red',});
	 idmain= null;
	 idmain2= null;
	
	
	
	
    
});



















$(document).on('click', '.actives', function () {

	
	if(idmain != null){
		
		$("."+idmain).removeClass(idmain);
		$(".actives").removeClass("actives");
		
		
		$(this).addClass(idmain);
		$('.port1').css('background-color', 'blue');
		$('.port2').css('background-color', 'lightblue');
		$(this).css('background-color', 'blue');
		 $('svg').css('display', 'none');
		 $('.port').bezier({strokeColor : 'red',});
		 $(".unactives").removeClass("unactives");
		 
		  // เลือก <input> element ที่อยู่ในคลาส "agm" ที่เกี่ยวข้องกับ <span> element
		    var inputElement = $(this).closest('.agm').find('input');
		    
		
         
		    
		    // ถ้าพบ <input> element
		    if (inputElement.length > 0) {
		        // ดึงค่า value ของ <input> element
		        var inputValue = inputElement.val();
		        var datacustom = $(inputElement).data('custom');
			    var datacustom2 = $(inputElement).data('custom2');
		        // แสดงค่าที่ดึงได้ใน alert
		        
		        //alert(datacustom);
		 
		    }
		 
		    
		    
		    if(idmain == "myform"){
		    	
		    	var extractedPart = inputValue.split('-')[1];
		        $("#condition_sub").val(extractedPart);
		        getdata("/loadcondition-condition/"+extractedPart,"tabledata2");
		     
            }
			 if(idmain == "myform2"){
					    	
					    	var extractedPart = inputValue.split('-')[1];
					        $("#condition_sub2").val(extractedPart);
					        getdata("/loadcondition-condition/"+extractedPart,"tabledata2");
					     
			            }
					    
			 if(idmain == "myform3"){
			 	
			 	var extractedPart = inputValue.split('-')[1];
			     $("#condition_sub3").val(extractedPart);
			     getdata("/loadcondition-condition/"+extractedPart,"tabledata2");
			  
			 }
			 
		    
		    var selectDivs = document.querySelectorAll('.Selectdata');

		 // วนลูปทุก <div> element
		 selectDivs.forEach(function(div) {
		     // หา <label> element ภายใน <div> element
		     var label = div.querySelector('label.form-label');
		     
		     // ตรวจสอบว่า <label> มีข้อความเป็น "Select" หรือไม่
		     if (label && label.textContent.trim() === setdata) {
		         // หา <select> element ที่อยู่ร่วมกับ <label> "Select"
		         var select = div.querySelector('select.form-select.mydata');


		         var checkbox = div.querySelector('span.checkbox');
		       
		         

		         // ทำอะไรกับ <select> element ที่พบได้ที่นี่
		         console.log(select);
		      
		         if(select){
			         var hidden1 = div.querySelector('input[type="hidden"].form-control2');
			         var hidden2 = div.querySelector('input[type="hidden"].form-control3');
			         hidden1.value = datacustom;
			         hidden2.value = datacustom2; 	 
		        	 
		        $(select).remove();
				         
		        
		        
		        
		         var selectElement = document.createElement('select');
		         selectElement.classList.add('form-select'); 
		         selectElement.classList.add('mydata'); 
		    
		     
		         var values = inputValue.split(',');

		         for (var i = 0; i < values.length; i++) {
		        	 
		        	 if (values[i].trim() !== '') {
		             // สร้าง <option> element
		             var option = document.createElement('option');
		             option.value = values[i];
		             option.textContent = values[i];
		             
		             // เพิ่ม <option> ลงใน <select>
		             selectElement.appendChild(option);
		        	 }
		             
		             
		         }


		         // เพิ่ม <select> element หลังจาก <label> element
		         label.parentNode.appendChild(selectElement);
		         
		         }
		         
		         if(checkbox){
		        	 
		             var hidden1 = div.querySelector('input[type="hidden"].form-control2');
			         var hidden2 = div.querySelector('input[type="hidden"].form-control3');
			         hidden1.value = datacustom;
			         hidden2.value = datacustom2; 	 
		        	 
		        	 
		        	 $(checkbox).html(''); 
		        	 
		       	    var hiddenInput = document.createElement('input');
		       	    hiddenInput.type = 'hidden';
		         	hiddenInput.className = 'mydata';
		         	hiddenInput.value = "";
    		        checkbox.appendChild(hiddenInput);
    		        
    		        
    		        
		        	  var values = inputValue.split(',');
		        	 
		        	 for (var i = 0; i < values.length; i++) {
		        		 
		        		    if (values[i].trim() !== '') {
		        		  
		        		    	
		        		        // สร้าง <input> element ประเภท checkbox
		        		        var checkboxs = document.createElement('input');
		        		        checkboxs.type = 'checkbox';
		        		        checkboxs.className = 'mydatas';
		        		        checkboxs.value = values[i];
		 
		        		        
		        		        // สร้าง <label> element ที่มีค่าจาก values[i]
		        		        var label = document.createElement('label');
		        		        label.textContent = values[i];
		        		        
		        		        // เพิ่ม <input> และ <label> ลงใน <span class="checkbox">
		        		        checkbox.appendChild(checkboxs);
		        		        checkbox.appendChild(label);
		        		        
		        		        // เพิ่มขีดจำนวนระหว่าง checkbox
		        		        checkbox.appendChild(document.createElement('br'));
		        		        
		        		        checkboxs.addEventListener('change', function() {
		        		        
		        		            var selectedValues = [];
		        		            var checkboxes = checkbox.querySelectorAll('.mydatas:checked');
		        		            checkboxes.forEach(function(checkbox) {
		        		       
		        		            	
		        		                selectedValues.push(checkbox.value);
		        		            });
		        		            hiddenInput.value = selectedValues.join(',');
		        		        });
		        		        
		        		    }
		        		    
		        		    
		        		  
		        		}
		        

		         }
		     }
		     
		     
		     
		     
		     
		     
		     
		     
		     
		 });

		    
		  
		      
		    
		 idmain= null;
		 
		 
		 
	}
	

});
    

    
    
    
    
    var element = document.getElementById("e-form1");
    element.classList.add("active");  
    
    
 
   
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        



        
        
        
$(document).on('click', '.unactives2', function () {
	
	$("."+idmain2).removeClass(idmain2);
	$("."+idmain).removeClass(idmain);
	$(".actives2").removeClass("actives");
	$(".unactives2").removeClass("unactives2");
	
	
	$(".actives").removeClass("actives");
	$(".unactives").removeClass("unactives");
	
	$('.port3').css('background-color', 'blue');
	$('.port4').css('background-color', 'lightblue');
	
	
	$('.port1').css('background-color', 'blue');
	$('.port5').css('background-color', 'lightblue');
	
	$(this).css('background-color', 'lightblue');
	 $('svg').css('display', 'none');
	

	 
	 
	 

	 
	  // เลือก <input> element ที่อยู่ในคลาส "agm" ที่เกี่ยวข้องกับ <span> element
	    var inputElement = $(this).closest('.agm').find('input');
	    
	 if(idmain == "myform"){
		  $("#condition_sub").val(0); 
		 
	 } 
	    
	 if(idmain == "myform2"){
		  $("#condition_sub2").val(0); 
		 
	 } 
	    
	 if(idmain == "myform3"){
		  $("#condition_sub3").val(0); 
		 
	 } 
	    
	    // ถ้าพบ <input> element
	    if (inputElement.length > 0) {
	        // ดึงค่า value ของ <input> element
	        var inputValue = inputElement.val();
	        var datacustom = $(inputElement).data('custom');
		    var datacustom2 = $(inputElement).data('custom2');
	        // แสดงค่าที่ดึงได้ใน alert
	       // alert(inputValue);
	    }
	 
	  //alert(idmain2);
	
	    var selectDivs = document.querySelectorAll('.get'+idmain2);
	 // วนลูปทุก <div> element
	 selectDivs.forEach(function(div) {

    	     var hidden = div.querySelector('input[type="hidden"].form-control3');
	         var hidden1 = div.querySelector('input[type="hidden"].form-control2');
	         var hidden2 = div.querySelector('input[type="hidden"].form-control5');
	         var hidden3 = div.querySelector('input[type="hidden"].form-control6');
	         var hidden4 = div.querySelector('input[type="hidden"].form-control7');
	         var hidden5 = div.querySelector('input[type="hidden"].form-control8');
	         var hidden6 = div.querySelector('input[type="hidden"].form-control9');
	         var hidden7 = div.querySelector('input[type="hidden"].form-control10');
	         var hidden8 = div.querySelector('input[type="hidden"].form-control11');
	         
	         var hidden19 = div.querySelector('input[type="hidden"].form-control19');
	         var hidden20 = div.querySelector('input[type="hidden"].form-control20');
	         
	         
	         var hidden12 = div.querySelector('input[type="hidden"].form-control12');
	         var hidden13 = div.querySelector('input[type="hidden"].form-control13');
	         var hidden14 = div.querySelector('input[type="hidden"].form-control14');
	         var hidden15 = div.querySelector('input[type="hidden"].form-control15');
	         var hidden16 = div.querySelector('input[type="hidden"].form-control16');
	         var hidden17 = div.querySelector('input[type="hidden"].form-control17');
	         var hidden18 = div.querySelector('input[type="hidden"].form-control18');
	         
	         var hidden21 = div.querySelector('input[type="hidden"].form-control21');
	         var hidden22 = div.querySelector('input[type="hidden"].form-control22');
	         
	         var hidden23 = div.querySelector('input[type="hidden"].form-control23');
	         var hidden24 = div.querySelector('input[type="hidden"].form-control24');
	         
	         
	         hidden.value = "-";
	         hidden1.value = "-";
	         hidden2.value = "-";
	         hidden3.value = "-";
	         hidden4.value = "-";
	         hidden5.value = "-";
	         hidden6.value = "-";
	         hidden7.value = "-";
	         hidden8.value = "-";
	         hidden19.value = "-";
	         hidden20.value = "-";
	         
	         
	         hidden12.value = "-";
	         hidden13.value = "-";
	         hidden14.value = "-";
	         hidden15.value = "-";
	         hidden16.value = "-";
	         hidden17.value = "-";
	         hidden18.value = "-";
	         hidden21.value = "-";
	         hidden22.value = "-";
	         hidden23.value = "-";
	         hidden24.value = "-"; 
	         
	         
	         $("#calculation_port"+hidden7).val(0);
	         
	     
	 });
	 
	 
	 
	 
	 
	 
	
	 $('.port1').bezier({strokeColor : 'blue',});
	 $('.port3').bezier({strokeColor : 'red',});

	 idmain2= null;
	 idmain= null;
	 port = null;
	
});


        
        
       
$(document).on('click', '.actives2', function (event) {


	if(idmain2 != null){
		
	
		
		  // เลือก <input> element ที่อยู่ในคลาส "agm" ที่เกี่ยวข้องกับ <span> element
	    var inputElement = $(this).closest('.agm').find('select');
	    
	    if (inputElement.length > 0) {
	        // ดึงค่า value ของ <input> element
	        var inputValue = inputElement.val();

	       var extractedPart = inputValue.split('_')[0];
     
	        
	        if(extractedPart.trim() == "-"){
	        	
	        	alert("please select data");
	        }else{
	        	
	        //	$("."+idmain2).removeClass(idmain2);
	    	
	    		 
	    		 
	    		 
	    		  if (event.ctrlKey) {
	    			  
	    				
	    				$(this).addClass(idmain2);
	    				 $('.port1').bezier({strokeColor : 'blue',});
	    				 $('.port3').bezier({strokeColor : 'red',});
	    			 
	    		    } else {
	    		    	
	    		
	    		    	
	    		    	$(".actives2").removeClass("actives2");
	    	    		$(this).addClass(idmain2);
	    	    		$('.port3').css('background-color', 'blue');
	    	    		$('.port4').css('background-color', 'lightblue');
	    	    		
	    	    		$('.port1').css('background-color', 'blue');
	    	    		$('.port5').css('background-color', 'lightblue');
	    	    		
	    	    		$(this).css('background-color', 'blue');
	    	    		
	    	    		
	    	    		 $('svg').css('display', 'none');
	    	    		 
	    	    		 
	    	    		 $('.port1').bezier({strokeColor : 'blue',});
	    	    		 $('.port3').bezier({strokeColor : 'red',});
	    	    		 
	    	    		 
	    	    		 $(".unactives2").removeClass("unactives2");
	    	    		 $(".unactives").removeClass("unactives");
	    	    		 
	    		    }
	    		 
	    		 
	    		 
	    		// alert("value " + inputValue.split('_')[0]);
	    		 
	    		 $("#calculation_port"+inputValue.split('_')[2]).val(inputValue.split('_')[1]);
	    		 
	    		 
	    		   var selectDivs = document.querySelectorAll('.get'+idmain2);
	    			 selectDivs.forEach(function(div) {
	    			     // หา <label> element ภายใน <div> element
	    			     var label = div.querySelector('label.form-label');
	    			     
	 
	    			    	     var hidden = div.querySelector('input[type="hidden"].form-control3');
	    				         var hidden1 = div.querySelector('input[type="hidden"].form-control2');
	    				         var hidden2 = div.querySelector('input[type="hidden"].form-control5');
	    				         var hidden3 = div.querySelector('input[type="hidden"].form-control6');
	    				         var hidden4 = div.querySelector('input[type="hidden"].form-control7');
	    				         var hidden5 = div.querySelector('input[type="hidden"].form-control8');
	    				         var hidden6 = div.querySelector('input[type="hidden"].form-control9');
	    				         var hidden7 = div.querySelector('input[type="hidden"].form-control10');
	    				         
	    				         
	    				         
	    				         var hidden8 = div.querySelector('input[type="hidden"].form-control11');
	    				         var hidden19 = div.querySelector('input[type="hidden"].form-control19');
	    				         var hidden20 = div.querySelector('input[type="hidden"].form-control20');
	    				         
	    				         var hidden12 = div.querySelector('input[type="hidden"].form-control12');
	    				         var hidden13 = div.querySelector('input[type="hidden"].form-control13');
	    				         var hidden14 = div.querySelector('input[type="hidden"].form-control14');
	    				         var hidden15 = div.querySelector('input[type="hidden"].form-control15');
	    				         var hidden16 = div.querySelector('input[type="hidden"].form-control16');
	    				         var hidden17 = div.querySelector('input[type="hidden"].form-control17');
	    				         var hidden18 = div.querySelector('input[type="hidden"].form-control18');
	    				         var hidden21 = div.querySelector('input[type="hidden"].form-control21');
	    				         var hidden22 = div.querySelector('input[type="hidden"].form-control22');
	    				         var hidden23 = div.querySelector('input[type="hidden"].form-control23'); 
	    				         var hidden24 = div.querySelector('input[type="hidden"].form-control24');
	    				        
	    				         
	    				         if(inputValue.split('_')[3] == 1){
	    				        	// alert("value " + inputValue.split('_')[0]);
	    				        	 hidden1.value = inputValue.split('_')[0];
	    				         }
   								  if(inputValue.split('_')[3] == 2){
   							         hidden2.value = inputValue.split('_')[0];
	    				         }
   								  if(inputValue.split('_')[3] == 3){
   							       hidden3.value= inputValue.split('_')[0];
 	    				         }    				          
   								  if(inputValue.split('_')[3] == 4){
   									  hidden4.value = inputValue.split('_')[0];
 	    				         }    				         
   								  if(inputValue.split('_')[3] == 5){
   									  hidden5.value = inputValue.split('_')[0];
 	    				         }
   								  if(inputValue.split('_')[3] == 6){
   								   hidden6.value = inputValue.split('_')[0];
 	    				         }
   								  if(inputValue.split('_')[3] == 7){
      								   hidden8.value = inputValue.split('_')[0];
    	    				     }
   								  if(inputValue.split('_')[3] == 15){
      								   hidden19.value = inputValue.split('_')[0];
    	    				         }
      								  if(inputValue.split('_')[3] == 16){
         								   hidden20.value = inputValue.split('_')[0];
       	    				     }
   								  
   								  
   								  
   								  
   							      if(inputValue.split('_')[3] == 8){
  	    				        	// alert("value " + inputValue.split('_')[0]);
  	    				        	 hidden12.value = inputValue.split('_')[0];
  	    				         }
     								  if(inputValue.split('_')[3] == 9){
     							         hidden13.value = inputValue.split('_')[0];
  	    				         }
     								  if(inputValue.split('_')[3] == 10){
     							       hidden14.value= inputValue.split('_')[0];
   	    				         }    				          
     								  if(inputValue.split('_')[3] == 11){
     									  hidden15.value = inputValue.split('_')[0];
   	    				         }    				         
     								  if(inputValue.split('_')[3] == 12){
     									  hidden16.value = inputValue.split('_')[0];
   	    				         }
     								  if(inputValue.split('_')[3] == 13){
     								   hidden17.value = inputValue.split('_')[0];
   	    				         }
     								  if(inputValue.split('_')[3] == 14){
        								   hidden18.value = inputValue.split('_')[0];
      	    				     }
     								  
     								  
     								  
     								  
     								  if(inputValue.split('_')[3] == 17){
        								   hidden21.value = inputValue.split('_')[0];
      	    				         }
        								  if(inputValue.split('_')[3] == 18){
           								   hidden22.value = inputValue.split('_')[0];
         	    				     }
        								  	  
   								 
        								  if(inputValue.split('_')[3] == 19){
              								   hidden23.value = inputValue.split('_')[0];
            	    				     }			  
        								  
        								  if(inputValue.split('_')[3] == 20){
              								   hidden24.value = inputValue.split('_')[0];
            	    				     }
        								  
        								  
        								  
   								 // alert(" PORT "+inputValue.split('_')[2]);
   						
   								 hidden7.value = inputValue.split('_')[2];
	    				         hidden.value = "cal"; 	
	    				         
	    			   
	    			     
	    			     
	    			     
	    			     
	    			     
	    			     
	    			     
	    			     
	    			     
	    				         
	    			 });
	    		 
	    		 
	    		 
	        }
	        
	        
	        
	    }
	     

	 
	    
	    
	    
	    
	    
	    
	    
		 idmain2= null;
 
	}
	
});
    

     
        
        
        
        
        
        
        
        

    </script>
</body>

   <script src="<c:url value="/resources/app-assets/vendors/js/editors/quill/katex.min.js" />"></script>
       <script src="<c:url value="/resources/app-assets/vendors/js/editors/quill/highlight.min.js" />"></script>
    <script src="<c:url value="/lib/eform/eform.js" />"></script>

  
  <script>
  
  //test
   function addfield(elementType,ids,label,datarefertype
		   ,datareferid,datareferid2,datareferid3,datareferid4,datareferid5,datareferid6,datareferid7,datareferid15,datareferid16
		   ,datareferid8,datareferid9,datareferid10,datareferid11,datareferid12,datareferid13,datareferid14,datareferid17,datareferid18,datareferid19,datareferid20
		   ,field_dataport
		   ,datarefercols,mainfield,subfield){

	   
	 
	   
 	const formPreviews = document.querySelector('.form-preview');
 	 let newInput;
 	 let newInputs;
	 var labels ="";
	 
	 

 
 	  if (elementType === 'text') {
 		  
 	
 		  
 	        newInput = document.createElement('div');
 	        newInput.className = 'row draggable-row';
 	        newInput.innerHTML = `
 	            <div class="col-md-12 col-12">
 	                <div class="mb-1 getported`+ids+`">
 	                    <label class="form-label getported`+ids+`" for="itemname">`+label+`</label>
 	                 
 	                 
 	                    <div class="dropdown pop" style="float:right;margin-right:20px;">
 	                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
 	                    Setup
 	                    </i>
 	                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
 	                            <div class="mb-1">
 	                                <label for="discount-input" class="form-label">Label</label>
 	                                <input type="text" class="form-control change-label" value="`+label+`" name="field_label"/>
 	                                 <input type="hidden"  value="text" name="field_type"/>
 	                            </div>
 	                            <div class="form-row mt-50" style="display:none">
 	                                <div class="mb-1 col-md-12">
 	                                <label for="tax-1-input" class="form-label">Main Field</label>
 	                                    <select id="tax-1-input" class="form-select" name="field_mainfield">
 	                                   
 	                           
 	                                   <option value="`+mainfield+`" selected>`+mainfield+`</option>

 	                                  <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
 	                                    </select>
 	                                </div>
 	                            
 	                            </div>
 	                         
 	                           <div class="form-row mt-50" style="display:none">
 	                            <div class="mb-1 col-md-12">
 	                            <label for="tax-1-input" class="form-label">Action type</label>
 	                                <select  id="tax-1-input" class="form-select" name="field_subfield">
 	                               <option value="`+subfield+`" selected>`+subfield+`</option>
 	                              <option value="-">-</option>
 	                                <option value="verification" >Verification</option>
 	                                <option value="register">register</option>
 	            						
 	                                </select>
 	                            </div>
 	                        
 	                        </div>
 	                        
 	                        
 	                   
 	                        
 	                        
 	                        
 	                        
 	                            
 	                            <div class="dropdown-divider my-1"></div>
 	                            <div class="d-flex justify-content-between">
 	                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
 	                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
 	                            </div>
 	                        </div>
 	                    </div>
 	                    <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
 						  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
 						  <input type="hidden"  name="field_datareferid2" value="`+datareferid2+`" class="form-control5"  >
 						  <input type="hidden"  name="field_datareferid3" value="`+datareferid3+`" class="form-control6"  >
 						  <input type="hidden"  name="field_datareferid4" value="`+datareferid4+`" class="form-control7"  >
 						  <input type="hidden"  name="field_datareferid5" value="`+datareferid5+`" class="form-control8"  >
 						  <input type="hidden"  name="field_datareferid6" value="`+datareferid6+`" class="form-control9"  >
 						  <input type="hidden"  name="field_datareferid7" value="`+datareferid7+`" class="form-control11">
 						  <input type="hidden"  name="field_datareferid15" value="`+datareferid15+`" class="form-control19">
 						  <input type="hidden"  name="field_datareferid16" value="`+datareferid16+`" class="form-control20">
 						  <input type="hidden"  name="field_datareferid8" value="`+datareferid8+`" class="form-control12"  >
 						  <input type="hidden"  name="field_datareferid9" value="`+datareferid9+`" class="form-control13"  >
 						  <input type="hidden"  name="field_datareferid10" value="`+datareferid10+`" class="form-control14"  >
 						  <input type="hidden"  name="field_datareferid11" value="`+datareferid11+`" class="form-control15"  >
 						  <input type="hidden"  name="field_datareferid12" value="`+datareferid12+`" class="form-control16"  >
 						  <input type="hidden"  name="field_datareferid13" value="`+datareferid13+`" class="form-control17"  >
 						  <input type="hidden"  name="field_datareferid14" value="`+datareferid14+`" class="form-control18"  >
 						  <input type="hidden"  name="field_datareferid17" value="`+datareferid17+`" class="form-control21"  >
 						  <input type="hidden"  name="field_datareferid18" value="`+datareferid18+`" class="form-control22"  >
 						  <input type="hidden"  name="field_datareferid19" value="`+datareferid19+`" class="form-control23"  >
 						  <input type="hidden"  name="field_datareferid20" value="`+datareferid20+`" class="form-control24"  >
 						  
 						  <input type="hidden"  name="field_dataport" value="`+field_dataport+`" class="form-control10"  >
 						   <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
 	                    <input type="text" class="form-control mydata" name="condition_title" value=""/>
 	                </div>
 	            </div>
 	        `;
 	        
 	        
 	   
 	        let newTableHeader = document.createElement('th');
 			newTableHeader.textContent = 'text';
 			newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
 			newTableHeader.classList.add('fixhead'); 
 			
 			const theadElement = document.getElementById('tabledata');
 			const tableRow = theadElement.querySelector('tr');
 			
 			tableRow.appendChild(newTableHeader);
 			      
 			const nodemain = document.querySelector('.node1');
 		
 			
 			
 			newInputs = document.createElement('div');
 		    newInputs.className = 'agm';        
 			newInputs.innerHTML = '<input id="1" value="'+label+'" class="change-label2"  readonly><span id="ported'+ids+'"  class="port port1" ></span>';
 			  nodemain.appendChild(newInputs);
 	        
 			  
 			  
 	        // ดึงข้อมูลเริ่มต้นของ label จาก input
 	        const initialLabelValue = newInput.querySelector('.change-label').value;
 	        const formLabel = newTableHeader.querySelector('.form-label'); 
 	        const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
 	        
 	        
 	        newInput.querySelector('.form-label').textContent = label;
 	        newTableHeader.textContent = label;
 	        newInputs.querySelector('.change-label2').value  = label;
 	        
 	        // หากมีการเปลี่ยนแปลงค่าใน input change-label
 	        newInput.querySelector('.change-label').addEventListener('input', (event) => {
 	            // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
 	            newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
 	            newTableHeader.textContent = event.target.value || initialLabelValue;
 	            newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
 	           edit = true;
 	        });

 	      
 	            const deleteButton = newInput.querySelector('.delete-button');
 	            deleteButton.addEventListener('click', () => {
 	                newInput.remove(); // ลบ div ตัวเอง
 	                newInputs.remove(); // ลบ div ตัวเอง
 	                newTableHeader.remove();
 	            });
 	            
 	            
 	           /*
 	            // เลือกองค์ประกอบ select
 	            const selectElement = newInput.querySelector('.tax-select');

 	            // สร้างองค์ประกอบ change-label
 	            const changeLabelElement = newInput.querySelector('.change-label');

 	            // เมื่อมีการเปลี่ยนแปลงใน select
 	            selectElement.addEventListener('change', (event) => {
 	              // ดึงค่า data จากตัวเลือกที่ถูกเลือก
 	              const selectedOption = selectElement.options[selectElement.selectedIndex];
 	              const dataValue = selectedOption.getAttribute('data');

 	              // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
 	              changeLabelElement.value = dataValue || 'Text';

 	              // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
 	              newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
 	              newTableHeader.textContent = changeLabelElement.value || 'Text';
 	              newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
 	            });      
 	            */
 	               
 	            
 	            
 	            
 	        
 	        
 	    }
 	
 	  
 	 if (elementType === 'status') {
		   
	        newInput = document.createElement('div');
	        newInput.className = 'row draggable-row';
	        newInput.innerHTML = `
	            <div class="col-md-12 col-12">
	                <div class="mb-1">
	                    <label class="form-label" for="itemname">`+label+`</label>
	                    <label class="form-label " for="itemname">`+labels+`</label>
	                    
	                 
	                    <div class="dropdown pop" style="float:right;margin-right:20px;">
	                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                    Setup
	                    </i>
	                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
	                            <div class="mb-1">
	                                <label for="discount-input" class="form-label">Label</label>
	                                <input type="text" class="form-control change-label" value="`+label+`" name="field_label"/>
	                                 <input type="hidden"  value="status" name="field_type"/>
	                            </div>
	                            <div class="form-row mt-50" style="display:none">
	                                <div class="mb-1 col-md-12">
	                                <label for="tax-1-input" class="form-label">Main Field</label>
	                                    <select id="tax-1-input" class="form-select" name="field_mainfield">
	                                   <option value="`+mainfield+`" selected>`+mainfield+`</option>
	                                   <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
	                                    </select>
	                                </div>
	                            
	                            </div>
	                            
	                            <div class="form-row mt-50" style="display:none">
 	                            <div class="mb-1 col-md-12">
 	                            <label for="tax-1-input" class="form-label">Sub Field</label>
 	                                <select  id="tax-1-input" class="form-select" name="field_subfield">
 	                               <option value="`+subfield+`" selected>`+subfield+`</option>
 	                                <option value="-" >-</option>
 	                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
 	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
 	            						  </c:forEach>
 	                                </select>
 	                            </div>
 	                        
 	                        </div>
	                            
	                            
	                            
	                            <div class="dropdown-divider my-1"></div>
	                            <div class="d-flex justify-content-between">
	                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
	                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
	                            </div>
	                        </div>
	                    </div>
	                    <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
						  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
						  <input type="hidden"  name="field_datareferid2" value="`+datareferid2+`" class="form-control5"  >
						  <input type="hidden"  name="field_datareferid3" value="`+datareferid3+`" class="form-control6"  >
						  <input type="hidden"  name="field_datareferid4" value="`+datareferid4+`" class="form-control7"  >
						  <input type="hidden"  name="field_datareferid5" value="`+datareferid5+`" class="form-control8"  >
						  <input type="hidden"  name="field_datareferid6" value="`+datareferid6+`" class="form-control9"  >
						  <input type="hidden"  name="field_datareferid7" value="`+datareferid7+`" class="form-control11">
						  <input type="hidden"  name="field_datareferid15" value="`+datareferid15+`" class="form-control19">
						  <input type="hidden"  name="field_datareferid16" value="`+datareferid16+`" class="form-control20">
						  <input type="hidden"  name="field_datareferid8" value="`+datareferid8+`" class="form-control12"  >
						  <input type="hidden"  name="field_datareferid9" value="`+datareferid9+`" class="form-control13"  >
						  <input type="hidden"  name="field_datareferid10" value="`+datareferid10+`" class="form-control14"  >
						  <input type="hidden"  name="field_datareferid11" value="`+datareferid11+`" class="form-control15"  >
						  <input type="hidden"  name="field_datareferid12" value="`+datareferid12+`" class="form-control16"  >
						  <input type="hidden"  name="field_datareferid13" value="`+datareferid13+`" class="form-control17"  >
						  <input type="hidden"  name="field_datareferid14" value="`+datareferid14+`" class="form-control18"  >
						  <input type="hidden"  name="field_datareferid17" value="`+datareferid17+`" class="form-control21"  >
						  <input type="hidden"  name="field_datareferid18" value="`+datareferid18+`" class="form-control22"  >
						  <input type="hidden"  name="field_datareferid19" value="`+datareferid19+`" class="form-control23"  >
 						  <input type="hidden"  name="field_datareferid20" value="`+datareferid20+`" class="form-control24"  >
						  <input type="hidden"  name="field_dataport" value="`+field_dataport+`" class="form-control10"  >
						  <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
						   
						  <div>
						   <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
	                       <input type="radio" class="btn-check mydata" value="Good"  name="btnradio" id="btnradio1" autocomplete="off" checked="">
	                       <label class="btn btn-outline-success waves-effect" for="btnradio1">Good</label>

	                       <input type="radio" class="btn-check mydata" value="Normal"   name="btnradio" id="btnradio2" autocomplete="off">
	                       <label class="btn btn-outline-warning waves-effect" for="btnradio2">Normal</label>

	                       <input type="radio" class="btn-check mydata" value="Bad"   name="btnradio" id="btnradio3" autocomplete="off">
	                       <label class="btn btn-outline-danger waves-effect" for="btnradio3">Bad</label>
	                       </div>
	                </div>
	            </div>
	        `;
	        
	        
	   
	        let newTableHeader = document.createElement('th');
			newTableHeader.textContent = 'text';
			newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
			newTableHeader.classList.add('fixhead'); 
			
			const theadElement = document.getElementById('tabledata');
			const tableRow = theadElement.querySelector('tr');
			
			tableRow.appendChild(newTableHeader);
			      
			const nodemain = document.querySelector('.node1');
		
			
			
			newInputs = document.createElement('div');
		    newInputs.className = 'agm';        
			newInputs.innerHTML = '<input id="1" value="'+label+'" class="change-label2"  readonly>';
			  nodemain.appendChild(newInputs);
	        
			  
			  
	        // ดึงข้อมูลเริ่มต้นของ label จาก input
	        const initialLabelValue = newInput.querySelector('.change-label').value;
	        const formLabel = newTableHeader.querySelector('.form-label'); 
	        const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
	        
	        
	        newInput.querySelector('.form-label').textContent = label;
	        newTableHeader.textContent = label;
	        newInputs.querySelector('.change-label2').value  = label;
	        
	        // หากมีการเปลี่ยนแปลงค่าใน input change-label
	        newInput.querySelector('.change-label').addEventListener('input', (event) => {
	            // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
	            newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
	            newTableHeader.textContent = event.target.value || initialLabelValue;
	            newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
	            edit = true;
	        });

	      
	            const deleteButton = newInput.querySelector('.delete-button');
	            deleteButton.addEventListener('click', () => {
	                newInput.remove(); // ลบ div ตัวเอง
	                newInputs.remove(); // ลบ div ตัวเอง
	                newTableHeader.remove();
	            });
	            
	            
	            /*
	            // เลือกองค์ประกอบ select
	            const selectElement = newInput.querySelector('.tax-select');

	            // สร้างองค์ประกอบ change-label
	            const changeLabelElement = newInput.querySelector('.change-label');

	            // เมื่อมีการเปลี่ยนแปลงใน select
	            selectElement.addEventListener('change', (event) => {
	              // ดึงค่า data จากตัวเลือกที่ถูกเลือก
	              const selectedOption = selectElement.options[selectElement.selectedIndex];
	              const dataValue = selectedOption.getAttribute('data');

	              // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
	              changeLabelElement.value = dataValue || 'Text';

	              // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
	              newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
	              newTableHeader.textContent = changeLabelElement.value || 'Text';
	              newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
	            });      
	            */
	               
	            
	            
	            
	        
	        
	    }
	
 	 if (elementType === 'time') {
		   
	        newInput = document.createElement('div');
	        newInput.className = 'row draggable-row';
	        newInput.innerHTML = `
	            <div class="col-md-12 col-12">
	                <div class="mb-1">
	                    <label class="form-label" for="itemname">`+label+`</label>
	                 
	                    
	                 
	                    <div class="dropdown pop" style="float:right;margin-right:20px;">
	                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                    Setup
	                    </i>
	                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
	                            <div class="mb-1">
	                                <label for="discount-input" class="form-label">Label</label>
	                                <input type="text" class="form-control change-label" value="`+label+`" name="field_label"/>
	                                 <input type="hidden"  value="time" name="field_type"/>
	                            </div>
	                            <div class="form-row mt-50" style="display:none">
	                                <div class="mb-1 col-md-12">
	                                <label for="tax-1-input" class="form-label">Main Field</label>
	                                    <select id="tax-1-input" class="form-select" name="field_mainfield">
	                                   <option value="`+mainfield+`" selected>`+mainfield+`</option>
	                                   <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
	                                    </select>
	                                </div>
	                            
	                            </div>
	                            
	                            <div class="form-row mt-50" style="display:none">
	                            <div class="mb-1 col-md-12">
	                            <label for="tax-1-input" class="form-label">Sub Field</label>
	                                <select  id="tax-1-input" class="form-select" name="field_subfield">
	                               <option value="`+subfield+`" selected>`+subfield+`</option>
	                                <option value="-" >-</option>
	                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
	                                </select>
	                            </div>
	                        
	                        </div>
	                            
	                            
	                            
	                            <div class="dropdown-divider my-1"></div>
	                            <div class="d-flex justify-content-between">
	                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
	                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
	                            </div>
	                        </div>
	                    </div>
	                    <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
						  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
						  <input type="hidden"  name="field_datareferid2" value="`+datareferid2+`" class="form-control5"  >
						  <input type="hidden"  name="field_datareferid3" value="`+datareferid3+`" class="form-control6"  >
						  <input type="hidden"  name="field_datareferid4" value="`+datareferid4+`" class="form-control7"  >
						  <input type="hidden"  name="field_datareferid5" value="`+datareferid5+`" class="form-control8"  >
						  <input type="hidden"  name="field_datareferid6" value="`+datareferid6+`" class="form-control9"  >
						  <input type="hidden"  name="field_datareferid7" value="`+datareferid7+`" class="form-control11">
						  <input type="hidden"  name="field_datareferid15" value="`+datareferid15+`" class="form-control19">
						  <input type="hidden"  name="field_datareferid16" value="`+datareferid16+`" class="form-control20">
						  <input type="hidden"  name="field_datareferid8" value="`+datareferid8+`" class="form-control12"  >
						  <input type="hidden"  name="field_datareferid9" value="`+datareferid9+`" class="form-control13"  >
						  <input type="hidden"  name="field_datareferid10" value="`+datareferid10+`" class="form-control14"  >
						  <input type="hidden"  name="field_datareferid11" value="`+datareferid11+`" class="form-control15"  >
						  <input type="hidden"  name="field_datareferid12" value="`+datareferid12+`" class="form-control16"  >
						  <input type="hidden"  name="field_datareferid13" value="`+datareferid13+`" class="form-control17"  >
						  <input type="hidden"  name="field_datareferid14" value="`+datareferid14+`" class="form-control18"  >
						  <input type="hidden"  name="field_datareferid17" value="`+datareferid17+`" class="form-control21"  >
						  <input type="hidden"  name="field_datareferid18" value="`+datareferid18+`" class="form-control22"  >
						  <input type="hidden"  name="field_datareferid19" value="`+datareferid19+`" class="form-control23"  >
						  <input type="hidden"  name="field_datareferid20" value="`+datareferid20+`" class="form-control24"  >
						  <input type="hidden"  name="field_dataport" value="`+field_dataport+`" class="form-control10"  >
						   <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
	                
	                    	 <input type="text" class="form-control time-mask mydata" placeholder="hh:mm:ss" id="time">
	                </div>
	            </div>
	        `;
	        
	        
	   
	        let newTableHeader = document.createElement('th');
			newTableHeader.textContent = 'text';
			newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
			newTableHeader.classList.add('fixhead'); 
			
			const theadElement = document.getElementById('tabledata');
			const tableRow = theadElement.querySelector('tr');
			
			tableRow.appendChild(newTableHeader);
			      
			const nodemain = document.querySelector('.node1');
		
			
			
			newInputs = document.createElement('div');
		    newInputs.className = 'agm';        
			newInputs.innerHTML = '<input id="1" value="'+label+'" class="change-label2"  readonly>';
			  nodemain.appendChild(newInputs);
	        
			  
			  
	        // ดึงข้อมูลเริ่มต้นของ label จาก input
	        const initialLabelValue = newInput.querySelector('.change-label').value;
	        const formLabel = newTableHeader.querySelector('.form-label'); 
	        const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
	        
	        
	        newInput.querySelector('.form-label').textContent = label;
	        newTableHeader.textContent = label;
	        newInputs.querySelector('.change-label2').value  = label;
	        
	        // หากมีการเปลี่ยนแปลงค่าใน input change-label
	        newInput.querySelector('.change-label').addEventListener('input', (event) => {
	            // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
	            newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
	            newTableHeader.textContent = event.target.value || initialLabelValue;
	            newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
	            edit = true;
	        });

	      
	            const deleteButton = newInput.querySelector('.delete-button');
	            deleteButton.addEventListener('click', () => {
	                newInput.remove(); // ลบ div ตัวเอง
	                newInputs.remove(); // ลบ div ตัวเอง
	                newTableHeader.remove();
	            });
	            
	            
	            /*
	            // เลือกองค์ประกอบ select
	            const selectElement = newInput.querySelector('.tax-select');

	            // สร้างองค์ประกอบ change-label
	            const changeLabelElement = newInput.querySelector('.change-label');

	            // เมื่อมีการเปลี่ยนแปลงใน select
	            selectElement.addEventListener('change', (event) => {
	              // ดึงค่า data จากตัวเลือกที่ถูกเลือก
	              const selectedOption = selectElement.options[selectElement.selectedIndex];
	              const dataValue = selectedOption.getAttribute('data');

	              // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
	              changeLabelElement.value = dataValue || 'Text';

	              // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
	              newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
	              newTableHeader.textContent = changeLabelElement.value || 'Text';
	              newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
	            });      
	            */
	               
	            
	            
	            
	        
	        
	    }
 	  
 	  
 	 if (elementType === 'textarea') {
         newInput = document.createElement('div');
         newInput.className = 'row draggable-row';
         newInput.innerHTML = `
             <div class="col-md-12 col-12">
                 <div class="mb-1">
                     <label class="form-label" for="itemname">`+label+`</label>
              
                     
                     
                     <div class="dropdown pop" style="float:right;margin-right:20px;">
                     <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                     Setup
                     </i>
                         <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                             <div class="mb-1">
                                 <label for="discount-input" class="form-label">Label</label>
                                 <input type="text" class="form-control change-label" value="`+label+`" name="field_label"/>
                                  <input type="hidden"  value="textarea" name="field_type"/>
                             </div>
                             <div class="form-row mt-50" style="display:none">
                                 <div class="mb-1 col-md-12">
                                 <label for="tax-1-input" class="form-label">Main Field</label>
                                     <select  id="tax-1-input" class="form-select" name="field_mainfield">
                                 
                                     <option value="`+mainfield+`" selected>`+mainfield+`</option>
                                     
                                     <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
                                     </select>
                                 </div>
                             
                             </div>
                             
                             
                             <div class="form-row mt-50" style="display:none">
	                            <div class="mb-1 col-md-12">
	                            <label for="tax-1-input" class="form-label">Sub Field</label>
	                                <select  id="tax-1-input" class="form-select" name="field_subfield">
	                               <option value="`+subfield+`" selected>`+subfield+`</option>
	                                <option value="-" >-</option>
	                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
	                                </select>
	                            </div>
	                        
	                        </div>
                             
                             
                             <div class="dropdown-divider my-1"></div>
                             <div class="d-flex justify-content-between">
                                 <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                 <button type="button" class="btn btn-outline-secondary">ปิด</button>
                             </div>
                         </div>
                     </div>
                     <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="`+datareferid2+`" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="`+datareferid3+`" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="`+datareferid4+`" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="`+datareferid5+`" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="`+datareferid6+`" class="form-control9"  >
					  <input type="hidden"  name="field_datareferid7" value="`+datareferid7+`" class="form-control11">
					  <input type="hidden"  name="field_datareferid15" value="`+datareferid15+`" class="form-control19">
					  <input type="hidden"  name="field_datareferid16" value="`+datareferid16+`" class="form-control20">
					  <input type="hidden"  name="field_datareferid8" value="`+datareferid8+`" class="form-control12"  >
					  <input type="hidden"  name="field_datareferid9" value="`+datareferid9+`" class="form-control13"  >
					  <input type="hidden"  name="field_datareferid10" value="`+datareferid10+`" class="form-control14"  >
					  <input type="hidden"  name="field_datareferid11" value="`+datareferid11+`" class="form-control15"  >
					  <input type="hidden"  name="field_datareferid12" value="`+datareferid12+`" class="form-control16"  >
					  <input type="hidden"  name="field_datareferid13" value="`+datareferid13+`" class="form-control17"  >
					  <input type="hidden"  name="field_datareferid14" value="`+datareferid14+`" class="form-control18"  >
					  <input type="hidden"  name="field_datareferid17" value="`+datareferid17+`" class="form-control21"  >
					  <input type="hidden"  name="field_datareferid18" value="`+datareferid18+`" class="form-control22"  >
					  <input type="hidden"  name="field_datareferid19" value="`+datareferid19+`" class="form-control23"  >
						  <input type="hidden"  name="field_datareferid20" value="`+datareferid20+`" class="form-control24"  >
					  <input type="hidden"  name="field_dataport" value="`+field_dataport+`" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
                     <textarea class="form-control mydata" name="condition_title" value=""/></textarea>
                 </div>
             </div>
         `;
         
         
       
         let newTableHeader = document.createElement('th');
 		newTableHeader.textContent = 'textarea';
 		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
 		newTableHeader.classList.add('fixhead'); 
 		
 		const theadElement = document.getElementById('tabledata');
 		const tableRow = theadElement.querySelector('tr');
 		
 		tableRow.appendChild(newTableHeader);
 		
 		const nodemain = document.querySelector('.node1');       
 		ids++;      
 		newInputs = document.createElement('div');
 	    newInputs.className = 'agm';        
 		newInputs.innerHTML = '<input id="1" value="'+label+'" class="change-label2"  readonly>';
 		nodemain.appendChild(newInputs);     
         
         
         
         // ดึงข้อมูลเริ่มต้นของ label จาก input
         const initialLabelValue = newInput.querySelector('.change-label').value;
         const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
         const formLabel = newTableHeader.querySelector('.form-label'); 
         
	        newInput.querySelector('.form-label').textContent = label;
 	        newTableHeader.textContent = label;
 	        newInputs.querySelector('.change-label2').value  = label;
         
         
         // หากมีการเปลี่ยนแปลงค่าใน input change-label
         newInput.querySelector('.change-label').addEventListener('input', (event) => {
             // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
             newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
             newTableHeader.textContent = event.target.value || initialLabelValue;
             newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
             edit = true;
         });

       
             const deleteButton = newInput.querySelector('.delete-button');
             deleteButton.addEventListener('click', () => {
                 newInput.remove(); // ลบ div ตัวเอง
                 newTableHeader.remove();
                 newInputs.remove(); // ลบ div ตัวเอง
             });
             
             
             /*
             // เลือกองค์ประกอบ select
             const selectElement = newInput.querySelector('.tax-select');

             // สร้างองค์ประกอบ change-label
             const changeLabelElement = newInput.querySelector('.change-label');

             // เมื่อมีการเปลี่ยนแปลงใน select
             selectElement.addEventListener('change', (event) => {
               // ดึงค่า data จากตัวเลือกที่ถูกเลือก
               const selectedOption = selectElement.options[selectElement.selectedIndex];
               const dataValue = selectedOption.getAttribute('data');

               // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
               changeLabelElement.value = dataValue || 'Text';

               // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
               newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
               newTableHeader.textContent = changeLabelElement.value || 'Text';
               newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
             });      
             */
             
             
             
         
         
     }
     
     
 	  
 	  

     
     if (elementType === 'number') {
         newInput = document.createElement('div');
         newInput.className = 'row draggable-row';
         newInput.innerHTML = `

                 <div class="mb-1 getported`+ids+`">
                     <label class="form-label" for="itemname">`+label+`</label>
                    
                     
                     
                     <div class="dropdown pop" style="float:right;margin-right:20px;">
                     <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                     Setup
                     </i>
                         <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                             <div class="mb-1">
                                 <label for="discount-input" class="form-label">Label</label>
                                 <input type="text" class="form-control change-label" value="`+label+`" name="field_label"/>
                                 	<input type="hidden"  value="number" name="field_type"/>
                             </div>
                             <div class="form-row mt-50" style="display:none">
                                 <div class="mb-1 col-md-12">
                                     <label for="tax-1-input" class="form-label">Main Field</label>
                                     <select  id="tax-1-input" class="form-select" name="field_mainfield">
                                    
	                                   <option value="`+mainfield+`" selected>`+mainfield+`</option>
	                                   <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
             						  
             						  
                                     </select>
                                 </div>
                             
                             </div>
                             <div class="form-row mt-50" style="display:none">
	                            <div class="mb-1 col-md-12">
	                            <label for="tax-1-input" class="form-label">Sub Field</label>
	                                <select  id="tax-1-input" class="form-select" name="field_subfield">
	                               <option value="`+subfield+`" selected>`+subfield+`</option>
	                                <option value="-" >-</option>
	                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
	                                </select>
	                            </div>
	                        
	                        </div>
                             
                             
                             
                             <div class="dropdown-divider my-1"></div>
                             <div class="d-flex justify-content-between">
                                 <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                 <button type="button" class="btn btn-outline-secondary">ปิด</button>
                             </div>
                         </div>
                     </div>
                     <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="`+datareferid2+`" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="`+datareferid3+`" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="`+datareferid4+`" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="`+datareferid5+`" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="`+datareferid6+`" class="form-control9"  >
					  <input type="hidden"  name="field_datareferid7" value="`+datareferid7+`" class="form-control11">
					  <input type="hidden"  name="field_datareferid15" value="`+datareferid15+`" class="form-control19">
					  <input type="hidden"  name="field_datareferid16" value="`+datareferid16+`" class="form-control20">
					  <input type="hidden"  name="field_datareferid8" value="`+datareferid8+`" class="form-control12"  >
					  <input type="hidden"  name="field_datareferid9" value="`+datareferid9+`" class="form-control13"  >
					  <input type="hidden"  name="field_datareferid10" value="`+datareferid10+`" class="form-control14"  >
					  <input type="hidden"  name="field_datareferid11" value="`+datareferid11+`" class="form-control15"  >
					  <input type="hidden"  name="field_datareferid12" value="`+datareferid12+`" class="form-control16"  >
					  <input type="hidden"  name="field_datareferid13" value="`+datareferid13+`" class="form-control17"  >
					  <input type="hidden"  name="field_datareferid14" value="`+datareferid14+`" class="form-control18"  >
					  <input type="hidden"  name="field_datareferid17" value="`+datareferid17+`" class="form-control21"  >
					  <input type="hidden"  name="field_datareferid18" value="`+datareferid18+`" class="form-control22"  >
					  <input type="hidden"  name="field_datareferid19" value="`+datareferid19+`" class="form-control23"  >
						  <input type="hidden"  name="field_datareferid20" value="`+datareferid20+`" class="form-control24"  >
					  <input type="hidden"  name="field_dataport" value="`+field_dataport+`" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
                     <input type="number" class="form-control mydata" name="condition_title" value=""/>
                 </div>
            
         `;
     
         
   
         let newTableHeader = document.createElement('th');
 		newTableHeader.textContent = 'Number';
 		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
 		newTableHeader.classList.add('fixhead'); 
 		
 		const theadElement = document.getElementById('tabledata');
 		const tableRow = theadElement.querySelector('tr');
 		
 		tableRow.appendChild(newTableHeader);      
         
 		const nodemain = document.querySelector('.node1');           
 		newInputs = document.createElement('div');
 	    newInputs.className = 'agm';        
 		newInputs.innerHTML = '<input id="1" value="'+label+'" class="change-label2"  readonly><span id="ported'+ids+'"  class="port port3" ></span>';
 		nodemain.appendChild(newInputs);     
         
 	
 		
        newInput.querySelector('.form-label').textContent = label;
	        newTableHeader.textContent = label;
	        newInputs.querySelector('.change-label2').value  = label;
     
 		
	        
         
         // ดึงข้อมูลเริ่มต้นของ label จาก input
    const initialLabelValue = newInput.querySelector('.change-label').value;
    const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
    // หากมีการเปลี่ยนแปลงค่าใน input change-label
    newInput.querySelector('.change-label').addEventListener('input', (event) => {
        // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
        newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
        newTableHeader.textContent = event.target.value || initialLabelValue;
        newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
        edit = true;
    });

 	   const deleteButton = newInput.querySelector('.delete-button');
 	   deleteButton.addEventListener('click', () => {
 	       newInput.remove(); // ลบ div ตัวเอง
 	       newTableHeader.remove();
 	       newInputs.remove(); // ลบ div ตัวเอง
 	   });
 	   
 	  /*
       // เลือกองค์ประกอบ select
       const selectElement = newInput.querySelector('.tax-select');

       // สร้างองค์ประกอบ change-label
       const changeLabelElement = newInput.querySelector('.change-label');

       // เมื่อมีการเปลี่ยนแปลงใน select
       selectElement.addEventListener('change', (event) => {
         // ดึงค่า data จากตัวเลือกที่ถูกเลือก
         const selectedOption = selectElement.options[selectElement.selectedIndex];
         const dataValue = selectedOption.getAttribute('data');

         // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
         changeLabelElement.value = dataValue || 'Text';

         // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
         newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
         newTableHeader.textContent = changeLabelElement.value || 'Text';
         newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
       });      
       */
       
         
         
     }
     
     if (elementType === 'date') {
         newInput = document.createElement('div');
         newInput.className = 'row draggable-row';
         newInput.innerHTML = `
             <div class="col-md-12 col-12">
                 <div class="mb-1">
                     <label class="form-label" for="itemname">`+label+`</label>
                   
                     
                
                    
                     <div class="dropdown pop" style="float:right;margin-right:20px;">
                     <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                     Setup
                     </i>
                         <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                             <div class="mb-1">
                                 <label for="discount-input" class="form-label">Label</label>
                                 <input type="text"  name="field_label" class="form-control change-label" value="`+label+`" />
                                 	<input type="hidden"  value="date" name="field_type"/>
                             </div>
                             <div class="form-row mt-50" style="display:none">
                                 <div class="mb-1 col-md-12">
                                     <label for="tax-1-input" class="form-label">MAIN Field</label>
                                     <select   class="form-select" name="field_mainfield">
                                     
                                     <option value="`+mainfield+`" selected>`+mainfield+`</option>
                                     <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
                                     </select>
                                 </div>
                             
                             </div>
                             
                             <div class="form-row mt-50" style="display:none">
                             <div class="mb-1 col-md-12">
                             <label for="tax-1-input" class="form-label">Sub Field</label>
                                 <select  id="tax-1-input" class="form-select" name="field_subfield">
                             
                                 <option value="-" >-</option>
                                     <c:forEach items="${columntype2 }" var="row" varStatus="count">
                                     <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
             						  </c:forEach>
                                 </select>
                             </div>
                         
                         </div>
                             
                             
                             
                             
                             
                             
                             <div class="dropdown-divider my-1"></div>
                             <div class="d-flex justify-content-between">
                                 <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                 <button type="button" class="btn btn-outline-secondary">ปิด</button>
                             </div>
                         </div>
                     </div>
                
                     <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="`+datareferid2+`" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="`+datareferid3+`" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="`+datareferid4+`" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="`+datareferid5+`" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="`+datareferid6+`" class="form-control9"  >
					  <input type="hidden"  name="field_datareferid7" value="`+datareferid7+`" class="form-control11">
					  <input type="hidden"  name="field_datareferid15" value="`+datareferid15+`" class="form-control19">
					  <input type="hidden"  name="field_datareferid16" value="`+datareferid16+`" class="form-control20">
					  <input type="hidden"  name="field_datareferid8" value="`+datareferid8+`" class="form-control12"  >
					  <input type="hidden"  name="field_datareferid9" value="`+datareferid9+`" class="form-control13"  >
					  <input type="hidden"  name="field_datareferid10" value="`+datareferid10+`" class="form-control14"  >
					  <input type="hidden"  name="field_datareferid11" value="`+datareferid11+`" class="form-control15"  >
					  <input type="hidden"  name="field_datareferid12" value="`+datareferid12+`" class="form-control16"  >
					  <input type="hidden"  name="field_datareferid13" value="`+datareferid13+`" class="form-control17"  >
					  <input type="hidden"  name="field_datareferid14" value="`+datareferid14+`" class="form-control18"  >
					  <input type="hidden"  name="field_datareferid17" value="`+datareferid17+`" class="form-control21"  >
					  <input type="hidden"  name="field_datareferid18" value="`+datareferid18+`" class="form-control22"  >
					  <input type="hidden"  name="field_datareferid19" value="`+datareferid19+`" class="form-control23"  >
						  <input type="hidden"  name="field_datareferid20" value="`+datareferid20+`" class="form-control24"  >
					  <input type="hidden"  name="field_dataport" value="`+field_dataport+`" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
                     <input type="date" class="form-control mydata" name="condition_title" value=""/>
                 </div>
                 </div>
               
         `;
    
             let newTableHeader = document.createElement('th');
 		newTableHeader.textContent = 'Date';
 		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
 		newTableHeader.classList.add('fixhead'); 
 		
 		const theadElement = document.getElementById('tabledata');
 		const tableRow = theadElement.querySelector('tr');
 		
 		tableRow.appendChild(newTableHeader);      
         
 		const nodemain = document.querySelector('.node1');       
 		ids++;      
 		newInputs = document.createElement('div');
 	    newInputs.className = 'agm';        
 		newInputs.innerHTML = '<input id="1" value="'+label+'" class="change-label2"  readonly>';
 		nodemain.appendChild(newInputs);   
         
 		
 	   newInput.querySelector('.form-label').textContent = label;
        newTableHeader.textContent = label;
        newInputs.querySelector('.change-label2').value  = label;
 		
 		
         
          // ดึงข้อมูลเริ่มต้นของ label จาก input
     const initialLabelValue = newInput.querySelector('.change-label').value;
     const formLabel = newTableHeader.querySelector('.form-label'); 
     const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
     
     // หากมีการเปลี่ยนแปลงค่าใน input change-label
     newInput.querySelector('.change-label').addEventListener('input', (event) => {
         // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
         newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
         newTableHeader.textContent = event.target.value || initialLabelValue;
         newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
     });

 	    const deleteButton = newInput.querySelector('.delete-button');
 	    deleteButton.addEventListener('click', () => {
 	        newInput.remove(); // ลบ div ตัวเอง
 	        newTableHeader.remove();
 	        newInputs.remove(); // ลบ div ตัวเอง
 	    });
     
         
 	   /*
        // เลือกองค์ประกอบ select
        const selectElement = newInput.querySelector('.tax-select');

        // สร้างองค์ประกอบ change-label
        const changeLabelElement = newInput.querySelector('.change-label');

        // เมื่อมีการเปลี่ยนแปลงใน select
        selectElement.addEventListener('change', (event) => {
          // ดึงค่า data จากตัวเลือกที่ถูกเลือก
          const selectedOption = selectElement.options[selectElement.selectedIndex];
          const dataValue = selectedOption.getAttribute('data');

          // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
          changeLabelElement.value = dataValue || 'Text';

          // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
          newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
          newTableHeader.textContent = changeLabelElement.value || 'Text';
          newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
        });      
        */
        

     }
      
     
     if (elementType === 'select') {
         newInput = document.createElement('div');
         newInput.className = 'row draggable-row';
         newInput.innerHTML = `
             <div class="col-md-12 col-12">
                 <div class="mb-1 Selectdata getported`+ids+`" >
                     <label class="form-label" for="itemname">`+label+`</label>
                   
                     
           
                    
                     <div class="dropdown pop" style="float:right;margin-right:20px;">
                     <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                     Setup
                     </i>
                         <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                             <div class="mb-1">
                                 <label for="discount-input" class="form-label">Label</label>
                                 <input type="text" class="form-control change-label" value="`+label+`" name="field_label"/>
                                 	<input type="hidden"  value="select" name="field_type"/>
                                 	
                                 	
                             </div>
                             <div class="form-row mt-50" style="display:none">
                                 <div class="mb-1 col-md-12">
                                     <label for="tax-1-input" class="form-label">MAIN Field</label>
                                     <select  id="tax-1-input" class="form-select" name="field_mainfield">
                                     <option value="`+mainfield+`" selected>`+mainfield+`</option>
                                     <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
                                     </select>
                                 </div>
                             
                             </div>
                             
                             
                             <div class="form-row mt-50" style="display:none">
                             <div class="mb-1 col-md-12">
                             <label for="tax-1-input" class="form-label">Sub Field</label>
                                 <select  id="tax-1-input" class="form-select" name="field_subfield">
                             
                                 <option value="-" >-</option>
                                     <c:forEach items="${columntype2 }" var="row" varStatus="count">
                                     <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
             						  </c:forEach>
                                 </select>
                             </div>
                         
                         </div>
                             
                             
                             
                             
                             
                             <div class="dropdown-divider my-1"></div>
                             <div class="d-flex justify-content-between">
                                 <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                 <button type="button" class="btn btn-outline-secondary">ปิด</button>
                             </div>
                         </div>
                     </div>
                     
                     
                     
                     
                     
                     
                     
                     
                
                     <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="`+datareferid2+`" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="`+datareferid3+`" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="`+datareferid4+`" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="`+datareferid5+`" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="`+datareferid6+`" class="form-control9"  >
					  <input type="hidden"  name="field_datareferid7" value="`+datareferid7+`" class="form-control11">
					  <input type="hidden"  name="field_datareferid15" value="`+datareferid15+`" class="form-control19">
					  <input type="hidden"  name="field_datareferid16" value="`+datareferid16+`" class="form-control20">
					  <input type="hidden"  name="field_datareferid8" value="`+datareferid8+`" class="form-control12"  >
					  <input type="hidden"  name="field_datareferid9" value="`+datareferid9+`" class="form-control13"  >
					  <input type="hidden"  name="field_datareferid10" value="`+datareferid10+`" class="form-control14"  >
					  <input type="hidden"  name="field_datareferid11" value="`+datareferid11+`" class="form-control15"  >
					  <input type="hidden"  name="field_datareferid12" value="`+datareferid12+`" class="form-control16"  >
					  <input type="hidden"  name="field_datareferid13" value="`+datareferid13+`" class="form-control17"  >
					  <input type="hidden"  name="field_datareferid14" value="`+datareferid14+`" class="form-control18"  >
					  <input type="hidden"  name="field_datareferid17" value="`+datareferid17+`" class="form-control21"  >
					  <input type="hidden"  name="field_datareferid18" value="`+datareferid18+`" class="form-control22"  >
					  <input type="hidden"  name="field_datareferid19" value="`+datareferid19+`" class="form-control23"  >
						  <input type="hidden"  name="field_datareferid20" value="`+datareferid20+`" class="form-control24"  >
					  <input type="hidden"  name="field_dataport" value="`+field_dataport+`" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
                     <select class="form-select mydata" id="select`+ids+`"/></select>
                 </div>
                 </div>
              
         `;
    
             let newTableHeader = document.createElement('th');
 		newTableHeader.textContent = label;
 		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
 		newTableHeader.classList.add('fixhead'); 
 		
 		const theadElement = document.getElementById('tabledata');
 		const tableRow = theadElement.querySelector('tr');
 		
 		tableRow.appendChild(newTableHeader);      
         
 		const nodemain = document.querySelector('.node1');       

 		var dataold = adddatasets(datarefertype,datareferid,ids);
 		
 	
 		
 		
 		newInputs = document.createElement('div');
 	    newInputs.className = 'agm';        
 		newInputs.innerHTML = '<input id="1" value="'+label+'" class="change-label2"  readonly><span id="ported'+ids+'"  class="port port1" ></span>';
 		nodemain.appendChild(newInputs); 
         
         
          // ดึงข้อมูลเริ่มต้นของ label จาก input
     const initialLabelValue = newInput.querySelector('.change-label').value;
     const formLabel = newTableHeader.querySelector('.form-label'); 
     const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
     // หากมีการเปลี่ยนแปลงค่าใน input change-label
     newInput.querySelector('.change-label').addEventListener('input', (event) => {
         // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
         newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
         newTableHeader.textContent = event.target.value || initialLabelValue;
         newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
     });

 	    const deleteButton = newInput.querySelector('.delete-button');
 	    deleteButton.addEventListener('click', () => {
 	        newInput.remove(); // ลบ div ตัวเอง
 	        newInputs.remove(); // ลบ div ตัวเอง
 	        newTableHeader.remove();
 	    });
     
         
 	   /*
        // เลือกองค์ประกอบ select
        const selectElement = newInput.querySelector('.tax-select');

        // สร้างองค์ประกอบ change-label
        const changeLabelElement = newInput.querySelector('.change-label');

        // เมื่อมีการเปลี่ยนแปลงใน select
        selectElement.addEventListener('change', (event) => {
          // ดึงค่า data จากตัวเลือกที่ถูกเลือก
          const selectedOption = selectElement.options[selectElement.selectedIndex];
          const dataValue = selectedOption.getAttribute('data');

          // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
          changeLabelElement.value = dataValue || 'Text';

          // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
          newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
          newTableHeader.textContent = changeLabelElement.value || 'Text';
          newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
        });      
        */
        
         

     }
     
     
     if (elementType === 'checkbox') {
         newInput = document.createElement('div');
         newInput.className = 'row draggable-row';
         newInput.innerHTML = `
             <div class="col-md-12 col-12">
                 <div class="Selectdata mb-1">
                     <label class="form-label" for="itemname">`+label+`
                     </label>
                     
          
                
                    
                     <div class="dropdown pop" style="float:right;margin-right:20px;">
                     <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                     Setup
                     </i>
                         <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                             <div class="mb-1">
                                 <label for="discount-input" class="form-label">Label</label>
                                 <input type="text" class="form-control change-label" value="`+label+`"   name="field_label"/>
                                 	<input type="hidden"  value="checkbox" name="field_type"/>
                             </div>
                             <div class="form-row mt-50" style="display:none">
                                 <div class="mb-1 col-md-12">
                                     <label for="tax-1-input" class="form-label">MAIN Field</label>
                                     <select  id="tax-1-input" class="form-select" name="field_mainfield">
                                     <option value="`+mainfield+`" selected>`+mainfield+`</option>
                                     <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
                                     </select>
                                 </div>
                             
                             </div>
                             
                             
                             <div class="form-row mt-50" style="display:none">
                             <div class="mb-1 col-md-12">
                             <label for="tax-1-input" class="form-label">Sub Field</label>
                                 <select  id="tax-1-input" class="form-select" name="field_subfield">
                             
                                 <option value="-" >-</option>
                                     <c:forEach items="${columntype2 }" var="row" varStatus="count">
                                     <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
             						  </c:forEach>
                                 </select>
                             </div>
                         
                         </div>
                             
                             
                             
                             <div class="dropdown-divider my-1"></div>
                             <div class="d-flex justify-content-between">
                                 <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                 <button type="button" class="btn btn-outline-secondary">ปิด</button>
                             </div>
                         </div>
                     </div>
                
             <br>   
             
             <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
			  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
			  <input type="hidden"  name="field_datareferid2" value="`+datareferid2+`" class="form-control5"  >
			  <input type="hidden"  name="field_datareferid3" value="`+datareferid3+`" class="form-control6"  >
			  <input type="hidden"  name="field_datareferid4" value="`+datareferid4+`" class="form-control7"  >
			  <input type="hidden"  name="field_datareferid5" value="`+datareferid5+`" class="form-control8"  >
			  <input type="hidden"  name="field_datareferid6" value="`+datareferid6+`" class="form-control9"  >
			  <input type="hidden"  name="field_datareferid7" value="`+datareferid7+`" class="form-control11">
			  <input type="hidden"  name="field_datareferid15" value="`+datareferid15+`" class="form-control19">
			  <input type="hidden"  name="field_datareferid16" value="`+datareferid16+`" class="form-control20">
			  <input type="hidden"  name="field_datareferid8" value="`+datareferid8+`" class="form-control12"  >
			  <input type="hidden"  name="field_datareferid9" value="`+datareferid9+`" class="form-control13"  >
			  <input type="hidden"  name="field_datareferid10" value="`+datareferid10+`" class="form-control14"  >
			  <input type="hidden"  name="field_datareferid11" value="`+datareferid11+`" class="form-control15"  >
			  <input type="hidden"  name="field_datareferid12" value="`+datareferid12+`" class="form-control16"  >
			  <input type="hidden"  name="field_datareferid13" value="`+datareferid13+`" class="form-control17"  >
			  <input type="hidden"  name="field_datareferid14" value="`+datareferid14+`" class="form-control18"  >
			  <input type="hidden"  name="field_datareferid17" value="`+datareferid17+`" class="form-control21"  >
			  <input type="hidden"  name="field_datareferid18" value="`+datareferid18+`" class="form-control22"  >
			  <input type="hidden"  name="field_datareferid19" value="`+datareferid19+`" class="form-control23"  >
				  <input type="hidden"  name="field_datareferid20" value="`+datareferid20+`" class="form-control24"  >
			  <input type="hidden"  name="field_dataport" value="`+field_dataport+`" class="form-control10"  >
			   <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
                     <span class="checkbox" id="checkbox`+ids+`"><input type="checkbox" class="mydata"/> Checkbox</span>
                 </div>
                 </div>
           
         `;
    
             let newTableHeader = document.createElement('th');
 		newTableHeader.textContent = label;
 		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
 		newTableHeader.classList.add('fixhead'); 
 		
 		const theadElement = document.getElementById('tabledata');
 		const tableRow = theadElement.querySelector('tr');
 	
 		tableRow.appendChild(newTableHeader);      
         
 		const nodemain = document.querySelector('.node1');       
      
 	
 		var dataold = adddatasets(datarefertype,datareferid,ids);
 		

 		
 		
 		
 		newInputs = document.createElement('div');
 	    newInputs.className = 'agm';        
 		newInputs.innerHTML = '<input id="1" value="'+label+'" class="change-label2"  readonly><span id="ported'+ids+'"  class="port port1" ></span>';
 		nodemain.appendChild(newInputs);  
         
         
          // ดึงข้อมูลเริ่มต้นของ label จาก input
     const initialLabelValue = newInput.querySelector('.change-label').value;
     const formLabel = newTableHeader.querySelector('.form-label'); 
     const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
     // หากมีการเปลี่ยนแปลงค่าใน input change-label
     newInput.querySelector('.change-label').addEventListener('input', (event) => {
         // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
         newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
         newTableHeader.textContent = event.target.value || initialLabelValue;
         newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
     });

 	    const deleteButton = newInput.querySelector('.delete-button');
 	    deleteButton.addEventListener('click', () => {
 	        newInput.remove(); // ลบ div ตัวเอง
 	        newInputs.remove(); // ลบ div ตัวเอง
 	        newTableHeader.remove();
 	    });
     
         
 	   /*
        // เลือกองค์ประกอบ select
        const selectElement = newInput.querySelector('.tax-select');

        // สร้างองค์ประกอบ change-label
        const changeLabelElement = newInput.querySelector('.change-label');

        // เมื่อมีการเปลี่ยนแปลงใน select
        selectElement.addEventListener('change', (event) => {
          // ดึงค่า data จากตัวเลือกที่ถูกเลือก
          const selectedOption = selectElement.options[selectElement.selectedIndex];
          const dataValue = selectedOption.getAttribute('data');

          // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
          changeLabelElement.value = dataValue || 'Text';

          // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
          newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
          newTableHeader.textContent = changeLabelElement.value || 'Text';
          newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
        });      
        */
        
         

     }
     
     
     
     
     
   
     
     if (elementType === 'Title') {
         newInput = document.createElement('div');
         newInput.className = 'row draggable-row';
         newInput.innerHTML = `
             <div class="col-md-12 col-12">
         	 <div class="form-title" style="background-color: #d9d8f2;padding: 10px;margin-bottom:5px;">
             <h4 class="form-title-text">`+label+`</h4>
             

             <div class="dropdown pop" style="float:right;margin-right:20px;top:-45">
             <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
             Setup
             </i>
                 <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                     <div class="mb-1">
                         <label for="discount-input" class="form-label">Label</label>
                         <input type="text" class="form-control change-label" value="`+label+`" name="field_label"/>
                         	<input type="hidden"  value="Title" name="field_type"/>
                         	<input type="hidden"  value="-" name="field_mainfield"/>
                         		<input type="hidden"  value="-" name="field_subfield"/>
                         		  <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
   						  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
   						  <input type="hidden"  name="field_datareferid2" value="`+datareferid2+`" class="form-control5"  >
   						  <input type="hidden"  name="field_datareferid3" value="`+datareferid3+`" class="form-control6"  >
   						  <input type="hidden"  name="field_datareferid4" value="`+datareferid4+`" class="form-control7"  >
   						  <input type="hidden"  name="field_datareferid5" value="`+datareferid5+`" class="form-control8"  >
   						  <input type="hidden"  name="field_datareferid6" value="`+datareferid6+`" class="form-control9"  >
   						  <input type="hidden"  name="field_datareferid7" value="`+datareferid7+`" class="form-control11">
   						  <input type="hidden"  name="field_datareferid15" value="`+datareferid15+`" class="form-control19">
   						  <input type="hidden"  name="field_datareferid16" value="`+datareferid16+`" class="form-control20">
   						  <input type="hidden"  name="field_datareferid8" value="`+datareferid8+`" class="form-control12"  >
   						  <input type="hidden"  name="field_datareferid9" value="`+datareferid9+`" class="form-control13"  >
   						  <input type="hidden"  name="field_datareferid10" value="`+datareferid10+`" class="form-control14"  >
   						  <input type="hidden"  name="field_datareferid11" value="`+datareferid11+`" class="form-control15"  >
   						  <input type="hidden"  name="field_datareferid12" value="`+datareferid12+`" class="form-control16"  >
   						  <input type="hidden"  name="field_datareferid13" value="`+datareferid13+`" class="form-control17"  >
   						  <input type="hidden"  name="field_datareferid14" value="`+datareferid14+`" class="form-control18"  >
   						  <input type="hidden"  name="field_datareferid17" value="`+datareferid17+`" class="form-control21"  >
   						  <input type="hidden"  name="field_datareferid18" value="`+datareferid18+`" class="form-control22"  >
   						  <input type="hidden"  name="field_datareferid19" value="`+datareferid19+`" class="form-control23"  >
 						  <input type="hidden"  name="field_datareferid20" value="`+datareferid20+`" class="form-control24"  >
   						  <input type="hidden"  name="field_dataport" value="`+field_dataport+`" class="form-control10"  >
   						   <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
                         	
                     </div>
                     <div class="form-row mt-50" style="display:none">
                         <div class="mb-1 col-md-6">
                             <label for="tax-1-input" class="form-label">ขนาด</label>
                             <select name="tax-1-input" id="tax-1-input" class="form-select">
                                 <option value="0%" selected>0%</option>
                                 <option value="1%">1%</option>
                                 <option value="10%">10%</option>
                                 <option value="18%">18%</option>
                                 <option value="40%">40%</option>
                             </select>
                         </div>
                     
                     </div>
                     <div class="dropdown-divider my-1"></div>
                     <div class="d-flex justify-content-between">
                         <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                         <button type="button" class="btn btn-outline-secondary">ปิด</button>
                     </div>
                 </div>
             </div>
         </div>
         
        
         </div>
             
         `;
         
         
         // ดึงข้อมูลเริ่มต้นของ label จาก input
         const initialLabelValue = newInput.querySelector('.change-label').value;

         // หากมีการเปลี่ยนแปลงค่าใน input change-label
         newInput.querySelector('.change-label').addEventListener('input', (event) => {
             // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
             newInput.querySelector('.form-title-text').textContent = event.target.value || initialLabelValue;
         });

             const deleteButton = newInput.querySelector('.delete-button');
             deleteButton.addEventListener('click', () => {
                 newInput.remove(); // ลบ div ตัวเอง
             });
     } 
     
     if (elementType === 'SubTitle') {
         newInput = document.createElement('div');
         newInput.className = 'row draggable-row';
         newInput.innerHTML = `
             <div class="col-md-12 col-12">
         	 <div class="form-title" style="padding: 10px;margin-bottom:5px;text-align: center;">
             <h6 class="form-title-text">`+label+`</h6>
             

             <div class="dropdown pop" style="float:right;margin-right:20px;top:-45">
             <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
             Setup
             </i>
                 <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                     <div class="mb-1">
                         <label for="discount-input" class="form-label">Label</label>
                         <input type="text" class="form-control change-label" value="`+label+`" name="field_label"/>
                         
                         
                         	<input type="hidden"  value="SubTitle" name="field_type"/>
                         	
                              	<input type="hidden"  value="0" name="field_mainfield"/>
                              		<input type="hidden"  value="-" name="field_subfield"/>
                              	
                              	  <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
       						  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
       						  <input type="hidden"  name="field_datareferid2" value="`+datareferid2+`" class="form-control5"  >
       						  <input type="hidden"  name="field_datareferid3" value="`+datareferid3+`" class="form-control6"  >
       						  <input type="hidden"  name="field_datareferid4" value="`+datareferid4+`" class="form-control7"  >
       						  <input type="hidden"  name="field_datareferid5" value="`+datareferid5+`" class="form-control8"  >
       						  <input type="hidden"  name="field_datareferid6" value="`+datareferid6+`" class="form-control9"  >
       						  <input type="hidden"  name="field_datareferid7" value="`+datareferid7+`" class="form-control11">
       						  <input type="hidden"  name="field_datareferid15" value="`+datareferid15+`" class="form-control19">
       						  <input type="hidden"  name="field_datareferid16" value="`+datareferid16+`" class="form-control20">
       						  <input type="hidden"  name="field_datareferid8" value="`+datareferid8+`" class="form-control12"  >
       						  <input type="hidden"  name="field_datareferid9" value="`+datareferid9+`" class="form-control13"  >
       						  <input type="hidden"  name="field_datareferid10" value="`+datareferid10+`" class="form-control14"  >
       						  <input type="hidden"  name="field_datareferid11" value="`+datareferid11+`" class="form-control15"  >
       						  <input type="hidden"  name="field_datareferid12" value="`+datareferid12+`" class="form-control16"  >
       						  <input type="hidden"  name="field_datareferid13" value="`+datareferid13+`" class="form-control17"  >
       						  <input type="hidden"  name="field_datareferid14" value="`+datareferid14+`" class="form-control18"  >
       						  <input type="hidden"  name="field_datareferid17" value="`+datareferid17+`" class="form-control21"  >
       						  <input type="hidden"  name="field_datareferid18" value="`+datareferid18+`" class="form-control22"  >
       						  <input type="hidden"  name="field_datareferid19" value="`+datareferid19+`" class="form-control23"  >
     						  <input type="hidden"  name="field_datareferid20" value="`+datareferid20+`" class="form-control24"  >
       						  <input type="hidden"  name="field_dataport" value="`+field_dataport+`" class="form-control10"  >
       						   <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
        					   
        					   
                     </div>
                     <div class="form-row mt-50" style="display:none">
                         <div class="mb-1 col-md-6">
                             <label for="tax-1-input" class="form-label">ขนาด</label>
                             <select name="tax-1-input" id="tax-1-input" class="form-select">
                                 <option value="0%" selected>0%</option>
                                 <option value="1%">1%</option>
                                 <option value="10%">10%</option>
                                 <option value="18%">18%</option>
                                 <option value="40%">40%</option>
                             </select>
                         </div>
                     
                     </div>
                     <div class="dropdown-divider my-1"></div>
                     <div class="d-flex justify-content-between">
                         <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                         <button type="button" class="btn btn-outline-secondary">ปิด</button>
                     </div>
                 </div>
             </div>
         </div>
         
        
         </div>
             
         `;
         
         
         // ดึงข้อมูลเริ่มต้นของ label จาก input
         const initialLabelValue = newInput.querySelector('.change-label').value;

         // หากมีการเปลี่ยนแปลงค่าใน input change-label
         newInput.querySelector('.change-label').addEventListener('input', (event) => {
             // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
             newInput.querySelector('.form-title-text').textContent = event.target.value || initialLabelValue;
         });

             const deleteButton = newInput.querySelector('.delete-button');
             deleteButton.addEventListener('click', () => {
                 newInput.remove(); // ลบ div ตัวเอง
             });
     } 
 	  
 	  
 	  
 	  
 	  
 	  
 	  

 	  
 	  // เพิ่มองค์ประกอบ Input ลงใน form-preview
 	    formPreviews.appendChild(newInput);
 	    
 	    // เพิ่มการลากและวางให้กับองค์ประกอบใหม่
 	    makeElementDraggable2(newInput);
 	    

 	    
 	    
 	    // เพิ่มการสลับตำแหน่งได้
 	    makeElementSortable2();

        
 	    
 	  
 
        if(dataold && elementType === 'select'){
        	
     	 
 		      	 var selected = document.getElementById("select"+ids);
 		      	 var values = dataold.split(',');
 	
 		       for (var i = 0; i < values.length; i++) {
 		      	 
 				      	 if (values[i].trim() !== '') {
 				           // สร้าง <option> element
 				           var option = document.createElement('option');
 				           option.value = values[i];
 				           option.textContent = values[i];
 				           
 				           // เพิ่ม <option> ลงใน <select>
 				           selected.appendChild(option);
 				      	 }
 				           
 		           
 		       }

        }
        if(dataold && elementType === 'checkbox'){
        	
        	 
	      	 var checkbox = document.getElementById("checkbox"+ids);
	      	 var values = dataold.split(',');

	      	 
	      	 $(checkbox).html(''); 
	      	 
	     	    var hiddenInput = document.createElement('input');
	     	    hiddenInput.type = 'hidden';
	          	hiddenInput.className = 'mydata';
	          	hiddenInput.value = "";
		        checkbox.appendChild(hiddenInput);
		        
	      	 
	      	 for (var i = 0; i < values.length; i++) {
	      		 
	      		    if (values[i].trim() !== '') {
	      		  
	      		    	
	      		        // สร้าง <input> element ประเภท checkbox
	      		        var checkboxs = document.createElement('input');
	      		        checkboxs.type = 'checkbox';
	      		        checkboxs.className = 'mydatas';
	      		        checkboxs.value = values[i];

	      		        
	      		        // สร้าง <label> element ที่มีค่าจาก values[i]
	      		        var label = document.createElement('label');
	      		        label.textContent = values[i];
	      		        
	      		        // เพิ่ม <input> และ <label> ลงใน <span class="checkbox">
	      		        checkbox.appendChild(checkboxs);
	      		        checkbox.appendChild(label);
	      		        
	      		        // เพิ่มขีดจำนวนระหว่าง checkbox
	      		        checkbox.appendChild(document.createElement('br'));
	      		        
	      		        checkboxs.addEventListener('change', function() {
	      		        
	      		            var selectedValues = [];
	      		            var checkboxes = checkbox.querySelectorAll('.mydatas:checked');
	      		            checkboxes.forEach(function(checkbox) {
	      		       
	      		            	
	      		                selectedValues.push(checkbox.value);
	      		            });
	      		            hiddenInput.value = selectedValues.join(',');
	      		        });
	      		        
	      		    }
	      		    
	      		    
	      		  
	      		}

   }
 }
   
   

   function makeElementDraggable2(element) {

       element.draggable = true;
       
       element.addEventListener('dragstart', (e) => {
           isDragging = true;
           dragElement = element;
           offsetX = e.clientX - element.getBoundingClientRect().left;
           offsetY = e.clientY - element.getBoundingClientRect().top;
       });
       
       element.addEventListener('dragend', () => {
           isDragging = false;
           dragElement = null;
       });
   }

   function makeElementSortable2() {
       const draggableRows = document.querySelectorAll('.draggable-row');
       
       draggableRows.forEach((element) => {
       	
           element.addEventListener('dragover', (e) => {
               e.preventDefault();
               const targetElement = e.target.closest('.draggable-row');
               if (!targetElement || targetElement === dragElement) return;
               const rect = targetElement.getBoundingClientRect();
               const isBelow = e.clientY > rect.top + rect.height / 2;
               
               // ตรวจสอบว่าตำแหน่งเป้าหมายของการวางอยู่ภายในองค์ประกอบอื่น
               if (isBelow) {
                   // เพิ่มองค์ประกอบลงในองค์ประกอบเป้าหมาย
                   
     
                   targetElement.appendChild(dragElement);
               } else {
                   // เพิ่มองค์ประกอบลงหน้าองค์ประกอบเป้าหมาย
                   

                   formPreview.insertBefore(dragElement, targetElement);
                   
                   
            
                   
               }
           });
           element.addEventListener('dragstart', (e) => {
           	  const targetElement = e.target.closest('.draggable-row');
               e.dataTransfer.setData('text/plain2', element.textContent);
           });
         
           
           // เพิ่ม event listener สำหรับการวางลงใน <div class="mb-1 col-md-6 sub">
           element.addEventListener('drop', (e) => {
           	
               e.preventDefault();
               const elementType = e.dataTransfer.getData('text/plain2');

               //alert(targetElementID);
               // ตรวจสอบว่าลากและวางลงใน <div class="mb-1 col-md-6 sub">
               if (elementType.trim() === 'Number') {
                   const subElement = element.querySelector('.mb-1.col-md-6.sub');
                   subElement.innerHTML ="";
                   newInput = document.createElement('div');
                   newInput.className = 'row draggable-row';
                   newInput.innerHTML = `
                       <div class="col-md-12 col-12">
                           <div class="mb-1">
                               <label class="form-label" for="itemname">Number</label>
                               <input type="number" class="form-control" name="condition_title" value=""/>
                           </div>
                       </div>
                   `;
                   
                   subElement.appendChild(newInput);
                   
                   e.stopPropagation();
               }
           });
           
       });
       
       

     

       
       
       
   }
   
   
   
   
   
   
   function adddatasets(type,id,ids) {
	   
	   
	   
	    if (type === "dataset") {
	    	 // ดึงค่า value ของ <select> โดยใช้ jQuery
	        var selectedValue = $("#dataset_data").val();

	        // หา <option> ที่มี value ตรงกับ selectedValue โดยใช้ jQuery
	        var selectedOption = $("#dataset_data option[value='"+id+"']");

	        // ดึงค่า data-custom จาก selectedOption โดยใช้ jQuery
	        var dataCustomValue = selectedOption.attr("data-custom");
	  

	        var node = $("#dataset_node").val();
	    
	    	
	    	let NewInput;
	    	NewInput = document.createElement('div');
	    	NewInput.className = 'agm';
	    	NewInput.innerHTML = '<span id="port2" class="port2 ported'+ids+'"></span><input id="2" value="'+dataCustomValue+'" data-custom ="'+id+'" data-custom2 ="dataset" readonly>';
	        
	        //dataset_data
	       // dataset_node

		   const nodePreview = document.querySelector('#dataset_node1');
		   nodePreview.appendChild(NewInput);
	        
	        
		   return dataCustomValue;
	      
	    }
	    
	    return null;
        
	}
    
  
  </script>
  
  
  
    <c:forEach items="${eformfields }" var="row" varStatus="count">

       <script>
     //step01
       addfield("${row.field_type}","myid${row.field_id}","${row.field_label}","${row.field_datarefertype}"
    		   ,"${row.field_datareferid}","${row.field_datareferid2}","${row.field_datareferid3}","${row.field_datareferid4}","${row.field_datareferid5}","${row.field_datareferid6}","${row.field_datareferid7}","${row.field_datareferid15}","${row.field_datareferid16}"
    		   ,"${row.field_datareferid8}","${row.field_datareferid9}","${row.field_datareferid10}","${row.field_datareferid11}","${row.field_datareferid12}"
    		   ,"${row.field_datareferid13}","${row.field_datareferid14}","${row.field_datareferid17}","${row.field_datareferid18}","${row.field_datareferid19}","${row.field_datareferid20}"
    		  ,"${row.field_dataport}","${row.field_datarefercols}","${row.field_cal_type}","${row.field_cal_type2}");
       </script>
       
       
          
    </c:forEach>

    <script>

    
    
const draggableElements = document.querySelectorAll('.draggable');
const draggableElementssub = document.querySelectorAll('.draggable-row');
const formPreview = document.querySelector('.form-preview');


draggableElements.forEach((element) => {
    element.addEventListener('dragstart', (e) => {
        e.dataTransfer.setData('text/plain', element.textContent);
    });
});


let isDragging = false;
let dragElement = null;
var edit = false;



formPreview.addEventListener('dragover', (e) => {
    e.preventDefault();
});
var ids = 10000;
formPreview.addEventListener('drop', (e) => {
    e.preventDefault();
    const elementType = e.dataTransfer.getData('text/plain');
    
    // สร้างองค์ประกอบ Input ที่จะเพิ่ม
    let newInput;
    let newInputs;
 
    edit = true;
    
    
    
    if (elementType === 'Text') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
                <div class="mb-1 getported`+ids+`" >
                    <label class="form-label" for="itemname">Text</label>
                    
                    
                    
                    <div class="dropdown pop" style="float:right;margin-right:20px;">
                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Setup
                    </i>
                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                            <div class="mb-1">
                                <label for="discount-input" class="form-label">label</label>
                                <input type="text" class="form-control change-label" value="text" name="field_label"/>
                                 <input type="hidden"  value="text" name="field_type"/>
                            </div>
                            <div class="form-row mt-50" style="display:none">
                                <div class="mb-1 col-md-12">
                                <label for="tax-1-input" class="form-label">Main Field</label>
                                    <select id="tax-1-input" class="form-select " name="field_mainfield">
                                
                                    <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ] </option>
	            						  </c:forEach>
                                    </select>
                                </div>
                            
                            </div>
                            
                            
                            
                            <div class="form-row mt-50" style="display:none">
	                            <div class="mb-1 col-md-12">
	                            <label for="tax-1-input" class="form-label">Action type</label>
	                                <select  id="tax-1-input" class="form-select" name="field_subfield">
	                                <option value="-">-</option>
 	                                <option value="verification" >Verification</option>
 	                                <option value="register">register</option>
	            						
	                                </select>
	                            </div>
	                        
	                        </div>
                            
                            
                            <div class="dropdown-divider my-1"></div>
                            <div class="d-flex justify-content-between">
                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
                            </div>
                        </div>
                    </div>
                    <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="-" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="-" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="-" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="-" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="-" class="form-control9"  >
					  <input type="hidden"  name="field_datareferid7" value="-" class="form-control11">
					  <input type="hidden"  name="field_datareferid15" value="-" class="form-control19">
					  <input type="hidden"  name="field_datareferid16" value="-" class="form-control20">
					  <input type="hidden"  name="field_datareferid8" value="-" class="form-control12"  >
					  <input type="hidden"  name="field_datareferid9" value="-" class="form-control13"  >
					  <input type="hidden"  name="field_datareferid10" value="-" class="form-control14"  >
					  <input type="hidden"  name="field_datareferid11" value="-" class="form-control15"  >
					  <input type="hidden"  name="field_datareferid12" value="-" class="form-control16"  >
					  <input type="hidden"  name="field_datareferid13" value="-" class="form-control17"  >
					  <input type="hidden"  name="field_datareferid14" value="-" class="form-control18"  >
					  <input type="hidden"  name="field_datareferid17" value="-" class="form-control21"  >
					  <input type="hidden"  name="field_datareferid18" value="-" class="form-control22"  >
					  <input type="hidden"  name="field_datareferid19" value="-"  class="form-control23"  >
						  <input type="hidden"  name="field_datareferid20"  value="-"  class="form-control24"  >
					  <input type="hidden"  name="field_dataport" value="-" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >
                    <input type="text" class="form-control mydata" name="condition_title" value=""/>
                </div>
            </div>
        `;
        
        
      
        let newTableHeader = document.createElement('th');
		newTableHeader.textContent = 'text';
		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
		newTableHeader.classList.add('fixhead'); 
		
		const theadElement = document.getElementById('tabledata');
		const tableRow = theadElement.querySelector('tr');
		
		tableRow.appendChild(newTableHeader);
		      
		const nodemain = document.querySelector('.node1');
		ids++;
		
		
		newInputs = document.createElement('div');
	    newInputs.className = 'agm';        
		newInputs.innerHTML = '<input id="1" value="text" class="change-label2"  readonly><span id="ported'+ids+'"  class="port port1" ></span>';
		  nodemain.appendChild(newInputs);
        
		  
		  
        // ดึงข้อมูลเริ่มต้นของ label จาก input
        const initialLabelValue = newInput.querySelector('.change-label').value;
        const formLabel = newTableHeader.querySelector('.form-label'); 
        const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
        
        
        // หากมีการเปลี่ยนแปลงค่าใน input change-label
        newInput.querySelector('.change-label').addEventListener('input', (event) => {
            // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
            newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
            newTableHeader.textContent = event.target.value || initialLabelValue;
            newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
            
        });

      
            const deleteButton = newInput.querySelector('.delete-button');
            deleteButton.addEventListener('click', () => {
                newInput.remove(); // ลบ div ตัวเอง
                newInputs.remove(); // ลบ div ตัวเอง
                newTableHeader.remove();
            });
            
            
            /*
            // เลือกองค์ประกอบ select
            const selectElement = newInput.querySelector('.tax-select');

            // สร้างองค์ประกอบ change-label
            const changeLabelElement = newInput.querySelector('.change-label');

            // เมื่อมีการเปลี่ยนแปลงใน select
            selectElement.addEventListener('change', (event) => {
              // ดึงค่า data จากตัวเลือกที่ถูกเลือก
              const selectedOption = selectElement.options[selectElement.selectedIndex];
              const dataValue = selectedOption.getAttribute('data');

              // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
              changeLabelElement.value = dataValue || 'Text';

              // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
              newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
              newTableHeader.textContent = changeLabelElement.value || 'Text';
              newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
            });      
            */
            
            
        
        
    }
    
    
    if (elementType === 'Time') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
                <div class="mb-1">
                    <label class="form-label" for="itemname">Time</label>
                    
                    
                    
                    <div class="dropdown pop" style="float:right;margin-right:20px;">
                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Setup
                    </i>
                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                            <div class="mb-1">
                                <label for="discount-input" class="form-label">label</label>
                                <input type="text" class="form-control change-label" value="time" name="field_label"/>
                                 <input type="hidden"  value="time" name="field_type"/>
                            </div>
                            <div class="form-row mt-50" style="display:none">
                                <div class="mb-1 col-md-12">
                                <label for="tax-1-input" class="form-label">Main Field</label>
                                    <select id="tax-1-input" class="form-select" name="field_mainfield">
                                
                                    <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
                                    </select>
                                </div>
                            
                            </div>
                            
                            
                            
                            <div class="form-row mt-50" style="display:none">
                            <div class="mb-1 col-md-12">
                            <label for="tax-1-input" class="form-label">Sub Field</label>
                                <select  id="tax-1-input" class="form-select" name="field_subfield">
                            
                                <option value="-" >-</option>
                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
            						  </c:forEach>
                                </select>
                            </div>
                        
                        </div>
                            
                            
                            
                            
                            <div class="dropdown-divider my-1"></div>
                            <div class="d-flex justify-content-between">
                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
                            </div>
                        </div>
                    </div>
                 
                    <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="-" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="-" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="-" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="-" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="-" class="form-control9"  >
					  <input type="hidden"  name="field_datareferid7" value="-" class="form-control11">
					  <input type="hidden"  name="field_datareferid15" value="-" class="form-control19">
					  <input type="hidden"  name="field_datareferid16" value="-" class="form-control20">
					  <input type="hidden"  name="field_datareferid8" value="-" class="form-control12"  >
					  <input type="hidden"  name="field_datareferid9" value="-" class="form-control13"  >
					  <input type="hidden"  name="field_datareferid10" value="-" class="form-control14"  >
					  <input type="hidden"  name="field_datareferid11" value="-" class="form-control15"  >
					  <input type="hidden"  name="field_datareferid12" value="-" class="form-control16"  >
					  <input type="hidden"  name="field_datareferid13" value="-" class="form-control17"  >
					  <input type="hidden"  name="field_datareferid14" value="-" class="form-control18"  >
					  <input type="hidden"  name="field_datareferid17" value="-" class="form-control21"  >
					  <input type="hidden"  name="field_datareferid18" value="-" class="form-control22"  >
					  <input type="hidden"  name="field_datareferid19" value="-"  class="form-control23"  >
					  <input type="hidden"  name="field_datareferid20"  value="-"  class="form-control24"  >
					  <input type="hidden"  name="field_dataport" value="-" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >

                    	 <input type="text" class="form-control time-mask mydata" placeholder="hh:mm:ss" id="time">
                </div>
            </div>
        `;
        
        
      
        let newTableHeader = document.createElement('th');
		newTableHeader.textContent = 'time';
		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
		newTableHeader.classList.add('fixhead'); 
		
		const theadElement = document.getElementById('tabledata');
		const tableRow = theadElement.querySelector('tr');
		
		tableRow.appendChild(newTableHeader);
		      
		const nodemain = document.querySelector('.node1');
		ids++;
		
		
		newInputs = document.createElement('div');
	    newInputs.className = 'agm';        
		newInputs.innerHTML = '<input id="1" value="time" class="change-label2"  readonly>';
		  nodemain.appendChild(newInputs);
        
		  
		  
        // ดึงข้อมูลเริ่มต้นของ label จาก input
        const initialLabelValue = newInput.querySelector('.change-label').value;
        const formLabel = newTableHeader.querySelector('.form-label'); 
        const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
        
        
        // หากมีการเปลี่ยนแปลงค่าใน input change-label
        newInput.querySelector('.change-label').addEventListener('input', (event) => {
            // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
            newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
            newTableHeader.textContent = event.target.value || initialLabelValue;
            newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
            
        });

      
            const deleteButton = newInput.querySelector('.delete-button');
            deleteButton.addEventListener('click', () => {
                newInput.remove(); // ลบ div ตัวเอง
                newInputs.remove(); // ลบ div ตัวเอง
                newTableHeader.remove();
            });
            
            
            /*
            // เลือกองค์ประกอบ select
            const selectElement = newInput.querySelector('.tax-select');

            // สร้างองค์ประกอบ change-label
            const changeLabelElement = newInput.querySelector('.change-label');

            // เมื่อมีการเปลี่ยนแปลงใน select
            selectElement.addEventListener('change', (event) => {
              // ดึงค่า data จากตัวเลือกที่ถูกเลือก
              const selectedOption = selectElement.options[selectElement.selectedIndex];
              const dataValue = selectedOption.getAttribute('data');

              // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
              changeLabelElement.value = dataValue || 'Text';

              // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
              newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
              newTableHeader.textContent = changeLabelElement.value || 'Text';
              newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
            });      
            */
            
            
            
            
        
        
    }
    
    
    if (elementType === 'Status') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
                <div class="mb-1">
                    <label class="form-label" for="itemname">Status</label>
                    
                    
                    
                    <div class="dropdown pop" style="float:right;margin-right:20px;">
                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Setup
                    </i>
                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                            <div class="mb-1">
                                <label for="discount-input" class="form-label">label</label>
                                <input type="text" class="form-control change-label" value="status" name="field_label"/>
                                 <input type="hidden"  value="status" name="field_type"/>
                            </div>
                            <div class="form-row mt-50" style="display:none">
                                <div class="mb-1 col-md-12">
                                <label for="tax-1-input" class="form-label">Main Field</label>
                                    <select id="tax-1-input" class="form-select" name="field_mainfield">
                                
                                    <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
                                    </select>
                                </div>
                            
                            </div>
                            
                            
                            
                            <div class="form-row mt-50" style="display:none">
                            <div class="mb-1 col-md-12">
                            <label for="tax-1-input" class="form-label">Sub Field</label>
                                <select  id="tax-1-input" class="form-select" name="field_subfield">
                            
                                <option value="-" >-</option>
                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
            						  </c:forEach>
                                </select>
                            </div>
                        
                        </div>
                            
                            
                            
                            
                            <div class="dropdown-divider my-1"></div>
                            <div class="d-flex justify-content-between">
                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
                            </div>
                        </div>
                    </div>
                 
                    <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="-" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="-" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="-" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="-" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="-" class="form-control9"  >
					  <input type="hidden"  name="field_datareferid7" value="-" class="form-control11">
					  <input type="hidden"  name="field_datareferid15" value="-" class="form-control19">
					  <input type="hidden"  name="field_datareferid16" value="-" class="form-control20">
					  <input type="hidden"  name="field_datareferid8" value="-" class="form-control12"  >
					  <input type="hidden"  name="field_datareferid9" value="-" class="form-control13"  >
					  <input type="hidden"  name="field_datareferid10" value="-" class="form-control14"  >
					  <input type="hidden"  name="field_datareferid11" value="-" class="form-control15"  >
					  <input type="hidden"  name="field_datareferid12" value="-" class="form-control16"  >
					  <input type="hidden"  name="field_datareferid13" value="-" class="form-control17"  >
					  <input type="hidden"  name="field_datareferid14" value="-" class="form-control18"  >
					  <input type="hidden"  name="field_datareferid17" value="-" class="form-control21"  >
					  <input type="hidden"  name="field_datareferid18" value="-" class="form-control22"  >
					  <input type="hidden"  name="field_datareferid19" value="-"  class="form-control23"  >
					  <input type="hidden"  name="field_datareferid20"  value="-"  class="form-control24"  >
					  <input type="hidden"  name="field_dataport" value="-" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >

					  <div>
					   <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                       <input type="radio" class="btn-check mydata" value="Good"  name="btnradio" id="btnradio1" autocomplete="off" >
                       <label class="btn btn-outline-success waves-effect" for="btnradio1">Good</label>

                       <input type="radio" class="btn-check mydata" value="Normal"   name="btnradio" id="btnradio2" autocomplete="off" >
                       <label class="btn btn-outline-warning waves-effect" for="btnradio2">Normal</label>

                       <input type="radio" class="btn-check mydata" value="Bad"  name="btnradio" id="btnradio3" autocomplete="off" >
                       <label class="btn btn-outline-danger waves-effect" for="btnradio3">Bad</label>
                       </div>
                   </div>
                </div>
            </div>
        `;
        
        
      
        let newTableHeader = document.createElement('th');
		newTableHeader.textContent = 'status';
		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
		newTableHeader.classList.add('fixhead'); 
		
		const theadElement = document.getElementById('tabledata');
		const tableRow = theadElement.querySelector('tr');
		
		tableRow.appendChild(newTableHeader);
		      
		const nodemain = document.querySelector('.node1');
		ids++;
		
		
		newInputs = document.createElement('div');
	    newInputs.className = 'agm';        
		newInputs.innerHTML = '<input id="1" value="time" class="change-label2"  readonly>';
		  nodemain.appendChild(newInputs);
        
		  
		  
        // ดึงข้อมูลเริ่มต้นของ label จาก input
        const initialLabelValue = newInput.querySelector('.change-label').value;
        const formLabel = newTableHeader.querySelector('.form-label'); 
        const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
        
        
        // หากมีการเปลี่ยนแปลงค่าใน input change-label
        newInput.querySelector('.change-label').addEventListener('input', (event) => {
            // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
            newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
            newTableHeader.textContent = event.target.value || initialLabelValue;
            newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
            
        });

      
            const deleteButton = newInput.querySelector('.delete-button');
            deleteButton.addEventListener('click', () => {
                newInput.remove(); // ลบ div ตัวเอง
                newInputs.remove(); // ลบ div ตัวเอง
                newTableHeader.remove();
            });
            
            
            /*
            // เลือกองค์ประกอบ select
            const selectElement = newInput.querySelector('.tax-select');

            // สร้างองค์ประกอบ change-label
            const changeLabelElement = newInput.querySelector('.change-label');

            // เมื่อมีการเปลี่ยนแปลงใน select
            selectElement.addEventListener('change', (event) => {
              // ดึงค่า data จากตัวเลือกที่ถูกเลือก
              const selectedOption = selectElement.options[selectElement.selectedIndex];
              const dataValue = selectedOption.getAttribute('data');

              // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
              changeLabelElement.value = dataValue || 'Text';

              // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
              newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
              newTableHeader.textContent = changeLabelElement.value || 'Text';
              newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
            });      
            */
            
            
            
            
        
        
    }
    
    
    
    if (elementType === 'Textarea') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
                <div class="mb-1 ">
                    <label class="form-label" for="itemname">Textarea</label>
                    
                    
                    
                    <div class="dropdown pop" style="float:right;margin-right:20px;">
                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Setup
                    </i>
                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                            <div class="mb-1">
                                <label for="discount-input" class="form-label">label</label>
                                <input type="text" class="form-control change-label" value="textarea" name="field_label"/>
                                 <input type="hidden"  value="textarea" name="field_type"/>
                            </div>
                            <div class="form-row mt-50" style="display:none">
                                <div class="mb-1 col-md-12">
                                <label for="tax-1-input" class="form-label">Main Field</label>
                                    <select  id="tax-1-input" class="form-select" name="field_mainfield">
                                
                                    <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
                                    </select>
                                </div>
                            
                            </div>
                            <div class="form-row mt-50" style="display:none">
                            <div class="mb-1 col-md-12">
                            <label for="tax-1-input" class="form-label">Sub Field</label>
                                <select  id="tax-1-input" class="form-select" name="field_subfield">
                            
                                <option value="-" >-</option>
                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
            						  </c:forEach>
                                </select>
                            </div>
                        
                        </div>
                            
                            
                            <div class="dropdown-divider my-1"></div>
                            <div class="d-flex justify-content-between">
                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
                            </div>
                        </div>
                    </div>
                    <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="-" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="-" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="-" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="-" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="-" class="form-control9"  >
					  <input type="hidden"  name="field_datareferid7" value="-" class="form-control11">
					  <input type="hidden"  name="field_datareferid15" value="-" class="form-control19">
					  <input type="hidden"  name="field_datareferid16" value="-" class="form-control20">
					  <input type="hidden"  name="field_datareferid8" value="-" class="form-control12"  >
					  <input type="hidden"  name="field_datareferid9" value="-" class="form-control13"  >
					  <input type="hidden"  name="field_datareferid10" value="-" class="form-control14"  >
					  <input type="hidden"  name="field_datareferid11" value="-" class="form-control15"  >
					  <input type="hidden"  name="field_datareferid12" value="-" class="form-control16"  >
					  <input type="hidden"  name="field_datareferid13" value="-" class="form-control17"  >
					  <input type="hidden"  name="field_datareferid14" value="-" class="form-control18"  >
					  <input type="hidden"  name="field_datareferid17" value="-" class="form-control21"  >
					  <input type="hidden"  name="field_datareferid18" value="-" class="form-control22"  >
					  <input type="hidden"  name="field_datareferid19" value="-"  class="form-control23"  >
					  <input type="hidden"  name="field_datareferid20"  value="-"  class="form-control24"  >
					  <input type="hidden"  name="field_dataport" value="-" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >
                    <textarea class="form-control mydata" name="condition_title" value=""/></textarea>
                </div>
            </div>
        `;
        
        
      
        let newTableHeader = document.createElement('th');
		newTableHeader.textContent = 'textarea';
		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
		newTableHeader.classList.add('fixhead'); 
		
		const theadElement = document.getElementById('tabledata');
		const tableRow = theadElement.querySelector('tr');
		
		tableRow.appendChild(newTableHeader);
		
		const nodemain = document.querySelector('.node1');       
		ids++;      
		newInputs = document.createElement('div');
	    newInputs.className = 'agm';        
		newInputs.innerHTML = '<input id="1" value="Textarea" class="change-label2"  readonly>';
		nodemain.appendChild(newInputs);     
        
        
        
        // ดึงข้อมูลเริ่มต้นของ label จาก input
        const initialLabelValue = newInput.querySelector('.change-label').value;
        const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
        const formLabel = newTableHeader.querySelector('.form-label'); 
        
        // หากมีการเปลี่ยนแปลงค่าใน input change-label
        newInput.querySelector('.change-label').addEventListener('input', (event) => {
            // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
            newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
            newTableHeader.textContent = event.target.value || initialLabelValue;
            newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
        });

      
            const deleteButton = newInput.querySelector('.delete-button');
            deleteButton.addEventListener('click', () => {
                newInput.remove(); // ลบ div ตัวเอง
                newTableHeader.remove();
                newInputs.remove(); // ลบ div ตัวเอง
            });
            
            
            /*
            // เลือกองค์ประกอบ select
            const selectElement = newInput.querySelector('.tax-select');

            // สร้างองค์ประกอบ change-label
            const changeLabelElement = newInput.querySelector('.change-label');

            // เมื่อมีการเปลี่ยนแปลงใน select
            selectElement.addEventListener('change', (event) => {
              // ดึงค่า data จากตัวเลือกที่ถูกเลือก
              const selectedOption = selectElement.options[selectElement.selectedIndex];
              const dataValue = selectedOption.getAttribute('data');

              // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
              changeLabelElement.value = dataValue || 'Text';

              // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
              newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
              newTableHeader.textContent = changeLabelElement.value || 'Text';
              newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
            });      
            */
              
            
            
            
        
        
    }
    
    
    if (elementType === 'Data') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `

                <div class="mb-1">
                    <label class="form-label" for="itemname">Data</label>
                    
                    
                    
                    <div class="dropdown pop" style="float:right;margin-right:20px;">
                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Setup
                    </i>
                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                            <div class="mb-1">
                                <label for="discount-input" class="form-label">label</label>
                                <input type="text" class="form-control change-label" value="data" name="field_label"/>
                                	<input type="hidden"  value="data" name="field_type"/>
                            </div>
                            <div class="form-row mt-50" style="display:none">
                                <div class="mb-1 col-md-12">
                                    <label for="tax-1-input" class="form-label">Main Field</label>
                                    <select  id="tax-1-input" class="form-select " name="field_mainfield">
                                    
                                    <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
            						  
            						  
                                    </select>
                                </div>
                            
                            </div>
                            
                            <div class="form-row mt-50" style="display:none">
                            <div class="mb-1 col-md-12">
                            <label for="tax-1-input" class="form-label">Sub Field</label>
                                <select  id="tax-1-input" class="form-select" name="field_subfield">
                            
                                <option value="-" >-</option>
                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
            						  </c:forEach>
                                </select>
                            </div>
                        
                        </div>
                            
                            
                            <div class="dropdown-divider my-1"></div>
                            <div class="d-flex justify-content-between">
                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
                            </div>
                        </div>
                    </div>
                    <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="-" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="-" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="-" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="-" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="-" class="form-"  >
					  <input type="hidden"  name="field_dataport" value="-" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >
                    <input type="text" class="form-control mydata" name="condition_title" value="" readonly/>
                </div>
           
        `;
    
        
  
        let newTableHeader = document.createElement('th');
		newTableHeader.textContent = 'Number';
		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
		newTableHeader.classList.add('fixhead'); 
		
		const theadElement = document.getElementById('tabledata');
		const tableRow = theadElement.querySelector('tr');
		
		tableRow.appendChild(newTableHeader);      
        
		const nodemain = document.querySelector('.node1');       
		ids++;      
		newInputs = document.createElement('div');
	    newInputs.className = 'agm';        
		newInputs.innerHTML = '<input id="1" value="Data" class="change-label2"  readonly><span id="'+ids+'"  class="port port1" ></span>';
		nodemain.appendChild(newInputs);     
        
		
        
        // ดึงข้อมูลเริ่มต้นของ label จาก input
   const initialLabelValue = newInput.querySelector('.change-label').value;
   const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
   // หากมีการเปลี่ยนแปลงค่าใน input change-label
   newInput.querySelector('.change-label').addEventListener('input', (event) => {
       // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
       newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
       newTableHeader.textContent = event.target.value || initialLabelValue;
       newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
   });

	   const deleteButton = newInput.querySelector('.delete-button');
	   deleteButton.addEventListener('click', () => {
	       newInput.remove(); // ลบ div ตัวเอง
	       newTableHeader.remove();
	       newInputs.remove(); // ลบ div ตัวเอง
	   });
	   
	   /*
       // เลือกองค์ประกอบ select
       const selectElement = newInput.querySelector('.tax-select');

       // สร้างองค์ประกอบ change-label
       const changeLabelElement = newInput.querySelector('.change-label');

       // เมื่อมีการเปลี่ยนแปลงใน select
       selectElement.addEventListener('change', (event) => {
         // ดึงค่า data จากตัวเลือกที่ถูกเลือก
         const selectedOption = selectElement.options[selectElement.selectedIndex];
         const dataValue = selectedOption.getAttribute('data');

         // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
         changeLabelElement.value = dataValue || 'Text';

         // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
         newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
         newTableHeader.textContent = changeLabelElement.value || 'Text';
         newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
       });      
       */
           
        
        
    }
     
    
    if (elementType === 'Number') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `

                <div class="mb-1 getported`+ids+`">
                    <label class="form-label" for="itemname">Number</label>
                    
                    
                    
                    <div class="dropdown pop" style="float:right;margin-right:20px;">
                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Setup
                    </i>
                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                            <div class="mb-1">
                                <label for="discount-input" class="form-label">label</label>
                                <input type="text" class="form-control change-label" value="Number" name="field_label"/>
                                	<input type="hidden"  value="number" name="field_type"/>
                            </div>
                            <div class="form-row mt-50" >
                                <div class="mb-1 col-md-12">
                                    <label for="tax-1-input" class="form-label">Main Field</label>
                                    <select  id="tax-1-input" class="form-select" name="field_mainfield">
                                    
                                    <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
            						  
            						  
                                    </select>
                                </div>
                            
                            </div>
                            
                            
                            <div class="form-row mt-50" style="display:none">
                            <div class="mb-1 col-md-12">
                            <label for="tax-1-input" class="form-label">Sub Field</label>
                                <select  id="tax-1-input" class="form-select" name="field_subfield">
                            
                                <option value="-" >-</option>
                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
            						  </c:forEach>
                                </select>
                            </div>
                        
                        </div>
                            
                            <div class="dropdown-divider my-1"></div>
                            <div class="d-flex justify-content-between">
                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
                            </div>
                        </div>
                    </div>
                    <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="-" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="-" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="-" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="-" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="-" class="form-control9"  >
					  <input type="hidden"  name="field_datareferid7" value="-" class="form-control11">
					  <input type="hidden"  name="field_datareferid15" value="-" class="form-control19">
					  <input type="hidden"  name="field_datareferid16" value="-" class="form-control20">
					  <input type="hidden"  name="field_datareferid8" value="-" class="form-control12"  >
					  <input type="hidden"  name="field_datareferid9" value="-" class="form-control13"  >
					  <input type="hidden"  name="field_datareferid10" value="-" class="form-control14"  >
					  <input type="hidden"  name="field_datareferid11" value="-" class="form-control15"  >
					  <input type="hidden"  name="field_datareferid12" value="-" class="form-control16"  >
					  <input type="hidden"  name="field_datareferid13" value="-" class="form-control17"  >
					  <input type="hidden"  name="field_datareferid14" value="-" class="form-control18"  >
					  <input type="hidden"  name="field_datareferid17" value="-" class="form-control21"  >
					  <input type="hidden"  name="field_datareferid18" value="-" class="form-control22"  >
					  <input type="hidden"  name="field_datareferid19" value="-"  class="form-control23"  >
					  <input type="hidden"  name="field_datareferid20"  value="-"  class="form-control24"  >
					  <input type="hidden"  name="field_dataport" value="-" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >
                    <input type="number" class="form-control mydata" name="condition_title" value=""/>
                </div>
           
        `;
    
        
  
        let newTableHeader = document.createElement('th');
		newTableHeader.textContent = 'Number';
		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
		newTableHeader.classList.add('fixhead'); 
		
		const theadElement = document.getElementById('tabledata');
		const tableRow = theadElement.querySelector('tr');
		
		tableRow.appendChild(newTableHeader);      
        
		const nodemain = document.querySelector('.node1');       
		ids++;      
		newInputs = document.createElement('div');
	    newInputs.className = 'agm';        
		newInputs.innerHTML = '<input id="1" value="Number" class="change-label2"  readonly><span id="ported'+ids+'"  class="port port3" ></span>';
		nodemain.appendChild(newInputs);     
        
		
        
        // ดึงข้อมูลเริ่มต้นของ label จาก input
   const initialLabelValue = newInput.querySelector('.change-label').value;
   const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
   // หากมีการเปลี่ยนแปลงค่าใน input change-label
   newInput.querySelector('.change-label').addEventListener('input', (event) => {
       // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
       newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
       newTableHeader.textContent = event.target.value || initialLabelValue;
       newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
   });

	   const deleteButton = newInput.querySelector('.delete-button');
	   deleteButton.addEventListener('click', () => {
	       newInput.remove(); // ลบ div ตัวเอง
	       newTableHeader.remove();
	       newInputs.remove(); // ลบ div ตัวเอง
	   });
	   
	   /*
       // เลือกองค์ประกอบ select
       const selectElement = newInput.querySelector('.tax-select');

       // สร้างองค์ประกอบ change-label
       const changeLabelElement = newInput.querySelector('.change-label');

       // เมื่อมีการเปลี่ยนแปลงใน select
       selectElement.addEventListener('change', (event) => {
         // ดึงค่า data จากตัวเลือกที่ถูกเลือก
         const selectedOption = selectElement.options[selectElement.selectedIndex];
         const dataValue = selectedOption.getAttribute('data');

         // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
         changeLabelElement.value = dataValue || 'Text';

         // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
         newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
         newTableHeader.textContent = changeLabelElement.value || 'Text';
         newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
       });      
       */
       
        
    }
    
    if (elementType === 'Date') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
                <div class="mb-1">
                    <label class="form-label" for="itemname">Date</label>
                    
                    
               
                   
                    <div class="dropdown pop" style="float:right;margin-right:20px;">
                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Setup
                    </i>
                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                            <div class="mb-1">
                                <label for="discount-input" class="form-label">label</label>
                          
                                	 <input type="text" class="form-control change-label" value="date" name="field_label"/>
                                	<input type="hidden"  value="date" name="field_type"/>
                            </div>
                            <div class="form-row mt-50" style="display:none">
                                <div class="mb-1 col-md-12">
                                    <label for="tax-1-input" class="form-label">MAIN Field</label>
                                    <select   class="form-select" name="field_mainfield">
                                    <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
                                    </select>
                                </div>
                            
                            </div>
                            
                            <div class="form-row mt-50" style="display:none">
                            <div class="mb-1 col-md-12">
                            <label for="tax-1-input" class="form-label">Sub Field</label>
                                <select  id="tax-1-input" class="form-select" name="field_subfield">
                            
                                <option value="-" >-</option>
                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
            						  </c:forEach>
                                </select>
                            </div>
                        
                        </div>
                            
                            
                            <div class="dropdown-divider my-1"></div>
                            <div class="d-flex justify-content-between">
                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
                            </div>
                        </div>
                    </div>
               
                    <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="-" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="-" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="-" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="-" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="-" class="form-control9"  >
					  <input type="hidden"  name="field_datareferid7" value="-" class="form-control11">
					  <input type="hidden"  name="field_datareferid15" value="-" class="form-control19">
					  <input type="hidden"  name="field_datareferid16" value="-" class="form-control20">
					  <input type="hidden"  name="field_datareferid8" value="-" class="form-control12"  >
					  <input type="hidden"  name="field_datareferid9" value="-" class="form-control13"  >
					  <input type="hidden"  name="field_datareferid10" value="-" class="form-control14"  >
					  <input type="hidden"  name="field_datareferid11" value="-" class="form-control15"  >
					  <input type="hidden"  name="field_datareferid12" value="-" class="form-control16"  >
					  <input type="hidden"  name="field_datareferid13" value="-" class="form-control17"  >
					  <input type="hidden"  name="field_datareferid14" value="-" class="form-control18"  >
					  <input type="hidden"  name="field_datareferid17" value="-" class="form-control21"  >
					  <input type="hidden"  name="field_datareferid18" value="-" class="form-control22"  >
					  <input type="hidden"  name="field_datareferid19" value="-"  class="form-control23"  >
					  <input type="hidden"  name="field_datareferid20"  value="-"  class="form-control24"  >
					  <input type="hidden"  name="field_dataport" value="-" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >
                    <input type="date" class="form-control mydata" name="condition_title" value=""/>
                </div>
                </div>
              
        `;
   
            let newTableHeader = document.createElement('th');
		newTableHeader.textContent = 'Date';
		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
		newTableHeader.classList.add('fixhead'); 
		
		const theadElement = document.getElementById('tabledata');
		const tableRow = theadElement.querySelector('tr');
		
		tableRow.appendChild(newTableHeader);      
        
		const nodemain = document.querySelector('.node1');       
		ids++;      
		newInputs = document.createElement('div');
	    newInputs.className = 'agm';        
		newInputs.innerHTML = '<input id="1" value="Date" class="change-label2"  readonly>';
		nodemain.appendChild(newInputs);   
        
        
         // ดึงข้อมูลเริ่มต้นของ label จาก input
    const initialLabelValue = newInput.querySelector('.change-label').value;
    const formLabel = newTableHeader.querySelector('.form-label'); 
    const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
    
    // หากมีการเปลี่ยนแปลงค่าใน input change-label
    newInput.querySelector('.change-label').addEventListener('input', (event) => {
        // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
        newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
        newTableHeader.textContent = event.target.value || initialLabelValue;
        newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
    });

	    const deleteButton = newInput.querySelector('.delete-button');
	    deleteButton.addEventListener('click', () => {
	        newInput.remove(); // ลบ div ตัวเอง
	        newTableHeader.remove();
	        newInputs.remove(); // ลบ div ตัวเอง
	    });
    
        
	    /*
        // เลือกองค์ประกอบ select
        const selectElement = newInput.querySelector('.tax-select');

        // สร้างองค์ประกอบ change-label
        const changeLabelElement = newInput.querySelector('.change-label');

        // เมื่อมีการเปลี่ยนแปลงใน select
        selectElement.addEventListener('change', (event) => {
          // ดึงค่า data จากตัวเลือกที่ถูกเลือก
          const selectedOption = selectElement.options[selectElement.selectedIndex];
          const dataValue = selectedOption.getAttribute('data');

          // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
          changeLabelElement.value = dataValue || 'Text';

          // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
          newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
          newTableHeader.textContent = changeLabelElement.value || 'Text';
          newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
        });      
        */
        

    }
     
    
    if (elementType === 'Select') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
                <div class="mb-1 Selectdata getported`+ids+`">
                    <label class="form-label" for="itemname">Select</label>
                    
                    
               
                   
                    <div class="dropdown pop" style="float:right;margin-right:20px;">
                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Setup
                    </i>
                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                            <div class="mb-1">
                                <label for="discount-input" class="form-label">label</label>
                                <input type="text" class="form-control change-label" value="select"  name="field_label"/>
                                	<input type="hidden"  value="select" name="field_type"/>
                            </div>
                            <div class="form-row mt-50" style="display:none">
                                <div class="mb-1 col-md-12">
                                    <label for="tax-1-input" class="form-label">MAIN Field</label>
                                    <select  id="tax-1-input" class="form-select" name="field_mainfield">
                                    <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
                                    </select>
                                </div>
                            
                            </div>
                            
                            
                            <div class="form-row mt-50" style="display:none">
                            <div class="mb-1 col-md-12">
                            <label for="tax-1-input" class="form-label">Sub Field</label>
                                <select  id="tax-1-input" class="form-select" name="field_subfield">
                            
                                <option value="-" >-</option>
                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
            						  </c:forEach>
                                </select>
                            </div>
                        
                        </div>
                            
                            <div class="dropdown-divider my-1" ></div>
                            <div class="d-flex justify-content-between">
                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
                            </div>
                        </div>
                    </div>
               
                    <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
					  <input type="hidden"  name="field_datareferid2" value="-" class="form-control5"  >
					  <input type="hidden"  name="field_datareferid3" value="-" class="form-control6"  >
					  <input type="hidden"  name="field_datareferid4" value="-" class="form-control7"  >
					  <input type="hidden"  name="field_datareferid5" value="-" class="form-control8"  >
					  <input type="hidden"  name="field_datareferid6" value="-" class="form-control9"  >
					  <input type="hidden"  name="field_datareferid7" value="-" class="form-control11">
					  <input type="hidden"  name="field_datareferid15" value="-" class="form-control19">
					  <input type="hidden"  name="field_datareferid16" value="-" class="form-control20">
					  <input type="hidden"  name="field_datareferid8" value="-" class="form-control12"  >
					  <input type="hidden"  name="field_datareferid9" value="-" class="form-control13"  >
					  <input type="hidden"  name="field_datareferid10" value="-" class="form-control14"  >
					  <input type="hidden"  name="field_datareferid11" value="-" class="form-control15"  >
					  <input type="hidden"  name="field_datareferid12" value="-" class="form-control16"  >
					  <input type="hidden"  name="field_datareferid13" value="-" class="form-control17"  >
					  <input type="hidden"  name="field_datareferid14" value="-" class="form-control18"  >
					  <input type="hidden"  name="field_datareferid17" value="-" class="form-control21"  >
					  <input type="hidden"  name="field_datareferid18" value="-" class="form-control22"  >
					  <input type="hidden"  name="field_datareferid19" value="-"  class="form-control23"  >
					  <input type="hidden"  name="field_datareferid20"  value="-"  class="form-control24"  >
					  <input type="hidden"  name="field_dataport" value="-" class="form-control10"  >
					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >
                    <select class="form-select mydata"/></select>
                </div>
                </div>
              
        `;
   
            let newTableHeader = document.createElement('th');
		newTableHeader.textContent = 'Select';
		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
		newTableHeader.classList.add('fixhead'); 
		
		const theadElement = document.getElementById('tabledata');
		const tableRow = theadElement.querySelector('tr');
		
		tableRow.appendChild(newTableHeader);      
        
		const nodemain = document.querySelector('.node1');       
		ids++;      
		newInputs = document.createElement('div');
	    newInputs.className = 'agm';        
		newInputs.innerHTML = '<input id="1" value="Select" class="change-label2"  readonly><span id="'+ids+'"  class="port port1" ></span>';
		nodemain.appendChild(newInputs); 
        
        
         // ดึงข้อมูลเริ่มต้นของ label จาก input
    const initialLabelValue = newInput.querySelector('.change-label').value;
    const formLabel = newTableHeader.querySelector('.form-label'); 
    const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
    // หากมีการเปลี่ยนแปลงค่าใน input change-label
    newInput.querySelector('.change-label').addEventListener('input', (event) => {
        // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
        newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
        newTableHeader.textContent = event.target.value || initialLabelValue;
        newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
    });

	    const deleteButton = newInput.querySelector('.delete-button');
	    deleteButton.addEventListener('click', () => {
	        newInput.remove(); // ลบ div ตัวเอง
	        newInputs.remove(); // ลบ div ตัวเอง
	        newTableHeader.remove();
	    });
    
        
	    /*
        // เลือกองค์ประกอบ select
        const selectElement = newInput.querySelector('.tax-select');

        // สร้างองค์ประกอบ change-label
        const changeLabelElement = newInput.querySelector('.change-label');

        // เมื่อมีการเปลี่ยนแปลงใน select
        selectElement.addEventListener('change', (event) => {
          // ดึงค่า data จากตัวเลือกที่ถูกเลือก
          const selectedOption = selectElement.options[selectElement.selectedIndex];
          const dataValue = selectedOption.getAttribute('data');

          // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
          changeLabelElement.value = dataValue || 'Text';

          // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
          newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
          newTableHeader.textContent = changeLabelElement.value || 'Text';
          newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
        });      
        */
        

    }
    
    
    if (elementType === 'Checkbox') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
                <div class="Selectdata mb-1">
                    <label class="form-label" for="itemname">Checkbox
                    </label>
                    
                    
               
                   
                    <div class="dropdown pop" style="float:right;margin-right:20px;">
                    <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Setup
                    </i>
                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                            <div class="mb-1">
                                <label for="discount-input" class="form-label">label</label>
                                <input type="text" class="form-control change-label" value="checkbox"  name="field_label"/>
                                	<input type="hidden"  value="checkbox" name="field_type"/>
                            </div>
                            <div class="form-row mt-50" style="display:none">
                                <div class="mb-1 col-md-12">
                                    <label for="tax-1-input" class="form-label">MAIN Field</label>
                                    <select  id="tax-1-input" class="form-select" name="field_mainfield">
                                    <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
	            						  </c:forEach>
                                    </select>
                                </div>
                            
                            </div>
                            
                            <div class="form-row mt-50" style="display:none">
                            <div class="mb-1 col-md-12">
                            <label for="tax-1-input" class="form-label">Sub Field</label>
                                <select  id="tax-1-input" class="form-select" name="field_subfield">
                            
                                <option value="-" >-</option>
                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
                                    <option value="${row.name()}">${row.name()}  [${row.label} ]</option>
            						  </c:forEach>
                                </select>
                            </div>
                        
                        </div>
                            
                            
                            <div class="dropdown-divider my-1"></div>
                            <div class="d-flex justify-content-between">
                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
                            </div>
                        </div>
                    </div>
               
            <br>   
            
            <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
			  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
			  <input type="hidden"  name="field_datareferid2" value="-" class="form-control5"  >
			  <input type="hidden"  name="field_datareferid3" value="-" class="form-control6"  >
			  <input type="hidden"  name="field_datareferid4" value="-" class="form-control7"  >
			  <input type="hidden"  name="field_datareferid5" value="-" class="form-control8"  >
			  <input type="hidden"  name="field_datareferid6" value="-" class="form-control9"  >
			  <input type="hidden"  name="field_datareferid7" value="-" class="form-control11">
			  <input type="hidden"  name="field_datareferid15" value="-" class="form-control19">
			  <input type="hidden"  name="field_datareferid16" value="-" class="form-control20">
			  <input type="hidden"  name="field_datareferid8" value="-" class="form-control12"  >
			  <input type="hidden"  name="field_datareferid9" value="-" class="form-control13"  >
			  <input type="hidden"  name="field_datareferid10" value="-" class="form-control14"  >
			  <input type="hidden"  name="field_datareferid11" value="-" class="form-control15"  >
			  <input type="hidden"  name="field_datareferid12" value="-" class="form-control16"  >
			  <input type="hidden"  name="field_datareferid13" value="-" class="form-control17"  >
			  <input type="hidden"  name="field_datareferid14" value="-" class="form-control18"  >
			  <input type="hidden"  name="field_datareferid17" value="-" class="form-control21"  >
			  <input type="hidden"  name="field_datareferid18" value="-" class="form-control22"  >
			  	  <input type="hidden"  name="field_datareferid19" value="-"  class="form-control23"  >
						  <input type="hidden"  name="field_datareferid20"  value="-"  class="form-control24"  >
			  <input type="hidden"  name="field_dataport" value="-" class="form-control10"  >
			   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >
                    <span class="checkbox"><input type="checkbox" class="mydata"/> Checkbox</span>
                </div>
                </div>
              
        `;
   
            let newTableHeader = document.createElement('th');
		newTableHeader.textContent = 'Checkbox';
		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
		newTableHeader.classList.add('fixhead'); 
		
		const theadElement = document.getElementById('tabledata');
		const tableRow = theadElement.querySelector('tr');
	
		tableRow.appendChild(newTableHeader);      
        
		const nodemain = document.querySelector('.node1');       
		ids++;      
		newInputs = document.createElement('div');
	    newInputs.className = 'agm';        
		newInputs.innerHTML = '<input id="1" value="Checkbox" class="change-label2"  readonly><span id="'+ids+'"  class="port port1" ></span>';
		nodemain.appendChild(newInputs);  
        
        
         // ดึงข้อมูลเริ่มต้นของ label จาก input
    const initialLabelValue = newInput.querySelector('.change-label').value;
    const formLabel = newTableHeader.querySelector('.form-label'); 
    const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
    // หากมีการเปลี่ยนแปลงค่าใน input change-label
    newInput.querySelector('.change-label').addEventListener('input', (event) => {
        // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
        newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
        newTableHeader.textContent = event.target.value || initialLabelValue;
        newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
    });

	    const deleteButton = newInput.querySelector('.delete-button');
	    deleteButton.addEventListener('click', () => {
	        newInput.remove(); // ลบ div ตัวเอง
	        newInputs.remove(); // ลบ div ตัวเอง
	        newTableHeader.remove();
	    });
    
        
	    /*
        // เลือกองค์ประกอบ select
        const selectElement = newInput.querySelector('.tax-select');

        // สร้างองค์ประกอบ change-label
        const changeLabelElement = newInput.querySelector('.change-label');

        // เมื่อมีการเปลี่ยนแปลงใน select
        selectElement.addEventListener('change', (event) => {
          // ดึงค่า data จากตัวเลือกที่ถูกเลือก
          const selectedOption = selectElement.options[selectElement.selectedIndex];
          const dataValue = selectedOption.getAttribute('data');

          // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
          changeLabelElement.value = dataValue || 'Text';

          // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
          newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
          newTableHeader.textContent = changeLabelElement.value || 'Text';
          newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
        });      
        */
        
        

    }
    
    
    
    
    
    if (elementType === 'Layout select : select') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
        	  <div class="row">
            <div class="mb-1 col-md-6 sub" >
                <label class="form-label" for="vertical-username">
                select
                </label>
                <select class="form-select" name="condition_typeunit3">
                <option value="Text">Text</option>
                <option value="Number">Number</option>
                 <option value="S1">ชุดข้อมูล : รายชื่อพนักงาน</option>

          
            <option value="1" selected="">ชุดข้อมูล : ผลการตรวจ</option>
                   
               </select>
            </div>
            <div class="mb-1 col-md-6 sub" >
                <label class="form-label" for="vertical-email">
                select
                </label>

                
                <div class="dropdown pop" style="float:right;margin-right:20px;">
                <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Setup
                </i>
                    <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                        <div class="mb-1">
                            <label for="discount-input" class="form-label">label</label>
                            <input type="text" class="form-control change-label" value="date" name="field_label"/>
                            	<input type="hidden"  value="date" name="field_type"/>
                        </div>
                        <div class="form-row mt-50" style="display:none">
                            <div class="mb-1 col-md-6">
                                <label for="tax-1-input" class="form-label">ขนาด</label>
                              
                            </div>
                        
                        </div>
                        <div class="dropdown-divider my-1"></div>
                        <div class="d-flex justify-content-between">
                            <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                            <button type="button" class="btn btn-outline-secondary">ปิด</button>
                        </div>
                    </div>
                </div>
           
                <select class="form-select" name="condition_typeunit3">
                <option value="Text">Text</option>
                <option value="Number">Number</option>
                 <option value="S1">ชุดข้อมูล : รายชื่อพนักงาน</option>

          
            <option value="1" selected="">ชุดข้อมูล : ผลการตรวจ</option>
                   
               </select>
            </div>
        </div>
            
        `;
        
        
        const initialLabelValue = newInput.querySelector('.change-label').value;

        // หากมีการเปลี่ยนแปลงค่าใน input change-label
        newInput.querySelector('.change-label').addEventListener('input', (event) => {
            // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
            newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
        });

            const deleteButton = newInput.querySelector('.delete-button');
            deleteButton.addEventListener('click', () => {
                newInput.remove(); // ลบ div ตัวเอง
            });
             
             
        
        
        
        
    }
    
    if (elementType === 'Title') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
        	 <div class="form-title" style="background-color: #d9d8f2;padding: 10px;margin-bottom:5px;">
            <h4 class="form-title-text">Your Title </h4>
            

            <div class="dropdown pop" style="float:right;margin-right:20px;top:-45">
            <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Setup
            </i>
                <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                    <div class="mb-1">
                        <label for="discount-input" class="form-label">label</label>
                        <input type="text" class="form-control change-label" value="Your Title" name="field_label"/>
                        	<input type="hidden"  value="Title" name="field_type"/>
                        	<input type="hidden"  value="-" name="field_mainfield"/>
                        		<input type="hidden"  value="-" name="field_subfield"/>
                        		
                        		
                        		   <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
      					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
      					  <input type="hidden"  name="field_datareferid2" value="-" class="form-control5"  >
      					  <input type="hidden"  name="field_datareferid3" value="-" class="form-control6"  >
      					  <input type="hidden"  name="field_datareferid4" value="-" class="form-control7"  >
      					  <input type="hidden"  name="field_datareferid5" value="-" class="form-control8"  >
      					  <input type="hidden"  name="field_datareferid6" value="-" class="form-control9"  >
      					  <input type="hidden"  name="field_datareferid7" value="-" class="form-control11">
      					  <input type="hidden"  name="field_datareferid15" value="-" class="form-control19">
      					  <input type="hidden"  name="field_datareferid16" value="-" class="form-control20">
      					  <input type="hidden"  name="field_datareferid8" value="-" class="form-control12"  >
      					  <input type="hidden"  name="field_datareferid9" value="-" class="form-control13"  >
      					  <input type="hidden"  name="field_datareferid10" value="-" class="form-control14"  >
      					  <input type="hidden"  name="field_datareferid11" value="-" class="form-control15"  >
      					  <input type="hidden"  name="field_datareferid12" value="-" class="form-control16"  >
      					  <input type="hidden"  name="field_datareferid13" value="-" class="form-control17"  >
      					  <input type="hidden"  name="field_datareferid14" value="-" class="form-control18"  >
      					  <input type="hidden"  name="field_datareferid17" value="-" class="form-control21"  >
      					  <input type="hidden"  name="field_datareferid18" value="-" class="form-control22"  >
      					  <input type="hidden"  name="field_datareferid19" value="-"  class="form-control23"  >
						  <input type="hidden"  name="field_datareferid20"  value="-"  class="form-control24"  >
      					  <input type="hidden"  name="field_dataport" value="-" class="form-control10"  >
      					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >
                        	
                    </div>
                    <div class="form-row mt-50" style="display:none">
                        <div class="mb-1 col-md-6">
                            <label for="tax-1-input" class="form-label">ขนาด</label>
                            <select name="tax-1-input" id="tax-1-input" class="form-select">
                                <option value="0%" selected>0%</option>
                                <option value="1%">1%</option>
                                <option value="10%">10%</option>
                                <option value="18%">18%</option>
                                <option value="40%">40%</option>
                            </select>
                        </div>
                    
                    </div>
                    <div class="dropdown-divider my-1"></div>
                    <div class="d-flex justify-content-between">
                        <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                        <button type="button" class="btn btn-outline-secondary">ปิด</button>
                    </div>
                </div>
            </div>
        </div>
        
       
        </div>
            
        `;
        
        
        // ดึงข้อมูลเริ่มต้นของ label จาก input
        const initialLabelValue = newInput.querySelector('.change-label').value;

        // หากมีการเปลี่ยนแปลงค่าใน input change-label
        newInput.querySelector('.change-label').addEventListener('input', (event) => {
            // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
            newInput.querySelector('.form-title-text').textContent = event.target.value || initialLabelValue;
        });

            const deleteButton = newInput.querySelector('.delete-button');
            deleteButton.addEventListener('click', () => {
                newInput.remove(); // ลบ div ตัวเอง
            });
    } 
    
    if (elementType === 'Sub Title') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
        	 <div class="form-title" style="padding: 10px;margin-bottom:5px;text-align: center;">
            <h6 class="form-title-text">Sub Title</h6>
            

            <div class="dropdown pop" style="float:right;margin-right:20px;top:-45">
            <i class="cursor-pointer more-options-dropdown me-0" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Setup
            </i>
                <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                    <div class="mb-1">
                        <label for="discount-input" class="form-label">label</label>
                        <input type="text" class="form-control change-label" value="Your Title" name="field_label"/>
                        	<input type="hidden"  value="SubTitle" name="field_type"/>
                             	<input type="hidden"  value="0" name="field_mainfield"/>
                             		<input type="hidden"  value="-" name="field_subfield"/>
                             	   <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
          					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
          					  <input type="hidden"  name="field_datareferid2" value="-" class="form-control5"  >
          					  <input type="hidden"  name="field_datareferid3" value="-" class="form-control6"  >
          					  <input type="hidden"  name="field_datareferid4" value="-" class="form-control7"  >
          					  <input type="hidden"  name="field_datareferid5" value="-" class="form-control8"  >
          					  <input type="hidden"  name="field_datareferid6" value="-" class="form-control9"  >
          					  <input type="hidden"  name="field_datareferid7" value="-" class="form-control11">
          					  <input type="hidden"  name="field_datareferid15" value="-" class="form-control19">
          					  <input type="hidden"  name="field_datareferid16" value="-" class="form-control20">
          					  <input type="hidden"  name="field_datareferid8" value="-" class="form-control12"  >
          					  <input type="hidden"  name="field_datareferid9" value="-" class="form-control13"  >
          					  <input type="hidden"  name="field_datareferid10" value="-" class="form-control14"  >
          					  <input type="hidden"  name="field_datareferid11" value="-" class="form-control15"  >
          					  <input type="hidden"  name="field_datareferid12" value="-" class="form-control16"  >
          					  <input type="hidden"  name="field_datareferid13" value="-" class="form-control17"  >
          					  <input type="hidden"  name="field_datareferid14" value="-" class="form-control18"  >
          					  <input type="hidden"  name="field_datareferid17" value="-" class="form-control21"  >
          					  <input type="hidden"  name="field_datareferid18" value="-" class="form-control22"  >
          					  <input type="hidden"  name="field_datareferid19" value="-"  class="form-control23"  >
    						  <input type="hidden"  name="field_datareferid20"  value="-"  class="form-control24"  >
          					  <input type="hidden"  name="field_dataport" value="-" class="form-control10"  >
          					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >
                    </div>
                    <div class="form-row mt-50" style="display:none">
                        <div class="mb-1 col-md-6">
                            <label for="tax-1-input" class="form-label">ขนาด</label>
                            <select name="tax-1-input" id="tax-1-input" class="form-select">
                                <option value="0%" selected>0%</option>
                                <option value="1%">1%</option>
                                <option value="10%">10%</option>
                                <option value="18%">18%</option>
                                <option value="40%">40%</option>
                            </select>
                        </div>
                    
                    </div>
                    <div class="dropdown-divider my-1"></div>
                    <div class="d-flex justify-content-between">
                        <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                        <button type="button" class="btn btn-outline-secondary">ปิด</button>
                    </div>
                </div>
            </div>
        </div>
        
       
        </div>
            
        `;
        
        
        // ดึงข้อมูลเริ่มต้นของ label จาก input
        const initialLabelValue = newInput.querySelector('.change-label').value;

        // หากมีการเปลี่ยนแปลงค่าใน input change-label
        newInput.querySelector('.change-label').addEventListener('input', (event) => {
            // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
            newInput.querySelector('.form-title-text').textContent = event.target.value || initialLabelValue;
        });

            const deleteButton = newInput.querySelector('.delete-button');
            deleteButton.addEventListener('click', () => {
                newInput.remove(); // ลบ div ตัวเอง
            });
    } 
    
    // เพิ่มองค์ประกอบ Input ลงใน form-preview
    formPreview.appendChild(newInput);
    
    // เพิ่มการลากและวางให้กับองค์ประกอบใหม่
    makeElementDraggable(newInput);
    
    // เพิ่มการสลับตำแหน่งได้
    makeElementSortable();
});

function makeElementDraggable(element) {

    element.draggable = true;
    
    element.addEventListener('dragstart', (e) => {
        isDragging = true;
        dragElement = element;
        offsetX = e.clientX - element.getBoundingClientRect().left;
        offsetY = e.clientY - element.getBoundingClientRect().top;
    });
    
    element.addEventListener('dragend', () => {
        isDragging = false;
        dragElement = null;
    });
}

function makeElementSortable() {
    const draggableRows = document.querySelectorAll('.draggable-row');
    
    draggableRows.forEach((element) => {
    	
        element.addEventListener('dragover', (e) => {
            e.preventDefault();
            const targetElement = e.target.closest('.draggable-row');
            if (!targetElement || targetElement === dragElement) return;
            const rect = targetElement.getBoundingClientRect();
            const isBelow = e.clientY > rect.top + rect.height / 2;
            
            // ตรวจสอบว่าตำแหน่งเป้าหมายของการวางอยู่ภายในองค์ประกอบอื่น
            if (isBelow) {
                // เพิ่มองค์ประกอบลงในองค์ประกอบเป้าหมาย
                
  
                targetElement.appendChild(dragElement);
            } else {
                // เพิ่มองค์ประกอบลงหน้าองค์ประกอบเป้าหมาย
                

                formPreview.insertBefore(dragElement, targetElement);
                
                
         
                
            }
        });
        element.addEventListener('dragstart', (e) => {
        	  const targetElement = e.target.closest('.draggable-row');
            e.dataTransfer.setData('text/plain2', element.textContent);
        });
      
        
        // เพิ่ม event listener สำหรับการวางลงใน <div class="mb-1 col-md-6 sub">
        element.addEventListener('drop', (e) => {
        	
            e.preventDefault();
            const elementType = e.dataTransfer.getData('text/plain2');

            //alert(targetElementID);
            // ตรวจสอบว่าลากและวางลงใน <div class="mb-1 col-md-6 sub">
            if (elementType.trim() === 'Number') {
                const subElement = element.querySelector('.mb-1.col-md-6.sub');
                subElement.innerHTML ="";
                newInput = document.createElement('div');
                newInput.className = 'row draggable-row';
                newInput.innerHTML = `
                    <div class="col-md-12 col-12">
                        <div class="mb-1">
                            <label class="form-label" for="itemname">Number</label>
                            <input type="number" class="form-control" name="condition_title" value=""/>
                        </div>
                    </div>
                `;
                
                subElement.appendChild(newInput);
                
                e.stopPropagation();
            }
        });
        
    });
    
    

  

    
    
    
}






function savetest() {
	
	
	// เลือกตารางโดยใช้ id
	var table = document.getElementById("tabledata");

	// เข้าถึง tbody ในตาราง
	var tbody = table.getElementsByTagName("tbody")[0];
	var row = tbody.insertRow();

    var divsWithChangeLabel = document.querySelectorAll('.mb-1');

    // ใช้ forEach() เพื่อลูปข้อมูลจากทุก <div> ที่มีคลาส change-label
    divsWithChangeLabel.forEach(function (divElement) {
        // ในแต่ละการลูป, เลือก <input> ที่มีคลาส mydata
        var inputElement = divElement.querySelector('input.mydata,textarea.mydata,select.mydata');
        var labelHTML = divElement.querySelector('.form-label');
        
        var i = 0;
        // ตรวจสอบว่า <input> ที่เลือกมีค่าหรือไม่
        if (inputElement) {
        	
        	//alert(labelHTML.innerHTML);
     	
            // ดึงค่าที่อยู่ใน <input>
            var value = inputElement.value;
 
            	var cell = row.insertCell();
            	cell.textContent = value;

       
            console.log(value);
            
            
      
            
        }
    });

	
	
	

	// สร้างแถวใหม่
	



}





    </script>
    

    <script>
function openCenteredWindow(url, name, width, height) {
	
	

    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>
         
        
  