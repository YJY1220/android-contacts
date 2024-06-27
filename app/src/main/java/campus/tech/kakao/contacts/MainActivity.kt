package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val contacts = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameText = findViewById<EditText>(R.id.nameText)
        val phoneText = findViewById<EditText>(R.id.phoneText)
        val emailText = findViewById<EditText>(R.id.emailText)
        val birthText = findViewById<TextView>(R.id.birthText)
        val memoText = findViewById<EditText>(R.id.memoText)
        val moreText = findViewById<TextView>(R.id.moreText)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val genderRadio = findViewById<RadioGroup>(R.id.genderRadio)
        val femaleButton = findViewById<RadioButton>(R.id.femaleButton)
        val maleButton = findViewById<RadioButton>(R.id.maleButton)
        val listView = findViewById<ListView>(R.id.listView)

        adapter = ContactAdapter(this, contacts)
        listView.adapter = adapter

        moreText.setOnClickListener {
            toggleMore()
        }

        birthText.setOnClickListener {
            showDatePicker()
        }

        cancelButton.setOnClickListener {
            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show()
        }

        saveButton.setOnClickListener {
            val name = nameText.text.toString().trim()
            val phone = phoneText.text.toString().trim()
            val email = emailText.text.toString().trim()
            val birth = birthText.text.toString().trim()
            val memo = memoText.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "이름과 전화번호는 필수 입력 값입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gender = if (genderRadio.checkedRadioButtonId == R.id.femaleButton) {
                "여성"
            } else {
                "남성"
            }

            val contact = Contact(name, phone, email, birth, gender, memo)
            contacts.add(contact)
            adapter.notifyDataSetChanged()

            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val contact = contacts[position]
            showContactDetails(contact)
        }
    }

    private fun toggleMore() {
        val moreLayout = findViewById<LinearLayout>(R.id.moreLayout)
        val moreText = findViewById<TextView>(R.id.moreText)

        if (moreLayout.visibility == LinearLayout.GONE) {
            moreLayout.visibility = LinearLayout.VISIBLE
            moreText.visibility = TextView.GONE
        } else {
            moreLayout.visibility = LinearLayout.GONE
            moreText.visibility = TextView.VISIBLE
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, day ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, day)
                val setting = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
                val formDate = setting.format(selectedDate.time)
                findViewById<TextView>(R.id.birthText).text = formDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showContactDetails(contact: Contact) {
        val intent = Intent(this, ContactDetailActivity::class.java).apply {
            putExtra("contact", contact)
        }
        startActivity(intent)
    }
}
