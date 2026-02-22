package com.assetguard.core.model.custody;

import com.assetguard.core.model.asset.Asset;
import com.assetguard.core.model.shared.AssetCondition;
import com.assetguard.core.model.shared.CustodyStatus;
import com.assetguard.core.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "custody_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CustodyLog {

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
    @JoinColumn(name = "assigned_by_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User assignedBy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "custodian_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User custodian;

    @Column(name = "checkout_date", nullable = false)
    @Builder.Default
    private Instant checkoutDate = Instant.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "checkout_condition", nullable = false)
    private AssetCondition checkoutCondition;

    @Column(name = "acceptance_date")
    private Instant acceptanceDate;

    @Column(name = "signature_hash")
    private String signatureHash;

    @Column(name = "checkin_date")
    private Instant checkinDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "checkin_condition")
    private AssetCondition checkinCondition;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private CustodyStatus status = CustodyStatus.PENDING;
}
