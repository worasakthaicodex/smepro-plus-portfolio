<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


 <head>
     <script src="<c:url value="/app/js/report_board_foruse.js" />"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<style>

    /* 1. Scoped CSS Variables */
    #erp-fluent-report-ui {
        --sys-bg: #F3F3F3;
        --sys-content-bg: #FFFFFF;
        --sys-border-light: #E5E5E5;
        --sys-border-card: #E0E0E0;
        --sys-accent: #005FB8;
        --sys-text-primary: #1A1A1A;
        --sys-text-secondary: #5D5D5D;
        --sys-text-tertiary: #8B8B8B;
        --sys-hover: rgba(0, 0, 0, 0.035);
        --sys-active: rgba(0, 0, 0, 0.06);
        --font-family: 'Segoe UI Variable Text', 'Segoe UI', -apple-system, BlinkMacSystemFont, sans-serif;

        /* ล็อคขนาดหน้าจอเริ่มต้นที่ 1200px ป้องกัน UI พังเมื่อย่อจอ */
        min-width: 1200px;
        
        font-family: var(--font-family);
        background-color: var(--sys-bg);
        color: var(--sys-text-primary);
        height: 100vh;
        width: 100%;
        display: flex;
        flex-direction: column;
        overflow-x: auto;
        overflow-y: hidden;
        -webkit-font-smoothing: antialiased;
        text-align: left;
    }

    /* 2. Scoped CSS Reset */
    #erp-fluent-report-ui, 
    #erp-fluent-report-ui * {
        box-sizing: border-box; margin: 0; padding: 0; user-select: none; cursor: default;
    }

    /* 3. Title Bar & Controls */
    #erp-fluent-report-ui .title-bar {
        height: 40px; display: flex; justify-content: space-between; align-items: center; padding-left: 16px;
    }
    #erp-fluent-report-ui .app-title {
        display: flex; align-items: center; gap: 12px; font-size: 12px; font-family: 'Segoe UI Variable Display', sans-serif;
    }
    #erp-fluent-report-ui .app-icon {
        width: 16px; height: 16px; background: linear-gradient(135deg, #005FB8, #5A1CCB); border-radius: 4px; display: flex; justify-content: center; align-items: center;
    }
    #erp-fluent-report-ui .app-icon svg { width: 10px; height: 10px; fill: white; }
    #erp-fluent-report-ui .window-controls { display: flex; height: 100%; }
    #erp-fluent-report-ui .win-btn {
        width: 46px; height: 100%; display: flex; justify-content: center; align-items: center; transition: background 0.1s;
    }
    #erp-fluent-report-ui .win-btn svg { width: 10px; height: 10px; stroke: #1A1A1A; stroke-width: 1px; }
    #erp-fluent-report-ui .win-btn:hover { background-color: var(--sys-hover); }
    #erp-fluent-report-ui .win-btn.close:hover { background-color: #E81123; }
    #erp-fluent-report-ui .win-btn.close:hover svg { stroke: white; }

    /* 4. Layout Structure */
    #erp-fluent-report-ui .app-body { display: flex; flex: 1; overflow: hidden; }

    /* 5. Sidebar */
    #erp-fluent-report-ui .sidebar {
        width: 280px; display: flex; flex-direction: column; padding: 0 12px 12px 12px; background-color: var(--sys-bg);
    }
    #erp-fluent-report-ui .nav-item {
        display: flex; align-items: center; height: 36px; padding: 0 12px; border-radius: 4px; margin-bottom: 2px; font-size: 14px; color: var(--sys-text-primary); position: relative; gap: 16px; transition: background 0.1s;
    }
    #erp-fluent-report-ui .nav-item:hover { background-color: var(--sys-hover); }
    #erp-fluent-report-ui .nav-item:active { background-color: var(--sys-active); color: var(--sys-text-secondary); }
    #erp-fluent-report-ui .nav-item.selected { background-color: var(--sys-hover); }
    #erp-fluent-report-ui .nav-item.selected::before {
        content: ''; position: absolute; left: 0px; top: 50%; transform: translateY(-50%); height: 16px; width: 3px; background-color: var(--sys-accent); border-radius: 4px;
    }
    #erp-fluent-report-ui .nav-icon { display: flex; justify-content: center; align-items: center; width: 16px; height: 16px; }
    #erp-fluent-report-ui .nav-icon svg { width: 16px; height: 16px; fill: none; stroke: var(--sys-text-primary); stroke-width: 1.5; }
    #erp-fluent-report-ui .nav-chevron { margin-right: -4px; width: 12px; height: 12px; opacity: 0.6; }
    #erp-fluent-report-ui .nav-group-header {
        display: flex; align-items: center; gap: 8px; height: 36px; padding: 0 12px; font-size: 14px; margin-top: 4px;
    }
    #erp-fluent-report-ui .nav-sub-items { padding-left: 28px; }
    #erp-fluent-report-ui .spacer { flex: 1; }

    /* 6. Main Content Area */
    #erp-fluent-report-ui .content-surface {
        flex: 1; background-color: var(--sys-content-bg); 
       border: 1px solid var(--sys-border-light);
         border-bottom: none; border-right: none; overflow-y: auto; display: flex; flex-direction: column; position: relative;
    }

    #erp-fluent-report-ui .hero-banner {
        position: relative; min-height: 320px; padding: 48px 48px;
        background: radial-gradient(circle at 80% 0%, rgba(180, 110, 255, 0.15) 0%, transparent 40%), radial-gradient(circle at 100% 100%, rgba(110, 50, 200, 0.1) 0%, transparent 50%), linear-gradient(135deg, #f8f6ff 0%, #eef0ff 100%);
        border-bottom: 1px solid var(--sys-border-light);
    }
    #erp-fluent-report-ui .hero-wave {
        position: absolute; top: 0; right: 0; bottom: 0; width: 60%;
        background-image: url("data:image/svg+xml,%3Csvg viewBox='0 0 800 400' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M800,0 C600,100 500,300 300,400 L800,400 Z' fill='url(%23grad)' opacity='0.7'/%3E%3Cpath d='M800,0 C650,50 400,200 200,400 L800,400 Z' fill='url(%23grad2)' opacity='0.5'/%3E%3Cdefs%3E%3ClinearGradient id='grad' x1='0%25' y1='0%25' x2='100%25' y2='100%25'%3E%3Cstop offset='0%25' stop-color='%23A162F7'/%3E%3Cstop offset='100%25' stop-color='%235D15C9'/%3E%3C/linearGradient%3E%3ClinearGradient id='grad2' x1='100%25' y1='0%25' x2='0%25' y2='100%25'%3E%3Cstop offset='0%25' stop-color='%23C084FC'/%3E%3Cstop offset='100%25' stop-color='%236B21A8'/%3E%3C/linearGradient%3E%3C/defs%3E%3C/svg%3E");
        background-size: cover; background-position: right center; pointer-events: none;
    }
    #erp-fluent-report-ui .hero-content { position: relative; z-index: 2; max-width: 500px; }
    #erp-fluent-report-ui .hero-subtitle { font-size: 14px; font-weight: 600; color: var(--sys-text-primary); margin-bottom: 8px; }
    #erp-fluent-report-ui .hero-title { font-size: 36px; font-weight: 600; color: var(--sys-text-primary); margin-bottom: 12px; letter-spacing: -0.5px; font-family: 'Segoe UI Variable Display', sans-serif;}
    #erp-fluent-report-ui .hero-desc { font-size: 14px; font-weight: 500; color: var(--sys-text-secondary); line-height: 1.5; }

    /* 7. Inner Layout Container (Holds Cards + Directory) */
    #erp-fluent-report-ui .main-content-inner {
        padding: 0 48px 48px 48px; margin-top: -64px; position: relative; z-index: 10;
        display: flex; flex-direction: column; gap: 24px;
    }

    /* 8. Vibrant Top Cards (4 ประเภทที่ขอมา) */
    #erp-fluent-report-ui .hero-cards-grid {
        display: grid; grid-template-columns: repeat(4, 1fr); gap: 12px;
    }
    #erp-fluent-report-ui .fluent-card-large {
        background-color: rgba(255, 255, 255, 0.75); backdrop-filter: blur(20px); -webkit-backdrop-filter: blur(20px);
        border: 1px solid rgba(0, 0, 0, 0.08); border-radius: 8px; padding: 20px;
        display: flex; flex-direction: column; min-height: 170px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.03); transition: all 0.15s ease;
        position: relative; /* สำหรับให้ ::before อ้างอิงพิกัด */
    }
    #erp-fluent-report-ui .fluent-card-large:hover {
        background-color: rgba(255, 255, 255, 0.95); box-shadow: 0 6px 16px rgba(0,0,0,0.06); transform: translateY(-2px); border-color: rgba(0,0,0,0.15);
    }
    
    /* === สไตล์สำหรับกล่องที่ถูก "เลือก" (Active) === */
    #erp-fluent-report-ui .fluent-card-large.active {
        background-color: #ffffff;
        border-color: var(--sys-accent);
        box-shadow: 0 0 0 1px var(--sys-accent), 0 6px 16px rgba(0, 95, 184, 0.15);
        transform: translateY(-2px);
    }
    /* แถบสีด้านซ้ายเวลาถูกเลือก */
    #erp-fluent-report-ui .fluent-card-large.active::before {
        content: '';
        position: absolute;
        left: -1px;
        top: 50%;
        transform: translateY(-50%);
        height: 40px;
        width: 4px;
        background-color: var(--sys-accent);
        border-radius: 4px;
    }

    #erp-fluent-report-ui .card-icon-box {
        width: 44px; height: 44px; border-radius: 6px; display: flex; justify-content: center; align-items: center; margin-bottom: 20px; box-shadow: 0 2px 6px rgba(0,0,0,0.15);
    }
    #erp-fluent-report-ui .card-icon-box svg { width: 22px; height: 22px; fill: white; }
    #erp-fluent-report-ui .card-title { font-size: 15px; font-weight: 600; margin-bottom: 6px; color: var(--sys-text-primary); }
    #erp-fluent-report-ui .card-desc { font-size: 12px; color: var(--sys-text-secondary); line-height: 1.4; flex: 1; }
    #erp-fluent-report-ui .card-link-icon {
        align-self: flex-end; margin-top: 12px; opacity: 0.5; transition: opacity 0.2s;
    }
    #erp-fluent-report-ui .fluent-card-large:hover .card-link-icon,
    #erp-fluent-report-ui .fluent-card-large.active .card-link-icon { 
        opacity: 1; fill: var(--sys-accent); 
    }
    #erp-fluent-report-ui .card-link-icon svg { width: 14px; height: 14px; fill: currentColor; }

    /* 9. Report Directory List (ด้านล่างการ์ด) */
    #erp-fluent-report-ui .directory-surface {
        background-color: var(--sys-content-bg);
        border: 1px solid var(--sys-border-light); border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.02); padding: 8px;
    }
    #erp-fluent-report-ui .directory-search {
        padding: 12px 16px; border-bottom: 1px solid var(--sys-border-light); display: flex; align-items: center; gap: 12px;
    }
    #erp-fluent-report-ui .directory-search svg { width: 16px; height: 16px; fill: var(--sys-text-secondary); }
    #erp-fluent-report-ui .directory-search input {
        border: none; background: transparent; font-family: inherit; font-size: 14px; color: var(--sys-text-primary); flex: 1; outline: none;
    }
    #erp-fluent-report-ui .report-category { margin-top: 16px; }
    #erp-fluent-report-ui .category-header {
        padding: 8px 16px; font-size: 12px; font-weight: 600; color: var(--sys-accent); text-transform: uppercase; letter-spacing: 0.5px;
    }
    #erp-fluent-report-ui .report-item {
        display: flex; align-items: center; padding: 12px 16px; margin: 0 8px; border-radius: 6px; gap: 16px; transition: background 0.1s;
    }
    #erp-fluent-report-ui .report-item:hover { background-color: var(--sys-hover); }
    #erp-fluent-report-ui .report-item:active { background-color: var(--sys-active); }
    #erp-fluent-report-ui .ri-icon {
        width: 32px; height: 32px; border-radius: 4px; background-color: #F0F4F9; display: flex; justify-content: center; align-items: center;
    }
    #erp-fluent-report-ui .ri-icon svg { width: 16px; height: 16px; fill: var(--sys-accent); }
    #erp-fluent-report-ui .ri-content { flex: 1; }
    #erp-fluent-report-ui .ri-title { font-size: 14px; font-weight: 600; color: var(--sys-text-primary); margin-bottom: 2px; }
    #erp-fluent-report-ui .ri-desc { font-size: 12px; color: var(--sys-text-secondary); }
    #erp-fluent-report-ui .ri-meta {
        font-size: 12px; color: var(--sys-text-tertiary); text-align: right; margin-right: 16px;
    }
    #erp-fluent-report-ui .ri-action {
        width: 24px; height: 24px; display: flex; justify-content: center; align-items: center; opacity: 0; transform: translateX(-4px); transition: all 0.2s;
    }
    #erp-fluent-report-ui .report-item:hover .ri-action { opacity: 1; transform: translateX(0); }
    #erp-fluent-report-ui .ri-action svg { width: 16px; height: 16px; fill: var(--sys-text-secondary); }
    
    
