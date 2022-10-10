<?php
$server = "localhost";
$username = "root";
$password = "";
$database = "pd_login";
$conn = new mysqli($server, $username, $password, $database);
if($conn->connect_error)
{
    die( "Connection_failed:" . $conn->connect_error);
}else{
    echo "sucess";
}
?>

