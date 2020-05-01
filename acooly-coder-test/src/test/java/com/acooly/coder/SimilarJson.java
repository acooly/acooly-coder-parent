/**
 * acooly-components-feature
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-04-29 16:52
 */
package com.acooly.coder;

import com.acooly.coder.domain.ColumnComment;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.JsonMapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangpu
 * @date 2020-04-29 16:52
 */
@Slf4j
public class SimilarJson {

    public static void main(String[] args) throws Exception {
        // {title:'证件类型', type:'option',options:{cert:身份证,pass:护照,other:其他}}
        //
        String similarJson = "{title:客户类型,type:option,options:{normal:普通,vip:重要,sepc:特别}}";
        similarJson = Strings.replace(similarJson, "’", "'");
        similarJson = Strings.replace(similarJson, "‘", "'");
        log.info("similarJson:{}", similarJson);
        ObjectMapper objectMapper = JsonMapper.nonEmptyMapper().getMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        ColumnComment columnComment = objectMapper.readValue(similarJson, ColumnComment.class);
//        JSONObject jsonObject = JSON.parseObject(similarJson);
        log.info("ColumnComment:{}", columnComment);
    }
}
