package com.demuxer.todoapi.repository

import com.demuxer.todoapi.entity.SubTaskEntity
import com.demuxer.todoapi.entity.TaskEntity
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

// TODO fix tests
@SpringBootTest
class SubTaskRepositoryTest {

    @Autowired
    private lateinit var subTaskRepository: SubTaskRepository

    @Autowired
    private lateinit var taskRepository: TaskRepository

    // task
    private val taskId = UUID.randomUUID()
    private val taskDescription = "This is a task description"
    private val startDate = Date()
    private val endDate = Date()
    private val task = TaskEntity(taskId, taskDescription, startDate, endDate)

    // first subtask
    private val firstSubTaskId = UUID.randomUUID()
    private val firstDescription = "This is the first subsection"
    private val firstSubTask = SubTaskEntity(firstSubTaskId, firstDescription, task)

    // second subtask
    private val secondSubTaskId = UUID.randomUUID()
    private val secondDescription = "This is the first subsection"
    private val secondSubTask = SubTaskEntity(secondSubTaskId, secondDescription, task)

    @BeforeAll
    fun setup() {
        taskRepository.save(task)
        subTaskRepository.save(firstSubTask)
        subTaskRepository.save(secondSubTask)
    }

    @Test
    fun getAllTest() {
        // when
        val subTasks = subTaskRepository.findAll().toList()

        // then
        Assertions.assertEquals(2, subTasks.size)
    }
}