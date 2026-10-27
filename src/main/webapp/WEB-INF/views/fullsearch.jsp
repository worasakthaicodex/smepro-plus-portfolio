<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <% pageContext.setAttribute("newLineChar", "\n"); %>


<%@ include file="common/mainhead.jspf" %>    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel='stylesheet' href='https://code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css'>
 <link href="<c:url value="/resources/treeview/style.css" />" rel="stylesheet">
 <link href="<c:url value="/lib/select2/select2.min.css" />" rel="stylesheet">
 
 
<title>Full search</title>


<style>
.select2-selection__arrow{
display:none;
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
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><spring:message code="search.keysearch" /> </h4>
                                </div>
                                <div class="card-body">
                                     
                           <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                            
                                <div class="card-body">
                                       <form:form method="POST"  action="/fullsearch">    
                                   
                                    <div class="row">
                                        <div class="col-xl-4 col-md-6 col-12">
                                            <div class="mb-1">
                                                <label class="form-label" for="basicInput"><spring:message code="search.key" /></label>
                                       
                                                <input name="data" class="form-control date" id="helpInputTop" value="${data }">
                                              
                                                
                                            </div>
                                        </div>
                                        <div class="col-xl-4 col-md-6 col-12">
                                            <div class="mb-1">
                                                   <label class="form-label" for="basicInput"><spring:message code="search.datestart" /></label>
                                  
                                                <input type="date"  name="datestart" class="form-control date" id="helpInputTop" value="${datestart }">
                                            </div>
                                        </div>
                                    <div class="col-xl-4 col-md-6 col-12">
                                            <div class="mb-1">
                                                   <label class="form-label" for="basicInput"><spring:message code="search.dateend" /></label>
                                  
                                                <input type="date" name="dateend" class="form-control date" id="helpInputTop" value="${dateend }">
                                            </div>
                                        </div>
                                  
                                    </div>
                                    
                                    <button class="btn btn-primary waves-effect waves-float waves-light" type="submit"><spring:message code="search.key" /></button>
                                      </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <br>
          
          
          <div class="card-body">
                                    <ul class="nav nav-tabs" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="home-tab" data-bs-toggle="tab" href="#home" aria-controls="home" role="tab" aria-selected="true"> 
                                      
                                <spring:message code="doc.type1" text="บันทึกคุณภาพ"  />      
                                       (${file.size()+0 })
                                              </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" id="profile-tab" data-bs-toggle="tab" href="#profile" aria-controls="profile" role="tab" aria-selected="false">
                                     
                                     <spring:message code="doc.type2" text="เอกสารคุณภาพ"  />
                                      (${file2.size()+0 })
                                            </a>
                                        </li>
        
                                        <li class="nav-item">
                                            <a class="nav-link" id="about-tab" data-bs-toggle="tab" href="#about" aria-controls="about" role="tab" aria-selected="false">
                                           
                                           <spring:message code="doc.distribution" text="เอกสารเเจกจ่าย"  />
                                            (${file3.size()+0 })
                                            </a>
                                        </li>
                                                <li class="nav-item">
                                            <a class="nav-link" id="about-tab2" data-bs-toggle="tab" href="#about2" aria-controls="about" role="tab" aria-selected="false">
                                           
                                           <spring:message code="doc.type3" text="เอกสารทั่วไป"  />
                                            (${file4.size()+0 })
                                            </a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="home" aria-labelledby="home-tab" role="tabpanel">
                                         
                                             <table class="table " style="font-size:14px">
                                    <thead >
                                        <tr style="font-size:12px;padding:1">
                                           <th style="font-size:12px"><spring:message code="comment.date" /></th>
                                            <th style="font-size:12px"><spring:message code="file.name" text="ชื่อไฟล์" /></th>
                                              <th style="font-size:12px"><spring:message code="tables.record" text="ชื่อบันทึก"  /></th>
                                                <th style="font-size:12px"> <spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /></th>
                                                <th style="font-size:12px"> <spring:message code="tables.inspector" text="ผู้ดูเเล" /></th>
                                                <th style="font-size:12px">  <spring:message code="tables.storage" text="แหล่งที่เก็บ" /></th>
                                            <th style="font-size:12px"><spring:message code="file.size" text="ขนาด" /></th>
                                         
                                         
                                            <th style="font-size:12px">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody class="datatable">
                                   
                                        
                                 <c:forEach items="${file }" var="row" varStatus="count">
                                 
                                    <c:set var="path" value="${row.file_path}" />
					                      <c:if test="${row.file_shotcut ne null}">   
					                       <c:set var="path" value="${row.file_shotcut}" />
					                      </c:if>     
                                       
                                 
                                 
                                      <tr>
                                           <td> ${row.file_dateadd } </td>
                                       <td>${row.file_name_original }</td>
                                           <td>${row.upload_pn_title }</td>
                                             
                                              <td>${row.upload_pn_respon }</td>
                                               <td>${row.upload_pn_app }</td>
          
                                                <td> ${row.file_path_full }</td>
                                             <td>${row.file_size2 } </td>
                                        
                              
                                              <td>
                                                  
                                        <c:if test="${row.group_id  >  0 }">     
                                       <a href="/subfileinfo-filemanager?id=${row.file_id }&&Folders=${path}" target="_blank"  
                                        class="btn btn-light waves-effect waves-float waves-light"> 
                                         <spring:message code="btn.view"  />
                                        </a>
                                     </c:if>
                                        <c:if test="${row.group_id  == null }">    
                                         <spring:message code="common.notaccess"  />
                                        </c:if> 
                                     
                                     
                                              </td>
                                        </tr>
                                 
      
                               
        </c:forEach>          
                                        
                                    </tbody>
                                </table>             
    
                    
                                         
                                        
                                        </div>
                                        <div class="tab-pane" id="profile" aria-labelledby="profile-tab" role="tabpanel">
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
                                        
                                         <c:forEach items="${file2 }" var="row" varStatus="count">
                                         
                     
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
                                       E-Form System
                                </c:if>
                                                   </td>
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table></div>
                  
                                      
                                          <div class="tab-pane" id="about" aria-labelledby="about-tab" role="tabpanel">
                                               <table class="table" >
                                    <thead>
                                        <tr>
                                            <th><spring:message code="label.no" text="ลำดับ" /></th>
                                          
                                            <th><spring:message code="tables.docname" text="ชื่อเอกสาร" /></th>
                                            
                                                 <th><spring:message code="file.path" text="ที่เก็บเอกสาร" /></th>
                                        
                                        
                                        </tr>
                                        </thead>
                                       
                                        <c:forEach items="${file3 }" var="row" varStatus="count">
                                      
                                        <tr >
                                            <td> ${count.count } </td>
                                             <td> ${row.file_name_original }</td>
                                             <td> ${row.file_path_full }</td>
                                     
                                      </tr>
                                      
                            		  </c:forEach>
                                        
                                         </table>
                                        </div>
                                        
                                        
                                        
                                        <div class="tab-pane" id="about2" aria-labelledby="about-tab" role="tabpanel">
                                      <table class="table " style="font-size:14px">
                                    <thead >
                                        <tr style="font-size:12px;padding:1">
                                           <th style="font-size:12px"><spring:message code="comment.date" /></th>
                                            <th style="font-size:12px"><spring:message code="file.name" text="ชื่อไฟล์" /></th>
                      <th><spring:message code="file.path" text="ที่เก็บเอกสาร" /></th>
                                            <th style="font-size:12px"><spring:message code="file.size" text="ขนาด" /></th>
                                         
                                         
                                            <th style="font-size:12px">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody class="datatable">
                                   
                                        
                                 <c:forEach items="${file4 }" var="row" varStatus="count">
                                 
                                    <c:set var="path" value="${row.file_path}" />
					                      <c:if test="${row.file_shotcut ne null}">   
					                       <c:set var="path" value="${row.file_shotcut}" />
					                      </c:if>     
                                       
                                 
                                 
                                      <tr>
                                           <td> ${row.file_dateadd } </td>
               		                        <td>${row.file_name_original }</td>
                     						  <td> ${row.file_path_full }</td>
          
                                              
                                             <td>${row.file_size2 } </td>
                                        
                              
                                              <td>
                                                  
                                      <c:if test="${row.group_id  >  0 }">     
                                       <a href="/subfileinfo-filemanager?id=${row.file_id }&&Folders=${path}" target="_blank"  
                                        class="btn btn-light waves-effect waves-float waves-light"> 
                                          <spring:message code="btn.view"  />
                                        </a>
                                      </c:if> 
                                         <c:if test="${row.group_id  == null }">    
                                         <spring:message code="common.notaccess"  />
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
 

    <script src="<c:url value="/lib/select2/select2.js" />"></script>
  <script src="<c:url value="/lib/select2/lodash.min.js" />"></script>
    <script>

$(function () {
    items = []
    

        <c:forEach items="${uploadlist }" var="row" varStatus="count">
        items.push({ id: "${row.upload_pn_note }", text : "${row.upload_pn_note }"})
       </c:forEach>
       
        
  
    pageSize = 100

    jQuery.fn.select2.amd.require(["select2/data/array", "select2/utils"],

    function (ArrayData, Utils) {
        function CustomData($element, options) {
            CustomData.__super__.constructor.call(this, $element, options);
        }
        Utils.Extend(CustomData, ArrayData);

        CustomData.prototype.query = function (params, callback) {
        
            results = [];
            if (params.term && params.term !== '') {
              results = _.filter(items, function(e) {
     
                return e.text.toUpperCase().indexOf(params.term.toUpperCase()) >= 0;
              });
              
              
            } else {
              results = items;
            }

            if (!("page" in params)) {
                params.page = 1;
            }
            var data = {};
            data.results = results.slice((params.page - 1) * pageSize, params.page * pageSize);
            data.pagination = {};
            data.pagination.more = params.page * pageSize < results.length;
            callback(data);
        };

      
            $(".selects2").select2({
                ajax: {},
                dataAdapter: CustomData
            });
   
    })
});

</script>

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

var element = document.getElementById("${nav_dar}");
element.classList.add("active");  

    </script>
</body>


