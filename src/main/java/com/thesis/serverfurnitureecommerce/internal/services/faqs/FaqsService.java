package com.thesis.serverfurnitureecommerce.internal.services.faqs;

import com.thesis.serverfurnitureecommerce.model.dto.FaqsDTO;

import java.util.List;

public interface FaqsService {
    List<FaqsDTO> getAllFaqs();
}