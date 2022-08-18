package com.demuxer.todoapi.service

import com.demuxer.todoapi.dto.SubTaskDTO
import com.demuxer.todoapi.entity.SubTaskEntity
import com.demuxer.todoapi.repository.SubTaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SubTaskService(
    @Autowired private val subTaskRepository: SubTaskRepository
) {

    fun getAll() : List<SubTaskDTO> {
        return subTaskRepository.findAll().map { SubTaskEntity.toDTO(it) }
    }

    fun getById(id: UUID) : SubTaskDTO {
        return SubTaskEntity.toDTO(subTaskRepository.getReferenceById(id))
    }

    fun create(subTaskDTO: SubTaskDTO) : SubTaskDTO {
        return SubTaskEntity.toDTO(subTaskRepository.save(SubTaskDTO.toEntity(subTaskDTO)))
    }

    fun update(subTaskDTO: SubTaskDTO) : SubTaskDTO {
        return SubTaskEntity.toDTO(subTaskRepository.save(SubTaskDTO.toEntity(subTaskDTO)))
    }

    fun delete(id: UUID) {
        subTaskRepository.deleteById(id)
    }
}