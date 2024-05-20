package at.htl.posts;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.ComponentActivity;
import javax.inject.Inject;

import at.htl.posts.model.Model;
import at.htl.posts.model.ModelStore;
import at.htl.posts.model.PostService;
import at.htl.posts.model.UserService;
import at.htl.posts.ui.layout.MainView;
import at.htl.posts.util.Config;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainView mainView;

    @Inject
    ModelStore modelStore;

    @Inject
    PostService postService;

    @Inject
    UserService userService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Config.load(this);
        var base_url = Config.getProperty("json.placeholder.baseurl");
        Log.i(TAG, "onCreate: " + base_url);
        mainView.buildContent(this);
        userService.getAll();
        postService.getAll();
    }
}
