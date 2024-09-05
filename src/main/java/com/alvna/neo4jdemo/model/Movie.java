package com.alvna.neo4jdemo.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Movie")
public class Movie {
    @Id @GeneratedValue
    private Long id;

    @Property("title")
    private String title;

    @Property("tagline")
    private String tagline;

    @Property("released")
    private Integer released;

//    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
//    private Person actor;
}