package max.bykov.mainprogress

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mainprogress.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        touchView.angleListener = {
            Log.d("!!!", "angel: $it")
        }
        touchView.setAngel(259f)
    }
}
