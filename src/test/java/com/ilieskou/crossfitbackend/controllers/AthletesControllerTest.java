package com.ilieskou.crossfitbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilieskou.crossfitbackend.controllers.dto.AthleteDto;
import com.ilieskou.crossfitbackend.services.AthletesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AthletesControllerTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private AthletesController controller;

    @MockBean
    private AthletesService service;

    private AthleteDto mockAthlete;


    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.controller).build();
        this.mockAthlete = getAthleteDtoMock();
    }

    @Test
    public void testGetAthleteById() throws Exception {
        doReturn(mockAthlete).when(service).getAthlete(mockAthlete.getId());

        mockMvc.perform(get("/api/v1/athletes/{id}", mockAthlete.getId())
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockAthlete.getId()))
                .andExpect(jsonPath("$.firstName").value(mockAthlete.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(mockAthlete.getLastName()))
                .andExpect(jsonPath("$.email").value(mockAthlete.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(mockAthlete.getPhoneNumber()));
    }

    @Test
    public void testGetAthletes() throws Exception {
        List<AthleteDto> mockAthletes = getAthletesDtoMock();
        doReturn(mockAthletes).when(service).getAllAthletes();

        mockMvc.perform(get("/api/v1/athletes")
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(mockAthletes.get(0).getId()))
                .andExpect(jsonPath("$[0].firstName").value(mockAthletes.get(0).getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(mockAthletes.get(0).getLastName()))
                .andExpect(jsonPath("$[0].email").value(mockAthletes.get(0).getEmail()))
                .andExpect(jsonPath("$[0].phoneNumber").value(mockAthletes.get(0).getPhoneNumber()))
                .andExpect(jsonPath("$[1].id").value(mockAthletes.get(1).getId()))
                .andExpect(jsonPath("$[1].firstName").value(mockAthletes.get(1).getFirstName()))
                .andExpect(jsonPath("$[1].lastName").value(mockAthletes.get(1).getLastName()))
                .andExpect(jsonPath("$[1].email").value(mockAthletes.get(1).getEmail()))
                .andExpect(jsonPath("$[1].phoneNumber").value(mockAthletes.get(1).getPhoneNumber()));
    }

    @Test
    public void testGetAthleteByemail() throws Exception {
        doReturn(mockAthlete).when(service).getAthlete(mockAthlete.getEmail());

        mockMvc.perform(get("/api/v1/athletes/email?email=" + mockAthlete.getEmail())
                .header("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockAthlete.getId()))
                .andExpect(jsonPath("$.firstName").value(mockAthlete.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(mockAthlete.getLastName()))
                .andExpect(jsonPath("$.email").value(mockAthlete.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(mockAthlete.getPhoneNumber()));
    }

    @Test
    public void testDeleteAthleteById() throws Exception {
        doNothing().when(service).delete(mockAthlete.getId());

        mockMvc.perform(delete("/api/v1/athletes/{id}", mockAthlete.getId())
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testPostNewAthlete() throws Exception {
        String athleteString = new ObjectMapper().writeValueAsString(mockAthlete);
        doReturn(mockAthlete).when(service).create(any());

        mockMvc.perform(post("/api/v1/athletes")
                .header("Content-Type", "application/json")
                .content(athleteString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockAthlete.getId()))
                .andExpect(jsonPath("$.firstName").value(mockAthlete.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(mockAthlete.getLastName()))
                .andExpect(jsonPath("$.email").value(mockAthlete.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(mockAthlete.getPhoneNumber()));
    }

    private AthleteDto getAthleteDtoMock() {
        return new AthleteDto(1L,
                "Nick", "Ilieskou", new Date(), new Date(), "abc@gmail.com", "123");
    }

    private List<AthleteDto> getAthletesDtoMock() {
        return Arrays.asList(
                new AthleteDto[]{
                        new AthleteDto(1L,
                                "Nick", "Ilieskou", new Date(), new Date(), "abc@gmail.com", "123"),
                        new AthleteDto(2L,
                                "Joe", "Doe", new Date(), new Date(), "def@gmail.com", "456")});

    }
}

