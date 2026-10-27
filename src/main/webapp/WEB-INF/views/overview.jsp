<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="common/mainhead.jspf" %>    

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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

 
<title>Overview</title>

<style>

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
    text-align: center;
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
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  







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


  <c:set var="user_status" value="0" />
  <c:set var="listcheck" value="${Member_name}" />
                                   
                                   
                                    <c:set var="list" value="${upload_pnsall.upload_pn_app}" />                    
                                          <c:if test='${fn:contains(list,listcheck)}'>
                                           <c:set var="user_status" value="2" />
                                        </c:if>
                                        
                                          <c:if test='${user_status == 0}'>  
		                                           <c:set var="list" value="${upload_pnsall.upload_pn_respon}" />
		                                          <c:if test='${fn:contains(list,listcheck)}'>
		                                            <c:set var="user_status" value="1" />
		                                        </c:if>
                                        </c:if>  
<c:if test='${user_status == 0}'>  
<div class="avatar bg-light-primary p-50 m-0" style="clear:both;">


                                        <div class="avatar-content"  title="guest" >
                                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14"viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users font-medium-5"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
                                        </div>
                                        
                                   
 </div>
</c:if>        

<c:if test='${user_status == 2}'>               
<div class="avatar bg-light-info p-50 m-0" style="clear:both;">

              
      <div class="avatar-content"  title="Inspector" >
                                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14"viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users font-medium-5"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
                                        </div>
                                   
 </div>
 </c:if>        
 <c:if test='${user_status == 1}'> 
<div class="avatar bg-light-success p-50 m-0" style="clear:both;">


                                     <div class="avatar-content"  title="uploader" >
                                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14"viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users font-medium-5"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
                                        </div>
 
                                        
                                   
 </div>
  </c:if> 
  
    <c:set var="list" value=" ${upload_pnsall.upload_pn_respon}" />
                                           
                                               <c:set var="listcheck" value="${Member_name}" />
                                          <c:if test='${fn:contains(list,listcheck)}'>
  
  	    <c:if test="${group != 1  }">
   <c:if test="${upload_pn.turn == 0}">  
  <br> <br>
          	 <a href="/turnin-filemanager/${upload_pn.upload_pn_id}"  class="btn btn-sm btn-primary" >
                                    Turn In
                                </a>   
        </c:if>                        
                                
       <c:if test="${upload_pn.turn == 1}">                                                       
                      <br> <br>              
<a href="/undoturn-filemanager/${upload_pn.upload_pn_id}"  class="btn btn-sm btn-primary" >
                                  Undo Turn
                                </a>  
      </c:if>                     
                  
           </c:if>              
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
                                                        <h4 class="mb-0 " >${upload_pn.upload_pn_note }</h4>
                                                  <br>
                                                        <div class="email-info-dropup dropdown">
                                                            <span role="button"   style="color:black">
                                                               <label  style="color:black;min-width:150px;">Schedule :</label>
                                                                 <c:if test="${upload_pn.upload_pn_type == 0}">    
                                                             <spring:message code="due.type1" text="กำหนดตามช่วงเวลา"  />
                                                         </c:if>  
                                                          <c:if test="${upload_pn.upload_pn_type == 2}">    
                                                             <spring:message code="due.type3" text="ไม่มีกำหนด"  />
                                                         </c:if>  
                                                  
                                                       <c:if test="${upload_pn.upload_pn_type == 0}">        
                                                     
                                              
                                                         
                                                       <spring:message code="tables.due" text="ความถี่การอัพโหลด" />  :  <c:if test="${upload_pn.upload_pn_due == 0}">    
                                                             <spring:message code="due.date" text="ทุกวัน"  />
                                                         </c:if>  
                                                      <c:if test="${upload_pn.upload_pn_due == 1}">    
                                                 			 <spring:message code="due.week" text="ทุกอาทิตย์" /> 
                                                 			 
                                      
										<c:if test="${upload_pn.upload_pn_type != '0' }">    
                                            <c:if test='${upload_pn.getUpload_pn_setdue().equals("W-1") }'>    
                                             (MONDAY)
                                          	</c:if> 
                                          	  <c:if test='${upload_pn.getUpload_pn_setdue().equals("W-2") }'>   
                                             (TUESDAY)
                                          	</c:if> 
                                          
                                         <c:if test='${upload_pn.getUpload_pn_setdue().equals("W-3") }'>   
                                             (THURSDAY)
                                          	</c:if> 
                                          
                                          <c:if test='${upload_pn.getUpload_pn_setdue().equals("W-4") }'>     
                                             (WEDNESDAY)
                                          	</c:if> 
                                          
                                            <c:if test='${upload_pn.getUpload_pn_setdue().equals("W-5") }'>    
                                             (FRIDAY)
                                          	</c:if> 
                                          
                                            <c:if test='${upload_pn.getUpload_pn_setdue().equals("W-6") }'>    
                                             (SATURDAY)
                                          	</c:if> 
                                          
                                           <c:if test='${upload_pn.getUpload_pn_setdue().equals("W-7") }'>   
                                             (SUNDAY)
                                          	</c:if> 
                                      </c:if> 
                                                 		
                                                 			 
                                                         </c:if>  
                                                           <c:if test="${upload_pn.upload_pn_due == 2}">    
                                                             <spring:message code="due.month" text="ทุกเดือน"  /> (  DATE ${upload_pn.upload_pn_setdue })
                                                         </c:if> 
                                                             </c:if> 
                                                        
                                                       
                                                          
                                                      <c:if test="${upload_pn.upload_pn_type == 1}">    
                                                             <spring:message code="due.type2" text="กำหนดตามวันที่" />
                                                                    <c:if test="${upload_pn.upload_pn_datedue != null}">    
                                                         ${upload_pn.upload_pn_datedue }
                                                         </c:if> 
                                                         </c:if>  
                                                          
                                                      
                                                          
                                                          
                                                          
                                                            </span><br>
                                                              <label  style="color:black;min-width:150px;">Location :</label>
                                                      <small  style="color:black">${upload_pn.upload_pn_storage}</small><br>
                                                        
                                                        
                                                        
                                             
                                                         
                                                       <label  style="color:black;min-width:150px;"><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /> :</label>
                                                      <small  style="color:black">${upload_pn.upload_pn_respon }</small>
                                                       <br>
                                                                 <label  style="color:black;min-width:150px;"> <spring:message code="tables.inspector" text="ผู้ดูเเล" /> :</label>
                                                      <small  style="color:black">${upload_pn.upload_pn_app }</small>
                              
                                                       
                                                             <br>
                               <label  style="color:black;min-width:150px;">
                               <spring:message code="tables.status" text="สถานะ" /> :</label>
                               <small  style="color:black">
                                  <c:if test="${upload_pn.upload_pn_end ==  1 }">  
                                                              <span class="badge  bg-success "><spring:message code="due.success"  /></span>
                                                        
                                                    </c:if>
                                                     <c:if test="${upload_pn.upload_pn_end == 0 }">  
                                                     <span class="badge  bg-warning "><spring:message code="due.process"  /></span>
                                                                                 
                                                    </c:if>
                               </small>            
                                         
                                         
                                                                 <br>
                               <label  style="color:black;min-width:150px;">
                               <spring:message code="project.info" text="ข้อมูลโครงการ" /> :</label>
                               <small  style="color:black">
                              ${upload_pn.project_info}
                               </small>    
                                                     
                                         
                                         
                                                       
                                                        </div>
                                                        
                                                        
                                                        
                                                        
                                                    </div>
                                                </div>
                                                <div class="mail-meta-item  align-items-top" style="height:150px">
                    
    
    
     
  <c:if test='${condition != null}'>
  <a href="/relationplan-condition/${upload_pn.upload_pn_id}" class="btn btn-sm btn-light waves-effect waves-float waves-light" style="float:right;margin-right:10px;"target="_blank"   
              onclick="openCenteredWindow(this.href, 'File Window', 1500, 600);  return false;"
              title="<spring:message code="common.Relationview"  />" ><i data-feather='share-2'></i> </a>   
