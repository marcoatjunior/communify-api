package com.communify.api.dto;

import java.util.Date;

import com.communify.api.enumerator.TaskOriginEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

    private String id;
    private String description;
    private String link;
    private Date returnDate;
    private TaskOriginEnum origin;
}
