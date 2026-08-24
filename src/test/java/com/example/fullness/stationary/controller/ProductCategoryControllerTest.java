package com.example.fullness.stationary.controller;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import com.example.fullness.stationary.form.CategoryForm;
import com.example.fullness.stationary.service.ProductCategoryService;

@WebMvcTest(ProductCategoryController.class)
class ProductCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductCategoryService productCategoryService;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void formSubmitShouldReturnValidationErrors() throws Exception {
        mockMvc.perform(post("/admin/category/add")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/category/form"))
                .andExpect(model().attributeExists("errorMessages"))
                .andExpect(model().attribute("errorMessages", hasItems("カテゴリー名を入力してください")));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void confirmationPageShouldUseSessionForm() throws Exception {
        CategoryForm form = new CategoryForm();
        form.setName("文房具");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("form", form);

        mockMvc.perform(get("/admin/category/add/confirm").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/category/confirm"))
                .andExpect(model().attributeExists("form"))
                .andExpect(model().attribute("form", hasProperty("name", is("文房具"))));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void confirmationSubmitShouldRejectInvalidCategoryName() throws Exception {
        CategoryForm form = new CategoryForm();
        form.setName("");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("form", form);

        mockMvc.perform(post("/admin/category/add/confirm")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .session(session)
                .param("action", "register"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/category/form"))
                .andExpect(model().attributeExists("errorMessages"))
                .andExpect(model().attribute("errorMessages", hasItems("カテゴリー名を入力してください")));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void completePageShouldShowRegisteredCategoryName() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("registeredCategoryName", "文房具");

        mockMvc.perform(get("/admin/category/add/complete").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/category/complete"))
                .andExpect(model().attribute("categoryName", is("文房具")));
    }
}
