package com.communify.api.enumerator;

import static java.util.Arrays.asList;
import java.util.List;
import lombok.Getter;

@Getter
public enum TaskOriginEnum {

    Classroom("CLASSROOM"), Moodle("MOODLE");

    private String ambienteVirtual;

    private TaskOriginEnum(String ambienteVirtual) {
        this.ambienteVirtual = ambienteVirtual;
    }

    public static List<TaskOriginEnum> getAll() {
        return asList(values());
    }

    public static TaskOriginEnum get(String ambienteVirtual) {
        if (ambienteVirtual != null && ambienteVirtual.length() != 0) {
            for (TaskOriginEnum obj : getAll()) {
                if (obj.ambienteVirtual.equals(ambienteVirtual)) {
                    return obj;
                }
            }
        }
        return null;
    }
}
