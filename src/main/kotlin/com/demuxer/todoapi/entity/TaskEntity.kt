package com.demuxer.todoapi.entity

import com.demuxer.todoapi.dto.TaskDTO
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.Date
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
data class TaskEntity(
    @Id
    val id: UUID,
    val description: String,
    val startDate: Date,
    val endDate: Date
) {
    companion object {
        fun toDTO(taskEntity: TaskEntity) = TaskDTO(taskEntity.id, taskEntity.description, taskEntity.startDate, taskEntity.endDate)
    }
}