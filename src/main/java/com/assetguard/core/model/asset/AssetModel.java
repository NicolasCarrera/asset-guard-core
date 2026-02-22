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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "asset_models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AssetModel {

    @Id
    @UuidGenerator
    @Column(name = "id")
    @EqualsAndHashCode.Include
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
    @Builder.Default
    private RecordStatus recordStatus = RecordStatus.ACTIVE;
}
