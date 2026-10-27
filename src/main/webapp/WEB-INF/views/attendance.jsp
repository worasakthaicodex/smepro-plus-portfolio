<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/head.jspf" %>   
<title>Smepro</title>
</head>
<!-- END: Head-->

<!-- BEGIN: Body-->




<body class="vertical-layout vertical-menu-modern blank-page navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="blank-page">
    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper">
            <div class="content-header row">
            </div>
            <div class="content-body">
           <div class="modal-body pb-5 px-sm-5 mx-50">
                <c:if test="${users != null}">  
                                <h1 class="text-center mb-2 pb-50" id="twoFactorAuthAppsTitle">Check  <c:if test="${attendances == null}"> IN</c:if>   <c:if test="${attendances != null}"> OUT </c:if> </h1>
</c:if>
                 <c:if test="${users == null}">   
                 
                <h1 class="text-center mb-2 pb-50" id="twoFactorAuthAppsTitle">  Attendance  </h1>            
</c:if>
								<div id="qrcode_id" >

                                <div class="d-flex justify-content-center my-2 py-50" id="formss">
                                  <c:if test="${users != null}">  
                                    <img class="img-fluid" src="data:image/png;base64,${qrCodeBytes}" width="122" alt="QR Code"> 
                                    
                                   <span style="padding:20px;padding-top:45px">OR</span>
                                    
                                     <a onclick="camera('files')" > <img class="img-fluid" src="/resources/app-assets/images/icons/capture.png" width="122" alt="QR Code"></a>
                                    </c:if>
                              
                              
                              
             
                    
                                </div>
                                
                                         <div  id="camera" style="display: none;">
             <span id="cameraFeeds">
                            <video id="cameraFeed" autoplay style="width:90%;margin-left:5%;zoom: 1;"></video><br><br>

                              <a href="#" id="captureButton" class="btn btn-primary me-1 waves-effect waves-float waves-light">ถ่ายรูป</a> 
    						  <a href="#" class="btn btn-primary me-1 waves-effect waves-float waves-light" onclick="generatePdf()" style="display:none">รวมเป็น PDF</a>
    						        <a href="#"  class="btn btn-light me-1 waves-effect waves-float waves-light" onclick="closes()">ยกเลิก</a> 
                             <br>
                             
                              <br>
                  </span>          
                              
                               <div class="row " id="card-drag-area">
                   
                    </div>
                              
                              
                        <canvas id="photoCanvas" style="display: none;"></canvas>
    						
    						
    								
    							
    									
    										
    												
    												
    											
    															
                              <br>
                              
                              
                               <div style="clear:both"></div>
                              
                              <br>  <br>  <br>
                                
                         
    						   
                          </div>  
                     
       			 <c:if test="${error == 'error'}"> 
                                <div class="alert alert-warning" role="alert">
                                    <h4 class="alert-heading">Error</h4>
                                    <div class="alert-body fw-normal">
                                      ไม่พบรหัสประจำตัวของคุณ
                                    </div>
                                </div>
               </c:if>
                                 </div>     
                                

                                <form class="row gy-1" onsubmit="return false">
                                  
                                    <c:if test="${users == null}"> 
	                                    <div class="col-12">
	                                        <input class="form-control"  id="Auth" type="text" placeholder="Enter authentication code" autocomplete="off">
	                                    </div>
                                    </c:if>
                                    
                                    <div class="col-12">
                                     <span style="float:left">
                                            <a href="/Unset-Attendance" onClick="confirm('เปลี่ยนผู้ใช้?')"  class="btn btn-outline-secondary mt-2 me-1 waves-effect" >
                                            เปลี่ยนผู้ใช้
                                        </a>
                                             <a href="/Unck-Attendance"  onClick="confirm('Check IN ใหม่?')" class="btn btn-outline-secondary mt-2 me-1 waves-effect" >
                                            Check IN ใหม่
                                        </a>
                                           </span>
                                      <span style="float:right">
                                        <a href="/login"  class="btn btn-outline-secondary mt-2 me-1 waves-effect" >
                                            Cancel
                                        </a>
                                      
                                    <c:if test="${users == null}"> 
                                        <button type="submit" onclick="navigateToNewPage()" class="btn btn-primary mt-2 waves-effect waves-float waves-light">
                                            <span class="me-50">Send</span>
                                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-chevron-right"><polyline points="9 18 15 12 9 6"></polyline></svg>
                                        </button>
                                       </c:if>
                                        
                                        
                                         </span>
                                    </div>
                                </form>
                            </div>
                            
                            
                            <div class="col-12" style="width:90%; margin:auto">
                            
                           
                            <c:if test="${users != null}">  
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Attendance  คุณคือ : ${users.user_name } ประจำวันที่ ${date }</h4>
                                
                            </div>
  
                      
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Date</th>
                                            <th>Check IN</th>
                                            <th>Check OUT</th>
                                           
                                             <th>IP</th>
                                             <th>Image IN</th>
                                              <th>Image OUT</th>
                                               <th>Location</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                  <c:forEach items="${list_attendances}" var="row" varStatus="count">  
                                        <tr>
                                            <td>${row.attendance_date }</td>
                                             <td>${row.attendance_checkin }</td>
                                             <td>${row.attendance_checkout }</td>
                                            
                                            
                                            <td>${row.attendance_ip }</td>
                                         <td> <c:if test="${row.attendance_file != null}"> 
                                        
                                         <img src="/readdocument/Attendance/${row.attendance_file}" style="width:100px;cursor: pointer;" target="_blank" 
									     onclick="openCenteredWindow(this.src, 'File Window', 500, 500);return false;"></img></c:if></td>
                                         <td> <c:if test="${row.attendance_file2 != null}">
                                         <img src="/readdocument/Attendance/${row.attendance_file2}" style="width:100px;cursor: pointer;" target="_blank" 
									     onclick="openCenteredWindow(this.src, 'File Window', 500, 500);return false;"></img></c:if></td>
									     
									     
                                             <td><a href="https://nominatim.openstreetmap.org/reverse?lat=${row.attendance_lat }&lon=${row.attendance_lon }&format=json"  target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 500, 200);return false;">${row.attendance_lat },${row.attendance_lon }</a></td>
                                        </tr>
                               </c:forEach>   
                                        
                                  
                                    </tbody>
                                </table>
                            </div>
                            
                                  <div class="card-body">
                                <p class="card-text">
                                  - กรอก Authentication ของคุณเพื่อยืนยันการ Check  
                                    <c:if test="${attendances == null}"> IN</c:if>   <c:if test="${attendances != null}"> OUT </c:if> จะปรากฏ QRCODE ให้เเสกน <br>
                                    - เมื่อมีการ Check IN เเล้วการเเสกนครั้งต่อไปจับนับเป็น Check OUT การเเเก้ไขเวลาให้เเสกนซ้ำในวันนั้น<br>
                                    - ถ้าพบกรณีที่ Check IN ผิดให้เลือกที่ <code>Check IN ใหม่</code> ก่อนเเสกนซ้ำ <br>
                                    - ไม่สามารถเเสกนเพื่อเเก้ไขข้อมูลได้ถ้าข้ามวัน
                                </p>
                            </div>
                        </div>
                    
                    </c:if>
                    
                    </div>
            </div>
        </div>
    </div>
    <!-- END: Content-->


  

