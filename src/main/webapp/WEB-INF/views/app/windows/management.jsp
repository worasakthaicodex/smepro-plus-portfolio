<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="UTF-8">
    <title>GPO UX Workflow Reference Application</title>
    <style>
    
    /* FONTS */
@font-face {
  font-family: 'Fira Sans';
  font-style: italic;
  font-weight: 100;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9A4kDNxMZdWfMOD5VvkrCqUTDfdA.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: italic;
  font-weight: 200;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9f4kDNxMZdWfMOD5VvkrAGQCf4VFw.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: italic;
  font-weight: 300;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9f4kDNxMZdWfMOD5VvkrBiQyf4VFw.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: italic;
  font-weight: 400;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9C4kDNxMZdWfMOD5VvkrjJYTc.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: italic;
  font-weight: 500;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9f4kDNxMZdWfMOD5VvkrA6Qif4VFw.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: italic;
  font-weight: 600;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9f4kDNxMZdWfMOD5VvkrAWRSf4VFw.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: italic;
  font-weight: 700;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9f4kDNxMZdWfMOD5VvkrByRCf4VFw.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: italic;
  font-weight: 800;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9f4kDNxMZdWfMOD5VvkrBuRyf4VFw.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: italic;
  font-weight: 900;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9f4kDNxMZdWfMOD5VvkrBKRif4VFw.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: normal;
  font-weight: 100;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9C4kDNxMZdWfMOD5Vn9LjJYTc.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: normal;
  font-weight: 200;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9B4kDNxMZdWfMOD5VnWKneRhf_.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: normal;
  font-weight: 300;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9B4kDNxMZdWfMOD5VnPKreRhf_.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: normal;
  font-weight: 400;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9E4kDNxMZdWfMOD5Vvl4jO.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: normal;
  font-weight: 500;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9B4kDNxMZdWfMOD5VnZKveRhf_.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: normal;
  font-weight: 600;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9B4kDNxMZdWfMOD5VnSKzeRhf_.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: normal;
  font-weight: 700;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9B4kDNxMZdWfMOD5VnLK3eRhf_.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: normal;
  font-weight: 800;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9B4kDNxMZdWfMOD5VnMK7eRhf_.ttf) format('truetype');
}
@font-face {
  font-family: 'Fira Sans';
  font-style: normal;
  font-weight: 900;
  src: url(https://fonts.gstatic.com/s/firasans/v18/va9B4kDNxMZdWfMOD5VnFK_eRhf_.ttf) format('truetype');
}
/* COLORS */
/* MIXINS */
/*
.random-number(@min, @max) { 
  @random-number: floor(Math.random() * @{max} + @{min});
}
*/
/* RESET */
.clearfix::after {
  display: block;
  width: 100%;
  height: 0;
  content: '';
  clear: both;
}

*,
*::after,
*::before {
  box-sizing: border-box;
}
ul,
li {
  list-style-type: none;
  margin: 0;
  padding: 0;
}
a {
  text-decoration: none;
}
.containers {
  width: 100%;
height: 100%;
    margin: 0;
    overflow: hidden; /* ป้องกันการ scroll ซ้อนกันที่ตัวนอกสุด */
  background-color: #1d1e22;
  background-color: #002338;
}
#container #wk-ecosystem {
  display: none;
  position: absolute;
  top: 20px;
  left: 20px;
  font-size: 16pt;
  color: rgba(255, 255, 255, 0.5);
  font-style: italic;
}
#container.wk-ecosystem {
  background-color: #004873;
}
#container.wk-ecosystem #wk-ecosystem {
  display: block;
}
#container.wk-ecosystem #window {
  box-shadow: 7px 7px 0 rgba(0, 0, 0, 0.2);
}
#container.masked > #backdrop {
  display: block;
  visibility: visible;
  z-index: 9;
  width: 100vw;
  height: 100vh;
  opacity: 0.5;
}
#container #backdrop {
  position: absolute;
  display: none;
  visibility: hidden;
  z-index: -100;
  width: 0;
  height: 0;
  background-color: #000;
  opacity: 0;
}
#container #container-footer {
  position: absolute;
  bottom: 20px;
  left: 20px;
}
#container #container-footer > menu > li {
  float: left;
  background-color: #36383f;
  border: 1px solid #000;
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 10pt;
  color: #ddd;
  box-shadow: 2px 2px 0 rgba(0, 0, 0, 0.3);
  margin: 16px 8px 0 0;
  cursor: pointer;
}
#container #container-footer > menu > li:hover {
  background-color: #484848;
}
/* WINDOW */
#window {

  width: 100%;
  height: 100%;
  margin: 0vw;
  background-color: #ffffff;
  overflow: hidden;
}
#window #window-header {
  position: relative;
  z-index: 5;
  width: 100%;
  height: 30px;
  background-color: #D8D8D8;
  background-color: #4D505A;
  background-color: #DADADA;

}
#window #window-header #window-resize {
  padding: 0 30px 0 8px;
  width: 86px;
  float: left;
}
#window #window-header #window-resize li {
  display: inline-block;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  margin: 10px 3px;
  float: left;
}
#window #window-header #window-resize li.close {
  background-color: #ff453a;
}
#window #window-header #window-resize li.minimize {
  background-color: #ff950a;
}
#window #window-header #window-resize li.maximize {
  background-color: #32d74b;
}
#window #window-header .window-tab {
  position: relative;
  display: inline-block;
  background-color: rgba(255, 255, 255, 0.5);
  padding: 0 12px 0 24px;
  height: 24px;
  margin: 6px 0 0;
  line-height: 24px;
  font-size: 8pt;
  font-weight: 400;
  color: #666;
  letter-spacing: 0.3pt;
  border-radius: 6px 6px 0 0;
}
#window #window-header .window-tab::before {
  display: block;
  content: '';
  position: absolute;
  top: 7px;
  left: 7px;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.15);
}
#window #window-body {
  width: 100%;
  height: 100%;
  cursor: default;
}
.wk-wheel {
  background-color: #80BDE1;
  border-radius: 50%;
  position: relative;
}
.wk-wheel::before,
.wk-wheel::after {
  position: absolute;
  content: '';
  display: block;
  top: 50%;
  left: 50%;
  border-radius: 50%;
}
.wk-wheel::before {
  z-index: 1;
  width: 66%;
  height: 66%;
  margin-top: -33%;
  margin-left: -33%;
  background-color: #C2DE90;
}
.wk-wheel::after {
  z-index: 2;
  width: 24%;
  height: 24%;
  margin-top: -12%;
  margin-left: -12%;
  background-color: #F29097;
}
/* EDITOR */
#editor {
  position: absolute;
  z-index: 10;
  top: 0;
  right: -600px;
  width: 600px;
  height: 100vh;
  background-color: #36383f;
  color: #ccc;
  box-shadow: -8px 0 0 rgba(0, 0, 0, 0.15);
}
#editor h1,
#editor h2,
#editor h3 {
  text-transform: uppercase;
  color: #eee;
}
#editor p {
  font-size: 11pt;
  line-height: 16pt;
  margin-bottom: 30px;
}
#editor p em {
  font-size: 11pt;
  color: #fff;
}
#editor h1 {
  font-size: 16pt;
  margin-bottom: 30px;
}
#editor h2 {
  font-size: 12pt;
  margin-bottom: 10pt;
}
#editor .input-group {
  width: 100%;
  margin: 16px 0;
}
#editor .input-group > label,
#editor .input-group > input {
  width: 100%;
}
#editor .input-group > label {
  display: block;
  font-size: 9pt;
  letter-spacing: 0.3pt;
  margin-bottom: 4px;
  line-height: 20px;
  color: #A3A3A3;
  text-transform: uppercase;
}
#editor .input-group > input {
  height: 36px;
  padding: 8px 16px;
  border: 1px solid transparent;
  background-color: rgba(0, 0, 0, 0.3);
  color: #BFBFBF;
  font-size: 12pt;
}
#editor .input-group > input,
#editor .input-group > input:focus {
  border: 0;
  outline: none;
}
#editor .input-group > input:focus {
  background-color: rgba(0, 0, 0, 0.5);
}
#editor .input-group > input::-webkit-input-placeholder {
  color: #757575;
  font-style: italic;
}
#editor .input-group > input:-moz-placeholder {
  color: #757575;
  opacity: 1;
}
#editor .input-group > input::-moz-placeholder {
  color: #757575;
  font-style: italic;
  opacity: 1;
}
#editor .input-group > input:-ms-input-placeholder {
  color: #757575;
  font-style: italic;
}
#editor .input-group > input::-ms-input-placeholder {
  color: #757575;
  font-style: italic;
}
#editor .input-group .editor-radioselect {
  padding: 2px;
}
#editor .input-group .editor-radioselect > li {
  float: left;
  font-size: 9pt;
  font-weight: 400;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  padding: 4px 8px;
  background-color: rgba(0, 0, 0, 0.3);
  color: #BFBFBF;
  cursor: pointer;
}
#editor .input-group .editor-radioselect > li:hover {
  background-color: rgba(64, 64, 64, 0.3);
}
#editor .input-group .editor-radioselect > li.selected {
  background-color: #32d74b;
  color: #125e1e;
}
#editor .input-group .editor-radioselect::after {
  content: '';
  display: block;
  clear: both;
}
#editor #editor-handle {
  position: absolute;
  cursor: pointer;
  right: 100%;
  width: 50px;
  height: 50px;
  padding: 10px;
  opacity: 0.5;
}
#editor #editor-handle svg {
  stroke: #eee;
}
#editor #editor-handle:hover {
  opacity: 1;
}
#editor.open {
  right: 0;
}
#editor.open #editor-handle {
  transform: rotate(180deg);
}
#editor > header,
#editor > footer {
  position: absolute;
  height: 50px;
  width: 600px;
}
#editor > header > menu {
  padding: 0 20px;
  background-color: rgba(0, 0, 0, 0.15);
}
#editor > header > menu > li {
  display: inline-block;
  padding: 6px 10px;
  line-height: 38px;
  height: 50px;
}
#editor > header > menu > li a {
  opacity: 0.5;
  cursor: pointer;
  color: #eee;
  font-size: 11pt;
  text-transform: uppercase;
  letter-spacing: 1px;
  padding: 0 0 6px;
}
#editor > header > menu > li:hover a {
  opacity: 0.8;
}
#editor > header > menu > li.active a {
  opacity: 1;
}
#editor > footer {
  bottom: 0;
  background-color: #282828;
}
#editor > footer p {
  padding: 0 16px;
  line-height: 50px;
  color: #777;
  font-size: 10pt;
  font-style: italic;
  margin: 0;
}
#editor #editor-body {
  height: calc(100vh - 100px);
  margin: 50px 0 50px;
}
#editor #editor-body > .tab {
  display: none;
  width: 100%;
  height: 100%;
  padding: 20px 30px;
}
#editor #editor-body > .tab.open {
  display: block;
}
/* REFERENCE APPLICATION */
.area-close {
  cursor: pointer;
  display: block;
  position: absolute;
  top: 0;
  right: 0;
  width: 22px;
  height: 22px;
  line-height: 22px;
  text-align: center;
  margin: 6px;
  color: #757575;
  opacity: 0.5;
}
.area-close::after {
  content: "\2715";
}
.area-close:hover {
  opacity: 1;
}
.ra-line-set {
  margin: 20px 0;
}
.ra-line {
  position: relative;
  height: 10px;
  width: calc(100% - 20px);
  
  border-radius: 5px;
  margin: 16px 10px;

}
.ra-line.active::after {
  content: '';
  top: -50%;
  left: 0;
  position: absolute;
  height: 200%;
  width: 3px;
  background-color: #80BDE1;
}
.ra-line.link,
.ra-line.link.icon::before {
  background: #beddf0;
}
.ra-line.small {
  height: 6px;
  border-radius: 3px;
  margin: 8px 4px;
}
.ra-line.small.icon {
  margin-left: 18px;
}
.ra-line.small.icon::before {
  height: 6px;
  width: 6px;
  left: -12px;
}
.ra-line.icon {
  margin-left: 32px;
}
.ra-line.icon::before {
  content: '';
  display: block;
  position: absolute;
  top: 0;
  left: -22px;
  height: 10px;
  width: 10px;
  background: rgba(0, 0, 0, 0.12);
  border-radius: 50%;
}
.ra-line.icon.active::after {
  left: -3px;
}
.ra-line.icon.active::before {
  background: #80BDE1;
}
.ra-line.clickable {
  cursor: pointer;
}
.ra-line.clickable:hover {
  background-color: rgba(0, 0, 0, 0.25);
}
.ra-line.clickable:hover.link {
  background-color: #80BDE1;
}
nav > li,
ul > li {
  /*  
.randomize-lines(@n, @i: 1) when (@i =< @n) {
    &:nth-child(@{i}) .ra-line.random-length{
      width: calc(floor(percentage(`0.5 + (Math.random() / 2)`)) - 20px);
    }
    .randomize-lines(@n, (@i + 1));
  }
  */
}
.dots {
  display: flex;
  align-items: center;
  width: 12px;
  height: 12px;
  margin: 9px 8px;
}
.dots::after {
  content: '';
  display: block;
  width: 25%;
  height: 25%;
  border-radius: 50%;
  animation-name: dots;
  animation-duration: 2s;
  animation-iteration-count: infinite;
  animation-timing-function: ease-out;
}
@keyframes dots {
  0%,
  100% {
    background-color: #474747;
    /* 1st dot */
    box-shadow: 6px 0 0 #474747, /* 2nd dot */ 12px 0 0 #474747;
    /* 3rd dot */
  }
}



