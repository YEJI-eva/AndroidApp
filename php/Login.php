<?php
	$con = mysqli_connect("localhost", "ndh", "1234", "testdb");
	mysqli_query($con, 'SET NAMES utf8');

	$email = $_POST["email"];
	$password = $_POST["password"];

	$statement = mysqli_prepare($con, "SELECT * FROM TESTMEMBER WHERE email = ? AND password = ?");
	mysqli_stmt_bind_param($statement, "ss", $email, $password);
	mysqli_stmt_execute($statement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $email, $password, $username);

	$response = array();
	$response["success"] = false;

	while(mysqli_stmt_fetch($statement)){
		$response["success"] = true;
		$response["email"] = $email;
		$response["password"] = $password;
		$response["username"] = $username;
	}

	echo json_encode($response);
?>