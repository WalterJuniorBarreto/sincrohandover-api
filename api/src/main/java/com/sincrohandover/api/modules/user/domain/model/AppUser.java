package com.sincrohandover.api.modules.user.domain.model;

import com.sincrohandover.api.shared.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "timezone", length = 50)
    private String timezone;

    @Column(name = "work_start", length = 10)
    private String workStart;

    @Column(name = "work_end", length = 10)
    private String workEnd;
}
