package com.example.demo.entity.template;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataValue {
    @ApiModelProperty("参数值")
    private String value;
    @ApiModelProperty("颜色")
    private String color;
}
