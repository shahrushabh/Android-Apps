<?php

global $conn;

include 'db_config.php';
$servername = SERVERNAME;
$db = DBNAME;

  try
  {
    $conn = new PDO("mysql:host=$servername;dbname=$db", USER, PASSWORD);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  }
    catch(PDOException $e)
  {
    echo "Connection failed: " . $e->getMessage();
  }
 
 //Queries the database with appropriate field as specified.
 $sql = 'SELECT Opponent, Price, Date, Section, Seat, Sport, Biddable
          FROM sale_listing';
  $q = $conn->query($sql);

  //Fetching data from database.
  $q->setFetchMode(PDO::FETCH_ASSOC);
  $result = $q->fetchAll();

  // Encode it as JSON and output back to client.
  print json_encode($result);
?>
