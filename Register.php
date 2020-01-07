<?php
	$con = mysqli_connect("localhost", "ndh", "1234", "testdb");
	mysqli_query($con, 'SET NAMES utf8');

	$email = $_POST["email"];
	$password = $_POST["password"];
	$username = $_POST["username"];

	$statement = mysqli_prepare($con, "INSERT INTO testmember VALUES (?, ?, ?)");
	mysqli_stmt_bind_param($statement, "sss", $email, $password, $username);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>