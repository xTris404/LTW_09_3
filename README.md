# IOTSTAR SHOP

Ứng dụng quản lý người dùng và sản phẩm xây dựng bằng **Spring Boot 4.1.1**, **Spring Security**, **Spring Data JPA / Hibernate**, **Thymeleaf**, **MapStruct**, **Spring Mail** và **Cloudinary**, sử dụng **SQL Server** làm hệ quản trị cơ sở dữ liệu.

Project được xây dựng theo kiến trúc **MVC + Service + Repository**, sử dụng DTO làm lớp trung gian giữa Entity và View.

---

## 1. Giới thiệu

IOTSTAR SHOP là ứng dụng web mô phỏng một hệ thống quản lý shop với các chức năng chính:

- Đăng ký tài khoản.
- Xác nhận tài khoản bằng OTP gửi qua email.
- Gửi lại OTP đăng ký.
- Đăng nhập bằng Spring Security và lưu session.
- Đăng xuất.
- Quên mật khẩu bằng OTP email.
- Đặt lại mật khẩu.
- Quản lý User/Role.
- Quản lý Product.
- Quan hệ **1 User - N Product**.
- Tìm kiếm và phân trang User.
- Tìm kiếm và phân trang Product.
- Thống kê tổng số User.
- Thống kê tổng số Product.
- Thống kê số Product của từng User.
- Upload hình ảnh Product lên Cloudinary.
- Mapping **DTO ⇄ Entity** bằng MapStruct.
- Validation bằng Jakarta Validation.
- Giao diện Thymeleaf.

Các yêu cầu cốt lõi của assignment gồm Authentication, User, Product, Cloudinary, MapStruct, Thymeleaf và SQL Server. 

---

## 2. Công nghệ sử dụng

| Thành phần | Công nghệ |
|---|---|
| Backend | Spring Boot 4.1.1 |
| Language | Java |
| Security | Spring Security 7.1.x |
| Database | Microsoft SQL Server |
| ORM | Spring Data JPA / Hibernate |
| View | Thymeleaf |
| Mapper | MapStruct 1.6.3 |
| Email | Spring Mail |
| Image hosting | Cloudinary |
| Validation | Jakarta Validation |
| Password hashing | BCrypt |
| Build tool | Maven |
| Authentication | Spring Security Session |
| Architecture | MVC + Service + Repository |

Stack công nghệ này bám theo tài liệu assignment, trong đó tài liệu nêu Spring Boot 4.1.1, Spring Security 7.1.x, SQL Server, JPA/Hibernate, Thymeleaf, MapStruct 1.6.3, Spring Mail, Cloudinary, Jakarta Validation, Maven, Session và BCrypt. 

> **Lưu ý:** Java version nên đặt theo JDK thực tế mà project đang sử dụng. Nếu `pom.xml` của project đã cấu hình Java 21 thì giữ Java 21; nếu project được cấu hình theo tài liệu gốc thì có thể sử dụng JDK 26.

---

## 3. Chức năng

### 3.1. Authentication

- Register.
- Gửi OTP xác nhận email.
- Verify OTP.
- Resend OTP.
- Login.
- Spring Security Session.
- Logout.
- Forgot password.
- Gửi OTP reset password.
- Verify OTP reset password.
- Đổi password.

Các chức năng Authentication trên được liệt kê trong tài liệu hướng dẫn gốc. 

### 3.2. User

- CRUD User.
- Search User.
- Pagination User.
- Role `ROLE_USER` / `ROLE_ADMIN`.
- Đếm tổng số User.
- Đếm số Product của từng User.
- Chỉ Admin được truy cập `/users/**`.

### 3.3. Product

- CRUD Product.
- Search Product.
- Pagination Product.
- Upload ảnh lên Cloudinary.
- Product thuộc về User.
- Hiển thị số Product của User.
- Xóa Product.

Các yêu cầu User/Product này tương ứng với assignment trong tài liệu. 

---

## 4. Kiến trúc hệ thống

