package `in`.bitcode.intentusagedemo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import `in`.bitcode.intentusagedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder().build()
        )

        binding.btnSendBroadcast.setOnClickListener {
            val intent = Intent("in.bitcode.download.COMPLETE")
            intent.putExtra("path", binding.edtPath.text.toString())
            //sendBroadcast(intent)
            sendStickyBroadcast(intent)
        }

        binding.btnViewImage.setOnClickListener {
            val intent = Intent("in.bitcode.media.VIEW")
            intent.setDataAndType(
                Uri.parse("file://${binding.edtPath.text.toString()}"),
                "image/png"
            )
            startActivity(intent)
        }

        binding.btnViewImageInGal.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(
                Uri.parse("file://${binding.edtPath.text.toString()}"),
                "image/png"
            )
            startActivity(intent)
        }

        binding.btnPlayAudio.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(
                Uri.parse("file://${binding.edtPath.text.toString()}"),
                "audio/mp3"
            )
            startActivity(intent)
        }

        binding.btnPlayVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(
                Uri.parse("file://${binding.edtPath.text.toString()}"),
                "video/mp4"
            )
            startActivity(intent)
        }

        binding.btnWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(binding.edtPath.text.toString())
            startActivity(intent)
        }

        binding.btnCall.setOnClickListener {
            //val intent = Intent(Intent.ACTION_DIAL)
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse(binding.edtPath.text.toString())
            startActivity(intent)
        }

        binding.btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setDataAndType(
                Uri.parse("file://${binding.edtPath.text.toString()}"),
                "video/mp4"
            )
            startActivity(intent)
        }

        binding.img.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(
                intent,
                1
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null) {
            binding.img.setImageURI(data.data)
        }
    }
}