<div class="modal fade" id="addRoleModal" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                                <div class="text-center mb-4">
                                    <h1 class="role-title">ยืนยันการ CHECK IN/OUT</h1>
                      
                                </div>
                                <!-- Add role form -->
                           
						<form:form  id="Form1" name="Form1" action="/inouts-Attendance?${_csrf.parameterName}=${_csrf.token}"   enctype="multipart/form-data">    
							<input type="file" name="file" id="files" class="form-control" >  
							<input type="hidden" name="username" value="${users.username }"> 
							<input type="hidden" name="lat" value="${lat }"> 
							<input type="hidden" name="lon" value="${lon }" > 
							
						
						                   
						          
                                    <div class="col-12 text-center mt-2">
                                        <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">บันทึก</button>
                                    </div>
                           </form:form>  
                       
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
                </div>

  



</body>
<%@ include file="common/foot.jspf" %>  

     <script>
     
     function navigateToNewPage(){
     	
    	 var Auth = $("#Auth").val();
    	 
    	 
    	 var newPageUrl = "/SetAuth-Attendance/"+Auth;
         window.location.href = newPageUrl;
    	
    }
    
    
     
     function Invert(button) {
         const cardDiv = button.closest('.draggable');
         if (cardDiv) {
             cardDiv.remove(); // ลบ div ที่คลิกออกไป
         }
         $("#cameraFeeds").toggle();
     }

     const cardDragArea = document.getElementById("card-drag-area");

     let divCount = 0; // เริ่มจาก 0 และเพิ่มทีละ 1 ทุกครั้งเมื่อกดปุ่ม

     // ฟังก์ชันสร้าง div ใหม่
     function createNewDiv() {
         divCount++; // เพิ่มจำนวน div

         const newDiv = document.createElement("div");
         newDiv.className = "col-xl-2 col-md-3 col-sm-3 draggable";
         
         newDiv.id = "card" + divCount; // เพิ่ม id ตามลำดับ

         newDiv.innerHTML = '<div class="card"><div class="card-header"> <h4 class="card-title">เข้างาน </h4><a href="#" onclick="generatejpg(this)"  data-bs-target="#addRoleModal" data-bs-toggle="modal">ใช้รูปนี้</a><a href="#" onclick="Invert(this)">ถ่ายใหม่</a></div> <div class="card-body"><img id="capturedPhoto'+divCount+'" class="capture" style="width:100%;float:left;margin-left:5px" alt="รูปถ่าย"> </div></div>';

         cardDragArea.appendChild(newDiv); // เพิ่ม div ใหม่ลงใน card-drag-area
         

                 feather.replace({
                     width: 14,
                     height: 14
                 });
         
                
                 $("#cameraFeeds").toggle();
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
        
       $("#submit").trigger('click'); 
      
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


				    
		    return false;
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
    
    
 

    
    function openCenteredWindow(url, name, width, height) {
        // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
        var left = (window.innerWidth / 2) - (width / 2);
        var top = (window.innerHeight / 2) - (height / 2);

        // เปิดหน้าต่างใหม่
        window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
    }

  
       
    

    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
    </script>
    
    
    
    



