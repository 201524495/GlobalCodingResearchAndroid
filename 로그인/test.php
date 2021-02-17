<?php
include_once('./new_Login/db.php');
include_once('./password.php');
//MainActivity에서 DB의 내용을 모두 출력하고 싶다.
$userID = $_GET["userID"]; //앱에서 입력받고
$userpasswd = $_GET["userPassword"]; //앱에서 앱력받고

//DB에 있는 userID와 입력받은 userID가 같은 레코드 select
$sql = mq("SELECT * FROM USER WHERE userID='$userID'");
$result = $sql->fetch_assoc();

if(strcmp($result['userID'], $userID) == 0) {//일치하는 값이 있으면

//  $result2json = array()
  
  echo json_encode($result);//값이 주루룩 나옴
//json 은 NAME과 VALUE를 가지고 있음
}
else {//널 값이면
  echo 'fail';
}
?>
