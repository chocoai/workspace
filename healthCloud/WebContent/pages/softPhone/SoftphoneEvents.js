//��绰������¼��ص�
//��Ҫ��Ӧ��绰�¼��������޸Ĵ��ļ���ֻ��Ҫ��Ӧ��Ӧ�� js_ �������Ϳ��Դ��� AG_ �����¼������ߵĲ���������ȫһ��


//EvtCallStatus��IPX��ϯ��绰����У�����ĵĺ���״̬�¼�
//����˵��:
//status: ����״̬��CallStatusö�����ͣ�������ʹ�á�
//Null = 0, ��Ϊ��ʼ��״̬
//Calling = 1, �绰����
//Incoming = 2, �绰���롣��ʱ�ɽ������絯��
//Early = 3,
//Connecting = 4, �����У�
//Confirmed = 5, ��ͨ״̬����ʼ����ͨ��
//Disconnected = 6, �绰�ѹҶϡ��绰���ն�����е���״̬����״̬֮�󣬶�Ӧ�ĺ��оͽ����ˡ�
//                  �����¼callId�����״̬�󣬶�Ӧ��callId�Ϳ�����Ϊ���ٴ���

//callId: ���б�־��������һ���Ǵ�0��4���ֱ��Ӧ��绰��5���绰�ߡ������в�������Ҫ�ṩcallId�����ǴӴ��¼�ȡ��

//caller,called,originCaller,originCalled�����к��룬���к��룬ԭʼ���к��룬ԭʼ���к��롣�����ַ�������Ӧ�绰���롣

//userData: ��绰���ݵ��Զ������ݣ��ַ�������������С�����ת�ơ��Ҷϵ绰ʱ�������Զ������ݣ����ڴ�����绰��ص�ҵ�����ݡ�

//type: ��������, CallTypeö�����ͣ�������ʹ�á�
//Null = 0, δ֪����
//Inbound = 1, ����
//Outbound = 2, ����
//Consult = 3, ��ѯ����ʱͬʱ���������绰������һ���绰���ڱ���״̬������һ��������
//ConsultBy = 4, ����ѯ

//consultCallId: ��ѯ����ID����ѯ�����У�����������Ƿ�����ѯ����������ѯ���е�ID�����û����ѯ����Ϊ-1
//originCallId: ԭ����ID����ѯ�����У�����������Ǳ���ѯ�ĺ��У��򷵻ط���ĺ��е�ID�����û����ѯ����Ϊ-1
//inConference: �Ƿ��ڻ����С�������

//mediaStatus: ý��״̬, MediaStatusö�����ͣ�������ʹ�á�
//None = 0, û������ý��
//Active = 1, ��������ý��
//Hold = 2, ����״̬��
//RemoteHold = 3, Զ�˱���״̬

//recordingFile���Ƿ���¼����������
//playingFile���Ƿ��ڷ�����������

function AG_EvtCallStatus(status, callId, caller, called, originCaller,
                originCalled, userData, uniqueId, type, consultCallId, originCallId,
                inConference, mediaStatus, recordingFile, playingFile) {

    if (typeof (window.js_EvtCallStatus) != "undefined")
        window.js_EvtCallStatus(status, callId, caller, called, originCaller,
                originCalled, userData, uniqueId, type, consultCallId, originCallId,
                inConference, mediaStatus, recordingFile, playingFile);
}

//�绰�������ָʾ�¼�
//����˵��: 
//type �������ͣ�����
// ��¼
//Login = 0,
// ע��
//Logout = 1,
// ����
//Ready = 2,
// ������
//NotReady =3 ,
// ���
//MakeCall = 4,
// Ӧ��
//AnswerCall = 5,
// �ҶϺ���
//ClearCall = 6,
// ����ת��
//TransferCall = 7,
// ���뵽����
//AddToConference = 8,
// �Ƴ�����
//RemoveFromConference = 9,
// ���б���
//HoldCall = 10,
// �����һ�
//RetrieveCall = 11,
// ����DTMF
//SendDtmf = 12,
// ����
//PlayFile = 13,
// ֹͣ����
//StopPlayFile = 14,
// ¼��
//RecordFile = 15,
// ֹͣ¼��
//StopRecordFile = 16,
// �೤����
//MonitorListenCall = 17,
// �೤���غ���
//MonitorInterceptCall = 18,
// �೤ǿ�ƹҶ�
//MonitorClearCall = 19,
// �೤ǿ��ע��
//MonitorLogout = 20,

//result: ���������������
function AG_RespOperation(type, result) {
    if (typeof(window.js_RespOperation) != "undefined")
        window.js_RespOperation(type, result);
}

//�����ļ���ʼ
//callId: ����ID
//path: �ļ�·��
function AG_EvtPlayStarted(callId, path) {
    if (typeof (window.js_EvtPlayStarted) != "undefined")
        window.js_EvtPlayStarted(callId, path);
}

//�����ļ�����
//callId: ����ID
//path: �ļ�·��
function AG_EvtPlayStopped(callId, path) {
    if (typeof (window.js_EvtPlayStopped) != "undefined")
        window.js_EvtPlayStopped(callId, path);
}

//����¼���ļ���ʼ
//callId: ����ID
//path: �ļ�·��
function AG_EvtRecordStarted(callId, path) {
    if (typeof (window.js_EvtRecordStarted) != "undefined")
        window.js_EvtRecordStarted(callId, path);
}

//����¼���ļ�����
//callId: ����ID
//path: �ļ�·��
function AG_EvtRecordStopped(callId, path) {
    if (typeof (window.js_EvtRecordStopped) != "undefined")
        window.js_EvtRecordStopped(callId, path);
}

//�յ�DTMF����
//callId: ����ID
//dtmf: DTMF����
function AG_EvtDtmf(callId, dtmf) {
    if (typeof (window.js_EvtDtmf) != "undefined")
        window.js_EvtDtmf(callId, dtmf);
}

//��绰״̬�ı�
//isConnected: ��绰�Ƿ����ӵ�������
function AG_EvtPhoneStatus(isConnected) {
    if (typeof (window.js_EvtPhoneStatus) != "undefined")
        window.js_EvtPhoneStatus(isConnected);
}

//�����¼����ʼ
//path: ¼���ļ�·��
function AG_EvtServerRecordStarted(path) {
    if (typeof (window.js_EvtServerRecordStarted) != "undefined")
        window.js_EvtServerRecordStarted(path);
}

//�����¼����ʼ����
//path: ¼���ļ�·��
function AG_EvtServerRecordStopped(path) {
    if (typeof (window.js_EvtServerRecordStopped) != "undefined")
        window.js_EvtServerRecordStopped(path);
}

//��绰�˳�
function AG_EvtPhoneExit(path) {
    if (typeof (window.js_EvtPhoneExit) != "undefined")
        window.js_EvtPhoneExit(path);
}