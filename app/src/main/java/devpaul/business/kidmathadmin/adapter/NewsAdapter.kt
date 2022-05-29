package devpaul.business.kidmathadmin.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.entities.News


class NewsAdapter(
    val context: Activity,
    var viewAS: ArrayList<News>
) :
    RecyclerView.Adapter<NewsAdapter.ViewAllSectionViewHolder>() {

    private var TAG = "ViewAllSection"
    private val db = Firebase.firestore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAllSectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewAllSectionViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewAllSectionViewHolder, position: Int) {

        val newsSection = viewAS[position]

        val options: RequestOptions = RequestOptions()
            .error(R.drawable.childrens)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
        holder.imageView?.let {
            Glide.with(context).load(newsSection.imagen).apply(options).into(it)
        }

        holder.textViewTitulo?.text = newsSection.titulo
        holder.textViewDescription?.text = newsSection.descripcion
        holder.textViewFecha?.text = newsSection.fecha

        holder.itemView.setOnClickListener {
            SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Estas seguro en elimnar esto?")
                .setContentText("No podrás recuperar esta información")
                .setCancelText("No")
                .setConfirmText("Si")
                .setConfirmClickListener {
                    db.collection("News").document(newsSection.idnew.toString())
                        .delete()
                        .addOnSuccessListener { success ->
                            Log.w(TAG, "Deleting document $success")
                            Toast.makeText(context,"Noticia eliminada", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
                    it.dismiss()
                }
                .showCancelButton(true)
                .setCancelClickListener { sDialog ->
                    sDialog.cancel()
                }.show()
        }

    }


    override fun getItemCount(): Int {
        return viewAS.size
    }

    class ViewAllSectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textViewTitulo: TextView? = null
        var textViewDescription: TextView? = null
        var textViewFecha: TextView? = null

        var imageView: ImageView? = null

        init {

            imageView = view.findViewById(R.id.view_img)

            textViewTitulo = view.findViewById(R.id.titulo_new)
            textViewDescription = view.findViewById(R.id.description_new)
            textViewFecha = view.findViewById(R.id.fecha_new)

        }
    }


}


