<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {

  $code = $title = $semester = $days = $time = $instructor = $room = "";
  $start_date = $end_date = $successful = "";

  // Check POST request data
  if (!empty(($_POST))) {

    $sql = "INSERT INTO Course (code, title, semester, days, time, instructor, room, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    if ($stmt = mysqli_prepare($conn, $sql)) {
      mysqli_stmt_bind_param($stmt, "sssssssss", $code, $title, $semester, $days, $time, $instructor, $room, $start_date, $end_date);
      // Set parameters
      $code = $_POST["code"];
      $title = $_POST["title"];
      $semester = $_POST["semester"];
      $days = $_POST["days"];
      $time = $_POST["time"];
      $instructor = $_POST["instructor"];
      $room = $_POST["room"];
      if (str_contains($_POST["semester"], 'Fall')) {
        $start_date = date('y-m-d', mktime(0, 0, 0, 9, 5, intval(substr($_POST["semester"], -4))));
        $end_date = date('y-m-d', mktime(0, 0, 0, 12, 22, intval(substr($_POST["semester"], -4))));
      } else {
        $start_date = date('y-m-d', mktime(0, 0, 0, 1, 8, intval(substr($_POST["semester"], -4))));
        $end_date = date('y-m-d', mktime(0, 0, 0, 4, 30, intval(substr($_POST["semester"], -4))));
      }
      if (mysqli_stmt_execute($stmt)) {
        $successful = "Course created successfully.";
      } else {
        echo "Something went wrong.", mysqli_stmt_error($stmt);
      }

      mysqli_stmt_close($stmt);
    }
  } else {
    echo "Could not retrieve any data. Please try again.";
  }
} 
    // Close connection
    mysqli_close($conn);
?>
