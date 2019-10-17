package com.ssafy.obosa.model.domain.auditing;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Data
public class DateEntity
{
    //@Temporal
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime registeredDate;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
