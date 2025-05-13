
<?php
$conn = new mysqli("localhost", "root", "", "usercrud");

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST['id'];
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    $stmt = $conn->prepare("UPDATE users SET name=?, email=?, password=? WHERE id=?");
    $stmt->bind_param("sssi", $name, $email, $password, $id);

    if ($stmt->execute()) {
        echo "User Updated Successfully";
    } else {
        echo "Error: " . $conn->error;
    }
}
?>
