<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
*{
  font-family: 'Mulish', sans-serif;
}
body{
  width: 100vw;
  height: 100vh;
  background: #1A182D;
}

.select-users-holder{
  position: fixed;
  width: 100%;
  height: 100%;
  z-index: 9;
  display: flex;
  align-items: center;
  justify-content: center;
}

.select-users{
  max-width: 750px;
  max-height: 550px;
  background: #141324;
  padding: 40px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  flex-direction: column;
  border-radius: 5px;
}

.title{
  color: #D1D5E5;
  font-weight: 600;
}

.users{
  display: flex;
  gap: 16px;
}

.user{
  width: 150px;
  height: 190px;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  flex-direction: column;
  border-radius: 10px;
  background: #1A182D;
  cursor: pointer;
  transition: all .25s ease 0s;
}

.user-image{
  width: 75px;
  height: 75px;
  background: #141324;
  padding: 5px;
  overflow: hidden;
  border-radius: 50%;
  display: block;
}

.user-image img{
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 50%;
}

.user-name, .add-text{
  font-size: 16px;
  font-weight: 600;
  color: #D1D5E5
}

.add-text{
  color: #5A9EE2;
}

.add-icon{
  width: 75px;
  height: 75px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.add-icon svg{
  width: 75%;
  height: 75%;
  color: #5A9EE2;
}

.user:focus{
  box-shadow: 0 0 0 3px #5A9EE280;
  outline: 0;
}
</style>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="select-users-holder" id="app">
  <div class="select-users">
    <h2 class="title">{{ title }}</h2>
    <div class="users">
      <span class="user" v-for="(user, index) in users" :key="index" tabindex="0">
        <span class="user-image"><img :src="user.avatar" :alt="user.name"></span>
        <span class="user-name">{{ user.name }}</span>
      </span>
      
      <span class="add-user user" tabindex="0">
        <span class="add-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
          </svg>          
        </span>
        <span class="add-text">Add user</span>
      </span>
    </div>
  </div>
</div>
</body>
</html>