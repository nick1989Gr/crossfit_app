package com.ilieskou.crossfitbackend.services;

import com.ilieskou.crossfitbackend.controllers.dto.AthleteDto;
import com.ilieskou.crossfitbackend.models.Athlete;
import com.ilieskou.crossfitbackend.repositories.AthletesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AthletesServiceTest {

    @Autowired
    private AthletesService athletesService;

    @MockBean
    private AthletesRepository athletesRepository;

    private Athlete expectedAthlete;
    private List<Athlete> expectedAthletes;

    @Before
    public void setup() {
        this.expectedAthlete = new Athlete(1L,
                "Nick", "Ilieskou", new Date(), new Date(), "abc@gmail.com", "123");
        this.expectedAthletes = Arrays.asList(
                new Athlete[]{
                        expectedAthlete,
                        new Athlete(2L,
                                "Joe", "Doe", new Date(), new Date(), "def@gmail.com", "456")
                });
        doReturn(expectedAthletes).when(athletesRepository).findAll();
        doReturn(Optional.of(expectedAthlete)).when(athletesRepository).findById(expectedAthlete.getId());
        doReturn(expectedAthlete).when(athletesRepository).findAthleteByEmail(expectedAthlete.getEmail());
    }

    @Test
    public void test_getAthleteById() {
        AthleteDto actualAthlete = athletesService.getAthlete(this.expectedAthlete.getId());
        assertThatAthleteAreEqual(actualAthlete, this.expectedAthlete);
    }

    @Test
    public void test_getAthleteByEmail() {
        AthleteDto actualAthlete = athletesService.getAthlete(this.expectedAthlete.getEmail());
        assertThatAthleteAreEqual(actualAthlete, this.expectedAthlete);
    }

    @Test
    public void test_getAllAthletes() {
        List<AthleteDto> actualAthletes = athletesService.getAllAthletes();
        assertThat(actualAthletes.size()).isEqualTo(expectedAthletes.size());
        for (int i = 0; i < expectedAthletes.size(); i++) {
            assertThatAthleteAreEqual(actualAthletes.get(i), expectedAthletes.get(i));
        }
    }

    @Test
    public void test_create() {
        AthleteDto expectedAthlete = new AthleteDto(1L,
                "Nick", "Ilieskou", new Date(), new Date(), "abc@gmail.com", "123");
        doReturn(this.expectedAthlete).when(athletesRepository).saveAndFlush(any());

        AthleteDto actualAthlete = athletesService.create(expectedAthlete);
        assertThat(actualAthlete.getFirstName()).isEqualTo(expectedAthlete.getFirstName());
        assertThat(actualAthlete.getLastName()).isEqualTo(expectedAthlete.getLastName());
        assertThat(actualAthlete.getEmail()).isEqualTo(expectedAthlete.getEmail());
        assertThat(actualAthlete.getPhoneNumber()).isEqualTo(expectedAthlete.getPhoneNumber());
        assertThat(actualAthlete.getId()).isEqualTo(expectedAthlete.getId());
        assertThat(actualAthlete.getDateOfBirth()).isEqualTo(expectedAthlete.getDateOfBirth());
        assertThat(actualAthlete.getEnrolledDate()).isEqualTo(expectedAthlete.getEnrolledDate());

    }

    private void assertThatAthleteAreEqual(AthleteDto actualAthlete, Athlete expectedAthlete) {
        assertThat(actualAthlete.getFirstName()).isEqualTo(expectedAthlete.getFirstName());
        assertThat(actualAthlete.getLastName()).isEqualTo(expectedAthlete.getLastName());
        assertThat(actualAthlete.getEmail()).isEqualTo(expectedAthlete.getEmail());
        assertThat(actualAthlete.getPhoneNumber()).isEqualTo(expectedAthlete.getPhoneNumber());
        assertThat(actualAthlete.getId()).isEqualTo(expectedAthlete.getId());
        assertThat(actualAthlete.getDateOfBirth()).isEqualTo(expectedAthlete.getDateOfBirth());
        assertThat(actualAthlete.getEnrolledDate()).isEqualTo(expectedAthlete.getEnrolledDate());
    }
}
