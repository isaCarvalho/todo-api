package com.demuxer.todoapi.repository

import com.demuxer.todoapi.entity.SubTaskEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SubTaskRepository : CrudRepository<SubTaskEntity, UUID>