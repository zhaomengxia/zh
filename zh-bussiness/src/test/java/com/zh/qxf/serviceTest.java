package com.zh.qxf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.collect.Lists;
import com.zh.Application;
import com.zh.dto.sys.MenuInfoDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class serviceTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    @Value("${server.port}")
    private int port;

    private MockMvc mockMvc;

    String menu = "/sysResources";

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).alwaysExpect(status().isOk()).build();
    }

    @Test
    public void getCommunityCharge()throws Exception{
        mockMvc.perform(get("/xihu/communityCharge/getCommunityCharge")
                .param("communityId", "1")
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addOrUpdateMenuInfo()throws Exception{
        MenuInfoDTO menuInfoDTO = new MenuInfoDTO();
        menuInfoDTO.setDescription("test");
        menuInfoDTO.setHttpPath("test");
        menuInfoDTO.setLayer(2);
        menuInfoDTO.setMenuName("测试子菜单");
        menuInfoDTO.setSeq(1);
        menuInfoDTO.setParentId(1075715631077478401L);
//        menuInfoDTO.setMenuId(1075715631077478401L);
//        keywordDTO.setId(1L);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(menuInfoDTO);
        System.out.println(requestJson);
        mockMvc.perform(post(  menu + "/addOrUpdateMenuInfo")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

