package com.ilieskou.crossfitbackend.services;

import com.ilieskou.crossfitbackend.controllers.dto.AthleteDto;
import com.ilieskou.crossfitbackend.models.Athlete;
import com.ilieskou.crossfitbackend.repositories.AthletesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class AthletesServiceTest {

    @MockBean
    private AthletesRepository athletesRepository;

    private AthletesService athletesService;
    private Athlete expectedAthlete;
    private AthleteDto expectedAthleteDto;
    private List<Athlete> expectedAthletes;

    @Before
    public void setup() {
        this.athletesService = new AthletesService(new ModelMapper(), athletesRepository);
        Date enrolledDate = new Date();
        Date dateofBirth = new Date();
        this.expectedAthleteDto = new AthleteDto(1L,
                "Nick", "Ilieskou", dateofBirth, enrolledDate, "abc@gmail.com", "123");

        this.expectedAthlete = new Athlete(1L,
                "Nick", "Ilieskou", dateofBirth, enrolledDate, "abc@gmail.com", "123");
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
        doReturn(this.expectedAthlete).when(athletesRepository).saveAndFlush(any());

        AthleteDto actualAthlete = athletesService.create(expectedAthleteDto);

        assertThat(actualAthlete.getFirstName()).isEqualTo(expectedAthleteDto.getFirstName());
        assertThat(actualAthlete.getLastName()).isEqualTo(expectedAthleteDto.getLastName());
        assertThat(actualAthlete.getEmail()).isEqualTo(expectedAthleteDto.getEmail());
        assertThat(actualAthlete.getPhoneNumber()).isEqualTo(expectedAthleteDto.getPhoneNumber());
        assertThat(actualAthlete.getId()).isEqualTo(expectedAthleteDto.getId());
        assertThat(actualAthlete.getDateOfBirth()).isEqualTo(expectedAthleteDto.getDateOfBirth());
        assertThat(actualAthlete.getEnrolledDate()).isEqualTo(expectedAthleteDto.getEnrolledDate());

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
