package com.carlos.javajna.alarm;

import com.carlos.javajna.alarm.struct.AlarmDevHostNo;
import com.sun.jna.*;

public interface AlarmSDKLibrary extends Library, AlarmSDKConstants {

    // 假设你的动态库文件名为 "AlarmSDK.so" (Linux) 或 "AlarmSDK.dll" (Windows)
    // 在Ubuntu上，JNA会自动查找 libAlarmSDK.so
    // 懒加载 Holder
    class Holder {
        static final AlarmSDKLibrary INSTANCE = Native.loadLibrary("AlarmSDK", AlarmSDKLibrary.class);
    }

    static AlarmSDKLibrary getInstance() {
        return Holder.INSTANCE;
    }
    /**
     * 初始化SDK
     * @param port 监听端口
     * @param ListenIPAddr 监听IP地址 (可选, 可以传null)
     * @return true 成功, false 失败
     */
    boolean Alarm_Init(int port, String ListenIPAddr); // char* 映射为 String

    /**
     * 释放SDK资源
     * @return true 成功, false 失败
     */
    boolean Alarm_Cleanup();

    /**
     * 注册回调函数，接收设备消息
     * @param fun 回调接口实现
     */
    void Alarm_SetEventCallBack(CallbackInterface.EventCallback fun);

    /**
     * 注册回调函数，接收设备状态消息
     * @param fun 回调接口实现
     */
    void Alarm_SetMessageCallBack(CallbackInterface.StateCallback fun);

    /**
     * 控制设备
     * @param controlType 控制类型 (ControlType)
     * @param devHostNo 设备编号
     * @param pass 控制密码 (4位 byte 数组)
     * @param izone 防区/分区/继电器号
     * @param zoneType 防区类型/继电器类型 (ZoneType)
     * @return 操作结果码
     */
    int Alarm_ControlDevice(int controlType, AlarmDevHostNo.ByValue devHostNo, byte[] pass, int izone, int zoneType);

    /**
     * 查询设备状态
     * @param getStateType 查询类型 (GetStateType)
     * @param devHostNo 设备编号
     * @param zone 防区/分区/继电器号
     * @param zoneType 防区类型/继电器类型 (ZoneType)
     * @return 操作结果码
     */
    int Alarm_QueryDevice(int getStateType, AlarmDevHostNo.ByValue devHostNo, int zone, int zoneType);

    /**
     * 获取设备属性
     * @param deviceProperty 设备属性类型 (DeviceProperty)
     * @param devHostNo 设备编号
     * @return 返回指向属性数据结构体的指针 (Pointer), 需要根据 deviceProperty 类型自行转换为对应的结构体
     */
    Pointer Alarm_GetDeviceProperty(int deviceProperty, AlarmDevHostNo.ByValue devHostNo);

    /**
     * 设置设备属性 (这里 wchat_t* message 映射为 WString)
     * @param devHostNo 设备编号
     * @param message 消息内容
     * @param messageLen 消息长度
     * @return true 成功, false 失败
     */
    boolean Alarm_SetDeviceProperty(AlarmDevHostNo.ByValue devHostNo, WString message, int messageLen);

    /**
     * 设置设备类型
     * @param deviceType 设备类型 (DeviceType)
     * @param devHostNo 设备编号
     * @param devHostIp 设备IP (可选, 可以传null或长度为20的byte数组)
     * @return 操作结果码
     */
    int Alarm_SetDeviceType(int deviceType, AlarmDevHostNo.ByValue devHostNo, byte[] devHostIp);

    /**
     * 校对时间
     * @return true 成功, false 失败
     */
    boolean Alarm_Proofreading_Time();

    /**
     * 注册扩展回调函数，接收设备消息
     * @param fun 回调接口实现
     */
    void Alarm_SetEventCallBackEx(CallbackInterface.EventCallbackEx fun);

    /**
     * 获取SDK版本信息
     * @return 版本字符串
     */
    String Alarm_GetVersion();

    // 注意：`void myCallback(void* pvoid, int nType);` 这个函数在头文件中只有声明，没有 AlarmSDK_DLL_API 导出，
    // 且不是 Alarm_ 前缀的公共API，通常不需要在JNA接口中映射，除非它是一个内部或特殊的Hook。
    // 如果确实需要，它将映射为 `void myCallback(Pointer pvoid, int nType);`
}