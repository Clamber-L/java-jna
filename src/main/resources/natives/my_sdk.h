#pragma once

#ifdef _WIN32
#define EXPORT_API __declspec(dllexport)
#else
#define EXPORT_API
#endif

extern "C" {
    EXPORT_API int add(int a, int b);
    EXPORT_API const char* get_message();
}

