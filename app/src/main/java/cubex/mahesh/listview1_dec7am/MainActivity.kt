package cubex.mahesh.listview1_dec7am

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val status = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.READ_EXTERNAL_STORAGE)
        if(status == PackageManager.PERMISSION_GRANTED){
            readFiles()
        }else{
          ActivityCompat.requestPermissions(
              this@MainActivity,
              arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
              111)
        }
    } // onCreate

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            readFiles()
        }else{
            System.exit(0)
        }

    }

    fun readFiles( ){

        var path = "/storage/emulated/0/WhatsApp/Media/WhatsApp Images/"
        var file = File(path)
        if(!file.exists()){
             path = "/storage/sdcard0/WhatsApp/Media/WhatsApp Images/"
             file = File(path)
        }
        var files:Array<File> = file.listFiles()
     /*   var myadapter = ArrayAdapter<String>(
            this@MainActivity,
            android.R.layout.simple_list_item_single_choice,
            files)
        lview.adapter = myadapter */
        var myadpter = object:BaseAdapter(){
            override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

                var inflater = LayoutInflater.from(this@MainActivity)

                var v = inflater.inflate(R.layout.indiview,null)

                return  v
            }
            override fun getItem(p0: Int): Any {
                return 0
            }
            override fun getItemId(p0: Int): Long {
                return  0
            }
            override fun getCount(): Int {
                return files.size
            }
        }

        lview.adapter  = myadpter

    } // readFiles( )

}