#ra-application {
  position: relative;
  width: 100%;
  height: 100%;
  background-color: #fff;
}
#ra-application #ra-app-select {
  display: none;
}
#ra-application.backstage.appswitcher #ra-app-select {
  display: block;
}
#ra-application.backstage.appswitcher.default {
  transform: translateX(70px);
}
#ra-application.backstage.appswitcher.small {
  transform: translateX(50px);
}
#ra-application.backstage.left {
  transform: translateX(150px);
}
#ra-application.masked > #ra-smoke {
  display: block;
  visibility: visible;
  z-index: 6;
}
#ra-smoke {
  position: absolute;
  top: 30;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: none;
  visibility: hidden;
  z-index: -999;
}
#ra-header {
  height: 30px;
  width: 100%;
  background-color: #ffffff;
  border-bottom: 1px solid #DADADA;
}
#ra-app {
  float: left;
  height: 30px;
}
#ra-app #ra-app-switcher {
  cursor: pointer;
  float: left;
  width: 30px;
  height: 30px;
  background-color: rgba(255, 255, 255, 0.1);
}
#ra-app #ra-app-switcher .wk-wheel {
  width: 18px;
  height: 18px;
  margin: 6px;
}
#ra-app #ra-app-title,
#ra-app #ra-app-context {
  float: left;
  height: 30px;
  line-height: 30px;
  color: #474747;
  font-size: 8pt;
  padding: 0 8px;
  opacity: 1;
}
#ra-app #ra-app-context {
  display: none;
  position: relative;
  color: #474747;
  font-style: italic;
  font-size: 6pt;
  line-height: 28px;
  padding-top: 2px;
}
#ra-app #ra-app-context::before {
  content: '';
  display: block;
  position: absolute;
  top: 7px;
  left: 0;
  width: 1px;
  height: 10px;
  background-color: #DADADA;
}
#ra-app #ra-app-cta {
  position: relative;
  display: block;
  float: left;
  height: 16px;
  width: 16px;
  margin: 7px;

  border-radius: 50%;
  cursor: pointer;
  opacity: 0.7;
}
#ra-app #ra-app-cta:hover {
  opacity: 1;
}
#ra-app #ra-app-cta::after {
  content: '';
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -3px;
  margin-left: -3px;
  display: block;
  height: 6px;
  width: 6px;
  background-color: rgba(255, 255, 255, 0.25);
  border-radius: 50%;
}
#ra-app #ra-app-cta-modal {
  cursor: pointer;
  position: absolute;
  z-index: 10;
  display: none;
  top: 50%;
  left: 50%;
  margin-top: -150px;
  margin-left: -90px;
  background-color: #ffffff;
  border-bottom: 30px solid #F6F6F6;
  width: 300px;
  height: 180px;
}
#ra-app #ra-app-cta-modal.open {
  display: block;
}
#ra-app #ra-app-cta-modal::before,
#ra-app #ra-app-cta-modal::after {
  display: block;
  content: '';
  position: absolute;
  top: 100%;
  right: 0;
  background-color: #007AC3;
  width: 60px;
  height: 20px;
  margin: 5px;
  opacity: 0.5;
}
#ra-app #ra-app-cta-modal::after {
  right: 65px;
}
#ra-body {
  width: 100%;
