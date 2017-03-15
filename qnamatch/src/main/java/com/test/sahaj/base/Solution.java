package com.test.sahaj.base;

/**
 * Created by ashish on 2/3/17.
 */
public interface Solution<InpType extends Input, OutType extends Output> {
    OutType solve(InpType inpType);
}
