package com.communify.api.mapper;

import static com.communify.api.builder.GenericBuilder.of;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.communify.api.dto.TaskDTO;
import com.communify.api.model.Task;

public class TaskMapper {

    private TaskMapper() {
    }
    
    public static List<Task> dtosToModels(List<TaskDTO> tasksDTOList) {
        return ofNullable(tasksDTOList)
            .map(tasksDTO -> tasksDTO.stream()
                .map(TaskMapper::dtoToModel)
                .collect(toList())).orElse(emptyList());
    }
    
    public static Task dtoToModel(TaskDTO taskDTO) {
        return ofNullable(taskDTO).map(TaskMapper::convertDTOToModel).orElse(null);
    }

    private static Task convertDTOToModel(TaskDTO taskDTO) {
        return of(Task::new)
            .with(Task::setId, taskDTO.getId())
            .with(Task::setDescription, taskDTO.getDescription())
            .with(Task::setLink, taskDTO.getLink())
            .with(Task::setCourse, taskDTO.getCourse())
            .with(Task::setReturnDate, taskDTO.getReturnDate())
            .with(Task::setOrigin, taskDTO.getOrigin())
            .build();
    }
}
