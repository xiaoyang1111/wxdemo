package com.example.demo.entity.template;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTemplate {
    @ApiModelProperty("接收者openid")
    private String touser;
    @ApiModelProperty("模板ID")
    private String template_id;
    @ApiModelProperty("模板跳转链接")
    private String url;
    @ApiModelProperty("跳小程序所需数据")
    private Miniprogram miniprogram;
    @ApiModelProperty("模板数据")
    private Map<String,DataValue> data;

}
