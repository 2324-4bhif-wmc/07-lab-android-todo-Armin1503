package at.htl.posts.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.posts.util.store.Store;

@Singleton
public class ModelStore extends Store<Model> {

    @Inject
    ModelStore() {
        super(Model.class, new Model());
    }

    public void setPosts(Post[] posts) {
        apply(model -> model.posts = posts);
    }
}