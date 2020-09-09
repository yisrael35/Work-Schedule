package yisraelbar.com.workschedule;

import java.util.ArrayList;
import java.util.List;

class ScheduleByDate {
//  private   String [][] schedule=new String[7][5];
    private int weekYear;
    private List<String> schedule=new ArrayList<>();

    public ScheduleByDate (int w1,List<String> s1){
//    this.schedule=new Schedule(s1);
        this.weekYear=w1;
        this.schedule=s1;
    }
    public ScheduleByDate(){

    }
    public ScheduleByDate (int w1,String [][] s1){
        this.weekYear=w1;
        for (int i=0; i<7;i++) {
            for (int j = 0; j < 5; j++) {
                if (s1[i][j]==(null)){
                    this.schedule.add("");
                }else this.schedule.add(s1[i][j]);

            }
        }
    }

    public void  convertTheListIntoArray(List<String> s1){
//        this.schedule=new boolean[7][5];
//        for (int i=0, k=0; i<7;i++) {
//            for (int j = 0; j < 5; j++) {
//                schedule[i][j] = s1.get(k);
//                k++;
//            }
//        }
    }


    public List<String> turnAStringArrayIntoList(String [][] s){
        List<String> strings=new ArrayList<>();
        for (int i=0; i<7;i++) {
            for (int j = 0; j < 5; j++) {
                if (s[i][j]==(null)){
                    strings.add("");
                }else strings.add(s[i][j]);

            }
        }
        return strings;
    }

    public int getWeekYear() {
        return weekYear;
    }

    public void setWeekYear(int weekYear) {
        this.weekYear = weekYear;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }
}
