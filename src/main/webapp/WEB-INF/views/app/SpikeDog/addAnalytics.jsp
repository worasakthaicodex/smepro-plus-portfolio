<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style>
    /* 1. Scoped CSS Variables */
    #erp-fluent-report-ui {
        --sys-bg: #F3F3F3;
        --sys-content-bg: #FFFFFF;
        --sys-border-light: #E5E5E5;
        --sys-border-card: #E0E0E0;
        --sys-border-input: #8A8A8A;
        --sys-accent: #005FB8;
        --sys-accent-hover: #004C93;
        --sys-text-primary: #1A1A1A;
        --sys-text-secondary: #5D5D5D;
        --sys-hover: rgba(0, 0, 0, 0.035);
        --sys-active: rgba(0, 0, 0, 0.06);
        --font-family: 'Segoe UI Variable Text', 'Segoe UI', -apple-system, BlinkMacSystemFont, sans-serif;

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

    #erp-fluent-report-ui, #erp-fluent-report-ui * {
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

    /* 4. Layout & Sidebar */
    #erp-fluent-report-ui .app-body { display: flex; flex: 1; overflow: hidden; }
    #erp-fluent-report-ui .sidebar {
        width: 280px; display: flex; flex-direction: column; padding: 0 12px 12px 12px; background-color: var(--sys-bg);
    }
    #erp-fluent-report-ui .nav-item {
        display: flex; align-items: center; height: 36px; padding: 0 12px; border-radius: 4px; margin-bottom: 2px; font-size: 14px; color: var(--sys-text-primary); position: relative; gap: 16px; transition: background 0.1s;
    }
    #erp-fluent-report-ui .nav-item:hover { background-color: var(--sys-hover); }
    #erp-fluent-report-ui .nav-item:active { background-color: var(--sys-active); }
    #erp-fluent-report-ui .nav-item.selected { background-color: var(--sys-hover); }
    #erp-fluent-report-ui .nav-item.selected::before {
        content: ''; position: absolute; left: 0px; top: 50%; transform: translateY(-50%); height: 16px; width: 3px; background-color: var(--sys-accent); border-radius: 4px;
    }
    #erp-fluent-report-ui .nav-icon { display: flex; justify-content: center; align-items: center; width: 16px; height: 16px; }
    #erp-fluent-report-ui .nav-icon svg { width: 16px; height: 16px; fill: none; stroke: var(--sys-text-primary); stroke-width: 1.5; }
    
    /* เมนูแบบ Dropdown Header */
    #erp-fluent-report-ui .nav-group-header {
        display: flex; align-items: center; gap: 8px; height: 36px; padding: 0 12px; font-size: 14px; margin-top: 16px; font-weight: 600;
    }
    #erp-fluent-report-ui .nav-chevron { margin-right: -4px; width: 12px; height: 12px; opacity: 0.6; }
    #erp-fluent-report-ui .nav-sub-items { padding-left: 12px; }
    #erp-fluent-report-ui .spacer { flex: 1; }

    /* 5. Main Content */
    #erp-fluent-report-ui .content-surface {
        flex: 1; background-color: var(--sys-content-bg); 
        border-top-left-radius: 8px; border: 1px solid var(--sys-border-light); border-bottom: none; 
        border-right: none; overflow-y: auto; display: flex; flex-direction: column; position: relative;
            height: 90%;
    }

    #erp-fluent-report-ui .hero-banner {
        position: relative; min-height: 240px; padding: 48px 48px;
        background: radial-gradient(circle at 80% 0%, rgba(0, 95, 184, 0.1) 0%, transparent 40%), linear-gradient(135deg, #f8f9fa 0%, #eef3f8 100%);
        border-bottom: 1px solid var(--sys-border-light);
    }
    #erp-fluent-report-ui .hero-content { position: relative; z-index: 2; max-width: 500px; }
    #erp-fluent-report-ui .hero-subtitle { font-size: 14px; font-weight: 600; color: var(--sys-accent); margin-bottom: 8px; }
    #erp-fluent-report-ui .hero-title { font-size: 32px; font-weight: 600; color: var(--sys-text-primary); margin-bottom: 12px; font-family: 'Segoe UI Variable Display', sans-serif;}
    #erp-fluent-report-ui .hero-desc { font-size: 14px; color: var(--sys-text-secondary); line-height: 1.5; }

    /* 6. Form Surface (กล่องใส่ฟอร์ม) */
    #erp-fluent-report-ui .form-container {
        padding: 0 48px 48px 48px; margin-top: -30px; position: relative; z-index: 10; max-width: 900px;
    }
    #erp-fluent-report-ui .form-surface {
        background-color: rgba(255, 255, 255, 0.95); backdrop-filter: blur(20px);
        border: 1px solid rgba(0, 0, 0, 0.08); border-radius: 8px; box-shadow: 0 4px 16px rgba(0,0,0,0.04); padding: 32px;
    }

    /* 7. Fluent UI Form Elements */
    #erp-fluent-report-ui .form-group { margin-bottom: 24px; }
    #erp-fluent-report-ui .form-label {
        display: block; font-size: 14px; font-weight: 600; margin-bottom: 8px; color: var(--sys-text-primary);
    }
    #erp-fluent-report-ui .form-hint {
        font-size: 12px; color: var(--sys-text-secondary); margin-top: 4px; display: block;
    }
    
    /* Text Inputs */
    #erp-fluent-report-ui .fluent-input {
        width: 100%; background-color: #FDFDFD;
        border: 1px solid var(--sys-border-light); border-bottom: 1px solid var(--sys-border-input);
        border-radius: 4px; padding: 8px 12px; font-family: inherit; font-size: 14px; color: var(--sys-text-primary);
        transition: border-bottom-color 0.2s, background-color 0.2s;
        cursor: text; user-select: text;
    }
    #erp-fluent-report-ui .fluent-input:hover { background-color: #F6F6F6; }
    #erp-fluent-report-ui .fluent-input:focus {
        outline: none; border-bottom: 2px solid var(--sys-accent); background-color: #FFFFFF; padding-bottom: 7px; /* ชดเชย border ที่หนาขึ้น */
    }

    #erp-fluent-report-ui textarea.fluent-input {
        resize: vertical; min-height: 80px;
    }

    /* Icon Picker Grid */
    #erp-fluent-report-ui .icon-picker-grid {
        display: flex; gap: 12px; flex-wrap: wrap;color:black;
    }
 #erp-fluent-report-ui .icon-option {
    width: 52px;
    height: 52px;
    border: 1px solid #d1d5db; /* เปลี่ยนจาก variable เพื่อทดสอบ */
    border-radius: 6px;
    background-color: #ffffff; 
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    color: #0000ff !important; /* บังคับสีน้ำเงิน */
    opacity: 1 !important;     /* ป้องกันการจาง */
    filter: none !important;    /* ป้องกันการโดน filter: grayscale หรือ opacity */
    position: relative;
    z-index: 10;
    box-shadow: 0 1px 3px rgba(0,0,0,0.1);
}
  #erp-fluent-report-ui .icon-option svg { 
    width: 24px; 
    height: 24px; 
 
    /* หรือระบุสีน้ำเงินตามที่คุณต้องการในส่วนก่อนหน้า */
    /* fill: #0000ff !important; */
    opacity: 1 !important; /* ป้องกันการสืบทอดความจางมาจาก parent */
}
    #erp-fluent-report-ui .icon-option:hover { background-color: var(--sys-hover); }
    
    /* Selected Icon State */
    #erp-fluent-report-ui .icon-option.selected {
        border-color: var(--sys-accent); background-color: #F0F6FF; box-shadow: 0 0 0 1px var(--sys-accent);
    }
    #erp-fluent-report-ui .icon-option.selected svg { fill: var(--sys-accent); }

    /* Action Buttons */
    #erp-fluent-report-ui .form-actions {
        display: flex; justify-content: flex-end; gap: 12px; margin-top: 32px; padding-top: 24px; border-top: 1px solid var(--sys-border-light);
    }
    #erp-fluent-report-ui .fluent-btn {
        padding: 6px 24px; border-radius: 4px; font-family: inherit; font-size: 14px; font-weight: 500;
        cursor: pointer; border: 1px solid var(--sys-border-input); background-color: #FFFFFF; color: var(--sys-text-primary);
        transition: all 0.1s; min-width: 100px; text-align: center;
    }
    #erp-fluent-report-ui .fluent-btn:hover { background-color: #F6F6F6; }
    #erp-fluent-report-ui .fluent-btn:active { background-color: #EFEFEF; color: var(--sys-text-secondary); }
    
    #erp-fluent-report-ui .fluent-btn.primary {
        background-color: var(--sys-accent); color: #FFFFFF; border-color: transparent;
    }
    #erp-fluent-report-ui .fluent-btn.primary:hover { background-color: var(--sys-accent-hover); }
    #erp-fluent-report-ui .fluent-btn.primary:active { background-color: #003E78; opacity: 0.8; }

