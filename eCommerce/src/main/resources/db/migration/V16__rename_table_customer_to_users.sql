ALTER TABLE customer RENAME TO users;

ALTER TABLE users
    ADD COLUMN role TEXT;