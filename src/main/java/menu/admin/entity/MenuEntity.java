package menu.admin.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class MenuEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    // 1

    @Column(nullable = false)
    private Date targetDate;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private StoreInfoEntity storeInfo;
}
