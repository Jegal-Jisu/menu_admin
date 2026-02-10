package menu.admin.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class StoreInfoEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 256, nullable = false)
    private String storeName;

    @Column
    private String storeUrl;

    @Column
    private String address;

    @Column
    private String pattern;

    @Column
    private int snsType;

    @Column
    private String snsId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeInfo")
    private List<MenuEntity> menus;
}
