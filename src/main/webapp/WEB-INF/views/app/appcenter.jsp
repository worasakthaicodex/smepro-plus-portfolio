<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html >
    <head>
        <title> WebOs</title>

<link rel="icon" href="data:,">
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Ubuntu'>
        <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css'>
        <link rel='stylesheet' href='https://cdn.rawgit.com/nizarmah/calendar-javascript-lib/master/calendarorganizer.min.css'>
        <script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
             
             
             <link href="<c:url value="/app/css/style.css" />" rel="stylesheet">
           <link href="<c:url value="/app/css/nav.css" />" rel="stylesheet">
            <link href="<c:url value="/app/css/app.css" />" rel="stylesheet">
          <script src="https://unpkg.com/feather-icons"></script>
    <link href="<c:url value="/app/css/tag.css" />" rel="stylesheet">

       
              <link href="<c:url value="/app/css/input.css" />" rel="stylesheet">
              
              
              <link href="https://cdn.jsdelivr.net/npm/tom-select@2.2.2/dist/css/tom-select.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/tom-select@2.2.2/dist/js/tom-select.complete.min.js"></script>
              
              
              
  <style>
   
        /* --- Wrapper หลัก (ขวาบน) --- */
        .assistant-wrapper {
            position: fixed;
            top: 25px;
            right: 25px;
            z-index: 2; /* อยู่บนสุดเสมอ */
            display: flex;
            flex-direction: column;
            align-items: flex-end;
            pointer-events: none; /* เพื่อไม่ให้บังการคลิกปุ่มอื่นในหน้าจอ */
        }

        /* --- 1. Manga Bubble (กล่องข้อความ) --- */
        .manga-bubble {
            background: #fff;
            border: 3px solid #000;
            border-radius: 12px;
            padding: 15px 20px;
            min-width: 180px;
            max-width: 260px;
            text-align: center;
            font-weight: 700;
            font-size: 15px;
            color: #333;
            margin-bottom: 10px;
            position: relative;
            box-shadow: 6px 6px 0 rgba(0,0,0,0.15);
            pointer-events: auto; /* ให้เมาส์กดที่กล่องได้ */
            
            /* เริ่มต้นซ่อนไว้ */
            opacity: 0;
            transform: scale(0.8) translateY(15px);
            transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
        }

        /* หาง Bubble */
        .manga-bubble::after {
            content: '';
            position: absolute;
            bottom: -13px;
            right: 35px;
            border-width: 13px 10px 0;
            border-style: solid;
            border-color: #000 transparent transparent transparent;
        }
        .manga-bubble::before {
            content: '';
            position: absolute;
            bottom: -8px;
            right: 38px;
            border-width: 10px 7px 0;
            border-style: solid;
            border-color: #fff transparent transparent transparent;
            z-index: 1;
        }

        .manga-bubble.show {
            opacity: 1;
            transform: scale(1) translateY(0);
        }

        /* สไตล์แจ้งเตือนด่วน (สีเหลือง) */
        .manga-bubble.alert {
            background: #fff9c4;
            border-color: #ff6f00;
            color: #e65100;
        }
        .manga-bubble.alert::after { border-top-color: #ff6f00; }
        .manga-bubble.alert::before { border-top-color: #fff9c4; }

        /* --- 2. Avatar Emoji --- */
        .avatar-box {
            width: 80px;
            height: 80px;
            font-size: 65px;
            display: flex;
            justify-content: center;
            align-items: center;
            cursor: pointer;
            pointer-events: auto;
            filter: drop-shadow(0 4px 6px rgba(0,0,0,0.1));
            transition: transform 0.2s;
            animation: float 4s ease-in-out infinite;
        }

        .avatar-box:hover { transform: scale(1.1); }
        .avatar-box:active { transform: scale(0.95); }

        /* --- 3. Menu --- */
        .manga-menu {
            position: absolute;
            top: 100%;
            right: 10px;
            margin-top: 5px;
            width: 500px;
            background: #fff;
            border: 3px solid #000;
            border-radius: 10px;
            box-shadow: 6px 6px 0 rgba(0,0,0,0.15);
            overflow: hidden;
            pointer-events: auto;
            
            visibility: hidden;
            opacity: 0;
            transform: translateY(-10px);
            transition: all 0.2s ease;
        }

        .manga-menu.open {
            visibility: visible;
            opacity: 1;
            transform: translateY(0);
        }

        .menu-item {
            padding: 12px 15px;
            border-bottom: 1px solid #eee;
            font-weight: 700;
            color: #333;
            cursor: pointer;
            display: flex;
            align-items: center;
            gap: 10px;
            transition: background 0.1s;
        }
        .menu-item:last-child { border-bottom: none; }
        .menu-item:hover { background: #ffe082; }

        @keyframes float {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-5px); }
        }

    </style>
<style>


body {
    user-select: none;
}

body {

  background-size: cover;
  background-position: center;
}

</style>
<style>
 

    
.bar {
  width: 100%;
  height: 6px;
  background: #333;
  margin: auto;
  position: relative;
  box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.5);
  overflow: hidden;
  display:none;
}

.bar:before {
  content: " ";
  top: 0px;
  left: 0px;
  position: absolute;
  width: 100px;
  height: 100%;
  background: #465;
  animation: load 2s infinite;
}

@keyframes load {
  0% {
    /* จุดเริ่มต้น: อยู่นอกขอบซ้าย */
    left: -100px; 
  }
  100% {
    /* จุดที่ 2: แก้ตรงนี้! */
    /* วิ่งไปที่ 100% ของความกว้างกล่องแม่ (สุดขอบขวา) */
    left: 100%; 
  }
} 
  
.sorted{

   cursor: pointer;
}

   
 




/*
Made by fhnb16 in 2021
CC BY-SA
*/

