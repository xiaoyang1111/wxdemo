package com.example.demo.entity.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    @ApiModelProperty("opeenId")
    private String openid;
    @ApiModelProperty("用户昵称")
    private String nickname;
    @ApiModelProperty("用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    private String sex;
    @ApiModelProperty("用户个人资料填写的省份")
    private String province;
    @ApiModelProperty("普通用户个人资料填写的城市")
    private String city;
    @ApiModelProperty("国家，如中国为CN")
    private String country;
    @ApiModelProperty("用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像）" +
            "，用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。")
    private String headimgurl;
    @ApiModelProperty("用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）")
    private String privilege;
    @ApiModelProperty("只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段")
    private String unionid;
}
