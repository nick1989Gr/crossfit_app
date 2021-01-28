package com.ilieskou.crossfitbackend.services;

import com.ilieskou.crossfitbackend.controllers.dto.AchievementsLogDto;
import com.ilieskou.crossfitbackend.controllers.dto.ExerciseRecordDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class AchievementsService {

    public ExerciseRecordDto getTopAchievements(Long exerciseId) {
        List<String> athletes = Arrays.asList("Ilieskou", "Black", "White", "Stones", "Kerling", "Pits", "Maden", "Gerald", "Pittren");
        List<Double> snatchAchievements = Arrays.asList(102.0, 103.5, 95.5, 85.0, 120.5, 115.0, 116.0, 130.0, 90.0, 96.5);
        List<Double> cleanAchievements = Arrays.asList(120.0, 185.0, 196.0, 145.0, 136.0, 120.0, 152.0, 97.0, 123.0, 115.0);
        List<Double> burpeesAchievements = Arrays.asList(90.0, 85.0, 96.0, 105.0, 106.0, 120.0, 85.0, 97.0, 109.0, 115.0);
        ExerciseRecordDto snatchRecord = new ExerciseRecordDto("Snatch", "Kg", athletes, snatchAchievements);
        ExerciseRecordDto cleanRecord = new ExerciseRecordDto("Clean", "Kg", athletes, cleanAchievements);
        ExerciseRecordDto burpeesRecord = new ExerciseRecordDto("Burpees", "burpees/min", athletes, burpeesAchievements);
        if (exerciseId == 1) return snatchRecord;
        if (exerciseId == 2) return cleanRecord;
        if (exerciseId == 3) return burpeesRecord;
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No records available for exercise id " + exerciseId);
    }

    //
    public List<AchievementsLogDto> getTopAchievementsForAthlete(Long athleteId) {

        AchievementsLogDto snatch = new AchievementsLogDto("Snatch", getDates(),
                Arrays.asList(100.0, 101.0, 110.0, 90.0, 115.0, 120.0, 122.5, 125.0, 130.0, 114.0, 97.0, 100.0, 98.0)
        );
        AchievementsLogDto clean = new AchievementsLogDto("Clean", getDates(),
                Arrays.asList(156.0, 175.0, 185.0, 184.0, 186.0, 189.0, 188.0, 130.0, 152.0, 126.0, 167.0, 100.0, 98.0)
        );
        AchievementsLogDto burpees = new AchievementsLogDto("Burpees", getDates(),
                Arrays.asList(56.0, 75.0, 85.0, 84.0, 86.0, 89.0, 88.0, 90.0, 92.0, 96.0, 97.0, 100.0, 98.0)
        );
        return Arrays.asList(snatch, clean, burpees);
    }

    private List<Date> getDates() {
        List<Date> result = new ArrayList<>();
        String[] sDates = {
                "1/1/2021",
                "2/1/2021",
                "3/1/2021",
                "4/1/2021",
                "5/1/2021",
                "8/1/2021",
                "10/1/2021",
                "12/1/2021",
                "18/1/2021",
                "19/1/2021",
                "20/1/2021",
                "21/1/2021",
                "28/1/2021",
        };
        try {
            for (String s : sDates) {
                result.add(new SimpleDateFormat("dd/MM/yyyy").parse(s));
            }
        } catch (ParseException e) {
        }
        return result;
    }
}

    