flex: 1;
height: 100%;
}
#ra-workspace {

  height: 90%;
  overflow: auto;

}
.ra-jellygrid {
  position: relative;
  width: 100%;
  height: 100%;
}
.ra-jellygrid::after,
.ra-jellygrid::before {
  content: '';
  display: block;
  position: absolute;
  background-color: #E72275;
}
.ra-jellygrid::after {
 display: none !important;
}
.ra-jellygrid::before {
 display: none !important;
}
#ra-aside-left,
#ra-aside-right {
  display: inline-block;
  height: 100%;
}
#ra-aside-left {
  float: left;

}
#ra-aside-left .ra-headbutt::before {
  top: 0;
  right: 0;
}
#ra-aside-right {
  float: right;
}
#ra-aside-right .ra-headbutt::before {
  top: 0;
  left: 0;
}
#ra-spine {
  background-color: #ffffff;
  border-right: 1px solid #DADADA;
  width: 150px;
  z-index:10;
}
#ra-spine .area-close,
#ra-spine .order,
#ra-spine::before {
  display: none;
}
#ra-spine.small {
  width: 32px;
}
#ra-spine .wk-wheel {
  position: absolute;
  bottom: 12px;
  left: 5px;
  width: calc(32px - 10px);
  height: calc(32px - 10px);
}
.ra-headbutt {
  position: relative;
  float: left;
  height: 100%;
  width: 200px;
}
.ra-headbutt .order {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 50px;
  height: 50px;
  margin-left: -25px;
  margin-top: -25px;
  text-align: center;
  line-height: 50px;
  font-size: 32pt;
  font-style: italic;
  color: #DADADA;
}
.ra-headbutt::before {
  position: absolute;
  top: 0;
  content: '';
  width: 1px;
  height: 100%;
  background-color: #DADADA;
}
.ra-smokebreak {
  position: absolute;
  top: 0;
  right: -300px;
  width: 300px;
  height: 100%;
  box-shadow: none;
  background-color: #ffffff;
}
.ra-smokebreak.open {
  z-index: 7;
  right: 0;
  box-shadow: -4px 0 0 rgba(0, 0, 0, 0.15);
}
#ra-identity {
  float: right;
  width: 350px;
  height: 30px;
}
#ra-identity::after {
  content: '';
  clear: both;
  display: block;
}
#ra-services {
  float: left;
  height: 100%;
  width: auto;
  padding: 3px 6px;
}
#ra-services::after {
  content: '';
  clear: both;
  display: block;
}
#ra-services > .ra-service {
  height: 20px;
  width: 20px;
  background-color: #FFF;
  border-radius: 50%;
  margin: 3px 5px;
 
  float: left;
  cursor: pointer;
    display: inline-flex;       /* ใช้ flex */
  justify-content: center;    /* จัดกลางแนวนอน */
  align-items: center;        /* จัดกลางแนวตั้ง */
  color:#fff;
}
/* 1. สร้าง Keyframes สำหรับการกระพริบ */
@keyframes blink-red {
  0% {
    background-color: #FFF; /* สีเดิม (ขาว) */
    box-shadow: 0 0 0 rgba(255, 0, 0, 0);
  }
  50% {
    background-color: #ff3b30; /* เปลี่ยนเป็นสีแดงแจ้งเตือน */
    box-shadow: 0 0 8px #ff3b30; /* เพิ่มแสงฟุ้ง (Glow) ให้ดูเหมือนไฟ LED */
  }
  100% {
    background-color: #FFF; /* กลับมาขาว */
    box-shadow: 0 0 0 rgba(255, 0, 0, 0);
  }
}

