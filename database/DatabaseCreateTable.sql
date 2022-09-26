CREATE TABLE ADMINS 
(
    admin_id bigserial not null primary key,
    username varchar(255) not null unique,
    password varchar(255) not null,
    name_ text null,
    created date default current_date,
    status smallint default 0
);
CREATE TABLE ROLES
(
    role_id bigserial not null primary key,
    admin_id bigint not null,
    name_ text,
    created date default current_date,
    creator text,
    modified date,
    modifier text,
    status smallint default 0,
    FOREIGN KEY(admin_id) REFERENCES admins(admin_id)
);
CREATE TABLE USERS
(
    user_id bigserial not null primary key,
    admin_id bigint null,
    username varchar(255) not null unique,
    password varchar(255) not null,
    address text ,
    phone varchar(15),
    email varchar(255) not null unique,
    image varchar(255) ,
    money real default 0,
    created date default current_date,
    creator text,
    modified date,
    modifier text,
    status smallint default 0,
    FOREIGN KEY(admin_id) REFERENCES admins(admin_id)
);
CREATE TABLE USERS_ROLE
(
    user_id bigint not null,
    role_id bigint not null,
    role_name text,
    created date default current_date,
    creator text,
    modified date,
    modifier text,
    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY(role_id) REFERENCES ROLES(role_id) 
);
CREATE TABLE CATEGORIES
(
    category_id bigserial not null primary key,
    parent_id bigint,
    admin_id bigint not null,
    name_ text not null,
    interest bigint default 0,
    like_ bigint default 0,
    created timestamp default current_timestamp,
    image varchar(255),
    creator text,
    modified timestamp,
    modifier text,
    status smallint default 0,
    FOREIGN KEY(parent_id) references categories(category_id),
    foreign key(admin_id) references admins(admin_id)
);
CREATE TABLE CATEGORIES_ATTR
(
    category_attr_id bigserial not null primary key,
    category_id bigint not null,
    admin_id bigint not null,
    image varchar(255)not null,
    name_ text not null,
    interest bigint default 0,
    like_ bigint default 0,
    describe text ,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text,
    status smallint default 0,
    foreign key(category_id) references categories(category_id),
    foreign key(admin_id) references admins(admin_id)
);
CREATE TABLE COURSES
(
    course_id bigserial not null primary key,
    user_id bigint not null,
    category_attr_id bigint not null,
    name_ text not null unique,
    image varchar(255) not null,
    study_time int ,
    like_ bigint default 0,
    price real,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text,
    describe text ,
    status smallint default 0,
    Foreign key(user_id)references users(user_id),
    foreign key(category_attr_id) references categories_attr(category_attr_id)
);
CREATE TABLE VIDEOS
(
    video_id bigserial not null primary key,
    title text ,
    url varchar(255)not null unique,
    describe text ,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text,
    status smallint default 0
);
CREATE TABLE COURSE_ATTR
(
    course_attr_id bigserial not null primary key,
    course_id bigint not null,
    video_id bigint not null,
    title text not null,
    describe text ,
    chapter smallint not null,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text,
    status smallint default 0,
    foreign key(course_id) references courses(course_id),
    foreign key(video_id) references videos(video_id)
);

CREATE TABLE PAYMENT_METHOD
(
    payment_method_id bigserial not null primary key,
    name_ text not null ,
    code varchar(255) not null unique,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text  
);
create table voucher_type
(
    voucher_type_id bigserial not null primary key,
    name_ text not null,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text 
);
CREATE TABLE VOUCHERS
(
    voucher_id bigserial not null primary key,
    voucher_type_id bigint not null,
    code varchar(255) not null unique,
    name_ text not null,
    effect_from timestamp not null,
    effect_until timestamp not null,
    quantity smallint ,
    discount real ,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text ,
    status smallint default 0,
    foreign key(voucher_type_id) REFERENCES voucher_type(voucher_type_id)
);
CREATE TABLE ORDERS
(
    order_id bigserial not null primary key,
    voucher_id bigint,
    code varchar(255)not null unique,
    user_id bigint not null,
    course_id bigint not null,
    effect_from timestamp,
    effect_until timestamp,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text,
    price real,
    status smallint default 0 ,
    foreign key(user_id) REFERENCES users(user_id),
    foreign key(course_id) REFERENCES courses(course_id),
    foreign key(voucher_id) references vouchers(voucher_id)
);
CREATE TABLE PAYMENTS
(
    payment_id bigserial not null primary key,
    order_id bigint ,
    payment_method_id bigint,
    status smallint default 1,
    pay_note text,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text,
    foreign key(order_id) references orders(order_id),
    foreign key(payment_method_id) REFERENCES payment_method(payment_method_id)
);



