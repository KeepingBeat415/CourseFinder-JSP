function validate_password() {
  let pass = document.getElementById("password").value;
  let confirm_pass = document.getElementById("confirm_password").value;
  if (pass !== confirm_pass) {
    document.getElementById("wrong_pass_alert").style.color = "red";
    document.getElementById("wrong_pass_alert").innerHTML =
      "Password is Not matched.";
    document.getElementById("submit").disabled = true;
    document.getElementById("submit").style.opacity = 0.4;
  } else {
    document.getElementById("wrong_pass_alert").style.color = "white";
    document.getElementById("wrong_pass_alert").innerHTML = "";
    document.getElementById("submit").disabled = false;
    document.getElementById("submit").style.opacity = 1;
  }
}

function validate_search_student() {
  let student_id = document.getElementById("search_student").value;

  if (!/^\d+$/.test(student_id)) {
    document.getElementById("invalidate_id_alert").style.color = "red";
    document.getElementById("invalidate_id_alert").innerHTML =
      "student ID only contains number.";
    document.getElementById("search_student_submit").disabled = true;
    document.getElementById("search_student_submit").style.opacity = 0.4;
  } else {
    document.getElementById("invalidate_id_alert").style.color = "white";
    document.getElementById("invalidate_id_alert").innerHTML = "";
    document.getElementById("search_student_submit").disabled = false;
    document.getElementById("search_student_submit").style.opacity = 1;
  }
}

function validate_search_course() {
  let course_code = document.getElementById("search_course").value;

  if (!/^([A-Z]{3,4})-(\d{3,4})$/.test(course_code)) {
    document.getElementById("invalidate_course_alert").style.color = "red";
    document.getElementById("invalidate_course_alert").innerHTML =
      "Should be in the form SOEN-387, MANA-2257, etc.";
    document.getElementById("search_course_submit").disabled = true;
    document.getElementById("search_course_submit").style.opacity = 0.4;
  } else {
    document.getElementById("invalidate_course_alert").style.color = "white";
    document.getElementById("invalidate_course_alert").innerHTML = "";
    document.getElementById("search_course_submit").disabled = false;
    document.getElementById("search_course_submit").style.opacity = 1;
  }
}

function validate_search_course_info() {
  let course_code = document.getElementById("search_course_info").value;

  if (!/^([A-Z]{3,4})-(\d{3,4})$/.test(course_code)) {
    document.getElementById("invalidate_course_info_alert").style.color = "red";
    document.getElementById("invalidate_course_info_alert").innerHTML =
      "Should be in the form SOEN-387, MANA-2257, etc.";
    document.getElementById("search_course_info_submit").disabled = true;
    document.getElementById("search_course_info_submit").style.opacity = 0.4;
  } else {
    document.getElementById("invalidate_course_info_alert").style.color =
      "white";
    document.getElementById("invalidate_course_info_alert").innerHTML = "";
    document.getElementById("search_course_info_submit").disabled = false;
    document.getElementById("search_course_info_submit").style.opacity = 1;
  }
}

function validate_username() {
  let username = document.getElementById("username").value;
  if (/\s/g.test(username)) {
    document.getElementById("exist_space_alert").style.color = "red";
    document.getElementById("exist_space_alert").innerHTML =
      "Cannot contains whitespace.";
    document.getElementById("submit").disabled = true;
    document.getElementById("submit").style.opacity = 0.4;
  } else {
    document.getElementById("exist_space_alert").style.color = "white";
    document.getElementById("exist_space_alert").innerHTML = "";
    document.getElementById("submit").disabled = false;
    document.getElementById("submit").style.opacity = 1;
  }
}
