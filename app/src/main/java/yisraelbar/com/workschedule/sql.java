package yisraelbar.com.workschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class sql extends SQLiteOpenHelper {
    private Context cn1;

    private int date;
    public sql(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        cn1 = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String query =("create table if not exists t1(date int,s00 text,s01 text,s02 text,s03 text,s04 text,\n" +
                    "s10 text,s11 text,s12 text,s13 text,s14 text,\n" +
                    "s20 text,s21 text,s22 text,s23 text,s24 text,\n" +
                    "s30 text,s31 text,s32 text,s33 text,s34 text,\n" +
                    "s40 text,s41 text,s42 text,s43 text,s44 text,\n" +
                    "s50 text,s51 text,s52 text,s53 text,s54 text,\n" +
                    "s60 text,s61 text,s62 text,s63 text,s64 text)");
            db.execSQL(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public List<ScheduleByDate> showSchedules(){
        List<ScheduleByDate> scheduleList=new ArrayList<>();
        String [][] schedule= new String[7][5];
        int index=0;
        try {
            String query = "select * from t1";
            SQLiteDatabase db=getWritableDatabase();
            Cursor c1;
            c1=db.rawQuery(query,null);
            if (c1.moveToFirst()){
                do {
                    date=c1.getInt(index);
                    for (int i=0; i<7;i++) {
                        for (int j = 0; j < 5; j++) {
                            index++;
                            schedule[i][j] =c1.getString(index);
                        }
                    }
                    ScheduleByDate sbd1=new ScheduleByDate();
                    sbd1=new ScheduleByDate(date,sbd1.turnAStringArrayIntoList(schedule));
                    scheduleList.add(sbd1);
                }while (c1.moveToNext());
            }
        } catch (Exception e) {
//                Toast.makeText(cn1, "error num1"+ e, Toast.LENGTH_SHORT).show();
        }   return scheduleList;
    }

    public void addSchedule (ScheduleByDate sbd1 ){
        int index=0;
        try {
            SQLiteDatabase sd=getWritableDatabase();
            ContentValues cv1=new ContentValues();
            cv1.put("date",sbd1.getWeekYear());
            List<String> sl1=sbd1.getSchedule();
//            cv1.put("schedule",sbd1.getSchedule());
            for (int i=0; i<7;i++) {
                for (int j = 0; j < 5; j++) {
                    cv1.put("s"+i+j,sl1.get(index));
//                    Log.d("yisrael", "sql "+sl1.get(index));
                    index++;
                }
            }
            sd.insert("t1",null,cv1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    public void deleteSchedule(int date){
//        String res="error";
        try {
            SQLiteDatabase db= getWritableDatabase();
            String query=("delete from t1 where date =  '"+date+"'" );
            db.execSQL(query);
//            res="change was made ";
//            Toast.makeText(cn1, res, Toast.LENGTH_SHORT).show();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }



    public String [][] search (int weekYear){
    String [][] schedule= new String[7][5];
    try {
        String query=("select * from t1 where date =  '"+weekYear+"'" );
        SQLiteDatabase db=getWritableDatabase();
        Cursor c1;
        int index=0;
        c1=db.rawQuery(query,null);
        if (c1.moveToFirst()){
            do {
                date=c1.getInt(index);
                for (int i=0; i<7;i++) {
                    for (int j = 0; j < 5; j++) {
                        index++;
                        schedule[i][j] =c1.getString(index);
                    }
                }
            }while (c1.moveToNext());
        }
    } catch (Exception e1) {
        e1.printStackTrace();
    }
    return schedule;
}

}
