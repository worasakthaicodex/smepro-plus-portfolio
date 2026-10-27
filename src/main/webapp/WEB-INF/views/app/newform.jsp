<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    
 <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-drag-drop.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
 
 
 
  <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-tree.css" />" rel="stylesheet">
   
   
   <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/s/select/select2.min.css" />" rel="stylesheet">
 
 
 
 
    
 

 

<title>App</title>

<style>
/* ปรับสีขอบของ select2 ให้เหมือน form-control */
.select2-container--default .select2-selection--single {
  border: 1px solid #ced4da; /* สีเดียวกับ form-control */
  border-radius: 0.375rem;   /* radius ของ Bootstrap 4/5 */
  height: calc(2.25rem + 2px); /* ให้สูงเท่า form-control */
  padding: 0.375rem 0.75rem; /* padding เดียวกัน */
  font-size: 1rem;
}
.select2-container--default .select2-selection--multiple {
  border: 1px solid #ced4da !important;  /* เปลี่ยนเป็นสีที่คุณต้องการ */
  border-radius: 4px;                /* เพิ่มมุมมนถ้าต้องการ */
}
/* ปรับตอน active/focus */
.select2-container--default .select2-selection--single:focus,
.select2-container--default .select2-selection--single.select2-selection--focus {
  border-color: #86b7fe; /* เหมือน form-control focus */
  outline: 0;
  box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25); /* เหมือน form-control */
}


/* === HEADING STYLE #3 === */
.three {
width:100%;
;
}


.three h1 {
  font-size: 16px;
  list-style-type: none;
  margin: 0;
 padding: 6px 8px;
  overflow: hidden;
  border: 1px solid #e7e7e7;
  background-color: #f3f3f3;
}

 
</style>
    <style>
    .notifications {
	background: transparent;
	box-shadow: 0 0 0 0.1em transparent;
	filter: brightness(1) grayscale(1);
	position: relative;
	width: 1.5em;
	height: 1.5em;
	transition: filter 0.15s 0.3s linear;
	-webkit-appearance: none;
	appearance: none;
	-webkit-tap-highlight-color: transparent;
	float:right;
	border:0px;
}
.notifications:focus {
	outline: none;
}
.notifications:before,
.notifications:after,
.notifications__badge,
.notifications__waves,
.notifications__sr {
	position: absolute;
}
.notifications:before,
.notifications:after {
	content: "";
	display: block;
}
.notifications:before {
	background: radial-gradient(0.4em 0.4em at 50% 1.1em,hsl(38,90%,55%) 47%,hsla(38,90%,55%,0) 50%);
	top: 0.2em;
	left: calc(50% - 0.2em);
	width: 0.4em;
	height: 1.3em;
	transform-origin: 50% 0.2em;
}
.notifications:after {
	background:
		/* rim  */
		radial-gradient(0.4em 0.2em at 0.2em 1.2em,hsl(45,90%,55%) 46%,hsla(45,90%,55%,0) 50%),
		linear-gradient(hsl(45,90%,55%),hsl(45,90%,55%)) 0.2em 1.1em / 1.1em 0.2em no-repeat,
		radial-gradient(0.4em 0.2em at 1.3em 1.2em,hsl(45,90%,55%) 46%,hsla(45,90%,55%,0) 50%),
		/* middle  */
		radial-gradient(1.2em 1.2em at 50% 0.75em,hsl(38,90%,55%) 49%,hsla(38,90%,55%,0) 50%) 0 0 / 100% 75% no-repeat,
		linear-gradient(hsl(38,90%,55%),hsl(38,90%,55%)) 0.15em 0.75em / 1.2em 0.4em no-repeat,
		/* top */
		radial-gradient(0.3em 0.3em at 50% 0.15em,hsl(45,90%,55%) 48%,hsla(45,90%,55%,0) 50%);
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	transform-origin: 50% 0.15em;
}
.notifications__badge {
	background: hsl(3,90%,55%);
	border-radius: 0.375rem;
	color: hsl(0,0%,100%);
	font-size: 0.5em;
	font-weight: bold;
	padding: 0 0.125rem;
	top: -0.125rem;
	right: -0.125rem;
	min-width: 0.75rem;
	height: 0.75rem;
	text-align: center;
	transition: transform 0.3s 0.15s ease-out;
	z-index: 1;
}
.notifications__badge:empty {
	transform: scale(0);
	transition-delay: 0s;
}
.notifications__waves,
.notifications--active:before,
.notifications--active:after {
	animation-duration: 2s;
	animation-timing-function: linear;
	animation-iteration-count: infinite;
}
.notifications__waves {
	animation-delay: 0.6s;
	border-top: 0.1em solid transparent;
	border-right: 0.1em solid hsla(var(--hue),10%,50%);
	border-bottom: 0.1em solid transparent;
	border-left: 0.1em solid hsla(var(--hue),10%,50%);
	border-radius: 50%;
	top: -50%;
	left: -50%;
	opacity: 0;
	pointer-events: none;
	width: 200%;
	height: 200%;
	transform: scale(0.3);
}
.notifications__waves:nth-child(2) { animation-delay: 0.75s; }
.notifications__waves:nth-child(3) { animation-delay: 0.9s; }
.notifications__waves:nth-child(4) { animation-delay: 1.05s; }

.notifications--active {
	filter: brightness(1) grayscale(0);
	transition-delay: 0s;
}

.notifications--active:before,
.notifications--active:after {
	animation-delay: 0.3s;
}

.notifications--active:before {animation-name: ringBefore; }
.notifications--active:after {animation-name: ringAfter; }
.notifications--active .notifications__waves {animation-name: waves; }

.notifications__sr {
	clip: rect(1px,1px,1px,1px);
	overflow: hidden;
	width: 1px;
	height: 1px;
}

/* Dark theme */
@media (prefers-color-scheme: dark) {
	:root {
		--bg: hsl(var(--hue),10%,10%);
		--fg: hsl(var(--hue),10%,90%);
	}
}

/* Animations */
@keyframes ringBefore {
	from { transform: rotate(0); }
	15% { transform: rotate(-3deg); }
	20% { transform: rotate(6deg); }
	25% { transform: rotate(-6deg); }
	30% { transform: rotate(18deg); }
	35% { transform: rotate(-18deg); }
	40% { transform: rotate(22deg); }
	45% { transform: rotate(-20deg); }
	50% { transform: rotate(18deg); }
	55% { transform: rotate(-16deg); }
	60% { transform: rotate(14deg); }
	65% { transform: rotate(-12deg); }
	70% { transform: rotate(10deg); }
	75% { transform: rotate(-8deg); }
	80% { transform: rotate(6deg); }
	85% { transform: rotate(-4deg); }
	90% { transform: rotate(2deg); }
	95% { transform: rotate(-1deg); }
	
}
@keyframes ringAfter {
	from,
	40%,
	to {
		transform: rotate(0);
	}
	5%,
	35% {
		transform: rotate(10deg);
	}
	10%,
	30% {
		transform: rotate(-10deg);
	}
	15%,
	25% {
		transform: rotate(20deg);
	}
	20% {
		transform: rotate(-20deg);
	}
}
@keyframes waves {
	from {
		opacity: 1;
		transform: scale(0.3);
	}
	20%,
	to {
		opacity: 0;
		transform: scale(1);
	}
}
    .input-group .select2-container .select2-selection {
    border-top-right-radius: 0 !important; /* ลบโค้งขอบขวาบน */
    border-bottom-right-radius: 0 !important; /* ลบโค้งขอบขวาล่าง */
}
    
    
.input-group .position-relative {
    flex: 1 1 auto; /* ให้ position-relative ขยายเต็มพื้นที่ใน input-group */
    width: 80%; /* บังคับความกว้าง */
}

