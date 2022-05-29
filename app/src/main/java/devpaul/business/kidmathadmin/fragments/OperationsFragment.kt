package devpaul.business.kidmathadmin.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devpaul.business.kidmathadmin.Constants
import devpaul.business.kidmathadmin.HomeActivity
import devpaul.business.kidmathadmin.R
import devpaul.business.kidmathadmin.activities.operations.DivStudentActivity
import devpaul.business.kidmathadmin.activities.operations.MulStudentActivity
import devpaul.business.kidmathadmin.activities.operations.ResStudentActivity
import devpaul.business.kidmathadmin.activities.operations.SumStudentActivity
import devpaul.business.kidmathadmin.adapter.PointsAdapter
import devpaul.business.kidmathadmin.entities.Points
import java.lang.Exception

class OperationsFragment : Fragment() , View.OnClickListener {

    var myView: View? = null
    //Buttons
    var btnSuma : CardView? = null
    var btnResta : CardView? = null
    var btnMultiplicacion : CardView? = null
    var btnDivision : CardView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_operations, container, false)

        btnSuma = myView?.findViewById(R.id.btnSuma)
        btnResta = myView?.findViewById(R.id.btnResta)
        btnMultiplicacion = myView?.findViewById(R.id.btnMultiplicacion)
        btnDivision = myView?.findViewById(R.id.btnDivision)

        btnSuma?.setOnClickListener(this)
        btnResta?.setOnClickListener(this)
        btnMultiplicacion?.setOnClickListener(this)
        btnDivision?.setOnClickListener(this)

        return myView
    }

    override fun onClick(view: View?) {
        when (view) {
            btnSuma -> goSumaView()
            btnResta -> goRestView()
            btnMultiplicacion -> goMulView()
            btnDivision -> goDivView()

        }
    }

    private fun goSumaView(){
        val i = Intent(requireContext(),SumStudentActivity::class.java)
        startActivity(i)
    }


    private fun goRestView(){
        val i = Intent(requireContext(),ResStudentActivity::class.java)
        startActivity(i)
    }

    private fun goMulView(){
        val i = Intent(requireContext(),MulStudentActivity::class.java)
        startActivity(i)
    }

    private fun goDivView(){
        val i = Intent(requireContext(),DivStudentActivity::class.java)
        startActivity(i)
    }

}