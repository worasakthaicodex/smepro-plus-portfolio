<%-- app/ProjectPlus/_pp_style.jsp  — include ในทุกหน้า ProjectPlus --%>
<style>
/* =====================================================
   PROJECTPLUS — SCOPED CSS (ผูกกับ #pm-glass-app เท่านั้น)
   ===================================================== */
#pm-glass-app {
    --bg-color:       #0d0e15;
    --sidebar-bg:     rgba(20,20,30,0.6);
    --main-bg:        rgba(30,30,45,0.6);
    --card-bg:        rgba(45,45,65,0.4);
    --card-hover:     rgba(60,60,85,0.7);
    --text-main:      #ffffff;
    --text-muted:     #8b8b9e;
    --accent-blue:    #2d72d9;
    --accent-blue-h:  #1b5cbd;
    --accent-purple:  #8e44ad;
    --status-green:   #27ae60;
    --status-red:     #e74c3c;
    --status-orange:  #f39c12;
    --input-bg:       rgba(0,0,0,0.25);
    --input-border:   rgba(255,255,255,0.1);

    width:100%; height:100%;
    background-color: var(--bg-color);
    background-image:
        radial-gradient(circle at 15% 50%, rgba(142,68,173,0.15), transparent 25%),
        radial-gradient(circle at 85% 30%, rgba(45,114,217,0.15), transparent 25%);
    color: var(--text-main);
    font-family: 'Segoe UI', -apple-system, BlinkMacSystemFont, Roboto, sans-serif;
    font-size: 13px;
}
#pm-glass-app * { box-sizing: border-box; }
#pm-glass-app * { scrollbar-width: thin; scrollbar-color: rgba(255,255,255,0.2) transparent; }
#pm-glass-app ::-webkit-scrollbar { width:6px; height:6px; }
#pm-glass-app ::-webkit-scrollbar-track { background:rgba(0,0,0,0.1); border-radius:10px; }
#pm-glass-app ::-webkit-scrollbar-thumb { background:rgba(255,255,255,0.2); border-radius:10px; }
#pm-glass-app ::-webkit-scrollbar-thumb:hover { background:rgba(255,255,255,0.4); }

/* === App Window === */
#pm-glass-app #pp-window {
    width:100%; height:100%;
    background: var(--main-bg);
    backdrop-filter: blur(30px); -webkit-backdrop-filter: blur(30px);
    border-radius:12px; border:1px solid rgba(255,255,255,0.1);
    display:flex; overflow:hidden;
    box-shadow: 0 20px 50px rgba(0,0,0,0.5);
}

/* === Sidebar === */
#pm-glass-app .pp-sidebar {
    width:220px; min-width:220px;
    background: var(--sidebar-bg);
    border-right:1px solid rgba(255,255,255,0.05);
    display:flex; flex-direction:column;
    padding:15px 0; overflow-y:auto;
}
#pm-glass-app .pp-sidebar .nav-logo {
    padding:0 18px 18px 18px;
    font-size:13px; font-weight:700; color:var(--text-main);
    display:flex; align-items:center; gap:8px;
    border-bottom:1px solid rgba(255,255,255,0.05);
    margin-bottom:10px;
}
#pm-glass-app .pp-sidebar .nav-logo i { color:var(--accent-purple); font-size:16px; }
#pm-glass-app .pp-nav-section { margin-bottom:20px; }
#pm-glass-app .pp-nav-title {
    font-size:10px; color:var(--text-muted);
    text-transform:uppercase; letter-spacing:1px;
    padding:0 18px; margin-bottom:6px; font-weight:600;
}
#pm-glass-app .pp-nav-item {
    padding:9px 18px; cursor:pointer;
    display:flex; align-items:center; gap:12px;
    font-size:12px; color:var(--text-muted);
    transition:all 0.2s; border-left:3px solid transparent;
    text-decoration:none;
}
#pm-glass-app .pp-nav-item:hover { color:var(--text-main); background:rgba(255,255,255,0.05); }
#pm-glass-app .pp-nav-item.active {
    color:var(--text-main); background:rgba(255,255,255,0.05);
    border-left-color:var(--accent-purple); font-weight:600;
}
#pm-glass-app .pp-nav-item i { font-size:14px; width:18px; text-align:center; }
#pm-glass-app .pp-nav-badge {
    margin-left:auto; background:var(--status-orange);
    color:white; font-size:9px; padding:2px 6px;
    border-radius:10px; font-weight:bold;
}
#pm-glass-app .pp-nav-project-box {
    background:rgba(0,0,0,0.2); margin:0 10px 15px 10px;
    padding:8px; border-radius:8px;
}
#pm-glass-app .pp-nav-project-box .pp-nav-title { color:var(--accent-blue); }
#pm-glass-app .pp-nav-project-box .pp-nav-item { padding:5px 8px; font-size:11px; }

