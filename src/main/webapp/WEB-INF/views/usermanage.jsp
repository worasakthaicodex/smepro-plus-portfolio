<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>index</title>
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
                <div class="col-lg-12">
                    <div class="white_card card_height_100 mb_30">
                  
                           
                        <div class="white_card_body">
                           <div class="card">
                              
        <div class="card-header border-bottom">
                                    <h4 class="card-title"> <spring:message code="nav.registration" text="ทะเบียนผู้ใช้" /> </h4>
                                    
                                   
                                    <sec:authorize access="hasAnyAuthority('ADD-UE')">  
                                    <a href="/add-usermanage"><button class="btn btn-primary waves-effect waves-float waves-light"><spring:message code="btn.add"/></button></a>
                                        
                                    </sec:authorize> 
                                  
                                    
                                </div>
                                
                                  <br>
                                      <a href="#" style="margin:10px;" onclick="cmode()"><button class="btn btn-primary waves-effect waves-float waves-light">View Full mode</button></a>
                                      <div onclick="display:none">
                                 <a href="#" style="margin:10px;" onclick="cmode()" ><button class="btn btn-primary waves-effect waves-float waves-light" >View Easy  mode</button></a>
                                </div>
                                
                                  <br> 
                                <div class="QA_table mb_30">
                                    <!-- table-responsive -->
                                    <table class="table lms_table_active3 " id="example">
                                        <thead>
                                            <tr>
                                                <th scope="col"><spring:message code="label.no" text="ลำดับ" /></th>
                                                 <th scope="col"><spring:message code="label.username" text="ชื่อผู้ใช้" /></th>
                                                <th scope="col"><spring:message code="label.name" text="ชื่อ" /></th>  
                                                 <th scope="col"><spring:message code="usermanage.role" text="บทบาท" /></th>       
                                                     <th scope="col"><spring:message code="tables.department" text="หน่วยงาน" /></th>                      
                                                <th scope="col"><spring:message code="label.building_action" text="จัดการ" /></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <c:forEach items="${user }" var="row" varStatus="count">
                                           
                                           
                                            <tr>
                                                <td>${count.count}</td>
                                                <td>${row.username}</td>
                                                <td>${row.user_name}</td>
                                                <td>${row.group_name}</td>
                                                  <td>${row.group_department}</td>              
                                                                <td>
                                                                
                                                                
                                <sec:authorize access="hasAnyAuthority('SET-UE')">                                  
                                     <a href="/set-usermanage?username=${row.username}" class="btn btn-light">
							          <spring:message code="btn.set" text="ปรับค่า" /></a>     
							          </sec:authorize>                                  
                                                                
                            <sec:authorize access="hasAnyAuthority('EDIT-UE')">                                      
  				          <a href="/edit-usermanage?id=${row.user_id}" class="btn btn-info">
							          <spring:message code="btn.edit" text="แก้ไข" /></a>
							          </sec:authorize> 
							          
				<sec:authorize access="hasAnyAuthority('DELETE-UE')">  			          
           <a href="/delete-usermanage/${row.user_id}" class="btn btn-danger" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.username}');"><spring:message code="btn.delete" text="ลบ" /></a>
							</sec:authorize> 	 
                                    
                                        
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
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>




<%@ include file="common/mainfoot.jspf" %>  
 
 
    <script>
    $(window).on('load', function() {
        if (feather) {
            feather.replace({
                width: 14,
                height: 14
            });
        }
    })
  
    
    function displays(){ 
 	   var Mode =  getCookie("Mode");
 		
    	     	if(Mode == 1){    
    	     	
    	     	 window.location.href = "/userfull-usermanage";
    			}
 
 		}

    

    function newcookieMode(type) { 
  	  var now = new Date();
  	  var time = now.getTime();
  	  var expireTime = time + 1000*36000;
  	  now.setTime(expireTime);
  	  document.cookie = 'Mode='+type+';expires='+now.toUTCString()+';path=/';
  	  //console.log(document.cookie);  // 'Wed, 31 Oct 2012 08:50:17 UTC'
  	
  	}
   
    function getCookie(name) {
        function escape(s) { return s.replace(/([.*+?\^$(){}|\[\]\/\\])/g, '\\$1'); }
        var match = document.cookie.match(RegExp('(?:^|;\\s*)' + escape(name) + '=([^;]*)'));
        return match ? match[1] : null;
    }
    
    
    
    function eraseCookie(name) {
        document.cookie = name+'=; Max-Age=0';
    }
    
    
    
    
    function cmode(){ 
    	
 	   var Mode =  getCookie("Mode");
    	
    	if(Mode == null){
    		newcookieMode(1);
			}else{
				eraseCookie("Mode");
				newcookieMode(1);
			}
			
    	
    window.location.href = "/userfull-usermanage";
    }
    
    
    

    
    

        
    var element = document.getElementById("nav_set");
  element.classList.add("active");      

    </script>
</body>

          