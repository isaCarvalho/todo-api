package com.demuxer.todoapi.dto

import com.demuxer.todoapi.entity.SubTaskEntity
import java.util.UUID

data class SubTaskDTO(
    val id: UUID,
    val description: String,
    val task: TaskDTO
) {
    companion object {
        fun toEntity(subTaskDTO: SubTaskDTO) : SubTaskEntity {
            return SubTaskEntity(
                subTaskDTO.id,
                subTaskDTO.description,
                TaskDTO.toEntity(subTaskDTO.task)
            )
        }
    }
}