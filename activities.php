<?php 
spl_autoload_extensions(".class.php");
spl_autoload_register();
 
$method = $_SERVER['REQUEST_METHOD'];
$request = explode('/', trim($_SERVER['PATH_INFO'], '/'));
$response = new api\models\Response();

switch ($method) {
	case 'GET':
		$getHandler = new api\activities\ActivitiesGetHandler();
		$response = $getHandler->handleRequest($request);
		break;
	case 'POST':
		break;
}
?>
<?php
http_response_code($response->getStatusHeader());
header('Access-Control-Allow-Origin: *'); 
header('Content-type: application/json');
echo $response->getBody();
?>