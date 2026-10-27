<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

 <script>
 
 //alert(44);
 
 $("#balance_befores").val("${totals }");
 
$(".balance_before").val("${totals }");
 
$("#orders").val("${orders }");
$(".order").val("${orders }");

$("#amounts").val("${amounts }");

var totals = ${totals+0};
var reserve = ${reserve+0};



if(reserve > 0){
	$("#reserve").html("**"+(totals-reserve)+" คงเหลือจริงหลังหักยอดจอง **");
}else{
	$("#reserve").html("");
}




 </script>



<option value="-">-</option>
                                              <c:forEach items="${subedatas }"  var="subrows" varStatus="count">
                                                <c:if test='${field == 1  }'>   
                                            <option value="${subrows.data_value1 }" >${subrows.data_value1 }</option>
                                                </c:if>
                                            <c:if test='${field == 2  }'>   
                                            <option value="${subrows.data_value2 }" >${subrows.data_value2 }</option>
                                                </c:if>
                                                  <c:if test='${field == 3  }'>   
                                            <option value="${subrows.data_value3 }" >${subrows.data_value3 }</option>
                                                </c:if>
                                                  <c:if test='${field == 4  }'>   
                                            <option value="${subrows.data_value4 }" >${subrows.data_value4 }</option>
                                                </c:if>
                                                  <c:if test='${field == 5  }'>   
                                            <option value="${subrows.data_value5 }" >${subrows.data_value5 }</option>
                                                </c:if>
                                                  <c:if test='${field == 6  }'>   
                                            <option value="${subrows.data_value6 }" >${subrows.data_value6 }</option>
                                                </c:if>
                                                  <c:if test='${field == 7  }'>   
                                            <option value="${subrows.data_value7 }" >${subrows.data_value7 }</option>
                                                </c:if>
                                                  <c:if test='${field == 8  }'>   
                                            <option value="${subrows.data_value8 }" >${subrows.data_value8 }</option>
                                                </c:if>
                                                  <c:if test='${field == 9  }'>   
                                            <option value="${subrows.data_value9 }" >${subrows.data_value9 }</option>
                                                </c:if>
                                                  <c:if test='${field == 10  }'>   
                                            <option value="${subrows.data_value10 }" >${subrows.data_value10 }</option>
                                                </c:if>
                                                  <c:if test='${field == 11  }'>   
                                            <option value="${subrows.data_value11 }" >${subrows.data_value11 }</option>
                                                </c:if>
                                                  <c:if test='${field == 12  }'>   
                                            <option value="${subrows.data_value12 }" >${subrows.data_value12 }</option>
                                                </c:if>
                                                  <c:if test='${field == 13  }'>   
                                            <option value="${subrows.data_value13 }" >${subrows.data_value13 }</option>
                                                </c:if>
                                                  <c:if test='${field == 14  }'>   
                                            <option value="${subrows.data_value14 }" >${subrows.data_value14 }</option>
                                                </c:if>
                                                  <c:if test='${field == 15  }'>   
                                            <option value="${subrows.data_value15 }" >${subrows.data_value15 }</option>
                                                </c:if>
                                                  <c:if test='${field == 16  }'>   
                                            <option value="${subrows.data_value16 }" >${subrows.data_value16 }</option>
                                                </c:if>
                                                  <c:if test='${field == 17  }'>   
                                            <option value="${subrows.data_value17 }" >${subrows.data_value17 }</option>
                                                </c:if>
                                                  <c:if test='${field == 18  }'>   
                                            <option value="${subrows.data_value18 }" >${subrows.data_value18 }</option>
                                                </c:if>
                                                  <c:if test='${field == 19  }'>   
                                            <option value="${subrows.data_value19 }" >${subrows.data_value19 }</option>
                                                </c:if>
                                                     <c:if test='${field == 20  }'>   
                                            <option value="${subrows.data_value20 }" >${subrows.data_value20 }</option>
                                                </c:if>
                                               
                                    
                                               
 </c:forEach>
 
 

                                                 