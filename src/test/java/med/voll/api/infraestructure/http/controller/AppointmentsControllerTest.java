package med.voll.api.infraestructure.http.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Should return 400 when provided req body is nonexistent")
    @WithMockUser
    void toScheduleScenario1() throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/appointments")).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}