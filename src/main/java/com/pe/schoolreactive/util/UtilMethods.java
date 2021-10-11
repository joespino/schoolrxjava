package com.pe.schoolreactive.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.function.LongUnaryOperator;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilMethods {

    public static final Supplier<LocalDateTime> getTimeNow = LocalDateTime::now;
    public static final LongUnaryOperator getIncrementIdentifier = s -> s + 1;
}
