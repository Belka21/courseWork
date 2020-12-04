package sample;

import java.util.Objects;

public class Key implements Comparable <Key>
{
    private int weight; // граничный вес
    private ValCiph valuable; // шифр ценности
    private int deliveTime; // время доставки в днях


    public Key()
    {
        weight = 0;
        valuable = ValCiph.NON;
        deliveTime = 0;
    }

    public  Key(int weight, ValCiph valuable, int deliveTime)
    {
        this.weight= weight;
        this.valuable= valuable;
        this.deliveTime= deliveTime;
    }

    //get
    public int getWeight() {
        return weight;
    }

    public ValCiph getValuable() {
        return valuable;
    }

    public int getDeliveTime() {
        return deliveTime;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return weight == key.weight &&
                deliveTime == key.deliveTime &&
                valuable == key.valuable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, valuable, deliveTime);
    }

    public int compareTo(Key k)
    {
        if (weight < k.weight) return -1;
        if (weight > k.weight) return 1;

        if (valuable.compareTo(k.valuable)<0) return -1;
        if (valuable.compareTo(k.valuable)>1) return 1;

        if (deliveTime<k.deliveTime) return -1;
        if (deliveTime>k.deliveTime) return 1;
        return 0;
    }
}
