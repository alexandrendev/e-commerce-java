CREATE TABLE admin(
    name VARCHAR(100),
    credentials_id INT,
    created_ad TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY(credentials_id) REFERENCES credentials(id)
);