```text
Browser
   │
   ▼
Thymeleaf View
   │
   ▼
Controller
   │
   ▼
Service
   │
   ├──────────────► Cloudinary
   │
   ├──────────────► Spring Mail
   │
   ▼
Repository
   │
   ▼
Spring Data JPA / Hibernate
   │
   ▼
SQL Server
```

### Luồng xử lý DTO

```text
HTML Form
   │
   ▼
DTO
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
MapStruct
   │
   ▼
Entity
   │
   ▼
Repository
   │
   ▼
Database
```

Khi trả dữ liệu:

```text
Database
   │
   ▼
Entity
   │
   ▼
MapStruct
   │
   ▼
DTO
   │
   ▼
Controller
   │
   ▼
Thymeleaf
```

**Entity không được truyền trực tiếp xuống View.**

---

## 5. Cấu trúc project

```text
shop/
├── pom.xml
├── README.md
├── .env
├── .env.example
│
└── src/
    ├── main/
    │   ├── java/
    │   │   └── vn/iotstar/
    │   │       ├── ShopApplication.java
    │   │       │
    │   │       ├── config/
    │   │       │   ├── CloudinaryConfig.java
    │   │       │   ├── SecurityConfig.java
    │   │       │   ├── EncodingConfig.java
    │   │       │   └── DataInitializer.java
    │   │       │
    │   │       ├── controller/
    │   │       │   ├── AuthController.java
    │   │       │   ├── HomeController.java
    │   │       │   ├── UserController.java
    │   │       │   ├── ProductController.java
    │   │       │   └── AppErrorController.java
    │   │       │
    │   │       ├── dto/
    │   │       │   ├── UserDTO.java
    │   │       │   ├── ProductDTO.java
    │   │       │   ├── RegisterDTO.java
    │   │       │   ├── LoginDTO.java
    │   │       │   ├── VerifyOtpDTO.java
    │   │       │   ├── ForgotPasswordDTO.java
    │   │       │   └── ResetPasswordDTO.java
    │   │       │
    │   │       ├── entity/
    │   │       │   ├── User.java
    │   │       │   ├── Role.java
    │   │       │   ├── Product.java
    │   │       │   └── OtpToken.java
    │   │       │
    │   │       ├── mapper/
    │   │       │   ├── UserMapper.java
    │   │       │   └── ProductMapper.java
    │   │       │
    │   │       ├── repository/
    │   │       │   ├── UserRepository.java
    │   │       │   ├── RoleRepository.java
    │   │       │   ├── ProductRepository.java
    │   │       │   └── OtpTokenRepository.java
    │   │       │
    │   │       ├── security/
    │   │       │   ├── CustomUserDetails.java
    │   │       │   └── CustomUserDetailsService.java
    │   │       │
    │   │       └── service/
    │   │           ├── AuthService.java
    │   │           ├── UserService.java
    │   │           ├── ProductService.java
    │   │           ├── OtpService.java
    │   │           ├── EmailService.java
    │   │           ├── CloudinaryService.java
    │   │           ├── CloudinaryUploadResult.java
    │   │           │
    │   │           └── impl/
    │   │               ├── AuthServiceImpl.java
    │   │               ├── UserServiceImpl.java
    │   │               ├── ProductServiceImpl.java
    │   │               ├── OtpServiceImpl.java
    │   │               ├── EmailServiceImpl.java
    │   │               └── CloudinaryServiceImpl.java
    │   │
    │   └── resources/
    │       ├── application.properties
    │       ├── static/
    │       │   ├── css/
    │       │   │   └── app.css
    │       │   └── js/
    │       │
    │       └── templates/
    │           ├── layouts/
    │           ├── fragments/
    │           │   ├── header.html
    │           │   └── footer.html
    │           ├── home.html
    │           ├── error.html
    │           ├── auth/
    │           │   ├── login.html
    │           │   ├── register.html
    │           │   ├── verify-otp.html
    │           │   ├── forgot-password.html
    │           │   └── reset-password.html
    │           ├── users/
    │           │   ├── list.html
    │           │   └── form.html
    │           └── products/
    │               ├── list.html
    │               └── form.html
    │
    └── test/
```

---

## 6. Database design

