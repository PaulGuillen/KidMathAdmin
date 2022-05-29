package devpaul.business.kidmathadmin.fragments

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devpaul.business.kidmathadmin.Constants
import devpaul.business.kidmathadmin.MainActivity
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.adapter.UserAdapter
import devpaul.business.kidmathadmin.entities.User
import java.util.*
import kotlin.collections.ArrayList

class StudentsFragment : Fragment() {

    var TAG = "ViewAllSection"

    var btnSalir : Button? = null
    var myView: View? = null
    private val db = Firebase.firestore
    private var recyclerViewAll: RecyclerView? = null

    //ViewAllSection
    var viewAllList = ArrayList<User>()
    private var viewAllAdapter: UserAdapter? = null
    private lateinit var auth: FirebaseAuth

    var linearnoData: LinearLayout? = null

    @Suppress("DEPRECATION")
    var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        linearnoData = myView?.findViewById(R.id.linearlayout_nodata)

        auth = Firebase.auth
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_students, container, false)

        progressDialog = ProgressDialog(requireContext())

        recyclerViewAll = myView?.findViewById(R.id.recyclerView)!!
        recyclerViewAll?.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewAllAdapter = UserAdapter(requireContext(), viewAllList)
        recyclerViewAll?.adapter = viewAllAdapter

        btnSalir = myView?.findViewById(R.id.btn_salir)
        btnSalir?.setOnClickListener {
            auth.signOut()
            val i = Intent(requireContext(), MainActivity::class.java)
            Toast.makeText(requireContext(),"Vuelva pronto", Toast.LENGTH_LONG).show()
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Eliminar el historial de pantallas
            startActivity(i)
        }

        return myView
    }

    override fun onStart() {
        super.onStart()
        if (getDataFromFirestore()) {
            Log.v(TAG, "Success to get data")
        } else {
            Log.v(TAG, "Cant get data")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAllList.clear()
        recyclerViewAll?.adapter = viewAllAdapter
        viewAllAdapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onPause() {
        super.onPause()
        viewAllList.clear()
        viewAllAdapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDataFromFirestore(): Boolean {
        progressDialog!!.show()
        progressDialog?.setContentView(R.layout.charge_dialog)
        Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
        db.collection(Constants.PATH_USERS).whereEqualTo("rol","alumno").orderBy("lastname", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty){
                    progressDialog?.dismiss()
                    linearnoData?.visibility = View.VISIBLE
                    recyclerViewAll?.visibility = View.GONE
                }else{
                    progressDialog?.dismiss()
                    linearnoData?.visibility = View.GONE
                    recyclerViewAll?.visibility = View.VISIBLE
                    for (document in documents) {
                        val section = document.toObject(User::class.java)
                        viewAllList.add(section)
                        viewAllAdapter?.notifyDataSetChanged()
                    }
                }
            }
            .addOnFailureListener { exception ->
                progressDialog?.dismiss()
                linearnoData?.visibility = View.VISIBLE
                recyclerViewAll?.visibility = View.GONE
                Log.w(TAG, "Error getting documents: ", exception)
            }
        return true
    }

}