package at.htl.posts.model;

import android.util.Log;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.posts.util.resteasy.RestApiClientBuilder;

@Singleton
public class UserService {
    static final String TAG = UserService.class.getSimpleName();
    public static String JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com";
    public final UserClient userClient;
    public final ModelStore store;

    @Inject
    UserService(RestApiClientBuilder builder, ModelStore store) {
        Log.i(TAG, "Creating UserService with base url: " + JSON_PLACEHOLDER_BASE_URL);
        userClient = builder.build(UserClient.class, JSON_PLACEHOLDER_BASE_URL);
        this.store = store;
    }
    public void getAll() {
        CompletableFuture
                .supplyAsync(() -> userClient.all())
                .thenAccept(store::setUsers);
    }
}
