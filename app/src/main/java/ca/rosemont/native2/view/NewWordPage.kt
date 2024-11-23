package ca.rosemont.native2.view

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.rosemont.native2.R
import ca.rosemont.native2.controller.Dictionnary
import ca.rosemont.native2.controller.DictionnaryWord
import ca.rosemont.native2.controller.Lobby

class NewWordPage : AppCompatActivity(),OnClickListener {
    companion object {
        lateinit var id : TextView
        lateinit var english_inp : TextView
        lateinit var french_inp : TextView
        lateinit var easy : ImageView
        lateinit var medium : ImageView
        lateinit var hard : ImageView
        lateinit var save : Button
        lateinit var controller : DictionnaryWord
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionnary_word)
        initalizationComposents()
        controller = DictionnaryWord(this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.easy -> controller.doClick(DictionnaryWord.doClickAction.SET_EASY)
            R.id.medium -> controller.doClick(DictionnaryWord.doClickAction.SET_MEDIUM)
            R.id.hard -> controller.doClick(DictionnaryWord.doClickAction.SET_HARD)
            R.id.save -> controller.doClick(DictionnaryWord.doClickAction.SAVE)
        }
    }
    private fun initalizationComposents() {
        id = findViewById(R.id.id)
        id.text = Lobby.DAO.getSize().toString()
        english_inp = findViewById(R.id.english_inp)
        french_inp = findViewById(R.id.french_inp)
        easy = findViewById(R.id.easy)
        easy.setOnClickListener(this)
        medium = findViewById(R.id.medium)
        medium.setOnClickListener(this)
        hard = findViewById(R.id.hard)
        hard.setOnClickListener(this)
        save = findViewById(R.id.save)
        save.setOnClickListener(this)
    }
}