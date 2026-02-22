package com.assetguard.core.model.maintenance;

import com.assetguard.core.model.asset.Asset;
import com.assetguard.core.model.shared.AuditMetadata;
import com.assetguard.core.model.shared.WorkOrderStatus;
import com.assetguard.core.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
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
@Table(name = "maintenance_work_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(callSuper = true)
public class MaintenanceWorkOrder extends AuditMetadata {

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "reported_by_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User reportedBy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "technician_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User technician;

    @Column(name = "issue_description", nullable = false, columnDefinition = "TEXT")
    private String issueDescription;

    @Column(name = "intervention_cost", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal interventionCost = BigDecimal.ZERO;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "completion_date")
    private Instant completionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private WorkOrderStatus status = WorkOrderStatus.OPEN;
}
