package ca.rosemont.native2.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ca.rosemont.native2.R
import ca.rosemont.native2.controller.Dictionnary
import ca.rosemont.native2.controller.Lobby
import ca.rosemont.native2.view.DictionnaryPage
import ca.rosemont.native2.view.NewWordPage

class RecyclerAdapter(val context: Context,var list : List<Word>):
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){
    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        var id : TextView
        var english : TextView
        var french : TextView
        var easy : ImageView
        var medium : ImageView
        var hard : ImageView
        init {
            id = itemView.findViewById(R.id.id)
            english = itemView.findViewById(R.id.english)
            french = itemView.findViewById(R.id.french)
            easy = itemView.findViewById(R.id.easy)
            medium = itemView.findViewById(R.id.medium)
            hard = itemView.findViewById(R.id.hard)
        }
    }
    var onItemClick: ((Word)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.item_dictionnary,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.id.text = list[position].id.toString()
        holder.french.text = list[position].wordFr
        holder.english.text = list[position].wordEn
        if (list[position].difficult==0x01.toByte()) {
            holder.easy.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
        } else if (list[position].difficult==0x02.toByte()) {
            holder.easy.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
            holder.medium.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
        } else {
            holder.easy.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
            holder.medium.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
            holder.hard.setImageResource(context.resources.getIdentifier("btn_star_big_on", "drawable", "android"))
        }
        holder.itemView.setOnClickListener{
            Lobby.DAO.removeWord((list[position]))
            context.startActivity(Intent(context,DictionnaryPage::class.java))
            (context as? AppCompatActivity)?.finishAffinity()
        }
    }
}
