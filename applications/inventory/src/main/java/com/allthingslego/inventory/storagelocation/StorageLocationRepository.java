package com.allthingslego.inventory.storagelocation;

import com.allthingslego.inventory.catalogset.CatalogSetDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel="storage_location", path="storage_location", excerptProjection= StorageLocationDTO.class)
public interface StorageLocationRepository extends PagingAndSortingRepository<StorageLocation, Long>,
CrudRepository<StorageLocation, Long>{

    // StorageLocation findByCode(@Param("code") String code);

}