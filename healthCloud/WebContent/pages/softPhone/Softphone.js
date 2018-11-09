
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
///     ��¼��ϯ
/// </summary>
/// <param name="user">��ϯ����</param>
/// <param name="password">��ϯ����</param>
/// <param name="phone">��¼�ĵ绰����</param>
        
function AG_Login(user, password, phone)
{
    softphone.Login(user, password, phone);
}

/// <summary>
///     ע��
/// </summary>
        
function AG_Logout()
{
    softphone.Logout();
}

/// <summary>
///     ���������������״̬Ϊ���У���·�ɹ��̻Ὣ�绰������ϯ
/// </summary> 
        
function AG_Ready()
{
    softphone.Ready();
}

/// <summary>
///     �����������û�о�����·�ɹ��̲��Ὣ�绰������ϯ�������ڵ绰������
/// </summary>
        
function AG_NotReady()
{
    softphone.NotReady();
}

/// <summary>
///     ����
/// </summary>
/// <param name="caller">���к���</param>
/// <param name="called">���к���</param>
/// <param name="userData">�û��Զ�������</param>
        
function AG_MakeCall(caller, called, userData)
{
    softphone.MakeCall(caller, called, userData);
}

/// <summary>
///     Ӧ�����绰
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
        
function AG_AnswerCall(callId)
{
    softphone.AnswerCall(callId);
}

/// <summary>
///     �Ҷ�ͨ��
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
/// <param name="userData">�û��Զ�������</param>
        
function AG_ClearCall(callId, userData)
{
    softphone.ClearCall(callId, userData);
}

/// <summary>
///     ����ת��
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
/// <param name="caller">���к���</param>
/// <param name="called">���к���</param>
/// <param name="type">ת�����͡��������͡�
///        ����ת��
///        Blind = 0,
///        ��ʼ��ѯ    
///        Start = 1,
///        �����ѯ��ת��
///        Complete = 2,    
///        ȡ����ѯ     
///        Cancel = 3
///</param>
/// <param name="userData">�û��Զ�������</param>
        
function AG_TransferCall(callId, caller, called, type,
                            userData)
{
    softphone.TransferCall(callId, caller, called, type, userData);
}

/// <summary>
///     �����м������
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
        
function AG_AddToConference(callId)
{
    softphone.AddToConference(callId);
}

/// <summary>
///     �����дӻ������Ƴ�
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
        
function AG_RemoveFromConference(callId)
{
    softphone.RemoveFromConference(callId);
}

/// <summary>
///     ���б��֡�����֮��ͨ����Ȼ���������޷�����ͨ�������еĶԶ��������Ǳ������֡�
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
        
function AG_HoldCall(callId)
{
    softphone.HoldCall(callId);
}

/// <summary>
///     �����һء��ӱ���״̬�ָ���
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
        
function AG_RetrieveCall(callId)
{
    softphone.RetrieveCall(callId);
}

/// <summary>
///     ����DTMF
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
/// <param name="dtmf">��Ҫ���͵�DTMF</param>
        
function AG_SendDtmf(callId, dtmf)
{
    softphone.SendDtmf(callId, dtmf);
}

/// <summary>
///     ���������ļ���֧�ֵ�����wave�ļ���
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
/// <param name="path">�ļ�·��</param>
/// <param name="loop">�Ƿ�ѭ������</param>
/// <param name="remoteOnly">�Ƿ�ֻ��Զ�˲��š�����ǣ���Զ�˿����������������������������˶���������</param>
        
function AG_PlayFile(callId, path, loop, remoteOnly)
{
    softphone.PlayFile(callId, path, loop, remoteOnly);
}

/// <summary>
///     ֹͣ���������ļ�
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
        
function AG_StopPlayFile(callId)
{
    softphone.StopPlayFile(callId);
}

/// <summary>
///     ��ʼ¼��
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
/// <param name="path">¼���ļ�����·��</param>
/// <param name="maxSize">¼���ļ���������ռ�ÿռ䡣��������ƣ����ʹ��0</param>
        
function AG_RecordFile(callId, path, maxSize)
{
    softphone.RecordFile(callId, path, maxSize);
}

/// <summary>
///     ֹͣ¼��
/// </summary>
/// <param name="callId">����ID����CallStatus�¼��л�ȡ</param>
        
function AG_StopRecordFile(callId)
{
    softphone.StopRecordFile(callId);
}