/* === Main Content === */
#pm-glass-app .pp-main { flex:1; display:flex; flex-direction:column; overflow:hidden; }

/* === Top Bar === */
#pm-glass-app .pp-topbar {
    height:54px; min-height:54px;
    display:flex; align-items:center; justify-content:space-between;
    padding:0 24px;
    border-bottom:1px solid rgba(255,255,255,0.05);
}
#pm-glass-app .pp-tabs { display:flex; gap:24px; height:100%; }
#pm-glass-app .pp-tab {
    display:flex; align-items:center;
    color:var(--text-muted); font-size:13px; cursor:pointer;
    border-bottom:2px solid transparent; transition:color 0.2s;
}
#pm-glass-app .pp-tab:hover { color:var(--text-main); }
#pm-glass-app .pp-tab.active { color:var(--text-main); font-weight:600; border-bottom-color:var(--text-main); }
#pm-glass-app .pp-topbar-right { display:flex; align-items:center; gap:16px; }
#pm-glass-app .pp-search {
    background:rgba(0,0,0,0.3); border:1px solid rgba(255,255,255,0.1);
    border-radius:6px; display:flex; align-items:center;
    padding:5px 10px; width:220px;
}
#pm-glass-app .pp-search i { color:var(--text-muted); font-size:11px; }
#pm-glass-app .pp-search input {
    background:transparent; border:none; outline:none;
    color:white; padding-left:8px; width:100%; font-size:12px; font-family:inherit;
}
#pm-glass-app .pp-avatar-btn {
    width:28px; height:28px; border-radius:50%;
    background:var(--accent-purple);
    display:flex; justify-content:center; align-items:center;
    font-size:11px; font-weight:bold; cursor:pointer;
}

/* === Project Header Bar (ใช้ใน Detail / Form pages) === */
#pm-glass-app .pp-proj-header {
    padding:16px 24px 0 24px;
    border-bottom:1px solid rgba(255,255,255,0.05);
    background:rgba(0,0,0,0.1);
}
#pm-glass-app .pp-breadcrumb {
    font-size:11px; color:var(--text-muted); margin-bottom:8px;
}
#pm-glass-app .pp-breadcrumb span + span::before { content:" / "; opacity:0.4; }
#pm-glass-app .pp-proj-title-row {
    display:flex; align-items:center; justify-content:space-between;
    margin-bottom:12px;
}
#pm-glass-app .pp-proj-title {
    font-size:16px; font-weight:700;
    display:flex; align-items:center; gap:10px;
}
#pm-glass-app .pp-inner-tabs { display:flex; gap:0; }
#pm-glass-app .pp-inner-tab {
    padding:10px 18px; cursor:pointer;
    font-size:12px; color:var(--text-muted);
    border-bottom:2px solid transparent; transition:0.2s;
}
#pm-glass-app .pp-inner-tab:hover { color:var(--text-main); }
#pm-glass-app .pp-inner-tab.active { color:var(--text-main); font-weight:600; border-bottom-color:var(--accent-blue); }

/* === Scroll Area === */
#pm-glass-app .pp-scroll { flex:1; overflow-y:auto; padding:20px 24px; }

