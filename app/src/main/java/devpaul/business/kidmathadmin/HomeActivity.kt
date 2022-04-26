package devpaul.business.kidmathadmin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import devpaul.business.kidmathadmin.fragments.*
import java.util.*

class HomeActivity : AppCompatActivity() {

    val sumFragment = SumFragment()
    val restFragment = RestFragment()
    val multFragment = MultFragment()
    val divFragment = DivFragment()
    val studFragment = StudentsFragment()

    @SuppressLint("SourceLockedOrientationActivity", "WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(sumFragment)
        //desactivar rotacion pantalla
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_suma -> replaceFragment(sumFragment)
                R.id.ic_resta -> replaceFragment(restFragment)
                R.id.ic_multiplicacion -> replaceFragment(multFragment)
                R.id.ic_division -> replaceFragment(divFragment)
                R.id.ic_alumnos -> replaceFragment(studFragment)
            }
             true

        }

    }


    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()

    }


    override fun onBackPressed() {
        super.onBackPressed()
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }



}