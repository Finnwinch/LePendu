package ca.rosemont.native2.controller

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import ca.rosemont.native2.R
import ca.rosemont.native2.model.Dao
import ca.rosemont.native2.model.DatabaseHelper_Dictionnary
import ca.rosemont.native2.model.Word
import ca.rosemont.native2.view.GamePage
import ca.rosemont.native2.view.LobbyPage
import ca.rosemont.native2.view.NewWordPage
import kotlin.random.Random

class Game(val context: Context) {
    private val tag : String = "[Game Controller]"
    private var disableButtons : MutableList<Button> = mutableListOf()
    private var currentLetter : Char? = null
    lateinit var wordStr : String
    var images : List<Int> = listOf(
        R.drawable.err01,
        R.drawable.err02,
        R.drawable.err03,
        R.drawable.err04,
        R.drawable.err05,
        R.drawable.err06,
    )
    var attempt : Int = 0
    var score : Int = 0
    var letterToFound : Int = 0
    lateinit var word : Word
    var words : List<Word> = Lobby.DAO.getAllWord().filter { word ->
        word.difficult == Lobby.difficult
    }
    enum class doClickAction {
        HINT,RETURN
    }
    init {
        updateWord()
        GamePage.hint.setOnClickListener{
            Log.i(tag, "hint is been call")
            indice()
        }
        GamePage.back.setOnClickListener{
            Log.i(tag, "goto lobby is been call")
            context.startActivity(Intent(context,LobbyPage::class.java))
        }
    }
    fun incrementAttempt() {
        GamePage.gameView.setImageResource(images[attempt])
        this.attempt++
    }
    fun incrementScore() {
        this.score++
        GamePage.score.text = score.toString()
    }
    fun incrementToFound() {
        letterToFound--
    }
    fun updateWord() {
        if (words.isEmpty()) {
            context.startActivity(Intent(context,NewWordPage::class.java).putExtra("level",Lobby.difficult))
            (context as? AppCompatActivity)?.finishAffinity()
            return
        }
        words.forEachIndexed { index, word ->
            println(index.toString() + " " + if (Lobby.isFrench) word.wordFr else word.wordEn + " " +  word.id + " " + word.difficult)
        }
        word = words.random()
        wordStr = if (Lobby.isFrench) word.wordFr else word.wordEn
        letterToFound = wordStr!!.length
        var str = ""
        for (i in 1..letterToFound) {
            str += "#"
        }
        GamePage.word.text = str
    }

    fun useLetter(letter: Char) {
        val letterisValid = inspectIfLetterIsValid(letter)
        updateInterface(letterisValid)
        isWin()
    }

    private fun inspectIfLetterIsValid(letter: Char): Boolean {
        return wordStr!!.contains(letter, true)
    }

    private fun updateInterface(found: Boolean) {
        if (found) {
            val currentWord = GamePage.word.text.toString().toCharArray()
            wordStr!!.forEachIndexed { index, char ->
                if (wordStr!![index].toUpperCase() == currentLetter) {
                    currentWord[index] = currentLetter!!
                    incrementToFound()
                }
            }
            GamePage.word.text = String(currentWord)
            return
        }
        incrementAttempt()
        if (attempt == 6) {
            score
            GamePage.score.text = "0"
            Toast.makeText(context, "Vous avez perdu", Toast.LENGTH_LONG).show()
            new()
        }
        return
    }

    private fun indice(): Char? {
        var currentWord = GamePage.word.text.toString()
        var letterToHelp : Char = '?'
        currentWord.forEachIndexed { index, char ->
            if (char == '#') {
                val sourceWord = wordStr!!.toCharArray()
                val letterToHelp = sourceWord[index]
                currentLetter = letterToHelp.toUpperCase()
                for (rowButton in listOf(GamePage.rowA, GamePage.rowB, GamePage.rowC, GamePage.rowD)) {
                    rowButton.forEach { button ->
                        if (button is Button) {
                            if (button.text.toString().toCharArray()[0] == this.currentLetter) {
                                disableButtons.add(button)
                                disableButton(button)
                            }
                        }
                    }
                }
                return letterToHelp.toUpperCase()
            }
        }
        return null
    }

    private fun isWin() {
        if (letterToFound == 0) {
            incrementScore()
            new()
            Toast.makeText(context, "Vous avez gagnez", Toast.LENGTH_LONG).show()
        }
    }

    private fun new() {
        attempt = 0
        updateWord()
        var cache = ""
        for (i in 1..letterToFound) {
            cache += "#"
        }
        GamePage.word.text = cache
        for (button in disableButtons) {
            button.isEnabled = true
        }
        GamePage.gameView.setImageResource(R.drawable.acceuil)
    }

    fun disableButton(button: Button) {
        button.isEnabled = false
        disableButtons.add(button)
        currentLetter = button.text.toString()[0]
        useLetter(currentLetter!!)
    }

    fun doClick(option: doClickAction) {
        when (option) {
            doClickAction.HINT -> GamePage.hint.performClick()
            doClickAction.RETURN -> GamePage.back.performClick()
        }
    }

}