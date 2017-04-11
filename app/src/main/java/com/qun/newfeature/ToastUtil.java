package com.qun.newfeature;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Qun on 2017/4/11.
 */

public class ToastUtil {
    private static Toast sToast;

    public static void showToast(Context context, String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
        sToast.setText(msg);
        sToast.show();
    }
}
