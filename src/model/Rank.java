package model;

import java.util.List;

public class Rank {

    int rank;

    boolean existence;
    String name;

    List<Character> values;

    public Rank(int rank, boolean existence, String name, List<Character> values) {
        this.rank = rank;
        this.existence = existence;
        this.name = name;
        this.values = values;
    }

    public Rank(boolean existence) {
        this.existence = existence;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isExistence() {
        return existence;
    }

    public void setExistence(boolean existence) {
        this.existence = existence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Character> getValues() {
        return values;
    }

    public void setValues(List<Character> values) {
        this.values = values;
    }


}
