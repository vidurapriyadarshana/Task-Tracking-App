package com.vidura.tasks.mappers;

import com.vidura.tasks.domain.dto.TaskListDto;
import com.vidura.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
