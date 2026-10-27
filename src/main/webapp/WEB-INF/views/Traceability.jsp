<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>Traceability</title>
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
                  
                   <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title"> <spring:message code="nav.Dataset" text="ชุดข้อมูล" /></h4>
                            <sec:authorize access="hasAnyAuthority('ADD-DS')">    
                                   <a href="/adddataset-condition" class="btn btn-primary waves-effect waves-float waves-light" > 
                                   <spring:message code="btn.add"/></a>
                            </sec:authorize>
                            </div>
                             
                            <div class="card-body">
                             
                                   <input type="text" class="files-filter2 " placeholder="Search" style="float:right" onkeyup="search()"/>
                                 <br>   <br>
                             <div class="table-responsive">
                   <table class="table" >
                                    <thead>
                                        <tr >
                                            <th >
                                            <spring:message code="tables.No" text="ลำดับ​" />
                                            </th>
                                                <th ><spring:message code="forms.Name" /></th>
                                           <th ><spring:message code="nav.Datasetname" text="ชุดข้อมูล" /></th>
                                           <th >
                                             <spring:message code="tables.action" text="ดำเนินการ" />
                                           </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${datasets }" var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                        
                                            <td>${count.count }</td>
                                            <td>${row.dataset_name }</td>
                                        <td>${row.dataset_1 }
                                        <c:if test="${row.dataset_2 != '' }">   
                                        ,${row.dataset_2 }
                                        </c:if>
                                       <c:if test="${row.dataset_3 != ''}">   
                                        ,${row.dataset_3 }
                                        </c:if>
                                        <c:if test="${row.dataset_4 != ''}">   
                                        ,${row.dataset_4 }
                                        </c:if>
                                          <c:if test="${row.dataset_5 != ''}">   
                                        ,${row.dataset_5 }
                                        </c:if>
                                        
                                        </td>
                                                   <td>
                                                   <sec:authorize access="hasAnyAuthority('SET-DS')">    
                                                  <a href="/addsubdataset-condition/${row.dataset_id }" class="btn btn-info">
                                                    <spring:message code="btn.add" />
                                                  </a>
                                                  </sec:authorize>
                                                  
                                                  <sec:authorize access="hasAnyAuthority('EDIT-DS')">  
                                                    <a href="/editdataset-condition/${row.dataset_id }" class="btn btn-info">
                                                    <spring:message code="btn.edit" text="แก้ไข" /></a>
                                                  </sec:authorize>
                                                     
                                                <sec:authorize access="hasAnyAuthority('DELETE-DS')">   
                                                   
                                                   <a href="/deletedataset-condition/${row.dataset_id }" class="btn btn-danger" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.dataset_name}');">
                                                   <spring:message code="btn.delete" text="ลบ" /></a>
                                                 </sec:authorize>
                                              
                                                 
                                                   </td>
                                        </tr>
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
 

    <script>
    
    var element = document.getElementById("e-form2");
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


