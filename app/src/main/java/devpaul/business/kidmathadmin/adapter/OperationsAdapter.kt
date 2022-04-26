package devpaul.business.kidmathadmin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.entities.Operations

class OperationsAdapter (private val context: Context, private val operationsList: MutableList<Operations>) :
    RecyclerView.Adapter<OperationsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemVIew = LayoutInflater.from(context).inflate(R.layout.item_users_operations, parent, false)
        return MyViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.textNombre.text = operationsList[position].name
        holder.textApellido.text = operationsList[position].lastname
        holder.textMejorPuntaje.text = operationsList[position].bestPoints
        holder.textUltimoIntento.text = operationsList[position].lastTry
        holder.textultimoacceso.text = operationsList[position].lastTimePlayed

        holder.itemView.setOnClickListener {
          /*  Toast.makeText(context,"Usuario : "+operationsList[position].name,Toast.LENGTH_LONG).show()*/
        }
    }

    override fun getItemCount(): Int {
        return operationsList.size
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