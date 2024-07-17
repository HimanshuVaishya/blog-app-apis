package com.codewithhimanshu.blog.blogappapis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private long id;
    @NotEmpty
    @Size(min=4, message = "name should contains min of 4 character")
    private String name;
    @Email(message="email address is not valid !!")
    private String email;
    @NotEmpty
    @Size(min = 8, max = 12,message = "password should contain min 8 character")
    private String password;
    @NotEmpty
    private String about;
}
