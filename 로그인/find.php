<?php

	$host = 'localhost';
	$uname = 'annjs0308';
	$pwd = 'qntkseo15*';
	$db = "annjs0308";

	$con = mysql_connect($host, $uname, $pwd) or die("connection failed");
	mysql_select_db($db, $con) or die("db selection failed");

	$id = $_POST['id'];

	$r = mysql_query("select userID,userPassword from USER where userID='$id'", $con);

	$row = mysql_fetch_array($r);

	print(json_encode($row));
	mysql_close($con);

?>