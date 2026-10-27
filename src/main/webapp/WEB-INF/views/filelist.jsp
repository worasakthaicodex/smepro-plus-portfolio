<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>File index</title>
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
                    
                         
                         
                         <div class="col-lg-6 col-md-6 col-12">
                        <div class="card card-app-design">
                        
                            <div class="card-body">
                    <h4 class="card-title">
                         <spring:message code="menu.Register" />
                                </h4>
                                <div class="design-planning-wrapper">
                                    <div class="design-planning">
                                        <p class="card-text mb-25">ALL</p>
                                        <h6 class="mb-0" id="ALL"></h6>
                                    </div>
                                    <div class="design-planning">
                                        <p class="card-text mb-25">QM</p>
                                        <h6 class="mb-0" id="QM"></h6>
                                    </div>
                                    <div class="design-planning">
                                        <p class="card-text mb-25">QP</h6>
                                          <h6 class="mb-0"  id="QP"></h6>
                                    </div>
                                        <div class="design-planning">
                                        <p class="card-text mb-25">WI</h6>
                                          <h6 class="mb-0"  id="WI"></h6>
                                    </div>
                                        <div class="design-planning">
                                        <p class="card-text mb-25">SD</h6>
                                          <h6 class="mb-0"  id="SD"></h6>
                                    </div>
                                    
                                       <div class="design-planning">
                                        <p class="card-text mb-25">FO (FORM + E-From)</h6>
                                          <h6 class="mb-0"  id="FO"></h6>
                                    </div>
                                    
                                </div>
                           
                            </div>
                        </div>
                    </div>
                         
                        </div>
                  
          
                    <div class="row">
                  
                   <div class="col-12">
                   
                   <div class="card">
                        
                        
                            <div class="card-body">
                                   <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                 <br>   <br>
                             <div class="table-responsive">
                   <table class="table" style="font-size:14px">
                                    <thead>
                                        <tr style="font-size:12px">
                                            <th style="font-size:12px">
                                            <spring:message code="tables.No" text="ลำดับ​" />
                                            </th>
                                                <th style="font-size:12px">  <spring:message code="tables.docid" text="รหัสเอกสาร​" /></th>
                                            <th style="font-size:12px">
                                            <spring:message code="tables.docname" text="ชื่อเอกสาร" />
                                            </th>
                                            <th style="font-size:12px">Rev</th>
                                            <th style="font-size:12px">
                                            <spring:message code="tables.docsize" text="ขนาด" />
                                            </th>
                                            
                                              <th style="font-size:12px"><spring:message code="tables.category" text="ชนิด" /></th>
                                         <th style="font-size:12px">
                                         <spring:message code="tables.datep" text="วันที่ประกาศใช้" />
                                         </th>
                                          <th style="font-size:12px">
                                           <spring:message code="tables.datee" text="วันที่หมดอายุ" />
                                          </th>
                                           <th style="font-size:12px">
                                             <spring:message code="tables.storage" text="แหล่งที่เก็บ" />
                                           </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                     <c:set var="type1" value="0" />
                                    <c:set var="type2" value="0" />
                                    <c:set var="type3" value="0" />
                                    <c:set var="type4" value="0" />
                                     <c:set var="type5" value="0" />
                                     
                                         <c:forEach items="${file }" var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                        
                                            <td>${count.count }</td>
                                            <td>${row.file_no }</td>
                                            <td>${row.file_name_original }</td>
                                            <td>${row.file_rev }</td>
                                             <td>${row.file_size2 }</td>
                                             <td>${row.file_code }</td>
                                        
                                           
                                                 <td>${row.file_dateadd }</td>
                                                  <td>${row.file_dateend }</td>
                                                   <td>
                                                   ${row.file_path_full }
                             
                                    <c:if test="${row.eform  >  0 }">   
                                       <c:set var="type5" value="${type5+1 }" />  
                                        <a href="/render-condition/${row.eform}" target="_blank" onclick="openCenteredWindow(this.href, 'File Window', 900, 650);return false;">Eform</a>
                                </c:if>
                                                   </td>
                                        </tr>
                                        
                                        
                                             <c:if test="${row.file_code.equals('Quality Manual (QM)')}">  
                                    <c:set var="type1" value="${type1+1 }" />
                                  </c:if> 
                                  <c:if test="${row.file_code.equals('Quality Procedure (QP)')}">  
                                    <c:set var="type2" value="${type2+1 }" />
                                  </c:if> 
                                  <c:if test="${row.file_code.equals('Work Instruction (WI)')}">  
                                    <c:set var="type3" value="${type3+1 }" />
                                  </c:if> 
                                  <c:if test="${row.file_code.equals('Supporting Data (SD)')}">  
                                    <c:set var="type4" value="${type4+1 }" />
                                  </c:if>
                                  <c:if test="${row.file_code.equals('Form (FO)')}">
                                    <c:set var="type5" value="${type5+1 }" />
                                  </c:if> 
                                        
                                          </c:forEach>
                                          
                                          
                                            <script>
                                            $("#ALL").html(${type1 +type2+ type3+ type4 + type5});
                                            $("#QM").html(${type1});
                                            $("#QP").html(${type2});
                                            $("#WI").html(${type3});
                                            $("#SD").html(${type4});
                                            $("#FO").html(${type5});
                                            </script>
                                          
                                          
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
 

    <script>
    
    var element = document.getElementById("nav_other6");
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
    
</body>


