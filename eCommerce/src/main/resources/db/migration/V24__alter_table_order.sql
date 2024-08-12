ALTER TABLE orders
    ADD COLUMN customer_id INT;

ALTER TABLE orders
    ADD CONSTRAINT fk_customer
    FOREIGN KEY (customer_id) REFERENCES users(id);