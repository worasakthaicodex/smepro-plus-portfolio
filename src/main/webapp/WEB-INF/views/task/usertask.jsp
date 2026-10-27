<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    

       <link href="<c:url value="/resources/app-assets/css/pages/app-email.css" />" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>


<link href="<c:url value="/resources/app-assets/vendors/css/pickers/pickadate/pickadate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/pickers/flatpickr/flatpickr.min.css" />" rel="stylesheet">

<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-flat-pickr.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-pickadate.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-drag-drop.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
 

 
<title>File Pn</title>

<style>
.ellipsis {
  display: inline-block;
  max-width: 250px;       /* ปรับตามความเหมาะสม */
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  vertical-align: middle;
}

.selected-border {
            border: 2px solid rgba(0, 0, 255, 0.8); /* ขอบสีน้ำเงินโปร่งใส */
             border-radius: 10px;
        }


.design-planning {
cursor: pointer; 
}

.ui-autocomplete{

z-index:9999;
}

.container {
  max-width: 960px;
}

.panel-default>.panel-heading {
  color: #333;
  background-color: #fff;
  border-color: #e4e5e7;
  padding: 0;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.panel-default>.panel-heading a {
  display: block;
  padding: 10px 15px;
}

.panel-default>.panel-heading a:after {
  content: "";
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: 'Glyphicons Halflings';
  font-style: normal;
  font-weight: 400;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  float: right;
  transition: transform .25s linear;
  -webkit-transition: -webkit-transform .25s linear;
}

.panel-default>.panel-heading a[aria-expanded="true"] {
  background-color: #eee;
}

.panel-default>.panel-heading a[aria-expanded="true"]:after {
  content: "\2212";
  -webkit-transform: rotate(180deg);
  transform: rotate(180deg);
}

.panel-default>.panel-heading a[aria-expanded="false"]:after {
  content: "\002b";
  -webkit-transform: rotate(90deg);
  transform: rotate(90deg);
}

.accordion-option {
  width: 100%;
  float: left;
  clear: both;
  margin: 15px 0;
}

.accordion-option .title {
  font-size: 20px;
  font-weight: bold;
  float: left;
  padding: 0;
  margin: 0;
}

.accordion-option .toggle-accordion {
  float: right;
  font-size: 16px;
  color: #6a6c6f;
}

.accordion-option .toggle-accordion:before {
  content: "Expand All";
}

.accordion-option .toggle-accordion.active:before {
  content: "Collapse All";
}



.progress {
    height: 15px;
    margin-bottom: 15px;
    overflow: hidden;
    background-color: #f5f5f5;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
    box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
    
}



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
   color:#111111;
  
}


  
td {

   border: 1.8px solid #dddddd;
      max-width: 150px;
     font-size:12px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
}
td  .action{
 text-align: center;
    border: 1px solid #dddddd;
    max-width: 150px;
    font-size: 14px;
    white-space: normal; /* ขึ้นบรรทัดใหม่เมื่อเนื้อหาล้น */
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1.8px solid #d1d1d1;
 background-color: #f4f4f4;
     height: 30px;
       box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  border-top: 1px solid #ccc;
  border-left: 1px solid #ccc;
  border-right: 1px solid #ccc;

}





.fixhead{
    max-width: 150px;
      min-width: 80px;
       
}
.fixbody{

    text-align: left;
   border: 1.8px solid #d1d1d1;

     height: 30px;
       box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  border-top: 1px solid #ccc;
  border-left: 1px solid #ccc;
  border-right: 1px solid #ccc;
       
}

thead {
    position: sticky;
    top: 0;
 background-color: #f4f4f4;
    z-index: 1; /* ให้หัวตารางอยู่ด้านบนของเนื้อหา */
      background-color: #dcdcdc;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  
}

