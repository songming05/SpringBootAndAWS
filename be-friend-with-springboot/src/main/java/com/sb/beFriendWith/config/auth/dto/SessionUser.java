package com.sb.beFriendWith.config.auth.dto;

import com.sb.beFriendWith.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picutre;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picutre = user.getPicture();
    }
}
