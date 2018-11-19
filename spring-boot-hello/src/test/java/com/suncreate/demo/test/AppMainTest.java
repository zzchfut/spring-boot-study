package com.suncreate.demo.test;

import com.suncreate.demo.AppMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.equalTo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.junit.Assert.*;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

/*
// MockMvc 模拟HTTP请求测试方式
@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppMain.class)
public class AppMainTest {

    private MockMvc mockMvc; // 模拟http请求

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AppMain()).build();
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("HelloSpring")));
    }
}
*/

/*
// Spring之TestRestTemplate测试方式
@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppMainTest {

    @LocalServerPort
    private int port;
    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/hello");
    }

    @Test
    public void getHello(){
        ResponseEntity<String> response = template.getForEntity(base.toString(),String.class);
        assertThat(response.getBody(), equalTo("HelloSpring"));
    }
}
*/

// 自动配置MockMvc的测试方式
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMain.class)
@AutoConfigureMockMvc
public class AppMainTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHello() throws Exception {
        this.mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("HelloSpring"))
                .andReturn();
    }
}
