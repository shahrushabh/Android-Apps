<?php

include 'db_config.php';
$servername = SERVERNAME;
$db = DBNAME;
$conn = null;

  try
  {
    $conn = new PDO("mysql:host=$servername;dbname=$db", USER, PASSWORD);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // prepare sql and bind parameters
    $stmt = $conn->prepare("INSERT INTO sale_listing (Opponent, Price, Date, Section, Seat, Sport, Biddable, Owner)
    VALUES (:Opponent, :Price, :Date, :Section, :Seat, :Sport, :Biddable, :Owner)");
    $stmt->bindParam(':Opponent', $Opponent);
    $stmt->bindParam(':Price', $Price);
    $stmt->bindParam(':Date', $Date);
    $stmt->bindParam(':Section', $Section);
    $stmt->bindParam(':Seat', $Seat);
    $stmt->bindParam(':Sport', $Sport);
    $stmt->bindParam(':Biddable', $Biddable);
    $stmt->bindParam(':Owner', $Owner);

    // Inserts a new Listing into the database.
    $Opponent = $_POST["Opponent"];
    $Price = $_POST["Price"];
    $Date = $_POST["Date"];
    $Section = $_POST["Section"];
    $Seat = $_POST["Seat"];
    $Sport = $_POST["Sport"];
    $Biddable = $_POST["Biddable"];
    $Owner = "rs";
    $stmt->execute();

    echo "New listing has been created successfully!";
  }

  catch(PDOException $e)
  {
    echo "Error: " . $e->getMessage();
  }
  $conn = null;
?>
