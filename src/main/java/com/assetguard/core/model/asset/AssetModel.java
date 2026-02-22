package com.assetguard.core.model.asset;

import com.assetguard.core.model.shared.AssetCategory;
import com.assetguard.core.model.shared.RecordStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "asset_models")
public class AssetModel {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private AssetCategory category;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "depreciation_years", nullable = false)
    private Integer depreciationYears;

    @Enumerated(EnumType.STRING)
    @Column(name = "record_status", nullable = false)
    private RecordStatus recordStatus = RecordStatus.ACTIVE;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AssetCategory getCategory() {
        return category;
    }

    public void setCategory(AssetCategory category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getDepreciationYears() {
        return depreciationYears;
    }

    public void setDepreciationYears(Integer depreciationYears) {
        this.depreciationYears = depreciationYears;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }
}
