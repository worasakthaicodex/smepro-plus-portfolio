<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="common/mainhead.jspf" %>    

<link href="<c:url value="/resources/app-assets/vendors/css/pickers/pickadate/pickadate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/pickers/flatpickr/flatpickr.min.css" />" rel="stylesheet">

<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-flat-pickr.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-pickadate.css" />" rel="stylesheet">


<link href="<c:url value="/resources/app-assets/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/css/bootstrap-extended.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>




 <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-drag-drop.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <c:set var = "now" value = "<%= new java.util.Date()%>" />
<title>File upload</title>
<style>



dselect2 {
    width: 200px; /* ปรับเป็นค่าที่คุณต้องการ */
    max-height: 40px; /* ปรับเป็นค่าที่คุณต้องการ */
    overflow-y: auto /*  ; เพื่อให้มีการเลื่อนแถบเลื่อนเมื่อข้อมูลเกินขอบเขตสูงสุด */
}
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



.draggable {
  cursor: red;
}



 table {
    border-collapse:collapse;
    margin: 0;
  width: 100%;
   border: 1px solid #dddddd;
   
}


  
td {
    text-align: center;
   border: 1px solid #dddddd;
      max-width: 150px;
     font-size:14px;
       overflow: hidden; /* ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
}


.fixhead{
    max-width: 150px;
      min-width: 80px;
      
}







</style>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
                    
          
   <form:form method="POST" modelAttribute="edata"  action="/addfiles-filemanager?${_csrf.parameterName}=${_csrf.token}"  id="form" enctype="multipart/form-data"> 
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">E-form : ${condition.condition_title }</h4>
                                </div>
                                
                    
                                <div class="card-body">
                             <c:if test="${upload_pn.size() == 0 }"> 
                           			  <div class="demo-spacing-0">
                                        <div class="alert alert-primary alert-dismissible fade show" role="alert">
                                            <div class="alert-body">
                                                ไม่พบการตั้งค่าการอัพโหลดไว้ กรุณาเช็คการตั้งค่าการอัพโหลดโดยตรงก่อนดำเนินการ
                                            </div>
                                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                        </div>
                                  	  </div>
                              </c:if>   
                              
                              
                             
                                      <c:if test="${upload_pn.size() > 0 }"> 
                                
                               
                                 
                                 
                              
                                        <div class="row" id="formss">
                              
                              
                              
                                  <c:if test="${upload.upload_pn_monitor > 0 }"> 
<input type="hidden" name="data_planid"  id="data_planid"  value="${planid }">                                     
<input type="hidden" name="data_planid"  id="data_planid"  value="${planid }">                           
<input type="hidden" name="data_eformid"  id="data_eformid"  value="${eformid }">                                        
<input type="hidden" name="data_eformrv"  id="data_eformrv"  value="${eformrv }">  
<input type="hidden" name="data_dateplan"  id="data_dateplan"  value="${dateplan }">  	  
<input type="hidden" name="data_plantype"  id="data_plantype"  value="${plantype }">  	 

        
                     <c:forEach items="${eformfields }" var="row" varStatus="count">
                               <c:set var="title" value="${row.field_label }" />
 
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${title }</label>
                                                 <c:if test='${row.field_type.equals("select")}'>      
                                                 <select name="data_value${count.count }"  id="data_value${row.field_id }" class="form-select">
                                                 </select>
                                                  </c:if>
                                                 
                                                 
                                                   <c:if test='${row.field_type.equals("text")}'>      
                                                 <input type="text" name="data_value${count.count }"  id="data_value${row.field_id }" class="autocomplete-input form-control">
                                                  </c:if>
                                                  
                                                    <c:if test='${row.field_type.equals("number")}'>      
                                                 <input type="number" name="data_value${count.count }"  id="data_value${row.field_id }" class="form-control">
                                                  </c:if>
                                                 
                                                    <c:if test='${row.field_type.equals("textarea")}'>      
                                                 <textarea name="data_value${count.count }"  id="data_value${row.field_id }" class="form-control"></textarea>
                                                  </c:if>
                                              <c:if test='${row.field_type.equals("date")}'>      
                                               <input type="date" name="data_value${count.count }"  id="data_value${row.field_id }" class="form-control">
                                                  </c:if>
                                                  
                                                   <c:if test='${row.field_type.equals("time")}'>      
                                              <input type="text" class="form-control time-mask mydata"  name="data_value${count.count }" id="data_value${row.field_id }"  placeholder="hh:mm:ss" >
                                                  </c:if> 
                                                  
                                                    <c:if test='${row.field_type.equals("checkbox")}'>
                                                 <br>      
                                                <span id="data_value${row.field_id }"></span>
                                                    </c:if>
                                                </div>
                                            </div>  
                                            
                                    <input type="hidden" name="data_title${count.count }"  id="data_title${row.field_id }"  value="${title }">                             
                        </c:forEach>
                            </c:if>  
                        
                        
                        
                                               <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">ระบุชื่อไฟล์ที่จะอัพโหลด</label>
                                                   <select name="upload_pn"  class="form-select">
                                                    <c:forEach items="${upload_pn }" var="row" >
                                                       <c:set var="s_upload" value="" />
                                                       <c:if test="${row.upload_pn_id == upload.upload_pn_id}">    
                                                              <c:set var="s_upload" value="selected" />
                                                    <option  value='${row.upload_pn_id }-1' ${s_upload}>${row.upload_pn_name }</option>
                                                    
                                                        <c:if test='${row.upload_pn_name2 != null &&  row.upload_pn_name2 != "" }'>  
                                                    <option  value='${row.upload_pn_id }-2' >${row.upload_pn_name2 }</option>
                                                    </c:if>
                                                         <c:if test='${row.upload_pn_name3 != null &&  row.upload_pn_name3 != "" }'>  
                                                    <option  value='${row.upload_pn_id }-3' >${row.upload_pn_name3 }</option>
                                                    </c:if>
                                                           <c:if test='${row.upload_pn_name4 != null &&  row.upload_pn_name4 != "" }'>  
                                                    <option  value='${row.upload_pn_id }-4' >${row.upload_pn_name4 }</option>
                                                    </c:if>
                                                <c:if test='${row.upload_pn_name5 != null &&  row.upload_pn_name5 != "" }'>  
                                                    <option  value='${row.upload_pn_id }-5' >${row.upload_pn_name5 }</option>
                                                    </c:if>
                                                         </c:if>  
                                                     </c:forEach>
                                                    </select>
                                                </div>
                                            </div>   
                                            
                                            
                                                 <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">เเนบเอกสาร</label><br>
                          
                                 
                                   <a href="#" class="btn btn-light me-1 waves-effect waves-float waves-light" onclick="camera()" style="float:left">
                               Open camera
                         </a >
                                 &nbsp;&nbsp;<span style="float:left;padding-top:10px"> OR   </span>
                                 <input type="file" name="file" id="file" class="form-control"  style="width:50%;float:left;margin-left:10px;"  >
                                 <input type="hidden" name="id" id="id" value="${idrefer }"  >
                                  <input type="hidden" name="level" id="level" value="${level }"  >
                                       <input type="hidden" name="upload_pn_ids" id="upload_pn_ids" value="${upload.upload_pn_id }"  >                   
                        
                           
                                                </div>
                                            </div>   
                                               
                                        
                                               
                                     <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">ประจำวันที่</label>
                                             <input type="text"  name="due" id="fp-default" class="form-control flatpickr-basic" placeholder="YYYY-MM-DD" value="${due }" required/>
                                                </div>
                                            </div>   
                                            
                                            
                          
                              
                                            
                                            
                                            
                                       
                                                         <br><br>
                                           
                                            
                                   
                                      <hr>   
                          
                                       
                                       
                                       
                           
                                       
                                       
                                       
                                            
            
               
                                      
                                            
                                         <div class="col-12">
                              <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">
                                <spring:message code="btn.add" text="เพิ่ม" />
                               </button>   
                          
                      
                           
                         <iframe id="pdfViewer" style="width:100%;width;height:500px;border:1px solid black;display:none"></iframe>
                         
                       
                                            </div>
                                            
                                            
                                            
                        
   
                           
                                        </div>
                                 
                                        
                        <div  id="camera" style="display: none;">
             
                            <video id="cameraFeed" autoplay style="width:60%;margin-left:20%"></video><br><br>
                              <a href="#" id="captureButton" class="btn btn-primary me-1 waves-effect waves-float waves-light">ถ่ายรูป</a> 
    						  <a href="#" class="btn btn-primary me-1 waves-effect waves-float waves-light" onclick="generatePdf()">รวมเป็น PDF</a>
                             <br>
                              <br>
                              
                              
                               <div class="row " id="card-drag-area">
                   
                    </div>
                              
                              
                        <canvas id="photoCanvas" style="display: none;"></canvas>
    						
    						
    								
    							
    									
    										
    												
    												
    													
    														<img id="capturedPhoto9" style="display: none;width:200px;float:left;margin-left:5px" alt="รูปถ่าย" >
    															<img id="capturedPhoto10" style="display: none;width:200px;float:left;margin-left:5px" alt="รูปถ่าย" >
    																<img id="capturedPhoto11" style="display: none;width:200px;float:left;margin-left:5px" alt="รูปถ่าย" >
    																<img id="capturedPhoto12" style="display: none;width:200px;float:left;margin-left:5px" alt="รูปถ่าย" >
    															
                              <br>
                              
                              
                               <div style="clear:both"></div>
                              
                              <br>  <br>  <br>
                                
                         
    						   
                          </div>    
                                        </c:if>    
                                </div>
                            </div>
                            
                        
                            
                        </div>
               </form:form>
                   
 


                      <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">E-Data : ${condition.condition_title }</h4>
                                </div>
                                
                 
                                     
                                <div class="card-body ">
                                  <div class="table-responsive">    
                                  
 
                                                 
          <table id="tabledata " style="height:auto">
                                    <thead>
                                        <tr>
                                
                                      
                              <c:forEach items="${eformfields }" var="row" varStatus="count">     
                              
                            
                              
                              
                                        <th class="form-label fixhead">${row.field_label }</th>
                               </c:forEach>  
                            <th class="form-label fixhead">วันที่เพิ่ม</th>
                                     <th class="form-label fixhead">เอกสารเเนบ</th>
                                   <th class="form-label fixhead">สถานะ</th>
                                     <th class="form-label fixhead">ผู้เพิ่ม</th>
                                               <th class="form-label fixhead">ดำเนินการ</th>
                                    </thead>
                                 <tbody>  
                                 
                                   <c:set var="newdata" value='' />
                                 <c:forEach items="${edatas }" var="rows" varStatus="count">  
                                 <tr class="rowbody">
                                 
                                     <c:if test="${rows.data_title1 != null }">  
                                      
                                     <td >${rows.data_value1 }</td>
                                     
                                     
                                  
	
                                  <c:set var="newdata" value='${newdata} ${rows.data_value1}' />
                                
                                      </c:if>
                                    <c:if test="${rows.data_title2 != null }">   
                                     <td>${rows.data_value2 }</td>
                                       <c:set var="newdata" value='${newdata} ${rows.data_title2}' />
                                      </c:if>
                                         <c:if test="${rows.data_title3 != null }">  
                                     <td>${rows.data_value3 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title3}' />
                                      </c:if>
                                         <c:if test="${rows.data_title4 != null }">  
                                     <td>${rows.data_value4 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title4}' />
                                      </c:if>
                                         <c:if test="${rows.data_title5 != null }">  
                                     <td>${rows.data_value5 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title5}' />
                                      </c:if>
                                         <c:if test="${rows.data_title6 != null }">  
                                     <td>${rows.data_value6 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title6}' />
                                      </c:if>
                                         <c:if test="${rows.data_title7 != null }">  
                                     <td>${rows.data_value7 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title7}' />
                                      </c:if>
                                         <c:if test="${rows.data_title8 != null }">  
                                     <td>${rows.data_value8 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title8}' />
                                      </c:if>
                                         <c:if test="${rows.data_title9 != null }">  
                                     <td>${rows.data_value9 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title9}' />
                                      </c:if>   <c:if test="${rows.data_title10 != null }">  
                                     <td>${rows.data_value10 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title10}' />
                                      </c:if>
                                         <c:if test="${rows.data_title11 != null }">  
                                     <td>${rows.data_value11 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title11}' />
                                      </c:if>
                                         <c:if test="${rows.data_title12 != null }">  
                                     <td>${rows.data_value12 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title12}' />
                                      </c:if>
                                         <c:if test="${rows.data_title13 != null }">  
                                     <td>${rows.data_value13 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title13}' />
                                      </c:if>
                                         <c:if test="${rows.data_title14 != null }">  
                                     <td>${rows.data_value14 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title14}' />
                                      </c:if>
                                         <c:if test="${rows.data_title15 != null }">  
                                     <td>${rows.data_value15 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title15}' />
                                      </c:if>
                                         <c:if test="${rows.data_title16 != null }">  
                                     <td>${rows.data_value16 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title16}' />
                                      </c:if>
                                         <c:if test="${rows.data_title17 != null }">  
                                     <td>${rows.data_value17 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title17}' />
                                      </c:if>
                                         <c:if test="${rows.data_title18 != null }">  
                                     <td>${rows.data_value18 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title18}' />
                                      </c:if>
                                         <c:if test="${rows.data_title19 != null }">  
                                     <td>${rows.data_value19 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title19}' />
                                      </c:if>
                                         <c:if test="${rows.data_title20 != null }">  
                                     <td>${rows.data_value20 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_title20}' />
                                      </c:if>
                                      
                 <td>${rows.data_dateadd }</td>
                              
                                       <td >
                                         <a href="/render-filemanager?file=${rows.data_filepath }" target="_blank" 
     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); changeRowColor(this); return false;">
    ${rows.data_filetype}
  </a>
                                     
                                     </td>
                                     
                                            <td>
                                         </td>
                                         
                                         
                                      <td>${rows.data_nameadd } </td>
                                     
                                     
                                     
                                     
                            
                                      <td>   
                                      <a href="#" class="btn btn-sm btn-success" >
							          Action</a>
                                      </td>
                                 </tr>
                                 </c:forEach>
                                 
                                                                  
                             <script>

	  $( function() {
		  
		  var newdata = '${newdata}'; // ข้อความเริ่มต้
		  var trimmedData = newdata.trim(); // ตัดช่องว่างที่อยู่ท้ายสตริง

		// แยกข้อมูลด้วยช่องว่างเป็น array
		var dataItems = trimmedData.split(/\s+/);

		// แปลงทุกตัวใน array เป็นตัวพิมพ์ใหญ่หรือตัวพิมพ์เล็ก
		var availableTags = new Set(dataItems.map(function(item) {
		    return item.toLowerCase(); // หรือใช้ .toUpperCase() ถ้าต้องการให้เป็นตัวพิมพ์ใหญ่ทั้งหมด
		}));

		// แปลง Set กลับเป็น array
		var uniqueTags = Array.from(availableTags);
		  
		  
	    $( ".autocomplete-input" ).autocomplete({
	      source: uniqueTags
	    });
	  } );
</script>      
                                 </tbody>
                                 
                                 
                                 
                                 
                                 </table>            
                    </div>
                           </div>        
                                
                               </div>    
                                </div>   
                                
                                
                                
                                
                                
                  
                    </div>
                    
                    
                    
                    
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>



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
                                                
                             
                                           <c:set var="data" value='${row.dataset_1 },${row.dataset_2 },${row.dataset_3 },${row.dataset_4 },${row.dataset_5 },${row.dataset_6 },${row.dataset_7 },${row.dataset_8 },${row.dataset_9 },${row.dataset_10 },${row.dataset_11 },${row.dataset_12 },${row.dataset_13 },${row.dataset_14 },${row.dataset_15 }' />
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






<%@ include file="common/mainfoot.jspf" %>  

<script src="<c:url value="/resources/app-assets/js/scripts/extensions/ext-component-drag-drop.js" />"></script>
<script src="<c:url value="/resources/app-assets/vendors/js/extensions/dragula.min.js" />"></script>


  <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/legacy.js" />"></script>
  <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.date.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>
 <script src="<c:url value="/resources/app-assets/js/scripts/forms/pickers/form-pickers.js" />"></script>
 
 	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

  
  <script>
  
  
  function changeRowColor(linkElement) {
	    var rowbodies = document.querySelectorAll('.rowbody');
	    
	    // กำหนด event listener สำหรับทุก .rowbody
	    rowbodies.forEach(function(rowbody) {
	        rowbody.addEventListener('click', function(event) {
	            rowbodies.forEach(function(body) {
	                body.style.backgroundColor = 'transparent'; // กำหนดสีพื้นหลังเป็น透วสีทั้งหมด
	            });
	            rowbody.style.backgroundColor = '#F5F5F5'; // เปลี่ยนสีพื้นหลังของ .rowbody ที่ถูกคลิกเป็นสีเหลือง
	        });
	    });
	    
	    // หา <tr> ที่ประกอบไปด้วยลิงก์ที่ถูกคลิก
	    var row = linkElement.closest('tr');
	    
	    // กำหนดสีพื้นหลังของ <tr> ที่ถูกคลิกเป็นสีเหลือง
	    row.style.backgroundColor = '#F5F5F5';
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
    <script>
    
    function adddatasets(type,id,ids) {
 	   
 	   
 	   
	    if (type === "dataset") {
	    	 // ดึงค่า value ของ <select> โดยใช้ jQuery
	        var selectedValue = $("#dataset_data").val();

	        // หา <option> ที่มี value ตรงกับ selectedValue โดยใช้ jQuery
	        var selectedOption = $("#dataset_data option[value='"+id+"']");

	        // ดึงค่า data-custom จาก selectedOption โดยใช้ jQuery
	        var dataCustomValue = selectedOption.attr("data-custom");
	
		   return dataCustomValue;
	      
	    }
	    
	    return null;
        
	}
    

    
     function addfield(elementType,ids,label,datarefertype,datareferid,datarefercols,mainfield,count){

			 	  
			 	  
			     if (elementType === 'select') {
			    	 var dataold = adddatasets(datarefertype,datareferid,ids);
			  	
			
			     }
			     
			     
			     if (elementType === 'checkbox') {
			    	 var dataold = adddatasets(datarefertype,datareferid,ids);
			   
			     }
			     
			
			 
			     if(dataold && elementType === 'select'){
			        	
			     	 
			 		      	 var selected = document.getElementById("data_value"+ids);
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
			        	
			        	 
				      	 var checkbox = document.getElementById("data_value"+ids);
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
				      		        checkboxs.name = 'data_value'+count;
				      		        
				      		        // สร้าง <label> element ที่มีค่าจาก values[i]
				      		        var label = document.createElement('label');
				      		        label.innerHTML = values[i]+'&nbsp;&nbsp;&nbsp;&nbsp;';
				      		        
				      		        // เพิ่ม <input> และ <label> ลงใน <span class="checkbox">
				      		        checkbox.appendChild(checkboxs);
				      		        checkbox.appendChild(label);
				      		        
				      		        // เพิ่มขีดจำนวนระหว่าง checkbox
				      		
				      		        
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
    
       </script>
    
    
    
    
<c:forEach items="${eformfields }" var="row" varStatus="count">
       <script>
       addfield("${row.field_type}","${row.field_id}","${row.field_label}","${row.field_datarefertype}","${row.field_datareferid}","${row.field_datarefercols}","${row.field_mainfield}","${count.count}");
       </script>
</c:forEach>

    
    
    
       <script>
  
    
    $(window).on('load', function() {
        if (feather) {
            feather.replace({
                width: 14,
                height: 14
            });
        }
    })
    
    
    
    
    
    window.jsPDF = window.jspdf.jsPDF;
    function generatePdf() {
    	 $("#camera").toggle();
    	 $("#formss").toggle();
    	 
        // สร้างเอกสาร PDF
        const doc = new jsPDF();
  
        let yOffset = 10;

        
        const elementsWithClass = document.querySelectorAll('.capture');

     // สร้างอาร์เรย์สำหรับเก็บค่า src
     const base64Images = [];

 
     
     // วนลูปผ่านองค์ประกอบที่มีคลาส
     elementsWithClass.forEach(element => {
         const src = element.getAttribute('src');
         if (src) {
        	 base64Images.push(src);
         }
     });
        

   

        for (const imageBase64 of base64Images) {
          const img = new Image();
          img.src = base64Images;

          const maxWidth = 195;
          const maxHeight = doc.internal.pageSize.getHeight();

          const imgWidth = 190;
          const imgHeight = img.height;
          console.log(maxHeight);
          let width, height;

          // คำนวณอัตราส่วนความกว้างและความสูง
          const aspectRatio = imgWidth / imgHeight;

          // ถ้าภาพกว้างเกินความกว้างของหน้า PDF ให้ย่อความกว้าง
          if (imgWidth > maxWidth) {
              width = maxWidth;
              height = maxWidth / aspectRatio;
          }else {
              width = imgWidth;
              height = imgHeight;
          }

          // ถ้าความสูงยังเกินความสูงของหน้า PDF ให้ย่อความสูง
          if (height > maxHeight) {
              height = maxHeight;
              width = maxHeight * aspectRatio;
          }

          doc.addImage(imageBase64, "PNG", 10, 10, width, height);
          doc.addPage(); // เพิ่มหน้าใหม่สำหรับรูปถัดไป
        }

        // สร้าง URL ของเอกสาร PDF
        const pdfDataUri = doc.output('datauristring');

        // แสดงเอกสาร PDF ใน iframe
        const pdfViewer = document.getElementById('pdfViewer');
        pdfViewer.src = pdfDataUri;
       
        
        
        const base64Data = pdfDataUri; // Your Base64 PDF data here

     // Create a Blob from the Base64 data
     const byteCharacters = atob(base64Data.split(',')[1]);
     const byteArrays = [];

     for (let offset = 0; offset < byteCharacters.length; offset += 512) {
       const slice = byteCharacters.slice(offset, offset + 512);

       const byteNumbers = new Array(slice.length);
       for (let i = 0; i < slice.length; i++) {
         byteNumbers[i] = slice.charCodeAt(i);
       }

       const byteArray = new Uint8Array(byteNumbers);
       byteArrays.push(byteArray);
     }

     const blob = new Blob(byteArrays, { type: 'application/pdf' });
        
    // const downloadLink = document.createElement('a');
    // downloadLink.href = URL.createObjectURL(blob);
    // downloadLink.download = 'output.pdf';
     //downloadLink.click();
      var inp  = document.getElementById('file');
      const dT = new DataTransfer();
      dT.items.add(new File([blob], 'created_auto.pdf'));
      inp.files = dT.files;
        
       
       
      }
   
    
    function deleteCard(button) {
        const cardDiv = button.closest('.draggable');
        if (cardDiv) {
            cardDiv.remove(); // ลบ div ที่คลิกออกไป
        }
    }

    const cardDragArea = document.getElementById("card-drag-area");

    let divCount = 0; // เริ่มจาก 0 และเพิ่มทีละ 1 ทุกครั้งเมื่อกดปุ่ม

    // ฟังก์ชันสร้าง div ใหม่
    function createNewDiv() {
        divCount++; // เพิ่มจำนวน div

        const newDiv = document.createElement("div");
        newDiv.className = "col-xl-2 col-md-3 col-sm-3 draggable";
        
        newDiv.id = "card" + divCount; // เพิ่ม id ตามลำดับ

        newDiv.innerHTML = '<div class="card"><div class="card-header"> <h4 class="card-title">Capture Card '+divCount+'</h4><button type="button" class="btn-close" onclick="deleteCard(this)"></button></div> <div class="card-body"><img id="capturedPhoto'+divCount+'" class="capture" style="width:100%;float:left;margin-left:5px" alt="รูปถ่าย"> </div></div>';

        cardDragArea.appendChild(newDiv); // เพิ่ม div ใหม่ลงใน card-drag-area
    }

    
    
    
    
     function camera(){
    	 
    	 $("#camera").toggle();
    	 $("#formss").toggle();
    	 
    	
    
    	 
    	  const cameraFeed = document.getElementById('cameraFeed');
          const captureButton = document.getElementById('captureButton');
          const photoCanvas = document.getElementById('photoCanvas');
         

          // เปิดกล้องและสตรีมวิดีโอ
          navigator.mediaDevices.getUserMedia({ video: true })
              .then(function (stream) {
                  cameraFeed.srcObject = stream;
              })
              .catch(function (error) {
                  console.error('เกิดข้อผิดพลาดในการเข้าถึงกล้อง: ', error);
              });
          
          // ถ่ายรูป
          captureButton.addEventListener('click', function () {
        	  
        	  createNewDiv();
        	
        
        	 const capturedPhoto = document.getElementById("capturedPhoto"+divCount);
              const context = photoCanvas.getContext('2d');
              photoCanvas.width = cameraFeed.videoWidth;
              photoCanvas.height = cameraFeed.videoHeight;
              context.drawImage(cameraFeed, 0, 0, cameraFeed.videoWidth, cameraFeed.videoHeight);

              capturedPhoto.src = photoCanvas.toDataURL('image/png');
              capturedPhoto.style.display = 'block';
            
              
              
          });
          
          // ดึงค่า src ของรูปภาพ
     
    
      
       

    
          
          
          
     }
    
    
   
        
              var element = document.getElementById("nav_doc");
  element.classList.add("active");  
    </script>
</body>


