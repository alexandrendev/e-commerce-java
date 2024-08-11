CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    order_date TIMESTAMP DEFAULT NOW(),
    status TEXT,
    total_amount DECIMAL(10,2),
    payment_method TEXT,
    delivery_address_id INT,
    FOREIGN KEY (delivery_address_id) REFERENCES address(id)
);

CREATE TABLE order_items (
    id SERIAL PRIMARY KEY ,
    order_id BIGINT,
    product_id INT,

    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);