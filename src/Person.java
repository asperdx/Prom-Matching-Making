import java.util.ArrayList;
import java.util.Collections;

/**
 * Written by TheSoberRussian on 2/17/15.
 */
public class Person {

    private String name;
    private String gender;
    private String genderPref;
    private ArrayList<String> qualities;
    private ArrayList<String> answers;
    private ArrayList<Integer> importance;
    private ArrayList<Maker> compatiblePeople;
    public int id;


    public Person(String name,int id, String gender, String genderPref){
        this.name = name;
        this.id = id;
        this.gender = gender.equals("M") ? "MALE":"FEMALE";
        //this.genderPref = genderPref.equals("S") ? "STRAIGHT": genderPref.equals("G") ? "GAY":"BI";
    }

    public ArrayList<String> getQualities() {
        return qualities;
    }

    public void setAnswersImportance(String[] array){
        answers = new ArrayList<String>();
        importance = new ArrayList<Integer>();
        qualities = new ArrayList<String>();
        for (int i = 2; i < array.length-1; i++) {
            if (i % 3 == 1){
                switch (array[i].charAt(0)){
                    case '1': importance.add(0); break;
                    case '2': importance.add(1); break;
                    case '3': importance.add(10); break;
                    case '4': importance.add(50); break;
                    case '5': importance.add(250); break;
                    default: break;
                }
            }
            else if (i % 3 == 0)
                answers.add(array[i]);
            else
                qualities.add(array[i]);

        }
    }

    public String getGender() {
        return gender;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public ArrayList<Integer> getImportance() {
        return importance;
    }

    public String getName() {
        return name;
    }


    public void matchMake(ArrayList<Person> list){
        compatiblePeople = new ArrayList<Maker>();
        for (Person x: list){
            if (id!=x.id){
                if (!gender.equals(x.getGender())) {
                    Maker temp = new Maker(this, x);
                    temp.Match();
                    compatiblePeople.add(temp);
                }
            }
        }
        Collections.sort(compatiblePeople);
    }

    public void results(){
        for (int i = 0; i < 10 && i < compatiblePeople.size(); i++) {
            System.out.println(compatiblePeople.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return "Name: " + name +" Gender: " + gender;// + " Orientation: " + genderPref;
    }
}
