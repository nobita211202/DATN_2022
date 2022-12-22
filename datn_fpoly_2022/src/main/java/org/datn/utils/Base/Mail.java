package org.datn.utils.Base;

public class Mail {
    public static String docChangePassword(String email,String password){
        return "<div>\n" +
                "    <p>Xin chào "+email+",</p>\n" +
                "    <p>Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu tài khoản khóa học của bạn. <br>\n" +
                "      Mật khẩu hiện tại của bạn là:</p>\n" +
                "    <p>"+password+"</p>\n" +
                "    Ngoài ra bạn có thể ấn vào <a href=\"#\">đây</a> để đổi lại mật khẩu.\n" +
                "    <hr>\n" +
                "    <div style=\"text-align: center;\">\n" +
                "       <p>từ</p>\n" +
                "       <p>Fuck you</p>\n" +
                "     </div>\n" +
                "  </div>";
    }
}
