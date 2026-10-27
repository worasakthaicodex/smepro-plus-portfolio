<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="th">
<head>
<meta charset="UTF-8">
<title>ProFile</title>
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
.badgealert {
    display: inline-flex;
    justify-content: center;
    align-items: center;

    min-width: 18px;
    height: 18px;
    padding: 0 5px;

    background-color: #e53935;   /* แดงนุ่มลง */
    color: white;
    font-size: 11px;
    font-weight: 600;

    border-radius: 999px;

    margin-left: 6px;  /* ดันไปชิดขวาจากข้อความ */
}


/* ========================================================
   ADAPTIVE PROFILE LAYOUT (บน-ล่าง กลายร่างเป็น ซ้าย-ขวา)
   ======================================================== */
.profile-layout-wrapper {
    display: flex;
    flex-direction: column; /* ค่าเริ่มต้น: เรียงจากบนลงล่าง */
    gap: 15px;
}

/* 🟢 โหมด TRAY (ซ้าย-ขวา) 🟢 */
.profile-layout-wrapper.is-tray {
    flex-direction: row; /* เปลี่ยนเป็นเรียงซ้ายขวา */
    align-items: flex-start;
}

.profile-layout-wrapper.is-tray .layout-left {
    flex: 4; /* ฝั่งซ้ายกินพื้นที่ 40% */
    min-width: 300px;
}

.profile-layout-wrapper.is-tray .layout-right {
    flex: 6; /* ฝั่งขวา (Tabs) กินพื้นที่ 60% */
    min-width: 400px;
    background-color: #F3F4F6;
    border: 1px solid #ddd; /* เพิ่มกรอบให้ดูเป็นสัดส่วนในโหมดแคบ */
}

/* ทำให้เนื้อหาใน Tabs กะทัดรัดขึ้นเมื่ออยู่ใน Tray */
.profile-layout-wrapper.is-tray .sidebar-row {
    margin-bottom: 5px; 
}
.profile-layout-wrapper.is-tray .tm-tab {
    padding: 8px 10px; /* ย่อขนาด Tab ลงนิดนึง */
    font-size: 11px;
}
      </style>
</head>



     <div id="wpf-wrapper">
     
      
           <div class="wpf-menu-bar" style="display:<c:if test ="${Tray == 1 }">none</c:if>">
   
    <div class="wpf-menu-item">
        <span><spring:message code="profile.CreationEfficiency" text="-​" /></span>
        <div class="wpf-dropdown">
        
        
        <c:if test ="${ process.direct_data_edit_mode == 1 }">
            <div class="wpf-drop-item" onclick="openwindow('draggable-window3','Edit Data','9000');
             getData('/editprofile-filemanager/${item.id }/${process.id}', 'mainbody3');"><spring:message code="btn.edit" text="-​" /></div>
          
          
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd" />