.input-group .select2-container {
    width: 100% !important; /* บังคับให้ Select2 container เต็มความกว้าง */
}

.input-group .select2-selection {
    width: 100% !important; /* ปรับ selection ให้เต็ม */
}
<c:if test="${DOC_ID != null }">  
.RF-${DOC_ID }{
	 background-color: #7dcea0  ;
	 color:#fff;
}
</c:if>


 .CHECK-1{
 background-color: #f1948a;
	 color:#212f3d;
}
    
    .container {
  width: 100%;

}



	  .progressbar {
  display: flex;
  justify-content: space-between;
  flex-wrap: nowrap; /* ไม่ให้ขึ้นบรรทัดใหม่ */
  padding: 0;
  margin: 0;
  counter-reset: step;
  overflow: hidden; /* กันไอเทมล้น */

}

.progressbar li {
  list-style: none;
  flex-grow: 1;
  flex-basis: auto; /* ปรับขนาดอัตโนมัติ */
  text-align: center;
  position: relative;
  cursor: pointer;

   
}

.progressbar li:before {
  content: counter(step);
  counter-increment: step;
  width: 24px; /* ลดขนาดวงกลม */
  height: 24px;
  line-height: 24px;
  font-size: font-size: 8px;  /* ลดขนาดตัวเลข */
  border: 1px solid #ddd;
  border-radius: 50%;
  display: block;
  text-align: center;
  margin: 0 auto 5px auto;
  background-color: #ddd;
  z-index: 99; /* ให้ตัวเลขอยู่หน้าเส้น */
}

.progressbar li:after {
  content: "";
  position: absolute;
  width: 80%;
  height: 1px;
  background-color: #ddd;
  top: 12px;
  left: -40%;
  z-index: 1; /* ให้เส้นอยู่ด้านหลัง */
}

.progressbar li:first-child:after {
  content: none;
}

/* Active Step */
.progressbar li.active {
  color: green;
}
.progressbar li.active:before {
  border-color: green;
   background-color: green;
   color:#fff;
   
}
.progressbar li.active_user:before {
  border-color: Blue;
   background-color: Blue;
   color:#fff;
   
}





.progressbar li.active + li:after {
  background-color: green;
  
}

/* 🔹 Responsive: ลดขนาดทุกอย่าง เมื่อ div แคบลง */
@media (max-width: 768px) {
  .progressbar li {
    font-size: 8px; /* ลดขนาดตัวอักษร */
       
  }

  .progressbar li:before {
    width: 20px; /* ลดขนาดวงกลม */
    height: 20px;
    line-height: 20px;
    font-size: 8px; /* ลดขนาดตัวเลข */
     
  }

  .progressbar li:after {
    height: 1px; /* ลดความหนาของเส้น */
    top: 10px;
   
  }
}


.mainalert {
  display: flex;
  align-items: center; /* จัดตรงกลางแนวตั้ง */
  justify-content: center; /* จัดตรงกลางแนวนอน */
  text-align: center; /* จัดข้อความตรงกลาง */

  height: 50px; /* กำหนดความสูงเพื่อให้กลางจริง ๆ */
}

