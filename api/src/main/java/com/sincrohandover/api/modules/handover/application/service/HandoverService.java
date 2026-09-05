package com.sincrohandover.api.modules.handover.application.service;

import com.sincrohandover.api.modules.handover.application.dto.HandoverRequest;
import com.sincrohandover.api.modules.handover.application.dto.HandoverResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface HandoverService {

    HandoverResponse createHandover(HandoverRequest request);
    Page<HandoverResponse> getHandovers(UUID projectId, String status, Pageable pageable);
}
