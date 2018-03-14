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
                return new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.MODIFY_AUDIO_SETTINGS};
            case GRAPHICS:
                return new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW};
            case CALLS:
                return new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE};
            case BOOT:
                return new String[]{Manifest.permission.RECEIVE_BOOT_COMPLETED, Manifest.permission.WAKE_LOCK};
            case ROOT:
                return new String[]{READ_PRIVILEGED_PHONE_STATE, Manifest.permission.WRITE_SECURE_SETTINGS, Manifest.permission.MODIFY_PHONE_STATE, Manifest.permission.WRITE_CONTACTS};
            case SMS:
                return new String[]{WRITE_SMS, Manifest.permission.READ_SMS};
            case DANGEROUS:
                return new String[]{Manifest.permission.LOCATION_HARDWARE, Manifest.permission.CAPTURE_VIDEO_OUTPUT, Manifest.permission.CAPTURE_SECURE_VIDEO_OUTPUT, Manifest.permission.ANSWER_PHONE_CALLS, Manifest.permission.PROCESS_OUTGOING_CALLS};
            default:
                return null;
        }
    }

    private static String READ_PRIVILEGED_PHONE_STATE = "android.permission.READ_PRIVILEGED_PHONE_STATE";
    private static String WRITE_SMS = "android.permission.WRITE_SMS";
}