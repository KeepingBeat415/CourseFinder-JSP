<?php
if (isset($_POST['search_student'])) {
  if (empty($_POST['student_id'])) {
    echo "<div class=\"alert alert-secondary\" role=\"alert\">
              Oops! No student ID matching your search.
            </div>
            ";
  } else {
    $student_id = $_POST['student_id'];
    // SQL Student Information
    $sql = "SELECT first_name, last_name FROM Users WHERE id = '$student_id' AND user_type = 'student'";

    if ($result = mysqli_query($conn, $sql)) {
      if (mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_array($result)) {
          echo "<h4> Name: " . $row['first_name'] . ' ' . $row['last_name'] . "</h4>";
        }
        $sql = "SELECT Course.code, Course.title, Course.semester, Course.days, Course.time, Course.room FROM Course JOIN Enrolled_In ON Course.id = Enrolled_In.course_id WHERE Enrolled_In.student_id = '$student_id'";

        if ($result = mysqli_query($conn, $sql)) {

          if (mysqli_num_rows($result) > 0) {
            echo "<table class=\"table table-striped\">
                    <thead>
                        <tr>
                            <th scope=\"col\">Course</th>
                            <th scope=\"col\">Title</th>
                            <th scope=\"col\">Semester</th>
                            <th scope=\"col\">Schedule</th>
                            <th scope=\"col\">Location</th>
                        </tr>
                    </thead>";
            while ($row = mysqli_fetch_array($result)) {
              echo "<tbody>";
              echo "<tr>";
              echo "<td>" . $row['code'] . "</td>";
              echo "<td>" . $row['title'] . "</td>";
              echo "<td>" . $row['semester'] . "</td>";
              echo "<td>" . $row['days'] . " " . $row['time'] . "</td>";
              echo "<td>" . $row['room'] . "</td>";
              echo "</tr>";
              echo "</tbody>";
            }
            echo "</table>";
          } else {
            echo "<div class=\"alert alert-secondary\" role=\"alert\">
            Oops! The student did not enrolled any courses.
          </div>
          ";
          }
        }
      } else {
        echo "<div class=\"alert alert-secondary\" role=\"alert\">
              Oops! No student ID matching your search.
            </div>
            ";
      }
    }
  }
}
