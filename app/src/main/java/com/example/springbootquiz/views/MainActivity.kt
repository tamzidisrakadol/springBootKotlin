package com.example.springbootquiz.views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.springbootquiz.R
import com.example.springbootquiz.databinding.ActivityMainBinding
import com.example.springbootquiz.modal.Question
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    private val questionList = mutableListOf<Question>()
    private lateinit var question: Question;
    private var index=0


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  fetchData()


        binding.nxtbtn.setOnClickListener {
            if (index < questionList.size - 1) {
                // Move to the next question
                index++
                setupQuestionView()
            } else if (index == questionList.size - 1) {
                // Display a toast when reaching the end of the question list
                Toast.makeText(this, "End of the questions", Toast.LENGTH_SHORT).show()
            }
        }

        binding.option1CV.setOnClickListener {
            checkAnswer(questionList[index].option1)
        }

        binding.option2CV.setOnClickListener {
            checkAnswer(questionList[index].option2)
        }

        binding.option3CV.setOnClickListener {
            checkAnswer(questionList[index].option3)
        }


        binding.option4CV.setOnClickListener {
            checkAnswer(questionList[index].option4)
        }
        

    }

    private fun fetchData() {
        val url = "http://192.168.31.131:8080/getQuestion"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getInt("id")
                val title = jsonObject.getString("title")
                val category = jsonObject.getString("category")
                val option1 = jsonObject.getString("option1")
                val option2 = jsonObject.getString("option2")
                val option3 = jsonObject.getString("option3")
                val option4 = jsonObject.getString("option4")
                val rightAnswer = jsonObject.getString("rightanswer")

                val question = Question(
                    id = id,
                    category = category,
                    option1 = option1,
                    option2 = option2,
                    option3 = option3,                      
                    option4 = option4,
                    rightAnswer = rightAnswer,
                    title = title
                )
                questionList.add(question)
            }
            setupQuestionView()
        }, {
            Log.d("error", "$it")
        })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }


    private fun setupQuestionView() {
        if (questionList.isEmpty()) {
            return // Return if the questionList is empty
        }
        val currentQuestion = questionList[index]
        binding.questionTV.text = currentQuestion.title
        binding.option1TV.text = currentQuestion.option1
        binding.option2TV.text = currentQuestion.option2
        binding.option3TV.text = currentQuestion.option3
        binding.option4TV.text = currentQuestion.option4
        

    }


    private fun  checkAnswer(userAnswer:String):Boolean{

        val currentQuestionAns = questionList[index].rightAnswer

        if (currentQuestionAns==userAnswer){
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
            index++
            setupQuestionView()
            return true
        }else{
            Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show()
            return false
        }

    }


}