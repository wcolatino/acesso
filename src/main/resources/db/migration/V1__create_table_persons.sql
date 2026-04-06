CREATE TABLE persons (
    id          BIGSERIAL PRIMARY KEY,
    device_id   BIGINT UNIQUE,
    name        VARCHAR(150) NOT NULL,
    type        VARCHAR(30) NOT NULL,
    photo_url   VARCHAR(255),
    active      BOOLEAN NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP NOT NULL
);