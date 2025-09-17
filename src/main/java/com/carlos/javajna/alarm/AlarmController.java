package com.carlos.javajna.alarm;

import com.carlos.javajna.alarm.struct.AlarmDevHostNo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alarm")
public class AlarmController {

    @GetMapping("device")
    public void device() {
        String devNo = "111101"; // 你的6位设备编号字符串
        AlarmDevHostNo.ByValue hostNo = new AlarmDevHostNo.ByValue();
        hostNo.SKHostNoH = (byte) Integer.parseInt(devNo.substring(0, 2), 16);
        hostNo.SKHostNoL = (byte) Integer.parseInt(devNo.substring(2, 4), 16);
        hostNo.SKHostNoGroup = (byte) Integer.parseInt(devNo.substring(4, 6), 16);

        hostNo.write(); // 写入JNA内部内存
//        int queryResult = AlarmSDKLibrary.getInstance().Alarm_QueryDevice(AlarmSDKConstants.GetStateType.GetArmState.getCode(),
//                hostNo, 1, AlarmSDKConstants.ZoneType.ZoneType_Unknown.getCode());
//        System.out.println("QueryDevice (GetArmState) result: " + queryResult);

        int control_res = AlarmSDKLibrary.getInstance().Alarm_ControlDevice(AlarmSDKConstants.ControlType.Ctrl_Arm.getCode(), hostNo, new byte[0], 0, AlarmSDKConstants.ZoneType.ZoneType_Unknown.getCode());
        System.out.println("control result: " + control_res);
    }
}
