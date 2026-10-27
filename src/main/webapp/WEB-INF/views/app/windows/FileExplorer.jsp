<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en" >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>แอปพลิเคชัน</title>
    <style>
       
.container_f {
    position: relative;
    width: 100%;
    background-color: #ffffff;
}

/** sidebar_f **************************
 * Base Settings
 ************************************/
#sidebar_f {
    float: left;
    width: 20%;
    padding: 10px;
    background-color: #212121;
    box-sizing: border-box;
    box-shadow: 2px 0px 2px rgba(0, 0, 0, 0.25);
    height: 600px;
    overflow-y: auto;
    z-index: 1;
}

/** sidebar_f **************************
 * Items
 ************************************/
#sidebar_f .title {
    font-size: 11px;
    font-weight: 600;
    margin-bottom: 8px;
    margin-top: 24px;
    color: #006ca2;
    letter-spacing: 0.75px;
}
#sidebar_f .title_top {
    font-size: 11px;
    font-weight: 600;
    margin-bottom: 8px;
    margin-top: 0px;
    color: #006ca2;
    letter-spacing: 0.75px;
}
#sidebar_f ul {
    list-style: none;
}
#sidebar_f li {
    margin-bottom: 3px;
    padding-top: 3px;
    padding-left: 6px;
    padding-bottom: 4px;
    font-size: 14px;
    cursor: pointer;
    -moz-transition: all 0.3s;
    -webkit-transition: all 0.3s;
    transition: all 0.3s;
}
#sidebar_f li i {
    margin-right: 10px;
    color: #456;
    width: 16px;
    height: auto;
    -moz-transition: all 0.3s;
    -webkit-transition: all 0.3s;
    transition: all 0.3s;
}
#sidebar_f li a {
    color: #456;
    text-decoration: none;
    -moz-transition: all 0.2s;
    -webkit-transition: all 0.2s;
    transition: all 0.2s;
}
#sidebar_f li.active {
    font-weight: 600;
    background-color: #006ca2;
    border-radius: 5px;
    -moz-transition: all 0.2s;
    -webkit-transition: all 0.2s;
    transition: all 0.2s;
}
#sidebar_f li.active:hover i,
#sidebar_f li.active:hover a,
#sidebar_f li.active i,
#sidebar_f li.active a {
    font-weight: 600;
    color: #fff;
    background-color: #006ca2;
    border-radius: 5px;
    -moz-transition: all 0.2s;
    -webkit-transition: all 0.2s;
    transition: all 0.2s;
}
#sidebar_f li:hover i,
#sidebar_f li:hover a {
    color: #006ca2;
}

/** toolbar_f **************************
 * Base Settings
 ************************************/
.toolbar_f {
    top: 0;
    left: 0;
    height: 35px;
    width: 100%;
    border-bottom: 1px solid #444;
    background-color: #171717;
    z-index: 1;
}

/** toolbar_f **************************
 * Bars
 ************************************/
.bar-main,
.bar-viewstyle {
    position: relative;
    float: left;
    padding: 0px;
    padding-right: 10px;
    margin-right: 10px;
    border-right: 1px solid #333;
}
.bar-viewstyle {
    display: flex;
    justify-content: flex-end;
    margin: 0px;
}

/** toolbar_f **************************
 * Buttons
 ************************************/
.standard-btn {
    border: 0;
    background-color: #171717;
    border-bottom: 2px solid #171717;
    color: #999;
    cursor: pointer;
    font-size: 16px;
    padding: 5px 5px;
    margin-left: 10px;
    outline: none;
}
.view-btn {
    background-color: #171717;
    border: none;
    color: #999;
    cursor: pointer;
    font-size: 16px;
    padding: 5px 5px;
    margin-left: 2px;
    outline: none;
}
.view-btn.active {
    color: #006ca2;
    border-bottom: 2px solid #006ca2;
}
.icons-view {
    display: flex;
    flex-wrap: wrap;
}
.icons-view li {
    width: 150px;
    margin: 25px;
    text-align: center;
}

/** FILES ****************************
 * Base Settings
 ************************************/
.main-content {
    float: left;
    width: 80%;
    padding: 0px;
    box-sizing: border-box;
    flex: 1;
    padding: 0px;
}
.main-content .files {
    position: relative;
    padding-left: 20px;
    padding: 0px;
    width: 100%;
    height: 565px;
    overflow-y: auto;
}
.main-content .files #files-list {
    position: relative;
    height: 100%;
    padding: 15px;
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    align-content: baseline;
    overflow-y: scroll;
    background-color: #fff;
}

