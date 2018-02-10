package com.sparrow.markdown.mark;

import com.sparrow.constant.CONSTANT;

/**
 * @author harry
 * @date 2018/2/6
 */
public enum MARK {
    H1(CONSTANT.ENTER_TEXT+"# ", CONSTANT.ENTER_TEXT,"<h1>%1$s</h1>"),
    H2(CONSTANT.ENTER_TEXT+"## ", CONSTANT.ENTER_TEXT,"<h2>%1$s</h2>"),
    H3(CONSTANT.ENTER_TEXT+"### ", CONSTANT.ENTER_TEXT,"<h3>%1$s</h3>"),
    H4(CONSTANT.ENTER_TEXT+"#### ", CONSTANT.ENTER_TEXT,"<h4>%1$s</h4>"),
    H5(CONSTANT.ENTER_TEXT+"##### ", CONSTANT.ENTER_TEXT,"<h5>%1$s</h5>"),
    H6(CONSTANT.ENTER_TEXT+"###### ", CONSTANT.ENTER_TEXT,"<h6>%1$s</h6>"),
    HORIZONTAL_LINE(CONSTANT.ENTER_TEXT+"---", CONSTANT.ENTER_TEXT,"<hr/>"),
    QUOTE(CONSTANT.ENTER_TEXT+">", CONSTANT.ENTER_TEXT,"<p class=\"quote\">%1$s</p>"),
    TAB(CONSTANT.ENTER_TEXT+"    ", null,"<p class=\"tab\">%1$s</p>"),
    CHECK_BOX(CONSTANT.ENTER_TEXT+"- [ ] ", CONSTANT.ENTER_TEXT,"<input type=\"checkbox\" disabled=\"\">%1$s"),
    DISABLE_CHECK_BOX(CONSTANT.ENTER_TEXT+"- [x] ", CONSTANT.ENTER_TEXT,"<input type=\"checkbox\" disabled=\"\" checked=\"checked\">%1$s"),
    CODE(CONSTANT.ENTER_TEXT+"```", "```","<p class=\"code\">%1$s</p>"),
    HIGHLIGHT(CONSTANT.ENTER_TEXT+"==", "==","<span class=\"highlight\">%1$s</span>"),
    UNDERLINE("++", "++","<span class=\"underline\">%1$s</span>"),
    ERASURE("~~", "~~","<span class=\"erasure\">%1$s</span>"),
    ITALIC("*", "*","<span class=\"italic\">%1$s</span>"),
    LITERARY("", "","%1$s"),
    BOLD("**", "**","<span class=\"bold\">%1$s</span>"),
    IMAGE("![.*](",")","<image src=\"%1$s\"/><label>%@$s</label>"),
    HYPER_LINK("-[.*](",")","<a href=\"%1$s\">%2$s</a>");


    private String start;
    private String end;
    private String format;

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getFormat() {
        return format;
    }

    MARK(String start, String end, String format) {
        this.start = start;
        this.end = end;
        this.format=format;
    }
}
