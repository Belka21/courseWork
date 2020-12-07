package sample;

import java.util.Objects;

public class Note implements Comparable <Note>{
    private float cost; //стоимость перевозки
    private int weight; // граничный вес
    private ValCiph valuable; // шифр ценности
    private int deliveTime; // время доставки в днях

    public Note()
    {
        cost = 0;
        weight = 0;
        valuable = ValCiph.NON;
        deliveTime = 0;
    }

    public Note(int weight, ValCiph valuable, int deliveTime, float cost)
    {
        this.weight= weight;
        this.valuable= valuable;
        this.deliveTime= deliveTime;
        this.cost = cost;
    }

    //set
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setValuable(ValCiph valuable) {
        this.valuable = valuable;
    }

    public void setDeliveTime(int deliveTime) {
        this.deliveTime = deliveTime;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    //get
    public float getCost() {
        return cost;
    }

    public int getWeight() {
        return weight;
    }

    public ValCiph getValuable() {
        return valuable;
    }

    public int getDeliveTime() {
        return deliveTime;
    }

    @Override
    public int compareTo(Note o)
    {
        if (weight < o.weight) return -1;
        if (weight > o.weight) return 1;

        if (valuable.compareTo(o.valuable)<0) return -1;
        if (valuable.compareTo(o.valuable)>1) return 1;

        if (deliveTime<o.deliveTime) return -1;
        if (deliveTime>o.deliveTime) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note nt = (Note) o;
        return weight == nt.weight &&
                deliveTime == nt.deliveTime &&
                valuable == nt.valuable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, valuable, deliveTime);
    }
}
