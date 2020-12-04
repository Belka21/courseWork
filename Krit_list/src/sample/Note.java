package sample;

import java.util.Objects;

public class Note implements Comparable <Note>{
    private float cost; //стоимость перевозки
    private  Key k; //ключ

    public Note()
    {
        cost = 0;
        k = new Key();
    }

    public Note(Key k, float cost)
    {
        this.k = k;
        this.cost = cost;
    }

    //set
    public void setK(Key k) {
        this.k.setDeliveTime(k.getDeliveTime());
        this.k.setValuable(k.getValuable());
        this.k.setWeight(k.getWeight());
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    //get
    public float getCost() {
        return cost;
    }

    public Key getK() {
        return k;
    }

    @Override
    public int compareTo(Note o) {
        return k.compareTo(o.getK());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(k, note.k);
    }

    @Override
    public int hashCode() {
        return Objects.hash(k);
    }
}
