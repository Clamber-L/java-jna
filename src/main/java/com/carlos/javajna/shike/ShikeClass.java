//package com.carlos.javajna.shike;
//
//import com.sun.jna.Pointer;
//
//public class ShikeClass {
//
//    public static void main(String[] args) {
//        ShikeInterface sdk = ShikeInterface.INSTANCE;
//
//        boolean ok = sdk.Alarm_Init(6000, null);
//        System.out.println("Init ok: " + ok);
//
//        // register simple event callback
//        sdk.Alarm_SetEventCallBack(new ShikeInterface.pEventCallback() {
//            @Override
//            public void invoke(int callBackType, ShikeInterface.AlarmDevHostNo devHostNo, byte[] devHostIp, int zone, int zoneType, byte[] time) {
//                String ip = "";
//                try {
//                    int len = 0;
//                    while (len < devHostIp.length && devHostIp[len] != 0) len++;
//                    ip = new String(devHostIp, 0, len);
//                } catch (Exception e) { ip = "err"; }
//
//                int cb = callBackType;
//                System.out.println("Event cbType=" + cb + " host=" + devHostNo + " ip=" + ip + " zone=" + zone + " zoneType=" + zoneType);
//            }
//        });
//
//        // get version
//        Pointer p = sdk.Alarm_GetVersion();
//        if (p != null && !Pointer.NULL.equals(p)) {
//            String v = p.getString(0);
//            System.out.println("DLL Version: " + v);
//        }
//
//        // Example: construct host no and query
//        ShikeInterface.AlarmDevHostNo hostNo = new ShikeInterface.AlarmDevHostNo((byte)0x01, (byte)0x02, (byte)0x03);
//        int ret = sdk.Alarm_QueryDevice(ShikeInterface.GetStateType.GetArmState, hostNo, 0, ShikeInterface.ZoneType.ZoneType_Unknown);
//        System.out.println("QueryDevice ret: " + ret);
//
//        // cleanup
//        boolean cleaned = sdk.Alarm_Cleanup();
//        System.out.println("Cleanup: " + cleaned);
//    }
//}
