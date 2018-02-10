package com.sparrow.markdown.parser.impl;

import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.parser.MarkParser;

/**
 * @author harry
 * @date 2018/2/6
 */
public class H2Parser implements MarkParser {
    @Override
    public void parse(MarkContext markContext) {
        markContext.parse(this.mark());
    }


    @Override public MARK mark() {
        return MARK.H2;
    }
}
