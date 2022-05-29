package devpaul.business.kidmathadmin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import devpaul.business.kidmathadmin.fragments.*
import java.util.*

class HomeActivity : AppCompatActivity() {

    val operationsFragment = OperationsFragment()
    val newsFragment = NewsFragment()
    val studFragment = StudentsFragment()

    @SuppressLint("SourceLockedOrientationActivity", "WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(operationsFragment)
        //desactivar rotacion pantalla
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_operaciones -> replaceFragment(operationsFragment)
                R.id.ic_noticias -> replaceFragment(newsFragment)
                R.id.ic_alumnos -> replaceFragment(studFragment)
            }
             true

        }

    }

    fun recreateFragment(fragment : Fragment){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            supportFragmentManager.beginTransaction().detach(fragment).commitNow()
            supportFragmentManager.beginTransaction().attach(fragment).commitNow()
        }else{
            supportFragmentManager.beginTransaction().detach(fragment).attach(fragment).commitNow()
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