<?php

include('db_config.php');

$servername = SERVERNAME;
$db = DBNAME;

  try
  {
    $conn = new PDO("mysql:host=$servername;dbname=$db", USER, PASSWORD);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "Connection SuccessFul!";
    $conn = null;
  }
  catch(PDOException $e)
  {
    echo "Connection failed: " . $e->getMessage();
  }

?>
