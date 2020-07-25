package com.communify.api.enumerator;

import static java.util.Arrays.asList;
import java.util.List;
import lombok.Getter;

@Getter
public enum CourseWorkOriginEnum {

    Classroom("CLASSROOM"), Moodle("MOODLE");

    private String ambienteVirtual;

    private CourseWorkOriginEnum(String ambienteVirtual) {
        this.ambienteVirtual = ambienteVirtual;
    }

    public static List<CourseWorkOriginEnum> getAll() {
        return asList(values());
    }

    public static CourseWorkOriginEnum get(String ambienteVirtual) {
        if (ambienteVirtual != null && ambienteVirtual.length() != 0) {
            for (CourseWorkOriginEnum obj : getAll()) {
                if (obj.ambienteVirtual.equals(ambienteVirtual)) {
                    return obj;
                }
            }
        }
        return null;
    }
}
