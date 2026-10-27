<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <% pageContext.setAttribute("newLineChar", "\n"); %>


<%@ include file="common/mainhead.jspf" %>    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel='stylesheet' href='https://code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css'>
 <link href="<c:url value="/resources/treeview/style.css" />" rel="stylesheet">
<title>Move File</title>
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
                                    <h4 class="card-title">Move</h4>
                                </div>
                                <div class="card-body">
                                     
                           
                                         <form:form method="POST"   modelAttribute="dar" action="/filemove-filemanager">         
                                    <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">File : </label>
                                                <input type="hidden" name="id" value="${file.file_id }">
                                                <div class="col-sm-9">
                                               ${file.file_name_original }
                                                </div>
                                            </div>   
                                <div class="mb-1 row">
                                                <label for="colFormLabelLg" class="col-sm-3 col-form-label-lg">Path : </label>
                                             
                                                <div class="col-sm-9">
                                               ${file.file_path_full }
                                                </div>
                                            </div>  
	          
	  	  <div class="mb-1 row">  									              
					<label for="colFormLabelLg" class="col-sm-3 col-form-label-lg"><spring:message code="dar.setpath" text="ระบุตำเเหน่งเอกสาร" /></label>
          <div class="col-sm-9">
<br>

       <ul class="tree required" id="webmeterdata_1_1" >  
 <c:forEach items="${drivess}" var="row">
               
	       <li class="tree-item" id="drivess">	
	       
	    <a href="javascript:void(0)" class="trigger" ><i class="d"></i>${row.drives_name}</a>

     
	     
	     <ul class="tree-parent" id="webmeterdata">
	<c:forEach items="${folders}" var="rows">    
	<c:if test="${rows.folder_idrefer == row.drives_id   }">      
		 
		                      
		 
		      
		      <li class="tree-item" id="metertree">
		       	<c:if test="${rows.folder_type2 == 0   }"> <input type="radio" name="mainfolder" value="${rows.folder_type}" ></c:if>  <a href="javascript:void(0)" class="trigger" ><i data-feather='folder'></i> ${rows.folder_name}</a>
       
				   <ul class="tree-parent">
				   
							   <c:forEach items="${folders}" var="rowss">    
								             <c:if test="${rowss.folder_idrefer == rows.folder_id }">     
								         
								                             
								         
								             
								              <li class="tree-item" id="metertree">
												            	<c:if test="${rows.folder_type2 == 0   }"> <input type="radio" name="mainfolder" value="${rowss.folder_type}" required></c:if> <a href="javascript:void(0)"  class="trigger"  > <i data-feather='folder'></i> ${rowss.folder_name}</a>
												         
								              </li>
								             
								              </c:if> 		    
				           </c:forEach>    
				    </ul>     
				         
		      </li>
		 
     </c:if> 		    
	  </c:forEach>   
    </ul>
	  
	  </li>
	  
 
 </c:forEach>


</ul>


          </div>				              
	  </div>
	  <br><br>
	   <button class="btn btn-primary me-1 mt-1 waves-effect waves-float waves-light"  onclick="return confirm('<spring:message code="action.move" text="ยืนยันการย้าย :" />${dar.dar_doc_name} ?');">
                                        Save
                                        </button>
	  
	  				
                                                 </form:form>
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


