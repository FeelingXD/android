package com.example.save_test

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.*


class MainActivity : AppCompatActivity() {
    public final fun Context.showToast(text:CharSequence, duration: Int= Toast.LENGTH_SHORT){
        Toast.makeText(this,text,duration).show()
    }
    val file ="data.txt"
    val testobj = arrayListOf<Any>("name","test")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun save(v:View){   //save
        val fileOutputStream: FileOutputStream

        try {
            fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write("데이터저장".toByteArray()) // byte 저장
            fileOutputStream.close()
        }
        catch (e: FileNotFoundException)
        {
            e.printStackTrace()
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        showToast("saved")
    }


    fun load(v:View){   //load
        if(file.toString()!=null&&file.trim()!=""){

            var fileinputstream:FileInputStream?=null
            fileinputstream = openFileInput(file)
            var inputStreamReader: InputStreamReader = InputStreamReader(fileinputstream)
            var bufferReader:BufferedReader = BufferedReader(inputStreamReader)

            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while({text = bufferReader.readLine(); text}()!=null){
                stringBuilder.append(text)
            }
            showToast(stringBuilder.toString())
        }
        else
            showToast("name of file cant be used")

    }
    fun getdir(v:View): Unit { //getdir
        showToast(filesDir.toString())

    }




    fun jsonsave(v: View){
        Log.d("jsonsave","used")
        val filename = "test.json"

        val user = User("jimin", "male", "game")
        val output : FileOutputStream

        try {
            output = openFileOutput(filename ,Context.MODE_PRIVATE)
            output.write(JsonUtil.toJson(user).toByteArray())
            showToast("saved")
            output.close()
        }catch (e:Exception){
            e.printStackTrace()
        }


    }

    fun jsonread(v: View) { // 파일읽기
        Log.d("jsonread","used")
        //file name =data.json
        val filename =getFilesDir().toString()+"/test.json"
        val file =File(filename)
        try {
            val reader = BufferedReader(FileReader(file))
            var result: String? = ""
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                result += line
            }
            try {
                Log.d("jsonread.try","used")
                val info = JSONObject(result)
                showToast(info.toString())
            }catch (e:java.lang.Exception){
                Log.d("에러",e.printStackTrace().toString())
            }
            reader.close()
        } catch (e1: FileNotFoundException) {
            showToast(filename)
            Log.d("파일못찾음", e1.printStackTrace().toString())
        } catch (e2: IOException) {
            Log.d("읽기오류", e2.printStackTrace().toString())
        }
    }
    fun jsonsave2(v: View){
        val filename = "test2.json"
        val user = User("jimin", "male", "game")
        val output : FileOutputStream

        try {
            output = openFileOutput(filename ,Context.MODE_PRIVATE)
            output.write(JsonUtil.toJson2(user,testobj ).toByteArray())
            showToast("saved")
            output.close()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun jsonread2(v: View){

        val filename =getFilesDir().toString()+"/test2.json"
        val file =File(filename)
        try {
            val reader = BufferedReader(FileReader(file))
            var result: String? = ""
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                result += line
            }
            try {
                Log.d("jsonread.try","used")
                val info = JSONObject(result)
                showToast(info.toString())
            }catch (e:java.lang.Exception){
                Log.d("에러",e.printStackTrace().toString())
            }
            reader.close()
        } catch (e1: FileNotFoundException) {
            showToast(filename)
            Log.d("파일못찾음", e1.printStackTrace().toString())
        } catch (e2: IOException) {
            Log.d("읽기오류", e2.printStackTrace().toString())
        }
    }




}