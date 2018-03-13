package com.library.pw.permissionsutil.di;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by android on 12/03/2018.
 */
@Module
public class ContextModule {
    private Activity mActivity;

    public ContextModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    Activity provideContext() {
        if (mActivity != null) {
            return mActivity;
        }
        return null;
    }
}
