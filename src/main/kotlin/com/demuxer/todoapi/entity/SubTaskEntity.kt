package com.demuxer.todoapi.entity

import com.demuxer.todoapi.dto.SubTaskDTO
import com.fasterxml.jackson.databind.BeanDescription
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
data class SubTaskEntity(
    @Id
    val id: UUID,
    val description: String,
    @ManyToOne
    val taskEntity: TaskEntity
) {
    companion object {
        fun toDTO(subTaskEntity: SubTaskEntity) : SubTaskDTO {
            return SubTaskDTO(
                subTaskEntity.id,
                subTaskEntity.description,
                TaskEntity.toDTO(subTaskEntity.taskEntity)
            )
        }
    }
}