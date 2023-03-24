package com.example.job4j_accidents.controller;

import com.example.job4j_accidents.Job4jAccidentsApplication;
import com.example.job4j_accidents.model.Accident;
import com.example.job4j_accidents.repository.springdata.AccidentRepository;
import com.example.job4j_accidents.service.AccidentService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Job4jAccidentsApplication.class)
@AutoConfigureMockMvc
@WithMockUser
class AccidentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AccidentRepository accidentRepository;
    @SpyBean
    private AccidentService accidentService;
    @Captor
    private ArgumentCaptor<Accident> accidentCapt;
    @Captor
    private ArgumentCaptor<List> rolesCapt;
    @Captor
    private ArgumentCaptor<Integer> typeCapt;

    @Test
    void viewCreateAccident() throws Exception {
        mockMvc.perform(get("/createAccident"))
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"))
                .andExpect(model().attributeExists("types", "rules"));
    }

    @Test
    void save() throws Exception {
        this.mockMvc.perform(post("/saveAccident")
                        .param("type.id", "1")
                        .param("rIds", "1,2")
                        .flashAttr("accident", Accident.builder()
                                .address("address_")
                                .text("text_")
                                .name("name_")
                                .build()))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(accidentService).create(accidentCapt.capture(),
                typeCapt.capture(), rolesCapt.capture());
        Accident accident = accidentCapt.getValue();
        assertThat(accident).satisfies(
                a -> assertThat(a.getAddress()).isEqualTo("address_"),
                a -> assertThat(a.getText()).isEqualTo("text_"),
                a -> assertThat(a.getName()).isEqualTo("name_"));
        assertThat(typeCapt.getValue()).isEqualTo(1);
        assertThat(rolesCapt.getValue()).isEqualTo(List.of(1, 2));
        Accident acdnt = accidentRepository.findById(accident.getId()).get();
        assertThat(acdnt).satisfies(
                a -> assertThat(a.getAddress()).isEqualTo("address_"),
                a -> assertThat(a.getText()).isEqualTo("text_"),
                a -> assertThat(a.getName()).isEqualTo("name_"));
        accidentRepository.deleteById(accident.getId());
    }

    @Test
    void getUpdateWithIdShouldReturnRelativeAccident() throws Exception {
        when(accidentService.findById(1)).thenCallRealMethod();
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
    void testUpdate() throws Exception {
        this.mockMvc.perform(post("/updateAccident")
                        .param("type.id", "1")
                        .param("rIds", "1,2")
                        .flashAttr("accident", Accident.builder()
                                .address("address")
                                .text("text")
                                .name("name")
                                .build()))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(accidentService).update(accidentCapt.capture(),
                typeCapt.capture(), rolesCapt.capture());
        Accident accident = accidentCapt.getValue();
        assertThat(accidentCapt.getValue()).satisfies(
                a -> assertThat(a.getAddress()).isEqualTo("address"),
                a -> assertThat(a.getText()).isEqualTo("text"),
                a -> assertThat(a.getName()).isEqualTo("name"));
        Accident acdnt = accidentRepository.findById(accident.getId()).get();
        assertThat(acdnt).satisfies(
                a -> assertThat(a.getAddress()).isEqualTo("address"),
                a -> assertThat(a.getText()).isEqualTo("text"),
                a -> assertThat(a.getName()).isEqualTo("name"));
        accidentRepository.deleteById(accident.getId());
    }
}
