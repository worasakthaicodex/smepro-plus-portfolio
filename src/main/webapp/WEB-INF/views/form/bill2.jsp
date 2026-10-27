<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var = "now" value = "<%= new java.util.Date()%>" />
<%@ include file="../common/mainhead.jspf" %>    
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">




<title>Requisition</title>

<style>
 @media print {
		    .notprint {
		      display: none;
		    }
		
		    th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
     
}
		    
		    .card{
		    border:0px;
		    }
		.footer{
		   display: none;
		}
		.table-responsive{
		   border:0px;
		}
		
		
		 table {
		    border-collapse:collapse;
		    margin: 0;
		  width: 100%;
		   border: 1px solid black;
		   
		}
		
		
		td {
    text-align: center;
   border: 1px solid black;
      max-width: 200px;
     font-size:14px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
}
td  .action{
 text-align: center;
    border: 1px solid black;
    max-width: 200px;
    font-size: 14px;
    white-space: normal; /* ขึ้นบรรทัดใหม่เมื่อเนื้อหาล้น */
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1px solid black;
  background-color: #f2f2f2;
     height: 30px;
     
}
		
	
		
		
  }
      * {
		
		 margin:5px;
		    }
  
      body {
		      width: 210mm; /* กว้างของกระดาษ A4 */
		      height: 297mm ; /* สูงของกระดาษ A4 */
		      margin: 0 auto; /* กำหนดขอบเพจเป็น 0 */
		  background-color:#ddd;
		  
		    }	
	.row{
	  
	  background-color:#fff;
	  
	  }
	  

  
  
  
  
.select2-selection__arrow{
display:none;
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
      max-width: 200px;
     font-size:14px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
}
td  .action{
 text-align: center;
    border: 1px solid #dddddd;
    max-width: 200px;
    font-size: 14px;
    white-space: normal; /* ขึ้นบรรทัดใหม่เมื่อเนื้อหาล้น */
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
     
}


.fixhead{
    max-width: 200px;
      min-width: 80px;
       
}



.fixhead2{
 background-color: #F0F8FF;
}


.pt{

 outline: 0;
  border-width: 0 0 1px;
  border-color: black;
  width:100px
}
.pts{

background:transparent;border:0;



 }
.pt3{

 outline: 0;
  border-width: 0 0 0px;
  width:100%;
}

*{
color:black;
}

</style>

<c:choose>
                                <c:when test='${cookie.theme.value.equals("moon")}'>
                               			<style>
											.select2-container--default .select2-selection--single .select2-selection__rendered{
											color:#b4b7bd;
											}
									 </style>
                                </c:when>
                                <c:otherwise>
								
                              </c:otherwise>
                               </c:choose> 

</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">



             
                               <div class="row">
                            <!-- Blog -->
                            <div class="col-12" id="maindata">
                            
                    <div  id="card${tasks.task_id}">
                                   <div class="card-body">
                                     
                                        <form:form method="POST" modelAttribute="report" action="/savereport-filemanager">
                                     
                                        <h4 class="card-title" style="width:150px;margin:auto">ใบเบิกค่าใช้จ่าย</h4>
                                        <div class="d-flex">
                                         
                                            <div class="author-info" style="width:100%">
                                            
                                             <div style="float:left">
                                                <small class="  ms-50 me-25" style="color:black">วันที่</small>
                                               ${edata.data_dateadd }
                                                
                                                </div> 
                                                <div style="float:right">
                                                <span class=" ms-50 me-25" style="color:black">เลขที่</span>
                                               ${r.r_date}${r.r_id}
                                                </div>
                                                
                                            </div>
                                        </div>
                                        
                                        
                                        
                                             <div class="d-flex">
                                         
                                            <div class="author-info" style="width:100%">
                                            
                                             <div style="float:left">
                                            
                                             ${edata.data_title1 }: ${edata.data_value1 }
                                                    
                                                </div> 
                                                <div style="float:right">
                                               ${edata.data_title2 }: ${edata.data_value2 }
                                                </div>
                                                
                                            </div>
                                        </div>
                                        
                                                   <div class="d-flex">
                                         
                                            <div class="author-info" style="width:100%">
                                            
                                          
                                        
                                                <span class=" ms-50 me-25" style="color:black">จ่ายให้</span>  <br>
                                              ${drawer.drawer_name}  
                                                       <br>
                                                       ${drawer.drawer_info}
                                                
                                            
                                        </div>  </div>
                                                
                                        <br>
                                        <table id="tabledata" class="tabledata" style="height:auto">
                               <thead>
                                 <th class="form-label fixhead">ลำดับ</th>
                                     <th class="form-label fixhead" style="width:70%">รายการ</th>
                               
                                     <th class="form-label fixhead">จำนวนเงิน</th>
                           
                             </th>
                                    </thead>
                                    
                                     <tbody>
                              <tr>
                                 <td><input type="text" class="pt3" value="1"></td>
                                 
                                       <td><textarea class="pt3">${edata.details }</textarea></td>
                                     <td><input type="text" class="pt3" value="${edata.withdrawal }"></td>
                             </tr>
                             
                                    </tbody>
                                    
                                   <tfoot>
                                   
                                     <tr>
                                 <th class="form-label fixhead"></th>
                                     <th class="form-label fixhead" style="width:70%">ยอดรวม</th>
                               
                                     <th class="form-label fixhead" >${edata.withdrawal }</th>
                              <tr>
                            
                                    </tfoot>  
                                    
                                    
                             </table>
                
