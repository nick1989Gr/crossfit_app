DROP TABLE IF EXISTS crossfit_class_info CASCADE;
CREATE TABLE crossfit_class_info
(
    id          SERIAL PRIMARY KEY,
    class_name  varchar(3000) NOT NULL,
    description varchar(3000) NOT NULL
);

INSERT INTO crossfit_class_info (id, class_name, description)
VALUES (1, 'wod', ' the mysterious acronym "WOD" (pronounced like a "wad" of biceps muscle you developed doing CrossFit) is short for something. Ages broke down some of the most popular CrossFit-related acronyms, which contribute to the perception that CrossFitters really do speak a different language:
WOD = Workout of the Day. This is the workout you ll get when you attend a CrossFit class.
AMRAP = As Many Rounds as Possible. Generally, the clock is set to a time cap and you want to complete as many rounds as possible of the workout before time runs out.
ATG = Ass to Grass. A term used for a full-depth squat.
RX = As Prescribed. Each WOD will have prescribed weights for you to use during the workout, but this is a suggestion. If you need to scale the workout to include lighter weights, do so.
EMOM = Every Minute on the Minute. One exercise or a series of exercises is performed for a set number of reps each minute. The time remaining in that minute is rest. Once the minute is up, you begin again.'),
       (2, 'weight lifting',
        'Olympic weightlifting, or Olympic-style weightlifting, often simply referred to as weightlifting, is a sport in which the athlete attempts a maximum-weight single lift of a barbell loaded with weight plates. The two competition lifts in order are the snatch and the clean and jerk. The snatch is a wide-grip, one-move lift. The clean and jerk is a close-grip, two-move lift. Each weightlifter receives three attempts in each, and the combined total of the highest two successful lifts determines the overall result within a bodyweight category. Bodyweight categories are different for male and female competitors and change over time. A lifter who fails to complete at least one successful snatch and one successful clean and jerk also fails to total, and therefore receives an "incomplete" entry for the competition. The clean and press was once a competition lift, but was discontinued due to difficulties in judging proper form.'),
       (3, 'calisthenics', 'Calisthenics (American English) or Callisthenics (British English)  is a form of strength training consisting of a variety of movements that exercise large muscle groups (gross motor movements), such as running, standing, grasping, pushing, etc. These exercises are often performed rhythmically and with minimal equipment, as bodyweight exercises. They are intended to increase strength, fitness, and flexibility, through movements such as pulling, pushing, bending, jumping, or swinging, using one''s body weight for resistance. Calisthenics can provide the benefits of muscular and aerobic conditioning, in addition to improving psychomotor skills such as balance, agility, and coordination.
Urban calisthenics is a form of street workout; calisthenics groups perform exercise routines in urban areas. Individuals and groups train to perform advanced calisthenics skills such as muscle-ups, levers, and various freestyle moves such as spins and flips.
Sports teams and military units often perform leader-directed group calisthenics as a form of synchronized physical training (often including a customized "call and response" routine) to increase group cohesion and discipline. Calisthenics is also popular as a component of physical education in primary and secondary schools over much of the globe.');