:root {
            --blur-radius: 6px;
            --window-radius: 8px;
            --top_menu-height: 22px;
            --bottom_menu-height: 40px;
            --bg-color: 14, 14, 16;
            --secondary-color: 0, 0, 0;
            --font-color: 225, 225, 227;
            --font-color-menu: 225, 225, 227;
            --font-shadow-color: 0, 0, 0, 64%;
            --accent-color: 21, 96, 147;
            --accent-opacity: 32%;
            --content-opacity: 64%;
            --secondary-opacity: 2%;
            --scrollbar-color-default: 255, 255, 255, 12%;
            --scrollbar-color-hover: 255, 255, 255, 20%;
            --main-bg-image: url('https://sun9-73.userapi.com/impf/SYsOe4HIKgA2HOU37hZo3zPZ6kr_IErWoNfzcg/Vqv1IdvbV14.jpg?size=1280x853&quality=96&sign=40e3b9590f011f1ca77afc02c852b33f&type=album');
            --main-bg-image: url('https://512pixels.net/downloads/macos-wallpapers-thumbs/10-14-Night-Thumb.jpg');
            --main-bg-image: url('https://i.ibb.co/8d9rw7n/Windows-11-Default-Dark-Wallpaper.jpg');
            --custom-blur-radius: 6px;
            --custom-main-bg-image: black;
            --custom-accent-color: 158, 0, 0;
            --custom-accent-opacity: 64%;

        }

        /*https://sun9-73.userapi.com/impf/SYsOe4HIKgA2HOU37hZo3zPZ6kr_IErWoNfzcg/Vqv1IdvbV14.jpg?size=1280x853&quality=96&sign=40e3b9590f011f1ca77afc02c852b33f&type=album*/
        .light_theme {
            --blur-radius: 2px;
            --window-radius: 4px;
            --top_menu-height: 22px;
            --bottom_menu-height: 40px;
            --bg-color: 255, 255, 255;
            --secondary-color: 255, 255, 255;
            --font-color: 14, 14, 16;
            --font-color-menu: 225, 225, 227;
            --font-shadow-color: 150, 150, 150, 80%;
            --accent-opacity: 64%;
            --content-opacity: 64%;
            --secondary-opacity: 80%;
            --scrollbar-color-default: 0, 0, 0, 12%;
            --scrollbar-color-hover: 0, 0, 0, 20%;
            --main-bg-image: url('https://512pixels.net/downloads/macos-wallpapers-thumbs/10-14-Day-Thumb.jpg');
            --main-bg-image: url('https://i0.wp.com/itc.ua/wp-content/uploads/2021/06/download-the-official-windows-11-wallpaper-533232-2.jpg');

        }

        /*https://sun9-73.userapi.com/impf/SYsOe4HIKgA2HOU37hZo3zPZ6kr_IErWoNfzcg/Vqv1IdvbV14.jpg?size=1280x853&quality=96&sign=40e3b9590f011f1ca77afc02c852b33f&type=album*/
        .custom_bg {
            --main-bg-image: var(--custom-main-bg-image)!important;
        }
        
        .custom_blur{
            --blur-radius: var(--custom-blur-radius)!important;
        }
        
        .custom_accent{
            --accent-color: var(--custom-accent-color)!important;
            --accent-opacity: var(--custom-accent-opacity)!important;
        }

        * {
            box-sizing: border-box;
            margin: 0px;
            outline: inherit;
            text-decoration: none;
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -khtml-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            -webkit-user-drag: none;
            -khtml-user-drag: none;
            -moz-user-drag: none;
            -o-user-drag: none;
            user-drag: none;
        }
        
        *, ::after, ::before {
            box-sizing: border-box;
        }

      

        html,
        body {
            height: 100%;
        }

        body {
            overflow: hidden;
            font-family: -apple-system, BlinkMacSystemFont, 'Roboto', 'Helvetica Neue', Geneva, "Noto Sans Armenian", "Noto Sans Bengali", "Noto Sans Cherokee", "Noto Sans Devanagari", "Noto Sans Ethiopic", "Noto Sans Georgian", "Noto Sans Hebrew", "Noto Sans Kannada", "Noto Sans Khmer", "Noto Sans Lao", "Noto Sans Osmanya", "Noto Sans Tamil", "Noto Sans Telugu", "Noto Sans Thai", sans-serif, arial, Tahoma, verdana;


        }


        
        body.devMode .devFunc{
            display:block;
        }

        .top_menu {
            position: fixed;
            height: var(--top_menu-height);
            top: 0px;
            z-index: 10;
            left: 0px;
            right: 0px;
            border-bottom: 1px solid #80808024;
            border-radius: 0 0 2px 2px;
        }

        .top_menu>.left {
            position: fixed;
            left: 0px;
            top: 0px;
            bottom: 0px;
        }

        .top_menu>.left>ul {
            display: flex;
            align-items: center;
            list-style-type: none;
            overflow: overlay;
        }

        .top_menu>.left>ul>li {
            margin: 1px 3px;
            padding: 0px 3px;
            height: 20px;
            border-radius: 4px;
            background-color: #ffffff08;
            flex: 0 1 auto;
            z-index: 11;
            transition: all 0.3s cubic-bezier(.25, .8, .25, 1);

        }

        .top_menu>.left>ul>li:hover {
            background-color: #ffffff24;
            transform: translateY(0px);

        }

        .top_menu>.right {
            position: fixed;
            right: 0px;
            top: 0px;
            bottom: 0px;
        }

        .top_menu>.right>ul {
            display: flex;
            align-items: center;
            list-style-type: none;
            overflow: overlay;
            justify-content: flex-end;
        }

        .top_menu>.right>ul>li {
            margin: 1px 3px;
            padding: 0px 3px;
            height: 20px;
            border-radius: 4px;
            background-color: #ffffff08;
            flex: 0 1 auto;
            z-index: 12;
            transition: all 0.3s cubic-bezier(.25, .8, .25, 1);

        }

        .top_menu>.right>ul>li>svg,.top_menu>.right>ul>li>svg>path {
            z-index: 11;

        }

        .top_menu>.right>ul>li:hover {
            background-color: #ffffff24;
            transform: translateY(0px);

        }
        svg, img, path{
            
    pointer-events: none;
        }

        .bottom_menu {
            position: fixed;
            height: var(--bottom_menu-height);
            bottom: 0px;
            z-index:7;
            left: 0px;
            right: 0px;
            border-top: 1px solid #80808024;
            border-radius: 2px 2px 0 0;
            /*text-transform: uppercase;*/
            /*
    bottom:6px;
    left:8px;
    right:8px;
    border-radius:8px;
    */
        }

        .bottom_menu>div {
            left: 0px;
            right: 0px;
            top: 0px;
            bottom: 0px;

        }

        .bottom_menu>div>ul {
            display: flex;
            align-items: center;
            align-content: center;
            list-style-type: none;
            overflow-x: overlay;
            overflow-y: hidden;
        }

        .bottom_menu>div>ul>li {
            position: relative;
            margin: 4px;
            padding: 4px;
            height: 32px;
            font-weight: 500 !important;
            flex: 0 1 auto;
            z-index: 8;
            transition: transform 0.3s cubic-bezier(.25, .8, .25, 1), background 0.3s ease;
            text-align: center;
            border-radius: 4px;
            /*background-color:#ffffff08;*/
            transform: translateY(0px) scale(1) translateZ(0);
            display: flex!important;
            align-items: center;
            align-content: center;
            border-bottom: 3px solid transparent;
            border-bottom: 3px solid transparent;
        }

        .bottom_menu>div>ul>li>span,
        .header>span {
            position: relative;
            flex: 0 1 auto;
            margin: auto 4px;
            z-index: 9;
            white-space: nowrap;

        }

        .bottom_menu>div>ul>.active {

            border: 1px solid transparent;
            /*border-bottom: 3px solid rgb(var(--accent-color));*/
            border-bottom: 3px solid transparent;
            backdrop-filter: saturate(80%);
        }

        .bottom_menu>div>ul>li:after {


            content: "";
            position: absolute;
            background-color: transparent;
            height: 3px;
            height: width;
            left: 32%;
            right: 32%;
            bottom: 2px;
            border-radius: 2px;

        }

        .bottom_menu>div>ul>.active:before {


            content: "";
            position: absolute;
            background-color: rgba(var(--accent-color), 85%);
            height: 3px;
            height: width;
            left: 2%;
            right: 2%;
            bottom: -2px;
            border-radius: 2px;

        }

        .bottom_menu>div>ul>.active:hover:before {


            content: "";
            position: absolute;
            background-color: rgba(var(--accent-color), 100%);
            height: 3px;
            height: width;
            left: 2%;
            right: 2%;
            bottom: -2px;
            border-radius: 2px;

        }

        .bottom_menu>div>ul>.active:active:before {


            content: "";
            position: absolute;
            background-color: rgba(var(--accent-color), 64%);
            height: 3px;
            height: width;
            left: 2%;
            right: 2%;
            bottom: -2px;
            border-radius: 2px;

        }

        .bottom_menu>div>ul>.active:hover {

            border: 1px solid #ffffff08;
            /*border-bottom: 3px solid rgb(var(--accent-color));*/
            border-bottom: 3px solid transparent;
            background-color: #ffffff16;

        }

        .bottom_menu>div>ul>.active:active {

            border: 1px solid #ffffff16;
            /*border-bottom: 3px solid rgb(var(--accent-color));*/
            border-bottom: 3px solid transparent;
            background-color: #ffffff24;

        }

        .bottom_menu>div>ul>.min {

            border: 1px solid transparent;
            /*border-bottom: 3px solid rgb(var(--accent-color));*/
            border-bottom: 3px solid transparent;
            backdrop-filter: saturate(-60%);
            transition: all 0.6s cubic-bezier(.25, .8, .25, 1);

        }

        .bottom_menu>div>ul>.min:hover {

            border: 1px solid #ffffff08;
            /*border-bottom: 3px solid rgb(var(--accent-color));*/
            border-bottom: 3px solid transparent;
            background-color: #ffffff16;

        }

        .bottom_menu>div>ul>.min:active {

            border: 1px solid #ffffff16;
            /*border-bottom: 3px solid rgb(var(--accent-color));*/
            border-bottom: 3px solid transparent;
            background-color: #ffffff24;

        }

        .bottom_menu>div>ul>.min:before {


            content: "";
            position: absolute;
            background-color: rgba(var(--accent-color), 60%);
            height: 3px;
            height: width;
            left: 2%;
            right: 2%;
            bottom: -2px;
            border-radius: 2px;

        }

        .bottom_menu>div>ul>.min:hover:before {


            content: "";
            position: absolute;
            background-color: rgba(var(--accent-color), 80%);
            height: 3px;
            height: width;
            left: 2%;
            right: 2%;
            bottom: -2px;
            border-radius: 2px;

        }

        .bottom_menu>div>ul>.min:active:before {


            content: "";
            position: absolute;
            background-color: rgba(var(--accent-color), 90%);
            height: 3px;
            height: width;
            left: 2%;
            right: 2%;
            bottom: -2px;
            border-radius: 2px;

        }

        .bottom_menu>div>ul>li:hover {
            backface-visibility: hidden;
            transform: translateY(-1px) translateZ(0);
            background-color: #ffffff24;

        }

        .bottom_menu>div>ul>li:hover img {
            transition: transform 0.3s cubic-bezier(.25, .8, .25, 1), background 0.3s ease;
            transform: translateY(0px) scale(1.12) translateZ(0);

        }

        .bottom_menu>div>ul>li:active {
            background-color: #00000016;

        }

        .bottom_menu>div>ul>li:first-child {
            margin-left: auto;
        }

        .bottom_menu>div>ul>li:last-child {
            margin-right: auto;
        }

        .bottom_menu.compact>div>ul>li>span {
            display: none;
        }

        .blur_layer {
            background-color: rgba(var(--accent-color), var(--accent-opacity));
            /*--bg-color*/
            backdrop-filter: blur(var(--blur-radius)) saturate(80%);
            /*text-shadow: 0px 0px 12px rgba(var(--bg-color),60%),0px 0px 6px rgba(var(--bg-color), 80%),0px 0px 3px rgba(var(--bg-color), 90%);*/
            text-shadow: 0px 0px 12px rgba(var(--bg-color),100%),0px 0px 2px rgba(var(--bg-color),60%),0px 0px 12px rgba(var(--bg-color), 80%),1px 1px 2px rgba(var(--bg-color), 100%);
            color: rgb(var(--font-color));
        }

        .blur_layer:before {
            content: '';
            background: rgba(var(--secondary-color), var(--secondary-opacity));
            /*position: absolute;*/
            position:relative;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
        }

        .blur_layer svg>path, .window svg>path {
            fill: rgb(var(--font-color));
        }
        .grid > button > svg > path {
            fill: rgb(var(--font-color));
        }

        .blur_layer svg,
        .blur_layer svg,
        .blur_layer img,
        .grid > button > svg{
            filter: drop-shadow(0px 0px 2px rgba(var(--bg-color),30%)) drop-shadow(0px 0px 12px rgba(var(--bg-color), 60%)) drop-shadow(1px 1px 2px rgba(var(--bg-color), 90%));
            fill:rgb(var(--font-color));
        }

        .accentBtn > svg{
            filter: drop-shadow(0px 0px 6px rgba(var(--font-color),100%))!important;
        }

  
    
       

        .subMenu,
        .revCtxMenu,
        .ctxMenu {
            cursor: pointer;
            position: absolute;
            border-radius: var(--window-radius);
            width: 150px;
            height: 115px;
            animation-duration: 0.2s;
            animation-iteration-count: 1;
            transition: 0.2s;
            z-index: 6;
            /*background-color: rgba(var(--secondary-color), 32%);
            color: rgba(var(--font-color), 60%);
            backdrop-filter: blur(16px);*/
            display: flex;
            flex-direction: column;
            color: #000;
            position: absolute;
            top: 30%;
            left: 50%;
            border-radius: var(--window-radius);
            padding-top: 5px;
            padding-bottom: 5px;
            min-width: 220px;
            z-index: 8;
            box-shadow: 0 2.8px 2.2px rgb(0 0 0 / 3%), 0 6.7px 5.3px rgb(0 0 0 / 4%), 0 12.5px 10px rgb(0 0 0 / 6%), 0 22.3px 17.9px rgb(0 0 0 / 7%), 0 41.8px 33.4px rgb(0 0 0 / 8%), 0 100px 80px rgb(0 0 0 / 11%);
            animation: .3s _Pop_58yyh_1;
            animation-timing-function: cubic-bezier(0, 1, .5, 1.08);
            color: rgb(var(--font-color));
        }

        .subMenu li,
        .revCtxMenu li,
        .ctxMenu li {
            position: relative;
            display: block;
            vertical-align: middle;
            list-style-type: none;
            padding: 3px 10px;
            padding-left: 10px;
            padding-right: 10px;
            font-size: 12px;
            margin: 1px;
            margin-left: 5px;
            margin-right: 5px;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
            border-radius: 5px;
            cursor: pointer;
            transition: .1s all ease-in;
        }

        #menu {
            border-radius: var(--window-radius);
        }

        .subMenu {
            border-radius: 2px;
            padding-left: 6px;
            height: 95px !important;
            background-color: rgba(var(--secondary-color), 80%) !important;
            backdrop-filter: blur(16px) !important;
            color: rgb(var(--font-color));
        }

        .subMenu li:hover,
        .revCtxMenu li:hover,
        .ctxMenu li:hover {
            color: rgb(var(--font-color));
            background-color: rgba(var(--secondary-color), 32%);
        }

        .subMenu li#frtItem,
        .revCtxMenu li#frtItem,
        .ctxMenu li#frtItem {}

        .subMenu li#frtItem:hover,
        .revCtxMenu li#frtItem:hover,
        .ctxMenu li#frtItem:hover {}

        .subMenu li:before,
        .revCtxMenu li:before,
        .ctxMenu li:before {
            position: absolute;
            visibility: hidden;
            width: 4px;
            height: 36px;
            top: 2px;
            left: 0px;
            content: "";
        }

        .subMenu li#frtItem:after,
        .revCtxMenu li#frtItem:after,
        .ctxMenu li#frtItem:after {
            position: inherit;
            right: -2%;
            display: inline-block;
            border-top: 1px solid #a9a9a9;
            border-right: 1px solid #a9a9a9;
            transform: rotate(54deg) skew(20deg);
            font-size: 20px;
            width: 8px;
            height: 8px;
            content: "";
        }

        .subMenu li:hover:before,
        .revCtxMenu li:hover:before,
        .ctxMenu li:hover:before {
            visibility: visible;
        }

        .subMenu:hover,
        .revCtxMenu:hover,
        .ctxMenu:hover {}

        .ctxMenu ul {
            margin: 0;
            padding: 0;
            text-align: center;
        }

        .ctxMenu {
            animation-name: appear;
        }

        .revCtxMenu {
            animation-name: revappear;
        }

        .subMenu {
            height: 160px;
            display: none;
            border-radius: var(--window-radius);
        }

        .subMenu:hover {
            display: block;
        }

        @keyframes appear {
            0% {
                transform: scale(0.6) translateX(-100px);
            }

            100% {
                transform: scale(1) translateX(0px);
            }
        }

        @keyframes revappear {
            0% {
                transform: scale(0.6) translateX(100px);
            }

            100% {
                transform: scale(1) translateX(0px);
            }
        }

        /*label {
            padding: 6px;
            width:100%;
        }

        label:after {
            content: '\a';
            white-space: pre;
        }*/
        
        .category {
           display: flex;
           flex-wrap:wrap;
        }
        .category-item {
           border: 2px solid transparent;
           border-radius: 4px;
           padding: 7px 15px;
           margin-bottom: 8px;
           margin-right: 8px;
           position:relative;
        }
        
        .checkbox:checked,
        .checkbox:not(:checked),
        .radio:checked,
        .radio:not(:checked){
            position: absolute;
            left: 16px;
            top: 8px;
            opacity:0;
            z-index: 1;
        }
        .checkbox:checked + label,
        .checkbox:not(:checked) + label,
        .radio:checked + label,
        .radio:not(:checked) + label{
           position: relative;
           padding-left: 35px;
        }
        .checkbox:checked + label::before,
        .radio:checked + label::before{
           content:'';
           position: absolute;
           width: 16px;
           height: 16px;
           left:0;
           top:calc(50% - 10px);
           background-color: transparent;
           border-radius: 2px;
           border: 2px solid rgb(var(--accent-color));
        }
        .checkbox:checked + label::after,
        .radio:checked + label::after{
           content:'';
           position: absolute;
           width: 12px;
           height: 12px;
           transition: all .2s ease;
           opacity: 1;
           left: 2px;
           top: calc(50% - 8px);
           background-color: rgba(var(--accent-color),80%);
        }
        .checkbox:not(:checked) + label::before,
        .radio:not(:checked) + label::before{
           content:'';
           position: absolute;
           width: 16px;
           height: 16px;
           left:0;
           top:calc(50% - 10px);
           background-color: transparent;
           border-radius: 2px;
           border: 2px solid rgba(var(--accent-color),80%);
        }
        .checkbox:not(:checked) + label::after,
        .radio:not(:checked) + label::after{
           content:'';
           position: absolute;
           width: 12px;
           height: 12px;
           transition: all .2s ease;
           opacity: 0;
           left: 2px;
           top: calc(50% - 8px);
           background-color: rgba(var(--accent-color),80%);
        }
        .radio:checked + label::before,
        .radio:not(:checked) + label::before,
        .radio:not(:checked) + label::after,
        .radio:checked + label::after{
           border-radius: 100%;
        }
        .checkbox:disabled + label::after,
        .checkbox:disabled + label::before,
        .checkbox:disabled + label,
        .radio:disabled + label::after,
        .radio:disabled + label::before,
        .radio:disabled + label{
           opacity:.5;
        }

        .themeBtn {
            background: rgba(var(--bg-color),32%);
            border: 1px solid rgba(var(--accent-color), 20%);
            border-bottom-color: rgba(var(--accent-color),60%);
            color: rgb(var(--font-color));
            border-bottom-width: 2px!important;
            text-shadow: 1px 1px 2px rgba(var(--bg-color), 0.32);
            box-shadow: 0px 0px 6px rgba(var(--bg-color), 0.16);
        }

        .themeBtn:hover {
            background: rgba(var(--bg-color),80%);
            border-color: rgba(var(--accent-color), 40%);
            border-bottom-color: rgba(var(--accent-color), 60%);
        }

        .themeBtn:active {
            background: rgba(var(--bg-color),64%);
            border-color: rgba(var(--accent-color), 80%);
            border-bottom-color: rgba(var(--accent-color), 80%);
        }

        .darkBtn {
            filter: invert() grayscale(1);
        }

        .invertBtn {
            filter: invert();
        }

        .accentBtn {
            background: rgb(var(--accent-color));
            border: 0.5px solid rgba(var(--accent-color), 64%);
            border-bottom-color: rgb(var(--accent-color));
            color: #fff;
        }

        .accentBtn > svg > path {
            fill: #fff!important;
        }

        .accentBtn:hover {
            background: rgba(var(--accent-color), 80%);
            border-color: rgba(var(--accent-color), 60%);
        }

        .accentBtn:active {
            background: rgba(var(--accent-color), 60%);
            border-color: rgba(var(--accent-color), 80%);
        }

        .warningBtn {
            background: rgba(255, 140, 0, 70%);
            border: 0.5px solid rgba(255, 140, 0, 64%);
            border-bottom-color: rgb(255, 140, 0);
            color: #fff;
        }

        .warningBtn:hover {
            background: rgba(255, 140, 0, 90%);
            border-color: rgb(255, 140, 0);
        }

        .warningBtn:active {
            background: rgba(255, 140, 0, 50%);
            border-color: rgba(255, 140, 0, 70%);
        }

        .dangerBtn {
            background: rgba(255, 35, 0, 70%);
            border: 0.5px solid rgba(255, 35, 0, 64%);
            border-bottom-color: rgb(255, 35, 0);
            color: #fff;
        }

        .dangerBtn:hover {
            background: rgba(255, 35, 0, 90%);
            border-color: rgb(255, 35, 0);
        }

        .dangerBtn:active {
            background: rgba(255, 35, 0, 50%);
            border-color: rgba(255, 35, 0, 70%);
        }

        .successBtn {
            color: #fff;
            background: rgba(0, 150, 64, 70%);
            border: 0.5px solid rgba(0, 150, 64, 64%);
            border-bottom-color: rgb(0, 150, 64);
        }

        .successBtn:hover {
            background: rgba(0, 150, 64, 90%);
            border-color: rgb(0, 150, 64);
        }

        .successBtn:active {
            background: rgba(0, 150, 64, 50%);
            border-color: rgba(0, 150, 64, 70%);
        }

        .linkBtn {
            border: 0.5px solid transparent;
            background: transparent;
            text-shadow: 1px 1px 3px rgb(var(--accent-color));
            box-shadow: none;
            color: rgb(var(--font-color));
            text-shadow: 1px 1px 2px rgba(var(--bg-color), 80%);
        }

        .linkBtn:hover {
            background: rgba(var(--accent-color), 0.42);
            border-color: transparent;
        }

        .linkBtn:active {
            background: rgba(var(--accent-color), 0.64);
            border: 0.5px solid #ddd;
            border-bottom-color: #bbb;
        }

        .fillBtn {
            width: 90%!important;
            max-width: 100%!important;
            margin: 4px 5%;
        }

        .largeBtn {
            padding: 6px 18px;
            font-size: 16px;
        }

        .desktopContainer {
            padding: 10px;
            padding-top: 32px;
            display: flex;
            flex-direction: column;
            flex-wrap: wrap;
            justify-content: flex-start;
            align-items: normal;
            align-content: flex-start;
            max-height: calc(100vh - 50px);
            color: rgb(var(--font-color));
            text-shadow: 1px 1px 2px rgba(var(--bg-color), 80%);
            font-weight: 500;
            width: fit-content;
        }

        .desktopItem {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            max-width: 80px;
            padding: 8px;
            text-align: center;
            font-size: 12px;
            margin-bottom: 5px;
            cursor: context-menu;
            border-radius: 4px;
            transition: .3s color ease;
        }

        .desktopItem,
        .desktopItem * {
            cursor: pointer;
        }

        .desktopIcon img {
            width: 40px;
            user-select: none;
        }

        .desktopItem:hover {
            background-color: rgba(131, 195, 255, .377);
            transition: all 0.1s ease-out;
        }



        #iframe_test {
            /*height: 512px;
            width: 935px;*/
            left: 70%;
            top: 70%;
            transform: translate(-70%, -70%);
            min-width: 935px !important;
        }

        #ui_test {
            /*width: 440px;
            height: 280px;*/
            left: 50%;
            top: 30%;
            transform: translate(-20%, -40%);
        }

        .startMenu {
            position: absolute;
            bottom: 60px;
            left: 6px;
            transform: translate(0, 0);
            max-height: 850px;
            width: 80%;
            max-width: 600px;
            z-index:6;
            box-shadow: 0 2.8px 2.2px rgb(0 0 0 / 2%), 0 6.7px 5.3px rgb(0 0 0 / 3%), 0 12.5px 10px rgb(0 0 0 / 4%), 0 22.3px 17.9px rgb(0 0 0 / 4%), 0 41.8px 33.4px rgb(0 0 0 / 5%), 0 100px 80px rgb(0 0 0 / 7%);
            flex-direction: column;
            overflow: hidden;
            border-radius: var(--window-radius);
        }

        .startMenu {
            visibility: hidden;
            animation: .4s slideDown;
            animation-timing-function: cubic-bezier(.3, .5, 0, 1);
            opacity:0;
        }

        .startMenu.active {
            display: flex;
            visibility: visible;
            animation: .4s slideUp;
            animation-timing-function: cubic-bezier(.3, .5, 0, 1);
            opacity:1;
        }
        
        @media screen and (max-width: 1200px){
            .startMenu.active {
                transform: translate(-50%, 0)!important;
                animation:none!important;
                left: 50%!important;
            }
            .startMenu {
                animation:none!important;
                transform: translate(-50%, 0)!important;
                left: 50%!important;
            }
        }

        @keyframes slideUp {
            from {
                transform: translate(0, 100%);
                opacity:0;
            }

            to {
                transform: translate(0, 0);
                opacity:1;
            }
        }

        @keyframes slideDown {
            from {
                transform: translate(0, 0);
                opacity:1;
            }

            to {
                transform: translate(0, 100%);
                opacity:0;
            }
        }

        .startMenu>.inner {
            width: 100%;
            height: 100%;
            position: relative;
        }

        .startMenu>.inner>.container {


            padding: 20px;
            padding-bottom: 5px;
            width: 100%;
            transition: .2s background ease;

        }

        .search_container {
            display: flex;
            width: 100%;
            background-color: rgba(var(--accent-color), 32%);
            border-radius: 5px;
            box-shadow: 0 3px 5px 0 rgba(0, 0, 0, .1);
            border-bottom: 2px rgb(var(--accent-color)) solid;
            padding-left: 17px;
            padding-right: 17px;
            transition: .2s all ease;
            color: #fff;
        }

        body.light_theme .search_container {
            background-color: #ffffff90;
        }

    

        .start_search_icon {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .start_another_container {
            padding: 0 30px;
            transition: .2s color ease;
            color: rgba(var(--font-color), 100%);
            font-weight:500;
        }

        .start_title_area {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
        }

        .start_title_area p {
            font-size: 14px;
            font-weight: 500;
        }

        .start_button {
            padding: 5px 9px;
            border-radius: 4px;
            background-color: rgba(46, 61, 83, .692);
            color: rgba(255, 255, 255, .836);
            cursor: pointer;
            font-size: 11px;
            letter-spacing: .2px;
            transition: .3s transform ease, .2s background-color ease, .2s color;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        body.light_theme .start_button:active {
            transform: scale(0.95);
            transition: .3s transform ease, .2s background-color ease, .2s color;
        }

        body.light_theme .start_button {
            background-color: rgba(255, 255, 255, .815);
            color: rgba(0, 0, 0, .836);
        }

        .start_apps_wrapper {
            display: grid;
            grid-template-columns: repeat(6, 1fr);
            grid-template-rows: repeat(3, 1fr);
            grid-column-gap: 15px;
            grid-row-gap: 15px;
            margin-top: 10px;
        }

        .start_app_item {
            font-size: 12px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            transition: .2s transform, .2s background-color;
            border-radius: 4px;
        }

        .start_app_item * {
            cursor: pointer;
        }

        .start_app_item:hover {
            background-color: rgba(3, 21, 41, .425);
        }

        body.light_theme .start_app_item:hover {
            background-color: rgba(255, 255, 255, .596);
        }

        .start_app_item:active {
            transform: scale(.9);
        }

        .start_app_item img {
            height: 60%;
            max-height: 42px;
            padding-bottom: 4px;
            padding-top: 2px;
        }

        .start_app_link {
            text-decoration: none;
            color: inherit;
            display: flex;
            width: 100%;
        }

        .start_app_link .start_app_item {
            width: 100%;
        }

        .start_app_item p {
            text-align: center;
        }

        /*.toastContain {
  position: fixed;
  left: calc(50% - 200px);
  bottom: 50px;
  width: 400px;
  z-index:12;
}
.toast {
  opacity: 0;
  min-height: 30px;
  padding: 5px;
  margin-top: -42px;
  box-shadow: 1px 1px 6px #0004;
  text-align: center;
  line-height: 30px;
  transform: scale(0.95);
  transition:
    margin-top 0.7s,
    transform 0.7s,
    opacity 0.7s;
    border-radius:6px;
}
.toast.open {
  margin-top: 10px;
  transform: scale(1) translateY(0);
  opacity: 1;
}*/

        .toastContain {
            position: fixed;
            right: 14px;
            bottom: 50px;
            width: 400px;
            z-index: 12;
        }

        .toast {
            opacity: 0;
            min-height: 30px;
            padding: 5px;
            margin-top: -42px;
            box-shadow: 1px 1px 6px #0004;
            text-align: center;
            line-height: 30px;
            transform: scale(0.95);
            transition:
                margin-top 0.7s,
                transform 0.7s,
                opacity 0.7s;
            border-radius: 6px;

            /*background-color: rgba(var(--secondary-color), 32%);
            color: rgba(var(--font-color), 60%);
            backdrop-filter: blur(24px);*/
        }

        .toast.open {
            margin-top: 10px;
            transform: scale(1) translateY(0);
            opacity: 1;
        }
        
        .control_center{
            position:absolute;
            right:6px;
            padding-left:6px;
            padding-right:6px;
            top:28px;
            min-width:267px;
            max-width:420px;
            min-height:64px;
            max-height:80%;
            /*visibility: hidden;*/
            transition:
                all 0.7s ease, width 1s ease-in-out;
            transform: translateY(-666px);
            border-radius: var(--window-radius);
            opacity: 0;
            animation: .6s slideDown2;
            animation-timing-function: cubic-bezier(.3, .5, 0, 1);
        }
        
        .control_center.active{
            opacity: 1;
            transform: translateY(0);
            z-index:9;
            visibility: visible;
    overflow-x: hidden;
    overflow-y: overlay;
            animation: .6s slideUp2;
            animation-timing-function: cubic-bezier(.3, .5, 0, 1);
        }
        
        .control_center.active > *{
            transition: width 1s ease-in-out;
        }
        
        @media screen and (max-width: 782px){
            .control_center.active {
            min-width:32%!important;
            max-width:90%!important;
                
            }
        }
        
        button > svg {
            min-width: 12px;
        }

        @keyframes slideUp2 {
            from {
                transform: translateY(-666px);
                opacity:0;
            }

            to {
                transform: translateY(0);
                opacity:1;
            }
        }

        @keyframes slideDown2 {
            from {
                transform: translateY(0);
                opacity:1;
            }

            to {
                transform: translateY(-666px);
                opacity:0;
            }
        }
        

.block {
    display: block;
}

.control_center > .controls_wrapper > .grid {
    display:flex;
    flex-wrap: wrap;
    justify-content:center;
 z-index:8;
            animation: .2s slideUp2;
            animation-timing-function: cubic-bezier(.3, .5, 0, 1);
}

.control_center > .controls_wrapper > .grid_control {
    display:flex;
    flex-wrap: wrap;
    justify-content:center;
 z-index:8;
    
}

.control_center .leftside, .control_center > .controls_wrapper label {
  margin-right: auto;
    font-weight:600;
    width:100%;
}

.control_center > .controls_wrapper > .grid > button, .control_center > .controls_wrapper > .grid_control > button {
 flex: 1 1 auto;
 min-width:80px;
 max-width:120px;
 width:20%;
 z-index:8;
 
}


.control_center > .controls_wrapper > .grid > button > span {
padding-top:6px;
 
}

.btnGroup{
 flex: 1 1 auto;
 min-width:60px;
 max-width:120px;
 width:20%;
 z-index:8;
 
    display: inline-flex;
}

.btnGroup > button {
 padding-top:6px;
    border-radius: 0px;
 flex: 1 1 auto;
    margin-left: 0;
    margin-right: 0;
    border-right-width: 0px;
    border-left-width: 0px;
}

.btnGroup > button:first-child {
    border-top-left-radius: 4px;
    border-bottom-left-radius: 4px;
    margin-right: 0;
    margin-left: 1px;
    border-right-width: 0px;
    border-left-width: 1px;
}

.btnGroup > button:last-child {
    border-top-right-radius: 4px;
    border-bottom-right-radius: 4px;
    margin-left: 0;
    margin-right: 1px;
    max-width:28px;
    padding-top: 12%;
    width: inherit!important;
    border-left-width: 0px;
    border-right-width: 1px;
}

.btnGroup > button > span {
 
}

.control_center > .controls_wrapper {
padding:12px 2px;
 
}

.control_center > .controls_wrapper.page.visible {
display:block;
            animation: .2s slideUp2;
            animation-timing-function: cubic-bezier(.3, .5, 0, 1);
 
}

label > span.rightAlign{
    float:right;
    padding:0;
    margin:0;
 z-index:8;
    cursor: default;
}

label > span.rightAlign > button{
    padding:3px;
    margin: 0px 3px 6px 3px;
 z-index:8;
    cursor: pointer;
}

.control_center > .controls_wrapper.page {
display:none;
            animation: .2s slideDown2;
            animation-timing-function: cubic-bezier(.3, .5, 0, 1);
 
}

.control_center > .controls_wrapper > .grid.collapsed {
display:none;
            animation: .2s slideDown2;
            animation-timing-function: cubic-bezier(.3, .5, 0, 1);
}
.control_center > .controls_wrapper > .grid_control > input[type=range] {
 z-index:8;
 
}

.control_center > .controls_wrapper > .grid > button > .inline {
  display: inline-block;
  width: 35%;
}

.control_center > .controls_wrapper > .grid > button > *, .control_center > .grid > button > * > * {
cursor:pointer;
}

.control_center > .controls_wrapper > .grid_control > button > *, .control_center > .grid_control > button > * > * {
cursor:pointer;
}

.control_center > .controls_wrapper > .grid > .btnGroup > button > *, .control_center > .grid > .btnGroup > button > * > * {
cursor:pointer;
}

 div[for="keyboard"] > .grid_control > button{
    width:100%!important;
    max-width:100%!important;
    height:42px;
    margin:4px 0px;
}

.p-2 {
    padding: .5rem;
    margin: 6px 1px;
}

.w-6 {
    width: 1.5rem;
    height: .9rem;
    margin:6px auto;
}

.devFunc{
    display:none;
}

    .showcase {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: calc(100% - 32px);
    }

    .showcase.ab {
        height: 100%;
    }

    .showcase_text h4 {
        font-size: 20px;
        font-weight: 400;
    }

    .showcase_image {
        width: clamp(40px, 40%, 200px);
    }

#notifications{
    width:100%;
    cursor: default;
}

#notifications > .toast.default.notif{
    position:relative;
    width:100%;
    opacity:1;
    margin-top: 6px;
    /*padding: 15px 5px;*/
    background-color:rgba(var(--bg-color),32%);
    cursor: default;
}

