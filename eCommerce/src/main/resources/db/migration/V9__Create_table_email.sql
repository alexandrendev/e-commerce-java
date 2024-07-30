CREATE TABLE email(
    id SERIAL PRIMARY KEY ,
    email VARCHAR(100),
    customer_id INT,

    FOREIGN KEY(customer_id) REFERENCES customer(id)
);