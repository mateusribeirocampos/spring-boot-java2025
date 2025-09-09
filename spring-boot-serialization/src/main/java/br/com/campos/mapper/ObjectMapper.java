package br.com.campos.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <SOURCE, TARGET> TARGET parseObject(SOURCE sourceObject, Class<TARGET> targetClass) {
        return mapper.map(sourceObject, targetClass);
    }

    public static <SOURCE, TARGET> List<TARGET> parseListObjects(List<SOURCE> sourceList, Class<TARGET> targetClass) {
        List<TARGET> targetList = new ArrayList<>();
        for (SOURCE sourceItem : sourceList) {
            targetList.add(mapper.map(sourceItem, targetClass));
        }
        return targetList;
    }
}