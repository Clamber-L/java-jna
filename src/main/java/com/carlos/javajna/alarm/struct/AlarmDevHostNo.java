package com.carlos.javajna.alarm.struct;

import com.sun.jna.Structure;

@Structure.FieldOrder({"SKHostNoH", "SKHostNoL", "SKHostNoGroup"})
public class AlarmDevHostNo extends Structure implements Structure.ByValue {
    public byte SKHostNoH;    // 编号高字节
    public byte SKHostNoL;    // 编号低字节
    public byte SKHostNoGroup; // 组号

    // 用于ByReference类型，当结构体作为指针传递时
    public static class ByReference extends AlarmDevHostNo implements Structure.ByReference {}
    // 用于ByValue类型，当结构体作为值传递时 (C++ 函数按值传递结构体的情况)
    public static class ByValue extends AlarmDevHostNo implements Structure.ByValue {}
}
