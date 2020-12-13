package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Journal {

    private String name; //имя журнала (файла)

    private ObservableList<Note> register; //сама структура


    //конструктор
    public Journal()
    {
        name="Base1";
        register = FXCollections.observableArrayList();
    }

    //конструктор с параметром
    public Journal(String name) // задание имени файла
    {
        this.name=name;
        register = FXCollections.observableArrayList();
    }

    public Journal(String name, List notes) // задание имени файла
    {
        this.name=name;
        register = FXCollections.observableArrayList();
    }

    //конструктор с параметрами
   /* public Journal(String name, Comparator comp)
    {
        this.name=name;
        register = new ArrayList<Note>(comp);
    }*/


    //get
    public String getName() {
        return name;
    }

    public ObservableList<Note> getRegister() {
        return register;
    }

    //set
    public void setName(String name) {
        this.name = name;
    }


    //добавление записи
    public boolean addNote(Note note)
    {
        if (register.contains(note))
            return false;

        register.add(note);
        return true;
    }

    //удаление продукта по ключу
    public boolean delNote(Note note)
    {
        return register.remove(note);
    }

    //удаление записи по index
    public boolean delNote(int index)
    {
        if (register.remove(index)!=null)
            return true;

        return false;
    }

    //удаление записи по стоимости в заданном диапазоне
    public boolean delByCost(float lowerRange, float heightRange)
    {
        return register.removeIf(reg -> ((reg.getCost()>=lowerRange) && (reg.getCost()<=heightRange)));
    }

    //update
    public boolean updateNote(int index, Note note)
    {
        int x = register.indexOf(note);//получаем индекс по ключу переданной записи

        //если индекс ключа и переданный индекс не совпадают - значит
        // хотят изменить на запись с совпадающим ключём
        if ((x>=0) && (x!=index))
        {
            register.set(index, register.get(index));
            return false;
        }
        register.set(index, note);
        return true;
    }

    //Выборка данных осуществляется сразу по нескольким праметрам передаваемым в
    //функцию
    //При отсутствии необходимости вобора по тому или иному параметру необходимо указать:
    // weight = -1
    // cipher = ""
    // deliveTime = -1
    // cost = -1
    public Journal selectData(int weight, String cipher, int deliveTime, float cost)
    {
        int weightSearch = weight;
        int deliveSearch = deliveTime;
        ValCiph ciphSearch = ValCiph.valueOf(cipher);
        float costSearch = cost;

        Journal subJour = new Journal (name+"subDate"); //передаём имя если захочится сохранить при этом показываем что это выборка

        for (Note iter: register)
        {
            if (weight<0) weightSearch =  iter.getWeight();
            if (deliveTime<0) deliveSearch =  iter.getDeliveTime();
            if (cipher == "") ciphSearch =  iter.getValuable();
            if (cost<0) costSearch = iter.getCost();

            if ((iter.getWeight()==weightSearch)
                    &&(iter.getValuable()==ciphSearch)
                    &&(iter.getDeliveTime()==deliveSearch)
                    &&(iter.getCost()==costSearch))
                subJour.register.add(iter);
        }
        return subJour;
    }


    public int countDelive()
    {
        if (register.size()==0) return 0;
        ArrayList <Integer> cost = new ArrayList<Integer>();
        for (Note iter:register)
        {
            if (cost.indexOf(iter.getDeliveTime())<0)
            {
                cost.add(iter.getDeliveTime());
            }
        }
        return cost.size();
    }



    //сортировка
    public Journal sort(Comparator comp)
    {
        Journal sortJour = new Journal (name+" sorted", register);
        Collections.sort(sortJour.register, comp);
        return sortJour;
    }


}
