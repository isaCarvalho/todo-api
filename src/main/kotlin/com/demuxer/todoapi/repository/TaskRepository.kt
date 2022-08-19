package com.demuxer.todoapi.repository

import com.demuxer.todoapi.entity.TaskEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface TaskRepository : CrudRepository<TaskEntity, UUID>