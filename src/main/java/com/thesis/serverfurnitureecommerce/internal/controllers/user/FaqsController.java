package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.faqs.FaqsService;
import com.thesis.serverfurnitureecommerce.model.dto.FaqsDTO;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.utils.annotation.ApiMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/faqs")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FaqsController {
    FaqsService faqsService;

    @ApiMessage("Get all faqs")
    @GetMapping
    public ResponseEntity<APIResponse<List<FaqsDTO>>> getFaqs() {
        log.info("Get all faqs");
        List<FaqsDTO> faqsDTOS = faqsService.getAllFaqs();
        return ResponseBuilder.buildResponse(faqsDTOS, faqsDTOS != null ? ErrorCode.FOUND : ErrorCode.NOT_FOUND);
    }
}
