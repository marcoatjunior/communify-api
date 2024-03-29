package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static com.communify.api.helper.ClassroomDateHelper.toDate;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.communify.api.dto.TaskDTO;
import com.communify.api.enumerator.TaskOriginEnum;
import com.communify.api.model.CourseWork;

public class CourseWorkToTaskMapper {

    private CourseWorkToTaskMapper() {
    }
    
    public static List<TaskDTO> modelsToDTOs(List<CourseWork> listCourseWorks) {
        return ofNullable(listCourseWorks)
            .map(courseWorks -> courseWorks.stream()
                .map(CourseWorkToTaskMapper::modelToDTO)
                .collect(toList())).orElse(emptyList());
    }

    public static TaskDTO modelToDTO(CourseWork courseWork) {
        return ofNullable(courseWork).map(CourseWorkToTaskMapper::convertModelToDTO).orElse(null);
    }

    private static TaskDTO convertModelToDTO(CourseWork courseWork) {
        return of(TaskDTO::new)
            .with(TaskDTO::setId, courseWork.getId())
            .with(TaskDTO::setDescription, courseWork.getTitle())
            .with(TaskDTO::setCourse, ofNullable(courseWork.getCourse())
                .map(course -> course.getName()).orElse(null))
            .with(TaskDTO::setLink, courseWork.getAlternateLink())
            .with(TaskDTO::setReturnDate, toDate(courseWork.getDueDate()))
            .with(TaskDTO::setOrigin, TaskOriginEnum.Classroom)
            .build();
    }
    
}
