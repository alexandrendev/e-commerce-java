ALTER TABLE users
    RENAME COLUMN created_ad TO created_at;


ALTER TABLE users
    ALTER COLUMN created_at SET DEFAULT NOW();



CREATE OR REPLACE FUNCTION update_modified_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_users_modified
    BEFORE UPDATE ON users
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();
