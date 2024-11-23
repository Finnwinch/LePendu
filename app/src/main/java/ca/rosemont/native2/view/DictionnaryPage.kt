package ca.rosemont.native2.view

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.rosemont.native2.R
import ca.rosemont.native2.controller.Dictionnary
import ca.rosemont.native2.controller.Lobby
import ca.rosemont.native2.model.RecyclerAdapter
import ca.rosemont.native2.model.Word

class DictionnaryPage : AppCompatActivity(),OnClickListener {
    companion object {
        lateinit var addWord : Button
        lateinit var gotoLobby : Button
        lateinit var recycler : RecyclerView
        lateinit var adapter : RecyclerAdapter
        lateinit var controller : Dictionnary
    }
    private fun initalizationComposents() {
        addWord = findViewById(R.id.addWord)
        addWord.setOnClickListener(this)
        gotoLobby = findViewById(R.id.gotoLobby)
        gotoLobby.setOnClickListener(this)
        recycler = findViewById(R.id.recycle)
        recycler.setOnClickListener(this)
        controller = Dictionnary(this)
    }
    private fun recycle() {
        adapter = RecyclerAdapter(this, Lobby.DAO.getAllWord())
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        adapter.onItemClick = {obj -> }
        recycler.layoutManager = layoutManager
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionnary)
        initalizationComposents()
        recycle()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.addWord -> controller.doClick(Dictionnary.doClickAction.NEW)
            R.id.gotoLobby -> controller.doClick(Dictionnary.doClickAction.RETURN)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.list = Lobby.DAO.getAllWord()
        adapter.notifyDataSetChanged()
    }
}