#notifications > .toast.default.notif > .sendTime {
    text-align: right;
    padding: 0px 12px;
    cursor: default;
}

#notifications > .toast.default.notif > .sendTime > .closeBtn {
    float: left;
    text-align: center;
    width: 32px;
    border-radius: 12px;
    color:rgba(var(--font-color),50%);
}

#notifications > .toast.default.notif > .sendTime > .closeBtn:hover {
    background-color: rgba(var(--bg-color),32%);
    color:rgb(var(--font-color));
}

#notifications > .toast.default.notif > .sendTime > .closeBtn:active {
    filter:invert();
}

input[type=range] {
  outline: 0;
  border: 0;
  border-radius: 12px;
  width: 400px;
  max-width: 100%;
  margin: 6px auto 6px;
  transition: box-shadow 0.2s ease-in-out;
  
    border: 1px solid rgba(var(--bg-color), 16%);
}
  input[type=range] {
    overflow: hidden;
    height: 20px;
    -webkit-appearance: none;
    background-color: #ddd;
            background-color: rgba(var(--bg-color), 42%);
  }
  input[type=range]:hover {
            background-color: rgba(var(--bg-color), 80%);
  }
  input[type=range]::-webkit-slider-runnable-track {
    height:20px;
    -webkit-appearance: none;
    color: #444;
    transition: box-shadow 0.2s ease-in-out;
  }
  input[type=range]::-webkit-slider-thumb {
    width: 10%;
    -webkit-appearance: none;
    height: 20px;
    box-shadow: -330px 0 0 320px rgba(var(--accent-color), 80%), inset 0 0 0 40px rgba(var(--font-color), 60%);
    border-radius: 50%;
  border-radius: 12px;
    transition: box-shadow 0.1s ease-in-out;
    position: relative;
    border: 1px solid rgba(var(--bg-color), 42%);
  }
  input[type=range]:disabled::-webkit-slider-thumb {
      background:transparent;
    box-shadow: -330px 0 0 320px rgba(var(--accent-color), 20%), inset 0 0 0 10px rgba(var(--accent-color), 40%);
  }
  input[type=range]:disabled::-webkit-slider-thumb:hover {
      background:transparent;
    box-shadow: -330px 0 0 320px rgba(var(--accent-color), 20%), inset 0 0 0 10px rgba(var(--accent-color), 40%);
  }
  input[type=range]::-webkit-slider-thumb:hover {
    background: #ccc;
            background: rgba(var(--accent-color), var(--accent-opacity));
    box-shadow: -330px 0 0 320px rgba(var(--accent-color), 90%), inset 0 0 0 10px rgba(var(--font-color), 80%);
        border: 2px solid rgba(var(--bg-color), 42%);
  }
  input[type=range]:active::-webkit-slider-thumb {
    background: #fff;
            background: rgba(var(--accent-color), var(--accent-opacity));
    box-shadow: -330px 0 0 320px rgba(var(--accent-color), 100%), inset 0 0 0 10px rgba(var(--font-color), 100%);
        border: 1px solid rgba(var(--bg-color), 64%);
  }
  
  
input[type=range]::-moz-range-progress {
  background-color: rgba(var(--font-color), 64%);
}
input[type=range]::-moz-range-track {
  background-color: rgba(var(--accent-color), 80%);
}
input[type=range]::-ms-fill-lower {
  background-color: rgba(var(--font-color), 64%);
}
input[type=range]::-ms-fill-upper {
  background-color: rgba(var(--accent-color), 80%);
}

.progress {
  position: relative;
  width: 100%;
  height: 32px;
  background: rgba(var(--bg-color), 64%);
    border-radius: var(--window-radius);
  overflow: hidden;
}

.progress.big {
  height: 64px;
}

.progress__fill {
  width: 0%;
  height: 100%;
  background: rgba(var(--accent-color), 80%);
  transition: all 0.2s;
}

.progress__text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  color: rgb(var(--font-color));
}

.container-calendar, .containerControls {
    padding: 15px;
    width: 100%;
    margin: 0 auto;
    overflow: auto;
    background: rgba(var(--bg-color),32%);
    border-radius: var(--window-radius);
}

 
.button-container-calendar button {
  display: inline-block;
  padding: 5px 10px;
} 

.table-calendar {
  border-collapse: collapse;
  width: 100%;
}

.table-calendar td, .table-calendar th {
  /*padding: 4px; 
  border: 1px solid #e2e2e2; */
  min-width: 30px;
  text-align: center;
  vertical-align: top;
}

.date-picker.selected {
    font-weight: bold;
    background-color: rgba(var(--accent-color),60%);
    color: rgb(var(--font-color))!important;
    border-radius: 4px;
}


      /*
.date-picker.selected span {
  border-bottom: 2px solid #abc837ff;
} */

/* sunday */
.date-picker:nth-child(1), .date-picker:nth-child(7)  { 
    color: rgb(var(--accent-color));
}


#monthAndYear {
  text-align: center;
  margin-top: 0;
}

.button-container-calendar {
  position: relative;
  margin-bottom: 1em;
  overflow: hidden;
  clear: both;
}

#previous {
  float: left;
}

#next {
  float: right;
}

.footer-container-calendar {
  margin-top: 1em;
  border-top: 1px solid #dadada;
  padding: 10px 0 2px 0;
  display: none;
}

.footer-container-calendar select {
  cursor: pointer;
  display: inline-block;
  zoom: 1;
  background: #ffffff;
  color: #585858;
  border: 1px solid #bfc5c5;
  border-radius: 3px;
  padding: 5px 1em;
}
   
   
.clock {
    display: inline-block;
    font-weight: bold;
    font-size: 1.5em;
    width: 100%;
    text-align:center;
    /* padding-left: 20px; */
}

.time {
    display: inline-block;
    min-width: 37px;
}

.colon {
    font-size: 1.1em;
    display: inline-block;
}

.date {
    display: block;
    min-width: 162px;
    /* padding-right: 30px; */
    /* border-right: 2px solid #00BBC4; */
    font-size: 1.2em;
    width: 100%;
    text-align:center;
}
.greet{
    
    min-width: 162px;
    /* padding-right: 30px; */
    font-size: 2.5em;
    width: 100%;
    text-align:center;
}

.qwerty {
    overflow: overlay;
    width:100%;
    min-width:150px;
    background: rgba(var(--bg-color),16%);
    border-radius: var(--window-radius);
}

.qwerty ul {
    width:100%;
    list-style-type: none;
    text-align: center;
    font-weight: normal;
    display: inline-block;
    text-align: left;
    overflow-y: auto;
    overflow-x: hidden;
}

