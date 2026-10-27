<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="th">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Traceability Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1000px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        .form-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .form-container > div {
            flex: 1 1 45%;
            display: flex;
            flex-direction: column;
            margin-bottom: 10px;
        }

        .form-container label {
            margin-top: 10px;
        }

        .form-container input, .form-container select {
            padding: 10px;
            margin-top: 5px;
        }

        button {
            padding: 10px;
            margin-top: 20px;
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .result {
            margin-top: 10px;
            padding: 20px;
            background-color: #e9e9e9;
            border-radius: 5px;
            overflow-x: auto;
        }

        .transaction-table {
            width: 95%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .transaction-table th, .transaction-table td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }

        .transaction-table th {
            background-color: #f9f9f9;
        }

        .transaction-table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
		  .form-label {
            min-width: 250px; /* กำหนดความกว้างของ label */
            text-align: right; /* จัดข้อความไปทางขวา */
            padding-right: 10px; /* เพิ่มพื้นที่ข้างขวาของ label */
        }

		  @media print {
                .noprint {
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
    </style>
</head>
<body>
    <div class="container">
        <h1 class="noprint"><spring:message code="workflows.Traceability"  /> </h1>
         <div class="noprint">
           <form action="/Traceabilitys-workflow" method="POST">
        <div class="form-container">
           
           
           <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">  
            <input  type="hidden" name="id" value="${id}">  
           
           
           <c:forEach items="${eformfields }"   var="row" varStatus="count">   

               <c:if test='${row.field_cal_type == "product" }'>     
                                     <c:set var="product" value="${row.field_label }" />  
                                     <c:set var="productno" value="${count.count }" />                     
                    </c:if>
                    
                    
                    
                   <c:if test='${row.field_cal_type == "lot" }'>   
                                     <c:set var="lot" value="${row.field_label }" />  
                                     <c:set var="lotno" value="${count.count }" />        
                    </c:if>
                    

                     <c:if test='${row.field_cal_type == "balance" }'>   
                                     <c:set var="balancelabel" value="${row.field_label }" />  
                                       <c:set var="balanceno" value="${count.count }" />      
                                    
                    </c:if>
                     <c:if test='${row.field_cal_type == "due" }'>   
                                     <c:set var="duelabel" value="${row.field_label }" />  
                                    
                    </c:if>
                    
                             <c:if test='${row.field_cal_type == "plus" }'>   
                                     <c:set var="addlabel" value="${row.field_label }" />  
                                    
                    </c:if>
                    <c:if test='${row.field_cal_type == "minus" }'>   
                                     <c:set var="dellabel" value="${row.field_label }" />  
                                    
                    </c:if>
                      <c:if test='${row.field_cal_type == "order" }'>   
                                     <c:set var="orderlabel" value="${row.field_label }" />  
                                    
                    </c:if>
                       <c:if test='${row.field_cal_type == "unit" }'>   
                                     <c:set var="unitlabel" value="${row.field_label }" /> 
                                       <c:set var="unitno" value="${count.count }" />    
                                    
                    </c:if>
                    
           </c:forEach>
           
           
           

            <div style="margin-right:10px;">
              <label for="production-stage">${product}:</label>
                <select id="production-stage" name="product">
                   <option value="-">-</option>
                <c:forEach items="${stock }" var="row" varStatus="count">
                
             <c:if test='${productno == 1  }'>  <c:set var="value" value="${row.data_value1}" /> </c:if>   
              <c:if test='${productno == 2  }'>  <c:set var="value" value="${row.data_value2}" /> </c:if>   
               <c:if test='${productno == 3  }'>  <c:set var="value" value="${row.data_value3}" /> </c:if>   
                <c:if test='${productno == 4  }'>  <c:set var="value" value="${row.data_value4}" /> </c:if>   
                 <c:if test='${productno == 5  }'>  <c:set var="value" value="${row.data_value5}" /> </c:if>   
                  <c:if test='${productno == 6  }'>  <c:set var="value" value="${row.data_value6}" /> </c:if>   
                   <c:if test='${productno == 7  }'>  <c:set var="value" value="${row.data_value7}" /> </c:if>   
                    <c:if test='${productno == 8  }'>  <c:set var="value" value="${row.data_value8}" /> </c:if>      
               
                <option value="${value}">${value}</option>      
                  
                </c:forEach>
                
                
                
                </select>
            </div>
            
              <c:set var="rowrun" value="2" /> 
              <c:if test='${lot != null}'>   
              <c:set var="rowrun" value="3" />  
            <div>
                <label for="production-stage">${lot}:</label>
                <select id="production-stage" name="lot">
                  <option value="-">-</option>
                <c:forEach items="${stock_lot }" var="row" varStatus="count">
                 
                 
              <c:if test='${lotno == 1  }'>  <c:set var="value" value="${row.data_value1}" /> </c:if>   
              <c:if test='${lotno == 2  }'>  <c:set var="value" value="${row.data_value2}" /> </c:if>   
               <c:if test='${lotno == 3  }'>  <c:set var="value" value="${row.data_value3}" /> </c:if>   
                <c:if test='${lotno == 4  }'>  <c:set var="value" value="${row.data_value4}" /> </c:if>   
                 <c:if test='${lotno == 5  }'>  <c:set var="value" value="${row.data_value5}" /> </c:if>   
                  <c:if test='${lotno == 6  }'>  <c:set var="value" value="${row.data_value6}" /> </c:if>   
                   <c:if test='${lotno == 7  }'>  <c:set var="value" value="${row.data_value7}" /> </c:if>   
                    <c:if test='${lotno == 8  }'>  <c:set var="value" value="${row.data_value8}" /> </c:if>      
               
                <option value="${value}">${value}</option>     
                 
                </c:forEach>
                
                
                
                </select>
            </div>
            </c:if>   
            
                     <div >
                <label for="date">ประเภทของข้อมูลที่จะแสดงผล dataType:</label>
                <select name="date_type">
                	<option value="Result">Result</option>
                	<option value="Transaction">Transaction</option>
                </select>
            </div>
            
                      <div >
            
            </div>
            
            <div style="margin-right:10px;">
                <label for="date"><spring:message code="workflows.datestart"  /> [Transaction only]:</label>
                <input type="date" id="date" name="date_start" >
            </div>
         <div >
                <label for="date"><spring:message code="workflows.dateend"  /> [Transaction only]:</label>
                <input type="date" id="date" name="date_end" >
            </div>
        </div>
        <button type="submit" ><spring:message code="btn.search" /></button>
        		 <button onclick="window.print()" ><spring:message code="btn.Print"  /></button>  
             </form>
             </div >
            
        
        
       <div id="result" class="result">
       
       ${error }
       
       
  
   

        <c:if test='${date_type =="Result" }'>
         <h1>Result</h1>
                    <div style="display: flex; flex-wrap: wrap;">
                        <div style="flex: 1 1 100%;">
                  	 
                  <h3>ปัจจุบัน</h3>
					 <table class="transaction-table">
                           
					
                          
                                      <thead>
                                <tr>
                             
                                    <th><spring:message code="tables.department"  /></th>
                                    <th style=" text-align: left;width:150px;">${product}</th>
                                    <c:if test='${lot != null }'>  <th >${lot}</th></c:if>
                                       <c:if test='${unitlabel != null }'> <th >${unitlabel }</th></c:if>
                                        <th >รับเข้า</th>
                                          <th>จ่ายออก</th>
                                            <th >use</th>
                                           
                                                  <th>โอนถ่าย</th>
                                        
                                   <th >${balancelabel }</th>
                          
           					
                                </tr>
                                  </thead>
                                  
                                      <tbody>
                       
			                            <tr>
			                             <c:set var="balance_total" value="0" />             
			                <c:if test='${balancelabel != null }'> 
			                     <c:set var="balance_total" value="${row.field_label }" />  
                                        <c:if test="${balanceno == 1}">  <c:set var="balance_total" value="${maindata.data_value1}" />    </c:if>
                                        <c:if test="${balanceno == 2}"><c:set var="balance_total" value="${maindata.data_value2}" />  </c:if>
                                        <c:if test="${balanceno == 3}"><c:set var="balance_total" value="${maindata.data_value3}" /> </c:if>
                                        <c:if test="${balanceno == 4}"><c:set var="balance_total" value="${maindata.data_value4}" /> </c:if>
                                        <c:if test="${balanceno == 5}"><c:set var="balance_total" value="${maindata.data_value5}" /> </c:if>
                                        <c:if test="${balanceno == 6}"><c:set var="balance_total" value="${maindata.data_value6}" /> </c:if>
                                        <c:if test="${balanceno == 7}"><c:set var="balance_total" value="${maindata.data_value7}" /> </c:if>
                                        <c:if test="${balanceno == 8}"><c:set var="balance_total" value="${maindata.data_value8}" /> </c:if>
                                        <c:if test="${balanceno == 9}"><c:set var="balance_total" value="${maindata.data_value9}" /> </c:if>
                           </c:if> 
			                                         
			                                         
			                                         
			                                           <td>${upload_pns.upload_pn_department }</td>     
                                    <td style=" text-align: left;">
                                     <c:if test="${productno == 1}">${maindata.data_value1}</c:if>
                                        <c:if test="${productno == 2}">${maindata.data_value2} </c:if>
                                        <c:if test="${productno == 3}">${maindata.data_value3} </c:if>
                                        <c:if test="${productno == 4}">${maindata.data_value4} </c:if>
                                        <c:if test="${productno == 5}">${maindata.data_value5} </c:if>
                                        <c:if test="${productno == 6}">${maindata.data_value6} </c:if>
                                        <c:if test="${productno == 7}">${maindata.data_value7} </c:if>
                                        <c:if test="${productno == 8}">${maindata.data_value8} </c:if>
                                        <c:if test="${productno == 9}">${maindata.data_value9} </c:if>
                                        <c:if test="${productno == 10}">${maindata.data_value10}</c:if>
                                    </td>
			                                         
			                             <c:if test='${lot != null }'>                          
			                                    <td>
			                            <c:if test="${lotno == 1}">${maindata.data_value1} </c:if>
                                        <c:if test="${lotno == 2}">${maindata.data_value2} </c:if>
                                        <c:if test="${lotno == 3}">${maindata.data_value3} </c:if>
                                        <c:if test="${lotno == 4}">${maindata.data_value4} </c:if>
                                        <c:if test="${lotno == 5}">${maindata.data_value5} </c:if>
                                        <c:if test="${lotno == 6}">${maindata.data_value6} </c:if>
                                        <c:if test="${lotno == 7}">${maindata.data_value7} </c:if>
                                        <c:if test="${lotno == 8}">${maindata.data_value8} </c:if>
                                        <c:if test="${lotno == 9}">${maindata.data_value9} </c:if>
                                        <c:if test="${lotno == 10}">${maindata.data_value10}</c:if>
			                                    
			                                    </td>
			                                            <td>
                                   
                               <c:if test='${unitlabel != null }'>    
                                          <c:if test="${unitno == 1}"> ${maindata.data_value1}</c:if>
                                        <c:if test="${unitno == 2}"> ${maindata.data_value2}</c:if>
                                        <c:if test="${unitno == 3}"> ${maindata.data_value3} </c:if>
                                        <c:if test="${unitno == 4}"> ${maindata.data_value4} </c:if>
                                        <c:if test="${unitno == 5}"> ${maindata.data_value5} </c:if>
                                        <c:if test="${unitno == 6}"> ${maindata.data_value6} </c:if>
                                        <c:if test="${unitno == 7}"> ${maindata.data_value7} </c:if>
                                        <c:if test="${unitno == 8}"> ${maindata.data_value8} </c:if>
                                        <c:if test="${unitno == 9}"> ${maindata.data_value9} </c:if>
                                        <c:if test="${unitno == 10}"> ${maindata.data_value10}</c:if>
                                 </c:if>       
                                    
                                    </td>     
			                                    
			                                    
			                                    </c:if>
			                                        <td>${Maxbalanc.data_value1 }</td> 
			                                      <td> <c:if test='${Maxbalanc != null }'>${Maxbalanc.data_value1 - balance_total } </c:if></td> 
			                                        <td></td>  
			                                             <td></td>     
			                                               
			                                                       
			                                       <td>${balance_total} </td>
			                                    
			                               
			                   
                                    
                                 
                                     
		         </tr>
								         
								  <c:forEach items="${received }"   var="row" varStatus="count">  
							
							   <tr>
			                                      
			                                         
			                                           <td>${row.data_department }</td>     
                                    <td style=" text-align: left;">
                                     <c:if test="${productno == 1}">${maindata.data_value1}</c:if>
                                        <c:if test="${productno == 2}">${maindata.data_value2} </c:if>
                                        <c:if test="${productno == 3}">${maindata.data_value3} </c:if>
                                        <c:if test="${productno == 4}">${maindata.data_value4} </c:if>
                                        <c:if test="${productno == 5}">${maindata.data_value5} </c:if>
                                        <c:if test="${productno == 6}">${maindata.data_value6} </c:if>
                                        <c:if test="${productno == 7}">${maindata.data_value7} </c:if>
                                        <c:if test="${productno == 8}">${maindata.data_value8} </c:if>
                                        <c:if test="${productno == 9}">${maindata.data_value9} </c:if>
                                        <c:if test="${productno == 10}">${maindata.data_value10}</c:if>
                                    </td>
			                                         
			                             <c:if test='${lot != null }'>                          
			                                    <td>
			                            <c:if test="${lotno == 1}">${maindata.data_value1}     </c:if>
                                        <c:if test="${lotno == 2}">${maindata.data_value2} </c:if>
                                        <c:if test="${lotno == 3}">${maindata.data_value3} </c:if>
                                        <c:if test="${lotno == 4}">${maindata.data_value4} </c:if>
                                        <c:if test="${lotno == 5}">${maindata.data_value5} </c:if>
                                        <c:if test="${lotno == 6}">${maindata.data_value6} </c:if>
                                        <c:if test="${lotno == 7}">${maindata.data_value7} </c:if>
                                        <c:if test="${lotno == 8}">${maindata.data_value8} </c:if>
                                        <c:if test="${lotno == 9}">${maindata.data_value9} </c:if>
                                        <c:if test="${lotno == 10}">${maindata.data_value10}</c:if>
			                                    
			                                    </td>
			                                    </c:if>
			                            
			                                      <td>
                                   
                               <c:if test='${unitlabel != null }'>    
                                          <c:if test="${unitno == 1}"> ${maindata.data_value1}</c:if>
                                        <c:if test="${unitno == 2}"> ${maindata.data_value2}</c:if>
                                        <c:if test="${unitno == 3}"> ${maindata.data_value3} </c:if>
                                        <c:if test="${unitno == 4}"> ${maindata.data_value4} </c:if>
                                        <c:if test="${unitno == 5}"> ${maindata.data_value5} </c:if>
                                        <c:if test="${unitno == 6}"> ${maindata.data_value6} </c:if>
                                        <c:if test="${unitno == 7}"> ${maindata.data_value7} </c:if>
                                        <c:if test="${unitno == 8}"> ${maindata.data_value8} </c:if>
                                        <c:if test="${unitno == 9}"> ${maindata.data_value9} </c:if>
                                        <c:if test="${unitno == 10}"> ${maindata.data_value10}</c:if>
                                 </c:if>       
                                    
                                    </td>
			                            <td> <c:if test='${map == 1}'>  
                                       ${row.data_value1}   
                                        </c:if> </td>  
			                            <td></td>        
			                                         <td>
			                           <c:if test='${map == 1}'>  
                                       ${row.data_value2}   
                                        </c:if> 
			                                         </td>
   
			                             <td>    </td>                      
			                                       <td>
                                    	 <c:if test='${receive_no != null }'>  
                                    	       <c:if test='${map == 0}'>   
                                      ${row.data_value1}
                                        </c:if> 
                                        
                                        
                                       <c:if test='${map == 1}'>  
                                       ${row.data_value1 - row.data_value2}   
                                        </c:if> 
                                                   
                                       </c:if>     
                                    </td>
                                     
			                                    
			                                    
			                      
                                    
                                 
                                     
		         </tr>
							
                                 </c:forEach>
                                
                                
                               
								
							
						  
								
					
								
								
								
								</tbody>
								</table>
							
							
							
                        </div>
                     <div style="flex: 1 1 30%;">
					 <img src="https://www.nanasupplier.com/picture/product/400/207806.jpg" style="width:75%;display:none" >
					   </div>
                    </div>
					<br>
						<br>
				<br>

                	<br>
                	
                	</c:if>       
                	

                	    <c:if test='${date_type =="Transaction" }'>
                	    <h1><spring:message code="workflows.Transaction"  /></h1>
                	        <h3>การรับ-จ่าย  ( Date : ${date_start } - ${date_end } )</h3>
                        <table class="transaction-table">
                            <thead>
                                <tr>
                                    <th><spring:message code="workflows.date"  /></th>
                                      <th><spring:message code="workflows.issuer_receiver"  /></th>
                                    <th><spring:message code="tables.department"  /></th>
                       
                                    <th><spring:message code="workflows.incoming"  /></th>
                                    <th><spring:message code="workflows.outgoing"  /></th>
                                    <th><spring:message code="workflows.remaining"  /></th>
                                </tr>
                            </thead>
                            <tbody>
                    
                    <c:set var="total1" value="0" /> 
                    <c:set var="total2" value="0" /> 
                    <c:set var="total3" value="0" /> 
                    
                     <c:forEach items="${data }"   var="row" varStatus="count">   
                            <tr>
                                <td title="[${row.data_id }]">${row.data_dateadd }</td>
                                <td>${row.data_nameadd }</td>
           
                               <td>${row.data_department }</td>
 						
                                <td>
                                 <c:if test="${row.data_eformid == port1}"> <c:set var="plus" value="${plus_no1 }" />  </c:if>
                                  <c:if test="${row.data_eformid == port2}"> <c:set var="plus" value="${plus_no2 }" />  </c:if>
          
                                     <c:if test="${plus == 1}">${row.data_value1}    <c:set var="total1" value="${total1 + row.data_value1}" /> </c:if>
                                        <c:if test="${plus == 2}">${row.data_value2} <c:set var="total1" value="${total1 + row.data_value2}" /></c:if>
                                        <c:if test="${plus == 3}">${row.data_value3} <c:set var="total1" value="${total1 + row.data_value3}" /></c:if>
                                        <c:if test="${plus == 4}">${row.data_value4} <c:set var="total1" value="${total1 + row.data_value4}" /></c:if>
                                        <c:if test="${plus == 5}">${row.data_value5} <c:set var="total1" value="${total1 + row.data_value5}" /></c:if>
                                        <c:if test="${plus == 6}">${row.data_value6} <c:set var="total1" value="${total1 + row.data_value6}" /></c:if>
                                        <c:if test="${plus == 7}">${row.data_value7} <c:set var="total1" value="${total1 + row.data_value7}" /></c:if>
                                        <c:if test="${plus == 8}">${row.data_value8} <c:set var="total1" value="${total1 + row.data_value8}" /></c:if>
                                        <c:if test="${plus == 9}">${row.data_value9} <c:set var="total1" value="${total1 + row.data_value9}" /></c:if>
                                        <c:if test="${plus == 10}">${row.data_value10} <c:set var="total1" value="${total1 + row.data_value10}" /></c:if>
                                </td>
                                <td>
                                
                                    <c:if test="${row.data_eformid == port1}"> <c:set var="minus" value="${minus_no1 }" />  </c:if>
                                  <c:if test="${row.data_eformid == port2}"> <c:set var="minus" value="${minus_no2 }" />  </c:if>
                                         <c:if test="${minus == 1}">${row.data_value1} <c:set var="total2" value="${total2 + row.data_value1}" /></c:if>
                                        <c:if test="${minus == 2}">${row.data_value2} <c:set var="total2" value="${total2 + row.data_value2}" /></c:if>
                                        <c:if test="${minus == 3}">${row.data_value3} <c:set var="total2" value="${total2 + row.data_value3}" /></c:if>
                                        <c:if test="${minus == 4}">${row.data_value4} <c:set var="total2" value="${total2 + row.data_value4}" /></c:if>
                                        <c:if test="${minus == 5}">${row.data_value5} <c:set var="total2" value="${total2 + row.data_value5}" /></c:if>
                                        <c:if test="${minus == 6}">${row.data_value6} <c:set var="total2" value="${total2 + row.data_value6}" /></c:if>
                                        <c:if test="${minus == 7}">${row.data_value7} <c:set var="total2" value="${total2 + row.data_value7}" /></c:if>
                                        <c:if test="${minus == 8}">${row.data_value8} <c:set var="total2" value="${total2 + row.data_value8}" /></c:if>
                                        <c:if test="${minus == 9}">${row.data_value9} <c:set var="total2" value="${total2 + row.data_value9}" /></c:if>
                                        <c:if test="${minus == 10}">${row.data_value10} <c:set var="total2" value="${total2 + row.data_value10}" /></c:if>
                                
                                </td>
                                <td>
                                    <c:set var="Remaining_total" value="${total1 - total2 }" />
                             ${Remaining_total }
                                
                                </td>
                            </tr>
                      </c:forEach>
                        
                        
                         
                                <tr>
                                    <td colspan="2" style="text-align: right;"><strong><spring:message code="workflows.total"  />:</strong></td>
                                      <td></td>
                                
                                     <td>${total1 }</td>
                                    <td>${total2 }</td>
                                    <td>${Remaining_total }</td>
                                </tr>
                            </tbody>
                        </table>
                        
                        
                        
                        
                                <h3>การนำไปใช้  ( Date : ${date_start } - ${date_end } )</h3>
                      	 <table class="transaction-table">
                           
					
                          
                                      <thead>
                                <tr>
                               <th><spring:message code="workflows.date"  /></th>
                                    <th><spring:message code="tables.department"  /></th>
                                    <th style=" text-align: left;width:150px;">${product}</th>
                                    <c:if test='${lot != null }'>  <th >${lot}</th></c:if>
                                       <c:if test='${unitlabel != null }'> <th >${unitlabel }</th></c:if>
                                        <th >รับเข้า</th>
                                   
                                            <th >ใช้ไป</th>
                                           
                                                 
                                        
                                   <th >${balancelabel }</th>
                          
           					
                                </tr>
                                  </thead>
                                  
                                      <tbody>
                       
			                            	         
								  <c:forEach items="${received }"   var="row" varStatus="count">  
							
							   <tr>
			                                      
			                                            <td title="[${row.data_id }]">${row.data_due }</td>
			                                           <td>${row.data_department }</td>     
                                    <td style=" text-align: left;">
                                     <c:if test="${productno == 1}">${maindata.data_value1}</c:if>
                                        <c:if test="${productno == 2}">${maindata.data_value2} </c:if>
                                        <c:if test="${productno == 3}">${maindata.data_value3} </c:if>
                                        <c:if test="${productno == 4}">${maindata.data_value4} </c:if>
                                        <c:if test="${productno == 5}">${maindata.data_value5} </c:if>
                                        <c:if test="${productno == 6}">${maindata.data_value6} </c:if>
                                        <c:if test="${productno == 7}">${maindata.data_value7} </c:if>
                                        <c:if test="${productno == 8}">${maindata.data_value8} </c:if>
                                        <c:if test="${productno == 9}">${maindata.data_value9} </c:if>
                                        <c:if test="${productno == 10}">${maindata.data_value10}</c:if>
                                    </td>
			                                         
			                             <c:if test='${lot != null }'>                          
			                                    <td>
			                            <c:if test="${lotno == 1}">${maindata.data_value1}     </c:if>
                                        <c:if test="${lotno == 2}">${maindata.data_value2} </c:if>
                                        <c:if test="${lotno == 3}">${maindata.data_value3} </c:if>
                                        <c:if test="${lotno == 4}">${maindata.data_value4} </c:if>
                                        <c:if test="${lotno == 5}">${maindata.data_value5} </c:if>
                                        <c:if test="${lotno == 6}">${maindata.data_value6} </c:if>
                                        <c:if test="${lotno == 7}">${maindata.data_value7} </c:if>
                                        <c:if test="${lotno == 8}">${maindata.data_value8} </c:if>
                                        <c:if test="${lotno == 9}">${maindata.data_value9} </c:if>
                                        <c:if test="${lotno == 10}">${maindata.data_value10}</c:if>
			                                    
			                                    </td>
			                                    </c:if>
			                            
			                                      <td>
                                   
                               <c:if test='${unitlabel != null }'>    
                                          <c:if test="${unitno == 1}"> ${maindata.data_value1}</c:if>
                                        <c:if test="${unitno == 2}"> ${maindata.data_value2}</c:if>
                                        <c:if test="${unitno == 3}"> ${maindata.data_value3} </c:if>
                                        <c:if test="${unitno == 4}"> ${maindata.data_value4} </c:if>
                                        <c:if test="${unitno == 5}"> ${maindata.data_value5} </c:if>
                                        <c:if test="${unitno == 6}"> ${maindata.data_value6} </c:if>
                                        <c:if test="${unitno == 7}"> ${maindata.data_value7} </c:if>
                                        <c:if test="${unitno == 8}"> ${maindata.data_value8} </c:if>
                                        <c:if test="${unitno == 9}"> ${maindata.data_value9} </c:if>
                                        <c:if test="${unitno == 10}"> ${maindata.data_value10}</c:if>
                                 </c:if>       
                                    
                                    </td>
			                            <td> <c:if test='${map == 1}'>  
                                       ${row.data_value1}   
                                        </c:if> </td>  
			                         
			                                         <td>
			                           <c:if test='${map == 1}'>  
                                       ${row.data_value2}   
                                        </c:if> 
			                                         </td>
   
			                                               
			                                       <td>
                                    	 <c:if test='${receive_no != null }'>  
                                    	       <c:if test='${map == 0}'>   
                                      ${row.data_value1}
                                        </c:if> 
                                        
                                        
                                       <c:if test='${map == 1}'>  
                                       ${row.data_value1 - row.data_value2}   
                                        </c:if> 
                                                   
                                       </c:if>     
                                    </td>
                                     
			                                    
			                                    
			                      
                                    
                                 
                                     
		         </tr>
							
                                 </c:forEach>
                                
                                
                               
								
							
						  
								
					
								
								
								
								</tbody>
								</table>
							
						
                        
                        
                    
                   
                      	</c:if>     
                    </div>
        
       
    </div>
</body>
</html>