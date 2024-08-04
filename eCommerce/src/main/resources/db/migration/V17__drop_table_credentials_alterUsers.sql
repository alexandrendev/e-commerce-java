ALTER TABLE users
    DROP COLUMN credentials_id;

DROP TABLE IF EXISTS credentials;

ALTER TABLE users
    ADD COLUMN username TEXT,
    ADD COLUMN password TEXT;

