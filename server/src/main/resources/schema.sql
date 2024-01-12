CREATE TABLE IF NOT EXISTS Product  (
                          id          INT PRIMARY KEY AUTO_INCREMENT,
                          name        VARCHAR(255) NOT NULL,
                          price       INT,
                          description TEXT,
                          highlights  VARCHAR(2000) ARRAY,
                          details     TEXT,
                          image_src   VARCHAR(2000) ARRAY,
                          image_alt   VARCHAR(255),
                          colors      VARCHAR(255) ARRAY,
                          gender      VARCHAR(255),
                          sizes       VARCHAR(255) ARRAY,
                          options     VARCHAR(255) ARRAY,
                          type        VARCHAR(255)
);


