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
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "artist")
    private String artist;
    @Column(name = "title")
    private String title;
    @Column(name = "format")
    private String format;
    @Column(name = "region")
    private String region;
    /*@Column(name = "review_id")
    private long reviewId;*/

    // collections table + records table
    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "collections",
            joinColumns={@JoinColumn(name="record_id", referencedColumnName="id")})
    private Collection collection;*/
}
