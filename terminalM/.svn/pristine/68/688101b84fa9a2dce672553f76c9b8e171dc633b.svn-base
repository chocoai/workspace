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

import java.util.List;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.iot.transform.v20180120.QueryThingConfigPopResponseUnmarshaller;
import com.aliyuncs.transform.UnmarshallerContext;

/**
 * @author auto create
 * @version 
 */
public class QueryThingConfigPopResponse extends AcsResponse {

	private String requestId;

	private Boolean success;

	private String errorMessage;

	private Data data;

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Data getData() {
		return this.data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public static class Data {

		private Integer currentPage;

		private Integer pageCount;

		private Integer pageSize;

		private Integer total;

		private List<ThingConfigInfo> list;

		public Integer getCurrentPage() {
			return this.currentPage;
		}

		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}

		public Integer getPageCount() {
			return this.pageCount;
		}

		public void setPageCount(Integer pageCount) {
			this.pageCount = pageCount;
		}

		public Integer getPageSize() {
			return this.pageSize;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}

		public Integer getTotal() {
			return this.total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public List<ThingConfigInfo> getList() {
			return this.list;
		}

		public void setList(List<ThingConfigInfo> list) {
			this.list = list;
		}

		public static class ThingConfigInfo {

			private String gmtCreate;

			private String gmtModified;

			private String productKey;

			private String iotId;

			private String configFormat;

			private String configId;

			private String configName;

			private Integer configSize;

			private String sign;

			private String signMethod;

			private String scope;

			private String ossPath;

			private Integer status;

			private String ossUrl;

			public String getGmtCreate() {
				return this.gmtCreate;
			}

			public void setGmtCreate(String gmtCreate) {
				this.gmtCreate = gmtCreate;
			}

			public String getGmtModified() {
				return this.gmtModified;
			}

			public void setGmtModified(String gmtModified) {
				this.gmtModified = gmtModified;
			}

			public String getProductKey() {
				return this.productKey;
			}

			public void setProductKey(String productKey) {
				this.productKey = productKey;
			}

			public String getIotId() {
				return this.iotId;
			}

			public void setIotId(String iotId) {
				this.iotId = iotId;
			}

			public String getConfigFormat() {
				return this.configFormat;
			}

			public void setConfigFormat(String configFormat) {
				this.configFormat = configFormat;
			}

			public String getConfigId() {
				return this.configId;
			}

			public void setConfigId(String configId) {
				this.configId = configId;
			}

			public String getConfigName() {
				return this.configName;
			}

			public void setConfigName(String configName) {
				this.configName = configName;
			}

			public Integer getConfigSize() {
				return this.configSize;
			}

			public void setConfigSize(Integer configSize) {
				this.configSize = configSize;
			}

			public String getSign() {
				return this.sign;
			}

			public void setSign(String sign) {
				this.sign = sign;
			}

			public String getSignMethod() {
				return this.signMethod;
			}

			public void setSignMethod(String signMethod) {
				this.signMethod = signMethod;
			}

			public String getScope() {
				return this.scope;
			}

			public void setScope(String scope) {
				this.scope = scope;
			}

			public String getOssPath() {
				return this.ossPath;
			}

			public void setOssPath(String ossPath) {
				this.ossPath = ossPath;
			}

			public Integer getStatus() {
				return this.status;
			}

			public void setStatus(Integer status) {
				this.status = status;
			}

			public String getOssUrl() {
				return this.ossUrl;
			}

			public void setOssUrl(String ossUrl) {
				this.ossUrl = ossUrl;
			}
		}
	}

	@Override
	public QueryThingConfigPopResponse getInstance(UnmarshallerContext context) {
		return	QueryThingConfigPopResponseUnmarshaller.unmarshall(this, context);
	}
}
