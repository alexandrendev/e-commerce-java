CREATE TABLE cart(
    id SERIAL PRIMARY KEY,
    customer_id INT,

    FOREIGN KEY(customer_id) REFERENCES customer(id)
);