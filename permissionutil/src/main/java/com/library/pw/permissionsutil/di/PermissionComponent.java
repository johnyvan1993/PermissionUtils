package com.library.pw.permissionsutil.di;

import com.library.pw.permissionsutil.PermissionUtil;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by android on 12/03/2018.
 */
@Singleton
@Component(modules = {ContextModule.class})
public interface PermissionComponent {
    void inject(PermissionUtil permissionUtil);
}
