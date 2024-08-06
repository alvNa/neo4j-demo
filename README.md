# neo4j-demo
neo4j-demo

docker-compose up -d


mvn liquibase:status --url jdbc:neo4j:bolt://localhost:7687 \
--username neo4j \
--password changeme \
--changeLogFile liquibase/master.xml \
update

--Delete All entities
MATCH (n)
DETACH DELETE n


LOAD CSV WITH HEADERS FROM "https://data.neo4j.com/northwind/categories.csv" AS row
CREATE (n:Category)
SET n = row