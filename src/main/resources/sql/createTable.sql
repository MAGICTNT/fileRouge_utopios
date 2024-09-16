CREATE TABLE tournament (
                            id_tournament SERIAL,
                            label_tournament VARCHAR(255) NOT NULL,
                            format VARCHAR(50) NOT NULL,
                            start_date DATE NOT NULL,
                            end_date VARCHAR(50) NOT NULL,
                            status VARCHAR(15) NOT NULL,
                            level VARCHAR(50),
                            minimum_age INTEGER,
                            elo INTEGER,
                            PRIMARY KEY (id_tournament)
);


CREATE TABLE message (
                         id_message SERIAL,
                         label_message VARCHAR(50) NOT NULL,
                         content VARCHAR(255) NOT NULL,
                         id_tournament INTEGER NOT NULL,
                         PRIMARY KEY (id_message),
                         FOREIGN KEY (id_tournament) REFERENCES tournament(id_tournament)
);

CREATE TABLE game (
                       id_game SERIAL,
                       game_date TIMESTAMP NOT NULL,
                       status VARCHAR(15) NOT NULL,
                       result INTEGER NOT NULL,
                       id_tournament INTEGER NOT NULL,
                       PRIMARY KEY (id_game),
                       FOREIGN KEY (id_tournament) REFERENCES tournament(id_tournament)
);

CREATE TABLE country (
                         id_country SERIAL,
                         label_country VARCHAR(255) NOT NULL,
                         tag_country VARCHAR(50),
                         PRIMARY KEY (id_country)
);

CREATE TABLE users (
                      id_users SERIAL,
                      login VARCHAR(50) NOT NULL,
                      birth_date DATE NOT NULL,
                      registration_date DATE NOT NULL,
                      password VARCHAR(50) NOT NULL,
                      elo INTEGER NOT NULL,
                      role VARCHAR(50) NOT NULL,
                      id_country INTEGER NOT NULL,
                      PRIMARY KEY (id_users),
                      UNIQUE (login),
                      FOREIGN KEY (id_country) REFERENCES country(id_country)
);

CREATE TABLE comment (
                         id_comment SERIAL,
                         text_content VARCHAR(255) NOT NULL,
                         id_users INTEGER NOT NULL,
                         id_message INTEGER NOT NULL,
                         PRIMARY KEY (id_comment),
                         FOREIGN KEY (id_users) REFERENCES users(id_users),
                         FOREIGN KEY (id_message) REFERENCES message(id_message)
);

CREATE TABLE participate (
                             id_users INTEGER,
                             id_tournament INTEGER,
                             PRIMARY KEY (id_users, id_tournament),
                             FOREIGN KEY (id_users) REFERENCES users(id_users),
                             FOREIGN KEY (id_tournament) REFERENCES tournament(id_tournament)
);

CREATE TABLE discuss (
                         id_users INTEGER,
                         id_game INTEGER,
                         PRIMARY KEY (id_users, id_game),
                         FOREIGN KEY (id_users) REFERENCES users(id_users),
                         FOREIGN KEY (id_game) REFERENCES game(id_game)
);

CREATE TABLE host (
                      id_tournament INTEGER,
                      id_country INTEGER,
                      PRIMARY KEY (id_tournament, id_country),
                      FOREIGN KEY (id_tournament) REFERENCES tournament(id_tournament),
                      FOREIGN KEY (id_country) REFERENCES country(id_country)
);

-- Renommer la colonne login en pseudo
ALTER TABLE users
    RENAME COLUMN login TO pseudo;

-- Ajouter la colonne mail
ALTER TABLE users
    ADD COLUMN mail VARCHAR(255) NOT NULL;