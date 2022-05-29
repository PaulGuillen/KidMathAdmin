package devpaul.business.kidmathadmin.activities.operations.operationsdetail

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.adapter.PointsDetailedAdapter
import devpaul.business.kidmathadmin.entities.PointsDetailed
import java.util.*
import kotlin.collections.ArrayList

class ViewHistoricalStudentActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    var TAG = "ViewAllSection"
    var recyclerViewAll: RecyclerView? = null

    //ViewAllSection
    var viewAllList = ArrayList<PointsDetailed>()
    private var viewAllAdapter: PointsDetailedAdapter? = null

    @Suppress("DEPRECATION")
    var progressDialog: ProgressDialog? = null

    var linearnoData: LinearLayout? = null

    @SuppressLint("NotifyDataSetChanged", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_historical_student)

        @Suppress("DEPRECATION")
        progressDialog = ProgressDialog(this)
        //desactivar rotacion pantalla
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        linearnoData = findViewById(R.id.linearlayout_nodata)

        recyclerViewAll = findViewById(R.id.recyclerView)
        recyclerViewAll?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewAllAdapter = PointsDetailedAdapter(this, viewAllList)
        recyclerViewAll?.adapter = viewAllAdapter

        val idUser = intent.getStringExtra("userId")
        val type = intent.getStringExtra("type")

        if (type != null && type.equals("Suma", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)

            val docRef = db.collection("AllResultsSum").whereEqualTo("userId",idUser)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        for (document in document) {
                            linearnoData?.visibility = View.GONE
                            recyclerViewAll?.visibility = View.VISIBLE
                            progressDialog?.dismiss()
                            val section = document.toObject(PointsDetailed::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                        }
                    } else {
                        progressDialog?.dismiss()
                        linearnoData?.visibility = View.VISIBLE
                        recyclerViewAll?.visibility = View.GONE
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    progressDialog?.dismiss()
                    linearnoData?.visibility = View.VISIBLE
                    recyclerViewAll?.visibility = View.GONE
                    Log.d(TAG, "get failed with ", exception)
                }

        }

        if (type != null && type.equals("Resta", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
            val docRef = db.collection("AllResultsRes").whereEqualTo("userId",idUser)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        for (document in document) {
                            linearnoData?.visibility = View.GONE
                            recyclerViewAll?.visibility = View.VISIBLE
                            progressDialog?.dismiss()
                            val section = document.toObject(PointsDetailed::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                        }
                    } else {
                        progressDialog?.dismiss()
                        linearnoData?.visibility = View.VISIBLE
                        recyclerViewAll?.visibility = View.GONE
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    progressDialog?.dismiss()
                    linearnoData?.visibility = View.VISIBLE
                    recyclerViewAll?.visibility = View.GONE
                    Log.d(TAG, "get failed with ", exception)
                }

        }

        if (type != null && type.equals("Multiplicacion", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
            val docRef = db.collection("AllResultsMul").whereEqualTo("userId",idUser)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        for (document in document) {
                            linearnoData?.visibility = View.GONE
                            recyclerViewAll?.visibility = View.VISIBLE
                            progressDialog?.dismiss()
                            val section = document.toObject(PointsDetailed::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                        }
                    } else {
                        progressDialog?.dismiss()
                        linearnoData?.visibility = View.VISIBLE
                        recyclerViewAll?.visibility = View.GONE
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }

        }

        if (type != null && type.equals("Division", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
            val docRef = db.collection("AllResultsDiv").whereEqualTo("userId",idUser)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        for (document in document) {
                            progressDialog?.dismiss()
                            linearnoData?.visibility = View.GONE
                            recyclerViewAll?.visibility = View.VISIBLE
                            val section = document.toObject(PointsDetailed::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                        }
                    } else {
                        progressDialog?.dismiss()
                        linearnoData?.visibility = View.VISIBLE
                        recyclerViewAll?.visibility = View.GONE
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    progressDialog?.dismiss()
                    linearnoData?.visibility = View.VISIBLE
                    recyclerViewAll?.visibility = View.GONE
                    Log.d(TAG, "get failed with ", exception)
                }

        }



    }

}