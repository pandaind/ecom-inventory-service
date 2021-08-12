package com.example.demo.inventory.service.mapper;

import com.example.demo.inventory.domain.Inventory;
import com.example.demo.inventory.service.dto.InventoryDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Inventory} and its DTO {@link InventoryDTO}.
 */
@Mapper(
        componentModel = "spring",
        uses = {})
public interface InventoryMapper extends EntityMapper<InventoryDTO, Inventory> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    InventoryDTO toDtoId(Inventory inventory);
}
