<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modern ERP - Project Management</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
     /* =========================================
   MODERN GLASSMORPHISM THEME - SCOPED VERSION
   กำหนดขอบเขตให้อยู่แค่ใน #pm-glass-app เท่านั้น
   ========================================= */

/* ย้ายตัวแปรจาก :root มาไว้ที่ Container หลัก */
#pm-glass-app {
    --bg-color: #0d0e15;
    --sidebar-bg: rgba(20, 20, 30, 0.6);
    --main-bg: rgba(30, 30, 45, 0.6);
    --card-bg: rgba(45, 45, 65, 0.5);
    --card-hover: rgba(60, 60, 85, 0.8);
    --text-main: #ffffff;
    --text-muted: #8b8b9e;
    --accent-blue: #2d72d9;
    --accent-blue-hover: #1b5cbd;
    --accent-purple: #8e44ad;
    --status-green: #27ae60;
    --status-red: #e74c3c;
    --status-orange: #f39c12;
    --body-gradient: radial-gradient(circle at 15% 50%, rgba(142, 68, 173, 0.15), transparent 25%),
                     radial-gradient(circle at 85% 30%, rgba(45, 114, 217, 0.15), transparent 25%);

    height: 100%; 
    background-color: var(--bg-color);
    background-image: var(--body-gradient);
 
    justify-content: center; 
    align-items: center; 
    overflow: hidden;
    color: var(--text-main);
    font-family: 'Segoe UI', -apple-system, BlinkMacSystemFont, Roboto, sans-serif;
}

/* บังคับ box-sizing เฉพาะองค์ประกอบที่อยู่ข้างใน */
#pm-glass-app * { 
    box-sizing: border-box; 
}

/* =========================================
   CUSTOM SCROLLBAR (เฉพาะข้างในแอปนี้)
   ========================================= */
#pm-glass-app * {
    scrollbar-width: thin;
    scrollbar-color: rgba(255, 255, 255, 0.2) transparent;
}
#pm-glass-app ::-webkit-scrollbar { width: 6px; height: 6px; }
#pm-glass-app ::-webkit-scrollbar-track { background: rgba(0, 0, 0, 0.1); border-radius: 10px; }
#pm-glass-app ::-webkit-scrollbar-thumb { background: rgba(255, 255, 255, 0.2); border-radius: 10px; }
#pm-glass-app ::-webkit-scrollbar-thumb:hover { background: rgba(255, 255, 255, 0.4); }

/* =========================================
   THE APP WINDOW & COMPONENTS
   ========================================= */
#pm-glass-app #mac-app-window {
    width: 100%; 
    height:100%; 
    background: var(--main-bg);
    backdrop-filter: blur(30px);
    -webkit-backdrop-filter: blur(30px);
    border-radius: 12px;
    border: 1px solid rgba(255,255,255,0.1);
    display: flex; 
    overflow: hidden;
    box-shadow: 0 20px 50px rgba(0,0,0,0.5);
}

#pm-glass-app .sidebar { width: 250px; background: var(--sidebar-bg); border-right: 1px solid rgba(255,255,255,0.05); display: flex; flex-direction: column; padding: 15px 0; }
#pm-glass-app .window-controls { display: flex; gap: 8px; padding: 0 20px 20px 20px; }
#pm-glass-app .dot { width: 12px; height: 12px; border-radius: 50%; }
#pm-glass-app .dot-red { background: #ff5f56; }
#pm-glass-app .dot-yellow { background: #ffbd2e; }
#pm-glass-app .dot-green { background: #27c93f; }
#pm-glass-app .nav-section { margin-bottom: 25px; }
#pm-glass-app .nav-title { font-size: 11px; color: var(--text-muted); text-transform: uppercase; letter-spacing: 1px; padding: 0 20px; margin-bottom: 10px; font-weight: 600; }
#pm-glass-app .nav-item { padding: 10px 20px; cursor: pointer; display: flex; align-items: center; gap: 15px; font-size: 13px; color: var(--text-muted); transition: all 0.2s; border-left: 3px solid transparent; }
#pm-glass-app .nav-item:hover { color: var(--text-main); background: rgba(255,255,255,0.05); }
#pm-glass-app .nav-item.active { color: var(--text-main); background: rgba(255,255,255,0.05); border-left-color: var(--accent-purple); font-weight: 600; }
#pm-glass-app .nav-item i { font-size: 16px; width: 20px; text-align: center; }
#pm-glass-app .nav-badge { margin-left: auto; background: var(--status-orange); color: white; font-size: 10px; padding: 2px 8px; border-radius: 10px; font-weight: bold; }

