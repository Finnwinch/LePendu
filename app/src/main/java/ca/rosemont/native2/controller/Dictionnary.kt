package ca.rosemont.native2.controller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import ca.rosemont.native2.view.DictionnaryPage
import ca.rosemont.native2.view.LobbyPage
import ca.rosemont.native2.view.NewWordPage

class Dictionnary(val context : Context){
    private val tag : String = "[Dictionnary Controller]"
    init {
        DictionnaryPage.addWord.setOnClickListener{
            context.startActivity(Intent(context,NewWordPage::class.java))
        }
        DictionnaryPage.gotoLobby.setOnClickListener{
            context.startActivity(Intent(context,LobbyPage::class.java))
            (context as? AppCompatActivity)?.finishAffinity()
        }

    }
    enum class doClickAction {
        NEW,RETURN
    }
    fun doClick(options : doClickAction) {
        when(options) {
            doClickAction.NEW -> DictionnaryPage.addWord.performClick()
            doClickAction.RETURN -> DictionnaryPage.gotoLobby.performClick()
        }
    }
}