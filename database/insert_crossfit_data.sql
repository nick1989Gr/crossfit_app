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
       
INSERT INTO crossfit_classes (crossfit_class_id, crossfit_class_type, crossfit_class_day, crossfit_class_time, crossfit_class_duration, max_participants)
VALUES (1, 'wod','Tuesday',  '15:00:00', 60, 5),
       (2, 'wod','Thursday', '15:00:00', 60, 5),
       (3, 'wod','Saturday', '15:00:00', 60, 5),
       (4, 'wod','Tuesday',  '16:00:00', 60, 5),
       (5, 'wod','Thursday', '16:00:00', 60, 5),
       (6, 'wod','Saturday', '16:00:00', 60, 5),
       (7, 'wod','Tuesday',  '17:00:00', 60, 5),
       (8, 'wod','Thursday', '17:00:00', 60, 5),
       (9, 'wod','Saturday', '17:00:00', 60, 5),
       (10,'weight lifting','Monday' , '18:00:00',60,5),
       (11,'weight lifting','Tuesday', '18:00:00',60,5),
       (12,'weight lifting','Wednesday', '18:00:00',60,5),
       (13,'weight lifting','Monday', '19:00:00',60,5),
       (14,'weight lifting','Tuesday', '19:00:00',60,5),
       (15,'weight lifting','Wednesday', '19:00:00',60,5),
       (16,'weight lifting','Monday', '20:00:00',60,5),
       (17,'weight lifting','Tuesday', '20:00:00',60,5),
       (18,'weight lifting','Wednesday', '20:00:00',60,5),
       (19,'calisthenics','Monday', '10:00:00', 90, 8),
       (20,'calisthenics','Wednesday', '10:00:00', 90, 8),
       (21,'calisthenics','Friday', '10:00:00', 90, 8),
       (22,'calisthenics','Monday', '12:00:00', 90, 8),
       (23,'calisthenics','Wednesday', '12:00:00', 90, 8),
       (24,'calisthenics','Friday', '12:00:00', 90, 8),
       (25,'calisthenics','Monday', '14:00:00', 90, 8),
       (26,'calisthenics','Wednesday', '14:00:00', 90, 8),
       (27,'calisthenics','Friday', '14:00:00', 90, 8);
       
       
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
       
INSERT INTO crossfit_class_registration (crossfit_class_registration_id, crossfit_class_id, athlete_id, crossfit_class_instance_ts)
VALUES (1,1,1,'2016-12-22 15:00:00+01'),
       (2,1,2,'2016-12-22 15:00:00+01'),
       (3,1,3,'2016-12-22 15:00:00+01'),
       (4,1,4,'2016-12-22 15:00:00+01'),
       (5,1,5,'2017-12-22 13:00:00+01'),
       (6,2,1,'2018-12-22 12:00:00+01'),
       (7,3,1,'2019-12-22 16:00:00+01');
       