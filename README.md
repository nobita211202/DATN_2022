# Dự án tốt nghiệp 2022 

## Quy định khi code backend

- Không được đặt tên biến sai quy tắc , phải đặt tên biến theo quy tắc con lạc đà .
    - Ví dụ :
    ```Java
    String createDate = new Date ().toString();         // Đúng quy tắc
    String createdate = new Date ().toString();         // Sai quy tắc
    ```
- Tên biến được khai báo với khóa **final** tức là hằng số phải viết in hoa hết, nếu biến nào có từ 2 chữ 
cái đổ lên có thể dùng ký tự '_' để nối 
    - Ví dụ
    ```Java
    public static final double PI = 3.14159 ;               // đây là biến có 1 chữ cái

    public static final long MAX_NUMBER = Long.MAX_NUMBER   // đây là biến có 2 chữ cái sẽ nối bằng _ 
    ```





