//软电话的相关事件回掉
//需要响应软电话事件，无需修改此文件，只需要对应对应的 js_ 方法，就可以处理 AG_ 方法事件。两者的参数必须完全一致


//EvtCallStatus是IPX座席软电话借口中，最核心的呼叫状态事件
//参数说明:
//status: 呼叫状态，CallStatus枚举类型，按整数使用。
//Null = 0, 此为初始化状态
//Calling = 1, 电话呼出
//Incoming = 2, 电话呼入。此时可进行来电弹屏
//Early = 3,
//Connecting = 4, 连接中，
//Confirmed = 5, 接通状态，开始正常通话
//Disconnected = 6, 电话已挂断。电话最终都会进行到此状态，此状态之后，对应的呼叫就结束了。
//                  如果记录callId，则此状态后，对应的callId就可以认为不再存在

//callId: 呼叫标志，整数。一般是从0到4，分别对应软电话的5条电话线。许多呼叫操作都需要提供callId，就是从此事件取得

//caller,called,originCaller,originCalled：主叫号码，被叫号码，原始主叫号码，原始被叫号码。都是字符串，都应电话号码。

//userData: 随电话传递的自定义数据，字符串。当发起呼叫、呼叫转移、挂断电话时允许传递自定义数据，用于传递与电话相关的业务数据。

//type: 呼叫类型, CallType枚举类型，按整数使用。
//Null = 0, 未知类型
//Inbound = 1, 呼入
//Outbound = 2, 呼出
//Consult = 3, 咨询（此时同时存在两个电话，其中一个电话处于保持状态，另外一个呼出）
//ConsultBy = 4, 被咨询

//consultCallId: 咨询呼叫ID。咨询呼叫中，如果本呼叫是发起咨询方，返回咨询呼叫的ID。如果没有咨询，则为-1
//originCallId: 原呼叫ID。咨询呼叫中，如果本呼叫是被咨询的呼叫，则返回发起的呼叫的ID。如果没有咨询，则为-1
//inConference: 是否在会议中。布尔型

//mediaStatus: 媒体状态, MediaStatus枚举类型，按整数使用。
//None = 0, 没有语音媒体
//Active = 1, 正常语音媒体
//Hold = 2, 保持状态，
//RemoteHold = 3, 远端保持状态

//recordingFile：是否在录音。布尔型
//playingFile：是否在放音。布尔型

function AG_EvtCallStatus(status, callId, caller, called, originCaller,
                originCalled, userData, uniqueId, type, consultCallId, originCallId,
                inConference, mediaStatus, recordingFile, playingFile) {

    if (typeof (window.js_EvtCallStatus) != "undefined")
        window.js_EvtCallStatus(status, callId, caller, called, originCaller,
                originCalled, userData, uniqueId, type, consultCallId, originCallId,
                inConference, mediaStatus, recordingFile, playingFile);
}

//电话操作结果指示事件
//参数说明: 
//type 操作类型，整数
// 登录
//Login = 0,
// 注销
//Logout = 1,
// 就绪
//Ready = 2,
// 不就绪
//NotReady =3 ,
// 外呼
//MakeCall = 4,
// 应答
//AnswerCall = 5,
// 挂断呼叫
//ClearCall = 6,
// 呼叫转移
//TransferCall = 7,
// 加入到会议
//AddToConference = 8,
// 移出会议
//RemoveFromConference = 9,
// 呼叫保持
//HoldCall = 10,
// 呼叫找回
//RetrieveCall = 11,
// 发送DTMF
//SendDtmf = 12,
// 放音
//PlayFile = 13,
// 停止放音
//StopPlayFile = 14,
// 录音
//RecordFile = 15,
// 停止录音
//StopRecordFile = 16,
// 班长监听
//MonitorListenCall = 17,
// 班长拦截呼叫
//MonitorInterceptCall = 18,
// 班长强制挂断
//MonitorClearCall = 19,
// 班长强制注销
//MonitorLogout = 20,

//result: 操作结果，布尔型
function AG_RespOperation(type, result) {
    if (typeof(window.js_RespOperation) != "undefined")
        window.js_RespOperation(type, result);
}

//播放文件开始
//callId: 呼叫ID
//path: 文件路径
function AG_EvtPlayStarted(callId, path) {
    if (typeof (window.js_EvtPlayStarted) != "undefined")
        window.js_EvtPlayStarted(callId, path);
}

//播放文件结束
//callId: 呼叫ID
//path: 文件路径
function AG_EvtPlayStopped(callId, path) {
    if (typeof (window.js_EvtPlayStopped) != "undefined")
        window.js_EvtPlayStopped(callId, path);
}

//本地录制文件开始
//callId: 呼叫ID
//path: 文件路径
function AG_EvtRecordStarted(callId, path) {
    if (typeof (window.js_EvtRecordStarted) != "undefined")
        window.js_EvtRecordStarted(callId, path);
}

//本地录制文件结束
//callId: 呼叫ID
//path: 文件路径
function AG_EvtRecordStopped(callId, path) {
    if (typeof (window.js_EvtRecordStopped) != "undefined")
        window.js_EvtRecordStopped(callId, path);
}

//收到DTMF按键
//callId: 呼叫ID
//dtmf: DTMF按键
function AG_EvtDtmf(callId, dtmf) {
    if (typeof (window.js_EvtDtmf) != "undefined")
        window.js_EvtDtmf(callId, dtmf);
}

//软电话状态改变
//isConnected: 软电话是否连接到服务器
function AG_EvtPhoneStatus(isConnected) {
    if (typeof (window.js_EvtPhoneStatus) != "undefined")
        window.js_EvtPhoneStatus(isConnected);
}

//服务端录音开始
//path: 录音文件路径
function AG_EvtServerRecordStarted(path) {
    if (typeof (window.js_EvtServerRecordStarted) != "undefined")
        window.js_EvtServerRecordStarted(path);
}

//服务端录音开始结束
//path: 录音文件路径
function AG_EvtServerRecordStopped(path) {
    if (typeof (window.js_EvtServerRecordStopped) != "undefined")
        window.js_EvtServerRecordStopped(path);
}

//软电话退出
function AG_EvtPhoneExit(path) {
    if (typeof (window.js_EvtPhoneExit) != "undefined")
        window.js_EvtPhoneExit(path);
}