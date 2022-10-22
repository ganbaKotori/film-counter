package info.alexanderramirez.filmcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFilm extends AppCompatActivity {
    private EditText filmTitleTextInput, filmWatchCountTextInput;
    private Button addFilmBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);

        filmTitleTextInput = findViewById(R.id.FilmTitleTextInput);
        filmWatchCountTextInput = findViewById(R.id.FilmWatchCountTextInput);
        addFilmBtn = findViewById(R.id.AddFilmBtn);

        dbHandler = new DBHandler(AddFilm.this);

        addFilmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddFilm.this, "Film has been added!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}