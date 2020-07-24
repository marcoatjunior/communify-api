package com.communify.api.dto;

import java.util.Calendar;

import com.communify.api.enumerator.TaskOriginEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

    private Long id;
    private String description;
    private Calendar returnDate;
    private TaskOriginEnum taskOrigin;
}
