package com.sincrohandover.api.modules.handover.application.service;

import com.sincrohandover.api.modules.handover.application.dto.HandoverRequest;
import com.sincrohandover.api.modules.handover.application.dto.HandoverResponse;

public interface HandoverService {

    HandoverResponse createHandover(HandoverRequest request);
}
