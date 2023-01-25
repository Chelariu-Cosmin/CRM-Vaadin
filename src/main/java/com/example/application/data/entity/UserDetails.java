package com.example.application.data.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDetails extends Contact {

    @NotNull
    @Length(min = 4, max = 64)
    private String handle;

    private AvatarImage avatar;

    @NotNull
    @Length(min = 8, max = 64)
    private String password;

    private boolean allowsMarketing;


}
