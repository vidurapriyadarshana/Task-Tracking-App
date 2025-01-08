package com.vidura.tasks.mappers.impl;

import com.vidura.tasks.domain.dto.TaskListDto;
import com.vidura.tasks.domain.entities.Task;
import com.vidura.tasks.domain.entities.TaskList;
import com.vidura.tasks.domain.entities.TaskStatus;
import com.vidura.tasks.mappers.TaskListMapper;
import com.vidura.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.description(),
                taskListDto.title(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {

        final List<Task> tasks = taskList.getTasks();

        return new TaskListDto(

                taskList.getId(),

                taskList.getTitle(),

                taskList.getDescription(),
                Optional.ofNullable(tasks)
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(tasks),
                Optional.ofNullable(tasks)
                        .map(t -> t.stream()
                                .map(taskMapper::toDto)
                                .toList())
                        .orElse(null)
        );

    }

    private Double calculateTaskListProgress(List<Task> tasks) {

        if (null == tasks) {
            return null;
        }

        long closedTaskCount = tasks.stream()
                .filter(task -> TaskStatus.CLOSED == task.getStatus())
                .count();

        return (double) closedTaskCount / tasks.size();
    }


}
