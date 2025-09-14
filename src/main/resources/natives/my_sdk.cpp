#include <cstring>
#include <iostream>
#include <vector>
#include <functional>

#ifdef _WIN32
#define EXPORT_API __declspec(dllexport)
#else
#define EXPORT_API
#endif

extern "C" {

// --------------------- 简单函数 ---------------------
EXPORT_API int add(int a, int b) {
    return a + b;
}

EXPORT_API const char* get_message() {
    return "Hello from C++ SDK!";
}

// --------------------- 结构体 ---------------------
// 对应 Java: Structs.Person
struct Person {
    int age;
    char name[64];
};

EXPORT_API void get_person(Person* person) {
    if (!person) return;
    person->age = 30;
    std::strncpy(person->name, "Alice", sizeof(person->name));
}

// --------------------- 回调函数 ---------------------
typedef void (*MyCallback)(int code, const char* msg);
MyCallback g_callback = nullptr;

EXPORT_API void register_callback(MyCallback callback) {
    g_callback = callback;
    // 测试触发一次回调
    if (g_callback) {
        g_callback(200, "Callback registered successfully");
    }
}

// --------------------- 数组函数 ---------------------
EXPORT_API int sum_array(int* arr, int len) {
    if (!arr || len <= 0) return 0;
    int sum = 0;
    for (int i = 0; i < len; ++i) {
        sum += arr[i];
    }
    return sum;
}

// --------------------- 内存缓冲区 ---------------------
EXPORT_API void process_buffer(void* buffer, int size) {
    if (!buffer || size <= 0) return;

    // 假设 buffer 是字节数组，这里简单把每个字节加 1
    unsigned char* data = (unsigned char*)buffer;
    for (int i = 0; i < size; ++i) {
        data[i] += 1;
    }
}
} // extern "C"
