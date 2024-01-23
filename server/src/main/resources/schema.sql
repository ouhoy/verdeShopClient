-- schema.sql

CREATE TABLE IF NOT EXISTS Product
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    price       INT,
    description TEXT,
    highlights  VARCHAR(2000) ARRAY,
    details     TEXT,
    thumbnail   TEXT,
    image_src   VARCHAR(2000) ARRAY,
    image_alt   VARCHAR(255),
    colors      VARCHAR(255) ARRAY,
    gender      VARCHAR(255),
    sizes       VARCHAR(255) ARRAY,
    options     VARCHAR(255) ARRAY,
    type        VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Users
(

    id        INT PRIMARY KEY AUTO_INCREMENT,
    firstname TEXT,
    lastname  TEXT,
    email     TEXT UNIQUE,
    userType  TEXT

);

CREATE TABLE IF NOT EXISTS Orders
(

    id       INT PRIMARY KEY AUTO_INCREMENT,
    products  JSON,
    address JSON,
    date     TEXT,
    total INT,
    user_id  INT,
    FOREIGN KEY (user_id) REFERENCES Users (id)

);

INSERT INTO Users (firstname, lastname, email, userType)
VALUES ('Abdallah', 'Dahmou', 'abdallah.dahmou@programhaven.com', 'SHOPPER');

