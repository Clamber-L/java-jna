package com.carlos.javajna.alarm.struct;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

// NETWORKCARD 结构体
@Structure.FieldOrder({
        "cDhcpSwitch", "cIp", "cGateway", "cSubnetMask",
        "cServerAddr1", "nPort1", "cServerAddr2", "nPort2", "cMacAddr"
})
public class NetworkCard extends Structure {
    public byte cDhcpSwitch;      // DHCP开关
    public byte[] cIp = new byte[4];     // 设备IP
    public byte[] cGateway = new byte[4];  // 网关
    public byte[] cSubnetMask = new byte[4]; // 子网掩码
    public byte[] cServerAddr1 = new byte[4]; // 服务器1网络地址
    public short nPort1;           // 服务器1端口
    public byte[] cServerAddr2 = new byte[4]; // 服务器2网络地址
    public short nPort2;           // 服务器2端口
    public byte[] cMacAddr = new byte[6];    // MAC地址

    public NetworkCard() {}

    public NetworkCard(Pointer p) {
        super(p);
        read();
    }

    public static class ByReference extends NetworkCard implements Structure.ByReference {}
    public static class ByValue extends NetworkCard implements Structure.ByValue {}
}
