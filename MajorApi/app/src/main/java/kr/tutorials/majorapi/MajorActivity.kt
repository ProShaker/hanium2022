package kr.tutorials.majorapi

import DataPackage_Careernet_List.DatasetCareernetList
import DataPackage_Careernet_Specific.DatasetCareernetSpecific
import Var_Set.*
import Var_Set.UrlSet.BASE_URL_Careernet
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kr.tutorials.majorapi.databinding.ActivityMajorBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

// Log.d TAG 상수화
private const val TAG = "칠전팔기"

// MajorActivity 객체 바인딩 변수
var mBinding : ActivityMajorBinding? = null
val majorBinding get() = mBinding!!

class MajorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMajorBinding.inflate(layoutInflater)
        setContentView(majorBinding.root)

        // 검색 결과가 존재하지 않을 때 프래그먼트를 보여줌
        supportFragmentManager.beginTransaction().add(R.id.BaseFrameFragment, BlankFragment()).commit()


        // 프로세스 작동 순서
        // 대학 유형 선택 selectUnivType
        // 전공 유형 선택 selectMajorType
        // 대학/전공 유형에 따른 API 호출 getListData
        // 리사이클러뷰에 학과명 표시
        // 학과명 선택시 관련 정보 프래그먼트에 표시 getSpecificData

        selectUnivType()

        // 리사이클러뷰에 뷰 연결
        majorBinding.MajorNameRV.layoutManager = LinearLayoutManager(this)

        majorBinding. {
            supportFragmentManager.beginTransaction().add(R.id.BaseFrameFragment, MajorSummary()).commit()
        }
    }
}

// 대학 유형을 선택하는 함수 -> 전공 유형 선택
private fun selectUnivType() {

    // 라디오 버튼으로 일반대학, 전문대학 구분하여 가져올 api 선택
    majorBinding.radiogroupUnivType.setOnCheckedChangeListener { _, checkedId ->
        when (checkedId) {

            // 선택에 따른 엔드포인트 변화
            R.id.radioButton_Univ -> {
                selectMajorType()
            }
            R.id.radioButton_College -> {
                UnivType = false
                selectMajorType()
            }
        }
    }
}

//라디오 버튼 중 전공 유형을 선택하는 함수 -> 대학/전공 유형에 따른 API 호출
fun selectMajorType() {

    //전공 계열을 선택하면 관련 과목명을 불러옴
    majorBinding.MajorType.setOnCheckedChangeListener { _, i ->
        println("전공 유형 버튼 선택되는 중")
        when (i) {
            R.id.radioButton_humanities -> majorTypeName = MajorSubject["인문계열"]
            R.id.radioButton_social -> majorTypeName = MajorSubject["사회계열"]
            R.id.radioButton_education -> majorTypeName = MajorSubject["교육계열"]
            R.id.radioButton_enginerring -> majorTypeName = MajorSubject["공학계열"]
            R.id.radioButton_nature -> majorTypeName = MajorSubject["자연계열"]
            R.id.radioButton_medicine -> majorTypeName = MajorSubject["의약계열"]
            R.id.radioButton_artphysical -> majorTypeName = MajorSubject["예체능계열"]
        }

        // 대학 유형에 따라 커리어넷 API 호출
        if(UnivType)
            getListData(retrofitInstance().getUnivMajorList(majorTypeName))
        else
            getListData(retrofitInstance().getCollegeMajorList(majorTypeName))
    }
}

//레트로핏 인스턴스 실행
fun retrofitInstance(): APIMajor {

    // 로깅 인터셉터 사용을 위해 선언
    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    //오류 방지를 위한 gson 객체 생성
    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL_Careernet)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    return retrofitBuilder.create(APIMajor::class.java)
}

// 대학/전공 유형에 따른 API 호출
fun getListData(MajorData: Call<DatasetCareernetList>) {
    MajorData.enqueue(object : Callback<DatasetCareernetList> {
        override fun onResponse(
            call: Call<DatasetCareernetList>,
            response: Response<DatasetCareernetList>
        ) {
            if (response.isSuccessful) {

                // 정상적으로 통신이 성공한 경우
                Log.d(TAG, "---리스트 데이터 onResponse 성공--- ")

                // 이전에 선택한 학과 목록이 나오지 않도록 초기화
                majorNameList.clear()
                majorNumbList.clear()

                // 받아온 Gson 데이터를 Json 으로 변환
                val tempJson = Gson().toJson(response.body())

                // Json 데이터 형식에 맞춰서 데이터 추출
                val tempJsonList = JSONObject(tempJson).getJSONObject("dataSearch").getJSONArray("content")

                // 반복문을 통해 학과명, 학과번호 리스트 완성
               for(i in 0 until tempJsonList.length())
                {
                    majorNameList.add(tempJsonList.getJSONObject(i).getString("mClass"))
                    majorNumbList.add(tempJsonList.getJSONObject(i).getString("majorSeq"))
                }

                // 리사이클러뷰 어댑터에 리스트 전달
               majorBinding.MajorNameRV.adapter = RecyclerViewAdapter(majorNameList, majorNumbList)

            } else {
                // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                Log.d(TAG, "onResponse 실패")
            }
        }

        override fun onFailure(call: Call<DatasetCareernetList>, t: Throwable) {
            // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
            Log.d(TAG, "onFailure 에러: " + t.message.toString())
        }
    })
}

// 선택한 학과명에 따른 API 호출
fun getSpecificData(MajorData: Call<DatasetCareernetSpecific>) {
    MajorData.enqueue(object : Callback<DatasetCareernetSpecific> {
        override fun onResponse(
            call: Call<DatasetCareernetSpecific>,
            response: Response<DatasetCareernetSpecific>
        ) {
            if (response.isSuccessful) {

                // 정상적으로 통신이 성공한 경우
                Log.d(TAG, "--------상세 데이터 onResponse 성공: -------")

                //받아온 Gson 데이터를 Json 으로 변환
                val tempJson = Gson().toJson(response.body())

                //Json 데이터 형식에 맞춰서 데이터 추출
                val tempJsonList = JSONObject(tempJson).getJSONObject("dataSearch").getJSONArray("content")
                println("전공명 : $majorName 번호 : $majorSeqNumb ")
                println(tempJsonList.getJSONObject(0).getString("interest"))
                println(tempJsonList.getJSONObject(0).getString("summary"))

                println("변수에 값 할당")
                majorSummary = tempJsonList.getJSONObject(0).getString("summary")
                majorInterest = tempJsonList.getJSONObject(0).getString("interest")


            } else {
                // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                Log.d(ContentValues.TAG, "onResponse 실패")
            }
        }

        override fun onFailure(call: Call<DatasetCareernetSpecific>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
}


















