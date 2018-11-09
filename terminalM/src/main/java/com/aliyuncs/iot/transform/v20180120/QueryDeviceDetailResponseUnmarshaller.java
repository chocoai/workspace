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

import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailResponse;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailResponse.Data;
import com.aliyuncs.transform.UnmarshallerContext;


public class QueryDeviceDetailResponseUnmarshaller {

	public static QueryDeviceDetailResponse unmarshall(QueryDeviceDetailResponse queryDeviceDetailResponse, UnmarshallerContext context) {
		
		queryDeviceDetailResponse.setRequestId(context.stringValue("QueryDeviceDetailResponse.RequestId"));
		queryDeviceDetailResponse.setSuccess(context.booleanValue("QueryDeviceDetailResponse.Success"));
		queryDeviceDetailResponse.setErrorMessage(context.stringValue("QueryDeviceDetailResponse.ErrorMessage"));

		Data data = new Data();
		data.setIotId(context.stringValue("QueryDeviceDetailResponse.Data.IotId"));
		data.setProductKey(context.stringValue("QueryDeviceDetailResponse.Data.ProductKey"));
		data.setProductName(context.stringValue("QueryDeviceDetailResponse.Data.ProductName"));
		data.setDeviceName(context.stringValue("QueryDeviceDetailResponse.Data.DeviceName"));
		data.setDeviceSecret(context.stringValue("QueryDeviceDetailResponse.Data.DeviceSecret"));
		data.setFirmwareVersion(context.stringValue("QueryDeviceDetailResponse.Data.FirmwareVersion"));
		data.setGmtCreate(context.stringValue("QueryDeviceDetailResponse.Data.GmtCreate"));
		data.setGmtActive(context.stringValue("QueryDeviceDetailResponse.Data.GmtActive"));
		data.setGmtOnline(context.stringValue("QueryDeviceDetailResponse.Data.GmtOnline"));
		data.setStatus(context.stringValue("QueryDeviceDetailResponse.Data.Status"));
		data.setIpAddress(context.stringValue("QueryDeviceDetailResponse.Data.IpAddress"));
		data.setNodeType(context.integerValue("QueryDeviceDetailResponse.Data.NodeType"));
		data.setRegion(context.stringValue("QueryDeviceDetailResponse.Data.Region"));
		queryDeviceDetailResponse.setData(data);
	 
	 	return queryDeviceDetailResponse;
	}
}