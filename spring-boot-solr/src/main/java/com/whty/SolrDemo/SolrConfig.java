package com.whty.SolrDemo;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * \* User: zjd
 * \* Date: 2018/9/5
 * \* Time: 19:46
 * \* Description:
 * \
 */
@Configuration
public class SolrConfig {

    @Value("${spring.data.solr.host}")
    protected String solrHost;

    /**
     * 配置SolrTemplate
     */
    @Bean
    public SolrTemplate solrTemplate() {
        SolrClient solrServer = new HttpSolrClient(solrHost);
        SolrTemplate template = new SolrTemplate(solrServer);
        return template;
    }
}