<?php
require_once '../config.php';

$search = $_GET['search'];
$sql = "SELECT code FROM Course WHERE BINARY code LIKE '$search%' LIMIT 5";
$result = mysqli_query($conn, $sql);
while ($row = mysqli_fetch_assoc($result)) {
  echo "<li class=\"list-group-item\">" . $row['code'] . "</li>";
}
