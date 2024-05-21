package org.sopt.jumpit.resume.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sopt.jumpit.resume.dto.ResumeCreateRequest;
import org.sopt.jumpit.user.domain.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "Resume")
@Getter
@Setter
@NoArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private User owner;

    @Column(name = "title")
    private String title;

    @Column(name = "isPrivate")
    private boolean isPrivate;

    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Builder
    private Resume(User owner, String title) {
        this.owner = owner;
        this.title = title;
        this.isPrivate = false;
    }

    public static Resume create(User owner, String title) {
        return Resume.builder()
                .title(title)
                .owner(owner)
                .build();
    }
}
