# Projet fil rouge Utopios

## Mise en place

- Ajouter un fichier `application.properties` dans le dossier `src/main/resources/`
- Le fichier `application.properties` doit contenir les informations suivantes :

    ```properties
    spring.application.name=fileRouge_utopios
    spring.datasource.url=jdbc:postgresql://localhost:5432/${Nom de la base de données}
    spring.datasource.username=${Nom de l'utilisateur de la base de données}
    spring.datasource.password=${Mot de passe}
    spring.jpa.hibernate.ddl-auto=update
    ```

- Sans oublier de spécifier le nom de la base de données, l'utilisateur et le mot de passe
