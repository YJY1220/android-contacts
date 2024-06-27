package campus.tech.kakao.contacts

import android.os.Parcel
import android.os.Parcelable

//전송하는 연락처 클래스
data class Contact(
    val name: String,
    val phone: String, //필수 값
    val email: String?, //null 가질 수 있음
    val birth: String?,
    val gender: String?,
    val memo: String?
    //Parcelable : android에서 객체 직렬화 인터페이스 - 이 데이터들을 intent나 bundle로 전달 가능
) : Parcelable {
    constructor(parcel: Parcel) : this( //Parcel 객체에서 데이터 읽어와서 Contact 구현
        parcel.readString() ?: "", //Parcel에서 문자열 읽음
        parcel.readString() ?: "", //여긴 null일 경우 빈 문자열 대신 사용
        parcel.readString(), //null 가능함
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    //Contact 객체 배열을 Parcel에 작성 -> 바이트스트림으로 변환해서 다른 acitivty에 전달해야함
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name) //Parcelable 구현하는 method - 객체를 Parcel에 사용하는 method
        parcel.writeString(phone)
        parcel.writeString(email)
        parcel.writeString(birth)
        parcel.writeString(gender)
        parcel.writeString(memo)
    }

    override fun describeContents(): Int { //Parcelable 구현 시 필수 - Parcel 직렬화할 때 필요 메서드
        return 0
    }

    //Parcelable 객체 생성 시 사용
    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact { //Parcel에서 contact 객체 생성
            // constructor(parcel: Parcel) 이거 호출해서 새로운 'contact' 객체 반환함
            return Contact(parcel)
        }

        //이 newArray method는 Parcelable 인터페이스가 객체 배열을 직렬화할 때 필수
        override fun newArray(size: Int): Array<Contact?> { //지정된 크기의 Contact 배열 생성함 - Parcelable 배열 필요시 사용
            return arrayOfNulls(size)
        }
    }
}
