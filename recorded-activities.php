<?php 
spl_autoload_extensions(".class.php");
spl_autoload_register();
 
$method = $_SERVER['REQUEST_METHOD'];
$request = explode('/', trim($_SERVER['PATH_INFO'], '/'));
$response = new api\models\Response();

switch ($method) {
	case 'PUT':
		$putHandler = new api\recordedActivities\RecordedActivitiesPutHandler();
		$response = $putHandler->handleRequest($request);
		break;
}
?>
<?php
http_response_code($response->getStatusHeader());
header('Access-Control-Allow-Origin: *'); 
header('Content-type: application/json');
echo $response->getBody();
?>