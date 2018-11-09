/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aliyuncs.iot.transform.v20180120;

import com.aliyuncs.iot.model.v20180120.QueryConfigByConfigIdPopResponse;
import com.aliyuncs.iot.model.v20180120.QueryConfigByConfigIdPopResponse.Data;
import com.aliyuncs.transform.UnmarshallerContext;


public class QueryConfigByConfigIdPopResponseUnmarshaller {

	public static QueryConfigByConfigIdPopResponse unmarshall(QueryConfigByConfigIdPopResponse queryConfigByConfigIdPopResponse, UnmarshallerContext context) {
		
		queryConfigByConfigIdPopResponse.setRequestId(context.stringValue("QueryConfigByConfigIdPopResponse.RequestId"));
		queryConfigByConfigIdPopResponse.setSuccess(context.booleanValue("QueryConfigByConfigIdPopResponse.Success"));
		queryConfigByConfigIdPopResponse.setErrorMessage(context.stringValue("QueryConfigByConfigIdPopResponse.ErrorMessage"));

		Data data = new Data();
		data.setGmtCreate(context.stringValue("QueryConfigByConfigIdPopResponse.Data.GmtCreate"));
		data.setGmtModified(context.stringValue("QueryConfigByConfigIdPopResponse.Data.GmtModified"));
		data.setProductKey(context.stringValue("QueryConfigByConfigIdPopResponse.Data.ProductKey"));
		data.setIotId(context.stringValue("QueryConfigByConfigIdPopResponse.Data.IotId"));
		data.setConfigFormat(context.stringValue("QueryConfigByConfigIdPopResponse.Data.ConfigFormat"));
		data.setConfigId(context.stringValue("QueryConfigByConfigIdPopResponse.Data.ConfigId"));
		data.setConfigName(context.stringValue("QueryConfigByConfigIdPopResponse.Data.ConfigName"));
		data.setConfigSize(context.integerValue("QueryConfigByConfigIdPopResponse.Data.ConfigSize"));
		data.setSign(context.stringValue("QueryConfigByConfigIdPopResponse.Data.Sign"));
		data.setSignMethod(context.stringValue("QueryConfigByConfigIdPopResponse.Data.SignMethod"));
		data.setScope(context.stringValue("QueryConfigByConfigIdPopResponse.Data.Scope"));
		data.setOssPath(context.stringValue("QueryConfigByConfigIdPopResponse.Data.OssPath"));
		data.setOssUrl(context.stringValue("QueryConfigByConfigIdPopResponse.Data.OssUrl"));
		data.setStatus(context.integerValue("QueryConfigByConfigIdPopResponse.Data.Status"));
		queryConfigByConfigIdPopResponse.setData(data);
	 
	 	return queryConfigByConfigIdPopResponse;
	}
}