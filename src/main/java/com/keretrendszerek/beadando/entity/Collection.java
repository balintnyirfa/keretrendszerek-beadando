package com.keretrendszerek.beadando.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "user_id", insertable=false, updatable=false)
    private long userId;
    @Column(name = "record_id")
    private long recordId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    /*@OneToOne(mappedBy = "collections")
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;*/

    /*@ManyToOne(cascade = CascadeType.ALL)
    private Record record;*/
}