/** header_f ***************************
 * Breadcrumb - location
 ************************************/
.header_f {
    position: relative;
    widht: 100%;
    background-color: #242424;
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.25);
    border-bottom: 1px solid #444;
    height: 35px;
    overflow: hidden;
    padding: 10px 10px;
    display: flex;
    flex-wrap: no-wrap;
}
.header_f span > p {
    position: relative;
    top: 0px;
    padding: 8px 0px 0px 8px;
    font-size: 13px;
    color: rgba(0, 0, 0, 0.7);
}
.header_f span {
    position: relative;
    padding-right: 5px;
    margin-left: 10px;
    top: -10px;
    background-color: #242424;
    color: rgba(0, 0, 0, 0.7);
    outline: none;
    text-decoration: none;
    transition: background 0.2s linear;
    height: 34px;
    width: fit-content;
}
.header_f span:after,
.header_f span:before {
    background: #242424;
    bottom: 0;
    clip-path: polygon(50% 50%, -50% -50%, 0 100%);
    content: "";
    left: 100%;
    position: absolute;
    top: 0px;
    transition: background 0.2s linear;
    width: 1em;
    z-index: 1;
}
.header_f span:before {
    background: #444;
    margin-left: 1px;
}
.header_f span:last-child {
    border-right: none;
}
.header_f span.active {
    height: 34px;
    margin-top: -0.15px;
}
.header_f span.active p {
    font-weight: 600;
    color: #006ca2;
}
.header_f i {
    color: #006ca2;
}

/** FILES ****************************
 * Files Settings
 ************************************/
.file_item {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    align-content: baseline;
    transition: background-color 0.3s ease;
    width: 80px;
    height: auto;
    margin-right: 8px;
    padding: 4px 42px;
    margin-bottom: 16px;
    border: 1px solid transparent;
    -webkit-user-select: contain;
    -moz-user-select: contain;
    -ms-user-select: contain;
    user-select: contain;
    overflow: hidden;
}
.file_item .file_icon {
    display: inline-block;
    position: relative;
    height: 64px;
    width: 64px;
}
.file_item .file_name {
    position: relative;
    top: 0px;
    font-size: 14px;
    text-align: center;
    white-space: no-wrap;
    word-break: keep-all;
    width: 90px;
    max-width: 90px;
    max-height: 36px;
    color: #000;
    text-decoration: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    text-overflow: ellipsis;
}
.file_item .file_size {
    font-size: 12px;
}
.file_item .file_type {
    font-size: 12px;
    margin-left: auto;
}

.file_item:select {
    background-color: #e1f2ff;
}
.file_item:hover {
    background-color: rgba(0, 0, 0, 0.15);
    border: 1px dotted rgba(0, 0, 0, 0.75);
    cursor: pointer;
}
.file_item.active {
    background-color: #e1f2ff;
}

/** FILES ****************************
 * View Settings
 ************************************/

/* Mega Icons */
.mega-icons li {
    display: inline-block;
    width: 25%;
    padding: 20px;
    text-align: center;
}
.mega-icons li img {
    width: 64px;
    height: 64px;
}

/* Big Icons */
.big-icons li {
    display: inline-block;
    width: 33.33%;
    padding: 20px;
    text-align: center;
}
.big-icons li img {
    width: 48px;
    height: 48px;
}

/* Normal Icons */
.normal-icons li {
    display: inline-block;
    width: 50%;
    padding: 20px;
    text-align: center;
}
.normal-icons li i {
    width: 32px;
    height: 32px;
}

/* Details */
.details table {
    width: 100%;
    border-collapse: collapse;
}
.details th,
.details td {
    padding: 10px;
    text-align: left;
}
.details th {
    border-bottom: 2px solid #ddd;
}
.details tr:hover {
    background-color: #f5f5f5;
}
.table-view {
    display: none;
    margin-top: 20px;
}
.icons-view li {
    width: 100px;
}
table {
    border-collapse: collapse;
    width: 100%;
}
table th,
table td {
    padding: 10px;
    text-align: left;
    border: 1px solid #ccc;
}
table th {
    font-weight: bold;
    background-color: #f5f5f5;
}
.table-view a {
    display: flex;
    align-items: center;
    width: 100%;
}
.table-view .file_icon {
    width: 60px;
    height: 20px;
}
.table-view .file_item i {
    width: 100%;
    height: 100%;
}

