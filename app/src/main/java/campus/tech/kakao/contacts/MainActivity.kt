package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val birthEditText = findViewById<EditText>(R.id.birthEditText)
        val memoEditText = findViewById<EditText>(R.id.memoEditText)
        val moreLayout = findViewById<LinearLayout>(R.id.moreLayout)
        val moreTextView = findViewById<TextView>(R.id.moreTextView)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        val femaleRadioButton = findViewById<RadioButton>(R.id.femaleRadioButton)
        val maleRadioButton = findViewById<RadioButton>(R.id.maleRadioButton)

        moreTextView.setOnClickListener {
            if (moreLayout.visibility == LinearLayout.GONE) {
                moreLayout.visibility = LinearLayout.VISIBLE
                moreTextView.text = "" // 수정된 부분: 더보기 텍스트를 지웁니다.
            } else {
                moreLayout.visibility = LinearLayout.GONE
                moreTextView.text = "더보기"
            }
        }

        birthEditText.setOnClickListener {
            showDatePicker()
        }

        cancelButton.setOnClickListener {
            clearFields()
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val birth = birthEditText.text.toString().trim()
            val memo = memoEditText.text.toString().trim()

            val gender = when (genderRadioGroup.checkedRadioButtonId) {
                R.id.femaleRadioButton -> "여성"
                R.id.maleRadioButton -> "남성"
                else -> ""
            }

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "이름과 전화번호는 필수 값입니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 여기에 저장 로직 추가

            Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
            clearFields()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
                val formattedDate = sdf.format(selectedDate.time)
                findViewById<EditText>(R.id.birthEditText).setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePicker.show()
    }

    private fun clearFields() {
        findViewById<EditText>(R.id.nameEditText).text.clear()
        findViewById<EditText>(R.id.phoneEditText).text.clear()
        findViewById<EditText>(R.id.emailEditText).text.clear()
        findViewById<EditText>(R.id.birthEditText).text.clear()
        findViewById<EditText>(R.id.memoEditText).text.clear()
        findViewById<RadioGroup>(R.id.genderRadioGroup).clearCheck()
        findViewById<LinearLayout>(R.id.moreLayout).visibility = LinearLayout.GONE
        findViewById<TextView>(R.id.moreTextView).text = "더보기"
    }
}