### 6.1. Các bảng

```text
roles
users
products
otp_tokens
```

### 6.2. Quan hệ

```text
Role
  │
  │ 1
  ▼
User
  │
  │ 1
  │
  └──────────────< Product
                    N


User
  │
  │
  └──────────────< OtpToken
```

### 6.3. User

Các trường chính:

```text
id
username
email
password
fullName
enabled
role_id
```

### 6.4. Role

```text
id
name
```

Role sử dụng:

```text
ROLE_USER
ROLE_ADMIN
```

### 6.5. Product

```text
id
name
description
price
imageUrl
imagePublicId
createdAt
user_id
```

`user_id` xác định Product thuộc User nào.

### 6.6. OtpToken

```text
id
email
otpHash
type
expiresAt
attempts
used
createdAt
```

OTP không nên lưu dưới dạng plaintext; implementation sử dụng BCrypt để hash OTP trước khi lưu.

---

## 7. Quan hệ User - Product

Quan hệ chính của hệ thống:

```text
1 User ─────────── N Product
```

Ví dụ:

```text
User: admin
 ├── Product A
 ├── Product B
 └── Product C
```

Product chứa khóa ngoại:

```text
user_id
```

Controller lấy User hiện tại từ `Authentication`, sau đó Service gán User cho Product thay vì cho phép form tự quyết định User sở hữu Product.

---

## 8. MapStruct

MapStruct được sử dụng để chuyển đổi:

```text
Entity ⇄ DTO
```

Ví dụ:

```text
User
  ↓
UserMapper
  ↓
UserDTO
```

và:

```text
Product
  ↓
ProductMapper
  ↓
ProductDTO
```

Một số mapping đặc biệt:

```text
User.role.name → UserDTO.roleName

Product.user.id       → ProductDTO.userId
Product.user.username → ProductDTO.username
```

Password, role và các quan hệ cần xử lý riêng được bỏ qua khi mapping từ DTO về Entity.

---

## 9. Authentication Flow

### 9.1. Register

```text
/register
    │
    ▼
Nhập username/email/password/fullName
    │
    ▼
Validate
    │
    ▼
Kiểm tra username/email trùng
    │
    ▼
Tạo User với enabled = false
    │
    ▼
Hash password bằng BCrypt
    │
    ▼
Gửi OTP qua email
    │
    ▼
/verify-otp
```

### 9.2. Verify OTP

```text
Email + OTP
    │
    ▼
Tìm OTP mới nhất
    │
    ▼
Kiểm tra:
- chưa sử dụng
- chưa hết hạn
- chưa vượt quá số lần thử
    │
    ▼
BCrypt.matches()
    │
    ▼
OTP hợp lệ
    │
    ▼
enabled = true
```

OTP có thời hạn 5 phút và giới hạn số lần thử.

### 9.3. Login

Spring Security xử lý:

```text
POST /login
```

Authentication sử dụng:

```text
username
password
```

User chưa xác nhận email sẽ không thể đăng nhập do `enabled = false`.

### 9.4. Logout

```text
POST /logout
    │
    ├── invalidate session
    └── delete JSESSIONID
```

### 9.5. Forgot Password

```text
/forgot-password
      │
      ▼
Nhập email
      │
      ▼
Kiểm tra User
      │
      ▼
Tạo OTP RESET_PASSWORD
      │
      ▼
Gửi email
      │
      ▼
/reset-password
      │
      ▼
Verify OTP
      │
      ▼
Hash password mới
      │
      ▼
Lưu password
```

---

## 10. OTP

Có hai loại OTP:

```text
REGISTER
RESET_PASSWORD
```

OTP được:

- Tạo bằng `SecureRandom`.
- Có 6 chữ số.
- Hash bằng BCrypt.
- Có thời hạn 5 phút.
- Chỉ được sử dụng một lần.
- Giới hạn 5 lần thử.
- OTP cũ cùng loại/email được xóa trước khi tạo OTP mới.

---

## 11. Spring Security

Các URL public:

```text
/
 /login
 /register
 /verify-otp
 /forgot-password
 /reset-password
 /resend-register-otp
 /css/**
 /js/**
```

