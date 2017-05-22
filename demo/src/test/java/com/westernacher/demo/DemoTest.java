package com.westernacher.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.westernacher.demo.controllers.UserController;
import com.westernacher.demo.data.interfaces.UserRepository;
import com.westernacher.demo.services.UserService;
import com.westernacher.demo.services.UserServiceImpl;
import com.westernacher.model.domain.User;
import com.westernacher.model.transformer.BaseTransformer;
import com.westernacher.model.transformer.UserTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class DemoTest {
 
    @Autowired
    private WebApplicationContext ctx;   
 
    private MockMvc mockMvc;
 
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }   
    
    @Test    
    public void users() throws Exception {        
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }    
 
    @Configuration
    @EnableWebMvc
    public static class TestConfiguration {
 
        @Bean
        public UserController userController() {
            return new UserController();
        } 
        
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        } 
        
        @Bean
        public BaseTransformer<User, com.westernacher.model.rest.User> userTransformer() {
            return new UserTransformer();
        } 
        
        @Bean
        public UserRepository userRepository() {
            return Mockito.mock(UserRepository.class);
        }
    } 
}