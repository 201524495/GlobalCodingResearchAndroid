<?php
include_once('../db.php');
include_once('../password.php');


$userid = $_POST['userid'];
$userpw = password_hash($_POST['userpw'], PASSWORD_DEFAULT);
$email = $_POST['email'];
// $email = $_POST['email']."@".$_POST['emadress'];
$user_class = $_POST['userClass'];

$sql = mq("SELECT * FROM member WHERE id='$userid'");
$member = $sql->fetch_array();

if(!$member==0){
  echo "overlap id";
}
else{
  mq("INSERT INTO member (user_class, id, pw, email) VALUES ('$user_class', '$userid', '$userpw', '$email')");
  if(strcmp($user_class, 'Student')==0){
    mq("INSERT INTO mb_student (mb_id) VALUES ('$userid')");
    mq("INSERT INTO mb_clear (mb_id) VALUES ('$userid')");
  }
  echo "signup success";
}
?>
