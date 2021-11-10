CREATE TABLE IF NOT EXISTS lord
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
    age  INTEGER,
    name VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS planets
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
    name    VARCHAR(255),
    lord_id BIGINT,
    PRIMARY KEY (id)
);

ALTER TABLE planets
    ADD CONSTRAINT LORD FOREIGN KEY (lord_id) REFERENCES lord