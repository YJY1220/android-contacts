package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val contact = intent.getParcelableExtra<Contact>("contact")

        val nameTextView = findViewById<TextView>(R.id.detailName)
        val phoneTextView = findViewById<TextView>(R.id.detailPhone)
        val emailTextView = findViewById<TextView>(R.id.detailEmail)
        val birthTextView = findViewById<TextView>(R.id.detailBirth)
        val genderTextView = findViewById<TextView>(R.id.detailGender)
        val memoTextView = findViewById<TextView>(R.id.detailMemo)

        contact?.let {
            nameTextView.text = it.name
            phoneTextView.text = it.phone
            emailTextView.text = it.email
            birthTextView.text = it.birth
            genderTextView.text = it.gender
            memoTextView.text = it.memo
        }
    }
}
