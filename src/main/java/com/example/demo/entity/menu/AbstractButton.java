package com.example.demo.entity.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbstractButton {

    @ApiModelProperty("按钮名称")
    private String name;
    @ApiModelProperty("按钮类型")
    private String type;
    @ApiModelProperty("click等点击类型必须")
    private String key;
}
