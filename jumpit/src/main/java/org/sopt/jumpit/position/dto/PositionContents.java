package org.sopt.jumpit.position.dto;

public record PositionContents(
    Long id,
    String title,
    String career,
    String education,
    String deadline,
    String location,
    String responsibilities,
    String qualifications,
    String preferred,
    String benefits,
    String notice
) {

    public static PositionContents of (Long id, String title, String career, String education, String deadline, String location, String responsibilities, String qualifications, String preferred, String benefits, String notice) {
        return new PositionContents(id, title, career, education, deadline, location, responsibilities, qualifications, preferred, benefits, notice);
    }
}
