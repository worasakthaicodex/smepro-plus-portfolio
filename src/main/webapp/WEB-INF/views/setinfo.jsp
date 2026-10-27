<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    
<title>Folder</title>

<style>


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

        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
                        <div class="col-8">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Folder</h4>
                                </div>
                                <div class="card-body">
                                     
                             
                                   
                                               
                                   
                                     
                                <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 "><b><spring:message code="folder.name" text="ชื่อโฟล์เดอร์"  /> :</b></label>
                                                <div class="col-sm-9">
                                                    ${folder.folder_name }
                                                </div>
                                            </div>
                                       <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 "><b><spring:message code="folder.use" text="ใช่ไป"  /> :</b></label>
                                                <div class="col-sm-9">
                                                    ${folder.folder_use2 }
                                                </div>
                                            </div>
                                                 <div class="mb-1 row">
                                                <label  class="col-sm-3"><b><spring:message code="folders.type" text="ประเภท" />:</b></label>
                                                <div class="col-sm-9">
                                                    <c:if test="${folder.folder_type2 == 0}"> <spring:message code="folders.type1" text="ทั่วไป" /></c:if>
                                                     <c:if test="${folder.folder_type2 == 1}"> <spring:message code="folders.type2" text="เอกสารคุณภาพ" /></c:if>
                                                      <c:if test="${folder.folder_type2 == 2}">  <spring:message code="folders.type3" text="สำเนาเอกสารคุณภาพ" /></c:if>
                                                       <c:if test="${folder.folder_type2 == 3}"> <spring:message code="folders.type4" text="บันทึกคุณภาพ" /></c:if>
                                                       
                                                </div>
                                            </div>
                                            
                                            
                                            
                                                   <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 "><b><spring:message code="folders.Installedprograms" text="ประเภท" />:</b></label>
                                                <div class="col-sm-9">${programs} </div>
                                            </div>
                                            
                                   
                                <br>    
                                
                                
                                
                                
                             <a href="/setauthorities-filemanager/${folder.folder_id }" >
                             <button class="btn btn-primary waves-effect waves-float waves-light" style="margin-top:10px"> <spring:message code="usermanage.access" text="สิทธิ์ในการเข้าถึง" /></button></a>
                                       
                                       
                                        </div>
                             
                                </div>
                            
                            </div>
                         
                         
                         <div class="col-lg-4 col-md-6 col-12">
                        <div class="card card-app-design">
                            <div class="card-body">
                     
                                <div class="design-group">
                                    <h6 class="section-label"> <spring:message code="usermanage.access" text="สิทธิ์ในการเข้าถึง" /></h6>
                                    
                         <c:forEach items="${ACCESS }" end="20"  var="row" varStatus="count">
                                    <span class="badge badge-light-warning">${row.authority }</span>
                        </c:forEach>
                                      
                                </div>
                                <div class="design-group">
                                    <h6 class="section-label"><spring:message code="usermanage.Latestusers" text="สิทธิ์ในการเข้าถึง" /></h6>
                                    
                       <c:forEach items="${views }" end="20"  var="row" varStatus="count">
                       
                                   <div class="avatar" title="${row.user_name }">
                                        <img src="/resources/app-assets/images/portrait/small/avatar.jpg"  width="34" height="34" alt="Avatar">
                                    </div>
                                    
                        </c:forEach>
                                        
                                    
                               
                                 
                                  
                                </div>
                                <div class="design-planning-wrapper">
                                    <div class="design-planning">
                                        <p class="card-text mb-25"><spring:message code="plan.total" text="จำนวนแผนงาน" /></p>
                                        <h6 class="mb-0" id="totalplan"></h6>
                                    </div>
                                    <div class="design-planning">
                                        <p class="card-text mb-25"><spring:message code="btn.enable" text=" เปิด" /></p>
                                        <h6 class="mb-0" id="use"></h6>
                                    </div>
                                    <div class="design-planning">
                                        <p class="card-text mb-25"><spring:message code="btn.disable" text="ปิด" /></h6>
                                          <h6 class="mb-0"  id="notuse"></h6>
                                    </div>
                                </div>
                           
                            </div>
                        </div>
                    </div>
                         
                        </div>
                  
                  
                   <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">
                            <spring:message code="plan.setplan"  />
                                </h4>
                                
                                     <c:if test="${folder.folder_type2 ==  3}">  
                                   <a href="/addupload_pn-filemanager/${idfolder}"><button class="btn btn-primary waves-effect waves-float waves-light">Add</button></a>
                      </c:if>
                      
                            </div>
                            
                            
                            
                            <div class="card-body"> <div class="table-responsive">
                            
                         
                   <table  >
                                    <thead>
                                        <tr>
                                            <th class="form-label fixhead">  <spring:message code="tables.No" text="ลำดับ"  /></th>
                                            <th class="form-label fixhead"><spring:message code="tables.record" text="ชื่อบันทึก"  /></th>
                                             <th class="form-label fixhead">E-form</th>
                                                 <th class="form-label fixhead"><spring:message code="tables.type" text="ประเภท" /></th>
                                              <th class="form-label fixhead"> <spring:message code="tables.due" text="กำหนด" /></th>
                                              
                                              <th class="form-label fixhead"><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /></th>
                                              <th class="form-label fixhead"><spring:message code="tables.inspector" text="ผู้ดูเเล" /></th>
                                            <th class="form-label fixhead"> <spring:message code="tables.clearing"  /></th>
                                              <th class="form-label fixhead"><spring:message code="tables.action" text="จัดการ" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                    <c:set var="use" value="0" />
                                    <c:set var="notuse" value="0" />
                                     <c:forEach items="${upload_pn }" var="row" varStatus="count">
                                        <c:set var="due" value="" />
                                       <c:if test="${row.upload_pn_due ==  0 && row.upload_pn_type == 0 }">   
                      						<spring:message code="due.date" text="ทุกวัน"  var="due"/>
                     					 </c:if>
                                        <c:if test="${row.upload_pn_due ==  1 }">   
                      						<spring:message code="due.week" text="ทุกอาทิตย์"  var="due"/>
                     					 </c:if>
                     					    <c:if test="${row.upload_pn_due ==  2 }">   
                      						<spring:message code="due.month" text="ทุกเดือน"  var="due"/>
                     					 </c:if>
                     					
                     				
                                        <tr>
                                        
                                            <td>${count.count }</td>
                                            <td>
                                                                 
                                            
       <a href="/relationplan-condition/${row.upload_pn_monitor}" target="_blank"   
              onclick="openCenteredWindow(this.href, 'File Window', 1500, 600);  return false;" title="Relation view"  style="display:none"> ${row.upload_pn_note } </a>       
                                   
                                          
                          
                                            ${row.upload_pn_note }
                                         
                                             
                                             
                                            </td>
                                             <td>   <c:if test="${row.upload_pn_monitor > 0 and  row.upload_pn_monitor < 100000000}">Yes</c:if></td>
                                              <td>
                                              
                                               <c:set var="type" value="" />
                                                  <c:if test="${row.upload_pn_type == 0}">    
                                                             <spring:message code="due.type1" text="กำหนดตามช่วงเวลา"  var="type"/>
                                                         </c:if>  
                                                      <c:if test="${row.upload_pn_type == 1}">    
                                                            <spring:message code="due.type2" text="กำหนดตามวันที่" var="type"/>
                                                         </c:if>  
                                                    <c:if test="${row.upload_pn_type ==2}">    
                                                            <spring:message code="due.type3" text="ไม่มีกำหนด" var="type"/>
                                                         </c:if>  
                                                   <c:if test="${row.upload_pn_type ==3}">    
                                                            <spring:message code="menu.project"  var="type"/>
                                                         </c:if>  
                                         ${type }
                                              </td>
                                            <td>${due }
                                            	     <c:if test="${row.upload_pn_type == 1 }">   
                      					 	${row.upload_pn_datedue }
                     					 </c:if>
                                            
                                            </td>
                                             <td title="${row.upload_pn_respon }">${row.upload_pn_respon }</td>
                                              <td>${row.upload_pn_app }</td>
                                               <td>${row.upload_pn_clearing }</td>
                                            <td>
                                            
                                               <c:if test="${folder.folder_type2 ==  3}">  
          	     <c:if test="${row.upload_pn_end == 0 }">                                     
             <a href="/checkupload_pn-filemanager?ids=${row.upload_pn_id}&&state=1&&Folder=${folders}&&id=${idfolder}" class="btn btn-sm btn-light">
							          ปิด </a>		
							          
							            <c:set var="use" value="${use+1 }" />
                                   
							          
					</c:if>		   
					  <c:if test="${row.upload_pn_end == 1 }">             
				   	
							          
                                    <c:set var="notuse" value="${notuse+1 }" />    
							            <a href="/checkupload_pn-filemanager?ids=${row.upload_pn_id}&&state=0&&Folder=${folders}&&id=${idfolder}" class="btn btn-sm btn-success">
							         เปิด</a>          
					</c:if>			          
       <a href="/editupload_pn-filemanager?id=${row.upload_pn_id}" class="btn btn-sm btn-info">
							          <spring:message code="btn.edit" text="แก้ไข" /></a>
							          
							          
           <a href="/deleteupload_pn-filemanager/${row.upload_pn_id}" class="btn btn-sm btn-danger" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.upload_pn_name}');"><spring:message code="btn.delete" text="ลบ" /></a>
								 
                       
                                            </c:if>
                                            
                                            
                                            
                                            </td>
                                            
                                        </tr>
                                              <script>
                                              $("#totalplan").html(${count.count});
                                              $("#use").html(${use});
                                              $("#notuse").html(${notuse});
                                              </script>
                                     </c:forEach>
                                    </tbody>
                                </table></div>
                   </div>   </div>
                    </div>
                    </div>
                    
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>





<%@ include file="common/mainfoot.jspf" %>  
 
  <c:if test="${dar.dar_flow > 1}"> 
   <script>
   function check(){
		 if( $('ul.required :radio:checked').length > 0){
			 return false;
			 alert('ระบุตำเเหน่งเอกสาร');
		 } 
		 return true;
   }

     </script>
     </c:if>
   <c:if test="${dar.dar_flow < 2}"> 
      <script>
   function check(){

		 return true;
   }

     </script>
  </c:if>
    <script>
    
 
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

var element = document.getElementById("nav_doc");
element.classList.add("active");      


    </script>
    
    
    <script>
function openCenteredWindow(url, name, width, height) {
	
	

    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}


var element = document.getElementById("nav_doc");
element.classList.add("active");  
</script>
</body>





