<?php
//DB 연결 -- ("IP 또는 도메인 주소","DB 사용자 아이디","DB 사용자 비밀번호","사용할 DB명");
    $con = mysqli_connect("localhost", "annjs0308", "qntkseo15*", "annjs0308");
//한글 사용 가능
    mysqli_query($con,'SET NAMES utf8');

    $userID = $_POST["userID"]; //로그인 시 입력받은 비밀번호
    $userPasswd = $_POST["userPassword"]; //로그인 시 입력받은 비밀번호
/*
//  DB에서 회원 정보 가져오기
//result는 userID와 일치하는 모든 degree를 들고온다
    $result = mysql_query("Select * FROM USER WHERE userID = '$userID'");
//연관배열과 숫자 인덱스 배열을 모두 사용할 수 있다. 
    $array = mysql_fetch_array($result); 
//hash_password는 DB에 있는 암호화된 비밀번호
    $hash_password = $array['hash']; 

//$userPasswd 는 로그인할 때 받아온 비밀번호 , $hash_password는 DB에 있는 암호화된 비밀번호
//    if( password_verify($userPasswd, $hash_password) ) {  } // 확인
*/
// statement는 userID를 비교해 같으면 진행 //준비된 SELECT문을 제작
      $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE userID = ?");
//statement에 ID 저장, 파라미터 값을 설정한다. 
      mysqli_stmt_bind_param($statement, "s", $userID);
//쿼리 실행
      mysqli_stmt_execute($statement);

//한 번에 하나의 레코드만을 반환
    mysqli_stmt_store_result($statement);
//데이터 버퍼를 바인딩해서 mysqli_stmt_bind_result() 호출로 추출한 열값을 사용
    mysqli_stmt_bind_result($statement, $userID, $userPassword, $userName, $userAge);

//$response 배열 선언
    $response = array();
//success가 true가 되면 앱에서 jsonObject, Toast, Intent 실행
    $response["success"] = true;
 
//더이상 열이 존재하지 않을 때까지 반복적으로 호출
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["userID"] = $userID;
        $response["userPassword"] = $userPassword;
        $response["userName"] = $userName;
        $response["userAge"] = $userAge;        
    }

    echo json_encode($response);

?>