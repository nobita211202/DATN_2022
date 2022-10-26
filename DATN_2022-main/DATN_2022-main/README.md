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
- Khi code 1 chức năng quá dài, các bạn có thể chú thích vào các dòng quan trọng trong phần code đó để mọi người có thể biết cách chạy, còn không thì phần code đó chỉ có bạn và chúa là người biết cách chạy.

- Viết code tối ưu, code đẹp nhìn bắt mắt :
    - Khi viết code thì cách trình bày code cũng là 1 vấn đề quan trọng. Ví dụ như
    ```Java
    import java.util.Scanner

    public class main{
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);                    // khi gán giá trị phải cách thưa ra nhìn sẽ đẹp hơn
            /*
                *   - Khi ta sử dụng các cấu trúc rẽ nhánh như if,else,switch-case,try-catch,for,while,..
                *   thì tất cả khối lệnh của cấu trúc rẽ nhánh đó phải được thụt vào 1 dòng.
                *   - Các câu lệnh rẽ nhánh chỉ có 1 dòng body bên trong thì không nên dùng dấu {}
            */
            if(true)
                System.out.println("Hello world in Java");          // Viết theo kiểu này
            if(true)
            System.out.println("Hello world in Java");             // Viết theo kiểu này sẽ dễ bị hiểu nhầm
            if(true){
                System.out.println("hello world in Java");          // Viết theo kiểu này cũng được nhưng thừa dấu {} vì nếu body có 1 lệnh thì ko cần
            }
        }
    }
    ```
    - Đã là coder Java phải hiểu được cấu trúc của ngôn ngữ lập trình Java, tuy Java không có con trỏ (Pointer) như C++ và C, Golang ... nhưng phần tham chiếu của nó được biểu hiện qua các Array,Class,List
    ```Java
    // Khi  1 người chưa am hiểu nhiều về Java thì họ nhìn dòng code này sẽ bảo đưa ra 2 kết quả đúng
    List list1 = new ArrayList();
    list1.add("tholv");
    list1.add("nguyennt");
    List list2 = list1;
    list2.add("1");
    System.out.println("Size of case 1 :" + list1.size());
    // trường hợp 2
    List list1 = new ArrayList();
    list1.add("tholv");
    list1.add("nguyennt");
    List list2 = new ArrayList();
    for(Object x : list1)
        list2.add(x);
    System.out.println("Size of case 1 :" + list1.size());

    /* Trường hợp 1 sẽ cho ra size = 3, trường hợp 2 thì lại bằng 2
            Khi bạn gán đối tượng 1 = đối tượng 2 hoặc list 1 bằng list 2
            Thì đó chính là tham chiếu , lúc đó đối tượng 1 sẽ là đối tượng 2 luôn, đối tượng 1 thay đổi
            thì đối tượng 2 cũng sẽ thay đổi theo,trên ví dụ kia khi list 2 bị thay đổi thì list 1 cũng sẽ thay đổi
    */
    // Cẩn thận với cái này nhé không là gặp rồi sẽ ko biết lỗi ở đâu đâu 
    ```
    ### Tạm kết ở đây
    #### later update : 26/09/2022