<br>
      <div class="d-flex">
                                         
                                            <div class="author-info" style="width:100%">
                                          
                                                <small class="  ms-50 me-25" style="color:black">ผู้ขอเบิก</small>
                                                <small><input type="text" class="pt" value="${usersed_send.user_name }"></small>
                                              <small class="  ms-50 me-25" style="color:black">ผู้รับเงิน</small>
                                                <small><input type="text" class="pt" value="${usersed_clear.user_name }"></small>
                                                  <small class="  ms-50 me-25" style="color:black">ผู้จ่ายเงิน</small>
                                                <small><input type="text" class="pt" value="${usersed_send.user_name }"></small>
                                                  <small class="  ms-50 me-25" style="color:black">ผู้อนุมัติ</small>
                                                <small><input type="text" class="pt" value="${usersed_app.user_name }"></small>
                                            </div>
                                        </div>
<br>
<hr>
<br>
        
           <h4 class="card-title" style="width:150px;margin:auto">ใบเคลียร์ค่าใช้จ่าย</h4>
                                
                                        
                                        
                                        
                                     
                                      
                                        <br>
                                          <br>
                                        <table id="tabledata" class="tabledata" style="height:auto">
                               <thead>
                                 <th class="form-label fixhead">ลำดับ</th>
                                     <th class="form-label fixhead" style="width:70%">รายการ</th>
                               
                                     <th class="form-label fixhead">จำนวนเงิน</th>
                           
                             </th>
                                    </thead>
                                    
                                     <tbody>
                              <tr>
                                 <td><input type="text" class="pt3" value="1"></td>
                                 
                                       <td><textarea class="pt3">${edata.details_clearing }</textarea></td>
                                     <td><input type="text" class="pt3" value="${edata.spent }"></td>
                             </tr>
                             
                                    </tbody>
                                    
                                   <tfoot>
                                   
                                     <tr>
                                 <th class="form-label fixhead"></th>
                                     <th class="form-label fixhead" style="width:70%">จำนวนเงิน</th>
                               
                                     <th class="form-label fixhead" >${edata.spent }</th>
                              <tr>
                            
                                    </tfoot>  
                                    
                                    
                             </table>
                             <br>
                                   <div class="d-flex">
                                         
                                            <div class="author-info" style="width:100%">
                                            
                                             <div style="float:left">
                                               <small class="  ms-50 me-25" style="color:black">คืนเงิน</small>
                                                <small><input type="text" class="pt" value="${edata.returned }"></small>
                                                  <small class="  ms-50 me-25" style="color:black">เบิกเพิ่ม</small>
                                                <small><input type="text" class="pt" value="${edata.additional }"></small>
                                                </div>
                                                
                                            </div>
                                        </div>
                      <div class="d-flex">
                                         
                                          
                                        </div>
