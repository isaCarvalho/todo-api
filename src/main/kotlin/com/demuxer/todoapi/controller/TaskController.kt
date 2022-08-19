package com.demuxer.todoapi.controller

import com.demuxer.todoapi.util.Response
import com.demuxer.todoapi.dto.TaskDTO
import com.demuxer.todoapi.exception.ResourceNotFoundException
import com.demuxer.todoapi.service.TaskService
import com.demuxer.todoapi.util.RESOURCE_NOT_FOUND
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class TaskController(
    @Autowired private val taskService: TaskService
) {

    @GetMapping("/task")
    @ResponseStatus(HttpStatus.OK)
    fun getAll() : Response<List<TaskDTO>> {
        return Response(taskService.getAll())
    }

    @GetMapping("/task/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable id: UUID) : Response<TaskDTO> {
        val task = taskService.getById(id) ?:
            throw ResourceNotFoundException("$RESOURCE_NOT_FOUND [task=$id]")

        return Response(task)
    }

    @PostMapping("/task")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody task: TaskDTO) : Response<TaskDTO> {
        return Response(taskService.create(task))
    }

    @PutMapping("/task")
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody task: TaskDTO) {
        taskService.update(task)
    }

    @DeleteMapping("/task/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: UUID) {
        val task = taskService.getById(id) ?:
            throw ResourceNotFoundException("$RESOURCE_NOT_FOUND [task=$id]")

        taskService.delete(task)
    }
}