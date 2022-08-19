package com.demuxer.todoapi.service

import com.demuxer.todoapi.dto.TaskDTO
import com.demuxer.todoapi.entity.TaskEntity
import com.demuxer.todoapi.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class TaskService(
    @Autowired private val taskRepository: TaskRepository
) {
    fun getAll() : List<TaskDTO> {
        return taskRepository.findAll().map { TaskEntity.toDTO(it) }
    }

    fun getById(id: UUID) : TaskDTO? {
        val task = taskRepository.findByIdOrNull(id) ?: return null

        return TaskEntity.toDTO(task)
    }

    fun create(task: TaskDTO) : TaskDTO {
        return TaskEntity.toDTO(taskRepository.save(TaskDTO.toEntity(task)))
    }

    fun update(task: TaskDTO) {
        taskRepository.save(TaskDTO.toEntity(task))
    }

    fun delete(task: TaskDTO) {
        taskRepository.delete(TaskDTO.toEntity(task))
    }
}