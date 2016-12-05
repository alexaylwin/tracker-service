<?php

namespace api\recordedActivities;

class RecordedActivitiesPutHandler {
	/*
		Request Mapping is:
			/recorded-activities/$userId/$startDate/$endDate/$activityId

			Where $userId is the id of the user
	*/
	function handleRequest($request) {
		$response = new \api\models\Response();

		//TODO: refactor this into generic DAO
		$db = new \mysqli('127.0.0.1', 'root', '', 'tracker');
		//$db = new \mysqli('localhost', 'bacmsca_tracker', 'September12', 'bacmsca_tracker');

		if($db->connect_errno > 0) {
			$response->setStatusHeader(500);
			return $response;
		}

		if( (!isset($request[0]) || empty($request[0])) 
			|| (!isset($request[1]) || empty($request[1])) 
			|| (!isset($request[2]) || empty($request[2])) 
			|| (!isset($request[3]) || empty($request[3])) 
		)  {
			$response->setStatusHeader(500);
			return $response;
		}
		$userid = $request[0];
		$starttime = new \DateTime($request[1]);		
		$endtime = new \DateTime($request[2]);	
		$activityid = $request[3];		
		
		//incoming datetime format is:
		//2016-11-01T18:39:14+0000
		//required format for datetime is:
		//date("Y-m-d H:i:s")
		$starttime->format("Y-m-d H:i:s");
		$endtime->format("Y-m-d H:i:s");

		$statement = $db->prepare("INSERT INTO `recorded_activities`(`user_id`, `start_time`, `end_time`, `activity_id`) VALUES (?, ?, ?, ?)");
		$statement->bind_param("issi", $userid, $starttime->format("Y-m-d H:i:s"), $endtime->format("Y-m-d H:i:s"), $activityid);
		$statement->execute();
		$statement->close();
		$db->commit();
		$db->close();

		$response->setStatusHeader(200);
		return $response;
	}

}
?>