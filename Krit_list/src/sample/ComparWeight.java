package sample;

import java.util.*;

public class ComparWeight implements Comparator<Note>
{
    private List<Note> register;

    public ComparWeight(List<Note> register)
    {
        this.register = register;
    }

    public int compare (Note k1, Note k2)
    {
        if (k1.getWeight()<k2.getWeight()) return -1;
        if (k1.getWeight()>k2.getWeight()) return 1;
        return 0;
    }
}
