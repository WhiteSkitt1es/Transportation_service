CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    fullname VARCHAR(128),
    username VARCHAR(64)  NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    role     VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS carriers
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(64) NOT NULL,
    phone_number VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS routes
(
    id                BIGSERIAL PRIMARY KEY,
    departure_point   VARCHAR(64) NOT NULL,
    destination_point VARCHAR(64) NOT NULL,
    id_carrier        BIGINT      NOT NULL REFERENCES carriers (id)
);

CREATE TABLE IF NOT EXISTS tickets
(
    id          BIGSERIAL PRIMARY KEY,
    seat_number INT       NOT NULL UNIQUE,
    time        TIMESTAMP NOT NULL,
    price       DECIMAL   NOT NULL,
    routes_id   BIGINT    NOT NULL REFERENCES routes (id)
);

