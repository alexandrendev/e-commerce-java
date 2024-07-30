CREATE TABLE image(
    id SERIAL PRIMARY KEY ,
    url VARCHAR(255),
    product_id INT,

    FOREIGN KEY(product_id) REFERENCES product(id)
);