.qwerty li {
    margin: 3px;
    padding:6px;
    border-radius: var(--window-radius);
    border:1px solid rgba(var(--bg-color),16%);
    line-height: 32pt;
    position:relative;
    height:42pt;
}

.qwerty li:hover {
    background: rgba(var(--accent-color),32%);
    border:1px solid rgba(var(--accent-color),64%);
}

.qwerty li:active {
    background: rgba(var(--accent-color),64%);
    border:1px solid rgba(var(--accent-color),100%);
}
.qwerty li > .main{
    position:absolute;
    top:-4px;
    right:0px;
    left:42px;
    height:50%;
    font-weight:600;
}
.qwerty li > .secondary{
    position:absolute;
    top:20px;
    right:-42px;
    left:42px;
    height:50%;
    font-weight:300;
}
.qwerty li > .icon{
    position:absolute;
    top:calc(50% - 16px);
}

        .tray_window, .left_menu_window{
             opacity: 0;
             position:absolute;
             left:245px;
             top:28px;
             max-width:256px;
             min-width:64px;
             max-height:70%;
             transition: all 0.7s ease, width 1s ease-in-out;
             transform: translateY(-666px);
             border-radius: var(--window-radius);
             animation: 1s slideDown2;
             animation-timing-function: cubic-bezier(.3, .5, 0, 1);
             padding:3px;
             display: flex;
             flex-flow: row wrap;
             justify-content: center;
             align-content: center;
            /*backdrop-filter: blur(16px);
            background-color: rgba(var(--secondary-color), 16%);*/
             
        }
        .tray_window > div{
             padding: 6px 8px;
             margin: 3px;
             flex: 0 0.5 auto;
        }
        .tray_window div:hover, .left_menu_window li:hover{
            /*background-color:rgba(var(--accent-color), 60%);*/
            background-color: rgba(var(--secondary-color), 32%);
             border-radius: var(--window-radius);
        }
        .tray_window.active, .left_menu_window.active{
            opacity:1;
            z-index:9;
            transform: translateY(0);
            visibility: visible;
            overflow-x: hidden;
            overflow-y: overlay;
            animation: .5s slideUp2;
            animation-timing-function: cubic-bezier(.3, .5, 0, 1);
            /*color: rgb(var(--font-color);*/
        }
        .left_menu_window ul{
        }
        .left_menu_window li{
            vertical-align: middle;
            padding: 3px 10px;
            padding-left: 10px;
            padding-right: 10px;
            font-size: 12px;
            margin: 1px;
            margin-left: 1px;
            margin-right: 1px;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
            border-radius: 5px;
            cursor: pointer;
            transition: .1s all ease-in;
        }
        
        .containerControls{
            margin-top:12px;
        }

        .containerControls > .btnGroup{
            width: unset!important;
            max-width: 256px!important;
        }

        .grid > .btnGroup > button{
            max-width:inherit!important;
            padding: 6px!important;
            width: auto!important;
        }

        .grid > .btnGroup > button.icon{
            max-width:28px!important;
        }
        
        a.ab{
            padding: 3px 6px;
            margin: 1px;
        }
        
        .checkbox, .radio, .switch {
            margin: 0;
            display: table;
            cursor: pointer;
        }
      
    
  

#organizerContainer {
  float: left;
  width: 18%;
  margin: 0;
}
#calendarContainer{
  float: left;
  width: 65%;
  margin: 0;
}
.cjslib-month,.cjslib-year {

   width: 100%;
}

.cjslib-calendar.cjslib-size-small{
   width: 100%;
}
.cjslib-events.cjslib-size-small {
   width: 320px; 
}
.cjslib-date {
   width: 100%;
}


.fa-button {
  background: #e0e0e0;
  border-radius: 3px;

}

.fa-button.dark {
  background: #444;
}

.fa-button:hover:not(.disabled) {
  cursor: pointer;
  border-radius: 3px;
  filter: brightness(110%) saturate(140%);
}

.fa-button.disabled {
  filter: grayscale(100%);
  opacity: 0.5;
}


.material-icons.text-success {
    color: #28a745!important;
    font-size: 16px;
    
}
.material-icons.text-warning {
    color: #ffc107!important;
    font-size: 16px;
}
.material-icons.text-danger {
    color: #dc3545!important;
    font-size: 16px;
}
.material-icons.text-light {
    color: #fff!important;
    font-size: 16px;
}
.material-icons.text-default {
  font-size: 16px;
}








/* บังคับให้เฉพาะ aside ที่เป็นลูกของ #surface เท่านั้น */
#surface > aside {
  /* [1] ตัดการสืบทอด Style จากข้างนอก */
  all: initial; 
  
  /* [2] ใส่ Style ของคุณกลับเข้าไป */
  position: absolute;
  padding: 18px;
  padding-top: 25px;
  width: 300px;
  display: flex;
  flex-wrap: wrap; 
  justify-content: center; 
  gap: 15px;
  
  /* บังคับค่าที่จำเป็นสำหรับการแสดงผล */
  box-sizing: border-box;
  text-align: center;
}

/* เจาะจงเฉพาะ .apps ที่อยู่ใน aside ของ #surface */
#surface > aside .apps {
  all: initial; /* ป้องกัน Style จาก class .apps ตัวอื่นหลุดมา */
  position: relative;
  width: 75px;
  max-height: 85px;
  text-align: center;
  padding: 4px 5px 0px 5px;
  cursor: copy;
  border: 1px solid rgba(238, 238, 238, 0);
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  align-items: center;
  box-sizing: border-box;
}

#surface > aside .apps:hover {
  background: rgba(97, 97, 97, 0.25);
  border: 1px solid rgba(97, 97, 97, 0.5);
}

/* เจาะจงเฉพาะ img และ p ที่อยู่ในนี้เท่านั้น */
#surface > aside .apps img {
  width: 50px !important;
  height: auto;
  display: block;
  margin: 0 auto;
}

#surface > aside .apps p {
  all: initial;
  display: block;
  width: 100%;
  font-family: 'Segoe UI', 'Sarabun', sans-serif;
  font-size: 12px;
  color: #fff;
  margin-top: 5px;
  text-align: center;
}




        /* ซ่อนเมนูตามค่าเริ่มต้น */
        .context-menu {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            box-shadow: 0px 0px 10px 0px #000;
            padding: 10px;
            z-index: 1000;
            width: 180px; 
            font: 11px sans-serif;
        }
        .context-menu a {
            display: block;
            padding: 5px;
            color: black;
            text-decoration: none;
        }
        .context-menu a:hover {
            background-color: #e5e5e5;
            }
            
            
th {
    text-align: left;
    color: #666;
    font-size:14px;
}
</style>

