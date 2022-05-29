package devpaul.business.kidmathadmin.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.activities.operations.operationsdetail.ViewHistoricalStudentActivity
import devpaul.business.kidmathadmin.entities.Points

class PointsAdapter (private val context: Context, private val pointsList: MutableList<Points>) :
    RecyclerView.Adapter<PointsAdapter.MyViewHolder>() {

    var TAG = "OperationsAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemVIew = LayoutInflater.from(context).inflate(R.layout.item_users_operations, parent, false)
        return MyViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val category = pointsList[position]
        holder.textNombre.text = pointsList[position].name
        holder.textApellido.text = pointsList[position].lastname
        holder.textMejorPuntaje.text = pointsList[position].bestPoints
        holder.textUltimoIntento.text = pointsList[position].lastTry
        holder.textultimoacceso.text = pointsList[position].lastTimePlayed

        holder.itemView.setOnClickListener {
            goToSections(category)
        }
    }


    private fun goToSections(category: Points) {
        val type = category.type
        if (type == "Suma"){
            val i = Intent(context, ViewHistoricalStudentActivity::class.java)
            i.putExtra("type", category.type)
            i.putExtra("userId", category.userId)
            context.startActivity(i)
        }

        if (type == "Resta"){
            val i = Intent(context, ViewHistoricalStudentActivity::class.java)
            i.putExtra("type", category.type)
            i.putExtra("userId", category.userId)
            context.startActivity(i)
        }

        if (type == "Multiplicacion"){
            val i = Intent(context, ViewHistoricalStudentActivity::class.java)
            i.putExtra("type", category.type)
            i.putExtra("userId", category.userId)
            context.startActivity(i)
        }

        if (type == "Division"){
            val i = Intent(context, ViewHistoricalStudentActivity::class.java)
            i.putExtra("type", category.type)
            i.putExtra("userId", category.userId)
            context.startActivity(i)
        }


    }

    override fun getItemCount(): Int {
        return pointsList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textNombre: TextView
        var textApellido: TextView
        var textMejorPuntaje: TextView
        var textUltimoIntento: TextView
        var textultimoacceso: TextView


        init {
            textNombre = itemView.findViewById(R.id.text_name)
            textApellido = itemView.findViewById(R.id.text_lastname)
            textUltimoIntento = itemView.findViewById(R.id.text_lastTry)
            textMejorPuntaje = itemView.findViewById(R.id.text_bestPoints)
            textultimoacceso = itemView.findViewById(R.id.text_lastimeplayed)
        }
    }
}