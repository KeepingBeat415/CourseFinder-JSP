<?php
    // Include config file
    include('config.php'); 
    // Display ERROR
    ini_set('display_errors', 1);
    ini_set('display_startup_errors', 1);
    error_reporting(E_ALL);
    // Initialize variables
    $username = $password = $first_name = $last_name = $address = "";
    $email = $phone_number = $DOB = $user_type = $password_err = $username_err="";
    $successful = "";

    // Processing form data
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        // Prepare a select statement
        $sql = "SELECT id FROM Users WHERE username = ?";
        // Validate username whether exist
        if($stmt = mysqli_prepare($conn, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "s", $username);
            // Set parameters
            $username = trim($_POST["username"]);
            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                /* store result */
                mysqli_stmt_store_result($stmt);
                
                if(mysqli_stmt_num_rows($stmt) == 1){
                    $username_err = "The Username is already existed.";
                } 
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }
        // Close statement
        mysqli_stmt_close($stmt);

        if(empty($password_err) && empty($username_err)){
            $sql = "INSERT INTO Users (username, password, first_name, last_name, address, email, phone_number, DOB, user_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            // Attempt to execute the prepared statement   
            if($stmt = mysqli_prepare($conn, $sql)){
                // Bind variables to the prepared statement as parameters
                mysqli_stmt_bind_param($stmt, "sssssssss", $username, $password, $first_name, $last_name, $address, $email, $phone_number, $DOB, $user_type);
                // Set parameters
                $username = $_POST["username"];
                $password = password_hash($_POST["password"], PASSWORD_DEFAULT); // Creates a password hash
                $first_name = $_POST["first_name"];
                $last_name = $_POST["last_name"];
                $address = $_POST["address"];
                $email = $_POST["email"];
                $phone_number = $_POST["phone_number"];
                $DOB = date('y-m-d', strtotime($_POST["DOB"]));
                $user_type = $_POST["user_type"];
                // Insert into database
                if(mysqli_stmt_execute($stmt)){
                    $successful = "Account created successfully.";
                } else{
                    echo "Oops! Something went wrong." . mysqli_stmt_error($stmt);
                }
                // Close Statement
                mysqli_stmt_close($stmt);
            }
            else{
                echo "Oops! Something went wrong.";
            }
    
        }
    }
    // Close connection
    mysqli_close($conn);
