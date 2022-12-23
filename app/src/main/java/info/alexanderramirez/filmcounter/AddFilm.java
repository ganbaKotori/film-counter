package info.alexanderramirez.filmcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                //retrieve data from text fields
                String filmTitle  = filmTitleTextInput.getText().toString();
                String filmWatchCountStr = filmWatchCountTextInput.getText().toString();
                //integer to be initialized after parsing string
                int filmWatchCount;

                try {
                    filmWatchCount = Integer.parseInt(filmWatchCountStr);

                } catch (NumberFormatException e) {
                    Toast.makeText(AddFilm.this, "Please enter a valid number...", Toast.LENGTH_SHORT).show();
                    return;
                }
                FilmModel fm = new FilmModel(filmTitle,filmWatchCount);
                if(fm.validateData()){
                    dbHandler.addNewFilm(fm.getFilmTitle(), fm.getFilmWatchCount());
                    filmTitleTextInput.setText("");
                    filmWatchCountTextInput.setText("");
                    Toast.makeText(AddFilm.this, "Film has been added!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddFilm.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(AddFilm.this, "Please enter valid data...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}