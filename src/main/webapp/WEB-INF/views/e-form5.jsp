<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <% pageContext.setAttribute("newLineChar", "\n"); %>


<%@ include file="common/mainhead.jspf" %>    
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">


  <link rel='stylesheet' href='http://sebastien.drouyer.com/jquery.flowchart-demo/jquery.flowchart/jquery.flowchart.min.css'>
<link rel='stylesheet' href='https://code.jquery.com/ui/1.12.1/themes/vader/jquery-ui.css'>

  <style>
  #chart {
  height: 300px;
}
  
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
    




.node {
    position: relative;
    width: 200;
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

.fixhead2{
 background-color: #F0F8FF;
}


 </style>
 
<title>E-form</title>
</head>
   
<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static" 
data-open="click" data-menu="vertical-menu-modern" data-col="" style="padding:0px;margin:0px;">


    <!-- BEGIN: Content-->
    <div class="app-content content"  style="padding:5px;margin:5px;">

        <div class="content-wrapper container-xxl p-0">
     
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
                
                

 
       <h1> ${title }</h1>
        
          <div class="card-body" style="margin-top:0px;padding:0px;">
                                    <ul class="nav nav-pills">
                                        <li class="nav-item" style="display:none">
                                            <a class="nav-link " id="home-tab" data-bs-toggle="pill" href="#home" aria-expanded="true" onclick="run2()">
                                         <spring:message code="forms.setting"  />
                                            </a>
                                        </li>
                                          <li class="nav-item">
                                            <a class="nav-link active" id="about-tab2" data-bs-toggle="pill" href="#dropdown2" aria-expanded="false" onclick="run()">
                                             <spring:message code="common.Relationview"/>
                                
                                            </a>
                                        </li>
                                        <li class="nav-item" >
                                            <a class="nav-link " id="about-tab" data-bs-toggle="pill" href="#dropdown1" aria-expanded="false" onclick="run2()">
                                            
                                            <spring:message code="forms.calculation"  />
                                            </a>
                                        </li>
                                     
                                    </ul>
                                    <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane " id="home" aria-labelledby="home-tab" aria-expanded="true">

                        
   <div class="form-preview " droppable="true" id="editerDiv">
            <!-- ตำแหน่งที่สามารถวางองค์ประกอบของฟอร์ม -->

        </div>

        <br>  <br>
                   <button type="button" class="btn btn-outline-secondary" onclick="savetest()"">Test</button>      
                     
            
                                        </div>
                                        <div class="tab-pane" id="dropdown1" role="tabpanel" aria-labelledby="dropdown1-tab" aria-expanded="false">
                                    
                              <div class="form-preview" >
                  <form:form method="POST" action="/editgroup-condition"  id="cmform" modelAttribute="report"   name="cmform">  
  <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">                                              
<input type="hidden" id="id1" name="id1" value='${condition.condition_id}'>
<input type="hidden" id="id2" name="id2" value='${port1}'>  
<input type="hidden" id="id3" name="id3" value='${port2}'>       
<input type="hidden" id="idrefer" name="idrefer" value='${upload_pns.upload_pn_id}'>       
                     Modular Name : <input type="text" name="condition_group" value="${condition.condition_group }" >    <button  class="btn btn-sm btn-success notprint" id="Save" >Save</button>
                     </form:form>
                     
                     
      <br>  <br>
                     <div class="node-editor" id="node-editor">
                     
        <b>  1.${condition.condition_name} [DATA]<br></b>            
      <c:forEach items="${eformfields }" var="row" varStatus="count">
		   -${row.field_label} 
		   
		   <br>
		</c:forEach>          
                 
                 
                 
        <c:if test="${condition2 != null}">   
           <br>  <br>

          <b>  2.${condition2.condition_name} [Transaction]<br></b>            
      <c:forEach items="${eformfields2s }" var="row" varStatus="count">
		   -${row.field_label} [${row.field_cal_type}] 
		   
		   <br>
		</c:forEach> 
     </c:if>
     
     
     
         <c:if test="${condition3 != null}">   
         <br>  <br>
         
          <b>  3.${condition3.condition_name} [Transaction]<br></b>            
      <c:forEach items="${eformfields3s }" var="row" varStatus="count">
		   -${row.field_label}  [${row.field_cal_type}] 
		   
		   <br>
		</c:forEach> 
          </c:if>	
     

     
           <br>  <br>   
      
</div>
                     
                             <div >
                                     
                                     <br><br><br>
                            
                                     <hr>
             
          <c:if test="${conditionssub.condition_id > 0}">   
                 EForm 1 : ${conditionssub.condition_name  }
                   <c:set var="list1" value="${conditionssub.condition_titlerefer}" />
  
                    
 
                 
                                      <table class="tabledata" style="height:auto">
                                    <thead>
                                        <tr>
                                        
                               <c:forEach items="${eformfields }" var="row" varStatus="count">  
                                   <c:set var="listcheck1" value="${count.count }" />
                               
                                  <th class="form-label fixhead fixhead2" ><input type="checkbox" name="refer1" value="${count.count }"
                                 <c:if test='${fn:contains(list1,listcheck1) ||  conditionssub.condition_titlerefer  == null }'>checked</c:if>
                                  > ${row.field_label }</th> 
                              </c:forEach>
                              <c:forEach items="${eformfields2 }" var="row" varStatus="count">     
                                        <th class="form-label fixhead">${row.field_label }</th>
                               </c:forEach> 
                               </tr>
                                 </thead>
                 </table>
                  <hr>
         </c:if>        
         <c:if test="${conditionssub2.condition_id > 0}">  
                 EForm 2 : ${conditionssub2.condition_name  }
                  <c:set var="list2" value="${conditionssub2.condition_titlerefer}" />
             
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
                  EForm 3 : ${conditionssub3.condition_name }
                     <c:set var="list3" value="${conditionssub3.condition_titlerefer}" />

                       
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
                                 
                                 
                                  </div>
                                       </div>    
                                        </div>
                                     
                                                <div class="tab-pane active" id="dropdown2" role="tabpanel" aria-labelledby="dropdown1-tab" aria-expanded="false">
                                    
                              <div class="form-preview2 " >
   
   
   
               <c:if test="${condition.join_type == 1}"> 
         <div id="chart"></div>
             </c:if>   
             
             
             
              <c:if test="${condition.join_type == 0}">       

<div class="node-editor" id="node-editor">

** <spring:message code="forms.info2"  />
<br><br>
                    <div class="row">
                      
                   
				                               
		<div class="col-2">


  <div class="node node3" id="node3">
   <div class="agm" style="padding-top:10px">
   Other Record
   <hr>
       <c:if test="${conditionold2.condition_id > 0}"> 
       [ ${conditionold2.condition_title } ]
   <div class="agm">
   <input id="1" value="${conditionold2.condition_name } " class="change-label2" readonly="">
   <span class="port " id="portedmyid${conditionold2.condition_id }"></span></div>
   </c:if>
   
     </div>


 </div>
  
  
 </div> 			                                 
		<div class="col-2">


  <div class="node node2" id="node2">
   <div class="agm" style="padding-top:10px">
   Other Record
   <hr>
     <c:if test="${conditionold.condition_id > 0}"> 
     [ ${conditionold.condition_title } ]
   <div class="agm"><span  class="port2 portedmyid${conditionold2.condition_id }"></span>
   <input id="1" value="${conditionold.condition_name } " class="change-label2" readonly="">
   <span id="portedmyid${conditionold.condition_id }" class="port "></span></div>
   </c:if>
     </div>


 </div>
  
  
 </div> 			      
	<div class="col-2">


  <div class="node" id="node1" style="background-color:#F0F8FF">
   <div class="agm" style="padding-top:10px">
   This Record
   <hr>
   [ ${condition.condition_title } ]
   <div class="agm"><span  class="port2 portedmyid${conditionold.condition_id }"></span>
   <input id="1" value="${upload_pns.upload_pn_respon }-${upload_pns.upload_pn_note}" class="change-label2" readonly="">
   <span id="portedmyid${condition.condition_id }" class="port port1"></span></div>

   
   
   
     </div>


 </div>
  
  
 </div> 
    <div class="col-2" >
    
     <div class="node" id="node2">
      <div class="agm style="padding-top:10px">
 Other Record
   <hr>
   
     <c:if test="${conditionssub.condition_id > 0}"> 
         [ ${conditionssub.condition_title } ]
   <div class="agm"><span  class="port2 portedmyid${condition.condition_id }"></span>
   <input id="1" value="${upload_pnssub.upload_pn_respon }-${upload_pnssub.upload_pn_note}" class="change-label2" readonly="">
   <span id="portedmyid${conditionssub.condition_id }" class="port port1"></span></div>
   </c:if>
   
   
     <c:if test="${conditionssub2.condition_id > 0}"> 
         [ ${conditionssub2.condition_title } ]
   <div class="agm"><span  class="port2 portedmyid${condition.condition_id }"></span>
   <input id="1" value="${upload_pnssub2.upload_pn_respon }-${upload_pnssub2.upload_pn_note}" class="change-label2" readonly="">
   <span id="portedmyid${conditionssub2.condition_id }" class="port port1"></span></div>
    </c:if>
    
         <c:if test="${conditionssub3.condition_id > 0}"> 
             [ ${conditionssub3.condition_title } ]
     <div class="agm"><span  class="port2 portedmyid${condition.condition_id }"></span>
   <input id="1" value="${upload_pnssub3.upload_pn_respon }-${upload_pnssub3.upload_pn_note}" class="change-label2" readonly="">
   <span id="portedmyid${conditionssub3.condition_id }" class="port port1"></span></div>
 
 </c:if>
 
  </div>
   </div>
     </div>
  
  
           <div class="col-2">
    
     <div class="node" id="node2" >
      <div class="agm style="padding-top:10px">

Other Record

   <hr>
    
  
     <c:if test="${conditionssubs.condition_id > 0}"> 
       [ ${conditionssubs.condition_title } ]
   <div class="agm"><span  class="port2 portedmyid${conditionssub.condition_id }"></span>
   <input id="1" value="${upload_other1.upload_pn_respon }-${upload_other1.upload_pn_note}" class="change-label2" readonly="">
  </div>
   </c:if>
   
   
     <c:if test="${conditionssub2s.condition_id > 0}"> 
       [ ${conditionssub2s.condition_title } ]
   <div class="agm"><span  class="port2 portedmyid${conditionssub.condition_id }"></span>
   <input id="1" value="${upload_other1s.upload_pn_respon }-${upload_other1s.upload_pn_note}" class="change-label2" readonly="">
</div>
    </c:if>
    
         <c:if test="${conditionssub3s.condition_id > 0}"> 
           [ ${conditionssub3s.condition_title } ]
     <div class="agm"><span  class="port2 portedmyid${conditionssub.condition_id }"></span>
   <input id="1" value="${upload_other1ss.upload_pn_respon }-${upload_other1ss.upload_pn_note}" class="change-label2" readonly="">
  </div>
 
 </c:if>
 
     
     
     
          <c:if test="${conditionssubss.condition_id > 0}">
            [ ${conditionssubss.condition_title } ] 
   <div class="agm"><span  class="port2 portedmyid${conditionssub2.condition_id }"></span>
   <input id="1" value="${upload_other2.upload_pn_respon }-${upload_other2.upload_pn_note}" class="change-label2" readonly="">
  </div>
   </c:if>
   
   
     <c:if test="${conditionssub2ss.condition_id > 0}"> 
       [ ${conditionssub2ss.condition_title } ]
   <div class="agm"><span  class="port2 portedmyid${conditionssub2.condition_id }"></span>
   <input id="1" value="${upload_other2s.upload_pn_respon }-${upload_other2s.upload_pn_note}" class="change-label2" readonly="">
</div>
    </c:if>
    
         <c:if test="${conditionssub3ss.condition_id > 0}"> 
           [ ${conditionssub3ss.condition_title } ]
     <div class="agm"><span  class="port2 portedmyid${conditionssub2.condition_id }"></span>
   <input id="1" value="${upload_other2ss.upload_pn_respon }-${upload_other2ss.upload_pn_note}" class="change-label2" readonly="">
  </div>
 
 </c:if>
 
 
 
      <c:if test="${conditionssubsss.condition_id > 0}"> 
        [ ${conditionssubsss.condition_title } ]
   <div class="agm"><span  class="port2 portedmyid${conditionssub3.condition_id }"></span>
   <input id="1" value="${upload_other3.upload_pn_respon }-${upload_other3.upload_pn_note}" class="change-label2" readonly="">
  </div>
   </c:if>
   
   
     <c:if test="${conditionssub2sss.condition_id > 0}"> 
       [ ${conditionssub2sss.condition_title } ]
   <div class="agm"><span  class="port2 portedmyid${conditionssub3.condition_id }"></span>
   <input id="1" value="${upload_other3s.upload_pn_respon }-${upload_other3s.upload_pn_note}" class="change-label2" readonly="">
</div>
    </c:if>
    
         <c:if test="${conditionssub3sss.condition_id > 0}"> 
           [ ${conditionssub3sss.condition_title } ]
     <div class="agm"><span  class="port2 portedmyid${conditionssub3.condition_id }"></span>
   <input id="1" value="${upload_other3ss.upload_pn_respon }-${upload_other3ss.upload_pn_note}" class="change-label2" readonly="">
  </div>
 
 </c:if>
     
     
  
  </div>
  
 
 
  

  </div>
</div>

</div>



                                  
                                       </div> 

     </c:if>   
  
  
  
                                        </div>
                                     
              
                     
                                     
                                     
                                     
                                     
                                     
                                     
                                    </div>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                         </div>     
                                
      
    </div>        
                  
                
                                
              
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
    
    
    
             <div class="modal fade text-start" id="large2" tabindex="-1" aria-labelledby="myModalLabel17" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel17">Condition</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                          <input type="hidden"  id="dataset_node2" value="">
                                                         <select class="form-select 2" id="dataset_data2">
                                           
                                                
                            							<option value="Type 1"  data-custom="Type 1">Type 1</option>
                              						<option value="Type 2"  data-custom="Type 2">Type 2</option>
                            			  	<option value="Type 3"  data-custom="Type 3">Type 3</option>
                                                         <option value="OEE (A)"  data-custom="OEE (A)">OEE (A)</option>
                                             			 <option value="OEE (P)"  data-custom="OEE (P)">OEE (P)</option>
                                               				 <option value="OEE (Q)"  data-custom="OEE (Q)">OEE (Q)</option>
                                               				  <option value="OEE"  data-custom="OEE">OEE</option>
                                                         </select>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="addCondition()">Import</button>
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
      });
    
    </script>