<br>
      <div class="d-flex">
                                         
                                            <div class="author-info" style="width:100%">
                                          
                                                <small class="  ms-50 me-25" style="color:black">ผู้คืน / รับเงิน</small>
                                                <small><input type="text" class="pt" value="${usersed_clear.user_name }"></small>
                                                  <small class="  ms-50 me-25" style="color:black">ผู้จ่ายเงิน</small>
                                                <small><input type="text" class="pt" value="${usersed_send.user_name }"></small>
                                                  <small class="  ms-50 me-25" style="color:black">ผู้อนุมัติ</small>
                                                <small><input type="text" class="pt" value="${usersed_send.user_name }"></small>
                                            </div>
                                        </div>                           
                             
                                        
                                        
                        <input type="hidden" name="report_id"  value="${report.report_id }" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    </form:form >
                                   
                                  
                              
                                    </div>
                                </div>
                          </div>
                            <!--/ Blog -->

                        

                            
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





<%@ include file="../common/mainfoot.jspf" %>  

    <script>
    document.getElementById('printButton').addEventListener('click', function() {
	    window.print(); // เรียกใช้งานการพิมพ์
	  });
    function mytype(){
    	 var value = document.getElementById("duetype").value;
    	
    	 
    	 
      	 if(value == 1){
    		 $("#duetype2").show();
    		 $("#duetype1").hide();
    		
    	 }else if(value == 2){
    
    		 $("#duetype1").hide();
    		 $("#duetype2").hide();
    		 
    	 }else{
    		 $("#duetype1").show();
    		 $("#duetype2").hide();
    	 }
    	 
    }
    
    
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
            var value = document.getElementById("duetype").value;
         	 if(value == 1){
        		 $("#duetype2").show();
        		 $("#duetype1").hide();
        		
        	 }else if(value == 2){
        
        		 $("#duetype1").hide();
        		 $("#duetype2").hide();
        		 
        	 }else{
        		 $("#duetype1").show();
        		 $("#duetype2").hide();
        	 }
        	 
        	 
            
            
        })
        
        var element = document.getElementById("nav_doc");
  element.classList.add("active");      


    </script>
    
    
    
    
</body>
<div class="modal fade" id="addNewCard" tabindex="-1" style="display: none;" aria-hidden="true">
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
                              <form:form method="POST" modelAttribute="task" id="formaddNewCardValidation" name="formaddNewCardValidation">
                                     <div id="savestatus">
                                   
                                      <input  type="hidden" name="task_id" id="task_id">
                                
                                <div  class="row">
                                    <div class="col-12">
                                        <label class="form-label" for="modalRoleName">ชื่องาน</label>
                                        <input type="text"  name="task_name"  id="task_name"  class="form-control" required >
                                    </div>
                                    <br>
                                    <div class="col-12">
                                        <label class="form-label" for="modalRoleName">คำอธิบายงาน</label>
                                        <textarea  class="form-control" name="task_note" id="task_note" rows="10" required></textarea>
                                    </div>
                                         <div class="col-6">
                                        <label class="form-label" for="modalRoleName">วันที่เริ่ม</label>
                                       <input type="date"  name="task_datestart" id="task_datestart"  class="form-control" required >
                                    </div>
                                        <div class="col-6">
                                        <label class="form-label" for="modalRoleName">วันที่สิ้นสุด</label>
                                         <input type="date" name="task_dateend" id="task_dateend"   class="form-control" required >
                                    </div>
                                    
                                  </div>   
                                    </div>
                                         <br>
                                    <div class="col-12 text-center mt-2">
     <button type="button"  class="btn btn-primary me-1 waves-effect waves-float waves-light" 
     data-bs-dismiss="modal" aria-label="Close" 
      onclick='postdata("/edittask-filemanager?${_csrf.parameterName}=${_csrf.token}","formaddNewCardValidation","maindata")'>บันทึก</button>
                                    </div>
                            </form:form>
                            
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
                </div>
 

