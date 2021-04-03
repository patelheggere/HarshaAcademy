package com.patelheggere.harshaacademy;

import android.app.Application;
import android.location.Location;
import android.os.StrictMode;


public class BaseApplication extends Application {
    private static BaseApplication mInstance;
   // private static StorageReference firebaseStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        // ApiClient.intialise();
       /* if(isDeve()) {
            ApiClient.setBaseUrl(AppConstants.appBaseUrlDev);
        }
        else
        {
            ApiClient.setBaseUrl(AppConstants.appBaseUrl);

        }*/

    }

    /*
    public static synchronized StorageReference getFirebaseStorage()
    {
        if(BuildConfig.DEBUG) {
            System.out.println("debug");
            Log.d("", "getFireBaseRef: Debug");
            firebaseStorage = FirebaseStorage.getInstance().getReference().child("test");
        }
        else {
            Log.d("", "getFireBaseRef: release");
            firebaseStorage = FirebaseStorage.getInstance().getReference().child("prod");
        }
        return firebaseStorage;
    }
*/
    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }

}