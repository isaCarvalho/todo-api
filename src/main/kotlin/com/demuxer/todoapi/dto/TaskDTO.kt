package com.demuxer.todoapi.dto

import com.demuxer.todoapi.entity.TaskEntity
import java.util.UUID
import java.util.Date

data class TaskDTO(
    val id: UUID,
    val description: String,
    val startDate: Date,
    val endDate: Date
) {
    companion object {
        fun toEntity(taskDTO: TaskDTO) = TaskEntity(taskDTO.id, taskDTO.description, taskDTO.startDate, taskDTO.endDate)
    }
}