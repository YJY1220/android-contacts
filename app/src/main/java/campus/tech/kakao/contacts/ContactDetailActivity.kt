package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

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

        val nameLabelTextView = findViewById<TextView>(R.id.nameLabelTextView)
        val phoneLabelTextView = findViewById<TextView>(R.id.phoneLabelTextView)
        val emailLabelTextView = findViewById<TextView>(R.id.emailLabelTextView)
        val birthLabelTextView = findViewById<TextView>(R.id.birthLabelTextView)
        val genderLabelTextView = findViewById<TextView>(R.id.genderLabelTextView)
        val memoLabelTextView = findViewById<TextView>(R.id.memoLabelTextView)

        val contact = intent.getParcelableExtra<Contact>("contact")

        contact?.let {
            nameTextView.text = it.name
            phoneTextView.text = it.phone
            if (!it.email.isNullOrEmpty()) {
                emailTextView.text = it.email
            } else {
                emailTextView.visibility = TextView.GONE
                emailLabelTextView.visibility = TextView.GONE
            }

            if (!it.birth.isNullOrEmpty()) {
                birthTextView.text = it.birth
            } else {
                birthTextView.visibility = TextView.GONE
                birthLabelTextView.visibility = TextView.GONE
            }

            if (!it.gender.isNullOrEmpty()) {
                genderTextView.text = it.gender
            } else {
                genderTextView.visibility = TextView.GONE
                genderLabelTextView.visibility = TextView.GONE
            }

            if (!it.memo.isNullOrEmpty()) {
                memoTextView.text = it.memo
            } else {
                memoTextView.visibility = TextView.GONE
                memoLabelTextView.visibility = TextView.GONE
            }
        }
    }
}
