package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class ContactAddActivity : AppCompatActivity() {
    private lateinit var nameText: EditText
    private lateinit var phoneText: EditText
    private lateinit var emailText: EditText
    private lateinit var birthText: TextView
    private lateinit var memoText: EditText
    private lateinit var moreLayout: LinearLayout
    private lateinit var moreText: TextView
    private lateinit var genderRadio: RadioGroup
    private lateinit var cancelButton: Button
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_add)

        nameText = findViewById(R.id.nameText)
        phoneText = findViewById(R.id.phoneText)
        emailText = findViewById(R.id.emailText)
        birthText = findViewById(R.id.birthText)
        memoText = findViewById(R.id.memoText)
        moreLayout = findViewById(R.id.moreLayout)
        moreText = findViewById(R.id.moreText)
        genderRadio = findViewById(R.id.genderRadio)
        cancelButton = findViewById(R.id.cancelButton)
        saveButton = findViewById(R.id.saveButton)

        //호출
        moreText.setOnClickListener {
            toggleMore()
        }

        birthText.setOnClickListener {
            showDatePicker()
        }

        cancelButton.setOnClickListener {
            Toast.makeText(this,"취소되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        saveButton.setOnClickListener {
            val name = nameText.text.toString().trim()
            val phone = phoneText.text.toString().trim()
            val email = emailText.text.toString().trim()
            val birth = birthText.text.toString().trim()
            val memo = memoText.text.toString().trim()

            //이름, 전화번호 미 입력 시 저장 불가
            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "이름과 전화번호는 필수 입력 값입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //성별 선택을 하지 않는 경우 아무것도 안떠야함 - "" 경우가 있어야 함
            //val gender = if (genderRadio.checkedRadioButtonId == R.id.femaleButton) {
            //    "여성"
            //} else if (genderRadio.checkedRadioButtonId == R.id.maleButton) {
            //    "남성"
            //}

            //성별 선택
            val gender = when (genderRadio.checkedRadioButtonId) {
                R.id.femaleButton -> "여성"
                R.id.maleButton -> "남성"
                else -> ""
            }

            //contact 객체 생성
            val contact = Contact(name, phone, email, birth, gender, memo)

            //intent로 결과 반환
            val resultIntent = Intent()
            resultIntent.putExtra("contact", contact)
            setResult(RESULT_OK, resultIntent)

            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    //더보기 토글 기능 - 표시 및 숨기기
    private fun toggleMore(){
        val moreLayout = findViewById<LinearLayout>(R.id.moreLayout)
        val moreText = findViewById<TextView>(R.id.moreText)

        if(moreLayout.visibility == LinearLayout.GONE){
            moreLayout.visibility = LinearLayout.VISIBLE
            moreText.visibility = TextView.GONE
        }
        else {
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
                val setting = SimpleDateFormat("yyyy.mm.dd", Locale.getDefault())
                val formDate = setting.format(selectedDate.time)
                findViewById<TextView>(R.id.birthText).text = formDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }
}


