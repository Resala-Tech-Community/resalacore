import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface UserService {

    @GET("/event/all")
    public Call<List<Event>> getUsers();

}
