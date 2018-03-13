package com.library.permissionutils.di;

import com.library.permissionutils.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by android on 13/03/2018.
 */
@Singleton
@Component(modules = {MyModule.class})
public interface MyComponent {
    void inject(MainActivity mainActivity);
}
