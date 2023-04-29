package br.edu.ifsp.scl.ads.sendmail

import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.ads.sendmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.cleanBtn.setOnClickListener {
            with (viewBinding) {
                toTxt.setText("")
                ccTxt.setText("")
                bccTxt.setText("")
                subjectTxt.setText("")
                messageTxt.setText("")
            }
        }

        viewBinding.sendBtn.setOnClickListener {
            val sendMailIntent = Intent(ACTION_SENDTO)

            with (sendMailIntent) {
                putExtra(EXTRA_EMAIL, arrayOf(viewBinding.toTxt.text.toString()))
                putExtra(EXTRA_CC, arrayOf(viewBinding.ccTxt.text.toString()))
                putExtra(EXTRA_BCC, arrayOf(viewBinding.bccTxt.text.toString()))
                putExtra(EXTRA_SUBJECT, arrayOf(viewBinding.subjectTxt.text.toString()))
                putExtra(EXTRA_TEXT, arrayOf(viewBinding.messageTxt.text.toString()))
                type = "message/rfc822"
                data = Uri.parse("mailto:")
            }

            val chooserIntent = Intent(Intent.ACTION_CHOOSER)
            chooserIntent.putExtra(Intent.EXTRA_INTENT, sendMailIntent)
            startActivity(chooserIntent)
        }
    }
}