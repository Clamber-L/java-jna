package com.carlos.javajna.alarm;

import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import javax.annotation.PostConstruct;

@Service
public class AlarmService {

    private final NativeLoadConfig loadConfig;

    private static CallbackInterface.EventCallback eventCallback;

    private static CallbackInterface.StateCallback stateCallback;

    private static CallbackInterface.EventCallbackEx eventCallbackEx;

    public AlarmService(NativeLoadConfig loadConfig) {
        this.loadConfig = loadConfig;
    }

    @PostConstruct
    public void init() {
        System.out.println("[JnaConfig] Loading native libraries from: " + loadConfig.getNativesPath());
        NativeLibLoader.loadFromDir(loadConfig.getNativesPath(), loadConfig.getLibraries().toArray(new String[0]));

        AlarmSDKLibrary sdk = AlarmSDKLibrary.getInstance();

        String version = sdk.Alarm_GetVersion();
        System.out.println("SDK Version: " + version);

        eventCallback  = (callBackType, devHostNo, devHostIpPtr, zone, zoneType, timePtr) -> {
            String devHostIp = devHostIpPtr.getString(0, "GBK");
            String time = timePtr.getString(0, "GBK");

            System.out.println("State Callback Received:");
            System.out.println("  CallBackType: " + callBackType);
            System.out.println("  HostNo: " + String.format("%02X%02X%02X",
                    devHostNo.SKHostNoH, devHostNo.SKHostNoL, devHostNo.SKHostNoGroup));
            System.out.println("  Dev IP: " + devHostIp);
            System.out.println("  Zone: " + zone);
            System.out.println("  ZoneType: " + zoneType);
            System.out.println("  Time: " + time);
        };
        sdk.Alarm_SetEventCallBack(eventCallback);
        System.out.println("Event callback registered.");

        stateCallback = (getStateType, devHostNo, zone, zoneType, state) -> {
            System.out.println("State Callback Received:");
            System.out.println("  GetStateType: " + getStateType);
            System.out.println("  HostNo: " + String.format("%02X%02X%02X",
                    devHostNo.SKHostNoH, devHostNo.SKHostNoL, devHostNo.SKHostNoGroup));
            System.out.println("  Zone: " + zone);
            System.out.println("  ZoneType: " + zoneType);
            System.out.println("  State: " + state);
        };
        sdk.Alarm_SetMessageCallBack(stateCallback);
        System.out.println("State callback registered.");

        boolean initSuccess = sdk.Alarm_Init(9898, "0.0.0.0"); // 监听端口8888，不指定监听IP
        if (initSuccess) {
            System.out.println("SDK initialized successfully.");
        } else {
            System.err.println("Failed to initialize SDK.");
        }
    }

//    @PostConstruct
//    public void runInit() {
//        // 1. 初始化SDK
//        System.out.println("Initializing SDK...");
//        boolean initSuccess = sdk.Alarm_Init(9000, "0.0.0.0"); // 监听端口9000，不指定监听IP
//        if (initSuccess) {
//            System.out.println("SDK initialized successfully.");
//        } else {
//            System.err.println("Failed to initialize SDK.");
//        }
//
//        // 2. 获取版本信息
//        String version = sdk.Alarm_GetVersion();
//        System.out.println("SDK Version: " + version);
//
//        // 3. 注册事件回调 (EventCallback)
//        eventCallback  = (callBackType, devHostNo, devHostIpPtr, zone, zoneType, timePtr) -> {
//            System.out.println("Event Callback Received:");
//            System.out.println("  CallBackType: " + callBackType);
//            System.out.println("  HostNo: " + String.format("%02X%02X%02X", devHostNo.SKHostNoH, devHostNo.SKHostNoL, devHostNo.SKHostNoGroup));
//
//            String hostIp = (devHostIpPtr != null) ? devHostIpPtr.getString(0, String.valueOf(StandardCharsets.UTF_8)) : "";
//            System.out.println("  HostIP: " + hostIp.trim()); // .trim() 可以去除末尾可能的空白字符
//
//            System.out.println("  Zone: " + zone);
//            System.out.println("  ZoneType: " + zoneType);
//
//            String eventTime = (timePtr != null) ? timePtr.getString(0, String.valueOf(StandardCharsets.UTF_8)) : "";
//            System.out.println("  Time: " + eventTime.trim());
//        };
//        sdk.Alarm_SetEventCallBack(eventCallback);
//        System.out.println("Event callback registered.");
//
//        eventCallbackEx = ((callBackType, devHostNo, devHostIp, zone, zoneType, time, remark) -> {
//            System.out.println("Event EX Callback Received:");
//            System.out.println("EX  CallBackType: " + callBackType);
//            System.out.println("EX  HostNo: " + String.format("%02X%02X%02X", devHostNo.SKHostNoH, devHostNo.SKHostNoL, devHostNo.SKHostNoGroup));
//
//            String hostIp = (devHostIp != null) ? devHostIp.getString(0, String.valueOf(StandardCharsets.UTF_8)) : "";
//            System.out.println("EX  HostIP: " + hostIp.trim()); // .trim() 可以去除末尾可能的空白字符
//
//            System.out.println("EX  Zone: " + zone);
//            System.out.println("EX  ZoneType: " + zoneType);
//
//            String eventTime = (time != null) ? time.getString(0, String.valueOf(StandardCharsets.UTF_8)) : "";
//            System.out.println("EX  Time: " + eventTime.trim());
//        });
//        sdk.Alarm_SetEventCallBackEx(eventCallbackEx);
//        System.out.println("Event EX callback registered.");
//
//        // 4. 注册状态回调 (StateCallback)
//        stateCallback = (getStateType, devHostNo, zone, zoneType, state) -> {
//            System.out.println("State Callback Received:");
//            System.out.println("  GetStateType: " + getStateType);
//            System.out.println("  HostNo: " + String.format("%02X%02X%02X", devHostNo.SKHostNoH, devHostNo.SKHostNoL, devHostNo.SKHostNoGroup));
//            System.out.println("  Zone: " + zone);
//            System.out.println("  ZoneType: " + zoneType);
//            System.out.println("  State: " + state);
//        };
//        sdk.Alarm_SetMessageCallBack(stateCallback);
//        System.out.println("State callback registered.");
//
//        String devNo = "111101"; // 你的6位设备编号字符串
//        AlarmDevHostNo.ByValue hostNo = new AlarmDevHostNo.ByValue();
//        hostNo.SKHostNoH = (byte) Integer.parseInt(devNo.substring(0, 2), 16);
//        hostNo.SKHostNoL = (byte) Integer.parseInt(devNo.substring(2, 4), 16);
//        hostNo.SKHostNoGroup = (byte) Integer.parseInt(devNo.substring(4, 6), 16);
//
//        hostNo.write(); // 写入JNA内部内存
//        // 7. 示例: 查询设备状态 (GetArmState)
//        System.out.println("Attempting to query arm state...");
//        int queryResult = sdk.Alarm_QueryDevice(AlarmSDKConstants.GetRelayState, hostNo, 1, AlarmSDKConstants.Wired);
//        System.out.println("QueryDevice (GetArmState) result: " + queryResult);
//
//        // 获取设备属性
//        Pointer pointer = sdk.Alarm_GetDeviceProperty(AlarmSDKConstants.Decice_Info, hostNo);
//        System.out.println("device info:" + pointer);
//    }
//
////    @PreDestroy
//    public void clean() {
//        boolean cleanUp = sdk.Alarm_Cleanup();
//        if (cleanUp) {
//            System.out.println("cleanup:" + cleanUp);
//        }
//    }
//
////    @Override
//    public void run(String... args) throws Exception {
//        // 1. 初始化SDK
//        System.out.println("Initializing SDK...");
//        boolean initSuccess = sdk.Alarm_Init(9000, "0.0.0.0"); // 监听端口9000，不指定监听IP
//        if (initSuccess) {
//            System.out.println("SDK initialized successfully.");
//        } else {
//            System.err.println("Failed to initialize SDK.");
//        }
//
//        // 2. 获取版本信息
//        String version = sdk.Alarm_GetVersion();
//        System.out.println("SDK Version: " + version);
//
//        // 3. 注册事件回调 (EventCallback)
//        eventCallback  = (callBackType, devHostNo, devHostIpPtr, zone, zoneType, timePtr) -> {
//            System.out.println("Event Callback Received:");
//            System.out.println("  CallBackType: " + callBackType);
//            System.out.println("  HostNo: " + String.format("%02X%02X%02X", devHostNo.SKHostNoH, devHostNo.SKHostNoL, devHostNo.SKHostNoGroup));
//
//            String hostIp = (devHostIpPtr != null) ? devHostIpPtr.getString(0, String.valueOf(StandardCharsets.UTF_8)) : "";
//            System.out.println("  HostIP: " + hostIp.trim()); // .trim() 可以去除末尾可能的空白字符
//
//            System.out.println("  Zone: " + zone);
//            System.out.println("  ZoneType: " + zoneType);
//
//            String eventTime = (timePtr != null) ? timePtr.getString(0, String.valueOf(StandardCharsets.UTF_8)) : "";
//            System.out.println("  Time: " + eventTime.trim());
//        };
//        sdk.Alarm_SetEventCallBack(eventCallback);
//        System.out.println("Event callback registered.");
//
//        eventCallbackEx = ((callBackType, devHostNo, devHostIp, zone, zoneType, time, remark) -> {
//            System.out.println("Event EX Callback Received:");
//            System.out.println("EX  CallBackType: " + callBackType);
//            System.out.println("EX  HostNo: " + String.format("%02X%02X%02X", devHostNo.SKHostNoH, devHostNo.SKHostNoL, devHostNo.SKHostNoGroup));
//
//            String hostIp = (devHostIp != null) ? devHostIp.getString(0, String.valueOf(StandardCharsets.UTF_8)) : "";
//            System.out.println("EX  HostIP: " + hostIp.trim()); // .trim() 可以去除末尾可能的空白字符
//
//            System.out.println("EX  Zone: " + zone);
//            System.out.println("EX  ZoneType: " + zoneType);
//
//            String eventTime = (time != null) ? time.getString(0, String.valueOf(StandardCharsets.UTF_8)) : "";
//            System.out.println("EX  Time: " + eventTime.trim());
//        });
//        sdk.Alarm_SetEventCallBackEx(eventCallbackEx);
//        System.out.println("Event EX callback registered.");
//
//        // 4. 注册状态回调 (StateCallback)
//        stateCallback = (getStateType, devHostNo, zone, zoneType, state) -> {
//            System.out.println("State Callback Received:");
//            System.out.println("  GetStateType: " + getStateType);
//            System.out.println("  HostNo: " + String.format("%02X%02X%02X", devHostNo.SKHostNoH, devHostNo.SKHostNoL, devHostNo.SKHostNoGroup));
//            System.out.println("  Zone: " + zone);
//            System.out.println("  ZoneType: " + zoneType);
//            System.out.println("  State: " + state);
//        };
//        sdk.Alarm_SetMessageCallBack(stateCallback);
//        System.out.println("State callback registered.");
//
//        String devNo = "111101"; // 你的6位设备编号字符串
//        AlarmDevHostNo.ByValue hostNo = new AlarmDevHostNo.ByValue();
//        hostNo.SKHostNoH = (byte) Integer.parseInt(devNo.substring(0, 2), 16);
//        hostNo.SKHostNoL = (byte) Integer.parseInt(devNo.substring(2, 4), 16);
//        hostNo.SKHostNoGroup = (byte) Integer.parseInt(devNo.substring(4, 6), 16);
//
//        hostNo.write(); // 写入JNA内部内存
//        // 7. 示例: 查询设备状态 (GetArmState)
//        System.out.println("Attempting to query arm state...");
//        int queryResult = sdk.Alarm_QueryDevice(AlarmSDKConstants.GetRelayState, hostNo, 1, AlarmSDKConstants.Wired);
//        System.out.println("QueryDevice (GetArmState) result: " + queryResult);
//
//        // 获取设备属性
//        Pointer pointer = sdk.Alarm_GetDeviceProperty(AlarmSDKConstants.Decice_Info, hostNo);
//        System.out.println("device info:" + pointer);
//    }
}
