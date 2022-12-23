package info.alexanderramirez.filmcounter;

import static org.junit.Assert.*;
import org.junit.Test;

public class FilmModelTest{

    @Test
    public void emptyFilmTitleShouldFailValidator(){
        FilmModel filmModel = new FilmModel("",0,0);
        assertEquals(false,filmModel.validateData());
    }

    @Test
    public void negativeWatchCountShouldFailValidator(){
        FilmModel filmModel = new FilmModel("The Social Network", -2,0);
        assertEquals(false,filmModel.validateData());
    }

}