/* === KPI Grid === */
#pm-glass-app .pp-kpi-grid { display:grid; grid-template-columns:repeat(4,1fr); gap:16px; margin-bottom:20px; }
#pm-glass-app .pp-kpi-card {
    background:rgba(0,0,0,0.2); border:1px solid rgba(255,255,255,0.05);
    border-radius:10px; padding:16px; position:relative; overflow:hidden;
}
#pm-glass-app .pp-kpi-card::before {
    content:''; position:absolute; top:-40%; right:-15%;
    width:80px; height:80px;
    background:radial-gradient(circle,var(--accent-purple),transparent 70%);
    opacity:0.1; filter:blur(15px);
}
#pm-glass-app .pp-kpi-title { font-size:11px; color:var(--text-muted); margin-bottom:8px; display:flex; justify-content:space-between; }
#pm-glass-app .pp-kpi-value { font-size:24px; font-weight:bold; }
#pm-glass-app .pp-kpi-trend { font-size:10px; margin-top:4px; }
#pm-glass-app .trend-up { color:var(--status-green); }
#pm-glass-app .trend-down { color:var(--status-red); }

/* === Section Header === */
#pm-glass-app .pp-section-hd {
    display:flex; justify-content:space-between; align-items:center;
    margin-bottom:14px;
}
#pm-glass-app .pp-section-title { font-size:13px; font-weight:600; }
#pm-glass-app .pp-hd-actions { display:flex; gap:8px; }

/* === Buttons === */
#pm-glass-app .pp-btn-outline {
    background:transparent; border:1px solid rgba(255,255,255,0.2);
    color:white; padding:6px 14px; border-radius:6px;
    font-size:11px; cursor:pointer; transition:0.2s; font-family:inherit;
}
#pm-glass-app .pp-btn-outline:hover { background:rgba(255,255,255,0.08); }
#pm-glass-app .pp-btn-primary {
    background:var(--accent-blue); border:none;
    color:white; padding:6px 14px; border-radius:6px;
    font-size:11px; cursor:pointer; transition:0.2s;
    font-weight:600; font-family:inherit;
}
#pm-glass-app .pp-btn-primary:hover { background:var(--accent-blue-h); }
#pm-glass-app .pp-btn-green {
    background:var(--status-green); border:none;
    color:white; padding:6px 14px; border-radius:6px;
    font-size:11px; cursor:pointer; font-weight:600; font-family:inherit;
}
#pm-glass-app .pp-btn-red {
    background:var(--status-red); border:none;
    color:white; padding:6px 14px; border-radius:6px;
    font-size:11px; cursor:pointer; font-weight:600; font-family:inherit;
}
#pm-glass-app .pp-btn-sm { padding:4px 10px; font-size:10px; }

/* === Status Badge === */
#pm-glass-app .pp-badge {
    padding:3px 10px; border-radius:20px;
    font-size:10px; font-weight:bold; text-transform:uppercase;
}
#pm-glass-app .pp-badge-green { background:rgba(39,174,96,0.15); color:var(--status-green); border:1px solid rgba(39,174,96,0.3); }
#pm-glass-app .pp-badge-orange { background:rgba(243,156,18,0.15); color:var(--status-orange); border:1px solid rgba(243,156,18,0.3); }
#pm-glass-app .pp-badge-red { background:rgba(231,76,60,0.15); color:var(--status-red); border:1px solid rgba(231,76,60,0.3); }
#pm-glass-app .pp-badge-blue { background:rgba(45,114,217,0.15); color:var(--accent-blue); border:1px solid rgba(45,114,217,0.3); }
#pm-glass-app .pp-badge-gray { background:rgba(255,255,255,0.08); color:#ccc; border:1px solid rgba(255,255,255,0.15); }

/* === Progress Bar === */
#pm-glass-app .pp-prog-wrap { width:100%; }
#pm-glass-app .pp-prog-labels { display:flex; justify-content:space-between; font-size:10px; margin-bottom:4px; color:var(--text-muted); }
#pm-glass-app .pp-prog-track { width:100%; height:5px; background:rgba(0,0,0,0.3); border-radius:3px; overflow:hidden; }
#pm-glass-app .pp-prog-fill { height:100%; border-radius:3px; background:var(--accent-blue); }

/* === Avatar === */
#pm-glass-app .pp-avatar-group { display:flex; }
#pm-glass-app .pp-avatar {
    width:26px; height:26px; border-radius:50%;
    border:2px solid var(--card-bg); background:#555;
    display:flex; justify-content:center; align-items:center;
    font-size:9px; font-weight:bold; margin-left:-7px; transition:0.2s;
}
#pm-glass-app .pp-avatar:first-child { margin-left:0; }
#pm-glass-app .pp-avatar:hover { transform:translateY(-3px); z-index:10; }

