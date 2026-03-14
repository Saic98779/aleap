package com.metaverse.workflow.files.controller;

import com.metaverse.workflow.files.service.FileService;
import com.metaverse.workflow.security.ApplicationAPIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/files")
public class FilesController {

    private final FileService fileService;


    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApplicationAPIResponse<List<String>>> uploadFiles(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam String directory) {

        List<String> storedPaths = files.stream()
                .map(file -> fileService.uploadFile(file, directory))
                .toList();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApplicationAPIResponse.<List<String>>builder()
                        .data(storedPaths)
                        .message("Files uploaded successfully")
                        .code(HttpStatus.CREATED.value())
                        .success(true)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApplicationAPIResponse<List<String>>> listFiles() {
        List<String> files = fileService.listFiles();
        return ResponseEntity.ok(ApplicationAPIResponse.<List<String>>builder()
                .data(files)
                .message("Files fetched")
                .code(HttpStatus.OK.value())
                .success(true)
                .build());
    }


    @DeleteMapping(params = "path")
    public ResponseEntity<ApplicationAPIResponse<Void>> deleteFile(@RequestParam String path) {
        boolean removed = fileService.deleteFile(path);
        if (removed) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    ApplicationAPIResponse.<Void>builder()
                            .message("File deleted")
                            .code(HttpStatus.OK.value())
                            .success(true)
                            .build()
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApplicationAPIResponse.<Void>builder()
                        .message("File not found")
                        .code(HttpStatus.NOT_FOUND.value())
                        .success(false)
                        .build()
        );
    }

}
