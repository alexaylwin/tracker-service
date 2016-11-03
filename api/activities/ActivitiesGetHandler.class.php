<?php
namespace api\activities;

class ActivitiesGetHandler {
	/*
		Request Mapping is:
			/activities/$userId

			Where $userId is the id of the user
	*/
	function handleRequest($request) {
		$response = new \api\models\Response();

		if(!isset($request[0]) || empty($request[0])) {
			$response->setStatusHeader(500);
			return $response;
		} 
		//Match the user ID to their activities
		if($request[0] == '1') {
			$response->setStatusHeader(200);
			$response->setBody('[{"id": "1", "name":"Work"},{"id": "2", "name":"Fun"},{"id": "3", "name":"Exercise"},{"id": "4", "name":"Household"}]');
		} else {
			$response->setStatusHeader(200);
			$response->setBody('[{"id": "1", "name":"Default Activity1"},{"id": "2", "name":"Default Activity2"},{"id": "3", "name":"Default Activity3"},{"id": "4", "name":"Default Activity4"}]');
		}

		return $response;
	}
}
?>