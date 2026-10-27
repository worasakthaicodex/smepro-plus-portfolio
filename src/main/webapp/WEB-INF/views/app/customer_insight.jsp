<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="chs-app-wrapper" id="chsCustomerInsightsApp">
    <style>
        /* CSS Reset & Scoping (คงสไตล์เดิมทั้งหมด) */
        .chs-app-wrapper {
            --sidebar-bg: #111827; --sidebar-active: #10b981; --bg-main: #f8fafc;
            --white: #ffffff; --text-dark: #0f172a; --text-base: #334155;
            --text-muted: #64748b; --border-color: #e2e8f0; --primary: #10b981;
            --primary-light: #dcfce7; --blue: #0ea5e9; --orange: #f59e0b;
            --ai-color: #8b5cf6; --ai-bg: #f5f3ff;
            font-family: 'Segoe UI', 'Inter', -apple-system, sans-serif;
            background-color: var(--bg-main); color: var(--text-base);
            display: flex; height: 90vh; min-height: 800px; overflow: hidden;
            box-shadow: 0 4px 20px rgba(0,0,0,0.05); font-size: 13px;
            line-height: 1.5; box-sizing: border-box;
        }
        .chs-app-wrapper * { box-sizing: border-box; margin: 0; padding: 0; }
        .chs-app-wrapper svg { fill: none; stroke: currentColor; stroke-width: 2; stroke-linecap: round; stroke-linejoin: round; }

        .chs-app-wrapper .main-container { flex: 1; display: flex; flex-direction: column; overflow: hidden; }
        .chs-app-wrapper .top-header { background-color: var(--white); height: 64px; min-height: 64px; display: flex; align-items: center; justify-content: space-between; padding: 0 24px; border-bottom: 1px solid var(--border-color); }
        .chs-app-wrapper .header-title { font-size: 18px; font-weight: 600; color: var(--text-dark); display: flex; align-items: center; gap: 8px;}
        .chs-app-wrapper .header-actions { display: flex; gap: 12px; align-items: center; }
        .chs-app-wrapper .btn-outline { background-color: var(--white); color: var(--text-dark); border: 1px solid var(--border-color); padding: 8px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: 500; display: flex; align-items: center; gap: 8px;}
        .chs-app-wrapper .btn-outline:hover { background-color: #f1f5f9; }

        .chs-app-wrapper .content-area { padding: 24px; overflow-y: auto; flex: 1; display: flex; flex-direction: column; gap: 24px; }
        .chs-app-wrapper .kpi-row { display: grid; grid-template-columns: repeat(4, 1fr); gap: 24px; }
        .chs-app-wrapper .kpi-card { background: var(--white); border: 1px solid var(--border-color); border-radius: 8px; padding: 20px; box-shadow: 0 1px 2px rgba(0,0,0,0.02); }
        .chs-app-wrapper .kpi-header { display: flex; justify-content: space-between; align-items: flex-start; margin-bottom: 8px; }
        .chs-app-wrapper .kpi-title { font-size: 12px; color: var(--text-muted); font-weight: 500; text-transform: uppercase; letter-spacing: 0.5px; }
        .chs-app-wrapper .kpi-icon { width: 32px; height: 32px; border-radius: 6px; display: flex; align-items: center; justify-content: center; }
        .chs-app-wrapper .kpi-icon svg { width: 16px; height: 16px; }
        .chs-app-wrapper .kpi-value { font-size: 24px; font-weight: 700; color: var(--text-dark); margin-bottom: 4px; }
        
        .chs-app-wrapper .bg-green { background: #dcfce7; color: #166534; }
        .chs-app-wrapper .bg-blue { background: #e0f2fe; color: #0369a1; }
        .chs-app-wrapper .bg-orange { background: #fef3c7; color: #b45309; }
        .chs-app-wrapper .bg-purple { background: var(--ai-bg); color: var(--ai-color); }

        .chs-app-wrapper .panel { background: var(--white); border: 1px solid var(--border-color); border-radius: 8px; padding: 24px; box-shadow: 0 1px 2px rgba(0,0,0,0.02); display: flex; flex-direction: column; }
        .chs-app-wrapper .panel-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px; }
        .chs-app-wrapper .panel-title { font-size: 16px; font-weight: 600; color: var(--text-dark); }

        .chs-app-wrapper .segment-list { display: flex; flex-direction: column; gap: 16px; }
        .chs-app-wrapper .segment-item { display: flex; align-items: center; gap: 16px; }
        .chs-app-wrapper .segment-label { width: 140px; font-size: 13px; font-weight: 500; color: var(--text-dark); display: flex; flex-direction: column;}
        .chs-app-wrapper .segment-label span { font-size: 11px; color: var(--text-muted); font-weight: normal;}
        .chs-app-wrapper .segment-track { flex: 1; height: 24px; background: #f1f5f9; border-radius: 4px; overflow: hidden; position: relative; }
        .chs-app-wrapper .segment-fill { height: 100%; display: flex; align-items: center; padding-left: 12px; color: var(--white); font-weight: 600; font-size: 11px; transition: width 0.5s ease; }
        .chs-app-wrapper .segment-value { width: 80px; text-align: right; font-weight: 600; color: var(--text-dark); font-size: 12px;}

        .chs-app-wrapper table { width: 100%; border-collapse: collapse; text-align: left; }
        .chs-app-wrapper th { padding: 12px 16px; font-weight: 500; color: var(--text-muted); font-size: 12px; border-bottom: 1px solid var(--border-color); }
        .chs-app-wrapper td { padding: 12px 16px; border-bottom: 1px solid #f1f5f9; color: var(--text-base); font-size: 13px; vertical-align: middle; }
        .chs-app-wrapper tr:hover td { background-color: #f8fafc; cursor: pointer; }
        
        .chs-app-wrapper .customer-info { display: flex; align-items: center; gap: 12px; }
        .chs-app-wrapper .avatar { width: 32px; height: 32px; border-radius: 50%; background: #e2e8f0; display: flex; align-items: center; justify-content: center; font-weight: 600; color: var(--text-muted); font-size: 12px; }
        .chs-app-wrapper .cust-name { font-weight: 600; color: var(--text-dark); }
        .chs-app-wrapper .cust-tier { font-size: 11px; color: var(--text-muted); }
        
        .chs-app-wrapper .ai-behavior-card { border: 1px solid #ddd6fe; border-radius: 8px; padding: 20px; background: linear-gradient(to bottom right, #ffffff, #f5f3ff); margin-bottom: 20px; }
        .chs-app-wrapper .ai-b-header { display: flex; align-items: center; gap: 8px; font-weight: 600; color: var(--ai-color); margin-bottom: 16px; font-size: 14px; }
        .chs-app-wrapper .ai-insight-text { font-size: 13px; color: var(--text-dark); line-height: 1.6; margin-bottom: 12px; }
        .chs-app-wrapper .ai-highlight { background: #ede9fe; color: var(--ai-color); font-weight: 600; padding: 2px 6px; border-radius: 4px; }

        .chs-app-wrapper .activity-list { display: flex; flex-direction: column; gap: 16px; }
        .chs-app-wrapper .activity-item { display: flex; gap: 12px; position: relative; }
        .chs-app-wrapper .activity-item::before { content: ''; position: absolute; left: 15px; top: 30px; bottom: -16px; width: 1px; background: var(--border-color); }
        .chs-app-wrapper .activity-item:last-child::before { display: none; }
        .chs-app-wrapper .activity-icon { width: 32px; height: 32px; border-radius: 50%; background: #f1f5f9; display: flex; align-items: center; justify-content: center; color: var(--text-muted); z-index: 1; flex-shrink: 0;}
        .chs-app-wrapper .activity-icon svg { width: 14px; height: 14px; }
        .chs-app-wrapper .activity-content { flex: 1; padding-top: 6px; }
        .chs-app-wrapper .activity-text { font-size: 13px; color: var(--text-dark); margin-bottom: 4px; }
        .chs-app-wrapper .activity-time { font-size: 11px; color: var(--text-muted); }

        /* Custom Styles สำหรับ ERP ลิงก์ย่อย */
        .chs-app-wrapper .link-pill { display: inline-block; padding: 2px 6px; background: #f1f5f9; border-radius: 4px; margin-right: 4px; margin-bottom: 4px; cursor: pointer; transition: 0.2s;}
        .chs-app-wrapper .link-pill:hover { background: var(--blue); color: white;}
        .chs-app-wrapper .link-pill.active { background: #e0f2fe; color: #0369a1; border: 1px solid #bae6fd;}
   
   
/* ใส่ในไฟล์ CSS ของคุณ */
.loading-overlay {
    /* หัวใจสำคัญ: ใช้ absolute เพื่อให้อยู่แค่ในกรอบแม่ */
    position: absolute; 
    top: 0; left: 0; right: 0; bottom: 0;
    width: 100%; height: 100%;
    background: rgba(255, 255, 255, 0.85);
    display: flex; flex-direction: column; justify-content: center; align-items: center;
    z-index: 1000; /* อยู่บนสุดของกล่องแม่ */
    backdrop-filter: blur(3px);
}

.loader-spinner {
    width: 40px; height: 40px;
    border: 4px solid #f3f3f3; border-top: 4px solid #3498db;
    border-radius: 50%; animation: spin-loader 1s linear infinite;
}

.loading-text {
    margin-top: 10px; font-size: 14px; color: #333; font-weight: bold;
}

@keyframes spin-loader {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
    </style>
<style>


/* doc_type badge */
.badge-fo { display:inline-block; padding:2px 9px; border-radius:99px; font-size:11px; font-weight:700; background:#dbeafe; color:#1d4ed8; }
.badge-qt { display:inline-block; padding:2px 9px; border-radius:99px; font-size:11px; font-weight:700; background:#f3e8ff; color:#7e22ce; }

/* closing_status badge */
.badge-win  { display:inline-block; padding:2px 9px; border-radius:99px; font-size:11px; font-weight:600; background:#dcfce7; color:#15803d; }
.badge-lose { display:inline-block; padding:2px 9px; border-radius:99px; font-size:11px; font-weight:600; background:#fee2e2; color:#b91c1c; }
.badge-none { display:inline-block; padding:2px 9px; border-radius:99px; font-size:11px; color:#94a3b8; }

/* aging badge */
.aging-badge { display:inline-block; padding:2px 9px; border-radius:99px; font-size:11px; font-weight:600; white-space:nowrap; }
.aging-3m    { background:#dbeafe; color:#1d4ed8; }
.aging-3_6m  { background:#dcfce7; color:#15803d; }
.aging-6_9m  { background:#fef9c3; color:#a16207; }
.aging-9_12m { background:#ffedd5; color:#c2410c; }
.aging-24m   { background:#fee2e2; color:#b91c1c; }

/* mini bar */
.fo-bar-wrap  { background:#e2e8f0; border-radius:99px; height:8px; min-width:80px; overflow:hidden; }
.fo-bar-fill  { height:100%; border-radius:99px; transition:width .4s ease; }

/* export btn */
.btn-csv {
    display:inline-flex; align-items:center; gap:4px;
    padding:4px 10px; border-radius:6px;
    border:1px solid var(--border, #cbd5e1);
    background:#fff; color:var(--text-main,#334155);
    font-size:12px; cursor:pointer; text-decoration:none;
    transition:background .15s;
}
.btn-csv:hover { background:#f1f5f9; }
</style>
    <main class="main-container" id="main-container-in">
        <header class="top-header">
            <div class="header-title">
                Customer Insights (ERP Analytics)
            </div>
            <div class="header-actions">
                <button class="btn-outline">
                    <svg viewBox="0 0 24 24" width="16" height="16"><circle cx="12" cy="12" r="3"></circle><path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path></svg>
                    Data Settings
                </button>
            </div>
        </header>

        <div class="content-area">
            
            <!-- KPI Row -->
            <div class="kpi-row">
                <!-- 1. เกรดลูกค้า -->
             <div class="kpi-card">
    <div class="kpi-header">
        <div class="kpi-title" title="ข้อมูลจาก Register Entry (ลูกค้า)">ลูกค้าตามเกรด</div>
        <div class="kpi-icon bg-blue">
            <svg viewBox="0 0 24 24"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
        </div>
    </div>
    <!-- แสดงยอดรวมลูกค้าทั้งหมด -->
    <div class="kpi-value">${totalCustomers}</div>
    <div style="font-size: 11px; color: var(--text-muted); margin-top: 6px;">
        <!-- กระจายยอดตามเกรด A, B, C, NEW, NONE -->
        <span class="link-pill" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/A','wpf-content-indexapp')">A: ${gradeA}</span> 
        <span class="link-pill" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/B','wpf-content-indexapp')">B: ${gradeB}</span> 
        <span class="link-pill" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/C','wpf-content-indexapp)">C: ${gradeC}</span>
        <br>
        <span class="link-pill active" style="margin-top:4px;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/NEW','wpf-content-indexapp')">NEW: ${gradeNew}</span> 
        <span class="link-pill" style="margin-top:4px;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/NONE','wpf-content-indexapp')">NONE: ${gradeNone}</span>
    </div>
</div>
                
			<div class="kpi-card">
    <div class="kpi-header">
        <div class="kpi-title" title="คำนวณจาก Register Grade History (เฉพาะ A, B, C)">การปรับเกรด (YTD)</div>
        <div class="kpi-icon bg-green">
            <svg viewBox="0 0 24 24"><polyline points="23 4 23 10 17 10"></polyline><polyline points="1 20 1 14 7 14"></polyline><path d="M3.51 9a9 9 0 0 1 14.85-3.36L23 10M1 14l4.64 4.36A9 9 0 0 0 20.49 15"></path></svg>
        </div>
    </div>
    <div class="kpi-value">
        <!-- ใส่ลิงก์สำหรับ อัปเกรด (GRADE_UP) -->
        <span class="link-pill" style="color:#10b981; cursor: pointer;" 
              onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/GRADE_UP', 'wpf-content-indexapp')">
              ↑ ${gradeUp}
        </span> 
        <span style="color:#e2e8f0; font-size:18px;">|</span> 
        <!-- ใส่ลิงก์สำหรับ ตกเกรด (GRADE_DOWN) -->
        <span class="link-pill" style="color:#f59e0b; cursor: pointer;" 
              onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/GRADE_DOWN', 'wpf-content-indexapp')">
              ↓ ${gradeDown}
        </span>
    </div>
    <div style="font-size: 11px; color: var(--text-muted); margin-top: 6px;">
        อัปเกรด ${gradeUp} ราย / ตกเกรด ${gradeDown} ราย
    </div>
</div>

							<!-- 3. ความถี่การติดต่อ (ลูกค้าทั้งหมด) -->
				<div class="kpi-card">
				    <div class="kpi-header">
				        <div class="kpi-title" title="อ้างอิงจาก Request Action (edata)">ความถี่การติดต่อ / Year</div>
				        <div class="kpi-icon bg-orange">
				            <svg viewBox="0 0 24 24"><path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path></svg>
				        </div>
				    </div>
				    <div class="kpi-value" style="font-size: 20px;">Avg: ${contactAvg} ครั้ง/ปี</div>
				    <div style="font-size: 11px; color: var(--text-muted); margin-top: 6px;">
				        <span class="link-pill" style="cursor: pointer;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/CONTACT_3', 'wpf-content-indexapp')">3+ ครั้ง: ${contact3}</span> 
				        <span class="link-pill" style="cursor: pointer;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/CONTACT_2', 'wpf-content-indexapp')">2 ครั้ง: ${contact2}</span>
				        <br>
				        <span class="link-pill" style="margin-top:4px; cursor: pointer;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/CONTACT_1', 'wpf-content-indexapp')">1 ครั้ง: ${contact1}</span> 
				        <span class="link-pill active" style="margin-top:4px; cursor: pointer;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/CONTACT_0', 'wpf-content-indexapp')">0 ครั้ง: ${contact0}</span>
				    </div>
				</div>
				
				<!-- 4. ความถี่การติดต่อ (ลูกค้าเก่า) -->
				<div class="kpi-card">
				    <div class="kpi-header">
				        <div class="kpi-title" title="อ้างอิงจาก Request Action (edata)">ความถี่การติดต่อ(ลูกค้าเก่า) / Year</div>
				        <div class="kpi-icon bg-orange">
				            <svg viewBox="0 0 24 24"><path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path></svg>
				        </div>
				    </div>
				    <div class="kpi-value" style="font-size: 20px;">Avg: ${oldContactAvg} ครั้ง/ปี</div>
				    <div style="font-size: 11px; color: var(--text-muted); margin-top: 6px;">
				        <span class="link-pill" style="cursor: pointer;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/OLD_CONTACT_3', 'wpf-content-indexapp')">3+ ครั้ง: ${oldContact3}</span> 
				        <span class="link-pill" style="cursor: pointer;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/OLD_CONTACT_2', 'wpf-content-indexapp')">2 ครั้ง: ${oldContact2}</span>
				        <br>
				        <span class="link-pill" style="margin-top:4px; cursor: pointer;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/OLD_CONTACT_1', 'wpf-content-indexapp')">1 ครั้ง: ${oldContact1}</span> 
				        <span class="link-pill active" style="margin-top:4px; cursor: pointer;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/OLD_CONTACT_0', 'wpf-content-indexapp')">0 ครั้ง: ${oldContact0}</span>
				    </div>
				</div>
            </div>

            <div class="dashboard-split">
                
                <div >
                    
         
     <div style="overflow:auto !important;max-height:500px" class="wpf-datagrid-container"> 
                        <table id="table-wrapper" class="table-wrapper">
         <thead>
            <tr>
                <th>กลุ่มลูกค้า</th>
                <th>ปี</th>
                <th>ประเภท (FO/QT)</th>
                <th>ปิดการขาย (QT)</th>
                <th>ระยะห่างจากการติดต่อล่าสุด</th>
                <th style="text-align:right;">จำนวน (ราย)</th>
                <th style="text-align:center;">Export</th>
            </tr>
        </thead>
        <tbody>

        <%-- หา max สำหรับ bar --%>
        <c:set var="maxCount" value="1" />
        <c:forEach var="row" items="${foQtSummaryList}">
            <c:if test="${row.total_customers > maxCount}">
                <c:set var="maxCount" value="${row.total_customers}" />
            </c:if>
        </c:forEach>

        <c:forEach var="row" items="${foQtSummaryList}">

            <%-- % bar --%>
            <c:set var="barPct" value="${(row.total_customers * 100) / maxCount}" />

            <%-- สี bar + aging badge ตาม bucket --%>
            <c:set var="barColor"   value="#ef4444" />
            <c:set var="badgeClass" value="aging-24m" />
            <c:choose>
                <c:when test="${row.aging_bucket == '<3M'}">
                    <c:set var="barColor" value="#3b82f6"/><c:set var="badgeClass" value="aging-3m"/>
                </c:when>
                <c:when test="${row.aging_bucket == '>3M'}">
                    <c:set var="barColor" value="#22c55e"/><c:set var="badgeClass" value="aging-3_6m"/>
                </c:when>
                <c:when test="${row.aging_bucket == '>6M'}">
                    <c:set var="barColor" value="#eab308"/><c:set var="badgeClass" value="aging-6_9m"/>
                </c:when>
                <c:when test="${row.aging_bucket == '>12M'}">
                    <c:set var="barColor" value="#f97316"/><c:set var="badgeClass" value="aging-9_12m"/>
                </c:when>
            </c:choose>

            <tr>
                <%-- กลุ่มลูกค้า --%>
                <td><strong>${row.register_groups}</strong></td>

                <%-- ปี --%>
                <td>${row.doc_year}</td>

                <%-- ประเภท FO/QT --%>
                <td>
                    <c:choose>
                        <c:when test="${row.doc_type == 'FO'}">
                            <span class="badge-fo">FO</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge-qt">QT</span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <%-- ปิดการขาย (เฉพาะ QT) --%>
                <td>
                    <c:choose>
                        <c:when test="${row.doc_type == 'FO'}">
                            <span class="badge-none">-</span>
                        </c:when>
                        <c:when test="${row.closing_status == 'ปิดได้'}">
                            <span class="badge-win">✅ ปิดได้</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge-lose">❌ ปิดไม่ได้</span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <%-- aging badge --%>
                <td><span class="aging-badge ${badgeClass}">${row.aging_bucket}</span></td>

                <%-- จำนวน + mini bar --%>
                <td style="text-align:right;">
                    <div style="display:flex; align-items:center; gap:8px; justify-content:flex-end;">
                        <div class="fo-bar-wrap" style="width:80px;">
                            <div class="fo-bar-fill" style="width:${barPct}%; background:${barColor};"></div>
                        </div>
                        <strong>${row.total_customers} ราย</strong>
                    </div>
                </td>

                <%-- Export CSV --%>
                <td style="text-align:center;">
                    <a class="btn-csv" href="#"
                       onclick="triggerFoQtSegment('${fn:escapeXml(row.register_groups)}','${row.doc_year}','${row.doc_type}','${fn:escapeXml(row.closing_status)}','${row.aging_bucket}'); return false;">
                        เลือก
                    </a>
                </td>
            </tr>

        </c:forEach>

        <c:if test="${empty foQtSummaryList}">
            <tr>
                <td colspan="7" style="text-align:center; color:var(--text-muted); padding:24px;">ไม่พบข้อมูล</td>
            </tr>
        </c:if>

        </tbody>
    </table>
</div>
         
         </div>
           <div class="panel" style="display:none">
    <div class="panel-header" style="margin-bottom: 20px;">
        <div class="panel-title">Customer Aging & Document Status</div>
        <span style="font-size: 12px; color: var(--text-muted);">ระยะเวลาติดต่อล่าสุดเทียบกับเอกสาร</span>
    </div>
    
    <div class="segment-list">
        <!-- ระยะ 3 เดือน -->
        <div class="segment-item">
            <a href="#" class="segment-label" style="text-decoration:none;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/AGING_3M', 'wpf-content-indexapp')">
                <strong>< 3 เดือน</strong> <span>คลิกดูรายชื่อ</span>
            </a>
            <div class="segment-track">
                <!-- 🌟 ใช้ตัวแปร ${p3M} แทน 65 -->
                <div class="segment-fill" style="width: ${p3M}%; background: var(--blue);">FO: ${f3M} | QT: ${q3M}</div>
            </div>
            <div class="segment-value">${c3M} ราย</div>
        </div>
        <!-- ระยะ 6 เดือน -->
        <div class="segment-item">
            <a href="#" class="segment-label" style="text-decoration:none;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/AGING_3_6M', 'wpf-content-indexapp')">
                <strong>3 - 6 เดือน</strong> <span>คลิกดูรายชื่อ</span>
            </a>
            <div class="segment-track">
                <!-- 🌟 ใช้ตัวแปร ${p3_6M} แทน 40 -->
                <div class="segment-fill" style="width: ${p3_6M}%; background: var(--primary);">FO: ${f3_6M} | QT: ${q3_6M}</div>
            </div>
            <div class="segment-value">${c3_6M} ราย</div>
        </div>
        <!-- ระยะ 9 เดือน -->
        <div class="segment-item">
            <a href="#" class="segment-label" style="text-decoration:none;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/AGING_6_9M', 'wpf-content-indexapp')">
                <strong>6 - 9 เดือน</strong> <span>คลิกดูรายชื่อ</span>
            </a>
            <div class="segment-track">
                <!-- 🌟 ใช้ตัวแปร ${p6_9M} แทน 25 -->
                <div class="segment-fill" style="width: ${p6_9M}%; background: var(--orange);">FO: ${f6_9M} | QT: ${q6_9M}</div>
            </div>
            <div class="segment-value">${c6_9M} ราย</div>
        </div>
        <!-- ระยะ 12 เดือน -->
        <div class="segment-item">
            <a href="#" class="segment-label" style="text-decoration:none;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/AGING_9_12M', 'wpf-content-indexapp')">
                <strong>9 - 12 เดือน</strong> <span>คลิกดูรายชื่อ</span>
            </a>
            <div class="segment-track">
                <!-- 🌟 ใช้ตัวแปร ${p9_12M} แทน 15 -->
                <div class="segment-fill" style="width: ${p9_12M}%; background: var(--text-muted);">FO: ${f9_12M} | QT: ${q9_12M}</div>
            </div>
            <div class="segment-value">${c9_12M} ราย</div>
        </div>
        <!-- ระยะ 24 เดือน -->
        <div class="segment-item">
            <a href="#" class="segment-label" style="text-decoration:none;" onclick="Loader2.show('main-container-in','กำลังโหลดข้อมูล process...');getData('/customer_insightRaw-filemanager/${appid}/AGING_24M', 'wpf-content-indexapp')">
                <strong>> 24 เดือน</strong> <span>คลิกดูรายชื่อ</span>
            </a>
            <div class="segment-track">
                <!-- 🌟 ใช้ตัวแปร ${p24M} แทน 5 -->
                <div class="segment-fill" style="width: ${p24M}%; background: #ef4444;">FO: ${f24M} | QT: ${q24M}</div>
            </div>
            <div class="segment-value">${c24M} ราย</div>
        </div>
    </div>
</div>

                    <!-- 5. โอกาสปิดการขาย & 7. ข้อมูลเชิงลึกจาก Entity -->
                    <div class="panel" style="padding: 0; flex: 1;display:none" >
                        <div class="panel-header" style="padding: 24px 24px 0 24px; margin-bottom: 16px;">
                            <div class="panel-title">Prospect Insights (From Entity)</div>
                            <button class="btn-outline" style="padding: 4px 8px; font-size: 11px;">View Full DB</button>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Customer ID</th>
                                    <th>Characteristics & Strategy</th>
                                    <th>Capital / Headcount</th>
                                    <th style="text-align: right;">Win Prob.</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- ดึงข้อมูลตามโครงสร้าง CustomerInsight Entity -->
                                <tr>
                                    <td>
                                        <div class="customer-info">
                                            <div class="avatar" style="background:#dcfce7; color:#166534;">A</div>
                                            <div>
                                                <div class="cust-name">CUST-00104</div>
                                                <div class="cust-tier">Horsepower: High</div>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <span style="display:block; font-weight:500;">กลุ่มกำลังซื้อสูง</span>
                                        <span class="cust-tier">Strategy: Value-based Selling</span>
                                    </td>
                                    <td>
                                        <span style="font-weight: 500;">High Capital</span><br>
                                        <span class="cust-tier">Headcount: 500+</span>
                                    </td>
                                    <td style="text-align: right; font-weight: 600; color: #10b981;">85%</td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="customer-info">
                                            <div class="avatar" style="background:#e0f2fe; color:#0369a1;">B</div>
                                            <div>
                                                <div class="cust-name">CUST-00215</div>
                                                <div class="cust-tier">Horsepower: Medium</div>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <span style="display:block; font-weight:500;">กลุ่มสนใจข้อมูล</span>
                                        <span class="cust-tier">Strategy: Consultative</span>
                                    </td>
                                    <td>
                                        <span style="font-weight: 500;">Medium Capital</span><br>
                                        <span class="cust-tier">Headcount: 50-100</span>
                                    </td>
                                    <td style="text-align: right; font-weight: 600; color: var(--blue);">60%</td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="customer-info">
                                            <div class="avatar" style="background:#fef3c7; color:#b45309;">C</div>
                                            <div>
                                                <div class="cust-name">CUST-00892</div>
                                                <div class="cust-tier">Horsepower: Low</div>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <span style="display:block; font-weight:500;">กลุ่มสนใจราคา</span>
                                        <span class="cust-tier">Strategy: Discount/Volume</span>
                                    </td>
                                    <td>
                                        <span style="font-weight: 500;">Low Capital</span><br>
                                        <span class="cust-tier">Headcount: < 20</span>
                                    </td>
                                    <td style="text-align: right; font-weight: 600; color: var(--orange);">35%</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div style="display: flex; flex-direction: column; gap: 24px;display:none">
                    
                    <!-- ข้อมูลเชิงลึกจาก AI (อ้างอิง Characteristic Entity) -->
                    <div class="panel" style="background: var(--white); padding: 0; border: none; box-shadow: none;">
                        <div class="ai-behavior-card">
                            <div class="ai-b-header">
                                <svg viewBox="0 0 24 24" width="18" height="18"><polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"></polygon></svg>
                                AI Insight & Strategy (Entity Analysis)
                            </div>
                            <div class="ai-insight-text">
                                ลูกค้าเกรด A ส่วนใหญ่จัดอยู่ในลักษณะ <span class="ai-highlight">กลุ่มสนใจข้อมูล</span> (Characteristics) และมี <span class="ai-highlight">Horsepower สูง</span> อัตราการเปิดเอกสาร QT เป็น FO อยู่ที่ 65% หากเซลส์ใช้ <span class="ai-highlight">Strategy ที่ 3</span>
                            </div>
                            <div class="ai-insight-text" style="color: var(--text-muted); font-size: 12px;">
                                💡 ข้อเสนอแนะ: ระบบตรวจพบลูกค้า 550 รายที่ "ติดต่อ 0 ครั้งในปีนี้" (NONE) แนะนำให้ดึงข้อมูลกลุ่มนี้มาทำ Email Marketing ส่ง Blueprint หรือ Catalog ใหม่
                            </div>
                        </div>
                    </div>

                    <div class="panel" style="flex: 1;" style="display:none">
                        <div class="panel-header" style="margin-bottom: 20px;">
                            <div class="panel-title" style="font-size: 14px;">Recent System Logs</div>
                        </div>
                        
                        <div class="activity-list">
                            <div class="activity-item">
                                <div class="activity-icon" style="background: #dcfce7; color: #166534;"><svg viewBox="0 0 24 24"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path></svg></div>
                                <div class="activity-content">
                                    <div class="activity-text"><span style="font-weight: 600;">Sale Team A</span> created <span style="font-weight: 600; color: var(--primary);">+3 FO Docs</span></div>
                                    <div class="activity-text" style="font-size: 12px;">Converted from QT (CUST-00104)</div>
                                    <div class="activity-time">Today, 10:45 AM</div>
                                </div>
                            </div>
                            
                            <div class="activity-item">
                                <div class="activity-icon"><svg viewBox="0 0 24 24"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg></div>
                                <div class="activity-content">
                                    <div class="activity-text"><span style="font-weight: 600;">System Auto-Update</span></div>
                                    <div class="activity-text" style="font-size: 12px;">12 Customers downgraded to <span style="color:var(--orange)">Grade C</span> (No contact > 9 mos)</div>
                                    <div class="activity-time">Yesterday, 23:59 PM</div>
                                </div>
                            </div>

                            <div class="activity-item">
                                <div class="activity-icon" style="background: #e0f2fe; color: #0369a1;"><svg viewBox="0 0 24 24"><polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg></div>
                                <div class="activity-content">
                                    <div class="activity-text"><span style="font-weight: 600;">Entity 'CustomerInsight' Updated</span></div>
                                    <div class="activity-text" style="font-size: 12px;">Strategy modified for 45 "High Capital" profiles.</div>
                                    <div class="activity-time">18 Apr 2026, 09:00 AM</div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        </div>
    </main>
</div>


       <script>
     
     
     /**
      * ระบบแสดงผลหน้าจอ Loading
      */
      var Loader2 = {
     		    // ใส่ชื่อ ID กล่องที่ต้องการให้มันไปโผล่
     		    show: function(containerId, message = "กำลังโหลด...") {
     		        const container = document.getElementById(containerId);
     		        if (!container) {
     		            console.warn("หาเป้าหมายไม่เจอ:", containerId);
     		            return;
     		        }

     		        // *** บังคับให้กล่องแม่เป็น relative ไม่งั้น overlay จะทะลุออกไปข้างนอก ***
     		        if (window.getComputedStyle(container).position === 'static') {
     		            container.style.position = 'relative';
     		        }

     		        // เช็คว่าในกล่องนี้มี overlay สร้างไว้หรือยัง
     		        let overlay = container.querySelector('.loading-overlay');
     		        
     		        if (!overlay) {
     		            // ถ้ายังไม่มี สร้างใหม่แล้วยัดลงไป
     		            overlay = document.createElement('div');
     		            overlay.className = 'loading-overlay';
     		            overlay.innerHTML = `
     		                <div class="loader-spinner"></div>
     		                <div class="loading-text">${message}</div>
     		            `;
     		            container.appendChild(overlay);
     		        } else {
     		            // ถ้ามีแล้ว แค่เปิดให้แสดงแล้วเปลี่ยนข้อความ
     		            overlay.querySelector('.loading-text').innerText = message;
     		            overlay.style.display = 'flex';
     		        }
     		    },

     		    // สั่งปิด โดยอ้างอิงจาก ID กล่องเดิม
     		    hide: function(containerId) {
     		        const container = document.getElementById(containerId);
     		        if (!container) return;

     		        const overlay = container.querySelector('.loading-overlay');
     		        if (overlay) {
     		            // ปิดการแสดงผล
     		            overlay.style.display = 'none'; 
     		            
     		            // หรือถ้าอยากลบทิ้งไปเลยเพื่อคืนหน่วยความจำ ให้ใช้:
     		            // overlay.remove();
     		        }
     		    }
     		};
         </script>
     
     
     
     
     
     <script>
     
     _csrf = "${_csrf.parameterName}=${_csrf.token}";
     var url = "${SegmentView.query_file}";
     var formattedURL = url.replace("Idoc:/", "/document-filemanager/");
     loadQueryFromURL(formattedURL,'${segmentation_id}');

     //✅ ตรวจก่อนว่ามีการกรอก Path ไว้ไหม
     if (!url || url.trim() === "" || url === "null" || url === "undefined") {
         document.getElementById("dynamicQuickEditContainer").innerHTML = `
             <div style="padding:16px; color:#a94442; background:#f2dede; border-radius:6px; border:1px solid #ebccd1;">
                 ⚠️ ยังไม่ได้กำหนด Query File<br>
                 <small style="color:#666;">กรุณาไปที่ตั้งค่ามุมมอง แล้วกรอก Path ของไฟล์ .cheese ให้ครบ</small>
             </div>`;
     } else {
         loadQueryFromURL(formattedURL, '${segmentation_id}');
     }
     
     
function exportFoCsv(group, year, aging, appid) {
	

    // encode param ป้องกันอักขระพิเศษ
    var params = new URLSearchParams({
        group:  group,
        year:   year,
        aging:  aging
    });
    // เรียก endpoint → ปรับ URL ให้ตรงกับระบบจริง
    var url = '/customer_insightRaw-filemanager/' + appid + '/FO_EXPORT?' + params.toString();

    // เปิด URL → browser จะ download CSV อัตโนมัติ (ถ้า controller ส่ง Content-Disposition)
  window.open(url, '_blank');   // เปิดแท็บใหม่
}


//ดึง csrf และ process_id จากหน้า
_csrf = "${_csrf.parameterName}=${_csrf.token}";
var _segmentFoQt   = "0";
var _processIdFoQt = "${appid}";




function triggerFoQtSegment(group, year, docType, closingStatus, aging) {
	
	openwindows('draggable-window3','Quick Filter','1200px','9000');
    openFoQtSegment(
        group,
        parseInt(year),
        docType,
        closingStatus,
        aging,
        _csrf,
        _segmentFoQt,
        _processIdFoQt
    );
}
</script>

<script>
 
 new SmartTable('#table-wrapper', {
	   //truncateClass: '.ant-table-cell',
	    sort: true,      // เปิด Sort
	    resize: true     // เปิด Resize
	});
 </script>