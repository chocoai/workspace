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
public class QueryConfigByConfigIdPopRequest extends RpcAcsRequest<QueryConfigByConfigIdPopResponse> {
	
	public QueryConfigByConfigIdPopRequest() {
		super("Iot", "2018-01-20", "QueryConfigByConfigIdPop");
	}

	private String configId;

	private Boolean containedOssUrl;

	public String getConfigId() {
		return this.configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
		if(configId != null){
			putQueryParameter("ConfigId", configId);
		}
	}

	public Boolean getContainedOssUrl() {
		return this.containedOssUrl;
	}

	public void setContainedOssUrl(Boolean containedOssUrl) {
		this.containedOssUrl = containedOssUrl;
		if(containedOssUrl != null){
			putQueryParameter("ContainedOssUrl", containedOssUrl.toString());
		}
	}

	@Override
	public Class<QueryConfigByConfigIdPopResponse> getResponseClass() {
		return QueryConfigByConfigIdPopResponse.class;
	}

}
