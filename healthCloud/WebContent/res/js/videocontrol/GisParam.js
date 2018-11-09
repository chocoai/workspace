var dev_id='';
var username='';
var password='';
var client_sup_id='';
var dev_sup_id='';
var client_sup_ip='';
var client_sup_port='';
var ch='';
var caption='';
var data_type='';

//设置视频浏览需要的参数
		function getGisVideoParam(devid){
			var GisVideoInfo = {
			//	"caption":"通道1",
			//	"dev_id":"90547785-fac8-4f6f-b135-c246eef491ba",
				"dev_id": devid ,	
				"username":"admin",
				"password":"1111",
				"client_sup_id":"whyhcscms",
				"dev_sup_id":"whyhcscms",
				"client_sup_ip":"192.168.1.10",
				"client_sup_port":"8000",
				"ch":"0",
				"data_type":"0"
			};
			var GisVideoInfoObject = eval(GisVideoInfo);
			var Jsontext = JSON.stringify(GisVideoInfoObject); 
			return Jsontext;
		}
		
		function getTalkParam(devid){
		   var parm = {
					"dev_id":devid,
					"username":"admin",
					"password":"1111",
					"client_sup_id":"whyhcscms",
					"dev_sup_id":"whyhcscms",
					"client_sup_ip":"192.168.1.10",
					"client_sup_port":"8000"
			};
			var talkInfoObject = eval(parm);
			var Jsontext = JSON.stringify(talkInfoObject); 
		   return Jsontext;
		}
		
		function acceptClientTalkParam(devid){ 
			   var parm = {
						"remote_id":devid
				};
				var ClientTalkObject = eval(parm);
				var Jsontext = JSON.stringify(ClientTalkObject); 
			   return Jsontext;
			}
		
		