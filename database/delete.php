
<?php
$conn = new mysqli("localhost", "root", "", "usercrud");

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST['id'];

    $stmt = $conn->prepare("DELETE FROM users WHERE id=?");
    $stmt->bind_param("i", $id);

    if ($stmt->execute()) {
        echo "User Deleted Successfully";
    } else {
        echo "Error: " . $conn->error;
    }
}
?>
