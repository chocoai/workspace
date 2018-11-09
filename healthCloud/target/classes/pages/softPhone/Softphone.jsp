<%@ page contentType="text/html; charset=UTF-8" %>
<%
	String path  = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html >
<head>
    <title>Softphone</title>
    <style type="text/css">

        html, body
        {
            height: 100%;
            overflow: auto;
        }
        body
        {
            padding: 0;
            margin: 0;
        }       
           
       .softphone
        {
            position: absolute;
            top: 85;    <!--  //115 -->
            left: 0;
            width: 100%;
            height: 38px;
            z-index: 50;
        }
        
        .PhoneNo
        {
            position: absolute;
            top: 52px;
            left: 283px;
            width: 158px;
            height: 32px;
            z-index: 100;
        }
        
        .TestArea
        {
            position: absolute;
            top: 50px;
            left: 0;
            width: 100%;
            height: 25px;
            z-index: 150;
        }

    </style>
    <script type="text/javascript" src="<%= basePath %>pages/softPhone/Silverlight.js"></script>
    <!--包含软电话的操作-->
    <script type="text/javascript" src="<%= basePath %>pages/softPhone/Softphone.js"></script>
    <!--包含软电话的事件-->
    <script type="text/javascript" src="<%= basePath %>pages/softPhone/SoftphoneEvents.js"></script>
    <script src="<%=basePath%>res/js/jquery-3.2.1.min.js"></script>
    


    <script type="text/javascript"> 
 //****************************************************以上为ajax请求***********************************************//
	//定义XMLHttpRequest对象  
	if(window.XMLHttpRequest){  
	    //兼容Mozilla、Safari等非IE浏览器  
	    var xmlhttprequest = new XMLHttpRequest();  
	}else{  
	    if(window.ActiveXObject){  
	        //兼容IE浏览器  
	        try{  
	            var xmlhttprequest = new ActiveXObject('Msxml12.XMLHTTP');  
	        }catch(e){  
	            try{  
	                xmlhttprequest = new ActiveXObject('Microsoft.XMLHTTP');  
	            }catch(e){  
	            }  
	        }  
	    }  
	}  

//****************************************************以上为ajax请求***********************************************//
        //Silverlight错误处理
        function onSilverlightError(sender, args) {
            var appSource = "";
            if (sender != null && sender != 0) {
                appSource = sender.getHost().Source;
            }
            var errorType = args.ErrorType;
            var iErrorCode = args.ErrorCode;

            if (errorType == "ImageError" || errorType == "MediaError") {
                return;
            }

            var errMsg = "Unhandled Error in Silverlight Application " + appSource + "\n";

            errMsg += "Code: " + iErrorCode + "    \n";
            errMsg += "Category: " + errorType + "       \n";
            errMsg += "Message: " + args.ErrorMessage + "     \n";

            if (errorType == "ParserError") {
                errMsg += "File: " + args.xamlFile + "     \n";
                errMsg += "Line: " + args.lineNumber + "     \n";
                errMsg += "Position: " + args.charPosition + "     \n";
            }
            else if (errorType == "RuntimeError") {
                if (args.lineNumber != 0) {
                    errMsg += "Line: " + args.lineNumber + "     \n";
                    errMsg += "Position: " + args.charPosition + "     \n";
                }
                errMsg += "MethodName: " + args.methodName + "     \n";
            }

            throw new Error(errMsg);
        }


        function getServerUrl() {
            //请把localhost替换成实际的IPX服务器部署地址ַ
            return "http://192.168.1.145:8731/Sogrand/ServiceLayer/Agent/http";

        }

        function getPhone() {           
      //      return "202";  //座席登陆的话机号码，应该从登陆界面获得。在IPX系统中必须是配置有的
            return  '<%=session.getAttribute("seatPhone")%>';
        }

        function getPhoneNo() { 
            return document.getElementById("PhoneNo").value;
        }

        function getAgentID() {
        //    return "58002";  //座席登陆的座席号，应该从登陆界面获得。在IPX系统中必须是配置有的
        	return  '<%=session.getAttribute("seatNum")%>';
        }

        function getAgentPassword() {
        	// return "58002";
            return '<%=session.getAttribute("seatPassword")%>';  //座席的密码，应该从登陆界面或者配置数据中获得。在IPX系统中必须是配置有的
        }
        function getClientId() {
  //          return "SessionId"; //这里应该返回实际的jsp的SessionId
        	  return '<%=session.getId()%>';
        }
        function getLoginName() {
            return "LoginName";  //暂时无用
        }
        function getAppraiseVdn() {
            return "701";       //这个是转座席评价IVR流程的字冠号码，根据IPX系统配置决定。
        }

        //下面的方法对应SoftphoneEvents.js中的AG_EvtCallStatus，用来接收呼叫事件
        //1呼出 2呼入  4连结中   5连接成功  6挂断
        function js_EvtCallStatus(status, callId, caller, called, originCaller,
            originCalled, userData, uniqueId, type, consultCallId, originCallId,
            inConference, mediaStatus, recordingFile, playingFile) {
            if (status == 2) { //来电响铃
            	showModalDialog('<%=basePath %>member/memberWindow?tel='+caller ,'example04','dialogWidth:800px;dialogHeight:420px;dialogLeft:200px;dialogTop:150px;center:yes;help:yes;resizable:yes;status:yes');
            	callComing(caller,1);
            }else if(status == 5){  //接听
            	//alert("接电话");
            	connect(caller);
            }else if(status == 1){  //主动呼出
            	//alert("呼出");
            	callComing(caller,0);
            }else if(status == 6){  //挂断
            	//alert("挂电话");
            	disConnect(caller);
            }
        }
        function test(){
        	
        	showModalDialog('<%=basePath %>member/memberWindow?tel=18627899133' ,'example04','dialogWidth:800px;dialogHeight:450px;dialogLeft:200px;dialogTop:150px;center:yes;help:yes;resizable:yes;status:yes');
        }

        function callComing(tel,type){
			$.ajax({
				cache : false,
				type : "post",
				dataType : "json",
				url : "<%=basePath %>/callRecord/addCallRecord",
				async : true,
				data : {
					"phoneNo" : tel,
					"callType" : type
				},
				traditional : true,
				success : function(data) {
				},
				error : function() {
				}
			})
        }
        function connect(tel){ //接电话操作，记录接听时间
			$.ajax({
				cache : false,
				type : "post",
				dataType : "json",
				url : "<%=basePath %>/callRecord/connectCall",
				async : true,
				data : {
					"phoneNo" : tel,
				},
				traditional : true,
				success : function(data) {
				},
				error : function() {
				}
			})
        }
        
        function disConnect(tel){  //挂电话操作，记录通话时间
			$.ajax({
				cache : false,
				type : "post",
				dataType : "json",
				url : "<%=basePath %>/callRecord/disConnectCall",
				async : true,
				data : {
					"phoneNo" : tel,
				},
				traditional : true,
				success : function(data) {
				},
				error : function() {
				}
			})
        }
        
