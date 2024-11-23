package ca.rosemont.native2.view

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ca.rosemont.native2.R
import ca.rosemont.native2.controller.Lobby
import ca.rosemont.native2.model.Dao
import ca.rosemont.native2.model.DatabaseHelper_Dictionnary
import ca.rosemont.native2.model.Word
import java.util.Locale

class LobbyPage : AppCompatActivity(), OnClickListener {
    private val tag : String = "[Lobby Page]"
    companion object {
        lateinit var play : Button
        lateinit var language : Button
        lateinit var level : Button
        lateinit var dictionary : Button
        lateinit var history : Button
        lateinit var credit : Button
        val CANADA_ENGLISH = Locale("en", "CA")
        lateinit var controller : Lobby
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)
        initalizationComposents()
        controller = Lobby(this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.play -> {
                Log.i(tag,"try perfom play")
                controller.doClick(Lobby.doClickAction.PLAY)
            }
            R.id.language -> {
                Log.i(tag,"try perfom language")
                controller.doClick(Lobby.doClickAction.LANGUAGE)
            }
            R.id.level -> {
                Log.i(tag,"try perfom level")
                controller.doClick(Lobby.doClickAction.LEVEL)
            }
            R.id.dictionnary -> {
                Log.i(tag,"try perfom dictionnary")
                controller.doClick(Lobby.doClickAction.DICTIONNARY)
            }
            R.id.history -> {
                Log.i(tag,"try perfom history")
                controller.doClick(Lobby.doClickAction.HISTORY)
            }
            R.id.credit -> {
                Log.i(tag,"try perfom credit")
                controller.doClick(Lobby.doClickAction.CREDIT)
            }
        }
    }
    private fun initalizationComposents() {
        play = findViewById(R.id.play)
        play.setOnClickListener(this)
        language = findViewById(R.id.language)
        language.setOnClickListener(this)
        level = findViewById(R.id.level)
        level.setOnClickListener(this)
        dictionary = findViewById(R.id.dictionnary)
        dictionary.setOnClickListener(this)
        history = findViewById(R.id.history)
        history.setOnClickListener(this)
        credit = findViewById(R.id.credit)
        credit.setOnClickListener(this)
    }
}