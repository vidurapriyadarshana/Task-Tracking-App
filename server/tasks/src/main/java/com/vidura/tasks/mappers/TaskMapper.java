package com.vidura.tasks.mappers;

import com.vidura.tasks.domain.dto.TaskDto;
import com.vidura.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