</c:if>

        <c:set var="list" value=" ${upload_pnsall.upload_pn_respon}${upload_pnsall.upload_pn_app}" />
                        
                        
                    
                        
                                           
                                               <c:set var="listcheck" value="${Member_name}" />
                                               
                                               
     <c:set var="list" value=" ${upload_pnsall.upload_pn_respon}${upload_pnsall.upload_pn_app}" />
                                           
                                               <c:set var="listcheck" value="${Member_name}" />
                                 
							   
							
				   
					                                 <a href="#"  class="btn btn-light  btn-sm waves-effect waves-float waves-light "  data-bs-toggle="modal" data-bs-target="#new-task-modal" 
    style="float:right;margin-right:10px;margin-bottom:2px;">
  <spring:message code="btn.comment"  /><span id="comment"> ( ${listcms2.size() } )</span> </a>	  		          				
  
                  <c:if test='${access == 1 || upload_pn.upload_pn_private == 0 }'>
                            <button type="button"   data-bs-toggle="modal" data-bs-target="#addNewCard4"
                             class="btn btn-sm btn-light me-1 waves-effect waves-float waves-light" style="float:right" 
                             title="<spring:message code="common.setting"  />">
                                  <i data-feather='settings'></i>
                                    </button>
                                    
                            
                          
                                    
                                    
                               </c:if>      
   
                                                </div>
                                                
                                                
                                                
                                            </div>
                                            
                                            
                                        <div  id="statusalls">      
                                            <div class="card-body mail-message-wrapper">
                              
								   <c:if test="${upload_pn.upload_pn_type != 2}">    
		                                   <div class="design-planning-wrapper" style="float:left;display:none">
		                                        <div class="design-planning  bg-light-primary" id="statusdoc">
		                                            <p class="card-text mb-25"><spring:message code="status.wait" text="รออัพโหลด" /></p>
		                                            <h6 class="mb-0" id="status1"></h6>
		                                        </div>
		                                        <div class="design-planning  bg-light-primary">
		                                            <p class="card-text mb-25"><spring:message code="status.waitingtocheck" text="รอตรวจ" /></p>
		                                            <h6 class="mb-0" id="status2"></h6>
		                                        </div>
		                                     <div class="design-planning  bg-light-primary">
		                                            <p class="card-text mb-25"><spring:message code="status.Pendingrevisions" text="รอเเก้ไข" /></p>
		                                            <h6 class="mb-0" id="status3"></h6>
		                                        </div>
		                                    </div>
		                                      </c:if>
		                                         </div>
                                  
                                    
                                     <ul class="list-unstyled d-flex align-items-center avatar-group mb-0" style="float:right">
                                     
                                        
                                        <c:set var="namesArray" value="${fn:split(upload_pnsall.upload_pn_respon, ',')}" />

												<c:forEach var="nameItem" items="${namesArray}" begin="0" end="5">
												    <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top"
												        title="UPLOADER : ${nameItem}" class="avatar avatar-sm pull-up"
												        data-bs-original-title="">
												        <img class="rounded-circle" src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="Avatar">
												    </li>
												</c:forEach>
												
										
                                           <c:set var="namesArray" value="${fn:split(upload_pnsall.upload_pn_app, ',')}" />

												<c:forEach var="nameItem" items="${namesArray}" begin="0" end="5">
												    <li data-bs-toggle="tooltip" data-popup="tooltip-custom" data-bs-placement="top"
												        title="INSPECTION : ${nameItem}" class="avatar avatar-sm pull-up"
												        data-bs-original-title="">
												        <img class="rounded-circle" src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="Avatar" style="border: 2px solid red;">
												    </li>
												</c:forEach>
                                     
                                    </ul>
                     
                     
                     
                     
                     
                   
                     
                                 
                                            </div>
                                            
                                           
                                            
                                            <div class="card-footer" id="statusalls2">
                                        
                                   
                                              <c:if test="${upload_pn.upload_pn_monitor > 0  && upload_pn.upload_pn_monitor <  100000000  && upload_pn.upload_pn_pivot == 1 }">     
                                                
                                              <div class="mail-attachments">
                                                    <div class="d-flex align-items-center mb-1">
                                                        <i data-feather='layers' class="font-medium-1 me-50"></i>
                                                        <h5 class="fw-bolder text-body mb-0">  <a  href="/mainreportplan-condition/${upload_pn.upload_pn_monitor}/${upload_pn.upload_pn_id}">
                                                        <spring:message code="common.report" />
                                                        </a></h5>
                                                        
                
                                                        
                                                                   <a  href="/addreport-filemanager?id=${upload_pn.upload_pn_id}" onclick="openCenteredWindow(this.href, 'File Window', 1500, 600);return false; ">  <button type="button"   
                                                                                                                       
                             class="btn btn-sm btn-light me-1 waves-effect waves-float waves-light" style="margin-left:20px;"   title="เพิ่ม">
                                <i data-feather='plus-circle'></i>
                                    </button></a>
                                                    </div>
                                                    <div class="d-flex flex-column">
                                                    
                                          
			                                                                   
          <c:forEach items="${reportall }" var="row" varStatus="count">                          
			        <a href="/mainreportplans-condition/${condition.condition_id}/${row.report_id}"  >- <img src="/resources/app-assets/images/icons/csv.png" height="18" alt="database" /> 
			        ${row.report_name } ${row.report_date } (${row.report_by })</a>
           </c:forEach> 
			                                               
			                                     
                                                    </div>
                                                </div>    
                                                 
                                                   <br> <br>   
                                                </c:if>
              
              
                                                   
   
                          
              
              
              
              
              
                
              
                                                
                                                
       
                                                 <div class="mail-attachments">
                                                    <div class="d-flex align-items-center mb-1">
                                           
                                                        <i data-feather='archive' class="font-medium-1 me-50"></i>
                                                        <h5 class="fw-bolder text-body mb-0">Overview</h5>
                                                    </div>
                                                    <div class="d-flex flex-column">
                                                    
                                                     
                                               <div class="row">
                                          
            
               <div class="col-xl-4 col-lg-6 col-md-6" >                                           
      <canvas id="verticalBarChart" ></canvas>
      
       <script>
        const ctx = document.getElementById('verticalBarChart').getContext('2d');
        const verticalBarChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['จำนวนแผนงาน'], // หมวดหมู่
                datasets: [
                    {
                        label: 'แผนต้นฉบับ', // แผนต้นฉบับ
                        data: [${original_task.size()+0 }], // ข้อมูลแผนต้นฉบับ
                        backgroundColor: 'rgba(255, 99, 132, 0.5)', // สีแดงโปร่งแสง
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1,
                    },
                    {
                        label: 'แผนปรับปรุง', // แผนปรับปรุง
                        data: [${task.size()+0 }], // ข้อมูลแผนปรับปรุง
                        backgroundColor: 'rgba(54, 162, 235, 0.5)', // สีน้ำเงินโปร่งแสง
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1,
                    },
                ],
            },
            options: {
                scales: {
                    x: {
                        grid: {
                            display: false, // ซ่อนเส้นกริดแกน X
                        },
                    },
                    y: {
                        beginAtZero: true, // เริ่มต้นที่ 0
                        grid: {
                            color: '#e0e0e0', // สีกริดแกน Y
                        },
                    },
                },
                plugins: {
                    legend: {
                        position: 'top', // วาง legend ไว้ด้านบน
                    },
                    tooltip: {
                        enabled: true,
                    },
                },
                barPercentage: 0.5, // ขนาดความกว้างแท่ง
                categoryPercentage: 0.5, // ระยะห่างระหว่างหมวดหมู่
            },
        });
    </script>     
     </div>        
            
            
            
            
             <div class="col-xl-4 col-lg-6 col-md-6" >                                           
 <canvas id="twoRowBarChart" ></canvas>
 
 <script>
        const ctx2 = document.getElementById('twoRowBarChart').getContext('2d');
        const twoRowBarChart2 = new Chart(ctx2, {
            type: 'bar',
            data: {
                labels: [
                    <c:forEach items="${task }" var="row" varStatus="count">     
                	'${row.task_name }',
                	</c:forEach>
                	], // หมวดหมู่

                datasets: [
                    {
                        label: 'งบประมาณ', // แผนต้นฉบับ
                        data: [
                        	   <c:forEach items="${task }" var="row" varStatus="count">     
                           	'${row.task_budget+0}',
                           	</c:forEach>
                        	
                        ],
                        backgroundColor: 'rgba(255, 99, 132, 0.5)', // สีแดงโปร่งแสง
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1,
                    },
                    {
                        label: 'ค่าใช้จ่าย', // แผนปรับปรุง
                        data: [
                        	   <c:forEach items="${task }" var="row" varStatus="count">     
                           	'${row.task_expenses+0 }',
                           	</c:forEach>
                        ],
                        backgroundColor: 'rgba(54, 162, 235, 0.5)', // สีน้ำเงินโปร่งแสง
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1,
                    },
                ],
            },
            options: {
                indexAxis: 'y', // เปลี่ยนแกนเป็นแนวนอน
                scales: {
                    x: {
                        beginAtZero: true,
                        grid: {
                            color: '#e0e0e0',
                        },
                    },
                    y: {
                        grid: {
                            display: false,
                        },
                    },
                },
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    tooltip: {
                        enabled: true,
                    },
                },
                barThickness: 10, // กำหนดความหนาของแท่ง
            },
        });
    </script>
     </div>                                
         
         
         
          
        
        
        
        <div class="col-xl-3 col-lg-6 col-md-6" >                                           
    <canvas id="activityDoughnutChart" ></canvas>
   
     </div>
      
      
   <hr>
             
        <div class="card-body">
                                    <ul class="nav nav-pills">
                                    
                                    
                                   
                                        <li class="nav-item">
                                            <a class="nav-link active" id="profile-tab" data-bs-toggle="pill" href="#profile" aria-expanded="false">
                                              แผนปรับปรุง
                                           </a>
                                        </li>
                                      <li class="nav-item">
                                            <a class="nav-link" id="home-tab" data-bs-toggle="pill" href="#home" aria-expanded="true">
                                               แผนต้นฉบับ
                                           </a>
                                        </li>
                                        
                                    </ul>
                                    
                                    
                                    <div class="tab-content">
                                        
                                        
                                        
                                        <div role="tabpanel" class="tab-pane" id="home" aria-labelledby="home-tab" aria-expanded="true">
                                           
                        
                                    <table class="table" id="data-table">
                                   <thead>
                                        <tr>
                                            <th>ลำดับ</th>
                                          
                                            <th>รายการ</th>
                                            <th>วันที่</th>
                                         <th>ผู้รับผิดชอบ</th>
                                               <th>งบประมาณ</th>
                                              <th>ยอดเรียกเก็บ</th>
                         
                                        </tr>
                                        </thead>
                                       
                                        
                                      
                                        <tbody>
                                    <c:set var="original_task_num" value="0" />      
       <c:forEach items="${original_task }" var="row" varStatus="count">      
       
             
             
                                        <tr class="datatable">
                                            <td style="text-align:left;" >${count.count }</td>
                                             <td style="text-align:left;" title="${row.task_name }">${row.task_name }</td>
                                          <td style="text-align:left;" title="${row.task_datestart } - ${row.task_dateend }"> ${row.task_datestart } - ${row.task_dateend }</td>
                                              <td style="text-align:left;" title="${row.task_iduser }"> ${ row.task_iduser }</td>
                                            <td style="text-align:left;" title="${row.task_budget }"> ${ row.task_budget }</td>
                                                           <td style="text-align:left;" title="${row.task_payment }"> ${ row.task_payment }</td>
                                       
                                          
                                      </tr>
                   					 </c:forEach>           
                            		  
                                         </tbody>
                                         
                                         </table>
                                        </div>
                                        <div class="tab-pane active" id="profile" role="tabpanel" aria-labelledby="profile-tab" aria-expanded="false">
                                        
                                        
                                          <table class="table">
                                    <thead>
                                        <tr>
                                            <th>ลำดับ</th>
                                          
                                            <th>รายการ</th>
                                            <th>วันที่</th>
                                         <th>ผู้รับผิดชอบ</th>
                                               <th>งบประมาณ</th>
                                         <th>ค่าใช้จ่าย</th>
                                              <th>ยอดเรียกเก็บ</th>
                                          <th>ยอดเก็บเงิน</th>
                                            <th>สถานะ</th>
                                        </tr>
                                        </thead>
                                       
                                      
                                      
                                        <tbody>
                                          <c:set var="task1" value="0" />
                                              <c:set var="task2" value="0" />
                                                  <c:set var="task3" value="0" />  
                                    <c:forEach items="${task }" var="row" varStatus="count">            
                                        <tr class="datatable">
                                <td style="text-align:left;" >${count.count }</td>
                                             <td style="text-align:left;" title="${row.task_name }">${row.task_name }</td>
                                          <td style="text-align:left;" title="${row.task_datestart } - ${row.task_dateend }"> ${row.task_datestart } - ${row.task_dateend }</td>
                                              <td style="text-align:left;" title="${row.task_iduser }"> ${ row.task_iduser }</td>
                                            <td style="text-align:left;" title="${row.task_budget }"> ${ row.task_budget }</td>
                                                  <td style="text-align:left;" title="${row.task_expenses }"> ${ row.task_expenses }</td>
                                                           <td style="text-align:left;" title="${row.task_payment }"> ${ row.task_payment }</td>
                                         <td style="text-align:left;" title="${row.task_real_payment }"> ${ row.task_real_payment }</td>
                                            <td style="text-align:left;">
                                            
                                            
                                                    <c:if test="${row.task_status ==  0 }">  
                                                    <span class="badge  bg-info ">ยังไม่ถึงกำหนด</span>
                                                                         <c:set var="task1" value="${task1+1 }" />
                                                    </c:if>
                                                     <c:if test="${row.task_status ==  1 }">  
                                                     <span class="badge  bg-warning ">ระหว่างดำเนินการ</span>
                                                                                 <c:set var="task2" value="${task2+1 }" />
                                                    </c:if>
                                                          <c:if test="${row.task_status ==  2 }">  
                                                          <span class="badge  bg-success ">เสร็จสิ้น</span>
                                                                                      <c:set var="task3" value="${task3+1 }" />
                                                    </c:if>
                                            
                                            </td>
                                      </tr>
                   					 </c:forEach>                  
                            	
                                      
                                        
                                        
                                            </tbody>
                                            </table>
                                        </div>
                                     
                                    </div>
                                </div>            
                    
                </div>
                                                     
                                                        
                                                    </div>
                                                </div>
                                                
                                       <br> <br>         
            
                        
                   <script>
        const ctx3 = document.getElementById('activityDoughnutChart').getContext('2d');
        const activityDoughnutChart3 = new Chart(ctx3, {
            type: 'doughnut',
            data: {
                labels: [
                    'เสร็จสิ้น',
                    'ระหว่างดำเนินการ',
                    'ยังไม่ถึงกำหนด'
                ],
                datasets: [{
                    data: [${task3+0},${task2+0}, ${task1+0}], // จำนวนกิจกรรมในแต่ละสถานะ
                    backgroundColor: [
                        'rgba(54, 162, 235, 0.5)', // สีฟ้า
                        'rgba(255, 99, 132, 0.5)', // สีแดง
                        'rgba(75, 192, 192, 0.5)'  // สีเขียว
                    ],
                    borderColor: [
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 99, 132, 1)',
                        'rgba(75, 192, 192, 1)'
                    ],
                    borderWidth: 1,
                }]
            },
            options: {
                plugins: {
                    legend: {
                    
                        position: 'bottom', // ตำแหน่ง legend ด้านบน
                    },
                    tooltip: {
                        enabled: true,
                    },
                },
            },
        });
    </script>      
                        
                        
                        
                        
                                                <br> <br> <br>
                                            
							          				
							          								
						
							     
										
                                            </div>
                                     
                                     
                                     
                                           
                                            <div class="card-body "  id="statusdocs" style="display:none">
                                    <ul class="nav nav-tabs" role="tablist" >
                                        <li class="nav-item" style="display:none">
                                            <a class="nav-link active" id="home-tab" data-bs-toggle="tab" href="#home" aria-controls="home" role="tab" aria-selected="true">รออัพโหลด</a>
                                        </li>
                                        <li class="nav-item" style="display:none">
                                            <a class="nav-link" id="profile-tab" data-bs-toggle="tab" href="#profile" aria-controls="profile" role="tab" aria-selected="false">รอตรวจ</a>
                                        </li>
                                 
                                        <li class="nav-item" style="display:none">
                                            <a class="nav-link" id="about-tab" data-bs-toggle="tab" href="#about" aria-controls="about" role="tab" aria-selected="false">รอเเก้ไข</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content " >
                                        <div class="tab-pane active" id="home" aria-labelledby="home-tab" role="tabpanel">
                                        
                                     
                                 
							          								    <button onclick="download('xlsx');"  type="button"  class="btn btn-primary"  style="float:right;margin-right:10px;">Export</button>
                                        <br>    <br>
                                        
                                        
                                         <div class="table-responsive" style="height:500px;">
                                        <table class="table" id="data-table">
                                    <thead>
                                        <tr>
                                        	<th>
                                                 NO
                                                   </th>
  													<th>
                                                  <spring:message code="tables.due"  />
                                                   </th>
                                              <th>
                                              
                                              
                                                <spring:message code="tables.dateadd"  />
                                           
                                              </th>
                               
                                            <th>
                                              <spring:message code="tables.dues"  />
                                             
                                            </th>
                                         
                                             <th>
                                               <spring:message code="tables.duecheck"  />
                                           
                                           
                                            </th>
                                            
                                             <th>
                               
                                             <spring:message code="tables.responsible" text="ผู้รับผิดชอบ" />
                                          
                                            </th>
                                            <th>
                                             <spring:message code="tables.inspector" text="ผู้ดูเเล" />
                                            </th>
                                       <th>
                                           <spring:message code="btn.delete"  />
                                            </th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                           <c:set var="num" value="0" />
                                         <c:forEach items="${report_pn }" var="row" varStatus="count">
                                  
                        <c:if test="${row.report_pn_status ==  1 }">  
                                      <c:set var="date" value="${row.report_pn_date}" />
							         <fmt:parseDate var="parsedDate" value="${date}" pattern="yyyy-MM-dd" />
							<fmt:formatDate var="dayOfWeek" value="${parsedDate}" pattern="EEE"  />
                                        <tr class="uploader-row" data-uploader="${row.report_pn_respon }">
                                        
                              
                                         <td>${count.count } </td>
                                            <td>${row.report_pn_date }  [ID:${row.report_pn_idrefer }]</td>
                                            <td>${row.report_pn_dateupload }</td>
                                            <td>${row.report_pn_date } [ ${dayOfWeek} ]</td>
                                               <td>${row.report_pn_datecheck }</td>
                                            <td >${row.report_pn_respon }</td>
                                             <td>${row.report_pn_app }</td>
                                             
                                                  <c:set var="list" value="${upload_pnsall.upload_pn_app}" />
                                           
                                               <c:set var="listcheck" value="${Member_name}" />
                                               
                                            <td>
                                             <c:if test='${fn:contains(list,listcheck) }'>
		                                            <a href="/delreport-filemanager/${row.report_pn_id }/${row.report_pn_idrefer }"
		                                            onclick="return confirm('<spring:message code="action.del" text="คุณต้องการลบแผนนี้" />');"  class="btn btn-danger">ลบ</a>
		                                            </c:if>
                                            </td>
                                       
                                        </tr>
                                        
                         	 </c:if>     
                         	    <c:if test="${group == 3    }">    
                         	             <c:set var="num" value="${num + 1}" />        
                                          <c:set var="status1" value="${num }" />
                                 	 </c:if>            
                           <c:if test="${group ==  2 }">      
                                         <c:if test="${Member_name ==  row.report_pn_respon }">  
                                          <c:set var="num" value="${num + 1}" />   
                                         <c:set var="status1" value="${num }" />
                                 	 </c:if>           
                              </c:if>      
                              
                              
                                          </c:forEach>
                                    </tbody>
                                </table>
                           
                                        </div>
                                          </div>
                                        <div class="tab-pane" id="profile" aria-labelledby="profile-tab" role="tabpanel">
                              
                                         <a href="#" id="statusall" style="display:none" class="btn btn-light waves-effect waves-float waves-light" style="float:right;margin-right:10px;">
							          					<spring:message code="common.Overview"  /></a>
							          								
							        <button onclick="download2('xlsx');"  type="button"  class="btn btn-primary" 
							         style="float:right;margin-right:10px;">
							         <spring:message code="btn.export"  />
							         </button>
                                        <br>  
                                        <br>   
                                        
                                           <div class="table-responsive" style="height:500px">
                                                <c:set var="num" value="0" />
                                        <table class="table" id="data-table2">
                                    <thead>
                                        <tr>
                                        	<th>
                                                 NO 
                                                   </th>
  													<th>
                                                  <spring:message code="tables.due"  />
                                                   </th>
                                              <th>
                                              
                                              
                                                <spring:message code="tables.dateadd"  />
                                           
                                              </th>
                               
                                            <th>
                                              <spring:message code="tables.dues"  />
                                             
                                            </th>
                                         
                                             <th>
                                               <spring:message code="tables.duecheck"  />
                                           
                                           
                                            </th>
                                            
                                             <th>
                               
                                             <spring:message code="tables.responsible" text="ผู้รับผิดชอบ" />
                                          
                                            </th>
                                            <th>
                                             <spring:message code="tables.inspector" text="ผู้ดูเเล" />
                                            </th>
                                      
                                          
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${report_pn2 }" var="row" varStatus="count">
                                         
                        <c:if test="${row.report_pn_status ==  2 }">  
                                      <c:set var="date" value="${row.report_pn_date}" />
							         <fmt:parseDate var="parsedDate" value="${date}" pattern="yyyy-MM-dd" />
							<fmt:formatDate var="dayOfWeek" value="${parsedDate}" pattern="EEE"  />
                                        <tr class="uploader-row2" data-uploader="${row.report_pn_app }">
                                        
                              
                                         <td>${count.count } </td>
                                            <td>${row.report_pn_date } </td>
                                            <td>${row.report_pn_dateupload }</td>
                                            <td>${row.report_pn_date } [ ${dayOfWeek} ]</td>
                                               <td>${row.report_pn_datecheck }</td>
                                            <td>${row.report_pn_respon }</td>
                                             <td>${row.report_pn_app }</td>
                                        
                                       
                                       
                                        </tr>
                         	 </c:if>               
                                             
                         	             <c:set var="num" value="${num + 1}" />        
                                          <c:set var="status2" value="${num }" />
                          
                              
                                          </c:forEach>
                                    </tbody>
                                </table>
                                        </div>
                                       
                                        </div>
                                          <div class="tab-pane" id="about" aria-labelledby="about-tab" role="tabpanel">
                               
                                         <a href="#" id="statusall3"  style="display:none" class="btn btn-light waves-effect waves-float waves-light" style="float:right;margin-right:10px;">
							          								<spring:message code="common.Overview"  /></a>
							          								
							          															    <button onclick="download3('xlsx');"  type="button"  class="btn btn-primary"  style="float:right;margin-right:10px;">
							          															         <spring:message code="btn.export"  /></button>
                                        <br>    
                                        <br>   
                                        
                                           <div class="table-responsive" style="height:500px;">
                                                <c:set var="num" value="0" />
                                        <table class="table" id="data-table3">
                                    <thead>
                                        <tr>
                                        	<th>
                                                 NO
                                                   </th>
  													<th>
                                                  <spring:message code="tables.due"  />
                                                   </th>
                                              <th>
                                              
                                              
                                                <spring:message code="tables.dateadd"  />
                                           
                                              </th>
                               
                                            <th>
                                              <spring:message code="tables.dues"  />
                                             
                                            </th>
                                         
                                             <th>
                                               <spring:message code="tables.duecheck"  />
                                           
                                           
                                            </th>
                                            
                                             <th>
                               
                                             <spring:message code="tables.responsible" text="ผู้รับผิดชอบ" />
                                          
                                            </th>
                                            <th>
                                             <spring:message code="tables.inspector" text="ผู้ดูเเล" />
                                            </th>
                                      
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${report_pn3 }" var="row" varStatus="count">
                                         
                        <c:if test="${row.report_pn_status ==  -1 }">  
                                      <c:set var="date" value="${row.report_pn_date}" />
							         <fmt:parseDate var="parsedDate" value="${date}" pattern="yyyy-MM-dd" />
							<fmt:formatDate var="dayOfWeek" value="${parsedDate}" pattern="EEE"  />
                                        <tr  class="uploader-row3" data-uploader="${row.report_pn_respon }">
                                        
                              
                                         <td>${count.count } </td>
                                            <td>${row.report_pn_date } </td>
                                            <td>${row.report_pn_dateupload }</td>
                                            <td>${row.report_pn_date } [ ${dayOfWeek} ]</td>
                                               <td>${row.report_pn_datecheck }</td>
                                            <td>${row.report_pn_respon }</td>
                                             <td>${row.report_pn_app }</td>
                                        
                                     
                                       
                                        </tr>
                         	 </c:if>               
                          <c:if test="${group ==  3 }">    
                         	             <c:set var="num" value="${num + 1}" />        
                                          <c:set var="status3" value="${num }" />
                           </c:if>            
                           <c:if test="${group ==  2   }">      
                                      <c:if test="${Member_name ==  row.report_pn_respon }">  
                                          <c:set var="num" value="${num + 1}" />   
                                         <c:set var="status3" value="${num }" />
                                     </c:if>           
                           </c:if>     
                                          </c:forEach>
                                    </tbody>
                                </table>
                                        </div>
                                        </div>
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                    </div>
                                </div>
                                        
                                        
                                         <div class="card-body"  id="statusreports"  style="display:none">
          
              
              
                   
              <c:if test="${condition  == null }">  </c:if>
                   
                   
                   
                       
                                
						
							    
							
                                
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
  <div class="powers">
