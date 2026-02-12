package com.allthingslego.inventory.ownedset;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(collectionResourceRel="owned_set", path="owned_set", excerptProjection=OwnedSetDTO.class)
public interface OwnedSetRepository extends PagingAndSortingRepository<OwnedSet, Long>,
CrudRepository<OwnedSet, Long>{

    Optional<OwnedSet> findByCatalogSetId(Long catalogSetId);

}