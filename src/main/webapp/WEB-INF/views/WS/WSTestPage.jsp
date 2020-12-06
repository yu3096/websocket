<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Web Socket Test Page</title>
    <script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"></script>
    <script>
        let ws = (function(){
            const _SOCKET_SERVER_API = "/wss";
            let stompClient;

            function conn (){
                stompClient = Stomp.over(new SockJS(_SOCKET_SERVER_API));
                stompClient.connect({}, ()=>{
                    stompClient.subscribe("/file/download", param => {
                        console.log(param);
                        debugger;
                    });
                });
            }
            function init(){
                conn();
            }

            function sendMessage(){
                stompClient.send("/queue/download", {}, JSON.stringify({fileTarget: "qweqwejbrgklerjbtlkjbqw", fileRename: "aaaa"}));
            }

            return {
                 init: init
                ,sendMessage: sendMessage
            }
        })();
    </script>
</head>
<body>
    <button id="wsConn"        onclick="ws.init();">ws connect</button>
    <button id="fileDownload"  onclick="ws.sendMessage();">file download</button>
</body>
</html>