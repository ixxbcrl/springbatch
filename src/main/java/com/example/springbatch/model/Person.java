package com.example.springbatch.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Person")
public class Person implements Serializable {
    private static final long serialVersionUID = -2343243243242432341L;

    @GenericGenerator(
            name = "personSequenceGenerator",
            strategy = "enhanced-sequence",
            parameters = {
                    @Parameter(name = "optimizer", value = "pooled-lo"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "10")
            }
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSequenceGenerator")
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String country;

}
