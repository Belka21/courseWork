package sample;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DBIO {

    //вывод из файла
    public static void cout(Journal j, String fileName) throws Exception
    {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String s;
        String subs;
        while ((s=br.readLine())!=null)
        {
            Note nt = new Note();
            subs = s.substring(0, s.indexOf(" "));
            nt.setWeight(Integer.parseInt(subs));
            s= s.substring(s.indexOf(" ")+1);

            subs = s.substring(0, s.indexOf(" "));
            nt.setValuable(ValCiph.valueOf(subs));
            s= s.substring(s.indexOf(" ")+1);

            subs = s.substring(0, s.indexOf(" "));
            nt.setDeliveTime(Integer.parseInt(subs));
            s= s.substring(s.indexOf(" ")+1);

            nt.setCost(Float.parseFloat(subs));

            j.addNote(nt);
        }
        fr.close();
    }

    //запись в файл
    public static void cin(Journal j, String fileName) throws  Exception
    {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Note iter: j.getRegister())
        {
            bw.write(String.valueOf(iter.getWeight()));
            bw.append(' ');

            bw.write(String.valueOf(iter.getValuable()));
            bw.append(' ');

            bw.write(String.valueOf(iter.getDeliveTime()));
            bw.append(' ');

            bw.write(String.valueOf(iter.getCost()));
            bw.write("\r\n");
            bw.flush();
        }

        fw.close();
    }
}
