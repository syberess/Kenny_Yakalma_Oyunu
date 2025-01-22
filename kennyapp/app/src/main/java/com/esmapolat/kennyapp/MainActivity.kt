package com.esmapolat.kennyapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.esmapolat.kennyapp.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    var score=0
    var imageviewArray=ArrayList<ImageView>()
    var runnable=Runnable{}
    var handler=Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageviewArray.add(binding.imageView)
        imageviewArray.add(binding.imageView2)
        imageviewArray.add(binding.imageView3)
        imageviewArray.add(binding.imageView4)
        imageviewArray.add(binding.imageView5)
        imageviewArray.add(binding.imageView6)
        imageviewArray.add(binding.imageView7)
        imageviewArray.add(binding.imageView8)
        imageviewArray.add(binding.imageView9)
        hideImages()


        object :CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                binding.textView.text="Time:${p0/1000}"
            }

            override fun onFinish() {
                binding.textView.text="Time:0"
                handler.removeCallbacks(runnable)
                for (image in imageviewArray){
                    image.visibility=View.INVISIBLE
                }
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")

                alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    val intentFromMain=intent
                    finish()
                    startActivity(intentFromMain)

                })
                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(this@MainActivity,"Game Over!",Toast.LENGTH_LONG).show()
                })
                alert.show()
            }

        }.start()


    }


    fun hideImages(){

        runnable=object :Runnable{
            override fun run() {
                for(image in imageviewArray){
                    image.visibility=View.INVISIBLE
                }
                val random=Random()
                val randomindex=random.nextInt(9)
                imageviewArray[randomindex].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)

            }

        }
        handler.post(runnable)


    }
    fun increasescore(view: View){
        score=score+1
        binding.textView2.text="Score:${score}"

    }

}