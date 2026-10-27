<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en"  ">

    <div id="body" class="mainbody2"   >
      <div id="data_iadmin">
       




  <div class="container"  >
   
   
          
  
       <div class=" toolbar"   style="border-top: 1px solid #808080;">
<div class="btn-group" style="color:black">

 
  <button class="fa-button" title="โหลดใหม่"  onclick="getData('/app-filemanager/${process.id}', 'microsofts${process.id}')"  style="color:black" data-original-title="List"> 
    <i class="material-icons text-default">
    restart_alt
  </i></button>
  
  
 <div style="display:none">
  <button class="fa-button " title="นำออก" 
  th:onclick="'shows(\'draggable-iadmin\',\'นำออก\'); getData(\'' + @{/export/department }+ '\', \'mainbody-iadmin\')'"
   style="color:black;"   data-original-title="Img">
    <i class="material-icons text-default">
      download
    </i>
   </button>
   <button class="fa-button "  title="ค้นหา"  data-original-title="Img"
   th:onclick="'shows(\'draggable-iadmin\',\'ค้นหาเเบบละเอียด\'); getData(\'' + @{/department/search }+ '\', \'mainbody-iadmin\')'"
   >
    <i class="material-icons text-default">

      manage_search
    </i>
   </button>
   <button class="fa-button " title="จัดเรียง"   data-original-title="Img"
   th:onclick="'shows(\'draggable-iadmin\',\'จัดเรียง\'); getData(\'' + @{/department/sort }+ '\', \'mainbody-iadmin${process.id}\')'"
   >
    <i class="material-icons text-default">

      swap_vert
    </i>
   </button>
  </div>
 


 

    
     
    


   <span style="float:right;padding: 0;margin-top:-4px" >

      </span>




    </div>



    
</div>

     
  </div>
  
  <div class="container2"  >
  
  <div id="body" class="mainbody"   >
  
  
  
  
  <table class="datatable">
      
      
  <thead>
    <tr>

      <th onclick="sorted('title_1')">ลำดับ</th>
            <th onclick="sorted('title_2')">Name</th>
        <th onclick="sorted('title_3')">Regiter name</th>
                <th>Similarity</th>


  </tr>
  </thead>
  <tbody>
 <c:forEach items="${levenshtein }" var="shtein" varStatus="count">  
                            
                                              <tr >
				                                       <td style="padding:5px;font-size:13px">${count.count }</td >
				                                     <td style="padding:5px;font-size:13px">${shtein.shtein_name1 }</td >
				                                      <td style="padding:5px;font-size:13px"> ${shtein.shtein_name2 }</td >   
				                                       <td style="padding:5px;font-size:13px">
											
														      <div class="fw-bold text-body-heading me-1">${shtein.distance}%</div>   
														
														    	
																	                                        
				                                        </td >                            
 


				                                        </tr>
                                         </c:forEach>

</tbody>
</table>
      
  
  
  </div>
  
  
  
  <div class="footer">
  
  </div>
  
  
  </div>
   














    






