package org.automate.demand.ltc.util;

import org.automate.demand.ltc.constant.StringConstant;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class ErrorUtils {

    public static Supplier<NoSuchElementException> demandNotFoundError(){
        return () -> new NoSuchElementException(StringConstant.ErrorMessages.DEMAND_NOT_FOUND);
    }
}