<span for="userFilter"><spring:message code="forms.Selectuser"  />:</span>
<c:set var="namesArray" value="${fn:split(upload_pnsall.upload_pn_respon, ',')}" />
<select id="userFilter">

<option value="all"><spring:message code="forms.all"  /></option>
<c:forEach var="nameItem" items="${namesArray}" begin="0" end="5">
<option value="${nameItem}">${nameItem}</option>
</c:forEach>


</select>

<span for="dateFilter"><spring:message code="forms.Chooseadate"  />:</span>
<select id="dateFilter">
  <option value="all"><spring:message code="forms.all"  /></option>
  <option value="today"><spring:message code="common.today"  /></option>
  <option value="7days"><spring:message code="common.7days"  /></option>
  <option value="thisMonth"><spring:message code="common.thisMonth"  /></option>
  <option value="thisYear"><spring:message code="common.thisYear"  /></option>
    <option value="1"><spring:message code="common.month"  /> 1</option>
    <option value="2"><spring:message code="common.month"  /> 2</option>
        <option value="3"><spring:message code="common.month"  /> 3</option>
            <option value="4"><spring:message code="common.month"  /> 4</option>
                <option value="5"><spring:message code="common.month"  /> 5</option>
                    <option value="6"><spring:message code="common.month"  /> 6</option>
                        <option value="7"><spring:message code="common.month"  /> 7</option>
                            <option value="8"><spring:message code="common.month"  /> 8</option>
                                <option value="9"><spring:message code="common.month"  /> 9</option>
                                    <option value="10"><spring:message code="common.month"  /> 10</option>
                                        <option value="11"><spring:message code="common.month"  /> 11</option>
                                            <option value="12"><spring:message code="common.month"  /> 12</option>
</select>

	        <c:set var="listcheck" value="${Member_name}" />  
                                    <c:set var="list" value="${upload_pnsall.upload_pn_app}" />                    
                                        
                                        
                                    
                        
<c:if test='${condition.condition_sub > 0 || condition.condition_child > 0}'>                                    
	     <c:set var="list" value="#" />                                           
	     <c:if test='${count_clearance > 0}'>
	     <c:set var="list" value="/duepn-filemanager?id=${clearance.data_planid }&&step=0&&group=3" />            
	     </c:if>       
	     
	
	
 </c:if>
  
   <c:if test='${condition !=  null}'>
   <a href="#"  class="btn btn-light btn-sm  waves-effect waves-float waves-light "  onclick="download4('xlsx');"   
   style="float:right;margin-right:10px;margin-bottom:2px;">
  <spring:message code="btn.export"  />
  </a>
  </c:if>
  
  		  <a href="#"  class="btn btn-light btn-sm  waves-effect waves-float waves-light "     data-bs-toggle="modal" data-bs-target="#new-task-modal2" 
   style="float:right;margin-right:10px;margin-bottom:2px;">
PD
  </a>				      
  	<a href="#"  class="btn btn-light btn-sm  waves-effect waves-float waves-light "     data-bs-toggle="modal" data-bs-target="#new-task-modal4" 
   style="float:right;margin-right:10px;margin-bottom:2px;">
Part
  </a>						   
<a href="#"  class="btn btn-light btn-sm  waves-effect waves-float waves-light "     data-bs-toggle="modal" data-bs-target="#new-task-modal3" 
   style="float:right;margin-right:10px;margin-bottom:2px;">
วัตถุดิบ
  </a>							      
  						      
  						      

  
 
  

    <a href="#"  class="btn btn-light btn-sm  waves-effect waves-float waves-light accordion2"     
   style="float:right;margin-right:10px;margin-bottom:2px;">
 <i data-feather='pie-chart'></i>
  </a>
      <a href="#"  class="btn btn-light btn-sm  waves-effect waves-float waves-light "       data-bs-toggle="modal" data-bs-target="#addNewCards2"   
   style="float:right;margin-right:10px;margin-bottom:2px;">
 <i data-feather='upload'></i>
  </a>
  
  <a href="#"  style="display:none" class="btn btn-light  btn-sm waves-effect waves-float waves-light "  data-bs-toggle="modal" data-bs-target="#new-task-modal" 
    style="float:right;margin-right:10px;margin-bottom:2px;">
 <i data-feather='search'></i></a>

</div>
  
  
  
    <div id="accordionBorderOne" style="display:none">
             <div class="accordion-body">
              <br>
                    
                       <c:if test="${e_report.status == 0}">   
                       
                       
  <a class="btn btn-sm btn-light me-1 waves-effect waves-float waves-light" 
  href="javascript:void(0)" style="float:right" title="<spring:message code="common.setting"  />"
   data-bs-toggle="modal" data-bs-target="#addNewCard3">
   <i data-feather='settings'></i>
</a>
                         
                        <div class="btn-group btn-group-sm  mt-md-0 mt-1" role="group" aria-label="Basic radio toggle button group" style="padding-left:5px;" >
                                        <input type="radio" class="btn-check" name="radio_options" id="radio_option1" autocomplete="off" checked="" onchange="sw('chartdiv')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_option1">Line</label>

                                        <input type="radio" class="btn-check" name="radio_options" id="radio_option2" autocomplete="off" onchange="sw('chartdiv2')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_option2">Column</label>

                                        <input type="radio" class="btn-check" name="radio_options" id="radio_option3" autocomplete="off" onchange="sw('chartdiv3')">
                                        <label class="btn btn-outline-primary waves-effect" for="radio_option3">Pie</label>
                                        
                                       
                                    </div>
                                    <br>  <br>
                               <div id="chartdiv" ></div>    
                                    </c:if>
                               
                                <div id="chartdiv2" style="display:none"></div>    
                                 <div id="chartdiv3" style="display:none"></div>     
                   <div id="echarts" style="display:none">  </div>
                </div>
        </div>
  
   <div class="table-responsive" style="max-height:700px;min-height:500px;" >
                                      
                                    
                                 <c:set var="listchecksum" value="${upload_pn.upload_pn_sum }" />
                                 <c:set var="listhidden" value="${upload_pn.upload_pn_avg }" />
                                  <c:set var="alowtitle" value="${condition.condition_titlerefer }" />
                                    <c:set var="listhidden2" value="${upload_pn.upload_pn_hidden }" />
                           
                                     
                                     
                            
                                          <%@ include file="sube-form.jsp" %>    
                                              
                                            
                                              
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


         <div class="modal fade" id="addNewCard" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="dar.uncheck" /></h1>
                              

                                <!-- form -->
                                <form    action="/rejectdocument-filemanager" id="formaddNewCardValidation" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber"><spring:message code="dar.reason" /></label>
                                        <div class="input-group input-group-merge">
                                       
                                            <input id="modalAddCardNumber" name="note" class="form-control autocomplete-input2" type="text" required/>
                                              
                                            <span class="input-group-text cursor-pointer p-25" id="modalAddCard2">
                                                <span class="add-card-type">   </span>
                                            </span>
                                        </div>
                                        ไม่ได้เข้า,ข้อมูลไม่ถูกต้อง หรือ อื่นๆ
                                          <input type="hidden" name="id" id="idreject" value=""> 
                                    </div>

                                   
                                

                                    <div class="col-12 text-center">
                                        <button type="button" class="btn btn-primary me-1 mt-1"  
                                        onclick='postdata("/rejectdocument-filemanager?${_csrf.parameterName}=${_csrf.token}","formaddNewCardValidation","savestatus")' data-bs-dismiss="modal" aria-label="Close">Save</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                                                 <script>

	  $( function() {
		  
		  var newdata = 'ไม่ได้เข้า,ข้อมูลไม่ถูกต้อง'; // ข้อความเริ่มต้
		  var trimmedData = newdata.trim(); // ตัดช่องว่างที่อยู่ท้ายสตริง
		var dataItems = trimmedData.split(',');
		  
	    $( ".autocomplete-input2" ).autocomplete({
	      source: dataItems
	    });
	  } );
</script>     
                </div>
                <!--/ add new card modal  -->







     <div class="modal fade" id="addNewCards" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="dar.check" /></h1>
                              

                                <!-- form -->
                                <form    action="/appdocument-filemanager" id="formaddNewCardValidation2" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                
                                
                                
               
            <c:forEach items="${eformfields }" begin="0" end="${size }"  var="row" varStatus="count">
                                 <c:set var="title" value="${row.field_label }" />   
                       <c:set var="appfield" value="${upload_pn.upload_pn_appfield }" />
                          <c:if test="${fn:contains(appfield,row.field_label)}"> 
                             
                                         <script>
                                         
                                         </script>
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${title }</label>
                                                    
                                                 <c:if test='${row.field_type.equals("select") && !row.field_cal_type.equals("product") &&  !row.field_cal_type.equals("lot")  }'>     
                                           
                                                 <select name="data_value${count.count }"  id="data_values${row.field_id }" class="form-select">
                                                 </select>
                                                  </c:if>
                                                   <c:if test='${row.field_type.equals("text")}'>   
                                                 <input type="text" name="data_value${count.count }"  id="data_values${row.field_id }" class="autocomplete-input form-control">
                                                
                                                 
                                                  </c:if>
                                                  
                                                  
                                          
                                                        <c:if test='${row.field_type.equals("number") }'>  
                                            					      <input type="number" step="0.01" min="0" name="data_values${count.count }"  id="data_value${row.field_id }" class="form-control">
                                                     </c:if>
                                             
        
                                                 
                                                 
                                                    <c:if test='${row.field_type.equals("textarea")}'>      
                                                 <textarea name="data_value${count.count }"  id="data_values${row.field_id }" class="form-control"></textarea>
                                                  </c:if>
                                              <c:if test='${row.field_type.equals("date")}'>      
                                               <input type="date" name="data_value${count.count }"  id="data_values${row.field_id }" class="form-control">
                                                  </c:if>
                                                  
                                                   <c:if test='${row.field_type.equals("time")}'>      
                                              <input type="time" class="form-control mydata"  name="data_value${count.count }" id="data_values${row.field_id }"  placeholder="hh:mm" >
                                                  </c:if> 
                                                  
                                                  
                                                               
                                                  
                                                  
                                                
                                                  
                                                    <c:if test='${row.field_type.equals("checkbox")}'>
                                                 <br>      
                                                <span id="data_value${row.field_id }"></span>
                                                    </c:if>
                                             
                                           <c:set var="totaltitle" value="${count.count }" />
                                            
                                            
                                            <c:if test='${totaltitle == null}'>  <c:set var="totaltitle" value="1" />  </c:if> 
                                            
                                    <input type="hidden" name="data_title${count.count }"  id="data_titles${row.field_id }"  value="${title }">     
                                   
                                   
                                    <input type="hidden" name="field_cal_type"  id="field_cal_type"  value="${row.field_cal_type }">         
                                     <input type="hidden" name="field_field_no"  id="field_field_no"  value="${row.field_field_no }">    
                                     
                                    </div>   
                                    
                                       </c:if> 
                            
                        </c:forEach>
                        
                        
                                   <div>    
								                           <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
									                       <input type="radio" class="btn-check mydata" value="Good"  name="data_status" id="btnradio1" autocomplete="off" >
									                       <label class="btn btn-outline-success waves-effect" for="btnradio1">Good</label>
								
									                       <input type="radio" class="btn-check mydata" value="Normal"   name="data_status" id="btnradio2" autocomplete="off" checked>
									                       <label class="btn btn-outline-warning waves-effect" for="btnradio2">Normal</label>
								
									                       <input type="radio" class="btn-check mydata" value="Bad"  name="data_status" id="btnradio3" autocomplete="off">
									                       <label class="btn btn-outline-danger waves-effect" for="btnradio3">Bad</label>
									                       </div>
									                         </div>
                                
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber"><spring:message code="dar.reason" /></label>
                                        <div class="input-group input-group-merge">
                                       
                                            <input id="modalAddCardNumber" name="note" class="form-control autocomplete-input3" type="text" required/>
                                        
                                            <span class="input-group-text cursor-pointer p-25" id="modalAddCard2">
                                                <span class="add-card-type"></span>
                                            </span>
                                        </div>
                                         
                                          <input type="hidden" name="id" id="idapp" value=""> 
                                    </div>
                                    
                                    
                                    
                                    
                                    

                                    <div class="col-12 text-center">
                                        <button type="button" class="btn btn-primary me-1 mt-1"  
                                        onclick='postdata("/appdocument-filemanager?${_csrf.parameterName}=${_csrf.token}","formaddNewCardValidation2","savestatus")' data-bs-dismiss="modal" aria-label="Close">Save</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                                                                     <script>

	  $( function() {
		  
		  var newdata = 'เข้าตามเเผน,เข้าไม่ตามเเผน,ไม่ได้เข้า,เลื่อน'; // ข้อความเริ่มต้
		  var trimmedData = newdata.trim(); // ตัดช่องว่างที่อยู่ท้ายสตริง
		var dataItems = trimmedData.split(',');
		  
		  

		  
	    $( ".autocomplete-input3" ).autocomplete({
	      source: dataItems
	    });
	    
	    
	    
	    
	  } );
