package med.voll.api.infraestructure.http.controller;

import med.voll.api.application.services.AppointmentServices;
import med.voll.api.domain.dtos.appointments.CancelAppointmentDTO;
import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.dtos.appointments.ScheduledAppointmentDTO;
import med.voll.api.domain.entities.Address;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.Patient;
import med.voll.api.domain.enums.CancelAppointmentReason;
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

    @Autowired
    private JacksonTester<CancelAppointmentDTO> toCancelAppointmentDTO;

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
    @DisplayName("Should return 400 when provided req body lack some information")
    @WithMockUser
    void toScheduleScenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var expertise = DoctorsExpertiseEnums.CARDIOLOGIA;
        var toScheduleAppointment = new ScheduleAppointmentsDTO(2L, null, date, expertise);
        var scheduledAppointment = new ScheduledAppointmentDTO(1L, 2L, 4L, date);

        var toScheduleAppointmentDtoJson = scheduleAppointmentsDTO.write(toScheduleAppointment).getJson();

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

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return 200 when provided req body is existent and valid")
    @WithMockUser
    void toScheduleScenario3() throws Exception {
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

    @Test
    @DisplayName("Should return 204 when a valid and existent appointment has cancelled")
    @WithMockUser
    void toCancelAppointmentScenario1() throws Exception {
        //given
        var now = LocalDateTime.now();
        var appointment = new Appointment(1L, buildAnDoctor(), buildAnPatient(), now, true);
        var toCancelAppointment = new CancelAppointmentDTO(appointment, CancelAppointmentReason.OTHER_REASON);

        var toCancelAppointmentDtoJson = toCancelAppointmentDTO.write(toCancelAppointment).getJson();

        Mockito
                .doNothing()
                .when(appointmentServices).toCancel(toCancelAppointment);

        //when
        var response = mvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toCancelAppointmentDtoJson)
        ).andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Should return 400 when provided DTO is invalid")
    @WithMockUser
    void toCancelAppointmentScenario2() throws Exception {
        //given
        var toCancelAppointment = new CancelAppointmentDTO(null, CancelAppointmentReason.OTHER_REASON);
        var toCancelAppointmentDtoJson = toCancelAppointmentDTO.write(toCancelAppointment).getJson();

        Mockito
                .doNothing()
                .when(appointmentServices).toCancel(toCancelAppointment);
        //when
        var response = mvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toCancelAppointmentDtoJson)
        ).andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    private Address buildAnAddress() {
        return new Address("here", "here", "06444900", "here", "sp", "12", "over here");
    }

    private Patient buildAnPatient() {
        return new Patient(1L, "patient", "email@email.com", "123456", "12312312312", buildAnAddress(), true);
    }

    private Doctor buildAnDoctor() {
        return new Doctor(1L, "doctor", "email", "1234567", "123456", DoctorsExpertiseEnums.CARDIOLOGIA, buildAnAddress(), true);
    }

}