<c:if test="${item.created_at != null && currentDate eq fn:substring(item.created_at, 0, 10)}">
            <div class="wpf-drop-item" onclick="getData('/deleteprofile-filemanager/${item.id }/${process.id}', 
            'microsofts${process.id}');"><spring:message code="btn.delete" text="-​" /></div>
  </c:if>          
            
            
            <div class="wpf-drop-item" style="display:none" onclick="getData('/Cloneprofile-filemanager/${item.id }/${process.id}', 
            'microsofts${process.id}');"><spring:message code="btn.clone" text="-​" /></div>
        </c:if>
        
        </div>
    </div>
    <div class="wpf-menu-item" style="display:none">
        <span>Watchlist</span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item">Monitor</div>
            <div class="wpf-drop-item">Threshold</div>
        </div>
    </div>
    <div class="wpf-menu-item" style="display:none">
        <span>Discovery</span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item">Quick Find</div>
            </div>
    </div>
     <div class="wpf-menu-item" style="display:none">
        <span>Dependency</span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item">Where Used Check</div>
            <div class="wpf-drop-item">Replacement Strategy</div>
        </div>
    </div>
 <div class="wpf-menu-item" style="display:none">
        <span>Data Scoping</span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item">Field-Level Security</div>
            <div class="wpf-drop-item">Record-Level Security</div>
        </div>
    </div>
    
     <div class="wpf-menu-item">
        <span><spring:message code="profile.Audit&Versioning" text="-" /></span>
        <div class="wpf-dropdown">
            <div class="wpf-drop-item" onclick="openwindow('draggable-window3','<spring:message code="profile.AuditLog" text="-" />','5000'); getData('/auditLog-filemanager/${item.register_code }${item.register_daterun }-${item.register_id }', 'mainbody3')"><spring:message code="profile.AuditLog" text="-" /></div>
            <div class="wpf-drop-item" onclick="openwindow('draggable-window3','<spring:message code="profile.Versioning" text="-" />','5000'); getData('/versioning-filemanager/${item.id }/${process.id}', 'mainbody3')"><spring:message code="profile.Versioning" text="-" /></div>
        </div>
    </div>
    </div>


    <div class=" toolbar" style="border-top: 1px solid #808080;display:<c:if test ="${Tray == 1 }">none</c:if>">


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
    <div class="sidebarprofile" style="display:<c:if test ="${Tray == 1 }">none</c:if>">
        <ul class="sidebar-menu sidebar-menus">
            <li class="setting-tab active"  onclick="getData('/profileapp-filemanager/${item.id}/${process.id}?from=${from}','${savediv}');switchtabsetting('0');" id="setting-0" >
            ข้อมูลหลัก</li>
			<li class="setting-tab"  onclick="getcalendarData('/reportjsonapp-filemanager/${item.id}?from=${from}','main${item.id }');switchtabsetting('1');loadcalendar();" id="setting-1" >
			ปฏิทิน</li>
			           
			<li class="setting-tab"  onclick="getData('/fileapp-filemanager/${item.id}/${process.id}?from=${from}','main${item.id}');switchtabsetting('2');" id="setting-2" >
			ไฟล์เเนบ</li>
           
           <li class="setting-tab"  onclick="getData('/listHelpDesk-filemanager/Profile/${item.id}/${process.id}?from=${from}','main${item.id}');switchtabsetting('3');" id="setting-3" >
			<spring:message code="profile.HelpDesk" text="-" /><c:if test="${countOpen > 0 }"><span class="badgealert">${countOpen }</span></c:if></li>

           
           <c:forEach items="${useextension }" var="row" varStatus="count"> 
            <li class="setting-tab" id="setting-${count.count +3}" 
            onclick="getData('/extension-filemanager/${row.id}/${item.id }/${process.id }?from=${from+0 }', 'main${item.id }');
            switchtabsetting('${count.count +3}');" >${row.name }</li>
          </c:forEach>
 
        </ul>
    </div>
 
      
   
<div class="main" id="main${item.id }" style="padding:10px">

