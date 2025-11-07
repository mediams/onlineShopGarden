-- liquibase formatted sql
-- changeset yevhen.kariev:004

ALTER TABLE products
    MODIFY COLUMN name VARCHAR(190) NOT NULL;
