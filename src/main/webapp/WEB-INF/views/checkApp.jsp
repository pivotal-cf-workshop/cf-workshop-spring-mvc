<!DOCTYPE html>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>Check App</title>
<link rel="stylesheet" href="resources/css/foundation.css" />

<script type="text/javascript">
	
	function getVersion(){
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("GET", "version", false);
		xmlHttp.send();
		return xmlHttp.responseText;
	}
	
	function addVersion(timeString){
		var div = document.getElementById('checkapp'); 
		div.innerHTML = div.innerHTML + getVersion() + ' - ' + timeString + '<br/>';
		window.scrollTo(0,document.body.scrollHeight);
		return;
	}
	
	var continueChecking;
	
	function startCheck(){
		continueChecking = setInterval(function (){ 
			d = new Date(); 
			addVersion(d.toLocaleTimeString())}, 2000);
	}
	
	function stopCheck(){
		clearInterval(continueChecking);
	}
	
</script>
</head>
<body>

    <nav class="top-bar" data-topbar>
      <ul class="title-area">
        <li class="name">
          <h1><a>Continuously Ping Version Endpoint</a></h1>
        </li>
      </ul>
      <section class="top-bar-section">
        <ul class="right">
          <li><a href=".">Home</a></li>
        </ul>
      </section>
    </nav>
	<br/>
	<a href="#" class="medium button"
	onclick="startCheck()"
	 >Start</a><br/>
	<div id="checkapp" class="large-12 columns">
	</div>
	<a href="#" class="medium button"
		onclick="stopCheck()"
		>Stop</a>
		
	<a href="#" class="medium button"
	 onclick="document.getElementById('checkapp').innerHTML=''">Clear</a>

	
</body>
</html>