package com.allthingslego.inventory.barcodemapping;

import com.allthingslego.inventory.catalogset.CatalogSetDTO;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="barcodeMappingInfo", types = {BarcodeMapping.class})
public interface BarcodeMappingDTO {
    String getBarcode();
    Long getCatalogSetId();
}
