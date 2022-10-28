<?php include 'admin_header.php' ?>

<div class="container theme-showcase" role="main">
  <!-- Main jumbotron for a primary marketing message or call to action -->
  <div class="jumbotron" style="padding: 50px 50px 250px 50px;">

    <form action="admin_home.php" method="post">
      <h3>Course Enrolled Students:</h3>
      <div class="form-row">
        <div class="form-group col-md-8">
          <input type="search" class="form-control" name="course_code" placeholder="Search by Course Code..." id="search_course" onkeyup="validate_search_course(); getSearchResults()">
        </div>
        <div class="form-group col-md-4">
          <button class="btn btn-primary" type="submit" name="search_course" id="search_course_submit">Submit</button>
        </div>
      </div>
      <div class="form-row">
        <div class="form-group col-md-8">
          <span id="invalidate_course_alert"></span>
        </div>
      </div>
      <div class="form-row">
        <div class="form-group col-md-8">
          <ul class="list-group list-group-flush" id="search_results"></ul>
        </div>
      </div>
    </form>
    <?php include 'search_course.php' ?>
  </div>


  <div class="jumbotron" style="padding: 50px 50px 250px 50px;">
    <form action="admin_home.php" method="post">
      <h3>Student Information:</h3>
      <div class="form-row">
        <div class="form-group col-md-8">
          <input type="search" class="form-control" name="student_id" placeholder="Search by Student ID..." id="search_student" onkeyup="validate_search_student()">
        </div>
        <div class="form-group col-md-4">
          <button class="btn btn-primary" type="submit" name="search_student" id="search_student_submit">Submit</button>
        </div>
      </div>
      <div class="form-row">
        <div class="form-group col-md-12">
          <span id="invalidate_id_alert"></span>
        </div>
      </div>
    </form>
    <?php include 'search_student.php' ?>
  </div>
</div>

</div>

<?php include 'admin_footer.php' ?>