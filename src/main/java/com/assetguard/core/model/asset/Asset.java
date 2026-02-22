package com.assetguard.core.model.asset;

import com.assetguard.core.model.shared.AuditMetadata;
import com.assetguard.core.model.shared.LifecycleStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "assets")
@Audited
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(callSuper = true)
public class Asset extends AuditMetadata {

    @Id
    @UuidGenerator
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "parent_asset_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Asset parentAsset;

    @ManyToOne(optional = false)
    @JoinColumn(name = "model_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AssetModel model;

    @Column(name = "asset_tag", nullable = false, unique = true)
    private String assetTag;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "purchase_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal purchaseCost;

    @Column(name = "residual_value", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal residualValue = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "lifecycle_status", nullable = false)
    @Builder.Default
    private LifecycleStatus lifecycleStatus = LifecycleStatus.AVAILABLE;
}
