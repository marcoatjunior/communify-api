package com.communify.api.dto;

import static com.communify.api.helper.DateHelper.format;

import java.util.Date;

import com.communify.api.enumerator.TaskOriginEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

    private String id;
    private String description;
    private String course;
    private String link;
    private Date returnDate;
    private TaskOriginEnum origin;
    
    public String getFormattedDate() {
        return format(getReturnDate());
    }
}
