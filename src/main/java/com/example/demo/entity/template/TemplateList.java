package com.example.demo.entity.template;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateList {
    @ApiModelProperty("接口调用凭证")
    private String access_token;
    @ApiModelProperty("模板ID")
    private String template_id;
    @ApiModelProperty("模板标题")
    private String title;
    @ApiModelProperty("模板所属行业的一级行业")
    private String primary_industry;
    @ApiModelProperty("模板所属行业的二级行业")
    private String deputy_industry;
    @ApiModelProperty("模板内容")
    private String content;
    @ApiModelProperty("模板示例")
    private String example;

}