/* แถม CSS สำหรับปุ่ม Delete แบบ Fluent UI */
    #erp-fluent-report-ui .fluent-btn.danger {
        color: #D13438;
        border-color: #F3DDDF;
        background-color: #FDF3F4;
    }
    #erp-fluent-report-ui .fluent-btn.danger:hover { background-color: #FDE7E9; border-color: #D13438; }
    #erp-fluent-report-ui .fluent-btn.danger:active { background-color: #FBEAEA; }
</style>

<div class="hero-banner">
    <div class="hero-content">
        <div class="hero-subtitle">Configuration</div>
        
        <div class="hero-title">
            <c:choose>
                <c:when test="${not empty report.id}">แก้ไขรายงาน (Edit Report)</c:when>
                <c:otherwise>ตั้งค่ารายงานใหม่</c:otherwise>
            </c:choose>
        </div>
        
        <div class="hero-desc">
            ระบุรายละเอียด ชื่อรายงาน ลิงก์ปลายทาง (URL) และเลือกสัญลักษณ์ (Icon) เพื่อนำไปแสดงผลบนหน้าระบบรายงานหลัก
        </div>
    </div>
</div>

<form id="formReport" name="formReport">
    <c:if test="${not empty report.id}">
        <input type="hidden" name="id" value="${report.id}" />
    </c:if>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <div class="form-container">
        <div class="form-surface">
            
            <div class="form-group">
                <label class="form-label">ชื่อรายงาน (Report Name)</label>
                <input type="text" id="inputReportName" name="reportName" class="fluent-input" 
                       placeholder="เช่น รายงานสรุปยอดขายรายเดือน" 
                       value="${report.reportName}" required />
            </div>

            <div class="form-group">
                <label class="form-label">หมวดหมู่ (Category)</label>
                <select id="inputCategory" name="category" class="fluent-input" style="width: 100%; padding: 8px; border: 1px solid var(--sys-border-light); border-radius: 4px;">
                    <option value="FI" ${report.category == 'FI' ? 'selected' : ''}>Financial Accounting (FI)</option>
                    <option value="SD" ${report.category == 'SD' ? 'selected' : ''}>Sales & Distribution (SD)</option>
                    <option value="MM" ${report.category == 'MM' ? 'selected' : ''}>Materials Management (MM)</option>
                    <option value="MK" ${report.category == 'MK' ? 'selected' : ''}>Marketing (MK)</option>
                     <option value="OF" ${report.category == 'OF' ? 'selected' : ''}>Office & HR (OF)</option>
                </select>
            </div>

            <div class="form-group">
                <label class="form-label">ลิงก์รายงาน (Report URL Link)</label>
                <input type="text" id="inputReportUrl" name="reportUrl" class="fluent-input" 
                       placeholder="https://bi.yourserver.com/report/view?id=..." 
                       value="${report.reportUrl}" required />
            </div>

            <div class="form-group">
                <label class="form-label">คำอธิบาย (Description)</label>
                <textarea id="inputDescription" class="fluent-input" name="description" 
                          placeholder="กรอกคำอธิบายสั้นๆ...">${report.description}</textarea>
            </div>

            <div class="form-group">
                <label class="form-label">เลือกสัญลักษณ์ (Report Logo / Icon)</label>
                <select id="inputIconType" name="iconType" class="fluent-input" style="width: 100%; padding: 8px; border: 1px solid var(--sys-border-light); border-radius: 4px;">
                    <option value="bar-chart" ${report.iconType == 'bar-chart' ? 'selected' : ''}>📊 กราฟแท่ง (Bar Chart)</option>
                    <option value="pie-chart" ${report.iconType == 'pie-chart' ? 'selected' : ''}>🥧 กราฟวงกลม (Pie Chart)</option>
                    <option value="line-chart" ${report.iconType == 'line-chart' ? 'selected' : ''}>📈 กราฟเส้น (Line Chart)</option>
                    <option value="table" ${report.iconType == 'table' ? 'selected' : ''}>📋 ตารางข้อมูล (Table)</option>
                    <option value="finance" ${report.iconType == 'finance' ? 'selected' : ''}>💰 เอกสารการเงิน (Finance)</option>
                </select>
            </div>

            <div class="form-actions">
                <c:if test="${not empty report.id}">
                    <button class="fluent-btn danger" type="button" style="margin-right: auto;"
                            onclick="if(confirm('คุณแน่ใจหรือไม่ที่จะลบรายงานนี้?')) { getData('/deleteAnalytics-filemanager/${report.id}', 'microsofts9900'); }">
                        ลบข้อมูล (Delete)
                    </button>
         
                </c:if>

                <button class="fluent-btn" type="button" onclick="getData('/allAnalytics-filemanager', 'analytics-main-content');">ยกเลิก</button>
                
                <button class="fluent-btn primary" type="button"
                        onclick="postData('/saveAnalytics-filemanager?${_csrf.parameterName}=${_csrf.token}', 'formReport','analytics-main-content');">
                    บันทึกข้อมูล (Save)
                </button>
            </div>

        </div>
    </div>
</form>