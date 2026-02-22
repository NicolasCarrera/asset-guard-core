package com.assetguard.core.model.asset;

import com.assetguard.core.model.maintenance.MaintenanceWorkOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "asset_capitalizations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AssetCapitalization {

    @Id
    @UuidGenerator
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "work_order_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MaintenanceWorkOrder workOrder;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "applied_date", nullable = false)
    private LocalDate appliedDate;
}
