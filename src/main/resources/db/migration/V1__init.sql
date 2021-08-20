CREATE TABLE filter
(
    id      BIGSERIAl PRIMARY KEY,
    filter_name   TEXT UNIQUE
);
INSERT INTO filter VALUES (1, 'FILTER 1');
INSERT INTO filter VALUES (2, 'FILTER 2');
SELECT setval('filter_id_seq', 10, true);

CREATE TABLE criteria
(
    id      BIGSERIAL PRIMARY KEY,
    criteria_name TEXT UNIQUE
);
INSERT INTO criteria VALUES (1, 'AMOUNT');
INSERT INTO criteria VALUES (2, 'TITLE');
INSERT INTO criteria VALUES (3, 'DATE');

CREATE TABLE type
(
    id BIGSERIAL PRIMARY KEY,
    type_name TEXT UNIQUE,
    criteria_id BIGSERIAl REFERENCES criteria
);
INSERT INTO type VALUES (1, 'LESS', 1);
INSERT INTO type VALUES (2, 'MORE', 1);
INSERT INTO type VALUES (3, 'CONTAINS', 2);
INSERT INTO type VALUES (4, 'START WITH', 2);
INSERT INTO type VALUES (5, 'TO', 3);
INSERT INTO type VALUES (6, 'BEFORE', 3);

CREATE TABLE amount
(
    id BIGSERIAL PRIMARY KEY,
    filter_id   BIGSERIAl REFERENCES filter,
    criteria_id BIGSERIAL REFERENCES criteria,
    type_id BIGSERIAl REFERENCES type,
    number BIGSERIAL
);
INSERT INTO amount VALUES (1, 1, 1, 1, 999);
INSERT INTO amount VALUES (2, 2, 1, 2, 100);
INSERT INTO amount VALUES (3, 1, 1, 2, 100);
SELECT setval('amount_id_seq', 10, true);

CREATE TABLE title
(
    id BIGSERIAL PRIMARY KEY,
    filter_id   BIGSERIAl REFERENCES filter,
    criteria_id BIGSERIAL REFERENCES criteria,
    type_id BIGSERIAl REFERENCES type,
    text TEXT

);
INSERT INTO title VALUES (1, 1, 2, 3, 'MEOW');
INSERT INTO title VALUES (2, 2, 2, 4, 'TEXT');
SELECT setval('title_id_seq', 10, true);

CREATE TABLE date
(
    id BIGSERIAL PRIMARY KEY,
    filter_id   BIGSERIAl REFERENCES filter,
    criteria_id BIGSERIAL REFERENCES criteria,
    type_id BIGSERIAl REFERENCES type,
    date TIMESTAMP
);
INSERT INTO date VALUES (1, 1, 3, 5, '2021-08-10');
INSERT INTO date VALUES (2, 2, 3, 6, '2021-08-09');
SELECT setval('date_id_seq', 10, true);


