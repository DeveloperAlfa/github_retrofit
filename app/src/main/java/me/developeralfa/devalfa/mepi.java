package me.developeralfa.devalfa;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by devalfa on 17/3/18.
 */

public interface mepi {
    @GET("developeralfa")
    Call<User> detailsLoader();
}
