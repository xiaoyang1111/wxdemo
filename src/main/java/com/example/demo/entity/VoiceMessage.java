package com.example.demo.entity;

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
@Builder
public class VoiceMessage {

    @ApiModelProperty("接收方帐号")
    private String ToUserName;
    @ApiModelProperty("开发者微信号")
    private String FromUserName;
    @ApiModelProperty("消息创建时间 （整型）")
    private String CreateTime;
    @ApiModelProperty("消息类型，语音为voice")
    private String MsgType;
    @ApiModelProperty("图文消息个数；当用户发送文本、图片、视频、图文、地理位置这五种消息时，开发者只能回复1条图文消息；" +
            "其余场景最多可回复8条图文消息")
    private String ArticleCount;
    @ApiModelProperty("图文消息信息")
    @XmlElement(name ="Articles")
    private List<Articles> Articles;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @Builder
    public static class Articles {
        @ApiModelProperty("图文消息信息")
        @XmlElement(name = "item")
        private List<Articles.Item> items;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class Item {
            @ApiModelProperty("视频消息的标题")
            private String Title;
            @ApiModelProperty("视频消息的描述")
            private String Description;
            @ApiModelProperty("音乐链接")
            private String MusicURL;
            @ApiModelProperty("高质量音乐链接，WIFI环境优先使用该链接播放音乐")
            private String HQMusicUrl;
            @ApiModelProperty("缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id")
            private String ThumbMediaId;
        }
    }
}
