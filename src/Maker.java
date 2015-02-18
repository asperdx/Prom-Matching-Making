import java.util.ArrayList;

/**
 * Written by TheSoberRussian on 2/17/15.
 */
public class Maker implements Comparable {

    private Person a;
    private Person b;
    private double compatibility;

    public Maker(Person a, Person b){
        this.a = a;
        this.b = b;
    }

    public void Match(){

        ArrayList<String> personAnswersA = a.getAnswers();
        ArrayList<String> personAnswersB = b.getAnswers();
        ArrayList<Integer> personImportanceA = a.getImportance();
        ArrayList<Integer> personImportanceB = b.getImportance();
        ArrayList<String> personAQual = a.getQualities();
        ArrayList<String> personBQual = b.getQualities();


        double totalA = 0;
        double totalB = 0;
        double maxA = 0;
        double maxB = 0;
        for (int i = 0; i < personAnswersA.size(); i++) {

            maxA+=personImportanceA.get(i);
            maxB+=personImportanceB.get(i);

            if (personAQual.get(i).equals(personAnswersB.get(i))){
                totalA+=personImportanceB.get(i);
            }

            if (personBQual.get(i).equals(personAnswersA.get(i))){
                totalB+=personImportanceA.get(i);
            }


        }

        double scoreA = totalA/maxB;
        double scoreB = totalB/maxA;

        compatibility = Math.sqrt(scoreA * scoreB);



    }

    @Override
    public int compareTo(Object o) {
        Maker temp = (Maker) o;

        return (int)(-1*((compatibility*100) - (temp.compatibility*100)));
    }

    @Override
    public String toString() {
        String s = String.format("%s and %s have a compatibility score of %.2f",a.getName(),b.getName(),compatibility* 100);
        return s+"%";
    }
}