/* 2. สร้าง Class .alert เพื่อเรียกใช้ */
.ra-service.alert {
  animation: blink-red 1.5s infinite ease-in-out; /* กระพริบวนไปเรื่อยๆ ทุก 1.5 วินาที */
  border: 1px solid transparent; /* กันขนาดเพี้ยนตอนเปลี่ยนสี */
}


#ra-services > .ra-service:hover {
  opacity: 0.5;
}
#ra-account {
  float: right;
  padding: 0 4px;
}
#ra-account::after {
  content: '';
  clear: both;
  display: block;
}
#ra-account > .ra-bubble {
  width: 20px;
  height: 20px;
  background-color: #fff;
  border-radius: 50%;
  margin: 5px 5px;
  position: absolute;
  top: 0;
  opacity: 0.8;
  cursor: pointer;
    display: inline-flex;       /* ใช้ flex */
  justify-content: center;    /* จัดกลางแนวนอน */
  align-items: center;        /* จัดกลางแนวตั้ง */
}
#ra-account > .ra-bubble:hover {
  opacity: 1;
  z-index: 3;
}
#ra-account > .ra-bubble:nth-child(1) {
  right: 3px;
  z-index: 2;
}
#ra-account > .ra-bubble:nth-child(2) {
  right: 25px;
 
  z-index: 1;
}
#ra-account #ra-titles {
  position: absolute;
  top: 3px;
  right: 36px;
  padding: 4px;
  width: 170px;
}
#ra-account #ra-titles::after {
  content: '';
  display: block;
  clear: both;
}
#ra-account #ra-titles > h1,
#ra-account #ra-titles > h2 {
  width: 100%;
  text-align: right;
  margin: 0;
}
#ra-account #ra-titles > h1 {
  font-size: 0.4rem;
  height: 9px;
}
#ra-account #ra-titles > h2 {
  font-size: 0.35rem;
  height: 7px;
  opacity: 0.7;
}
.ra-backstagebreak {
  z-index: 0;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 99%;

  background-color: #007AC3;
  
}
.ra-app-icon {
  position: relative; /* สำคัญมาก: เพื่อให้ตัวเลขเทียบตำแหน่งกับกล่องนี้ */
 display: inline-flex;  
  justify-content: center;    /* จัดกลางแนวนอน */
  align-items: center;        /* จัดกลางแนวตั้ง */
  
  width: 35px;
  height: 35px;
  background-color: #fff;
  border-radius: 4px;
    position: relative;
}


/* ตัวเลขมุมขวาบน */
.badge-count {
    position: absolute;
    top: -5px;    /* ปรับระยะห่างจากขอบบน */
    right: -5px;  /* ปรับระยะห่างจากขอบขวา */
    background-color: red; /* สีพื้นหลัง (เช่น สีแดงแจ้งเตือน) */
    color: white;          /* สีตัวเลข */
    border-radius: 50%;    /* ทำเป็นวงกลม */
    padding: 2px 6px;      /* ระยะห่างรอบตัวเลข */
    font-size: 12px;       /* ขนาดตัวอักษร */
    font-weight: bold;
    min-width: 18px;
    text-align: center;
    box-shadow: 0 0 2px rgba(0,0,0,0.5);
}


.fly-ball {
    width: 18px;
    height: 18px;
    border-radius: 50%;
    position: absolute;
    z-index: 9999;
    pointer-events: none;
    animation: fly 0.7s ease-out forwards;
  }

  @keyframes fly {
    0%   { transform: translate(0,0); opacity: 1; }
    100% { transform: translate(var(--dx), var(--dy)); opacity: 1; }
  }
  /* ชิ้นส่วนหลังแตก */
  .piece {
    width: 10px;
    height: 10px;
    border-radius: 50%;
    position: absolute;
    z-index: 9999;
    pointer-events: none;
    animation: boom 0.6s ease-out forwards;
  }

  @keyframes boom {
    0% { transform: translate(0,0) scale(1); opacity: 1; }
    100% { transform: translate(var(--px), var(--py)) scale(0.3); opacity: 0; }
  }



