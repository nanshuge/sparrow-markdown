/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sparrow.markdown.parser.impl;

import com.sparrow.constant.magic.DIGIT;
import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.mark.MarkEntity;
import com.sparrow.markdown.parser.MarkParser;

import static com.sparrow.markdown.mark.MarkContext.BORROWABLE_BLANK;

/**
 * @author by harry
 */
public abstract class AbstractWithEndTagParser implements MarkParser {

    @Override public boolean detectStartMark(MarkContext markContext) {

        if (markContext.getContent().startsWith(this.mark().getStart(), markContext.getCurrentPointer())) {
            return true;
        }
        int currentPointer = markContext.getCurrentPointer();
        String content = markContext.getContent();
        //borrowable blank shared with last blank
        if (!BORROWABLE_BLANK.contains(this.mark())) {
            return false;
        }
        //for next -=1 is not NEGATIVE
        if (currentPointer < 1) {
            return false;
        }
        currentPointer -= 1;
        //borrow blank so first latter must be \n
        if (content.charAt(currentPointer) != '\n') {
            return false;
        }
        //if borrow blank later although not equal then not match
        if (!content.startsWith(this.mark().getStart(), currentPointer)) {
            return false;
        }
        return true;
    }

    @Override public MarkEntity validate(MarkContext markContext) {
        int startIndex = markContext.getCurrentPointer() + this.mark().getStart().length();
        int endMarkIndex = markContext.getContent().indexOf(this.mark().getEnd(), startIndex);
        if (endMarkIndex <= 1) {
            return null;
        }
        if (markContext.getContent().charAt(startIndex) == '\n' || markContext.getContent().charAt(endMarkIndex - 1) == '\n') {
            return null;
        }

        if (endMarkIndex <= startIndex) {
            return null;
        }
        String content = markContext.getContent().substring(markContext.getCurrentPointer()
            + this.mark().getStart().length(), endMarkIndex);
        if (content.contains("\n\n")) {
            return null;
        }
        MarkEntity markEntity = MarkEntity.createCurrentMark(this.mark(), endMarkIndex);
        markEntity.setContent(content);
        return markEntity;
    }

    @Override public void parse(MarkContext markContext) {
        String content = markContext.getCurrentMark().getContent();
        //如果包含复杂结构，至少需要两个字符
        if (content.length() <= 2 || MarkContext.CHILD_MARK_PARSER.get(this.mark()) == null) {
            markContext.append(String.format(this.mark().getFormat(), content));
            markContext.setPointer(markContext.getCurrentMark().getEnd());
            if (this.mark().getEnd() != null) {
                markContext.skipPointer(this.mark().getEnd().length());
            }
            return;
        }
        MarkContext innerContext = new MarkContext(content);
        innerContext.setParentMark(this.mark());
        MarkdownParserComposite.getInstance().parse(innerContext);
        markContext.append(String.format(this.mark().getFormat(), innerContext.getHtml()));
        markContext.setPointer(markContext.getCurrentMark().getEnd() + this.mark().getEnd().length());
    }
}