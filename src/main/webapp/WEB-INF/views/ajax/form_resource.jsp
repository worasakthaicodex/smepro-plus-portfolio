 <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
 
  									 <div  class="row">
                                    <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.name"  /></label>
                                        <input type="text"  name="name"  id="name"  class="form-control" value="${tasks.task_name }" required >
                                    </div>
                                    <br>
                                    <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.description" /></label>
                                        <textarea  class="form-control" name="description" id="description"  rows="5"  required>${tasks.task_note }</textarea>
                                    </div>
                              
                                           <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.estimatedCost"  /></label>
                                        <input type="text"  name="estimatedCost"  id="estimatedCost"  class="form-control" value="${tasks.task_name }"  >
                                    </div>
                                            <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.unit"  /></label>
                                        <input type="text"  name="unit"  id="unit"  class="form-control" value="${tasks.task_name }"  >
                                    </div>
                                           <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.remarks"  /></label>
                                        <input type="text"  name="remarks"  id="remarks"  class="form-control" value="${tasks.task_name }"  >
                                    </div>
                                      <input type="hidden"  name="resourceType" value="${type }"  >
                                        <input type="hidden"  name="taskId" value="${upload_id }"  >
                                        <br><br>
       						    </div>   
