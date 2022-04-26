package devpaul.business.kidmathadmin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.entities.User

class UserAdapter  (private val context: Context, private val operationsList: MutableList<User>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemVIew =
            LayoutInflater.from(context).inflate(R.layout.item_users, parent, false)
        return MyViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.textNombre.text = operationsList[position].name
        holder.textApellido.text = operationsList[position].lastname
        holder.textCorreo.text = operationsList[position].email
        holder.textRol.text = operationsList[position].rol

        holder.itemView.setOnClickListener {
     /*       Toast.makeText(context, "Usuario : " + operationsList[position].name, Toast.LENGTH_LONG).show()*/
        }
    }

    override fun getItemCount(): Int {
        return operationsList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textNombre: TextView
        var textApellido: TextView
        var textCorreo: TextView
        var textRol: TextView


        init {
            textNombre = itemView.findViewById(R.id.text_name)
            textApellido = itemView.findViewById(R.id.text_lastname)
            textCorreo = itemView.findViewById(R.id.text_correo)
            textRol = itemView.findViewById(R.id.text_rol)
        }
    }
}