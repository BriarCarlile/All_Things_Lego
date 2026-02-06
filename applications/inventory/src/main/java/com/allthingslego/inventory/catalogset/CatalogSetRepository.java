package com.allthingslego.inventory.catalogset;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel="catalog_set", path="catalog_set")
public interface CatalogSetRepository extends PagingAndSortingRepository<CatalogSet, Long>,
CrudRepository<CatalogSet, Long>{

    List<CatalogSet> findByBricklinkNo(@Param("bricklinkNo") String bricklinkNo);

}
