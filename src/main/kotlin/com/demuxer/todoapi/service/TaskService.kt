package com.demuxer.todoapi.service

import com.demuxer.todoapi.dto.TaskDTO
import com.demuxer.todoapi.entity.TaskEntity
import com.demuxer.todoapi.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.util.*

@Service
class TaskService(
    @Autowired private val taskRepository: TaskRepository
) {
    fun getAll() : List<TaskDTO> {
        return taskRepository.findAll().map { TaskEntity.toTaskDTO(it) }
    }

    fun getById(@PathVariable id: UUID) : TaskDTO {
        return TaskEntity.toTaskDTO(taskRepository.getReferenceById(id))
    }

    fun create(@RequestBody task: TaskDTO) : TaskDTO {
        return TaskEntity.toTaskDTO(taskRepository.save(TaskDTO.toTaskEntity(task)))
    }

    fun update(@RequestBody task: TaskDTO) : TaskDTO {
        return TaskEntity.toTaskDTO(taskRepository.save(TaskDTO.toTaskEntity(task)))
    }

    fun delete(@PathVariable id: UUID) {
        taskRepository.deleteById(id)
    }
}