/** TOOLS ****************************
 * Modal
 ************************************/
.modal {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: none;
    background-color: rgba(0, 0, 0, 0.5); /* Black with opacity */
}
.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #fff;
    width: 300px; /* Could be more or less, depending on screen size */
    border-radius: 8px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
    position: relative; /* Used for positioning the close button */
}
.modal .close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
    position: absolute;
    top: 5px;
    right: 10px;
    cursor: pointer;
    transition: color 0.3s ease;
}
.modal .close:hover,
.modal .close:focus {
    color: #000;
}
.modal h2 {
    position: relative;
    font-size: 16px;
    font-weight: 500;
    margin-bottom: 10px;
    margin-top: -5px;
}
#error-message {
    color: red;
    margin-top: 10px;
    font-size: 14px;
}
.modal-show {
    animation: modal-show 0.4s ease-out;
    animation-fill-mode: forwards;
}
@keyframes modal-show {
    from {
        opacity: 0;
        transform: scale(0.8);
    }
    to {
        opacity: 1;
        transform: scale(1);
    }
}
.modal-hide {
    animation: modal-hide 0.4s ease-out;
    animation-fill-mode: forwards;
}
@keyframes modal-hide {
    from {
        opacity: 1;
        transform: scale(1);
    }
    to {
        opacity: 0;
        transform: scale(0.8);
    }
}

/** TOOLS ****************************
 * Create Folder
 ************************************/
#new-folder-form {
    display: flex;
    flex-direction: row;
}
#new-folder-name {
    padding: 8px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
    margin-right: 10px;
    outline: none;
    width: 270px;
}
#create-folder-btn {
    background-color: #005a87;
    color: white;
    font-size: 16px;
    border: none;
    border-radius: 4px;
    padding: 8px;
    cursor: pointer;
}
#create-folder-btn:hover {
    background-color: #006ca2;
}

/** TOOLS ****************************
 * File Uploader
 ************************************/
#file_upload_button {
    background-color: #1a73e8;
    border: none;
    border-radius: 4px;
    color: #fff;
    font-size: 14px;
    margin-top: 20px;
    padding: 10px;
    width: 100%;
}
#file_upload_input {
    display: none;
}
#file_upload_label {
    background-color: #fff;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 14px;
    padding: 10px;
    text-align: center;
    width: 100%;
}
#file_upload_label:hover {
    background-color: #f0f0f0;
    cursor: pointer;
}
#file_upload_label.selected {
    background-color: #e1f2ff;
    border: 1px solid #1a73e8;
    color: #1a73e8;
}

/* File Input */
#file-input-container_f {
    display: none;
}
#file-input-label {
    background-color: #3e3e3e;
    color: #fff;
    padding: 5px 10px;
    display: inline-block;
    cursor: pointer;
    border-radius: 5px;
    margin: 10px 0;
}
#file-input-label:hover {
    background-color: #555;
}
#file-input {
    display: none;
}

/** TOOLS ****************************
 * Tooltips
 ************************************/
.tooltip {
    position: relative;
    display: inline-block;
    cursor: pointer;
    font-size: 20px;
    margin-right: 10px;
}
.tooltip .tooltiptext {
    visibility: hidden;
    width: 120px;
    background-color: #555;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 5px 0;
    position: absolute;
    z-index: 1;
    bottom: 125%;
    left: 50%;
    margin-left: -60px;
    opacity: 0;
    transition: opacity 0.3s;
}
.tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 50%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #555 transparent transparent transparent;
}
.tooltip:hover .tooltiptext {
    visibility: visible;
    opacity: 1;
}

    </style>
