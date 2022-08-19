package com.demuxer.todoapi.controller

import com.demuxer.todoapi.dto.SubTaskDTO
import com.demuxer.todoapi.exception.ResourceNotFoundException
import com.demuxer.todoapi.service.SubTaskService
import com.demuxer.todoapi.util.RESOURCE_NOT_FOUND
import com.demuxer.todoapi.util.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
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
        val subTask = subTaskService.getById(id) ?:
        throw ResourceNotFoundException("$RESOURCE_NOT_FOUND [subtask=$id]")

        return Response(subTask)
    }

    @PostMapping("/subtask")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody subTask: SubTaskDTO) : Response<SubTaskDTO> {
        return Response(subTaskService.save(subTask))
    }

    @PutMapping("/subtask")
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody subTask: SubTaskDTO) : Response<SubTaskDTO> {
        return Response(subTaskService.save(subTask))
    }

    @DeleteMapping("/subtask/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: UUID) {
        val subTask = subTaskService.getById(id) ?:
            throw ResourceNotFoundException("$RESOURCE_NOT_FOUND [subtask=$id]")

        subTaskService.delete(subTask)
    }
}