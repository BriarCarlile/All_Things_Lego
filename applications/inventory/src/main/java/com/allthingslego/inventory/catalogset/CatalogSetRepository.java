package com.allthingslego.inventory.catalogset;

import com.allthingslego.inventory.ownedset.OwnedSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.Optional;


@RepositoryRestResource(collectionResourceRel="catalog_set", path="catalog_set", excerptProjection=CatalogSetDTO.class)
public interface CatalogSetRepository extends PagingAndSortingRepository<CatalogSet, Long>,
CrudRepository<CatalogSet, Long>{
    Collection<? extends OwnedSet> searchCatalogSetByBricklinkNoOrName(String bricklinkNo, String name);
}