</script> 
                </div>
               






    <div class="modal fade" id="addNewCard3" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle">Charts Setting</h1>

                                <!-- form -->
                                
                                <form:form method="POST" modelAttribute="e_report"  action="/editcharts-filemanager" class="row gy-1 gx-2 mt-75"  >
                                
                            
                                   <input type="hidden" name="e_report_id" id="e_report_id" value="${e_report.e_report_id}"> 
                                      <input type="hidden" name="upload_pn_id" id="upload_pn_id" value="${upload_pn.upload_pn_id } "> 
                             
                              <input type="hidden" name="group" value="${group } "> 
                               <input type="hidden" name="id" value="${id } "> 
                                <input type="hidden" name="step" value="${step } "> 
                             
                             
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber">
                                        <spring:message code="forms.chart"  />
                                        </label>
                                        <div class="input-group input-group-merge">
                             
                                  			  <select class="form-select" name="status">
			                            
			                     
                                  <c:if test="${e_report.status == 0}"> 
                                  <c:set var="myselected1" value="selected" />           
                             </c:if>                
					              <c:if test="${e_report.status == 1}"> 
                                  <c:set var="myselected2" value="selected" />           
                             </c:if>            
		               <option value="0" ${myselected1 }><spring:message code="btn.enable" text="เปิด" /></option>
		                        <option value="1" ${myselected2 }><spring:message code="btn.disable" text="ปิด" /></option>
                        
		                               
		               
		                                     </select>
		                                     
		                                     
		                                     
                                        </div>
                                        
                                    </div>
                             
                             
                             <hr>
                             
                             
                             
                          
                                    <div class="col-4">
                                        <label class="form-label" for="modalAddCardNumber">Line (Lable)</label>
                                        <div class="input-group input-group-merge">
                                       
                               
                                     <select class="form-select" name="line_label">
                           <c:set var="myselected" value="" />
                               
                               
                                <c:if test="${e_report.line_label == 'ผู้เพิ่ม'}"> 
                                    <c:set var="myselected" value="selected" />
                                </c:if>
                                     
                                     
                                                 <c:if test="${e_report.pie_label == 'บันทึกผู้ตรวจ'}"> 
                                  <c:set var="myselecteds" value="selected" />           
                             </c:if>                   
			                                  
			                                    <option value="บันทึกผู้ตรวจ" ${myselecteds }><spring:message code="common.notecheck"  /></option>
                                     
                                        <option value="ผู้เพิ่ม" ${selected }><spring:message code="common.userinsert"  /></option>
                                             <c:set var="myselected" value="" />
			                      <c:forEach items="${eformfields }" var="row" varStatus="count">     
                         
                         
                             <c:if test="${e_report.line_label == row.field_label}"> 
                                    <c:set var="myselected" value="selected" />
                                </c:if>
                                   
                               <option value="${row.field_label}" ${myselected }>${row.field_label }</option>
                          
                                
                                    <c:set var="myselected" value="" />
                               </c:forEach>  
			                                  </select>
                                        </div>
                                        
                                    </div>
                                    
                                      <div class="col-4">
                                        <label class="form-label" for="modalAddCardNumber">Line (Data)</label>
                                        <div class="input-group input-group-merge">
			                               
                                  
                                   <select class="form-select" name="line_data">
                                   
                                       <c:set var="myselected" value="" />
                                  <c:if test="${e_report.line_data == 'lable_num'}"> 
                                               <c:set var="myselected" value="selected" />           
                                </c:if>     
                                    <option value="lable_num" ${myselected }><spring:message code="forms.Counttherows"  /></option>
				                    <c:set var="myselected" value="" />
				          <c:forEach items="${eformfields }" var="row" varStatus="count">     
                                  
                              <c:if test="${e_report.line_data == row.field_label}"> 
                                    <c:set var="myselected" value="selected" />
                                </c:if>
                     
                                <c:if test="${row.field_type == 'number'}"> 
                               <option value="${row.field_label}" ${myselected }>${row.field_label }</option>
                                </c:if>
                                  <c:set var="myselected" value="" />
                               </c:forEach> 
			                       </select>
                                  
                                        </div>
                                        
                                    </div>
                                         <div class="col-4">
                                        <label class="form-label" for="modalAddCardNumber" style="display:none">Line (Target)</label>
                                        <div class="input-group input-group-merge"  style="display:none">
                                       
                                  <input id="modalAddCardNumber" name="line_target" class="form-control add-credit-card-mask" type="number" step="0.01" min="0"   value="0"/>
                                    
                                        </div>
                                        
                                    </div>
                                    
                                       <div class="col-4">
                                        <label class="form-label" for="modalAddCardNumber">Column (Lable)</label>
                                        <div class="input-group input-group-merge">
                                       
                                      
                                     <select class="form-select" name="column_label">
                                     
                             <c:set var="myselected" value="" />
                                  <c:if test="${e_report.column_label == 'ผู้เพิ่ม'}"> 
                                               <c:set var="myselected" value="selected" />           
                             </c:if>     
                                     
                                                 <c:if test="${e_report.pie_label == 'บันทึกผู้ตรวจ'}"> 
                                  <c:set var="myselecteds" value="selected" />           
                             </c:if>                   
			                                  
			                                    <option value="บันทึกผู้ตรวจ" ${myselecteds }><spring:message code="common.notecheck"  /></option>
                                     
                                     
			                                <option value="ผู้เพิ่ม" ${myselected }><spring:message code="common.userinsert"  /></option>
			                                     <c:set var="myselected" value="" />
			        <c:forEach items="${eformfields }" var="row" varStatus="count">   
			        
			        
			                  <c:if test="${e_report.column_label == row.field_label}"> 
                                    <c:set var="myselected" value="selected" />
                                </c:if>
			          
                       
                               <option value="${row.field_label}" ${myselected }>${row.field_label }</option>
                       
                                    <c:set var="myselected" value="" />
                               </c:forEach>  
			                                  </select>
                                        </div>
                                        
                                    </div>
                                    
                                      <div class="col-4">
                                        <label class="form-label" for="modalAddCardNumber">Column (Data)</label>
                                        <div class="input-group input-group-merge">
                                       
                                      <select class="form-select" name="column_data">
                                      
                           <c:set var="myselected" value="" />
                                  <c:if test="${e_report.column_data == 'lable_num'}"> 
                                  <c:set var="myselected" value="selected" />           
                             </c:if>     
                                      
                                       <option value="lable_num" ${myselected }><spring:message code="forms.Counttherows"  /></option>
                                            <c:set var="myselected" value="" />
                                       
                                           <c:forEach items="${eformfields }" var="row" varStatus="count">     
      
                          <c:if test="${e_report.column_data == row.field_label}"> 
                                    <c:set var="myselected" value="selected" />
                                </c:if>
			          
      
                               <option value="${row.field_label}" ${myselected }>${row.field_label }</option>
                                <c:set var="myselected" value="" />
                               </c:forEach>  
			                                 
			                                  </select>
                               
                                        </div>
                                        
                                    </div>
                                         <div class="col-4">
                                        <label class="form-label" for="modalAddCardNumber">Column (Target)</label>
                                        <div class="input-group input-group-merge">
                                       
                                     
                                    <input id="modalAddCardNumber" name="column_target" class="form-control add-credit-card-mask" 
                                    type="number" step="0.01" min="0" value="${e_report.column_target}" min="0"/>
                                        </div>
                                        
                                    </div>
                                  
                                    
                                                    <div class="col-4">
                                        <label class="form-label" for="modalAddCardNumber">Pie (Lable)</label>
                                        <div class="input-group input-group-merge">
                              
                               
			                                  
			                                  <select class="form-select" name="pie_label">
			                
			                <c:set var="myselected" value="" />
                                  <c:if test="${e_report.pie_label == 'ผู้เพิ่ม'}"> 
                                  <c:set var="myselected" value="selected" />           
                             </c:if>     
                                        <c:if test="${e_report.pie_label == 'บันทึกผู้ตรวจ'}"> 
                                  <c:set var="myselecteds" value="selected" />           
                             </c:if>                   
			                                  
			                                    <option value="บันทึกผู้ตรวจ" ${myselecteds }><spring:message code="common.notecheck"  /></option>
			                                    <option value="ผู้เพิ่ม" ${myselecteds }><spring:message code="common.userinsert"  /></option>
			                                         <c:set var="myselected" value="" />
			        <c:forEach items="${eformfields }" var="row" varStatus="count">     
			        
                          <c:if test="${e_report.pie_label == row.field_label}"> 
                                    <c:set var="myselected" value="selected" />
                                </c:if>
                                
                                
                               <option value="${row.field_label}" ${myselected }>${row.field_label }</option>
                          <c:set var="myselected" value="" />
                               </c:forEach> 
			                                  </select>
                                        </div>
                                        
                                    </div>
                                    
                                      <div class="col-4">
                                        <label class="form-label" for="modalAddCardNumber">Pie (Data)</label>
                                        <div class="input-group input-group-merge">
                             
                                  			  <select class="form-select" name="pie_data">
			                            
			                              <c:set var="myselected" value="" />
                                  <c:if test="${e_report.pie_data == 'lable_num'}"> 
                                  <c:set var="myselected" value="selected" />           
                             </c:if>                
					                  
		               <option value="lable_num" ${myselected }><spring:message code="forms.Counttherows"  /></option>
		                    <c:set var="myselected" value="" />
		               
		                             <c:forEach items="${eformfields }" var="row" varStatus="count">   
		                             
		                            <c:if test="${e_report.pie_data == row.field_label}"> 
                                    <c:set var="myselected" value="selected" />
                                </c:if>       
		                               
                              <c:if test="${row.field_type == 'number'}"> 
                               <option value="${row.field_label}" ${myselected }>${row.field_label }</option>
                                </c:if>
                               
                               
                          <c:set var="myselected" value="" />
                               </c:forEach> 
		                               
		               
		                                     </select>
		                                     
		                                     
		                                     
                                        </div>
                                        
                                    </div>
                                       
                                       
                                    
                                  
                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1"><spring:message code="btn.save"  /></button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         <spring:message code="btn.cancel"  />
                                        </button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->




    <div class="modal fade" id="addNewCard4" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-lg" >
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"><spring:message code="plan.setting" /></h1>


               <form:form method="POST" modelAttribute="upload_pn" action="/setplan-filemanager">
               
               
               <input type="hidden" name="upload_pn_id" value="${upload_pn.upload_pn_id }">
               <input type="hidden" name="step" value="${step }">
               <input type="hidden" name="group" value="${group }">
               
               
                            <div class="card-body">
                                    <div class="nav-vertical">
                                        <ul class="nav nav-tabs nav-left flex-column" role="tablist" style="height: 140px;">
                                            <li class="nav-item">
                                                <a class="nav-link active" id="baseVerticalLeft-tab1" data-bs-toggle="tab" aria-controls="tabVerticalLeft1" 
                                                href="#tabVerticalLeft1" role="tab" aria-selected="true">
                                                <spring:message code="plan.setup1" />
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" id="baseVerticalLeft-tab2" data-bs-toggle="tab" aria-controls="tabVerticalLeft2"
href="#tabVerticalLeft2" role="tab" aria-selected="false">
<spring:message code="plan.setup2" />
</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" id="baseVerticalLeft-tab3" data-bs-toggle="tab" aria-controls="tabVerticalLeft3" 
                                                href="#tabVerticalLeft3" role="tab" aria-selected="false">
                                              
                                              <spring:message code="plan.setup3" />
                                                </a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="tabVerticalLeft1" role="tabpanel" aria-labelledby="baseVerticalLeft-tab1">
                                       			  <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label">
                                                    <spring:message code="plan.access1" />
                                                    </label>
                                                    <select  class="form-select" name="upload_pn_private">
                                                    <option value="0"  <c:if test='${upload_pn.upload_pn_private == 0 }'>selected</c:if>>
                                                    <spring:message code="plan.access2" />
                                                    </option>
                                                    <option value="1"  <c:if test='${upload_pn.upload_pn_private == 1 }'>selected</c:if>>
                                                    <spring:message code="plan.access3" />
                                                    </option></select>
                                                </div>
                                                
                                                
                                                    	  <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label">
                                                     <spring:message code="plan.Setoverallpermissions" />
                                                    </label>
                                                    <select  class="form-select" name="upload_pn_allpermissions">
                                                    <option value="0"  <c:if test='${upload_pn.upload_pn_allpermissions == 0 }'>selected</c:if>>
                                                    <spring:message code="plan.openpermissions" />
                                                    </option>
                                                    <option value="1"  <c:if test='${upload_pn.upload_pn_allpermissions == 1 }'>selected</c:if>>
                                                 <spring:message code="plan.closepermissions" />
                                                    </option></select>
                                                </div>
                                                
                                                
                                                <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label">
                                                    <spring:message code="plan.Allowsoverallpermissions" />
                                                    </label>
                                                    <select  class="form-select" name="upload_pn_allowallpermissions">
                                                    <option value="0"  <c:if test='${upload_pn.upload_pn_allowallpermissions == 0 }'>selected</c:if>>
                                                    <spring:message code="plan.allow" />
                                                    </option>
                                                    <option value="1"  <c:if test='${upload_pn.upload_pn_allowallpermissions == 1 }'>selected</c:if>>
                                                    <spring:message code="plan.notallowed" />
                                                    </option></select>
                                                </div>
                                                
                                                <c:if test='${eformfields2 != null}'>
		                                                  <div class="mb-1">
		                                                    <label for="todoTitleAdd" class="form-label">
		                                                    <spring:message code="plan.send1" />
		                                                    </label>
		                                                    <select  class="form-select" name="upload_pn_send1">
		                                                     <c:forEach items="${conditionssub1 }" var="row" varStatus="count">
		                                                    <option value="${row.upload_pn_id }"  <c:if test='${upload_pn.upload_pn_send1 == row.upload_pn_id  }'>selected</c:if>>${row.upload_pn_respon }</option>
		                                                    </c:forEach>
		                                                    
		                                                    </select>
		                                                </div>
                                                </c:if>
                                                                                               
                                            
                                                
                                                
                                            </div>
                                            
                                            
                                            
                                            <div class="tab-pane" id="tabVerticalLeft2" role="tabpanel" aria-labelledby="baseVerticalLeft-tab2">
                                                   <p>
                                                   <spring:message code="plan.edatacall" />
                                                   </p>
                                            <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label">
                                                    <spring:message code="plan.cal" />
                                                    </label>
                                                    <select  class="form-select select2" multiple="multiple" name="upload_pn_sum">
                                                 
                                                    <c:forEach items="${eformfields }" var="rows" varStatus="count">  
                                                       
                                                        <c:set var="listcheck" value="${upload_pn.upload_pn_sum }" />
                                                        <c:set var="listrespon" value="${rows.field_label }" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label }" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2 }" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${upload_pn.upload_pn_sum }" />
                                                        <c:set var="listrespon" value="${rows.field_label }" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label }" ${check }>${rows.field_label }</option>
														</c:forEach> 
														
														 
                                                    </select>
                                                </div>
                                                
                                                
                                                
                                                  <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">
                                                 <spring:message code="plan.hide" /> [This Form]
                                                    </label>
                                                    
                                                    
                                                
                                                    <select  class="form-select select2" multiple="multiple" name="upload_pn_hidden">
                                                    
                                                         <c:set var="listcheck" value="${upload_pn.upload_pn_hidden }" />
                                                       <c:forEach items="${eformfields }" var="rows" varStatus="count">  
                                                       
                                                       
                                                   
                                                        <c:set var="listrespon" value="${rows.field_label }" />    
                                                       
                                                       
                                                        <c:set var="check" value="" />
					                                     <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
                                                                <option value="${rows.field_label }" ${check }>${rows.field_label }</option>
														</c:forEach> 
														
														    <c:set var="check" value="" />
														   <c:set var="listrespon" value="userinsert" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="userinsert" ${check }>*<spring:message code="common.userinsert"  /></option>
														
														    <c:set var="check" value="" />
														   <c:set var="listrespon" value="userrefer" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="userrefer" ${check }>*<spring:message code="common.userrefer"  /></option>
														
														
										
														
														
														
														
														    <c:set var="check" value="" />
														<c:set var="listrespon" value="usercheck" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="usercheck" ${check }>*<spring:message code="common.usercheck"  /></option>
														
														    <c:set var="check" value="" />
														<c:set var="listrespon" value="date" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="date" ${check }>*<spring:message code="common.date"  /></option>
														
														    <c:set var="check" value="" />
															   <c:set var="listrespon" value="datecheck" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="datecheck" ${check }>*<spring:message code="common.datecheck"  /></option>
														
														
													    <c:set var="check" value="" />
														<c:set var="listrespon" value="attached" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="attached" ${check }>*<spring:message code="common.attached"  /></option>
														
														
														    <c:set var="check" value="" />
																<c:set var="listrespon" value="status" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="status" ${check }>*<spring:message code="common.status"  /></option>
														
														
														    <c:set var="check" value="" />
																<c:set var="listrespon" value="notecheck" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="notecheck" ${check }>*<spring:message code="common.notecheck"  /></option>
														
														
                                         
													
														  </select> 
										  
                                                </div> 
                                                
                                                
                                                   <c:if test='${eformfields2.size() > 0}'>  
                                                <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">
                                                 <spring:message code="plan.hide" />   [Form Refer ]
                                                    </label>
                                                    
                                                    
                                                
                                                    <select  class="form-select select2" multiple="multiple" name="upload_pn_avg">
                                                    
                                                         <c:set var="listcheck" value="${upload_pn.upload_pn_avg }" />
                                                       <c:forEach items="${eformfields2 }" var="rows" varStatus="count">  
                                                       
                                                       
                                                   
                                                        <c:set var="listrespon" value="${rows.field_label }" />    
                                                       
                                                       
                                                        <c:set var="check" value="" />
					                                     <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
                                                                <option value="${rows.field_label }" ${check }>${rows.field_label }</option>
														</c:forEach> 
														
														    <c:set var="check" value="" />
														   <c:set var="listrespon" value="userinsert" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="userinsert" ${check }>*<spring:message code="common.userinsert"  /></option>
														
														
														    <c:set var="check" value="" />
														<c:set var="listrespon" value="userrefer" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="userrefer" ${check }>*<spring:message code="common.userrefer"  /></option>
														
														    <c:set var="check" value="" />
														<c:set var="listrespon" value="date" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="date" ${check }>*<spring:message code="common.date"  /></option>
														
														    <c:set var="check" value="" />
														<c:set var="listrespon" value="attached" /> 
														 <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
														<option value="attached" ${check }>*<spring:message code="common.attached"  /></option>
														
										
													
														  </select> 
										  
                                                </div>   
                                                
                                       
                                      
                                            		</c:if>		
                                            		
                                            		
                                            		    <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">
                                                 <spring:message code="plan.appfield" />
                                                    </label>
                                                    
                                                    
                                                
                                                    <select  class="form-select select2" multiple="multiple" name="upload_pn_appfield">
                                                    
                                                         <c:set var="listcheck" value="${upload_pn.upload_pn_appfield }" />
                                                       <c:forEach items="${eformfields }" var="rows" varStatus="count">  
                                                       
                                                       
                                                   
                                                        <c:set var="listrespon" value="${rows.field_label }" />    
                                                       
                                                       
                                                        <c:set var="check" value="" />
					                                     <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
                                                                <option value="${rows.field_label }" ${check }>${rows.field_label }</option>
														</c:forEach> 
														
													
														  </select> 
										  
                                                </div> 
                                                
                                                
                                            		
                                            		
                                            		
                                                     <br>  <br>  <br>  <br>  <br>  <br>
                                                 <br>  <br>  <br>  <br>  <br>  <br>
                                                 
                                                    </div>
                                                 
                                            <div class="tab-pane" id="tabVerticalLeft3" role="tabpanel" aria-labelledby="baseVerticalLeft-tab3">
                                            
                                     <div class="card-body pt-2">
                                        <p>
                                          <spring:message code="plan.plugin" />  
                                    
                                        </p>
   
                                        
                                          <input type="hidden" class="form-check-input"  name= "upload_pn_report" id="upload_pn_report" value="1">
                                        
                                        <div class="d-flex mt-2">
                                            <div class="flex-shrink-0">
                                                <img src="/resources/app-assets/images/icons/Pivot.png" alt="google" class="me-1" height="38" width="38">
                                            </div>
                                            
                                            
                                        
                                            
                                            
                                            <div class="d-flex align-item-center justify-content-between flex-grow-1">
                                                <div class="me-1">
                                                    <p class="fw-bolder mb-0">
                                                    <spring:message code="plan.plugin1" />
                                                    </p>
                                                    <span>
                                                    <spring:message code="plan.plugininfo1" />
                                                    </span>
                                                </div>
                                                <div class="mt-50 mt-sm-0">
                                                    <div class="form-check form-switch form-check-primary">
                                                        <input type="checkbox" class="form-check-input"  name= "upload_pn_pivot" id="upload_pn_pivot" value="1"   <c:if test='${upload_pn.upload_pn_pivot == 1 }'>checked</c:if>>
                                                        <label class="form-check-label" for="upload_pn_pivot">
                                                            <span class="switch-icon-left"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check"><polyline points="20 6 9 17 4 12"></polyline></svg></span>
                                                            <span class="switch-icon-right"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg></span>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-flex mt-2" style="display:none">
                                            <div class="flex-shrink-0"style="display:none">
                                                <img src="/resources/app-assets/images/illustration/faq-illustrations.svg" alt="slack" class="me-1" height="38" width="38">
                                            </div>
                                            <div class="d-flex align-item-center justify-content-between flex-grow-1">
                                                <div class="me-1" style="display:none">
                                                    <p class="fw-bolder mb-0">
                                                    <spring:message code="plan.plugin2" />
                                                    </p>
                                                    <span>
                                                    <spring:message code="plan.plugininfo2" />
                                                    </span>
                                                </div>
                                                <div class="mt-50 mt-sm-0" style="display:none">
                                                    <div class="form-check form-switch form-check-primary">
                                                        <input type="checkbox" class="form-check-input" id="checkboxSlack" name= "upload_pn_follow" id="upload_pn_follow" value="1" <c:if test='${upload_pn.upload_pn_follow == 1 }'>checked</c:if> >
                                                        <label class="form-check-label" for="upload_pn_follow">
                                                            <span class="switch-icon-left"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check"><polyline points="20 6 9 17 4 12"></polyline></svg></span>
                                                            <span class="switch-icon-right"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg></span>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                  
                                    </div>
                                            </div>
                                               <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1" >
                                        <spring:message code="btn.save" />
                                        
                                        </button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         
                                         
                                         <spring:message code="btn.cancel" />
                                        </button>
                                    </div>
                                        </div>
                                    </div>
                                </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->






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
      
      
      
      
      
      
    <div class="modal fade" id="addNewCards2" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog   <c:if test='${eformfields.size() <= 9 }'>modal-lg</c:if><c:if test='${eformfields.size() > 9 }'>modal-xl</c:if>  modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                       
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="forms.Fillininformation"  /></h1>

                           <form:form method="POST" modelAttribute="edata"  action="/addfiles2-filemanager?${_csrf.parameterName}=${_csrf.token}" onsubmit="return validateForm()"  id="form" enctype="multipart/form-data"> 
                      
                             
                                <span id="aboutdata" style="display:none;padding:10px;">
                                <div class="demo-spacing-0">
                                <div class="alert alert-primary alert-dismissible fade show" role="alert">
                                            <div class="alert-body">
                                              
                                             <spring:message code="forms.editinfo"  />
            <button type="button"   onclick="unedit()" class="btn btn-sm btn-light me-1 waves-effect waves-float waves-light">
              <spring:message code="btn.cancel"  />
           </button>
           
           
           
                                            </div>
                               
                                        </div>
                                        </div>
                                </span>
                                
                                
                    
                                <div class="card-body" style="overflow:hidden">
                      
     
                              
                                        <div class="row" >
                              
               
                              