User/Admin:

```text
/users/**     → ROLE_ADMIN
/products/**  → authenticated
```

Các request khác yêu cầu đăng nhập.

Session:

```text
maximumSessions(1)
```

Tức mỗi tài khoản chỉ được phép có tối đa một session đăng nhập đồng thời theo cấu hình hiện tại.

---

## 12. Phân quyền

### ROLE_USER

Có thể:

- Đăng nhập.
- Xem trang chính.
- Quản lý Product theo các route được phép.

### ROLE_ADMIN

Có toàn bộ quyền của User và thêm:

- CRUD User.
- Search User.
- Pagination User.
- Xem số Product của từng User.
- Quản lý Role trong form User.

---

## 13. Product và Cloudinary

Khi tạo Product có upload ảnh:

```text
MultipartFile
     │
     ▼
CloudinaryService
     │
     ▼
Cloudinary
     │
     ├── secure_url
     └── public_id
```

Database lưu thông tin ảnh để có thể:

- Hiển thị ảnh.
- Thay ảnh.
- Xóa ảnh cũ khi update.
- Xóa ảnh trên Cloudinary khi Product bị xóa.

### Khuyến nghị cấu trúc dữ liệu

Nên lưu:

```text
imageUrl
imagePublicId
```

thành **hai cột riêng biệt** thay vì ghép:

```text
url|publicId
```

Điều này giúp việc update/delete ảnh rõ ràng và tránh phải parse chuỗi.

---

## 14. Search và Pagination

### User

Search theo:

```text
username
email
fullName
```

Ví dụ:

```text
/users?keyword=admin&page=0&size=10
```

### Product

Search theo:

```text
name
description
```

Ví dụ:

```text
/products?keyword=phone&page=0&size=10
```

Pagination sử dụng:

```java
PageRequest.of(page, size)
```

và kết quả trả về:

```java
Page<UserDTO>
Page<ProductDTO>
```

---

## 15. Thống kê

Trang Home có thể hiển thị:

```text
Total Users
Total Products
```

User management có thể hiển thị:

```text
User A → 5 Products
User B → 2 Products
User C → 0 Products
```

Repository sử dụng query `count` để lấy số Product theo User.

---

## 16. Cài đặt và chạy project

### Bước 1: Clone project

```bash
git clone <repository-url>
cd shop
```

### Bước 2: Kiểm tra Java

```bash
java -version
```

Đảm bảo JDK phù hợp với `java.version` trong `pom.xml`.

### Bước 3: Kiểm tra Maven

```bash
mvn -version
```

### Bước 4: Tạo `.env`

```text
.env.example
      ↓
     .env
```

Điền thông tin SQL Server, Gmail và Cloudinary.

### Bước 5: Build project

```bash
mvn clean install
```

### Bước 6: Chạy project

```bash
mvn spring-boot:run
```

Hoặc chạy:

```text
ShopApplication.java
```

từ IntelliJ IDEA / Eclipse / VS Code.

---

## 17. Truy cập ứng dụng

Sau khi ứng dụng khởi động thành công:

```text
http://localhost:8080
```

### Authentication

```text
/register
/login
/verify-otp
/forgot-password
/reset-password
```

### User

```text
/users
/users/create
/users/edit/{id}
/users/delete/{id}
```

Chỉ Admin được truy cập nhóm URL này.

### Product

```text
/products
/products/create
/products/edit/{id}
/products/delete/{id}
```

---

## 18. Quy trình test chức năng

### Test 1 — Register

1. Mở `/register`.
2. Nhập username.
3. Nhập email.
4. Nhập password.
5. Xác nhận password.
6. Nhập họ tên.
7. Submit.
8. Kiểm tra email.
9. Nhập OTP.
10. Kiểm tra tài khoản được enable.
11. Chuyển sang Login.

### Test 2 — Login

```text
/login
```

Đăng nhập bằng tài khoản đã xác nhận.

Kiểm tra:

- Authentication thành công.
- Session được tạo.
- Trang Home hiển thị.

### Test 3 — Admin

