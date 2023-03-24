package com.example.job4j_accidents.controller;

import com.example.job4j_accidents.Job4jAccidentsApplication;
import com.example.job4j_accidents.model.User;
import com.example.job4j_accidents.repository.springdata.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Job4jAccidentsApplication.class)
@AutoConfigureMockMvc
class RegControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private UserRepository userRepositorySpy;
    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void saveNewUserShouldSaveAndRedirect() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", "username")
                        .param("password", "password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
        verify(userRepositorySpy).save(userCaptor.capture());
        User user = userCaptor.getValue();
        assertThat(user.getUsername()).isEqualTo("username");
        assertThat(userRepositorySpy.findById(user.getId()).get().getUsername())
                .isEqualTo("username");
        userRepositorySpy.deleteById(user.getId());
    }

    @Test
    void saveExistingUserShouldAlertMessage() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", "userA")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
        verify(userRepositorySpy).save(userCaptor.capture());
        User user = userCaptor.getValue();
        mockMvc.perform(post("/reg")
                        .param("username", "userA")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("reg"))
                .andExpect(model().attribute("errorMessage",
                        "Пользователь с таким именем уже существует"));
        userRepositorySpy.deleteById(user.getId());
    }

    @Test
    void regPage() throws Exception {
        mockMvc.perform(get("/reg"))
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }
}
