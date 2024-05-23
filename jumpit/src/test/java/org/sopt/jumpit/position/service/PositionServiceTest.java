package org.sopt.jumpit.position.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sopt.jumpit.company.domain.Company;
import org.sopt.jumpit.global.common.dto.message.ErrorMessage;
import org.sopt.jumpit.global.exception.NotFoundException;
import org.sopt.jumpit.position.domain.Position;
import org.sopt.jumpit.position.dto.PositionsFindResponse;
import org.sopt.jumpit.position.repository.PositionRepository;
import org.sopt.jumpit.skill.domain.Skill;
import org.sopt.jumpit.skill.service.SkillService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PositionServiceTest {

    @InjectMocks
    private PositionService positionService;

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private SkillService skillService;

    @Mock
    private Company mockCompany;

    @Test
    @DisplayName("키워드 '백엔드 개발자'로 검색할 때 포지션 찾기: 성공 시 결과가 포함된 리스트 반환")
    void testFindPositionsByKeyword_Success() throws NoSuchFieldException, IllegalAccessException {
        // Given: "백엔드 개발자" 키워드로 포지션을 찾기 위한 설정
        Position samplePosition = createPositionWithReflection(1L, "백엔드 개발자", mockCompany);
        when(positionRepository.findPositionsByTitleContaining("백엔드 개발자"))
                .thenReturn(Optional.of(List.of(samplePosition)));

        Skill javaSkill = createSkillWithReflection("Java", "java.png");
        when(skillService.findByOwnerId(anyLong()))
                .thenReturn(List.of(javaSkill)); // 스킬 리스트를 반환하는 설정

        // When: 검색 메소드 실행
        PositionsFindResponse result = positionService.findPositionsByKeyword("백엔드 개발자");

        // Then: 결과 검증, 정확한 포지션이 반환되었는지 확인
        assertThat(result.position()).hasSize(1);
        assertThat(result.position().get(0).title()).isEqualTo("백엔드 개발자");
    }

    @Test
    @DisplayName("키워드 '채은아 생일 축하해'로 검색할 때 포지션 없음: 예외 발생하여 테스트 성공")
    void testFindPositionsByKeyword_NotFound() {
        // Given: "Nonexistent" 키워드로 포지션을 찾지 못할 때의 설정
        when(positionRepository.findPositionsByTitleContaining("채은아 생일 축하해"))
                .thenReturn(Optional.empty());

        // Then: 예외가 발생하면 테스트 성공으로 간주
        assertThatThrownBy(() -> positionService.findPositionsByKeyword("채은아 생일 축하해"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(ErrorMessage.SEARCH_FAILED.getMessage());
    }

    // 리플렉션을 사용하여 Position 객체 생성
    private Position createPositionWithReflection(Long id, String title, Company company) throws NoSuchFieldException, IllegalAccessException {
        Position position = new Position();
        Field idField = position.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(position, id);

        Field titleField = position.getClass().getDeclaredField("title");
        titleField.setAccessible(true);
        titleField.set(position, title);

        Field companyField = position.getClass().getDeclaredField("company");
        companyField.setAccessible(true);
        companyField.set(position, company);

        return position;
    }

    // 리플렉션을 사용하여 Skill 객체 생성
    private Skill createSkillWithReflection(String name, String image) throws NoSuchFieldException, IllegalAccessException {
        Skill skill = new Skill();
        Field nameField = skill.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(skill, name);

        Field imageField = skill.getClass().getDeclaredField("image");
        imageField.setAccessible(true);
        imageField.set(skill, image);

        return skill;
    }
}
