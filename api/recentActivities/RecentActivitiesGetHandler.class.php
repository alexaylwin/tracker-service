<?php
namespace api\recentActivities;

class RecentActivitiesGetHandler {
	/*
		Request Mapping is:
			/activities/$userId/$startDate/$endDate

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
			$responseText = <<<EOT
	{startTime: 2016-03-11 10:47AM,
		endTime: 2016-03-11 12:47PM,
		duration: 120,
		activityId: 2 },
	{startTime: 2016-03-05 10:47AM,
		endTime: 2016-03-05 11:47AM,
		duration: 1900,
		activityId: 3 },
	{startTime: 2016-03-01 1:47PM,
		endTime: 2016-03-01 2:47PM,
		duration: 200,
		activityId: 4 }
EOT;
			$response->setBody($responseText);
		} else {
			$response->setStatusHeader(200);
			$responseText = <<<EOT
	{startTime: 2019-03-11 10:47AM,
		endTime: 2019-03-11 12:47PM,
		duration: 120,
		activityId: 1 },
	{startTime: 2019-03-05 10:47AM,
		endTime: 2019-03-05 11:47AM,
		duration: 1900,
		activityId: 1 },
	{startTime: 2019-03-01 1:47PM,
		endTime: 2019-03-01 2:47PM,
		duration: 200,
		activityId: 1 }
EOT;
			$response->setBody($responseText);
		}

		return $response;
	}
}
?>