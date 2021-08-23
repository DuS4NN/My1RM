package com.my1rm.model.database;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LANGUAGE")
@NoArgsConstructor
@Getter
@Setter
public class Language {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @Column(name = "name", columnDefinition = "VARCHAR(20)")
    @NotNull
    private String name;

    @Column(name = "image_url", columnDefinition = "VARCHAR(100)")
    @NotNull
    private String image_url;

    @OneToMany(targetEntity = UserOptions.class, fetch = FetchType.LAZY, mappedBy = "language", cascade = CascadeType.ALL)
    private Set<UserOptions> userOptions;
}
