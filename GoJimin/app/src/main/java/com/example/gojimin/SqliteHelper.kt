package com.example.gojimin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.Editable
import android.util.Log

data class Student(val DNO:Int ,val Name:String ,val Contact:String , val Email:String, val PWD:String="0")
class SqliteHelper(context:Context,name:String,version:Int):SQLiteOpenHelper(context,name,null,version) {

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql ="create table if not exists student(" +
                "DNO integer primary key," +
                "Name String not null," +
                "Contact String not null," +
                "Email String not null," +
                "PWD String not null); "
        with(p0) {
            this!!.execSQL(sql)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun selectstudent(): MutableList<Student>{
        val list = mutableListOf<Student>()

        var select = "select * from student"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select,null)

        while(cursor.moveToNext()){
            val DNO= cursor.getInt(0)
            val Name= cursor.getString(1)
            val Contact= cursor.getString(2)
            val Email= cursor.getString(3)
            val PWD = cursor.getString(4)
            list.add(Student(DNO, Name, Contact, Email,PWD))
        }
        cursor.close()
        rd.close()
        return list
    }

    fun login(Email: String?, PWD: String?): Boolean{
        var select = "select Email,PWD from student where " +
                "Email='$Email' AND PWD ='$PWD';"
        val rd = readableDatabase
        val cursor =rd.rawQuery(select,null);

        if(cursor.count!=1){
            Log.d("value_false","${cursor.count}")
            return false
        }
        Log.d("value_true","${cursor.count}")
        return true
    }

    private fun Inittable(){// 테이블 데이터
        var wd =writableDatabase
        val query =  "insert into student values(1234,'홍길동','010-1234-3287','hong@gmail.com','1234!')"
        val query2 = "insert into student values(5678,'박문수','011-3333-2222','park@gmail.com','5678!')"
        val query3 = "insert into student values(9012,'이순신','010-9999-2222','lee@gmail.com','9012!')"
        val query4 = "insert into student values(20174222,'고지민','010-3259-1812','wlals425315@gmail.com','20174222!')"
        wd.execSQL(query)
        wd.execSQL(query2)
        wd.execSQL(query3)
        wd.execSQL(query4)
    }

}