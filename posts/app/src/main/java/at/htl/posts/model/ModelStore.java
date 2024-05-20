package at.htl.posts.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.posts.model.entity.Post;
import at.htl.posts.model.entity.User;
import at.htl.posts.model.entity.dto.UserDto;
import at.htl.posts.util.store.Store;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import androidx.compose.runtime.rxjava3.RxJava3AdapterKt;


@Singleton
public class ModelStore extends Store<Model> {

    @Inject
    ModelStore() {
        super(Model.class, new Model());
    }

    public void setPosts(Post[] posts) {
        apply(model -> {
            for(Post post : posts) {
                for (User user : model.users) {
                    if (Objects.equals(user.id, post.userId)) {
                        post.user = user;
                    }
                }
            }
            model.posts = posts;
        });
    }

    public void setUsers(UserDto[] userDtos) {
        User[] users = mapUser(userDtos);
        apply(model -> model.users = users);
    }

    private User[] mapUser(UserDto[] userDtos) {
        List<User> users = new ArrayList<>();

        for (UserDto userDto : userDtos) {
            User user = new User(userDto.id, userDto.name, userDto.username,
                    userDto.email, userDto.phone);
            users.add(user);
        }

        return users.toArray(new User[0]);
    }
}