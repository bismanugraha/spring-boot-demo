-- Update role column to store enum names (ADMIN, CUSTOMER) instead of ordinals (0, 1)
ALTER TABLE users MODIFY COLUMN role VARCHAR(50) NOT NULL;