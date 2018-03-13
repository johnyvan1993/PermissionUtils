package com.library.permissionutils.di;

import android.app.Activity;

import com.library.pw.permissionsutil.PermissionUtil;

import dagger.Module;
import dagger.Provides;

/**
 * Created by android on 13/03/2018.
 */
@Module
public class MyModule {
    private Activity mActivity;

    public MyModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    PermissionUtil provideMain() {
        return new PermissionUtil(mActivity);
    }
}
