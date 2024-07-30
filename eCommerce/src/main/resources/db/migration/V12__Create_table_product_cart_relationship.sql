CREATE TABLE product_cart(
    id SERIAL PRIMARY KEY ,
    cart_id INT,
    product_id INT,

    FOREIGN KEY(cart_id) REFERENCES cart(id),
    FOREIGN KEY(product_id) REFERENCES product(id)
);