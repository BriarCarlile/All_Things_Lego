package com.allthingslego.inventory.ownedset;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel="owned_set", path="owned_set")
public interface OwnedSetRepository extends PagingAndSortingRepository<OwnedSet, Long>,
CrudRepository<OwnedSet, Long>{

    List<OwnedSet> findByCatalogSetId(@Param("catalogSetId") Long catalogSetId);

}