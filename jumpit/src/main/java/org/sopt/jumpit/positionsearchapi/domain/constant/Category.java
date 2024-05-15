package org.sopt.jumpit.positionsearchapi.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    FRONTEND_DEVELOPER(1, "프론트엔드 개발자"),
    DBA(2, "DBA"),
    IOS_DEVELOPER(3, "iOS 개발자"),
    ANDROID_DEVELOPER(4, "안드로이드 개발자");

    private final int id;
    private final String description;

    public static Category fromDescription(String description) {
        for (Category category : values()) {
            if (category.getDescription().equals(description)) {
                return category;
            }
        }
        return null;
    }
}
