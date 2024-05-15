package org.sopt.jumpit.resume.domain;

import jakarta.persistence.*;
import org.sopt.jumpit.user.domain.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "Resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private User owner;

    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

}