<input type="hidden" name="data_planid"  id="data_planid"  value="${upload_pn.upload_pn_id  }">                                                           
<input type="hidden" name="data_eformid"  id="data_eformid"  value="${eformid+0 }">                                        
<input type="hidden" name="data_eformrv"  id="data_eformrv"  value="${eformrv +0}">  
<input type="hidden" name="data_dateplan"  id="data_dateplan"  value="${dateplan }">
<input type="hidden" name="report_id"  id="report_id"  value="0">  	   
<input type="hidden" name="data_id"  id="data_id"  value="0">  	  	  
<input type="hidden" name="data_plantype"  id="data_plantype"  value="${plantype }">  	 
<input type="hidden" name="edit"  id="edit"  value="0">     
<input type="hidden" name="port" value="${port}">    
<input type="hidden" name="data_filetype"  id="data_filetype"  value="">     


   <input type="hidden"   id="orders" class="form-control" title=""   value="0" readonly>
   <input type="hidden"    id="balance_befores" class="form-control" title="สต๊อก"  value="0" readonly>
   <input type="hidden"    id="amounts" class="form-control" title="สะสม"  value="0" readonly>


    <c:if test='${eformfields.size() > 9 }'>     
       <c:set var="size" value="${eformfields.size() / 2 }" />
       <c:set var="mdrows" value="4" />
       <c:set var="subrow" value="block" />
    </c:if>
       <c:if test='${eformfields.size() <= 9 }'>     
       <c:set var="mdrows" value="6" />
      <c:set var="subrow" value="none" />
       <c:set var="size" value="${eformfields.size() }" />
    </c:if>
    
      <c:set var="lot" value="0" />
   <div class="col-md-${mdrows } col-12">
                     <c:forEach items="${eformfields }" begin="0" end="${size }"  var="row" varStatus="count">
                                 <c:set var="title" value="${row.field_label }" />   
                       <c:set var="appfield" value="${upload_pn.upload_pn_appfield }" />
                          <c:if test="${!fn:contains(appfield,row.field_label)}"> 
                             
                        
 
                                         
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${title }</label>
                                                    
                                                 <c:if test='${row.field_type.equals("select")   }'>     
                                           
                                                 <select name="data_value${count.count }"  id="data_value${row.field_id }" class="form-select ${row.field_cal_type }"  onchange="copyValueToClass('${row.field_cal_type}')" >
                                                 </select>
                                                 
                                                 
                                               
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }" id="action_${row.field_cal_type }"  value="${row.field_cal_type2 }"/>
                                                  </c:if>
                                                  
                                    
                                           
                                           
                                           
                                   
                                           
                                           
                                           
                                                 
                                                   <c:if test='${row.field_type.equals("text")}'>   
                                                   
                                               
                                                      
                                                 <input type="text" name="data_value${count.count }"  id="data_value${row.field_id }" 
                                                 class="autocomplete-input ${row.field_cal_type } form-control"  onchange="copyValueToClass('${row.field_cal_type}')">
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }"   value="${row.field_cal_type2 }"/>
                                                  </c:if>
                                                  
                                                  
                                                  
                                    
                                                  
                                                    <c:if test='${row.field_type.equals("number")}'>
                                                  
                                            					      <input type="number" step="0.01" min="0" name="data_value${count.count }"  
                                            					      id="data_value${row.field_id }" 
                                            					        class="autocomplete-input ${row.field_cal_type } form-control"  onchange="copyValueToClass('${row.field_cal_type}')">
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }" id="action_${row.field_cal_type }"  value="${row.field_cal_type2 }"/>

                                             
                                                  </c:if>
                                                 
                                                 
                                         
                                                 
                                                    <c:if test='${row.field_type.equals("textarea")}'>      
                                                 <textarea name="data_value${count.count }"  id="data_value${row.field_id }" class="form-control"></textarea>
                                                  </c:if>
                                              <c:if test='${row.field_type.equals("date")}'>      
                                               <input type="date" name="data_value${count.count }"  id="data_value${row.field_id }" class="form-control">
                                                  </c:if>
                                                  
                                                   <c:if test='${row.field_type.equals("time")}'>      
                                              <input type="time" class="form-control  mydata  ${row.field_cal_type }"  name="data_value${count.count }" id="data_value${row.field_id }" 
                                               placeholder="hh:mm"  onchange="copyValueToClass('${row.field_cal_type}')">
                                          
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }"   value="${row.field_cal_type2 }"/>
                                                  </c:if> 
                                                  
                                               <c:if test='${row.field_type.equals("status")}'>      
                                               <div>
								                           <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
									                       <input type="radio" class="btn-check mydata" value="Good"  name="data_status" id="btnradio1" autocomplete="off" >
									                       <label class="btn btn-outline-success waves-effect" for="btnradio1">Good</label>
								
									                       <input type="radio" class="btn-check mydata" value="Normal"   name="data_status" id="btnradio2" autocomplete="off" checked>
									                       <label class="btn btn-outline-warning waves-effect" for="btnradio2">Normal</label>
								
									                       <input type="radio" class="btn-check mydata" value="Bad"  name="data_status" id="btnradio3" autocomplete="off">
									                       <label class="btn btn-outline-danger waves-effect" for="btnradio3">Bad</label>
									                       </div>
									                           </div>
                                                  </c:if> 
                                                  
                                                  
                                                  
                                                    <c:if test='${row.field_type.equals("checkbox")}'>
                                                 <br>      
                                                <span id="data_value${row.field_id }"></span>
                                                    </c:if>
                                             
                                           <c:set var="totaltitle" value="${count.count }" />
                                            
                                            
                                            <c:if test='${totaltitle == null}'>  <c:set var="totaltitle" value="1" />  </c:if> 
                                            
                                    <input type="hidden" name="data_title${count.count }"  id="data_title${row.field_id }"  value="${title }">     
                                   
                                   
                                    <input type="hidden" name="field_cal_type"  id="field_cal_type"  value="${row.field_cal_type }">         
                                     <input type="hidden" name="field_field_no"  id="field_field_no"  value="${row.field_field_no }">    
                                     
                                    </div>   
                                    
                                       </c:if> 
                              <c:if test="${fn:contains(appfield,row.field_label)}"> 
                                     <input type="hidden" class="form-control time-mask mydata"  name="data_value${count.count }" id="data_value${row.field_id }"  >
                                     <input type="hidden" name="data_title${count.count }"  id="data_title${row.field_id }"  value="${title }">     
                                    <input type="hidden" name="field_cal_type"  id="field_cal_type"  value="${row.field_cal_type }">         
                                     <input type="hidden" name="field_field_no"  id="field_field_no"  value="${row.field_field_no }">   
                                       <input type="hidden" name="data_title${count.count }"  id="data_title${row.field_id }"  value="${title }">     
                              </c:if>         
                        </c:forEach>
                        
                        
                            <c:if test='${lot == 0    }'>     
                                         <div class="lot" style="display:none"></div>
                                        </c:if>   
                                           
                        
                            </div> 
                            
                            
                            <div class="col-md-${mdrows } col-12" style="display:${subrow}">
                     <c:forEach items="${eformfields }" begin="${size+1 }"   var="row" varStatus="count2">
                                <c:set var="title" value="${row.field_label }" />
                             
                               <c:set var="appfield" value="${upload_pn.upload_pn_appfield }" />
                          <c:if test="${!fn:contains(appfield,row.field_label)}"> 
                             
                             
                           
 
                                         
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${title }</label>
                                                    
                                                 <c:if test='${row.field_type.equals("select") && !row.field_cal_type.equals("product") &&  !row.field_cal_type.equals("lot")  }'>     
                                           
                                                 <select name="data_value${count2.count+totaltitle }"  id="data_value${row.field_id }" class="form-select ${row.field_cal_type }"    onchange="copyValueToClass('${row.field_cal_type}')" >
                                           
                                                 </select>
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }" id="action_${row.field_cal_type }"  value="${row.field_cal_type2 }"/>
                                                  </c:if>
                                                  
                                
                                           
                             
                             
                             
                             
                             
                             
                                                 
                                                 
                                                   <c:if test='${row.field_type.equals("text")}'>      
                                                 <input type="text" name="data_value${count2.count+totaltitle }"  id="data_value${row.field_id }" 
                                                 class="autocomplete-input ${row.field_cal_type } form-control"  onchange="copyValueToClass('${row.field_cal_type}')">
                                                      <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                  </c:if>
                                                  
                                            
                                            
                                            
                                                           <c:if test='${row.field_type.equals("number")}'>
                                                  
                                            			<input type="number" step="0.01" min="0" name="data_value${count2.count+totaltitle }"  
                                            			 id="data_value${row.field_id }" class="autocomplete-input ${row.field_cal_type } form-control"  
                                            			 onchange="copyValueToClass('${row.field_cal_type}')">
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }" id="action_${row.field_cal_type }"   value="${row.field_cal_type2 }"/>

                                             
                                                  </c:if>
                                                 
                                                 
                                                 
                                        
                                            
                                                 
                                                    <c:if test='${row.field_type.equals("textarea")}'>      
                                                 <textarea name="data_value${count2.count+totaltitle }"  id="data_value${row.field_id }" class="form-control"></textarea>
                                                  </c:if>
                                              <c:if test='${row.field_type.equals("date")}'>      
                                               <input type="date" name="data_value${count2.count+totaltitle }"  id="data_value${row.field_id }" class="form-control">
                                                  </c:if>
                                                  
                                                   <c:if test='${row.field_type.equals("time")}'>      
                                              <input type="time" class="form-control mydata  ${row.field_cal_type }"  name="data_value${count2.count+totaltitle }" id="data_value${row.field_id }"  
                                              placeholder="hh:mm"     class="autocomplete-input ${row.field_cal_type } form-control"  onchange="copyValueToClass('${row.field_cal_type}')">
                                              
                                                     <input type="hidden" name="${row.field_cal_type }" id="${row.field_cal_type }"  value="${data_value }"/>
                                                   <input type="hidden" name="action_${row.field_cal_type }"   value="${row.field_cal_type2 }"/>
                                              
                                              
                                                  </c:if> 
                                                  
                                                 <c:if test='${row.field_type.equals("status")}'>    
                                                   <div>  
								                           <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
									                       <input type="radio" class="btn-check mydata" value="Good"  name="data_status"id="btnradio1" autocomplete="off" >
									                       <label class="btn btn-outline-success waves-effect" for="btnradio1">Good</label>
								
									                       <input type="radio" class="btn-check mydata" value="Normal"   name="data_status" id="btnradio2" autocomplete="off" checked>
									                       <label class="btn btn-outline-warning waves-effect" for="btnradio2">Normal</label>
								
									                       <input type="radio" class="btn-check mydata" value="Bad"  name="data_status" id="btnradio3" autocomplete="off">
									                       <label class="btn btn-outline-danger waves-effect" for="btnradio3">Bad</label>
									                       </div>
									                        </div>
                                                  </c:if> 
                                                  
                                                  
                                                    <c:if test='${row.field_type.equals("checkbox")}'>
                                                 <br>      
                                                <span id="data_value${row.field_id }"></span>
                                                    </c:if>
                                                </div>
                                         
                                            
                                    <input type="hidden" name="data_title${count2.count+totaltitle }"  id="data_title${row.field_id }"  value="${title }">       
                                     
                                     </c:if>
                                        <c:if test="${fn:contains(appfield,row.field_label)}"> 
                                     <input type="hidden" class="form-control time-mask mydata"  name="data_value${count2.count+totaltitle }" id="data_value${row.field_id }"  >
                                     <input type="hidden" name="data_title${count.count }"  id="data_title${row.field_id }"  value="${title }">     
                                    <input type="hidden" name="field_cal_type"  id="field_cal_type"  value="${row.field_cal_type }">         
                                     <input type="hidden" name="field_field_no"  id="field_field_no"  value="${row.field_field_no }">    
                                       <input type="hidden" name="data_title${count2.count+totaltitle }"  id="data_title${row.field_id }"  value="${title }">       
                              </c:if>   
                                                             
                        </c:forEach>
                            </div> 
                            
                     
                        
                        
                        
                                               <div class="col-md-${mdrows } col-12">
                                               
  
                                        
                                                <div class="mb-1">
                <input type="hidden" name="id" id="id" value="${idrefer }"  >
               <input type="hidden" name="level" id="level" value="${level }"  >
               <input type="hidden" name="upload_pn_ids" id="upload_pn_ids" value="${upload_pn.upload_pn_id }"  > 
               <input type="hidden" name="log_id" id="log_id" value="0"  > 
               
               
                 <c:if test="${upload_pn.upload_pn_filecheck == 1}">  
               <c:set var="filecheck" value="required" />
                 </c:if>
                             <c:if test="${ not empty upload_pn.upload_pn_name }">
                        <label class="form-label" for="first-name-column">1.${upload_pn.upload_pn_name }</label>  <br>
                                     <div class="input-group mb-1">
                            <span id="filedata1" class="filedata"></span>
                            
                            <span id="filedatas1" class="filedatas">
                                <a href="#" class="btn btn-light me-1 waves-effect waves-float waves-light" onclick="camera('files')" style="float:left">
                               Open camera
                         </a >
                              <input type="file" name="file1" id="files" class="form-control"   ${filecheck } style="width:50%;float:left;margin-left:10px;"  >
                 			  </span>
                            </div>
                          
                           </c:if>
                           
                              <c:if test="${  empty upload_pn.upload_pn_name }">
                                    <input type="file" name="file1" id="files" class="form-control"    style="display:none"  >
                            </c:if>
                            
                            
                            
                               <c:if test="${not empty  upload_pn.upload_pn_name2 }">
                               <label class="form-label" for="first-name-column">2.${upload_pn.upload_pn_name2 }</label><br>
                                      <div class="input-group mb-1">
                              
                              
                                <span id="filedata2" class="filedata"></span>
                            
                            <span id="filedatas2" class="filedatas">
                                <a href="#" class="btn btn-light me-1 waves-effect waves-float waves-light" onclick="camera('files2')" style="float:left">
                               Open camera
                         </a >
                              <input type="file" name="file2" id="files2" class="form-control"   ${filecheck }   style="width:50%;float:left;margin-left:10px;"  >
                           </span>
                         
                            </div>
                             </c:if>
                                <c:if test="${not empty  upload_pn.upload_pn_name3 }">
                               <label class="form-label" for="first-name-column">3.${upload_pn.upload_pn_name3 }</label><br>
                                      <div class="input-group mb-1">
                              
                              
                                 <span id="filedata3" class="filedata"></span>
                            
                            <span id="filedatas3" class="filedatas">
                                <a href="#" class="btn btn-light me-1 waves-effect waves-float waves-light" onclick="camera('files3')" style="float:left">
                               Open camera
                         </a >
                              <input type="file" name="file3" id="files3" class="form-control"   ${filecheck }  style="width:50%;float:left;margin-left:10px;"  >
                    </span>
                         
                            </div>
                             </c:if>
                                <c:if test="${not empty  upload_pn.upload_pn_name4}">
                               <label class="form-label" for="first-name-column">4.${upload_pn.upload_pn_name4 }</label><br>
                                      <div class="input-group mb-1">
                              
                                 <span id="filedata4" class="filedata"></span>
                            
                            <span id="filedatas4" class="filedatas">
                                <a href="#" class="btn btn-light me-1 waves-effect waves-float waves-light" onclick="camera('files4')" style="float:left">
                               Open camera
                         </a >
                              <input type="file" name="file4" id="files4" class="form-control"   ${filecheck }  style="width:50%;float:left;margin-left:10px;"  >
                           </span>
                            </div>
                             </c:if>
                                <c:if test="${not empty  upload_pn.upload_pn_name5 }">
                                
                       
                               <label class="form-label" for="first-name-column">5.${upload_pn.upload_pn_name5 }</label><br>
                                      <div class="input-group mb-1">
                             <span id="filedata5" class="filedata"></span>
                         <span id="filedatas5" class="filedatas">   
                                <a href="#" class="btn btn-light me-1 waves-effect waves-float waves-light" onclick="camera('files5')" style="float:left">
                               Open camera
                         </a >
                              <input type="file" name="file5" id="files5" class="form-control"   ${filecheck }  style="width:50%;float:left;margin-left:10px;"  >
              </span>
                            </div>
                             </c:if>
                            
                            
                   
                            
                            
                                                </div>
                                                  <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="common.userrefer"  /></label>
                                           
                                            
                                          <div class="input-group mb-1">
                               <select  class="form-select" name="namerefer" id="namerefer">
                               <c:set var="namesArray" value="${fn:split(upload_pnsall.upload_pn_respon, ',')}" />
								<option value=""><spring:message code="forms.Notspecified"  /></option>	
												<c:forEach var="nameItem" items="${namesArray}" begin="0" end="5">
									<option value="${nameItem}">${nameItem}</option>			
												</c:forEach>
                               </select>
                            </div>  
                            
                            
                            
                                            
                                                </div>
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">
                                                  
                                                             <spring:message code="forms.date"  />
                                                    </label>
                                           
                                            
                                          <div class="input-group mb-3">
                                <button class="btn btn-light btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">เลือกวันที่จากแผน</button>
                                <div class="dropdown-menu">
                                     <c:forEach items="${dateplan }" var="row" varStatus="count">   
                                    <a class="dropdown-item dropdownitem" href="#" data-custom="${row.report_pn_date }">${row.report_pn_date }</a>
                                    </c:forEach>
                                </div>
                                 <span >
                                 <input type="date"  name="due" id="fp-default" class="form-control flatpickr-basic" placeholder="YYYY-MM-DD" value="${due }"  style="width:100%;" required/>
                                 </span>
                            </div>  
                            
                            
                            
                                            
                                                </div>
                                                
                                                
                                                                   
               
                                                
                                                
                                            </div>   
                                            
                                            
                          
                              
                                            
                                            
                                            
                                       
                                                         <br><br>
                                           
                                            
                                   
                                      <hr>   
                          
                                       
                                       
                                       
                           
                   
                                       
                                       
                                            
            
               
                                      
                                            
                                         <div class="col-12">
                               
                                   
                        
               
                                           

                                          <c:if test='${upload_pn.upload_pn_respon == Member_name}'>  
                               
                                         
                        <c:if test="${upload_pn.turn == 0}">     
                                         
                              <button type="button"  id="submits" class="btn btn-primary me-1 waves-effect waves-float waves-light" 
                              onclick='uploadfile("/addfiles2-filemanager?${_csrf.parameterName}=${_csrf.token}","form","savestatus")'>
                                <spring:message code="btn.add" text="เพิ่ม" />
                               </button>   
                                <button type="button"   id="submits" class="btn btn-primary me-1 waves-effect waves-float waves-light "  
                              onclick='uploadfile("/addfiles2-filemanager?${_csrf.parameterName}=${_csrf.token}","form","savestatus")'      >
                              <spring:message code="btn.addandclose"  />
                               </button> 
                        </c:if>
              
                          </c:if>
                         <c:if test='${upload_pn.upload_pn_respon != Member_name}'> 
                      <button type="button"    >
                               Not access
                               </button>   
                           </c:if>     
                          <br> <br>