<style>
    /* สไตล์หลักของวงกลมตัวย่อ */
    .circle-badge {
        width: 16px;
        height: 16px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        color: white;
        font-weight: bold;
        font-size: 12px;
        flex-shrink: 0;
        margin-right: 10px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    /* สีไล่ระดับ (Gradient) สำหรับแต่ละความถี่ */
    .bg-day { background: linear-gradient(135deg, #7367F0, #CE9FFC); }    /* สีม่วงแบบในรูปคุณ (D) */
    .bg-week { background: linear-gradient(135deg, #FF9F43, #FFC371); }   /* สีส้ม (W) */
    .bg-month { background: linear-gradient(135deg, #EA5455, #FEB692); }  /* สีแดง (M) */
    .bg-year { background: linear-gradient(135deg, #4b4b4b, #9d9d9d); }   /* สีเทาดำ (Y) */
    .bg-default { background: linear-gradient(135deg, #28C76F, #81FBB8); }/* สีเขียว (ทั่วไป) */
</style>

<style>
 
        /* --- สไตล์ของ Widget --- */
        .gami-widget {
            position: absolute;
            top: 50px;
            right: 20px; /* ค่าเริ่มต้น แต่ถ้าขยับแล้ว JS จะลบค่านี้ทิ้ง */
            width: 320px;
            background: rgba(255, 255, 255, 0.85);
            backdrop-filter: blur(10px);
            -webkit-backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.5);
            border-radius: 12px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.15);
            z-index: 2;
            overflow: hidden;
            display: flex;
            flex-direction: column;
            transition: height 0.3s ease;
        }

        /* ส่วนหัว (Header) */
        .gami-header {
            padding: 10px 15px;
            background: rgba(255, 255, 255, 0.5);
            border-bottom: 1px solid rgba(0, 0, 0, 0.05);
            display: flex;
            justify-content: space-between;
            align-items: center;
            cursor: grab;
            user-select: none;
        }
        .gami-header:active {
            cursor: grabbing;
        }
        .gami-title {
            font-size: 14px;
            font-weight: bold;
            color: #333;
            display: flex;
            align-items: center;
            gap: 8px;
        }
        .gami-controls button {
            background: none;
            border: none;
            cursor: pointer;
            padding: 2px 5px;
            border-radius: 4px;
            color: #666;
            transition: background 0.2s;
        }
        .gami-controls button:hover {
            background: rgba(0, 0, 0, 0.1);
            color: #000;
        }

        /* ส่วนเนื้อหา (Body) */
        .gami-body {
            padding: 15px;
        }

        /* Profile Player */
        .player-profile {
            display: flex;
            align-items: center;
            gap: 12px;
            margin-bottom: 12px;
        }
        .avatar {
            width: 45px;
            height: 45px;
            background: linear-gradient(135deg, #7367F0, #CE9FFC);
            color: white;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 20px;
            font-weight: bold;
            box-shadow: 0 4px 8px rgba(115, 103, 240, 0.4);
        }
        .player-info h6 {
            margin: 0 0 3px 0;
            font-size: 15px;
            color: #333;
        }
        .player-info span {
            font-size: 12px;
            color: #666;
            background: #e0e0e0;
            padding: 2px 8px;
            border-radius: 12px;
            font-weight: bold;
        }

        /* --- ส่วนภาพรวม (Overall Summary) --- */
        .overall-summary {
            background: rgba(115, 103, 240, 0.1);
            padding: 10px;
            border-radius: 8px;
            margin-bottom: 15px;
            border: 1px dashed #7367F0;
        }
        .overall-header {
            display: flex;
            justify-content: space-between;
            font-size: 13px;
            font-weight: 700;
            color: #7367F0;
            margin-bottom: 5px;
        }
        .overall-text {
            font-size: 11px;
            color: #666;
            text-align: right;
            margin-top: 4px;
        }

        /* รายการภารกิจ (Quests) */
        .quest-item {
            margin-bottom: 12px;
        }
        .quest-header {
            display: flex;
            justify-content: space-between;
            font-size: 13px;
            margin-bottom: 5px;
            color: #444;
            font-weight: 600;
            align-items: center;
        }
        .quest-name {
            display: flex;
            align-items: center;
            gap: 6px;
        }
        .quest-reward {
            color: #ff9f43;
            font-size: 12px;
            font-weight: bold;
        }

        /* หลอด Progress */
        .progress-container {
            width: 100%;
            height: 6px;
            background: #e9ecef;
            border-radius: 10px;
            overflow: hidden;
        }
        .progress-bar {
            height: 100%;
            border-radius: 10px;
            transition: width 0.5s ease;
        }
        .bg-primary { background: #7367F0; }
        .bg-success { background: #28C76F; }
        .bg-gradient-warning { background: linear-gradient(135deg, #ff9f43, #ffc07e); }

        .hidden { display: none !important; }
    </style>

</head>


<body id="surface" style="background-image: url('<c:url value="${not empty userSetting.backgroundImage ? userSetting.backgroundImage : '/app/system/background/marsumilae.jpg'}" />');">

 <aside>
 
   <div class='apps' style="display:none" th:oncontextmenu="menu(event)"   th:ondblclick="' shows(\'draggable-window3\',\'Bin\'); getData(\'' + @{/Bin}+ '\', \'mainbody3\');    '">
    <img src='http://d33wubrfki0l68.cloudfront.net/f23270c8732fcd425cfba34215cebd21c49f2f56/e090c/img/recycle.png'>

    <p>Bin</p>

  </div>
 
  <div class='apps' style="display:none"  style="display:none" th:oncontextmenu="menu(event)"  ondblclick="shows2('draggable-window2','FileExplorer'); getData('/ChooseFile-filemanager', 'ubuntus') ">
    <img src='http://d33wubrfki0l68.cloudfront.net/68f0050063c1964dfe8e5ab4309941c6185dbeac/9e24d/img/folder.png'>

    <p>New Folder</p>

  </div>
 




  <div class='apps'  style="display:none" th:oncontextmenu="menu(event)"  th:ondblclick="' shows(\'draggable-window3\',\'I-spreadsheet\'); getData(\'' + @{/Spreadsheet}+ '\', \'mainbody3\');    '">
    <img src='https://static2.sharepointonline.com/files/fabric/assets/brand-icons/product-fluent/svg/excel_48x1.svg'>
    <p>เอกสารไม่มีชื่อ</p>
  </div>
 
<c:set var="safeHiddenList" value=",${userSetting.hiddenApps}," />

<script>
    var GLOBAL_APP_CONFIG = {}; // ตัวเก็บข้อมูล App ทั้งหมด
</script>


<c:set var="isHide9910" value="${fn:contains(safeHiddenList, ',9910,')}" />

<div class='apps' 
     data-id="9910"
     data-name="Index"
     data-link="/qcompro-filemanager/"
     data-logo="/resources/app-assets/images/icons/APP/qcompro.png"
     data-box=" [<spring:message code="btn.InProgress"  text="-​" />]"
     data-startapponly="0"
     ondblclick="App(this);"  
     oncontextmenu="menu(event);"
     style="${isHide9910 ? 'display:none;' : ''}"> <img src='/resources/app-assets/images/icons/APP/qcompro.png'>
    <p>Qcompro</p>
</div>

<script>
    GLOBAL_APP_CONFIG['9910'] = {
        id: '9910',
        name: 'Qcompro',
        link: '/qcompro-filemanager/',
        startapponly: 0,
        logo: '/resources/app-assets/images/icons/APP/qcompro.png'
    };
</script>


<c:set var="isHide9999" value="${fn:contains(safeHiddenList, ',9999,')}" />

<div class='apps' 
     data-id="9999"
     data-name="Jerry Mouse BI"
     data-link="/reportapp-filemanager/"
     data-logo="/resources/app-assets/images/icons/APP/JBI.png"
     data-box=" [<spring:message code="btn.InProgress"  text="-​" />]" 
     data-startapponly="0"
     ondblclick="App(this)"  
     oncontextmenu="menu(event)"
     style="${isHide9999 ? 'display:none;' : ''}"> <img src='/resources/app-assets/images/icons/APP/JBI.png'>
    <p>Jerry Mouse BI</p>
</div>
<script>
                GLOBAL_APP_CONFIG['9999'] = {
                    id: '9999',
                    name: 'Jerry Mouse BI',
                    link: '/reportapp-filemanager/',
                    startapponly: 0,
                    logo: '/resources/app-assets/images/icons/APP/JBI.png'
                };
</script>



<c:set var="isHide9900" value="${fn:contains(safeHiddenList, ',9900,')}" />

<div class='apps' 
     data-id="9900"
     data-name="SpikeDog Analytics"
     data-link="/analytics-filemanager/"
     data-logo="/resources/app-assets/images/icons/APP/SpikeDog.png"
     data-box=" [<spring:message code="btn.InProgress"  text="-​" />]" 
     data-startapponly="0"
     ondblclick="App(this)"  
     oncontextmenu="menu(event)"
     style="${isHide9900 ? 'display:none;' : ''}"> <img src='/resources/app-assets/images/icons/APP/SpikeDog.png'>
    <p>SpikeDog Analytics</p>
</div>
<script>
                GLOBAL_APP_CONFIG['9900'] = {
                    id: '9900',
                    name: 'SpikeDog Analytics',
                    link: '/analytics-filemanager/',
                    startapponly: 0,
                    logo: '/resources/app-assets/images/icons/APP/SpikeDog.png'
                };
</script>

<c:set var="isHide9901" value="${fn:contains(safeHiddenList, ',9901,')}" />


<div class='apps' 
     data-id="9901"
     data-name="My Promos"
     data-link="/Promos-app/"
     data-logo="/resources/app-assets/images/icons/APP/mpm.jpg"
     data-box="" 
     data-startapponly="0"
     ondblclick="App(this)"  
     oncontextmenu="menu(event)"
     style="${isHide9901 ? 'display:none;' : ''}"> <img src='/resources/app-assets/images/icons/APP/mpm.jpg'>
    <p>My Promos</p>
</div>
<script>
                GLOBAL_APP_CONFIG['9901'] = {
                    id: '9901',
                    name: 'My Promos',
                    link: '/Promos-app/',
                    startapponly: 0,
                    logo: '/resources/app-assets/images/icons/APP/mpm.jpg'
                };
</script>


<div class='apps' 
     data-id="9902"
     data-name="Project+"
     data-link="/ProjectPlus-app/"
     data-logo="/resources/app-assets/images/icons/APP/ProjectPlus.png"
     data-box="" 
     data-startapponly="0"
     ondblclick="App(this)"  
     oncontextmenu="menu(event)"
     style="${isHide9902 ? 'display:none;' : ''}"> <img src='/resources/app-assets/images/icons/APP/ProjectPlus.png'>
    <p>Project+</p>
</div>
<script>
                GLOBAL_APP_CONFIG['9902'] = {
                    id: '9902',
                    name: 'My Promos',
                    link: '/ProjectPlus-app/',
                    startapponly: 0,
                    logo: '/resources/app-assets/images/icons/APP/ProjectPlus.png'
                };
</script>





<c:forEach items="${workflow}" var="row">
    <c:if test='${row.workflow_id > 0}'>
        <c:forEach items="${row.process}" var="rows" varStatus="count">
            
            <%-- Logic Link เดิม --%>
            <c:if test='${rows.program_type == 0}'><c:set var="link" value="/app-filemanager/" /></c:if>
            <c:if test='${rows.program_type == 1}'><c:set var="link" value="/managementapp-filemanager/" /></c:if>
            <c:if test='${rows.program_type == 2}'><c:set var="link" value="/indexapp-filemanager/" /></c:if>

            <c:set var="logo" value="qexcellent.png" />
            <c:if test='${rows.process_logo != null && rows.process_logo != "" }'>
                <c:set var="logo" value="${rows.process_logo}" />
            </c:if>

            <%-- เช็คว่า ID นี้ต้องซ่อนไหม --%>
            <c:set var="currentIdCheck" value=",${rows.id}," />
            <c:set var="isHideLoop" value="${fn:contains(safeHiddenList, currentIdCheck)}" />

            <%-- HTML แสดงผล (ซ่อนถ้า ID ตรง) --%>
            <div class='apps' id="APP-${rows.id}"
                 data-reference ="${rows.reference }"
                 data-id="${rows.id}"
                 data-name="${rows.name} "
                 data-link="${link }"
                 data-logo="/resources/app-assets/images/icons/APP/${logo}"
                 data-box=" [<spring:message code="btn.InProgress" text="-​" />]" 
                 data-startapponly="0"
                 ondblclick="App(this)" 
                 oncontextmenu="menu(event)"
                 style="${isHideLoop ? 'display:none;' : ''}"> <span id="badge-${rows.id}" class="app-badge" style="display:<c:if test= "${rows.alert < 1}">none</c:if>">
                    !
                 </span>
         
                 <img src='/resources/app-assets/images/icons/APP/${logo}'>
                 <p>${rows.name }</p>
                
            </div>

            <%-- JS Config (ทำงานเสมอ ห้ามซ่อน) --%>
            <script>
                GLOBAL_APP_CONFIG['${rows.id}'] = {
                    id: '${rows.id}',
                    name: '${rows.name}',
                    link: '${link}',
                    startapponly: 0,
                    logo: '/resources/app-assets/images/icons/APP/${logo}'
                };
            </script>

        </c:forEach>
    </c:if>
</c:forEach>
 




  
	<div id="context-menu-bin" class="context-menu">
    <a href="#" onclick="postData('/bin_recover', 'bin_recover_form', 'mainbody3')">กู้คืน</a>
		<a href="#" onclick="postData('/bin_delete', 'bin_delete_form', 'mainbody3')">ลบถาวร</a>
	</div>

  <form id="bin_recover_form"     method="POST">
      <input type="hidden" value="0"  id="bin_recover" name="bin_recover"/>
      <input type="hidden" value="0"  id="bin_recover_type" name="bin_recover_type"/>
    </form>
    <form id="bin_delete_form"  method="POST">
      <input type="hidden" value="0"  id="bin_delete" name="bin_delete"/>
      <input type="hidden" value="0"  id="bin_delete_type" name="bin_delete_type"/>
    </form>


</aside>




<div class="body">








   <div class="window microsoft draggable2" id="draggable-window4" style="z-index: 10;display:none">
      <div class="header system2" >
        <div class="title"> 
          <div class="icon"><i class="material-icons">settings_system_daydream</i></div>
          <div class="text">System</div>
        </div>
        <div class="buttons">
         <div class="btn "></div>
          <div class="btn close" onclick="closeed('draggable-window4')"> <i class="material-icons">close</i></div>
        </div>
      </div>
      <div class="bar" id="barmainbody4"></div>
      <div class="form" id="system2">
    
      
      <div class="container2"  id="mainbody4">
      
         <div id="body" class="mainbody4"   >
      
         </div>
         
         
         
         <div class="footer">
         
         </div>
         
         
         </div>
      
      
      </div>
      </div>
   

      <div class="window microsoft draggable2" id="draggable-window5" style="z-index: 10;display:none;" >
         <div class="header calendar" >
           <div class="title"> 
             <div class="icon"><i class="material-icons">settings_system_daydream</i></div>
             <div class="text">Calendar</div>
           </div>
           <div class="buttons">
            <div class="btn "></div>
             <div class="btn close" onclick="closeed('draggable-window5')"> <i class="material-icons">close</i></div>
           </div>
         </div>
  
    
       
         
       
         
    
        
            
            
            
            <div class="footer">
            
            </div>
            
           
         
         
   
         </div>

<!-- windows -->

<%@ include file="windows/mainwindows.jspf" %>  
<%@ include file="windows/settingwindows.jspf" %>  
<%@ include file="windows/mainwindows2.jspf" %>  
<%@ include file="windows/settingwindows2.jspf" %>  





<div id="desktop">
</div>



         
</div>








<div class="dockposition">
<div class="content">
  <div class="back"></div>
  <div class="icons" id="dockposition">
  </div>
</div>
</div>



<div style="display:none">
      <a th:href="@{/report}" >Report</a><br>
      <a th:href="@{/notification}" >Notification</a><br>
      <a th:href="@{/calendar}" >Calendar</a><br>
      <a th:href="@{/setting}" >Setting</a><br>

   </div>
   <div id="mainsave"></div> 
    </body>

<div class="assistant-wrapper">
    <div class="manga-bubble" id="bubble">...</div>
    <div class="avatar-box" id="avatar" onclick="toggleMenu()">😉</div>
    
    <div class="manga-menu" id="menued">
 <div class="menu-item" style="display: flex; gap: 5px; padding: 5px; align-items: center;">
    <button id="micBtn" onclick="startDictation()" style="..." title="กดเพื่อพูด">🎙️</button>
    <button onclick="document.getElementById('aiInput').value='#CM '; document.getElementById('aiInput').focus();" 
            style="cursor: pointer; border-radius: 50%; border: none; background: #fff1b8; padding: 8px; font-size: 16px;" title="โหมดคำสั่ง">⚡</button>
            
    <input type="text" id="aiInput" placeholder="ถาม AI หรือกด ⚡ เพื่อสั่งงาน..." 
           onkeypress="handleAIKeyPress(event)"
           style="width: 100%; padding: 5px; border-radius: 5px; border: 1px solid #ccc; outline: none;">
    <button onclick="askGemini()" style="cursor: pointer; border-radius: 5px; border: none; background: #007bff; color: white; padding: 0 10px;">ถาม</button>
</div>
           <a href="#" onclick="shows2('draggable-window3','Calendar');getData('/allcalendar-filemanager', 'mainbody3')"><div class="menu-item" ><span>📅</span>ปฏิทิน</div></a>
        <div class="menu-item" onclick="shows2('draggable-window6','Setting'); getData('/Setting-filemanager', 'mainbody6'); "><span>⚙️</span> ตั้งค่า</div>
       
    
           <a onclick="showGamificationWidget(); return false;"><div class="menu-item" ><span>📊</span>ภารกิจของฉัน</div></a>
        <a href="/appcenter-filemanager?language=en"><div class="menu-item" ><span>EN</span>Change language</div></a>
        <a href="/appcenter-filemanager?language=th"><div class="menu-item" ><span>TH</span>เปลี่ยนภาษา</div></a>
        <a href="/"><div class="menu-item" ><span>👈</span>ไประบบหลัก</div></a>

        <a href="/logout"> <div class="menu-item" ><span>⚡</span> ออกจากระบบ</div></a>
    </div>
    

</div>

    <div id="aiModalBackdrop" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.5); z-index:9998;" onclick="closeAiModal()"></div>
<div id="aiModal" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%, -50%); width:80%; max-width:600px; max-height:80vh; background:#fff; border-radius:10px; box-shadow:0 5px 15px rgba(0,0,0,0.3); z-index:9999; flex-direction:column; font-family:sans-serif;">
    <div style="padding:15px; border-bottom:1px solid #ddd; display:flex; justify-content:space-between; align-items:center; background:#f8f9fa; border-radius:10px 10px 0 0;">
        <h3 style="margin:0; font-size:18px;;color:black">🤖 คำตอบจากระบบ ERP</h3>
        <button onclick="closeAiModal()" style="border:none; background:#ff4d4f; color:white; border-radius:5px; padding:5px 15px; cursor:pointer; font-weight:bold;">ปิด ❌</button>
    </div>
    <div id="aiModalContent" style="padding:20px; overflow-y:auto; line-height:1.6; white-space:pre-wrap; font-size: 14px;color:black"></div>
</div>





 <div id="gamiWidget" class="gami-widget" style="display:none">
        <div id="gamiHeader" class="gami-header">
            <div class="gami-title">
                <i data-feather="target" style="width: 16px; height: 16px; color:#ea5455;"></i> 
                ภารกิจของคุณ
            </div>
            <div class="gami-controls">
                <button id="btnMinimize" title="พับเก็บ"><i data-feather="minus" style="width: 14px; height: 14px;"></i></button>
                <button id="btnClose" title="ปิด"><i data-feather="x" style="width: 14px; height: 14px;"></i></button>
            </div>
        </div>

        <div id="gamiBody" class="gami-body">
            
            <div class="player-profile">
                <div class="avatar">A</div>
                <div class="player-info">
                    <h6>นาย A (นักขาย)</h6>
                    <span>✨ Level 5 | XP รวม: 1,250</span>
                </div>
            </div>

            <div class="overall-summary">
                <div class="overall-header">
                    <span>ภาพรวมวันนี้ (Overall)</span>
                    <span>65%</span>
                </div>
                <div class="progress-container" style="height: 8px;">
                    <div class="progress-bar bg-gradient-warning" style="width: 65%;"></div>
                </div>
                <div class="overall-text">สำเร็จแล้ว 2 จาก 5 ภารกิจ</div>
            </div>

            <div class="quests-list">
                <div class="quest-item">
                    <div class="quest-header">
                        <div class="quest-name">
                            <i data-feather="check-circle" style="width: 14px; height: 14px; color: #7367F0;"></i> 
                            โทรติดตามลูกค้า (FO)
                        </div>
                        <span>8/10 <span class="quest-reward">+50</span></span>
                    </div>
                    <div class="progress-container">
                        <div class="progress-bar bg-primary" style="width: 80%;"></div>
                    </div>
                </div>

                <div class="quest-item">
                    <div class="quest-header">
                        <div class="quest-name">
                            <i data-feather="check-circle" style="width: 14px; height: 14px; color: #28C76F;"></i> 
                            ปิดการขาย (QT)
                        </div>
                        <span>5/5 <span class="quest-reward">+100</span></span>
                    </div>
                    <div class="progress-container">
                        <div class="progress-bar bg-success" style="width: 100%;"></div>
                    </div>
                </div>
            </div>

        </div>
    </div>




<link rel='stylesheet' href='https://unpkg.com/@yaireo/tagify/dist/tagify.css'>




 <script src="<c:url value="/app/js/display.js" />"></script>
 <script src="<c:url value="/app/js/word.js" />"></script>
    <script src="<c:url value="/app/js/report.js" />"></script>
 <script src="<c:url value="/app/js/script.js" />"></script>
  <script src="<c:url value="/app/js/nav.js" />"></script>
   <script src="<c:url value="/app/js/app.js" />"></script>
   <script src="<c:url value="/app/js/main.js" />"></script>
    <script src='https://unpkg.com/@yaireo/tagify'></script>
    <script src="<c:url value="/app/js/tag.js" />"></script>
        <script src="<c:url value="/app/js/table.js" />"></script>
           <script src="<c:url value="/app/js/calendar.js" />"></script>
    <script src="https://cdn.amcharts.com/lib/5/index.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
    <script src='https://cdn.rawgit.com/nizarmah/calendar-javascript-lib/master/calendarorganizer.min.js'></script>
  <script src="<c:url value="/app/js/ai.js" />"></script>




 <div id="context-extension" class="context-menu">
  
            <a href="#" id="extension_file" data-custom=""  
      onclick="fileByextension('/fileextension-filemanager',this)" style="display:none">ไฟล์เเนบ</a>  
      <a href="#" id="extension_edit" data-custom=""  
      onclick="editByextension('/editdataextension-filemanager',this)">เเก้ไข</a>
 
      <a href="#" id="extension_del" data-custom=""  onclick="deleteByextension('/deldataextension-filemanager',this)">ลบ</a>
  	<a href="#" id="extension_canceldel" data-custom5=""  onclick="canceleditByextension('/cancelDeleteByextension-filemanager',this)">ยกเลิกการลบ</a>

  
  </div>
 
<script>





// ---------------------------------------------------
//ระบบ Goals
// ---------------------------------------------------
// 1. เรียกใช้งาน Feather Icons
feather.replace();

// 2. ประกาศตัวแปรหลัก
const gamiWidget = document.getElementById('gamiWidget');
const gamiBody = document.getElementById('gamiBody');
const btnMinimize = document.getElementById('btnMinimize');
const btnClose = document.getElementById('btnClose');

// --- [A] ฟังก์ชันสำหรับเรียกเปิดจากภายนอก (Global Function) ---
window.showGamificationWidget = function() {
    if (gamiWidget) {
        gamiWidget.style.display = 'block';
        localStorage.setItem('gamiVisible', 'true');
        // โหลดข้อมูลทันทีที่เปิด
        loadGamificationData();
    }
};

// --- [B] ลอจิกการแสดงผลเบื้องต้นและตำแหน่ง ---
const isVisible = localStorage.getItem('gamiVisible');
const savedPos = localStorage.getItem('gamiWidgetPos');

// เช็คสถานะการเปิด/ปิดล่าสุด
if (isVisible === 'false') {
    gamiWidget.style.display = 'none';
} else {
    gamiWidget.style.display = 'block';
    // ถ้าเปิดอยู่ ให้โหลดข้อมูลทันที
    loadGamificationData();
}

// เช็คตำแหน่งล่าสุด
if (savedPos && gamiWidget) {
    const pos = JSON.parse(savedPos);
    gamiWidget.style.top = pos.top;
    gamiWidget.style.left = pos.left;
    gamiWidget.style.right = 'auto'; 
}

// --- [C] Event Listeners สำหรับปุ่มควบคุม ---

// ปุ่มย่อ/ขยาย
btnMinimize.addEventListener('click', () => {
    gamiBody.classList.toggle('hidden');
});

// ปุ่มปิด (X)
btnClose.addEventListener('click', () => {
    gamiWidget.style.display = 'none';
    localStorage.setItem('gamiVisible', 'false');
});

// --- [D] ลอจิกการจับลาก (Drag and Drop) ---
dragElement(gamiWidget);

function dragElement(elmnt) {
    if (!elmnt) return;
    var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
    var header = document.getElementById("gamiHeader");
    
    if (header) {
        header.onmousedown = dragMouseDown;
    } else {
        elmnt.onmousedown = dragMouseDown;
    }

    function dragMouseDown(e) {
        e = e || window.event;
        // ป้องกันการลากเมื่อคลิกโดนปุ่มกด
        if (e.target.closest('button')) return;
        
        e.preventDefault();
        pos3 = e.clientX;
        pos4 = e.clientY;
        document.onmouseup = closeDragElement;
        document.onmousemove = elementDrag;
    }

    function elementDrag(e) {
        e = e || window.event;
        e.preventDefault();
        pos1 = pos3 - e.clientX;
        pos2 = pos4 - e.clientY;
        pos3 = e.clientX;
        pos4 = e.clientY;
        
        elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
        elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
        elmnt.style.right = "auto"; 
    }

    function closeDragElement() {
        document.onmouseup = null;
        document.onmousemove = null;
        
        // บันทึกตำแหน่งลง LocalStorage
        const pos = {
            top: elmnt.style.top,
            left: elmnt.style.left
        };
        localStorage.setItem('gamiWidgetPos', JSON.stringify(pos));
    }
}
        
        
        
        
        
        
        
        


  document.addEventListener('contextmenu', function(event) {
       // event.preventDefault();
    });

  var contextMenu = document.getElementById('context-extension');
  const editBtn = document.getElementById('extension_edit');
  const delBtn = document.getElementById('extension_del');
  const cancelBtn = document.getElementById('extension_canceldel');
  
  
  
  function extensionMenu(event, element) {
      event.preventDefault();

   
      
      // ดึงค่า data-custom จาก item ที่กด
      const value = element.getAttribute("data-custom");
      const value2 = element.getAttribute("data-custom2");
      const value3 = element.getAttribute("data-custom3");
      const value4 = element.getAttribute("data-custom4");
      const value5 = element.getAttribute("data-custom5");
      const value6 = element.getAttribute("data-custom6");
      // ใส่ลงในปุ่ม context menu
      editBtn.setAttribute("data-custom", value);
      editBtn.setAttribute("data-custom2", value2);
      editBtn.setAttribute("data-custom3", value3);
      editBtn.setAttribute("data-custom4", value4);
      editBtn.setAttribute("data-custom6", value6);
      cancelBtn.setAttribute("data-custom5", value);
     
      delBtn.setAttribute("data-custom", value);
      delBtn.setAttribute("data-custom2", value5);
      
      // กำหนดตำแหน่งเมนู
      contextMenu.style.top = event.pageY + "px";
      contextMenu.style.left = event.pageX + "px";
      contextMenu.style.display = 'block';
  }

  // คลิกข้างนอก → ซ่อนเมนู
  document.addEventListener('click', function(event) {
      if (!contextMenu.contains(event.target)) {
          contextMenu.style.display = 'none';
      }
  });

  function deleteByextension(link, element) {
	  
	 
	  const value = element.getAttribute("data-custom");
	  const value2 = element.getAttribute("data-custom2");
	  const extension = document.getElementById('extension-'+value);

	  
	  getData(link+"/"+value, "saveextension");
	  
	  if(value2 == "0"){
		  extension.style.display = 'none';
	
	  }else{
		  extension.classList.add('deleted'); 
	  }  
	  contextMenu.style.display = 'none';
  }
  
  
  function canceleditByextension(link, element) {
	  
  
	  const value = element.getAttribute("data-custom5");
	  const extension = document.getElementById('extension-'+value);
	  

	  getData(link+"/"+value, "saveextension");
	  contextMenu.style.display = 'none';
	  if (extension) {
	        extension.classList.remove('deleted'); 
	    }
  }
  
  function editByextension(link, element) {
	  
		 
	  const value = element.getAttribute("data-custom");
	  const value2 = element.getAttribute("data-custom2");
	  const value3 = element.getAttribute("data-custom3");
	  const value4 = element.getAttribute("data-custom4");
	  const value6 = element.getAttribute("data-custom6");
	  const extension = document.getElementById('extension-'+value);
	  
	  getData(link+"/"+value+"/"+value2+"/"+value3+"/"+value4, "saveex-"+value4+value6);
	  contextMenu.style.display = 'none';

  }

  function fileByextension(link, element) {
	  
		 
	  const value = element.getAttribute("data-custom");
	  const value2 = element.getAttribute("data-custom2");
	  const value3 = element.getAttribute("data-custom3");
	  const value4 = element.getAttribute("data-custom4");
	  const extension = document.getElementById('extension-'+value);
	  
	  getData(link+"/"+value+"/"+value2+"/"+value3+"/"+value4, "saveex-"+value4);
	  contextMenu.style.display = 'none';

  }
  
  
  

</script>









<%@ include file="other/notification.jsp" %>



<script>

function savestatus(){

	  setTimeout(() => {
	    var notify = document.getElementById('notify');
	    if (notify) {
	      notify.style.transition = 'opacity 0.5s ease';
	      notify.style.opacity = '0';
	      setTimeout(() => notify.remove(), 500); // ลบหลังจางหาย
	    }
	  }, 1500);
}

</script>

<script>
      function toggleCheckboxes(checkbox,name) {
          var checkboxes = document.getElementsByName(name);
  
          for (var i = 0; i < checkboxes.length; i++) {
              checkboxes[i].checked = checkbox.checked;
          }
      }
  </script>



      <script>

var index = 99;
var windowsid = 0;  
let indexs = 1;	
const appIds = new Set();    


function removes(div){

if(confirm("คุณต้องการนำข้อมูลออก ?")){
   var element = document.getElementById(div);
   
   if(element){
     
      element.remove();
    }


}
return false;

}



function postDatawaitFor(url, formId, div,wait) {

  if(contextMenu_bin){
    contextMenu_bin.style.display = 'none';
  } 

  


    var element = document.getElementById("bar" + div);
    if (element) {
        element.style.display = 'block';
    }



    var formData = new FormData(document.getElementById(formId));

    var xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            document.getElementById(div).innerHTML = xhr.responseText;
            
            var fileInput = document.getElementById('file');
            if (fileInput) {
               fileInput.value = "";
            }

              if(wait){
            	  waitForElementAndLoad(wait);
              }


        } else {
          // แสดงข้อความข้อผิดพลาดที่เป็นมิตร
        var userFriendlyMessage = xhr.responseText || errorMessage;
        alert(userFriendlyMessage);
        
        // หรือคุณอาจแสดงข้อความใน div เฉพาะได้
        if (responseDiv) {
            responseDiv.innerHTML = `<p style="color: red;">${userFriendlyMessage}</p>`;
        }

        // Log ข้อผิดพลาด
        console.error(`HTTP error! Status: ${xhr.status}, Response: ${xhr.responseText}`);
        }

        if (element) {
            element.style.display = 'none';
        }
    };

    xhr.onerror = function () {
        alert('Network error occurred');
    };

    // Send the form data in the request body
    xhr.send(formData);
}








function confirmAndDelete(link,myForm,mainbody) {
    if (confirm('Are you sure you want to delete?')) {
        postData(link, myForm,mainbody);
       
    }
}

function confirmAndGet(link,mainbody,confirms) {
    if (confirm(confirms)) {
        getData(link,mainbody);
       
    }
    
    return false;
}

function confirmAndGet_closeed2(link,mainbody,confirms,call) {
    if (confirm(confirms)) {
        getData(link,mainbody);
        closeed2(call);
    }
    
    return false;
}




function openApp(url, div, del = undefined) {
	
	

    

    var confirms = true;
       if(del !== undefined ){
            if(confirm("คุณต้องการนำข้อมูลออก ?")){
                   var element = document.getElementById(del);
                 if(element){
                      element.remove();
                    }
            }else{
                  confirms = false;
                }
          }

 if(confirms == true){         
      var element = document.getElementById("bar" + div);
      if (element) {
          element.style.display = 'block';
      }

      var xhr = new XMLHttpRequest();
      xhr.open('GET', url, true);
      xhr.timeout = 60000; 
      xhr.onload = function () {
          if (xhr.status >= 200 && xhr.status < 300) {
              var responseURL = xhr.responseURL;
              if (responseURL && responseURL.indexOf('login') !== -1) {
                  // ถ้า URL ของการตอบสนองมี "login", แสดงว่า session หมดอายุ
                  alert('Session หมดอายุ กรุณา login ใหม่');
                  window.location.href = '/login'; // หรือ redirect ไปยังหน้า login
              } else {

            		var container = document.getElementById(div);
                    container.innerHTML = xhr.responseText; // 1. ยัด HTML เข้าไปก่อน
                	runScripts(container);
       
            		 
            	
              	  
              }
          } else {
              alert(`HTTP error! Status: ${xhr.status}`);
          }
          if (element) {
              element.style.display = 'none';
          }
      };

      xhr.onerror = function () {
          alert('Network error occurred');
          element.style.display = 'none';
      };

   xhr.ontimeout = function () {
    alert('คำขอใช้เวลานานเกินไป กรุณาลองใหม่อีกครั้ง');
        if (element) {
            element.style.display = 'none';
        }
    };

      xhr.send();
    }

}



function getData(url, div, appId = undefined) {
	
			//document.getElementById(div).innerHTML = "";
            var element = document.getElementById("bar" + div);
            if (element) {
                element.style.display = 'block';
            }

            var xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.timeout = 60000; 
            xhr.onload = function () {
                if (xhr.status >= 200 && xhr.status < 300) {
                    var responseURL = xhr.responseURL;
                    if (responseURL && responseURL.indexOf('login') !== -1) {
                        // ถ้า URL ของการตอบสนองมี "login", แสดงว่า session หมดอายุ
                        alert('Session หมดอายุ กรุณา login ใหม่');
                        window.location.href = '/login'; // หรือ redirect ไปยังหน้า login
                    } else {
                        
                    	//document.getElementById(div).innerHTML = xhr.responseText;
                    	
                    	var container = document.getElementById(div);
                        container.innerHTML = xhr.responseText; // 1. ยัด HTML เข้าไปก่อน
                    	runScripts(container);
                        
                        
                    	if(appId ) { tagifyAll(appId); }
                    }
                } else {
                    alert(`HTTP error! Status: ${xhr.status}`);
                }
                if (element) {
                    element.style.display = 'none';
                }
            };

            xhr.onerror = function () {
                alert('Network error occurred' + url);
              
            };

         xhr.ontimeout = function () {
          alert('คำขอใช้เวลานานเกินไป กรุณาลองใหม่อีกครั้ง');
              if (element) {
                  element.style.display = 'none';
              }
          };

            xhr.send();
        

}







function closeed(name,appId){

      var element = document.getElementById(name+appId);
      element.remove();
      appIds.delete(appId);
      
      if (!appIds.has(appId)) {
		  console.log(`delete has:`+appId);
	  }
      
      
       
      var taskbar = document.getElementById("taskbar"+appId);
      taskbar.remove();
      
      deltagifyAll(appId);


      }
      
function Apphas(appId) {
	  if (appIds.has(appId)) {
		  console.log(`appId has:`+appId);
	    return false;
	  }

	  appIds.add(appId);
	  console.log(`เพิ่ม appId ใหม่: `+appId);
	    return true;
	
	}
	
	
	
	
	
function makeDraggable(elementId) {
    const win = document.getElementById('draggable-windows' + elementId);
    const header = document.getElementById('header-microsofts' + elementId);

    if (!win || !header) return;
    if (win.dataset.draggable === 'true') return;
    win.dataset.draggable = 'true';

    let offsetX = 0;
    let offsetY = 0;
    let dragging = false;

    // --- ส่วนที่ 1: คลิกที่ไหนก็ได้บนวินโดว์เพื่อดึงมาข้างหน้า (ยกเว้นปุ่ม) ---
    win.addEventListener('mousedown', function (e) {
        // ถ้าคลิกโดนปุ่ม (.btn) หรือลูกของปุ่ม (<i>) ไม่ต้องรัน z-index
        if (e.target.closest('.btn')) {
            return; 
        }
        
        indexs = (indexs || 1000) + 1;
        win.style.zIndex = indexs;
        
        // เพิ่มลูกเล่น: ใส่ class เพื่อทำ animation นุ่มๆ ตอนสลับหน้าต่างที่คุยกันก่อนหน้า
        win.classList.remove('window-focus-animate');
        void win.offsetWidth; 
        win.classList.add('window-focus-animate');
    });

    // --- ส่วนที่ 2: การลากผ่าน Header ---
    header.addEventListener('mousedown', function (e) {
        // สำคัญมาก: ถ้าคลิกโดนปุ่มบน Header ต้อง "หยุด" ไม่ให้เริ่มลากวินโดว์
        if (e.target.closest('.btn')) {
            return; 
        }

        e.preventDefault();
        dragging = true;

        // คำนวณ offset
        const rect = win.getBoundingClientRect();
        offsetX = e.clientX - rect.left;
        offsetY = e.clientY - rect.top;

        function onMouseMove(ev) {
            if (!dragging) return;
            win.style.left = (ev.clientX - offsetX) + 'px';
            win.style.top = (ev.clientY - offsetY) + 'px';
        }

        function onMouseUp() {
            dragging = false;
            document.removeEventListener('mousemove', onMouseMove);
            document.removeEventListener('mouseup', onMouseUp);
        }

        document.addEventListener('mousemove', onMouseMove);
        document.addEventListener('mouseup', onMouseUp);
    });

    bringToFront(elementId);
    // ส่วนของ Touch (ถ้าใช้) ก็ให้ใส่เช็ค e.target.closest('.btn') เช่นกันครับ
}
	

   
function Addtaskbar(element){
	
	const elementId = element.dataset.id;
    const name = element.dataset.name;
    const logo = element.dataset.logo;
  

    
	   var element = document.getElementById("dockposition");
	   var html=`	 <div class="icon" id="taskbar`+elementId+`"  tabindex="1" onclick="expanded('taskbar`+elementId+`','draggable-windows`+elementId+`');">
		     <img src='`+logo+`' style="width:75%;margin:auto">
	         <div class="title">`+name+`</div>
	       </div>`;
	   element.insertAdjacentHTML('beforeend', html);
}
   



	   function App2(element){
			
			const elementId = element.dataset.id;
		    const name = element.dataset.name;
		    const logo = element.dataset.logo;
		    const box= element.dataset.box;
		    const link= element.dataset.link;
		    const from= element.dataset.from;
		    const from_type= element.dataset.from_type;
		    const process_refer= element.dataset.process_refer;

		    
		    
		    
				  if(Apphas(elementId) == false){
					  closeed('draggable-windows',elementId);
					  Apphas(elementId);
				
				  }

		           newWindow(elementId);	  
		           openApp(link+elementId+"?from="+from+"&&from_type="+from_type+"&&process_refer="+process_refer,'microsofts'+elementId);
		           
		         //  alert(link+elementId+"?from="+from+"+&&from_type="+from_type);
		           
		           Addtaskbar(element);
		           
		        
		           var title = document.getElementById('app'+elementId);
		           title.innerText = name+box;
		           
		           var icon = document.getElementById('icon'+elementId);
		           icon.innerHTML = " <img src='"+logo+"' style='width:25px'>";
		           
		           var element = document.getElementById('draggable-windows'+elementId);
		           element.classList.remove('slide-out');	
		           element.style.display = 'block';    
		           element.style.zIndex = indexs+2;
		}
		      


      function expanded(taskbar,windows){

   
    	  var element = document.getElementById(windows);
    	  element.classList.remove('slide-out');	
    	  
    	  element.classList.add('window-focus-animate');
    	   element.classList.add('show');

    	   
    	   var dock = document.getElementById(taskbar);
    	   dock.style.backgroundColor = "#FFF";;
      }


      function shows2(elementId,title){
    	  
      var element = document.getElementById(elementId);
      element.classList.remove('slide-out');	
      element.style.display = 'block';
      element.style.zIndex = indexs+2;
  
      if(elementId == "draggable-window3"){
    	  var setting = document.getElementById("materialsystem");
          setting.innerText = title;
 
      
      }
      if(elementId == "draggable-window2"){

    	  var setting = document.getElementById("app_draggable-window2");
          setting.innerText = title;
      
       
      }
      if(elementId == "draggable-window6" ){
	  var setting = document.getElementById("miniwindows");
	          setting.innerText = title;
	   
	       
	      }
	      
    
    
      
      

      }
      
      
      
      
      
      
      function openwindows(elementId, title, width, customZIndex) {
    	    var element = document.getElementById(elementId);
    	    
    	    // 1. จัดการการแสดงผลและการเคลื่อนไหว
    	    element.classList.remove('slide-out');
    	    element.style.display = 'block';
    	    
    	    // 🌟 จัดการ Z-Index (ถ้ามีการส่งค่า customZIndex มาให้ใช้ค่านั้น ถ้าไม่มีใช้ indexs + 2)
    	    if (customZIndex !== undefined && customZIndex !== null) {
    	        element.style.zIndex = customZIndex;
    	    } else {
    	        element.style.zIndex = indexs + 2;
    	    }

    	    // 2. กำหนดความกว้าง (ถ้ามีการส่งค่ามา)
    	    if (width) {
    	        element.style.setProperty('width', width, 'important');
    	        
    	        // ปลดล็อค min/max width ที่ล็อคไว้ใน CSS
    	        element.style.setProperty('min-width', '0px', 'important'); 
    	        element.style.setProperty('max-width', 'none', 'important');
    	    }

    	    // 3. กำหนด Title ให้ตรงกับ Window ที่เปิด (ปรับเป็น else if เพื่อลดภาระการประมวลผล)
    	    if (elementId === "draggable-window3") {
    	        document.getElementById("materialsystem").innerText = title;
    	    } else if (elementId === "draggable-window2") {
    	        document.getElementById("app_draggable-window2").innerText = title;
    	    } else if (elementId === "draggable-window6") {
    	        document.getElementById("miniwindows").innerText = title;
    	    }
    	}
      
      
      

      function setTitleApp(elementId,title){
    	  
    	  var element = document.getElementById(elementId);
    	  	element.innerText = title;   
    	  }
      
      

      function closeed2(elementId){
    	    var element = document.getElementById(elementId);
    	    element.style.display = 'none';
    	   
      }
      
      

      function toggle(elementId) {
    	  var el = document.getElementById(elementId);
    	  if (!el) return;

    	  el.classList.toggle("visible");
    	  el.classList.toggle("hidden");
    	}
      
      
      
     function minimize(windows,elementId){
  
    	 
    var element = document.getElementById(windows+elementId);
    element.classList.remove('window-focus-animate');
    element.classList.remove('show');
    
    element.classList.add('slide-out');
    element.addEventListener('animationend', function () {
    element.style.display = 'none';
    });
    

    var dock = document.getElementById("taskbar"+elementId);
    dock.style.backgroundColor = "#449";
    
    /*
      if (element) {
  

      
  
   
      
      if(elementId == 'draggable-windows'){
      
        var dock = document.getElementById("dock_admin");
        dock.style.backgroundColor = "#449";
      }
      if(elementId == 'draggable-window2'){
      
        var dock = document.getElementById("dock_training");
        dock.style.backgroundColor = "#337ab7";
      }
      
      }
      */
      }
      
       var isExpanded = true;
  
    function toggleExpand() {
      var windowElement = document.getElementById('draggable-window');
  
      isExpanded = !isExpanded;
      
      if (isExpanded) {
        windowElement.classList.add('expanded');
      
  
        
      
      } else {
        windowElements.classList.remove('expanded');
      
    
        
      }
    }
     
  
      
      
    
 
    
   


  var isDragging3 = false;
  var offsetX3, offsetY3;
  var originalZIndex3;	
  var header3 = document.querySelector('.system');
  var draggableWindow3 = document.getElementById('draggable-window3');


  draggableWindow3.addEventListener('click', function (e) {
	    
	    // แปลงค่า zIndex ปัจจุบันเป็นตัวเลข (ถ้าไม่มีค่าให้ถือเป็น 0)
	    let currentZ = parseInt(draggableWindow3.style.zIndex) || 0;

	    // เช็คเงื่อนไข: ถ้าค่าของฉัน น้อยกว่า ค่าสูงสุดในระบบ ค่อยอัปเดต
	    if (currentZ < indexs) {
	        indexs++;
	        draggableWindow3.style.zIndex = indexs;
	    }
	    
	});

  header3.addEventListener('mousedown', function (e) {
      isDragging3 = true;

      // Save the original z-index
      originalZIndex3 = draggableWindow3.style.zIndex;
  

  
      offsetX3 = e.clientX - draggableWindow3.getBoundingClientRect().left;
      offsetY = e.clientY - draggableWindow3.getBoundingClientRect().top;
  });
  
  
  
  
  
  document.addEventListener('mousemove', function (e) {
      if (isDragging3) {
          var x = e.clientX - offsetX3;
          var y = e.clientY - offsetY;
          draggableWindow3.style.left = x + 'px';
          draggableWindow3.style.top = y + 'px';
          
      }

      
  });
  
  document.addEventListener('mouseup', function () {
      if (isDragging3) {
          isDragging3 = false;
  
          // Set back the original z-index or a default value
         // draggableWindow3.style.zIndex = originalZIndex || '10';
      }
  });	






  var isDragging6 = false;
  var offsetX6, offsetY6;
  var originalZIndex6;	
  var header6 = document.querySelector('.setting');
  var draggableWindow6 = document.getElementById('draggable-window6');



  draggableWindow6.addEventListener('click', function (e) {
 
    var currentZIndex6 = parseInt(draggableWindow6.style.zIndex) || 0;
    if (currentZIndex6 <= 5000) {
        index++;
        draggableWindow6.style.zIndex = index;
    }
    
  });
  header6.addEventListener('mousedown', function (e) {
      isDragging6 = true;

      // Save the original z-index
      originalZIndex6 = draggableWindow6.style.zIndex;

  
      offsetX6 = e.clientX - draggableWindow6.getBoundingClientRect().left;
      offsetY = e.clientY - draggableWindow6.getBoundingClientRect().top;
  });
  
  document.addEventListener('mousemove', function (e) {
      if (isDragging6) {
          var x = e.clientX - offsetX6;
          var y = e.clientY - offsetY;
          draggableWindow6.style.left = x + 'px';
          draggableWindow6.style.top = y + 'px';
      }

  });
  
  document.addEventListener('mouseup', function () {
      if (isDragging6) {
          isDragging6 = false;
  
          // Set back the original z-index or a default value
        //  draggableWindow5.style.zIndex = originalZIndex || '12';
      }
  });	






  //app

    var apps = {
       init: function(){
          this.surface.addEventListener('click', function(e){ apps.leftClick(event) })
          this.surface.addEventListener('contextmenu', function(e){ apps.rightClick(event) })
          var li = this.menu.getElementsByTagName('li')
          for(var i = 0; i < li.length; i++){
             li[i].addEventListener('click', function(){
                apps.leftClick(event, this)
             });
          }
       },
       surface: document.getElementById('surface'),
       display: document.getElementById('display'),
       menu: document.getElementById('menu'),
       x: 0,
       y: 0,
       rightClick: function(event){
          event.preventDefault()
          this.menu.style.display = ''
          
          var cx = event.offsetX
          var cy = event.offsetY
          
          var maxWidth = this.surface.offsetWidth
          var maxHeight = this.surface.offsetHeight
          
          if(this.menu.offsetWidth + cx > maxWidth) 
             cx -= this.menu.offsetWidth
          if(this.menu.offsetHeight + cy > maxHeight) 
             cy -= this.menu.offsetHeight
          
          this.menu.style.left = cx + 'px'
          this.menu.style.top = cy + 'px'
          this.menu.style.opacity = '0'
          this.menu.style.transition = ''
          setTimeout(function(){
             apps.menu.style.opacity = '1'
             apps.menu.style.transition = 'opacity 1s'
          }, 0);
       },
       leftClick: function(event, li = false){
          this.menu.style.display = 'none'
          if(li)
             this.display.innerHTML = 'You clicked: ' + li.innerHTML;
       }
    }; 
  
    //apps.init();

  function testopen(){

   am5.ready(function() {


// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chart");


// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);


// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root.container.children.push(am5xy.XYChart.new(root, {
  panX: false,
  panY: false,
  wheelX: "panX",
  wheelY: "zoomX",
  paddingLeft: 0,
  layout: root.verticalLayout
}));


// Add legend
// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
var legend = chart.children.push(am5.Legend.new(root, {
  centerX: am5.p50,
  x: am5.p50
}));


var data = [];

// Get all rows in the tbody
var rows = document.querySelectorAll('#dt-table tbody tr');

// Loop through each row
rows.forEach(function (row) {
    var rowData = {};

    // Get values from each cell using the class names
    rowData.year = row.querySelector('.data1').textContent;
    rowData.europe = parseFloat(row.querySelector('.data2').textContent);
    rowData.namerica = parseFloat(row.querySelector('.data3').textContent);

    if(row.querySelector('.data4')){
        rowData.namerica2 = parseFloat(row.querySelector('.data4').textContent);
    }


    // Push the rowData object to the data array
    data.push(rowData);
});

console.log(data);


// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xRenderer = am5xy.AxisRendererX.new(root, {
  cellStartLocation: 0.1,
  cellEndLocation: 0.9,
  minorGridEnabled: true
});

var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
  categoryField: "year",
  renderer: xRenderer,
  tooltip: am5.Tooltip.new(root, {})
}));

xRenderer.grid.template.setAll({
  location: 1
})

xAxis.data.setAll(data);

var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
  min: 0,
  renderer: am5xy.AxisRendererY.new(root, {
    strokeOpacity: 0.1
  })
}));


// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
function makeSeries(name, fieldName, stacked) {
  var series = chart.series.push(am5xy.ColumnSeries.new(root, {
    stacked: stacked,
    name: name,
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: fieldName,
    categoryXField: "year"
  }));

  series.columns.template.setAll({
    tooltipText: "{name}, {categoryX}:{valueY}",
    width: am5.percent(90),
    tooltipY: am5.percent(10)
  });
  series.data.setAll(data);

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  series.appear();

  series.bullets.push(function () {
    return am5.Bullet.new(root, {
      locationY: 0.5,
      sprite: am5.Label.new(root, {
        text: "{valueY}",
        fill: root.interfaceColors.get("alternativeText"),
        centerY: am5.percent(50),
        centerX: am5.percent(50),
        populateText: true
      })
    });
  });

  legend.data.push(series);
}

makeSeries(document.getElementById('main1').textContent, "europe", false);
makeSeries(document.getElementById('main2').textContent, "namerica", true);

if(document.getElementById('main3')){
  makeSeries(document.getElementById('main3').textContent, "namerica2", true);
}


// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
chart.appear(1000, 100);

}); // end am5.ready()

  }








  function toggleClass(element, toggleClass) {
            var currentClass = element.className || '';
            var newClass;
            if (currentClass.split(' ').indexOf(toggleClass) > -1) { //has class
                newClass = currentClass.replace(new RegExp('\\b' + toggleClass + '\\b', 'g'), '')
            } else {
                newClass = currentClass + ' ' + toggleClass;
            }
            element.className = newClass.trim();
        }


  function controlsCenter(page){
    
    var center = document.getElementById('controlCenter');
    var right = document.getElementById('rightMenu');
    

    if(center.classList.contains('active')){
        //toggleClass(center, 'active');
        hideControlsFromCenter();
        document.querySelector('div[for="'+page+'"]').classList.add("visible");
        return false;
    }
        toggleClass(center, 'active');
        
            hideControlsFromCenter();
            
    
        document.querySelector('div[for="'+page+'"]').classList.add("visible");
    
 
    
    
}



