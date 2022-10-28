<?php
if (isset($_POST['search_course'])) {
  if (empty($_POST['course_code'])) {
    echo "<div class=\"alert alert-secondary\" style=\"position: absolute; margin-top: 60px;\" role=\"alert\">
              Oops! No course matching your search.
            </div>
            ";
  } else {
    $course_code = $_POST['course_code'];
    $sql = "SELECT Users.id, Users.first_name, Users.last_name, Users.phone_number,Users.email FROM Users JOIN Enrolled_In ON Users.id = Enrolled_In.student_id JOIN Course ON Course.id = Enrolled_In.course_id WHERE BINARY Course.code = '$course_code'";

    if ($result = mysqli_query($conn, $sql)) {

      if (mysqli_num_rows($result) > 0) {
        echo "<table class=\"table table-striped\">
                <thead>
                    <tr>
                        <th scope=\"col\">Student ID</th>
                        <th scope=\"col\">Name</th>
                        <th scope=\"col\">Phone</th>
                        <th scope=\"col\">Email</th>
                    </tr>
                </thead>";
        while ($row = mysqli_fetch_array($result)) {
          echo "<tbody>";
          echo "<tr>";
          echo "<td>" . $row['id'] . "</td>";
          echo "<td>" . $row['first_name'] . ' ' . $row['last_name'] . "</td>";
          echo "<td>" . $row['phone_number'] . "</td>";
          echo "<td>" . $row['email'] . "</td>";
          echo "</tr>";
          echo "</tbody>";
        }
        echo "</table>";
      } else {
        echo "<div class=\"alert alert-secondary\"  style=\"position: absolute; margin-top: 60px;\" role=\"alert\">
        Oops! No course matching your search.
      </div>
      ";
      }
    }
  }
}
