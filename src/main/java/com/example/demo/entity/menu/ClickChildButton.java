package com.example.demo.entity.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClickChildButton extends ChildButton{

    @ApiModelProperty("事件按钮key")
    private String key;
}
