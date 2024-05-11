package at.htl.posts.model;

import android.util.Log;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.posts.util.resteasy.RestApiClientBuilder;

@Singleton
public class PostService {
    static final String TAG = PostService.class.getSimpleName();
    public static String JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com";
    public final PostClient postClient;
    public final ModelStore store;

    @Inject
    PostService(RestApiClientBuilder builder, ModelStore store) {
        Log.i(TAG, "Creating PostService with base url: " + JSON_PLACEHOLDER_BASE_URL);
        postClient = builder.build(PostClient.class, JSON_PLACEHOLDER_BASE_URL);
        this.store = store;
    }
    public void getAll() {
        CompletableFuture
                .supplyAsync(() -> postClient.all())
                .thenAccept(store::setPosts);
    }
}
