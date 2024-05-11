package at.htl.posts;

import android.app.Application;
import android.util.Log;

import javax.inject.Singleton;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
@Singleton
public class PostApplication extends Application {

    static final String TAG = PostApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "App started ...");
    }

}