</style>

<div id="erp-fluent-report-ui">


    <div class="app-body">
        
        <div class="sidebar">
            <div class="nav-item">
                <div class="nav-icon"><svg viewBox="0 0 24 24"><path d="M12 3l9 7v11h-6v-7h-6v7H3V10z"/></svg></div>
                Home
            </div>
            <div class="nav-item selected" onclick="getData('/analytics-filemanager/9900','microsofts9900');">
                <div class="nav-icon"><svg viewBox="0 0 24 24"><path d="M4 6h16M4 12h16M4 18h16"/></svg></div>
                Report Directory
            </div>
            
            
            <div id="newAnalytics">
             <div class="nav-group-header">
                <svg class="nav-chevron" viewBox="0 0 24 24" style="transform: rotate(90deg);"><path d="M9 18l6-6-6-6" fill="none" stroke="currentColor" stroke-width="2"/></svg>
                <div class="nav-icon"><svg viewBox="0 0 24 24"><path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/></svg></div>
                จัดการข้อมูล (Data)
            </div>

            <div class="nav-sub-items" >
                <div class="nav-item" onclick="getData('/addAnalytics-filemanager','analytics-main-content');">
                    <div class="nav-icon"><svg viewBox="0 0 24 24"><path d="M14 2H6c-1.1 0-1.99.9-1.99 2L4 20c0 1.1.89 2 1.99 2H18c1.1 0 2-.9 2-2V8l-6-6zm2 14h-3v3h-2v-3H8v-2h3v-3h2v3h3v2zm-3-7V3.5L18.5 9H13z"/></svg></div>
                    เพิ่มรายงานใหม่
                </div>
                <div class="nav-item">
                    <div class="nav-icon"><svg viewBox="0 0 24 24"><path d="M3 13h8V3H3v10zm0 8h8v-6H3v6zm10 0h8V11h-8v10zm0-18v6h8V3h-8z"/></svg></div>
                    จัดการหมวดหมู่
                </div>
                <div class="nav-item">
                    <div class="nav-icon"><svg viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-6h2v6zm0-8h-2V7h2v2z"/></svg></div>
                    บันทึกการตั้งค่า
                </div>
            </div>
  </div>
            <div id="related-reports-section" style="display: none; margin-top: 16px;">
                <div class="nav-group-header">
                    <svg class="nav-chevron" viewBox="0 0 24 24" style="transform: rotate(90deg);"><path d="M9 18l6-6-6-6" fill="none" stroke="currentColor" stroke-width="2"/></svg>
                    <div class="nav-icon">
                        <svg viewBox="0 0 24 24"><path d="M4 6h16M4 12h16M4 18h7"/></svg>
                    </div>
                    รายงานที่เกี่ยวข้อง
                </div>
                
                <div class="nav-sub-items" id="related-reports-list">
                </div>
            </div>	
            <div class="spacer"></div>
            <div class="nav-item">
                <div class="nav-icon"><svg viewBox="0 0 24 24"><path d="M19.4 15a1.65 1.65 0 00.33 1.82l.06.06a2 2 0 010 2.83 2 2 0 01-2.83 0l-.06-.06a1.65 1.65 0 00-1.82-.33 1.65 1.65 0 00-1 1.51V21a2 2 0 01-2 2 2 2 0 01-2-2v-.09A1.65 1.65 0 009 19.4a1.65 1.65 0 00-1.82.33l-.06.06a2 2 0 01-2.83 0 2 2 0 010-2.83l.06-.06a1.65 1.65 0 00.33-1.82 1.65 1.65 0 00-1.51-1H3a2 2 0 01-2-2 2 2 0 012-2h.09A1.65 1.65 0 004.6 9a1.65 1.65 0 00-.33-1.82l-.06-.06a2 2 0 010-2.83 2 2 0 012.83 0l.06.06a1.65 1.65 0 001.82.33H9a1.65 1.65 0 001-1.51V3a2 2 0 012-2 2 2 0 012 2v.09a1.65 1.65 0 001 1.51 1.65 1.65 0 001.82-.33l.06-.06a2 2 0 012.83 0 2 2 0 010 2.83l-.06.06a1.65 1.65 0 00-.33 1.82V9a1.65 1.65 0 001.51 1H21a2 2 0 012 2 2 2 0 01-2 2h-.09a1.65 1.65 0 00-1.51 1z"/></svg></div>
                Settings
            </div>
            
        </div>

        <div class="content-surface" id="analytics-main-content">
            
            <div class="hero-banner">
                <div class="hero-wave"></div>
                <div class="hero-content">
                    <div class="hero-subtitle">Analytics Center</div>
                    <div class="hero-title">Report Directory</div>
                    <div class="hero-desc">
                        Browse and access all enterprise reports. Select a category below to filter, or scroll down to view the complete directory.
                    </div>
                </div>
            </div>

            <div class="main-content-inner">
                
                <div class="hero-cards-grid">
                    
                    <div class="fluent-card-large active" onclick="alert('Filter: รายงานทั่วไป')">
                        <div class="card-icon-box" style="background: linear-gradient(135deg, #11998E, #38EF7D);"> 
                            <svg viewBox="0 0 24 24"><path d="M14 2H6c-1.1 0-1.99.9-1.99 2L4 20c0 1.1.89 2 1.99 2H18c1.1 0 2-.9 2-2V8l-6-6zm2 16H8v-2h8v2zm0-4H8v-2h8v2zm-3-5V3.5L18.5 9H13z"/></svg>
                        </div>
                        <div class="card-title">รายงานทั่วไป (General)</div>
                        <div class="card-desc">รายงานสรุปข้อมูลรายวันและเอกสารทั่วไปสำหรับการทำงานพื้นฐาน</div>
                        <div class="card-link-icon"><svg viewBox="0 0 24 24"><path d="M14 3v2h3.59l-9.83 9.83 1.41 1.41L19 6.41V10h2V3m-2 16H5V5h7V3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2v-7h-2v7z"/></svg></div>
                    </div>

                    <div class="fluent-card-large" onclick="alert('Filter: รายงานหลัก')">
                        <div class="card-icon-box" style="background: linear-gradient(135deg, #0052D4, #4364F7);"> 
                            <svg viewBox="0 0 24 24"><path d="M19 3H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zM9 17H7v-7h2v7zm4 0h-2V7h2v10zm4 0h-2v-4h2v4z"/></svg>
                        </div>
                        <div class="card-title">รายงานหลัก (Core)</div>
                        <div class="card-desc">แดชบอร์ดหลักและกราฟสรุปตัวชี้วัด (KPIs) ที่สำคัญต่อองค์กร</div>
                        <div class="card-link-icon"><svg viewBox="0 0 24 24"><path d="M14 3v2h3.59l-9.83 9.83 1.41 1.41L19 6.41V10h2V3m-2 16H5V5h7V3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2v-7h-2v7z"/></svg></div>
                    </div>

                    <div class="fluent-card-large" onclick="alert('Filter: การเทสแบ็ค')">
                        <div class="card-icon-box" style="background: linear-gradient(135deg, #8E2DE2, #4A00E0);"> 
                            <svg viewBox="0 0 24 24"><path d="M12 1L3 5v6c0 5.55 3.84 10.74 9 12 5.16-1.26 9-6.45 9-12V5l-9-4zm-2 16l-4-4 1.41-1.41L10 14.17l6.59-6.59L18 9l-8 8z"/></svg>
                        </div>
                        <div class="card-title">การเทสเเบ็ค (Traceback)</div>
                        <div class="card-desc">ตรวจสอบประวัติย้อนหลัง ทวนสอบข้อมูล และผลการทดสอบระบบ</div>
                        <div class="card-link-icon"><svg viewBox="0 0 24 24"><path d="M14 3v2h3.59l-9.83 9.83 1.41 1.41L19 6.41V10h2V3m-2 16H5V5h7V3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2v-7h-2v7z"/></svg></div>
                    </div>

                    <div class="fluent-card-large" onclick="alert('Filter: วิเคราะห์เชิงลึก')">
                        <div class="card-icon-box" style="background: linear-gradient(135deg, #FF416C, #FF4B2B);"> 
                            <svg viewBox="0 0 24 24"><circle cx="7" cy="8" r="3"/><circle cx="17" cy="8" r="3"/><circle cx="12" cy="17" r="3"/><path d="M8.5 10.5l3.5 4 3.5-4"/></svg>
                        </div>
                        <div class="card-title">วิเคราะห์เชิงลึก (Deep Analytics)</div>
                        <div class="card-desc">รายงานขั้นสูง ขุดเจาะข้อมูลแบบหลายมิติเพื่อหา Insight ทางธุรกิจ</div>
                        <div class="card-link-icon"><svg viewBox="0 0 24 24"><path d="M14 3v2h3.59l-9.83 9.83 1.41 1.41L19 6.41V10h2V3m-2 16H5V5h7V3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2v-7h-2v7z"/></svg></div>
                    </div>

                </div>

                <div class="directory-surface">
                    
                    <div class="directory-search">
                        <svg viewBox="0 0 24 24"><path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/></svg>
                      <input type="text" id="searchInput" oninput="renderReports()" placeholder="ค้นหารายงานจากชื่อ, รหัส, หรือแผนก...">
                    </div>

                    <div class="report-category">
   
                    <div id="report-list-container"></div>

                  
                    </div>

                    <div style="height: 16px;"></div>

                </div>
            </div>



        </div>
    </div>



</div>


<script>
    // 1. รับค่าจาก Spring ModelMap (Listreports) มาสร้างเป็น JavaScript Array
    var reportsData = [
        <c:forEach items="${Listreports}" var="report" varStatus="status">
        {
            id: "${report.id}",
            reportName: "${report.reportName}",
            category: "${report.category}",
            description: "${report.description}",
            iconType: "${report.iconType}"
        }${!status.last ? ',' : ''}
        </c:forEach>
    ];

    
    renderReports();
    var analyticsBtn = document.getElementById("newAnalytics");
    if (analyticsBtn) {
        analyticsBtn.style.display = "block"; // ซ่อนปุ่ม/กล่อง
    }
</script>