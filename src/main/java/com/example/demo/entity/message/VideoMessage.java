package com.example.demo.entity.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoMessage {
    @ApiModelProperty("接收方帐号")
    private String ToUserName;
    @ApiModelProperty("开发者微信号")
    private String FromUserName;
    @ApiModelProperty("消息创建时间 （整型）")
    private String CreateTime;
    @ApiModelProperty("消息类型，语音为voice")
    private String MsgType;
    @ApiModelProperty("通过素材管理中的接口上传多媒体文件，得到的id")
    private String MediaId;
}
