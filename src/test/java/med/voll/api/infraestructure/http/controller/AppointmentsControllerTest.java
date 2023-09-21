package med.voll.api.infraestructure.http.controller;

import med.voll.api.application.services.AppointmentServices;
import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.dtos.appointments.ScheduledAppointmentDTO;
import med.voll.api.domain.enums.DoctorsExpertiseEnums;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ScheduleAppointmentsDTO> scheduleAppointmentsDTO;

    @Autowired
    private JacksonTester<ScheduledAppointmentDTO> scheduledAppointmentDTO;

    @MockBean
    private AppointmentServices appointmentServices;

    @Test
    @DisplayName("Should return 400 when provided req body is nonexistent")
    @WithMockUser
    void toScheduleScenario1() throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/appointments")).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return 200 when provided req body is existent and valid")
    @WithMockUser
    void toScheduleScenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var expertise = DoctorsExpertiseEnums.CARDIOLOGIA;
        var toScheduleAppointment = new ScheduleAppointmentsDTO(2L, 4L, date, expertise);
        var scheduledAppointment = new ScheduledAppointmentDTO(1L, 2L, 4L, date);

        var toScheduleAppointmentDtoJson = scheduleAppointmentsDTO.write(toScheduleAppointment).getJson();
        var scheduledAppointmentDtoJson = scheduledAppointmentDTO.write(scheduledAppointment).getJson();

        Mockito.when(appointmentServices.toSchedule(toScheduleAppointment)).thenReturn(scheduledAppointment);

        var response = mvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toScheduleAppointmentDtoJson)
                )
                .andReturn()
                .getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(scheduledAppointmentDtoJson);
    }
}