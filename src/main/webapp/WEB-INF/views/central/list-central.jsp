<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    

<title>File index</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>  

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
                           <div class="content-header row">
                <div class="content-header-left col-md-9 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-12">
                            
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                   <li class="breadcrumb-item"><a href="/central-condition/0">Central</a>
                                    </li>
                                    
                                   <c:if test="${maincentrals.central_level < 2 }"> 
                             
		                                 <c:if test="${maincentrals.central_id > 0}"> 
		                                    <li class="breadcrumb-item "><a href="/viewcentral-condition/${maincentrals.central_id  }">${maincentrals.central_name2 }</a>
		                                    </li>
		                                    </c:if>
		                                
		                                 
		                                     <c:if test="${Foldername != null and  Subfoldername != null}">    
		                                    <li class="breadcrumb-item"><a href="/filemanager/${Drives }/${Folders }?id=${Foldersid }">${Foldername }</a>
		                                    </li>
		                                    </c:if>
                                
                                    </c:if>
                                  
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="content-header-right text-md-end col-md-3 col-12 d-md-block d-none">
                    <div class="mb-1 breadcrumb-right">
                  
                    </div>
                </div>
            </div>
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Central</h4>
                                
                                <c:if test="${maincentrals.central_level < 2 }">
                                         <c:if test="${maincentrals.central_id > 0}">    
                                   <a href="/addcentral-condition/${maincentrals.central_level+1}/${maincentrals.central_id}" class="btn btn-primary waves-effect waves-float waves-light" >ADD</a>
                                       </c:if>
                                          <c:if test="${maincentrals.central_id < 0}">    
                                   <a href="/addcentral-condition/${level }" class="btn btn-primary waves-effect waves-float waves-light" >ADD</a>
                                       </c:if>
                                             </c:if>
                                         <c:if test="${maincentrals.central_level == 2 }">
                                        <a href="/addmainfield-condition/${maincentrals.central_id }" class="btn btn-primary waves-effect waves-float waves-light" >ADD</a>
                                           </c:if>
                                        
                                        
                            </div>
                             
                            <div class="card-body">
                             
                                   <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                 <br>   <br>
                                 
                                 
                                 
                             <div class="table-responsive">
                                           
<c:if test="${maincentrals.central_id > 0}">    
                   ${maincentrals.central_name1 } [${maincentrals.central_name2 }]<br>  <br> 
</c:if>                    
                  
                  
                 <c:if test="${centrals != null}">  
                   <table class="table" >
                                    <thead>
                                        <tr >
                                            <th >
                                            <spring:message code="tables.No" text="ลำดับ​" />
                                            </th>
                                         
                                           <th width="30%">Central name</th>
                                           <th width="30%">Central type</th>
                                           <th>Central child</th>
                                           <th>
                                             <spring:message code="tables.action" text="ดำเนินการ" />
                                           </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${centrals }" var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                        
                                            <td>${count.count }</td>
                                            <td>${row.central_name1 } [${row.central_name2 }]</td>
                                        <td>
                                        ${row.central_type1 } [${row.central_type2 }]
                                       </td>
                                       
                                       
                                    
                                        <td>
                                        ${row.central_total }
                                       </td>
                                                   <td>
                                                    <a href="/viewcentral-condition/${row.central_id  }" class="btn btn-light"><spring:message code="btn.View" text="ดู" /></a>
                                                    
                                                    <a href="/editcentral-condition/${row.central_id  }/${row.central_level  }" class="btn btn-info"><spring:message code="btn.edit" text="แก้ไข" /></a>
                                                    
                                                   
                                                   <a href="/deletecentral-condition/${row.central_id  }/${row.central_level  }?idrefer=${maincentrals.central_id+0 }" class="btn btn-danger" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.central_name1}');">
                                                   <spring:message code="btn.delete" text="ลบ" /></a>
                                               
                                                   </td>
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table></div>
                   
                   </c:if>
                  
                      <c:if test="${mainfield != null}">  
                         <table class="table" >
                                    <thead>
                                        <tr >
                                            <th >
                                            <spring:message code="tables.No" text="ลำดับ​" />
                                            </th>
                                         
                                           <th width="30%">Mainfield name</th>
                                           <th width="30%">Input type</th>
                                           <th>Input name</th>
                                           <th>
                                             <spring:message code="tables.action" text="ดำเนินการ" />
                                           </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${mainfield }" var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                        
                                            <td>${count.count }</td>
                                            <td>${row.field_name1 } [${row.field_name2 }]</td>
                                        <td>
                                        ${row.field_inputtype } 
                                       </td>
                                       
                                       
                                    
                                        <td>
                                        ${row.field_inputname }
                                       </td>
                                                   <td>
                                                    <a href="/viewmainfield-condition/${row.field_id  }" class="btn btn-light"><spring:message code="btn.View" text="ดู" /></a>
                                                    
                                                    <a href="/editmainfield-condition/${row.field_id  }" class="btn btn-info"><spring:message code="btn.edit" text="แก้ไข" /></a>
                                                    
                                                   
                                                   <a href="/deletemainfield-condition/${row.field_id  }" class="btn btn-danger" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.field_name1}');">
                                                   <spring:message code="btn.delete" text="ลบ" /></a>
                                               
                                                   </td>
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table></div>
                   
                    </c:if>
                   
                   
                   
                   
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






<%@ include file="../common/mainfoot.jspf" %>  
 

    <script>
    
    var element = document.getElementById("e-form3");
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
</body>


