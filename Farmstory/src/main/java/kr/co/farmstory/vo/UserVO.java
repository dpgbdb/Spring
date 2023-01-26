package kr.co.farmstory.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {
    private String uid;
    private String pass;
    private String pass1;
    private String pass2;
    private String name;
    private String nick;
    private String email;
    private String hp;
    private String grade;
    private String zip;
    private String addr1;
    private String addr2;
    private String regip;
    private String rdate;
}