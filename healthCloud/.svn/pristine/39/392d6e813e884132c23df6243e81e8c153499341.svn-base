
var softphone;
function js_Init() {
    softphone = document.getElementById("slSoftphone").content.softphone;
}


function Agent() {
    this.Login= AG_Login;
    this.Logout= AG_Logout;
    this.Ready= AG_Ready;
    this.NotReady= AG_NotReady;
    this.MakeCall= AG_MakeCall;
    this.AnswerCall= AG_AnswerCall;
    this.ClearCall= AG_ClearCall;
    this.TransferCall= AG_TransferCall;
    this.AddToConference= AG_AddToConference;
    this.RemoveFromConference= AG_RemoveFromConference;
    this.HoldCall= AG_HoldCall;
    this.RetrieveCall= AG_RetrieveCall;
    this.SendDtmf= AG_SendDtmf;
    this.PlayFile= AG_PlayFile;
    this.StopPlayFile= AG_StopPlayFile;
    this.RecordFile= AG_RecordFile;
    this.StopRecordFile= AG_StopRecordFile;

}


/// <summary>
///     登录座席
/// </summary>
/// <param name="user">座席工号</param>
/// <param name="password">座席密码</param>
/// <param name="phone">登录的电话号码</param>
        
function AG_Login(user, password, phone)
{
    softphone.Login(user, password, phone);
}

/// <summary>
///     注销
/// </summary>
        
function AG_Logout()
{
    softphone.Logout();
}

/// <summary>
///     就绪。就绪后，如果状态为空闲，则路由过程会将电话分配座席
/// </summary> 
        
function AG_Ready()
{
    softphone.Ready();
}

/// <summary>
///     不就绪。如果没有就绪，路由过程不会将电话分配座席。可用于电话呼出。
/// </summary>
        
function AG_NotReady()
{
    softphone.NotReady();
}

/// <summary>
///     呼出
/// </summary>
/// <param name="caller">主叫号码</param>
/// <param name="called">被叫号码</param>
/// <param name="userData">用户自定义数据</param>
        
function AG_MakeCall(caller, called, userData)
{
    softphone.MakeCall(caller, called, userData);
}

/// <summary>
///     应答呼入电话
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
        
function AG_AnswerCall(callId)
{
    softphone.AnswerCall(callId);
}

/// <summary>
///     挂断通话
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
/// <param name="userData">用户自定义数据</param>
        
function AG_ClearCall(callId, userData)
{
    softphone.ClearCall(callId, userData);
}

/// <summary>
///     呼叫转移
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
/// <param name="caller">主叫号码</param>
/// <param name="called">被叫号码</param>
/// <param name="type">转移类型。整数类型。
///        单步转移
///        Blind = 0,
///        开始咨询    
///        Start = 1,
///        完成咨询后转移
///        Complete = 2,    
///        取消咨询     
///        Cancel = 3
///</param>
/// <param name="userData">用户自定义数据</param>
        
function AG_TransferCall(callId, caller, called, type,
                            userData)
{
    softphone.TransferCall(callId, caller, called, type, userData);
}

/// <summary>
///     将呼叫加入会议
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
        
function AG_AddToConference(callId)
{
    softphone.AddToConference(callId);
}

/// <summary>
///     将呼叫从会议中移出
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
        
function AG_RemoveFromConference(callId)
{
    softphone.RemoveFromConference(callId);
}

/// <summary>
///     呼叫保持。保持之后，通话仍然保留，但无法进行通话，呼叫的对端听到的是保持音乐。
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
        
function AG_HoldCall(callId)
{
    softphone.HoldCall(callId);
}

/// <summary>
///     呼叫找回。从保持状态恢复。
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
        
function AG_RetrieveCall(callId)
{
    softphone.RetrieveCall(callId);
}

/// <summary>
///     发送DTMF
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
/// <param name="dtmf">需要发送的DTMF</param>
        
function AG_SendDtmf(callId, dtmf)
{
    softphone.SendDtmf(callId, dtmf);
}

/// <summary>
///     播放语音文件。支持单声道wave文件。
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
/// <param name="path">文件路径</param>
/// <param name="loop">是否循环播放</param>
/// <param name="remoteOnly">是否只向远端播放。如果是，则远端可以听到，本方听不到；否则两端都可以听到</param>
        
function AG_PlayFile(callId, path, loop, remoteOnly)
{
    softphone.PlayFile(callId, path, loop, remoteOnly);
}

/// <summary>
///     停止播放语音文件
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
        
function AG_StopPlayFile(callId)
{
    softphone.StopPlayFile(callId);
}

/// <summary>
///     开始录音
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
/// <param name="path">录音文件保存路径</param>
/// <param name="maxSize">录音文件允许的最大占用空间。如果不限制，则可使用0</param>
        
function AG_RecordFile(callId, path, maxSize)
{
    softphone.RecordFile(callId, path, maxSize);
}

/// <summary>
///     停止录音
/// </summary>
/// <param name="callId">呼叫ID。由CallStatus事件中获取</param>
        
function AG_StopRecordFile(callId)
{
    softphone.StopRecordFile(callId);
}