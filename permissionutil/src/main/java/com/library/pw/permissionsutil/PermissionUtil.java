package com.library.pw.permissionsutil;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.library.pw.permissionsutil.di.ContextModule;
import com.library.pw.permissionsutil.di.DaggerPermissionComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private int size = 0;
    private int count = 0;

    private Result check(GroupPermissions groupPermissions) {

        if (groupPermissions == null) {
            mResult
                    .reset()
                    .setMessage("No Parameter!");
            return mResult;
        }

        String[] lists = Util.getPermissions(groupPermissions);
        return handleList(lists);
    }

    private Result handleList(String[] lists) {
        if (mResult == null) {
            mResult = new Result();
        }

        if (lists == null) {
            mResult
                    .reset()
                    .setMessage("These permissions does not have in this version!");
            return mResult;
        }

        size = lists.length;
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
        return handleResult(result);
    }

    private boolean handleResult(Result result) {
        String status = result.getStatus();
        String[] list = result.getPermissions();
        Log.d(getClass().getCanonicalName(), result.getMessage());
        switch (status) {
            case "granted":
                if (mOnPermissionGrantedListener != null) {
                    mOnPermissionGrantedListener.onExecute();
                }
                return true;
            case "denied":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mActivity.requestPermissions(list, 10000);
                } else {
                    ActivityCompat.requestPermissions(mActivity, list, 10000);
                }
                return false;
            default:
                return false;
        }
    }

    //callback nullable
    public void requirePermissions(GroupPermissions[] listGroup, OnPermissionGranted onPermissionGranted) {
        List<String> results = new ArrayList<>();
        for (GroupPermissions groupPermissions : listGroup) {
            String[] list = Util.getPermissions(groupPermissions);
            if (list != null && list.length > 0) {
                Collections.addAll(results, list);
            }
        }

        mOnPermissionGrantedListener = onPermissionGranted;
        Result result = handleList(results.toArray(new String[]{}));
        handleResult(result);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 10000) {
            if (grantResults.length > 0) {

                for (int i : grantResults) {
                    boolean isGranted = i == PackageManager.PERMISSION_GRANTED;
                    if (isGranted) {
                        Log.d(getClass().getCanonicalName(), "permission " + permissions[count] + " is granted");
                        ++count;
                    }
                }

                if (count == size) {
                    if (mOnPermissionGrantedListener != null) {
                        mOnPermissionGrantedListener.onExecute();
                    }
                }

                count = 0;
                size = 0;
            }
        }
    }
}
