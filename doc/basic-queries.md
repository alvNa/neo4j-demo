
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