"use strict";

// function that creates dummy data for demonstration
function createDummyData() {
  var date = new Date();
  var data = {};

  for (var i = 0; i < 2; i++) {
    data[date.getFullYear() + i] = {};

    for (var j = 0; j < 2; j++) {
      data[date.getFullYear() + i][j + 1] = {};

      for (var k = 0; k < Math.ceil(Math.random() * 10); k++) {
        var l = Math.ceil(Math.random() * 28);

        try {
          data[date.getFullYear() + i][j + 1][l].push({
            startTime: "10:00",
            endTime: "12:00",
            text: "Some Event Here"
          });
        } catch (e) {
          data[date.getFullYear() + i][j + 1][l] = [];
          data[date.getFullYear() + i][j + 1][l].push({
            startTime: "10:00",
            endTime: "12:00",
            text: "Some Event Here"
          });
        }
      }
    }
  }

  return data;
}



  </script>

    </html>



    <script th:inline="javascript">




    function getcalendarData(url, div, appId = undefined) {
    	
    	
                var element = document.getElementById("bar" + div);
                if (element) {
                    element.style.display = 'block';
                }

                var xhr = new XMLHttpRequest();
                xhr.open('GET', url, true);
                xhr.timeout = 60000; 
                xhr.onload = function () {
                    if (xhr.status >= 200 && xhr.status < 300) {
                        var responseURL = xhr.responseURL;
                        if (responseURL && responseURL.indexOf('login') !== -1) {
                            // ถ้า URL ของการตอบสนองมี "login", แสดงว่า session หมดอายุ
                            alert('Session หมดอายุ กรุณา login ใหม่');
                            window.location.href = '/login'; // หรือ redirect ไปยังหน้า login
                        } else {
                        	loadcalendar(xhr.responseText,div);
                        }
                    } else {
                        alert(`HTTP error! Status: ${xhr.status}`);
                    }
                    if (element) {
                        element.style.display = 'none';
                    }
                };

                xhr.onerror = function () {
                    alert('Network error occurred');
                    element.style.display = 'none';
                };

             xhr.ontimeout = function () {
              alert('คำขอใช้เวลานานเกินไป กรุณาลองใหม่อีกครั้ง');
                  if (element) {
                      element.style.display = 'none';
                  }
              };

                xhr.send();
            

    }
 function    loadcalendar(data,div){
      // creating the dummy static data
        var parsedData;
    try {
        parsedData = JSON.parse(data);
    } catch(e) {
        console.error("Error parsing JSON:", e);
        parsedData  = createDummyData()
       // return;
    }

      

console.log(parsedData);
document.getElementById(div).innerHTML = '<div style="width:100%"><div id="calendarContainer" ></div><div id="organizerContainer"></div></div>';
// initializing a new calendar object, that will use an html container to create itself
var calendar = new Calendar(
  "calendarContainer", // id of html container for calendar
  "small", // size of calendar, can be small | medium | large
  [
    "Wednesday", // left most day of calendar labels
    15 // maximum length of the calendar labels
  ],
  [
    "#E91E63", // primary color
    "#C2185B", // primary dark color
    "#FFFFFF", // text color
    "#F8BBD0" // text dark color
  ]
);

// initializing a new organizer object, that will use an html container to create itself
var organizer = new Organizer(
  "organizerContainer", // id of html container for calendar
  calendar, // defining the calendar that the organizer is related to
  parsedData // giving the organizer the static data that should be displayed
);

 }

 </script>



