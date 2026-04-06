CREATE TABLE access_logs (
    id          BIGSERIAL PRIMARY KEY,
    person_id   BIGINT REFERENCES persons(id),
    device_id   BIGINT,
    accessed_at TIMESTAMP NOT NULL,
    granted     BOOLEAN NOT NULL,
    reason      VARCHAR(100)
);