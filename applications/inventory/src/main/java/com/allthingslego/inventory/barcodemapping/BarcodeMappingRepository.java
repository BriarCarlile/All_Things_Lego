package com.allthingslego.inventory.barcodemapping;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel="barcode", path="barcode", excerptProjection=BarcodeMappingDTO.class)
public interface BarcodeMappingRepository extends PagingAndSortingRepository<BarcodeMapping, String>,
CrudRepository<BarcodeMapping, String>{

    // BarcodeMapping findByCatalogSetId(@Param("catalogSetId") Long catalogSetId);

}