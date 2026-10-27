<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Report kpi</title>
  
       <link href="<c:url value="/resources/app-assets/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/app-assets/css/bootstrap-extended.css" />" rel="stylesheet">
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>

<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
<style>

@import url(https://fonts.googleapis.com/css?family=Roboto:300);
.center-text{

  display: flex;
  align-items: flex-start; /* จัดตรงขอบบนขององค์ประกอบ */
  justify-content: center; /* จัดตรงกลางในแนวนอน */
}
.header {
  background: #182444;
  margin: -5px;
  padding-left: 5px;  
}
 table {
    width: 100%;
    border-collapse:collapse;
    margin: auto;
    height: 100%;
      border: 1px solid #eee;
    table-layout: fixed;
}

.filter{
  background: #C0C0C0;
}

.charts-page{
  padding: 0.2% 0 0;
  margin: auto;
}

.charts{

}


h2{
  color: #E06216;
  font-family: "Roboto", sans-serif;
}
body{

}


td {
    text-align: center;
   border: 1px solid #dddddd;
     font-size:14px;
}
td:first-child { text-align: left; }
th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
       font-size:14px;
}
td input {
    width: 100%;
 text-align: center;
  height: 100%;
     border: 1px solid #eee;
}


        .containers {
            width: 100%;
            height: 94vh;
            display: flex;
            overflow: hidden;
        }

        .half {
            flex: 1;
            overflow: auto;
            border: 1px solid #ccc;
            padding: 10px;
            min-width: 0; /* ใช้เพื่อให้ข้อความภายใน div ไม่ถูกตัดขอบ */
        }
    </style>
</head>
<body>
<!-- partial:index.partial.html -->
<div class="header">
<br>
<h1 style="color:#fff;margin:0px">E-form 


 <a href="#"  style="float:right" class="btn btn-primary me-1 waves-effect waves-float waves-light"  onclick="setting('main1')">
                                เเก้ไข
     </a> 
      <a href="#" style="float:right"  class="btn btn-primary me-1 waves-effect waves-float waves-light"  onclick="setting('main2')">
                                รายการสรุป
     </a> 
</h1>



</div>




<div class="charts-page">