input[readonly] {
 background: linear-gradient(90deg, #f9f9f9, #ffffff);

  border-color: #ddd;
}


select[readonly] {
 background: linear-gradient(90deg, #f9f9f9, #ffffff);
  border-color: #ddd;
}

textarea[readonly] {
 background: linear-gradient(90deg, #f9f9f9, #ffffff);

  border-color: #ddd;
}


.dark-layout .tab_content
 {
    background-color: #283046;
    color: #fff;
       box-shadow: 0 4px 24px 0 rgba(34, 41, 47, 0.24);
}
.dark-layout .tab_container 
 {
	    border: #283046;
       box-shadow: 0 4px 24px 0 rgba(34, 41, 47, 0.24);
}



.dark-layout select[readonly] {
 background-color: #283046;
  color: #fff;
  background-image: none !important;
  background-clip: border-box !important;
  box-shadow: none !important;
  color: #fff !important;
    border-color: #404656;
}

.dark-layout textarea[readonly] {
background-color: #283046;
  color: #fff;
}
.dark-layout input[readonly] {
  background-color: #283046 !important;
  background-image: none !important;
  background-clip: border-box !important;
  box-shadow: none !important;
  color: #fff !important;
}


</style>



</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>  




				

 <div class="app-content content ">
 <div class="content-body">
            <div class="content-header row">
                <div class="content-header-left col-md-9 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-12">
                            <h2 class="content-header-title float-start mb-0">App</h2>
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                     <li class="breadcrumb-item">${work.workflow_name}
                                    </li>  
                                    <li class="breadcrumb-item"><a href="/app-filemanager/${process.id}">${process.name} </a>
                                    </li>
                                  <li class="breadcrumb-item">เพิ่มข้อมูล 
                                    </li>
                                </ol>
                            </div>
                        </div>
                        
                        
                    </div>
                    
                  
                    
                </div>
                  
                 <div class="content-header-right text-md-end col-md-3 col-12 d-md-block d-none">
                   <sec:authorize access="hasAuthority('EDIT-DOC-${docs.id}')">
                    <div class="mb-1 breadcrumb-right" style="float:right;margin-left:5px">
                    
               
                    
                        <div class="dropdown">
                <button class="btn-icon btn btn-primary btn-round btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i data-feather="grid"></i></button>
                            <div class="dropdown-menu dropdown-menu-end">
                      
        
       			<%@ include file="sub/flow.jspf" %>                        
        
                            
                            
                      </div>
                    </div>
                </div>
                         </sec:authorize>
       
            
                <div class="btn-group view-toggle ms-50" role="group" style="float:right">
                                  
                                    
                               
		                           <c:if test="${ edatas != null }"> 
		                                  
		                                    <a  href="/docprint-filemanager?id=${edatas.data_id }&&type=${type}"  target="_blank" 
												     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">
		                    
		                            
		                                  <input type="radio" class="btn-check" name="view-btn-radio" data-view="grid" id="gridView" autocomplete="off">
                                        <label class="btn btn-outline-primary p-50 btn-sm waves-effect" for="gridView">
                                            <i class="me-1" data-feather="printer"></i>
                                          <spring:message code="btn.print"/>
                                        </label>
                                        
                                        </a> 
                                     </c:if>   

                     			 
                   
 
                                        
                                    </div>
            
            
            
            </div>
        
        
                               
           
                               
                               
    
            
            <section id="basic-horizontal-layouts">
                    <div class="row">

<form method="POST" modelAttribute="edata"  action="/saveapp-filemanager?${_csrf.parameterName}=${_csrf.token}"   id="form" enctype="multipart/form-data"> 
   <input type="hidden" name="form_status"  value="app">                      

                    
                             
                          
<div class="card-body" style="padding:5px">            
<input type="hidden" name="id" value="${transaction['id']+0}"> 
<input type="hidden" name="edata_id" value="${edatas.data_id+0}">          
<input type="hidden" name="data_id" value="${edatas.data_id+0}">              
<input type="hidden" name="tempId" value="${tempId}">                         
<input type="hidden" name="sub_processes_id"  id="sub_processes_id"  value="${sub_processes.id}">                                                           
<input type="hidden" name="processes_id"  id="processes_id"  value="${process.id}">     

<a href="/app-filemanager/${process.id}" class="btn btn-sm btn-icon btn-light  waves-effect waves-float waves-light"  style="background-color:#fff">
<i data-feather='chevron-left'></i>
</a>



 
 
 
                    								  <c:choose>
                    								   <c:when test="${ edatas == null  }">

			<button  type="submit"  id="submits" class="btn btn-icon btn-sm btn-success waves-effect waves-float waves-light" >
			<i data-feather='save'></i>
			</button>
         								     	</c:when>
                    								     	
                    								   								   <c:when test="${   fn:contains(sub_processes.edit_list,'edit_list_data')  }">

			<button  type="submit"  id="submits" class="btn btn-icon btn-sm btn-success waves-effect waves-float waves-light" >
			<i data-feather='save'></i>
			</button>
         								     	</c:when>
                    								     	    	
                    								 	
                    								  
                    								  	    <c:when test="${  
                    								  	   (  docs.current_step == 'Create' 
                    								  	     ||  docs.current_step == 'Clear'
                    								  	  )   }">
															   
			<button   type="submit"  id="submits" class="btn btn-icon btn-sm btn-success waves-effect waves-float waves-light" >
			<i data-feather='save'></i>
			</button>

                    								     	</c:when>
                    								  
                    							
													
									
														</c:choose>      
 
 
 
 
 
 
   <c:if test="${edatas != null && docs.current_step == 'Create' }">       
<a href="/undocumentapp-filemanager/${edatas.data_id}/${process.id}"  onclick="return confirm('คุณเเน่ใจจะลบ')" class="btn btn-sm btn-icon btn-danger waves-effect waves-float waves-light" >
<i data-feather='trash-2'></i>
</a>
 </c:if> 

    <c:if test="${edatas != null && process.process_join != null && (docs.current_step == 'Create' &&  docs.current_step != 'Clear') 
    && (reference_only + modify_count + continue_count + continue_count  + hierarchical_count) > 0  }">  
<a   href="#"   onclick="getdatas('/inboxrefer-filemanager/${process.process_join }','divrefer');"    data-bs-toggle="modal" data-bs-target="#addNewCards3" style="background-color:#fff" class="btn btn-sm btn-icon btn-light waves-effect waves-float waves-light">
<i data-feather='archive'></i>
</a>
 </c:if> 
           
           
           
<c:if test="${edatas != null  && (docs.current_step == 'Create' &&  docs.current_step != 'Clear') && refer_type != null }">  
    <a   href="#" onclick="getdatas('/workflow_db-filemanager/${process.id}/${refer_type }/-/0?edata_id=${edatas.data_id}','wrappermain')" 
    data-bs-toggle="modal" data-bs-target="#new-stock-modal" style="background-color:#fff" class="btn btn-sm btn-icon btn-light waves-effect waves-float waves-light">
   <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-paperclip"><path d="M21.44 11.05l-9.19 9.19a6 6 0 0 1-8.49-8.49l9.19-9.19a4 4 0 0 1 5.66 5.66l-9.2 9.19a2 2 0 0 1-2.83-2.83l8.49-8.48"></path></svg>                                     
</a>
</c:if>  
      <a   href="#" 
    data-bs-toggle="modal" data-bs-target="#info" style="background-color:#fff" class="btn btn-sm btn-icon btn-light waves-effect waves-float waves-light">
                <i data-feather='alert-circle'></i>                      
</a>      


             
  <div class="container" style="overflow-y:auto">
  <br>
 <ul class="progressbar" style="<c:if test="${all_processes.size() > 6 }">width:600px</c:if>">
       <c:forEach items="${all_processes }" var="rows" varStatus="count">
      
       <c:set  var="mystep" value="" />

       <c:if test="${Appstep_no > count.count-1 }"> <c:set var="mystep" value="active" /></c:if>
        <c:if test="${Appstep_no == count.count-1 }"> <c:set var="mystep" value="active_user" /></c:if>

			<c:set var="steps" value="${rows.description}" />
           <c:if test="${rows.description == '' }"> <c:set var="steps" value="${rows.step}" /></c:if>
        
        <li class="${mystep }"><span style="font-size:10px""> ${steps} </span></li>
        </c:forEach>
      </ul>
    </div>
                  
</div>   
                    
                    <div class="col-md-12 col-12">
            

              
                  
                        
                           
                          
   
   
   
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
                  <c:if test="${docs.current_step == 'rejected'  }">
 	<div class="col-12">
                                                <div class="mb-1 row">
                                     
                                                     <div class="col-sm-12"> 
                                                     <div class="alert-danger" >
  		<p>[rejected] ${docs.remarks_rejected} </p>
  		
  	
</div>  
                                                      </div>
                                                     </div> 
                                           </div>   
</c:if>  
    
 

 <style>
ul.tabs {
	margin: 0;
	padding: 0;
	float: left;
	list-style: none;
	height: 32px;
	border-bottom: 1px solid  #eee;
	width: 100%;
}

ul.tabs li {
	float: right;
	margin: 0;
	cursor: pointer;
	padding: 0px 21px;
	height: 31px;
	line-height: 31px;
	border-top: 1px solid #eee;
	border-left: 1px solid #eee;
	border-bottom: 1px solid #eee;
	background-color: #eee;
	color: #ccc;
	overflow: hidden;
	position: relative;
}

.tab_last { border-right: 1px solid #333; }

ul.tabs li:hover {
	background-color: #ccc;
	color: #333;
}

ul.tabs li.active {
	background-color: #fff;
	color: #333;
	border-bottom: 1px solid #fff;
	display: block;
}

.tab_container {
	border: 1px solid #eee;
	border-top: none;
	clear: both;
	float: left;
	width: 100%;
	background: #fff;
	overflow: auto;
}

.tab_content {
	padding: 20px;
	display: none;
}

.tab_drawer_heading { display: none; }

@media screen and (max-width: 480px) {
	.tabs {
		display: none;
	}
	.tab_drawer_heading {
		background-color: #ccc;
		color: #fff;
		border-top: 1px solid #333;
		margin: 0;
		padding: 5px 20px;
		display: block;
		cursor: pointer;
		-webkit-touch-callout: none;
		-webkit-user-select: none;
		-khtml-user-select: none;
		-moz-user-select: none;
		-ms-user-select: none;
		user-select: none;
	}
	.d_active {
		background-color: #666;
		color: #fff;
	}
}


 </style>
 
 <ul class="tabs" >
 
 
   <c:if test="${sub_reference != null  && sub_reference != '' }">  
 <c:set var="namesArray" value="${fn:split(sub_reference, ',')}" />
 <c:forEach var="referencedocs" end="5" items="${namesArray}">
 

<c:set var="view_type" value="0" />
	<c:if test="${ refer_type == 'edata_register_processing' }"><c:set var="view_type" value="1" /></c:if>
  <li rel="tab3" onclick="getdatas('/loadreference-filemanager/${referencedocs }?view_type=${view_type }','tab3')">${referencedocs }</li>
</c:forEach>
  </c:if>
 
 
  <c:if test="${edatas.reference != null  && edatas.reference != '' && edatas.reference != 'null' }">  
 <c:set var="namesArray" value="${fn:split(edatas.reference, ',')}" />
 <c:forEach var="referencedocs" end="5" items="${namesArray}">
 

<c:set var="view_type" value="0" />
	<c:if test="${ refer_type == 'edata_register_processing' }"><c:set var="view_type" value="1" /></c:if>
  <li rel="tab3" onclick="getdatas('/loadreference-filemanager/${referencedocs }?view_type=${view_type }','tab3')">${referencedocs }</li>
</c:forEach>


  </c:if>

   <c:if test="${edatas.attach_type != null  && edatas.attach_type != '' && edatas.attach_type != 'null' }">  
     <li rel="tab3" onclick="getdatas('/loadattach-filemanager/${edatas.attach_type }/${edatas.attachdocuments }/${edatas.data_id}/${refer_type }','tab3')">${edatas.attach_type }-${edatas.attachdocuments }</li>
    </c:if>
    
    
  <li class=" <c:if test="${stepclear != 1 }"> active</c:if>" rel="tab1">${DOC_ID}</li> 
 <c:if test="${stepclear == 1 }">   <li rel="tab2" class="active">${DOC_ID }_2</li></c:if>
 


</ul>
<div class="tab_container">

  <div id="tab1" class="tab_content">
   
       
<div class="col-md-12 col-12" id="myCard">   
                         <div class="mb-1 row">
<div class="col-sm-3"></div>
			
			         </div>  
	         
	         
<div class="row">	  
<div class="col-md-12 col-12">
<%@ include file="../app/common.jsp" %> 
</div>
</div>


    

<div id="subdata">           

   <c:set var="action_batch_check" value="${fn:replace(action_batch,' ', '')}" />
	<c:set var="action_batch_check" value="${fn:replace(action_batch_check, ',', '')}" />
   
  
		<c:if test="${action_batch != null && not empty action_batch_check   }">  
			        <%@ include file="sub/gs5.jspf" %>                                
		</c:if>     
</div>	      

   

                     
</div>   
<div class="col-md-12 col-12" id="myCard">   

</div> 




  </div>
  <!-- #tab1 -->
  <h3 class="tab_drawer_heading" rel="tab2">Tab 2</h3>
  <div id="tab2" class=" tab_content ">
	<c:if test="${stepclear == 1 }">                        
	                    
	        
		<div class="col-md-12 col-12">    
		

			         
			         
		<%@ include file="../app/common2.jsp" %>          
		<div id="subdatac">        
		
		      		
				<c:if test="${action_batch_clear != null }">  
						<c:set var="typeshow" value="11" />
						<%@ include file="../app/general_system.jspf" %>                                    
				</c:if>     
		</div>	      
		</div>
	                     
	 
</c:if>   
  </div>
  <!-- #tab2 -->
  <h3 class="tab_drawer_heading" rel="tab3">Tab 3</h3>
  <div id="tab3" class="tab_content">

  </div>

 
</div>

 

<script>
document.getElementById("submits").addEventListener("click", function(event) {
    document.getElementById("tab3").innerHTML = ""; // ลบข้อมูลใน div id="tab3"
});
</script>
    <script>
 // tabbed content
    // http://www.entheosweb.com/tutorials/css/tabs.asp
    $(".tab_content").hide();
  
    <c:if test="${stepclear != 1 }">    $(".tab_content:first").show();</c:if>  

    <c:if test="${stepclear == 1 }"> $("#tab2").show();</c:if>
    
    

  /* if in tab mode */
    $("ul.tabs li").click(function() {
		
      $(".tab_content").hide();
      var activeTab = $(this).attr("rel"); 
      $("#"+activeTab).fadeIn();		
		
      $("ul.tabs li").removeClass("active");
      $(this).addClass("active");

	  $(".tab_drawer_heading").removeClass("d_active");
	  $(".tab_drawer_heading[rel^='"+activeTab+"']").addClass("d_active");
	  
    });
	/* if in drawer mode */
	$(".tab_drawer_heading").click(function() {
      
      $(".tab_content").hide();
      var d_activeTab = $(this).attr("rel"); 
      $("#"+d_activeTab).fadeIn();
	  
	  $(".tab_drawer_heading").removeClass("d_active");
      $(this).addClass("d_active");
	  
	  $("ul.tabs li").removeClass("active");
	  $("ul.tabs li[rel^='"+d_activeTab+"']").addClass("active");
    });
	
	
	/* Extra class "tab_last" 
	   to add border to right side
	   of last tab */
	$('ul.tabs li').last().addClass("tab_last");
	    </script>



                       
                        
   

         <br>       <br>    <br>    <br>           
 </div>                               
                          
       </form>                      
                          
                          
                          
                          
                          
                          
                

       
                       
                        <div class="col-md-6 col-12">
  
  
             <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Support information</h4>
                                </div>
                                <div class="card-body">
                                     <div class="transaction-item">
                                    <div class="d-flex flex-row">

               
         
                    <c:if test="${formability1 != null }">
                                          <c:if test="${levenshtein.size() == 0 }">
                                               <div class="avatar bg-light-success rounded float-start" data-bs-toggle="modal" data-bs-target="#info_check">
                                                <div class="avatar-content">
                                              <i data-feather='user-check'></i>  </div>
                                            </div>
                                            <div class="transaction-percentage">
                                                <h6 class="transaction-title">  <spring:message code="check.duplicate_names"/></h6>
                                                <small>  <spring:message code="check.duplicate_names_result_yes"/></small>
                                            </div>
                                           </c:if>
                                         
                                             <c:if test="${levenshtein.size() > 0  && levenshtein_count > 0}">
                                               <div class="avatar bg-light-danger rounded float-start"  data-bs-toggle="modal" data-bs-target="#info_check">
                                                <div class="avatar-content">
                                             <i data-feather='user-x'></i>   </div>
                                            </div>
                                            <div class="transaction-percentage">
                                                <h6 class="transaction-title">  <spring:message code="check.duplicate_names"/></h6>
                                                <small>  <spring:message code="check.duplicate_names_result_no"/></small>
                                            </div>
                                           </c:if>
                                             <c:if test="${levenshtein.size() > 0  && levenshtein_count == 0}">
                                               <div class="avatar bg-light-warning rounded float-start"  data-bs-toggle="modal" data-bs-target="#info_check">
                                                <div class="avatar-content">
                                             <i data-feather='user-x'></i>   </div>
                                            </div>
                                            <div class="transaction-percentage">
                                                <h6 class="transaction-title">  <spring:message code="check.duplicate_names"/></h6>
                                                <small>  <spring:message code="check.duplicate_names_result_no"/></small>
                                            </div>
                                           </c:if>
                                           </c:if>
                                         
                 
                                  
                                   
                                        
                                        
                                    </div>
                             
                                </div>
                                
                  
                                
                               </div>
                            </div>
                          </div>  
                            
    <div class="col-md-6 col-12">
  <div class="card">
                        
                                <div class="card-body">
                                    <ul class="nav nav-tabs" role="tablist">
                                 
                                 
                                    
                                        <li class="nav-item">
                                            <a class="nav-link active" id="aboutIcon-tab" data-bs-toggle="tab" href="#aboutIcon" aria-controls="about" role="tab" aria-selected="false">
                               				<i data-feather='upload'></i>
                                          <spring:message code="Form.upload"/>
                                          </a>
                                        </li>
                                        
                                               <li class="nav-item">
                                            <a class="nav-link " id="homeIcon-tab" data-bs-toggle="tab" href="#homeIcon" aria-controls="home" role="tab" aria-selected="true">
                                            <i data-feather='send'></i>
                                            <spring:message code="Form.step"/></a>
                                        </li>
                                   <li class="nav-item">
                                            <a class="nav-link" id="followup-tab" data-bs-toggle="tab" href="#followup" aria-controls="about" role="tab" aria-selected="false">
                               				<i data-feather='calendar'></i>
                               				                          <spring:message code="Form.followup"/>
                                       
                                          </a>
                                        </li>
                                        
                                                    <c:if test="${goals != null }">
                                               <li class="nav-item">
                                            <a class="nav-link" id="profileIcon-tab" data-bs-toggle="tab" href="#profileIcon" aria-controls="profile" role="tab" aria-selected="false">
                                       <i data-feather='info'></i>
                                           <spring:message code="Form.goals"/>
                                           
      <c:if test="${upload_log.status <  100  || upload_log == null }">   
  <button id="notifications" class="notifications notifications--active" type="button">
	<span class="notifications__waves"></span>
	<span class="notifications__waves"></span>
	<span class="notifications__waves"></span>
	<span class="notifications__waves"></span>
	<span class="notifications__badge" data-badge>?</span>
</button>    
</c:if>
                                           
                                           </a>
                                           
   
                                        </li>
                                       </c:if> 
                                        
                                        
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane " id="homeIcon" aria-labelledby="homeIcon-tab" role="tabpanel">
                                         
                                         
                                             <c:if test="${docs.remarks_rejected == 'Rejected' }">   
                                             
                      <span class="mail-label badge rounded-pill badge-light-danger">
                              ${ docs.remarks_rejected}
                                            </span> 
                                 </c:if>
                                 </h4>
                         
                    
      
                      
                            
                         <table  class="table">
                                    <thead>
                                        <tr>
           
				                                      
				                                       <th style="padding:5px;font-size:13px">NO</th >
				                                     <th style="padding:5px;font-size:13px">DATE</th >
				                                       <th style="padding:5px;font-size:13px">TYPE</th >                            
														 <th style="padding:5px;font-size:13px">TYPE</th >     
														       <th style="padding:5px;font-size:13px">SEND</th >       
														       <th style="padding:5px;font-size:13px">TO</th >       
														             <th style="padding:5px;font-size:13px">Comment</th >    
				                                    

                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                            
                                    
                                
                                        <c:forEach items="${log }" var="logs"  varStatus="count">
				                                     
				                                     
				                                      <tr >
				                                       <td style="padding:5px;font-size:13px">${count.count }</td >
				                                     <td style="padding:5px;font-size:13px">${logs.flow_date}</td >
				                                      <td style="padding:5px;font-size:13px">${logs.flow_step}</td >   
				                                       <td style="padding:5px;font-size:13px">${logs.flow_type}</td >                            
														
														       <td style="padding:5px;font-size:13px">${logs.send}</td >       
														       <td style="padding:5px;font-size:13px">${logs.to}</td >       
														             <td style="padding:5px;font-size:13px">${logs.flow_comment}</td >    
				                                        </tr>
                                        </c:forEach>
                                        
                                        
                                    </tbody>
                                    </table> 
                         
                                         
                          
                                        </div>
                                        <div class="tab-pane " id="profileIcon" aria-labelledby="profileIcon-tab" role="tabpanel">
                                          <%@ include file="sub/goalshow.jspf" %>  
                                        </div>
                                        <div class="tab-pane" id="followup" aria-labelledby="followup-tab" role="tabpanel">
                                             <%@ include file="sub/followup.jspf" %> 
                                        </div>
                                        
                                      
                                        <div class="tab-pane active" id="aboutIcon" aria-labelledby="aboutIcon-tab" role="tabpanel">
                                     
                                     
                                                     <div class="col-md-12 col-12">
 
                        
                                                <div class="mb-1">
                                                
              <sec:authorize access="hasAuthority('EDIT-DOC-${docs.id}')">
                    <c:if test="${docs.current_step !=   'Completion' }">
                      <a href="#" class="btn btn-light me-1 waves-effect waves-float waves-light"  style="float:right"     data-bs-toggle="modal" data-bs-target="#addNewCards2">
                               Upload
                         </a >
                  </c:if>
                </sec:authorize>          
                         
              
         <div  style="clear:both" ></div>
              <br>
    <div id="subdataformupload">
                         <%@ include file="file.jsp" %>  
                </div>
                
                            
                            
                   
                       
                            
                                                </div>
                                    
                                
                                                
                                                
                                      
                                                
                                                
                                            </div>   
                                
                                
                                     
                                     
                                        </div>
                                    </div>
                                </div>
                            </div>
  
  
  
  
                       
                            
                            
                 
                        
                        
                 
                          
                        </div>
                  


					 </div>
                </section>
                
                 

</div>

</div>

 

      <div id="savesuccess"></div>  
 
 <form method="POST"  action="/addreferitem-filemanager?${_csrf.parameterName}=${_csrf.token}"  id="formimplode" name ="formimplode"> 
	 <input type="hidden" id="selectedValues" name="selectedValues">    
<input type="hidden" name="sub_processes_id"  id="sub_processes_id"  value="${sub_processes.id}">                                                           
<input type="hidden" name="processes_id"  id="processes_id"  value="${process.id}">      
<input type="hidden" name="edata_id"  id="edata_id"  value="${edatas.data_id}">  
<input type="hidden" name="edata_type"  id="edata_type"  value="${edata_type}">  
	     
</form>  	 
  
 
<c:if test="${action_batch != null }">  
				<c:set var="typeshow" value="3" />
				<%@ include file="../app/general_system.jspf" %>                                    
</c:if>
<c:if test="${action_batch_clear != null }">  
				<c:set var="typeshow" value="4" />
				<%@ include file="../app/general_system.jspf" %>                                    
</c:if>




</div>

<c:set var="typeshow" value="12" />
  <%@ include file="general_system.jspf" %>      

      <%@ include file="sub/model.jspf" %>    
  
  <div class="modal fade" id="info" tabindex="-1" aria-labelledby="addNewCardTitle3" aria-hidden="true">
 
                    <div class="modal-dialog   modal-lg modal-dialog-centered">
                         <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel33">INFO</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                            <div class="modal-body">
                                                            
                                                            
                                                                             <%@ include file="input/main_input.jspf" %>  
                                                                             
                                                                             
                                                                             
 </div>   
                                                    </div>    
                               
                                        </div>
                                        </div>
  
  
  
  
  
  
  
 <div class="modal fade" id="addNewCards3" tabindex="-1" aria-labelledby="addNewCardTitle3" aria-hidden="true">
 
                    <div class="modal-dialog   modal-lg modal-dialog-centered">
                         <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel33"><spring:message code="item.importreferencedocuments" text="-" /></h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                  
                                                  
                                                  
                                                         <form:form method="POST" action="/import_reference-filemanager"  > 
                                                         
                                                        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                        <input type="hidden" name="processes_id"  id="processes_id"  value="${process.id}">   
														<input type="hidden" name="edata_id" value="${edatas.data_id+0}">  
														<input type="hidden" name="refer_type" id="refer_type" value="${refer_type }">
							
														
                                                            <div class="modal-body">
                        
                                                    
                                                <div id="divrefer"></div>
                                                               
                                                              <input type="hidden"  id="selectedIds"  name="selectedIds" value="" class="form-control">
                                                            </div>
                                                            <div class="modal-footer" Style="display: inline-block;">
                                                                <button type="submit"  class="btn btn-primary waves-effect waves-float waves-light " >เพิ่ม</button>
                                                            </div>
                                                </form:form>
                                                    </div>    
                               
                                        </div>
                                        </div>

  
 <div class="modal fade" id="edititem" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
 
                    <div class="modal-dialog   modal-lg modal-dialog-centered">
                         <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel33">เพิ่มรายการ</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                  
                                                         <form:form method="POST"  modelAttribute="purchase"  action="/additem2-filemanager"  id="formclear" name ="formclear"> 
                                                          <input type="hidden"  name="item_id" value="${edatas.data_id }" class="form-control">
                                                          <input type="hidden"  name="upload_pn_ids" value="${upload_pn.upload_pn_id}" class="form-control">
                                                            <div class="modal-body" id="subdata_edititem">
                                                  
                                                                
                                                            </div>
                                                            <div class="modal-footer" Style="display: inline-block;">
                                                                <button type="button"  onclick='postdata("/additem2-filemanager?${_csrf.parameterName}=${_csrf.token}","formclear","subdata")'  class="btn btn-primary waves-effect waves-float waves-light" data-bs-dismiss="modal">เพิ่ม</button>
                                                            </div>
                                                </form:form>
                                                    </div>    
                               
                                        </div>
                                        </div>


 
 
 
 <div class="modal fade" id="addNewCards2" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
 
                    <div class="modal-dialog   modal-lg modal-dialog-centered">
                    
                    
                     <form id="formupload1" name="formupload1" action="/uploadfile_app-filemanager/25" method="POST" enctype="multipart/form-data">
                                                       
                         <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel33"> <spring:message code="Form.upload"/></h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                  
                                                        <input type="hidden"  name="edata_id" value="${edatas.data_id }" class="form-control">
                            							  <input type="hidden"  name="folder" value="${work.workflow_folder}" class="form-control">
                                                            <div class="modal-body">
                                                           
                                            <div id="formss">
                                                                <label><spring:message code="files.tag"/>: </label>
                                                                <div class="mb-1">
                                                                  <c:set var="namesArray" value="${fn:split(dbValue, ',')}" />
                                                    <select name="file_tag" class="form-select">   
                                                        	    <option value="-">
												    -
												    </option>       
                                                      	<c:forEach var="nameItem" items="${namesArray}" begin="0" end="5">
												 
												    <option value="${nameItem}">
												      ${nameItem}
												    </option>
											
												    
												</c:forEach>
                                                     	 </select>                  
                                                                    
                                                                    
                                                                    
                                                                </div>
         
                                                                   <label><spring:message code="item.estimated" text="ราคาประมาณการ" />: </label>
                                                                <div class="mb-1">
                                                                        <span id="filedatas1" class="filedatas">
												                                <a href="#" class="btn btn-light me-1 waves-effect waves-float waves-light" id="opencamera" style="float:left">
												                               Open camera
												                         </a >
												                              <input type="file" name="files" id="uploadFileInput" class="form-control"   style="width:50%;float:left;margin-left:10px;"  multiple>
												                 			  </span>
												                 			 
                          									  </div>
                                                        </div>
                                  
                                                         <div style="clear:both">
    <style>                                                     
                                                         
      /* ปรับแต่งให้ส่วนต่างๆ ดูเป็นระเบียบ */
.camera-controls {
    text-align: center;
    margin-bottom: 20px;
}

.camera-buttons a {
    margin: 5px;
}

/* กำหนดพื้นที่ดูรูปให้มีขนาดที่เหมาะสม */
.photo-display {
    margin-top: 20px;
}

#photoCanvas {
    display: block;
    margin: 0 auto 20px;
    max-width: 100%;
    height: auto;
    border-radius: 8px;
}

/* จัดให้พื้นที่ card-drag-area มีระยะห่าง */
#card-drag-area {
    margin-top: 20px;
}                                      
    </style> 
             
                                                      

