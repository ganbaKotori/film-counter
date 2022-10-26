package info.alexanderramirez.filmcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FilmRVAdapter extends RecyclerView.Adapter<FilmRVAdapter.ViewHolder> {

    private ArrayList<FilmModel> filmModelArraylist;
    private Context context;

    public FilmRVAdapter(ArrayList<FilmModel> filmModelArrayList, Context context) {
        this.filmModelArraylist = filmModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FilmModel model = filmModelArraylist.get(position);
        holder.filmTitleTextView.setText(model.getFilmTitle());
        System.out.println(model.getFilmWatchCount());
        holder.filmWatchCountTextView.setText("Watch Count: " + Integer.toString(model.getFilmWatchCount()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateFilm.class);
                i.putExtra("film_title", model.getFilmTitle());
                i.putExtra("film_watch_count", Integer.toString(model.getFilmWatchCount()));
                i.putExtra("film_id", Integer.toString(model.getId()));

                // starting our activity.
                context.startActivity(i);
            }
        });
        holder.incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //below line is to get data from all edit text fields
//                String courseName  = courseNameEdt.getText().toString();
//                String courseTracks = courseTracksEdt.getText().toString();
//                String courseDuration = courseDurationEdt.getText().toString();
//                String courseDescription = courseDescriptionEdt.getText().toString();
//
//                //validating if the text fields are empty or not
//                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty()){
//                    Toast.makeText(MainActivity.this, "Please enter all the data...", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);
                DBHandler dbHandler = new DBHandler(context);
                model.setFilmWatchCount(model.getFilmWatchCount() + 1);
                dbHandler.updateFilm(model.getId(),model.getFilmTitle(),model.getFilmWatchCount());
                holder.filmWatchCountTextView.setText("Watch Count: " + Integer.toString(model.getFilmWatchCount()));
//                courseNameEdt.setText("");
//                courseDurationEdt.setText("");
//                courseTracksEdt.setText("");
//                courseDescriptionEdt.setText("");
            }
        });

        holder.decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //below line is to get data from all edit text fields
//                String courseName  = courseNameEdt.getText().toString();
//                String courseTracks = courseTracksEdt.getText().toString();
//                String courseDuration = courseDurationEdt.getText().toString();
//                String courseDescription = courseDescriptionEdt.getText().toString();
//
//                //validating if the text fields are empty or not
//                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty()){
//                    Toast.makeText(MainActivity.this, "Please enter all the data...", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);
                DBHandler dbHandler = new DBHandler(context);
                if(model.getFilmWatchCount() > 0){
                    model.setFilmWatchCount(model.getFilmWatchCount() - 1);
                    dbHandler.updateFilm(model.getId(),model.getFilmTitle(),model.getFilmWatchCount());
                    holder.filmWatchCountTextView.setText("Watch Count: " + Integer.toString(model.getFilmWatchCount()));
                }

//                courseNameEdt.setText("");
//                courseDurationEdt.setText("");
//                courseTracksEdt.setText("");
//                courseDescriptionEdt.setText("");
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmModelArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView filmTitleTextView, filmWatchCountTextView;
        private Button incrementBtn, decrementBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            filmTitleTextView = itemView.findViewById(R.id.FilmTitleTextView);
            filmWatchCountTextView = itemView.findViewById(R.id.FilmWatchCountTextView);
            incrementBtn = itemView.findViewById(R.id.IncrementCountBtn);
            decrementBtn = itemView.findViewById(R.id.DecrementCountBtn);
        }
    }
}