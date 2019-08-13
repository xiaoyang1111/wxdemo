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
public class Miniprogram {
    @ApiModelProperty("所需跳转到的小程序appid")
    private String appid;
    @ApiModelProperty("所需跳转到小程序的具体页面路径")
    private String pagepath;
}
