package com.example.job4j_accidents.controller;

import com.example.job4j_accidents.Job4jAccidentsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Job4jAccidentsApplication.class)
@AutoConfigureMockMvc
@WithMockUser
class AccidentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void viewCreateAccident() throws Exception {
        mockMvc.perform(get("/createAccident"))
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"))
                .andExpect(model().attributeExists("types", "rules"));
    }

    @Test
    void save() {
    }

    @Test
    void updateWithIdShouldReturnRelativeAccident() throws Exception {
        mockMvc.perform(get("/formUpdateAccident")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("formUpdateAccident"))
                .andExpect(model()
                        .attributeExists("accident", "types", "rulesAll"))
                .andExpect(model().attribute("accident",
                        hasProperty("id", is(1))));
    }

    @Test
    void testUpdate() {
    }
}