<div class="progress progress-striped" id="progress_div" style="display: none;">
<div class="progress-bar progress-bar-primary" role="progressbar" id="bar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 0%;
  background-image: -webkit-gradient(linear, 0 100%, 100% 0, color-stop(0.25, rgba(255, 255, 255, 0.15)), color-stop(0.25, transparent), color-stop(0.5, transparent), color-stop(0.5, rgba(255, 255, 255, 0.15)), color-stop(0.75, rgba(255, 255, 255, 0.15)), color-stop(0.75, transparent), to(transparent));
    background-image: -webkit-linear-gradient(45deg, rgba(255, 255, 255, 0.15) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.15) 50%, rgba(255, 255, 255, 0.15) 75%, transparent 75%, transparent);
    background-image: -moz-linear-gradient(45deg, rgba(255, 255, 255, 0.15) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.15) 50%, rgba(255, 255, 255, 0.15) 75%, transparent 75%, transparent);
    background-image: linear-gradient(45deg, rgba(255, 255, 255, 0.15) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.15) 50%, rgba(255, 255, 255, 0.15) 75%, transparent 75%, transparent);
    background-image: -webkit-linear-gradient(45deg,rgba(255,255,255,0.15) 25%,transparent 25%,transparent 50%,rgba(255,255,255,0.15) 50%,rgba(255,255,255,0.15) 75%,transparent 75%,transparent);
    background-image: linear-gradient(45deg,rgba(255,255,255,0.15) 25%,transparent 25%,transparent 50%,rgba(255,255,255,0.15) 50%,rgba(255,255,255,0.15) 75%,transparent 75%,transparent);
    background-size: 40px 40px;
    background-color: #3c8dbc;
">
<span id="percent" style="font-size:9px">0%</span>
</div>
</div>
                           
                         <iframe id="pdfViewer" style="width:100%;width;height:500px;border:1px solid black;display:none"></iframe>
                         
                       
                                            </div>
                                            
                                            
                                            
                        
   
                           
                                        </div>
                                 
                                        
                        <div  id="camera" style="display: none;">
             
                            <video id="cameraFeed" autoplay style="width:60%;margin-left:20%;zoom: 1;"></video><br><br>

                              <a href="#" id="captureButton" class="btn btn-primary me-1 waves-effect waves-float waves-light">ถ่ายรูป</a> 
    						  <a href="#" class="btn btn-primary me-1 waves-effect waves-float waves-light" onclick="generatePdf()">รวมเป็น PDF</a>
    						        <a href="#"  class="btn btn-light me-1 waves-effect waves-float waves-light" onclick="closes()">ยกเลิก</a> 
                             <br>
                             
                              <br>
                              
                              
                               <div class="row " id="card-drag-area">
                   
                    </div>
                              
                              
                        <canvas id="photoCanvas" style="display: none;"></canvas>
    						
    						
    								
    							
    									
    										
    												
    												
    											
    															
                              <br>
                              
                              
                               <div style="clear:both"></div>
                              
                              <br>  <br>  <br>
                                
                         
    						   
                          </div>     
                          
                          
                                </div>
                           
                            
                        
                      
               </form:form>
                  
                  
                  
                     <div id="eform" style="display:none">  </div>
                    
            	
                        
                           <form:form method="POST" id="calform">
                                
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                   <input  type="hidden" name="formid" id="formid"  value="">
                                   <input  type="hidden" name="field"  id="field"  value="">
                                   <input  type="hidden" name="value" id="value"  value="">
                                    <input  type="hidden" name="value2" id="value2"  value="">
                                   <input  type="hidden" name="product_no" id="product_nos"  value="">
                                   
                                   <input  type="hidden" name="amount_no" id="amount_no"  value="${amount_no }">
                                   <input  type="hidden" name="conditionid" id="conditionid"  value="${eformid}">

                                 
             
                            </form:form>
  
  
<div class="lot2"></div>
    	<script> 	
function copyValueToClass(fieldClass) {
	

    // หาค่า input แรกตาม class
    var sourceInput = document.querySelector('.' + fieldClass);
    
    // หาค่า input เป้าหมายที่ต้องการจะคัดลอกค่าไป
    var targetInput = document.getElementById(fieldClass);

    // คัดลอกค่าจาก input แรกไปยัง input ที่สอง
    if (sourceInput && targetInput) {

    	
        targetInput.value = sourceInput.value.toString();
    }
}
	</script> 
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->

	
      
      
      
      
<div id="savestatus"></div>
<%@ include file="common/mainfoot.jspf" %>  




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
	    var element = document.getElementById("nav_project");
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
        evaluateAndDisplay();
        evaluateAndDisplay2();
        sum();
	    
	    
	    
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
    document.getElementById("statusalls").style.display = "none";
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
    document.getElementById("statusalls").style.display = "none";
    document.getElementById("statusalls2").style.display = "none";
    document.getElementById("statusdocs").style.display = "none";
    document.getElementById("echarts").style.display = "none";
  
 //   evaluateAndDisplay();
   // evaluateAndDisplay2();
  //  sum();
</c:if>

    <c:if test="${group == 1  }">
    // เปลี่ยนแสดง id statusalls
    document.getElementById("statusalls").style.display = "block";
    document.getElementById("statusalls2").style.display = "block";
    // ซ่อน id statusdocs
     document.getElementById("eform").style.display = "none";
    document.getElementById("statusdocs").style.display = "none";
    document.getElementById("statusreports").style.display = "none";
   </c:if>


    
    
    
    
    
    
    
    
    
    /*
    document.getElementById("statusall4").addEventListener("click", function() {
        // เปลี่ยนแสดง id statusalls
        document.getElementById("statusalls").style.display = "block";
        document.getElementById("statusalls2").style.display = "block";
 	
        
        // ซ่อน id statusdocs
         document.getElementById("eform").style.display = "none";
        document.getElementById("statusdocs").style.display = "none";
        document.getElementById("statusreports").style.display = "none";
    });
    */
    
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
    
    
    
    /*
    document.getElementById("statusreport").addEventListener("click", function() {
        // เปลี่ยนแสดง id statusdocs
        document.getElementById("statusreports").style.display = "block";
        document.getElementById("echarts").style.display = "block";
        // ซ่อน id statusalls
        document.getElementById("eform").style.display = "none";
        document.getElementById("statusalls").style.display = "none";
        document.getElementById("statusalls2").style.display = "none";
        document.getElementById("statusdocs").style.display = "none";
        
        sum();
        
        
    });
    
    */
    
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
    
