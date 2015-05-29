<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<%@ page session="false" %>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title> EXAMPLE application for PCF</title>
    <link rel="stylesheet" href="resources/css/foundation.css" />
    <script src="resources/js/modernizr.js"></script>
  </head>
  <body>

    <nav class="top-bar" data-topbar>
      <ul class="title-area">
        <li class="name">
          <h1><a href="./">EXAMPLE application for PCF</a></h1>
        </li>
      </ul>
      <section class="top-bar-section">
        <ul class="right">
          <li><a href="./">Home</a></li>
          <li><a href="attendees">Attendees</a></li>
          <li><a href="env">Show Environment</a></li>
          <li><a href="checkApp">Version</a></li>
        </ul>
      </section>
    </nav>

    <div class="row">
      <div class="large-12 columns">
        <h3 style="color:#01786E">Demo Application Pivotal CF</h3>
        <hr/>
      </div>
    </div>

    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
          <h4>Hello Pivotal CF</h4><br/>
          <p>The instance index is <b><em>${instanceIndex}</em></b></p>
          
	    </div>
        <hr/>
      </div>
    </div>
    
    <div class="row">
      <div class="large-12 columns">
        <!-- This simply refreshes the page to demonstrate changed (or unchanged) ports -->
        <a href="javascript:window.location.reload();" class="medium button">Refresh</a>
        <!-- This link should be changed to the application's kill action -->
        <a href="kill" class="medium alert button">Kill</a>
      </div>
    </div>

    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>

    </body>
</html>
