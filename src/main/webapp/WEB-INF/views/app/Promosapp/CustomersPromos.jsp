<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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

        .chs-app-wrapper .dashboard-split { display: grid; grid-template-columns: 1.8fr 1.2fr; gap: 24px; }
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
    </style>

    <main class="main-container">
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
                        <div class="kpi-title">Customer Grades</div>
                        <div class="kpi-icon bg-blue"><svg viewBox="0 0 24 24"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg></div>
                    </div>
                    <div class="kpi-value">2,840</div>
                    <div style="font-size: 11px; color: var(--text-muted); margin-top: 6px;">
                        <span class="link-pill">A: 420</span> <span class="link-pill">B: 850</span> <span class="link-pill">C: 1,200</span>
                        <br><span class="link-pill active" style="margin-top:4px;">NEW: 150</span> <span class="link-pill" style="margin-top:4px;">NONE: 220</span>
                    </div>
                </div>
                
                <!-- 2. การเคลื่อนไหวเกรดลูกค้า -->
                <div class="kpi-card">
                    <div class="kpi-header">
                        <div class="kpi-title">Grade Movement (YTD)</div>
                        <div class="kpi-icon bg-green"><svg viewBox="0 0 24 24"><polyline points="23 4 23 10 17 10"></polyline><polyline points="1 20 1 14 7 14"></polyline><path d="M3.51 9a9 9 0 0 1 14.85-3.36L23 10M1 14l4.64 4.36A9 9 0 0 0 20.49 15"></path></svg></div>
                    </div>
                    <div class="kpi-value"><span style="color:#10b981">↑ 85</span> <span style="color:#e2e8f0; font-size:18px;">|</span> <span style="color:#f59e0b">↓ 24</span></div>
                    <div style="font-size: 11px; color: var(--text-muted); margin-top: 6px;">
                        อัปเกรด 85 ราย / ตกเกรด 24 ราย
                    </div>
                </div>

                <!-- 3. ความถี่การติดต่อ -->
                <div class="kpi-card">
                    <div class="kpi-header">
                        <div class="kpi-title">Contact Frequency / Year</div>
                        <div class="kpi-icon bg-orange"><svg viewBox="0 0 24 24"><path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path></svg></div>
                    </div>
                    <div class="kpi-value" style="font-size: 20px;">Avg: 1.8 ครั้ง/ปี</div>
                    <div style="font-size: 11px; color: var(--text-muted); margin-top: 6px;">
                        <span class="link-pill">3 ครั้ง: 412</span> <span class="link-pill">2 ครั้ง: 850</span>
                        <br><span class="link-pill" style="margin-top:4px;">1 ครั้ง: 1,028</span> <span class="link-pill active" style="margin-top:4px;">0 ครั้ง: 550</span>
                    </div>
                </div>

                <!-- 4. เอกสารปีนี้ -->
                <div class="kpi-card" style="border: 1px solid var(--primary);">
                    <div class="kpi-header">
                        <div class="kpi-title" style="color: var(--primary);">Active Documents (YTD)</div>
                        <div class="kpi-icon bg-purple"><svg viewBox="0 0 24 24"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path><polyline points="14 2 14 8 20 8"></polyline><line x1="16" y1="13" x2="8" y2="13"></line><line x1="16" y1="17" x2="8" y2="17"></line><polyline points="10 9 9 9 8 9"></polyline></svg></div>
                    </div>
                    <div class="kpi-value" style="font-size: 20px; color: var(--text-dark);">FO: 840 ใบ</div>
                    <div style="font-size: 11px; color: var(--text-muted); margin-top: 6px;">
                        เทียบกับ <span style="font-weight:bold; color:var(--text-dark);">QT: 2,150 ใบ</span> 
                        <br>อัตราการแปลง (Conversion): 39%
                    </div>
                </div>
            </div>

            <div class="dashboard-split">
                
                <div style="display: flex; flex-direction: column; gap: 24px;">
                    
                    <!-- 6. กลุ่มตามการติดต่อ & เอกสาร -->
                    <div class="panel">
                        <div class="panel-header" style="margin-bottom: 20px;">
                            <div class="panel-title">Customer Aging & Document Status</div>
                            <span style="font-size: 12px; color: var(--text-muted);">ระยะเวลาติดต่อล่าสุดเทียบกับเอกสาร</span>
                        </div>
                        
                        <div class="segment-list">
                            <!-- ระยะ 3 เดือน -->
                            <div class="segment-item">
                                <a href="#" class="segment-label" style="text-decoration:none;">
                                    <strong>< 3 เดือน</strong>
                                    <span>คลิกดูรายชื่อ</span>
                                </a>
                                <div class="segment-track">
                                    <div class="segment-fill" style="width: 65%; background: var(--blue);">FO: 450 | QT: 800</div>
                                </div>
                                <div class="segment-value">1,250 ราย</div>
                            </div>
                            <!-- ระยะ 6 เดือน -->
                            <div class="segment-item">
                                <a href="#" class="segment-label" style="text-decoration:none;">
                                    <strong>3 - 6 เดือน</strong>
                                    <span>คลิกดูรายชื่อ</span>
                                </a>
                                <div class="segment-track">
                                    <div class="segment-fill" style="width: 40%; background: var(--primary);">FO: 120 | QT: 450</div>
                                </div>
                                <div class="segment-value">570 ราย</div>
                            </div>
                            <!-- ระยะ 9 เดือน -->
                            <div class="segment-item">
                                <a href="#" class="segment-label" style="text-decoration:none;">
                                    <strong>6 - 9 เดือน</strong>
                                    <span>คลิกดูรายชื่อ</span>
                                </a>
                                <div class="segment-track">
                                    <div class="segment-fill" style="width: 25%; background: var(--orange);">FO: 40 | QT: 150</div>
                                </div>
                                <div class="segment-value">190 ราย</div>
                            </div>
                            <!-- ระยะ 12 เดือน -->
                            <div class="segment-item">
                                <a href="#" class="segment-label" style="text-decoration:none;">
                                    <strong>9 - 12 เดือน</strong>
                                    <span>คลิกดูรายชื่อ</span>
                                </a>
                                <div class="segment-track">
                                    <div class="segment-fill" style="width: 15%; background: var(--text-muted);">FO: 15 | QT: 60</div>
                                </div>
                                <div class="segment-value">75 ราย</div>
                            </div>
                            <!-- ระยะ 24 เดือน -->
                            <div class="segment-item">
                                <a href="#" class="segment-label" style="text-decoration:none;">
                                    <strong>> 24 เดือน</strong>
                                    <span>คลิกดูรายชื่อ</span>
                                </a>
                                <div class="segment-track">
                                    <div class="segment-fill" style="width: 5%; background: #ef4444;">FO: 0 | QT: 10</div>
                                </div>
                                <div class="segment-value">25 ราย</div>
                            </div>
                        </div>
                    </div>

                    <!-- 5. โอกาสปิดการขาย & 7. ข้อมูลเชิงลึกจาก Entity -->
                    <div class="panel" style="padding: 0; flex: 1;">
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

                <div style="display: flex; flex-direction: column; gap: 24px;">
                    
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

                    <div class="panel" style="flex: 1;">
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