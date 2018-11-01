package com.an.curd.test;

import com.an.curd.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:/Users/bxan/IdeaProjects/SSMcurd/src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MVCTest {
    @Autowired
    WebApplicationContext applicationContext;
    MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void testPage() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/emps")
                        .param("pn", "5"))
                .andReturn();

        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("sum---->" + pageInfo.getPages());
        System.out.println("current---->" + pageInfo.getPageNum());
        System.out.println("size---->" + pageInfo.getSize());
        System.out.println("total---->" + pageInfo.getTotal());


        int[] navigatepageNums = pageInfo.getNavigatepageNums();
        for (int i = 0; i < navigatepageNums.length; ++i) {
            System.out.println(navigatepageNums[i]);
        }


        List<Employee> list = pageInfo.getList();

    }

}
