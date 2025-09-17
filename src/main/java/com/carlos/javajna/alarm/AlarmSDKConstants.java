package com.carlos.javajna.alarm;

public interface AlarmSDKConstants {

    // CallBackType
    int CallBackType_Unknown = 0;
    int DeviceOnline = 1;
    int DeviceOffline = 2;
    int Arm = 3;
    int DisArm = 4;
    int Alarm = 5;
    int Neglect = 6;
    int UnNeglect = 7;
    int RelayOn = 8;
    int RelayOff = 9;
    int ZoneArm = 10;
    int ZoneDisArm = 11;
    int AcNormal = 12;
    int AcAbnormal = 13;
    int DcNormal = 14;
    int DcAbnormal = 15;
    int ZoneFault = 16;
    int ZoneFaultRecovery = 17;
    int AlarmSoundOn = 18;
    int AlarmSoundOff = 19;
    int WarningSignsOn = 20;
    int WarningSignsOff = 21;
    int AnswerAlarm = 22;
    int InSetting = 23;
    int OutSetting = 24;
    int BatteryLow = 25;
    int BatteryRecovery = 26;
    int AlarmRecovery = 27;
    int ZoneLost = 28;
    int ZoneLostRecovery = 29;
    int BusZoneLow = 30;
    int BusZoneLowRecovery = 31;
    int GetStateEnd = 1000;

    // GetStateType
    enum  GetStateType
    {
        GetArmState(0),//布撤防状态
        GetFZoneArmState(1), //防区布撤防状态
        GetNeglectState(2),//旁路/取消旁路状态
        GetRelayState(3),//继电器状态
        GetZoneArmState(4),  //分区布撤防状态（259、8604设备支持）
        GetFaultState(5),//防区故障状态
        GetAcState(6),//交流电状态
        GetDcState(7),//备用电池状态
        GetAlarmSoundState(8),//警号状态
        GetWarningSignsState(9); //警示牌状态（259设备支持）

        private final Integer code;

        public Integer getCode() {
            return code;
        }

        GetStateType(Integer code) {
            this.code = code;
        }
    };

    // ControlType
    enum ControlType {
        Ctrl_Arm(0),//布防（整机）
        Ctrl_DisArm(1),//撤防（整机）
        Ctrl_FZoneArm(2),//防区布防（8604设备不支持）
        Ctrl_FZoneDisArm(3),//防区撤防（8604设备不支持）
        Ctrl_Reset(4), //复位/接警
        Ctrl_Neglect(5),//旁路
        Ctrl_UnNeglect(6),//取消旁路
        Ctrl_RelayOn(7),//继电器打开
        Ctrl_RelayOff(8), //继电器关闭
        Ctrl_ZoneArm(9), //分区布防（259、8604设备支持）
        Ctrl_ZoneDisArm(10),// 分区撤防（259、8604设备支持）
        Ctrl_CloseAlarmSound(11); //关闭警号

        private final Integer code;

        ControlType(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    };

    // DeviceType
    int Device_Unknown = 0;
    int Device_485NORMAL = 1;
    int Device_216 = 2;
    int Device_3110 = 3;
    int Device_7800 = 4;
    int Device_236GPlus = 5;
    int Device_969IP = 6;
    int Device_8604 = 7;
    int Device_5200 = 8;
    int Device_259 = 9;
    int Device_136 = 10;
    int Device_812_KR816 = 11;
    int Device_Vista_4110 = 12;
    int Device_Vista_20_120 = 13;
    int Device_CK = 14;
    int Device_QR_236GPlus = 15;
    int Device_831AI = 16;
    int Device_239G_24 = 17;
    int Device_2300Plus = 18;

    // ZoneType
    enum  ZoneType {
        ZoneType_Unknown(0), //未知
        Wired(1),//有线
        Wireless(2), //无线
        Bus(3), //总线
        LocalRelay(4), //本地继电器
        ExtensionRelay1(5),//扩展继电器模块1
        ExtensionRelay2(6),//扩展继电器模块2
        ExtensionRelay3(7),//扩展继电器模块3
        ExtensionRelay4(8),//扩展继电器模块4
        ExtensionRelay5(9),//扩展继电器模块5
        ExtensionRelay6(10),//扩展继电器模块6
        ExtensionRelay7(11),//扩展继电器模块7
        ExtensionRelay8(12),//扩展继电器模块8
        Zone(13), //分区
        Keyboard_SOS(14), //键盘紧急
        Keyboard_IllegalOpen(15), //键盘防拆
        Host_IllegalOpen(16), //主机防拆
        HumanoidWarning(17),  //人形警戒
        DectorIllegalOpen(18); //无线防区防拆

        private final Integer code;

        ZoneType(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    // DeviceProperty
    int Device_NetworkCard1 = 0;
    int Device_NetworkCard2 = 1;
    int Decice_Info = 2;
}