<div id="cameraArea" style="display:none"></div>

               </div>
                         
                                                           
                                                                
                                                                
                                              
                                                            </div>
                                                            <div class="modal-footer" Style="display: inline-block;">
                                                                <button type="button" 
                                                                 onclick='postdata("/uploadfile_app-filemanager?${_csrf.parameterName}=${_csrf.token}","formupload1","subdataformupload")'  
                                                                 class="btn btn-primary waves-effect waves-float waves-light" data-bs-dismiss="modal">เพิ่ม</button>
                                                            </div>
                                                </form>
                                                    </div>    
                               
                                        </div>
                                        </div>




                                 


         <!-- Right Sidebar starts -->
                        <div class="modal modal-slide-in sidebar-todo-modal fade" id="new-task-modal" >
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
                                       
                                         <div class="todo-task-list-wrapper list-group" id="wrappermain" >
                                 
                                 
    <input type="text" id="searchInput" placeholder="Search..." class="form-control mb-3">
                
                
               <style>
 #Register th {
    position: sticky;
    top: 0;
    z-index: 10; /* ให้แน่ใจว่าหัวตารางอยู่ด้านบน */
}        
               </style>       
 
                                 
                                 
                                 <div style="max-height:500px;overflow-y:auto">
                                                 
                     <a href="#" onclick="cancelpd()" class="btn btn-sm btn-danger"   style="text-align: left;font-size: 10px;padding:5px"><spring:message code="btn.cancel"  /></a> 
                                                  
                                                    <br>  <br>
                                                    <table class="table tableed" id="Register">
                                            
                                            <thead>
                          <tr>
                           <th style="font-size: 10px;padding:5px">ID</th>
                      <th style="font-size: 10px;padding:5px"><spring:message code="maindata.pd"  /></th>
                      <th style="font-size: 10px;padding:5px"><spring:message code="maindata.batch"  /></th>
                      <th style="font-size: 10px;padding:5px"><spring:message code="maindata.product"  /></th>
                        <th style="font-size: 10px;padding:5px"><spring:message code="maindata.customer"  /></th>
                       <th style="font-size: 10px;padding:5px"><spring:message code="maindata.order"  /></th>
                       <th style="font-size: 10px;padding:5px"><spring:message code="maindata.total_order"  /></th>
                              <th style="font-size: 10px;padding:5px"><spring:message code="maindata.purchase_order"  /></th>
                       <th style="font-size: 10px;padding:5px"><spring:message code="maindata.apply"  /></th>
                        </tr>  
                        </thead>
                   <c:forEach items="${registerpd }" var="row" varStatus="count">  
                           <tr>
                                 <td style="text-align: left;font-size: 10px;padding:5px" data-custom="id">${row.id}</td>
                        <td style="text-align: left;font-size: 10px;padding:5px" data-custom="pd">${row.production_directive}</td>
                           <td style="text-align: left;font-size: 10px;padding:5px"  data-custom="batch">${row.batch}</td>
                        <td style="text-align: left;font-size: 10px;padding:5px"  data-custom="product">${row.product}</td>
                           <td style="text-align: left;font-size: 10px;padding:5px"  data-custom="customer">${row.customer}</td>
                          <td style="text-align: left;font-size: 10px;padding:5px" >${row.order_quantity}</td>
                               <td style="text-align: left;font-size: 10px;padding:5px" >${row.total_order_quantity}</td>
                                    <td style="text-align: left;font-size: 10px;padding:5px"  data-custom="purchase_order">${row.purchase_order}</td>
                            <td style="text-align: left;font-size: 10px;padding:5px"><a href="#" class="btn btn-sm btn-success"   style="text-align: left;font-size: 10px;padding:5px"><spring:message code="maindata.apply"  /></a></td>
                        </tr>
                        
                   </c:forEach>  
                   
                   
                   
                        </table> 
                       </div> 
       <script>
       
        function cancelpd(){
        	   $("#stock_pd").val("0");
        	    $("#display_stock_pd").val("").css("border", "2px solid red").prop("disabled", true);

       }
       
    $(document).ready(function() {
        // ฟังก์ชันกรองข้อมูลเมื่อพิมพ์ลงในช่องSearch
        $("#searchInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#Register tbody tr").filter(function() {
                // ซ่อนหรือแสดงแถวตามการSearch
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
    const id = row.find("td[data-custom='id']").text();
    const batch = row.find("td[data-custom='batch']").text();
    const product = row.find("td[data-custom='product']").text();
    const customer = row.find("td[data-custom='customer']").text();
    const purchase_order = row.find("td[data-custom='purchase_order']").text();
    // นำข้อมูลที่ได้ไปใส่ใน input ที่ต้องการ



    
    
    $("#stock_pd").val(id);
    const data =  " PD: "+pd+" Batch: "+batch+" Product: "+product;
    
    // นำข้อมูลที่ได้ไปใส่ใน input ที่ต้องการ
    $("#display_stock_pd").val(data).css("border", "2px solid red").prop("disabled", true);

    // หากต้องการทำให้สีขอบกลับเป็นปกติหลังจากเวลาหนึ่ง
    setTimeout(function() {
        $("#display_stock_pd").css("border", "").prop("readonly", false);
    }, 10000); // 10000ms = 10 วินาที
       
    
    
    
    
    
    
});
      </script>  
                                 
                                       </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                   
              
                   
                           <!-- Right Sidebar starts -->
                        <div class="modal modal-slide-in sidebar-todo-modal fade" id="new-task-modal2" >
                            <div class="modal-dialog sidebar-lg"  style="width:800px">
                                <div class="modal-content p-0">
                                      <form  action="#" id="adddistributor" method="POST" name="adddistributor"  class="todo-modal needs-validation"  novalidate onsubmit="return false">
                                        <div class="modal-header align-items-center mb-1">
                                            <h5 class="modal-title">Database</h5>
                                            <div class="todo-item-action d-flex align-items-center justify-content-between ms-auto">
                                              
                                                <i data-feather="x" class="cursor-pointer" data-bs-dismiss="modal" stroke-width="3"></i>
                                            </div>
                                        </div>
                                        <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                       
                                         <div class="todo-task-list-wrapper list-group" id="wrappermain_2">
                                 
                                     	<c:if test="${fkey.datalink_name == 'requisition_form'  }">  
									<c:set var="typeshow" value="3" />
									  <%@ include file="requisition_form.jspf" %>                                   
									</c:if>  
                                       </div>
                                 
                                        </div>
                                        
                                         </form> 
                                </div>
                            </div>
                        </div>
                   
     
     
     
               <!-- Right Sidebar starts -->
                        <div class="modal modal-slide-in sidebar-todo-modal fade" id="new-task-modal3" >
                            <div class="modal-dialog sidebar-lg"  style="width:800px">
                                <div class="modal-content p-0">
                                      <form:form method="POST" action="/registration_to_pr-filemanager"  > 
                                        <div class="modal-header align-items-center mb-1">
                                            <h5 class="modal-title">Registration To Pr </h5>
                                            <div class="todo-item-action d-flex align-items-center justify-content-between ms-auto">
                                              
                                                <i data-feather="x" class="cursor-pointer" data-bs-dismiss="modal" stroke-width="3"></i>
                                            </div>
                                        </div>
                                        <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                       
                                         <div class="todo-task-list-wrapper list-group" id="wrappermain_3">
                                 
                               
                                       </div>
                                 
                                        </div>
                                        
                                         </form:form > 
                                </div>
                            </div>
                        </div>
     
     
 
     
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
                                                        <div class="modal-footer" Style="display: inline-block;">
                                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="adddataset()">Import</button>
                                                        </div>
                                                    </div>
                                                </div>
      </div>
           
     

     
     
     
     
     
         <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>     
                       
       <%@ include file="../common/mainfoot.jspf" %>                   
            <script src="<c:url value="/resources/app-assets/vendors/js/extensions/jstree.min.js" />"></script>  






 <script src="<c:url value="/resources/app-assets/js/scripts/extensions/ext-component-drag-drop.js" />"></script>
<script src="<c:url value="/resources/app-assets/vendors/js/extensions/dragula.min.js" />"></script>
	  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	  

<script src="<c:url value="/resources/app-assets/vendors/js/ui/jquery.sticky.js" />"></script>	  
<script src="<c:url value="/resources/app-assets/vendors/js/forms/spinner/jquery.bootstrap-touchspin.js" />"></script>	  

	  
	  
    <script>
    
    
 // Min - Max

    var touchspinValue = $('.touchspin-min-max'),
      counterMin = 0,
      counterMax = 10;
    if (touchspinValue.length > 0) {
      touchspinValue
        .TouchSpin({
          min: counterMin,
          max: counterMax,
          buttondown_txt: feather.icons['minus'].toSvg(),
          buttonup_txt: feather.icons['plus'].toSvg()
        })
        .on('touchspin.on.startdownspin', function () {
          var $this = $(this);
          $('.bootstrap-touchspin-up').removeClass('disabled-max-min');
          if ($this.val() == counterMin) {
            $(this).siblings().find('.bootstrap-touchspin-down').addClass('disabled-max-min');
          }
        })
        .on('touchspin.on.startupspin', function () {
          var $this = $(this);
          $('.bootstrap-touchspin-down').removeClass('disabled-max-min');
          if ($this.val() == counterMax) {
            $(this).siblings().find('.bootstrap-touchspin-up').addClass('disabled-max-min');
          }
        });
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

    
    function  setsubkey(){
    
    	
       var subkey =  $("#subkey").val();
    	
   	  $("#subkey"+subkey).html(" ");
   

     	
     	
  	var keyed4 = $('#keyed4').find(":selected").val();  
 	 if(keyed4 !== "" &&  keyed4 != null){
		 var key4 = ` <span class="badge badge-light-primary">4.`+keyed4+`</span>`; 
		 document.getElementById("subkey"+subkey).insertAdjacentHTML('afterbegin',key4);
	 }
   	var keyed3 = $('#keyed3').find(":selected").val();  
	 if(keyed3 !== "" &&  keyed3 != null){
		 var key3 = ` <span class="badge badge-light-primary">3.`+keyed3+`</span>`; 
		 document.getElementById("subkey"+subkey).insertAdjacentHTML('afterbegin',key3);
	 }  
	var keyed2 = $('#keyed2').find(":selected").val();  
	 	 if(keyed2 !== "" &&  keyed2 != null){
			 var key2 = ` <span class="badge badge-light-primary">2.`+keyed2+`</span>`; 
			 document.getElementById("subkey"+subkey).insertAdjacentHTML('afterbegin',key2);
	}  
	   	  
	    	var keyed1 = $('#keyed1').find(":selected").val();  
	    	
		 	 if(keyed1 !== "" && keyed1 != null ){
				 var key1 = ` <span class="badge badge-light-primary">1.`+keyed1+`</span>`; 
				 document.getElementById("subkey"+subkey).insertAdjacentHTML('afterbegin',key1);
		   }
    
		 	$(".eflow_key_1_"+subkey).val(keyed1);
	    	$(".eflow_key_2_"+subkey).val(keyed2);
	    	$(".eflow_key_3_"+subkey).val(keyed3);
	    	$(".eflow_key_4_"+subkey).val(keyed4); 
  
    }
    
    function  edit(id){
    	
     $("#view_"+id).hide();
   	$("#edit_"+id).hide();
    $("#save_"+id).show();
  	$("#cancel_"+id).show();
 	$("#delete_"+id).show();
  	
 	$("#inputname_"+id).show();
 	$("#divname_"+id).hide();
 	$("#inputnote_"+id).show();
 	$("#divnote_"+id).hide();
 	$("#tag_"+id).show();
 	$("#modal_"+id).show();
 	
 	$(".datadel"+id).show();
	$(".dataset"+id).show();

    }
    
    function cancel(id){
    	$("#delete_"+id).hide();
        $("#view_"+id).show();
      	$("#edit_"+id).show();
       $("#save_"+id).hide();
     	$("#cancel_"+id).hide();
    	$("#inputname_"+id).hide();
    	$("#divname_"+id).show();
    	$("#inputnote_"+id).hide();
    	$("#divnote_"+id).show();
    	$("#tag_"+id).hide();
    	$("#modal_"+id).hide();
     	
     	$(".datadel"+id).hide();
    	$(".dataset"+id).hide();
   }
 function saves(id){
    	
	 var name = $("#inputname_"+id).val();
	 var note = $("#inputname_"+note).val();
	 $("#divnote_"+id).html(note);
	 $("#divname_"+id).html(name);
	 $("#delete_"+id).hide();
	 
		$(".datadel"+id).hide();
    	$(".dataset"+id).hide();
	 
        $("#view_"+id).show();
      	$("#edit_"+id).show();
       $("#save_"+id).hide();
     	$("#cancel_"+id).hide();
    	$("#inputname_"+id).hide();
    	$("#divname_"+id).show();
    	$("#inputnote_"+id).hide();
    	$("#divnote_"+id).show();
    	$("#tag_"+id).hide();
    	$("#modal_"+id).hide();
   }
    
    

     function setkey(id,data1,data2,data3,data4){
    	 $("#key").val(id);
    	 $("#mainkey1").val(data1);
    	 $("#mainkey2").val(data2);
    	 $("#mainkey3").val(data3);
    	 $("#mainkey4").val(data4);
     }
    
    function  addeform(id){
  	  $("#wrap").val(id);
  	  
    }
    
    function   del(id){
    	$("#booster"+id).remove();
    } 
    
    
    var selectElement = document.getElementById('dataeform');
    var selectedOption= selectElement.options[selectElement.selectedIndex];
    var dataCustomValue  = selectedOption.getAttribute('data-custom');
     var selectedValue = selectElement.value;
 	

 	selectElement.addEventListener('change', function() {
 		  selectedOption = selectElement.options[selectElement.selectedIndex];
 		 dataCustomValue = selectedOption.getAttribute('data-custom');
       	  selectedValue = selectElement.value;
         console.log(selectedValue);
 	});
 	
 	
 	function generateRandomNumber() {
 		  let randomNumber = '';
 		  for (let i = 0; i < 9; i++) {
 		    randomNumber += Math.floor(Math.random() * 9);
 		  }
 		  return randomNumber;
 		}
    
 
    

    
	function getdata(url,ID){

					        $.ajax({
					        type:'GET',
					        url:url,
					        data: {id : ID},
					        
					     success: function(data){
					    $("#savestatus").html(data);	
				
					     }
					});
		
		return false;
	    
 }
 
	  
	
		function save(url){
	
	        $.ajax({
	        type:'GET',
	        url:url,
	        data: {id : ID},
	        
	     success: function(data){
	    $("#savestatus").html(data);	
	
	     }
	});
	
	return false;
	
	}
	
		function deletes(url,ID){
			if(confirm("Want to delete?")){
				
		        $.ajax({
			        type:'GET',
			        url:url,
			        data: {id : ID},
			        
			     success: function(data){
			    	 $("#project_"+ID).remove();	
			     }
			});

			}
		}
    
    
    var element = document.getElementById("nav_monitor");
    element.classList.add("active");  
    function search() {

	     var filter = $('.files-filter2').val();
	      $('.datatable').filter(function() {
	    
	    	    $(this).hide();
	            var regex = new RegExp(filter, 'ig');
	          
	            if (regex.test($(this).text())) {
	                $(this).show();
	            } else {
	                $(this).hide();
	            }
	            
	            
	        });
	  
   
}
 
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


function remove(id){
	
	$("#"+id).remove();
}


    </script>














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




<script> 	



function postdata(Mode, Mode2, Mode3) {

    let targetDiv = $("#" + Mode3);
    
    // ทำให้เนื้อหาเก่าจางลง
    targetDiv.css("opacity", "0.5").css("position", "relative");

    // สร้าง Spinner อยู่ตรงกลางของ div
    let loadingSpinner = `
        <div class="spinner-overlay">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>`;

    // แทรก Spinner เข้าไปใน div
    targetDiv.append(loadingSpinner);

		
	
	
    var formData = new FormData($("#" + Mode2)[0]);

    $.ajax({
        url: Mode,
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function(result){
            $('#' + Mode3).html(result);
            targetDiv.css("opacity", "1"); // คืนค่า opacity ปกติ
        },
        error: function(jqXHR, textStatus, errorThrown) {
                window.location.href = '/login'; // URL ของหน้า login
       
        }
    });

   // $("#" + Mode2)[0].reset();
   
    return false;
}
	    
	    
	    
	    
	    
function getdata(url,div){
	

	 var data ="คุณต้องการลบรายการนี้ใช่หรือไม่?" 
	
		   
		 if ( confirm(data) ) {


				        $.ajax({
				        type:'GET',
				        url:url,
				        data: {ID : 0},
				        
				     success: function(data){
				  			  $("#"+div).html(data);	

				     },
				   error: function(jqXHR, textStatus, errorThrown) {
				       
				                // Redirect to login page if unauthorized
				                window.location.href = '/login'; // URL ของหน้า login
				      
				  }      
				        
				});
		}
		 
		
		 
		 
	return false;
 
	}

function getdatas(url,div){
	

	let loadingSpinner = `
	    <div class="d-flex justify-content-center align-items-center" style="height: 100%;">
	        <div class="spinner-border text-primary" role="status">
	            <span class="visually-hidden">Loading...</span>
	        </div>
	    </div>`;
		$("#" + div).html(loadingSpinner);
		


				        $.ajax({
				        type:'GET',
				        url:url,
				        data: {ID : 0},
				        
				     success: function(data){
				  			  $("#"+div).html(data);	

				     },
				   error: function(jqXHR, textStatus, errorThrown) {
			           //   window.location.href = '/error'; // URL ของหน้า login
				   
				  }      
				        
				});
		
		
		 
		 
	return false;

	}
	    
	    
	    
	</script> 	
	

















<script>





function openCenteredWindow(url, name, width, height) {
	

    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่โดยมี `noopener` และ `noreferrer` เพิ่มเข้ามา
    var newWindow = window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left + ', noopener, noreferrer');

 
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
	  <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>


	
	
	
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
	<script src="<c:url value="/lib/BadLogicRotate/badlogic-image-rotate.js" />"></script>
		   <script>
		   
		   const today = new Date();
		   const day = today.getDate();
		   const month = today.getMonth() + 1; // เดือนเริ่มจาก 0 ต้องบวก 1
		   const year = today.getFullYear();
		   
		   BadLogicRotate.init("cameraArea", {
		        videoWidth: "100%",
		        buttonClass: "btn btn-primary",
		        captureText: "ถ่ายภาพ",
		        pdfText: "รวม PDF",
		        cancelText: "ปิดกล้อง",
		        imagePreviewWidth: "120px",
		        inputFileId: "uploadFileInput",
		        cameraButtonId: "opencamera",
		        pdfName: "${process.name}_"+year+"-"+month+"-"+day+".pdf",
		        
		        onStart: function() {
	
		          	 $("#cameraArea").toggle();
		        	 $("#formss").toggle();
		        },
		        // Callback เมื่อกดยกเลิก
		        onCancel: function() {
		          	 $("#cameraArea").toggle();
		        	 $("#formss").toggle();
		        },
		         onCapture: function() {
		          	 
		        },
		        onGeneratePdf: function() {
		        	$("#cameraArea").toggle();
		        	 $("#formss").toggle();
		        },
		       

		        
		    });
		   
		   function switchs(id){
			   $("#"+id).toggle();
			   
		   }
		   
		    </script>



 

</body>

<%@ include file="../other/alert.jsp" %>






<c:if test="${Sys_Error != null }">
  		   <script> 
    $(document).ready(function() {
    	
    	   notify('error','Save','${Sys_Error_TYPE }');
      });
    
    </script>   
</div> 

</c:if>  


  <c:if test="${Sys_Save == 'Save_Sucess' && Sys_Error == null }"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Success');
      });
    
    </script>
</c:if>
       
  <script>
  

  

 





    
  

    
    
  
   
    
    
 
    </script>

 