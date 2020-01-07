<?php
	$con = mysqli_connect("localhost", "ndh", "1234", "testdb");
	mysqli_query($con, 'SET NAMES utf8');

	$storeNum = $_POST["storeNum"];
	$userName = $_POST["userName"];
	$reviewContent = $_POST["review"];
	$rate = $_POST["rate"];
	
	

	$statement = mysqli_prepare($con, "INSERT INTO reviews (storeNum, email, userName, reviewContent, rate) VALUES (?, (SELECT email FROM testmember WHERE userName = ?), ?, ?, ?)");
	mysqli_stmt_bind_param($statement, "sssss", $storeNum, $userName, $userName, $reviewContent, $rate);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>