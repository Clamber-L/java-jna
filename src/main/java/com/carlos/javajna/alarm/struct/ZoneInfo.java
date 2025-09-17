package com.carlos.javajna.alarm.struct;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

// ZONEINFO 结构体 (依赖于 AlarmSDKConstants.ZoneType)
@Structure.FieldOrder({
        "belongPartition", "zoneIndex", "zoneType", "bArmstatus",
        "bNeglect", "bFault", "b24HourArm", "bAlarmstatus",
        "bLoststatus", "bLowVoltage", "bIllegalOpen"
})
public class ZoneInfo extends Structure {
    public int belongPartition; // 所属分区
    public int zoneIndex;     // 防区/分区号
    public int zoneType;      // 类型（有线、无线、总线、分区），映射到 AlarmSDKConstants.ZoneType
    public boolean bArmstatus;  // 布撤防状态
    public boolean bNeglect;    // 旁路状态（分区没有旁路状态）
    public boolean bFault;      // 故障状态（分区没有故障状态）
    public boolean b24HourArm;  // 是否24小时防区
    public boolean bAlarmstatus;// 报警状态
    public boolean bLoststatus; // 失联状态
    public boolean bLowVoltage; // 低压状态
    public boolean bIllegalOpen; // 防拆状态

    public ZoneInfo() {}

    public ZoneInfo(Pointer p) {
        super(p);
        read();
    }

    public static class ByReference extends ZoneInfo implements Structure.ByReference {}
    public static class ByValue extends ZoneInfo implements Structure.ByValue {}
}