create table voucher_attr
(
    voucher_id bigint not null,
    course_id bigint,
    status smallint default 0
);
CREATE TABLE IP_ADDRESS_USER
(
    user_id bigint not null,
    ip_address varchar(255) not null,
    created timestamp default current_timestamp
);
CREATE TABLE block_user
(
     user_id bigint not null,
    ip_address varchar(255) not null,
    created timestamp default current_timestamp,
    effect_from timestamp,
    effect_until timestamp,
    status smallint default 0   
);

CREATE TABLE REPORT_QUANTITY_DAILY
(
    user_id bigint not null,
    category_id bigint not null,
    course_id bigint not null,
    voucher_id bigint not null,
    order_id bigint not null,
    report_date date,
    foreign key(user_id) references users(user_id),
    foreign key(category_id) references categories(category_id),
    foreign key(course_id) references courses(course_id),
    foreign key(voucher_id) references vouchers(voucher_id),
    foreign key(order_id) references orders(order_id)
);
CREATE TABLE REPORT_QUANTITY_MONTHLY
(
    user_id bigint not null,
    category_id bigint not null,
    course_id bigint not null,
    voucher_id bigint not null,
    order_id bigint not null,
    report_date date,
    foreign key(user_id) references users(user_id),
    foreign key(category_id) references categories(category_id),
    foreign key(course_id) references courses(course_id),
    foreign key(voucher_id) references vouchers(voucher_id),
    foreign key(order_id) references orders(order_id)
);
CREATE TABLE COMMENT_
(
    comment_id bigserial not null primary key,
    parent_id bigserial ,
    user_id bigint ,
    course_id bigint ,
    course_attr_id bigint,
    likes bigint,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text,
    status smallint default 0,
    foreign key(user_id) REFERENCES users(user_id),
    foreign key(course_id) REFERENCES courses(course_id),
    foreign key(course_attr_id) REFERENCES course_attr(course_attr_id),
    foreign key(comment_id) references comment_(comment_id)
);
CREATE TABLE CONTACT_SUBJECT
(
    contact_subject_id bigserial not null primary key,
    name_ text,
    code varchar(255) not null unique,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text,
    status smallint default 0
);
CREATE TABLE CONTACTS
(
    contact_id bigserial not null primary key,
    contact_subject_id bigint,
    fullname text,
    email varchar(255) not null,
    phone varchar(15),
    describe text,
    created timestamp default current_timestamp,
    foreign key(contact_subject_id) REFERENCES contact_subject(contact_subject_id)
);
CREATE TABLE LOGIN_FAILS
(
    user_id bigint,
    ip_address varchar(255),
    fail_count int ,
    created timestamp default current_timestamp,
    os_name varchar(255),
    location_ text ,
    foreign key (user_id) references users(user_id)
);
create table forgot_password
(
    user_id bigint,
    forgot_password_count int,
    created timestamp default current_timestamp,
    creator text,
    modified timestamp,
    modifier text,
    foreign key (user_id) references users(user_id)
);
CREATE TABLE MAIL_OUT_QUEUE
(
    message_id bigserial not null primary key,
    message_time timestamp default current_timestamp,
    sender text,
    recipient varchar(500),
    subject text not null,
    content_ text not null
);
ALTER TABLE IP_ADDRESS_USER
ADD FOREIGN KEY (user_id) references users(user_id);
ALTER TABLE VOUCHER_ATTR
ADD FOREIGN KEY(VOUCHER_ID) REFERENCES VOUCHERS(VOUCHER_ID);
ALTER TABLE BLOCK_USER
ADD FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID);


--- BẢNG ORDER CÓ 3 STATUS : CHỜ THANH TOÁN(0), CHỜ DUYỆT(1), ĐÃ DUYỆT(2)