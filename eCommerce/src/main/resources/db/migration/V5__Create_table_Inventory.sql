CREATE TABLE inventory(
    id SERIAL PRIMARY KEY,
    product_id INT,
    warehouse_id INT,
    quantity INT,

    FOREIGN KEY(product_id) REFERENCES product(id),
    FOREIGN KEY(warehouse_id) REFERENCES warehouse(id)
);