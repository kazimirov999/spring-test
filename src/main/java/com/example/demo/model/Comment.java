package com.example.demo.model;

import lombok.*;


import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"subComments", "user", "product", "parent"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comments")
public class Comment extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "text")
    private String text;


    @ManyToOne
    @JoinColumn(name = "parent")
    private Comment parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> subComments;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "userId")
    private User user;

}
