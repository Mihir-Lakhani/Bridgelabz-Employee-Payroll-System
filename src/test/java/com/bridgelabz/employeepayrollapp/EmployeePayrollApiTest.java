package com.bridgelabz.employeepayrollapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeePayrollApiTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper json;

    @Test
    void crudAndMissingId() throws Exception {
        String body = mvc.perform(post("/employeepayrollservice/create")
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Lisa\",\"salary\":2000}"))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        long id = json.readTree(body).get("id").asLong();
        mvc.perform(get("/employeepayrollservice/get/{id}", id))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Lisa"));
        mvc.perform(put("/employeepayrollservice/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Mark\",\"salary\":3000}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.salary").value(3000));
        mvc.perform(delete("/employeepayrollservice/delete/{id}", id))
                .andExpect(status().isNoContent());
        mvc.perform(get("/employeepayrollservice/get/{id}", id))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.error").exists());
    }

    @Test
    void rejectsInvalidCreateAndUpdate() throws Exception {
        mvc.perform(post("/employeepayrollservice/create")
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Ma\",\"salary\":300}"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.error").exists());
        mvc.perform(put("/employeepayrollservice/update/1")
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"\",\"salary\":3000}"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.error").exists());
    }
}
