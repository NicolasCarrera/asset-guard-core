package com.assetguard.core.model.asset;

import static org.junit.jupiter.api.Assertions.*;

import com.assetguard.core.model.shared.AssetCategory;
import com.assetguard.core.model.shared.LifecycleStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class AssetTest {

    @Test
    void shouldCreateAssetWithDefaults() {
        Asset asset = new Asset();

        assertEquals(LifecycleStatus.AVAILABLE, asset.getLifecycleStatus());
        assertEquals(BigDecimal.ZERO, asset.getResidualValue());
    }

    @Test
    void shouldSetAndGetAssetProperties() {
        Asset asset = new Asset();
        AssetModel model = new AssetModel();
        model.setCategory(AssetCategory.IT_HARDWARE);
        model.setManufacturer("Dell");
        model.setModelName("Latitude 5550");
        model.setDepreciationYears(3);

        asset.setAssetTag("AST-001");
        asset.setSerialNumber("SN123456");
        asset.setModel(model);
        asset.setPurchaseDate(LocalDate.of(2026, 1, 1));
        asset.setPurchaseCost(new BigDecimal("1500.00"));
        asset.setResidualValue(new BigDecimal("100.00"));
        asset.setLifecycleStatus(LifecycleStatus.ASSIGNED);

        assertEquals("AST-001", asset.getAssetTag());
        assertEquals("SN123456", asset.getSerialNumber());
        assertEquals(model, asset.getModel());
        assertEquals(LocalDate.of(2026, 1, 1), asset.getPurchaseDate());
        assertEquals(new BigDecimal("1500.00"), asset.getPurchaseCost());
        assertEquals(new BigDecimal("100.00"), asset.getResidualValue());
        assertEquals(LifecycleStatus.ASSIGNED, asset.getLifecycleStatus());
    }
}
