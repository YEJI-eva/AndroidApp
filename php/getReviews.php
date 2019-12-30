<?php 
    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('dbcon.php');

    $storeNum=isset($_POST['storeNum']) ? $_POST['storeNum'] : '';

    $stmt = $con->prepare("select userName, reviewContent, regDate, rate from reviews where storeNum = '$storeNum'");
    $stmt->execute();

    if ($stmt->rowCount() > 0)
    {
        $data = array(); 

        while($row=$stmt->fetch(PDO::FETCH_ASSOC))
        {
            extract($row);
    
            array_push($data, 
                array('userName'=>$userName,
                'reviewContent'=>$reviewContent,
                'regDate'=>$regDate,
                'rate'=>$rate
            ));
        }

        header('Content-Type: application/json; charset=utf8');
        $json = json_encode(array("reviews"=>$data), JSON_PRETTY_PRINT + JSON_UNESCAPED_UNICODE);
        echo $json;
    }
?>