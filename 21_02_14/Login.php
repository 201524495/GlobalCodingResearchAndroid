<?php
    $con = mysqli_connect("localhost", "annjs0308", "qntkseo15*", "annjs0308");
    mysqli_query($con,'SET NAMES utf8');

    $userID = $_POST["userID"]; //로그인 시 입력받은 비밀번호?
    $userPassword = $_POST["userPassword"]; //로그인 시 입력받은 비밀번호?

//USER DB에서 회원 정보 가져오기
    $result = mysql_query("Select * FROM USER WHERE userID = '$userID');
    $array = mysql_fetch_array($result);
    $hash_password = $array['password'];

    $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE userID = ? AND hash = ?");
    mysqli_stmt_bind_param($statement, "ss", $userID, $hash);
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $userPassword, $userName, $userAge, $hash);

    $response = array();
    $response["success"] = false;
 
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["userID"] = $userID;
        $response["userPassword"] = $userPassword;
        $response["userName"] = $userName;
        $response["userAge"] = $userAge;        
        $response["hash"] = $hash;
    }

    echo json_encode($response);



?>