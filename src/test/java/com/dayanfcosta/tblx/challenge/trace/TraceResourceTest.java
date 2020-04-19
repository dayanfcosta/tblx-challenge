package com.dayanfcosta.tblx.challenge.trace;

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

class TraceResourceTest extends AbstractResourceTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void whenFindAll_returnAllTracesFound() throws Exception {
    final var result = mockMvc.perform(
        get("/traces")
            .param("startTime", now().toString())
            .param("endTime", now().plusDays(10).toString())
            .param("vehicleId", String.valueOf(1))
    )
        .andReturn();

    mockMvc.perform(asyncDispatch(result))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(1));
  }

  @Test
  void whenFindAllWithInvalidVehicleId_throwsIllegalArgumentException() throws Exception {
    mockMvc.perform(
        get("/traces")
            .param("startTime", now().toString())
            .param("endTime", now().plusDays(10).toString())
            .param("vehicleId", String.valueOf(0))
    )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").value("400"))
        .andExpect(jsonPath("$.error").value("Bad Request"))
        .andExpect(jsonPath("$.message").value("Invalid vehicle ID"));
  }

  @Test
  void whenFindAllWithInvalidTimeFrame_throwsIllegalArgumentException() throws Exception {
    mockMvc.perform(
        get("/traces")
            .param("startTime", now().plusDays(2).toString())
            .param("endTime", now().toString())
            .param("vehicleId", String.valueOf(0))
    )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.error").value("Bad Request"))
        .andExpect(jsonPath("$.message").value("End time MUST be after or equals start time"));
  }

}