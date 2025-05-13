<?php
$conn = new mysqli("localhost", "root", "", "usercrud");

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    $stmt = $conn->prepare("INSERT INTO users (name, email, password) VALUES (?, ?, ?)");
    $stmt->bind_param("sss", $name, $email, $password);

    if ($stmt->execute()) {
        echo "User Created Successfully";
    } else {
        echo "Error: " . $conn->error;
    }
}
?>

