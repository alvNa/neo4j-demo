# neo4j-demo
neo4j-demo


## Start docker containers
```bash
docker-compose up -d
```

## Go Neo4J DB Admin

- http://localhost:7474/browser/
User: neo4j
Pass: password

## Queries

- List full graph DB
```cypher
MATCH (n) 
RETURN n
```

- Delete All entities
```cypher
MATCH (n)
DETACH DELETE n
```

- Clean Liquibase
```cypher
MATCH (n:__LiquibaseChangeSet) DETACH DELETE n;
MATCH (n:__LiquibaseChangeLog) DETACH DELETE n;

DROP CONSTRAINT unique_liquibase_context;
DROP CONSTRAINT unique_liquibase_label;
DROP CONSTRAINT unique_liquibase_lock;
DROP CONSTRAINT unique_liquibase_tag;
```

- List n Movies
```cypher
MATCH (n:Movie) RETURN n LIMIT 25
```

- Delete All Movies
```cypher
MATCH (n:Movie) DETACH DELETE n
```

- List n Persons
```cypher
MATCH (p:Person) RETURN p LIMIT 25
```

- Query by person's name
```cypher
MATCH (p:Person {name:'Keanu Reeves'})
RETURN p.name AS name, p.born AS born
```

- Query by Movie's date
```cypher
MATCH (m:Movie) 
WHERE m.released >=2000 
RETURN m LIMIT 25
```

- Query by Movie's filtering not null generes
```cypher
MATCH (m:Movie)
WHERE m.genre is not null
RETURN m LIMIT 25
```

- Query Person using date range
```cypher
MATCH (bornInEighties:Person)
  WHERE bornInEighties.born >= 1980 AND bornInEighties.born < 1990
RETURN bornInEighties.name as name, bornInEighties.born as born
  ORDER BY born DESC
```

- Load CSV
```cypher
LOAD CSV WITH HEADERS FROM "https://data.neo4j.com/northwind/categories.csv" AS row
CREATE (n:Category)
SET n = row
```

- Queries using relations
```cypher
MATCH (m:Movie {title: 'The Matrix'})<-[d:DIRECTED]-(p:Person)
RETURN p.name as director;
```

- Query movies where Keanu Reeves acted in order by date
```cypher
MATCH (m:Movie)<-[a:ACTED_IN]-(p:Person)
WHERE p.name ='Keanu Reeves'
RETURN m.title, a.roles, m.released
ORDER BY m.released DESC
```

-- We want to add a couple of User nodes
```cypher
MERGE (u:User {userId: 534})
SET u.name = "Sandy Jones";

MERGE (u:User {userId: 105})
SET u.name = "Clinton Spencer";
```

SHOW PROCEDURES yield name, description, signature


WITH 'https://raw.githubusercontent.com/neo4j/apoc/5.23/core/src/test/resources/person.json' AS url

CALL apoc.load.json(url) YIELD value as person

MERGE (p:Person {name:person.name})
ON CREATE SET p.age = person.age, p.children = size(person.children)

## References
- https://medium.com/@ankshukray/mastering-neo4j-with-spring-boot-a-complete-guide-with-configuration-and-examples-939bde3d17c4
- https://neo4j.com/docs/cypher-manual/current/queries/basic/
- https://medium.com/@cat.edelveis/using-liquibase-with-spring-boot-tutorial-79245a0b79a6
- https://graphacademy.neo4j.com/courses/modeling-fundamentals
- https://github.com/neo4j/apoc
