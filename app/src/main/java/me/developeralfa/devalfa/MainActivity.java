package me.developeralfa.devalfa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    EditText editText;
    TextView textView;
    Button button;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress);
        listView = findViewById(R.id.list);
        textView = findViewById(R.id.name);
        editText = findViewById(R.id.edit);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.github.com/users/").build();
                mepi mepi1 = retrofit.create(mepi.class);
                Call<User> call=mepi1.detailsLoader(text);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();

                        textView.setText(user.name);
                        TextView textView1 = findViewById(R.id.bio);
                        progressBar.setVisibility(View.GONE);
                        textView1.setText(user.public_repos+"");
                        ImageView imageView = findViewById(R.id.img);
                        Picasso.get().load(user.avatar_url).into(imageView);

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Log.d("msg",t.getMessage());
                    }
                });

            }
        });

    }
}
