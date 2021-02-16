<?php
include_once('./new_Login/db.php');
include_once('./password.php');
//MainActivity에서 DB의 내용을 모두 출력하고 싶다.
$userID = $_POST["userID"]; //앱에서 입력받고
$userpasswd = $_POST["userPassword"]; //앱에서 앱력받고

//DB에 있는 userID와 입력받은 userID가 같은 레코드 select
$sql = mq("SELECT * FROM USER WHERE userID='$userID'");
$result = $sql->fetch_assoc();

if() {//널 값이 아니면
  echo 'success';
}
else {//널 값이면
  echo 'fail';
}
?>
