package com.campos.testspringboot.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <SOURCE, TARGET> TARGET parseObject(SOURCE sourceObject, Class<TARGET> targetClass) {
        if (sourceObject == null) return null;
        return mapper.map(sourceObject, targetClass);
    }

    public static <SOURCE, TARGET> List<TARGET> parseListObjects(List<SOURCE> sourceList, Class<TARGET> targetClass) {
        if (sourceList == null) return new ArrayList<>();

        List<TARGET> targetList = new ArrayList<>();
        for (SOURCE sourceItem : sourceList) {
            if (sourceItem != null){
                targetList.add(mapper.map(sourceItem, targetClass));
            }
        }
        return targetList;
    }

}
