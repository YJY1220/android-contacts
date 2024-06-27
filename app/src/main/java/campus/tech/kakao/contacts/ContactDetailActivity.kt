package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val profileImageView = findViewById<ImageView>(R.id.profileImageView)
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val phoneTextView = findViewById<TextView>(R.id.phoneTextView)
        val emailTextView = findViewById<TextView>(R.id.emailTextView)
        val birthTextView = findViewById<TextView>(R.id.birthTextView)
        val genderTextView = findViewById<TextView>(R.id.genderTextView)
        val memoTextView = findViewById<TextView>(R.id.memoTextView)

        val contact = intent.getParcelableExtra<Contact>("contact")

        contact?.let {
            nameTextView.text = "이름: ${it.name}"
            phoneTextView.text = "전화번호: ${it.phone}"
            emailTextView.text = "이메일: ${it.email}"
            birthTextView.text = "생일: ${it.birth}"
            genderTextView.text = "성별: ${it.gender}"
            memoTextView.text = "메모: ${it.memo}"
        }
    }
}
