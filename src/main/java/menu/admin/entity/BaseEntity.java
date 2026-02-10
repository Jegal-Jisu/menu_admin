package menu.admin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private String  createTime;
    @LastModifiedDate
    @Column(updatable = false)
    private String updateTime;

    @PrePersist
    void prePersist() {
        createTime = LocalDateTime.now().toString();
        updateTime = LocalDateTime.now().toString();
    }

    @PreUpdate
    void preUpdate() {
        updateTime = LocalDateTime.now().toString();
    }

    public static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
