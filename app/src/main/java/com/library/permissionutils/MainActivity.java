package com.library.permissionutils;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.library.permissionutils.di.DaggerMyComponent;
import com.library.permissionutils.di.MyModule;
import com.library.pw.permissionsutil.GroupPermissions;
import com.library.pw.permissionsutil.OnPermissionGranted;
import com.library.pw.permissionsutil.PermissionUtil;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    PermissionUtil mPermissionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMyComponent.builder().myModule(new MyModule(this)).build().inject(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPermissionUtil.requirePermissions(new GroupPermissions[]{GroupPermissions.CAMERA}, new OnPermissionGranted() {
            @Override
            public void onExecute() {
                Toast.makeText(getBaseContext(), "success", Toast.LENGTH_LONG).show();
            }
        });
    }
}