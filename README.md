<h1 align="center">
  📗 Dex Reader 📘  
</br>  
  <i>Đọc để tận hưởng – Lưu để ghi nhớ</i>
</h1>

**Dex Reader** là một dự án cá nhân với chủ đề **đọc truyện tranh (manga)** trên nền tảng **Android**, được xây dựng bằng **Kotlin** và **Jetpack Compose**, tuân theo tư duy **Clean Architecture**.
Ứng dụng lấy dữ liệu từ **MangaDex API**, mang lại trải nghiệm đọc manga mượt mà, trực quan và thân thiện với người dùng.

---

## 🌟 Tính năng chính

* 🏠 Hiển thị các manga mới cập nhật, đang thịnh hành, mới phát hành và được đánh giá cao.

* 📖 Trình đọc được tối ưu với các tính năng: zoom, toàn màn hình, tải trang nhanh và hiển thị ảnh chất lượng cao.

* ↔️ Khi đọc truyện, có thể dễ dàng chuyển qua lại giữa các chương trước/sau nhờ cơ chế **cache & tải trước (pre-fetching)**.

* 🔍 Tìm kiếm manga theo tên, có gợi ý khi nhập.

* 📂 Duyệt manga theo thể loại, hỗ trợ sắp xếp và lọc.

* ❤️ Lưu manga yêu thích, quản lý và đồng bộ với tài khoản người dùng.

* 📚 Theo dõi lịch sử đọc, tiếp tục đọc từ chương gần nhất và có thể xoá lịch sử.

* 👤 Đăng ký/Đăng nhập bằng **Firebase Authentication**, quản lý thông tin cá nhân và cập nhật hồ sơ.

* ⚙️ Hỗ trợ **Dark Mode / Light Mode** và tự động đồng bộ theo giao diện hệ thống.

---

## 🛠️ Công nghệ sử dụng

* **Ngôn ngữ lập trình:** [Kotlin](https://kotlinlang.org/)

* **Kiến trúc:** [MVVM](https://www.geeksforgeeks.org/mvvm-model-view-viewmodel-architecture-pattern-in-android/) kết hợp với [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

* **Giao diện (UI):** [Jetpack Compose](https://developer.android.com/compose), [Material 3](https://m3.material.io/develop/android/jetpack-compose)

* **Dependency Injection:** [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=vi)

* **Cơ sở dữ liệu cục bộ:** [Room](https://developer.android.com/training/data-storage/room)

* **Lưu trữ cấu hình:** [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)

* **Giao tiếp mạng:** [Retrofit](https://square.github.io/retrofit/), [OkHttp](https://square.github.io/okhttp/), [Moshi](https://github.com/square/moshi)

* **API tích hợp:** [MangaDex API](https://api.mangadex.org/docs/)

* **Xác thực người dùng:** [Firebase Auth](https://firebase.google.com/docs/auth)

* **Đồng bộ dữ liệu đám mây:** [Cloud Firestore](https://firebase.google.com/docs/firestore?hl=vi)

* **Load ảnh:** [Coil](https://coil-kt.github.io/coil/)

* **Điều hướng màn hình:** [Jetpack Navigation Compose](https://developer.android.com/develop/ui/compose/navigation)

* **Xử lý bất đồng bộ:** Kotlin [Coroutines](https://developer.android.com/kotlin/coroutines), [Flow](https://developer.android.com/kotlin/flow)

* **Cache:** Tải trước chương truyện và hỗ trợ đọc offline

---

## 🏗️ Cấu trúc package

```
com.truyen.dexreader/
├── data/                   # Tầng dữ liệu
│   ├── local/              # Database & lưu trữ local
│   ├── network/            # API & DTO
│   ├── repository/         # Triển khai repository
│   └── mapper/             # Chuyển đổi dữ liệu
├── domain/                 # Tầng nghiệp vụ
│   ├── model/              # Model
│   ├── repository/         # Interface repository
│   └── usecase/            # Use case
├── presentation/           # Tầng giao diện
│   ├── screens/            # Các màn hình UI
│   ├── theme/              # Theme ứng dụng
│   └── navigation/         # Điều hướng
├── di/                     # Dependency Injection
└── utils/                  # Hàm tiện ích
```

---

## 🚀 Bắt đầu sử dụng

### **Yêu cầu môi trường**

* Android Studio Hedgehog | 2023.1.1 hoặc mới hơn
* JDK 11 trở lên
* Android SDK API 24 trở lên

### **Cài đặt**

* Clone repository:

  ```
  git clone https://github.com/truyen/DexReader.git
  ```

* **Cấu hình Firebase:**

  * Tạo một project mới trên Firebase.
  * Thêm ứng dụng Android với package name: `com.truyen.dexreader`
  * Tải file `google-services.json` và đặt vào thư mục `app/`.

* **Cấu hình API URL:**

  * Tạo file `local.properties` ở thư mục gốc của project.
  * Thêm nội dung:

  ```
  BASE_URL=https://api.mangadex.org
  UPLOAD_URL=https://uploads.mangadex.org
  ```

### **Build & chạy ứng dụng**

```
./gradlew build
./gradlew installDebug
```
