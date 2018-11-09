package com.whty.SolrDemo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Id;

/**
 * \* User: zjd
 * \* Date: 2018/9/5
 * \* Time: 9:07
 * \* Description:
 * \
 */
@SolrDocument
public class Post {
    @Id
    @Field("id")
    private String id; //主键需要加两个注解uniqueKey(必填)
    @Field
    private String post_id;//普通的属性加Field注解即可
    @Field
    private String post_content;
    @Field
    private String post_plateId;
    @Field
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_plateId() {
        return post_plateId;
    }

    public void setPost_plateId(String post_plateId) {
        this.post_plateId = post_plateId;
    }
}