const download = type => {

	  const el = document.getElementById("data-table");
	  const file = XLSX.utils.table_to_book(el);
	  // Configuring width of the columns:
	  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
	  return XLSX.writeFile(file, "UPLOAD." + type);
	};
	
	
	const download2 = type => {

		  const el = document.getElementById("data-table2");
		  const file = XLSX.utils.table_to_book(el);
		  // Configuring width of the columns:
		  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
		  return XLSX.writeFile(file, "UPLOAD." + type);
		};
		
		
		const download3 = type => {

			  const el = document.getElementById("data-table3");
			  const file = XLSX.utils.table_to_book(el);
			  // Configuring width of the columns:
			  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
			  return XLSX.writeFile(file, "UPLOAD." + type);
			};
			const download4 = type => {

				  const el = document.getElementById("tabledata");
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





<div class="modal fade" id="addRoleModal4" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role" >
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                                <div class="text-center mb-4">
                                    <h1 class="role-title">มอบหมายงาน</h1>
                                    <p>มอบหมายงานอื่นๆให้ทีมงานของคุณ</p>
                                </div>
                                <!-- Add role form -->
                             <div id="divdata"></div>
                            
                       
                                <!--/ Add role form -->
                               <!--/ Add role form -->
                      <c:set var="listcheck" value="${Member_name}" />  
                             <c:set var="list2" value="${upload_pnsall.upload_pn_app}" /> 
                              <c:if test='${fn:contains(list,listcheck)}'>
                              
                         <c:if test='${upload_pn.project == 0}'>            
                       <a href="/run_project-filemanager?id=${upload_pn.upload_pn_id}" class="btn btn-sm btn-success" onclick="return confirm('Are you sure?')"> เริ่มโครงการ </a>    
                          </c:if>
                       
                         <c:if test='${upload_pn.project == 1}'>       
                      <a href="/close_project-filemanager?id=${upload_pn.upload_pn_id}" class="btn btn-sm btn-light" onclick="return confirm('Are you sure?')"> ปิดโครงการ </a>
                         </c:if>
                       
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

  











<div class="modal fade" id="addRoleModal" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                                <div class="text-center mb-4">
                                    <h1 class="role-title">มอบหมายงาน</h1>
                                    <p>มอบหมายงานอื่นๆให้ทีมงานของคุณ</p>
                                </div>
                                <!-- Add role form -->
                              <form:form method="POST" modelAttribute="task" action="/addtask-filemanager">
                                
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                 <input  type="hidden" name="id" value="${id}">
                                  <input  type="hidden" name="step" value="${step}">
                                   <input  type="hidden" name="group" value="${group}">
                                
                                <div  class="row">
                                    <div class="col-12">
                                        <label class="form-label" for="modalRoleName">ชื่องาน</label>
                                        <input type="text" id="modalRoleName" name="task_name"  class="form-control" required >
                                    </div>
                                    <br>
                                    <div class="col-12">
                                        <label class="form-label" for="modalRoleName">คำอธิบายงาน</label>
                                        <textarea  class="form-control" name="task_note" rows="10" required></textarea>
                                    </div>
                                    
                                   
                                    
                                    
                                    
                                         <div class="col-6">
                                        <label class="form-label" for="modalRoleName">วันที่เริ่ม</label>
                                       <input type="date" id="modalRoleName" name="task_datestart" class="form-control" required >
                                    </div>
                                        <div class="col-6">
                                        <label class="form-label" for="modalRoleName">วันที่สิ้นสุด</label>
                                         <input type="date" id="modalRoleName" name="task_dateend"  class="form-control" required >
                                    </div>
                                             <div class="col-6">
                                        <label class="form-label" for="modalRoleName">งบประมาณ</label>
                                       <input type="number" id="modalRoleName" name="task_budget" class="form-control" min="0.1"  >
                                    </div>
                                            <div class="col-6">
                                        <label class="form-label" for="modalRoleName">การเก็บเงินจากลูกค้า</label>
                                       <input type="number" id="modalRoleName" name="task_payment" class="form-control" min="0.1"  >
                                    </div>
                                  </div>   
                                    
                                         <br>
                                    <div class="col-12 text-center mt-2">
                                        <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">บันทึก</button>
                                    </div>
                            </form:form>
                            
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
                </div>

  
  

            <div class="modal modal-slide-in sidebar-todo-modal fade" id="new-task-modal2" >
                            <div class="modal-dialog sidebar-lg"  style="width:800px">
                                <div class="modal-content p-0">
                                    <form id="form-modal-todo" class="todo-modal needs-validation" novalidate onsubmit="return false">
                                        <div class="modal-header align-items-center mb-1">
                                            <h5 class="modal-title">Register</h5>
                                            <div class="todo-item-action d-flex align-items-center justify-content-between ms-auto">
                                              
                                                <i data-feather="x" class="cursor-pointer" data-bs-dismiss="modal" stroke-width="3"></i>
                                            </div>
                                        </div>
                                        <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                       
                                         <div class="todo-task-list-wrapper list-group" id="wrappermain">
                                 <input type="text" id="searchInput" placeholder="ค้นหา..." class="form-control mb-3">
                                 
                                 
                                                    <table class="table" id="Register">
                                            
                                            <thead>
                          <tr>
                      <th>PD</th>
                      <th>Batch</th>
                      <th>Product</th>
                        <th>Customer</th>
                       <th>ยอดสั่งผลิต</th>
                       <th>ยอดสั่งผลิตรวม</th>
                       <th>ใช้</th>
                        </tr>  
                        </thead>
                   <c:forEach items="${steps }" var="row" varStatus="count">  
                           <tr>
                        <td style="text-align: left;" data-custom="pd">${row.production_directive}</td>
                           <td style="text-align: left;"  data-custom="batch">${row.batch}</td>
                        <td style="text-align: left;"  data-custom="product">${row.product}</td>
                           <td style="text-align: left;"  data-custom="customer">${row.customer}</td>
                          <td style="text-align: left;" >${row.order_quantity}</td>
                               <td style="text-align: left;" >${row.total_order_quantity}</td>
                            <td style="text-align: left;"><a href="#" class="btn btn-sm btn-success btn-new"   >เลือก</a></td>
                        </tr>
                        
                   </c:forEach>  
                   
                   
                   
                        </table> 
                        
       <script>
    $(document).ready(function() {
        // ฟังก์ชันกรองข้อมูลเมื่อพิมพ์ลงในช่องค้นหา
        $("#searchInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#Register tbody tr").filter(function() {
                // ซ่อนหรือแสดงแถวตามการค้นหา
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
            });
        });
    });
</script>                 
                        
        <script>      
        $(".btn-new").on("click", function(event) {
            event.preventDefault();

            // ปิด Modal ปัจจุบัน
            const currentModal = $(this).closest(".modal");
            currentModal.modal('hide');

            // รอให้ Modal ปิดแล้วค่อยเปิด Modal ใหม่
            currentModal.on('hidden.bs.modal', function() {
                $("#addNewCards2").modal('show');
            });
        });
        
        
      $("#Register").on("click", "a", function(event) {
    event.preventDefault();

    // ดึงข้อมูลจากแถวที่กดเลือก
    const row = $(this).closest("tr");
    const pd = row.find("td[data-custom='pd']").text();
    const batch = row.find("td[data-custom='batch']").text();
    const product = row.find("td[data-custom='product']").text();
    const customer = row.find("td[data-custom='customer']").text();

    // นำข้อมูลที่ได้ไปใส่ใน input ที่ต้องการ
    $(".production_directive").val(pd);
    $(".batch").val(batch);
    $(".product").val(product);
    $(".customer").val(customer);
    
    
    $("#production_directive").val(pd);
    $("#batch").val(batch);
    $("#product").val(product);
    $("#customer").val(customer);
});
      </script>  
                                 
                                       </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                   
  
  
  
  
   <div class="modal modal-slide-in sidebar-todo-modal fade" id="new-task-modal3" >
                            <div class="modal-dialog sidebar-lg"  style="width:800px">
                                <div class="modal-content p-0">
                                    <form id="form-modal-todo" class="todo-modal needs-validation" novalidate onsubmit="return false">
                                        <div class="modal-header align-items-center mb-1">
                                            <h5 class="modal-title">Register</h5>
                                            <div class="todo-item-action d-flex align-items-center justify-content-between ms-auto">
                                              
                                                <i data-feather="x" class="cursor-pointer" data-bs-dismiss="modal" stroke-width="3"></i>
                                            </div>
                                        </div>
                                        <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                       
                                         <div class="todo-task-list-wrapper list-group" id="wrappermain">
                                 <input type="text" id="searchInput2" placeholder="ค้นหา..." class="form-control mb-3">
                                 
                                 
                                                    <table class="table" id="Register2">
                                            
                                            <thead>
                          <tr>
                      <th>material</th>
                      <th>manufacturer</th>
                      <th>Batch ผู้ผลิต</th>
                       <th>ใช้</th>
                        </tr>  
                        </thead>
                   <c:forEach items="${steps2 }" var="row" varStatus="count">  
                       
                                  <c:if test="${row.material != '' }">
                           <tr>
                        <td style="text-align: left;" data-custom="material">${row.material}</td>
                           <td style="text-align: left;" >${row.manufacturer}</td>
                        <td style="text-align: left;"  data-custom="batch_material">${row.batch_material}</td>
                            <td style="text-align: left;"><a href="#" class="btn btn-sm btn-success btn-new"   >เลือก</a></td>
                        </tr>
                        </c:if>
                   </c:forEach>  
                   
                   
                   
                        </table> 
                        
       <script>
    $(document).ready(function() {
        // ฟังก์ชันกรองข้อมูลเมื่อพิมพ์ลงในช่องค้นหา
        $("#searchInput2").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#Register2 tbody tr").filter(function() {
                // ซ่อนหรือแสดงแถวตามการค้นหา
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
            });
        });
    });
</script>                 
                        
        <script>      
        $(".btn-new").on("click", function(event) {
            event.preventDefault();

            // ปิด Modal ปัจจุบัน
            const currentModal = $(this).closest(".modal");
            currentModal.modal('hide');

            // รอให้ Modal ปิดแล้วค่อยเปิด Modal ใหม่
            currentModal.on('hidden.bs.modal', function() {
                $("#addNewCards3").modal('show');
            });
        });
        
        
      $("#Register2").on("click", "a", function(event) {
    event.preventDefault();

    // ดึงข้อมูลจากแถวที่กดเลือก
    const row = $(this).closest("tr");
    const pd = row.find("td[data-custom='material']").text();


    // นำข้อมูลที่ได้ไปใส่ใน input ที่ต้องการ
    $(".material").val(material);
    $("#material").val(material);
});
      </script>  
                                 
                                       </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                   
  
  
  
  
  
  
  
  
   <div class="modal modal-slide-in sidebar-todo-modal fade" id="new-task-modal4" >
                            <div class="modal-dialog sidebar-lg"  style="width:800px">
                                <div class="modal-content p-0">
                                    <form id="form-modal-todo" class="todo-modal needs-validation" novalidate onsubmit="return false">
                                        <div class="modal-header align-items-center mb-1">
                                            <h5 class="modal-title">Register</h5>
                                            <div class="todo-item-action d-flex align-items-center justify-content-between ms-auto">
                                              
                                                <i data-feather="x" class="cursor-pointer" data-bs-dismiss="modal" stroke-width="3"></i>
                                            </div>
                                        </div>
                                        <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                       
                                         <div class="todo-task-list-wrapper list-group" id="wrappermain">
                                 <input type="text" id="searchInput3" placeholder="ค้นหา..." class="form-control mb-3">
                                 
                                 
                                                    <table class="table" id="Register3">
                                            
                                            <thead>
                          <tr>
                      <th>part</th>
                       <th>Batch</th>
                       <th>ใช้</th>
                        </tr>  
                        </thead>
                   <c:forEach items="${steps3 }" var="row" varStatus="count">  
                           <tr>
                        <td style="text-align: left;" data-custom="part">${row.part}</td>
                        <td style="text-align: left;"  data-custom="batch">${row.batch}</td>
                            <td style="text-align: left;"><a href="#" class="btn btn-sm btn-success btn-new"   >เลือก</a></td>
                        </tr>
                        
                   </c:forEach>  
                   
                   
                   
                        </table> 
                        
       <script>
    $(document).ready(function() {
        // ฟังก์ชันกรองข้อมูลเมื่อพิมพ์ลงในช่องค้นหา
        $("#searchInput3").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#Register3 tbody tr").filter(function() {
                // ซ่อนหรือแสดงแถวตามการค้นหา
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
            });
        });
    });
</script>                 
                        
        <script>      
        $(".btn-new").on("click", function(event) {
            event.preventDefault();

            // ปิด Modal ปัจจุบัน
            const currentModal = $(this).closest(".modal");
            currentModal.modal('hide');

            // รอให้ Modal ปิดแล้วค่อยเปิด Modal ใหม่
            currentModal.on('hidden.bs.modal', function() {
                $("#addNewCards4").modal('show');
            });
        });
        
        
      $("#Register3").on("click", "a", function(event) {
    event.preventDefault();

    // ดึงข้อมูลจากแถวที่กดเลือก
    const row = $(this).closest("tr");
    const part = row.find("td[data-custom='part']").text();
    const batch = row.find("td[data-custom='batch']").text();

    // นำข้อมูลที่ได้ไปใส่ใน input ที่ต้องการ
    $(".part").val(part);
    $(".batch").val(batch);
    
    $("#part").val(part);
    $("#batch").val(batch);
});
      </script>  
                                 
                                       </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                   
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
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
        //  console.log(maxHeight);
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
      var inp  = document.getElementById(fileid);
      const dT = new DataTransfer();
      dT.items.add(new File([blob], 'created_auto.pdf'));
      inp.files = dT.files;
        
       
       
      }
   

    function generatejpg(button) {
    	
    	 $("#camera").toggle();
    	 $("#formss").toggle();
    	
        // หา element <img> ที่อยู่ใน <div> ที่มีปุ่มที่ถูกคลิก
        const parentDiv = button.closest('.card');
        const capturedPhoto = parentDiv.querySelector('.capture');

        // ดึงข้อมูล URL ของรูปถ่าย
        const imageBase64 = capturedPhoto.src;

        // สร้าง blob จากข้อมูล URL ของรูปถ่าย
        fetch(imageBase64)
            .then(response => response.blob())
            .then(blob => {
                // สร้าง DataTransfer object และเพิ่มไฟล์ blob เข้าไป
                const dT = new DataTransfer();
                dT.items.add(new File([blob], 'created_auto.jpeg'));

                // หา element ที่มี id เป็น 'file'
                var inp = document.getElementById(fileid);

                // กำหนดไฟล์ให้กับ element input
                inp.files = dT.files;
            })
            .catch(error => console.error('Error:', error));
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

        newDiv.innerHTML = '<div class="card"><div class="card-header"> <h4 class="card-title">Card '+divCount+'</h4><a href="#" onclick="generatejpg(this)"><i data-feather="check-circle"></i></a><a href="#" onclick="Invert(this)"><i data-feather="rotate-ccw"></i></a><button type="button" class="btn-close" onclick="deleteCard(this)"></button></div> <div class="card-body"><img id="capturedPhoto'+divCount+'" class="capture" style="width:100%;float:left;margin-left:5px" alt="รูปถ่าย"> </div></div>';

        cardDragArea.appendChild(newDiv); // เพิ่ม div ใหม่ลงใน card-drag-area
        

                feather.replace({
                    width: 14,
                    height: 14
                });
        
        
    }

    
    
    function closes(){

   	 $("#camera").toggle();
	 $("#formss").toggle();
	 
    }
    
    
    let cameraOpened = true;
    var fileid;
    
     function camera(id){
    	 
    	 
    	 fileid = id;
    	 $("#camera").toggle();
    	 $("#formss").toggle();
    	 
    	
    if(cameraOpened){
    	 cameraOpened = false;
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
      
       

    
          
          
          
     }
    
    
 
    </script>
    
    
    
    
    
    
    <c:if test="${save == 'success'}">    
        <script>

    var statusForm = document.getElementById("statuseform");
        statusForm.click();
</script>
 
  </c:if>
  <script>
    // หากมีการคลิกที่รายการใน dropdown
    document.querySelectorAll('.dropdownitem').forEach(function(item) {
        item.addEventListener('click', function(event) {
            // ป้องกันการโหลดหน้าใหม่ (หรือการส่ง form) ที่เป็นการเซิร์ฟเวอร์
            event.preventDefault();
            
            // ดึงค่า data-custom จากรายการที่ถูกคลิก
            var selectedDate = item.getAttribute('data-custom');
            
            // กำหนดค่าที่เลือกใน input field
            document.getElementById('fp-default').value = selectedDate;
        });
    });
</script>







  <script>

  function  sum(){
	  
<c:forEach items="${eformfields }" var="rows" varStatus="count">  



var total = 0;
$('tr:has(td[data-custom="${rows.field_label }"]:visible)').each(function() {
    var value = parseInt($(this).find('td[data-custom="${rows.field_label }"]').text());
    if (!isNaN(value)) {
        total += value;
    }
});


$('#${rows.field_label }').text(total);



</c:forEach>  



<c:forEach items="${eformfields2 }" var="rows" varStatus="count">  

<c:if test="${fn:contains(alowtitle,count.count)}">  

var total = 0;
$('tr:has(td[data-custom="${rows.field_label }"]:visible)').each(function() {
    var value = parseInt($(this).find('td[data-custom="${rows.field_label }"]').text());
    if (!isNaN(value)) {
        total += value;
    }
});
	$('#${rows.field_label }').text(total);

</c:if>

</c:forEach>  

  }


  
  $(document).ready(function() {

	  $(".toggle-accordion").on("click", function() {
	    var accordionId = $(this).attr("accordion-id"),
	      numPanelOpen = $(accordionId + ' .collapse.in').length;
	    
	    $(this).toggleClass("active");

	    if (numPanelOpen == 0) {
	      openAllPanels(accordionId);
	    } else {
	      closeAllPanels(accordionId);
	    }
	  })

	  openAllPanels = function(aId) {
	    console.log("setAllPanelOpen");
	    $(aId + ' .panel-collapse:not(".in")').collapse('show');
	  }
	  closeAllPanels = function(aId) {
	    console.log("setAllPanelclose");
	    $(aId + ' .panel-collapse.in').collapse('hide');
	  }
	     
	});
  
  
  
</script>









<!-- Chart code -->	



  <c:if test="${e_report.status == 0}"> 
 <script>
 $("#accordion").show();
 </script>
 
<style> 
#chartdiv {
  width: 100%;
  height: 500px;
}
#chartdiv2 {
  width: 100%;
  height: 500px;
}
#chartdiv3 {
  width: 100%;
  height: 500px;
}
</style>
</c:if>