<div class="containers">
        <div class="half" id="leftPane">
           <img src="/document-filemanager/DriveC/Folder26/OEUGQLX02M0D611BP.png">
        </div>
        <div class="half" id="rightPane">
         <div class="charts">
  
   

    <div class="row">
       
     <div class="col-md-12 ">
         <div id="main1" style="display:none">
         
          <div class="card-header">
                                    <h4 class="card-title">Reality Capture Work Flow (E-form Monitor)</h4>
                                </div>   
                                                <c:set var="title1" value="รายการ" />
                                               <c:set var="title2" value="${conditions.condition_type1 }" />
                                               <c:set var="title3" value="${conditions.condition_type2 }" />
                                               <c:set var="title4" value="${conditions.condition_type3 }" />
                                                <c:set var="title5" value="${conditions.condition_target }" />
                                                 <c:set var="title6" value="${conditions.condition_result }" />
                                               <c:set var="condition_typeunit1" value="${conditions.condition_typeunit1 }" />
                                             <input type="hidden"  value="${conditions.condition_id}" name="dataeform_condition_id"/> 
 <c:if test='${conditions.condition_1 != "" and  conditions.condition_1 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_1 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_1_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_1_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_1_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_1_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_1_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_1_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target1 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_1_total }" />  
                                                         <c:set var="listname" value="condition_1_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if> 
 <c:if test='${conditions.condition_2 != "" and  conditions.condition_2 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_2 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_2_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_2_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_2_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_2_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_2_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_2_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target2 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_2_total }" />  
                                                         <c:set var="listname" value="condition_2_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if> 
 <c:if test='${conditions.condition_3 != "" and  conditions.condition_3 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_3 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_3_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_3_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_3_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_3_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_3_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_3_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target3 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_3_total }" />  
                                                         <c:set var="listname" value="condition_3_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if> 
 <c:if test='${conditions.condition_4 != "" and  conditions.condition_4 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_4 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_4_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_4_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_4_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_4_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_4_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_4_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target4 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_4_total }" />  
                                                         <c:set var="listname" value="condition_4_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if>    
 <c:if test='${conditions.condition_5 != "" and  conditions.condition_5 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_5 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_5_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_5_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_5_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_5_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_5_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_5_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target5 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_5_total }" />  
                                                         <c:set var="listname" value="condition_5_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if> 
 <c:if test='${conditions.condition_6 != "" and  conditions.condition_6 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_6 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_6_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_6_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_6_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_6_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_6_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_6_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target6 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_6_total }" />  
                                                         <c:set var="listname" value="condition_6_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if>    
 <c:if test='${conditions.condition_7 != "" and  conditions.condition_7 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_7 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_7_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_7_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_7_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_7_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_7_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_7_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target7 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_7_total }" />  
                                                         <c:set var="listname" value="condition_7_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if>   
 <c:if test='${conditions.condition_8 != "" and  conditions.condition_8 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_8 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_8_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_8_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_8_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_8_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_8_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_8_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target8 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_8_total }" />  
                                                         <c:set var="listname" value="condition_8_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if>      
 <c:if test='${conditions.condition_9 != "" and  conditions.condition_9 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_9 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_9_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_9_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_9_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_9_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_9_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_9_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target9 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_9_total }" />  
                                                         <c:set var="listname" value="condition_9_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if>   
 <c:if test='${conditions.condition_10 != "" and  conditions.condition_10 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_10 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_10_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_10_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_10_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_10_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_10_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_10_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target10 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_10_total }" />  
                                                         <c:set var="listname" value="condition_10_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if>     
 <c:if test='${conditions.condition_11 != "" and  conditions.condition_11 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_11 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_11_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_11_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_11_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_11_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_11_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_11_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target11 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_11_total }" />  
                                                         <c:set var="listname" value="condition_11_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if>   
 <c:if test='${conditions.condition_12 != "" and  conditions.condition_12 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_12 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_12_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_12_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_12_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_12_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_12_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_12_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target12 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_12_total }" />  
                                                         <c:set var="listname" value="condition_12_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if>   
 <c:if test='${conditions.condition_13 != "" and  conditions.condition_13 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_13 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_13_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_13_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_13_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_13_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_13_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_13_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target13 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_13_total }" />  
                                                         <c:set var="listname" value="condition_13_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if>   
 <c:if test='${conditions.condition_14 != "" and  conditions.condition_14 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_14 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_14_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_14_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_14_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_14_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_14_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_14_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target14 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_14_total }" />  
                                                         <c:set var="listname" value="condition_14_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if> 
 <c:if test='${conditions.condition_15 != "" and  conditions.condition_15 != null}'>  
                                                
                                            
                                            <div data-repeater-list="invoice">
                                            <div data-repeater-item="">
                                                <div class="row d-flex align-items-end">
                                                    <div class="col-md-4 col-12 ">
                                                        <div class="mb-1">
                                                            <label class="form-label" for="itemname">${title1 }</label>
                                                            <input type="text" class="form-control" value="${conditions.condition_15 }" readonly>
                                                        </div>
                                                    </div>


	
                                 <div class="col-md-2 col-12 type1"  >
                                 
                                     <c:set var="listname" value="condition_15_note1" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit1}" />  
                                     <c:set var="listdata" value="${eformdata.condition_15_note1 }" />                  
                                     <c:set var="dataset" value="${dataset1 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title2 }</label>

     
                                                  <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>
	
		
		
         					<div class="col-md-2 col-12 type3" >
                                 
                                     <c:set var="listname" value="condition_15_note2" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit2}" />  
                                     <c:set var="listdata" value="${eformdata.condition_15_note2 }" />                  
                                     <c:set var="dataset" value="${dataset2 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title3 }</label>

     
                                                 <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>

       						  <div class="col-md-2 col-12 type4" >
                                 
                                     <c:set var="listname" value="condition_15_note3" />     
                                      <c:set var="conditionunit" value="${conditions.condition_typeunit3}" />  
                                     <c:set var="listdata" value="${eformdata.condition_15_note3 }" />                  
                                     <c:set var="dataset" value="${dataset3 }" />                
                                                    
                                                    
                                                        <div class="mb-1" >
                                                            <label class="form-label type1" for="itemcost">${title4 }</label>

     
                                             <c:if test="${conditionunit == 'Text'}">
                                                 <input type="text"  class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" value="${listdata }"/> 
                                              </c:if>
                                              <c:if test="${conditionunit == 'Number'}">
                                                 <input type="number"  s class="form-control" placeholder="กรอกข้อมูล"  name="${listname}" placeholder="กรอกข้อมูล"  value="${listdata }"/> 
                                             </c:if>

                                                <c:if test='${!conditionunit.equals("S1") and !conditionunit.equals("Text") and  !conditionunit.equals("Number")  }'>
                                                       <%@ include file="eformparts/select.jsp" %>  
                                                  </c:if>
                                                       
                                                 <c:if test="${conditionunit == 'S1'}">
                                                       <%@ include file="eformparts/select2.jsp" %>  
                                                 </c:if>
                                                       
                                                       
                                                        </div>
                               </div>


                                                    <div class="col-md-1 col-12 target">
                                                     <c:set var="listdata" value="${conditions.condition_target15 }" />     
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title5 }</label>
                                                            <input type="number" class="form-control"  class="form-control" value="${listdata }" readonly>
                                                        </div>
                                                    </div>
                                                    
                                                      <div class="col-md-1 col-12 result">
                                                      
                                                        <c:set var="listdata" value="${eformdata.condition_15_total }" />  
                                                         <c:set var="listname" value="condition_15_total" /> 
                                                         
                                                        <div class="mb-1">
                                                              <label class="form-label" for="itemquantity">${title6 }</label>
                                                            <input type="number" class="form-control" value="${listdata }"  name="${listname }">
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
               </c:if>     


