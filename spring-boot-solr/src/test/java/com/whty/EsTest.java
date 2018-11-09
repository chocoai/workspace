package com.whty;

import com.google.gson.Gson;
import com.whty.ElasticsearchDemo.Employee;
import com.whty.ElasticsearchDemo.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * \* User: zjd
 * \* Date: 2018/9/5
 * \* Time: 8:55
 * \* Description:
 * \
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EsTest {
    @Autowired
    private EmployeeRepository es;
    @Test
    public void add() {
        Employee EmployeesDO = new Employee();
        EmployeesDO.setId("1");
        EmployeesDO.setFirstName("xuxu");
        EmployeesDO.setLastName("zh");
        EmployeesDO.setAge(26);
        EmployeesDO.setAbout("i am in peking");
        es.save(EmployeesDO);
    }
    @Test
    public void del() {
        es.deleteAll();
    }

    @Test
    public void update() {
        Employee EmployeesDO = es.queryEmployeeById("1");
        EmployeesDO.setFirstName("哈哈");
        es.save(EmployeesDO);
    }

    @Test
    public void query() {
        Employee accountInfo = es.queryEmployeeById("1");
        System.err.println(new Gson().toJson(accountInfo));
    }
}