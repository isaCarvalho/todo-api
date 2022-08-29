package com.demuxer.todoapi.service

import com.demuxer.todoapi.entity.SubTaskEntity
import com.demuxer.todoapi.entity.TaskEntity
import com.demuxer.todoapi.repository.SubTaskRepository
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
class SubTaskServiceTest {

    @Mock
    lateinit var mockSubTaskRepository: SubTaskRepository

    @InjectMocks
    lateinit var subTaskService: SubTaskService

    @BeforeEach
    fun setUp() {
        Mockito.reset(mockSubTaskRepository)
    }

    @Test
    fun `should return all of the subtasks`() {
        // given
        val startDate = Date()
        val endDate = Date.from(Instant.ofEpochMilli(1661781557 + 10000))

        val task = TaskEntity(UUID.randomUUID(), "Task", startDate, endDate)
        val subTasks = listOf(SubTaskEntity(UUID.randomUUID(), "First subtask", task))

        // when
        Mockito.`when`(mockSubTaskRepository.findAll()).thenReturn(subTasks)

        val savedSubTasks = subTaskService.getAll()

        // then
        Assertions.assertEquals(1, savedSubTasks.size)
    }

    @Test
    fun `should return a subTask by id`() {
        // given
        val startDate = Date()
        val endDate = Date.from(Instant.ofEpochMilli(1661781557 + 10000))
        val task = TaskEntity(UUID.randomUUID(), "Task", startDate, endDate)

        val subTaskId = UUID.randomUUID()
        val subTask = SubTaskEntity(subTaskId, "First subtask", task)

        // when
        Mockito.`when`(mockSubTaskRepository.findById(subTaskId)).thenReturn(Optional.of(subTask))

        val savedSubTask = subTaskService.getById(subTaskId)

        // then
        Assertions.assertNotEquals(null, savedSubTask)
    }

    @Test
    fun `should return a null when filtering a non-existing subTask`() {
        // given
        val subTaskId = UUID.randomUUID()

        // when
        Mockito.`when`(mockSubTaskRepository.findById(subTaskId)).thenReturn(Optional.ofNullable(null))

        val savedSubTask = subTaskService.getById(subTaskId)

        // then
        Assertions.assertEquals(null, savedSubTask)
    }
}