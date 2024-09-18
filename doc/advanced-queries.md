

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

- We want to add a couple of User nodes
```cypher
MERGE (u:User {userId: 534})
SET u.name = "Sandy Jones";

MERGE (u:User {userId: 105})
SET u.name = "Clinton Spencer";
```

- List procedures
```cypher
SHOW PROCEDURES yield name, description, signature
```

- Load JSON example
```cypher
WITH 'https://raw.githubusercontent.com/neo4j/apoc/5.23/core/src/test/resources/person.json' AS url

CALL apoc.load.json(url) YIELD value as person

MERGE (p:Person {name:person.name})
ON CREATE SET p.age = person.age, p.children = size(person.children)
```
- Hops

This query matches all Person nodes exactly 2 hops away from Tom Hanks and returns the first five rows. 
The DISTINCT operator ensures that the result contain no duplicate values.

```cypher
MATCH (tom:Person {name:'Tom Hanks'})--{2}(colleagues:Person)
RETURN DISTINCT colleagues.name AS name, colleagues.born AS bornIn
ORDER BY bornIn
LIMIT 5
```

This query matches all Person nodes between 1 and 4 hops away from Tom Hanks and returns the first five rows.
```cypher
MATCH (p:Person {name:'Tom Hanks'})--{1,4}(colleagues:Person)
RETURN DISTINCT colleagues.name AS name, colleagues.born AS bornIn
ORDER BY bornIn, name
LIMIT 5
```

The SHORTEST keyword can be used to find a variation of the shortest paths between two nodes. In this example, ALL SHORTEST paths between the two nodes Keanu Reeves and Tom Cruise are found

```cypher
MATCH p = ALL SHORTEST (:Person {name:"Keanu Reeves"})--+(:Person {name:"Tom Cruise"})
RETURN count(p) AS pathCount, length(p) AS pathLength
```

The following query tries to recommend co-actors for Keanu Reeves, who he has yet to work with but who his co-actors have worked with. 
The query then orders the results by how frequently a matched co-co-actor has collaborated with one of Keanu Reeves' co-actors.

```cypher
MATCH (keanu:Person {name:'Keanu Reeves'})-[:ACTED_IN]->(m:Movie)<-[:ACTED_IN]-(coActors:Person),
  (coActors:Person)-[:ACTED_IN]->(m2:Movie)<-[:ACTED_IN]-(cocoActors:Person)
WHERE NOT (keanu)-[:ACTED_IN]->()<-[:ACTED_IN]-(cocoActors) AND keanu <> cocoActors
RETURN cocoActors.name AS recommended, count(cocoActors) AS strength
ORDER BY strength DESC
LIMIT 7
```

There are several connections between the Keanu Reeves and Tom Hanks nodes in the movie database, but the two have never worked together in a film. 
The following query matches coactors who could introduce the two, by looking for co-actors who have worked with both of them in separate movies:

```cypher
MATCH (:Person {name: 'Keanu Reeves'})-[:ACTED_IN]->(:Movie)<-[:ACTED_IN]-(coActor:Person),
(coActor)-[:ACTED_IN]->(:Movie)<-[:ACTED_IN]-(:Person {name:'Tom Hanks'})
RETURN DISTINCT coActor.name AS coActor
```

A regular expression: a.name =~ 'Tim.*'.
Get all the actors whose name is James
```cypher
MATCH (p:Person)
WHERE p.name =~ 'James.*'
RETURN p.name AS name, p.born AS born
```

Map
It is an example of a map projection with a literal entry, which in turn also uses map projection inside 
the aggregating collect() function.
```cypher
MATCH (keanu:Person {name: 'Keanu Reeves'})-[:ACTED_IN]->(movie:Movie)
WITH keanu, collect(movie{.title, .released}) AS movies
RETURN keanu{.name, movies: movies}

MATCH (keanu:Person {name:'Keanu Reeves'})
RETURN toStringList([keanu.name, keanu.age]) AS keanuList
```

To find all shortest paths, the ALL SHORTEST keywords can be used:
```cypher
MATCH p = ALL SHORTEST
(:Station {name: "Denmark Hill"})
(()-[:LINK]-(:Station))+
(:Station {name: "Clapham Junction"})
RETURN [station IN nodes(p) | station.name] AS route
```

In general, SHORTEST k can be used to return the k shortest paths. The following returns the two shortest paths:

```cypher
MATCH p = SHORTEST 2
(:Station {name: "Denmark Hill"})
(()-[:LINK]-(:Station))+
(:Station {name: "Clapham High Street"})
RETURN [station IN nodes(p) | station.name] AS route
```

The paths matched by a path pattern can be restricted to only the shortest (by number of hops) by including 
the keyword SHORTEST k, where k is the number of paths to match. For example, the following example uses SHORTEST 1 
to return the length of the shortest path between Worcester Shrub Hill and Bromsgrove:

```cypher
CREATE (asc:Station {name:"Ashchurch"}),
       (bmv:Station {name:"Bromsgrove"}),
       (cnm:Station {name:"Cheltenham Spa"}),
       (dtw:Station {name:"Droitwich Spa"}),
       (hby:Station {name:"Hartlebury"}),
       (psh:Station {name:"Pershore"}),
       (wop:Station {name:"Worcestershire Parkway"}),
       (wof:Station {name:"Worcester Foregate Street"}),
       (wos:Station {name:"Worcester Shrub Hill"})
CREATE (asc)-[:LINK {distance: 7.25}]->(cnm),
       (asc)-[:LINK {distance: 11.29}]->(wop),
       (asc)-[:LINK {distance: 14.75}]->(wos),
       (bmv)-[:LINK {distance: 31.14}]->(cnm),
       (bmv)-[:LINK {distance: 6.16}]->(dtw),
       (bmv)-[:LINK {distance: 12.6}]->(wop),
       (dtw)-[:LINK {distance: 5.64}]->(hby),
       (dtw)-[:LINK {distance: 6.03}]->(wof),
       (dtw)-[:LINK {distance: 5.76}]->(wos),
       (psh)-[:LINK {distance: 4.16}]->(wop),
       (wop)-[:LINK {distance: 3.71}]->(wos),
       (wof)-[:LINK {distance: 0.65}]->(wos)

MATCH p = SHORTEST 1 (wos:Station)-[:LINK]-+(bmv:Station)
WHERE wos.name = "Worcester Shrub Hill" AND bmv.name = "Bromsgrove"
RETURN length(p) AS result
```

SHORTEST k GROUPS

```cypher
MATCH p = SHORTEST 2 GROUPS (wos:Station)-[:LINK]-+(bmv:Station)
WHERE wos.name = "Worcester Shrub Hill" AND bmv.name = "Bromsgrove"
RETURN [n in nodes(p) | n.name] AS stops, length(p) AS pathLength
```