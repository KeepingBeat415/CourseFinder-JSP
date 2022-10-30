<?php include 'admin_header.php' ?>
<?php include 'course_service.php' ?>

<html>

<body>
  <div class="container-fluid">
    <h2>Create Course</h2>
    <?php
    if (!empty($successful)) {
      echo "<div class=\"alert alert-success\" role=\"alert\">$successful</div>";
    }
    ?>
    <form action="course_create.php" name="courseForm" onsubmit="return validateForm()" method="POST">
      <div class="form-group">
        <label>Course Code</label>
        <input type="text" name="code" class="form-control" required>
        <small class="form-text text-muted">Should be in the form SOEN-387, MANA-2257, etc.</small>
      </div>
      <div class="form-group">
        <label>Title</label>
        <input type="text" name="title" class="form-control" required>
      </div>
      <div class="form-group">
        <label>Semester</label>
        <input type="text" name="semester" class="form-control" required>
        <small class="form-text text-muted">Should be in the form Fall 2021, Winter 2022, etc.</small>
      </div>
      <div class="form-group">
        <label>Days</label>
        <input type="text" name="days" class="form-control" required>
        <small class="form-text text-muted" id="days-check">Should be in the form MonWedFri, MonFri, TueThu, Thu, etc.</small>
      </div>
      <div class="form-group">
        <label>Time</label>
        <input type="text" name="time" class="form-control" required>
        <small class="form-text text-muted">Should be in the form 8:45-10:00, 13:15-14:30, etc.</small>
      </div>
      <div class="form-group">
        <label>Instructor</label>
        <input type="text" name="instructor" class="form-control" required>
      </div>
      <div class="form-group">
        <label>Room</label>
        <input type="text" name="room" class="form-control" required>
      </div>
      <br />
      <button type="submit" class="btn btn-primary mb-2">Create Course</button>
    </form>
  </div>

  <script>
    function validateForm() {
      let form = document.forms["courseForm"];
      const codeRegex = /^([A-Z]{3,4})-(\d{3,4})$/;
      const semesterRegex = /^((Fall)|(Winter))\s20[2-9][0-9]$/;
      const daysRegex = /^((Mon)|(Tue)|(Wed)|(Thu)|(Fri)){1,5}$/;
      const timeRegex = /^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]-([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
      // VALIDATE HERE  
      if (!codeRegex.test(form["code"].value)) {
        alert("Please check course code value again.");
        return false;
      }
      if (!semesterRegex.test(form["semester"].value)) {
        alert("Please check semester value again.");
        return false;
      }
      if (!daysRegex.test(form["days"].value)) {
        alert("Please select at least 1 day of the week");
        return false;
      }
      if (!timeRegex.test(form["time"].value)) {
        alert("Please check time value again.");
        return false;
      }
      // SUBMIT
      submit();
    }

    function submit() {
      document.forms["courseForm"].submit();
    }
  </script>
</body>

</html>