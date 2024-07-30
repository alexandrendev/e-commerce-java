CREATE TABLE warehouse(
    id SERIAL PRIMARY KEY,
    city_id INT,

    FOREIGN KEY(city_id) REFERENCES city(id)
);

