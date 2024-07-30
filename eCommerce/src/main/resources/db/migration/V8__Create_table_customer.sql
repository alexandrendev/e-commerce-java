CREATE TABLE customer(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    credentials_id INT,

    FOREIGN KEY (credentials_id) REFERENCES credentials(id)

);