package com.sincrohandover.api.modules.user.application.service;

import com.sincrohandover.api.modules.user.application.dto.AppUserRequest;
import com.sincrohandover.api.modules.user.application.dto.AppUserResponse;

public interface AppUserService {
    AppUserResponse createUser(AppUserRequest request);
    AppUserResponse getByEmail(String email);
}
