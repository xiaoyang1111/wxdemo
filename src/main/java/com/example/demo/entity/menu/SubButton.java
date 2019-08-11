package com.example.demo.entity.menu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubButton {
    @ApiModelProperty("按钮名称")
    private String name;
    @ApiModelProperty("按钮")
    public List<ChildButton> sub_button;
}