#ra-app-select {
  padding-top: 32px;
  width: 150px;
}
#ra-app-select .ra-app-meta {
  width: 100%;
  background-color: rgba(0, 0, 0, 0.1);

}
#ra-app-select .ra-app-meta .ra-app-meta-title .ra-app-icon,
#ra-app-select .ra-app-meta .ra-app-meta-title .ra-line {
  float: left;
}
#ra-app-select .ra-app-meta .ra-app-meta-title .ra-line {
  width: 50%;
  margin: 3px 6px;
}
#ra-app-select .ra-app-meta .ra-app-meta-title::after {
  content: '';
  clear: both;
  display: block;
}
#ra-app-select .ra-app-meta ul {
  padding: 12px 0;
}
#ra-app-select .ra-app-meta ul .ra-line {
  margin: 2px 6px;
}
#ra-app-select .ra-app-meta .ra-app-icon {
  width: 40px;
  height: 40px;

}
#ra-app-select .ra-app-meta .ra-line {
  display: inline-block;
  margin: 0 6px;
  width: 50%;
}
#ra-app-select > nav {
  padding: 6px 0;
}
#ra-app-select > nav > li {
  cursor: pointer;
}
#ra-app-select > nav > li .ra-app-icon,
#ra-app-select > nav > li .ra-line {
  float: left;
}
#ra-app-select > nav > li:hover .ra-app-icon {
  opacity: 0.5;
}
#ra-app-select > nav > li .ra-app-icon {
  cursor: pointer;
  margin: 6px 13px;

}
#ra-app-select > nav > li:nth-child(1) .ra-app-icon {
  background-color: #EFAB40;
}
#ra-app-select > nav > li:nth-child(2) .ra-app-icon {
  background-color: #40B2A1;
}
#ra-app-select > nav > li:nth-child(3) .ra-app-icon {
  background-color: #AF4995;
}
#ra-app-select > nav > li:nth-child(4) .ra-app-icon {
  background-color: #5B528C;
}
#ra-app-select > nav > li:nth-child(5) .ra-app-icon {
  background-color: #A4CD58;
}
#ra-app-select > nav > li:nth-child(6) .ra-app-icon {
  background-color: #409bd2;
}
#ra-app-select > nav > li:nth-child(7) .ra-app-icon {
  background-color: #EC5862;
}
#ra-app-select > nav > li .ra-line {
  width: calc(100% - 64px);
  margin: 13px 0;
  margin-top:25px;
  background-color: rgba(255, 255, 255, 0.15);
}
#ra-app-select > nav > li .ra-line.clickable:hover {
  background-color: rgba(255, 255, 255, 0.25);
}
/* 
 *  NOTIFICATIONS
 */
#ra-notification-dispenser {
  position: absolute;
  z-index: 10;
  bottom: 0;
}
#ra-notification-dispenser[data-ra-var-value="right"] {
  right: 0;
}
#ra-notification-dispenser[data-ra-var-value="left"] {
  left: 0;
}
#ra-notification-dispenser[data-ra-var-value="left"] .ra-notification.popping {
  transform: translateX(-160px);
}
.ra-notification {
  position: relative;
  padding: 16px 16px 8px;
  margin: 16px;
  height: auto;
  width: 160px;
  background-color: #ffffff;
  border: 1px solid #EDEDED;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.12);
}
.ra-notification .ra-line {
  margin: 0 0 8px 0;
}
.ra-notification::before {
  content: '';
  display: block;
  position: absolute;
  top: 0;
  right: 100%;
  height: 100%;
  width: 4px;
  background-color: #007AC3;
}
.ra-notification.ra-confirm::before {
  background-color: #85BC20;
}
.ra-notification.ra-warning::before {
  background-color: #EA8F00;
}
.ra-notification.ra-error::before {
  background-color: #E5202E;
}
.ra-notification > .area-close {
  z-index: 1;
  cursor: pointer;
}
.ra-notification > .area-close::hover {
  opacity: 1;
}
.ra-notification.popping {
  transform: translateX(160px);
  opacity: 0;
}
.ra-notification.pushing {
  border-top: 0;
  border-bottom: 0;
  margin-top: 0;
  margin-bottom: 0;
  padding-top: 0;
  padding-bottom: 0;
  height: 0;
  opacity: 0;
}
.ra-card-container {
  width: 100%;
  height: 220px;
  background-color: #F6F6F6;
  padding: 32px 12px 12px;
  text-align: center;
  overflow: hidden;
}
.ra-card-container .ra-card {
  display: inline-block;
  background-color: #ffffff;
  border: 1px solid #EDEDED;
  margin: 8px;
}
.ra-card-container .ra-card,
.ra-card-container .ra-card.ra-card-square {
  height: 160px;
  width: 160px;
}
.ra-card-container .ra-card.ra-card-landscape {
  height: 160px;
  width: 320px;
}
.ra-card {
  position: relative;
  display: inline-block;
  background-color: #ffffff;
  border: 1px solid #EDEDED;
  margin: 8px;
}
.ra-card,
.ra-card.ra-card-square {
  height: 160px;
  width: 160px;
}
.ra-card.ra-card-landscape {
  height: 160px;
  width: 320px;
}
.ra-card > .ra-card-action-bar {
  width: 100%;
  position: absolute;
  bottom: 0;
  left: 0;
  padding: 0 4px 8px;
}
.ra-card > .ra-card-action-bar::after {
  display: block;
  content: '';
  clear: both;
}
.ra-card > .ra-card-action-bar > .ra-line {
  float: left;
  max-width: 40px;
}
.ra-card > .ra-card-action-bar > .ra-line.icon {
  max-width: calc(40px - 42px);
}
.ra-smokebreak .ra-line-set {
  padding-top: 8px;
}
.ra-smokebreak .ra-card-set {
  border-top: 1px solid #EDEDED;
  padding: 16px 0;
}
.ra-smokebreak .ra-card {
  width: calc(100% - 16px);
  height: 100px;
  display: inline-block;
  background-color: #ffffff;
  border: 1px solid #EDEDED;
  margin: 4px 8px;
}
.ra-content-container {
  width: 40vw;
  max-width: 90%;
  margin: 0 auto;
  padding: 48px 0;
}
.ra-content-container::after {
  display: block;
  content: '';
  clear: both;
}
.ra-content-container > h1 {
  width: 17vw;
}
.ra-content-container > h1 > .ra-line {
  margin-left: 0;
}
.ra-datatable .ra-datatable-header,
.ra-datatable .ra-datatable-row {
  width: 100%;
  height: 24px;
}
.ra-datatable .ra-datatable-header {
  height: 28px;
  border-bottom: 1px solid #BFBFBF;
}
.ra-datatable .ra-datatable-header .ra-line {
  display: inline-block;
}
.ra-datatable .ra-datatable-row {
  width: 100%;
  border-bottom: 1px solid #EDEDED;
}
.ra-datatable .ra-datatable-row .ra-line {
  display: inline-block;
  opacity: 0.5;
}
.ra-datatable .ra-datatable-row:hover {
  cursor: pointer;
  background-color: #ecf5fb;
}

