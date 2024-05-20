package at.htl.posts.model;

import at.htl.posts.model.entity.dto.UserDto;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public interface UserClient {

    @GET
    UserDto[] all();
}
