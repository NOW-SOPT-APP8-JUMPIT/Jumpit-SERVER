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
import org.sopt.jumpit.relationship.domain.PositionCategory;
import org.sopt.jumpit.relationship.service.PositionCategoryService;
import org.sopt.jumpit.skill.domain.Skill;
import org.sopt.jumpit.skill.service.SkillService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

    @Mock
    private PositionCategoryService positionCategoryService;

    @Test
    @DisplayName("키워드 '백엔드 개발자'로 검색 시, 해당 키워드를 포함하는 포지션 리스트 반환")
    void testFindPositionsByKeyword_Success() throws NoSuchFieldException, IllegalAccessException {
        // Given: "백엔드 개발자" 키워드로 포지션을 찾기 위한 설정
        Position samplePosition = createPositionByKeywordWithReflection(1L, "백엔드 개발자", mockCompany);
        when(positionRepository.findPositionsByTitleContaining("백엔드 개발자"))
                .thenReturn(Optional.of(List.of(samplePosition)));

        Skill javaSkill = createSkillByKeywordWithReflection("Java", "https://drive.google.com/file/d/1TliH1yADLU_2pmpy6PjavnkqWjdJ9MGa/view?usp=drive_link");
        when(skillService.findByOwnerId(anyLong()))
                .thenReturn(List.of(javaSkill)); // 스킬 리스트를 반환하는 설정

        // When: 검색 메소드 실행
        PositionsFindResponse result = positionService.findPositionsByKeyword("백엔드 개발자");

        // Then: 결과 검증, 정확한 포지션이 반환되었는지 확인
        assertThat(result.position()).hasSize(1);
        assertThat(result.position().get(0).title()).isEqualTo("백엔드 개발자");
    }

    // 리플렉션을 사용하여 Position 객체 생성
    private Position createPositionByKeywordWithReflection(Long id, String title, Company company) throws NoSuchFieldException, IllegalAccessException {
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
    private Skill createSkillByKeywordWithReflection(String name, String image) throws NoSuchFieldException, IllegalAccessException {
        Skill skill = new Skill();
        Field nameField = skill.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(skill, name);

        Field imageField = skill.getClass().getDeclaredField("image");
        imageField.setAccessible(true);
        imageField.set(skill, image);

        return skill;
    }

    @Test
    @DisplayName("존재하지 않는 키워드로 검색 시, NotFoundException 발생")
    void testFindPositionsByKeyword_NotFound() {
        // Given: "Nonexistent" 키워드로 포지션을 찾지 못할 때의 설정
        when(positionRepository.findPositionsByTitleContaining("채은아 생일 축하해"))
                .thenReturn(Optional.empty());

        // Then: 예외가 발생하면 테스트 성공으로 간주
        assertThatThrownBy(() -> positionService.findPositionsByKeyword("채은아 생일 축하해"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(ErrorMessage.SEARCH_FAILED.getMessage());
    }

    @Test
    @DisplayName("키워드와 카테고리에 따라 필터링된 포지션 목록 조회")
    void testFindFilteredPositionsByKeyword() {
        // Given: "1" 키워드와 카테고리 ID 목록을 설정하여 포지션 필터링을 위한 준비
        // "1" 키워드로 포지션을 검색할 때 사용될 예상 키워드와 카테고리 목록을 설정
        String keyword = "1";
        List<Long> categoryIds = Arrays.asList(1L, 2L);

        // Position 객체를 목킹하여, "[토스 뱅크] System Engineer"라는 제목과 함께 설정
        Position position1 = mock(Position.class);
        when(position1.getId()).thenReturn(1L);
        when(position1.getTitle()).thenReturn("[토스 뱅크] System Engineer");

        // Company 객체도 목킹하여, 해당 포지션의 회사 정보 반환 설정
        Company mockCompany = mock(Company.class);
        when(mockCompany.getId()).thenReturn(10L);
        when(position1.getCompany()).thenReturn(mockCompany);

        // Skill 객체를 목킹하여, "Java"라는 기술 스택 정보 반환 설정
        Skill skill = mock(Skill.class);
        when(skill.getName()).thenReturn("Java");
        when(skill.getImage()).thenReturn("https://drive.google.com/file/d/1TliH1yADLU_2pmpy6PjavnkqWjdJ9MGa/view?usp=drive_link");

        // PositionCategoryService에서 카테고리 ID에 따라 PositionCategory 객체 목 리스트를 반환하도록 설정
        PositionCategory positionCategoryMock = mock(PositionCategory.class);
        when(positionCategoryMock.getPosition()).thenReturn(position1);
        when(positionCategoryService.findPositionByCategory(anyLong())).thenReturn(Arrays.asList(positionCategoryMock));

        // PositionRepository에서 제공된 키워드를 포함하는 포지션 목록을 반환하도록 설정
        when(positionRepository.findPositionsByTitleContaining(anyString())).thenReturn(Optional.of(List.of(position1)));
        when(skillService.findByOwnerId(anyLong())).thenReturn(List.of(skill));

        // When: 실제 검색 메서드를 실행하여 결과를 받음
        PositionsFindResponse result = positionService.findFilteredPositionsByKeyword(keyword, categoryIds);

        // Then: 결과 검증, "[토스 뱅크] System Engineer" 포지션을 정확하게 반환했는지 확인
        assertThat(result.position()).hasSize(1);
        assertThat(result.position().get(0).title()).isEqualTo("[토스 뱅크] System Engineer");

        // Verify: 각 서비스의 메서드가 예상대로 호출되었는지 검증
        verify(positionCategoryService, times(categoryIds.size())).findPositionByCategory(anyLong());
        verify(positionRepository).findPositionsByTitleContaining(keyword.trim());
        verify(skillService).findByOwnerId(position1.getId());
    }

    @Test
    @DisplayName("키워드와 카테고리로 포지션을 찾지 못했을 때 예외를 발생시키는 실패 테스트")
    void testFindPositionsByKeyword_Failure() {
        // Given: "1" 키워드와 ID 1, 2로 구성된 카테고리 목록으로 설정
        String keyword = "1";
        List<Long> categoryIds = Arrays.asList(1L, 2L);

        // PositionRepository에서 해당 키워드를 포함하는 포지션 목록이 없는 상황 설정 (빈 Optional 반환)
        when(positionRepository.findPositionsByTitleContaining(keyword)).thenReturn(Optional.empty());

        // When: 해당 키워드로 포지션 검색을 시도하고 결과가 없을 경우 RuntimeException을 예상
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            positionService.findFilteredPositionsByKeyword(keyword, categoryIds);
        });

        // Then: 예외 발생을 확인하고, "포지션을 찾을 수 없습니다" 메시지가 포함되어 있는지 검증
        assertThat(exception.getMessage()).contains("[ERROR] 채용 공고 검색에 실패하였습니다.");

        // Verify: PositionRepository의 findPositionsByTitleContaining 메서드가 호출됨을 확인
        verify(positionRepository).findPositionsByTitleContaining(keyword.trim());
        verifyNoInteractions(skillService); // 검색 결과가 없으므로 SkillService는 호출되지 않음
    }

}
