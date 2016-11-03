<?php
namespace api\models;

class Response {
	private $body;
	private $statusHeader;

	public function setBody($new_responseBody) {
		$this->body = $new_responseBody;
	}

	public function getBody() {
		return $this->body;
	}

	public function setStatusHeader($new_responseStatusHeader) {
		$this->statusHeader = $new_responseStatusHeader;
	}

	public function getStatusHeader() {
		return $this->statusHeader;
	}
}

?>