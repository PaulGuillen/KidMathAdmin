package devpaul.business.kidmathadmin.activities.operations

import android.annotation.SuppressLint
import android.app.Dialog
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devpaul.business.kidmathadmin.Constants
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.adapter.PointsAdapter
import devpaul.business.kidmathadmin.entities.Points
import java.lang.Exception

class SumStudentActivity : AppCompatActivity() {

    var TAG = "ViewAllSection"

    var myView: View? = null
    private val db = Firebase.firestore
    private var recyclerViewAll: RecyclerView? = null

    //ViewAllSection
    var viewAllList = ArrayList<Points>()
    private var viewAllAdapter: PointsAdapter? = null

    var shimmerFrameLayout : ShimmerFrameLayout? = null

    var linearnoData: LinearLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sum_student)

        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout)

        linearnoData = findViewById(R.id.linearlayout_nodata)

        recyclerViewAll = findViewById(R.id.recyclerView)
        recyclerViewAll?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerViewAll?.setHasFixedSize(true)
        viewAllAdapter = PointsAdapter(this, viewAllList)
        recyclerViewAll?.adapter = viewAllAdapter

        if (isOnline()){
            getDatSumStudents()
        }else{
            getConnectionValidation()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDatSumStudents() {
        db.collection(Constants.PATH_POINTS_SUM).orderBy("lastname", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documents ->
                if(!documents.isEmpty){
                    for (document in documents) {
                        linearnoData?.visibility = View.GONE
                        shimmerFrameLayout?.visibility = View.GONE
                        recyclerViewAll?.visibility = View.VISIBLE
                        val section = document.toObject(Points::class.java)
                        viewAllList.add(section)
                        viewAllAdapter?.notifyDataSetChanged()
                    }
                }else{
                    linearnoData?.visibility = View.VISIBLE
                    shimmerFrameLayout?.visibility = View.GONE
                    recyclerViewAll?.visibility = View.GONE
                }

            }
            .addOnFailureListener { exception ->
                shimmerFrameLayout?.visibility = View.GONE
                Log.w(TAG, "Error getting documents: ", exception)
            }

    }

    private fun getConnectionValidation() {
        try {
            val customDialog = Dialog(this)
            customDialog.setContentView(R.layout.connection_dialog)
            customDialog.show()
            val mylamda = Thread {
                for (x in 0..10) {
                    Thread.sleep(3500)
                    customDialog.dismiss()
                }
            }
            startThread(mylamda)
        } catch (e: Exception) {
            Log.v(TAG, "Error: $e");
        }
    }

    private fun startThread(mylamda: Thread) {
        mylamda.start()
    }

    @Suppress("DEPRECATION")
    private fun isOnline(): Boolean {
        val conMgr = this.applicationContext
            .getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conMgr.activeNetworkInfo
        if (netInfo == null || !netInfo.isConnected || !netInfo.isAvailable) {
            Log.v(TAG, "Error: $netInfo");
            /*     Toast.makeText(this@ViewAllSectionActivity, "Sin conexion a internet!", Toast.LENGTH_LONG).show()*/
            return false
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout?.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        shimmerFrameLayout?.startShimmerAnimation()
    }

}