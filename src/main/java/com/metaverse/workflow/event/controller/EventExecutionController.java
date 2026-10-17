package com.metaverse.workflow.event.controller;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.event.service.AleapMediaCoverageRequest;
import com.metaverse.workflow.event.service.EventExecutionSessionRequest;
import com.metaverse.workflow.event.service.EventDetailsService;
import com.metaverse.workflow.event.service.EventSessionService;
import com.metaverse.workflow.exceptions.DataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EventExecutionController {

    private final EventSessionService eventSessionService;

    private final EventDetailsService eventDetailsService;

    @PostMapping(value = "/event/execution/images", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSessionImages(@RequestPart("data") String data,
                                               @RequestPart(value = "image1", required = false) MultipartFile image1,
                                               @RequestPart(value = "image2", required = false) MultipartFile image2,
                                               @RequestPart(value = "image3", required = false) MultipartFile image3,
                                               @RequestPart(value = "image4", required = false) MultipartFile image4,
                                               @RequestPart(value = "image5", required = false) MultipartFile image5) throws ParseException, java.text.ParseException {
        log.info("Event controller save session images, data : {}", data);
        JSONParser parser = new JSONParser();
        EventExecutionSessionRequest request = parser.parse(data, EventExecutionSessionRequest.class);
        try {
            WorkflowResponse response = eventSessionService.saveSessionImages(request, image1, image2, image3, image4, image5);
            return ResponseEntity.ok(response);
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }

    @PostMapping(value = "/event/execution/media-coverage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMediaCoverage(@RequestPart("data") String data,
                                               @RequestPart(value = "image1", required = false) MultipartFile image1,
                                               @RequestPart(value = "image2", required = false) MultipartFile image2,
                                               @RequestPart(value = "image3", required = false) MultipartFile image3) throws ParseException {
        log.info("Event controller save event media, data : {}", data);
        JSONParser parser = new JSONParser();
        AleapMediaCoverageRequest request = parser.parse(data, AleapMediaCoverageRequest.class);
        try {
            WorkflowResponse response = eventDetailsService.saveMediaCoverage(request, image1, image2, image3);
            return ResponseEntity.ok(response);
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }
}
