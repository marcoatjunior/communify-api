package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.helper.DateHelper.transformDateToString;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.communify.api.dto.CourseWorkDTO;
import com.communify.api.enumerator.CourseWorkOriginEnum;
import com.google.api.services.classroom.model.CourseWork;

public class CourseWorkMapper {

    private CourseWorkMapper() {
    }
    
    public static List<CourseWorkDTO> modelsToDTOs(List<CourseWork> listCouseWorks) {
        return ofNullable(listCouseWorks)
            .map(courseWorks -> courseWorks.stream()
                .map(CourseWorkMapper::modelToDTO)
                .collect(toList())).orElse(emptyList());
    }

    public static CourseWorkDTO modelToDTO(CourseWork courseWork) {
        return ofNullable(courseWork).map(CourseWorkMapper::convertModelToDTO).orElse(null);
    }

    private static CourseWorkDTO convertModelToDTO(CourseWork courseWork) {
        return of(CourseWorkDTO::new)
            .with(CourseWorkDTO::setId, courseWork.getId())
            .with(CourseWorkDTO::setDescription, courseWork.getTitle())
            .with(CourseWorkDTO::setLink, courseWork.getAlternateLink())
            .with(CourseWorkDTO::setReturnDate, transformDateToString(courseWork.getDueDate()))
            .with(CourseWorkDTO::setOrigin, CourseWorkOriginEnum.Classroom)
            .build();
    }
    
}
