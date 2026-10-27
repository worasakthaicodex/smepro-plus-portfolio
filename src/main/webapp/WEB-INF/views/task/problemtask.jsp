<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
     <!-- Blog Comment -->
                            <div class="col-12 mt-1" id="blogComment">
                        
		              
                    	    <form method="POST"  id="problems_form" name="problems_form">


      									<input type="hidden" name="task_id" value="${tasked.task_id}">
                                        <input type="hidden" name="actionplan_id" value="${actionplan.actionplan_id}">
                                        
                                        <div id="subproblems_form">
  												<%@ include file="formproblems_list.jsp" %>  
                                        </div>
                                    </form>
                                    
                                    <br>
                                    
                                    
                                    <div id="problems_list">
                                 		  <%@ include file="problems_list.jsp" %>  
                                     </div>
                                
                                    </div>
                          
                            
<script>
$("#task_title").html('<spring:message code="project.problemsidentified" /> ');
</script>                     
                            
                         
                            