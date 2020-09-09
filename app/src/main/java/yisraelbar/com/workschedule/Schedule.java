package yisraelbar.com.workschedule;

import android.util.Log;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Schedule {

    private boolean [][] schedule;
    public Schedule (){
        this.schedule=new boolean[7][5];
        for (int i=0; i<7;i++) {
            for (int j = 0; j < 5; j++) {
                schedule[i][j] = false;
            }
        }
    }

    public List<Boolean> createListOfOption(){
        List<Boolean> booleans=new ArrayList<>();
        for (int i=0; i<7;i++) {
            for (int j = 0; j < 5; j++) {
                booleans.add(schedule[i][j]) ;
            }
        }
        return booleans;
    }
    public void  convertTheListIntoArray(List<Boolean> b1){
       this.schedule=new boolean[7][5];
        for (int i=0, k=0; i<7;i++) {
            for (int j = 0; j < 5; j++) {
                schedule[i][j] = b1.get(k);
                k++;
            }
        }
    }
    public boolean [][]  convertTheListIntoArray1(List<Boolean> b1){
        this.schedule=new boolean[7][5];
        for (int i=0, k=0; i<7;i++) {
            for (int j = 0; j < 5; j++) {
                schedule[i][j] = b1.get(k);
                k++;
            }
        }
        return schedule;
    }


    public boolean[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(boolean[][] schedule) {
        this.schedule = schedule;
    }
    public void setSchedule(int i, int j,boolean val) {
        this.schedule[i][j] =val;
    }

    @Override
    public String toString() {
       String toReturn="";
        for (int i=0; i<7;i++) {
            for (int j = 0; j < 5; j++) {
               if ( schedule[i][j] ==true) {
                switch (i){
                    case 0:
//                        toReturn+="Sunday ";
                        toReturn+="ראשון ";
                        toReturn = toStringHelper(toReturn, j);
                        break;
                    case 1:
//                        toReturn+="Monday ";
                        toReturn+="שני ";
                        toReturn = toStringHelper(toReturn, j);
                        break;
                    case 2:
//                        toReturn+="Tuesday  ";
                        toReturn+="שלישי  ";
                        toReturn = toStringHelper(toReturn, j);
                        break;
                    case 3:
//                        toReturn+="Wednesday ";
                        toReturn+="רביעי ";
                        toReturn = toStringHelper(toReturn, j);
                        break;
                    case 4:
//                        toReturn+="Thursday ";
                        toReturn+="חמישי ";
                        toReturn = toStringHelper(toReturn, j);
                        break;
                    case 5:
//                        toReturn+="Friday ";
                        toReturn+="שישי ";
                        toReturn = toStringHelper(toReturn, j);
                        break;
                    case 6:
//                        toReturn+="Saturday ";
                        toReturn+="שבת ";
                        toReturn = toStringHelper(toReturn, j);
                        break;

                    default:
                }
               }
            }
        }

       return toReturn;
    }

    private String toStringHelper(String toReturn, int j) {
        switch (j){
            case 0:
//                                toReturn+="Morning \n";
                toReturn+="בוקר \n";
                break;
            case 1:
//                                toReturn+="Noon \n";
                toReturn+="צהריים \n";
                break;
            case 2:
//                                toReturn+="Night \n";
                toReturn+="לילה \n";
                break;
            case 3:
//                                toReturn+="Morning 7-19 \n";
                toReturn+="שלדי בוקר \n";
                break;
            case 4:
//                                toReturn+="Night 19-7 \n";
                toReturn+="שלדי לילה \n";
                break;
            default:
        }
        return toReturn;
    }
}
