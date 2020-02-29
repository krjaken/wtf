package com.krjaken.wtf.api;

import com.krjaken.wtf.core.memory.MemoryService;
import com.krjaken.wtf.core.memory.db.dtos.LanguageDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class WtfRestApiService {
    private MemoryService memoryService;

    public WtfRestApiService(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/languages")
    public List<LanguageDto> getAllEmployees() {
        log.info("getAllEmployees");
        return memoryService.findAll();
    }

}
