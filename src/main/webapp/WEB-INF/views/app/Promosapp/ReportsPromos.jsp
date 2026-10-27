<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="chs-app-wrapper" id="chsReportsApp">
    <style>
        /* CSS Reset & Scoping */
        .chs-app-wrapper {
            --sidebar-bg: #111827;
            --sidebar-active: #10b981;
            --bg-main: #f8fafc;
            --white: #ffffff;
            --text-dark: #0f172a;
            --text-base: #334155;
            --text-muted: #64748b;
            --border-color: #e2e8f0;
            --primary: #10b981;
            --primary-light: #dcfce7;
            --blue: #0ea5e9;
            --ai-color: #8b5cf6;
            
            font-family: 'Segoe UI', 'Inter', -apple-system, sans-serif;
            background-color: var(--bg-main);
            color: var(--text-base);
            display: flex;
            height: 90vh;
            min-height: 800px;
            overflow: hidden;
            box-shadow: 0 4px 20px rgba(0,0,0,0.05);
            font-size: 13px;
            line-height: 1.5;
            box-sizing: border-box;
        }

        .chs-app-wrapper * { box-sizing: border-box; margin: 0; padding: 0; }
        .chs-app-wrapper svg { fill: none; stroke: currentColor; stroke-width: 2; stroke-linecap: round; stroke-linejoin: round; }

     
        /* ================= MAIN LAYOUT & HEADER ================= */
        .chs-app-wrapper .main-container { flex: 1; display: flex; flex-direction: column; overflow: hidden; }
        .chs-app-wrapper .top-header { background-color: var(--white); height: 64px; min-height: 64px; display: flex; align-items: center; justify-content: space-between; padding: 0 24px; border-bottom: 1px solid var(--border-color); }
        .chs-app-wrapper .header-title { font-size: 18px; font-weight: 600; color: var(--text-dark); }
        
        .chs-app-wrapper .header-actions { display: flex; gap: 12px; align-items: center; }
        .chs-app-wrapper .btn-outline { background-color: var(--white); color: var(--text-dark); border: 1px solid var(--border-color); padding: 8px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: 500; display: flex; align-items: center; gap: 8px;}
        .chs-app-wrapper .btn-outline:hover { background-color: #f1f5f9; }

        /* ================= CONTENT BODY ================= */
        .chs-app-wrapper .content-area { padding: 24px; overflow-y: auto; flex: 1; display: flex; flex-direction: column; gap: 24px; }

        /* Filter Bar */
        .chs-app-wrapper .filter-bar { display: flex; justify-content: space-between; align-items: center; }
        .chs-app-wrapper .filter-group { display: flex; gap: 16px; }
        .chs-app-wrapper .filter-select { padding: 8px 32px 8px 12px; border: 1px solid var(--border-color); border-radius: 4px; font-size: 13px; color: var(--text-dark); font-weight: 500; background: var(--white) url("data:image/svg+xml;utf8,<svg viewBox='0 0 24 24' width='16' height='16' fill='none' stroke='%2364748b' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'><polyline points='6 9 12 15 18 9'></polyline></svg>") no-repeat right 8px center; appearance: none; outline: none; cursor: pointer; }

        /* KPI Row */
        .chs-app-wrapper .kpi-row { display: grid; grid-template-columns: repeat(4, 1fr); gap: 24px; }
        .chs-app-wrapper .kpi-card { background: var(--white); border: 1px solid var(--border-color); border-radius: 8px; padding: 20px; box-shadow: 0 1px 2px rgba(0,0,0,0.02); }
        .chs-app-wrapper .kpi-title { font-size: 12px; color: var(--text-muted); font-weight: 500; margin-bottom: 8px; text-transform: uppercase; letter-spacing: 0.5px; }
        .chs-app-wrapper .kpi-value { font-size: 24px; font-weight: 700; color: var(--text-dark); margin-bottom: 8px; display: flex; align-items: center; gap: 8px; }
        .chs-app-wrapper .trend-badge { font-size: 11px; padding: 2px 6px; border-radius: 4px; font-weight: 600; display: inline-flex; align-items: center; gap: 2px; }
        .chs-app-wrapper .trend-up { background: #dcfce7; color: #166534; }
        .chs-app-wrapper .trend-down { background: #fee2e2; color: #991b1b; }

        /* Chart & Breakdown Section (Split 2:1) */
        .chs-app-wrapper .dashboard-split { display: grid; grid-template-columns: 2fr 1fr; gap: 24px; }
        .chs-app-wrapper .panel { background: var(--white); border: 1px solid var(--border-color); border-radius: 8px; padding: 24px; box-shadow: 0 1px 2px rgba(0,0,0,0.02); display: flex; flex-direction: column; }
        .chs-app-wrapper .panel-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px; }
        .chs-app-wrapper .panel-title { font-size: 16px; font-weight: 600; color: var(--text-dark); }
        
        /* CSS Bar Chart System */
        .chs-app-wrapper .chart-container { flex: 1; display: flex; flex-direction: column; position: relative; min-height: 250px; }
        .chs-app-wrapper .y-axis { position: absolute; left: 0; top: 0; bottom: 30px; display: flex; flex-direction: column; justify-content: space-between; color: var(--text-muted); font-size: 11px; padding-right: 10px; width: 40px; text-align: right; }
        .chs-app-wrapper .chart-grid { position: absolute; left: 50px; right: 0; top: 0; bottom: 30px; border-bottom: 1px solid var(--border-color); display: flex; flex-direction: column; justify-content: space-between; }
        .chs-app-wrapper .grid-line { width: 100%; border-top: 1px dashed var(--border-color); height: 0; }
        
        .chs-app-wrapper .bars-area { position: absolute; left: 50px; right: 0; top: 0; bottom: 30px; display: flex; align-items: flex-end; justify-content: space-around; padding: 0 10px; }
        .chs-app-wrapper .bar-group { display: flex; flex-direction: column; align-items: center; width: 40px; height: 100%; justify-content: flex-end; position: relative; cursor: pointer; }
        .chs-app-wrapper .bar-fill { width: 32px; background: var(--primary); border-radius: 4px 4px 0 0; transition: all 0.3s ease; position: relative; }
        .chs-app-wrapper .bar-group:hover .bar-fill { background: var(--blue); }
        
        /* Tooltip on Hover */
        .chs-app-wrapper .bar-tooltip { position: absolute; top: -35px; background: var(--text-dark); color: var(--white); padding: 4px 8px; border-radius: 4px; font-size: 11px; font-weight: 500; opacity: 0; transition: opacity 0.2s; white-space: nowrap; pointer-events: none; }
        .chs-app-wrapper .bar-tooltip::after { content: ''; position: absolute; bottom: -4px; left: 50%; transform: translateX(-50%); border-width: 4px 4px 0; border-style: solid; border-color: var(--text-dark) transparent transparent transparent; }
        .chs-app-wrapper .bar-group:hover .bar-tooltip { opacity: 1; }

        .chs-app-wrapper .x-axis { position: absolute; left: 50px; right: 0; bottom: 0; height: 30px; display: flex; justify-content: space-around; align-items: center; color: var(--text-muted); font-size: 11px; padding: 0 10px; }

        /* Audience Progress Bars */
        .chs-app-wrapper .audience-list { display: flex; flex-direction: column; gap: 20px; }
        .chs-app-wrapper .audience-item { display: flex; flex-direction: column; gap: 8px; }
        .chs-app-wrapper .aud-header { display: flex; justify-content: space-between; font-size: 13px; font-weight: 500; color: var(--text-dark); }
        .chs-app-wrapper .aud-track { width: 100%; height: 8px; background: #f1f5f9; border-radius: 4px; overflow: hidden; }
        .chs-app-wrapper .aud-fill { height: 100%; border-radius: 4px; }

        /* Table Section */
        .chs-app-wrapper table { width: 100%; border-collapse: collapse; text-align: left; }
        .chs-app-wrapper th { padding: 12px 16px; font-weight: 500; color: var(--text-muted); font-size: 12px; border-bottom: 1px solid var(--border-color); background: #f8fafc; }
        .chs-app-wrapper th:first-child { border-top-left-radius: 6px; }
        .chs-app-wrapper th:last-child { border-top-right-radius: 6px; text-align: right; }
        .chs-app-wrapper td { padding: 16px; border-bottom: 1px solid #f1f5f9; color: var(--text-base); font-size: 13px; }
        .chs-app-wrapper td:last-child { text-align: right; font-weight: 600; color: var(--text-dark); }
        .chs-app-wrapper tr:hover td { background-color: #f8fafc; }

    </style>

    <aside class="sidebar">
 <%@ include file="nav.jspf" %>  
    </aside>

    <main class="main-container">
        <header class="top-header">
            <div class="header-title">Performance Reports</div>
            <div class="header-actions">
                <button class="btn-outline">
                    <svg viewBox="0 0 24 24" width="16" height="16"><rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect><line x1="16" y1="2" x2="16" y2="6"></line><line x1="8" y1="2" x2="8" y2="6"></line><line x1="3" y1="10" x2="21" y2="10"></line></svg>
                    Jan 1, 2026 - Jun 30, 2026
                </button>
                <button class="btn-outline">
                    <svg viewBox="0 0 24 24" width="16" height="16"><path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path><polyline points="7 10 12 15 17 10"></polyline><line x1="12" y1="15" x2="12" y2="3"></line></svg>
                    Export Report
                </button>
            </div>
        </header>

        <div class="content-area">
            
            <div class="filter-bar">
                <div class="filter-group">
                    <select class="filter-select"><option>All Categories</option><option>B2B Deals</option><option>Retail Promos</option></select>
                    <select class="filter-select"><option>All Status</option><option>Active</option><option>Ended</option></select>
                </div>
            </div>

            <div class="kpi-row">
                <div class="kpi-card">
                    <div class="kpi-title">Total Generated Revenue</div>
                    <div class="kpi-value">
                        ฿1.85M 
                        <span class="trend-badge trend-up"><svg viewBox="0 0 24 24" width="10" height="10"><polyline points="18 15 12 9 6 15"></polyline></svg> 12%</span>
                    </div>
                    <div style="font-size: 11px; color: var(--text-muted);">vs Previous 6 Months</div>
                </div>
                <div class="kpi-card">
                    <div class="kpi-title">Total Redemptions (Manual)</div>
                    <div class="kpi-value">
                        2,450
                        <span class="trend-badge trend-up"><svg viewBox="0 0 24 24" width="10" height="10"><polyline points="18 15 12 9 6 15"></polyline></svg> 5.4%</span>
                    </div>
                    <div style="font-size: 11px; color: var(--text-muted);">Across 14 Active Promos</div>
                </div>
                <div class="kpi-card">
                    <div class="kpi-title">Avg. Revenue / Redemption</div>
                    <div class="kpi-value">
                        ฿755
                        <span class="trend-badge trend-down" style="color: #991b1b;"><svg viewBox="0 0 24 24" width="10" height="10"><polyline points="6 9 12 15 18 9"></polyline></svg> 2.1%</span>
                    </div>
                    <div style="font-size: 11px; color: var(--text-muted);">Slight drop in B2C segment</div>
                </div>
                <div class="kpi-card" style="border-bottom: 3px solid var(--ai-color);">
                    <div class="kpi-title" style="color: var(--ai-color);">AI Strategy Win-Rate</div>
                    <div class="kpi-value">
                        78%
                        <span style="font-size: 14px; font-weight: 500; color: var(--text-muted); margin-left: 4px;">Success</span>
                    </div>
                    <div style="font-size: 11px; color: var(--text-muted);">Promos optimized by AI hit their targets</div>
                </div>
            </div>

            <div class="dashboard-split">
                
                <div class="panel">
                    <div class="panel-header">
                        <div class="panel-title">Revenue Trend vs Target</div>
                        <div style="display: flex; gap: 12px; font-size: 12px; color: var(--text-muted);">
                            <span style="display: flex; align-items: center; gap: 4px;"><div style="width:10px; height:10px; background:var(--primary); border-radius:2px;"></div> Actual Revenue</span>
                            <span style="display: flex; align-items: center; gap: 4px;"><div style="width:10px; height:2px; background:var(--text-dark); border-top:2px dashed var(--text-dark);"></div> Target Goal</span>
                        </div>
                    </div>
                    
                    <div class="chart-container">
                        <div class="y-axis">
                            <span>400k</span><span>300k</span><span>200k</span><span>100k</span><span>0</span>
                        </div>
                        
                        <div class="chart-grid">
                            <div class="grid-line"></div><div class="grid-line"></div><div class="grid-line"></div><div class="grid-line"></div><div class="grid-line" style="border-top-style: solid;"></div>
                        </div>

                        <div style="position: absolute; left: 50px; right: 0; bottom: 70%; border-top: 2px dashed var(--text-dark); z-index: 2;"></div>

                        <div class="bars-area">
                            <div class="bar-group">
                                <div class="bar-tooltip">Jan: ฿180k</div>
                                <div class="bar-fill" style="height: 45%;"></div>
                            </div>
                            <div class="bar-group">
                                <div class="bar-tooltip">Feb: ฿220k</div>
                                <div class="bar-fill" style="height: 55%;"></div>
                            </div>
                            <div class="bar-group">
                                <div class="bar-tooltip">Mar: ฿350k (AI Boost)</div>
                                <div class="bar-fill" style="height: 87%; background: var(--ai-color);"></div>
                            </div>
                            <div class="bar-group">
                                <div class="bar-tooltip">Apr: ฿290k</div>
                                <div class="bar-fill" style="height: 72%;"></div>
                            </div>
                            <div class="bar-group">
                                <div class="bar-tooltip">May: ฿310k</div>
                                <div class="bar-fill" style="height: 77%;"></div>
                            </div>
                            <div class="bar-group">
                                <div class="bar-tooltip">Jun: ฿380k</div>
                                <div class="bar-fill" style="height: 95%;"></div>
                            </div>
                        </div>

                        <div class="x-axis">
                            <span>Jan</span><span>Feb</span><span>Mar</span><span>Apr</span><span>May</span><span>Jun</span>
                        </div>
                    </div>
                </div>

                <div class="panel">
                    <div class="panel-header">
                        <div class="panel-title">Redemptions by Audience</div>
                    </div>
                    
                    <div class="audience-list">
                        <div class="audience-item">
                            <div class="aud-header">
                                <span>Existing VIPs</span>
                                <span style="font-weight: 600;">52%</span>
                            </div>
                            <div class="aud-track"><div class="aud-fill" style="width: 52%; background: var(--primary);"></div></div>
                            <div style="font-size: 11px; color: var(--text-muted);">1,274 Redemptions</div>
                        </div>
                        
                        <div class="audience-item">
                            <div class="aud-header">
                                <span>B2B / Corporate</span>
                                <span style="font-weight: 600;">31%</span>
                            </div>
                            <div class="aud-track"><div class="aud-fill" style="width: 31%; background: var(--blue);"></div></div>
                            <div style="font-size: 11px; color: var(--text-muted);">759 Redemptions</div>
                        </div>

                        <div class="audience-item">
                            <div class="aud-header">
                                <span>New Customers</span>
                                <span style="font-weight: 600;">17%</span>
                            </div>
                            <div class="aud-track"><div class="aud-fill" style="width: 17%; background: #f59e0b;"></div></div>
                            <div style="font-size: 11px; color: var(--text-muted);">417 Redemptions</div>
                        </div>
                    </div>

                    <div style="margin-top: auto; padding-top: 20px; border-top: 1px solid var(--border-color); font-size: 12px; color: var(--text-muted); line-height: 1.5;">
                        <span style="color: var(--ai-color); font-weight: 600;">AI Insight:</span> B2B segment yields the highest average order value (฿4,200). Consider shifting Q3 budget towards corporate bundles.
                    </div>
                </div>
            </div>

            <div class="panel" style="padding: 0;">
                <div class="panel-header" style="padding: 24px 24px 0 24px; margin-bottom: 16px;">
                    <div class="panel-title">Top Performing Campaigns</div>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>Campaign Name</th>
                            <th>Offer Type</th>
                            <th>Audience</th>
                            <th>Total Redemptions</th>
                            <th>Generated Revenue</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td style="font-weight: 500;">
                                Summer B2B Bulk Discount 
                                <span style="background:#fef3c7; color:#d97706; font-size:10px; padding:2px 6px; border-radius:4px; margin-left:6px;">Rank 1</span>
                            </td>
                            <td>Fixed Amount (THB)</td>
                            <td>B2B / Corporate</td>
                            <td>312</td>
                            <td>฿1,310,400</td>
                        </tr>
                        <tr>
                            <td style="font-weight: 500;">Q2 VIP Upgrade Plan</td>
                            <td>Percentage (30%)</td>
                            <td>Existing VIPs</td>
                            <td>425</td>
                            <td>฿212,500</td>
                        </tr>
                        <tr>
                            <td style="font-weight: 500;">New Year Expo Returnees</td>
                            <td>Buy 1 Get 1 Free</td>
                            <td>New Customers</td>
                            <td>310</td>
                            <td>฿155,000</td>
                        </tr>
                        <tr>
                            <td style="font-weight: 500;">Trade-in Old Gadget 2026</td>
                            <td>Free Service</td>
                            <td>All Customers</td>
                            <td>120</td>
                            <td>฿105,000</td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </main>
</div>