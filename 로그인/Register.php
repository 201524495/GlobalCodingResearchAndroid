<?php 
include "./password.php"; 
//DB 연결 -- ("IP 또는 도메인 주소","DB 사용자 아이디","DB 사용자 비밀번호","사용할 DB명");
    $con = mysqli_connect("localhost", "annjs0308", "qntkseo15*", "annjs0308");
    mysqli_query($con,'SET NAMES utf8');

//앱에서 입력받은 ID, PW, Name, Age를 저장
    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userName = $_POST["userName"];
    $userAge = $_POST["userAge"];

// id와 암호화한 PW를 $hash로 설정
    $hash = password_hash($userPassword, PASSWORD_DEFAULT);  // 비밀번호 암호화 

//DB에 저장(INSERT), 물음표가 5개인 것은 5개를 넣겠다는 뜻
    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?,?,?,?,?)");
//string : s, int : i를 의미한다. ID, PW, Name, Age, hash 다 저장
    mysqli_stmt_bind_param($statement, "sssis", $userID, $userPassword, $userName, $userAge, $hash);
// 쿼리 실행
    mysqli_stmt_execute($statement);

//$response 변수 배열화
	$response = array();
//success가 true가 되면 앱에서 Toast & Intent 실행
    	$response["success"] = true;

// 
    echo json_encode($response);

?>