#pm-glass-app .main-content { flex: 1; display: flex; flex-direction: column; overflow: hidden; }
#pm-glass-app .top-bar { height: 60px; display: flex; align-items: center; justify-content: space-between; padding: 0 30px; border-bottom: 1px solid rgba(255,255,255,0.05); }
#pm-glass-app .top-tabs { display: flex; gap: 30px; height: 100%; }
#pm-glass-app .top-tab { display: flex; align-items: center; color: var(--text-muted); font-size: 14px; cursor: pointer; border-bottom: 2px solid transparent; transition: color 0.2s; }
#pm-glass-app .top-tab:hover { color: var(--text-main); }
#pm-glass-app .top-tab.active { color: var(--text-main); font-weight: 600; border-bottom-color: var(--text-main); }
#pm-glass-app .top-right { display: flex; align-items: center; gap: 20px; }
#pm-glass-app .search-box { background: rgba(0,0,0,0.3); border: 1px solid rgba(255,255,255,0.1); border-radius: 6px; display: flex; align-items: center; padding: 6px 12px; width: 280px; }
#pm-glass-app .search-box i { color: var(--text-muted); font-size: 12px; }
#pm-glass-app .search-box input { background: transparent; border: none; outline: none; color: white; padding-left: 10px; width: 100%; font-size: 13px; font-family: inherit; }
#pm-glass-app .profile-pic { width: 30px; height: 30px; border-radius: 50%; background: var(--accent-purple); display: flex; justify-content: center; align-items: center; font-size: 12px; font-weight: bold; }

#pm-glass-app .content-scroll { flex: 1; overflow-y: auto; padding: 30px; }

#pm-glass-app .banner { background: linear-gradient(135deg, #8e44ad, #3498db, #2ecc71); border-radius: 12px; padding: 25px 30px; margin-bottom: 30px; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 10px 20px rgba(142, 68, 173, 0.2); position: relative; overflow: hidden; }
#pm-glass-app .banner::after { content: ''; position: absolute; top: 0; left: 0; right: 0; bottom: 0; background-image: radial-gradient(rgba(255,255,255,0.2) 1px, transparent 1px); background-size: 20px 20px; opacity: 0.5; pointer-events: none; }
#pm-glass-app .banner-text { position: relative; z-index: 2; max-width: 70%; }
#pm-glass-app .banner-title { font-size: 20px; font-weight: bold; margin-bottom: 10px; display: flex; align-items: center; gap: 10px; }
#pm-glass-app .banner-icon { background: rgba(0,0,0,0.2); padding: 5px 8px; border-radius: 6px; font-size: 14px; }
#pm-glass-app .banner-desc { font-size: 13px; line-height: 1.5; opacity: 0.9; margin-bottom: 15px; }
#pm-glass-app .banner-btn { background: rgba(255,255,255,0.2); border: 1px solid rgba(255,255,255,0.4); color: white; padding: 8px 20px; border-radius: 20px; cursor: pointer; font-size: 13px; font-weight: 600; backdrop-filter: blur(5px); transition: background 0.2s; font-family: inherit; }
#pm-glass-app .banner-btn:hover { background: rgba(255,255,255,0.3); }

#pm-glass-app .section-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px; }
#pm-glass-app .section-title { font-size: 14px; color: var(--text-muted); font-weight: 600; }
#pm-glass-app .filter-btn { background: transparent; border: 1px solid rgba(255,255,255,0.1); color: var(--text-muted); padding: 4px 10px; border-radius: 4px; font-size: 11px; cursor: pointer; font-family: inherit; }

#pm-glass-app .catalog-container { background: rgba(0,0,0,0.2); border-radius: 12px; border: 1px solid rgba(255,255,255,0.05); overflow: hidden; }
#pm-glass-app .catalog-item { display: grid; grid-template-columns: 50px 2.5fr 1.5fr 1.5fr 100px 40px; align-items: center; padding: 15px 20px; border-bottom: 1px solid rgba(255,255,255,0.05); transition: background 0.2s; }
#pm-glass-app .catalog-item:last-child { border-bottom: none; }
#pm-glass-app .catalog-item:hover { background: var(--card-hover); }

#pm-glass-app .item-icon { width: 36px; height: 36px; border-radius: 8px; display: flex; justify-content: center; align-items: center; font-size: 16px; color: white; }
#pm-glass-app .icon-dev { background: #2980b9; }
#pm-glass-app .icon-design { background: #e67e22; }
#pm-glass-app .icon-bug { background: #c0392b; }

#pm-glass-app .item-info { display: flex; flex-direction: column; gap: 4px; }
#pm-glass-app .item-code { font-size: 14px; font-weight: 600; color: var(--text-main); }
#pm-glass-app .item-desc { font-size: 11px; color: var(--text-muted); }
#pm-glass-app .wbs-badge { background: rgba(255,255,255,0.1); padding: 2px 6px; border-radius: 4px; font-size: 10px; color: #a9cce3; width: max-content; margin-top: 2px; }

