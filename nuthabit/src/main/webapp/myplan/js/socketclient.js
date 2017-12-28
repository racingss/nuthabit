function initWebsocket() {
	var host = window.location.host;
	var websocket;

	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://" + host
				+ "/socket");
	} else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket("ws://" + host + "/socket");
	} else {
		websocket = new SockJS("http://" + host + "/socket/sockjs");
	}

	websocket.onopen = function(evnt) {
		console.log("websocket connected");
	}

	websocket.onmessage = function(evnt) {
		console.log("received a message " + evnt.data);
	}

	websocket.onerror = function(evnt) {
		console.log("websocket error");
	}

	websocket.onclose = function(evnt) {
		console.log("websocket close");
	}	
}