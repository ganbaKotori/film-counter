package info.alexanderramirez.filmcounter;

public class FilmModel {

    private String filmTitle;
    private int filmWatchCount;
    private int id;

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public int getFilmWatchCount() {
        return filmWatchCount;
    }

    public void setFilmWatchCount(int filmWatchCount) {
        this.filmWatchCount = filmWatchCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FilmModel(String filmTitle, int filmWatchCount, int id) {
        this.filmTitle = filmTitle;
        this.filmWatchCount = filmWatchCount;
        this.id = id;
    }

    public FilmModel(String filmTitle, int filmWatchCount){
        this.filmTitle = filmTitle;
        this.filmWatchCount = filmWatchCount;
    }

    public Boolean validateData(){
        if (this.getFilmTitle().length() == 0){
            return false;
        }
        if (this.getFilmWatchCount() < 0){
            return false;
        }

        return true;
    }
}