<script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.js'></script>




<script>
function randerchart(){

  var poin1 = document.getElementById("poin1");
  var poin2 = document.getElementById("poin2");
  var poin3 = document.getElementById("poin3");
  var poin4 = document.getElementById("poin4");
  var poin5 = document.getElementById("poin5");
  var poin6 = document.getElementById("poin6");

      var canvas = document.getElementById("canvas");

      var gradientBlue = canvas.getContext('2d').createLinearGradient(0, 0, 0, 150);
      gradientBlue.addColorStop(0, 'rgba(85, 85, 255, 0.9)');
      gradientBlue.addColorStop(1, 'rgba(151, 135, 255, 0.8)');

      var gradientHoverBlue = canvas.getContext('2d').createLinearGradient(0, 0, 0, 150);
      gradientHoverBlue.addColorStop(0, 'rgba(65, 65, 255, 1)');
      gradientHoverBlue.addColorStop(1, 'rgba(131, 125, 255, 1)');

      var gradientRed = canvas.getContext('2d').createLinearGradient(0, 0, 0, 150);
      gradientRed.addColorStop(0, 'rgba(255, 85, 184, 0.9)');
      gradientRed.addColorStop(1, 'rgba(255, 135, 135, 0.8)');

      var gradientHoverRed = canvas.getContext('2d').createLinearGradient(0, 0, 0, 150);
      gradientHoverRed.addColorStop(0, 'rgba(255, 65, 164, 1)');
      gradientHoverRed.addColorStop(1, 'rgba(255, 115, 115, 1)');

      var redArea = null;
      var blueArea = null;

      var shadowed = {
        beforeDatasetsDraw: function(chart, options) {
          chart.ctx.shadowColor = 'rgba(0, 0, 0, 0.25)';
          chart.ctx.shadowBlur = 40;
        },
        afterDatasetsDraw: function(chart, options) {
          chart.ctx.shadowColor = 'rgba(0, 0, 0, 0)';
          chart.ctx.shadowBlur = 0;
        }
      };

      Chart.plugins.register({
        afterEvent: function(chart, e) {
          // Hardcoded hover areas
          
          // Red chart
          chart.ctx.beginPath();
          chart.ctx.moveTo(91, 69);
          chart.ctx.lineTo(152, 80);
          chart.ctx.lineTo(192, 75);
          chart.ctx.lineTo(213, 138);
          chart.ctx.lineTo(148, 168);
          chart.ctx.lineTo(105, 126);
          chart.ctx.fill();
          chart.ctx.closePath();
          
          if (chart.ctx.isPointInPath(e.x, e.y)) {
            var dataset = window.chart.data.datasets[0];
            dataset.backgroundColor = gradientHoverRed;
            window.chart.update();
            canvas.style.cursor = 'pointer';
          } else {
            var dataset = window.chart.data.datasets[0];
            dataset.backgroundColor = gradientRed;
            window.chart.update();
            canvas.style.cursor = 'default';
          }
          
          // Blue chart
          chart.ctx.beginPath();
          chart.ctx.moveTo(85, 61);
          chart.ctx.lineTo(149, 66);
          chart.ctx.lineTo(224, 63);
          chart.ctx.lineTo(179, 112);
          chart.ctx.lineTo(152, 177);
          chart.ctx.lineTo(121, 117);
          chart.ctx.fill();
          chart.ctx.closePath();
          
          if (chart.ctx.isPointInPath(e.x, e.y)) {
            var dataset = window.chart.data.datasets[1];
            dataset.backgroundColor = gradientHoverBlue;
            window.chart.update();
            canvas.style.cursor = 'pointer';
          } else {
            var dataset = window.chart.data.datasets[1];
            dataset.backgroundColor = gradientBlue;
            window.chart.update();
            canvas.style.cursor = 'default';
          }
        }
      });

      window.chart = new Chart(document.getElementById("canvas"), {
          type: "radar",
          data: {
              labels: ["KSA", "EXP", "ABIL", "TTM", "COM", "ENT"],
              datasets: [{
                  label: "Donté Panlin",
                  data: [poin1.value, poin2.value, poin3.value, poin4.value, poin5.value, poin6.value],
                  fill: true,
                  backgroundColor: gradientRed,
                  borderColor: 'transparent',
                  pointBackgroundColor: "transparent",
                  pointBorderColor: "transparent",
                  pointHoverBackgroundColor: "transparent",
                  pointHoverBorderColor: "transparent",
                  pointHitRadius: 50,
              }
              
         
              , {
                  label: "ค่าเฉลี่ย",
                  data: [0, 0, 0, 0, 0, 0],
                  fill: true,
                  backgroundColor: gradientBlue,
                  borderColor: "transparent",
                  pointBackgroundColor: "transparent",
                  pointBorderColor: "transparent",
                  pointHoverBackgroundColor: "transparent",
                  pointHoverBorderColor: "transparent",
                  pointHitRadius: 50,
              }
        
            
            ]
          },
          options: {
            legend: {
              display: false,
            },
            tooltips: {
              enabled: false,
              custom: function(tooltip) {
                  var tooltipEl = document.getElementById('tooltip');
                  if (tooltip.body) {
                    tooltipEl.style.display = 'block';
                    if (tooltip.body[0].lines && tooltip.body[0].lines[0]) {
                      tooltipEl.innerHTML = tooltip.body[0].lines[0];
                    }
                  } else {
                    setTimeout(function() {
                      tooltipEl.style.display = 'none';
                    }, 500);
                  }
              },
            },
            gridLines: {
              display: false
            },
            scale: {
              ticks: {
                  maxTicksLimit: 1,
                  display: false,
              }
            }
          },
          plugins: [shadowed]
      });

}