<div class="profile-layout-wrapper <c:if test='${Tray == 1}'>is-tray</c:if>">
        
        <div class="layout-left">
    <div class="cl_window">

        <div>
            <div class="main-form">
                <div class="form-row">
                    <label>รหัส:</label>
                    <input type="text" name="name" class="full-width" value="${item.register_code }${item.register_daterun }-${item.register_id }">
                </div>
                <div class="form-row">
                    <label>คำนำหน้า/รูปแบบ:</label>
                    <input type="text" class="full-width" value="${item.register_title }" placeholder="เช่น บจก., วิสาหกิจชุมชน">
                </div>
                <div class="form-row">
                    <label>ชื่อ:</label>
                    <input type="text" name="name" class="full-width" value="${item.register_name }">
                </div>
                
                <div class="form-row">
                    <label>Status:</label>
                    <input type="text" class="full-width" value="${item.register_status}">
                </div>

                <div class="separator"></div>

                <div class="form-row">
                    <label>สร้างเมื่อ:</label>
                    <input type="text" class="full-width" value="${item.created_at }">
                </div>
                <div class="form-row">
                    <label>อัพเดตเมื่อ:</label>
                    <input type="text" class="full-width" value="${item.updated_at }">
                </div>
                <div class="form-row">
                    <label>อัพเดตครั้งที่:</label>
                    <input type="text" class="full-width" value="${item.revision }">
                </div>
            </div>

            <div class="sidebars" style="">
                <div class="photo" style="float:left;margin-right:5px;display:none">
                    <img src="https://i.imgur.com/8z8Y5jF.jpg" alt="Photo 1">
                </div>
                <div class="photo" style="float:left;display:none">
                    <img src="https://i.imgur.com/8z8Y5jF.jpg" alt="Photo 2">
                </div>
            </div>
        </div>
    </div>
 </div>
 
 <div class="layout-right windows active" style="background-color:#F3F4F6;">
    <div class="windows active" style="background-color:#F3F4F6;">
        <div class="cl_window">
            <div id="tm-v2-wrapper">
                <div class="tm-window">

                    <div class="tm-tab-bar" style="width:100%">
                        <div class="tm-tab active" id="Tab-Tabs-Form-1" onclick="switchTab('Tabs-Form','4','1')">
                            ข้อมูลทั่วไป
                        </div>
                        <div class="tm-tab" id="Tab-Tabs-Form-2" onclick="switchTab('Tabs-Form','4','2')">
                            <spring:message code="profile.Communication&Address" text="ที่อยู่และการติดต่อ" />
                        </div>
                        <div class="tm-tab" id="Tab-Tabs-Form-3" onclick="switchTab('Tabs-Form','4','3')">
                            <spring:message code="profile.finance" text="ข้อมูลการเงิน" />
                        </div>
                        <div class="tm-tab" id="Tab-Tabs-Form-4" onclick="switchTab('Tabs-Form','4','4')">
                           ผู้ติดต่อ & อื่นๆ
                        </div>
                    </div>

                    <div class="tm-content">
                        <div id="Form-Tabs-Form-1">
                            <div class="wpf-datagrid-container" style="padding:10px">
                                <div class="sidebar-row">
                                    <label style="width: 156px;">ประเภท (Party Type):</label>
                                    <input type="text" value="${item.party_type }" placeholder="บุคคลธรรมดา / นิติบุคคล">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">กลุ่ม (Type):</label>
                                    <input type="text" value="${item.register_type }">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">หมวดหมู่ (Groups):</label>
                                    <input type="text" value="${item.register_groups }">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">หมวดหมู่สินค้า:</label>
                                    <input type="text" value="${item.product_category }">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">รายละเอียด (Note):</label>
                                    <textarea style="width: 60%;">${item.register_note }</textarea>
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">Categorization:</label>
                                    <input type="text" value="${item.categorization}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">Behavior:</label>
                                    <input type="text" value="${item.behavior}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">เกรด / คะแนน:</label>
                                    <div style="display:inline-flex; gap:10px; width: 60%;">
                                        <input type="text" style="width:48%" value="${item.register_grade}" placeholder="เกรด">
                                        <input type="text" style="width:48%" value="${item.register_poin}" placeholder="คะแนน">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="Form-Tabs-Form-2" style="display:none;">
                            <div class="wpf-datagrid-container" style="padding:10px">
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.address" text="ที่อยู่" />:</label>
                                    <textarea style="width: 60%;">${item.register_address}</textarea>
                                </div>
                                 <div class="sidebar-row">
                                    <label style="width: 156px;">ที่อยู่เเบบเต็ม:</label>
                                    <textarea style="width: 60%;">${item.register_fulladdress}</textarea>
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">ภูมิภาค (Region):</label>
                                    <input type="text" value="${item.region}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">พิกัด (Lat, Lon):</label>
                                    <div style="display:inline-flex; gap:10px; width: 60%;">
                                        <input type="text" value="${item.lat}" placeholder="Latitude" style="width: 48%;">
                                        <input type="text" value="${item.lon}" placeholder="Longitude" style="width: 48%;">
                                    </div>
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.tax_id" text="เลขประจำตัวผู้เสียภาษี" />:</label>
                                    <input type="text" value="${item.tax_id }">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.business_type" text="ประเภทธุรกิจ" />:</label>
                                    <input type="text" value="${item.business_type }">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.branch_code" text="รหัสสาขา" />:</label>
                                    <input type="text" value="${item.branch_code }">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.phone_number" text="เบอร์โทรศัพท์" />:</label>
                                    <input type="text" value="${item.phone_number}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">แฟกซ์ (Fax):</label>
                                    <input type="text" value="${item.fax}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.mobile_number" text="มือถือ" />:</label>
                                    <input type="text" value="${item.mobile_number}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.register_email" text="อีเมลบริษัท" />:</label>
                                    <input type="text" value="${item.register_email}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.website" text="เว็บไซต์" />:</label>
                                    <input type="text" value="${item.website }">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.social" text="Social" />:</label>
                                    <input type="text" value="${item.social }">
                                </div>
                            </div>
                        </div>

                        <div id="Form-Tabs-Form-3" style="display:none;">
                            <div class="wpf-datagrid-container" style="padding:10px">
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.payment_terms" text="เงื่อนไขการชำระเงิน" />:</label>
                                    <input type="text" value="${item.payment_terms }">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.credit_limit" text="วงเงินเครดิต" />:</label>
                                    <input type="text" value="${item.credit_limit }">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.bank_account" text="บัญชีธนาคาร" />:</label>
                                    <input type="text" value="${item.bank_account }">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.preferred_currency" text="สกุลเงินที่ต้องการ" />:</label>
                                    <input type="text" value="${item.preferred_currency }">
                                </div>
                            </div>
                        </div>

                        <div id="Form-Tabs-Form-4" style="display:none;">
                            <div class="wpf-datagrid-container" style="padding:10px">
                                <h4 style="margin-top:0; border-bottom: 1px solid #ccc; padding-bottom: 5px;">ข้อมูลผู้ติดต่อหลัก</h4>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.contact_person" text="ชื่อผู้ติดต่อ" />:</label>
                                    <input type="text" value="${item.contact_person}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">ตำแหน่งผู้ติดต่อ:</label>
                                    <input type="text" value="${item.person_position}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">เบอร์โทรผู้ติดต่อ:</label>
                                    <input type="text" value="${item.person_number}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;"><spring:message code="profile.person_email" text="อีเมลผู้ติดต่อ" />:</label>
                                    <input type="text" value="${item.person_email}">
                                </div>

                                <h4 style="margin-top:15px; border-bottom: 1px solid #ccc; padding-bottom: 5px;">ข้อมูลผู้อนุมัติ (Approver)</h4>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">ชื่อผู้อนุมัติ:</label>
                                    <input type="text" value="${item.contact_approvers}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">ตำแหน่งผู้อนุมัติ:</label>
                                    <input type="text" value="${item.approvers_position}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">เบอร์โทรผู้อนุมัติ:</label>
                                    <input type="text" value="${item.approvers_number}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">อีเมลผู้อนุมัติ:</label>
                                    <input type="text" value="${item.approvers_email}">
                                </div>

                                <h4 style="margin-top:15px; border-bottom: 1px solid #ccc; padding-bottom: 5px;">ข้อมูลอื่นๆ</h4>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">ข้อมูล DBD:</label>
                                    <input type="text" value="${item.dbd}">
                                </div>
                                <div class="sidebar-row">
                                    <label style="width: 156px;">เงื่อนไขอื่นๆ:</label>
                                    <textarea style="width: 60%;">${item.register_other_conditions}</textarea>
                                </div>
                            </div>
                        </div>

                    </div> </div>
            </div>
        </div>
    </div>
    </div>

</div>



</div>

    </div>

<script>
maxcleanData = `
ชื่อ: ${item.register_name }
ประเภท: ${item.register_groups } (${item.product_category })
ภูมิภาค : ${item.region}
รายละเอียด: ${item.register_note }
`;
</script>

</body>
</html>