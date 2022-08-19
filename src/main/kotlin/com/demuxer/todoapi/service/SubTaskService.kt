package com.demuxer.todoapi.service

import com.demuxer.todoapi.dto.SubTaskDTO
import com.demuxer.todoapi.entity.SubTaskEntity
import com.demuxer.todoapi.exception.ResourceNotFoundException
import com.demuxer.todoapi.repository.SubTaskRepository
import com.demuxer.todoapi.util.RESOURCE_NOT_FOUND
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SubTaskService(
    @Autowired private val subTaskRepository: SubTaskRepository
) {

    fun getAll() : List<SubTaskDTO> {
        return subTaskRepository.findAll().map { SubTaskEntity.toDTO(it) }
    }

    fun getById(id: UUID) : SubTaskDTO? {
        val subTask = subTaskRepository.findByIdOrNull(id) ?: return null
        return SubTaskEntity.toDTO(subTask)
    }

    fun save(subTaskDTO: SubTaskDTO) : SubTaskDTO {
        return SubTaskEntity.toDTO(subTaskRepository.save(SubTaskDTO.toEntity(subTaskDTO)))
    }

    fun delete(subTask: SubTaskDTO) {
        subTaskRepository.delete(SubTaskDTO.toEntity(subTask))
    }
}