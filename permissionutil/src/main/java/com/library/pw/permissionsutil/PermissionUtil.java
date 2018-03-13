package com.library.pw.permissionsutil;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.library.pw.permissionsutil.di.ContextModule;
import com.library.pw.permissionsutil.di.DaggerPermissionComponent;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * Created by android on 12/03/2018.
 */

public class PermissionUtil {
    @Inject
    Activity mActivity;

    public PermissionUtil(Activity activity) {
        DaggerPermissionComponent.builder().contextModule(new ContextModule(activity)).build().inject(this);
    }

    private Result mResult;
    private OnPermissionGranted mOnPermissionGrantedListener;

    private Result check(GroupPermissions groupPermissions) {

        if (groupPermissions == null) {
            mResult
                    .reset()
                    .setMessage("No Parameter!");
            return mResult;
        }

        if (mResult == null) {
            mResult = new Result();
        }

        String[] lists = Util.getPermissions(groupPermissions);

        if (lists == null) {
            mResult
                    .reset()
                    .setMessage("These permissions does not have in this version!");
            return mResult;
        }

        mResult.setPermissions(lists);

        for (String p : lists) {
            int granted = ActivityCompat.checkSelfPermission(mActivity, p);
            if (granted != PackageManager.PERMISSION_GRANTED) {
                Log.d(getClass().getCanonicalName(), "permission " + p + " denied!");
                mResult
                        .reset()
                        .setStatus("denied")
                        .setMessage("Have a permission may be not declared!");
                return mResult;
            }
        }

        mResult
                .reset()
                .setStatus("granted")
                .setMessage("these permissions granted!");

        return mResult;
    }

    //callback nullable
    public boolean requirePermissions(GroupPermissions groupPermissions, OnPermissionGranted onPermissionGranted) {
        mOnPermissionGrantedListener = onPermissionGranted;

        Result result = check(groupPermissions);
        String status = result.getStatus();
        String[] list = result.getPermissions();
        System.out.println(result.getMessage());
        switch (status) {
            case "granted":
                if (mOnPermissionGrantedListener != null) {
                    mOnPermissionGrantedListener.onExecute();
                }
                return true;
            case "denied":
                ActivityCompat.requestPermissions(mActivity, list, 10000);
                return false;
            default:
                return false;
        }
    }

    //callback nullable
    public void requirePermissions(GroupPermissions[] listGroup, OnPermissionGranted onPermissionGranted) {
        Boolean[] booleans = new Boolean[]{};
        for (GroupPermissions groupPermissions : listGroup) {
            boolean isGranted = requirePermissions(groupPermissions, null);
            if (isGranted) {
                Arrays.fill(booleans, Boolean.TRUE);
            }
        }

        mOnPermissionGrantedListener = onPermissionGranted;

        int listSize = listGroup.length;
        int bSize = booleans.length;
        if (bSize == listSize) {
            if (mOnPermissionGrantedListener != null) {
                mOnPermissionGrantedListener.onExecute();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int r = grantResults[0];
        boolean isGranted = r == PackageManager.PERMISSION_GRANTED;

        if (requestCode == 10000) {
            if (isGranted) {
                if (mOnPermissionGrantedListener != null) {
                    mOnPermissionGrantedListener.onExecute();
                }
            } else {
                Log.d(getClass().getCanonicalName(), "User denied this permissions");
            }
        }
    }
}
