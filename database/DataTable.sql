INSERT INTO ADMINS (USERNAME,PASSWORD,NAME_) VALUES('tholv','123',N'Thọ');
---USER TABLE
INSERT INTO USERS(USERNAME,PASSWORD,ADDRESS,PHONE,EMAIL)
VALUES(1,'hoangtt','123','Ha noi','09392191');
INSERT INTO USERS(USERNAME,PASSWORD,ADDRESS,PHONE,email)
VALUES('nguyennt','123','hai Phong','0836451259','nguyenntph1778@fpt.edu.vn');
--category table
INSERT INTO CATEGORIES(ADMIN_ID,NAME_)
VALUES(1,N'Lập Trình');
INSERT INTO CATEGORIES(ADMIN_ID,NAME_)
VALUES(1,N'Lập Trình C');
INSERT INTO CATEGORIES(PARENT_ID,ADMIN_ID,NAME_)
VALUES(1,1,N'Lập Trình hướng đối tượng C');
--Categories_attr table
INSERT INTO CATEGORIES_ATTR(CATEGORY_ID,ADMIN_ID,IMAGE,NAME_)
VALUES(2,1,'img/demo',N'Lập Trình C cho người mới bắt đầu');
INSERT INTO CATEGORIES_ATTR(CATEGORY_ID,ADMIN_ID,IMAGE,NAME_)
VALUES(2,1,'img/demo',N'Lập Trình C cơ bản');
INSERT INTO CATEGORIES_ATTR(CATEGORY_ID,ADMIN_ID,IMAGE,NAME_)
VALUES(2,1,'img/demo',N'Lập Trình C nâng cao');
--Course 
INSERT INTO COURSES(ADMIN_ID,CATEGORY_ATTR_ID,NAME_,IMAGE,STUDY_TIME,PRICE)
VALUES(1,1,N'Lập trình C nâng cao','null',72,10000)
INSERT INTO COURSES(ADMIN_ID,CATEGORY_ATTR_ID,NAME_,IMAGE,STUDY_TIME,PRICE)
VALUES(1,1,N'Lập trình C căn bản cao','null',72,10000)
--video 
INSERT INTO VIDEOS(TITLE,URL,DESCRIBE) VALUES('BÀI 1','http://youtube.com','Lập trình cho người mới bắt đầu');
INSERT INTO VIDEOS(TITLE,URL,DESCRIBE) VALUES('BÀI 2','http://youtube.com/1','Lập trình cho người mới bắt đầu');
INSERT INTO VIDEOS(TITLE,URL,DESCRIBE) VALUES('BÀI 3','http://youtube.com/2','Lập trình cho người mới bắt đầu');
INSERT INTO VIDEOS(TITLE,URL,DESCRIBE) VALUES('BÀI 4','http://youtube.com/3','Lập trình cho người mới bắt đầu');
--COURSE ATTR
insert into course_attr(course_id,video_id,title,describe,chapter)
values(2,1,'Bài 1 : Ứng dụng hello world','Chương trình C đầu tiên',1);
insert into course_attr(course_id,video_id,title,describe,chapter)
values(2,1,'Bài 2 : Khai báo biến','Biến dữ liệu int,long,short,..',2);
-- PAYMENT_METHOD 
INSERT INTO PAYMENT_METHOD(NAME_,CODE)VALUES(N'Quét QR','QRCODE');
INSERT INTO PAYMENT_METHOD(NAME_,CODE)VALUES(N'Chuyển khoản','banking');
INSERT INTO PAYMENT_METHOD(NAME_,CODE)VALUES(N'Chuyển qua ví điện tử','Pay');
--orrders
INSERT INTO ORDERS(CODE,USER_ID,COURSE_ID,EFFECT_FROM,EFFECT_UNTIL,STATUS)
VALUES('ORDER_30082022_19193',1,2,current_date,current_date,0);
INSERT INTO ORDERS(CODE,USER_ID,COURSE_ID,EFFECT_FROM,EFFECT_UNTIL,STATUS)
VALUES('ORDER_30082022_19194',1,1,current_date,current_date,0);
INSERT INTO ORDERS(CODE,USER_ID,COURSE_ID,EFFECT_FROM,EFFECT_UNTIL,STATUS,Created)
VALUES('ORDER_30082022_191293',1,2,current_date,current_date,0,'2022-08-30');
INSERT INTO ORDERS(CODE,USER_ID,COURSE_ID,EFFECT_FROM,EFFECT_UNTIL,STATUS,Created)
VALUES('ORDER_30082022_1912293',1,2,current_date,current_date,0,'2022-09-30');
--- voucher type
insert into voucher_type(name_) values(N'Giảm theo %');
insert into voucher_type(name_) values(N'Giảm theo tiền');
--- voucher
insert into vouchers(voucher_type_id,code,name_,effect_from,effect_until,quantity,discount)
values(1,'HDHJJJ','Giảm giá 30%','2020-08-30','2020-09-10',10,30);
insert into vouchers(voucher_type_id,code,name_,effect_from,effect_until,quantity,discount)
values(1,'HDHJJJ1','Giảm giá 50%','2020-08-30','2020-09-10',10,50);
update orders set voucher_id=1;
---payment
insert into payments(order_id,payment_method_id,status,pay_note)
values (1,1,0,'Thành công');
insert into payments(order_id,payment_method_id,status,pay_note)
values (1,1,1,'Đang xử lý');
------------
alter table REPORT_QUANTITY_DAILY drop column report_date;
alter table REPORT_QUANTITY_DAILY add  payment_method varchar(50) ;
alter table REPORT_QUANTITY_DAILY add  report_date date;
---------
alter table REPORT_QUANTITY_MONTHLY drop column report_date;
alter table REPORT_QUANTITY_MONTHLY add  payment_method varchar(50) ;
alter table REPORT_QUANTITY_MONTHLY add  report_date date;
