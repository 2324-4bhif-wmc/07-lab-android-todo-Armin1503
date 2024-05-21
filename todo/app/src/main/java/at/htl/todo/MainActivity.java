package at.htl.todo;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import javax.inject.Inject;
import at.htl.todo.ui.layout.MainView;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    public static final String TAG = MainActivity.class.getName();
    @Inject
    MainView mainView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView.setContentOfActivity(this);
    }
}