/* === Card === */
#pm-glass-app .pp-card {
    background:var(--card-bg); border:1px solid rgba(255,255,255,0.05);
    border-radius:10px; padding:16px;
}
#pm-glass-app .pp-card-title {
    font-size:12px; font-weight:600; color:var(--text-muted);
    margin-bottom:14px; display:flex; justify-content:space-between; align-items:center;
}

/* === Table === */
#pm-glass-app .pp-table { width:100%; border-collapse:collapse; }
#pm-glass-app .pp-table thead tr { border-bottom:1px solid rgba(255,255,255,0.08); }
#pm-glass-app .pp-table th {
    padding:8px 12px; text-align:left;
    font-size:10px; color:var(--text-muted);
    text-transform:uppercase; letter-spacing:0.5px; font-weight:600;
}
#pm-glass-app .pp-table td { padding:10px 12px; border-bottom:1px solid rgba(255,255,255,0.04); font-size:12px; }
#pm-glass-app .pp-table tbody tr { transition:background 0.15s; }
#pm-glass-app .pp-table tbody tr:hover { background:rgba(255,255,255,0.04); }
#pm-glass-app .pp-table tbody tr:last-child td { border-bottom:none; }

/* === Form === */
#pm-glass-app .pp-form-grid { display:grid; grid-template-columns:2fr 1fr; gap:16px; }
#pm-glass-app .pp-form-card {
    background:rgba(0,0,0,0.2); border:1px solid rgba(255,255,255,0.06);
    border-radius:10px; padding:18px; margin-bottom:14px;
}
#pm-glass-app .pp-form-card-title {
    font-size:12px; font-weight:600; margin-bottom:14px;
    display:flex; align-items:center; gap:8px; color:var(--text-muted);
}
#pm-glass-app .pp-form-row { display:grid; grid-template-columns:1fr 1fr; gap:12px; margin-bottom:12px; }
#pm-glass-app .pp-form-group { display:flex; flex-direction:column; gap:5px; }
#pm-glass-app .pp-form-group.full { grid-column:1/-1; }
#pm-glass-app .pp-form-group label { font-size:11px; color:var(--text-muted); font-weight:500; }
#pm-glass-app .pp-form-group label .req { color:var(--status-red); }
#pm-glass-app .pp-form-group input[type="text"],
#pm-glass-app .pp-form-group input[type="date"],
#pm-glass-app .pp-form-group input[type="number"],
#pm-glass-app .pp-form-group select,
#pm-glass-app .pp-form-group textarea {
    background:var(--input-bg); border:1px solid var(--input-border);
    color:white; padding:8px 12px; border-radius:7px;
    font-size:12px; font-family:inherit; outline:none; transition:border 0.2s;
}
#pm-glass-app .pp-form-group input:focus,
#pm-glass-app .pp-form-group select:focus,
#pm-glass-app .pp-form-group textarea:focus { border-color:var(--accent-blue); }
#pm-glass-app .pp-form-group select option { background:#1e1e2d; color:white; }
#pm-glass-app .pp-form-group textarea { resize:vertical; min-height:80px; }
#pm-glass-app .pp-input-group { display:flex; align-items:center; }
#pm-glass-app .pp-input-addon {
    background:rgba(255,255,255,0.05); border:1px solid var(--input-border);
    border-right:none; padding:8px 12px; border-radius:7px 0 0 7px;
    font-size:12px; color:var(--text-muted);
}
#pm-glass-app .pp-input-group input { border-radius:0 7px 7px 0; }
#pm-glass-app .pp-range { width:100%; accent-color:var(--accent-blue); cursor:pointer; }

/* === Loading bar === */
#pm-glass-app .pp-loading-bar {
    display:none; height:2px;
    background:linear-gradient(90deg,var(--accent-purple),var(--accent-blue));
    animation:ppSlide 1s infinite;
}
@keyframes ppSlide { 0%{margin-left:0;width:30%} 50%{margin-left:35%;width:30%} 100%{margin-left:70%;width:30%} }

/* === Tab Content (show/hide) === */
#pm-glass-app .pp-tab-content { display:none; }
#pm-glass-app .pp-tab-content.active { display:block; }
</style>
