-- Drop table user_to_tournament
DROP TABLE IF EXISTS user_to_tournament;

-- Drop table comment
DROP TABLE IF EXISTS comment;

-- Drop table result
DROP TABLE IF EXISTS result;

-- Drop table game
DROP TABLE IF EXISTS game;

-- Drop table message
DROP TABLE IF EXISTS message;

-- Drop table tournament
DROP TABLE IF EXISTS tournament;

-- Drop table user_account
DROP TABLE IF EXISTS user_account;

-- Drop table country
DROP TABLE IF EXISTS country;

--------------------------------------------------

-- Create table country
create table country
(
    id    bigserial
        primary key,
    label varchar(255) not null
        constraint uk_nqlhjw63lbdup0favvgjsnlxb
            unique,
    tag   varchar(255) not null
        constraint uk_8q0y5ylo5eamqbhgqs5qajsm3
            unique
);
-- Create table user_account
create table user_account
(
    id                bigserial
        primary key,
    account_level     varchar(255) not null
        constraint user_account_account_level_check
            check ((account_level)::text = ANY
                   ((ARRAY ['ADMIN'::character varying, 'USER'::character varying])::text[])),
    birth_date        date         not null,
    elo               integer      not null,
    email             varchar(255) not null
        constraint uk_hl02wv5hym99ys465woijmfib
            unique,
    password          varchar(255) not null,
    pseudo            varchar(255) not null
        constraint uk_l3mjqdikxbc1m88nhv0siupnh
            unique,
    registration_date date         not null,
    country_id        bigint       not null
        constraint fkjw2udettleqn650i6baq1ip42
            references country
);
-- Create table tournament
create table tournament
(
    id         bigserial
        primary key,
    end_date   date         not null,
    format     varchar(255) not null
        constraint tournament_format_check
            check ((format)::text = ANY ((ARRAY ['SINGLE'::character varying, 'DOUBLE'::character varying])::text[])),
    label      varchar(255) not null,
    min_age    integer      not null,
    min_elo    integer      not null,
    start_date date         not null,
    status     varchar(255) not null
        constraint tournament_status_check
            check ((status)::text = ANY
                   ((ARRAY ['NOT_STARTED'::character varying, 'IN_PROGRESS'::character varying, 'FINISHED'::character varying])::text[]))
);
-- Create table message
create table message
(
    id            bigserial
        primary key,
    content       varchar(2500) not null,
    label         varchar(255)  not null,
    tournament_id bigint        not null
        constraint fkks9jato39gr7eqx3l6a3lremn
            references tournament,
    user_id       bigint        not null
        constraint fkjlulh83p0xvnkj2hpi7eq3in1
            references user_account
);
-- Create table game
create table game
(
    id            bigserial
        primary key,
    match_date    date         not null,
    status        varchar(255) not null
        constraint game_status_check
            check ((status)::text = ANY
                   ((ARRAY ['NOT_STARTED'::character varying, 'IN_PROGRESS'::character varying, 'FINISHED'::character varying])::text[])),
    tournament_id bigint       not null
        constraint fk2xfdbv4n193efuyajlqh0vs6j
            references tournament
);
-- Create table result
create table result
(
    game_id bigint  not null
        constraint fkekbtbib5q84jum7wrse4euumh
            references game,
    user_id bigint  not null
        constraint fk9msc4h42pspkdvuv65f3ikqsh
            references user_account,
    result  integer not null,
    primary key (game_id, user_id)
);
-- Create table comment
create table comment
(
    id            bigserial
        primary key,
    content       varchar(2500) not null,
    label         varchar(255)  not null,
    tournament_id bigint        not null
        constraint fkfuu7b728vxdnkpar54r9xvai1
            references tournament,
    user_id       bigint        not null
        constraint fk3y3uou7na66pfn512byon549s
            references user_account,
    message_id    bigint        not null
        constraint fkatlrxw2dnvma9h401t2ql2ri8
            references message
);
-- Create table user_to_tournament
create table user_to_tournament
(
    user_id       bigint not null
        constraint fkiw96bstmodoh85272y8e9ns2v
            references user_account,
    tournament_id bigint not null
        constraint fk7mttikp6wgjumq9j53d7xq5n3
            references tournament
);

--------------------------------------------------

-- Insert into country
INSERT INTO country (label, tag)
VALUES ('USA', 'US'),
       ('Canada', 'CA'),
       ('Germany', 'DE');

-- Insert into user_account
INSERT INTO user_account (account_level, birth_date, elo, email, password, pseudo, registration_date, country_id)
VALUES ('USER', '1990-01-01', 1500, 'john.doe@example.com', 'password123', 'johnny', '2024-01-01', 1),
       ('USER', '1985-05-15', 1600, 'jane.smith@example.com', 'password456', 'janey', '2024-02-01', 2),
       ('ADMIN', '1980-07-20', 1700, 'admin@example.com', 'admin', 'admin', '2024-03-01', 3);

-- Insert into tournament
INSERT INTO tournament (end_date, format, label, min_age, min_elo, start_date, status)
VALUES ('2024-06-30', 'SINGLE', 'Summer Tournament', 18, 1400, '2024-06-01', 'NOT_STARTED'),
       ('2024-12-31', 'DOUBLE', 'Winter Tournament', 21, 1500, '2024-12-01', 'IN_PROGRESS');

-- Insert into message
INSERT INTO message (content, label, tournament_id, user_id)
VALUES ('Welcome to the Summer Tournament!', 'Announcement', 1, 1),
       ('Don''t forget to register for the Winter Tournament!', 'Reminder', 2, 2);

-- Insert into game
INSERT INTO game (match_date, status, tournament_id)
VALUES ('2024-06-15', 'NOT_STARTED', 1),
       ('2024-12-15', 'IN_PROGRESS', 2);

-- Insert into result
INSERT INTO result (game_id, user_id, result)
VALUES (1, 1, 3),
       (2, 2, 1);

-- Insert into comment
INSERT INTO comment (content, label, tournament_id, user_id, message_id)
VALUES ('Great match, very exciting!', 'Feedback', 1, 1, 1),
       ('Looking forward to the next tournament!', 'Suggestion', 2, 2, 2);

-- Insert into user_to_tournament
INSERT INTO user_to_tournament (user_id, tournament_id)
VALUES (1, 1),
       (2, 2);
