package com.carlos.javajna.alarm.struct;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

// HOSTINFO 结构体 (依赖于 AlarmDevHostNo, AlarmSDKConstants.DeviceType, ZoneInfo)
@Structure.FieldOrder({
        "devHostNo", "devHostIp", "devType", "bArm", "zoneInfos", "zoneCount"
})
public class HostInfo extends Structure {
    public AlarmDevHostNo devHostNo;    // 主机编号
    public byte[] devHostIp = new byte[20]; // 主机IP (char devHostIp[20] -> byte[20] for fixed-size char array)
    public int devType;           // 主机类型，映射到 AlarmSDKConstants.DeviceType
    public boolean bArm;          // 布撤防状态
    // public ZoneInfo.ByReference zoneInfos; // 如果C++的ZoneInfo* zoneInfos是指向单个ZoneInfo
    public Pointer zoneInfos; // 如果ZoneInfo* zoneInfos指向一个ZoneInfo数组的开始，需要手动处理内存和数组遍历
    public int zoneCount;         // 子信息数量

    public HostInfo() {}

    public HostInfo(Pointer p) {
        super(p);
        read();
    }

    public static class ByReference extends HostInfo implements Structure.ByReference {}
    public static class ByValue extends HostInfo implements Structure.ByValue {}
}
