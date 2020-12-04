package sample;

import java.util.*;

public class ComparWeight implements Comparator<Key>
{
    private List<Note> register;

    public ComparWeight(List<Note> register)
    {
        this.register = register;
    }

    public int compare (Key k1, Key k2)
    {
        if (k1.getWeight()<k2.getWeight()) return -1;
        if (k1.getWeight()>k2.getWeight()) return 1;
        return 0;
    }
}
