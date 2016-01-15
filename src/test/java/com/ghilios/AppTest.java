package com.ghilios;

import com.ghilios.config.TestAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;

/**
 * Created by ghilios on 1/14/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestAppConfig.class)
public class AppTest {

    @Autowired private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void hal_samples_href_under_api() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/api")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.samples.href", is("http://localhost/api/samples")));
    }

    @Test
    public void get_api_samples_succeeds() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/samples")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void get_api_samples_someOtherMethod_succeeds() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/samples/someOtherMethod")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void get_api_samples_nonExistentMethod_fails() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/samples/nonExistentMethod")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void get_root_samples_fails() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/samples")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
