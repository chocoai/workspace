/**
 * Copyright (C) 2010-2016 Alibaba Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.whty.util;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.whty.entity.*;
import com.whty.mapper.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * MQ消息处理类
 */
@Component
public class MessageListenerImpl implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(IotConsumer.class);

    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        try{
            logger.info("Receive: " + message);
            String body = new String(message.getBody());
            logger.info(body);
            if(!body.contains("commandEvent") && body.contains("action") && !body.contains("identifier")){
                JSONObject jsStr = JSONObject.fromObject(body);
                DeviceStatusListener deviceStatusListener = (DeviceStatusListener) JSONObject.toBean(jsStr, DeviceStatusListener.class);
                DeviceStatusLogThread.add(deviceStatusListener);
                Date dayTime = new Date();
                dayTime.setTime((Long) deviceStatusListener.getStatus().get("time"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logger.info(sdf.format(dayTime));
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Action.CommitMessage;
    }
}
