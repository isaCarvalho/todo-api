package com.demuxer.todoapi.controller

import com.demuxer.todoapi.dto.SubTaskDTO
import com.demuxer.todoapi.service.SubTaskService
import com.demuxer.todoapi.util.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class SubTaskController(
    @Autowired private val subTaskService: SubTaskService
) {

    @GetMapping("/subtask")
    @ResponseStatus(HttpStatus.OK)
    fun getAll() : Response<List<SubTaskDTO>> {
        return Response(subTaskService.getAll())
    }
}