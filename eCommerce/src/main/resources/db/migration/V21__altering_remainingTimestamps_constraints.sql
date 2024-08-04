ALTER TABLE product
    RENAME COLUMN created_ad TO created_at;

ALTER TABLE product
    ALTER COLUMN created_at SET DEFAULT NOW();

CREATE TRIGGER update_product_timestamp
    BEFORE UPDATE ON product
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();


ALTER TABLE email
    RENAME COLUMN created_ad TO created_at;

ALTER TABLE email
    ALTER COLUMN created_at SET DEFAULT NOW();

CREATE TRIGGER update_email_timestamp
    BEFORE UPDATE ON email
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();



ALTER TABLE inventory
    RENAME COLUMN created_ad TO created_at;

ALTER TABLE inventory
    ALTER COLUMN created_at SET DEFAULT NOW();

CREATE TRIGGER update_inventory_timestamp
    BEFORE UPDATE ON inventory
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();


ALTER TABLE address
    RENAME COLUMN created_ad TO created_at;

ALTER TABLE address
    ALTER COLUMN created_at SET DEFAULT NOW();

CREATE TRIGGER update_address_timestamp
    BEFORE UPDATE ON address
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();
