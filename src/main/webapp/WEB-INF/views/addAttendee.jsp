<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<%@ page session="false" %>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Pivotal CF Workshop | Attendee AMI Lookup</title>
    <link rel="stylesheet" href="resources/css/foundation.css" />
    <script src="resources/js/modernizr.js"></script>
  </head>
  <body>

    <nav class="top-bar" data-topbar>
      <ul class="title-area">
        <li class="name">
          <h1><a href="./">Pivotal CF</a></h1>
        </li>
      </ul>
    </nav>

    <div class="row">
      <div class="large-12 columns">
        <h3>Add Attendee</h3>
        <hr/>
      </div>
    </div>
	
    <div class="row">
      <div class="input">
        <form action="addAttendee" method="post">
        	First Name: <input type="text" id="firstNamefirstName" name="firstName">
        	Last Name:<input type="text" id="lastName" name="lastName">
        	Email Address:<input type="text" id="emailAddress" name="emailAddress">
        	<input type="submit" class="medium button" value="Add">
        	<a href="attendees" class="medium button">Cancel</a>
        </form>
      </div>
    </div>
   
    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>

    </body>
</html>
