<?php
if(isset($_POST['email']) && isset ($_POST['password'])){
    require_once "conn.php";
    // require_once "validate.php";
    $email = $_POST['email'];
    $password1 = $_POST['password'];
    $sql = "select * from farmers where email = '$email'and password= '" . md5($password1) . "'";
    $result = $conn->query ($sql);
    if($result->num_rows > 0)
{
    echo "success";
}
else
{
echo "failure";
}
}
?>