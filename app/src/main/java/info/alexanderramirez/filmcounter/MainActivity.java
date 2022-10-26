package info.alexanderramirez.filmcounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button addFilmBtn;
    private ArrayList<FilmModel> filmModelArrayList;
    private FilmRVAdapter filmsRVAdapter;
    private RecyclerView filmsRV;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFilmBtn = findViewById(R.id.AddNewFilmBtn);
        addFilmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddFilm.class);
                startActivity(i);
            }
        });

        filmModelArrayList = new ArrayList<>();
        dbHandler = new DBHandler(MainActivity.this);

        filmModelArrayList = dbHandler.readFilms();
        Log.i("activity", "number of films: " + filmModelArrayList.size());

        filmsRVAdapter = new FilmRVAdapter(filmModelArrayList, MainActivity.this);
        filmsRV = findViewById(R.id.FilmsRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        filmsRV.setLayoutManager(linearLayoutManager);
        filmsRV.setAdapter(filmsRVAdapter);

    }
}