package ca.rosemont.native2.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.rosemont.native2.R
import ca.rosemont.native2.controller.Game
import ca.rosemont.native2.controller.Lobby
import org.w3c.dom.Text

class GamePage : AppCompatActivity(),OnClickListener {
    private val tag : String = "[Game Page]"
    companion object {
        lateinit var gameView: ImageView
        lateinit var hint : Button
        lateinit var back : Button
        lateinit var score : TextView
        lateinit var word : TextView
        lateinit var rowA : LinearLayout
        lateinit var rowB : LinearLayout
        lateinit var rowC : LinearLayout
        lateinit var rowD : LinearLayout
        lateinit var controller : Game
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initalizationComposents()
        controller = Game(this)
    }
    override fun onClick(v: View?) {
        if (v is Button) {
            controller.disableButton(v)
        }
        when (v?.id) {
            R.id.hint -> controller.doClick(Game.doClickAction.HINT)
            R.id.back -> controller.doClick(Game.doClickAction.RETURN)
        }
    }
    private fun initalizationComposents() {
        gameView = findViewById(R.id.gameView)
        hint = findViewById(R.id.hint)
        hint.setOnClickListener(this)
        back = findViewById(R.id.back)
        back.setOnClickListener(this)
        score = findViewById(R.id.score)
        word = findViewById(R.id.word)
        rowA = findViewById(R.id.rowA)
        rowB = findViewById(R.id.rowB)
        rowC = findViewById(R.id.rowC)
        val difficultLevel : TextView = findViewById(R.id.difficultLevel)
        difficultLevel.text = when (Lobby.difficult) {
            0x01.toByte() -> this.getString(R.string.levelEasy)
            0x02.toByte() -> this.getString(R.string.levelMedium)
            else -> this.getString(R.string.levelHard)
        }
        rowD = findViewById(R.id.rowD)
        ('A'..'Z').forEachIndexed { index, name ->
            when {
                name in 'A'..'H' -> rowA.addView(generateButton(name))
                name in 'I'..'P' -> rowB.addView(generateButton(name))
                name in 'Q'..'X' -> rowC.addView(generateButton(name))
                name in 'Y'..'Z' -> rowD.addView(generateButton(name))
            }
        }

    }
    private fun generateButton(name: Any): Button {
        val button = Button(this)
        button.setOnClickListener(this)
        button.text = name.toString()
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.weight = 1f
        button.layoutParams = params
        return button
    }

}