</head>
<body >
  
    <div class="container_f">           
        <div class="toolbar_f">
            <div class="bar-main">
                <button id="back-btn" class="standard-btn" disabled  title="Go backwards in visited directories"> <i class="fa fa-chevron-left"></i></button>
                <button id="forward-btn" class="standard-btn" disabled  title="Go Forwards in visited directories"><i class="fa fa-chevron-right"></i></button>
                <button id="new-file-btn" class="standard-btn" title="Create new file"><i class="fa fa-file-circle-plus"></i></button>
                <button id="new-folder-btn" class="standard-btn" title="Create new folder" onClick="openModal('#new-folder-modal')"><i class="fa fa-folder-plus"></i></button>
            </div>
            <div class="bar-viewstyle">
                <button class="view-btn" data-view="icons" title=""><i class="fa fa-th-large"></i></button>
                <button class="view-btn active" data-view="table" title="Listview with Details"><i class="fa fa-table"></i></button>
            </div>
        </div>
    </div>
    <div id="sidebar_f" class="sidebar_f">
        <ul>
          <p class="title_top">LOCAL</p>
          <li class="active"><a href="#"><i class="fa-solid fa-home"></i>Home</a></li>
          <li class=""><a href="#"><i class="fa-solid fa-book-open"></i>Libraries</a></li>
          <li class=""><a href="#"><i class="fa-solid fa-server"></i>Shared Files</a></li>
        </ul>
        <ul>
        <p class="title">CLOUD DRIVES</p>
        <li class=""><a href="#"><i class="fa-brands fa-google-drive"></i>Google Drive</a></li>
        <li class=""><a href="#"><i class="fa-solid fa-apple-whole"></i>iCloud Drive</a></li>
          <li class=""><a href="#"><i class="fa-brands fa-windows"></i>OneCloud</a></li>
         <li class=""><a href="#"><i class="fa-brands fa-dropbox"></i>DropBox</a></li>
          <li class=""><a href="#"><i class="fa-solid fa-circle-plus"></i>Add Drive...</a></li>
        </ul>
     
    </div>
  
    <div id="main_content" class="main-content">
      <div class="header_f"><i class="fa-solid fa-folder"></i> 
          <span class="active"><p>Home</p></span>
          <span><p>...</p></span>
      </div>
        <div class="files">
            <ul id="files-list">
              <li class="file_item" >
                  <img class="file_icon" src="https://uas.edu.kw/wp-content/uploads/2018/12/folder-icon.png" alt="folder icon"><br>
                    <a class="file_name" href="#">Documents</a>
                </li>
              <li class="file_item" >
                  <img class="file_icon" src="https://uas.edu.kw/wp-content/uploads/2018/12/folder-icon.png" alt="folder icon"><br>
                    <a class="file_name" href="#">Files</a>
                </li>
              <li class="file_item" >
                  <img class="file_icon" src="https://uas.edu.kw/wp-content/uploads/2018/12/folder-icon.png" alt="folder icon"><br>
                    <a class="file_name" href="#">Music</a>
                </li>
              <li class="file_item" >
                  <img class="file_icon" src="https://uas.edu.kw/wp-content/uploads/2018/12/folder-icon.png" alt="folder icon"><br>
                    <a class="file_name" href="#">Pictures</a>
                </li>
              <li class="file_item" >
                  <img class="file_icon" src="https://uas.edu.kw/wp-content/uploads/2018/12/folder-icon.png" alt="folder icon"><br>
                    <a class="file_name" href="#">Videos</a>
                </li>
              <li class="file_item" >
                  <img class="file_icon" src="https://cdn1.iconfinder.com/data/icons/files-and-folders-vol-1-1/64/folder-cloud-512.png"><br>
                    <a class="file_name" href="#">Cloud Drives</a>
                </li>
              <li class="file_item" >
                  <img class="file_icon" src="https://pics.computerbase.de/9/2/3/0/1/logo-256.png"><br>
                    <a class="file_name" href="#">Adobe Cloud</a>
                </li>
              <li class="file_item" >
                  <img class="file_icon" src="https://aux.iconspalace.com/uploads/2027973063208346346.png" alt="folder icon"><br>
                    <a class="file_name" href="#">AppData</a>
                </li>
              <li class="file_item" >
                  <img class="file_icon" src="https://icons.iconseeker.com/png/fullsize/milkyway-blue/folder-backup.png" alt="folder icon"><br>
                    <a class="file_name" href="#">Backups</a>
                </li>
            </ul>
        </div>
        </div>
    <div id="new-folder-modal" class="modal">
        <div class="modal-content">
            <span class="close" onClick="closeModal('#new-folder-modal')">&times;</span>
            <h2>Create New Folder</h2>
            <form id="new-folder-form">
                <input type="text" id="new-folder-name" name="new-folder-name" placeholder="Enter folder name">
                <button type="submit" id="create-folder-btn">Create</button>
            </form>
        </div>
    </div>
</body>
</html>
 