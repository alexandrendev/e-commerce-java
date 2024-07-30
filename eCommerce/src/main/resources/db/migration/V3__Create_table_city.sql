CREATE TABLE city(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    state_id INT,

    FOREIGN KEY(state_id) REFERENCES state(id)
);