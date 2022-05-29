package devpaul.business.kidmathadmin.activities.news

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devpaul.business.kidmathadmin.Constants
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.adapter.NewsAdapter
import devpaul.business.kidmathadmin.entities.News
import java.util.*

class NewsActivity : AppCompatActivity() {

    var TAG = "NewsActivity"

    @Suppress("DEPRECATION")
    var progressDialog: ProgressDialog? = null

    var recyclerViewAll: RecyclerView? = null

    var btnActualizar: Button? = null

    //ViewAllSection
    var viewAllList = ArrayList<News>()
    private var viewAllAdapter: NewsAdapter? = null

    private val db = Firebase.firestore

    var linearnoData: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        progressDialog = ProgressDialog(this)

        linearnoData = findViewById(R.id.linearlayout_nodata)

        btnActualizar = findViewById(R.id.btn_actualizar)
        btnActualizar?.setOnClickListener {
            this.recreate()
        }

        recyclerViewAll = findViewById(R.id.recyclerView)
        recyclerViewAll?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewAllAdapter = NewsAdapter(this, viewAllList)
        recyclerViewAll?.adapter = viewAllAdapter

        loadRecycler()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadRecycler(){
        progressDialog!!.show()
        progressDialog?.setContentView(R.layout.charge_dialog)
        Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
        db.collection(Constants.PATH_NEWS).orderBy("fecha", Query.Direction.ASCENDING)
            .addSnapshotListener { value, error ->
                if (value?.isEmpty!!){
                    linearnoData?.visibility = View.VISIBLE
                    recyclerViewAll?.visibility = View.GONE
                    progressDialog?.dismiss()
                }else{
                    for (doc in value.documentChanges) {
                        linearnoData?.visibility = View.GONE
                        recyclerViewAll?.visibility = View.VISIBLE
                        progressDialog?.dismiss()
                        val section = doc.document.toObject(News::class.java)
                        viewAllList.add(section)
                        viewAllAdapter?.notifyDataSetChanged()
                    }
                }
                if (error != null) {
                    Log.v(TAG,"Error $error")
                    progressDialog?.dismiss()
                    linearnoData?.visibility = View.VISIBLE
                    recyclerViewAll?.visibility = View.GONE
                    return@addSnapshotListener
                }
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        progressDialog?.dismiss()
    }
}