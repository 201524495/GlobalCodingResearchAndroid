<?php 
include "./password.php"; 
    $con = mysqli_connect("localhost", "annjs0308", "qntkseo15*", "annjs0308");
    mysqli_query($con,'SET NAMES utf8');

    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userName = $_POST["userName"];
    $userAge = $_POST["userAge"];
// id와 암호화한 PW를 DB에 저장
    $hash = password_hash($userPassword, PASSWORD_DEFAULT);  // 비밀번호 암호화 

    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssis", $userID, $userPassword, $userName, $userAge, $hash);
    mysqli_stmt_execute($statement);// ID, PW, Name, Age, Hash 다 저장

	$response = array();
    	$response["success"] = true;

    echo json_encode($response);

   

    
 
   




?>