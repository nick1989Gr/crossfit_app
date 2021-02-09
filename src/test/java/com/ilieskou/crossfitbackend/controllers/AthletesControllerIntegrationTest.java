package com.ilieskou.crossfitbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilieskou.crossfitbackend.controllers.dto.AthleteDto;
import com.ilieskou.crossfitbackend.services.AthletesService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AthletesControllerIntegrationTest {

    private static final String WRONG_TOKEN = "This is the wrong token";

    @MockBean
    private AthletesService service;

    @Autowired
    private MockMvc mockMvc;

    private AthleteDto mockAthlete;
    private String accessToken;

    @Value("${auth0.audience}")
    private String audience;
    @Value("${auth0.client.id}")
    private String clientId;
    @Value("${auth0.client.secret}")
    private String clientSecret;
    @Value("${auth0.client.id.with.read.athletes.scope}")
    private String clientIdWithScope;
    @Value("${auth0.client.secret.with.read.athletes.scope}")
    private String clientSecretWithScope;

    @BeforeEach
    public void setup() {
        this.mockAthlete = getAthleteDtoMock();
        this.accessToken = getAccessToken();
    }

    @Test
    @DisplayName("GET athletes - Found and results are correct")
    void testGetAthletesFound() throws Exception {

        String accessToken = getAccessTokenWithReadAthletesScope();
        List<AthleteDto> mockAthletes = getAthletesDtoMock();
        doReturn(mockAthletes).when(service).getAllAthletes();

        mockMvc.perform(get("/api/v1/athletes")
                .header("Authorization", "Bearer " + accessToken)
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
    @DisplayName("GET athletes - Not Authorized due to no scope")
    void testGetAthletesNotAuthorizedNoScope() throws Exception {

        List<AthleteDto> mockAthletes = getAthletesDtoMock();
        doReturn(mockAthletes).when(service).getAllAthletes();

        mockMvc.perform(get("/api/v1/athletes")
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("GET athletes - Not Authorized no token")
    void testGetAthletesNotAuthorizedNoToken() throws Exception {

        List<AthleteDto> mockAthletes = getAthletesDtoMock();
        doReturn(mockAthletes).when(service).getAllAthletes();

        mockMvc.perform(get("/api/v1/athletes")
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET athlete/1 - Found and results are correct")
    void testGetAthleteByIdFound() throws Exception {
        doReturn(mockAthlete).when(service).getAthlete(mockAthlete.getId());

        mockMvc.perform(get("/api/v1/athletes/{id}", mockAthlete.getId())
                .header("Authorization", "Bearer " + accessToken)
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
    @DisplayName("GET athlete/1 - Unathorized request due to no access token")
    void testGetAthleteByIdNotAuthorized() throws Exception {
        doReturn(mockAthlete).when(service).getAthlete(mockAthlete.getId());

        mockMvc.perform(get("/api/v1/athletes/{id}", mockAthlete.getId())
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET athlete/1 - Unathorized request due to wrong access token")
    void testGetAthleteByIdNotAuthorizedDueToWrongAccessToken() throws Exception {
        AthleteDto mockAthlete = getAthleteDtoMock();
        doReturn(mockAthlete).when(service).getAthlete(mockAthlete.getId());

        mockMvc.perform(get("/api/v1/athletes/{id}", mockAthlete.getId())
                .header("Authorization", "Bearer " + WRONG_TOKEN)
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


    @Test
    @DisplayName("GET athlete/email?email=abc@gmail.com - Found and results are correct")
    void testGetAthleteByemailFound() throws Exception {
        doReturn(mockAthlete).when(service).getAthlete(mockAthlete.getEmail());

        mockMvc.perform(get("/api/v1/athletes/email?email=" + mockAthlete.getEmail())
                .header("Authorization", "Bearer " + accessToken)
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
    @DisplayName("GET athlete/email?email=abc@gmail.com - Authorization error due to wrong token")
    void testGetAthleteByemailAuthorizationErrorDueToWrongToken() throws Exception {
        doReturn(mockAthlete).when(service).getAthlete(mockAthlete.getEmail());

        mockMvc.perform(get("/api/v1/athletes/email?email=" + mockAthlete.getEmail())
                .header("Authorization", "Bearer " + WRONG_TOKEN)
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET athlete/email?email=abc@gmail.com - Authorization error")
    void testGetAthleteByemailAuthorizationError() throws Exception {

        doReturn(mockAthlete).when(service).getAthlete("abc@gmail.com");

        mockMvc.perform(get("/api/v1/athletes/email?email=" + mockAthlete.getEmail())
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


    @Test
    @DisplayName("DELETE athlete/1 - Deleted")
    void testDeleteAthleteById() throws Exception {

        doNothing().when(service).delete(mockAthlete.getId());

        mockMvc.perform(delete("/api/v1/athletes/{id}", mockAthlete.getId())
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE athlete/1 - Not Authorized")
    void testDeleteAthleteByIdNotAuthorized() throws Exception {
        final Long id = Long.valueOf(1);
        doNothing().when(service).delete(id);

        mockMvc.perform(delete("/api/v1/athletes/{id}", id))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("DELETE athlete/1 - Not Authorized due to wrong token")
    void testDeleteAthleteByIdNotAuthorizedDueToWrongToken() throws Exception {
        final Long id = Long.valueOf(1);
        doNothing().when(service).delete(id);

        mockMvc.perform(delete("/api/v1/athletes/{id}", id)
                .header("Authorization", "Bearer " + WRONG_TOKEN)
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("POST athlete")
    void testPostNewAthlete() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String athleteString = mapper.writeValueAsString(mockAthlete);
        doReturn(mockAthlete).when(service).create(any());

        mockMvc.perform(post("/api/v1/athletes")
                .header("Authorization", "Bearer " + accessToken)
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

    private String getAccessTokenWithReadAthletesScope() {
        HttpResponse<String> response = Unirest.post("https://crossfitapp-dev.eu.auth0.com/oauth/token")
                .header("content-type", "application/json")
                .body("{\"client_id\":\"" + clientIdWithScope
                        + "\",\"client_secret\":\"" + clientSecretWithScope
                        + "\",\"audience\":\"" + audience
                        + "\",\"grant_type\":\"client_credentials\"}")
                .asString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        String accessToken = jsonParser.parseMap(response.getBody()).get("access_token").toString();
        return accessToken;
    }

    private String getAccessToken() {
        HttpResponse<String> response = Unirest.post("https://crossfitapp-dev.eu.auth0.com/oauth/token")
                .header("content-type", "application/json")
                .body("{\"client_id\":\"" + clientId
                        + "\",\"client_secret\":\"" + clientSecret
                        + "\",\"audience\":\"" + audience
                        + "\",\"grant_type\":\"client_credentials\"}")
                .asString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        String accessToken = jsonParser.parseMap(response.getBody()).get("access_token").toString();
        return accessToken;
    }
}
