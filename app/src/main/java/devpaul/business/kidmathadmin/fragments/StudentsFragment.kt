package devpaul.business.kidmathadmin.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devpaul.business.kidmathadmin.Constants
import devpaul.business.kidmathadmin.MainActivity
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.adapter.OperationsAdapter
import devpaul.business.kidmathadmin.adapter.UserAdapter
import devpaul.business.kidmathadmin.entities.Operations
import devpaul.business.kidmathadmin.entities.User

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = Firebase.auth
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_students, container, false)

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
        Log.v(TAG, "View created")
        viewAllList.clear()
        recyclerViewAll?.adapter = viewAllAdapter
        viewAllAdapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onPause() {
        super.onPause()
        Log.v(TAG, "On Pause")
        viewAllList.clear()
        viewAllAdapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDataFromFirestore(): Boolean {
        db.collection(Constants.PATH_USERS).whereEqualTo("rol","alumno").orderBy("lastname", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val section = document.toObject(User::class.java)
                    viewAllList.add(section)
                    viewAllAdapter?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
        return true
    }

}