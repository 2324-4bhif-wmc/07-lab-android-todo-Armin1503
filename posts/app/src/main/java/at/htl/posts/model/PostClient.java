package at.htl.posts.model;

import at.htl.posts.model.entity.Post;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/posts")
@Consumes(MediaType.APPLICATION_JSON)
public interface PostClient {
    @GET
    Post[] all();
}
