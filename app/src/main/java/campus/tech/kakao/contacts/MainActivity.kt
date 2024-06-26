package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val birthEditText = findViewById<TextView>(R.id.birthTextView)
        val memoEditText = findViewById<EditText>(R.id.memoEditText)
        val moreLayout = findViewById<LinearLayout>(R.id.moreLayout)
        val moreTextView = findViewById<TextView>(R.id.moreTextView)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        val femaleRadioButton = findViewById<RadioButton>(R.id.femaleRadioButton)
        val maleRadioButton = findViewById<RadioButton>(R.id.maleRadioButton)

        moreTextView.setOnClickListener {
            toggleMore()
        }

        birthEditText.setOnClickListener {
            showDatePicker()
        }

        cancelButton.setOnClickListener {
            //clearFields()
            Toast.makeText(this, "취소되었습니다", Toast.LENGTH_SHORT).show()
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

            Toast.makeText(this, "저장이 완료되었습니다", Toast.LENGTH_SHORT).show()
            //clearFields()
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
                findViewById<TextView>(R.id.birthTextView).text = formattedDate // 이 부분 수정
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun toggleMore() {
        val moreLayout = findViewById<LinearLayout>(R.id.moreLayout)
        val moreTextView = findViewById<TextView>(R.id.moreTextView)

        if (moreLayout.visibility == LinearLayout.GONE) {
            moreLayout.visibility = LinearLayout.VISIBLE
            moreTextView.visibility = TextView.GONE
        } else {
            moreLayout.visibility = LinearLayout.GONE
            moreTextView.visibility = TextView.VISIBLE
        }
    }

    /*private fun clearFields() {
        findViewById<EditText>(R.id.nameEditText).text.clear()
        findViewById<EditText>(R.id.phoneEditText).text.clear()
        findViewById<EditText>(R.id.emailEditText).text.clear()
        findViewById<EditText>(R.id.birthTextView).text.clear()
        findViewById<EditText>(R.id.memoEditText).text.clear()
        findViewById<RadioGroup>(R.id.genderRadioGroup).clearCheck()
        findViewById<LinearLayout>(R.id.moreLayout).visibility = LinearLayout.GONE
        findViewById<TextView>(R.id.moreTextView).visibility = TextView.VISIBLE
    }*/
}
