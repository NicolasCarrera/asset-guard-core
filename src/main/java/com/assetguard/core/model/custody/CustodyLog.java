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
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "custody_logs")
public class CustodyLog {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @ManyToOne(optional = false)
    @JoinColumn(name = "assigned_by_id", nullable = false)
    private User assignedBy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "custodian_id", nullable = false)
    private User custodian;

    @Column(name = "checkout_date", nullable = false)
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
    private CustodyStatus status = CustodyStatus.PENDING;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public User getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(User assignedBy) {
        this.assignedBy = assignedBy;
    }

    public User getCustodian() {
        return custodian;
    }

    public void setCustodian(User custodian) {
        this.custodian = custodian;
    }

    public Instant getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Instant checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public AssetCondition getCheckoutCondition() {
        return checkoutCondition;
    }

    public void setCheckoutCondition(AssetCondition checkoutCondition) {
        this.checkoutCondition = checkoutCondition;
    }

    public Instant getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Instant acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public String getSignatureHash() {
        return signatureHash;
    }

    public void setSignatureHash(String signatureHash) {
        this.signatureHash = signatureHash;
    }

    public Instant getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Instant checkinDate) {
        this.checkinDate = checkinDate;
    }

    public AssetCondition getCheckinCondition() {
        return checkinCondition;
    }

    public void setCheckinCondition(AssetCondition checkinCondition) {
        this.checkinCondition = checkinCondition;
    }

    public CustodyStatus getStatus() {
        return status;
    }

    public void setStatus(CustodyStatus status) {
        this.status = status;
    }
}
