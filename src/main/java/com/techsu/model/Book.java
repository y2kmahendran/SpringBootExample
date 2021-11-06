package com.techsu.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Book name must not be empty")
    private String name;
    private String synopsis;

    private LocalDateTime lastUpdate;
    @Column(nullable = false)
    private LocalDateTime createTime;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Category category;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Author author;

    @PrePersist
    public void setCreateTime() {
        createTime = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdateTime() {
        lastUpdate = LocalDateTime.now();
    }
}
