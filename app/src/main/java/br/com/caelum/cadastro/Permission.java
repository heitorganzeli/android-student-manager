package br.com.caelum.cadastro;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    private static final int CODE = 123;
    private static final List<String> permissionList = new ArrayList<>();

    public static void checkPermissions(Activity activity) {
        String[] permissions = {Manifest.permission.CALL_PHONE,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
        };

        for (String permission : permissions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permission);
                }
            }
        }

        request(activity);
    }

    private static void request(Activity activity) {
        String[] array = permissionList.toArray(new String[]{});

        if (permissionList.size() > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(array, CODE);
            }

        }
    }
}
