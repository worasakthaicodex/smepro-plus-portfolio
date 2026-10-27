<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <script>

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