<div class="modal fade" id="addNewCard2" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                                <div class="text-center mb-4">
                                    <h1 class="role-title">มอบหมายงานให้ผู้รับผิดชอบ</h1>
                                </div>
                                <!-- Add role form -->
                              <form:form method="POST" modelAttribute="task" id="formaddNewCardValidation2" name="formaddNewCardValidation2">
                                     <div >
                                   
                                      <input  type="hidden" name="task_id" id="task_id" value="${tasks.task_id }">
                                
                                <div  class="row">
                                    <div class="col-12">
                                        <label class="form-label" for="modalRoleName">ผู้รับผิดชอบ</label>
                              
                                           <select class="select2 form-select"  name="task_iduser" id="default-select-multi"  multiple="multiple"> 
                                 
                                                         <c:set var="listrespon" value="${tasks.task_iduser }" />
                                                          <c:set var="Array" value="${users.upload_pn_respon },${users.upload_pn_app }" />
                                                      
                                                           <c:set var="namesArray" value="${fn:split(Array,',')}" />

												<c:forEach var="nameItem" items="${namesArray}" begin="0" end="5">
												
												
												 <c:set var="check" value="" />
												    <c:set var="listcheck" value="${nameItem }" />
					                                     <c:if test='${fn:contains(listrespon,listcheck)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
												
												   <option value="${nameItem}"  ${check }>${nameItem}</option>
												</c:forEach>
												
												
                           
                                     
                                                      
                                                      
                                          </select>
                                        
                                    </div>
                                    
                                    
                                  </div>   
                                    </div>
                                         <br>
                                    <div class="col-12 text-center mt-2">
     <button type="button"  class="btn btn-primary me-1 waves-effect waves-float waves-light" 
     data-bs-dismiss="modal" aria-label="Close" 
      onclick='postdata("/addteamtask-filemanager?${_csrf.parameterName}=${_csrf.token}","formaddNewCardValidation2","maindata")'>บันทึก</button>
                                    </div>
                            </form:form>
                            
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
                </div>


                             
       <div class="modal fade" id="addNewCard3" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                                <div class="text-center mb-4">
                                    <h1 class="role-title">เอกสารประกอบ</h1>
                                </div>
                                <!-- Add role form -->
                              <form:form method="POST" modelAttribute="task" id="formaddNewCardValidation3" name="formaddNewCardValidation3" enctype="multipart/form-data">
                                     <div >
                                   
                                 
                                
                                <div  class="row">
                                    <div class="col-8">
                                        <label class="form-label" for="modalRoleName">เอกสารประกอบ</label>
                              
                                    <input type="file" name="file" class="form-control" >
                                    <input type="hidden" name="folder" id="folder" value="${folder }"  >
                                    <input  type="hidden" name="task_id" id="task_id" value="${tasks.task_id }">
                                    </div>
                                                                                                        
                                     <div class="col-4 text-center mt-2">
     <button type="button"  class="btn btn-primary me-1 waves-effect waves-float waves-light" 
     onclick='postdata("/addfiletask-filemanager?${_csrf.parameterName}=${_csrf.token}","formaddNewCardValidation3","maindata")'>นำเข้า</button>
                                    </div> 
                                    
                                  </div>  
  
                                    </div>             

                                         <br>
                                         
                                         <h4 class="mb-50">เอกสารประกอบ</h4>
                                         
                                         
                                         
                                         
                                         <div id="files">
		                                        <c:forEach var="row" items="${files}" begin="0" end="5">
		                                            <li class="d-block">
		                                                <span class="me-25">-</span>
		                                                <span>	<a href="/render2-filemanager?file=${row.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">${row.file_name_original }</a>
									     
									     
									     </span>
									     
					  
		<span class="me-25"><a  href="#"  onclick="getdata2('/delfiletask-filemanager/${tasks.task_id }/${row.file_id }','${row.file_id }');" class="btn btn-sm btn-danger"  >นำออก</a></span>
		                                            </li>
		                                            <br>
		                                         </c:forEach>  
                                           </div>   
                                           
                                           <br><br><br>
                                         
                      
                            </form:form>
                            
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
                </div>
       

  	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
            
                
  <script>  
  
  
