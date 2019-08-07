package com.example.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticlesMessage {
    @ApiModelProperty("接收方帐号")
    private String ToUserName;
    @ApiModelProperty("开发者微信号")
    private String FromUserName;
    @ApiModelProperty("消息创建时间 （整型）")
    private String CreateTime;
    @ApiModelProperty("消息类型，文本为text")
    private String MsgType;
    @ApiModelProperty("回复的消息内容")
    private String Content;
}
