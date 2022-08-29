package com.demuxer.todoapi.service

import com.demuxer.todoapi.entity.TaskEntity
import com.demuxer.todoapi.repository.TaskRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.time.Instant
import java.util.*

@ExtendWith(MockitoExtension::class)
class TaskServiceTest {

    @Mock
    lateinit var mockTaskRepository: TaskRepository

    @InjectMocks
    lateinit var taskService: TaskService

    @BeforeEach
    fun setUp() {
        Mockito.reset(mockTaskRepository)
    }

    @Test
    fun `should return all of the subtasks`() {
        // given
        val startDate = Date()
        val endDate = Date.from(Instant.ofEpochMilli(1661781557 + 10000))
        val task = TaskEntity(UUID.randomUUID(), "Task", startDate, endDate)

        // when
        Mockito.`when`(mockTaskRepository.findAll()).thenReturn(listOf(task))

        val savedTasks = taskService.getAll()

        // then
        Assertions.assertEquals(1, savedTasks.size)
    }

    @Test
    fun `should return a task by id`() {
        // given
        val taskId = UUID.randomUUID()
        val startDate = Date()
        val endDate = Date.from(Instant.ofEpochMilli(1661781557 + 10000))
        val task = TaskEntity(taskId, "Task", startDate, endDate)

        // when
        Mockito.`when`(mockTaskRepository.findById(taskId)).thenReturn(Optional.of(task))

        val savedTask = taskService.getById(taskId)

        // then
        Assertions.assertNotEquals(null, savedTask)
    }

    @Test
    fun `should return a null when filtering a non-existing task`() {
        // given
        val taskId = UUID.randomUUID()

        // when
        Mockito.`when`(mockTaskRepository.findById(taskId)).thenReturn(Optional.ofNullable(null))

        val savedTask = taskService.getById(taskId)

        // then
        Assertions.assertEquals(null, savedTask)
    }
}