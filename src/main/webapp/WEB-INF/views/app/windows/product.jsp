<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="th">
<head>
<meta charset="UTF-8">
<title>Clark M. Admiral - Amicus Attorney</title>
<style>

        :root {
            --bg-color: #f0f0f0;
            --border-color: #d9d9d9;
            --input-border: #7a7a7a;
            --focus-border: #0078d7;
            --text-color: #222;
            --font-stack: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

 

        /* Window Container */
       .cl_window .window {
       font-family: 'Segoe UI', sans-serif;
    font-size: 14px;
            width: 750px;
            background-color: var(--bg-color);
            border: 1px solid #0078d7; /* Active window border color */
            box-shadow: 0 4px 10px rgba(0,0,0,0.2);
            display: flex;
            flex-direction: column;
        }

        /* Header */
       .cl_window .window-header {
            background-color: white;
            padding: 8px 10px;
            font-size: 14px;
            font-weight: 500;
            display: flex;
            justify-content: space-between;
            align-items: center;
            /* In Win10 style, header often blends, but adding separator for clarity */
            border-bottom: 1px solid #eee; 
        }

       .cl_window .close-btn {
            cursor: pointer;
            font-size: 16px;
            color: #666;
            line-height: 1;
        }

       .cl_window .window-body {
            padding: 15px;
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        /* Layout Grid */
       .cl_window .top-section {
            display: flex;
            gap: 20px;
        }

       .cl_window .main-form {
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 8px;
        }

       .cl_window .sidebars {
            width: 300px;
   
        }

        /* Form Elements */
       .cl_window .form-row {
            display: flex;
            align-items: center;
        }

       .cl_window label {
            width: 161px;
            font-family: 'Segoe UI', sans-serif;
    font-size: 13px;
            color: var(--text-color);
        }

      .cl_window  input[type="text"], 
       .cl_window input[type="date"], 
       .cl_window input[type="time"],
      .cl_window  select {
            padding: 3px 5px;
            
    border: 1px solid #bbb;
            font-family: var(--font-stack);
            font-size: 13px;
            height: 26px;
             width: 80%;
        }

      .cl_window  input[type="text"]:focus, 
      .cl_window  input[type="date"]:focus, 
      .cl_window  input[type="time"]:focus,
     .cl_window   select:focus {
            border-color: var(--focus-border);
            outline: none;
        }

       .cl_window .full-width {
            flex: 1;
        }

        /* Date Time Row Specifics */
      .cl_window  .datetime-group {
            display: flex;
            align-items: center;
            gap: 5px;
        }

     .cl_window   .datetime-group input[type="date"] { width: 110px; }
       .cl_window .datetime-group input[type="time"] { width: 100px; }

      .cl_window  .checkbox-label {
            margin-left: 10px;
            width: auto;
            display: flex;
            align-items: center;
            gap: 5px;
            cursor: pointer;
        }

        /* Horizontal Rule */
      .cl_window  .separator {
            border-bottom: 1px solid #ccc;
            margin: 5px 0;
        }

        /* Sidebar Specifics */
       .cl_window .sidebar-row {
            display: flex;
            align-items: center;
            margin-bottom: 5px;
            width:100%;
        }
       .cl_window .sidebar-row label { width: 140px; }
       .cl_window .sidebar-row select { flex: 1; }

       .cl_window .color-preview {
            display: inline-block;
            width: 12px;
            height: 12px;
            background-color: #5c8ae6; /* Example Blue */
            margin-right: 5px;
            vertical-align: middle;
        }

        /* Table Area (Replacing the large textarea) */
       .cl_window .table-container {
          
            border: 1px solid #adadad; /* Blue border like in the mockup for the textarea */
            background: white;
            height: 156px;
            overflow-y: auto;
        }

       .cl_window table {
            width: 100%;
            border-collapse: collapse;
        }

       .cl_window th {
            background-color: #f0f0f0;
            border-bottom: 1px solid #ccc;
            border-right: 1px solid #eee;
            text-align: left;
            padding: 5px;
            font-weight: 600;
            font-size: 12px;
            position: sticky;
            top: 0;
        }

      .cl_window  td {
            border-bottom: 1px solid #eee;
            border-right: 1px solid #eee;
            padding: 5px;
            vertical-align: top;
        }

      .cl_window  tr:hover {
            background-color: #e6f2fa;
        }

        /* Footer */
       .cl_window .window-footer {
            padding: 10px 15px;
            display: flex;
            justify-content: flex-end;
            gap: 10px;
            background-color: var(--bg-color);
        }

      .cl_window  button {
            min-width: 80px;
            padding: 4px 15px;
            font-family: var(--font-stack);
            font-size: 13px;
            border: 1px solid #adadad;
            background-color: #e1e1e1;
            cursor: pointer;
        }

      .cl_window  button:hover {
            background-color: #e5f1fb;
            border-color: #0078d7;
        }

        button.primary {
            border: 2px solid #0078d7; /* Emulate the focus look */
            border-radius: 0;
        }
        
       .cl_window button.sidebar-btn {
            width: 100%;
   
        }

        /* Utilities */
        .cl_window .disabled {
            background-color: #e6e6e6;
            color: #888;
        }
      </style>
</head>



     <div id="wpf-wrapper">
           <div class="wpf-menu-bar">
   
    <div class="wpf-menu-item">
        <span>Creation Efficiency</span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item">Clone</div>
        </div>
    </div>

    <div class="wpf-menu-item">
        <span>Discovery</span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item">Quick Find</div>
            </div>
    </div>
 <div class="wpf-menu-item">
        <span>Data Scoping</span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item">Field-Level Security</div>
            <div class="wpf-drop-item">Record-Level Security</div>
        </div>
    </div>
     <div class="wpf-menu-item">
        <span>Audit & Versioning</span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item">Change Log</div>
            <div class="wpf-drop-item">Revision History</div>
        </div>
    </div>
    </div>


    </div>

    <div class=" toolbar" style="border-top: 1px solid #808080;">


<c:set var="savediv" value="microsofts${process.id}" />
<c:if test="${shortprofile == 1 }">
	<c:set var="savediv" value="mainbody2" />
</c:if>


  <div class="btn-group" style="color:black">
  
  
  <c:if test="${shortprofile != 1 }">
  

  
<span class="btn-hover-effect" style="cursor: pointer;">
   <a title="กลับ"  onclick="getData('/indexapp-filemanager/${process.id}?from=${from}', '${savediv}');
   setTitleApp('app${process.id}','${process.name} [<spring:message code="btn.InProgress"  text="-​" />]');">
 <img src="/app/system/icon/Left-blue.ico">
    </a>
    </span>


     <c:if test="${from > 0 }">

    <span class="btn-hover-effect" style="cursor: pointer;">
   <a title="เชื่อมข้อมูล"  onclick="getData('/Syncdataindexapp-filemanager/${from}/${item.id }/${process.id}', 'Sync-${savediv}');">
 <img src="/app/system/icon/database-check.ico">
    </a>
    </span>
      <span class="btn-hover-effect" style="cursor: pointer;">
   <a title="ยกเลิกเชื่อมข้อมูล"  onclick="getData('/UnSyncdataindexapp-filemanager/${from}/${item.id }/${process.id}', 'Sync-${savediv}');">
 <img src="/app/system/icon/database delete.ico">
    </a>
    </span>
 <span id="Sync-${savediv}"></span>

       </c:if>
 
  
        </c:if>
    
  <c:if test="${edatas.data_id > 0 }">
    <button class="fa-button" onclick="getData('/editdocumentapp-filemanager/${edatas.data_id}/${process.id}', '${savediv}')"   style="color:black" data-original-title="List"> <i class="material-icons text-default">
      restart_alt
    </i></button>
 </c:if>

    

      

 <c:if test="${edatas.data_id > 0 }">
 
 

 
<button class="fa-button dark"  style="color:black" data-original-title="Img" title="ลบ"
       onclick="confirmAndDelete('/undocumentapp-filemanager?${_csrf.parameterName}=${_csrf.token}', 'myForms${process.id}', '${savediv}')"
       >
        <i class="material-icons text-danger">
   
          delete
        </i>
       </button>
       
        <button class="fa-button "  style="color:black" data-original-title="Img" title="ลบ"
       onclick="confirmAndDelete('/undocumentapp-filemanager?${_csrf.parameterName}=${_csrf.token}', 'myForms${process.id}', '${savediv}')"
       >
        <i class="material-icons text-default">
   
		add_photo_alternate
        </i>
       </button>
          <button class="fa-button "  style="color:black" data-original-title="Img" title="ลบ"
       onclick="confirmAndDelete('/undocumentapp-filemanager?${_csrf.parameterName}=${_csrf.token}', 'myForms${process.id}', '${savediv}')"
       >
        <i class="material-icons text-default">
   
	print
        </i>
       </button>
       
    
           <button class="fa-button "  style="color:black" data-original-title="Img" title="Flow"
           onclick="shows2('draggable-window2','Link'); getData('/flowdoc-filemanager/${edatas.data_id+0}/${process.id}', 'mainbody2');
           set_title_app('app_draggable-window2','การส่งเอกสาร')"
  
       >
        <i class="material-icons text-default">
   
	send
        </i>
       </button>
  </c:if>

  
   <div class="spatiu" id="barmain${item.id }" style="float:right;display:none">
    <div class="bars"></div>
    <div id="light"></div>
</div>
 

      </div>

  </div>
 
<body class="bodyprofile">




<div class="container containerprofile" >
    <div class="sidebarprofile">
        <ul class="sidebar-menu sidebar-menus">
            <li class="setting-tab active"  onclick="getData('/profileapp-filemanager/${item.id }/${process.id}', '${savediv}');switchtabsetting('0');" id="setting-0" >Contact</li>
   
           <c:forEach items="${useextension }" var="row" varStatus="count"> 
            <li class="setting-tab" id="setting-${count.count}" 
            onclick="getData('/extension-filemanager/${row.id}/${item.id }/${process.id }?from=${from+0 }', 'main${item.id }');
            switchtabsetting('${count.count +1}');" >${row.name }</li>
          </c:forEach>
 
        </ul>
    </div>
 
      

    <div class="main" id="main${item.id }" style="padding:10px">
        
 <div class="cl_window">

<div class="top-section">
                <div class="main-form">
                    <div class="form-row">
                        <label>ชื่อบริการ:</label>
                        <input type="text" name="name" class="full-width" value="${item.product_names }">
                    </div>
                    <div class="form-row">
                        <label>ประภท :</label>
                        <input type="text" class="full-width" value="${item.product_type }" >
                    </div>
 					<div class="form-row">
                        <label>หมวดหมู่:</label>
                        <input type="text" class="full-width" value="${item.product_category }" >
                    </div>
				<div class="form-row">
                        <label>Status:</label>
                                    <input type="text" class="full-width" value="${item.product_status }" >
                 
                    </div>
                
                    
                    <div class="separator"></div>
                    	<div class="form-row">
                        <label>อัพเดตครั้งที่:</label>
                        <input type="text" class="full-width" value="${item.updated_at }" >
                    </div>
				<div class="form-row">
                        <label>อัพเดตเมื่อ:</label>
                        <input type="text" class="full-width" value="${item.updated_at }" >
                    </div>
                    
                  



                    <div class="separator"></div>



                    
                    
                        
                </div>

                <div class="sidebars">
                
                  <div class="photo" style="float:left;margin-right:5px">
                    <img src="https://i.imgur.com/8z8Y5jF.jpg" alt="Clark M. Admiral">
                      
                </div>
                  <div class="photo" style="float:left">
                    <img src="https://i.imgur.com/8z8Y5jF.jpg" alt="Clark M. Admiral">
                      
                </div>
                
     

                    
                </div>
            
            </div>

 </div>



        <div class="content contentprofile" style="position: relative;   ">
            <div class="left-col">
                <h1></h1>
        
           
                          
                          
                           
                                     
                                    
            </div>

        </div>



  <div class="windows active"   style="background-color:#F3F4F6;">
   <div class="cl_window">
  
           <div id="tm-v2-wrapper">
<div class="tm-window">
            
   
             <div class="tm-tab-bar" style="width:100%"> 
    
 <div class="tm-tab  active" id="Tab-Tabs-Form-1" onclick="switchTab('Tabs-Form','3','1')">ข้อมูลทั่วไป</div>
<div class="tm-tab" id="Tab-Tabs-Form-2" onclick="switchTab('Tabs-Form','3','2')">ข้อมูลราคา</div>
  <div class="tm-tab" id="Tab-Tabs-Form-3" onclick="switchTab('Tabs-Form','3','3')">โดยผู้ใช้</div>  
            </div>

            <div class="tm-content">
                






                    

	<div id="Form-Tabs-Form-1">
   <div class="wpf-datagrid-container" style="padding:10px">
   
    <div class="sidebar-row">
                 <label style="width: 156px;">รายละเอียด:</label>
                         <textarea  >${item.product_details}</textarea>
                    </div>
                  <div class="sidebar-row">
                 <label style="width: 156px;">หน่วยนับ:</label>
                         <input type="text" value="${item.unit}" style="" >
                    </div>
 					
 			  <div class="sidebar-row">
     
   
   
                        <label style="width: 156px;">Dimensions:</label>
                         <input type="text" value="${item.product_dimensions}" style="" >
                    </div>
 				           <div class="sidebar-row">
                        <label style="width:  156px;">product_expiration_date:</label>
                         <input type="text" value="${item.product_expiration_date }"  style="">
                    </div>
 					
 					
</div>
	</div>
<div id="Form-Tabs-Form-2" style="display:none;">
<div class="wpf-datagrid-container" style="padding:10px">


               
                    <div class="sidebar-row">
                        <label style="width:  156px;">product_selling_price:</label>
                         <input type="text" value="${item.product_selling_price }"  style="">
                    </div>
                   <div class="sidebar-row">
                        <label style="width: 156px;">product_cost_price :</label>
                         <input type="text" value="${item.product_cost_price }" style="" >
                    </div>
                      <div class="sidebar-row">
                        <label style="width:  156px;">product_wholesale_price:</label>
                         <input type="text" value="${item.product_wholesale_price }"  style="">
                    </div>
                    
                   
   </div>
	</div>
	
	
	<div id="Form-Tabs-Form-3" style="display:none;">
<div class="wpf-datagrid-container">
   </div>
	</div>
            </div>

       </div>
  </div>
         
     </div>                 
    
                       
             
  
  
  
 
  </div>



     
               

   
        
         
        
        
        
         
    </div>
</div>



</body>
</html>