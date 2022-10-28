<?php
// Initialize session
session_start();
// Include config file
require_once "config.php";
// Display ERROR Messages
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

//Check whether user is already logged in, and redirect to home page
if(isset($_SESSION["logged_in"]) && $_SESSION["logged_in"] === true){

    if($_SESSION["user_type"]=="admin"){
        header("location: admin/admin_home.php");
    }
    else{
        header("location: student/student_home.php");
    }

}

// Initialize variables
$username = $password = $user_type = "";
$username_err = $password_err = "";

// Processing form data when form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $sql = "SELECT id, username, password, user_type FROM Users WHERE username = ?";

    if ($stmt = mysqli_prepare($conn, $sql)) {
        // Bind variables to the prepared statement as parameters
        mysqli_stmt_bind_param($stmt, "s", $username);

        $username = $_POST["username"];
        $password = $_POST["password"];

        // Attempt to execute the prepared statement
        if (mysqli_stmt_execute($stmt)) {
            // Store result
            mysqli_stmt_store_result($stmt);
            // Check username exists, then verify password
            if (mysqli_stmt_num_rows($stmt) == 1) {
                // Bind result variables
                mysqli_stmt_bind_result($stmt, $id, $username, $hashed_password, $user_type);

                if (mysqli_stmt_fetch($stmt)) {
                    // Check password, then start a new session
                    if (password_verify($password, $hashed_password)) {

                        session_start();
                        //Store data in session variables
                        $_SESSION["logged_in"] = true;
                        $_SESSION["user_type"] = $user_type;
                        $_SESSION["id"] = $id;

                        // Redirect user to admin or student page
                        if ($user_type == "admin") {
                            header("location: admin/admin_home.php");
                        } else {
                            header("location: student/student_home.php");
                        }
                    } else {
                        // Display error message when password is not valid
                        $password_err = "Password is incorrect.";
                    }
                }
            } else {
                // Display error message when username doesn't exist
                $username_err = "Username is not exist.";
            }
        } else {
            echo "Oops! Something went wrong.";
        }
        // Close statement
        mysqli_stmt_close($stmt);
    }
}
// Close connection
mysqli_close($conn);
?>