package com.example.save_test

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.util.ArrayList
import android.content.ContextWrapper


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

        fun toJson2(user: User, questlist:ArrayList<Quest>,context:ContextWrapper): String { //quest = guestjson this fun not used if you need to use this fun you might test fun
            try {
                val host_json = JSONObject()
                host_json.put("name", user.name)
                host_json.put("gender", user.gender)
                host_json.put("attention", user.attention)

                val quests_ary = JSONArray()
                for(quests in questlist){
                    var quest_json = JSONObject()
                    quest_json.put("name",quests.name )
                    quest_json.put("explain",quests.explain)
                    quests_ary.put(quest_json)
                }
                host_json.put("quests", quests_ary)
                return host_json.toString()

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return ""
        }

        fun arylist2jsonary( questlist : ArrayList<Quest>,context:ContextWrapper):String {

            try {

                val jsonAry=JSONArray()
                val file = context.filesDir

                for (values in questlist) {
                    var obj = JSONObject()
                    obj.put("name", values.name)
                    obj.put("explain", values.explain)
                    jsonAry.put(obj)
                }

                return jsonAry.toString()

            } catch (e: Exception) {
                print(e.stackTrace)
            }

            return ""
        }
        fun fileDir(context : ContextWrapper):String{
            return context.filesDir.toString()
        }
    }
}