.fixhead2{
    position: sticky;
    top: 0;
  background-color: #f4f4f4;
    z-index: 1; /* ให้หัวตารางอยู่ด้านบนของเนื้อหา */
      background-color: #dcdcdc;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}



</style>
<style>
.card-app-design .design-planning-wrapper:not(:last-child) {
    margin-bottom: 1.3rem;
}
.card-app-design .design-planning-wrapper {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
}
.py-75 {
    padding-top: 0.75rem !important;
    padding-bottom: 0.75rem !important;
}

.card-app-design .design-planning-wrapper .design-planning:not(:last-child) {
    margin-right: 0.7rem;
}
.card-app-design .design-planning-wrapper .design-planning {
    padding: 0.5rem;
    margin-bottom: 0.7rem;
    text-align: center;
    background-color: #f8f8f8;
    border-radius: 0.357rem;
    min-width: 6.4rem;
}


</style>

</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>  







    <!-- BEGIN: Content-->
    <div class="app-content content ">
    
    <c:if test="${error == 'condition_null'  }">
    <div class="alert alert-danger mt-1 alert-validation-msg" role="alert">
                                            <div class="alert-body d-flex align-items-center">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-info me-50"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>
                                                <span>Error01 <strong> E-form </strong>.  <spring:message code="forms.error1"  /></span>
        </div>
          </div>
      </c:if>    
        <c:if test="${error == 'report_null'  }">
    <div class="alert alert-danger mt-1 alert-validation-msg" role="alert">
                                            <div class="alert-body d-flex align-items-center">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-info me-50"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>
                                                <span>Error02.<spring:message code="forms.error2"  /> </span>
        </div>
          </div>
      </c:if>  
      
              <c:if test="${error == 'user_null'  }">
    <div class="alert alert-danger mt-1 alert-validation-msg" role="alert">
                                            <div class="alert-body d-flex align-items-center">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-info me-50"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>
                                                <span>Error03. <spring:message code="forms.error3"  /></span>
        </div>
          </div>
      </c:if>
      
                                  
     <c:if test="${error == null  }">
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            
             <div style="float:left">
             
 
<c:if test="${group == 2  }">
              <a href="/duepn-filemanager?id=${back.upload_pn_id }&&group=${group }" class="btn-icon btn btn-primary btn-round btn-sm  waves-effect waves-float waves-light <c:if test="${back == null  }">disabled </c:if>"  >
   <i data-feather='arrow-left'></i> 
            </a>
            <a href="/pn-filemanager?group=2" class="btn-icon btn btn-primary btn-round btn-sm  waves-effect waves-float waves-light " 
            >
   <i data-feather='home'></i>
            </a>
            <a href="/duepn-filemanager?id=${next.upload_pn_id }&&group=${group }" 
            class="btn-icon btn btn-primary btn-round btn-sm  waves-effect waves-float waves-light <c:if test="${next == null  }"
            >disabled </c:if>" >
    <i data-feather='arrow-right'></i>
            </a>

            <br><br>
            &nbsp;&nbsp;
             </c:if>
&nbsp;&nbsp;

              
<c:if test='${USER_GROUP =="guest"}'>  
<div class="avatar bg-light-primary p-50 m-0" style="clear:both;">


                                        <div class="avatar-content"  title="guest" >
                                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14"viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users font-medium-5"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
                                        </div>
                                        
                                   
 </div>
</c:if>        

<c:if test='${USER_GROUP =="Inspector"}'>      

<div class="avatar bg-light-info p-50 m-0" style="clear:both;">
          <div class="avatar-content"  title="Inspector : ${Member_name }" >
    <c:if test="${user_img != null}">   
                                  <img class="round" src="/avatar/${user_img}" alt="avatar" height="55" width="55" >
     </c:if>     
         <c:if test="${user_img == null}">   
                                  <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14"viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users font-medium-5"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
                                      
     </c:if>                         
                           
                     </div>                              
                                
 
                                        
                                   
 </div>


         

 </c:if>        
 <c:if test='${USER_GROUP =="uploader" ||  USER_GROUP =="admin"}'> 
 
 
<div class="avatar bg-light-success p-50 m-0" style="clear:both;">
          <div class="avatar-content"  title="${Member_name }" >
    <c:if test="${user_img != null}">   
                                  <img class="round" src="/avatar/${user_img}" alt="avatar" height="55" width="55" >
     </c:if>     
         <c:if test="${user_img == null}">   
                                  <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14"viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users font-medium-5"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
     </c:if>                         
                           
                     </div>                              
                                
 
                                        
                                   
 </div>
  </c:if> 
  
 
                                
           </div>  
            <div class="content-body">
            
           
            
            
                  <!-- Detailed Email View -->
                        <div class="email-data-list" style="display:block;z-index:999;width:100%">
                            <!-- Detailed Email Header starts -->
                          

                            <!-- Detailed Email Content starts -->
                            <div class="email-scroll-area">
                              
                      
                                <div class="row">
                                    <div class="col-12">
                                    
                                    
                                     
                                        <div class="card card-app-design" >
                                        
                                            <div class="card-header email-detail-head">
                                            
                                           
                                                <div class="user-details d-flex justify-content-between align-items-center flex-wrap">
   
                                                    <div class="mail-items">
                                                        <h4 class="mb-0 " >${upload_pn.upload_pn_note } </h4> 
                                      
                                                   </div>
                                       

      
                                                </div>
                          
                                                        
                                                
                                            </div>
           

                                         <div class="card-body"  id="statusreports"  style="display:none">
             <label  style="color:black;min-width:100px;"><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /> :</label>
                                                      <small  style="color:black" class="ellipsis">${MyGoal.responsible }  </small> 
                                                     
           
              
            
							    
							
                                
                                    <div class="tab-content " >
                                    
  <style>
  ..nav-tabs{
   border: 2px;
    /* ไม่ต้องมีเส้นขอบด้านล่าง */
  }
.accordion-button{
     background-color: #f2f2f2;
    display: block;
    padding: 10px 15px;
    font-weight: bold;
    border-bottom: none; /* ไม่ต้องมีเส้นขอบด้านล่าง */
}

.accordion-item{
    border: 1px solid #dddddd;
    border-top: none; /* ไม่ต้องมีเส้นขอบด้านบน */
   border: 1px solid #dddddd;
}
 
.accordion-button2 {
    background-color: #f2f2f2;

    font-weight: bold;
 
  
}

.accordion-item2 {

    border-radius: 5px; /* ทำมุมของ accordion item */
}
.tabs {
	display: flex;
	flex-wrap: wrap; 
	margin:0px;
	padding:0px;
}
.tabs label {
    background-color: #eff0f2;
   border-bottom: 1px solid #fff;
    color: #888;
    font-weight: 500;
    display: block;
    letter-spacing: 0;
    outline: none;
    padding: 0 20px;
    text-decoration: none;
    -webkit-transition: all 0.2s ease-in-out;
    -moz-transition: all 0.2s ease-in-out;
    transition: all 0.2s ease-in-out;
    cursor: pointer;

}
.tabs .tab {
  order: 99; // Put the tabs last
  flex-grow: 1;
	width: 100%;
	display: none;
  padding: 0px;
  margin:0px;
  background: #fff;
}
.tabs input[type="radio"] {
	display: none;
}
.tabs input[type="radio"]:checked + label {
	
    background:#fff;
    cursor: no-drop;
}
.tabs input[type="radio"]:checked + label + .tab {
	display: block;
}

@media (max-width: 45em) {
  .tabs .tab,
  .tabs label {
    order: initial;
  }
  .tabs label {
    width: 100%;
    margin-right: 0;
    margin-top: 0.2rem;
  }
}

.accordion-body{

pading:0px
}
.accordion-collapse {
  padding: 0px;
  margin:0px;
}
.accordion-item{
  padding: 0px;
  margin:0px;

}
.accordion-body{
  padding: 0px;
  margin:0px;
}

.accordion .accordion-body {
    padding-top: 0px;
}

.powers {
    background: #fff;
    padding: 10px;
    width: 100%;
    height: 40px;
    overflow: hidden;
    border-bottom: 1px solid #eee;
    margin-bottom: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* เพิ่มเงา */
    
}
.accordion-button{
	 background-color:#7367f0;
	 color:#fff;
} 



.table-responsive::-webkit-scrollbar {
  width: 16px;
}
.table-responsive::-webkit-scrollbar-thumb {
  background-color: #7b7d7f;
  border: 5px solid #fff;
  border-radius: 10rem;
}
.table-responsive::-webkit-scrollbar-track {
  position: absolute;
  right: -3rem;
  top: -50rem;
  background: transparent;
}


  </style>
 <div class="accordion accordion-border" id="accordionBorder" data-toggle-hover="true">
    <div class="accordion-item">
  
        <div id="accordionBordertree" >
  <div class="accordion-body" >
            <div class="tabs">
	  
			
  <input type="radio" name="tabs" id="tabone" checked="checked">
  <label for="tabone"></label>


  <div class="tab">
  
             <div class="powers" style="padding:4px"  >
                                                             

<a href="/allusertask-filemanager/${id }"  class="btn btn-light btn-sm  waves-effect waves-float waves-light " style="float:left;margin:0;">
                            <i data-feather='chevron-left'></i>
                                </a>  
                     <div style="float:right;">
         <div class="btn-group btn-group-sm  mt-md-0 mt-1" role="group" aria-label="Basic radio toggle button group" style="padding-left:5px;" >
                                        <input type="radio" class="btn-check" name="radio_options" id="radio_option1" autocomplete="off" checked="" onchange="sw('chartdiv')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_option1"><spring:message code="tables.target2"  /> (${Respon.isUploads })</label>

                                        <input type="radio" class="btn-check" name="radio_options" id="radio_option2" autocomplete="off" onchange="sw('chartdiv2')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_option2"> <spring:message code="tables.target3"/> (${Respon.isUploadsLast })</label>

                                        <input type="radio" class="btn-check" name="radio_options" id="radio_option3" autocomplete="off" onchange="sw('chartdiv3')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_option3">  <spring:message code="tables.target4" /> (${Respon.missedUploads })</label>
                                        
                                       
                                    </div>
</div>
</div>   	
  
  
  
    <div id="accordionBorderOne" style="display:none">
             <div class="accordion-body">

                </div>
        </div>
  
   <div class="table-responsive" id="chartdiv" style="max-height:700px;min-height:500px;" >
                                      
                                                  <table id="tabledataes" class="tabledata" style="height:auto">
                                    <thead>
                                        <tr >  
                                         <th class="form-label  " >#</th>
                                          <th class="form-label fixhead " ><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /></th>
                                                   <th class="form-label fixhead " >   <spring:message code="tables.date_upload"  /></th>


                                              
                                                    
                                        </tr>
                                        </thead>
                                             <tbody>
                                              <c:forEach items="${ListIsUploads }" var="row" varStatus="count">     
                                               <tr > 
                                                 <td class="form-label fixbody "  style="    text-align: center;">${count.count } </td>
                                                 <td class="form-label fixbody "  style="    text-align: left;">${row.user_name }</td>
                                                 <td class="form-label fixbody " style="    text-align: center;">${row.upload_date } ${row.upload_time }</td>
                                        
                                                </td>
                                         	 </tr>
                                         	 </c:forEach>
                                          </tbody>
     						    </table>
                            
                                              
                                          </div>
                   <div class="table-responsive" id="chartdiv2" style="max-height:700px;min-height:500px;display:none" >
                                      
                                                  <table id="tabledataes" class="tabledata" style="height:auto">
                                    <thead>
                                        <tr >  
                                         <th class="form-label  " >#</th>
                                          <th class="form-label fixhead " ><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /></th>
                                                   <th class="form-label fixhead " ><spring:message code="tables.date_upload"  /> ( <spring:message code="tables.target3"/>)</th>


                                              
                                                    
                                        </tr>
                                        </thead>
                                             <tbody>
                                              <c:forEach items="${ListIsUploadAll }" var="row" varStatus="count">     
                                               <tr > 
                                                 <td class="form-label fixbody "  style="    text-align: center;">${count.count }</td>
                                <td class="form-label fixbody "  style="    text-align: left;">${row.data_nameadd }</td>
                                                 <td class="form-label fixbody " style="    text-align: center;">${row.data_dateadd }</td>
                                        
                                                </td>
                                         	 </tr>
                                         	 </c:forEach>
                                          </tbody>
     						    </table>
                            
                                              
                                          </div>                       
                            <div class="table-responsive" id="chartdiv3" style="max-height:700px;min-height:500px;display:none" >
                                      
                                                  <table id="tabledataes" class="tabledata" style="height:auto">
                                    <thead>
                                        <tr >  
                                         <th class="form-label  " >#</th>
                                          <th class="form-label fixhead " ><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /></th>
                                                   <th class="form-label fixhead " ><spring:message code="tables.date_missedupload"  /></th>


                                              
                                                    
                                        </tr>
                                        </thead>
                                             <tbody>
                                              <c:forEach items="${ListMissedUploads }" var="row" varStatus="count">     
                                               <tr > 
                                                 <td class="form-label fixbody "  style="    text-align: center;">${count.count }</td>
                                                 <td class="form-label fixbody "  style="    text-align: left;">${row.user_name }</td>
                                                 <td class="form-label fixbody " style="    text-align: center;">${row.missed_due }</td>
                                        
                                                </td>
                                         	 </tr>
                                         	 </c:forEach>
                                          </tbody>
     						    </table>
                            
                                              
                                          </div>
                                         
                                         </div>
         
</div>
  
   		

  <label for="tabtwo"></label>

  
  
  <div class="tab">

                                  

 </div>

  
  
   
  
</div>
            </div>
        </div>
    </div>
    
   
 
     
</div>

       
                                    
                                   


           
                                      
                                      
                                        </div>
                                        
                                    </div>
                                         
                                         
                                         </div>
                                     
                                            
                                            
                                        </div>
                                        
                                           
                                    </div>
                                </div>
                             
                            </div>
                            <!-- Detailed Email Content ends -->
                        </div>
                        <!--/ Detailed Email View -->
                      
                      
                      


            </div>
           </c:if>    
        
        </div>
    </div>
    <!-- END: Content-->



      
<div id="savestatus"></div>
<%@ include file="../common/mainfoot.jspf" %>  




  <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/legacy.js" />"></script>
  <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.date.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>
 <script src="<c:url value="/resources/app-assets/js/scripts/forms/pickers/form-pickers.js" />"></script>
    <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>


 <script src="<c:url value="/resources/app-assets/js/scripts/extensions/ext-component-drag-drop.js" />"></script>
<script src="<c:url value="/resources/app-assets/vendors/js/extensions/dragula.min.js" />"></script>
 	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	  

<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'>  </script>  
	  
	  
	  
	    <script>  
	    <c:if test="${group == 1  }">
	    var element = document.getElementById("project");
	    element.classList.add("active");  
	    </c:if>
	    <c:if test="${group != 1  }">
	    var element = document.getElementById("nav_dar5");
	    element.classList.add("active");  
	    </c:if>
	    
 $('#headingBordertree .accordion-button').click(function (e) {
	    	
	    	
	    	$("#accordionBordertree").toggle();

	    	
	    });
	    
	    
	    $('.accordion2').click(function (e) {
	    	
	    	
	    	$("#accordionBorderOne").toggle();

	    	
	    });
	    
    $('#headingBorderFour .accordion-button').click(function (e) {
	    	
	    	
	    	$("#accordionBorderFour").toggle();

	    	
	    });   
	    
	    

	    function getdatas(url,div,type){
	    
	    	var status = 0;
	    	
	    	
	    	if(type == 1){
	    		if (confirm("คุณต้องการดำเนินการ?"))
				{
	    			status=1;
				}
	    	}else{
	    		status=1;
	    	}
	
				
				if(status == 1){
					  $.ajax({
			    	        type:'GET',
			    	        url:url,
			    	        data: {ID : 0},
			    	        
			    	     success: function(data){
			    	    $("#"+div).html(data);	
		
			    	     }
			    	});
					
				}
				    	  
			
			
			
			return false;
			
	}
	    
	    
	    function getdatasselect(url,div){
		    
		
	    	alert(url)
					  $.ajax({
			    	        type:'GET',
			    	        url:url,
			    	        data: {ID : 0},
			    	        
			    	     success: function(data){
			    	    $("#"+div).html(data);	
		
			    	     }
			    	});
					

			return false;
			
	   }
	    
	    
	    
	    
	    
	    function reject(id){
	    	$("#idreject").val(id);
	    }
	    
	    function app(id){
	    	$("#idapp").val(id);
	    }
	    
	    
	    function postdata(Mode,Mode2,Mode3) {

		    var formData = new FormData($("#"+ Mode2)[0]);

				    $.ajax({
				        url: Mode,
				        type: "POST",
				        data: formData,
				        processData: false,
				        contentType: false,
					    success: function(result){
					    $('#' + Mode3).html(result);
					   
				        evaluateAndDisplay();
				        evaluateAndDisplay2();
				        sum();
					       }
					    });
				

				    $("#"+Mode2)[0].reset();
				   
		    return false;
		    }	
	    
	    
	    
	    
	    function postdatacal(Mode,Mode2,Mode3) {

		    var formData = new FormData($("#"+ Mode2)[0]);

				    $.ajax({
				        url: Mode,
				        type: "POST",
				        data: formData,
				        processData: false,
				        contentType: false,
					    success: function(result){
					    $('.' + Mode3).html(result);
					       },
					    error: function(jqXHR, textStatus, errorThrown) {
					            if (jqXHR.status === 401) {
					                // Redirect to login page if unauthorized
					                window.location.href = '/login'; // URL ของหน้า login
					     }}
					    });
				

			
				   
		    return false;
		    }	
	    
	    
	    function uploadfile(Mode,Mode2,Mode3) {
	    	
	    	var due = $("#fp-default").val();
	    	var edit = $("#edit").val();
	    	$("#reserve").html("");
	    	
	    	<c:if test="${eformfields2 != null  }">   
	    	
	    	
		    	if(edit == 0){
				    	alert("ฟอร์มนี้รองรับเเต่การเเก้ไขข้อมูลเท่านั้น");
			    		return false;
		    	}
	    	</c:if>
	    	
            <c:if test="${upload_pn.upload_pn_filecheck == 1}"> 
            
	       	 var fileInput1 = $('#files');
	    	 var fileInput2 = $('#files2');
	    	 var fileInput3 = $('#files3');
	    	 var fileInput4 = $('#files4');
	    	 var fileInput5 = $('#files5');
    	 
	    	 
	    	    if (fileInput1.length) { 
	    	   
	    	    	if(!fileInput1.val() ){
				    	alert("Please select a file to upload");
			    		return false;
		    		}
	    	    }
	    	    
	    	    
			  if (fileInput2.length) { 
				    	    	
				    	    	if(!fileInput2.val() ){
							    	alert("Please select a file to upload");
						    		return false;
					    		}
			  }
			  
			  
			  if (fileInput3.length) { 
			  	
			  	if(!fileInput3.val() ){
				    	alert("Please select a file to upload");
			  		return false;
					}
			  }
			  
			  
			  if (fileInput4.length) { 
			  	
			  	if(!fileInput4.val() ){
				    	alert("Please select a file to upload");
			  		return false;
					}
			  }
			  
			  
			  if (fileInput5.length) { 
			  	
			  	if(!fileInput5.val() ){
				    	alert("Please select a file to upload");
			  		return false;
					}
			  }
	    	 
	    	 	
            </c:if>
	    
	    	 
	    	if(due.trim() === ""){
	    		alert("กรอกวันที่ด้วย");
	    		return false;
	    	}
	    	
	    
	    	
	    	
	    	 var button = document.getElementById("submits");
	           button.disabled = true;

	         
	    var formData = new FormData($("#"+ Mode2)[0]);
	    var $fileUpload = $("input[type='file']");

	        if (parseInt($fileUpload.get(0).files.length)>20){
	            alert("You can only upload a maximum of 20 files");
	    		return false;
	    	
	        }
	    $.ajax({
	        url: Mode,
	        type: "POST",
	        data: formData,
	           xhr: function() {
	                    var myXhr = $.ajaxSettings.xhr();
	                    if(myXhr.upload){
	                        myXhr.upload.addEventListener('progress',progress, false);
	                    }
	                    return myXhr;
	            },
	    processData: false,
	    contentType: false,
	    success: function(result){
	    $('#' + Mode3).html(result);
       
	    
	   //evaluateAndDisplay();
       // evaluateAndDisplay2();
      //  sum();
	    
	    //applyFilters();
	    
        button.disabled = false;
	       }
	    });


	    return false;
	    }	


	    
	    

	    function progress(e){

	        if(e.lengthComputable){
	            var max = e.total;
	            var current = e.loaded;

	            var Percentage = (current * 100)/max;
	          //  console.log(Percentage);
	    $("#progress_div").css("display", "block");	
	    $("#bar").css("width",Percentage + "%");
	    var Per = Math.round(Percentage);
	    $('#percent').html(Per + "%");

	            if(Percentage >= 100)
	            {
	    $("#progress_div").css("display", "none");
	            }
	        }  
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	   function getdata(url,id){
	
		   unedit();
		 
		 var result = url.split("/")[1]; 
		 var data = ""; 
		  
		 if(result == "editdocument-filemanager"){
			   data="คุณต้องการเเก้ไขรายการนี้ใช่หรือไม่?"
		   }else if(result == "appdocument-filemanager"){
			   data="คุณต้องการอนุมัติรายการนี้ใช่หรือไม่?"
		   }else{
			   data="คุณต้องการลบรายการนี้ใช่หรือไม่?" 
		   }
			   
			 if ( confirm(data) ) {
	
	
					        $.ajax({
					        type:'GET',
					        url:url,
					        data: {ID : id},
					        
					     success: function(data){
					    $("#savestatus").html(data);	
					    evaluateAndDisplay();
				        evaluateAndDisplay2();
				        sum();
					     },
					        error: function(jqXHR, textStatus, errorThrown) {
					            if (jqXHR.status === 401) {
					                // Redirect to login page if unauthorized
					                window.location.href = '/login'; // URL ของหน้า login
					            }      
					        }   
					});
			}
			 
			
			 
			 
		return false;
	    
		}
 
	   function getdata2(url,id){
					        $.ajax({
					        type:'GET',
					        url:url,
					        data: {ID : id},
					        
					     success: function(data){
					    $("#savestatus").html(data);	

					     }
					});
			
			 
			
			 
			 
		return false;
	    
		}
 
	    </script>
	  
	  
	  
    <script>
    
    
    
   
  
    
    
   document.getElementById("statusreports").style.display = "block";
    document.getElementById("eform").style.display = "block";
    // ซ่อน id statusalls

    document.getElementById("statusalls2").style.display = "none";
    document.getElementById("statusdocs").style.display = "none";
    document.getElementById("echarts").style.display = "none";
  


    
    
    $("#status1").html("${status1}");
    $("#status2").html("${status2}");
    $("#status3").html("${status3}");
    
    if(${status1 + status2 + status3}  > 0 ){
    	$("#s-upload").show(); 
    }else{
    	 $("#s-success").show();
    }
    
    
    <c:if test="${group != 1  }">
    document.getElementById("statusreports").style.display = "block";
    document.getElementById("eform").style.display = "block";
    // ซ่อน id statusalls

    document.getElementById("statusalls2").style.display = "none";
    document.getElementById("statusdocs").style.display = "none";
    document.getElementById("echarts").style.display = "none";
  
 //   evaluateAndDisplay();
   // evaluateAndDisplay2();
  //  sum();
</c:if>

    <c:if test="${group == 1  }">

    document.getElementById("statusalls2").style.display = "block";
    // ซ่อน id statusdocs
     document.getElementById("eform").style.display = "none";
    document.getElementById("statusdocs").style.display = "none";
    document.getElementById("statusreports").style.display = "none";
   </c:if>


    
    
    
    
    
    
    
    
    

    
	   <c:if test="${upload_pn.upload_pn_type != 2}"> 
    // เมื่อคลิกที่ id statusdoc
    document.getElementById("statusdoc").addEventListener("click", function() {
        // เปลี่ยนแสดง id statusdocs
        document.getElementById("statusdocs").style.display = "block";
        
        // ซ่อน id statusalls
         document.getElementById("eform").style.display = "none";
        document.getElementById("statusalls").style.display = "none";
        document.getElementById("statusalls2").style.display = "none";
        document.getElementById("statusreports").style.display = "none";
    });
    
    
    

    
    </c:if>
    
    

    
    
    
    

    
    
    
    $('.trigger').click(function(e){
      e.preventDefault();
      
      var childUl = $(this).siblings("ul.tree-parent");
      
      if( childUl.hasClass('open') ){
        
        childUl.removeClass('open');
      } else {
        
        childUl.addClass('open');
      }
      
    });
    
    
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
 <script src="<c:url value="/lib/xlsx.full.min.js" />"></script> 

 <script>

			const download4 = type => {

				  const el = document.getElementById("tabledataes");
				  const file = XLSX.utils.table_to_book(el);
				  // Configuring width of the columns:
				  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
				  return XLSX.writeFile(file, "UPLOAD." + type);
				};		
			
			
</script>


  <script>
  
  
  function changeRowColor(linkElement) {
	    var rowbodies = document.querySelectorAll('.rowbody');
	    
	    // กำหนด event listener สำหรับทุก .rowbody
	    rowbodies.forEach(function(rowbody) {
	        rowbody.addEventListener('click', function(event) {
	            rowbodies.forEach(function(body) {
	                body.style.backgroundColor = 'transparent'; // กำหนดสีพื้นหลังเป็น透วสีทั้งหมด
	                
	                body.classList.remove('selected-border');
	            });
	           // row.style.backgroundColor = 'rgba(0, 0, 255, 0.5)'; // เปลี่ยนสีพื้นหลังของ .rowbody ที่ถูกคลิกเป็นสีเหลือง
	            row.classList.add('selected-border');
	        });
	    });
	    
	    // หา <tr> ที่ประกอบไปด้วยลิงก์ที่ถูกคลิก
	    var row = linkElement.closest('tr');
	    
	    // กำหนดสีพื้นหลังของ <tr> ที่ถูกคลิกเป็นสีเหลือง
	    row.style.backgroundColor = '#F5F5F5';
	}
  
  
  
</script>
  
  


<div class="modal modal-slide-in sidebar-todo-modal fade show" id="new-task-modal"  aria-modal="true" role="dialog">
                            <div class="modal-dialog sidebar-lg">
                                <div class="modal-content p-0">
                                    <form method="POST"  id="cmform" name="cmform">
                                        <div class="modal-header align-items-center mb-1">
                                            <h5 class="modal-title">Comment</h5>
                                            <div class="todo-item-action d-flex align-items-center justify-content-between ms-auto">
                                               
                                                 <input type="hidden" name="cm_idrefer" value="${id}">
                                                <input type="hidden" name="type" value="upload_pn">
                                                
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x cursor-pointer" data-bs-dismiss="modal"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
                                            </div>
                                        </div>
                                        <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                            <div class="action-tags">
                                                <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label">กรอกข้อความข้อคุณ</label>
                                                    <textarea type="text" name="cm_title" id="todoTitleAdd" name="todoTitleAdd" class="new-todo-item-title form-control" ></textarea>
                                                </div>
                                          
                                             
                                          
                                            </div>
                                            <div class="my-1">
                                                <button type="button" onclick='postdata("/addcm-filemanager?${_csrf.parameterName}=${_csrf.token}","cmform","cmdiv")' class="btn btn-primary add-todo-item me-1 waves-effect waves-float waves-light">Add</button>
                                                <button type="button"     class="btn btn-outline-secondary add-todo-item waves-effect" data-bs-dismiss="modal">
                                                    Cancel
                                                </button>
                                               
                                             
                                            </div>
                                        </div>
                                    </form>
                                      <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                    <div class="tab-content" id="myTabContent">
                                      
                                            <div class="tab-pane fade active show" id="activity-tab" role="tabpanel" aria-labelledby="activity-tab">
                                                <h6 class="file-manager-title my-2">วันนี้</h6>
                                                
                                   <div id="cmdiv">
                                                
                                    <c:forEach items="${listcms2 }" var="row" varStatus="count">
                                   <div class="d-flex align-items-center mb-2" id="cm-${row.cm_id }">
                                                    <div class="avatar avatar-sm me-50" >
                                                        <img src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="avatar" width="28">
                                                    </div>
                                                    <div class="more-info" style="width:90%">
                                                        <p class="mb-0">
                                                            <span class="fw-bold">${row.cm_user_name }</span> <span class="fw-bold"> ${cm_date } </span>
                                                            <br>
                                                            ${row.cm_title }
                                                       
                                                        </p>
                                                    </div>
                                                       <button type="button" onclick="return getdata('/delcm-filemanager/${row.cm_id}');"  class="btn btn-sm btn-outline-danger update-btn waves-effect ">
                                                    Delete
                                                </button>
                                          </div>


                               </c:forEach>
                                 </div>
                                              
                                              
                                                <h6 class="file-manager-title mt-3 mb-2">ความคิดเห็นทั้งหมด</h6>
                                            <c:forEach items="${listcms1 }" var="row" varStatus="count">
                                   <div class="d-flex align-items-center mb-2" id="cm-${row.cm_id }">
                                                    <div class="avatar avatar-sm me-50" >
                                                        <img src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="avatar" width="28">
                                                    </div>
                                                    <div class="more-info" style="width:90%">
                                                        <p class="mb-0">
                                                            <span class="fw-bold">${row.cm_user_name }</span> <span class="fw-bold"> ${cm_date } </span>
                                                            <br>
                                                            ${row.cm_title }
                                                       
                                                        </p>
                                                    </div>
                                                       <button type="button"   onclick="return getdata('/delcm-filemanager/${row.cm_id}/${row.type} }');"  class="btn btn-sm btn-outline-danger update-btn waves-effect ">
                                                    Delete
                                                </button>
                                          </div>


                               </c:forEach>
                                       
                                             
                                            </div>
                                        </div>
                                            </div>
                                </div>
                            </div>
                        </div>


















  
  
  
  
                   
  
  
  
  
  
  
  
  

                   
  
  
  
  
  
  
  
  
  
  
  
  
  

  
  











<script>


   
   function search() {
	    var filter = $('.search_id').val(); // รับค่าจากช่องSearch

	    
	    var regex = new RegExp(filter, 'ig'); // สร้าง regex สำหรับการSearch

	    // Searchแถวในตารางที่มีคลาส .datatable และซ่อน/แสดงแถวที่ตรงกับการSearch
	    $('#tabledataes tbody tr').each(function() {
	        var rowText = $(this).text(); // ดึงข้อความของแถวนี้
	        if (regex.test(rowText)) {
	            $(this).show(); // แสดงแถวที่ตรงกับการSearch
	        } else {
	            $(this).hide(); // ซ่อนแถวที่ไม่ตรงกับการSearch
	        }
	    });
	}

   
   

   function  sw(div){
		$("#chartdiv").hide();
		$("#chartdiv2").hide();
		$("#chartdiv3").hide();

		$("#"+div).show();
	}

   
   
</script> 


   
    	   <script>
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
    </script>