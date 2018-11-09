package com.whty.SolrDemo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Id;

/**
 * \* User: zjd
 * \* Date: 2018/10/18
 * \* Time: 15:17
 * \* Description:
 * \
 */
@SolrDocument
public class Message {
    @Id
    @Field("id")
    private String id; //主键需要加两个注解uniqueKey(必填)
    @Field
    private String name;//普通的属性加Field注解即可

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}