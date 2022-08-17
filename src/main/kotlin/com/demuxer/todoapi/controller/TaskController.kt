package com.demuxer.todoapi.controller

import com.demuxer.todoapi.util.Response
import com.demuxer.todoapi.dto.TaskDTO
import com.demuxer.todoapi.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class TaskController(
    @Autowired private val taskService: TaskService
) {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun getAll() : Response<List<TaskDTO>> {
        return Response(taskService.getAll())
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable id: UUID) : Response<TaskDTO> {
        return Response(taskService.getById(id))
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody task: TaskDTO) : Response<TaskDTO> {
        return Response(taskService.create(task))
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody task: TaskDTO) : Response<TaskDTO> {
        return Response(taskService.update(task))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: UUID) {
        taskService.delete(id)
    }
}