#pm-glass-app .assignee { display: flex; align-items: center; gap: 8px; }
#pm-glass-app .assignee-pic { width: 24px; height: 24px; border-radius: 50%; background: #555; display: flex; justify-content: center; align-items: center; font-size: 10px; font-weight: bold; }
#pm-glass-app .assignee-name { font-size: 12px; color: var(--text-muted); }

#pm-glass-app .progress-wrapper { width: 80%; }
#pm-glass-app .progress-labels { display: flex; justify-content: space-between; font-size: 10px; margin-bottom: 4px; color: var(--text-muted); }
#pm-glass-app .progress-track { width: 100%; height: 6px; background: rgba(255,255,255,0.1); border-radius: 3px; overflow: hidden; }
#pm-glass-app .progress-fill { height: 100%; border-radius: 3px; }

#pm-glass-app .action-btn { background: transparent; border: 1px solid rgba(255,255,255,0.2); color: var(--text-main); padding: 6px 12px; border-radius: 15px; font-size: 11px; cursor: pointer; transition: all 0.2s; text-align: center; font-weight: 600; width: 100%; font-family: inherit; }
#pm-glass-app .action-btn:hover { background: rgba(255,255,255,0.1); }
#pm-glass-app .action-btn.primary { background: var(--accent-purple); border-color: var(--accent-purple); color: white; }
#pm-glass-app .action-btn.primary:hover { background: #732d91; }

#pm-glass-app .more-btn { color: var(--text-muted); cursor: pointer; text-align: center; }
#pm-glass-app .more-btn:hover { color: white; }
    </style>
