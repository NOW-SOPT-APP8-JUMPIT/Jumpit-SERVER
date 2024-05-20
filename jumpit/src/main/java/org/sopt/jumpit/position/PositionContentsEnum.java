package org.sopt.jumpit.position;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum PositionContentsEnum {
    CAREER("career"),
    EDUCATION("education"),
    DEADLINE("deadline"),
    LOCATION("location"),
    RESPONSIBILITIES("responsibilities"),
    QUALIFICATIONS("qualifications"),
    PREFERRED("preferred"),
    BENEFITS("benefits"),
    NOTICE("notice");

    private final String value;

    PositionContentsEnum(String value) {
        this.value = value;
    }
}
