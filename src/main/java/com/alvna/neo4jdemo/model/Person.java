package com.alvna.neo4jdemo.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Person")
public class Person {
    @Id @GeneratedValue
    private Long id;
    @Property("name")
    private String name;
    @Property("born")
    private Integer born;

    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.OUTGOING)
    private List<Movie> moviesActed;
}
