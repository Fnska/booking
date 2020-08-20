package io.fnska.booking.converter;

import org.springframework.data.domain.Page;


public interface Converter<Entity, Dto> {
    Dto entityToDto(Entity entity);
    Entity dtoToEntity(Dto dto);
    Page<Dto> entityToDto(Page<Entity> reservation);
}
