<?php

include 'db_config.php';
$servername = SERVERNAME;
$db = DBNAME;
$isnewuser = (int)$_POST["isNewUser"];

function login()
{
  try
  {
    $conn = new PDO("mysql:host=$servername;dbname=$db", USER, PASSWORD);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
 
    //Queries the database for given user.
    $sql = $conn->prepare("SELECT username, password FROM user WHERE username = :user AND password = :pass");
    $sql->bindparam(':user',$_POST["username"]);
    $sql->bindparam(':pass',$_POST["password"]);
    $out = $sql->execute();

    //Validate given user credentials.
    if(!empty($out))
    {
      echo "Login Succssful.";
    }
    else
    {
      echo "Login failed.";
    }
  }
  catch(PDOException $e)
  {
    echo "Error: " . $e->getMessage();
  }
  $conn = null;
}

function registerUser()
{
  try
  {
    $conn = new PDO("mysql:host=$servername;dbname=$db", USER, PASSWORD);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // prepare sql and bind parameters
    $stmt = $conn->prepare("INSERT INTO user (firstname, lastname, username, password, email) 
    VALUES (:Firstname, :Lastname, :Username, :Password, :Email)");
    $stmt->bindParam(':Firstname', $_POST["firstname"]);
    $stmt->bindParam(':Lastname', $_POST["lastname"]);
    $stmt->bindParam(':Username', $_POST["username"]);
    $stmt->bindParam(':Password', $_POST["password"]);
    $stmt->bindParam(':Email', $_POST["email"]);
    $stmt->execute();
    echo "Registeration Successful."
  }
  catch(PDOException $e)
  {
    echo "Error: " . $e->getMessage();
  }
  $conn = null;
}

if(isnewuser)
{
  registerUser();
}
else
{
  login();
}

?>