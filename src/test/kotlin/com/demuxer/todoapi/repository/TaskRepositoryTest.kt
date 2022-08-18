package com.demuxer.todoapi.repository

import com.demuxer.todoapi.entity.TaskEntity
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskRepositoryTest {

    @Autowired
    private lateinit var taskRepository: TaskRepository

    private val taskId = UUID.randomUUID()
    private val taskDescription = "This is a task description"
    private val startDate = Date()
    private val endDate = Date()

    @BeforeAll
    fun setUp() {
        val task = TaskEntity(taskId, taskDescription, startDate, endDate)
        taskRepository.save(task)
    }

    @Test
    fun getAllTaskTest() {
        // given
        val tasks = taskRepository.findAll()

        // then
        Assertions.assertEquals(1, tasks.size)
    }

    @Test
    fun getTaskTest() {
        // given
        val task = taskRepository.get(taskId)

        // then
        Assertions.assertNotEquals(null, task)
    }

    @Test
    fun saveTaskWithSuccessTest() {
        // given
        val task = TaskEntity(UUID.randomUUID(), taskDescription, startDate, endDate)

        // when
        val savedTask = taskRepository.save(task)

        // then
        Assertions.assertEquals(savedTask, task)
    }

    @Test
    fun saveTaskWithErrorTest() {
        // given
        val task = TaskEntity(taskId, taskDescription, startDate, endDate)

        // then
        Assertions.assertThrows(Exception::class.java) { taskRepository.save(task) }
    }

    // TODO fix these tests
    @Test
    fun updateTaskWithSuccessTest() {

        // given
        val newDescription = "This is a new description"

        // when
        val updateCode = taskRepository.update(taskId, newDescription, startDate, endDate)

        // then
        Assertions.assertEquals(1, updateCode)
        Assertions.assertEquals(1, taskRepository.count())
    }

    @Test
    fun deleteTaskWithErrorTest() {
        // when
        val deleteCode = taskRepository.delete(UUID.randomUUID())

        // then
        Assertions.assertEquals(0, deleteCode)
        Assertions.assertEquals(1, taskRepository.count())
    }

    @Test
    fun deleteTaskWithSuccessTest() {
        // when
        val deleteCode = taskRepository.delete(taskId)

        // then
        Assertions.assertEquals(1, deleteCode)
        Assertions.assertEquals(0, taskRepository.count())
    }
}