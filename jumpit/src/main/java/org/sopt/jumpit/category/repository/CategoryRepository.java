package org.sopt.jumpit.category.repository;

import org.sopt.jumpit.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
