package com.techsu.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @NotEmpty(message = "Author name must not be empty")
    @NotNull
    @Column(nullable = false)
    private String name;
    private String type;


    private LocalDateTime lastUpdate;
    @Column(nullable = false)
    private LocalDateTime createTime;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();

    @PrePersist
    public void setCreateTime(){
        createTime = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdateTime(){
        lastUpdate = LocalDateTime.now();
    }


}
