package com.dayanfcosta.tblx.challenge.vehicle;

import static java.time.LocalDate.now;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dayanfcosta.tblx.challenge.AbstractResourceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class VehicleResourceTest extends AbstractResourceTest {

  private static final String OPERATOR = "OPERATOR";

  @Autowired
  private MockMvc mockMvc;

  @Test
  void whenFindAllAtStop_returnAllVehiclesFound() throws Exception {
    final var result = mockMvc.perform(
        get("/vehicles")
            .param("startTime", now().toString())
            .param("endTime", now().plusDays(10).toString())
            .param("operator", OPERATOR)
            .param("atStop", String.valueOf(true))
    )
        .andReturn();

    mockMvc.perform(asyncDispatch(result))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(5))
        .andExpect(jsonPath("$.[0]").value("2"))
        .andExpect(jsonPath("$.[1]").value("4"))
        .andExpect(jsonPath("$.[2]").value("6"))
        .andExpect(jsonPath("$.[3]").value("8"))
        .andExpect(jsonPath("$.[4]").value("10"));
  }

  @Test
  void whenFindAllNotAtStop_returnAllVehiclesFound() throws Exception {
    final var result = mockMvc.perform(
        get("/vehicles")
            .param("startTime", now().toString())
            .param("endTime", now().plusDays(10).toString())
            .param("operator", OPERATOR)
            .param("atStop", String.valueOf(false))
    )
        .andReturn();

    mockMvc.perform(asyncDispatch(result))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(5))
        .andExpect(jsonPath("$.[0]").value("1"))
        .andExpect(jsonPath("$.[1]").value("3"))
        .andExpect(jsonPath("$.[2]").value("5"))
        .andExpect(jsonPath("$.[3]").value("7"))
        .andExpect(jsonPath("$.[4]").value("9"));
  }

  @Test
  void whenFindAllWithEmptyOperator_throwsIllegalArgumentException() throws Exception {
    mockMvc.perform(
        get("/vehicles")
            .param("startTime", now().toString())
            .param("endTime", now().plusDays(10).toString())
            .param("operator", "")
            .param("atStop", String.valueOf(false))
    )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.error").value("Bad Request"))
        .andExpect(jsonPath("$.message").value("Invalid operator ID"));
  }

  @Test
  void whenFindAllWithNullOperator_throwsIllegalArgumentException() throws Exception {
    mockMvc.perform(
        get("/vehicles")
            .param("startTime", now().toString())
            .param("endTime", now().plusDays(10).toString())
            .param("operator", "")
            .param("atStop", String.valueOf(false))
    )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.error").value("Bad Request"))
        .andExpect(jsonPath("$.message").value("Invalid operator ID"));
  }

  @Test
  void whenFindAllWithInvalidTimeFrame_throwsIllegalArgumentException() throws Exception {
    mockMvc.perform(
        get("/vehicles")
            .param("startTime", now().plusDays(2).toString())
            .param("endTime", now().toString())
            .param("operator", OPERATOR)
    )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.error").value("Bad Request"))
        .andExpect(jsonPath("$.message").value("End time MUST be after or equals start time"));
  }

}