function switchminichart(id){

  if(id == 1){
      var element = document.getElementById("canvas");
      element.style.display = "none";

      var element = document.getElementById("grade");
      element.style.display = "block";

  }else{
          var element = document.getElementById("grade");
      element.style.display = "none";

      var element = document.getElementById("canvas");
      element.style.display = "block";

  }

   

}







</script>

<script>
//1. ฟังก์ชันคำนวณ Badge (ปรับปรุงใหม่เช็คสถานะการแสดงผลแทนตัวเลข)
function updateReferenceBadges() {
    // เลือก div .apps ทั้งหมดที่มี data-reference
    var parentApps = document.querySelectorAll(".apps[data-reference]");

    parentApps.forEach(function(app) {
        var refString = app.getAttribute("data-reference");
        
        // ถ้าไม่มี reference ให้ข้าม
        if (!refString || refString.trim() === "") return;

        var refIds = refString.split(',');
        
        // --- ส่วนตรวจสอบลูก (ปรับใหม่ ใช้ Flag แทนการบวกเลข) ---
        var hasActiveChild = false; // ตั้งค่าเริ่มต้นว่า "ไม่มีลูกตัวไหนเตือน"

        refIds.forEach(function(id) {
            var cleanId = id.trim();
            if(cleanId === "") return;
            
            // หา Badge ลูก
            var targetBadge = document.getElementById("badge-" + cleanId);
            
            // เช็คว่ามี Badge ลูก และไม่ได้ถูกซ่อนอยู่ (สมมติว่าตอนซ่อนใช้ style.display = "none")
            if (targetBadge && targetBadge.style.display !== "none") {
                hasActiveChild = true; // เจอว่ามีลูกอย่างน้อย 1 ตัวแจ้งเตือน
            }
        });

        // --- ส่วนอัปเดต Badge แม่ ---
        // ดึง ID ของ App ตัวแม่มา
        var parentId = app.getAttribute("data-id"); 
        
        // ประกอบร่างเป็น ID ของ Badge แม่
        var myBadge = document.getElementById("badge-" + parentId);

        if (myBadge) {
            myBadge.innerText = "!"; // ให้แม่แสดง ! เสมอเมื่อโชว์

            // ซ่อน/แสดง ตามสถานะของลูก
            if (hasActiveChild) {
                myBadge.style.display = ""; // แสดงผล เพราะมีลูกอย่างน้อย 1 ตัวโชว์ !
            } else {
                myBadge.style.display = "none"; // ซ่อน เพราะไม่มีลูกตัวไหนโชว์เลย
            }
        }
    });
}
// 2. สั่งให้ทำงานทันทีเมื่อหน้าเว็บโหลดเสร็จ (รอบแรก)
document.addEventListener("DOMContentLoaded", function() {
	updateReferenceBadges();
});

// 3. ใส่ใน setInterval เพื่อทำงานรอบต่อไป
setInterval(function() {
    // งานเดิม: Keep Alive
    fetch('/keep_alive', { method: 'GET' }); 
    
    // งานใหม่: คำนวณ Badge อีกครั้ง (เผื่อค่าลูกเปลี่ยน)
    updateReferenceBadges(); 
    
}, 600000);

  </script>



<div style="display:none">

<title>App Center</title>


 <div class="notifications">
    <div class="notif-button" id="notif-icon">
      <img  class="icons" src="https://img.icons8.com/ios-filled/50/user-male-circle.png" alt="User"/>
      <span>${Member_name }</span>
    </div>
    <div class="notif-dropdown" id="notif-dropdown">
       <div class="notif-item"> <a href="/" style="text-decoration: none; color: inherit;">🏚️ HOME</a></div>
      <div class="notif-item" style="display:none">📢 New update available</div>
  
      <div class="notif-item" style="display:none">🔔 Reminder: Submit report</div>
    </div>
  </div>


  <div class="container">
    <h2>I-DOC Apps</h2>
    <div class="search-box">
      <input type="text" id="search" placeholder="Search apps...">
    </div>
    <div class="grid">
    
    
               <c:forEach items="${workflow }" var="row" varStatus="count">
             
			<c:if test='${row.workflow_id >  0}'>

   							<c:forEach items="${row.process }" var="rows">
             			
						                     
						                          <div class="app">
    
        
             <c:if test='${rows.alert > 0}'> 
                 <span class="notif-badge">${rows.alert}  </span>
     		     </c:if>
		
				 <c:set var="logo" value="qexcellent.png" />
				     
				      <c:if test='${rows.process_logo  != null && rows.process_logo  != "" }'>
				       <c:set var="logo" value="${rows.process_logo}" />	
				       </c:if>
				
				
				
          <a  href="/app-filemanager/${rows.id}" target="_blank"> 
        <img class="icon" src="/resources/app-assets/images/icons/APP/${logo}" style="width:55px;height:48px;" />
              </a>    
              ${rows.name }
      </div>
						              
                                      </c:forEach>   

                    </c:if>     
                        
                                          </c:forEach>  
                  
    
      
      
      
    </div>
  </div>




  <script>
    window.addEventListener('DOMContentLoaded', () => {
      const apps = document.querySelectorAll('.app');
      const searchInput = document.getElementById('search');
      const notifIcon = document.getElementById('notif-icon');
      const notifDropdown = document.getElementById('notif-dropdown');

      // App selection
      apps.forEach(app => {
        app.addEventListener('click', () => {
          apps.forEach(el => el.classList.remove('active'));
          app.classList.add('active');
        });
      });

      // Search filter
      searchInput.addEventListener('input', () => {
        const keyword = searchInput.value.toLowerCase();
        apps.forEach(app => {
          const text = app.textContent.toLowerCase();
          app.style.display = text.includes(keyword) ? 'flex' : 'none';
        });
      });

      // Notification dropdown
      notifIcon.addEventListener('click', () => {
        notifDropdown.classList.toggle('show');
      });

      // Close dropdown when clicking outside
      document.addEventListener('click', (e) => {
        if (!notifDropdown.contains(e.target) && !notifIcon.contains(e.target)) {
          notifDropdown.classList.remove('show');
        }
      });
    });
    
    
    
    
    

    
  </script>
  
  <script>
function initVerticalSplitters() {
  const splitters = document.querySelectorAll('.splitter-v');

  splitters.forEach(splitter => {
    const first = splitter.querySelector('.pane.first');
    const second = splitter.querySelector('.pane.second');
    const separator = splitter.querySelector('.separator');

    let startY, startFirstHeight, startSecondHeight;

    separator.addEventListener('mousedown', e => {
      startY = e.clientY;
      startFirstHeight = first.offsetHeight;
      startSecondHeight = second.offsetHeight;

      document.body.style.userSelect = 'none';
      document.addEventListener('mousemove', onMouseMove);
      document.addEventListener('mouseup', onMouseUp);
    });

    function onMouseMove(e) {
      const dy = e.clientY - startY;
      const min = 30; // ความสูงขั้นต่ำของแต่ละ pane
      const clampedDy = Math.max(Math.min(dy, startSecondHeight - min), -startFirstHeight + min);

      first.style.height = (startFirstHeight + clampedDy) + 'px';
      second.style.height = (startSecondHeight - clampedDy) + 'px';
    }

    function onMouseUp() {
      document.body.style.userSelect = '';
      document.removeEventListener('mousemove', onMouseMove);
      document.removeEventListener('mouseup', onMouseUp);
    }
  });
}


</script>
  

  <script>
function sendBall(fromSelector, toSelector) {
  const from = document.querySelector(fromSelector);
  const to = document.querySelector(toSelector);

  const f = from.getBoundingClientRect();
  const t = to.getBoundingClientRect();

  const startX = (f.left-10) + f.width / 2;
  const startY = f.top + f.height / 2;

  const endX = (t.left-5) + t.width / 2;
  const endY = (t.top-2) + t.height / 2;

  const dx = endX - startX;
  const dy = endY - startY;

  const ball = document.createElement("div");
  ball.className = "fly-ball";

  /* ✨ ดึงสีพื้นหลังตัวส่งแล้วใช้กับลูกบอล */
  const senderColor = window.getComputedStyle(from).backgroundColor;
  ball.style.backgroundColor = senderColor;

  ball.style.left = startX + "px";
  ball.style.top = startY + "px";
  ball.style.setProperty("--dx", dx + "px");
  ball.style.setProperty("--dy", dy + "px");

  document.body.appendChild(ball);

  ball.addEventListener("animationend", () => {
    explode(endX, endY);
    ball.remove();
  });
  

  to.classList.add('alert-flash');

  setTimeout(() => {
	  to.classList.remove('alert-flash');
  }, 20000);  // 20 วินาที
}

function explode(x, y) {
  const colors = ["red","yellow","green","blue","purple","orange","cyan","pink"];

  for (let i = 0; i < 10; i++) {
    const piece = document.createElement("div");
    piece.className = "piece";
    piece.style.background = colors[Math.floor(Math.random() * colors.length)];

    piece.style.left = (x - 5) + "px";
    piece.style.top = (y - 5) + "px";

    const px = (Math.random()*120 - 60) + "px";
    const py = (Math.random()*120 - 60) + "px";

    piece.style.setProperty("--px", px);
    piece.style.setProperty("--py", py);

    document.body.appendChild(piece);

    piece.addEventListener("animationend", () => piece.remove());
  }
  
  

}




var isDragging2 = false;
  var offsetX2, offsetY2;
  var originalZIndex2;	
  var header2 = document.querySelector('.sub-system');
  var draggableWindow2 = document.getElementById('draggable-window2');

  draggableWindow2.addEventListener('click', function (e) {

    var currentZIndex = parseInt(draggableWindow2.style.zIndex) || 0;
    if (currentZIndex <= 5000) {
        index++;
        draggableWindow2.style.zIndex = index;
    }
    

  
  });


  header2.addEventListener('mousedown', function (e) {
      isDragging2 = true;

      // Save the original z-index
      originalZIndex2 = draggableWindow2.style.zIndex;

  
      offsetX2 = e.clientX - draggableWindow2.getBoundingClientRect().left;
      offsetY = e.clientY - draggableWindow2.getBoundingClientRect().top;
  });
  
  document.addEventListener('mousemove', function (e) {
      if (isDragging2) {
          var x = e.clientX - offsetX2;
          var y = e.clientY - offsetY;
          draggableWindow2.style.left = x + 'px';
          draggableWindow2.style.top = y + 'px';
        
      }

  });
  
  document.addEventListener('mouseup', function () {
      if (isDragging2) {
          isDragging2 = false;
      }
  });	
  
  
  
  
//ฟังก์ชันสำหรับปรับความสูง
  function autoResizeTextarea(el) {
      // รีเซ็ตความสูงกลับเป็น auto ก่อน เพื่อให้มันหดตัวได้เวลาผู้ใช้ลบข้อความ
      el.style.height = 'auto'; 
      // ตั้งความสูงใหม่ตามเนื้อหาจริง (scrollHeight)
      el.style.height = (el.scrollHeight) + 'px'; 
  }

  // 1. ดักจับทุกครั้งที่มีการพิมพ์ (หรือลบ) ใน Textarea ทั้งหน้าเว็บ
  document.addEventListener('input', function(event) {
      if (event.target.tagName.toLowerCase() === 'textarea') {
          autoResizeTextarea(event.target);
      }
  });

  // 2. ปรับความสูงให้พอดีตั้งแต่ตอนโหลดหน้าเว็บเสร็จ
  // (สำคัญมาก: เพราะช่องที่มีข้อมูล ${edata[key]} ยาวๆ มาอยู่แล้ว มันจะได้ขยายรอไว้เลย)
  document.addEventListener("DOMContentLoaded", function() {
      const allTextareas = document.querySelectorAll('textarea');
      allTextareas.forEach(ta => autoResizeTextarea(ta));
  });

var member_name = "${Member_name}";
</script>
 <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

   </div>
