package com.keretrendszerek.beadando.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "collections")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "record_id")
    private long recordId;
    /*@Column(name = "count")
    private int count;*/

    @OneToOne(mappedBy = "collections")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Record record;
}
