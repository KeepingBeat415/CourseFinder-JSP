<?php include 'login-service.php' ?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

  <!-- Optional theme -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

  <!-- Latest compiled and minified JavaScript -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>

  <!-- Custom styles for this template -->
  <link href="css/theme.css" rel="stylesheet" />

  <!-- Custom styles for this template -->
  <link href="css/signin.css" rel="stylesheet">

  <script type="text/javascript" src="javascript/functions.js"></script>

  <title>Login</title>
</head>

<body>

  <div class="container">

    <form class="form-signin" action="index.php" method="post">
      <h2 class="form-signin-heading">Sign in</h2>


<%--      <?php--%>
<%--      if (!empty($username_err)) {--%>
<%--        echo "<div class=\"alert alert-danger\" role=\"alert\">$username_err</div>";--%>
<%--      }--%>
<%--      if (!empty($password_err)) {--%>
<%--        echo "<div class=\"alert alert-danger\" role=\"alert\">$password_err</div>";--%>
<%--      }--%>
<%--      ?>--%>
      <label class="sr-only" for="username">Username</label>
      <input name="username" class="form-control" placeholder="Username" id="username" onkeyup="validate_username()" required autofocus>
      <span id="exist_space_alert"></span>
      <label class="sr-only">Password</label>
      <input type="password" name="password" class="form-control" placeholder="Password" required>
      <p>Don't have an account? <a href="register.jsp">Sign up now</a>.</p>
      <button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">Sign in</button>
    </form>

  </div> <!-- /container -->
</body>



</html>