package com.carlos.javajna.alarm;

import com.carlos.javajna.alarm.struct.AlarmDevHostNo;
import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface CallbackInterface {

    interface StateCallback extends Callback {
        void invoke(int getStateType, AlarmDevHostNo.ByValue devHostNo, int zone, int zoneType, int state);
    }

    // 修改 EventCallbackEx 和 EventCallback 中的 byte[] 为 Pointer
    interface EventCallbackEx extends Callback {
        void invoke(int callBackType, AlarmDevHostNo.ByValue devHostNo, Pointer devHostIp, int zone, int zoneType, Pointer time, long remark);
    }

    interface EventCallback extends Callback {
        void invoke(int callBackType, AlarmDevHostNo.ByValue devHostNo, Pointer devHostIp, int zone, int zoneType, Pointer time);
    }
}
