<?php
if(isset($_POST['email']) && isset ($_POST['password']) && isset ($_POST['phone'])){
    require_once "conn.php";
    require_once "validate.php";
    $email = validate($_POST['email']);
    $password = validate($_POST['password']);
    $phone = validate($_POST['phone']);
    $sql = "insert into farmers values ( '', = '$email', '$phone' , '" . md5($password) . "') ";
   
    if(!$conn->query($sql))
{
    echo "success";
}
else
{
echo "failure";
}
}
?>