<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
<script src="https://cdn.amcharts.com/lib/5/percent.js"></script>


<script>
<%@ include file="eformparts/chart1.jsp" %>

</script>	


<c:if test="${e_report.status == 0}"> 	
<script>
var value =  $("td[data-custom='${e_report.column_data }']").text();	
if("${e_report.column_data }" != "lable_num"  && Number.isNaN(Number(value)) ){	
	<%@ include file="eformparts/chart2.jsp" %>
}else{	
	<%@ include file="eformparts/chart2s.jsp" %>
}	
</script>	


<script>
<%@ include file="eformparts/chart3.jsp" %>
</script>		
</c:if>



<!-- Chart code -->	







<script>
function unedit(){
	
	var upload_pn_ids = document.getElementById("upload_pn_ids").value;
	var level = document.getElementById("level").value;
	var id = document.getElementById("id").value;
	
	   var form = document.getElementById("form");
	    form.reset(); // ล้างข้อมูลในฟอร์ม
	
	    
	    
	$("#aboutdata").hide();	
	$(".filedatas").show();	
	$(".filedata").html('');	
	$("#edit").val(0);
	$("#data_id").val(0);
	$("#data_filetype").val("");
	$("#report_id").val(0);	
	
	
	 document.getElementById("upload_pn_ids").value = upload_pn_ids;
	    document.getElementById("level").value = level;
	    document.getElementById("id").value = id;
	    document.getElementById("log_id").value = 0;
}


function Invert(button) {
    var capturedPhoto = document.getElementById("capturedPhoto1");
    
    // ตรวจสอบการหมุนปัจจุบันของภาพ
    var currentRotation = capturedPhoto.style.transform;
    if (currentRotation === "rotate(180deg)") {
        // หากภาพถูกหมุน 180 องศาให้หมุนกลับมาที่ตำแหน่งเริ่มต้น
        capturedPhoto.style.transform = "rotate(0deg)";
    } else {
        // หากภาพไม่ได้ถูกหมุนให้หมุน 180 องศา
        capturedPhoto.style.transform = "rotate(180deg)";
    }
}


function  sw(div){
	$("#chartdiv").hide();
	$("#chartdiv2").hide();
	$("#chartdiv3").hide();

	$("#"+div).show();
}

</script>




   <script>
  // ฟังก์ชันสำหรับแสดงผลลัพธ์ตามตัวกรอง
  
  
	  var userIndex = $("tr td[data-custom='ผู้เพิ่ม']").index();
	  var dateIndex = $("tr td[data-custom='data_due']").index();
	  var userIndex2 = $("tr td[data-custom='ผู้เพิ่ม2']").index();
	  var dateIndex2 = $("tr td[data-custom='data_due2']").index();
	  

  function applyFilters() {
	  

    var selectedUser = document.getElementById("userFilter").value;
    var selectedDate = document.getElementById("dateFilter").value;
    
    
  
    var rows = document.getElementById("tabledata").getElementsByTagName("tr");

    for (var i = 1; i < rows.length; i++) {
      var row = rows[i];
      var cells = row.getElementsByTagName("td");
      
      
      if(userIndex != 0 &&   dateIndex != 0){
   
    var user = cells[userIndex] ? cells[userIndex].textContent : "";
    var date = cells[dateIndex] ? cells[dateIndex].textContent : "";
 
      
      

			   //   var date = row.querySelector("[data-customer='data_due']").textContent; 
				  //var user = row.querySelector("[data-customer='ผู้เพิ่ม']").textContent;
			      
			      var showRow = true;
			
			
			      
			      // ตรวจสอบตัวกรองผู้ใช้
			      if (selectedUser !== "all" && user !== selectedUser) {
			        showRow = false;
			      }
			
			      // ตรวจสอบตัวกรองวันที่
			      if (selectedDate !== "all") {
			        var today = new Date();
			        var filterDate = new Date(date);
			        
			       
			        
			        
			        if (selectedDate === "today" && filterDate.toDateString() !== today.toDateString()) {
			          showRow = false;
			        } else if (selectedDate === "7days" && (today - filterDate) > 7 * 24 * 60 * 60 * 1000) {
			          showRow = false;
			        } else if (selectedDate === "thisMonth" && filterDate.getMonth() !== today.getMonth()) {
			          showRow = false;
			        } else if (selectedDate === "thisYear" && filterDate.getFullYear() !== today.getFullYear()) {
			          showRow = false;
			      }
			        else if (selectedDate === "1" && filterDate.getMonth() !== 0 ) {
			            showRow = false;
			          } 
			          else if (selectedDate === "2" && filterDate.getMonth() !== 1 ) {
			              showRow = false;
			            } 
			            
			          else if (selectedDate === "3" && filterDate.getMonth() !== 2) {
			              showRow = false;
			            } 
			            
			          else if (selectedDate === "4" && filterDate.getMonth() !== 3 ) {
			              showRow = false;
			            } 
			            
			          else if (selectedDate === "5" && filterDate.getMonth() !== 4) {
			              showRow = false;
			            } 
			            
			          else if (selectedDate === "6" && filterDate.getMonth() !== 5 ) {
			              showRow = false;
			            } 
			            
			          else if (selectedDate === "7" && filterDate.getMonth() !== 6) {
			              showRow = false;
			            } 
			            
			          else if (selectedDate === "8" && filterDate.getMonth() !== 7 ) {
			              showRow = false;
			            } 
			            
			          else if (selectedDate === "9" && filterDate.getMonth() !== 8 ) {
			              showRow = false;
			            } 
			            
			          else if (selectedDate === "10" && filterDate.getMonth() !== 9 ) {
			              showRow = false;
			            } 
			            
			          else if (selectedDate === "11" && filterDate.getMonth() !== 10) {
			              showRow = false;
			            } 
			            
			          else if (selectedDate === "12" && filterDate.getMonth() !== 11 ) {
			              showRow = false;
			            } 
			        
			        
			        else if (parseInt(selectedDate) >= 1 && parseInt(selectedDate) <= 12 && filterDate.getMonth() !== parseInt(selectedDate) - 1) {
			            // Compare the selected month (after converting to integer) with filterDate's month
			            showRow = false;
			        }
			        
			        
			        
			      }
			
			      // แสดงหรือซ่อนแถวตามผลตรวจสอบ
			      row.style.display = showRow ? "" : "none";
			      
      }
      
    }

   
    
   


    runchartdiv1();
    runchartdiv2();
    runchartdiv3();  

  }


   document.getElementById("userFilter").addEventListener("change", applyFilters);
  document.getElementById("dateFilter").addEventListener("change", applyFilters);

  
  $("#userFilter").change(function() {
      sum();
  });
  $("#dateFilter").change(function() {
      sum();
  });
  
  
  // แสดงผลลัพธ์เริ่มต้น
  //applyFilters();
    
   runchartdiv1();
   runchartdiv2();
   runchartdiv3();

   
   
   

   
   
   
</script>             
    
   
   <script>
    function evaluateAndDisplay() {

    	/*
      // ดึงทุก tr ที่มี class เป็น "inputDiv"
      var inputDivs = document.querySelectorAll('.inputDiv');

      // วนลูปทุก inputDivs
      inputDivs.forEach(function(inputDiv) {
    
    	    try {  
    	  // ดึงค่านิพจน์จาก attribute data-expression
        var expression = inputDiv.getAttribute('data-expression');
    	//alert(expression);
        // นำนิพจน์มาประมวลผล
   if(expression && expression != '' && expression != null ){
	      // นำนิพจน์มาประมวลผล
	     
	      
	      var result = eval(expression);

	      // แสดงผลลัพธ์ใน innerText ของ tr
	      
	      if(result == "undefined"){
	    	  result = expression;
	      }
	      
	      inputDiv.innerText = result;
	   
   }

    } catch (error) {
        
    }
        
        
      });
      
      */
    }
  </script>
   
 <script>
    function evaluateAndDisplay2() {
    	

    	/*
      // ดึงทุก tr ที่มี class เป็น "inputDiv"
      var inputDivs = document.querySelectorAll('[class^="inputDiv"]');

      
      var i1 =0;var i2 =0;var i3 =0;var i4 =0;var i5 =0;
      var i6 =0;var i7 =0;var i8 =0;var i9 =0;var i10 =0;
      var i11 =0;var i12 =0;var i13 =0;var i14 =0;var i15 =0;
      var i16 =0;var i17 =0;var i18 =0;var i19 =0;var i20 =0;
      // วนลูปทุก inputDivs
      var x =0;
      inputDivs.forEach(function(inputDiv) {
        // ดึงค่าตัวเลขจาก innerText


        x++;
        
        // ดึงค่านิพจน์จาก attribute data-expression
    try { 
        var expression = inputDiv.getAttribute('data-expression');
       
     
        
        if(expression  && expression != '' && expression != null    ){
        	
    
        	
        	  if(x == 1 ){i1 = expression;}if(x == 2 ){ i2 = expression; }if(x == 3 ){  i3 = expression;}if(x == 4 ){  i4 = expression;}if(x == 5 ){  i5 = expression;}
        }
      
        
       if( expression == "end" ){
    	   i1 =0; i2 =0; i3 =0; i4 =0; i5 =0;
    	   i6 =0; i7 =0; i8 =0; i9 =0; i10 =0;
    	   i11 =0; i12 =0; i13 =0; i14 =0; i15 =0;
    	   i16 =0; i17 =0; i18 =0; i19 =0; i20 =0;
    	   x=0;
       }
        
        
        
        // แทนที่ตัวเลขในนิพจน์ด้วยค่าที่ได้จาก innerText
       expressions = expression.replace(/#1/g, i1).replace(/#2/g, i2).replace(/#3/g, i3).replace(/#4/g, i4).replace(/#5/g, i5)
       .replace(/#6/g, i6).replace(/#7/g, i7).replace(/#8/g, i8).replace(/#9/g, i9).replace(/#10/g, i10)
       .replace(/#11/g, i11).replace(/#12/g, i12).replace(/#13/g, i13).replace(/#14/g, i14).replace(/#15/g, i15)
        .replace(/#16/g, i16).replace(/#17/g, i17).replace(/#18/g, i18).replace(/#19/g, i19).replace(/#20/g, i20);
        
	
        
   
            // นำนิพจน์มาประมวลผล
            var result = eval(expressions);
         
  	    
  	  
            // แสดงผลลัพธ์ใน innerText ของ tr
             if(expression  && expression != '' && expression != null    ){
           				 inputDiv.innerText = result;
             }
            
            
          } catch (error) {
        	  
          }
              
   

      });
      
      */
    }
</script>















<script>


  function getlot() {
	  
	
	  
    // Get the selected value
    var selectedValue = document.getElementsByClassName("product")[0].value;
    var selectedOption = document.getElementsByClassName("product")[0].querySelector("option:checked");
    var value = selectedOption.value;
    var formid = selectedOption.getAttribute("data-custom");
    
    
    var field =0
    if(document.getElementById("lot_no")){
    	  field = document.getElementById("lot_no").value;
    }
  
    
    var product_no = document.getElementById("product_no").value;
    
    
    
  	$("#formid").val(formid);
  	 $("#field").val(field);
    $("#value").val(value);
  	 $("#product_nos").val(product_no);
    
   
  	postdatacal("/getlot-filemanager?${_csrf.parameterName}=${_csrf.token}","calform","lot");

    //console.log("Selected value: " + selectedValue);
    // You can perform any additional actions here based on the selected value
  }
</script>

<script>
  function getlot2() {
	  
	  

	  
	  
    // Get the selected value
    var selectedValue = document.getElementsByClassName("product")[0].value;
    var selectedOption = document.getElementsByClassName("product")[0].querySelector("option:checked");
    var value = selectedOption.value;
    var formid = selectedOption.getAttribute("data-custom");

    var field = document.getElementById("lot_no").value;
    var product_no = document.getElementById("product_no").value;
   
    
    var selectedOption2 = document.getElementsByClassName("lot")[0].querySelector("option:checked");
    var value2 = selectedOption2.value;


    $("#formid").val(formid);
    $("#field").val(field);
    $("#value").val(value);
    $("#product_nos").val(product_no);
    $("#value2").val(value2);

    //alert(value2);

    postdatacal("/getlot2-filemanager?${_csrf.parameterName}=${_csrf.token}","calform","lot2");
    
    console.log("Selected value: " + selectedValue);
    // You can perform any additional actions here based on the selected value
  }
</script>


<script>
  function getdiff() {
	  
	  var data =   $(".caldata").val();
	  var orders =   $("#orders").val();
	  var balance_befores =   $("#balance_befores").val();
	  var amounts =   $("#amounts").val();
	  
	  
	  var  balance_after =  parseFloat(balance_befores)- parseFloat(data);
	  var  diff2 = parseFloat(orders)- ( parseFloat(amounts) + parseFloat(data) );
	  var  diff3 = parseFloat(data+0) + parseFloat(amounts+0);
	
	  
	  //alert(balance_after);
	  
	  $(".balance_after").val(balance_after);
	  // $("#diff3").val(diff3);
	 //   $("#diff2").val(diff2);
	  
	 
	    
  }
</script>

<script>
  function getdiff2() {
	  
	  
	  
	  var data =   $(".caldata").val(); //ยอดล่าสุด
	  var orders =   $(".orders").val(); // 
	  var balance_befores =   $("#balance_befores").val(); 
	  var amounts =   $("#amounts").val();
	  
	  
	var  balance_after =  parseFloat(balance_befores)- parseFloat(data);
	var  diff2 = parseFloat(orders)- ( parseFloat(amounts) + parseFloat(data) );
	var  diff3 = parseFloat(data) + parseFloat(amounts);
	  
	    $(".balance_after").val(balance_after);
	    
	    
	  //  $(".diff2").val(diff2);
	 //  $(".diff3").val(diff3);
	 
	    
  }
  
  
  
  var dataItems1 = [];
  <c:forEach items="${steps}" var="row">
      // ดึงค่าจาก `steps` มาใช้
      dataItems.push('${row.production_directive}');  // `someProperty` เป็นข้อมูลที่ต้องการให้แสดงใน autocomplete
  </c:forEach>

  // ใช้งาน jQuery autocomplete
  $(".autocomplete-input production_directive").autocomplete({
      source: dataItems
  });
  
  
  var dataItems2 = [];
  <c:forEach items="${steps}" var="row">
      // ดึงค่าจาก `steps` มาใช้
      dataItems.push('${row.batch}');  // `someProperty` เป็นข้อมูลที่ต้องการให้แสดงใน autocomplete
  </c:forEach>

  // ใช้งาน jQuery autocomplete
  $(".autocomplete-input batch").autocomplete({
      source: dataItems
  });
  
  
</script>
    
    