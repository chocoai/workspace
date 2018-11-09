/**
 * 
 */
package com.whty.common.util;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.iot.model.v20180120.BatchCheckDeviceNamesRequest;
import com.aliyuncs.iot.model.v20180120.BatchCheckDeviceNamesResponse;
import com.aliyuncs.iot.model.v20180120.BatchRegisterDeviceRequest;
import com.aliyuncs.iot.model.v20180120.BatchRegisterDeviceResponse;
import com.aliyuncs.iot.model.v20180120.DisableThingRequest;
import com.aliyuncs.iot.model.v20180120.DisableThingResponse;
import com.aliyuncs.iot.model.v20180120.EnableThingRequest;
import com.aliyuncs.iot.model.v20180120.EnableThingResponse;
import com.aliyuncs.iot.model.v20180120.InvokeThingServiceRequest;
import com.aliyuncs.iot.model.v20180120.InvokeThingServiceResponse;
import com.aliyuncs.iot.model.v20180120.QueryBatchCheckDeviceNamesStatusRequest;
import com.aliyuncs.iot.model.v20180120.QueryBatchCheckDeviceNamesStatusResponse;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailRequest;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailResponse;
import com.aliyuncs.iot.model.v20180120.QueryDevicePropertyStatusRequest;
import com.aliyuncs.iot.model.v20180120.QueryDevicePropertyStatusResponse;
import com.aliyuncs.iot.model.v20180120.QueryDeviceStatisticsRequest;
import com.aliyuncs.iot.model.v20180120.QueryDeviceStatisticsResponse;
import com.aliyuncs.iot.model.v20180120.QueryProductRequest;
import com.aliyuncs.iot.model.v20180120.QueryProductResponse;
import com.aliyuncs.iot.model.v20180120.RegisterDeviceRequest;
import com.aliyuncs.iot.model.v20180120.RegisterDeviceResponse;

/**
 * @author zhangzheng
 * @date 2018年4月3日
 */
