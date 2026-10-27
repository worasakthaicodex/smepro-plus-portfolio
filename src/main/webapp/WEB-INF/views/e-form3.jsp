<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<frameset cols="50%,50%">
    <frame src="/render-condition/${id2 }?title=Old Doc" >
    <frame src="/render-condition/${id }?title=New Doc" >

</frameset>