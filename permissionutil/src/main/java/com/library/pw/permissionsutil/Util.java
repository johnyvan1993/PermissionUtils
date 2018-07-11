package com.library.pw.permissionsutil;

import android.Manifest;

/**
 * Created by android on 12/03/2018.
 */

public class Util {

    public static String[] getPermissions(GroupPermissions groupPermissions) {
        switch (groupPermissions) {
            case CAMERA:
                return new String[]{Manifest.permission.CAMERA};
            case STORAGE:
                return new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            case NETWORK:
                return new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE};
            case LOCATION:
                return new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            case CONTACT:
                return new String[]{Manifest.permission.READ_CONTACTS};
            case MEDIA:
                return new String[]{Manifest.permission.RECORD_AUDIO};
            case GRAPHICS:
                return new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW};
            case CALLS:
                return new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE};
            case BOOT:
                return new String[]{Manifest.permission.RECEIVE_BOOT_COMPLETED, Manifest.permission.WAKE_LOCK};
            case SMS:
                return new String[]{Manifest.permission.READ_SMS};
            default:
                return null;
        }
    }
}