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

function getSearchResults() {
  let results = document.getElementById("search_results");
  let searchVal = document.getElementById("search_course").value;

  if (searchVal.length < 1) {
    results.style.display = "none";
    return;
  }

  //console.log('searchVal : ' + searchVal);
  let xhr = new XMLHttpRequest();
  let url = "search_results.php?search=" + searchVal;
  // open function
  xhr.open("GET", url, true);

  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4 && xhr.status == 200) {
      let text = xhr.responseText;
      //console.log('response from: ' + xhr.responseText);
      results.innerHTML = text;
      results.style.display = "block";
    }
  };

  xhr.send();
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