</head>
<body>
<div id="pm-glass-app">
<div id="mac-app-window">
    
    <div class="sidebar">
  
        <div class="nav-section">
            <div class="nav-title">Workspace</div>
            <div class="nav-item" onclick="getData('/ProjectPlus-app/${appid}','microsofts${appid}')"><i class="fa fa-briefcase"></i> All Projects</div>
            <div class="nav-item active"><i class="fa fa-list-check"></i> My Tasks <span class="nav-badge">3</span></div>
            <div class="nav-item"><i class="fa fa-chart-gantt"></i> Gantt Timeline</div>
            <div class="nav-item"><i class="fa fa-kanban"></i> Kanban Board</div>
        </div>

        <div class="nav-section">
            <div class="nav-title">Planning & Control</div>
            <div class="nav-item"><i class="fa fa-sitemap"></i> WBS Elements</div>
            <div class="nav-item"><i class="fa fa-coins"></i> Budget & Costs</div>
            <div class="nav-item"><i class="fa fa-users-gear"></i> Resources</div>
            <div class="nav-item"><i class="fa fa-file-contract"></i> Contracts</div>
        </div>

        <div class="nav-section">
            <div class="nav-title">Reporting</div>
            <div class="nav-item"><i class="fa fa-chart-pie"></i> Dashboards</div>
            <div class="nav-item"><i class="fa fa-clock-rotate-left"></i> Timesheets</div>
        </div>
    </div>

    <div class="main-content">
        
        <div class="top-bar">
            <div class="top-tabs">
                <div class="top-tab active">Active Tasks</div>
                <div class="top-tab">Pending Review</div>
                <div class="top-tab">Milestones</div>
            </div>
            <div class="top-right">
                <div class="search-box">
                    <i class="fa fa-search"></i>
                    <input type="text" placeholder="Search Task, WBS, or Assignee...">
                </div>
                <i class="fa fa-bell" style="color:var(--text-muted); cursor:pointer;"></i>
   
            </div>
        </div>

        <div class="content-scroll">
            
            <div class="banner">
                <div class="banner-text">
                    <div class="banner-title">
                        <span class="banner-icon"><i class="fa fa-flag-checkered"></i></span>
                        Critical Milestone: Phase 1 Release
                    </div>
                    <div class="banner-desc">
                        Project: [PRJ-Alpha] ERP Transformation. 
                        The backend integration phase is due in 3 days. 2 blocking tasks remain. Please update status immediately.
                    </div>
                    <button class="banner-btn" onclick="logTime()">Log Work Hours</button>
                </div>
                <i class="fa fa-rocket" style="font-size: 80px; opacity: 0.2; z-index: 2; position: absolute; right: 30px; bottom: -10px;"></i>
            </div>

            <div class="section-header">
                <div class="section-title">My Pending Tasks (Sprint 12)</div>
                <button class="filter-btn"><i class="fa fa-filter"></i> Filter</button>
            </div>
            
            <div class="catalog-container">
                
                <div class="catalog-item" id="task-1">
                    <div class="item-icon icon-dev"><i class="fa fa-code"></i></div>
                    <div class="item-info">
                        <span class="item-code">TSK-8042: API Integration</span>
                        <span class="item-desc">Integrate Payment Gateway with Finance Module</span>
                        <span class="wbs-badge">WBS: PRJ-A-01.03</span>
                    </div>
                    <div class="assignee">
                        <div class="assignee-pic" style="background: #3498db;">W1</div>
                        <span class="assignee-name">You</span>
                    </div>
                    <div class="progress-wrapper">
                        <div class="progress-labels"><span>In Progress</span><span>60%</span></div>
                        <div class="progress-track">
                            <div class="progress-fill" style="width: 60%; background: var(--status-orange);"></div>
                        </div>
                    </div>
                    <div>
                        <button class="action-btn primary" id="btn-1" onclick="updateTask(1)">Update</button>
                    </div>
                    <div class="more-btn"><i class="fa fa-ellipsis"></i></div>
                </div>

                <div class="catalog-item" id="task-2">
                    <div class="item-icon icon-design"><i class="fa fa-pen-nib"></i></div>
                    <div class="item-info">
                        <span class="item-code">TSK-8045: UI Mockups</span>
                        <span class="item-desc">Design Dashboard screens for HR Module</span>
                        <span class="wbs-badge">WBS: PRJ-A-02.01</span>
                    </div>
                    <div class="assignee">
                        <div class="assignee-pic" style="background: #e74c3c;">JS</div>
                        <span class="assignee-name">Jane S.</span>
                    </div>
                    <div class="progress-wrapper">
                        <div class="progress-labels"><span>Completed</span><span>100%</span></div>
                        <div class="progress-track">
                            <div class="progress-fill" style="width: 100%; background: var(--status-green);"></div>
                        </div>
                    </div>
                    <div>
                        <button class="action-btn" id="btn-2" style="border-color:var(--status-green); color:var(--status-green);">Review</button>
                    </div>
                    <div class="more-btn"><i class="fa fa-ellipsis"></i></div>
                </div>

                <div class="catalog-item" id="task-3">
                    <div class="item-icon icon-bug"><i class="fa fa-bug"></i></div>
                    <div class="item-info">
                        <span class="item-code">BUG-102: Data Sync Issue</span>
                        <span class="item-desc">User profile sync failing on mobile endpoints</span>
                        <span class="wbs-badge">WBS: PRJ-A-01.99</span>
                    </div>
                    <div class="assignee">
                        <div class="assignee-pic" style="background: #3498db;">W1</div>
                        <span class="assignee-name">You</span>
                    </div>
                    <div class="progress-wrapper">
                        <div class="progress-labels"><span style="color:var(--status-red);">Not Started</span><span>0%</span></div>
                        <div class="progress-track">
                            <div class="progress-fill" style="width: 0%; background: var(--status-red);"></div>
                        </div>
                    </div>
                    <div>
                        <button class="action-btn" id="btn-3" onclick="updateTask(3)" style="border-color:var(--status-red); color:var(--status-red);">Start Task</button>
                    </div>
                    <div class="more-btn"><i class="fa fa-ellipsis"></i></div>
                </div>

            </div>

            <div class="section-header" style="margin-top: 30px;">
                <div class="section-title">Recent Project Announcements</div>
            </div>
            <div class="catalog-container" style="opacity: 0.8;">
                <div class="catalog-item" style="grid-template-columns: 50px 1fr 100px;">
                    <div class="item-icon" style="background: rgba(255,255,255,0.1);"><i class="fa fa-bullhorn text-muted"></i></div>
                    <div class="item-info">
                        <span class="item-code">Q3 Budget Approved</span>
                        <span class="item-desc">The steering committee has approved additional resources for Phase 2. Check WBS budgeting.</span>
                    </div>
                    <div class="assignee-name" style="text-align:right;">2 hours ago</div>
                </div>
            </div>

        </div>
    </div>
</div>
</div>

<script>
    function logTime() {
        let btn = document.querySelector('.banner-btn');
        btn.innerText = "Opening Timesheet...";
        btn.style.background = "rgba(255,255,255,0.4)";
        setTimeout(() => {
            btn.innerHTML = '<i class="fa fa-check"></i> Draft Created';
        }, 1000);
    }

    function updateTask(id) {
        let btn = document.getElementById('btn-' + id);
        btn.className = 'action-btn';
        btn.style.background = 'rgba(39, 174, 96, 0.2)'; 
        btn.style.borderColor = 'var(--status-green)';
        btn.style.color = 'var(--status-green)';
        btn.innerHTML = '<i class="fa fa-check"></i> Done';
        btn.disabled = true;
    }
</script>

</body>
</html>