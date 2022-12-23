package info.alexanderramirez.filmcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFilm extends AppCompatActivity {
    private EditText filmTitleTextInput, filmWatchCountTextInput;
    private Button updateFilmBtn, deleteFilmBtn;
    private DBHandler dbHandler;
    String filmTitle, filmWatchCount, filmId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_film);

        filmTitleTextInput = findViewById(R.id.FilmTitleTextInput);
        filmWatchCountTextInput = findViewById(R.id.FilmWatchCountTextInput);
        updateFilmBtn = findViewById(R.id.UpdateFilmBtn);
        deleteFilmBtn = findViewById(R.id.DeleteFilmBtn);

        dbHandler = new DBHandler(UpdateFilm.this);

        filmTitle = getIntent().getStringExtra("film_title");
        filmWatchCount = getIntent().getStringExtra("film_watch_count");
        filmId = getIntent().getStringExtra("film_id");

        filmTitleTextInput.setText(filmTitle);
        filmWatchCountTextInput.setText(filmWatchCount);

        updateFilmBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String updatedFilmTitle = filmTitleTextInput.getText().toString();
                String updatedFilmWatchCountStr = filmWatchCountTextInput.getText().toString();
                int updatedFilmCount;

                try {
                    updatedFilmCount = Integer.parseInt(updatedFilmWatchCountStr);

                } catch (NumberFormatException e) {
                    Toast.makeText(UpdateFilm.this, "Please enter a valid number...", Toast.LENGTH_SHORT).show();
                    return;
                }

                //initialize FilmModel instance
                FilmModel fm = new FilmModel(updatedFilmTitle,updatedFilmCount,Integer.parseInt(filmId));
                if (fm.validateData()){
                    dbHandler.updateFilm(fm.getId(),fm.getFilmTitle(),fm.getFilmWatchCount());
                    Toast.makeText(UpdateFilm.this, "Film has been updated!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateFilm.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(UpdateFilm.this, "Please input valid data!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteFilmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.deleteFilm(Integer.parseInt(filmId));
                Toast.makeText(UpdateFilm.this, "Film has been deleted!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateFilm.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}