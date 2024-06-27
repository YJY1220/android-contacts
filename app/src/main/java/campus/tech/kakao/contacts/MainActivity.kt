package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //view 요소들 선언하는 거
    private lateinit var noContactsText: TextView //맨 처음 설명 텍스트
    private lateinit var contactsRecyclerView: RecyclerView //스크롤
    private lateinit var addContactButton: FloatingActionButton //플로팅 + 버튼
    private val contactsList = mutableListOf<Contact>() //리스트 조회

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //처음 view 요소들 초기화
        noContactsText = findViewById(R.id.noContactsText)
        contactsRecyclerView = findViewById(R.id.contactsRecyclerView) //조회 스크롤 - RecyclerView로 설정
        addContactButton = findViewById(R.id.addContactButton) //연락처 추가 버튼 : + 버튼

        //Recyclerview에 LinearLayoutManager로 수직으로 배열 - 스크롤 가능하도록 조회 목록 형식으로 만들어야 하기 때문
        //또한, 연락처 항목 클릭 시 세부 정보 보여주는 화면(detail)으로 전환하는 기능
        contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        contactsRecyclerView.adapter = ContactsAdapter(contactsList) {  //adapter를 설정해서 list관리함 - 뷰 바인딩
            contact ->
            val intent = Intent(this, ContactDetailActivity::class.java)
            /*클릭된 연락처 객체(content)를 intent에 담아서 contact detail activity로 전달함 */
            intent.putExtra("contact", contact)
            startActivity(intent) //intent로 담아서 전달
        }

        //연락처 추가 버튼 click listner
        //floatingactioinbutton 눌렀을 때 수행 작업 정의 클릭 리스터
        addContactButton.setOnClickListener {
            //새로운 intent 객체 생성 - intent : 애플리케이션 구성 요소 간 작업 수행 - 다른 액티비티 시작 및 전달
            //this - context : 여기선 mainactivity의 context
            val intent = Intent(this, AddContactActivity::class.java) //addcontactactivity 클래스의 'class' 객체 - 즉 입력한 정보들 담은 class
            startActivityForResult(intent, REQUEST_CODE_ADD_CONTACT) //add 후 결과 받기
            //startActivityForResult - 새 activity 시작 후 그 activity 종료되면 결과 받음
            //REQUEST_CODE_ADD_CONTACT : 결과 구분 위한 요청 코드 - 상수임
        }

        updateUI()
    }

    //연락처 추가 후 결과 받았을 때 UI 업데이트 하는 거
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_CONTACT && resultCode == RESULT_OK) {
            data?.getParcelableExtra<Contact>("contact")?.let { contact ->
                contactsList.add(contact) // 연락처 리스트에 추가 함
                updateUI() // update
            }
        }
    }

    //UI update method
    private fun updateUI() {
        if (contactsList.isEmpty()) { //리스트 비어있는지 확인해야함
            //만약 비어있다면
            noContactsText.visibility = TextView.VISIBLE //안내 텍스트 보이게
            contactsRecyclerView.visibility = RecyclerView.GONE //recyclerview 없앰
        } else { //만약 있다면
            noContactsText.visibility = TextView.GONE //안내 텍스트 삭제
            contactsRecyclerView.visibility = RecyclerView.VISIBLE //recyclerview 생기게
            contactsRecyclerView.adapter?.notifyDataSetChanged() //adapter에 데이터 변경 알리기
        }
    }

    //연락처 추가 요청 코드
    companion object {
        const val REQUEST_CODE_ADD_CONTACT = 1 //1로 요청하는 것 - 구별
    }
}
