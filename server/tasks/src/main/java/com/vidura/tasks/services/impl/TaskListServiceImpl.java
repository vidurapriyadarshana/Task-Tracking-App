package com.vidura.tasks.services.impl;

import com.vidura.tasks.domain.entities.TaskList;
import com.vidura.tasks.repositories.TaskListRepository;
import com.vidura.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {

        if(null != taskList.getId()){
            throw new IllegalArgumentException("TaskList already has an id");
        }

        if(null == taskList.getTitle() || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("TaskList title must be present");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getDescription(),
                taskList.getTitle(),
                null,
                now,
                now
        ));
    }
}