/* --- Header màu hồng --- */
thead.ant-table-thead .ant-table-cell {
  border-bottom: 3px solid #F6AEBA !important;
  background-color: #fff !important;
  font-weight: bold;
}

.workflow-table{

weight: 100%;
}
.expand{
cursor: pointer;
}

/* --- Workflow cấp 0 --- */
.workflow-table > .ant-table-container .ant-table-tbody > tr > td {
  background-color: #ffffff; /* trắng */
  font-weight: 600;
}

/* --- Job cấp 1 --- */
.workflow-table .ant-table-expanded-row .ant-table {
  margin: 8px 0;
  border-left: 4px solid #F6AEBA;
}

.workflow-table .ant-table-expanded-row .ant-table-tbody > tr > td {
  background-color: #fafafa; /* xám nhạt */
}

/* --- Stage / Execution cấp 2 --- */
.workflow-table .ant-table-expanded-row .ant-table-expanded-row .ant-table {
  margin: 6px 0;
  border-left: 4px solid #cbbeea;
}

.workflow-table .ant-table-expanded-row .ant-table-expanded-row .ant-table-tbody > tr > td {
  background-color: #f0f8ff; /* xanh nhạt */
  font-size: 12px;
}

/* Hover effect */
.workflow-table .ant-table-tbody > tr:hover > td {
  background-color: #eef6ff !important;
}

/* Workflow row (cấp gốc) */
.workflow-table > .ant-table-container .ant-table-tbody > tr.workflow-row > td {
  background-color: #ffffff !important;
  font-weight: 600;
  border-top: 3px solid #F6AEBA !important;
  box-shadow: inset 0 1px 0 #f0f0f0;
}

/* Job row (cấp 1) */
.workflow-table .ant-table-expanded-row .ant-table-tbody > tr.job-row > td {
  background-color: #fafafa !important;
}

/* Stage / Execution row (cấp 2) */
.workflow-table .ant-table-expanded-row .ant-table-expanded-row .ant-table-tbody > tr.stage-row > td {
  background-color: #f5faff !important;
  font-size: 12px;
}

/* Card effect cho bảng con */
.workflow-table .ant-table-expanded-row .ant-table {
  background: #fff;
  border: 1px solid #eee;
  border-radius: 8px;
  padding: 8px;
  margin: 6px 0;
}

/* Workflow row (cấp gốc, cha to nhất) */
.workflow-table > .ant-table-container .ant-table-tbody > tr.workflow-row > td {
  background-color: #ffeef3 !important; /* hồng nhạt */
  font-weight: 700;
  border-top: 3px solid #F6AEBA !important;
}

/* Job row (cấp 1) */
.workflow-table .ant-table-expanded-row .ant-table-tbody > tr.job-row > td {
  background-color: #f5f5f5 !important; /* xám nhạt */
  font-weight: 600;
}

/* Stage / Execution row (cấp 2) */
.workflow-table .ant-table-expanded-row .ant-table-expanded-row .ant-table-tbody > tr.stage-row > td {
  background-color: #f0faff !important; /* xanh rất nhạt */
  font-size: 12px;
}

/* Workflow row (ngoài cùng, sáng nhất) */
.workflow-table > .ant-table-container .ant-table-tbody > tr.workflow-row > td {
  background-color: #fff8fb !important; /* gần trắng, hồng nhạt */
  font-weight: 700;
  border-top: 3px solid #F6AEBA !important;
}

/* Job row (cấp 1, tối hơn Workflow) */
.workflow-table .ant-table-expanded-row .ant-table-tbody > tr.job-row > td {
  background-color: #f0f0f0 !important; /* xám nhạt */
  font-weight: 600;
}

/* Stage / Executor row (cấp 2, tối nhất) */
.workflow-table .ant-table-expanded-row .ant-table-expanded-row .ant-table-tbody > tr.stage-row > td {
  background-color: #e0e7f1 !important; /* xanh xám tối hơn hẳn */
  font-size: 12px;
  font-weight: 500;
}

.ant-table-wrapper tfoot>tr>td {
    position: relative;
    padding: 6px 16px !important;
    overflow-wrap: break-word;
}
.ant-table-cell {
    position: relative;
    padding: 4px 16px !important;
    overflow-wrap: break-word;
}

:where(.css-xepvsj).ant-table-wrapper .ant-table-tbody >tr >th >.ant-table-wrapper:only-child .ant-table, :where(.css-xepvsj).ant-table-wrapper .ant-table-tbody >tr >td >.ant-table-wrapper:only-child .ant-table, :where(.css-xepvsj).ant-table-wrapper .ant-table-tbody >tr >th >.ant-table-expanded-row-fixed>.ant-table-wrapper:only-child .ant-table, :where(.css-xepvsj).ant-table-wrapper .ant-table-tbody >tr >td >.ant-table-expanded-row-fixed>.ant-table-wrapper:only-child .ant-table {
    margin-block: 0px;
    margin-inline: 32px -16px;
}


.alert-flash {
  animation: alertFlash 1s ease-in-out 20; /* วาบ 20 รอบ = 20 วิ */
}

@keyframes alertFlash {
  0% { opacity: 1; transform: scale(1); }
  50% { opacity: 0.8; transform: scale(1.1); }
  100% { opacity: 1; transform: scale(1); }
}

  .window-tab.tabselect{
	  background-color:#fff !important; 
  } 
  
  
  
  
  
#body_asideinfo{

font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            font-size: 12px;
            color:black;
            width:100%
}  
  
  
  
  
  
    
.containers{
  padding: 0px;
  margin: 0px;
  background: #222;
font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #FFF;
  height: 100%;
  
}
  
 </style>
 
 
     <style>
/* สร้างคลาสสำหรับหมุน 1 รอบ (ใช้เวลา 1 วินาที) */
.spin-once {
    animation: spin-once-anim 1s linear;
}

