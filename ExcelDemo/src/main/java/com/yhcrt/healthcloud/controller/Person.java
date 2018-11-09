/**
 * 
 */
package com.yhcrt.healthcloud.controller;

/** 
 * @ClassName: Person 
 * @Description: TODO
 * @author: zjd
 * @date: 2018年3月7日 上午11:05:19  
 */
public class Person {

	public void save(String name) {
        System.out.println("我是save()方法");
    }

    public void update(String name, Integer id) {
        System.out.println("我是update()方法");
    }

    public String getPersonName(Integer id) {
        System.out.println("我是getPersonName()方法");
        return "xxx";
    }
}
