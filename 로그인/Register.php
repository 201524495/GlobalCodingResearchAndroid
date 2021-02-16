<?php
include_once('./new_Login/db.php');
include_once('./password.php');
//Register Button
    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userName = $_POST["userName"];
    $userAge = $_POST["userAge"];
// id와 암호화한 PW를 DB에 저장
    $hash = password_hash($userPassword, PASSWORD_DEFAULT);  // 비밀번호 암호화

//?????????//
    if($userID != " ") { // statement가 true가 되면 ( 입력값이 잘 들어온 경우 )
      $statement = mq("INSERT INTO USER VALUES ('$userID', '$userPassword', '$userName', '$userAge', '$hash')");
      echo 'success'; // *** NULL값 못받는 것도 해야함
    }
    else {// statement가 false가 되면 ( 입력값이 잘 안들어온 경우 )
      echo 'fail';
    }

?>