/*          function CallIncoming(caller, called, originCaller, originCalled, userData) {
            alert("有来电:主叫" + caller + "原主叫" + originCaller);
        }

        //一下为演示用的代码
        var agent = new Agent();
        
        function MakeCallTest() {
            agent.MakeCall(getPhone(), "700", "");
        }

        function AnswerCallTest() {
            //注意callId应该在呼叫事件中获取。0是一般情况下默认第一个电话的callId
            agent.AnswerCall(0);
           // showModalDialog('../jsp/laoren/oldManCallForm2.jsp','example04','dialogWidth:800px;dialogHeight:420px;dialogLeft:200px;dialogTop:150px;center:yes;help:yes;resizable:yes;status:yes');
            
        }

        function ClearCallTest() {
            //注意callId应该在呼叫事件中获取。0是一般情况下默认第一个电话的callId
            agent.ClearCall(0, "");
        }  */

    </script>
</head>
<body>
 
        
    <form id="form1" runat="server" >
    <input type="text" class="PhoneNo" id="PhoneNo" onclick="javascript:test();" />  
  <!--    <img src="pages/softPhone/logo80a.png" />  -->
    <div class="softphone">    
        <object data="data:application/x-silverlight-2," type="application/x-silverlight-2"
            id="slSoftphone" width="100%" height="100%">
            <param name="source" value="<%= basePath %>pages/softPhone/ClientBin/Softphone.xap" />
            <param name="onError" value="onSilverlightError" />
            <param name="background" value="white" />
            <param name="minRuntimeVersion" value="4.0.50826.0" />
            <param name="autoUpgrade" value="true" />
            <param name="windowless" value="true" />
            <a href="http://go.microsoft.com/fwlink/?LinkID=149156&v=4.0.50826.0" style="text-decoration: none">
                <img src="http://go.microsoft.com/fwlink/?LinkId=161376" alt="Get Microsoft Silverlight"
                    style="border-style: none" />
            </a>
        </object> 
        <iframe id="_sl_historyFrame" style="visibility: hidden; height: 0; width: 0; border: 0">
        </iframe>
    </div>
    </form>

</body>
</html>
