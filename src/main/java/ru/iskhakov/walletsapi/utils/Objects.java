package ru.iskhakov.walletsapi.utils;

import lombok.experimental.UtilityClass;
import ru.iskhakov.walletsapi.dto.OperationType;

import java.util.function.Function;

@UtilityClass
public class Objects {

    public static boolean isNullOrEmpty(String str) {
        return com.google.common.base.Strings.isNullOrEmpty(str);
    }

    public static OperationType applyOperationTypeIfNotNull(String str, Function<String, OperationType> action) {
        if (isNullOrEmpty(str)) {
            return OperationType.UNDEFINED;
        }
        return action.apply(str);
    }
}