function edit(div){
	  
	  $("#sub-"+div).toggle();
	  $("#edit-"+div).toggle();
	  var c = $("#c-"+div).val();
	  var f = $("#f-"+div).val();
	  $("#subimg-"+div).html(f);
     $("#comment-"+div).val(c);
	  $("#img-"+div).show();
	 $("#imgs-"+div).hide();
	  
	 
	 
}
function edit2(div){
	  
	  $("#subs-"+div).toggle();
	  $("#edits-"+div).toggle();
	  var c = $("#c-"+div).val();
	  var f = $("#f-"+div).val();
	  $("#subimg-"+div).html(f);
      $("#comment-"+div).val(c);
	 $("#img-"+div).show();
	$("#imgs-"+div).hide();
	  
}
  function filedownload(file){
  	
  	window.open("/filedownload-filemanager?id="+file, '_blank').focus();
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
			
					       }
					    });


				    $("#"+Mode2)[0].reset();
				    
		    return false;
		    }	
	    
	    
	    function getdata(url,id){
	    	
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
	    
	    
	    function getdata2(url,id){
	    	
	    	
	    	if (confirm("คุณต้องการดำเนินการ?"))
	    	{
			    	    $.ajax({
			    	        type:'GET',
			    	        url:url,
			    	        data: {ID : id},
			    	        
			    	     success: function(data){
			    	    $("#maindata").html(data);	
		
			    	     }
			    	});
	    		
	    	}
	    	
	    	
	    	
	    	

return false;

} 
	    
	    
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
	
	    
</script>  


   

   



   
      <script>
   
  
    function evaluateAndDisplay() {

   
      // ดึงทุก tr ที่มี class เป็น "inputDiv"
      var inputDivs = document.querySelectorAll('.inputDiv');

      // วนลูปทุก inputDivs
      inputDivs.forEach(function(inputDiv) {
    
    	    try {  
    	  // ดึงค่านิพจน์จาก attribute data-expression
        var expression = inputDiv.getAttribute('data-expression');
    	//alert(expression);
        // นำนิพจน์มาประมวลผล
   if(expression){
	      // นำนิพจน์มาประมวลผล
	      var result = eval(expression);

	      // แสดงผลลัพธ์ใน innerText ของ tr
	      inputDiv.innerText = result;
	   
   }

    } catch (error) {
        
    }
        
        
      });
    }
  </script>
   
 <script>
    function evaluateAndDisplay2() {
    	

    	
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
       
        
        if(expression){
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
       expression = expression.replace(/#1/g, i1).replace(/#2/g, i2).replace(/#3/g, i3).replace(/#4/g, i4).replace(/#5/g, i5)
       .replace(/#6/g, i6).replace(/#7/g, i7).replace(/#8/g, i8).replace(/#9/g, i9).replace(/#10/g, i10)
       .replace(/#11/g, i11).replace(/#12/g, i12).replace(/#13/g, i13).replace(/#14/g, i14).replace(/#15/g, i15)
        .replace(/#16/g, i16).replace(/#17/g, i17).replace(/#18/g, i18).replace(/#19/g, i19).replace(/#20/g, i20);
        
	
        
   
            // นำนิพจน์มาประมวลผล
            var result = eval(expression);

            // แสดงผลลัพธ์ใน innerText ของ tr
            inputDiv.innerText = result;
          } catch (error) {
              
          }
              

      });
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

   function  sum(){
 	  
 <c:forEach items="${eformfields }" var="rows" varStatus="count">  



 var total = 0;
 $('tr:has(td[data-custom="${rows.field_label }"]:visible)').each(function() {
     var value = parseInt($(this).find('td[data-custom="${rows.field_label }"]').text());
     if (!isNaN(value)) {
         total += value;
     }
 });







 // แทรกผลรวมลงใน <td> ที่มี id="ผลผลิต"



 $('#${rows.field_label }').text(total);

 </c:forEach>  



 <c:forEach items="${eformfields2 }" var="rows" varStatus="count">  



 var total = 0;
 $('tr:has(td[data-custom="${rows.field_label }"]:visible)').each(function() {
     var value = parseInt($(this).find('td[data-custom="${rows.field_label }"]').text());
     if (!isNaN(value)) {
         total += value;
     }
 });


 $('#${rows.field_label }').text(total);

 </c:forEach>  

   }


   

   evaluateAndDisplay();
   evaluateAndDisplay2();
   sum();
   

 </script>


 
	    