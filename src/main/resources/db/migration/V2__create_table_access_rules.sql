CREATE TABLE access_rules (
    id           BIGSERIAL PRIMARY KEY,
    person_id    BIGINT NOT NULL REFERENCES persons(id),
    valid_from   TIMESTAMP NOT NULL,
    valid_until  TIMESTAMP,
    days_of_week VARCHAR(50),
    time_from    TIME,
    time_until   TIME,
    active       BOOLEAN NOT NULL DEFAULT TRUE
);