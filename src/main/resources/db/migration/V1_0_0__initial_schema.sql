DROP TABLE IF EXISTS athletes CASCADE;
CREATE TABLE athletes
(
    athlete_id    SERIAL PRIMARY KEY,
    first_name    varchar(30) NOT NULL,
    last_name     varchar(30) NOT NULL,
    date_of_birth date        NOT NULL,
    enrolled_date date        NOT NULL,
    email         varchar(80) NOT NULL,
    phone_number  varchar(20) NULL
);

DROP TABLE IF EXISTS crossfit_classes CASCADE;
CREATE TABLE crossfit_classes
(
    crossfit_class_id       SERIAL PRIMARY KEY,
    crossfit_class_type     varchar(30) NOT NULL,
    crossfit_class_ts       TIMESTAMPTZ NOT NULL,
    crossfit_class_duration integer     NOT NULL,
    max_participants        integer     NOT NULL
);


DROP TABLE IF EXISTS crossfit_classes_athletes CASCADE;
CREATE TABLE crossfit_classes_athletes
(
    crossfit_class_id integer NOT NULL REFERENCES crossfit_classes (crossfit_class_id),
    athlete_id        integer NOT NULL REFERENCES athletes (athlete_id)
);

DROP TABLE IF EXISTS instructors CASCADE;
CREATE TABLE instructors
(
    instructor_id SERIAL PRIMARY KEY,
    first_name    varchar(30)  NOT NULL,
    last_name     varchar(30)  NOT NULL,
    date_of_birth date         NOT NULL,
    email         varchar(80)  NOT NULL,
    phone_number  varchar(20)  NULL,
    bio           varchar(100) NOT NULL
);

DROP TABLE IF EXISTS crossfit_classes_instructors CASCADE;
CREATE TABLE crossfit_classes_instructors
(
    crossfit_class_id integer NOT NULL REFERENCES crossfit_classes (crossfit_class_id),
    instructor_id     integer NOT NULL REFERENCES instructors (instructor_id)
);


