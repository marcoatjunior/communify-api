package com.communify.api.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskOriginEnum {

    Classroom("CLASSROOM"), Moodle("MOODLE");

    private String ambienteVirtual;
}
