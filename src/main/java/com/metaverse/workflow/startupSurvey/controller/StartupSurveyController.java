package com.metaverse.workflow.startupSurvey.controller;

import com.metaverse.workflow.startupSurvey.dto.ApiResponse;
import com.metaverse.workflow.startupSurvey.dto.StartupSurveyDTO;
import com.metaverse.workflow.startupSurvey.service.StartupSurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/startup-surveys")
@RequiredArgsConstructor
public class StartupSurveyController {

    private final StartupSurveyService startupSurveyService;

    /**
     * Save startup survey as draft
     * POST /api/v1/startup-surveys/draft
     */
    @PostMapping("/draft")
    public ResponseEntity<ApiResponse<StartupSurveyDTO>> saveDraft(@RequestBody StartupSurveyDTO startupSurveyDTO) {
        try {
            StartupSurveyDTO savedSurvey = startupSurveyService.saveDraft(startupSurveyDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(savedSurvey, "Survey saved as draft successfully", HttpStatus.CREATED.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to save survey as draft: " + e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    /**
     * Get survey by ID
     * GET /api/v1/startup-surveys/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StartupSurveyDTO>> getSurveyById(@PathVariable Long id) {
        try {
            StartupSurveyDTO survey = startupSurveyService.getSurveyById(id);
            return ResponseEntity.ok(ApiResponse.success(survey, "Survey retrieved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage(), HttpStatus.NOT_FOUND.value()));
        }
    }

    /**
     * Get all surveys with pagination
     * GET /api/v1/startup-surveys?page=0&size=10&sort=createdAt,desc
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<StartupSurveyDTO>>> getAllSurveys(Pageable pageable) {
        try {
            Page<StartupSurveyDTO> surveys = startupSurveyService.getAllSurveys(pageable);
            return ResponseEntity.ok(ApiResponse.success(surveys, "Surveys retrieved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve surveys: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    /**
     * Update survey (full update)
     * PUT /api/v1/startup-surveys/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StartupSurveyDTO>> updateSurvey(
            @PathVariable Long id,
            @RequestBody StartupSurveyDTO startupSurveyDTO) {
        try {
            StartupSurveyDTO updatedSurvey = startupSurveyService.updateSurvey(id, startupSurveyDTO);
            return ResponseEntity.ok(ApiResponse.success(updatedSurvey, "Survey updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to update survey: " + e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    /**
     * Partial update (draft mode)
     * PATCH /api/v1/startup-surveys/{id}
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<StartupSurveyDTO>> partialUpdateSurvey(
            @PathVariable Long id,
            @RequestBody StartupSurveyDTO startupSurveyDTO) {
        try {
            startupSurveyDTO.setId(id);
            StartupSurveyDTO updatedSurvey = startupSurveyService.saveDraft(startupSurveyDTO);
            return ResponseEntity.ok(ApiResponse.success(updatedSurvey, "Survey updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to update survey: " + e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    /**
     * Delete survey by ID
     * DELETE /api/v1/startup-surveys/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSurvey(@PathVariable Long id) {
        try {
            startupSurveyService.deleteSurvey(id);
            return ResponseEntity.ok(ApiResponse.success(null, "Survey deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to delete survey: " + e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}
