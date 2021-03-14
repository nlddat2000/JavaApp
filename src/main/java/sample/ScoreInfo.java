package sample;

public class ScoreInfo {
    private String name;
    private String tname;
    private float score;

    public ScoreInfo() {

    }

    public ScoreInfo(String name, String tname, float score) {
        this.name = name;
        this.tname = tname;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }


    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}