</c:if>
 
    <script>

    
  
    
    function loaddataset(node){
    	
    	$("#dataset_node").val(node);
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
  
        $('.port').bezier({strokeColor : 'red',});
        
  
        

        
 
        
       
        
        
        
        
     
    }
    function run2(){
    	 $('svg').css('display', 'none');
     
    }

    
 

    var idmain = 0;
    var setdata = null;
    
    
$(document).on('click', '.port1', function () {
	
	
	$(".unactives").removeClass("unactives");
	$('.port1').css('background-color', 'blue');
	
	
 var divId = $(this).attr('id');
 idmain = divId;
 $('#'+divId).css('background-color', 'green');
 $('#'+divId).addClass("unactives");
 
 $('.port2').css('background-color', 'red');
 $('.port2').addClass("actives");

 
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

	    
	  
	      
	 $('.port').bezier({strokeColor : 'red',});    
	 idmain= null;
	
	
	
	
	
    
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
        
        document.getElementById("file").onchange = function() {
    document.getElementById("form").submit();
};




    </script>
</body>

   <script src="<c:url value="/resources/app-assets/vendors/js/editors/quill/katex.min.js" />"></script>
       <script src="<c:url value="/resources/app-assets/vendors/js/editors/quill/highlight.min.js" />"></script>
    <script src="<c:url value="/lib/eform/eform.js" />"></script>

  
  <script>
   function addfield(elementType,ids,label,datarefertype,datareferid,datarefercols,mainfield){

	   
	 
	   
 	const formPreviews = document.querySelector('.form-preview');
 	 let newInput;
 	 let newInputs;
 

 	  if (elementType === 'text') {
 		  
 	
 		  
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
 	                                 <input type="hidden"  value="text" name="field_type"/>
 	                            </div>
 	                            <div class="form-row mt-50">
 	                                <div class="mb-1 col-md-12">
 	                                <label for="tax-1-input" class="form-label">Main Field</label>
 	                                    <select id="tax-1-input" class="form-select tax-select" name="field_mainfield">
 	                                   <option value="`+mainfield+`" selected>`+label+`</option>
 	                                    <option value="-" >ระบุุเอง</option>
 	                                    <c:forEach items="${textfield }" var="row" varStatus="count">
 	                                    <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
 	            
 	        });

 	      
 	            const deleteButton = newInput.querySelector('.delete-button');
 	            deleteButton.addEventListener('click', () => {
 	                newInput.remove(); // ลบ div ตัวเอง
 	                newInputs.remove(); // ลบ div ตัวเอง
 	                newTableHeader.remove();
 	            });
 	            
 	            
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
	                            <div class="form-row mt-50">
	                                <div class="mb-1 col-md-12">
	                                <label for="tax-1-input" class="form-label">Main Field</label>
	                                    <select id="tax-1-input" class="form-select" name="field_mainfield">
	                                   <option value="`+mainfield+`" selected>`+mainfield+`</option>
	                                   <option value="-" >-</option>
	                                    <c:forEach items="${columntype }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}</option>
	            						  </c:forEach>
	                                    </select>
	                                </div>
	                            
	                            </div>
	                            
	                            <div class="form-row mt-50">
	                            <div class="mb-1 col-md-12">
	                            <label for="tax-1-input" class="form-label">Sub Field</label>
	                                <select  id="tax-1-input" class="form-select" name="field_subfield">
	                               <option value="`+subfield+`" selected>`+subfield+`</option>
	                                <option value="-" >-</option>
	                                    <c:forEach items="${columntype2 }" var="row" varStatus="count">
	                                    <option value="${row.name()}">${row.name()}</option>
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
	                                 <input type="hidden"  value="text" name="field_type"/>
	                            </div>
	                            <div class="form-row mt-50">
	                                <div class="mb-1 col-md-12">
	                                <label for="tax-1-input" class="form-label">Main Field</label>
	                                    <select id="tax-1-input" class="form-select tax-select" name="field_mainfield">
	                                   <option value="`+mainfield+`" selected>`+label+`</option>
	                                    <option value="-" >ระบุุเอง</option>
	                                    <c:forEach items="${timefield }" var="row" varStatus="count">
	                                    <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
	            
	        });

	      
	            const deleteButton = newInput.querySelector('.delete-button');
	            deleteButton.addEventListener('click', () => {
	                newInput.remove(); // ลบ div ตัวเอง
	                newInputs.remove(); // ลบ div ตัวเอง
	                newTableHeader.remove();
	            });
	            
	            
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
	              changeLabelElement.value = dataValue || 'Time';

	              // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
	              newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
	              newTableHeader.textContent = changeLabelElement.value || 'Time';
	              newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
	            });      
	            
	            
	            
	        
	        
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
                             <div class="form-row mt-50">
                                 <div class="mb-1 col-md-12">
                                 <label for="tax-1-input" class="form-label">Main Field</label>
                                     <select  id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                 
                                     <option value="`+mainfield+`" selected>`+label+`</option>
                                     
	                                    <option value="-" >ระบุุเอง</option>
                                     <c:forEach items="${textareafield }" var="row" varStatus="count">
                                     <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
         });

       
             const deleteButton = newInput.querySelector('.delete-button');
             deleteButton.addEventListener('click', () => {
                 newInput.remove(); // ลบ div ตัวเอง
                 newTableHeader.remove();
                 newInputs.remove(); // ลบ div ตัวเอง
             });
             
             
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
               changeLabelElement.value = dataValue || 'textarea';

               // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
               newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
               newTableHeader.textContent = changeLabelElement.value || 'textarea';
                newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
             });      
             
             
             
         
         
     }
     
     
 	  
 	  

     
     if (elementType === 'number') {
         newInput = document.createElement('div');
         newInput.className = 'row draggable-row';
         newInput.innerHTML = `

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
                                 	<input type="hidden"  value="number" name="field_type"/>
                             </div>
                             <div class="form-row mt-50" >
                                 <div class="mb-1 col-md-12">
                                     <label for="tax-1-input" class="form-label">Main Field</label>
                                     <select  id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                    
	                                   <option value="`+mainfield+`" selected>`+label+`</option>
                                     <option value="-" >ระบุุเอง</option>
                                     <c:forEach items="${numberfield }" var="row" varStatus="count">
                                     <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
          changeLabelElement.value = dataValue || 'Number';

          // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
          newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
          newTableHeader.textContent = changeLabelElement.value || 'Text';
          newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;   
        });      
         
         
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
                             <div class="form-row mt-50">
                                 <div class="mb-1 col-md-12">
                                     <label for="tax-1-input" class="form-label">MAIN Field</label>
                                     <select   class="form-select tax-select" name="field_mainfield">
                                     
                                     <option value="`+mainfield+`" selected>`+label+`</option>
                                     <option value="-" >ระบุุเอง</option>
                                     <c:forEach items="${datefield }" var="row" varStatus="count">
                                     <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
           changeLabelElement.value = dataValue || 'date';

           // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
           newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue; 
           newTableHeader.textContent = changeLabelElement.value || 'Text';
           newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;  
         });
         

     }
      
     
     if (elementType === 'select') {
         newInput = document.createElement('div');
         newInput.className = 'row draggable-row';
         newInput.innerHTML = `
             <div class="col-md-12 col-12">
                 <div class="mb-1 Selectdata">
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
                             <div class="form-row mt-50">
                                 <div class="mb-1 col-md-12">
                                     <label for="tax-1-input" class="form-label">MAIN Field</label>
                                     <select  id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                     <option value="`+mainfield+`" selected>`+label+`</option>
                                     <option value="-" >ระบุุเอง</option>
                                     <c:forEach items="${selectfield }" var="row" varStatus="count">
                                     <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
           changeLabelElement.value = dataValue || 'Select';

           // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
           newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue; 
           newTableHeader.textContent = changeLabelElement.value || 'Select';
           newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;   
         });
         

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
                             <div class="form-row mt-50">
                                 <div class="mb-1 col-md-12">
                                     <label for="tax-1-input" class="form-label">MAIN Field</label>
                                     <select  id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                     <option value="`+mainfield+`" selected>`+label+`</option>
                                     <option value="-" selected>ระบุุเอง</option>
                                     <c:forEach items="${checkboxfield }" var="row" varStatus="count">
                                     <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
           changeLabelElement.value = dataValue || 'Checkbox';

           // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
           newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue; 
           newTableHeader.textContent = changeLabelElement.value || 'Checkbox';
           newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;   
         });
         

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
                         		<input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
                         	 <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
                         	<input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
                         	
                     </div>
                     <div class="form-row mt-50" style="display:none">
                         <div class="mb-1 col-md-6">
                             <label for="tax-1-input" class="form-label">ขนาด</label>
                             <select name="tax-1-input" id="tax-1-input" class="form-select tax-select">
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
                              	
                              	
                              		<input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
                              	 <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
                              	<input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
        					   
        					   
                     </div>
                     <div class="form-row mt-50" style="display:none">
                         <div class="mb-1 col-md-6">
                             <label for="tax-1-input" class="form-label">ขนาด</label>
                             <select name="tax-1-input" id="tax-1-input" class="form-select tax-select">
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
 
    
    
    
    
    if (elementType === 'Text') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
                <div class="mb-1">
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
                            <div class="form-row mt-50">
                                <div class="mb-1 col-md-12">
                                <label for="tax-1-input" class="form-label">Main Field</label>
                                    <select id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                
                                    <option value="-" selected>ระบุุเอง</option>
                                    <c:forEach items="${textfield }" var="row" varStatus="count">
                                    <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
		newInputs.innerHTML = '<input id="1" value="text" class="change-label2"  readonly>';
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
                            <div class="form-row mt-50">
                                <div class="mb-1 col-md-12">
                                <label for="tax-1-input" class="form-label">Main Field</label>
                                    <select id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                
                                    <option value="-" selected>ระบุุเอง</option>
                                    <c:forEach items="${timefield }" var="row" varStatus="count">
                                    <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
              changeLabelElement.value = dataValue || 'Time';

              // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
              newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
              newTableHeader.textContent = changeLabelElement.value || 'Time';
              newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
            });      
            
            
            
        
        
    }
    
    
    if (elementType === 'Textarea') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
                <div class="mb-1">
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
                            <div class="form-row mt-50">
                                <div class="mb-1 col-md-12">
                                <label for="tax-1-input" class="form-label">Main Field</label>
                                    <select  id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                
                                    <option value="-" selected>ระบุุเอง</option>
                                    <c:forEach items="${textareafield }" var="row" varStatus="count">
                                    <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
              changeLabelElement.value = dataValue || 'textarea';

              // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
              newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
              newTableHeader.textContent = changeLabelElement.value || 'textarea';
               newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
            });      
            
            
            
        
        
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
                            <div class="form-row mt-50" >
                                <div class="mb-1 col-md-12">
                                    <label for="tax-1-input" class="form-label">Main Field</label>
                                    <select  id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                    
                                    <option value="-" selected>ระบุุเอง</option>
                                    <c:forEach items="${numberfield }" var="row" varStatus="count">
                                    <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
         changeLabelElement.value = dataValue || 'Number';

         // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
         newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
         newTableHeader.textContent = changeLabelElement.value || 'Text';
         newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;   
       });      
        
        
    }
     
    
    if (elementType === 'Number') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `

                <div class="mb-1">
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
                                    <select  id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                    
                                    <option value="-" selected>ระบุุเอง</option>
                                    <c:forEach items="${numberfield }" var="row" varStatus="count">
                                    <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
		newInputs.innerHTML = '<input id="1" value="Number" class="change-label2"  readonly>';
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
         changeLabelElement.value = dataValue || 'Number';

         // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
         newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
         newTableHeader.textContent = changeLabelElement.value || 'Text';
         newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;   
       });      
        
        
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
                            <div class="form-row mt-50">
                                <div class="mb-1 col-md-12">
                                    <label for="tax-1-input" class="form-label">MAIN Field</label>
                                    <select   class="form-select tax-select" name="field_mainfield">
                                    <option value="-" selected>ระบุุเอง</option>
                                    <c:forEach items="${datefield }" var="row" varStatus="count">
                                    <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
          changeLabelElement.value = dataValue || 'date';

          // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
          newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue; 
          newTableHeader.textContent = changeLabelElement.value || 'Text';
          newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;  
        });
        

    }
     
    
    if (elementType === 'Select') {
        newInput = document.createElement('div');
        newInput.className = 'row draggable-row';
        newInput.innerHTML = `
            <div class="col-md-12 col-12">
                <div class="mb-1 Selectdata">
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
                            <div class="form-row mt-50">
                                <div class="mb-1 col-md-12">
                                    <label for="tax-1-input" class="form-label">MAIN Field</label>
                                    <select  id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                    <option value="-" selected>ระบุุเอง</option>
                                    <c:forEach items="${selectfield }" var="row" varStatus="count">
                                    <option value="${row.field_id   }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
          changeLabelElement.value = dataValue || 'date';

          // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
          newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue; 
          newTableHeader.textContent = changeLabelElement.value || 'Date';
          newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;   
        });
        

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
                            <div class="form-row mt-50">
                                <div class="mb-1 col-md-12">
                                    <label for="tax-1-input" class="form-label">MAIN Field</label>
                                    <select  id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                    <option value="-" selected>ระบุุเอง</option>
                                    <c:forEach items="${checkboxfield }" var="row" varStatus="count">
                                    <option value="${row.field_id  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
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
          changeLabelElement.value = dataValue || 'Checkbox';

          // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
          newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue; 
          newTableHeader.textContent = changeLabelElement.value || 'Checkbox';
          newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;   
        });
        

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
                                <select name="tax-1-input" id="tax-1-input" class="form-select tax-select" name="field_mainfield">
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
                        	       <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
      					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
      					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >
                        	
                    </div>
                    <div class="form-row mt-50" style="display:none">
                        <div class="mb-1 col-md-6">
                            <label for="tax-1-input" class="form-label">ขนาด</label>
                            <select name="tax-1-input" id="tax-1-input" class="form-select tax-select">
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
                             	
                         	       <input type="hidden"  name="field_datarefertype" value="-" class="form-control3"  >
       					  <input type="hidden"  name="field_datareferid" value="-" class="form-control2"  >
       					   <input type="hidden"  name="field_datarefercols" value="-" class="form-control4"  >
                    </div>
                    <div class="form-row mt-50" style="display:none">
                        <div class="mb-1 col-md-6">
                            <label for="tax-1-input" class="form-label">ขนาด</label>
                            <select name="tax-1-input" id="tax-1-input" class="form-select tax-select">
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



