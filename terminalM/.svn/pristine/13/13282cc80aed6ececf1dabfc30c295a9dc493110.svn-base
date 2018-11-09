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

import com.aliyuncs.iot.model.v20180120.QueryThingFunctionSwitchPopResponse;
import com.aliyuncs.iot.model.v20180120.QueryThingFunctionSwitchPopResponse.Data;
import com.aliyuncs.transform.UnmarshallerContext;


public class QueryThingFunctionSwitchPopResponseUnmarshaller {

	public static QueryThingFunctionSwitchPopResponse unmarshall(QueryThingFunctionSwitchPopResponse queryThingFunctionSwitchPopResponse, UnmarshallerContext context) {
		
		queryThingFunctionSwitchPopResponse.setRequestId(context.stringValue("QueryThingFunctionSwitchPopResponse.RequestId"));
		queryThingFunctionSwitchPopResponse.setSuccess(context.booleanValue("QueryThingFunctionSwitchPopResponse.Success"));
		queryThingFunctionSwitchPopResponse.setErrorMessage(context.stringValue("QueryThingFunctionSwitchPopResponse.ErrorMessage"));

		Data data = new Data();
		data.setGmtCreate(context.stringValue("QueryThingFunctionSwitchPopResponse.Data.GmtCreate"));
		data.setGmtModified(context.stringValue("QueryThingFunctionSwitchPopResponse.Data.GmtModified"));
		data.setIdentifier(context.stringValue("QueryThingFunctionSwitchPopResponse.Data.Identifier"));
		data.setScope(context.stringValue("QueryThingFunctionSwitchPopResponse.Data.Scope"));
		data.setType(context.stringValue("QueryThingFunctionSwitchPopResponse.Data.Type"));
		data.setStatus(context.integerValue("QueryThingFunctionSwitchPopResponse.Data.Status"));
		queryThingFunctionSwitchPopResponse.setData(data);
	 
	 	return queryThingFunctionSwitchPopResponse;
	}
}