create database if not exists contacts;

use contacts;

CREATE TABLE if not exists contact
(
id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
firstname VARCHAR(100) NOT NULL,
lastname VARCHAR(100) NOT NULL,
birthday DATE NOT NULL,
cnt_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
cnt_updated_at DATETIME NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Add Indexes */
CREATE INDEX contact_lastname_Idx ON contact (lastname) USING BTREE;
CREATE INDEX contacto_firstname_Idx ON contact (firstname) USING BTREE;
CREATE UNIQUE INDEX contact_firstname_lastname_Idx ON contact (firstname, lastname) USING BTREE;
ALTER TABLE contact modify column phone bigint;
ALTER TABLE contact modify column hidden boolean not null;


