
<?php
include '../db.php';
include '../password.php';

if($_POST['userid'] == "" || $_POST['userpw'] == ""){
  // echo "<script> alert('아이디나 패스워드를 입력해 주세요.'); history.back(); </script>";
  echo "아이디나 패스워드를 입력해 주세요.";
}
else{
  $id = $_POST['userid'];
  $password = $_POST['userpw'];
  $sql = mq("SELECT * FROM member WHERE id='$id'");
  $member = $sql->fetch_array();
  $hash_pw = $member['pw'];
  if(password_verify($password, $hash_pw)){
    //$_SESSION['userid'] = $id;
    //$_SESSION['userpw'] = $hash_pw;
    // echo "<script> location.href='main.php'; </script>";
    // echo "Login success";
    $member2json = array("id" => $member['id'], "email" => $member['email'], "userClass" => $member['user_class']);
    header("Content-type: application/json");
    echo json_encode($member2json);
  }
  else{
    // echo "<script> alert('아이디 혹은 비밀번호를 다시 확인해 주세요'); history.back();</script> ";
    $member2 = array("id" => "", "email" => "", "userClass" => "");
    header("Content-type: application/json");
    echo json_encode($member2);
  }
}



?>