$('.port').bezier({strokeColor : 'red',});

    </script>
    

  <script src='https://code.jquery.com/jquery-1.12.1.js'></script>
<script src='https://code.jquery.com/ui/1.12.1/jquery-ui.js'></script>
<script src='http://sebastien.drouyer.com/jquery.flowchart-demo/jquery.flowchart/jquery.flowchart.js'></script>    
    
                   <c:set var="output_1" value="" />
                      <c:set var="output_2" value="" />
                      <c:set var="output_3" value="" />
                      <c:set var="output_4" value="" />
                      <c:set var="output_5" value="" />
                      
     <c:forEach items="${eformfields }" var="row" varStatus="count">  
                     
       
                      
                        <c:if test="${map_key1.map_key_main1 == row.field_postion}">  
          					    <c:set var="output_1" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key1.map_key_main2 == row.field_postion}">  
          					    <c:set var="output_2" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key1.map_key_main3 == row.field_postion}">  
          					    <c:set var="output_3" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key1.map_key_main4 == row.field_postion}">  
          					    <c:set var="output_4" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key1.map_key_main5 == row.field_postion}">  
          					    <c:set var="output_5" value="${row.field_label }" />
         			  </c:if>
        
    </c:forEach>
    
    
                    <c:set var="input_1" value="" />
                      <c:set var="input_2" value="" />
                      <c:set var="input_3" value="" />
                      <c:set var="input_4" value="" />
                      <c:set var="input_5" value="" />
                      
                      
                                  <c:set var="inputs_1" value="" />
                      <c:set var="inputs_2" value="" />
                      <c:set var="inputs_3" value="" />
                      <c:set var="inputs_4" value="" />
                      <c:set var="inputs_5" value="" />
                      
                              <c:set var="outputs_1" value="" />
                      <c:set var="outputs_2" value="" />
                      <c:set var="outputs_3" value="" />
                      <c:set var="outputs_4" value="" />
                      <c:set var="outputs_5" value="" />
                      
                      
                                         <c:set var="outputss_1" value="" />
                      <c:set var="outputss_2" value="" />
                      <c:set var="outputss_3" value="" />
                      <c:set var="outputss_4" value="" />
                      <c:set var="outputss_5" value="" />
    
      <c:forEach items="${eformfields2 }" var="row" varStatus="count">  
                     
       
                          <c:if test="${map_key1.map_key_sub1 == row.field_postion}">  
          					    <c:set var="input_1" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key1.map_key_sub2 == row.field_postion}">  
          					    <c:set var="input_2" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key1.map_key_sub3 == row.field_postion}">  
          					    <c:set var="input_3" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key1.map_key_sub4 == row.field_postion}">  
          					    <c:set var="input_4" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key1.map_key_sub5 == row.field_postion}">  
          					    <c:set var="input_5" value="${row.field_label }" />
         			  </c:if>
        
                        <c:if test="${map_key2.map_key_main1 == row.field_postion}">  
          					    <c:set var="outputs_1" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key2.map_key_main2 == row.field_postion}">  
          					    <c:set var="outputs_2" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key2.map_key_main3 == row.field_postion}">  
          					    <c:set var="outputs_3" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key2.map_key_main4 == row.field_postion}">  
          					    <c:set var="outputs_4" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key2.map_key_main5 == row.field_postion}">  
          					    <c:set var="outputs_5" value="${row.field_label }" />
         			  </c:if>
        
    </c:forEach>
         <c:forEach items="${eformfields3 }" var="row" varStatus="count">  
                     
       
                          <c:if test="${map_key2.map_key_sub1 == row.field_postion}">  
          					    <c:set var="inputs_1" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key2.map_key_sub2 == row.field_postion}">  
          					    <c:set var="inputs_2" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key2.map_key_sub3 == row.field_postion}">  
          					    <c:set var="inputs_3" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key2.map_key_sub4 == row.field_postion}">  
          					    <c:set var="inputs_4" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key2.map_key_sub5 == row.field_postion}">  
          					    <c:set var="inputs_5" value="${row.field_label }" />
         			  </c:if>
        
                        <c:if test="${map_key3.map_key_main1 == row.field_postion}">  
          					    <c:set var="outputss_1" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key3.maps_key_main2 == row.field_postion}">  
          					    <c:set var="outputss_2" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key3.map_key_main3 == row.field_postion}">  
          					    <c:set var="outputss_3" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key3.map_key_main4 == row.field_postion}">  
          					    <c:set var="outputss_4" value="${row.field_label }" />
         			  </c:if>
         			     <c:if test="${map_key3.map_key_main5 == row.field_postion}">  
          					    <c:set var="outputss_5" value="${row.field_label }" />
         			  </c:if>
        
    </c:forEach>
    
    