Đăng nhập bằng:

```text
SEED_ADMIN_USERNAME
SEED_ADMIN_PASSWORD
```

Sau đó:

```text
/users
```

Kiểm tra:

- List User.
- Search.
- Pagination.
- Create.
- Update.
- Delete.
- Role.
- Product count.

### Test 4 — Product

```text
/products
```

Kiểm tra:

- List.
- Search.
- Pagination.
- Create.
- Upload ảnh.
- Update.
- Replace ảnh.
- Delete.
- Xóa ảnh Cloudinary.

### Test 5 — Forgot Password

1. `/forgot-password`
2. Nhập email.
3. Kiểm tra email OTP.
4. `/reset-password`
5. Nhập OTP.
6. Nhập password mới.
7. Đăng nhập lại.

---

## 19. Một số lỗi thường gặp

### Lỗi không kết nối SQL Server

Kiểm tra:

```env
DB_URL=
DB_USERNAME=
DB_PASSWORD=
```

và đảm bảo SQL Server đang chạy.

Kiểm tra port:

```text
1433
```

Nếu SQL Server dùng instance/port khác thì cập nhật `DB_URL`.

---

### Lỗi `Unable to determine Dialect`

Đây thường là lỗi thứ cấp khi Hibernate không lấy được JDBC metadata.

Không nên chỉ thêm:

```properties
hibernate.dialect=...
```

mà bỏ qua lỗi kết nối database.

Hãy kiểm tra trước:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
SQL Server
databaseName
JDBC Driver
```

---

### Lỗi không gửi được OTP

Kiểm tra:

```text
MAIL_HOST
MAIL_PORT
MAIL_USERNAME
MAIL_PASSWORD
```

Đối với Gmail, kiểm tra App Password.

---

### Lỗi Cloudinary

Kiểm tra:

```text
CLOUDINARY_CLOUD_NAME
CLOUDINARY_API_KEY
CLOUDINARY_API_SECRET
```

Ngoài ra kiểm tra file upload có phải image hay không.

---

### Lỗi MapStruct không tạo Mapper

Kiểm tra dependency:

```xml
<artifactId>mapstruct</artifactId>
```

và annotation processor:

```xml
<artifactId>mapstruct-processor</artifactId>
```

Sau đó chạy:

```bash
mvn clean compile
```

---


## 20. Tóm tắt luồng hệ thống

```text
                    ┌──────────────────┐
                    │     Browser      │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │    Thymeleaf     │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │    Controller    │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │     Service      │
                    └────┬─────┬───────┘
                         │     │
               ┌─────────┘     └─────────┐
               ▼                         ▼
        ┌──────────────┐          ┌─────────────┐
        │   MapStruct  │          │ Cloudinary  │
        └──────┬───────┘          └─────────────┘
               │
               ▼
        ┌──────────────┐
        │   Repository │
        └──────┬───────┘
               │
               ▼
        ┌──────────────┐
        │  SQL Server  │
        └──────────────┘

Authentication:
Browser → Spring Security → CustomUserDetailsService
                       → UserRepository
                       → Session

OTP:
Register/Forgot Password
        ↓
OtpService
        ↓
BCrypt OTP Hash
        ↓
OtpToken
        ↓
EmailService
        ↓
Gmail SMTP
```

---

## 21. Kết luận

IOTSTAR SHOP đáp ứng nhóm yêu cầu chính của assignment:

- Spring Boot 4.1.1.
- Spring Security.
- Session-based authentication.
- Register + OTP email.
- Forgot password + OTP.
- User/Role.
- Product.
- Quan hệ 1 User - N Product.
- CRUD.
- Search.
- Pagination.
- Statistics.
- MapStruct DTO ⇄ Entity.
- Thymeleaf.
- SQL Server.
- Spring Mail.
- Cloudinary.
- BCrypt.
- Maven.

Project được tổ chức theo hướng tách biệt rõ:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

và:

```text
Entity
    ⇄ MapStruct ⇄
DTO
```

giúp code dễ bảo trì, dễ kiểm thử và phù hợp với yêu cầu kiến trúc của bài tập.
