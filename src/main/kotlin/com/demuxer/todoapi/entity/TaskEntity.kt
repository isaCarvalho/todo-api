package com.demuxer.todoapi.entity

import com.demuxer.todoapi.dto.TaskDTO
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.Date
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
data class TaskEntity(
    @Id
    val id: UUID,
    val description: String,
    val startDate: Date,
    val endDate: Date
) {
    companion object {
        fun toTaskDTO(taskEntity: TaskEntity) = TaskDTO(taskEntity.id, taskEntity.description, taskEntity.startDate, taskEntity.endDate)
    }
}