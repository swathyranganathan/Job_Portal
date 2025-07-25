package com.swathy.entity;
import com.mysql.cj.protocol.ColumnDefinition;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "jobs")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description is required")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Skills are required")
    private String skills;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private User company;

    public enum Status{
        PENDING,
        APPROVED,
        REJECTED
    }
}
