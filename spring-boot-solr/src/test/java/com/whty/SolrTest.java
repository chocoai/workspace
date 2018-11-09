package com.whty;

import com.whty.SolrDemo.Message;
import com.whty.util.GUIDGenerator;
import com.whty.SolrDemo.Post;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * \* User: zjd
 * \* Date: 2018/9/5
 * \* Time: 8:55
 * \* Description:
 * \
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SolrTest {

    @Autowired
    private SolrTemplate solrTemplate;

    @org.junit.Test
    public void add() {
        List<Post> list = new ArrayList<>();
        for(int i = 0;i<5;i++){
            Post post = new Post();
            post.setId(GUIDGenerator.getGUID());
            post.setPost_id(GUIDGenerator.getGUID());
            post.setPost_content("今天天气很好啊"+i);
            post.setPost_plateId(GUIDGenerator.getGUID());
            post.setName("zjd"+i);
            list.add(post);
        }
        solrTemplate.saveBeans("collection1",list);//collection1对应solr_home中的collection1
        solrTemplate.commit("collection1");
    }

    @org.junit.Test
    public void del() {
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete("collection1",query);
        solrTemplate.commit("collection1");
    }

    @org.junit.Test
    public void getByitem() {//条件查询
        Query query = new SimpleQuery("*:*");
        Criteria criteria = new Criteria("post_content").contains("好");
        query.addCriteria(criteria);
        query.setOffset((long)0);//开始索引
        query.setRows(20);//每页记录数
        ScoredPage<Post> page = solrTemplate.queryForPage("collection1",query,Post.class);
        for(Post post:page.getContent()){
            System.out.println(post.getPost_id()+post.getPost_plateId()+post.getPost_content());
        }
    }
}