<script>      

		
		    	 
			   var data = {
						  "operators": {
						    "operator1": {
						      "top": 20,
						      "left": 20,
						      "properties": {
						        "title": "${condition.condition_name}",
						        "inputs": {},
						        "class": "flow-request",
						        "outputs": 
						            <c:if test="${output_1 != ''}">    
						        {
						          
						        	"output_1": {
						            "label": "${output_1}"
						          },
						          </c:if>
						          <c:if test="${output_2 != ''}">    
						          "output_2": {
							            "label": "${output_2}"
							          },
							          
							          </c:if>  
							          <c:if test="${output_3 != ''}"> 
						          "output_3": {
							            "label": "${output_3}"
							          },
							          
							          </c:if> 
							          <c:if test="${output_4 != ''}"> 
							          "output_4": {
								            "label": "${output_4}"
								          },
								          </c:if> 
								          <c:if test="${output_5 != ''}"> 
							          "output_5": {
								            "label": "${output_5}"
								          }
								          </c:if> 
						        }
						      }
						    },
						    "created_operator_0": {
						      "top": 20,
						      "left": 240,
						      "properties": {
						        "class": "flow-condition",
						        "title": "${conditionssub.condition_name}",
						        "inputs":
						        
						        {
						 <c:if test="${input_1 != ''}"> 
						          "input_1": {
						            "label": "${input_1}"
						          },
						    </c:if> 
						          <c:if test="${input_2 != ''}"> 
						          "input_2": {
						            "label": "${input_2}"
						          },
						    </c:if> 
						          <c:if test="${input_3 != ''}"> 
						          "input_3": {
						            "label": "${input_3}"
						          },
						    </c:if> 
						          <c:if test="${input_4 != ''}"> 
						          "input_4": {
						            "label": "${input_4}"
						          },
						    </c:if> 
						          <c:if test="${input_5 != ''}"> 
						          "input_5": {
						            "label": "${input_5}"
						          },
						    </c:if> 
						          
						          
						          
						        },
						        "outputs": {
						        	  <c:if test="${outputs_1 != ''}">    	
						        	
						          "output_1": {
						            "label": "${outputs_1}"
						          }  ,
							        
						        	  </c:if> 
						     	  <c:if test="${outputs_2 != ''}">    	
						        	
						          "output_2": {
						            "label": "${outputs_2}"
						          }  ,
							        
						        	  </c:if> 
						     	  <c:if test="${outputs_3 != ''}">    	
						        	
						          "output_3": {
						            "label": "${outputs_3}"
						          }  ,
							        
						        	  </c:if> 
						     	  <c:if test="${outputs_4 != ''}">    	
						        	
						          "output_4": {
						            "label": "${outputs_4}"
						          }  ,
							        
						        	  </c:if> 
						     	  <c:if test="${outputs_5 != ''}">    	
						        	
						          "output_5": {
						            "label": "${outputs_5}"
						          }  ,
							        
						        	  </c:if> 
						        
						        
						        }
						      }
						    },
						    
						    
					<c:if test="${conditionssub2 != null}">     
						    "created_operator_1": {
						      "top": 20,
						      "left": 460,
						      "properties": {
						        "class": "flow-page",
						        "title": "${conditionssub2.condition_name}",
						        "inputs": {
						        	
						        	
						        	 <c:if test="${inputs_1 != ''}"> 
							          "input_1": {
							            "label": "${inputs_1}"
							          },
							    </c:if> 
							          <c:if test="${inputs_2 != ''}"> 
							          "input_2": {
							            "label": "${inputs_2}"
							          },
							    </c:if> 
							          <c:if test="${inputs_3 != ''}"> 
							          "input_3": {
							            "label": "${inputs_3}"
							          },
							    </c:if> 
							          <c:if test="${inputs_4 != ''}"> 
							          "input_4": {
							            "label": "${inputs_4}"
							          },
							    </c:if> 
							          <c:if test="${inputs_5 != ''}"> 
							          "input_5": {
							            "label": "${inputs_5}"
							          },
							    </c:if> 
							          
						         
						         
						        },
						        "outputs": {
						       
						        
						        
						        }
						      }
						    }
						    </c:if> 
						    
						    
						  },
						  "links": {
							  
					           <c:if test="${input_1 != ''}">    
						    "0": {
						      "fromOperator": "operator1",
						      "fromConnector": "output_1",
						      "fromSubConnector": 0,
						      "toOperator": "created_operator_0",
						      "toConnector": "input_1",
						      "toSubConnector": 0
						    },
						    </c:if>
						    <c:if test="${input_2 != ''}">   
						    "1": {
							      "fromOperator": "operator1",
							      "fromConnector": "output_2",
							      "fromSubConnector": 0,
							      "toOperator": "created_operator_0",
							      "toConnector": "input_2",
							      "toSubConnector": 0
							   
						  }
						  ,
						    </c:if>
				
						  
						    <c:if test="${input_3 != ''}">   
						  "2": {
							      "fromOperator": "operator1",
							      "fromConnector": "output_3",
							      "fromSubConnector": 0,
							      "toOperator": "created_operator_0",
							      "toConnector": "input_3",
							      "toSubConnector": 0
							   
						  }
						   ,
						   </c:if>
						   
						    <c:if test="${input_4 != ''}">  
						   "3": {
							      "fromOperator": "operator1",
							      "fromConnector": "output_4",
							      "fromSubConnector": 0,
							      "toOperator": "created_operator_0",
							      "toConnector": "input_4",
							      "toSubConnector": 0
							 
						  }
						   ,
						   </c:if>
						   <c:if test="${input_5 != ''}">    
						   "4": {
							      "fromOperator": "operator1",
							      "fromConnector": "output_5",
							      "fromSubConnector": 0,
							      "toOperator": "created_operator_0",
							      "toConnector": "input_5",
							      "toSubConnector": 0
							   
						  }
						   ,
						   </c:if>
						   
						   
							 
						   <c:if test="${inputs_1 != ''}">   
						    "5": {
						      "fromOperator": "created_operator_0",
						      "fromConnector": "output_1",
						      "fromSubConnector": 0,
						      "toOperator": "created_operator_1",
						      "toConnector": "input_1",
						      "toSubConnector": 0
						    },
						    </c:if>
						    <c:if test="${inputs_2 != ''}">   
						   "6": {
							      "fromOperator": "created_operator_0",
							      "fromConnector": "output_2",
							      "fromSubConnector": 0,
							      "toOperator": "created_operator_1",
							      "toConnector": "input_2",
							      "toSubConnector": 0
							   
						  }
						   
						   ,
						   </c:if>
						   
						   
						   <c:if test="${inputs_3 != ''}">   
						   "7": {
							      "fromOperator": "created_operator_0",
							      "fromConnector": "output_3",
							      "fromSubConnector": 0,
							      "toOperator": "created_operator_1",
							      "toConnector": "input_3",
							      "toSubConnector": 0
							   
						  }
						   ,
						   </c:if> 
						   <c:if test="${inputs_4 != ''}">   
						   "8": {
							      "fromOperator": "created_operator_0",
							      "fromConnector": "output_4",
							      "fromSubConnector": 0,
							      "toOperator": "created_operator_1",
							      "toConnector": "input_4",
							      "toSubConnector": 0
							   
						  }
						   ,
						   
						   </c:if>  
						   <c:if test="${inputs_5 != ''}">   
						   "9": {
							      "fromOperator": "created_operator_0",
							      "fromConnector": "output_5",
							      "fromSubConnector": 0,
							      "toOperator": "created_operator_1",
							      "toConnector": "input_5",
							      "toSubConnector": 0
							   
						  }
						   </c:if> 
						   
						   
						   
						  }
			    
			    
						};

						$(document).ready(function() {
						  $("#chart").flowchart({
						    "data": data
						  });
						});    
		     
		 
		</script>    
    
         
        
  