package ca.rosemont.native2.controller

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ca.rosemont.native2.R
import ca.rosemont.native2.model.Dao
import ca.rosemont.native2.model.DatabaseHelper_Dictionnary
import ca.rosemont.native2.view.DictionnaryPage
import ca.rosemont.native2.view.GamePage
import ca.rosemont.native2.view.LobbyPage
import java.util.Locale

class Lobby(var context : Context) {
    private val tag : String = "[Lobby Controller]"
    companion object {
        var isFrench : Boolean = true
        var difficult : Byte = 0x01
        lateinit var DAO : Dao
    }
    init {
        DAO = Dao(DatabaseHelper_Dictionnary(context))
    }
    enum class doClickAction {
        PLAY, LANGUAGE, LEVEL, DICTIONNARY, HISTORY, CREDIT
    }
    fun doClick(options : doClickAction) {
        when(options) {
            doClickAction.PLAY -> {
                Log.i(tag,"perfom play")
                context.startActivity(Intent(context,GamePage::class.java))
                (context as? AppCompatActivity)?.finishAffinity()
            }
            doClickAction.LANGUAGE -> {
                context.resources.updateConfiguration(Configuration(context.resources.configuration).apply {
                    setLocale(if (isFrench) LobbyPage.CANADA_ENGLISH else Locale.CANADA_FRENCH)
                }, context.resources.displayMetrics)
                context.startActivity(Intent(context,LobbyPage::class.java))
                (context as? AppCompatActivity)?.finishAffinity()
                isFrench = !isFrench
            }
            doClickAction.LEVEL -> {
                Log.i(tag,"perfom LEVEL")
                if (difficult == 0x01.toByte()) {
                    difficult = 0x02.toByte()
                    LobbyPage.level.text = context.getString(R.string.levelMedium)
                } else if (difficult == 0x02.toByte()) {
                    difficult = 0x03.toByte()
                    LobbyPage.level.text = context.getString(R.string.levelHard)
                } else {
                    difficult = 0x01.toByte()
                    LobbyPage.level.text = context.getString(R.string.levelEasy)
                }
            }
            doClickAction.DICTIONNARY -> {
                Log.i(tag,"perfom dictionnary")
                context.startActivity(Intent(context,DictionnaryPage::class.java))
                (context as? AppCompatActivity)?.finishAffinity()
            }
            doClickAction.HISTORY -> {
                Log.i(tag,"perfom history")
                //context.startActivity(Intent(context,?))
                (context as? AppCompatActivity)?.finishAffinity()
            }
            doClickAction.CREDIT -> {
                Log.i(tag,"perfom credit")
                //context.startActivity(Intent(context,?))
                (context as? AppCompatActivity)?.finishAffinity()
            }
        }
    }
    init {
        if (difficult == 0x02.toByte()) {
            LobbyPage.level.text = context.getString(R.string.levelMedium)
        } else if (difficult == 0x03.toByte()) {
            LobbyPage.level.text = context.getString(R.string.levelHard)
        } else {
            LobbyPage.level.text = context.getString(R.string.levelEasy)
        }
    }
}