<!DOCTYPE html>
<html>
    <head>
        <title>Pause Example</title>
        <meta http-equiv="Content-type" content="text/html; charset=utf-8">
        <script type="text/javascript" charset="utf-8" src="../../js/cordova/cordova.js"></script>
        <script type="text/javascript" charset="utf-8">
            //页面加载后添加各事件监听
            function onLoad() {
                document.addEventListener("deviceready", onDeviceReady, false);
                document.addEventListener("resume", onResume, false);
                document.addEventListener("pause", onPause, false);
            }

            //Cordova加载完毕
            function onDeviceReady() {
                alert("Cordova加载完毕！");
            }

            //进入后台
            function onPause() {
                console.log("应用进入到后台！");
            }

            //恢复到前台
            function onResume() {
                alert("应用回到前台运行！");
            }
        </script>
    </head>
    <body onload="onLoad()">
    </body>
</html>