import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Written by TheSoberRussian on 2/17/15.
 */
public class Matcher {

    public static void main(String[] args) throws IOException{

        Scanner file = new Scanner(new File("data.dat"));
        ArrayList<Person> people = new ArrayList<Person>();
        while (file.hasNextLine()){
            String[] split = file.nextLine().split(",");
            Person temp = new Person(split[0],Integer.parseInt(split[1]),
                    split[split.length-1].charAt(1)+"",split[split.length-1].charAt(0)+"");
            temp.setAnswersImportance(split);
            people.add(temp);
        }



        for(Person x: people){
            x.matchMake(people);
        }

        for (Person x: people){
            x.results();
        }

    }

}
