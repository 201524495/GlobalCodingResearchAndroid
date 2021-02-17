<?php
include_once('./new_Login/db.php');
include_once('./password.php');
//Login Button
$userID = $_POST["userID"]; //로그인 시 입력받은 비밀번호
$userPasswd = $_POST["userPassword"]; //로그인 시 입력받은 비밀번호

$sql = mq("SELECT * FROM USER WHERE userID='$userID'"); //아이디와 동일한 레코드 추출
$result = $sql->fetch_assoc(); //$result로 저장하고
$hash = $result['hash'];//result의 hash 값을 $hash에 저장

 // 암호화된 PW와 입력받은 PW 비교
if(password_verify($userPasswd, $hash)){ // $result에 저장된 <hash> 값과 비교
  echo 'success'; //
}
else{
  echo 'fail'; //
}
?>
