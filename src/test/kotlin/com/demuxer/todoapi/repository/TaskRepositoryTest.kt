package com.demuxer.todoapi.repository

import com.demuxer.todoapi.entity.TaskEntity
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import java.util.*
import javax.transaction.Transactional

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class TaskRepositoryTest {

    @Autowired
    private lateinit var taskRepository: TaskRepository

    private val taskId = UUID.randomUUID()
    private val taskDescription = "This is a task description"
    private val startDate = Date()
    private val endDate = Date()

    private val task = TaskEntity(taskId, taskDescription, startDate, endDate)

    @BeforeEach
    fun setUp() {
        taskRepository.save(task)
    }

    @Test
    fun getAllTaskTest() {
        // when
        val tasks = taskRepository.findAll().toList()

        // then
        Assertions.assertEquals(1, tasks.size)
    }

    @Test
    fun getTaskTest() {
        // given
        val tasks = taskRepository.findAll().toList()

        // when
        val filteredTask = taskRepository.findByIdOrNull(taskId)

        // then
        Assertions.assertEquals(1, tasks.size)
        Assertions.assertEquals(task, filteredTask)
    }

    @Test
    fun saveTaskWithSuccessTest() {
        // given
        val task = TaskEntity(UUID.randomUUID(), taskDescription, startDate, endDate)

        // when
        val savedTask = taskRepository.save(task)

        // then
        Assertions.assertEquals(task, savedTask)
    }

    @Test
    fun updateTaskWithSuccessTest() {

        // given
        val oldDescription = "This is the old description"
        val newDescription = "This is a new description"
        taskRepository.save(TaskEntity(taskId, oldDescription, startDate, endDate))

        // when
        val updatedTask = taskRepository.save(TaskEntity(taskId, newDescription, startDate, endDate))

        // then
        Assertions.assertEquals(newDescription, updatedTask.description)
    }

    @Test
    fun deleteTaskWithSuccessTest() {
        // when
        taskRepository.deleteById(taskId)
        val task = taskRepository.findById(taskId)

        // then
        Assertions.assertTrue(task.isEmpty)
    }
}