package com.whty.wfd;

import javax.swing.*;
import java.util.*;

/**
 * \* User: zjd
 * \* Date: 2018/9/11
 * \* Time: 16:18
 * \* Description:
 * \
 */
public class Test2 {

    public static void main(String[] args) {
        String temp="data:audio/mp4;base64,asdasdasdasdasdasdsad";
        String temp2="data:audio/mp4;base64asdasdasdasdasdasdsad";
        int leng=temp2.lastIndexOf("base64,");
        System.out.println(leng);
        String result=temp.substring(leng+7);
        System.out.println(result);
    }

    public static Map volidateUser(String content,String[] userIds){
        Map map = new HashMap();
        List<String> lists= new ArrayList<>();
        if(userIds.length>0){
            for(String userId:userIds){
                if(content.contains(userId)){
                    content =content.replace(userId,"");
                    lists.add(userId);
                }
            }
        }
        return map;
    }


}