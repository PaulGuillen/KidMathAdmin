package devpaul.business.kidmathadmin.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.activities.operations.operationsdetail.ViewHistoricalStudentActivity
import devpaul.business.kidmathadmin.activities.userdetail.StudentDetailActivity
import devpaul.business.kidmathadmin.entities.User

class UserAdapter  (private val context: Context, private val operationsList: MutableList<User>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    var TAG = "UserAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemVIew =
            LayoutInflater.from(context).inflate(R.layout.item_users, parent, false)
        return MyViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val category = operationsList[position]

        holder.textNombre.text = operationsList[position].name
        holder.textApellido.text = operationsList[position].lastname
        holder.textCorreo.text = operationsList[position].email
        holder.textRol.text = operationsList[position].rol

        holder.itemView.setOnClickListener {
            goToSections(category)
        }

    }


    private fun goToSections(category: User) {

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.operations_dialog)

        val btnSuma = dialog.findViewById(R.id.btnSuma) as CardView
        val btnResta = dialog.findViewById(R.id.btnResta) as CardView
        val btnMultiplicacion = dialog.findViewById(R.id.btnMultiplicacion) as CardView
        val btnDivision = dialog.findViewById(R.id.btnDivision) as CardView
        val btnCerrar = dialog.findViewById(R.id.btn_cerrar) as Button

        btnCerrar.setOnClickListener {
            dialog.dismiss()
        }

        btnSuma.setOnClickListener {
            val i = Intent(context, StudentDetailActivity::class.java)
            i.putExtra("userId", category.userId)
            i.putExtra("type", "suma")
            context.startActivity(i)
            dialog.dismiss()
            Log.v(TAG,"userId: ${category.userId}")
        }
        btnResta.setOnClickListener {
            val i = Intent(context, StudentDetailActivity::class.java)
            i.putExtra("userId", category.userId)
            i.putExtra("type", "resta")
            context.startActivity(i)
            dialog.dismiss()
            Log.v(TAG,"userId: ${category.userId}")
        }
        btnMultiplicacion.setOnClickListener {
            val i = Intent(context, StudentDetailActivity::class.java)
            i.putExtra("userId", category.userId)
            i.putExtra("type", "multiplicacion")
            context.startActivity(i)
            dialog.dismiss()
            Log.v(TAG,"userId: ${category.userId}")
        }

        btnDivision.setOnClickListener {
            val i = Intent(context, StudentDetailActivity::class.java)
            i.putExtra("userId", category.userId)
            i.putExtra("type", "division")
            context.startActivity(i)
            dialog.dismiss()
            Log.v(TAG,"userId: ${category.userId}")
        }
        dialog.show()


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