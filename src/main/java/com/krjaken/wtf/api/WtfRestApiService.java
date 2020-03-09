package com.krjaken.wtf.api;

import com.krjaken.wtf.api.core.enums.LanguageEnum;
import com.krjaken.wtf.core.curiosity.CuriosityCore;
import com.krjaken.wtf.core.languages.dtos.ConceptDto;
import com.krjaken.wtf.core.memory.MemoryService;
import com.krjaken.wtf.core.memory.db.dtos.LanguageDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class WtfRestApiService {
    private static final String UPLOADED_FOLDER = "src/main/resources/files/";
    private MemoryService memoryService;
    private CuriosityCore curiosityCore;

    public WtfRestApiService(MemoryService memoryService, CuriosityCore curiosityCore) {
        this.memoryService = memoryService;
        this.curiosityCore = curiosityCore;
    }

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/getLanguages")
    public ResponseEntity<List<LanguageDto>> getAllEmployees() {
        log.info("getAllEmployees");

        List<LanguageDto> languages;
        try {
            languages = memoryService.getLanguages();
        } catch (Exception e) {
            return new ResponseEntity("Find languages error", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<List<LanguageDto>>(languages, HttpStatus.OK);
    }

    @PutMapping("/putLenguage")
    public ResponseEntity<?> putLanguage(LanguageDto languageDto) {

        if (languageDto == null) {
            return new ResponseEntity("Please fill all fields LegalDto", HttpStatus.OK);
        }
        memoryService.createLanguage(languageDto);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @PutMapping("/putWordAnalise")
    public ResponseEntity<?> putWordAnalise() {
        curiosityCore.searchInfo();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @PostMapping("/insertConcept")
    public ResponseEntity<?> pushConcept(LanguageEnum languageEnum, @RequestBody ConceptDto conceptDto) {

        if (languageEnum == null) {
            return new ResponseEntity("You must select a languageEnum!", HttpStatus.OK);
        }

        LanguageDto language = memoryService.getLanguage(languageEnum);

        if (language == null) {
            return new ResponseEntity("Language " + languageEnum + " not insert in DB", HttpStatus.OK);
        }

        try {
            memoryService.inputConcept(conceptDto, language);
            return new ResponseEntity<>("Concept added", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/uploadLanguageFile")
    public ResponseEntity<?> uploadLanguageFile(LanguageEnum languageEnum, @RequestParam("file") MultipartFile uploadfile) {
        log.info("createRussianLanguage {}", languageEnum);

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("You must select a file!", HttpStatus.OK);
        }

        if (languageEnum == null) {
            return new ResponseEntity("You must select a languageEnum!", HttpStatus.OK);
        }

        LanguageDto language = memoryService.getLanguage(languageEnum);

        if (language == null) {
            return new ResponseEntity("Language " + languageEnum + " not insert in DB", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(Arrays.asList(uploadfile));
            memoryService.inputLanguageData(UPLOADED_FOLDER + "/" + uploadfile.getOriginalFilename(), language);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " + uploadfile.getOriginalFilename(), new HttpHeaders(),
                HttpStatus.OK);
    }

    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; // next pls
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }

    }

}
