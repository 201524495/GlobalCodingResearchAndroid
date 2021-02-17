<?php
include_once('./new_Login/db.php');
include_once('./password.php');
//Register Button
//공백 여부는 앱에서 판단.
    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userName = $_POST["userName"];
    $userAge = $_POST["userAge"];
// id와 암호화한 PW를 DB에 저장
    $hash = password_hash($userPassword, PASSWORD_DEFAULT);  // 비밀번호 암호화

    $sql = mq("SELECT * FROM USER WHERE userID = '$userID'"); // 기존 DB와 비교
    $result = $sql -> fetch_array(); //member에 저장

    if( $result ) { // 아이디가 중복인 경우
      echo 'fail';
      //echo 'ID is already Registered';
    }
    else {// 중복이 아닌 경우
        echo 'success'; // *** NULL값 못받는 것도 해야함
        $statement = mq("INSERT INTO USER VALUES ('$userID', '$userPassword', '$userName', '$userAge', '$hash')");
    }

?>
