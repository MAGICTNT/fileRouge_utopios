-- Ajouter la colonne id_users à la table message
ALTER TABLE message
    ADD COLUMN id_users INTEGER NOT NULL;

-- Ajouter une clé étrangère vers la table users
ALTER TABLE message
    ADD CONSTRAINT fk_message_users
        FOREIGN KEY (id_users) REFERENCES users(id_users);