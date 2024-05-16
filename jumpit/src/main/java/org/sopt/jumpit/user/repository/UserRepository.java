package org.sopt.jumpit.user.repository;

import org.sopt.jumpit.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
