//package com.carlos.javajna.shike;
//
//import com.sun.jna.*;
//import com.sun.jna.win32.StdCallLibrary;
//import com.sun.jna.win32.W32APIOptions;
//
//import java.util.List;
//
//public interface ShikeInterface extends Library {
//
//    ShikeInterface INSTANCE = Native.load("AlarmSDK", ShikeInterface.class, W32APIOptions.UNICODE_OPTIONS);
//
//    interface CallBackType {
//        int CallBackType_Unknown = 0;
//        int DeviceOnline = 1;
//        int DeviceOffline = 2;
//        int Arm = 3;
//        int DisArm = 4;
//        int Alarm = 5;
//        int Neglect = 6;
//        int UnNeglect = 7;
//        int RelayOn = 8;
//        int RelayOff = 9;
//        int ZoneArm = 10;
//        int ZoneDisArm = 11;
//        int AcNormal = 12;
//        int AcAbnormal = 13;
//        int DcNormal = 14;
//        int DcAbnormal = 15;
//        int ZoneFault = 16;
//        int ZoneFaultRecovery = 17;
//        int AlarmSoundOn = 18;
//        int AlarmSoundOff = 19;
//        int WarningSignsOn = 20;
//        int WarningSignsOff = 21;
//        int AnswerAlarm = 22;
//        int InSetting = 23;
//        int OutSetting = 24;
//        int BatteryLow = 25;
//        int BatteryRecovery = 26;
//        int AlarmRecovery = 27;
//        int ZoneLost = 28;
//        int ZoneLostRecovery = 29;
//        int BusZoneLow = 30;
//        int BusZoneLowRecovery = 31;
//        int GetStateEnd = 1000;
//    }
//
//    // GetStateType
//    interface GetStateType {
//        int GetArmState = 0;
//        int GetFZoneArmState = 1;
//        int GetNeglectState = 2;
//        int GetRelayState = 3;
//        int GetZoneArmState = 4;
//        int GetFaultState = 5;
//        int GetAcState = 6;
//        int GetDcState = 7;
//        int GetAlarmSoundState = 8;
//        int GetWarningSignsState = 9;
//    }
//
//    // ControlType
//    interface ControlType {
//        int Ctrl_Arm = 0;
//        int Ctrl_DisArm = 1;
//        int Ctrl_FZoneArm = 2;
//        int Ctrl_FZoneDisArm = 3;
//        int Ctrl_Reset = 4;
//        int Ctrl_Neglect = 5;
//        int Ctrl_UnNeglect = 6;
//        int Ctrl_RelayOn = 7;
//        int Ctrl_RelayOff = 8;
//        int Ctrl_ZoneArm = 9;
//        int Ctrl_ZoneDisArm = 10;
//        int Ctrl_CloseAlarmSound = 11;
//    }
//
//    // DeviceType (partial)
//    interface DeviceType {
//        int Device_Unknown = 0;
//        int Device_485NORMAL = 1;
//        int Device_216 = 2;
//        int Device_3110 = 3;
//        int Device_7800 = 4;
//        int Device_236GPlus = 5;
//        int Device_969IP = 6;
//        int Device_8604 = 7;
//        int Device_5200 = 8;
//        int Device_259 = 9;
//        int Device_136 = 10;
//        int Device_812_KR816 = 11;
//        int Device_Vista_4110 = 12;
//        int Device_Vista_20_120 = 13;
//        int Device_CK = 14;
//        int Device_QR_236GPlus = 15;
//        int Device_831AI = 16;
//        int Device_239G_24 = 17;
//        int Device_2300Plus = 18;
//    }
//
//    // ZoneType (partial)
//    interface ZoneType {
//        int ZoneType_Unknown = 0;
//        int Wired = 1;
//        int Wireless = 2;
//        int Bus = 3;
//        int LocalRelay = 4;
//        int ExtensionRelay1 = 5;
//        int ExtensionRelay2 = 6;
//        int ExtensionRelay3 = 7;
//        int ExtensionRelay4 = 8;
//        int ExtensionRelay5 = 9;
//        int ExtensionRelay6 = 10;
//        int ExtensionRelay7 = 11;
//        int ExtensionRelay8 = 12;
//        int Zone = 13;
//        int Keyboard_SOS = 14;
//        int Keyboard_IllegalOpen = 15;
//        int Host_IllegalOpen = 16;
//        int HumanoidWarning = 17;
//        int DectorIllegalOpen = 18;
//    }
//
//    // DeviceProperty
//    interface DeviceProperty {
//        int Device_NetworkCard1 = 0;
//        int Device_NetworkCard2 = 1;
//        int Decice_Info = 2;
//    }
//
//    // --- Structures ---
//    // Use 1-byte packing (ALIGN_NONE) to match #pragma pack(1)
//    abstract class PackedStructure extends Structure {
//        public PackedStructure() {
//            super();
//            this.setAlignType(Structure.ALIGN_NONE); // 1-byte alignment
//        }
//
//        public PackedStructure(Pointer p) {
//            super(p);
//            this.setAlignType(Structure.ALIGN_NONE);
//            read();
//        }
//
//        @Override
//        protected List<String> getFieldOrder() {
//            return super.getFieldOrder();
//        }
//    }
//
//    // AlarmDevHostNo { byte SKHostNoH; byte SKHostNoL; byte SKHostNoGroup; }
//    @Structure.FieldOrder({ "SKHostNoH", "SKHostNoL", "SKHostNoGroup" })
//    class AlarmDevHostNo extends PackedStructure {
//        public byte SKHostNoH;
//        public byte SKHostNoL;
//        public byte SKHostNoGroup;
//
//        public AlarmDevHostNo() { super(); }
//
//        public AlarmDevHostNo(Pointer p) { super(p); }
//
//        public AlarmDevHostNo(byte h, byte l, byte g) {
//            this.SKHostNoH = h;
//            this.SKHostNoL = l;
//            this.SKHostNoGroup = g;
//        }
//
//        public int toInt() {
//            int hi = SKHostNoH & 0xFF;
//            int lo = SKHostNoL & 0xFF;
//            int group = SKHostNoGroup & 0xFF;
//            return (hi << 16) | (lo << 8) | group;
//        }
//
//        @Override
//        public String toString() {
//            return String.format("HostNo{H=0x%02X, L=0x%02X, G=0x%02X}", SKHostNoH, SKHostNoL, SKHostNoGroup);
//        }
//    }
//
//    // NETWORKCARD
//    @Structure.FieldOrder({ "cDhcpSwitch", "cIp", "cGateway", "cSubnetMask", "cServerAddr1", "nPort1", "cServerAddr2", "nPort2", "cMacAddr" })
//    class NETWORKCARD extends PackedStructure {
//        public byte cDhcpSwitch;            // char
//        public byte[] cIp = new byte[4];    // unsigned char[4]
//        public byte[] cGateway = new byte[4];
//        public byte[] cSubnetMask = new byte[4];
//        public byte[] cServerAddr1 = new byte[4];
//        public short nPort1;                // unsigned short -> short (be careful with sign)
//        public byte[] cServerAddr2 = new byte[4];
//        public short nPort2;
//        public byte[] cMacAddr = new byte[6];
//
//        public NETWORKCARD() { super(); }
//        public NETWORKCARD(Pointer p) { super(p); }
//
//        public String ipToString(byte[] arr) {
//            if (arr == null) return "";
//            int a = arr[0] & 0xFF;
//            int b = arr[1] & 0xFF;
//            int c = arr[2] & 0xFF;
//            int d = arr[3] & 0xFF;
//            return String.format("%d.%d.%d.%d", a, b, c, d);
//        }
//
//        @Override
//        public String toString() {
//            return "NETWORKCARD{" +
//                    "dhcp=" + (cDhcpSwitch & 0xFF) +
//                    ", ip=" + ipToString(cIp) +
//                    ", gw=" + ipToString(cGateway) +
//                    ", mask=" + ipToString(cSubnetMask) +
//                    ", server1=" + ipToString(cServerAddr1) + ":" + (nPort1 & 0xFFFF) +
//                    ", server2=" + ipToString(cServerAddr2) + ":" + (nPort2 & 0xFFFF) +
//                    ", mac=" + macToString() +
//                    '}';
//        }
//
//        private String macToString() {
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < cMacAddr.length; i++) {
//                if (i > 0) sb.append(':');
//                sb.append(String.format("%02X", cMacAddr[i]));
//            }
//            return sb.toString();
//        }
//    }
//
//    // ZONEINFO
//    @Structure.FieldOrder({
//            "belongPartition", "zoneIndex", "zoneType",
//            "bArmstatus", "bNeglect", "bFault", "b24HourArm",
//            "bAlarmstatus", "bLoststatus", "bLowVoltage", "bIllegalOpen"
//    })
//    class ZONEINFO extends PackedStructure {
//        public int belongPartition;
//        public int zoneIndex;
//        public int zoneType; // ZoneType as int
//
//        // bools in C -> map to byte here for safety (0/1)
//        public byte bArmstatus;
//        public byte bNeglect;
//        public byte bFault;
//        public byte b24HourArm;
//        public byte bAlarmstatus;
//        public byte bLoststatus;
//        public byte bLowVoltage;
//        public byte bIllegalOpen;
//
//        public ZONEINFO() { super(); }
//        public ZONEINFO(Pointer p) { super(p); }
//
//        public boolean asBool(byte v) { return v != 0; }
//
//        @Override
//        public String toString() {
//            return "ZONEINFO{" +
//                    "partition=" + belongPartition +
//                    ", index=" + zoneIndex +
//                    ", type=" + zoneType +
//                    ", arm=" + asBool(bArmstatus) +
//                    ", neglect=" + asBool(bNeglect) +
//                    ", fault=" + asBool(bFault) +
//                    ", 24h=" + asBool(b24HourArm) +
//                    ", alarm=" + asBool(bAlarmstatus) +
//                    ", lost=" + asBool(bLoststatus) +
//                    ", lowVolt=" + asBool(bLowVoltage) +
//                    ", illegalOpen=" + asBool(bIllegalOpen) +
//                    '}';
//        }
//    }
//
//    // HOSTINFO
//    @Structure.FieldOrder({ "devHostNo", "devHostIp", "devType", "bArm", "zoneInfos", "zoneCount" })
//    class HOSTINFO extends PackedStructure {
//        public AlarmDevHostNo devHostNo;       // nested struct
//        public byte[] devHostIp = new byte[20]; // char[20]
//        public int devType;                    // DeviceType (enum int)
//        public byte bArm;                      // bool -> byte
//        public Pointer zoneInfos;              // pointer to ZoneInfo array (ZoneInfo* in C)
//        public int zoneCount;
//
//        public HOSTINFO() { super(); }
//        public HOSTINFO(Pointer p) { super(p); }
//
//        public String devHostIpString() {
//            int len = 0;
//            while (len < devHostIp.length && devHostIp[len] != 0) len++;
//            return new String(devHostIp, 0, len);
//        }
//
//        public ZONEINFO[] readZones() {
//            if (zoneInfos == null || Pointer.NULL.equals(zoneInfos) || zoneCount <= 0) return new ZONEINFO[0];
//            ZONEINFO[] zones = (ZONEINFO[]) (new ZONEINFO(zoneInfos)).toArray(zoneCount);
//            return zones;
//        }
//
//        @Override
//        public String toString() {
//            return "HOSTINFO{" +
//                    "hostNo=" + devHostNo +
//                    ", ip=" + devHostIpString() +
//                    ", devType=" + devType +
//                    ", arm=" + (bArm != 0) +
//                    ", zoneCount=" + zoneCount +
//                    '}';
//        }
//    }
//
//    // --- Callbacks (StdCallCallback for __stdcall) ---
//    // typedef  void(__stdcall* pEventCallback) (CallBackType callBackType, AlarmDevHostNo devHostNo, char devHostIp[20], int zone, ZoneType zoneType, char time[25]);
//    interface pEventCallback extends Callback {
//        void invoke(int callBackType, AlarmDevHostNo devHostNo, byte[] devHostIp /* length 20 */, int zone, int zoneType, byte[] time /* length 25 */);
//    }
//
//    // typedef  void(__stdcall* pEventCallbackEx) (CallBackType callBackType, AlarmDevHostNo devHostNo, char devHostIp[20], int zone, ZoneType zoneType, char time[25], long long Remark);
//    interface pEventCallbackEx extends Callback {
//        void invoke(int callBackType, AlarmDevHostNo devHostNo, byte[] devHostIp /*20*/, int zone, int zoneType, byte[] time /*25*/, long Remark);
//    }
//
//    // typedef  void(__stdcall* pStateCallBack) (GetStateType getStateType, AlarmDevHostNo devHostNo, int zone, ZoneType  zoneType, CallBackType State);
//    interface pStateCallBack extends Callback {
//        void invoke(int getStateType, AlarmDevHostNo devHostNo, int zone, int zoneType, int state);
//    }
//
//    // --- Function mappings ---
//    // Alarm_Init(int port, char* ListenIPAddr = NULL);
//    boolean Alarm_Init(int port, String ListenIPAddr); // pass null for default
//
//    // Alarm_Cleanup();
//    boolean Alarm_Cleanup();
//
//    // Alarm_SetEventCallBack(pEventCallback fun);
//    void Alarm_SetEventCallBack(pEventCallback fun);
//
//    // Alarm_SetMessageCallBack(pStateCallBack fun);
//    void Alarm_SetMessageCallBack(pStateCallBack fun);
//
//    // Alarm_ControlDevice(ControlType controlType, AlarmDevHostNo devHostNo, byte* pass, int izone, ZoneType zoneType);
//    int Alarm_ControlDevice(int controlType, AlarmDevHostNo devHostNo, byte[] pass /*password bytes*/, int izone, int zoneType);
//
//    // Alarm_QueryDevice(GetStateType getStateType, AlarmDevHostNo devHostNo, int zone, ZoneType zoneType);
//    int Alarm_QueryDevice(int getStateType, AlarmDevHostNo devHostNo, int zone, int zoneType);
//
//    // Alarm_GetDeviceProperty(DeviceProperty deviceProperty, AlarmDevHostNo devHostNo);
//    Pointer Alarm_GetDeviceProperty(int deviceProperty, AlarmDevHostNo devHostNo);
//
//    // Alarm_SetDeviceProperty(AlarmDevHostNo devHostNo, wchar_t* message, int messageLen);
//    boolean Alarm_SetDeviceProperty(AlarmDevHostNo devHostNo, WString message, int messageLen);
//
//    // Alarm_SetDeviceType(DeviceType deviceType, AlarmDevHostNo devHostNo, char devHostIp[20] = NULL);
//    int Alarm_SetDeviceType(int deviceType, AlarmDevHostNo devHostNo, String devHostIp);
//
//    // Alarm_Proofreading_Time();
//    boolean Alarm_Proofreading_Time();
//
//    // Alarm_SetEventCallBackEx(pEventCallbackEx fun);
//    void Alarm_SetEventCallBackEx(pEventCallbackEx fun);
//
//    // Alarm_GetVersion();
//    Pointer Alarm_GetVersion(); // const char* -> Pointer; use getString(0) to read
//}
