//package com.alvna.neo4jdemo.model;
//
//import lombok.*;
//import org.springframework.data.neo4j.core.schema.Id;
//import org.springframework.data.neo4j.core.schema.Node;
//import org.springframework.data.neo4j.core.schema.Relationship;
//
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Node("Author")
//public class Author {
//    @Id
//    private Long id;
//
//    private String name;
//
//    @Relationship(type = "WRITTEN_BY", direction = Relationship.Direction.INCOMING)
//    private List<Book> books;
//
//    // Constructor, getters and setters
//}
