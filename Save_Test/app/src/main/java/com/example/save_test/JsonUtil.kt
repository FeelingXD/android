package com.example.save_test

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.util.ArrayList

public final class JsonUtil {
    companion object {
        fun toJson(user: User): String {
            try {
                val host_json = JSONObject()
                host_json.put("name", user.name)
                host_json.put("gender", user.gender)
                host_json.put("attention", user.attention)
                return host_json.toString()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return ""
        }

        fun toJson2(user: User, quest: ArrayList<Any>): String { //quest = guestjson
            try {
                val host_json = JSONObject()
                host_json.put("name", user.name)
                host_json.put("gender", user.gender)
                host_json.put("attention", user.attention)

                val quests = org.json.simple.JSONArray()
                for(value in quest){
                    quests.add(value)
                }
                host_json.put("quest",quests)
                return host_json.toString()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return ""
        }

        fun arylist2jsonary( questlist : ArrayList<String>): String?{
            try {
                val jsonAry: JSONArray
                for (values in questlist) {
                    var obj = JSONObject()
                    obj.put("fir", questlist.get(0))
                    obj.put("sec",questlist.get(1))
                }
            } catch (e: Exception) {
                print(e.stackTrace)
            }

            return ""
        }
    }
}
