 <?php
  // session_start();

  $db = new mysqli("localhost", "annjs0308", "qntkseo15*", "annjs0308");
  $db->set_charset("utf8");

  if ($db->connect_errno){
    die('Connect Error: '.$db->connect_error);
  }

  function mq($sql){
    global $db;
    return $db->query($sql);
  }

  ?>
