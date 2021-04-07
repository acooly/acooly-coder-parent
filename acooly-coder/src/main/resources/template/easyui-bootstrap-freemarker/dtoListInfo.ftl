/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
package ${nameScheme.dto.packageName};

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.core.common.facade.InfoBase;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;

/**
* ${table.comment} List DTO
* For facade and openApi
*
* @author ${configuration.codeAuthor}
* @date ${datetime("yyyy-MM-dd HH:mm:ss")}
*/
@Getter
@Setter
public class ${nameScheme.domainClassName}ListInfo extends InfoBase {

    @NotNull
    @OpenApiField(desc = "ID", constraint = "物理ID", demo = "1", ordinal = 1)
    private Long id;

}
