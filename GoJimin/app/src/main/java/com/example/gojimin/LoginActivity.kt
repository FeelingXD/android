package com.example.gojimin

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Rect
import android.location.GnssAntennaInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.gojimin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var db :SqliteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bar= supportActionBar
        bar!!.hide()

        db= SqliteHelper(this,"student.db",1)
        binding.loginButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            if(db.login(binding.loginId.text.toString(),binding.loginPwd.text.toString())){// true
                builder.setTitle("로그인 성공!")
                builder.setMessage("다음 화면으로 이동하시겠습니까?")
                builder.setPositiveButton("예", DialogInterface.OnClickListener {
                    dialogInterface, i ->
                        val nextIntent = Intent(this, ListActivity::class.java)
                        startActivity(nextIntent)
                })

            }

            else{
                builder.setTitle("로그인 실패!")
                builder.setMessage("이메일과 패스워드를 확인하시오")
                builder.setPositiveButton("예",null)
            }

            builder.setNegativeButton("아니오",null)
            builder.show()
        }
    }
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val focusView: View? = currentFocus
        if (focusView != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = ev.x.toInt()
            val y = ev.y.toInt()
            if (!rect.contains(x, y)) {
                val imm: InputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(focusView.windowToken, 0)
                focusView.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}
