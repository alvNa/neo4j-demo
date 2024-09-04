-- liquibase formatted cypher

-- changeset anavarro:1

CREATE CONSTRAINT IF NOT EXISTS FOR (p:Person) REQUIRE (p.name) IS UNIQUE;
CREATE INDEX IF NOT EXISTS FOR (p:Person) ON (p.born);
CREATE CONSTRAINT IF NOT EXISTS FOR (m:Movie) REQUIRE (m.title) IS UNIQUE;
CREATE INDEX IF NOT EXISTS FOR (m:Movie) ON (m.released);