@keyframes spin-once-anim {
    from { transform: rotate(0deg); }
    to { transform: rotate(360deg); }
}

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
  </head>
    
  <body>
  


<div id="container" class="containers">
 
 
  <section id="window">
  
  
    <div id="window-body">
    
<c:set  var="accessRole"  value="APP-USER-${process.id}" />
<c:set  var="accessRole2"  value="APP-ADMIN-${process.id}" />
<sec:authorize access="!hasAnyAuthority('${accessRole}','${accessRole2}')">     
<font style="color:red;padding:10px">Error Has not Any Authority</font>				
</sec:authorize>
<sec:authorize access="hasAnyAuthority('${accessRole}','${accessRole2}')">  
    
    
    
      <section id="ra-backstagebreak" class="ra-backstagebreak">
        <article id="ra-app-select">
  
          <nav>
             <c:forEach items="${appSelect }"   var="row" varStatus="count"> 
           
           
            <li class=" <c:if test="${thisApp.id == row.id }">ra-app-meta</c:if> raapp"  onclick="MangementAppswitchApps(this,${row.id});">
            <div class="ra-app-icon <c:if test="${count.count == 1 }">ra-app-icons </c:if>"   id="clo" 
             >${row.management_abbreviation }
           <c:if test="${row.alert > 0}"> <span class="badge-count">${row.alert }</span></c:if>
             
            </div>
            

          
       		 
            
            <div class="clearfix"></div></li>
            
            
            </c:forEach>
           
           
            <li style="padding:3px;display:none"><div class="ra-app-icon" id="fo5" onclick="sendBall('#fo5','#fo4')">FO5</div><div class="ra-line clickable random-length" data-action="push-notification" data-type="confirm"></div><div class="clearfix"></div></li>
            <li style="padding:3px;display:none"><div class="ra-app-icon" id="fo4" >QT</div><div class="ra-line clickable random-length" data-action="push-notification" data-type="warning"></div><div class="clearfix"></div></li>
            <li style="padding:3px;display:none"><div class="ra-app-icon" >SO</div><div class="ra-line clickable random-length" data-action="push-notification" data-type="error"></div><div class="clearfix"></div></li>
            <li style="padding:3px;display:none"><div class="ra-app-icon" style="padding:3px;display:none"></div><div class="ra-line clickable random-length"></div><div class="clearfix"></div></li>
            <li style="padding:3px;display:none"><div class="ra-app-icon" style="padding:3px;display:none"></div><div class="ra-line clickable random-length"></div><div class="clearfix"></div></li>
            <li style="padding:3px;display:none"><div class="ra-app-icon" style="padding:3px;display:none"></div><div class="ra-line clickable random-length"></div><div class="clearfix"></div></li>
          </nav>
          
          
 
        </article>
      </section>
      <div id="ra-application">
        <div id="ra-smoke"></div>
        <div id="ra-notification-dispenser" data-ra-var-radioselect="notificationDispenserLocation" data-ra-var-value="null"><ul></ul></div>
        <header id="ra-header">
          <section id="ra-app">
            <div id="ra-app-switcher" data-action="switch-application" data-size="default">
              <div class="wk-wheel" data-action="switch-application" data-size="default"></div>
              
              
     
  
              
            </div>
 
            <div id="ra-app-context" data-ra-var-text="appcontext"></div>
            <div id="ra-app-cta" data-action="open-cta"></div>
            <div id="ra-app-cta-modal" data-action="close-cta"></div>
            
            
            
            
          </section>
          <section id="ra-identity">
            <menu id="ra-services">
    
              <li class="ra-service" onclick="shows2('draggable-window6','Import'); getData('/formMassOperationsSystemImport-filemanager/${process.id}', 'mainbody6');">
     <img  src="/app/system/icon/database  add.ico" />
              </li>
    
                       <li class="ra-service" onclick="shows2('draggable-window3','Calendar');getData('/calendar-filemanager/${process.id}', 'mainbody3')">
                   <img  src="/app/system/icon/calendar.ico" /></li>
         

    <li class="ra-service <c:if test="${alert > 0}"> alert</c:if>"
              onclick="toggleasideInfo();getData('/notiapp-filemanager/${process.id}/${process.reference}','asideinfo');"><img  src="/app/system/icon/megaphone.ico" />   
    			</li>

                                                 <li class="ra-service" >
                   
                      
    <img  src="/app/system/icon/options.ico" />
                    
                    </li>
    

     
              
              
              
              
            </menu>
            <div id="ra-account">
             <div id="ra-organization" class="ra-bubble" style="width:150px;margin-right:20px">
   
   	 <input type="search" id="search" onkeyup="searchTable(this)">
   
  </div>
                   
                    
                    

              <div id="ra-titles">
                <h1 data-ra-var-text="apppersona"></h1>
                <h2 data-ra-var-text="appcontext"></h2>
              </div>
                    
            </div>
          </section>
        </header>
        <div id="ra-body">
          <aside id="ra-aside-left" style="padding:0px">
            <article id="ra-spine" class="ra-headbutt small">
              <ul class="ra-line-set">
                 <c:forEach items="${appSelect }"   var="row" varStatus="count"> 
                <li title="${row.management_abbreviation }" class="ra-line icon  <c:if test="${thisApp.id == row.id }">active</c:if> clickable random-length" onclick="MangementAppswitchApps(this,${row.id});"></li>
                </c:forEach>
                
         
              </ul>
            </article>
          </aside> 
                  <div id="window-header" style="margin-left:32px">
  
      <nav style="margin-left:10px">
      <ui>
          <c:forEach items="${tabManagement }"   var="row" varStatus="count"> 
                <Li class="window-tab window-tab${thisApp.id} <c:if test="${count.count == 1 }">tabselect</c:if>" 
                onclick="MangementAppswitchTab(this, ${row.id},'${thisApp.id}');"  id="tab-link-${thisApp.id}_${row.id}"  style="cursor: pointer;">${row.tab_name } <c:if test="${row.tab_count == 1 }">
                <span id="TAB-${thisApp.id}-${row.id}">[${row.box_total+0 }]</span></c:if></li>
           </c:forEach>

                    </ui>
      </nav>
    </div>
    
 
    <c:forEach items="${tabManagement }"   var="row" varStatus="count"> 
          <div id="ra-workspace${row.id}" style='color:black;overflow:auto;padding:0px;height: 90%;display:<c:if test="${count.count > 1}">none</c:if>' 
          class="workspace">
    
    <%@ include file="tabmanagement.jsp" %>  
    </div>
    </c:forEach>
    
    
          <aside id="ra-aside-right"></aside>
          <div  id="asideinfo" class="ra-smokebreak">
            <span class="area-close"></span>
          	<div id="body_asideinfo"></div>
          </div>
          <div class="clearfix"></div>
        </div>
      </div>

  </section>

  
