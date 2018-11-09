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

package com.aliyuncs.iot.model.v20180120;

import com.aliyuncs.RpcAcsRequest;

/**
 * @author auto create
 * @version 
 */
public class AddThingConfigPopRequest extends RpcAcsRequest<AddThingConfigPopResponse> {
	
	public AddThingConfigPopRequest() {
		super("Iot", "2018-01-20", "AddThingConfigPop");
	}

	private String iotId;

	private String signMethod;

	private String configFormat;

	private String configName;

	private String scope;

	private String deviceName;

	private String productKey;

	private String configContent;

	public String getIotId() {
		return this.iotId;
	}

	public void setIotId(String iotId) {
		this.iotId = iotId;
		if(iotId != null){
			putQueryParameter("IotId", iotId);
		}
	}

	public String getSignMethod() {
		return this.signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
		if(signMethod != null){
			putQueryParameter("SignMethod", signMethod);
		}
	}

	public String getConfigFormat() {
		return this.configFormat;
	}

	public void setConfigFormat(String configFormat) {
		this.configFormat = configFormat;
		if(configFormat != null){
			putQueryParameter("ConfigFormat", configFormat);
		}
	}

	public String getConfigName() {
		return this.configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
		if(configName != null){
			putQueryParameter("ConfigName", configName);
		}
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
		if(scope != null){
			putQueryParameter("Scope", scope);
		}
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
		if(deviceName != null){
			putQueryParameter("DeviceName", deviceName);
		}
	}

	public String getProductKey() {
		return this.productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
		if(productKey != null){
			putQueryParameter("ProductKey", productKey);
		}
	}

	public String getConfigContent() {
		return this.configContent;
	}

	public void setConfigContent(String configContent) {
		this.configContent = configContent;
		if(configContent != null){
			putQueryParameter("ConfigContent", configContent);
		}
	}

	@Override
	public Class<AddThingConfigPopResponse> getResponseClass() {
		return AddThingConfigPopResponse.class;
	}

}
