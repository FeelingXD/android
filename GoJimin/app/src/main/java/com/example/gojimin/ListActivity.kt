package com.example.gojimin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.gojimin.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    lateinit var db:SqliteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("기말고사 프로젝트")

        val listview = findViewById<ListView>(R.id.Listview) as ListView
        db = SqliteHelper(this,"student.db",1)

        val studentList = db.selectstudent()

        val adapter = ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1 , studentList)
        listview.adapter = ListViewAdapter(this, studentList)
    }

    private class ListViewAdapter(context: Context,studentlist:MutableList<Student>): BaseAdapter(){
        private val mContext = context
        private val student = studentlist
        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }
        override fun getItem(p0: Int): Any {
            val selectItem = student.get(p0)
            return selectItem
        }

        override fun getCount(): Int {
            return student.size
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val  layoutInflater = LayoutInflater.from(mContext)
            val row = layoutInflater.inflate(R.layout.row_list,p2,false)

            val emailText = row.findViewById<TextView>(R.id.Email)
            val PWDText = row.findViewById<TextView>(R.id.PWD)

            emailText.text = student.get(p0).Email
            PWDText.text= student.get(p0).PWD

            return row
        }
    }

}