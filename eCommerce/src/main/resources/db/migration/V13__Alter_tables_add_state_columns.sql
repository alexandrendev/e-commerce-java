ALTER TABLE product
    ADD COLUMN created_ad TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP;

ALTER TABLE customer
    ADD COLUMN created_ad TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP;

ALTER TABLE email
    ADD COLUMN created_ad TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP;

ALTER TABLE credentials
    ADD COLUMN created_ad TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP;

ALTER TABLE address
    ADD COLUMN created_ad TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP;

ALTER TABLE inventory
    ADD COLUMN created_ad TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP;

