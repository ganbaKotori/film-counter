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
        FilmModel modal = filmModelArraylist.get(position);
        holder.filmTitleTextView.setText(modal.getFilmTitle());
        System.out.println(modal.getFilmWatchCount());
        holder.filmWatchCountTextView.setText(Integer.toString(modal.getFilmWatchCount()));

        // below line is to add on click listener for our recycler view item.
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // on below line we are calling an intent.
//                Intent i = new Intent(context, UpdateCourseActivity.class);
//
//                // below we are passing all our values.
//                i.putExtra("name", modal.getCourseName());
//                i.putExtra("description", modal.getCourseDescription());
//
//                // starting our activity.
//                context.startActivity(i);
//            }
//        });
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

                Toast.makeText(v.getContext(), "Incrementing", Toast.LENGTH_SHORT).show();
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