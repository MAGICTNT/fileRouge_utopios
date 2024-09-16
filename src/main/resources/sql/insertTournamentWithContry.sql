-- Insérer un tournoi en France
INSERT INTO tournament (label_tournament, format, start_date, end_date, status,  minimum_age, elo)
VALUES ('French Championship', 'Single Elimination', '2024-10-01', '2024-10-10', 'Scheduled',  18, 1500);

-- Insérer un tournoi en Belgique
INSERT INTO tournament (label_tournament, format, start_date, end_date, status,  minimum_age, elo)
VALUES ('Russe Open', 'Double Elimination', '2024-11-05', '2024-11-15', 'Scheduled',  16, 1200);

-- Associer les tournois avec leurs pays respectifs (France et Belgique)
INSERT INTO host (id_tournament, id_country)
VALUES (1, (SELECT id_country FROM country WHERE label_country = 'Français')),
       (2, (SELECT id_country FROM country WHERE label_country = 'Russe'));
