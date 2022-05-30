package com.dsm.dcs.dto.request;

import com.dsm.dcs.entity.Action;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SendEmailRequest {

    @NotNull(message = "action은 Null을 허용하지 않습니다.")
    private Action action;

    @NotBlank(message = "value는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String value;

}
