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
                //below line is to get data from all edit text fields
                String filmTitle  = filmTitleTextInput.getText().toString();
                String filmWatchCount = filmWatchCountTextInput.getText().toString();

                //validating if the text fields are empty or not
                if (filmTitle.isEmpty() && filmWatchCount.isEmpty()){
                    Toast.makeText(AddFilm.this, "Please enter all the data...", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewFilm(filmTitle, Integer.parseInt(filmWatchCount));
                filmTitleTextInput.setText("");
                filmWatchCountTextInput.setText("");
                Toast.makeText(AddFilm.this, "Film has been added!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddFilm.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}