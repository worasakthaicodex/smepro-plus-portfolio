<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 85%;
                margin: 20px auto;
                background: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h1, h3, h2, h4{
                text-align: center;
                color: #333;
            }
            h2 {
                color: #555;
            }
            table {
            
                border-collapse: collapse;
                margin-bottom: 20px;
                  font-size:11px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 2px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            #print-button {
                display: block;
                margin: 20px auto;
                padding: 10px 20px;
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
                text-align: center;
                font-size: 16px;
                border-radius: 5px;
            }
           @media print {
                #print-button {
                    display: none;
                }
                body {
                    background-color: #fff;
                }
                .container {
                    width: auto;
                    margin: 0;
                    padding: 0;
                    box-shadow: none;
                }
                th {
                    background-color: #f2f2f2 !important;
                    color: #333 !important;
                    -webkit-print-color-adjust: exact;
                }
            }
            
            
            
             .equal-size-table th, .equal-size-table td {
            min-width: 100px; /* ปรับขนาดความกว้าง */
            max-width: 100px; /* ปรับขนาดความกว้าง */
            text-align: center; /* จัดให้อยู่กึ่งกลางแนวนอน */
            vertical-align: middle; /* จัดให้อยู่กึ่งกลางแนวตั้ง */
            
            font-size:11px;
 			}


        </style>
    </head>
    <body>
        <div class="container">

            <div class="report-section">
              
     
       
                <table style="width:100%;padding:20px">

                   <thead>
                         <tr>  <td ><h3>${conditions.condition_title}</h3>
                         
                                             <h5 >
                                             
                <c:if test="${searchs.search_product != '' }">
               <p style="width:48%;float:left"><strong>Product: </strong>${searchs.search_product }</p>  <br>
               </c:if>
                <p style="width:48%;float:left"><strong>Date: </strong>${searchs.search_date } - ${searchs.search_date_end } </p>  <br>                      
                </h5>
                         </td>  </tr>
                          <tr>  
                             </tr>
                    </thead>
                  
                  
                 
                        <tbody>
                     
                        <tr>
  
                            <th>${count.count}.${row}</th>
                        </tr>
                      
                        <tr>
                    
                            <td>
            
                                       <table class="equal-size-table">
                                        
                                       <tr>
                                    <c:forEach items="${edatas}" var="rows" begin="0" end="0" >     
                                    <c:if test="${rows.data_title1 != null}"><th>${rows.data_title1}</th></c:if>
                                    <c:if test="${rows.data_title2 != null}"><th>${rows.data_title2}</th></c:if>
                                    <c:if test="${rows.data_title3 != null}"><th>${rows.data_title3}</th></c:if>
                                    <c:if test="${rows.data_title4 != null}"><th>${rows.data_title4}</th></c:if>
                                    <c:if test="${rows.data_title5 != null}"><th>${rows.data_title5}</th></c:if>
                                    <c:if test="${rows.data_title6 != null}"><th>${rows.data_title6}</th></c:if>
                                    <c:if test="${rows.data_title7 != null}"><th>${rows.data_title7}</th></c:if>
                                    <c:if test="${rows.data_title8 != null}"><th>${rows.data_title8}</th></c:if>
                                    <c:if test="${rows.data_title9 != null}"><th>${rows.data_title9}</th></c:if>
                                    <c:if test="${rows.data_title10 != null}"><th>${rows.data_title10}</th></c:if>
                                    
                                    
                                   <c:if test="${rows.data_title11 != null}"><th>${rows.data_title11}</th></c:if>
                                  <c:if test="${rows.data_title12 != null}"><th>${rows.data_title12}</th></c:if>
                                   <c:if test="${rows.data_title13 != null}"><th>${rows.data_title13}</th></c:if>
                                      <c:if test="${rows.data_title14 != null}"><th>${rows.data_title14}</th></c:if>
                                    <c:if test="${rows.data_title15 != null}"><th>${rows.data_title15}</th></c:if>
                                    
                                    
                                    
                                    
                                    
                                  <th> <spring:message code="common.userinsert" /></th>
                                      <th><spring:message code="common.usercheck"  /></th>
                                             <th><spring:message code="common.datecheck"  /></th>
                                       <th><spring:message code="common.notecheck"  /></th>
                                                
                                             
                                
                                      </c:forEach>
                                       </tr>
                                        
                                        
                                          
                                          
                                          
                                    <c:forEach items="${edatas}" var="rows"  >   
                                         <tr>  
                                    <c:if test="${rows.data_title1 != null}"><td title=""> ${rows.data_value1}</td></c:if>
                                    <c:if test="${rows.data_title2 != null}"><td>${rows.data_value2}</td></c:if>
                                    <c:if test="${rows.data_title3 != null}"><td>${rows.data_value3}</td></c:if>
                                    <c:if test="${rows.data_title4 != null}"><td>${rows.data_value4}</td></c:if>
                                    <c:if test="${rows.data_title5 != null}"><td>${rows.data_value5}</td></c:if>
                                    <c:if test="${rows.data_title6 != null}"><td>${rows.data_value6}</td></c:if>
                                    <c:if test="${rows.data_title7 != null}"><td>${rows.data_value7}</td></c:if>
                                    <c:if test="${rows.data_title8 != null}"><td>${rows.data_value8}</td></c:if>
                                    <c:if test="${rows.data_title9 != null}"><td>${rows.data_value9}</td></c:if>
                                    <c:if test="${rows.data_title10 != null}"><td>${rows.data_value10}</td></c:if>
                                    
                                       <c:if test="${rows.data_title11 != null}"><td>${rows.data_value11}</td></c:if>
                                          <c:if test="${rows.data_title12 != null}"><td>${rows.data_value12}</td></c:if>
                                             <c:if test="${rows.data_title13 != null}"><td>${rows.data_value13}</td></c:if>
                                                <c:if test="${rows.data_title14 != null}"><td>${rows.data_value14}</td></c:if>
                                                   <c:if test="${rows.data_title15 != null}"><td>${rows.data_value15}</td></c:if>
                                    
                                    
                                    
                                    <td> ${rows.data_nameadd}</td>
                                             <td> ${rows.data_nameapp }</td>
                                                  <td> ${rows.data_datecheck }</td>
                                                       <td> ${rows.data_comment }</td>
                                           </tr>
                                      </c:forEach>
                                
                       
                                       </table>       
                                                               </td>
                        </tr>
                                          </tbody>            
                                
                    
                    
                      
                      
                    
                 
                    <tbody id="production-report">
                        <!-- Data will be populated here by JavaScript -->
                    </tbody>
                </table>
            </div>
            <button id="print-button" onclick="window.print()"><spring:message code="btn.Print" /></button>
        </div>
    </body>
</html>