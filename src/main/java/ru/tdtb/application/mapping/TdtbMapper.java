package ru.tdtb.application.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TdtbMapper {
    @Autowired
    private org.dozer.Mapper mapper;

    public <T> T map(Object o, Class<T> clazz) {
        return map(o, clazz, null);
    }

    public <T> T map(Object o, Class<T> clazz, String mapId) {
        if (o == null) return null;
        return mapper.map(o, clazz, mapId);
    }

    public <T> List<T> map(Collection<?> collection, Class<T> clazz) {
        if (collection == null) return null;
        return collection.stream()
                .map(o -> map(o, clazz))
                .collect(Collectors.toList());
    }
}
