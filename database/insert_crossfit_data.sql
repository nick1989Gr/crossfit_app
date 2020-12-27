INSERT INTO athletes (athlete_id, first_name, last_name, date_of_birth, enrolled_date, email, phone_number)
VALUES (1,'Nick', 'Ilieskou', '1989-04-11','2019-01-01', 'nickilieskou@gmail.com','+31622855789'),
       (2,'John', 'Black', '1988-04-11','2020-01-01', 'johnBlack@gmail.com','+31622855789'),
       (3,'Jim', 'White', '1986-04-11','2015-01-01', 'jimwhite@gmail.com','+31622855789'),
       (4,'Aad', 'Stones', '1989-04-11','2014-01-01', 'AadStones@gmail.com','+31622855789'),
       (5,'Rick', 'Kerling', '1989-04-11','2016-01-01', 'rickkerling@gmail.com','+31622855789'),
       (6,'Peter', 'Pits', '1991-04-11','2018-01-01', 'peterpits@gmail.com','+31622855789'),
       (7,'James', 'Maden', '1992-04-11','2014-01-01', 'jamesmaden@gmail.com','+31622855789'),
       (8,'Nick', 'Gerald', '1989-04-11','2017-01-01', 'nickgerald@gmail.com','+31622855789'),
       (9,'Pit', 'Pittren', '1984-04-11','2017-01-01', 'pitpittren@gmail.com','+31622855789');
       
INSERT INTO crossfit_classes (crossfit_class_id, crossfit_class_type, crossfit_class_ts, crossfit_class_duration, max_participants)
VALUES (1, 'wod',           '2021-01-01 15:00:00+01', 60, 5),
       (2, 'wod',           '2021-01-01 16:00:00+01', 60, 5),
       (3, 'wod',           '2021-01-01 17:00:00+01', 60, 5),
       (4, 'wod',           '2021-01-01 18:00:00+01', 60, 5),
       (5, 'wod',           '2021-01-01 19:00:00+01', 60, 5),
       (6, 'wod',           '2021-01-02 15:00:00+01', 60, 5),
       (7, 'wod',           '2021-01-02 16:00:00+01', 60, 5),
       (8, 'wod',           '2021-01-02 17:00:00+01', 60, 5),
       (9, 'wod',           '2021-01-02 18:00:00+01', 60, 5),
       (10,'weight lifting','2021-01-01 15:00:00+01', 60, 5),
       (11,'weight lifting','2021-01-01 16:00:00+01', 60, 5),
       (12,'weight lifting','2021-01-01 17:00:00+01', 60, 5),
       (13,'weight lifting','2021-01-01 18:00:00+01', 60, 5),
       (14,'weight lifting','2021-01-01 19:00:00+01', 60, 5),
       (15,'weight lifting','2021-01-02 15:00:00+01', 60, 5),
       (16,'weight lifting','2021-01-02 16:00:00+01', 60, 5),
       (17,'weight lifting','2021-01-02 17:00:00+01', 60, 5),
       (18,'weight lifting','2021-01-02 18:00:00+01', 60, 5),
       (19,'calisthenics',  '2021-01-01 15:00:00+01', 90, 8),
       (20,'calisthenics',  '2021-01-01 16:00:00+01', 90, 8),
       (21,'calisthenics',  '2021-01-01 17:00:00+01', 90, 8),
       (22,'calisthenics',  '2021-01-01 18:00:00+01', 90, 8),
       (23,'calisthenics',  '2021-01-01 19:00:00+01', 90, 8),
       (24,'calisthenics',  '2021-01-02 15:00:00+01', 90, 8),
       (25,'calisthenics',  '2021-01-02 16:00:00+01', 90, 8),
       (26,'calisthenics',  '2021-01-02 17:00:00+01', 90, 8),
       (27,'calisthenics',  '2021-01-02 18:00:00+01', 90, 8);
       

       
INSERT INTO instructors (instructor_id, first_name, last_name, date_of_birth, email, phone_number, bio)
VALUES (1,'Tim', 'Madeson', '1989-04-11', 'timmadeson@gmail.com','+31622855789','Crossfit Trainer with specialization in WODs'),
       (2,'John', 'Feras', '1988-04-11', 'johnferas@gmail.com','+31622855789','Crossfit Trainer with specialization in Wegiht lifting'),
       (3,'Camel', 'Black', '1986-04-11', 'camelblack@gmail.com','+31622855789','Crossfit Trainer with specialization in Calisthenics'),
       (4,'John', 'Lifter', '1989-04-11', 'johnlifter@gmail.com','+31622856569','Assistant Crossfit trainer'),
       (5,'Kate', 'Cinderela', '1989-04-11', 'katecinderela@gmail.com','+31622856111','Junior Assistant Crossfit trainer');
       

INSERT INTO crossfit_classes_instructors (crossfit_class_id, instructor_id)
VALUES (1,1),
       (2,1),
       (3,1),
       (4,1),
       (5,1),
       (6,1),
       (7,1),
       (8,1),
       (9,1),
       (10,2),
       (11,2),
       (12,2),
       (13,2),
       (14,2),
       (15,2),
       (16,2),
       (17,2),
       (18,2),
       (19,3),
       (20,3),
       (21,3),
       (22,3),
       (23,3),
       (24,3),
       (25,3),
       (26,3),
       (27,3);
       
INSERT INTO crossfit_classes_athletes (crossfit_class_id, athlete_id)
VALUES (1,1),
       (1,2),
       (1,3),
       (1,4),
       (2,1),
       (2,2),
       (2,3),
       (2,4),
       (2,5);
       