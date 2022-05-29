package devpaul.business.kidmathadmin.fragments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import devpaul.business.kidmathadmin.Constants
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.activities.news.NewsActivity
import devpaul.business.kidmathadmin.adapter.NewsAdapter
import devpaul.business.kidmathadmin.entities.News
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class NewsFragment : Fragment() {

    var TAG = "NewsFragment"

    var myView: View? = null

    var btnUpload: Button? = null
    var btnVer: Button? = null

    var textTitulo: EditText ? = null
    var textDescription: EditText ? = null
    var textId: TextView ? = null


    private val File = 1
    private val db = Firebase.firestore

    @Suppress("DEPRECATION")
    var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_news, container, false)

        btnUpload = myView?.findViewById(R.id.btn_subir)
        btnVer = myView?.findViewById(R.id.btn_ver_noticias)
        btnVer?.setOnClickListener {
            viewNews()
        }

        textTitulo = myView?.findViewById(R.id.titulo)
        textDescription = myView?.findViewById(R.id.descripcion)

        progressDialog = ProgressDialog(requireContext())

        btnUpload?.setOnClickListener {
            fileUpload()
        }


        return  myView
    }

    private  fun fileUpload(){
       val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, File)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        progressDialog!!.show()
        progressDialog?.setContentView(R.layout.charge_dialog)
        Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)

        if (requestCode == File){
            if (resultCode == RESULT_OK){
                val FileUri = data!!.data
                val Folder : StorageReference =
                    FirebaseStorage.getInstance().reference.child("Images")
                val file_name : StorageReference = Folder.child("file"+FileUri!!.lastPathSegment)
                file_name.putFile(FileUri).addOnSuccessListener { taskSnapshot ->
                    file_name.downloadUrl.addOnSuccessListener { uri ->

                        val titulo = textTitulo?.text
                        val descripcion = textDescription?.text

                        val date = getCurrentDateTime()
                        val todayDay = date.toString("yyyy/MM/dd")

                        val randomUUID = UUID.randomUUID().toString()

                        val hashMap = HashMap<String, String>()

                        hashMap["idnew"] = randomUUID
                        hashMap["imagen"] = java.lang.String.valueOf(uri)
                        hashMap["titulo"] = titulo.toString()
                        hashMap["descripcion"] = descripcion.toString()
                        hashMap["fecha"] = todayDay

                        if (titulo!!.isEmpty() || descripcion!!.isEmpty()){
                            progressDialog?.dismiss()
                            Toast.makeText(requireContext(),"Llenar los campos vacíos",Toast.LENGTH_LONG).show()
                        }else{
                            db.collection(Constants.PATH_NEWS).document(randomUUID).set(hashMap)
                            clearData()
                            progressDialog?.dismiss()
                            Toast.makeText(requireContext(),"Se subió la notica correctamente", Toast.LENGTH_LONG).show()
                        }

                    }
                }
            }else{
                progressDialog?.dismiss()
                Log.v(TAG,"Error $resultCode")
            }
        }
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun viewNews(){
        val i = Intent(requireContext(),NewsActivity::class.java)
        startActivity(i)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private  fun clearData(){
        textDescription?.text?.clear()
        textTitulo?.text?.clear()
    }




}