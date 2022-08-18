package com.demuxer.todoapi.controller

import com.demuxer.todoapi.dto.SubTaskDTO
import com.demuxer.todoapi.service.SubTaskService
import com.demuxer.todoapi.util.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class SubTaskController(
    @Autowired private val subTaskService: SubTaskService
) {

    @GetMapping("/subtask")
    @ResponseStatus(HttpStatus.OK)
    fun getAll() : Response<List<SubTaskDTO>> {
        return Response(subTaskService.getAll())
    }

    @GetMapping("/subtask/{id}")
    fun getById(@PathVariable id: UUID) : Response<SubTaskDTO> {
        return Response(subTaskService.getById(id))
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody subTask: SubTaskDTO) : Response<SubTaskDTO> {
        return Response(subTaskService.create(subTask))
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody subTask: SubTaskDTO) : Response<SubTaskDTO> {
        return Response(subTaskService.update(subTask))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: UUID) {
        subTaskService.delete(id)
    }
}