</div>

</sec:authorize>

  </body> 
   
<script>
loadMangementApp();
LoadTab();



function MangementAppswitchApps(element, appId) {
    getData('/managementapp-filemanager/${process.id}?thisapp='+appId,'microsofts${process.id}');
    unsendCallBackKey();
}

function MangementAppswitchTab(element, tabId, appId) {
    const showId = 'ra-workspace' + tabId;
    const container = document.getElementById(showId);

    if (!container) return; // ป้องกัน Error กรณีหา div ไม่เจอ

    // 1. เช็คว่าตอนนี้ "กำลังโหลดอันเก่าอยู่หรือเปล่า" 
    if (container.querySelector('.loading-overlay') && 
        container.querySelector('.loading-overlay').style.display !== 'none') {
        console.warn("หยุด! กำลังโหลดข้อมูลเก่าอยู่ ห้ามกดแท็บซ้อน");
        return; 
    }

    // --- 🟢 ส่วนที่เพิ่มเข้ามา: ตรวจสอบสถานะการจำ (Cache) ---
    // เช็คว่าแท็บที่กด คือแท็บที่ Active อยู่ตอนนี้หรือเปล่า (ถ้าใช่ = ผู้ใช้ตั้งใจกดซ้ำเพื่อ Force Reload)
    const isAlreadyActive = element.classList.contains('tabselect');
    // เช็คว่าหน้าต่างนี้เคยถูกโหลดข้อมูลจนเสร็จไปแล้วหรือยัง
    const isLoaded = container.getAttribute('data-loaded') === 'true';

    // 2. สลับหน้าจอแบบเด็ดขาด (100% Force Override)
    document.querySelectorAll('.workspace').forEach(ws => {
        ws.style.setProperty('display', 'none', 'important');
    });
    container.style.setProperty('display', 'block', 'important');

    // 3. จัดการสถานะปุ่ม Tab (เคลียร์สีอันเก่า ไฮไลท์อันใหม่)
    const allTabs = document.querySelectorAll('.window-tab');
    allTabs.forEach(tab => {
        tab.classList.remove('tabselect');
    });
    element.classList.add('tabselect');

    // --- 🟢 ตัดสินใจ: ใช้ของที่จำไว้ หรือ โหลดใหม่? ---
    if (isLoaded && !isAlreadyActive) {
        console.log("แท็บนี้มีข้อมูลแล้ว - ใช้ของที่จำไว้ (สลับหน้าจออย่างเดียว)");
        return; // จบฟังก์ชันตรงนี้เลย ข้อมูลเก่าก็ยังอยู่ครบ
    }

    // ==========================================
    // เข้าสู่กระบวนการโหลดข้อมูล (เพิ่งกดครั้งแรก หรือ กดซ้ำเพื่อรีเฟรช)
    // ==========================================
    console.log(isAlreadyActive ? "รีเฟรชข้อมูลใหม่..." : "โหลดข้อมูลครั้งแรก...");

    // 4. ห้ามกด Tab อื่นๆ ทั้งหมดทันที (Lock ทั้งแผง)
    allTabs.forEach(tab => {
        tab.style.pointerEvents = 'none'; 
    });
    element.style.pointerEvents = 'auto'; // ให้ตัวมันเองยังดูเหมือนกดได้อยู่

    // 5. เตรียม Link และสั่งโชว์ Loader
    var link = '/tabmanagement-filemanager/${process.id}?thistab=' + tabId + '&&thisapp=' + appId;
    Loader3.show(showId, 'กำลังโหลดข้อมูล process...');

    // 6. เรียก Data
    getData(link, showId);
    callBackKey(link, showId, "AppTab");

    // 🟢 แปะป้ายบอกว่า "หน้าต่างนี้มีข้อมูลแล้วนะ จำไว้ด้วย!"
    container.setAttribute('data-loaded', 'true');

    // 7. ปลดล็อก Tab เมื่อทุกอย่างเสร็จสิ้น
    const checkFinish = setInterval(() => {
        const overlay = container.querySelector('.loading-overlay');
        // ถ้า overlay หายไปแล้ว หรือ display เป็น none แปลว่าโหลดเสร็จแล้ว
        if (!overlay || overlay.style.display === 'none') {
            allTabs.forEach(tab => {
                tab.style.pointerEvents = 'auto'; // กลับมาให้กดได้ตามปกติ
            });
            clearInterval(checkFinish);
        }
    }, 500); 
}







// --- ส่วนสำคัญ: เปิดหน้ามาแล้วกดให้เลย ---
function LoadTab(mycalss) {
    // 1. ไปดูว่าในเครื่องจำ Tab ไหนไว้ล่าสุด
    const lastTabId = localStorage.getItem('lastSelectedTabId');

    if (lastTabId) {
        // 2. ค้นหา Element ของ Tab นั้น
        const targetTab = document.getElementById('tab-link-' + lastTabId);
        
        if (targetTab) {
            console.log("เจอค่าเก่า สั่งคลิก Tab ID:", lastTabId);
            targetTab.click(); // สั่งให้เกิด Event click (ฟังก์ชัน switchTab จะทำงานทันที)
        } else {
            // ถ้าหา ID เดิมไม่เจอ (เช่น ข้อมูลถูกลบ) ให้คลิก Tab แรกแทน
            clickFirstTab();
        }
    } else {
        // ถ้าเป็นการเปิดครั้งแรก (ยังไม่มีในจำ) ให้คลิก Tab แรกเป็น Default
        clickFirstTab();
    }
};

function clickFirstTab() {
  //  const firstTab = document.querySelector('.window-tab');
 if (firstTab) firstTab.click();
}

</script>

<script>

var Loader3 = {
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




</html>
