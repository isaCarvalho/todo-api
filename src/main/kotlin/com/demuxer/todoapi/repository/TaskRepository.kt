package com.demuxer.todoapi.repository

import com.demuxer.todoapi.entity.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional


@Repository
interface TaskRepository : JpaRepository<TaskEntity, UUID> {

    @Modifying
    @Transactional
    @Query("UPDATE TaskEntity t SET t.description = :description, t.startDate = :startDate, t.endDate = :endDate WHERE t.id = :id")
    fun update(@Param("id") id : UUID, @Param("description") description: String, @Param("startDate") startDate: Date, @Param("endDate") endDate: Date) : Int

    @Modifying
    @Transactional
    @Query("DELETE FROM TaskEntity t WHERE t.id = :id")
    fun delete(@Param("id") id : UUID) : Int

    @Query("SELECT t.id, t.description, t.startDate, t.endDate FROM TaskEntity t WHERE t.id = :id")
    fun get(@Param("id") id : UUID) : TaskEntity?
}