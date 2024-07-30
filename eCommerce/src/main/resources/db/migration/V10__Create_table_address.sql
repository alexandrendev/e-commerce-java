CREATE TABLE address(
    id SERIAL PRIMARY KEY ,
    street VARCHAR(100),
    number VARCHAR(10),
    complement VARCHAR(150),
    city_id INT,
    customer_id INT,

    FOREIGN KEY(city_id) REFERENCES city(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id)
);