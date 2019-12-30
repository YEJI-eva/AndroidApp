<?php 
    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('dbcon.php');

    $stmt = $con->prepare("select registerNum, restaurantName, address, tel from restaurant where cuisine='일식'");
    $stmt->execute();

    if ($stmt->rowCount() > 0)
    {
        $data = array(); 

        while($row=$stmt->fetch(PDO::FETCH_ASSOC))
        {
            extract($row);
    
            array_push($data, 
                array('registerNum'=>$registerNum,
                'restaurantName'=>$restaurantName,
                'address'=>$address,
                'tel'=>$tel
            ));
        }

        header('Content-Type: application/json; charset=utf8');
        $json = json_encode(array("japaneseFood"=>$data), JSON_PRETTY_PRINT + JSON_UNESCAPED_UNICODE);
        echo $json;
    }
?>