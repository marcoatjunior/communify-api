package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.helper.DateHelper.transform;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.communify.api.dto.TaskDTO;
import com.communify.api.enumerator.TaskOriginEnum;
import com.google.api.services.classroom.model.CourseWork;

public class CourseWorkMapper {

    private CourseWorkMapper() {
    }
    
    public static List<TaskDTO> modelsToDTOs(List<CourseWork> listCourseWorks) {
        return ofNullable(listCourseWorks)
            .map(courseWorks -> courseWorks.stream()
                .map(CourseWorkMapper::modelToDTO)
                .collect(toList())).orElse(emptyList());
    }

    public static TaskDTO modelToDTO(CourseWork courseWork) {
        return ofNullable(courseWork).map(CourseWorkMapper::convertModelToDTO).orElse(null);
    }

    private static TaskDTO convertModelToDTO(CourseWork courseWork) {
        return of(TaskDTO::new)
            .with(TaskDTO::setId, courseWork.getId())
            .with(TaskDTO::setDescription, courseWork.getTitle())
            .with(TaskDTO::setLink, courseWork.getAlternateLink())
            .with(TaskDTO::setReturnDate, transform(courseWork.getDueDate()))
            .with(TaskDTO::setOrigin, TaskOriginEnum.Classroom)
            .build();
    }
    
}
