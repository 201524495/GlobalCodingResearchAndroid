<?php
include_once('./new_Login/db.php');
include_once('./password.php');
//MainActivity에서 DB의 내용을 모두 출력하고 싶다.
$userID = $_GET["userID"]; //앱에서 입력받고
$userpasswd = $_GET["userPassword"]; //앱에서 앱력받고

//DB에 있는 userID와 입력받은 userID가 같은 레코드 select
$sql = mq("SELECT * FROM USER WHERE userID='$userID'");

$row = mysqli_fetch_array($sql);
$data = $row[0];
$result = array();
$result[0] = $row[0];
$result[1] = $row[1];
$result[2] = $row[2];
$result[3] = $row[3];

if($data) {
  echo $data;
}
    //echo json_encode($result2json);//값이 주루룩 나옴
else {//널 값이면
  echo 'fail';
}
?>