</div>

<div id="main2" >


     <h6 class="center-text">บันทึก  : ${conditions.condition_title }</h6>
				                   <h6 class="center-text">${conditions.condition_subtitle }</h6>

				                   <c:if test="${conditions.condition_support4 == 1}">
				           		  <h6 class="center-text">ความถี่  : <input type="radio" name="condition_support4" value="1"> รายวัน / <input type="radio" name="condition_support4" value="2"> รายสัปดาห์ 
				           		  / <input type="radio" name="condition_support4" value="3"> รายเดือน/ <input type="radio" name="condition_support4" value="4"> รายเปี</h6>
				           		  </c:if>
				           		  
				             	
				             		  <h6 class="center-text">
				            <c:if test="${conditions.condition_support1 == 1}">
				           		วันที่บันทึก  : ${due }
				           	</c:if>
				           	<c:if test="${conditions.condition_support2 == 1}">
				           		เวลา  :  ${time } 
				           	</c:if>
				           	  	<c:if test="${conditions.condition_support3 == 1}">
				           		 แผนก :  ${department }  ตำเเหน่งงานผ้บันทึก :  ${groups } 
				           	</c:if>
				             		  </h6>
				           
                             
				                     <br>
				           
   
        <table class="table table-bordered">
          <thead>
            <tr >
                                            <th style="width:20%;font-size:14px;" class="type1">
                                            รายการ
                                            </th>
                                                <th  class="type2" style="font-size:14px;">${conditions.condition_type1 }</th>
                                            <th class="type3" style="font-size:14px;"> ${conditions.condition_type2 }</th>
                                            <th class="type4" style="font-size:14px;">${conditions.condition_type3 }</th>
                                            <th class="type5" style="font-size:14px;">${conditions.condition_target } </th>
                       
                                              <th class="type7" style="font-size:14px;">${conditions.condition_result }</th>
                                         
                                        </tr>
          </thead>
          
            
          
          
          <tbody>
            <tr>
              <td id="title1" class="type1">${conditions.condition_1 }&nbsp; </td>
              <td id="note1_1" class="type2">${eformdata.condition_1_note1 }</td>
              <td id="note1_2" class="type3">${eformdata.condition_1_note2 }</td>
              <td id="note1_3" class="type4">${eformdata.condition_1_note3 }</td>
               <td id="note1_4" class="type5"> ${conditions.condition_target1 }</td>
               
               
           
                      
                <td id="note1_5" class="type7">${eformdata.condition_1_total }</td>
            </tr>
       
       
            <tr>
              <td id="title2" class="type1">${conditions.condition_2 }&nbsp; </td>
              <td id="note2_1"  class="type2">${eformdata.condition_2_note1 }</td>
              <td id="note2_2" class="type3">${eformdata.condition_2_note2 }</td>
              <td id="note2_3" class="type4">${eformdata.condition_2_note3 }</td>
               <td id="note2_4" class="type5"> ${conditions.condition_target2 }</td>
               
        
            
                      
                <td id="note2_5" class="type7">${eformdata.condition_2_total }</td>
            </tr>
            
                 <tr>
              <td id="title3" class="type1">${conditions.condition_3 }&nbsp; </td>
              <td id="note3_1"  class="type2">${eformdata.condition_3_note1 }</td>
              <td id="note3_2" class="type3">${eformdata.condition_3_note2 }</td>
              <td id="note3_3" class="type4">${eformdata.condition_3_note3 }</td>
               <td id="note3_4" class="type5"> ${conditions.condition_target3 }</td>
               
          
                
                                <td id="note3_5" class="type7">${eformdata.condition_3_total }</td>
            </tr>
            
                 <tr>
              <td id="title4" class="type1">${conditions.condition_4 }&nbsp; </td>
              <td id="note4_1"  class="type2">${eformdata.condition_4_note1 }</td>
              <td id="note4_2" class="type3">${eformdata.condition_4_note2 }</td>
              <td id="note4_3" class="type4">${eformdata.condition_4_note3 }</td>
               <td id="note4_4" class="type5"> ${conditions.condition_target4 }</td>
               
               
                    
                   
                      
                      
                <td id="note4_5" class="type7">${eformdata.condition_4_total }</td>
                
                
            </tr>
            
                 <tr>
              <td id="title5" class="type1">${conditions.condition_5 }&nbsp; </td>
              <td id="note5_1"  class="type2">${eformdata.condition_5_note1 }</td>
              <td id="note5_2" class="type3">${eformdata.condition_5_note2 }</td>
              <td id="note5_3"  class="type4">${eformdata.condition_5_note3 }</td>
               <td id="note5_4" class="type5"> ${conditions.condition_target5 }</td>
  
          
     
  
  
                <td id="note5_5" class="type7">${eformdata.condition_5_total }</td>
            </tr>
            
                 <tr>
              <td id="title1" class="type1">${conditions.condition_6 }&nbsp; </td>
              <td id="note6_1"  class="type2">${eformdata.condition_6_note1 }</td>
              <td id="note6_2" class="type3">${eformdata.condition_6_note2 }</td>
              <td id="note6_3" class="type4">${eformdata.condition_6_note3 }</td>
               <td id="note6_4" class="type5"> ${conditions.condition_target6 }</td>
             

                      
                <td id="note6_5" class="type7">${eformdata.condition_6_total }</td>
            </tr>
            
                 <tr>
              <td id="title7" class="type1">${conditions.condition_7 }&nbsp; </td>
              <td id="note7_1"  class="type2">${eformdata.condition_7_note1 }</td>
              <td id="note7_2" class="type3">${eformdata.condition_7_note2 }</td>
              <td id="note7_3" class="type4">${eformdata.condition_7_note3 }</td>
               <td id="note7_4" class="type5"> ${conditions.condition_target7 }</td>
        
        
                      
                <td id="note7_5" class="type7">${eformdata.condition_7_total }</td>
            </tr>
            
                 <tr>
              <td id="title8" class="type1">${conditions.condition_8 }&nbsp; </td>
              <td id="note8_1"  class="type2">${eformdata.condition_8_note1 }</td>
              <td id="note8_2" class="type3">${eformdata.condition_8_note2 }</td>
              <td id="note8_3" class="type4">${eformdata.condition_8_note3 }</td>
               <td id="note8_4" class="type5"> ${conditions.condition_target8 }</td>
        
  
                      
                <td id="note8_5" class="type7">${eformdata.condition_8_total }</td>
            </tr>
            
                 <tr>
              <td id="title9" class="type1">${conditions.condition_9 }&nbsp; </td>
              <td id="note9_1"  class="type2">${eformdata.condition_9_note1 }</td>
              <td id="note9_2" class="type3">${eformdata.condition_9_note2 }</td>
              <td id="note9_3" class="type4">${eformdata.condition_9_note3 }</td>
               <td id="note9_4" class="type5"> ${conditions.condition_target9 }</td>


                      
                <td id="note9_5" class="type7">${eformdata.condition_9_total }</td>
            </tr>
            
                 <tr>
              <td id="title10" class="type1">${conditions.condition_10 }&nbsp; </td>
              <td id="note10_1"  class="type2">${eformdata.condition_10_note1 }</td>
              <td id="note10_2" class="type3">${eformdata.condition_10_note2 }</td>
              <td id="note10_3" class="type4">${eformdata.condition_10_note3 }</td>
               <td id="note10_4" class="type5"> ${conditions.condition_target10 }</td>
        

                      
                <td id="note10_5" class="type7">${eformdata.condition_10_total }</td>
            </tr>
            
                 <tr>
              <td id="title11" class="type1">${conditions.condition_11 }&nbsp; </td>
              <td id="note11_1"  class="type2">${eformdata.condition_11_note1 }</td>
              <td id="note11_2" class="type3">${eformdata.condition_11_note2 }</td>
              <td id="note11_3" class="type4">${eformdata.condition_11_note3 }</td>
               <td id="note11_4" class="type5"> ${conditions.condition_target11 }</td>
        
            
                      
                <td id="note11_5" class="type7">${eformdata.condition_11_total }</td>
            </tr>
            
                 <tr>
              <td id="title12" class="type1">${conditions.condition_12 }&nbsp; </td>
              <td id="note12_1"  class="type2">${eformdata.condition_12_note1 }</td>
              <td id="note12_2" class="type3">${eformdata.condition_12_note2 }</td>
              <td id="note12_3" class="type4">${eformdata.condition_12_note3 }</td>
               <td id="note12_4" class="type5"> ${conditions.condition_target12 }</td>
        
                      
                <td id="note12_5" class="type7">${eformdata.condition_12_total }</td>
            </tr>
            
                 <tr>
              <td id="title13" class="type1">${conditions.condition_13 }&nbsp; </td>
              <td id="note13_1"  class="type2">${eformdata.condition_13_note1 }</td>
              <td id="note13_2" class="type3">${eformdata.condition_13_note2 }</td>
              <td id="note13_3" class="type4">${eformdata.condition_13_note3 }</td>
               <td id="note13_4" class="type5"> ${conditions.condition_target13 }</td>
        
    
                      
                <td id="note13_5" class="type7">${eformdata.condition_13_total }</td>
            </tr>
            
                 <tr>
              <td id="title14" class="type1">${conditions.condition_14 }&nbsp; </td>
              <td id="note14_1"  class="type2">${eformdata.condition_14_note1 }</td>
              <td id="note14_2" class="type3">${eformdata.condition_14_note2 }</td>
              <td id="note14_3" class="type4">${eformdata.condition_14_note3 }</td>
               <td id="note14_4" class="type5"> ${conditions.condition_target14 }</td>
                 
  
                      
                <td id="note14_5" class="type7">${eformdata.condition_14_total }</td>
            </tr>
            
                 <tr>
              <td id="title15" class="type1" >${conditions.condition_15 }&nbsp; </td>
              <td id="note15_1"  class="type2">${eformdata.condition_15_note1 }</td>
              <td id="note15_2"  class="type3">${eformdata.condition_15_note2 }</td>
              <td id="note15_3" class="type4">${eformdata.condition_15_note3 }</td>
               <td id="note15_4" class="type5"> ${conditions.condition_target15 }</td>
        

                      
                <td id="note15_5" class="type7">${eformdata.condition_15_total }</td>
            </tr>
       
          </tbody>
        </table>
        <br> <br> <br>
                        <c:if test="${conditions.condition_support5 == 1}">
				           		
                                 	<h6>บันทึกเพิ่มเติ่ม (ผู้บันทึก) : </h6>
                                 	<textarea name="condition_support5_1">${eformdata.condition_support5_1 }</textarea> 
                                 	<h6>บันทึกเพิ่มเติ่ม (ผู้ตรวจ) : </h6> 
                                 	<textarea name="condition_support5_2">${eformdata.condition_support5_2 }</textarea> 
				         	</c:if>
      
        
      </div>
     
    </div>


   <br>   <br>   <br>
   
   </div>
   
    </div>


        </div>
        
        
        
    </div>




</div>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>


<!-- partial -->

<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>
<script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>
<script src="<c:url value="/resources/app-assets/js/scripts/extensions/ext-component-drag-drop.js" />"></script>
<script src="<c:url value="/resources/app-assets/vendors/js/extensions/dragula.min.js" />"></script>



<script>

function setting(div){

	$("#main1").hide();
	$("#main2").hide();
	$("#"+div).show();
}

</script>

</body>
</html>
