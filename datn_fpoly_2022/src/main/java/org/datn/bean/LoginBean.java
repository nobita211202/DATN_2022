package org.datn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data@NoArgsConstructor
@AllArgsConstructor
public class LoginBean implements Serializable {
    private String userOrEmail;
    private String password;
}
