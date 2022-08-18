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
        return taskRepository.findAll().map { TaskEntity.toDTO(it) }
    }

    fun getById(id: UUID) : TaskDTO {
        return TaskEntity.toDTO(taskRepository.getReferenceById(id))
    }

    fun create(task: TaskDTO) : TaskDTO {
        return TaskEntity.toDTO(taskRepository.save(TaskDTO.toEntity(task)))
    }

    fun update(task: TaskDTO) : TaskDTO {
        return TaskEntity.toDTO(taskRepository.save(TaskDTO.toEntity(task)))
    }

    fun delete(id: UUID) {
        taskRepository.deleteById(id)
    }
}