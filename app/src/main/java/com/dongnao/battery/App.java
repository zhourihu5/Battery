package com.dongnao.battery;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;

import com.dongnao.battery.location.LocationService;

import java.util.List;



public class App extends Application {

    private Intent location;
    private static App applicationt;

    @Override
    public void onCreate() {
        super.onCreate();
        if (!TextUtils.equals(BuildConfig.APPLICATION_ID + ":location", getProcessName(Process
                .myPid()))) {
            applicationt = this;
            location = new Intent(this, LocationService.class);
            startService(location);
        }
    }

    public static App getApplicationt() {
        return applicationt;
    }

    public Intent getLocation() {
        return location;
    }

    String getProcessName(int pid) {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
}
