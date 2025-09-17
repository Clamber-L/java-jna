package com.carlos.javajna.alarm;

import org.springframework.stereotype.Service;
import java.util.Objects;
import javax.annotation.PostConstruct;

@Service
public class AlarmService {

    private final NativeLoadConfig loadConfig;

    private static CallbackInterface.EventCallback eventCallback;

    private static CallbackInterface.StateCallback stateCallback;

    public AlarmService(NativeLoadConfig loadConfig) {
        this.loadConfig = loadConfig;
    }

    @PostConstruct
    public void init() {
        System.out.println("[JnaConfig] Loading native libraries from: " + loadConfig.getNativesPath());
        // 判断操作系统 读取sdk文件
        this.loadByOs();

        AlarmSDKLibrary sdk = AlarmSDKLibrary.getInstance();

        // 2️⃣ 先注册回调
        registerEventCallback();
        registerStateCallback();

        boolean initSuccess = sdk.Alarm_Init(9898, "0.0.0.0"); // 监听端口8888，不指定监听IP
        if (initSuccess) {
            System.out.println("SDK initialized successfully.");
        } else {
            System.err.println("Failed to initialize SDK.");
        }
    }

    private void loadByOs() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            NativeLibLoader.loadAll("pthreadVC2","QRNetSDK","AlarmSDK");
        }else {
            // 忽略macos 非windows下都是使用linux
            NativeLibLoader.loadAll("qrnetsdk","AlarmSDK");
        }
    }

    private void registerEventCallback() {
        eventCallback  = (callBackType, devHostNo, devHostIpPtr, zone, zoneType, timePtr) -> {
            String devHostIp = devHostIpPtr.getString(0, "GBK");
            String time = timePtr.getString(0, "GBK");

            System.out.println("Event Callback Received:");
            System.out.println("  CallBackType: " + callBackType);
            System.out.println("  HostNo: " + String.format("%02X%02X%02X",
                    devHostNo.SKHostNoH, devHostNo.SKHostNoL, devHostNo.SKHostNoGroup));
            System.out.println("  Dev IP: " + devHostIp);
            System.out.println("  Zone: " + zone);
            System.out.println("  ZoneType: " + zoneType);
            System.out.println("  Time: " + time);
        };
        AlarmSDKLibrary.getInstance().Alarm_SetEventCallBack(eventCallback);
        System.out.println("Event callback registered.");
    }

    private void registerStateCallback() {
        stateCallback = (getStateType, devHostNo, zone, zoneType, state) -> {
            System.out.println("State Callback Received:");
            System.out.println("  GetStateType: " + getStateType);
            System.out.println("  HostNo: " + String.format("%02X%02X%02X",
                    devHostNo.SKHostNoH, devHostNo.SKHostNoL, devHostNo.SKHostNoGroup));
            System.out.println("  Zone: " + zone);
            System.out.println("  ZoneType: " + zoneType);
            System.out.println("  State: " + state);
        };
        AlarmSDKLibrary.getInstance().Alarm_SetMessageCallBack(stateCallback);
        System.out.println("State callback registered.");
    }
}
