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

import java.util.ArrayList;
import java.util.List;

import com.aliyuncs.iot.model.v20180120.QueryThingConfigPopResponse;
import com.aliyuncs.iot.model.v20180120.QueryThingConfigPopResponse.Data;
import com.aliyuncs.iot.model.v20180120.QueryThingConfigPopResponse.Data.ThingConfigInfo;
import com.aliyuncs.transform.UnmarshallerContext;


public class QueryThingConfigPopResponseUnmarshaller {

	public static QueryThingConfigPopResponse unmarshall(QueryThingConfigPopResponse queryThingConfigPopResponse, UnmarshallerContext context) {
		
		queryThingConfigPopResponse.setRequestId(context.stringValue("QueryThingConfigPopResponse.RequestId"));
		queryThingConfigPopResponse.setSuccess(context.booleanValue("QueryThingConfigPopResponse.Success"));
		queryThingConfigPopResponse.setErrorMessage(context.stringValue("QueryThingConfigPopResponse.ErrorMessage"));

		Data data = new Data();
		data.setCurrentPage(context.integerValue("QueryThingConfigPopResponse.Data.CurrentPage"));
		data.setPageCount(context.integerValue("QueryThingConfigPopResponse.Data.PageCount"));
		data.setPageSize(context.integerValue("QueryThingConfigPopResponse.Data.PageSize"));
		data.setTotal(context.integerValue("QueryThingConfigPopResponse.Data.Total"));

		List<ThingConfigInfo> list = new ArrayList<ThingConfigInfo>();
		for (int i = 0; i < context.lengthValue("QueryThingConfigPopResponse.Data.List.Length"); i++) {
			ThingConfigInfo thingConfigInfo = new ThingConfigInfo();
			thingConfigInfo.setGmtCreate(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].GmtCreate"));
			thingConfigInfo.setGmtModified(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].GmtModified"));
			thingConfigInfo.setProductKey(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].ProductKey"));
			thingConfigInfo.setIotId(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].IotId"));
			thingConfigInfo.setConfigFormat(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].ConfigFormat"));
			thingConfigInfo.setConfigId(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].ConfigId"));
			thingConfigInfo.setConfigName(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].ConfigName"));
			thingConfigInfo.setConfigSize(context.integerValue("QueryThingConfigPopResponse.Data.List["+ i +"].ConfigSize"));
			thingConfigInfo.setSign(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].Sign"));
			thingConfigInfo.setSignMethod(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].SignMethod"));
			thingConfigInfo.setScope(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].Scope"));
			thingConfigInfo.setOssPath(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].OssPath"));
			thingConfigInfo.setStatus(context.integerValue("QueryThingConfigPopResponse.Data.List["+ i +"].Status"));
			thingConfigInfo.setOssUrl(context.stringValue("QueryThingConfigPopResponse.Data.List["+ i +"].OssUrl"));

			list.add(thingConfigInfo);
		}
		data.setList(list);
		queryThingConfigPopResponse.setData(data);
	 
	 	return queryThingConfigPopResponse;
	}
}