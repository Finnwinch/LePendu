package ca.rosemont.native2.controller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import ca.rosemont.native2.model.Word
import ca.rosemont.native2.view.DictionnaryPage
import ca.rosemont.native2.view.NewWordPage
import kotlin.properties.Delegates

class DictionnaryWord(val context: Context) {
    private var difficult by Delegates.notNull<Byte>()

    init {
        NewWordPage.easy.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
        difficult = 0x01
        NewWordPage.save.setOnClickListener{
            if (!NewWordPage.french_inp.text.toString().isEmpty() and !NewWordPage.english_inp.text.toString().isEmpty()) {
                Lobby.DAO.insertWord(Word(Lobby.DAO.getSize(),NewWordPage.french_inp.text.toString().uppercase(),NewWordPage.english_inp.text.toString().uppercase(),difficult))
                context.startActivity(Intent(context,DictionnaryPage::class.java))
                (context as? AppCompatActivity)?.finishAffinity()
            }
        }
        NewWordPage.easy.setOnClickListener{
            NewWordPage.easy.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
            NewWordPage.medium.setImageResource(context.resources.getIdentifier("btn_star_big_off", "drawable", "android"))
            NewWordPage.hard.setImageResource(context.resources.getIdentifier("btn_star_big_off", "drawable", "android"))
            difficult = 0x01
        }
        NewWordPage.medium.setOnClickListener{
            NewWordPage.easy.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
            NewWordPage.medium.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
            NewWordPage.hard.setImageResource(context.resources.getIdentifier("btn_star_big_off", "drawable", "android"))
            difficult = 0x02
        }
        NewWordPage.hard.setOnClickListener{
            NewWordPage.easy.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
            NewWordPage.medium.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
            NewWordPage.hard.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
            difficult = 0x03
        }
    }
    enum class doClickAction {
        SAVE,SET_EASY,SET_MEDIUM,SET_HARD
    }
    fun doClick(options : doClickAction) {
        when(options) {
            doClickAction.SAVE -> NewWordPage.save.performClick()
            doClickAction.SET_EASY -> NewWordPage.easy.performClick()
            doClickAction.SET_MEDIUM -> NewWordPage.medium.performClick()
            doClickAction.SET_HARD -> NewWordPage.hard.performClick()
        }
    }
}