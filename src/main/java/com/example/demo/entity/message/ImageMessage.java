package com.example.demo.entity.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Builder
public class ImageMessage {
    @ApiModelProperty("接收方帐号")
    private String ToUserName;
    @ApiModelProperty("开发者微信号")
    private String FromUserName;
    @ApiModelProperty("消息创建时间 （整型）")
    private String CreateTime;
    @ApiModelProperty("消息类型，文本为text")
    private String MsgType;
    @XmlElement(name="image")
    public List<Image> images;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Image{
        @ApiModelProperty("回复的消息内容")
        private String MediaId;
    }

}