public class IotUtils extends BaseIot {
	public static void queryDeviceStatistics(String productKey) {
		QueryDeviceStatisticsRequest request = new QueryDeviceStatisticsRequest();

		request.setProductKey(productKey);

		QueryDeviceStatisticsResponse response = (QueryDeviceStatisticsResponse) executeTest(request);

		if (response != null && response.getSuccess() != false) {
			LogUtil.print("获取物数量的服务！ " + JSONObject.toJSONString(response));
		} else {
			LogUtil.error("获取物的数量服务！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}

	}

	public static void queryDevicePropertyStatus(String iotId, String productKey, String deviceName) {
		QueryDevicePropertyStatusRequest request = new QueryDevicePropertyStatusRequest();

		request.setIotId(iotId);
		request.setProductKey(productKey);
		request.setDeviceName(deviceName);

		QueryDevicePropertyStatusResponse response = (QueryDevicePropertyStatusResponse) executeTest(request);

		if (response != null && response.getSuccess() != false) {
			LogUtil.print("调用物的服务！ " + JSONObject.toJSONString(response));
		} else {
			LogUtil.error("调用物的服务！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}
	}

	/**
	 * 调用物的服务 可以作为事件发送
	 * 
	 * @param iotId
	 * @param productKey
	 * @param deviceName
	 * @param identifier
	 * @param args
	 */
	public static void invokeThingService(String iotId, String productKey, String deviceName, String identifier,
			String args) {
		InvokeThingServiceRequest request = new InvokeThingServiceRequest();

		request.setIotId(iotId);
		request.setProductKey(productKey);
		request.setDeviceName(deviceName);
		request.setIdentifier(identifier);
		request.setArgs(args);

		InvokeThingServiceResponse response = (InvokeThingServiceResponse) executeTest(request);

		if (response != null && response.getSuccess() != false) {
			LogUtil.print("调用物的服务！ " + JSONObject.toJSONString(response));
		} else {
			LogUtil.error("调用物的服务！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}
	}

	/**
	 * 获取物的属性
	 * 
	 * @param identifier
	 * @param startTime
	 * @param endTime
	 * @param pageSize
	 * @param asc
	 */
	// private static void queryDevicePropertyData(String identifier,Long
	// startTime, Long endTime,Integer pageSize,Integer asc){
	// QueryDevicePropertyDataRequest request = new
	// QueryDevicePropertyDataRequest();
	// request.setProductKey(productKey);
	// request.setApplyId(applyId);
	//
	// QueryBatchCheckDeviceNamesStatusResponse response =
	// (QueryBatchCheckDeviceNamesStatusResponse) executeTest(request);
	//
	// if (response != null && response.getSuccess() != false) {
	// LogUtil.print("查询批量检查设备名称状态成功！ " + JSONObject.toJSONString(response));
	// } else {
	// LogUtil.error("查询批量检查设备名称状态失败！requestId:" + response.getRequestId() +
	// "原因：" + response.getErrorMessage());
	// }
	// }

	public static void queryBatchCheckDeviceNamesStatus(String productKey, Long applyId) {
		QueryBatchCheckDeviceNamesStatusRequest request = new QueryBatchCheckDeviceNamesStatusRequest();
		request.setProductKey(productKey);
		request.setApplyId(applyId);

		QueryBatchCheckDeviceNamesStatusResponse response = (QueryBatchCheckDeviceNamesStatusResponse) executeTest(
				request);

		if (response != null && response.getSuccess() != false) {
			LogUtil.print("查询批量检查设备名称状态成功！ " + JSONObject.toJSONString(response));
		} else {
			LogUtil.error("查询批量检查设备名称状态失败！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}
	}

	public static void batchRegisterDevice(String productKey, int count) {
		BatchRegisterDeviceRequest request = new BatchRegisterDeviceRequest();
		request.setProductKey(productKey);
		request.setCount(count);

		BatchRegisterDeviceResponse response = (BatchRegisterDeviceResponse) executeTest(request);
		if (response != null && response.getSuccess() != false) {
			LogUtil.print("批量注册设备成功！ " + JSONObject.toJSONString(response));
		} else {
			LogUtil.error("批量注册设备失败！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}
	}

	public static void batchCheckDeviceNames(String productKey, List<String> deviceNames) {
		BatchCheckDeviceNamesRequest request = new BatchCheckDeviceNamesRequest();
		request.setProductKey(productKey);
		request.setDeviceNames(deviceNames);

		BatchCheckDeviceNamesResponse response = (BatchCheckDeviceNamesResponse) executeTest(request);
		if (response != null && response.getSuccess() != false) {
			LogUtil.print("批量检查设备名称成功！ " + JSONObject.toJSONString(response));
		} else {
			LogUtil.error("批量检查设备名称失败！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}
	}

	public static void enableThing(String iotId) {
		EnableThingRequest request = new EnableThingRequest();
		request.setIotId(iotId);

		EnableThingResponse response = (EnableThingResponse) executeTest(request);
		if (response != null && response.getSuccess() != false) {
			LogUtil.print("设备解禁成功！ " + JSONObject.toJSONString(response));
		} else {
			LogUtil.error("查询解禁失败！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}
	}

	public static void registerDevice(String productKey, String deviceName) {
		RegisterDeviceRequest request = new RegisterDeviceRequest();
		request.setProductKey(productKey);
		request.setDeviceName(deviceName);
		RegisterDeviceResponse response = (RegisterDeviceResponse) executeTest(request);
		if (response != null && response.getSuccess() != false) {
			LogUtil.print("注册设备成功！ " + JSONObject.toJSONString(response));
		} else {
			LogUtil.error("注册设备失败！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}
	}

	public static void disableThing(String iotId) {
		DisableThingRequest request = new DisableThingRequest();
		request.setIotId(iotId);

		DisableThingResponse response = (DisableThingResponse) executeTest(request);
		if (response != null && response.getSuccess() != false) {
			LogUtil.print("查询禁用成功！ " + JSONObject.toJSONString(response));
		} else {
			LogUtil.error("查询禁用失败！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}
	}

	/**
	 * 查询产品详情
	 * 
	 * @param productKey
	 */
	public static void queryProduct(String productKey) {
		QueryProductRequest request = new QueryProductRequest();
		request.setProductKey(productKey);

		QueryProductResponse response = (QueryProductResponse) executeTest(request);
		if (response != null && response.getSuccess() != false) {
			LogUtil.print("查询设备详情成功！ " + JSONObject.toJSONString(response));
		} else {
			LogUtil.error("查询设备详情失败！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}
	}

	public static QueryDeviceDetailResponse queryDeviceDetail(String productKey, String deviceName) {
		QueryDeviceDetailRequest request = new QueryDeviceDetailRequest();
		request.setProductKey(productKey);
		request.setDeviceName(deviceName);
		return (QueryDeviceDetailResponse) executeTest(request);
	}
}
