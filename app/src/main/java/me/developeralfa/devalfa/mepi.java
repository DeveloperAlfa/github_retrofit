package me.developeralfa.devalfa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by devalfa on 17/3/18.
 */

public interface mepi {
    @GET("{uname}")
    Call<User> detailsLoader(@Path("uname") String user);
}
